package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

public class KDFParameters implements DerivationParameters {

    byte[] f3383iv;
    byte[] shared;

    public KDFParameters(byte[] bArr, byte[] bArr2) {
        this.shared = bArr;
        this.f3383iv = bArr2;
    }

    public byte[] getIV() {
        return this.f3383iv;
    }

    public byte[] getSharedSecret() {
        return this.shared;
    }
}
