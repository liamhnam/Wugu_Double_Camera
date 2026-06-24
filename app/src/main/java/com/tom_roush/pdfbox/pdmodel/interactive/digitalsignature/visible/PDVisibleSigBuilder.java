package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.visible;

import android.graphics.Bitmap;
import android.util.Log;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.LosslessFactory;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDField;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDSignatureField;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PDVisibleSigBuilder implements PDFTemplateBuilder {
    private final PDFTemplateStructure pdfStructure = new PDFTemplateStructure();

    @Override
    public void createPage(PDVisibleSignDesigner pDVisibleSignDesigner) {
        this.pdfStructure.setPage(new PDPage(new PDRectangle(pDVisibleSignDesigner.getPageWidth(), pDVisibleSignDesigner.getPageHeight())));
        Log.i("PdfBox-Android", "PDF page has been created");
    }

    @Override
    public void createTemplate(PDPage pDPage) throws IOException {
        PDDocument pDDocument = new PDDocument();
        pDDocument.addPage(pDPage);
        this.pdfStructure.setTemplate(pDDocument);
    }

    public PDVisibleSigBuilder() {
        Log.i("PdfBox-Android", "PDF Strucure has been created");
    }

    @Override
    public void createAcroForm(PDDocument pDDocument) {
        PDAcroForm pDAcroForm = new PDAcroForm(pDDocument);
        pDDocument.getDocumentCatalog().setAcroForm(pDAcroForm);
        this.pdfStructure.setAcroForm(pDAcroForm);
        Log.i("PdfBox-Android", "AcroForm page has been created");
    }

    @Override
    public PDFTemplateStructure getStructure() {
        return this.pdfStructure;
    }

    @Override
    public void createSignatureField(PDAcroForm pDAcroForm) throws IOException {
        this.pdfStructure.setSignatureField(new PDSignatureField(pDAcroForm));
        Log.i("PdfBox-Android", "Signature field has been created");
    }

    @Override
    public void createSignature(PDSignatureField pDSignatureField, PDPage pDPage, String str) throws IOException {
        PDSignature pDSignature = new PDSignature();
        PDAnnotationWidget pDAnnotationWidget = pDSignatureField.getWidgets().get(0);
        pDSignatureField.setValue(pDSignature);
        pDAnnotationWidget.setPage(pDPage);
        pDPage.getAnnotations().add(pDAnnotationWidget);
        pDSignature.setName(str);
        pDSignature.setByteRange(new int[]{0, 0, 0, 0});
        pDSignature.setContents(new byte[4096]);
        this.pdfStructure.setPdSignature(pDSignature);
        Log.i("PdfBox-Android", "PDSignature has been created");
    }

    @Override
    public void createAcroFormDictionary(PDAcroForm pDAcroForm, PDSignatureField pDSignatureField) throws IOException {
        List<PDField> fields = pDAcroForm.getFields();
        COSDictionary cOSObject = pDAcroForm.getCOSObject();
        pDAcroForm.setSignaturesExist(true);
        pDAcroForm.setAppendOnly(true);
        cOSObject.setDirect(true);
        fields.add(pDSignatureField);
        pDAcroForm.setDefaultAppearance("/sylfaen 0 Tf 0 g");
        this.pdfStructure.setAcroFormFields(fields);
        this.pdfStructure.setAcroFormDictionary(cOSObject);
        Log.i("PdfBox-Android", "AcroForm dictionary has been created");
    }

    @Override
    public void createSignatureRectangle(PDSignatureField pDSignatureField, PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException {
        PDRectangle pDRectangle = new PDRectangle();
        pDRectangle.setUpperRightX(pDVisibleSignDesigner.getxAxis() + pDVisibleSignDesigner.getWidth());
        pDRectangle.setUpperRightY(pDVisibleSignDesigner.getTemplateHeight() - pDVisibleSignDesigner.getyAxis());
        pDRectangle.setLowerLeftY((pDVisibleSignDesigner.getTemplateHeight() - pDVisibleSignDesigner.getyAxis()) - pDVisibleSignDesigner.getHeight());
        pDRectangle.setLowerLeftX(pDVisibleSignDesigner.getxAxis());
        pDSignatureField.getWidgets().get(0).setRectangle(pDRectangle);
        this.pdfStructure.setSignatureRectangle(pDRectangle);
        Log.i("PdfBox-Android", "Signature rectangle has been created");
    }

    @Override
    public void createAffineTransform(byte[] bArr) {
        this.pdfStructure.setAffineTransform(new AffineTransform(bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5]));
        Log.i("PdfBox-Android", "Matrix has been added");
    }

    @Override
    public void createProcSetArray() {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) COSName.getPDFName("PDF"));
        cOSArray.add((COSBase) COSName.getPDFName("Text"));
        cOSArray.add((COSBase) COSName.getPDFName("ImageB"));
        cOSArray.add((COSBase) COSName.getPDFName("ImageC"));
        cOSArray.add((COSBase) COSName.getPDFName("ImageI"));
        this.pdfStructure.setProcSet(cOSArray);
        Log.i("PdfBox-Android", "ProcSet array has been created");
    }

    @Override
    public void createSignatureImage(PDDocument pDDocument, Bitmap bitmap) throws IOException {
        this.pdfStructure.setImage(LosslessFactory.createFromImage(pDDocument, bitmap));
        Log.i("PdfBox-Android", "Visible Signature Image has been created");
    }

    @Override
    public void createFormatterRectangle(byte[] bArr) {
        PDRectangle pDRectangle = new PDRectangle();
        pDRectangle.setUpperRightX(bArr[0]);
        pDRectangle.setUpperRightY(bArr[1]);
        pDRectangle.setLowerLeftX(bArr[2]);
        pDRectangle.setLowerLeftY(bArr[3]);
        this.pdfStructure.setFormatterRectangle(pDRectangle);
        Log.i("PdfBox-Android", "Formatter rectangle has been created");
    }

    @Override
    public void createHolderFormStream(PDDocument pDDocument) {
        this.pdfStructure.setHolderFormStream(new PDStream(pDDocument));
        Log.i("PdfBox-Android", "Holder form stream has been created");
    }

    @Override
    public void createHolderFormResources() {
        this.pdfStructure.setHolderFormResources(new PDResources());
        Log.i("PdfBox-Android", "Holder form resources have been created");
    }

    @Override
    public void createHolderForm(PDResources pDResources, PDStream pDStream, PDRectangle pDRectangle) {
        PDFormXObject pDFormXObject = new PDFormXObject(pDStream);
        pDFormXObject.setResources(pDResources);
        pDFormXObject.setBBox(pDRectangle);
        pDFormXObject.setFormType(1);
        this.pdfStructure.setHolderForm(pDFormXObject);
        Log.i("PdfBox-Android", "Holder form has been created");
    }

    @Override
    public void createAppearanceDictionary(PDFormXObject pDFormXObject, PDSignatureField pDSignatureField) throws IOException {
        PDAppearanceDictionary pDAppearanceDictionary = new PDAppearanceDictionary();
        pDAppearanceDictionary.getCOSObject().setDirect(true);
        pDAppearanceDictionary.setNormalAppearance(new PDAppearanceStream(pDFormXObject.getCOSObject()));
        pDSignatureField.getWidgets().get(0).setAppearance(pDAppearanceDictionary);
        this.pdfStructure.setAppearanceDictionary(pDAppearanceDictionary);
        Log.i("PdfBox-Android", "PDF appearance dictionary has been created");
    }

    @Override
    public void createInnerFormStream(PDDocument pDDocument) {
        this.pdfStructure.setInnterFormStream(new PDStream(pDDocument));
        Log.i("PdfBox-Android", "Stream of another form (inner form - it will be inside holder form) has been created");
    }

    @Override
    public void createInnerFormResource() {
        this.pdfStructure.setInnerFormResources(new PDResources());
        Log.i("PdfBox-Android", "Resources of another form (inner form - it will be inside holder form) has been created");
    }

    @Override
    public void createInnerForm(PDResources pDResources, PDStream pDStream, PDRectangle pDRectangle) {
        PDFormXObject pDFormXObject = new PDFormXObject(pDStream);
        pDFormXObject.setResources(pDResources);
        pDFormXObject.setBBox(pDRectangle);
        pDFormXObject.setFormType(1);
        this.pdfStructure.setInnerForm(pDFormXObject);
        Log.i("PdfBox-Android", "Another form (inner form - it will be inside holder form) has been created");
    }

    @Override
    public void insertInnerFormToHolderResources(PDFormXObject pDFormXObject, PDResources pDResources) {
        this.pdfStructure.setInnerFormName(pDResources.add(pDFormXObject, "FRM"));
        Log.i("PdfBox-Android", "Now inserted inner form inside holder form");
    }

    @Override
    public void createImageFormStream(PDDocument pDDocument) {
        this.pdfStructure.setImageFormStream(new PDStream(pDDocument));
        Log.i("PdfBox-Android", "Created image form stream");
    }

    @Override
    public void createImageFormResources() {
        this.pdfStructure.setImageFormResources(new PDResources());
        Log.i("PdfBox-Android", "Created image form Resources");
    }

    @Override
    public void createImageForm(PDResources pDResources, PDResources pDResources2, PDStream pDStream, PDRectangle pDRectangle, AffineTransform affineTransform, PDImageXObject pDImageXObject) throws IOException {
        PDFormXObject pDFormXObject = new PDFormXObject(pDStream);
        pDFormXObject.setBBox(pDRectangle);
        pDFormXObject.setMatrix(affineTransform);
        pDFormXObject.setResources(pDResources);
        pDFormXObject.setFormType(1);
        pDResources.getCOSObject().setDirect(true);
        COSName cOSNameAdd = pDResources2.add(pDFormXObject, "n");
        COSName cOSNameAdd2 = pDResources.add(pDImageXObject, "img");
        this.pdfStructure.setImageForm(pDFormXObject);
        this.pdfStructure.setImageFormName(cOSNameAdd);
        this.pdfStructure.setImageName(cOSNameAdd2);
        Log.i("PdfBox-Android", "Created image form");
    }

    @Override
    public void injectProcSetArray(PDFormXObject pDFormXObject, PDPage pDPage, PDResources pDResources, PDResources pDResources2, PDResources pDResources3, COSArray cOSArray) {
        pDFormXObject.getResources().getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        pDPage.getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        pDResources.getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        pDResources2.getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        pDResources3.getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        Log.i("PdfBox-Android", "Inserted ProcSet to PDF");
    }

    @Override
    public void injectAppearanceStreams(PDStream pDStream, PDStream pDStream2, PDStream pDStream3, COSName cOSName, COSName cOSName2, COSName cOSName3, PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException {
        String str = "q 100 0 0 50 0 0 cm /" + cOSName2.getName() + " Do Q\n";
        String str2 = "q 1 0 0 1 0 0 cm /" + cOSName3.getName() + " Do Q \n";
        String str3 = "q 1 0 0 1 0 0 cm /" + cOSName.getName() + " Do Q\n";
        appendRawCommands(this.pdfStructure.getHolderFormStream().createOutputStream(), str2);
        appendRawCommands(this.pdfStructure.getInnerFormStream().createOutputStream(), str3);
        appendRawCommands(this.pdfStructure.getImageFormStream().createOutputStream(), str);
        Log.i("PdfBox-Android", "Injected appearance stream to pdf");
    }

    public void appendRawCommands(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes("UTF-8"));
        outputStream.close();
    }

    @Override
    public void createVisualSignature(PDDocument pDDocument) {
        this.pdfStructure.setVisualSignature(pDDocument.getDocument());
        Log.i("PdfBox-Android", "Visible signature has been created");
    }

    @Override
    public void createWidgetDictionary(PDSignatureField pDSignatureField, PDResources pDResources) throws IOException {
        COSDictionary cOSObject = pDSignatureField.getWidgets().get(0).getCOSObject();
        cOSObject.setNeedToBeUpdated(true);
        cOSObject.setItem(COSName.f2254DR, (COSBase) pDResources.getCOSObject());
        this.pdfStructure.setWidgetDictionary(cOSObject);
        Log.i("PdfBox-Android", "WidgetDictionary has been created");
    }

    @Override
    public void closeTemplate(PDDocument pDDocument) throws IOException {
        pDDocument.close();
        this.pdfStructure.getTemplate().close();
    }
}
