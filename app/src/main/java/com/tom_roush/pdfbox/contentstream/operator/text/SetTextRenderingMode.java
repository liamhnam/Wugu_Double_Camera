package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.graphics.state.RenderingMode;
import java.io.IOException;
import java.util.List;

public class SetTextRenderingMode extends OperatorProcessor {
    @Override
    public String getName() {
        return "Tr";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 1) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSNumber) {
            this.context.getGraphicsState().getTextState().setRenderingMode(RenderingMode.fromInt(((COSNumber) cOSBase).intValue()));
        }
    }
}
