package org.bouncycastle.cms;

public class CMSException extends Exception {

    Exception f2919e;

    public CMSException(String str) {
        super(str);
    }

    public CMSException(String str, Exception exc) {
        super(str);
        this.f2919e = exc;
    }

    @Override
    public Throwable getCause() {
        return this.f2919e;
    }

    public Exception getUnderlyingException() {
        return this.f2919e;
    }
}
