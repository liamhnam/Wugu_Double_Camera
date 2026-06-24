package com.tom_roush.pdfbox.contentstream.operator.text;

import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoveTextSetLeading extends OperatorProcessor {
    @Override
    public String getName() {
        return StandardStructureTypes.f2380TD;
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 2) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(1);
        if (cOSBase instanceof COSNumber) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new COSFloat(((COSNumber) cOSBase).floatValue() * (-1.0f)));
            this.context.processOperator("TL", arrayList);
            this.context.processOperator("Td", list);
        }
    }
}
