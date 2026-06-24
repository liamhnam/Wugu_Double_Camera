package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public class FDFPageInfo implements COSObjectable {
    private final COSDictionary pageInfo;

    public FDFPageInfo() {
        this.pageInfo = new COSDictionary();
    }

    public FDFPageInfo(COSDictionary cOSDictionary) {
        this.pageInfo = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.pageInfo;
    }
}
