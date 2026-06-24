package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFField;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PDNonTerminalField extends PDField {
    public PDNonTerminalField(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    PDNonTerminalField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    @Override
    public int getFieldFlags() {
        COSInteger cOSInteger = (COSInteger) getCOSObject().getDictionaryObject(COSName.f2262FF);
        if (cOSInteger != null) {
            return cOSInteger.intValue();
        }
        return 0;
    }

    @Override
    void importFDF(FDFField fDFField) throws IOException {
        super.importFDF(fDFField);
        List<FDFField> kids = fDFField.getKids();
        List<PDField> children = getChildren();
        for (int i = 0; kids != null && i < kids.size(); i++) {
            for (PDField pDField : children) {
                if (pDField instanceof PDField) {
                    PDField pDField2 = pDField;
                    FDFField fDFField2 = kids.get(i);
                    String partialFieldName = fDFField2.getPartialFieldName();
                    if (partialFieldName != null && partialFieldName.equals(pDField2.getPartialName())) {
                        pDField2.importFDF(fDFField2);
                    }
                }
            }
        }
    }

    @Override
    FDFField exportFDF() throws IOException {
        FDFField fDFField = new FDFField();
        fDFField.setPartialFieldName(getPartialName());
        fDFField.setValue(getValue());
        List<PDField> children = getChildren();
        ArrayList arrayList = new ArrayList();
        Iterator<PDField> it = children.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().exportFDF());
        }
        fDFField.setKids(arrayList);
        return fDFField;
    }

    public List<PDField> getChildren() {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.KIDS);
        for (int i = 0; i < cOSArray.size(); i++) {
            PDField pDFieldFromDictionary = PDField.fromDictionary(getAcroForm(), (COSDictionary) cOSArray.getObject(i), this);
            if (pDFieldFromDictionary != null) {
                arrayList.add(pDFieldFromDictionary);
            }
        }
        return arrayList;
    }

    public void setChildren(List<PDField> list) {
        getCOSObject().setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    @Override
    public String getFieldType() {
        return getCOSObject().getNameAsString(COSName.f2264FT);
    }

    public COSBase getValue() {
        return getCOSObject().getDictionaryObject(COSName.f2320V);
    }

    @Override
    public String getValueAsString() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2320V);
        return dictionaryObject != null ? dictionaryObject.toString() : "";
    }

    public void setValue(COSBase cOSBase) throws IOException {
        getCOSObject().setItem(COSName.f2320V, cOSBase);
    }

    @Override
    public void setValue(String str) throws IOException {
        getCOSObject().setString(COSName.f2320V, str);
    }

    public COSBase getDefaultValue() {
        return getCOSObject().getDictionaryObject(COSName.f2256DV);
    }

    public void setDefaultValue(COSBase cOSBase) {
        getCOSObject().setItem(COSName.f2320V, cOSBase);
    }

    @Override
    public List<PDAnnotationWidget> getWidgets() {
        return Collections.emptyList();
    }
}
