package com.p020hp.printsdk;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1714j {

    public final C1700g0 f1344a;

    public final int f1345b;

    public final EnumC1744p f1346c;

    public final EnumC1716j1 f1347d;

    public final int f1348e;

    public final String f1349f;

    public final double f1350g;

    public final double f1351h;

    public final double f1352i;

    public final double f1353j;

    public final boolean f1354k;

    public final Lazy f1355l;

    public static final class a extends Lambda implements Function0<C1694f0> {
        public a() {
            super(0);
        }

        @Override
        public C1694f0 invoke() {
            C1714j c1714j = C1714j.this;
            return new C1694f0(c1714j.f1347d, c1714j.f1348e, c1714j.f1354k);
        }
    }

    public C1714j(C1700g0 outputSize, int i, EnumC1744p rotateMode, EnumC1716j1 colorSpace, int i2, String scaling, double d, double d2, double d3, double d4, boolean z) {
        Intrinsics.checkNotNullParameter(outputSize, "outputSize");
        Intrinsics.checkNotNullParameter(rotateMode, "rotateMode");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Intrinsics.checkNotNullParameter(scaling, "scaling");
        this.f1344a = outputSize;
        this.f1345b = i;
        this.f1346c = rotateMode;
        this.f1347d = colorSpace;
        this.f1348e = i2;
        this.f1349f = scaling;
        this.f1350g = d;
        this.f1351h = d2;
        this.f1352i = d3;
        this.f1353j = d4;
        this.f1354k = z;
        this.f1355l = LazyKt.lazy(new a());
    }

    public C1714j(C1700g0 c1700g0, int i, EnumC1744p enumC1744p, EnumC1716j1 enumC1716j1, int i2, String str, double d, double d2, double d3, double d4, boolean z, int i3) {
        this(c1700g0, (i3 & 2) != 0 ? 300 : i, (i3 & 4) != 0 ? EnumC1744p.Auto : enumC1744p, (i3 & 8) != 0 ? EnumC1716j1.Rgb : enumC1716j1, (i3 & 16) != 0 ? 2 : i2, (i3 & 32) != 0 ? "auto" : str, d, d2, d3, d4, (i3 & 1024) != 0 ? false : z);
    }

    public final int m547a() {
        return this.f1345b;
    }

    public final double m548b() {
        return this.f1350g;
    }

    public final double m549c() {
        return this.f1352i;
    }

    public final double m550d() {
        return this.f1353j;
    }

    public final double m551e() {
        return this.f1351h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1714j)) {
            return false;
        }
        C1714j c1714j = (C1714j) obj;
        return Intrinsics.areEqual(this.f1344a, c1714j.f1344a) && this.f1345b == c1714j.f1345b && this.f1346c == c1714j.f1346c && this.f1347d == c1714j.f1347d && this.f1348e == c1714j.f1348e && Intrinsics.areEqual(this.f1349f, c1714j.f1349f) && Intrinsics.areEqual((Object) Double.valueOf(this.f1350g), (Object) Double.valueOf(c1714j.f1350g)) && Intrinsics.areEqual((Object) Double.valueOf(this.f1351h), (Object) Double.valueOf(c1714j.f1351h)) && Intrinsics.areEqual((Object) Double.valueOf(this.f1352i), (Object) Double.valueOf(c1714j.f1352i)) && Intrinsics.areEqual((Object) Double.valueOf(this.f1353j), (Object) Double.valueOf(c1714j.f1353j)) && this.f1354k == c1714j.f1354k;
    }

    public final C1700g0 m552f() {
        return this.f1344a;
    }

    public final EnumC1744p m553g() {
        return this.f1346c;
    }

    public final String m554h() {
        return this.f1349f;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((this.f1344a.hashCode() * 31) + Integer.hashCode(this.f1345b)) * 31) + this.f1346c.hashCode()) * 31) + this.f1347d.hashCode()) * 31) + Integer.hashCode(this.f1348e)) * 31) + this.f1349f.hashCode()) * 31) + Double.hashCode(this.f1350g)) * 31) + Double.hashCode(this.f1351h)) * 31) + Double.hashCode(this.f1352i)) * 31) + Double.hashCode(this.f1353j)) * 31;
        boolean z = this.f1354k;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Options(outputSize=");
        sb.append(this.f1344a).append(", dpi=").append(this.f1345b).append(", rotateMode=").append(this.f1346c).append(", colorSpace=").append(this.f1347d).append(", renderMode=").append(this.f1348e).append(", scaling=").append(this.f1349f).append(", mediaBottomMargin=").append(this.f1350g).append(", mediaTopMargin=").append(this.f1351h).append(", mediaLeftMargin=").append(this.f1352i).append(", mediaRightMargin=").append(this.f1353j).append(", especialElementEnhancement=").append(this.f1354k).append(')');
        return sb.toString();
    }
}
