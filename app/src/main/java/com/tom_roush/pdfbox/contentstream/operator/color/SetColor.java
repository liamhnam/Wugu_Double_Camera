package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceColorSpace;
import java.io.IOException;
import java.util.List;

public abstract class SetColor extends OperatorProcessor {
    protected abstract PDColor getColor();

    protected abstract PDColorSpace getColorSpace();

    protected abstract void setColor(PDColor pDColor);

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        PDColorSpace colorSpace = getColorSpace();
        if ((colorSpace instanceof PDDeviceColorSpace) && list.size() < colorSpace.getNumberOfComponents()) {
            throw new MissingOperandException(operator, list);
        }
        COSArray cOSArray = new COSArray();
        cOSArray.addAll(list);
        setColor(new PDColor(cOSArray, colorSpace));
    }
}
