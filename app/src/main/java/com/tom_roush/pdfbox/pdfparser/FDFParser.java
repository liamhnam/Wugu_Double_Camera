package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.p022io.RandomAccessBuffer;
import com.tom_roush.pdfbox.p022io.RandomAccessFile;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FDFParser extends COSParser {
    public FDFParser(String str) throws IOException {
        this(new File(str));
    }

    public FDFParser(File file) throws IOException {
        super(new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER));
        this.fileLen = file.length();
        init();
    }

    public FDFParser(InputStream inputStream) throws IOException {
        super(new RandomAccessBuffer(inputStream));
        this.fileLen = this.source.length();
        init();
    }

    private void init() throws IOException {
        String property = System.getProperty(COSParser.SYSPROP_EOFLOOKUPRANGE);
        if (property != null) {
            try {
                setEOFLookupRange(Integer.parseInt(property));
            } catch (NumberFormatException unused) {
                Log.w("PdfBox-Android", "System property com.tom_roush.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '" + property + "'");
            }
        }
        this.document = new COSDocument();
    }

    private void initialParse() throws IOException {
        COSDictionary cOSDictionaryRebuildTrailer;
        long startxrefOffset = getStartxrefOffset();
        if (startxrefOffset > 0) {
            cOSDictionaryRebuildTrailer = parseXref(startxrefOffset);
        } else {
            cOSDictionaryRebuildTrailer = rebuildTrailer();
        }
        COSBase trailerValuesDynamically = parseTrailerValuesDynamically(cOSDictionaryRebuildTrailer);
        if (trailerValuesDynamically instanceof COSDictionary) {
            parseDictObjects((COSDictionary) trailerValuesDynamically, null);
        }
        this.initialParseDone = true;
    }

    public void parse() throws IOException {
        try {
            if (!parseFDFHeader()) {
                throw new IOException("Error: Header doesn't contain versioninfo");
            }
            initialParse();
        } catch (Throwable th) {
            if (this.document != null) {
                IOUtils.closeQuietly(this.document);
                this.document = null;
            }
            throw th;
        }
    }
}
