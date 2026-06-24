package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

public class ShowTextLineAndSpace extends OperatorProcessor {
    @Override
    public String getName() {
        return "\"";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 3) {
            throw new MissingOperandException(operator, list);
        }
        this.context.processOperator("Tw", list.subList(0, 1));
        this.context.processOperator("Tc", list.subList(1, 2));
        this.context.processOperator("'", list.subList(2, 3));
    }
}
