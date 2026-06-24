package org.bouncycastle.tsp.ers;

class ExpUtil {
    ExpUtil() {
    }

    static IllegalStateException createIllegalState(String str, Throwable th) {
        return new IllegalStateException(str, th);
    }
}
