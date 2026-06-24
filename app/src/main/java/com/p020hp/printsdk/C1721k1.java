package com.p020hp.printsdk;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1721k1 extends Lambda implements Function3<byte[], Integer, OutputStream, Unit> {

    public final EnumC1716j1 f1398a;

    public C1721k1(EnumC1716j1 enumC1716j1) {
        super(3);
        this.f1398a = enumC1716j1;
    }

    @Override
    public Unit invoke(byte[] bArr, Integer num, OutputStream outputStream) throws IOException {
        byte[] input = bArr;
        int iIntValue = num.intValue();
        OutputStream output = outputStream;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(output, "output");
        output.write(EnumC1716j1.m557a(this.f1398a, input, iIntValue));
        return Unit.INSTANCE;
    }
}
