package com.tom_roush.pdfbox.pdfparser;

import com.tom_roush.pdfbox.cos.COSObject;

public interface PDFXRef {
    COSObject getObject(int i);
}
