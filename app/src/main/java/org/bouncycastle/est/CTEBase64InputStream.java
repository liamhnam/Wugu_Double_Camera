package org.bouncycastle.est;

import com.google.android.material.internal.ViewUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.UByte;
import org.bouncycastle.util.encoders.Base64;

class CTEBase64InputStream extends InputStream {
    protected boolean end;
    protected final Long max;
    protected long read;

    protected int f3417rp;
    protected final InputStream src;

    protected int f3418wp;
    protected final byte[] rawBuf = new byte[1024];
    protected final byte[] data = new byte[ViewUtils.EDGE_TO_EDGE_FLAGS];
    protected final OutputStream dataOutputStream = new OutputStream() {
        @Override
        public void write(int i) throws IOException {
            byte[] bArr = CTEBase64InputStream.this.data;
            CTEBase64InputStream cTEBase64InputStream = CTEBase64InputStream.this;
            int i2 = cTEBase64InputStream.f3418wp;
            cTEBase64InputStream.f3418wp = i2 + 1;
            bArr[i2] = (byte) i;
        }
    };

    public CTEBase64InputStream(InputStream inputStream, Long l) {
        this.src = inputStream;
        this.max = l;
    }

    @Override
    public void close() throws IOException {
        this.src.close();
    }

    protected int pullFromSrc() throws IOException {
        int i;
        if (this.read >= this.max.longValue()) {
            return -1;
        }
        int i2 = 0;
        do {
            i = this.src.read();
            if (i >= 33 || i == 13 || i == 10) {
                byte[] bArr = this.rawBuf;
                if (i2 >= bArr.length) {
                    throw new IOException("Content Transfer Encoding, base64 line length > 1024");
                }
                bArr[i2] = (byte) i;
                this.read++;
                i2++;
            } else if (i >= 0) {
                this.read++;
            }
            if (i <= -1 || i2 >= this.rawBuf.length || i == 10) {
                break;
            }
        } while (this.read < this.max.longValue());
        if (i2 > 0) {
            try {
                Base64.decode(this.rawBuf, 0, i2, this.dataOutputStream);
            } catch (Exception e) {
                throw new IOException("Decode Base64 Content-Transfer-Encoding: " + e);
            }
        } else if (i == -1) {
            return -1;
        }
        return this.f3418wp;
    }

    @Override
    public int read() throws IOException {
        if (this.f3417rp == this.f3418wp) {
            this.f3417rp = 0;
            this.f3418wp = 0;
            int iPullFromSrc = pullFromSrc();
            if (iPullFromSrc == -1) {
                return iPullFromSrc;
            }
        }
        byte[] bArr = this.data;
        int i = this.f3417rp;
        this.f3417rp = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }
}
