package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.List;

public class SetStrokingDeviceCMYKColor extends SetStrokingColor {
    @Override
    public String getName() {
        return "K";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.getGraphicsState().setStrokingColorSpace(this.context.getResources().getColorSpace(COSName.DEVICECMYK));
        super.process(operator, list);
    }
}
