package com.tom_roush.pdfbox.contentstream.operator.state;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.graphics.state.RenderingIntent;
import java.io.IOException;
import java.util.List;

public class SetRenderingIntent extends OperatorProcessor {
    @Override
    public String getName() {
        return "ri";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 1) {
            throw new MissingOperandException(operator, list);
        }
        this.context.getGraphicsState().setRenderingIntent(RenderingIntent.fromString(((COSName) list.get(0)).getName()));
    }
}
