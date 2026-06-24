package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.visible;

import android.graphics.Bitmap;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDSignatureField;
import java.io.IOException;

public interface PDFTemplateBuilder {
    void closeTemplate(PDDocument pDDocument) throws IOException;

    void createAcroForm(PDDocument pDDocument);

    void createAcroFormDictionary(PDAcroForm pDAcroForm, PDSignatureField pDSignatureField) throws IOException;

    void createAffineTransform(byte[] bArr);

    void createAppearanceDictionary(PDFormXObject pDFormXObject, PDSignatureField pDSignatureField) throws IOException;

    void createFormatterRectangle(byte[] bArr);

    void createHolderForm(PDResources pDResources, PDStream pDStream, PDRectangle pDRectangle);

    void createHolderFormResources();

    void createHolderFormStream(PDDocument pDDocument);

    void createImageForm(PDResources pDResources, PDResources pDResources2, PDStream pDStream, PDRectangle pDRectangle, AffineTransform affineTransform, PDImageXObject pDImageXObject) throws IOException;

    void createImageFormResources();

    void createImageFormStream(PDDocument pDDocument);

    void createInnerForm(PDResources pDResources, PDStream pDStream, PDRectangle pDRectangle);

    void createInnerFormResource();

    void createInnerFormStream(PDDocument pDDocument);

    void createPage(PDVisibleSignDesigner pDVisibleSignDesigner);

    void createProcSetArray();

    void createSignature(PDSignatureField pDSignatureField, PDPage pDPage, String str) throws IOException;

    void createSignatureField(PDAcroForm pDAcroForm) throws IOException;

    void createSignatureImage(PDDocument pDDocument, Bitmap bitmap) throws IOException;

    void createSignatureRectangle(PDSignatureField pDSignatureField, PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException;

    void createTemplate(PDPage pDPage) throws IOException;

    void createVisualSignature(PDDocument pDDocument);

    void createWidgetDictionary(PDSignatureField pDSignatureField, PDResources pDResources) throws IOException;

    PDFTemplateStructure getStructure();

    void injectAppearanceStreams(PDStream pDStream, PDStream pDStream2, PDStream pDStream3, COSName cOSName, COSName cOSName2, COSName cOSName3, PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException;

    void injectProcSetArray(PDFormXObject pDFormXObject, PDPage pDPage, PDResources pDResources, PDResources pDResources2, PDResources pDResources3, COSArray cOSArray);

    void insertInnerFormToHolderResources(PDFormXObject pDFormXObject, PDResources pDResources);
}
