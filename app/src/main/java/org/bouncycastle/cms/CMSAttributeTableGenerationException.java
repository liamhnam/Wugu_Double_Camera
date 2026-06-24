package org.bouncycastle.cms;

public class CMSAttributeTableGenerationException extends CMSRuntimeException {

    Exception f2918e;

    public CMSAttributeTableGenerationException(String str) {
        super(str);
    }

    public CMSAttributeTableGenerationException(String str, Exception exc) {
        super(str);
        this.f2918e = exc;
    }

    @Override
    public Throwable getCause() {
        return this.f2918e;
    }

    @Override
    public Exception getUnderlyingException() {
        return this.f2918e;
    }
}
