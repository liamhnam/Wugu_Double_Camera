package com.tom_roush.pdfbox.contentstream.operator.state;

import android.graphics.Paint;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

public class SetLineCapStyle extends OperatorProcessor {
    @Override
    public String getName() {
        return "J";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        Paint.Cap cap;
        if (list.size() < 1) {
            throw new MissingOperandException(operator, list);
        }
        int iIntValue = ((COSNumber) list.get(0)).intValue();
        if (iIntValue == 0) {
            cap = Paint.Cap.BUTT;
        } else if (iIntValue == 1) {
            cap = Paint.Cap.ROUND;
        } else {
            cap = iIntValue != 2 ? null : Paint.Cap.SQUARE;
        }
        this.context.getGraphicsState().setLineCap(cap);
    }
}
