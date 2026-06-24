package com.p020hp.printsdk;

import kotlin.collections.ArraysKt;

public abstract class AbstractC1760s1 {

    public static final a f1756c = new a();

    public final int f1757a;

    public final int f1758b;

    public static final class a {
        public final void m655a(byte[] bArr, int i, int i2, int i3) {
            if (i3 == 1) {
                byte b = bArr[i];
                bArr[i] = bArr[i2];
                bArr[i2] = b;
            } else {
                byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArr, i, i + i3);
                System.arraycopy(bArr, i2, bArr, i, i3);
                System.arraycopy(bArrCopyOfRange, 0, bArr, i2, i3);
            }
        }
    }

    public AbstractC1760s1(int i, int i2) {
        this.f1757a = i;
        this.f1758b = i2;
    }

    public final int m653a() {
        return this.f1758b;
    }

    public abstract void mo579a(int i, int i2, EnumC1716j1 enumC1716j1, byte[] bArr);

    public final int m654b() {
        return this.f1757a;
    }
}
