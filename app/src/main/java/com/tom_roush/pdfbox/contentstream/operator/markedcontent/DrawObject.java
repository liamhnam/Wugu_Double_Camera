package com.tom_roush.pdfbox.contentstream.operator.markedcontent;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.text.PDFMarkedContentExtractor;
import java.io.IOException;
import java.util.List;

public class DrawObject extends OperatorProcessor {
    @Override
    public String getName() {
        return "Do";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 1) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSName) {
            PDXObject xObject = this.context.getResources().getXObject((COSName) cOSBase);
            ((PDFMarkedContentExtractor) this.context).xobject(xObject);
            if (xObject instanceof PDTransparencyGroup) {
                this.context.showTransparencyGroup((PDTransparencyGroup) xObject);
            } else if (xObject instanceof PDFormXObject) {
                this.context.showForm((PDFormXObject) xObject);
            }
        }
    }
}
