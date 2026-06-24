package com.tom_roush.pdfbox.contentstream.operator.state;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import java.io.IOException;
import java.util.List;

public class SetGraphicsStateParameters extends OperatorProcessor {
    @Override
    public String getName() {
        return "gs";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 1) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSName) {
            COSName cOSName = (COSName) cOSBase;
            PDExtendedGraphicsState extGState = this.context.getResources().getExtGState(cOSName);
            if (extGState == null) {
                throw new IOException("name for 'gs' operator not found in resources: /" + cOSName.getName());
            }
            extGState.copyIntoGraphicsState(this.context.getGraphicsState());
        }
    }
}
