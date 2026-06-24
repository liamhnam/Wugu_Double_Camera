package com.tom_roush.pdfbox.multipdf;

import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDDocumentCatalog;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentProperties;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LayerUtility {
    private static final boolean DEBUG = true;
    private static final Set<String> PAGE_TO_FORM_FILTER = new HashSet(Arrays.asList(PDAnnotationMarkup.RT_GROUP, "LastModified", "Metadata"));
    private final PDFCloneUtility cloner;
    private final PDDocument targetDoc;

    public LayerUtility(PDDocument pDDocument) {
        this.targetDoc = pDDocument;
        this.cloner = new PDFCloneUtility(pDDocument);
    }

    public PDDocument getDocument() {
        return this.targetDoc;
    }

    public void wrapInSaveRestore(PDPage pDPage) throws IOException {
        COSStream cOSStreamCreateCOSStream = getDocument().getDocument().createCOSStream();
        OutputStream outputStreamCreateOutputStream = cOSStreamCreateCOSStream.createOutputStream();
        outputStreamCreateOutputStream.write("q\n".getBytes("ISO-8859-1"));
        outputStreamCreateOutputStream.close();
        COSStream cOSStreamCreateCOSStream2 = getDocument().getDocument().createCOSStream();
        OutputStream outputStreamCreateOutputStream2 = cOSStreamCreateCOSStream2.createOutputStream();
        outputStreamCreateOutputStream2.write("Q\n".getBytes("ISO-8859-1"));
        outputStreamCreateOutputStream2.close();
        COSDictionary cOSObject = pDPage.getCOSObject();
        COSBase dictionaryObject = cOSObject.getDictionaryObject(COSName.CONTENTS);
        if (dictionaryObject instanceof COSStream) {
            COSArray cOSArray = new COSArray();
            cOSArray.add((COSBase) cOSStreamCreateCOSStream);
            cOSArray.add(dictionaryObject);
            cOSArray.add((COSBase) cOSStreamCreateCOSStream2);
            cOSObject.setItem(COSName.CONTENTS, (COSBase) cOSArray);
            return;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray2 = (COSArray) dictionaryObject;
            cOSArray2.add(0, cOSStreamCreateCOSStream);
            cOSArray2.add((COSBase) cOSStreamCreateCOSStream2);
            return;
        }
        throw new IOException("Contents are unknown type: " + dictionaryObject.getClass().getName());
    }

    public PDFormXObject importPageAsForm(PDDocument pDDocument, int i) throws IOException {
        return importPageAsForm(pDDocument, pDDocument.getPage(i));
    }

    public PDFormXObject importPageAsForm(PDDocument pDDocument, PDPage pDPage) throws IOException {
        PDFormXObject pDFormXObject = new PDFormXObject(new PDStream(this.targetDoc, pDPage.getContents(), COSName.FLATE_DECODE));
        PDResources resources = pDPage.getResources();
        PDResources pDResources = new PDResources();
        this.cloner.cloneMerge(resources, pDResources);
        pDFormXObject.setResources(pDResources);
        transferDict(pDPage.getCOSObject(), pDFormXObject.getCOSObject(), PAGE_TO_FORM_FILTER, true);
        AffineTransform affineTransformCreateAffineTransform = pDFormXObject.getMatrix().createAffineTransform();
        PDRectangle mediaBox = pDPage.getMediaBox();
        PDRectangle cropBox = pDPage.getCropBox();
        if (cropBox == null) {
            cropBox = mediaBox;
        }
        int rotation = pDPage.getRotation();
        affineTransformCreateAffineTransform.translate(mediaBox.getLowerLeftX() - cropBox.getLowerLeftX(), mediaBox.getLowerLeftY() - cropBox.getLowerLeftY());
        if (rotation == 90) {
            affineTransformCreateAffineTransform.scale(cropBox.getWidth() / cropBox.getHeight(), cropBox.getHeight() / cropBox.getWidth());
            affineTransformCreateAffineTransform.translate(0.0d, cropBox.getWidth());
            affineTransformCreateAffineTransform.rotate(-1.5707963267948966d);
        } else if (rotation == 180) {
            affineTransformCreateAffineTransform.translate(cropBox.getWidth(), cropBox.getHeight());
            affineTransformCreateAffineTransform.rotate(-3.141592653589793d);
        } else if (rotation == 270) {
            affineTransformCreateAffineTransform.scale(cropBox.getWidth() / cropBox.getHeight(), cropBox.getHeight() / cropBox.getWidth());
            affineTransformCreateAffineTransform.translate(cropBox.getHeight(), 0.0d);
            affineTransformCreateAffineTransform.rotate(-4.71238898038469d);
        }
        affineTransformCreateAffineTransform.translate(-cropBox.getLowerLeftX(), -cropBox.getLowerLeftY());
        if (!affineTransformCreateAffineTransform.isIdentity()) {
            pDFormXObject.setMatrix(affineTransformCreateAffineTransform);
        }
        BoundingBox boundingBox = new BoundingBox();
        boundingBox.setLowerLeftX(cropBox.getLowerLeftX());
        boundingBox.setLowerLeftY(cropBox.getLowerLeftY());
        boundingBox.setUpperRightX(cropBox.getUpperRightX());
        boundingBox.setUpperRightY(cropBox.getUpperRightY());
        pDFormXObject.setBBox(new PDRectangle(boundingBox));
        return pDFormXObject;
    }

    public PDOptionalContentGroup appendFormAsLayer(PDPage pDPage, PDFormXObject pDFormXObject, AffineTransform affineTransform, String str) throws IOException {
        PDDocumentCatalog documentCatalog = this.targetDoc.getDocumentCatalog();
        PDOptionalContentProperties oCProperties = documentCatalog.getOCProperties();
        if (oCProperties == null) {
            oCProperties = new PDOptionalContentProperties();
            documentCatalog.setOCProperties(oCProperties);
        }
        if (oCProperties.hasGroup(str)) {
            throw new IllegalArgumentException("Optional group (layer) already exists: " + str);
        }
        PDOptionalContentGroup pDOptionalContentGroup = new PDOptionalContentGroup(str);
        oCProperties.addGroup(pDOptionalContentGroup);
        PDPageContentStream pDPageContentStream = new PDPageContentStream(this.targetDoc, pDPage, PDPageContentStream.AppendMode.APPEND, false);
        pDPageContentStream.beginMarkedContent(COSName.f2287OC, pDOptionalContentGroup);
        pDPageContentStream.saveGraphicsState();
        pDPageContentStream.transform(new Matrix(affineTransform));
        pDPageContentStream.drawForm(pDFormXObject);
        pDPageContentStream.restoreGraphicsState();
        pDPageContentStream.endMarkedContent();
        pDPageContentStream.close();
        return pDOptionalContentGroup;
    }

    private void transferDict(COSDictionary cOSDictionary, COSDictionary cOSDictionary2, Set<String> set, boolean z) throws IOException {
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            COSName key = entry.getKey();
            if (!z || set.contains(key.getName())) {
                if (z || !set.contains(key.getName())) {
                    cOSDictionary2.setItem(key, this.cloner.cloneForNewDocument(entry.getValue()));
                }
            }
        }
    }
}
