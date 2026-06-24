package com.tom_roush.pdfbox.contentstream.operator.graphics;

import com.p020hp.jipp.model.Media;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

public final class CloseFillNonZeroAndStrokePath extends GraphicsOperatorProcessor {
    @Override
    public String getName() {
        return Media.f726b;
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.processOperator("h", list);
        this.context.processOperator("B", list);
    }
}
