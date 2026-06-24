package com.tom_roush.pdfbox.contentstream.operator.graphics;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

public final class ClosePath extends GraphicsOperatorProcessor {
    @Override
    public String getName() {
        return "h";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (this.context.getCurrentPoint() == null) {
            Log.w("PdfBox-Android", "ClosePath wihtout initial MoveTo");
        } else {
            this.context.closePath();
        }
    }
}
