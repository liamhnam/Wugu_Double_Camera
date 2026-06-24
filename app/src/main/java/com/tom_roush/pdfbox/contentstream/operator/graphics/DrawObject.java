package com.tom_roush.pdfbox.contentstream.operator.graphics;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.MissingResourceException;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.IOException;
import java.util.List;

public final class DrawObject extends GraphicsOperatorProcessor {
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
            PDXObject xObject = this.context.getResources().getXObject(cOSName);
            if (xObject == null) {
                throw new MissingResourceException("Missing XObject: " + cOSName.getName());
            }
            if (xObject instanceof PDImageXObject) {
                this.context.drawImage((PDImageXObject) xObject);
            } else if (xObject instanceof PDTransparencyGroup) {
                getContext().showTransparencyGroup((PDTransparencyGroup) xObject);
            } else if (xObject instanceof PDFormXObject) {
                getContext().showForm((PDFormXObject) xObject);
            }
        }
    }
}
