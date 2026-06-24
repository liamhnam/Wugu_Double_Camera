package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

public class FDFNamedPageReference implements COSObjectable {
    private final COSDictionary ref;

    public FDFNamedPageReference() {
        this.ref = new COSDictionary();
    }

    public FDFNamedPageReference(COSDictionary cOSDictionary) {
        this.ref = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.ref;
    }

    public String getName() {
        return this.ref.getString(COSName.NAME);
    }

    public void setName(String str) {
        this.ref.setString(COSName.NAME, str);
    }

    public PDFileSpecification getFileSpecification() throws IOException {
        return PDFileSpecification.createFS(this.ref.getDictionaryObject(COSName.f2260F));
    }

    public void setFileSpecification(PDFileSpecification pDFileSpecification) {
        this.ref.setItem(COSName.f2260F, pDFileSpecification);
    }
}
