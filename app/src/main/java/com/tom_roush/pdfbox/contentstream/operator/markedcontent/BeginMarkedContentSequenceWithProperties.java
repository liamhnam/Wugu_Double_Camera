package com.tom_roush.pdfbox.contentstream.operator.markedcontent;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.text.PDFMarkedContentExtractor;
import java.io.IOException;
import java.util.List;

public class BeginMarkedContentSequenceWithProperties extends OperatorProcessor {
    @Override
    public String getName() {
        return "BDC";
    }

    @Override
    public void process(Operator operator, List<COSBase> list) throws IOException {
        COSName cOSName = null;
        COSDictionary cOSDictionary = null;
        for (COSBase cOSBase : list) {
            if (cOSBase instanceof COSName) {
                cOSName = (COSName) cOSBase;
            } else if (cOSBase instanceof COSDictionary) {
                cOSDictionary = (COSDictionary) cOSBase;
            }
        }
        if (this.context instanceof PDFMarkedContentExtractor) {
            ((PDFMarkedContentExtractor) this.context).beginMarkedContentSequence(cOSName, cOSDictionary);
        }
    }
}
