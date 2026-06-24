package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.p022io.RandomAccessBufferedFileInputStream;
import com.tom_roush.pdfbox.pdfparser.PDFParser;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigProperties;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SignatureOptions implements Closeable {
    public static final int DEFAULT_SIGNATURE_SIZE = 9472;
    private int pageNo = 0;
    private int preferredSignatureSize;
    private COSDocument visualSignature;

    public void setPage(int i) {
        this.pageNo = i;
    }

    public int getPage() {
        return this.pageNo;
    }

    public void setVisualSignature(File file) throws IOException {
        PDFParser pDFParser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
        pDFParser.parse();
        this.visualSignature = pDFParser.getDocument();
    }

    public void setVisualSignature(InputStream inputStream) throws IOException {
        PDFParser pDFParser = new PDFParser(new RandomAccessBufferedFileInputStream(inputStream));
        pDFParser.parse();
        this.visualSignature = pDFParser.getDocument();
    }

    public void setVisualSignature(PDVisibleSigProperties pDVisibleSigProperties) throws IOException {
        setVisualSignature(pDVisibleSigProperties.getVisibleSignature());
    }

    public COSDocument getVisualSignature() {
        return this.visualSignature;
    }

    public int getPreferredSignatureSize() {
        return this.preferredSignatureSize;
    }

    public void setPreferredSignatureSize(int i) {
        if (i > 0) {
            this.preferredSignatureSize = i;
        }
    }

    @Override
    public void close() throws IOException {
        COSDocument cOSDocument = this.visualSignature;
        if (cOSDocument != null) {
            cOSDocument.close();
        }
    }
}
