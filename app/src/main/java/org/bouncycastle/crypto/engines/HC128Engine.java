package org.bouncycastle.crypto.engines;

import androidx.core.app.FrameMetricsAggregator;
import com.google.android.material.internal.ViewUtils;
import com.p020hp.jipp.model.Status;
import kotlin.UByte;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class HC128Engine implements StreamCipher {
    private boolean initialised;

    private byte[] f3168iv;
    private byte[] key;

    private int[] f3169p = new int[512];

    private int[] f3170q = new int[512];
    private int cnt = 0;
    private byte[] buf = new byte[4];
    private int idx = 0;

    private static int dim(int i, int i2) {
        return mod512(i - i2);
    }

    private static int m1414f1(int i) {
        return (i >>> 3) ^ (rotateRight(i, 7) ^ rotateRight(i, 18));
    }

    private static int m1415f2(int i) {
        return (i >>> 10) ^ (rotateRight(i, 17) ^ rotateRight(i, 19));
    }

    private int m1416g1(int i, int i2, int i3) {
        return (rotateRight(i, 10) ^ rotateRight(i3, 23)) + rotateRight(i2, 8);
    }

    private int m1417g2(int i, int i2, int i3) {
        return (rotateLeft(i, 10) ^ rotateLeft(i3, 23)) + rotateLeft(i2, 8);
    }

    private byte getByte() {
        if (this.idx == 0) {
            int iStep = step();
            byte[] bArr = this.buf;
            bArr[0] = (byte) (iStep & 255);
            int i = iStep >> 8;
            bArr[1] = (byte) (i & 255);
            int i2 = i >> 8;
            bArr[2] = (byte) (i2 & 255);
            bArr[3] = (byte) ((i2 >> 8) & 255);
        }
        byte[] bArr2 = this.buf;
        int i3 = this.idx;
        byte b = bArr2[i3];
        this.idx = 3 & (i3 + 1);
        return b;
    }

    private int m1418h1(int i) {
        int[] iArr = this.f3170q;
        return iArr[i & 255] + iArr[((i >> 16) & 255) + 256];
    }

    private int m1419h2(int i) {
        int[] iArr = this.f3169p;
        return iArr[i & 255] + iArr[((i >> 16) & 255) + 256];
    }

    private void init() {
        if (this.key.length != 16) {
            throw new IllegalArgumentException("The key must be 128 bits long");
        }
        this.idx = 0;
        this.cnt = 0;
        int[] iArr = new int[Status.Code.serverErrorInternalError];
        for (int i = 0; i < 16; i++) {
            int i2 = i >> 2;
            iArr[i2] = ((this.key[i] & UByte.MAX_VALUE) << ((i & 3) * 8)) | iArr[i2];
        }
        System.arraycopy(iArr, 0, iArr, 4, 4);
        int i3 = 0;
        while (true) {
            byte[] bArr = this.f3168iv;
            if (i3 >= bArr.length || i3 >= 16) {
                break;
            }
            int i4 = (i3 >> 2) + 8;
            iArr[i4] = ((bArr[i3] & UByte.MAX_VALUE) << ((i3 & 3) * 8)) | iArr[i4];
            i3++;
        }
        System.arraycopy(iArr, 8, iArr, 12, 4);
        for (int i5 = 16; i5 < 1280; i5++) {
            iArr[i5] = m1415f2(iArr[i5 - 2]) + iArr[i5 - 7] + m1414f1(iArr[i5 - 15]) + iArr[i5 - 16] + i5;
        }
        System.arraycopy(iArr, 256, this.f3169p, 0, 512);
        System.arraycopy(iArr, ViewUtils.EDGE_TO_EDGE_FLAGS, this.f3170q, 0, 512);
        for (int i6 = 0; i6 < 512; i6++) {
            this.f3169p[i6] = step();
        }
        for (int i7 = 0; i7 < 512; i7++) {
            this.f3170q[i7] = step();
        }
        this.cnt = 0;
    }

    private static int mod1024(int i) {
        return i & 1023;
    }

    private static int mod512(int i) {
        return i & FrameMetricsAggregator.EVERY_DURATION;
    }

    private static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private static int rotateRight(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private int step() {
        int iM1419h2;
        int i;
        int iMod512 = mod512(this.cnt);
        if (this.cnt < 512) {
            int[] iArr = this.f3169p;
            iArr[iMod512] = iArr[iMod512] + m1416g1(iArr[dim(iMod512, 3)], this.f3169p[dim(iMod512, 10)], this.f3169p[dim(iMod512, FrameMetricsAggregator.EVERY_DURATION)]);
            iM1419h2 = m1418h1(this.f3169p[dim(iMod512, 12)]);
            i = this.f3169p[iMod512];
        } else {
            int[] iArr2 = this.f3170q;
            iArr2[iMod512] = iArr2[iMod512] + m1417g2(iArr2[dim(iMod512, 3)], this.f3170q[dim(iMod512, 10)], this.f3170q[dim(iMod512, FrameMetricsAggregator.EVERY_DURATION)]);
            iM1419h2 = m1419h2(this.f3170q[dim(iMod512, 12)]);
            i = this.f3170q[iMod512];
        }
        int i2 = i ^ iM1419h2;
        this.cnt = mod1024(this.cnt + 1);
        return i2;
    }

    @Override
    public String getAlgorithmName() {
        return "HC-128";
    }

    @Override
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters parameters;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f3168iv = parametersWithIV.getIV();
            parameters = parametersWithIV.getParameters();
        } else {
            this.f3168iv = new byte[0];
            parameters = cipherParameters;
        }
        if (!(parameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to HC128 init - " + cipherParameters.getClass().getName());
        }
        this.key = ((KeyParameter) parameters).getKey();
        init();
        this.initialised = true;
    }

    @Override
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            bArr2[i3 + i4] = (byte) (bArr[i + i4] ^ getByte());
        }
        return i2;
    }

    @Override
    public void reset() {
        init();
    }

    @Override
    public byte returnByte(byte b) {
        return (byte) (b ^ getByte());
    }
}
