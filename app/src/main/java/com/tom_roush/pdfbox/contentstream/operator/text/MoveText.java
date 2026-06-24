package com.tom_roush.pdfbox.contentstream.operator.text;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.util.Matrix;
import java.util.List;

public class MoveText extends OperatorProcessor {
    @Override
    public String getName() {
        return "Td";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws MissingOperandException {
        if (list.size() < 2) {
            throw new MissingOperandException(operator, list);
        }
        Matrix textLineMatrix = this.context.getTextLineMatrix();
        if (textLineMatrix == null) {
            Log.w("PdfBox-Android", "TextLineMatrix is null, " + getName() + " operator will be ignored");
            return;
        }
        COSBase cOSBase = list.get(0);
        COSBase cOSBase2 = list.get(1);
        if ((cOSBase instanceof COSNumber) && (cOSBase2 instanceof COSNumber)) {
            textLineMatrix.concatenate(new Matrix(1.0f, 0.0f, 0.0f, 1.0f, ((COSNumber) cOSBase).floatValue(), ((COSNumber) cOSBase2).floatValue()));
            this.context.setTextMatrix(textLineMatrix.m1575clone());
        }
    }
}
