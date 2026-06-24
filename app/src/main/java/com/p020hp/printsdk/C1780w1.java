package com.p020hp.printsdk;

import com.p020hp.printsdk.AbstractC1760s1;
import kotlin.jvm.internal.Intrinsics;

public final class C1780w1 extends AbstractC1760s1 {

    public final AbstractC1760s1 f1949d;

    public C1780w1(AbstractC1760s1 abstractC1760s1, int i, int i2) {
        super(i, i2);
        this.f1949d = abstractC1760s1;
    }

    @Override
    public void mo579a(int i, int i2, EnumC1716j1 colorSpace, byte[] byteArray) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        this.f1949d.mo579a((this.f1758b - i) - i2, i2, colorSpace, byteArray);
        AbstractC1760s1.a aVar = AbstractC1760s1.f1756c;
        int i3 = this.f1757a;
        int i4 = colorSpace.f1362a;
        int length = (byteArray.length / (i3 * i4)) / 2;
        for (int i5 = 0; i5 < length; i5++) {
            for (int i6 = 0; i6 < i3; i6++) {
                aVar.m655a(byteArray, ((i5 * i3) + i6) * i4, ((((r0 - i5) * i3) - i6) - 1) * i4, i4);
            }
        }
        if (length % 2 != 0) {
            int i7 = i3 / 2;
            for (int i8 = 0; i8 < i7; i8++) {
                aVar.m655a(byteArray, ((length * i3) + i8) * i4, (((r3 + i3) - i8) - 1) * i4, i4);
            }
        }
    }
}
