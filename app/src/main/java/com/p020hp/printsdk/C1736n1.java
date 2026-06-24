package com.p020hp.printsdk;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1736n1 extends Lambda implements Function3<byte[], Integer, OutputStream, Unit> {

    public final EnumC1716j1 f1517a;

    public C1736n1(EnumC1716j1 enumC1716j1) {
        super(3);
        this.f1517a = enumC1716j1;
    }

    @Override
    public Unit invoke(byte[] bArr, Integer num, OutputStream outputStream) throws IOException {
        byte[] input = bArr;
        int iIntValue = num.intValue();
        OutputStream output = outputStream;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(output, "output");
        int iM555a = EnumC1716j1.m555a(this.f1517a, input[iIntValue + 3]);
        if (iM555a == 0) {
            output.write(255);
            output.write(255);
            output.write(255);
        } else if (iM555a != 255) {
            EnumC1716j1 enumC1716j1 = this.f1517a;
            output.write(EnumC1716j1.m556a(enumC1716j1, EnumC1716j1.m555a(enumC1716j1, input[iIntValue]), iM555a));
            EnumC1716j1 enumC1716j12 = this.f1517a;
            output.write(EnumC1716j1.m556a(enumC1716j12, EnumC1716j1.m555a(enumC1716j12, input[iIntValue + 1]), iM555a));
            EnumC1716j1 enumC1716j13 = this.f1517a;
            output.write(EnumC1716j1.m556a(enumC1716j13, EnumC1716j1.m555a(enumC1716j13, input[iIntValue + 2]), iM555a));
        } else {
            output.write(input, iIntValue, 3);
        }
        return Unit.INSTANCE;
    }
}
