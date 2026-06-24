package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

public class ParametersWithID implements CipherParameters {

    private byte[] f3386id;
    private CipherParameters parameters;

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.f3386id = bArr;
    }

    public byte[] getID() {
        return this.f3386id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
