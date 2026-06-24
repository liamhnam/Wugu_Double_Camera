package com.tom_roush.pdfbox.multipdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.p022io.MemoryUsageSetting;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PDFMergerUtility {
    private static final String STRUCTURETYPE_DOCUMENT = "Document";
    private String destinationFileName;
    private OutputStream destinationStream;
    private boolean ignoreAcroFormErrors = false;
    private int nextFieldNum = 1;
    private final List<InputStream> sources = new ArrayList();
    private final List<FileInputStream> fileInputStreams = new ArrayList();

    public String getDestinationFileName() {
        return this.destinationFileName;
    }

    public void setDestinationFileName(String str) {
        this.destinationFileName = str;
    }

    public OutputStream getDestinationStream() {
        return this.destinationStream;
    }

    public void setDestinationStream(OutputStream outputStream) {
        this.destinationStream = outputStream;
    }

    public void addSource(String str) throws FileNotFoundException {
        addSource(new File(str));
    }

    public void addSource(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        this.sources.add(fileInputStream);
        this.fileInputStreams.add(fileInputStream);
    }

    public void addSource(InputStream inputStream) {
        this.sources.add(inputStream);
    }

    public void addSources(List<InputStream> list) {
        this.sources.addAll(list);
    }

    @Deprecated
    public void mergeDocuments() throws Throwable {
        mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }

    public void mergeDocuments(MemoryUsageSetting memoryUsageSetting) throws Throwable {
        MemoryUsageSetting partitionedCopy;
        List<InputStream> list = this.sources;
        if (list == null || list.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        PDDocument pDDocument = null;
        try {
            if (memoryUsageSetting != null) {
                partitionedCopy = memoryUsageSetting.getPartitionedCopy(this.sources.size() + 1);
            } else {
                partitionedCopy = MemoryUsageSetting.setupMainMemoryOnly();
            }
            Iterator<InputStream> it = this.sources.iterator();
            PDDocument pDDocument2 = new PDDocument(partitionedCopy);
            while (it.hasNext()) {
                try {
                    PDDocument pDDocumentLoad = PDDocument.load(it.next(), partitionedCopy);
                    arrayList.add(pDDocumentLoad);
                    appendDocument(pDDocument2, pDDocumentLoad);
                } catch (Throwable th) {
                    th = th;
                    pDDocument = pDDocument2;
                    if (pDDocument != null) {
                        pDDocument.close();
                    }
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ((PDDocument) it2.next()).close();
                    }
                    Iterator<FileInputStream> it3 = this.fileInputStreams.iterator();
                    while (it3.hasNext()) {
                        it3.next().close();
                    }
                    throw th;
                }
            }
            OutputStream outputStream = this.destinationStream;
            if (outputStream == null) {
                pDDocument2.save(this.destinationFileName);
            } else {
                pDDocument2.save(outputStream);
            }
            pDDocument2.close();
            Iterator it4 = arrayList.iterator();
            while (it4.hasNext()) {
                ((PDDocument) it4.next()).close();
            }
            Iterator<FileInputStream> it5 = this.fileInputStreams.iterator();
            while (it5.hasNext()) {
                it5.next().close();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void appendDocument(com.tom_roush.pdfbox.pdmodel.PDDocument r21, com.tom_roush.pdfbox.pdmodel.PDDocument r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 913
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.multipdf.PDFMergerUtility.appendDocument(com.tom_roush.pdfbox.pdmodel.PDDocument, com.tom_roush.pdfbox.pdmodel.PDDocument):void");
    }

    private void mergeAcroForm(PDFCloneUtility pDFCloneUtility, PDAcroForm pDAcroForm, PDAcroForm pDAcroForm2) throws IOException {
        if (pDAcroForm2.getFields() != null) {
            Iterator<PDField> it = pDAcroForm.getFieldTree().iterator();
            while (it.hasNext()) {
                String partialName = it.next().getPartialName();
                if (partialName.startsWith("dummyFieldName")) {
                    this.nextFieldNum = Math.max(this.nextFieldNum, Integer.parseInt(partialName.substring(14, partialName.length())) + 1);
                }
            }
            COSArray cOSArray = (COSArray) pDAcroForm.getCOSObject().getItem(COSName.FIELDS);
            for (PDField pDField : pDAcroForm2.getFieldTree()) {
                COSDictionary cOSDictionary = (COSDictionary) pDFCloneUtility.cloneForNewDocument(pDField.getCOSObject());
                if (pDAcroForm.getField(pDField.getFullyQualifiedName()) != null) {
                    COSName cOSName = COSName.f2310T;
                    StringBuilder sb = new StringBuilder("dummyFieldName");
                    int i = this.nextFieldNum;
                    this.nextFieldNum = i + 1;
                    cOSDictionary.setString(cOSName, sb.append(i).toString());
                }
                cOSArray.add((COSBase) cOSDictionary);
            }
            pDAcroForm.getCOSObject().setItem(COSName.FIELDS, (COSBase) cOSArray);
        }
    }

    public boolean isIgnoreAcroFormErrors() {
        return this.ignoreAcroFormErrors;
    }

    public void setIgnoreAcroFormErrors(boolean z) {
        this.ignoreAcroFormErrors = z;
    }

    private void updatePageReferences(COSDictionary cOSDictionary, Map<COSDictionary, COSDictionary> map) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.f2293PG);
        if ((dictionaryObject instanceof COSDictionary) && map.containsKey(dictionaryObject)) {
            cOSDictionary.setItem(COSName.f2293PG, (COSBase) map.get(dictionaryObject));
        }
        COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.OBJ);
        if ((dictionaryObject2 instanceof COSDictionary) && map.containsKey(dictionaryObject2)) {
            cOSDictionary.setItem(COSName.OBJ, (COSBase) map.get(dictionaryObject2));
        }
        COSBase dictionaryObject3 = cOSDictionary.getDictionaryObject(COSName.f2274K);
        if (dictionaryObject3 instanceof COSArray) {
            updatePageReferences((COSArray) dictionaryObject3, map);
        } else if (dictionaryObject3 instanceof COSDictionary) {
            updatePageReferences((COSDictionary) dictionaryObject3, map);
        }
    }

    private void updatePageReferences(COSArray cOSArray, Map<COSDictionary, COSDictionary> map) {
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSArray) {
                updatePageReferences((COSArray) object, map);
            } else if (object instanceof COSDictionary) {
                updatePageReferences((COSDictionary) object, map);
            }
        }
    }

    private void updateParentEntry(COSArray cOSArray, COSDictionary cOSDictionary) {
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSDictionary) {
                COSDictionary cOSDictionary2 = (COSDictionary) object;
                if (cOSDictionary2.getDictionaryObject(COSName.f2292P) != null) {
                    cOSDictionary2.setItem(COSName.f2292P, (COSBase) cOSDictionary);
                }
            }
        }
    }

    private void updateStructParentEntries(PDPage pDPage, int i) throws IOException {
        pDPage.setStructParents(pDPage.getStructParents() + i);
        List<PDAnnotation> annotations = pDPage.getAnnotations();
        ArrayList arrayList = new ArrayList();
        for (PDAnnotation pDAnnotation : annotations) {
            pDAnnotation.setStructParent(pDAnnotation.getStructParent() + i);
            arrayList.add(pDAnnotation);
        }
        pDPage.setAnnotations(arrayList);
    }

    private boolean isDynamicXfa(PDAcroForm pDAcroForm) {
        return pDAcroForm != null && pDAcroForm.xfaIsDynamic();
    }
}
