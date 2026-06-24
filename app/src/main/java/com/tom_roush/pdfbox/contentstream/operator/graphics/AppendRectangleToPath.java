package com.tom_roush.pdfbox.contentstream.operator.graphics;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

public final class AppendRectangleToPath extends GraphicsOperatorProcessor {
    @Override
    public String getName() {
        return "re";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 4) {
            throw new MissingOperandException(operator, list);
        }
        if (checkArrayTypesClass(list, COSNumber.class)) {
            COSNumber cOSNumber = (COSNumber) list.get(0);
            COSNumber cOSNumber2 = (COSNumber) list.get(1);
            COSNumber cOSNumber3 = (COSNumber) list.get(2);
            COSNumber cOSNumber4 = (COSNumber) list.get(3);
            float fFloatValue = cOSNumber.floatValue();
            float fFloatValue2 = cOSNumber2.floatValue();
            float fFloatValue3 = cOSNumber3.floatValue() + fFloatValue;
            float fFloatValue4 = cOSNumber4.floatValue() + fFloatValue2;
            this.context.appendRectangle(this.context.transformedPoint(fFloatValue, fFloatValue2), this.context.transformedPoint(fFloatValue3, fFloatValue2), this.context.transformedPoint(fFloatValue3, fFloatValue4), this.context.transformedPoint(fFloatValue, fFloatValue4));
        }
    }
}
