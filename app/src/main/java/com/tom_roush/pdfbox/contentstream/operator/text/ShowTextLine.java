package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

public class ShowTextLine extends OperatorProcessor {
    @Override
    public String getName() {
        return "'";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.processOperator("T*", (List<COSBase>) null);
        this.context.processOperator("Tj", list);
    }
}
