package com.p020hp.printsdk;

import com.p020hp.jipp.model.Orientation;
import com.p020hp.jipp.model.Sides;
import com.p020hp.jipp.model.Types;
import java.io.OutputStream;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1725l0 extends Lambda implements Function2<AbstractC1704h, OutputStream, Unit> {

    public final C1724l f1452a;

    public final C1705h0 f1453b;

    public final C1687e f1454c;

    public final C1680d f1455d;

    public final int f1456e;

    public C1725l0(C1724l c1724l, C1705h0 c1705h0, C1687e c1687e, C1680d c1680d, int i) {
        super(2);
        this.f1452a = c1724l;
        this.f1453b = c1705h0;
        this.f1454c = c1687e;
        this.f1455d = c1680d;
        this.f1456e = i;
    }

    @Override
    public Unit invoke(AbstractC1704h abstractC1704h, OutputStream outputStream) {
        AbstractC1704h convert = abstractC1704h;
        OutputStream output = outputStream;
        Intrinsics.checkNotNullParameter(convert, "$this$convert");
        Intrinsics.checkNotNullParameter(output, "output");
        EnumC1716j1 enumC1716j1 = this.f1452a.f1421d.f1425c;
        boolean zM537a = this.f1453b.m537a(this.f1454c);
        String str = (String) this.f1455d.f1149c.getValue(Types.sides);
        if (str == null) {
            str = Sides.oneSided;
        }
        C1741o1 c1741o1 = new C1741o1(enumC1716j1, str, null, null, zM537a, this.f1456e, 12);
        String str2 = (String) this.f1454c.f1179a.getValue(Types.pwgRasterDocumentSheetBack);
        if (str2 == null) {
            str2 = "normal";
        }
        Orientation orientation = (Orientation) this.f1454c.f1179a.getValue(Types.orientationRequested);
        if (orientation == null) {
            orientation = Orientation.portrait;
        }
        C1683d2 c1683d2 = new C1683d2(c1741o1, str2, orientation);
        C1705h0.f1224d.invoke("jpgToPwg() writing with " + c1683d2);
        new C1696f2(output, c1683d2, null, 4).m512a(convert);
        return Unit.INSTANCE;
    }
}
