package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.apache.log4j.helpers.DateLayout;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;

public class NullDigest implements Digest {
    private OpenByteArrayOutputStream bOut = new OpenByteArrayOutputStream();

    private static class OpenByteArrayOutputStream extends ByteArrayOutputStream {
        private OpenByteArrayOutputStream() {
        }

        void copy(byte[] bArr, int i) {
            System.arraycopy(this.buf, 0, bArr, i, size());
        }

        @Override
        public void reset() {
            super.reset();
            Arrays.clear(this.buf);
        }
    }

    @Override
    public int doFinal(byte[] bArr, int i) {
        int size = this.bOut.size();
        this.bOut.copy(bArr, i);
        reset();
        return size;
    }

    @Override
    public String getAlgorithmName() {
        return DateLayout.NULL_DATE_FORMAT;
    }

    @Override
    public int getDigestSize() {
        return this.bOut.size();
    }

    @Override
    public void reset() {
        this.bOut.reset();
    }

    @Override
    public void update(byte b) {
        this.bOut.write(b);
    }

    @Override
    public void update(byte[] bArr, int i, int i2) {
        this.bOut.write(bArr, i, i2);
    }
}
