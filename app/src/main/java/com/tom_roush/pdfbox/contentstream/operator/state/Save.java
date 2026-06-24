package com.tom_roush.pdfbox.contentstream.operator.state;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import java.util.List;

public class Save extends OperatorProcessor {
    @Override
    public String getName() {
        return "q";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) {
        this.context.saveGraphicsState();
    }
}
