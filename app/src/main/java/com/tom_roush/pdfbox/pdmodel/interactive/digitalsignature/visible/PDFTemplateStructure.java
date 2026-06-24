package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.visible;

import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDField;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDSignatureField;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFTemplateStructure {
    private PDAcroForm acroForm;
    private COSDictionary acroFormDictionary;
    private List<PDField> acroFormFields;
    private AffineTransform affineTransform;
    private PDAppearanceDictionary appearanceDictionary;
    private PDRectangle formatterRectangle;
    private PDFormXObject holderForm;
    private PDResources holderFormResources;
    private PDStream holderFormStream;
    private PDImageXObject image;
    private PDFormXObject imageForm;
    private COSName imageFormName;
    private PDResources imageFormResources;
    private PDStream imageFormStream;
    private COSName imageName;
    private PDFormXObject innerForm;
    private COSName innerFormName;
    private PDResources innerFormResources;
    private PDStream innterFormStream;
    private PDPage page;
    private PDSignature pdSignature;
    private COSArray procSet;
    private PDSignatureField signatureField;
    private PDRectangle signatureRectangle;
    private PDDocument template;
    private COSDocument visualSignature;
    private COSDictionary widgetDictionary;

    public PDPage getPage() {
        return this.page;
    }

    public void setPage(PDPage pDPage) {
        this.page = pDPage;
    }

    public PDDocument getTemplate() {
        return this.template;
    }

    public void setTemplate(PDDocument pDDocument) {
        this.template = pDDocument;
    }

    public PDAcroForm getAcroForm() {
        return this.acroForm;
    }

    public void setAcroForm(PDAcroForm pDAcroForm) {
        this.acroForm = pDAcroForm;
    }

    public PDSignatureField getSignatureField() {
        return this.signatureField;
    }

    public void setSignatureField(PDSignatureField pDSignatureField) {
        this.signatureField = pDSignatureField;
    }

    public PDSignature getPdSignature() {
        return this.pdSignature;
    }

    public void setPdSignature(PDSignature pDSignature) {
        this.pdSignature = pDSignature;
    }

    public COSDictionary getAcroFormDictionary() {
        return this.acroFormDictionary;
    }

    public void setAcroFormDictionary(COSDictionary cOSDictionary) {
        this.acroFormDictionary = cOSDictionary;
    }

    public PDRectangle getSignatureRectangle() {
        return this.signatureRectangle;
    }

    public void setSignatureRectangle(PDRectangle pDRectangle) {
        this.signatureRectangle = pDRectangle;
    }

    public AffineTransform getAffineTransform() {
        return this.affineTransform;
    }

    public void setAffineTransform(AffineTransform affineTransform) {
        this.affineTransform = affineTransform;
    }

    public COSArray getProcSet() {
        return this.procSet;
    }

    public void setProcSet(COSArray cOSArray) {
        this.procSet = cOSArray;
    }

    public PDImageXObject getImage() {
        return this.image;
    }

    public void setImage(PDImageXObject pDImageXObject) {
        this.image = pDImageXObject;
    }

    public PDRectangle getFormatterRectangle() {
        return this.formatterRectangle;
    }

    public void setFormatterRectangle(PDRectangle pDRectangle) {
        this.formatterRectangle = pDRectangle;
    }

    public PDStream getHolderFormStream() {
        return this.holderFormStream;
    }

    public void setHolderFormStream(PDStream pDStream) {
        this.holderFormStream = pDStream;
    }

    public PDFormXObject getHolderForm() {
        return this.holderForm;
    }

    public void setHolderForm(PDFormXObject pDFormXObject) {
        this.holderForm = pDFormXObject;
    }

    public PDResources getHolderFormResources() {
        return this.holderFormResources;
    }

    public void setHolderFormResources(PDResources pDResources) {
        this.holderFormResources = pDResources;
    }

    public PDAppearanceDictionary getAppearanceDictionary() {
        return this.appearanceDictionary;
    }

    public void setAppearanceDictionary(PDAppearanceDictionary pDAppearanceDictionary) {
        this.appearanceDictionary = pDAppearanceDictionary;
    }

    public PDStream getInnerFormStream() {
        return this.innterFormStream;
    }

    public void setInnterFormStream(PDStream pDStream) {
        this.innterFormStream = pDStream;
    }

    public PDResources getInnerFormResources() {
        return this.innerFormResources;
    }

    public void setInnerFormResources(PDResources pDResources) {
        this.innerFormResources = pDResources;
    }

    public PDFormXObject getInnerForm() {
        return this.innerForm;
    }

    public void setInnerForm(PDFormXObject pDFormXObject) {
        this.innerForm = pDFormXObject;
    }

    public COSName getInnerFormName() {
        return this.innerFormName;
    }

    public void setInnerFormName(COSName cOSName) {
        this.innerFormName = cOSName;
    }

    public PDStream getImageFormStream() {
        return this.imageFormStream;
    }

    public void setImageFormStream(PDStream pDStream) {
        this.imageFormStream = pDStream;
    }

    public PDResources getImageFormResources() {
        return this.imageFormResources;
    }

    public void setImageFormResources(PDResources pDResources) {
        this.imageFormResources = pDResources;
    }

    public PDFormXObject getImageForm() {
        return this.imageForm;
    }

    public void setImageForm(PDFormXObject pDFormXObject) {
        this.imageForm = pDFormXObject;
    }

    public COSName getImageFormName() {
        return this.imageFormName;
    }

    public void setImageFormName(COSName cOSName) {
        this.imageFormName = cOSName;
    }

    public COSName getImageName() {
        return this.imageName;
    }

    public void setImageName(COSName cOSName) {
        this.imageName = cOSName;
    }

    public COSDocument getVisualSignature() {
        return this.visualSignature;
    }

    public void setVisualSignature(COSDocument cOSDocument) {
        this.visualSignature = cOSDocument;
    }

    public List<PDField> getAcroFormFields() {
        return this.acroFormFields;
    }

    public void setAcroFormFields(List<PDField> list) {
        this.acroFormFields = list;
    }

    public ByteArrayInputStream getTemplateAppearanceStream() throws IOException {
        COSDocument visualSignature = getVisualSignature();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new COSWriter(byteArrayOutputStream).write(visualSignature);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        getTemplate().close();
        return byteArrayInputStream;
    }

    public COSDictionary getWidgetDictionary() {
        return this.widgetDictionary;
    }

    public void setWidgetDictionary(COSDictionary cOSDictionary) {
        this.widgetDictionary = cOSDictionary;
    }
}
