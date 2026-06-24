package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

public class SetTextRise extends OperatorProcessor {
    @Override
    public String getName() {
        return "Ts";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 1) {
            return;
        }
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSNumber) {
            this.context.getGraphicsState().getTextState().setRise(((COSNumber) cOSBase).floatValue());
        }
    }
}
