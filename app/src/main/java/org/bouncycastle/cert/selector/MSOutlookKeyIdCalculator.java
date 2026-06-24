package org.bouncycastle.cert.selector;

import java.io.IOException;
import kotlin.UByte;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Pack;

class MSOutlookKeyIdCalculator {

    private static abstract class GeneralDigest {
        private static final int BYTE_LENGTH = 64;
        private long byteCount;
        private byte[] xBuf;
        private int xBufOff;

        protected GeneralDigest() {
            this.xBuf = new byte[4];
            this.xBufOff = 0;
        }

        protected GeneralDigest(GeneralDigest generalDigest) {
            this.xBuf = new byte[generalDigest.xBuf.length];
            copyIn(generalDigest);
        }

        protected void copyIn(GeneralDigest generalDigest) {
            byte[] bArr = generalDigest.xBuf;
            System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
            this.xBufOff = generalDigest.xBufOff;
            this.byteCount = generalDigest.byteCount;
        }

        public void finish() {
            long j = this.byteCount << 3;
            byte b = -128;
            while (true) {
                update(b);
                if (this.xBufOff == 0) {
                    processLength(j);
                    processBlock();
                    return;
                }
                b = 0;
            }
        }

        protected abstract void processBlock();

        protected abstract void processLength(long j);

        protected abstract void processWord(byte[] bArr, int i);

        public void reset() {
            this.byteCount = 0L;
            this.xBufOff = 0;
            int i = 0;
            while (true) {
                byte[] bArr = this.xBuf;
                if (i >= bArr.length) {
                    return;
                }
                bArr[i] = 0;
                i++;
            }
        }

        public void update(byte b) {
            byte[] bArr = this.xBuf;
            int i = this.xBufOff;
            int i2 = i + 1;
            this.xBufOff = i2;
            bArr[i] = b;
            if (i2 == bArr.length) {
                processWord(bArr, 0);
                this.xBufOff = 0;
            }
            this.byteCount++;
        }

        public void update(byte[] bArr, int i, int i2) {
            while (this.xBufOff != 0 && i2 > 0) {
                update(bArr[i]);
                i++;
                i2--;
            }
            while (i2 > this.xBuf.length) {
                processWord(bArr, i);
                byte[] bArr2 = this.xBuf;
                i += bArr2.length;
                i2 -= bArr2.length;
                this.byteCount += (long) bArr2.length;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i++;
                i2--;
            }
        }
    }

    private static class SHA1Digest extends GeneralDigest {
        private static final int DIGEST_LENGTH = 20;

        private static final int f2908Y1 = 1518500249;

        private static final int f2909Y2 = 1859775393;

        private static final int f2910Y3 = -1894007588;

        private static final int f2911Y4 = -899497514;

        private int f2912H1;

        private int f2913H2;

        private int f2914H3;

        private int f2915H4;

        private int f2916H5;

        private int[] f2917X = new int[80];
        private int xOff;

        public SHA1Digest() {
            reset();
        }

        private int m1340f(int i, int i2, int i3) {
            return ((~i) & i3) | (i2 & i);
        }

        private int m1341g(int i, int i2, int i3) {
            return (i & i3) | (i & i2) | (i2 & i3);
        }

        private int m1342h(int i, int i2, int i3) {
            return (i ^ i2) ^ i3;
        }

        public int doFinal(byte[] bArr, int i) {
            finish();
            Pack.intToBigEndian(this.f2912H1, bArr, i);
            Pack.intToBigEndian(this.f2913H2, bArr, i + 4);
            Pack.intToBigEndian(this.f2914H3, bArr, i + 8);
            Pack.intToBigEndian(this.f2915H4, bArr, i + 12);
            Pack.intToBigEndian(this.f2916H5, bArr, i + 16);
            reset();
            return 20;
        }

        public String getAlgorithmName() {
            return McElieceCCA2KeyGenParameterSpec.SHA1;
        }

        public int getDigestSize() {
            return 20;
        }

        @Override
        protected void processBlock() {
            for (int i = 16; i < 80; i++) {
                int[] iArr = this.f2917X;
                int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
                iArr[i] = (i2 >>> 31) | (i2 << 1);
            }
            int iM1342h = this.f2912H1;
            int iM1342h2 = this.f2913H2;
            int i3 = this.f2914H3;
            int i4 = this.f2915H4;
            int i5 = this.f2916H5;
            int i6 = 0;
            int i7 = 0;
            while (i6 < 4) {
                int i8 = i7 + 1;
                int iM1340f = i5 + ((iM1342h << 5) | (iM1342h >>> 27)) + m1340f(iM1342h2, i3, i4) + this.f2917X[i7] + f2908Y1;
                int i9 = (iM1342h2 >>> 2) | (iM1342h2 << 30);
                int i10 = i8 + 1;
                int iM1340f2 = i4 + ((iM1340f << 5) | (iM1340f >>> 27)) + m1340f(iM1342h, i9, i3) + this.f2917X[i8] + f2908Y1;
                int i11 = (iM1342h >>> 2) | (iM1342h << 30);
                int i12 = i10 + 1;
                int iM1340f3 = i3 + ((iM1340f2 << 5) | (iM1340f2 >>> 27)) + m1340f(iM1340f, i11, i9) + this.f2917X[i10] + f2908Y1;
                i5 = (iM1340f >>> 2) | (iM1340f << 30);
                int i13 = i12 + 1;
                iM1342h2 = i9 + ((iM1340f3 << 5) | (iM1340f3 >>> 27)) + m1340f(iM1340f2, i5, i11) + this.f2917X[i12] + f2908Y1;
                i4 = (iM1340f2 >>> 2) | (iM1340f2 << 30);
                iM1342h = i11 + ((iM1342h2 << 5) | (iM1342h2 >>> 27)) + m1340f(iM1340f3, i4, i5) + this.f2917X[i13] + f2908Y1;
                i3 = (iM1340f3 >>> 2) | (iM1340f3 << 30);
                i6++;
                i7 = i13 + 1;
            }
            int i14 = 0;
            while (i14 < 4) {
                int i15 = i7 + 1;
                int iM1342h3 = i5 + ((iM1342h << 5) | (iM1342h >>> 27)) + m1342h(iM1342h2, i3, i4) + this.f2917X[i7] + f2909Y2;
                int i16 = (iM1342h2 >>> 2) | (iM1342h2 << 30);
                int i17 = i15 + 1;
                int iM1342h4 = i4 + ((iM1342h3 << 5) | (iM1342h3 >>> 27)) + m1342h(iM1342h, i16, i3) + this.f2917X[i15] + f2909Y2;
                int i18 = (iM1342h >>> 2) | (iM1342h << 30);
                int i19 = i17 + 1;
                int iM1342h5 = i3 + ((iM1342h4 << 5) | (iM1342h4 >>> 27)) + m1342h(iM1342h3, i18, i16) + this.f2917X[i17] + f2909Y2;
                i5 = (iM1342h3 >>> 2) | (iM1342h3 << 30);
                int i20 = i19 + 1;
                iM1342h2 = i16 + ((iM1342h5 << 5) | (iM1342h5 >>> 27)) + m1342h(iM1342h4, i5, i18) + this.f2917X[i19] + f2909Y2;
                i4 = (iM1342h4 >>> 2) | (iM1342h4 << 30);
                iM1342h = i18 + ((iM1342h2 << 5) | (iM1342h2 >>> 27)) + m1342h(iM1342h5, i4, i5) + this.f2917X[i20] + f2909Y2;
                i3 = (iM1342h5 >>> 2) | (iM1342h5 << 30);
                i14++;
                i7 = i20 + 1;
            }
            int i21 = 0;
            while (i21 < 4) {
                int i22 = i7 + 1;
                int iM1341g = i5 + ((iM1342h << 5) | (iM1342h >>> 27)) + m1341g(iM1342h2, i3, i4) + this.f2917X[i7] + f2910Y3;
                int i23 = (iM1342h2 >>> 2) | (iM1342h2 << 30);
                int i24 = i22 + 1;
                int iM1341g2 = i4 + ((iM1341g << 5) | (iM1341g >>> 27)) + m1341g(iM1342h, i23, i3) + this.f2917X[i22] + f2910Y3;
                int i25 = (iM1342h >>> 2) | (iM1342h << 30);
                int i26 = i24 + 1;
                int iM1341g3 = i3 + ((iM1341g2 << 5) | (iM1341g2 >>> 27)) + m1341g(iM1341g, i25, i23) + this.f2917X[i24] + f2910Y3;
                i5 = (iM1341g >>> 2) | (iM1341g << 30);
                int i27 = i26 + 1;
                iM1342h2 = i23 + ((iM1341g3 << 5) | (iM1341g3 >>> 27)) + m1341g(iM1341g2, i5, i25) + this.f2917X[i26] + f2910Y3;
                i4 = (iM1341g2 >>> 2) | (iM1341g2 << 30);
                iM1342h = i25 + ((iM1342h2 << 5) | (iM1342h2 >>> 27)) + m1341g(iM1341g3, i4, i5) + this.f2917X[i27] + f2910Y3;
                i3 = (iM1341g3 >>> 2) | (iM1341g3 << 30);
                i21++;
                i7 = i27 + 1;
            }
            int i28 = 0;
            while (i28 <= 3) {
                int i29 = i7 + 1;
                int iM1342h6 = i5 + ((iM1342h << 5) | (iM1342h >>> 27)) + m1342h(iM1342h2, i3, i4) + this.f2917X[i7] + f2911Y4;
                int i30 = (iM1342h2 >>> 2) | (iM1342h2 << 30);
                int i31 = i29 + 1;
                int iM1342h7 = i4 + ((iM1342h6 << 5) | (iM1342h6 >>> 27)) + m1342h(iM1342h, i30, i3) + this.f2917X[i29] + f2911Y4;
                int i32 = (iM1342h >>> 2) | (iM1342h << 30);
                int i33 = i31 + 1;
                int iM1342h8 = i3 + ((iM1342h7 << 5) | (iM1342h7 >>> 27)) + m1342h(iM1342h6, i32, i30) + this.f2917X[i31] + f2911Y4;
                i5 = (iM1342h6 >>> 2) | (iM1342h6 << 30);
                int i34 = i33 + 1;
                iM1342h2 = i30 + ((iM1342h8 << 5) | (iM1342h8 >>> 27)) + m1342h(iM1342h7, i5, i32) + this.f2917X[i33] + f2911Y4;
                i4 = (iM1342h7 >>> 2) | (iM1342h7 << 30);
                iM1342h = i32 + ((iM1342h2 << 5) | (iM1342h2 >>> 27)) + m1342h(iM1342h8, i4, i5) + this.f2917X[i34] + f2911Y4;
                i3 = (iM1342h8 >>> 2) | (iM1342h8 << 30);
                i28++;
                i7 = i34 + 1;
            }
            this.f2912H1 += iM1342h;
            this.f2913H2 += iM1342h2;
            this.f2914H3 += i3;
            this.f2915H4 += i4;
            this.f2916H5 += i5;
            this.xOff = 0;
            for (int i35 = 0; i35 < 16; i35++) {
                this.f2917X[i35] = 0;
            }
        }

        @Override
        protected void processLength(long j) {
            if (this.xOff > 14) {
                processBlock();
            }
            int[] iArr = this.f2917X;
            iArr[14] = (int) (j >>> 32);
            iArr[15] = (int) (j & (-1));
        }

        @Override
        protected void processWord(byte[] bArr, int i) {
            int i2 = bArr[i] << 24;
            int i3 = i + 1;
            int i4 = i2 | ((bArr[i3] & UByte.MAX_VALUE) << 16);
            int i5 = i3 + 1;
            int i6 = (bArr[i5 + 1] & UByte.MAX_VALUE) | i4 | ((bArr[i5] & UByte.MAX_VALUE) << 8);
            int[] iArr = this.f2917X;
            int i7 = this.xOff;
            iArr[i7] = i6;
            int i8 = i7 + 1;
            this.xOff = i8;
            if (i8 == 16) {
                processBlock();
            }
        }

        @Override
        public void reset() {
            super.reset();
            this.f2912H1 = 1732584193;
            this.f2913H2 = -271733879;
            this.f2914H3 = -1732584194;
            this.f2915H4 = 271733878;
            this.f2916H5 = -1009589776;
            this.xOff = 0;
            int i = 0;
            while (true) {
                int[] iArr = this.f2917X;
                if (i == iArr.length) {
                    return;
                }
                iArr[i] = 0;
                i++;
            }
        }
    }

    MSOutlookKeyIdCalculator() {
    }

    static byte[] calculateKeyId(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] bArr = new byte[sHA1Digest.getDigestSize()];
        try {
            byte[] encoded = subjectPublicKeyInfo.getEncoded(ASN1Encoding.DER);
            sHA1Digest.update(encoded, 0, encoded.length);
            sHA1Digest.doFinal(bArr, 0);
            return bArr;
        } catch (IOException unused) {
            return new byte[0];
        }
    }
}
