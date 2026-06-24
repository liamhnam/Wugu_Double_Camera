package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdfparser.FDFParser;
import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FDFDocument implements Closeable {
    private COSDocument document;

    public FDFDocument() {
        COSDocument cOSDocument = new COSDocument();
        this.document = cOSDocument;
        cOSDocument.setVersion(1.2f);
        this.document.setTrailer(new COSDictionary());
        setCatalog(new FDFCatalog());
    }

    public FDFDocument(COSDocument cOSDocument) {
        this.document = cOSDocument;
    }

    public FDFDocument(Document document) throws IOException {
        this();
        Element documentElement = document.getDocumentElement();
        if (!documentElement.getNodeName().equals("xfdf")) {
            throw new IOException("Error while importing xfdf document, root should be 'xfdf' and not '" + documentElement.getNodeName() + "'");
        }
        setCatalog(new FDFCatalog(documentElement));
    }

    public void writeXML(Writer writer) throws IOException {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<xfdf xmlns=\"http://ns.adobe.com/xfdf/\" xml:space=\"preserve\">\n");
        getCatalog().writeXML(writer);
        writer.write("</xfdf>\n");
    }

    public COSDocument getDocument() {
        return this.document;
    }

    public FDFCatalog getCatalog() {
        COSDictionary cOSDictionary = (COSDictionary) this.document.getTrailer().getDictionaryObject(COSName.ROOT);
        if (cOSDictionary == null) {
            FDFCatalog fDFCatalog = new FDFCatalog();
            setCatalog(fDFCatalog);
            return fDFCatalog;
        }
        return new FDFCatalog(cOSDictionary);
    }

    public void setCatalog(FDFCatalog fDFCatalog) {
        this.document.getTrailer().setItem(COSName.ROOT, fDFCatalog);
    }

    public static FDFDocument load(String str) throws IOException {
        FDFParser fDFParser = new FDFParser(str);
        fDFParser.parse();
        return new FDFDocument(fDFParser.getDocument());
    }

    public static FDFDocument load(File file) throws IOException {
        FDFParser fDFParser = new FDFParser(file);
        fDFParser.parse();
        return new FDFDocument(fDFParser.getDocument());
    }

    public static FDFDocument load(InputStream inputStream) throws IOException {
        FDFParser fDFParser = new FDFParser(inputStream);
        fDFParser.parse();
        return new FDFDocument(fDFParser.getDocument());
    }

    public static FDFDocument loadXFDF(String str) throws IOException {
        return loadXFDF(new BufferedInputStream(new FileInputStream(str)));
    }

    public static FDFDocument loadXFDF(File file) throws IOException {
        return loadXFDF(new BufferedInputStream(new FileInputStream(file)));
    }

    public static FDFDocument loadXFDF(InputStream inputStream) throws IOException {
        return new FDFDocument(XMLUtil.parse(inputStream));
    }

    public void save(File file) throws Throwable {
        save(new FileOutputStream(file));
    }

    public void save(String str) throws Throwable {
        save(new FileOutputStream(str));
    }

    public void save(OutputStream outputStream) throws Throwable {
        COSWriter cOSWriter = null;
        try {
            COSWriter cOSWriter2 = new COSWriter(outputStream);
            try {
                cOSWriter2.write(this);
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

    public void saveXFDF(File file) throws IOException {
        saveXFDF(new BufferedWriter(new FileWriter(file)));
    }

    public void saveXFDF(String str) throws IOException {
        saveXFDF(new BufferedWriter(new FileWriter(str)));
    }

    public void saveXFDF(Writer writer) throws IOException {
        try {
            writeXML(writer);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void close() throws IOException {
        this.document.close();
    }
}
