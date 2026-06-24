package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.util.List;

public class BeginText extends OperatorProcessor {
    @Override
    public String getName() {
        return "BT";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.setTextMatrix(new Matrix());
        this.context.setTextLineMatrix(new Matrix());
        this.context.beginText();
    }
}
