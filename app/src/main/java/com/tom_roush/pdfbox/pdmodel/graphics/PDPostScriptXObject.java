package com.tom_roush.pdfbox.pdmodel.graphics;

import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;

public class PDPostScriptXObject extends PDXObject {
    public PDPostScriptXObject(COSStream cOSStream) {
        super(cOSStream, COSName.f2294PS);
    }
}
