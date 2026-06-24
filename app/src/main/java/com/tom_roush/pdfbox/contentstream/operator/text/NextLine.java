package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSFloat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NextLine extends OperatorProcessor {
    @Override
    public String getName() {
        return "T*";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new COSFloat(0.0f));
        arrayList.add(new COSFloat(this.context.getGraphicsState().getTextState().getLeading() * (-1.0f)));
        this.context.processOperator("Td", arrayList);
    }
}
