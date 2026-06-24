package com.p020hp.printsdk;

import com.p020hp.printsdk.AbstractC1760s1;
import kotlin.jvm.internal.Intrinsics;

public final class C1775v1 extends AbstractC1760s1 {

    public final AbstractC1760s1 f1910d;

    public C1775v1(AbstractC1760s1 abstractC1760s1, int i, int i2) {
        super(i, i2);
        this.f1910d = abstractC1760s1;
    }

    @Override
    public void mo579a(int i, int i2, EnumC1716j1 colorSpace, byte[] byteArray) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        this.f1910d.mo579a((this.f1758b - i) - i2, i2, colorSpace, byteArray);
        AbstractC1760s1.a aVar = AbstractC1760s1.f1756c;
        int i3 = this.f1757a;
        int i4 = colorSpace.f1362a;
        int i5 = i3 * i4;
        int length = (byteArray.length / i5) / 2;
        for (int i6 = 0; i6 < length; i6++) {
            aVar.m655a(byteArray, i6 * i3 * i4, ((r0 - i6) - 1) * i3 * i4, i5);
        }
    }
}
