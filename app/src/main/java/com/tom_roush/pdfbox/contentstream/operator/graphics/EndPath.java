package com.tom_roush.pdfbox.contentstream.operator.graphics;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

public final class EndPath extends GraphicsOperatorProcessor {
    @Override
    public String getName() {
        return "n";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.endPath();
    }
}
