package com.tom_roush.pdfbox.pdmodel.common;

import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PDMetadata extends PDStream {
    public PDMetadata(PDDocument pDDocument) {
        super(pDDocument);
        getCOSObject().setName(COSName.TYPE, "Metadata");
        getCOSObject().setName(COSName.SUBTYPE, "XML");
    }

    public PDMetadata(PDDocument pDDocument, InputStream inputStream) throws IOException {
        super(pDDocument, inputStream);
        getCOSObject().setName(COSName.TYPE, "Metadata");
        getCOSObject().setName(COSName.SUBTYPE, "XML");
    }

    public PDMetadata(COSStream cOSStream) {
        super(cOSStream);
    }

    public InputStream exportXMPMetadata() throws IOException {
        return createInputStream();
    }

    public void importXMPMetadata(byte[] bArr) throws IOException {
        OutputStream outputStreamCreateOutputStream = createOutputStream();
        outputStreamCreateOutputStream.write(bArr);
        outputStreamCreateOutputStream.close();
    }
}
