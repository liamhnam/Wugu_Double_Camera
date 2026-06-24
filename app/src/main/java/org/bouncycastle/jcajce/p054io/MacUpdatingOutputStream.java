package org.bouncycastle.jcajce.p054io;

import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Mac;

class MacUpdatingOutputStream extends OutputStream {
    private Mac mac;

    MacUpdatingOutputStream(Mac mac) {
        this.mac = mac;
    }

    @Override
    public void write(int i) throws IOException {
        this.mac.update((byte) i);
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        this.mac.update(bArr);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.mac.update(bArr, i, i2);
    }
}
