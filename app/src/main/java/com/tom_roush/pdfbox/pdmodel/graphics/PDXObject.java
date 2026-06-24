package com.tom_roush.pdfbox.pdmodel.graphics;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.ResourceCache;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.IOException;

public class PDXObject implements COSObjectable {
    private final PDStream stream;

    public static PDXObject createXObject(COSBase cOSBase, PDResources pDResources) throws IOException {
        if (cOSBase == null) {
            return null;
        }
        if (!(cOSBase instanceof COSStream)) {
            throw new IOException("Unexpected object type: " + cOSBase.getClass().getName());
        }
        COSStream cOSStream = (COSStream) cOSBase;
        String nameAsString = cOSStream.getNameAsString(COSName.SUBTYPE);
        if (COSName.IMAGE.getName().equals(nameAsString)) {
            return new PDImageXObject(new PDStream(cOSStream), pDResources);
        }
        if (COSName.FORM.getName().equals(nameAsString)) {
            ResourceCache resourceCache = pDResources != null ? pDResources.getResourceCache() : null;
            COSDictionary cOSDictionary = (COSDictionary) cOSStream.getDictionaryObject(COSName.GROUP);
            if (cOSDictionary != null && COSName.TRANSPARENCY.equals(cOSDictionary.getCOSName(COSName.f2301S))) {
                return new PDTransparencyGroup(cOSStream, resourceCache);
            }
            return new PDFormXObject(cOSStream, resourceCache);
        }
        if (COSName.f2294PS.getName().equals(nameAsString)) {
            return new PDPostScriptXObject(cOSStream);
        }
        throw new IOException("Invalid XObject Subtype: " + nameAsString);
    }

    protected PDXObject(COSStream cOSStream, COSName cOSName) {
        this.stream = new PDStream(cOSStream);
        cOSStream.setName(COSName.TYPE, COSName.XOBJECT.getName());
        cOSStream.setName(COSName.SUBTYPE, cOSName.getName());
    }

    protected PDXObject(PDStream pDStream, COSName cOSName) {
        this.stream = pDStream;
        pDStream.getCOSObject().setName(COSName.TYPE, COSName.XOBJECT.getName());
        pDStream.getCOSObject().setName(COSName.SUBTYPE, cOSName.getName());
    }

    protected PDXObject(PDDocument pDDocument, COSName cOSName) {
        PDStream pDStream = new PDStream(pDDocument);
        this.stream = pDStream;
        pDStream.getCOSObject().setName(COSName.TYPE, COSName.XOBJECT.getName());
        pDStream.getCOSObject().setName(COSName.SUBTYPE, cOSName.getName());
    }

    @Override
    public final COSStream getCOSObject() {
        return this.stream.getCOSObject();
    }

    @Deprecated
    public final COSStream getCOSStream() {
        return this.stream.getCOSObject();
    }

    @Deprecated
    public final PDStream getPDStream() {
        return this.stream;
    }

    public final PDStream getStream() {
        return this.stream;
    }
}
