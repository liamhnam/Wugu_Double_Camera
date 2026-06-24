package org.bouncycastle.x509.util;

public class StreamParsingException extends Exception {

    Throwable f3772_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f3772_e = th;
    }

    @Override
    public Throwable getCause() {
        return this.f3772_e;
    }
}
