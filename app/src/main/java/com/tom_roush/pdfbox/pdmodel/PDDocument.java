package com.tom_roush.pdfbox.pdmodel;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.p022io.MemoryUsageSetting;
import com.tom_roush.pdfbox.p022io.RandomAccessBuffer;
import com.tom_roush.pdfbox.p022io.RandomAccessBufferedFileInputStream;
import com.tom_roush.pdfbox.p022io.RandomAccessRead;
import com.tom_roush.pdfbox.p022io.ScratchFile;
import com.tom_roush.pdfbox.pdfparser.PDFParser;
import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.encryption.AccessPermission;
import com.tom_roush.pdfbox.pdmodel.encryption.PDEncryption;
import com.tom_roush.pdfbox.pdmodel.encryption.ProtectionPolicy;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandler;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandlerFactory;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDField;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDSignatureField;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import okhttp3.internal.http2.Http2Connection;

public class PDDocument implements Closeable {
    private AccessPermission accessPermission;
    private boolean allSecurityToBeRemoved;
    private final COSDocument document;
    private PDDocumentCatalog documentCatalog;
    private Long documentId;
    private PDDocumentInformation documentInformation;
    private PDEncryption encryption;
    private final Set<PDFont> fontsToSubset;
    private final RandomAccessRead pdfSource;
    private ResourceCache resourceCache;
    private SignatureInterface signInterface;

    public PDDocument() {
        this(MemoryUsageSetting.setupMainMemoryOnly());
    }

    public PDDocument(MemoryUsageSetting memoryUsageSetting) {
        ScratchFile scratchFile;
        this.fontsToSubset = new HashSet();
        this.resourceCache = new DefaultResourceCache();
        try {
            scratchFile = new ScratchFile(memoryUsageSetting);
        } catch (IOException e) {
            Log.w("PdfBox-Android", "Error initializing scratch file: " + e.getMessage() + ". Fall back to main memory usage only.");
            try {
                scratchFile = new ScratchFile(MemoryUsageSetting.setupMainMemoryOnly());
            } catch (IOException unused) {
                scratchFile = null;
            }
        }
        COSDocument cOSDocument = new COSDocument(scratchFile);
        this.document = cOSDocument;
        this.pdfSource = null;
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDocument.setTrailer(cOSDictionary);
        COSDictionary cOSDictionary2 = new COSDictionary();
        cOSDictionary.setItem(COSName.ROOT, (COSBase) cOSDictionary2);
        cOSDictionary2.setItem(COSName.TYPE, (COSBase) COSName.CATALOG);
        cOSDictionary2.setItem(COSName.VERSION, (COSBase) COSName.getPDFName("1.4"));
        COSDictionary cOSDictionary3 = new COSDictionary();
        cOSDictionary2.setItem(COSName.PAGES, (COSBase) cOSDictionary3);
        cOSDictionary3.setItem(COSName.TYPE, (COSBase) COSName.PAGES);
        cOSDictionary3.setItem(COSName.KIDS, (COSBase) new COSArray());
        cOSDictionary3.setItem(COSName.COUNT, (COSBase) COSInteger.ZERO);
    }

    public void addPage(PDPage pDPage) {
        getPages().add(pDPage);
    }

    public void addSignature(PDSignature pDSignature, SignatureInterface signatureInterface) throws IOException {
        addSignature(pDSignature, signatureInterface, new SignatureOptions());
    }

    public void addSignature(PDSignature pDSignature, SignatureInterface signatureInterface, SignatureOptions signatureOptions) throws IOException {
        int preferredSignatureSize = signatureOptions.getPreferredSignatureSize();
        if (preferredSignatureSize > 0) {
            pDSignature.setContents(new byte[preferredSignatureSize]);
        } else {
            pDSignature.setContents(new byte[SignatureOptions.DEFAULT_SIGNATURE_SIZE]);
        }
        pDSignature.setByteRange(new int[]{0, Http2Connection.DEGRADED_PONG_TIMEOUT_NS, Http2Connection.DEGRADED_PONG_TIMEOUT_NS, Http2Connection.DEGRADED_PONG_TIMEOUT_NS});
        this.signInterface = signatureInterface;
        PDDocumentCatalog documentCatalog = getDocumentCatalog();
        int count = documentCatalog.getPages().getCount();
        if (count == 0) {
            throw new IllegalStateException("Cannot sign an empty document");
        }
        PDPage pDPage = documentCatalog.getPages().get(Math.min(Math.max(signatureOptions.getPage(), 0), count - 1));
        PDAcroForm acroForm = documentCatalog.getAcroForm();
        documentCatalog.getCOSObject().setNeedToBeUpdated(true);
        if (acroForm == null) {
            acroForm = new PDAcroForm(this);
            documentCatalog.setAcroForm(acroForm);
        } else {
            acroForm.getCOSObject().setNeedToBeUpdated(true);
        }
        List<PDField> fields = acroForm.getFields();
        if (fields == null) {
            fields = new ArrayList<>();
            acroForm.setFields(fields);
        }
        PDSignatureField pDSignatureFieldFindSignatureField = findSignatureField(fields, pDSignature);
        if (pDSignatureFieldFindSignatureField == null) {
            pDSignatureFieldFindSignatureField = new PDSignatureField(acroForm);
            pDSignatureFieldFindSignatureField.setValue(pDSignature);
            pDSignatureFieldFindSignatureField.getWidgets().get(0).setPage(pDPage);
        }
        pDSignatureFieldFindSignatureField.getWidgets().get(0).setPrinted(true);
        List<PDField> fields2 = acroForm.getFields();
        acroForm.getCOSObject().setDirect(true);
        acroForm.setSignaturesExist(true);
        acroForm.setAppendOnly(true);
        boolean zCheckSignatureField = checkSignatureField(fields2, pDSignatureFieldFindSignatureField);
        COSDocument visualSignature = signatureOptions.getVisualSignature();
        if (visualSignature == null) {
            prepareNonVisibleSignature(pDSignatureFieldFindSignatureField);
            return;
        }
        prepareVisibleSignature(pDSignatureFieldFindSignatureField, acroForm, visualSignature);
        List<PDAnnotation> annotations = pDPage.getAnnotations();
        pDPage.setAnnotations(annotations);
        if (!(annotations instanceof COSArrayList) || !(fields2 instanceof COSArrayList) || !((COSArrayList) annotations).toList().equals(((COSArrayList) fields2).toList()) || !zCheckSignatureField) {
            annotations.add(pDSignatureFieldFindSignatureField.getWidgets().get(0));
        }
        pDPage.getCOSObject().setNeedToBeUpdated(true);
    }

    private PDSignatureField findSignatureField(List<PDField> list, PDSignature pDSignature) {
        PDSignatureField pDSignatureField;
        PDSignature signature;
        PDSignatureField pDSignatureField2 = null;
        for (PDField pDField : list) {
            if ((pDField instanceof PDSignatureField) && (signature = (pDSignatureField = (PDSignatureField) pDField).getSignature()) != null && signature.getCOSObject().equals(pDSignature.getCOSObject())) {
                pDSignatureField2 = pDSignatureField;
            }
        }
        return pDSignatureField2;
    }

    private boolean checkSignatureField(List<PDField> list, PDSignatureField pDSignatureField) {
        boolean z;
        Iterator<PDField> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            PDField next = it.next();
            if ((next instanceof PDSignatureField) && next.getCOSObject().equals(pDSignatureField.getCOSObject())) {
                z = true;
                pDSignatureField.getCOSObject().setNeedToBeUpdated(true);
                break;
            }
        }
        if (!z) {
            list.add(pDSignatureField);
        }
        return z;
    }

    private void prepareVisibleSignature(PDSignatureField pDSignatureField, PDAcroForm pDAcroForm, COSDocument cOSDocument) {
        boolean z = true;
        boolean z2 = true;
        for (COSObject cOSObject : cOSDocument.getObjects()) {
            if (!z && !z2) {
                break;
            }
            COSBase object = cOSObject.getObject();
            if (object instanceof COSDictionary) {
                COSDictionary cOSDictionary = (COSDictionary) object;
                COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.TYPE);
                if (z && COSName.ANNOT.equals(dictionaryObject)) {
                    assignSignatureRectangle(pDSignatureField, cOSDictionary);
                    z = false;
                }
                COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.f2264FT);
                COSBase dictionaryObject3 = cOSDictionary.getDictionaryObject(COSName.f2230AP);
                if (z2 && COSName.SIG.equals(dictionaryObject2) && (dictionaryObject3 instanceof COSDictionary)) {
                    assignAppearanceDictionary(pDSignatureField, (COSDictionary) dictionaryObject3);
                    assignAcroFormDefaultResource(pDAcroForm, cOSDictionary);
                    z2 = false;
                }
            }
        }
        if (z || z2) {
            throw new IllegalArgumentException("Template is missing required objects");
        }
    }

    private void assignSignatureRectangle(PDSignatureField pDSignatureField, COSDictionary cOSDictionary) {
        pDSignatureField.getWidgets().get(0).setRectangle(new PDRectangle((COSArray) cOSDictionary.getDictionaryObject(COSName.RECT)));
    }

    private void assignAppearanceDictionary(PDSignatureField pDSignatureField, COSDictionary cOSDictionary) {
        PDAppearanceDictionary pDAppearanceDictionary = new PDAppearanceDictionary(cOSDictionary);
        cOSDictionary.setDirect(true);
        pDSignatureField.getWidgets().get(0).setAppearance(pDAppearanceDictionary);
    }

    private void assignAcroFormDefaultResource(PDAcroForm pDAcroForm, COSDictionary cOSDictionary) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.f2254DR);
        if (dictionaryObject instanceof COSDictionary) {
            COSDictionary cOSDictionary2 = (COSDictionary) dictionaryObject;
            cOSDictionary2.setDirect(true);
            cOSDictionary2.setNeedToBeUpdated(true);
            pDAcroForm.getCOSObject().setItem(COSName.f2254DR, (COSBase) cOSDictionary2);
        }
    }

    private void prepareNonVisibleSignature(PDSignatureField pDSignatureField) throws IOException {
        pDSignatureField.getWidgets().get(0).setRectangle(new PDRectangle());
    }

    public void addSignatureField(List<PDSignatureField> list, SignatureInterface signatureInterface, SignatureOptions signatureOptions) throws IOException {
        PDDocumentCatalog documentCatalog = getDocumentCatalog();
        documentCatalog.getCOSObject().setNeedToBeUpdated(true);
        PDAcroForm acroForm = documentCatalog.getAcroForm();
        if (acroForm == null) {
            acroForm = new PDAcroForm(this);
            documentCatalog.setAcroForm(acroForm);
        }
        COSDictionary cOSObject = acroForm.getCOSObject();
        cOSObject.setDirect(true);
        cOSObject.setNeedToBeUpdated(true);
        if (!acroForm.isSignaturesExist()) {
            acroForm.setSignaturesExist(true);
        }
        List<PDField> fields = acroForm.getFields();
        for (PDSignatureField pDSignatureField : list) {
            pDSignatureField.getCOSObject().setNeedToBeUpdated(true);
            checkSignatureField(fields, pDSignatureField);
            if (pDSignatureField.getSignature() != null) {
                pDSignatureField.getCOSObject().setNeedToBeUpdated(true);
                addSignature(pDSignatureField.getSignature(), signatureInterface, signatureOptions);
            }
        }
    }

    public void removePage(PDPage pDPage) {
        getPages().remove(pDPage);
    }

    public void removePage(int i) {
        getPages().remove(i);
    }

    public PDPage importPage(PDPage pDPage) throws IOException {
        InputStream contents;
        PDPage pDPage2 = new PDPage(new COSDictionary(pDPage.getCOSObject()), this.resourceCache);
        try {
            contents = pDPage.getContents();
            if (contents != null) {
                try {
                    pDPage2.setContents(new PDStream(this, pDPage.getContents(), COSName.FLATE_DECODE));
                } catch (IOException unused) {
                    IOUtils.closeQuietly(contents);
                }
            }
            addPage(pDPage2);
        } catch (IOException unused2) {
            contents = null;
        }
        return pDPage2;
    }

    public PDDocument(COSDocument cOSDocument) {
        this(cOSDocument, null);
    }

    public PDDocument(COSDocument cOSDocument, RandomAccessRead randomAccessRead) {
        this(cOSDocument, randomAccessRead, null);
    }

    public PDDocument(COSDocument cOSDocument, RandomAccessRead randomAccessRead, AccessPermission accessPermission) {
        this.fontsToSubset = new HashSet();
        this.resourceCache = new DefaultResourceCache();
        this.document = cOSDocument;
        this.pdfSource = randomAccessRead;
        this.accessPermission = accessPermission;
    }

    public COSDocument getDocument() {
        return this.document;
    }

    public PDDocumentInformation getDocumentInformation() {
        if (this.documentInformation == null) {
            COSDictionary trailer = this.document.getTrailer();
            COSDictionary cOSDictionary = (COSDictionary) trailer.getDictionaryObject(COSName.INFO);
            if (cOSDictionary == null) {
                cOSDictionary = new COSDictionary();
                trailer.setItem(COSName.INFO, (COSBase) cOSDictionary);
            }
            this.documentInformation = new PDDocumentInformation(cOSDictionary);
        }
        return this.documentInformation;
    }

    public void setDocumentInformation(PDDocumentInformation pDDocumentInformation) {
        this.documentInformation = pDDocumentInformation;
        this.document.getTrailer().setItem(COSName.INFO, (COSBase) pDDocumentInformation.getCOSObject());
    }

    public PDDocumentCatalog getDocumentCatalog() {
        if (this.documentCatalog == null) {
            COSBase dictionaryObject = this.document.getTrailer().getDictionaryObject(COSName.ROOT);
            if (dictionaryObject instanceof COSDictionary) {
                this.documentCatalog = new PDDocumentCatalog(this, (COSDictionary) dictionaryObject);
            } else {
                this.documentCatalog = new PDDocumentCatalog(this);
            }
        }
        return this.documentCatalog;
    }

    public boolean isEncrypted() {
        return this.document.isEncrypted();
    }

    public PDEncryption getEncryption() {
        if (this.encryption == null && isEncrypted()) {
            this.encryption = new PDEncryption(this.document.getEncryptionDictionary());
        }
        return this.encryption;
    }

    public void setEncryptionDictionary(PDEncryption pDEncryption) throws IOException {
        this.encryption = pDEncryption;
    }

    public PDSignature getLastSignatureDictionary() throws IOException {
        List<PDSignature> signatureDictionaries = getSignatureDictionaries();
        int size = signatureDictionaries.size();
        if (size > 0) {
            return signatureDictionaries.get(size - 1);
        }
        return null;
    }

    public List<PDSignatureField> getSignatureFields() throws IOException {
        ArrayList arrayList = new ArrayList();
        PDAcroForm acroForm = getDocumentCatalog().getAcroForm();
        if (acroForm != null) {
            for (PDField pDField : acroForm.getFields()) {
                if (pDField instanceof PDSignatureField) {
                    arrayList.add((PDSignatureField) pDField);
                }
            }
        }
        return arrayList;
    }

    public List<PDSignature> getSignatureDictionaries() throws IOException {
        ArrayList arrayList = new ArrayList();
        Iterator<PDSignatureField> it = getSignatureFields().iterator();
        while (it.hasNext()) {
            COSBase dictionaryObject = it.next().getCOSObject().getDictionaryObject(COSName.f2320V);
            if (dictionaryObject != null) {
                arrayList.add(new PDSignature((COSDictionary) dictionaryObject));
            }
        }
        return arrayList;
    }

    Set<PDFont> getFontsToSubset() {
        return this.fontsToSubset;
    }

    public static PDDocument load(File file) throws IOException {
        return load(file, "", MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(File file, MemoryUsageSetting memoryUsageSetting) throws IOException {
        return load(file, "", (InputStream) null, (String) null, memoryUsageSetting);
    }

    public static PDDocument load(File file, String str) throws IOException {
        return load(file, str, (InputStream) null, (String) null, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(File file, String str, MemoryUsageSetting memoryUsageSetting) throws IOException {
        return load(file, str, (InputStream) null, (String) null, memoryUsageSetting);
    }

    public static PDDocument load(File file, String str, InputStream inputStream, String str2) throws IOException {
        return load(file, str, inputStream, str2, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(File file, String str, InputStream inputStream, String str2, MemoryUsageSetting memoryUsageSetting) throws IOException {
        RandomAccessBufferedFileInputStream randomAccessBufferedFileInputStream = new RandomAccessBufferedFileInputStream(file);
        try {
            ScratchFile scratchFile = new ScratchFile(memoryUsageSetting);
            try {
                PDFParser pDFParser = new PDFParser(randomAccessBufferedFileInputStream, str, inputStream, str2, scratchFile);
                pDFParser.parse();
                return pDFParser.getPDDocument();
            } catch (IOException e) {
                IOUtils.closeQuietly(scratchFile);
                throw e;
            }
        } catch (IOException e2) {
            IOUtils.closeQuietly(randomAccessBufferedFileInputStream);
            throw e2;
        }
    }

    public static PDDocument load(InputStream inputStream) throws IOException {
        return load(inputStream, "", (InputStream) null, (String) null, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(InputStream inputStream, MemoryUsageSetting memoryUsageSetting) throws IOException {
        return load(inputStream, "", (InputStream) null, (String) null, memoryUsageSetting);
    }

    public static PDDocument load(InputStream inputStream, String str) throws IOException {
        return load(inputStream, str, (InputStream) null, (String) null, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(InputStream inputStream, String str, InputStream inputStream2, String str2) throws IOException {
        return load(inputStream, str, inputStream2, str2, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(InputStream inputStream, String str, MemoryUsageSetting memoryUsageSetting) throws IOException {
        return load(inputStream, str, (InputStream) null, (String) null, memoryUsageSetting);
    }

    public static PDDocument load(InputStream inputStream, String str, InputStream inputStream2, String str2, MemoryUsageSetting memoryUsageSetting) throws IOException {
        ScratchFile scratchFile = new ScratchFile(memoryUsageSetting);
        try {
            PDFParser pDFParser = new PDFParser(scratchFile.createBuffer(inputStream), str, inputStream2, str2, scratchFile);
            pDFParser.parse();
            return pDFParser.getPDDocument();
        } catch (IOException e) {
            IOUtils.closeQuietly(scratchFile);
            throw e;
        }
    }

    public static PDDocument load(byte[] bArr) throws IOException {
        return load(bArr, "");
    }

    public static PDDocument load(byte[] bArr, String str) throws IOException {
        return load(bArr, str, (InputStream) null, (String) null);
    }

    public static PDDocument load(byte[] bArr, String str, InputStream inputStream, String str2) throws IOException {
        return load(bArr, str, inputStream, str2, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(byte[] bArr, String str, InputStream inputStream, String str2, MemoryUsageSetting memoryUsageSetting) throws IOException {
        PDFParser pDFParser = new PDFParser(new RandomAccessBuffer(bArr), str, inputStream, str2, new ScratchFile(memoryUsageSetting));
        pDFParser.parse();
        return pDFParser.getPDDocument();
    }

    public void save(String str) throws IOException {
        save(new File(str));
    }

    public void save(File file) throws IOException {
        save(new BufferedOutputStream(new FileOutputStream(file)));
    }

    public void save(OutputStream outputStream) throws IOException {
        if (this.document.isClosed()) {
            throw new IOException("Cannot save a document which has been closed");
        }
        Iterator<PDFont> it = this.fontsToSubset.iterator();
        while (it.hasNext()) {
            it.next().subset();
        }
        this.fontsToSubset.clear();
        COSWriter cOSWriter = new COSWriter(outputStream);
        try {
            cOSWriter.write(this);
        } finally {
            cOSWriter.close();
        }
    }

    public void saveIncremental(OutputStream outputStream) throws Throwable {
        COSWriter cOSWriter = null;
        try {
            COSWriter cOSWriter2 = new COSWriter(outputStream, this.pdfSource);
            try {
                cOSWriter2.write(this, this.signInterface);
                cOSWriter2.close();
                cOSWriter2.close();
            } catch (Throwable th) {
                th = th;
                cOSWriter = cOSWriter2;
                if (cOSWriter != null) {
                    cOSWriter.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public PDPage getPage(int i) {
        return getDocumentCatalog().getPages().get(i);
    }

    public PDPageTree getPages() {
        return getDocumentCatalog().getPages();
    }

    public int getNumberOfPages() {
        return getDocumentCatalog().getPages().getCount();
    }

    @Override
    public void close() throws IOException {
        if (this.document.isClosed()) {
            return;
        }
        this.document.close();
        RandomAccessRead randomAccessRead = this.pdfSource;
        if (randomAccessRead != null) {
            randomAccessRead.close();
        }
    }

    public void protect(ProtectionPolicy protectionPolicy) throws IOException {
        if (isAllSecurityToBeRemoved()) {
            Log.w("PdfBox-Android", "do not call setAllSecurityToBeRemoved(true) before callingprotect() as protect() implies setAllSecurityToBeRemoved(false)");
            setAllSecurityToBeRemoved(false);
        }
        if (!isEncrypted()) {
            this.encryption = new PDEncryption();
        }
        SecurityHandler securityHandlerNewSecurityHandlerForPolicy = SecurityHandlerFactory.INSTANCE.newSecurityHandlerForPolicy(protectionPolicy);
        if (securityHandlerNewSecurityHandlerForPolicy == null) {
            throw new IOException("No security handler for policy " + protectionPolicy);
        }
        getEncryption().setSecurityHandler(securityHandlerNewSecurityHandlerForPolicy);
    }

    public AccessPermission getCurrentAccessPermission() {
        if (this.accessPermission == null) {
            this.accessPermission = AccessPermission.getOwnerAccessPermission();
        }
        return this.accessPermission;
    }

    public boolean isAllSecurityToBeRemoved() {
        return this.allSecurityToBeRemoved;
    }

    public void setAllSecurityToBeRemoved(boolean z) {
        this.allSecurityToBeRemoved = z;
    }

    public Long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Long l) {
        this.documentId = l;
    }

    public float getVersion() {
        float f;
        float version = getDocument().getVersion();
        if (version < 1.4f) {
            return version;
        }
        String version2 = getDocumentCatalog().getVersion();
        if (version2 != null) {
            try {
                f = Float.parseFloat(version2);
            } catch (NumberFormatException e) {
                Log.e("PdfBox-Android", "Can't extract the version number of the document catalog.", e);
                f = -1.0f;
            }
        } else {
            f = -1.0f;
        }
        return Math.max(f, version);
    }

    public void setVersion(float f) {
        float version = getVersion();
        if (f == version) {
            return;
        }
        if (f < version) {
            Log.e("PdfBox-Android", "It's not allowed to downgrade the version of a pdf.");
        } else if (getDocument().getVersion() >= 1.4f) {
            getDocumentCatalog().setVersion(Float.toString(f));
        } else {
            getDocument().setVersion(f);
        }
    }

    public ResourceCache getResourceCache() {
        return this.resourceCache;
    }

    public void setResourceCache(ResourceCache resourceCache) {
        this.resourceCache = resourceCache;
    }
}
