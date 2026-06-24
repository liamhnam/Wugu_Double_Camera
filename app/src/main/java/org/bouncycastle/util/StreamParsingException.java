package org.bouncycastle.util;

public class StreamParsingException extends Exception {

    Throwable f3770_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f3770_e = th;
    }

    @Override
    public Throwable getCause() {
        return this.f3770_e;
    }
}
