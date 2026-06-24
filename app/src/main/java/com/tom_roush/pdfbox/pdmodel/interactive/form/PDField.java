package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFField;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import java.io.IOException;
import java.util.List;

public abstract class PDField implements COSObjectable {
    private static final int FLAG_NO_EXPORT = 4;
    private static final int FLAG_READ_ONLY = 1;
    private static final int FLAG_REQUIRED = 2;
    private final PDAcroForm acroForm;
    private final COSDictionary dictionary;
    private final PDNonTerminalField parent;

    abstract FDFField exportFDF() throws IOException;

    public abstract int getFieldFlags();

    public abstract String getFieldType();

    public abstract String getValueAsString();

    public abstract List<PDAnnotationWidget> getWidgets();

    public abstract void setValue(String str) throws IOException;

    PDField(PDAcroForm pDAcroForm) {
        this(pDAcroForm, new COSDictionary(), null);
    }

    PDField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        this.acroForm = pDAcroForm;
        this.dictionary = cOSDictionary;
        this.parent = pDNonTerminalField;
    }

    static PDField fromDictionary(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        return PDFieldFactory.createField(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    protected COSBase getInheritableAttribute(COSName cOSName) {
        if (this.dictionary.containsKey(cOSName)) {
            return this.dictionary.getDictionaryObject(cOSName);
        }
        PDNonTerminalField pDNonTerminalField = this.parent;
        if (pDNonTerminalField != null) {
            return pDNonTerminalField.getInheritableAttribute(cOSName);
        }
        return this.acroForm.getCOSObject().getDictionaryObject(cOSName);
    }

    public void setReadOnly(boolean z) {
        this.dictionary.setFlag(COSName.f2262FF, 1, z);
    }

    public boolean isReadOnly() {
        return this.dictionary.getFlag(COSName.f2262FF, 1);
    }

    public void setRequired(boolean z) {
        this.dictionary.setFlag(COSName.f2262FF, 2, z);
    }

    public boolean isRequired() {
        return this.dictionary.getFlag(COSName.f2262FF, 2);
    }

    public void setNoExport(boolean z) {
        this.dictionary.setFlag(COSName.f2262FF, 4, z);
    }

    public boolean isNoExport() {
        return this.dictionary.getFlag(COSName.f2262FF, 4);
    }

    public void setFieldFlags(int i) {
        this.dictionary.setInt(COSName.f2262FF, i);
    }

    public PDFormFieldAdditionalActions getActions() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.f2229AA);
        if (cOSDictionary != null) {
            return new PDFormFieldAdditionalActions(cOSDictionary);
        }
        return null;
    }

    void importFDF(FDFField fDFField) throws IOException {
        COSBase cOSValue = fDFField.getCOSValue();
        if (cOSValue != null) {
            this.dictionary.setItem(COSName.f2320V, cOSValue);
        }
        Integer fieldFlags = fDFField.getFieldFlags();
        if (fieldFlags != null) {
            setFieldFlags(fieldFlags.intValue());
            return;
        }
        Integer setFieldFlags = fDFField.getSetFieldFlags();
        int fieldFlags2 = getFieldFlags();
        if (setFieldFlags != null) {
            fieldFlags2 |= setFieldFlags.intValue();
            setFieldFlags(fieldFlags2);
        }
        Integer clearFieldFlags = fDFField.getClearFieldFlags();
        if (clearFieldFlags != null) {
            setFieldFlags((~clearFieldFlags.intValue()) & fieldFlags2);
        }
    }

    public PDNonTerminalField getParent() {
        return this.parent;
    }

    PDField findKid(String[] strArr, int i) {
        int i2;
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.KIDS);
        PDField pDFieldFromDictionary = null;
        if (cOSArray != null) {
            for (int i3 = 0; pDFieldFromDictionary == null && i3 < cOSArray.size(); i3++) {
                COSDictionary cOSDictionary = (COSDictionary) cOSArray.getObject(i3);
                if (strArr[i].equals(cOSDictionary.getString(COSName.f2310T)) && (pDFieldFromDictionary = fromDictionary(this.acroForm, cOSDictionary, (PDNonTerminalField) this)) != null && strArr.length > (i2 = i + 1)) {
                    pDFieldFromDictionary = pDFieldFromDictionary.findKid(strArr, i2);
                }
            }
        }
        return pDFieldFromDictionary;
    }

    public PDAcroForm getAcroForm() {
        return this.acroForm;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public String getPartialName() {
        return this.dictionary.getString(COSName.f2310T);
    }

    public void setPartialName(String str) {
        this.dictionary.setString(COSName.f2310T, str);
    }

    public String getFullyQualifiedName() {
        String partialName = getPartialName();
        PDNonTerminalField pDNonTerminalField = this.parent;
        String fullyQualifiedName = pDNonTerminalField != null ? pDNonTerminalField.getFullyQualifiedName() : null;
        return fullyQualifiedName != null ? partialName != null ? fullyQualifiedName + "." + partialName : fullyQualifiedName : partialName;
    }

    public String getAlternateFieldName() {
        return this.dictionary.getString(COSName.f2315TU);
    }

    public void setAlternateFieldName(String str) {
        this.dictionary.setString(COSName.f2315TU, str);
    }

    public String getMappingName() {
        return this.dictionary.getString(COSName.f2313TM);
    }

    public void setMappingName(String str) {
        this.dictionary.setString(COSName.f2313TM, str);
    }

    public String toString() {
        return getFullyQualifiedName() + "{type: " + getClass().getSimpleName() + " value: " + getInheritableAttribute(COSName.f2320V) + "}";
    }
}
