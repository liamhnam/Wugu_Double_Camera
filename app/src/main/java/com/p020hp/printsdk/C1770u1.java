package com.p020hp.printsdk;

import com.p020hp.printsdk.AbstractC1760s1;
import kotlin.jvm.internal.Intrinsics;

public final class C1770u1 extends AbstractC1760s1 {

    public final AbstractC1760s1 f1846d;

    public C1770u1(AbstractC1760s1 abstractC1760s1, int i, int i2) {
        super(i, i2);
        this.f1846d = abstractC1760s1;
    }

    @Override
    public void mo579a(int i, int i2, EnumC1716j1 colorSpace, byte[] byteArray) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        this.f1846d.mo579a(i, i2, colorSpace, byteArray);
        AbstractC1760s1.a aVar = AbstractC1760s1.f1756c;
        int i3 = this.f1757a;
        int i4 = colorSpace.f1362a;
        int length = byteArray.length / (i3 * i4);
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = i3 / 2;
            for (int i7 = 0; i7 < i6; i7++) {
                aVar.m655a(byteArray, ((i5 * i3) + i7) * i4, (((r5 + i3) - i7) - 1) * i4, i4);
            }
        }
    }
}
