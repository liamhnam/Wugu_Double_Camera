package com.tom_roush.pdfbox.contentstream.operator.graphics;

import android.graphics.Path;
import com.p020hp.jipp.model.Media;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.List;

public class FillNonZeroRule extends GraphicsOperatorProcessor {
    @Override
    public String getName() {
        return Media.f730f;
    }

    @Override
    public final void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.fillPath(Path.FillType.WINDING);
    }
}
