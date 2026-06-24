package com.p020hp.printsdk;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

public final class C1765t1 extends AbstractC1760s1 {
    public C1765t1(int i, int i2) {
        super(i, i2);
    }

    @Override
    public void mo579a(int i, int i2, EnumC1716j1 colorSpace, byte[] byteArray) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        ArraysKt.fill$default(byteArray, (byte) -1, 0, 0, 6, (Object) null);
    }
}
