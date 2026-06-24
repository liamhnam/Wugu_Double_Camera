package com.p020hp.printsdk;

import java.io.IOException;
import java.io.OutputStream;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public enum EnumC1716j1 {
    Rgba(4),
    Rgb(3),
    Grayscale(1);


    public final int f1362a;

    public final Function3<byte[], Integer, OutputStream, Unit> f1363b = new a();

    public static final class a extends Lambda implements Function3<byte[], Integer, OutputStream, Unit> {
        public a() {
            super(3);
        }

        @Override
        public Unit invoke(byte[] bArr, Integer num, OutputStream outputStream) throws IOException {
            byte[] input = bArr;
            int iIntValue = num.intValue();
            OutputStream output = outputStream;
            Intrinsics.checkNotNullParameter(input, "input");
            Intrinsics.checkNotNullParameter(output, "output");
            output.write(input, iIntValue, EnumC1716j1.this.f1362a);
            return Unit.INSTANCE;
        }
    }

    EnumC1716j1(int i) {
        this.f1362a = i;
    }

    public static final int m556a(EnumC1716j1 enumC1716j1, int i, int i2) {
        enumC1716j1.getClass();
        return (((i * i2) / 255) - i2) + 255;
    }

    public static final int m555a(EnumC1716j1 enumC1716j1, byte b) {
        enumC1716j1.getClass();
        return b & UByte.MAX_VALUE;
    }

    public static final int m557a(EnumC1716j1 enumC1716j1, byte[] bArr, int i) {
        enumC1716j1.getClass();
        return ((((bArr[i] & UByte.MAX_VALUE) * 13933) + ((bArr[i + 1] & UByte.MAX_VALUE) * 46871)) + ((bArr[i + 2] & UByte.MAX_VALUE) * 4732)) >>> 16;
    }

    public final Function3<byte[], Integer, OutputStream, Unit> m558a(EnumC1716j1 outputColor) {
        Intrinsics.checkNotNullParameter(outputColor, "outputColor");
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            int iOrdinal2 = outputColor.ordinal();
            if (iOrdinal2 == 0) {
                return this.f1363b;
            }
            if (iOrdinal2 == 1) {
                return new C1736n1(this);
            }
            if (iOrdinal2 == 2) {
                return new C1731m1(this);
            }
            throw new NoWhenBranchMatchedException();
        }
        if (iOrdinal == 1) {
            int iOrdinal3 = outputColor.ordinal();
            if (iOrdinal3 == 0) {
                return C1726l1.f1457a;
            }
            if (iOrdinal3 == 1) {
                return this.f1363b;
            }
            if (iOrdinal3 == 2) {
                return new C1721k1(this);
            }
            throw new NoWhenBranchMatchedException();
        }
        if (iOrdinal == 2) {
            int iOrdinal4 = outputColor.ordinal();
            if (iOrdinal4 == 0) {
                return C1711i1.f1337a;
            }
            if (iOrdinal4 == 1) {
                return C1706h1.f1280a;
            }
            if (iOrdinal4 == 2) {
                return this.f1363b;
            }
            throw new NoWhenBranchMatchedException();
        }
        throw new NoWhenBranchMatchedException();
    }
}
