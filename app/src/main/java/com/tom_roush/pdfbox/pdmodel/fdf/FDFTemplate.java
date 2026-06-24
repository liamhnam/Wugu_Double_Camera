package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.ArrayList;
import java.util.List;

public class FDFTemplate implements COSObjectable {
    private final COSDictionary template;

    public FDFTemplate() {
        this.template = new COSDictionary();
    }

    public FDFTemplate(COSDictionary cOSDictionary) {
        this.template = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.template;
    }

    public FDFNamedPageReference getTemplateReference() {
        COSDictionary cOSDictionary = (COSDictionary) this.template.getDictionaryObject(COSName.TREF);
        if (cOSDictionary != null) {
            return new FDFNamedPageReference(cOSDictionary);
        }
        return null;
    }

    public void setTemplateReference(FDFNamedPageReference fDFNamedPageReference) {
        this.template.setItem(COSName.TREF, fDFNamedPageReference);
    }

    public List<FDFField> getFields() {
        COSArray cOSArray = (COSArray) this.template.getDictionaryObject(COSName.FIELDS);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(new FDFField((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setFields(List<FDFField> list) {
        this.template.setItem(COSName.FIELDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public boolean shouldRename() {
        return this.template.getBoolean(COSName.RENAME, false);
    }

    public void setRename(boolean z) {
        this.template.setBoolean(COSName.RENAME, z);
    }
}
