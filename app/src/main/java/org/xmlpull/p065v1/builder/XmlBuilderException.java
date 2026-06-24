package org.xmlpull.p065v1.builder;

import java.io.PrintStream;
import java.io.PrintWriter;

public class XmlBuilderException extends RuntimeException {
    protected Throwable detail;

    public XmlBuilderException(String str) {
        super(str);
    }

    public XmlBuilderException(String str, Throwable th) {
        super(str);
        this.detail = th;
    }

    public Throwable getDetail() {
        return this.detail;
    }

    @Override
    public String getMessage() {
        if (this.detail == null) {
            return super.getMessage();
        }
        return new StringBuffer().append(super.getMessage()).append("; nested exception is: \n\t").append(this.detail.getMessage()).toString();
    }

    @Override
    public void printStackTrace(PrintStream printStream) {
        if (this.detail == null) {
            super.printStackTrace(printStream);
            return;
        }
        synchronized (printStream) {
            printStream.println(new StringBuffer().append(super.getMessage()).append("; nested exception is:").toString());
            this.detail.printStackTrace(printStream);
        }
    }

    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintWriter printWriter) {
        if (this.detail == null) {
            super.printStackTrace(printWriter);
            return;
        }
        synchronized (printWriter) {
            printWriter.println(new StringBuffer().append(super.getMessage()).append("; nested exception is:").toString());
            this.detail.printStackTrace(printWriter);
        }
    }
}
