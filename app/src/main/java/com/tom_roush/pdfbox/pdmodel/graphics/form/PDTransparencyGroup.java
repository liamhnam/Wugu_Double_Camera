package com.tom_roush.pdfbox.pdmodel.graphics.form;

import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.ResourceCache;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;

public class PDTransparencyGroup extends PDFormXObject {
    public PDTransparencyGroup(PDStream pDStream) {
        super(pDStream);
    }

    public PDTransparencyGroup(COSStream cOSStream, ResourceCache resourceCache) {
        super(cOSStream, resourceCache);
    }

    public PDTransparencyGroup(PDDocument pDDocument) {
        super(pDDocument);
    }
}
