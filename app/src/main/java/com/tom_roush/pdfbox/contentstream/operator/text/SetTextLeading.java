package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.util.List;

public class SetTextLeading extends OperatorProcessor {
    @Override
    public String getName() {
        return "TL";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) {
        this.context.getGraphicsState().getTextState().setLeading(((COSNumber) list.get(0)).floatValue());
    }
}
