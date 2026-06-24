package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import java.io.IOException;
import java.util.List;

public class SetNonStrokingColorSpace extends OperatorProcessor {
    @Override
    public String getName() {
        return "cs";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        PDColorSpace colorSpace = this.context.getResources().getColorSpace((COSName) list.get(0));
        this.context.getGraphicsState().setNonStrokingColorSpace(colorSpace);
        this.context.getGraphicsState().setNonStrokingColor(colorSpace.getInitialColor());
    }
}
