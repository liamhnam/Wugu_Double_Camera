package org.bouncycastle.jcajce.p054io;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

class DigestUpdatingOutputStream extends OutputStream {
    private MessageDigest digest;

    DigestUpdatingOutputStream(MessageDigest messageDigest) {
        this.digest = messageDigest;
    }

    @Override
    public void write(int i) throws IOException {
        this.digest.update((byte) i);
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        this.digest.update(bArr);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.digest.update(bArr, i, i2);
    }
}
