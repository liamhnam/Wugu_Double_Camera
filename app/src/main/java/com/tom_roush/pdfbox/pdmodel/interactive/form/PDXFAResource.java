package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class PDXFAResource implements COSObjectable {
    private static final int BUFFER_SIZE = 1024;
    private final COSBase xfa;

    public PDXFAResource(COSBase cOSBase) {
        this.xfa = cOSBase;
    }

    @Override
    public COSBase getCOSObject() {
        return this.xfa;
    }

    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        COSInputStream cOSInputStreamCreateInputStream = null;
        try {
            if (getCOSObject() instanceof COSArray) {
                byte[] bArr = new byte[1024];
                COSArray cOSArray = (COSArray) getCOSObject();
                for (int i = 1; i < cOSArray.size(); i += 2) {
                    COSBase object = cOSArray.getObject(i);
                    if (object instanceof COSStream) {
                        cOSInputStreamCreateInputStream = ((COSStream) object).createInputStream();
                        while (true) {
                            int i2 = cOSInputStreamCreateInputStream.read(bArr, 0, 1024);
                            if (i2 == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i2);
                        }
                        byteArrayOutputStream.flush();
                    }
                }
            } else if (this.xfa.getCOSObject() instanceof COSStream) {
                byte[] bArr2 = new byte[1024];
                cOSInputStreamCreateInputStream = ((COSStream) this.xfa.getCOSObject()).createInputStream();
                while (true) {
                    int i3 = cOSInputStreamCreateInputStream.read(bArr2, 0, 1024);
                    if (i3 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, i3);
                }
                byteArrayOutputStream.flush();
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            if (0 != 0) {
                cOSInputStreamCreateInputStream.close();
            }
        }
    }

    public Document getDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setNamespaceAware(true);
        return documentBuilderFactoryNewInstance.newDocumentBuilder().parse(new ByteArrayInputStream(getBytes()));
    }
}
