package com.tom_roush.pdfbox.contentstream.operator.state;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.util.Matrix;
import java.util.List;

public class SetMatrix extends OperatorProcessor {
    @Override
    public String getName() {
        return "Tm";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws MissingOperandException {
        if (list.size() < 6) {
            throw new MissingOperandException(operator, list);
        }
        Matrix matrix = new Matrix(((COSNumber) list.get(0)).floatValue(), ((COSNumber) list.get(1)).floatValue(), ((COSNumber) list.get(2)).floatValue(), ((COSNumber) list.get(3)).floatValue(), ((COSNumber) list.get(4)).floatValue(), ((COSNumber) list.get(5)).floatValue());
        this.context.setTextMatrix(matrix);
        this.context.setTextLineMatrix(matrix.m1575clone());
    }
}
