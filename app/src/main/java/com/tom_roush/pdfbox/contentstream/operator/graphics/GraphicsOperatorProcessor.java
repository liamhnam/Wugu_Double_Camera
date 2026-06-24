package com.tom_roush.pdfbox.contentstream.operator.graphics;

import com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine;
import com.tom_roush.pdfbox.contentstream.PDFStreamEngine;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;

public abstract class GraphicsOperatorProcessor extends OperatorProcessor {
    protected PDFGraphicsStreamEngine context;

    @Override
    public void setContext(PDFStreamEngine pDFStreamEngine) {
        super.setContext(pDFStreamEngine);
        this.context = (PDFGraphicsStreamEngine) pDFStreamEngine;
    }
}
