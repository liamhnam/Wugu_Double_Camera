package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.p022io.RandomAccessRead;
import com.tom_roush.pdfbox.p022io.ScratchFile;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.encryption.AccessPermission;
import com.tom_roush.pdfbox.pdmodel.encryption.DecryptionMaterial;
import com.tom_roush.pdfbox.pdmodel.encryption.PDEncryption;
import com.tom_roush.pdfbox.pdmodel.encryption.PublicKeyDecryptionMaterial;
import com.tom_roush.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

public class PDFParser extends COSParser {
    private AccessPermission accessPermission;
    private PDEncryption encryption;
    private String keyAlias;
    private InputStream keyStoreInputStream;
    private String password;

    public PDFParser(RandomAccessRead randomAccessRead) throws IOException {
        this(randomAccessRead, "", ScratchFile.getMainMemoryOnlyInstance());
    }

    public PDFParser(RandomAccessRead randomAccessRead, ScratchFile scratchFile) throws IOException {
        this(randomAccessRead, "", scratchFile);
    }

    public PDFParser(RandomAccessRead randomAccessRead, String str) throws IOException {
        this(randomAccessRead, str, ScratchFile.getMainMemoryOnlyInstance());
    }

    public PDFParser(RandomAccessRead randomAccessRead, String str, ScratchFile scratchFile) throws IOException {
        this(randomAccessRead, str, null, null, scratchFile);
    }

    public PDFParser(RandomAccessRead randomAccessRead, String str, InputStream inputStream, String str2) throws IOException {
        this(randomAccessRead, str, inputStream, str2, ScratchFile.getMainMemoryOnlyInstance());
    }

    public PDFParser(RandomAccessRead randomAccessRead, String str, InputStream inputStream, String str2, ScratchFile scratchFile) throws IOException {
        super(randomAccessRead);
        this.password = "";
        this.keyStoreInputStream = null;
        this.keyAlias = null;
        this.encryption = null;
        this.fileLen = randomAccessRead.length();
        this.password = str;
        this.keyStoreInputStream = inputStream;
        this.keyAlias = str2;
        init(scratchFile);
    }

    private void init(ScratchFile scratchFile) throws IOException {
        String property = System.getProperty(COSParser.SYSPROP_EOFLOOKUPRANGE);
        if (property != null) {
            try {
                setEOFLookupRange(Integer.parseInt(property));
            } catch (NumberFormatException unused) {
                Log.w("PdfBox-Android", "System property com.tom_roush.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '" + property + "'");
            }
        }
        this.document = new COSDocument(scratchFile);
    }

    public PDDocument getPDDocument() throws IOException {
        PDDocument pDDocument = new PDDocument(getDocument(), this.source, this.accessPermission);
        pDDocument.setEncryptionDictionary(this.encryption);
        return pDDocument;
    }

    protected void initialParse() throws IOException {
        COSDictionary cOSDictionaryRebuildTrailer;
        long startxrefOffset = getStartxrefOffset();
        if (startxrefOffset > -1) {
            cOSDictionaryRebuildTrailer = parseXref(startxrefOffset);
        } else {
            cOSDictionaryRebuildTrailer = isLenient() ? rebuildTrailer() : null;
        }
        prepareDecryption();
        COSBase trailerValuesDynamically = parseTrailerValuesDynamically(cOSDictionaryRebuildTrailer);
        if (!(trailerValuesDynamically instanceof COSDictionary)) {
            throw new IOException("Expected root dictionary, but got this: " + trailerValuesDynamically);
        }
        COSDictionary cOSDictionary = (COSDictionary) trailerValuesDynamically;
        if (isLenient() && !cOSDictionary.containsKey(COSName.TYPE)) {
            cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.CATALOG);
        }
        COSObject catalog = this.document.getCatalog();
        if (catalog != null && (catalog.getObject() instanceof COSDictionary)) {
            parseDictObjects((COSDictionary) catalog.getObject(), null);
            COSBase dictionaryObject = cOSDictionaryRebuildTrailer.getDictionaryObject(COSName.INFO);
            if (dictionaryObject instanceof COSDictionary) {
                parseDictObjects((COSDictionary) dictionaryObject, null);
            }
            this.document.setDecrypted();
        }
        this.initialParseDone = true;
    }

    public void parse() throws IOException {
        try {
            if (!parsePDFHeader() && !parseFDFHeader()) {
                throw new IOException("Error: Header doesn't contain versioninfo");
            }
            if (!this.initialParseDone) {
                initialParse();
            }
            IOUtils.closeQuietly(this.keyStoreInputStream);
        } catch (Throwable th) {
            IOUtils.closeQuietly(this.keyStoreInputStream);
            if (this.document != null) {
                IOUtils.closeQuietly(this.document);
                this.document = null;
            }
            throw th;
        }
    }

    private void prepareDecryption() throws IOException {
        DecryptionMaterial standardDecryptionMaterial;
        COSBase item = this.document.getTrailer().getItem(COSName.ENCRYPT);
        if (item == null || (item instanceof COSNull)) {
            return;
        }
        if (item instanceof COSObject) {
            parseDictionaryRecursive((COSObject) item);
        }
        try {
            this.encryption = new PDEncryption(this.document.getEncryptionDictionary());
            if (this.keyStoreInputStream != null) {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(this.keyStoreInputStream, this.password.toCharArray());
                standardDecryptionMaterial = new PublicKeyDecryptionMaterial(keyStore, this.keyAlias, this.password);
            } else {
                standardDecryptionMaterial = new StandardDecryptionMaterial(this.password);
            }
            this.securityHandler = this.encryption.getSecurityHandler();
            this.securityHandler.prepareForDecryption(this.encryption, this.document.getDocumentID(), standardDecryptionMaterial);
            this.accessPermission = this.securityHandler.getCurrentAccessPermission();
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            throw new IOException("Error (" + e2.getClass().getSimpleName() + ") while creating security handler for decryption", e2);
        }
    }

    private void parseDictionaryRecursive(COSObject cOSObject) throws IOException {
        parseObjectDynamically(cOSObject, true);
        for (COSBase cOSBase : ((COSDictionary) cOSObject.getObject()).getValues()) {
            if (cOSBase instanceof COSObject) {
                COSObject cOSObject2 = (COSObject) cOSBase;
                if (cOSObject2.getObject() == null) {
                    parseDictionaryRecursive(cOSObject2);
                }
            }
        }
    }
}
