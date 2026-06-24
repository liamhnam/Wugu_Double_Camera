package com.tom_roush.pdfbox.pdmodel.interactive.form;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFCatalog;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFDictionary;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFDocument;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFField;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class PDAcroForm implements COSObjectable {
    private static final int FLAG_APPEND_ONLY = 2;
    private static final int FLAG_SIGNATURES_EXIST = 1;
    private final COSDictionary dictionary;
    private final PDDocument document;
    private Map<String, PDField> fieldCache;

    public PDAcroForm(PDDocument pDDocument) {
        this.document = pDDocument;
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.FIELDS, (COSBase) new COSArray());
    }

    public PDAcroForm(PDDocument pDDocument, COSDictionary cOSDictionary) {
        this.document = pDDocument;
        this.dictionary = cOSDictionary;
    }

    PDDocument getDocument() {
        return this.document;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public void importFDF(FDFDocument fDFDocument) throws IOException {
        List<FDFField> fields = fDFDocument.getCatalog().getFDF().getFields();
        if (fields != null) {
            for (FDFField fDFField : fields) {
                PDField field = getField(fDFField.getPartialFieldName());
                if (field != null) {
                    field.importFDF(fDFField);
                }
            }
        }
    }

    public FDFDocument exportFDF() throws IOException {
        FDFDocument fDFDocument = new FDFDocument();
        FDFCatalog catalog = fDFDocument.getCatalog();
        FDFDictionary fDFDictionary = new FDFDictionary();
        catalog.setFDF(fDFDictionary);
        ArrayList arrayList = new ArrayList();
        Iterator<PDField> it = getFields().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().exportFDF());
        }
        fDFDictionary.setID(this.document.getDocument().getDocumentID());
        if (!arrayList.isEmpty()) {
            fDFDictionary.setFields(arrayList);
        }
        return fDFDocument;
    }

    public void flatten() throws IOException {
        if (xfaIsDynamic()) {
            Log.w("PdfBox-Android", "Flatten for a dynamix XFA form is not supported");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<PDField> it = getFieldTree().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        flatten(arrayList, false);
    }

    public void flatten(List<PDField> list, boolean z) throws IOException {
        PDPageContentStream pDPageContentStream;
        if (xfaIsDynamic()) {
            Log.w("PdfBox-Android", "Flatten for a dynamix XFA form is not supported");
            return;
        }
        if (z) {
            refreshAppearances(list);
        }
        Iterator<PDField> it = list.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            for (PDAnnotationWidget pDAnnotationWidget : it.next().getWidgets()) {
                if (pDAnnotationWidget.getNormalAppearanceStream() != null) {
                    PDPage page = pDAnnotationWidget.getPage();
                    boolean z3 = true;
                    if (!z2) {
                        pDPageContentStream = new PDPageContentStream(this.document, page, PDPageContentStream.AppendMode.APPEND, true, true);
                    } else {
                        z3 = z2;
                        pDPageContentStream = new PDPageContentStream(this.document, page, PDPageContentStream.AppendMode.APPEND, true);
                    }
                    PDFormXObject pDFormXObject = new PDFormXObject(pDAnnotationWidget.getNormalAppearanceStream().getCOSObject());
                    Matrix translateInstance = Matrix.getTranslateInstance(pDAnnotationWidget.getRectangle().getLowerLeftX(), pDAnnotationWidget.getRectangle().getLowerLeftY());
                    pDPageContentStream.saveGraphicsState();
                    pDPageContentStream.transform(translateInstance);
                    pDPageContentStream.drawForm(pDFormXObject);
                    pDPageContentStream.restoreGraphicsState();
                    pDPageContentStream.close();
                    z2 = z3;
                }
            }
        }
        for (PDPage pDPage : this.document.getPages()) {
            ArrayList arrayList = new ArrayList();
            for (PDAnnotation pDAnnotation : pDPage.getAnnotations()) {
                if (!(pDAnnotation instanceof PDAnnotationWidget)) {
                    arrayList.add(pDAnnotation);
                }
            }
            pDPage.setAnnotations(arrayList);
        }
        setFields(Collections.emptyList());
        this.dictionary.removeItem(COSName.XFA);
    }

    public void refreshAppearances() throws IOException {
        for (PDField pDField : getFieldTree()) {
            if (pDField instanceof PDTerminalField) {
                ((PDTerminalField) pDField).constructAppearances();
            }
        }
    }

    public void refreshAppearances(List<PDField> list) throws IOException {
        for (PDField pDField : list) {
            if (pDField instanceof PDTerminalField) {
                ((PDTerminalField) pDField).constructAppearances();
            }
        }
    }

    public List<PDField> getFields() {
        PDField pDFieldFromDictionary;
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.FIELDS);
        if (cOSArray == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            COSDictionary cOSDictionary = (COSDictionary) cOSArray.getObject(i);
            if (cOSDictionary != null && (pDFieldFromDictionary = PDField.fromDictionary(this, cOSDictionary, null)) != null) {
                arrayList.add(pDFieldFromDictionary);
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setFields(List<PDField> list) {
        this.dictionary.setItem(COSName.FIELDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public Iterator<PDField> getFieldIterator() {
        return new PDFieldTree(this).iterator();
    }

    public PDFieldTree getFieldTree() {
        return new PDFieldTree(this);
    }

    public void setCacheFields(boolean z) {
        if (z) {
            this.fieldCache = new HashMap();
            for (PDField pDField : getFieldTree()) {
                this.fieldCache.put(pDField.getFullyQualifiedName(), pDField);
            }
            return;
        }
        this.fieldCache = null;
    }

    public boolean isCachingFields() {
        return this.fieldCache != null;
    }

    public PDField getField(String str) {
        Map<String, PDField> map = this.fieldCache;
        if (map != null) {
            return map.get(str);
        }
        for (PDField pDField : getFieldTree()) {
            if (pDField.getFullyQualifiedName().compareTo(str) == 0) {
                return pDField;
            }
        }
        return null;
    }

    public String getDefaultAppearance() {
        return this.dictionary.getString(COSName.f2249DA, "");
    }

    public void setDefaultAppearance(String str) {
        this.dictionary.setString(COSName.f2249DA, str);
    }

    public boolean getNeedAppearances() {
        return this.dictionary.getBoolean(COSName.NEED_APPEARANCES, false);
    }

    public void setNeedAppearances(Boolean bool) {
        this.dictionary.setBoolean(COSName.NEED_APPEARANCES, bool.booleanValue());
    }

    public PDResources getDefaultResources() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.f2254DR);
        if (cOSDictionary != null) {
            return new PDResources(cOSDictionary, this.document.getResourceCache());
        }
        return null;
    }

    public void setDefaultResources(PDResources pDResources) {
        this.dictionary.setItem(COSName.f2254DR, pDResources);
    }

    public boolean hasXFA() {
        return this.dictionary.containsKey(COSName.XFA);
    }

    public boolean xfaIsDynamic() {
        return hasXFA() && getFields().isEmpty();
    }

    public PDXFAResource getXFA() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.XFA);
        if (dictionaryObject != null) {
            return new PDXFAResource(dictionaryObject);
        }
        return null;
    }

    public void setXFA(PDXFAResource pDXFAResource) {
        this.dictionary.setItem(COSName.XFA, pDXFAResource);
    }

    public int getQ() {
        COSNumber cOSNumber = (COSNumber) this.dictionary.getDictionaryObject(COSName.f2295Q);
        if (cOSNumber != null) {
            return cOSNumber.intValue();
        }
        return 0;
    }

    public void setQ(int i) {
        this.dictionary.setInt(COSName.f2295Q, i);
    }

    public boolean isSignaturesExist() {
        return this.dictionary.getFlag(COSName.SIG_FLAGS, 1);
    }

    public void setSignaturesExist(boolean z) {
        this.dictionary.setFlag(COSName.SIG_FLAGS, 1, z);
    }

    public boolean isAppendOnly() {
        return this.dictionary.getFlag(COSName.SIG_FLAGS, 2);
    }

    public void setAppendOnly(boolean z) {
        this.dictionary.setFlag(COSName.SIG_FLAGS, 2, z);
    }
}
