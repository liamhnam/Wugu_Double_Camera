package com.p020hp.printsdk;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1726l1 extends Lambda implements Function3<byte[], Integer, OutputStream, Unit> {

    public static final C1726l1 f1457a = new C1726l1();

    public C1726l1() {
        super(3);
    }

    @Override
    public Unit invoke(byte[] bArr, Integer num, OutputStream outputStream) throws IOException {
        byte[] input = bArr;
        int iIntValue = num.intValue();
        OutputStream output = outputStream;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(output, "output");
        output.write(input[iIntValue]);
        output.write(input[iIntValue + 1]);
        output.write(input[iIntValue + 2]);
        output.write(255);
        return Unit.INSTANCE;
    }
}
