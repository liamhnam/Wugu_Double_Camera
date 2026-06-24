package com.tom_roush.pdfbox.contentstream.operator.state;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

public class Restore extends OperatorProcessor {
    @Override
    public String getName() {
        return "Q";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (this.context.getGraphicsStackSize() > 1) {
            this.context.restoreGraphicsState();
            return;
        }
        throw new EmptyGraphicsStackException();
    }
}
