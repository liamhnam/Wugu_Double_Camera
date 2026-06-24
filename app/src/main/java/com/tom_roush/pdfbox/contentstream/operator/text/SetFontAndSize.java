package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

public class SetFontAndSize extends OperatorProcessor {
    @Override
    public String getName() {
        return "Tf";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 2) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(0);
        COSBase cOSBase2 = list.get(1);
        if ((cOSBase instanceof COSName) && (cOSBase2 instanceof COSNumber)) {
            this.context.getGraphicsState().getTextState().setFontSize(((COSNumber) cOSBase2).floatValue());
            this.context.getGraphicsState().getTextState().setFont(this.context.getResources().getFont((COSName) cOSBase));
        }
    }
}
