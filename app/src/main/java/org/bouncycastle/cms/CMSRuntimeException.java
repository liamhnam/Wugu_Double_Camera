package org.bouncycastle.cms;

public class CMSRuntimeException extends RuntimeException {

    Exception f2920e;

    public CMSRuntimeException(String str) {
        super(str);
    }

    public CMSRuntimeException(String str, Exception exc) {
        super(str);
        this.f2920e = exc;
    }

    @Override
    public Throwable getCause() {
        return this.f2920e;
    }

    public Exception getUnderlyingException() {
        return this.f2920e;
    }
}
