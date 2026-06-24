package com.tom_roush.pdfbox.contentstream.operator.state;

import android.util.Log;
import com.p020hp.jipp.model.Media;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.util.Iterator;
import java.util.List;

public class SetLineDashPattern extends OperatorProcessor {
    @Override
    public String getName() {
        return Media.f728d;
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws MissingOperandException {
        COSBase next;
        if (list.size() < 2) {
            throw new MissingOperandException(operator, list);
        }
        boolean z = false;
        COSBase cOSBase = list.get(0);
        if (cOSBase instanceof COSArray) {
            COSBase cOSBase2 = list.get(1);
            if (cOSBase2 instanceof COSNumber) {
                COSArray cOSArray = (COSArray) cOSBase;
                int iIntValue = ((COSNumber) cOSBase2).intValue();
                Iterator<COSBase> it = cOSArray.iterator();
                do {
                    if (it.hasNext()) {
                        next = it.next();
                        if (!(next instanceof COSNumber)) {
                            Log.e("PdfBox-Android", "dash array has non number element " + next + ", ignored");
                            cOSArray = new COSArray();
                        }
                    }
                    z = true;
                    break;
                } while (((COSNumber) next).floatValue() == 0.0f);
                if (cOSArray.size() > 0 && z) {
                    Log.e("PdfBox-Android", "dash lengths all zero, ignored");
                    cOSArray = new COSArray();
                }
                this.context.setLineDashPattern(cOSArray, iIntValue);
            }
        }
    }
}
