package com.brother.sdk.print.info;

public class MibControlUnsupportedOperationException extends MibControlException {
    private static final long serialVersionUID = 1;

    public MibControlUnsupportedOperationException() {
    }

    public MibControlUnsupportedOperationException(String str, Throwable th) {
        super(str, th);
    }

    public MibControlUnsupportedOperationException(String str) {
        super(str);
    }

    public MibControlUnsupportedOperationException(Throwable th) {
        super(th);
    }
}
