package com.tom_roush.pdfbox.contentstream.operator.state;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.List;

public class SetFlatness extends OperatorProcessor {
    @Override
    public String getName() {
        return "i";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.getGraphicsState().setFlatness(((COSNumber) list.get(0)).floatValue());
    }
}
