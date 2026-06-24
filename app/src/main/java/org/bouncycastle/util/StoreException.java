package org.bouncycastle.util;

public class StoreException extends RuntimeException {

    private Throwable f3769_e;

    public StoreException(String str, Throwable th) {
        super(str);
        this.f3769_e = th;
    }

    @Override
    public Throwable getCause() {
        return this.f3769_e;
    }
}
