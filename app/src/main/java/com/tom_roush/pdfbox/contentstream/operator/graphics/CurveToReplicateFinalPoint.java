package com.tom_roush.pdfbox.contentstream.operator.graphics;

import android.graphics.PointF;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

public final class CurveToReplicateFinalPoint extends GraphicsOperatorProcessor {
    @Override
    public String getName() {
        return "y";
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
            PointF pointFTransformedPoint = this.context.transformedPoint(cOSNumber.floatValue(), cOSNumber2.floatValue());
            PointF pointFTransformedPoint2 = this.context.transformedPoint(cOSNumber3.floatValue(), cOSNumber4.floatValue());
            this.context.curveTo(pointFTransformedPoint.x, pointFTransformedPoint.y, pointFTransformedPoint2.x, pointFTransformedPoint2.y, pointFTransformedPoint2.x, pointFTransformedPoint2.y);
        }
    }
}
