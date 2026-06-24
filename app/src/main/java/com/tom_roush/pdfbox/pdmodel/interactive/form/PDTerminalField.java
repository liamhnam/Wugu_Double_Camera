package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFField;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.cmc.BodyPartID;

public abstract class PDTerminalField extends PDField {
    abstract void constructAppearances() throws IOException;

    protected PDTerminalField(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    PDTerminalField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public void setActions(PDFormFieldAdditionalActions pDFormFieldAdditionalActions) {
        getCOSObject().setItem(COSName.f2229AA, pDFormFieldAdditionalActions);
    }

    @Override
    public int getFieldFlags() {
        COSInteger cOSInteger = (COSInteger) getCOSObject().getDictionaryObject(COSName.f2262FF);
        if (cOSInteger != null) {
            return cOSInteger.intValue();
        }
        if (getParent() != null) {
            return getParent().getFieldFlags();
        }
        return 0;
    }

    @Override
    public String getFieldType() {
        String nameAsString = getCOSObject().getNameAsString(COSName.f2264FT);
        return (nameAsString != null || getParent() == null) ? nameAsString : getParent().getFieldType();
    }

    @Override
    public void importFDF(FDFField fDFField) throws IOException {
        super.importFDF(fDFField);
        PDAnnotationWidget pDAnnotationWidget = getWidgets().get(0);
        if (pDAnnotationWidget != null) {
            int annotationFlags = pDAnnotationWidget.getAnnotationFlags();
            Integer widgetFieldFlags = fDFField.getWidgetFieldFlags();
            if (widgetFieldFlags != null) {
                pDAnnotationWidget.setAnnotationFlags(widgetFieldFlags.intValue());
                return;
            }
            Integer setWidgetFieldFlags = fDFField.getSetWidgetFieldFlags();
            if (setWidgetFieldFlags != null) {
                annotationFlags |= setWidgetFieldFlags.intValue();
                pDAnnotationWidget.setAnnotationFlags(annotationFlags);
            }
            Integer clearWidgetFieldFlags = fDFField.getClearWidgetFieldFlags();
            if (clearWidgetFieldFlags != null) {
                pDAnnotationWidget.setAnnotationFlags(((int) (((long) clearWidgetFieldFlags.intValue()) ^ BodyPartID.bodyIdMax)) & annotationFlags);
            }
        }
    }

    @Override
    FDFField exportFDF() throws IOException {
        FDFField fDFField = new FDFField();
        fDFField.setPartialFieldName(getPartialName());
        fDFField.setValue(getCOSObject().getDictionaryObject(COSName.f2320V));
        return fDFField;
    }

    @Override
    public List<PDAnnotationWidget> getWidgets() {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            arrayList.add(new PDAnnotationWidget(getCOSObject()));
        } else if (cOSArray.size() > 0) {
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSDictionary) {
                    arrayList.add(new PDAnnotationWidget((COSDictionary) object));
                }
            }
        }
        return arrayList;
    }

    public void setWidgets(List<PDAnnotationWidget> list) {
        getCOSObject().setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    @Deprecated
    public PDAnnotationWidget getWidget() {
        return getWidgets().get(0);
    }

    protected final void applyChange() throws IOException {
        if (getAcroForm().getNeedAppearances()) {
            return;
        }
        constructAppearances();
    }
}
