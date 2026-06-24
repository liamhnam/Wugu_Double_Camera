package com.tom_roush.pdfbox.contentstream.operator.graphics;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDInlineImage;
import java.io.IOException;
import java.util.List;

public final class BeginInlineImage extends GraphicsOperatorProcessor {
    @Override
    public String getName() {
        return "BI";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.drawImage(new PDInlineImage(operator.getImageParameters(), operator.getImageData(), this.context.getResources()));
    }
}
