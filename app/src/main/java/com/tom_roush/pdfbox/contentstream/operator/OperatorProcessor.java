package com.tom_roush.pdfbox.contentstream.operator;

import com.tom_roush.pdfbox.contentstream.PDFStreamEngine;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public abstract class OperatorProcessor {
    protected PDFStreamEngine context;

    public abstract String getName();

    public abstract void process(Operator operator, List<COSBase> list) throws IOException;

    protected OperatorProcessor() {
    }

    protected final PDFStreamEngine getContext() {
        return this.context;
    }

    public void setContext(PDFStreamEngine pDFStreamEngine) {
        this.context = pDFStreamEngine;
    }

    public boolean checkArrayTypesClass(List<COSBase> list, Class cls) {
        Iterator<COSBase> it = list.iterator();
        while (it.hasNext()) {
            if (!cls.isInstance(it.next())) {
                return false;
            }
        }
        return true;
    }
}
