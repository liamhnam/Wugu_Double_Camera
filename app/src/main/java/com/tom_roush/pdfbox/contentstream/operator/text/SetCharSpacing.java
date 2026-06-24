package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

public class SetCharSpacing extends OperatorProcessor {
    @Override
    public String getName() {
        return "Tc";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.isEmpty()) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(list.size() - 1);
        if (cOSBase instanceof COSNumber) {
            this.context.getGraphicsState().getTextState().setCharacterSpacing(((COSNumber) cOSBase).floatValue());
        }
    }
}
