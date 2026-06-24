package com.p020hp.printsdk;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1706h1 extends Lambda implements Function3<byte[], Integer, OutputStream, Unit> {

    public static final C1706h1 f1280a = new C1706h1();

    public C1706h1() {
        super(3);
    }

    @Override
    public Unit invoke(byte[] bArr, Integer num, OutputStream outputStream) throws IOException {
        byte[] input = bArr;
        int iIntValue = num.intValue();
        OutputStream output = outputStream;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(output, "output");
        byte b = input[iIntValue];
        output.write(b);
        output.write(b);
        output.write(b);
        return Unit.INSTANCE;
    }
}
