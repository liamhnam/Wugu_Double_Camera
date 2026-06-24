package com.tom_roush.pdfbox.contentstream.operator;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
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
            COSName cOSName = (COSName) cOSBase;
            if (this.context.getResources().isImageXObject(cOSName)) {
                return;
            }
            PDXObject xObject = this.context.getResources().getXObject(cOSName);
            if (xObject instanceof PDTransparencyGroup) {
                this.context.showTransparencyGroup((PDTransparencyGroup) xObject);
            } else if (xObject instanceof PDFormXObject) {
                this.context.showForm((PDFormXObject) xObject);
            }
        }
    }
}
