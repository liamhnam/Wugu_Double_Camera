package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

public class ShowTextAdjusted extends OperatorProcessor {
    @Override
    public String getName() {
        return "TJ";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 1) {
            return;
        }
        COSBase cOSBase = list.get(0);
        if ((cOSBase instanceof COSArray) && this.context.getTextMatrix() != null) {
            this.context.showTextStrings((COSArray) cOSBase);
        }
    }
}
