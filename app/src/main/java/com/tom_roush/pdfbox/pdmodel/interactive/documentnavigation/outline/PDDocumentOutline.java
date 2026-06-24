package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public final class PDDocumentOutline extends PDOutlineNode {
    @Override
    public void closeNode() {
    }

    @Override
    public boolean isNodeOpen() {
        return true;
    }

    @Override
    public void openNode() {
    }

    public PDDocumentOutline() {
        getCOSObject().setName(COSName.TYPE, COSName.OUTLINES.getName());
    }

    public PDDocumentOutline(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        getCOSObject().setName(COSName.TYPE, COSName.OUTLINES.getName());
    }
}
