package org.apache.http.conn.ssl;

public class AllowAllHostnameVerifier extends AbstractVerifier {
    public final String toString() {
        return "ALLOW_ALL";
    }

    @Override
    public final void verify(String str, String[] strArr, String[] strArr2) {
    }
}
