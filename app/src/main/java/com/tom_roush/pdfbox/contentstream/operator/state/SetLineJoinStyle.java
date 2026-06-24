package com.tom_roush.pdfbox.contentstream.operator.state;

import android.graphics.Paint;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

public class SetLineJoinStyle extends OperatorProcessor {
    @Override
    public String getName() {
        return "j";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        Paint.Join join;
        if (list.size() < 1) {
            throw new MissingOperandException(operator, list);
        }
        int iIntValue = ((COSNumber) list.get(0)).intValue();
        if (iIntValue == 0) {
            join = Paint.Join.MITER;
        } else if (iIntValue == 1) {
            join = Paint.Join.ROUND;
        } else {
            join = iIntValue != 2 ? null : Paint.Join.BEVEL;
        }
        this.context.getGraphicsState().setLineJoin(join);
    }
}
