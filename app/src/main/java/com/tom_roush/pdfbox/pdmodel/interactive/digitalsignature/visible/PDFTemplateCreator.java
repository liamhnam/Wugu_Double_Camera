package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.visible;

import android.util.Log;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDSignatureField;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PDFTemplateCreator {
    PDFTemplateBuilder pdfBuilder;

    public PDFTemplateCreator(PDFTemplateBuilder pDFTemplateBuilder) {
        this.pdfBuilder = pDFTemplateBuilder;
    }

    public PDFTemplateStructure getPdfStructure() {
        return this.pdfBuilder.getStructure();
    }

    public InputStream buildPDF(PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException {
        Log.i("PdfBox-Android", "pdf building has been started");
        PDFTemplateStructure structure = this.pdfBuilder.getStructure();
        this.pdfBuilder.createProcSetArray();
        this.pdfBuilder.createPage(pDVisibleSignDesigner);
        PDPage page = structure.getPage();
        this.pdfBuilder.createTemplate(page);
        PDDocument template = structure.getTemplate();
        this.pdfBuilder.createAcroForm(template);
        PDAcroForm acroForm = structure.getAcroForm();
        this.pdfBuilder.createSignatureField(acroForm);
        PDSignatureField signatureField = structure.getSignatureField();
        this.pdfBuilder.createSignature(signatureField, page, pDVisibleSignDesigner.getSignatureFieldName());
        this.pdfBuilder.createAcroFormDictionary(acroForm, signatureField);
        this.pdfBuilder.createAffineTransform(pDVisibleSignDesigner.getAffineTransformParams());
        AffineTransform affineTransform = structure.getAffineTransform();
        this.pdfBuilder.createSignatureRectangle(signatureField, pDVisibleSignDesigner);
        this.pdfBuilder.createFormatterRectangle(pDVisibleSignDesigner.getFormatterRectangleParams());
        PDRectangle formatterRectangle = structure.getFormatterRectangle();
        this.pdfBuilder.createSignatureImage(template, pDVisibleSignDesigner.getImage());
        this.pdfBuilder.createHolderFormStream(template);
        PDStream holderFormStream = structure.getHolderFormStream();
        this.pdfBuilder.createHolderFormResources();
        PDResources holderFormResources = structure.getHolderFormResources();
        this.pdfBuilder.createHolderForm(holderFormResources, holderFormStream, formatterRectangle);
        this.pdfBuilder.createAppearanceDictionary(structure.getHolderForm(), signatureField);
        this.pdfBuilder.createInnerFormStream(template);
        this.pdfBuilder.createInnerFormResource();
        PDResources innerFormResources = structure.getInnerFormResources();
        this.pdfBuilder.createInnerForm(innerFormResources, structure.getInnerFormStream(), formatterRectangle);
        PDFormXObject innerForm = structure.getInnerForm();
        this.pdfBuilder.insertInnerFormToHolderResources(innerForm, holderFormResources);
        this.pdfBuilder.createImageFormStream(template);
        PDStream imageFormStream = structure.getImageFormStream();
        this.pdfBuilder.createImageFormResources();
        PDResources imageFormResources = structure.getImageFormResources();
        this.pdfBuilder.createImageForm(imageFormResources, innerFormResources, imageFormStream, formatterRectangle, affineTransform, structure.getImage());
        this.pdfBuilder.injectProcSetArray(innerForm, page, innerFormResources, imageFormResources, holderFormResources, structure.getProcSet());
        this.pdfBuilder.injectAppearanceStreams(holderFormStream, imageFormStream, imageFormStream, structure.getImageFormName(), structure.getImageName(), structure.getInnerFormName(), pDVisibleSignDesigner);
        this.pdfBuilder.createVisualSignature(template);
        this.pdfBuilder.createWidgetDictionary(signatureField, holderFormResources);
        ByteArrayInputStream templateAppearanceStream = structure.getTemplateAppearanceStream();
        Log.i("PdfBox-Android", "stream returning started, size= " + templateAppearanceStream.available());
        template.close();
        return templateAppearanceStream;
    }
}
