package com.p020hp.printsdk;

import com.p020hp.jipp.model.Sides;
import com.p020hp.jipp.model.Types;
import java.io.OutputStream;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1720k0 extends Lambda implements Function2<AbstractC1704h, OutputStream, Unit> {

    public final C1705h0 f1392a;

    public final C1687e f1393b;

    public final C1680d f1394c;

    public final boolean f1395d;

    public final int f1396e;

    public final int f1397f;

    public C1720k0(C1705h0 c1705h0, C1687e c1687e, C1680d c1680d, boolean z, int i, int i2) {
        super(2);
        this.f1392a = c1705h0;
        this.f1393b = c1687e;
        this.f1394c = c1680d;
        this.f1395d = z;
        this.f1396e = i;
        this.f1397f = i2;
    }

    @Override
    public Unit invoke(AbstractC1704h abstractC1704h, OutputStream outputStream) {
        AbstractC1704h convert = abstractC1704h;
        OutputStream output = outputStream;
        Intrinsics.checkNotNullParameter(convert, "$this$convert");
        Intrinsics.checkNotNullParameter(output, "output");
        boolean zM537a = this.f1392a.m537a(this.f1393b);
        String str = (String) this.f1394c.f1149c.getValue(Types.sides);
        if (str == null) {
            str = Sides.oneSided;
        }
        C1741o1 c1741o1 = new C1741o1(this.f1395d ? EnumC1716j1.Rgb : EnumC1716j1.Grayscale, str, null, null, zM537a, this.f1396e, 12);
        int i = this.f1397f;
        String str2 = (String) this.f1393b.f1179a.getValue(Types.pclmRasterBackSide);
        if (str2 == null) {
            str2 = "normal";
        }
        C1785x1 c1785x1 = new C1785x1(c1741o1, i, str2);
        C1705h0.f1224d.invoke("pdfToPclm() writing with " + c1785x1);
        new C1790y1(output, c1785x1).m689a(convert);
        return Unit.INSTANCE;
    }
}
