package com.tom_roush.pdfbox.contentstream.operator.markedcontent;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.text.PDFMarkedContentExtractor;
import java.io.IOException;
import java.util.List;

public class EndMarkedContentSequence extends OperatorProcessor {
    @Override
    public String getName() {
        return "EMC";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (this.context instanceof PDFMarkedContentExtractor) {
            ((PDFMarkedContentExtractor) this.context).endMarkedContentSequence();
        }
    }
}
