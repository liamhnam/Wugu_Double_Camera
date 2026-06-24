package org.bouncycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowSigner implements MessageSigner {
    private static final int MAXITS = 65536;

    private ComputeInField f3741cf = new ComputeInField();
    RainbowKeyParameters key;
    private SecureRandom random;
    int signableDocumentLength;

    private short[] f3742x;

    private short[] initSign(Layer[] layerArr, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        short[] sArrMultiplyMatrix = this.f3741cf.multiplyMatrix(((RainbowPrivateKeyParameters) this.key).getInvA1(), this.f3741cf.addVect(((RainbowPrivateKeyParameters) this.key).getB1(), sArr));
        for (int i = 0; i < layerArr[0].getVi(); i++) {
            this.f3742x[i] = (short) this.random.nextInt();
            short[] sArr3 = this.f3742x;
            sArr3[i] = (short) (sArr3[i] & 255);
        }
        return sArrMultiplyMatrix;
    }

    private short[] makeMessageRepresentative(byte[] bArr) {
        int i = this.signableDocumentLength;
        short[] sArr = new short[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            short s = bArr[i3];
            sArr[i2] = s;
            sArr[i2] = (short) (s & 255);
            i3++;
            i2++;
            if (i2 >= i) {
                break;
            }
        }
        return sArr;
    }

    private short[] verifySignatureIntern(short[] sArr) {
        short[][] coeffQuadratic = ((RainbowPublicKeyParameters) this.key).getCoeffQuadratic();
        short[][] coeffSingular = ((RainbowPublicKeyParameters) this.key).getCoeffSingular();
        short[] coeffScalar = ((RainbowPublicKeyParameters) this.key).getCoeffScalar();
        short[] sArr2 = new short[coeffQuadratic.length];
        int length = coeffSingular[0].length;
        for (int i = 0; i < coeffQuadratic.length; i++) {
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                for (int i4 = i3; i4 < length; i4++) {
                    sArr2[i] = GF2Field.addElem(sArr2[i], GF2Field.multElem(coeffQuadratic[i][i2], GF2Field.multElem(sArr[i3], sArr[i4])));
                    i2++;
                }
                sArr2[i] = GF2Field.addElem(sArr2[i], GF2Field.multElem(coeffSingular[i][i3], sArr[i3]));
            }
            sArr2[i] = GF2Field.addElem(sArr2[i], coeffScalar[i]);
        }
        return sArr2;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] generateSignature(byte[] r15) {
        /*
            r14 = this;
            org.bouncycastle.pqc.crypto.rainbow.RainbowKeyParameters r0 = r14.key
            org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters r0 = (org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters) r0
            org.bouncycastle.pqc.crypto.rainbow.Layer[] r0 = r0.getLayers()
            int r1 = r0.length
            org.bouncycastle.pqc.crypto.rainbow.RainbowKeyParameters r2 = r14.key
            org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters r2 = (org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters) r2
            short[][] r2 = r2.getInvA2()
            int r2 = r2.length
            short[] r2 = new short[r2]
            r14.f3742x = r2
            int r2 = r1 + (-1)
            r2 = r0[r2]
            int r2 = r2.getViNext()
            byte[] r3 = new byte[r2]
            short[] r15 = r14.makeMessageRepresentative(r15)
            r4 = 0
            r5 = r4
        L26:
            short[] r6 = r14.initSign(r0, r15)     // Catch: java.lang.Exception -> Laa
            r7 = r4
            r8 = r7
        L2c:
            if (r7 >= r1) goto L7f
            r9 = r0[r7]     // Catch: java.lang.Exception -> Laa
            int r9 = r9.getOi()     // Catch: java.lang.Exception -> Laa
            short[] r9 = new short[r9]     // Catch: java.lang.Exception -> Laa
            r10 = r0[r7]     // Catch: java.lang.Exception -> Laa
            int r10 = r10.getOi()     // Catch: java.lang.Exception -> Laa
            short[] r10 = new short[r10]     // Catch: java.lang.Exception -> Laa
            r10 = r4
        L3f:
            r11 = r0[r7]     // Catch: java.lang.Exception -> Laa
            int r11 = r11.getOi()     // Catch: java.lang.Exception -> Laa
            if (r10 >= r11) goto L50
            short r11 = r6[r8]     // Catch: java.lang.Exception -> Laa
            r9[r10] = r11     // Catch: java.lang.Exception -> Laa
            int r8 = r8 + 1
            int r10 = r10 + 1
            goto L3f
        L50:
            org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField r10 = r14.f3741cf     // Catch: java.lang.Exception -> Laa
            r11 = r0[r7]     // Catch: java.lang.Exception -> Laa
            short[] r12 = r14.f3742x     // Catch: java.lang.Exception -> Laa
            short[][] r11 = r11.plugInVinegars(r12)     // Catch: java.lang.Exception -> Laa
            short[] r9 = r10.solveEquation(r11, r9)     // Catch: java.lang.Exception -> Laa
            if (r9 == 0) goto L77
            r10 = r4
        L61:
            int r11 = r9.length     // Catch: java.lang.Exception -> Laa
            if (r10 >= r11) goto L74
            short[] r11 = r14.f3742x     // Catch: java.lang.Exception -> Laa
            r12 = r0[r7]     // Catch: java.lang.Exception -> Laa
            int r12 = r12.getVi()     // Catch: java.lang.Exception -> Laa
            int r12 = r12 + r10
            short r13 = r9[r10]     // Catch: java.lang.Exception -> Laa
            r11[r12] = r13     // Catch: java.lang.Exception -> Laa
            int r10 = r10 + 1
            goto L61
        L74:
            int r7 = r7 + 1
            goto L2c
        L77:
            java.lang.Exception r6 = new java.lang.Exception     // Catch: java.lang.Exception -> Laa
            java.lang.String r7 = "LES is not solveable!"
            r6.<init>(r7)     // Catch: java.lang.Exception -> Laa
            throw r6     // Catch: java.lang.Exception -> Laa
        L7f:
            org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField r6 = r14.f3741cf     // Catch: java.lang.Exception -> Laa
            org.bouncycastle.pqc.crypto.rainbow.RainbowKeyParameters r7 = r14.key     // Catch: java.lang.Exception -> Laa
            org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters r7 = (org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters) r7     // Catch: java.lang.Exception -> Laa
            short[] r7 = r7.getB2()     // Catch: java.lang.Exception -> Laa
            short[] r8 = r14.f3742x     // Catch: java.lang.Exception -> Laa
            short[] r6 = r6.addVect(r7, r8)     // Catch: java.lang.Exception -> Laa
            org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField r7 = r14.f3741cf     // Catch: java.lang.Exception -> Laa
            org.bouncycastle.pqc.crypto.rainbow.RainbowKeyParameters r8 = r14.key     // Catch: java.lang.Exception -> Laa
            org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters r8 = (org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters) r8     // Catch: java.lang.Exception -> Laa
            short[][] r8 = r8.getInvA2()     // Catch: java.lang.Exception -> Laa
            short[] r6 = r7.multiplyMatrix(r8, r6)     // Catch: java.lang.Exception -> Laa
            r7 = r4
        L9e:
            if (r7 >= r2) goto La8
            short r8 = r6[r7]     // Catch: java.lang.Exception -> Laa
            byte r8 = (byte) r8     // Catch: java.lang.Exception -> Laa
            r3[r7] = r8     // Catch: java.lang.Exception -> Laa
            int r7 = r7 + 1
            goto L9e
        La8:
            r6 = 1
            goto Lab
        Laa:
            r6 = r4
        Lab:
            r7 = 65536(0x10000, float:9.1835E-41)
            if (r6 != 0) goto Lb3
            int r5 = r5 + 1
            if (r5 < r7) goto L26
        Lb3:
            if (r5 == r7) goto Lb6
            return r3
        Lb6:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "unable to generate signature - LES not solvable"
            r15.<init>(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.rainbow.RainbowSigner.generateSignature(byte[]):byte[]");
    }

    @Override
    public void init(boolean z, CipherParameters cipherParameters) {
        RainbowKeyParameters rainbowKeyParameters;
        if (!z) {
            rainbowKeyParameters = (RainbowPublicKeyParameters) cipherParameters;
        } else {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (RainbowPrivateKeyParameters) parametersWithRandom.getParameters();
                this.signableDocumentLength = this.key.getDocLength();
            }
            this.random = CryptoServicesRegistrar.getSecureRandom();
            rainbowKeyParameters = (RainbowPrivateKeyParameters) cipherParameters;
        }
        this.key = rainbowKeyParameters;
        this.signableDocumentLength = this.key.getDocLength();
    }

    @Override
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        short[] sArr = new short[bArr2.length];
        for (int i = 0; i < bArr2.length; i++) {
            sArr[i] = (short) (bArr2[i] & 255);
        }
        short[] sArrMakeMessageRepresentative = makeMessageRepresentative(bArr);
        short[] sArrVerifySignatureIntern = verifySignatureIntern(sArr);
        if (sArrMakeMessageRepresentative.length != sArrVerifySignatureIntern.length) {
            return false;
        }
        boolean z = true;
        for (int i2 = 0; i2 < sArrMakeMessageRepresentative.length; i2++) {
            z = z && sArrMakeMessageRepresentative[i2] == sArrVerifySignatureIntern[i2];
        }
        return z;
    }
}
