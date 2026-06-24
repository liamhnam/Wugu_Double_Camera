package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSString;
import java.io.IOException;
import java.util.List;

public class ShowText extends OperatorProcessor {
    @Override
    public String getName() {
        return "Tj";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 1) {
            return;
        }
        COSBase cOSBase = list.get(0);
        if ((cOSBase instanceof COSString) && this.context.getTextMatrix() != null) {
            this.context.showTextString(((COSString) cOSBase).getBytes());
        }
    }
}
