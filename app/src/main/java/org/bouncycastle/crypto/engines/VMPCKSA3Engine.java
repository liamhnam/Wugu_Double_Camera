package org.bouncycastle.crypto.engines;

import kotlin.UByte;

public class VMPCKSA3Engine extends VMPCEngine {
    @Override
    public String getAlgorithmName() {
        return "VMPC-KSA3";
    }

    @Override
    protected void initKey(byte[] bArr, byte[] bArr2) {
        this.f3217s = (byte) 0;
        this.f3215P = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.f3215P[i] = (byte) i;
        }
        for (int i2 = 0; i2 < 768; i2++) {
            int i3 = i2 & 255;
            this.f3217s = this.f3215P[(this.f3217s + this.f3215P[i3] + bArr[i2 % bArr.length]) & 255];
            byte b = this.f3215P[i3];
            this.f3215P[i3] = this.f3215P[this.f3217s & UByte.MAX_VALUE];
            this.f3215P[this.f3217s & UByte.MAX_VALUE] = b;
        }
        for (int i4 = 0; i4 < 768; i4++) {
            int i5 = i4 & 255;
            this.f3217s = this.f3215P[(this.f3217s + this.f3215P[i5] + bArr2[i4 % bArr2.length]) & 255];
            byte b2 = this.f3215P[i5];
            this.f3215P[i5] = this.f3215P[this.f3217s & UByte.MAX_VALUE];
            this.f3215P[this.f3217s & UByte.MAX_VALUE] = b2;
        }
        for (int i6 = 0; i6 < 768; i6++) {
            int i7 = i6 & 255;
            this.f3217s = this.f3215P[(this.f3217s + this.f3215P[i7] + bArr[i6 % bArr.length]) & 255];
            byte b3 = this.f3215P[i7];
            this.f3215P[i7] = this.f3215P[this.f3217s & UByte.MAX_VALUE];
            this.f3215P[this.f3217s & UByte.MAX_VALUE] = b3;
        }
        this.f3216n = (byte) 0;
    }
}
