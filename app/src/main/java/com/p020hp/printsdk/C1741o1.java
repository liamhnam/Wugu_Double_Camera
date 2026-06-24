package com.p020hp.printsdk;

import com.p020hp.jipp.model.PrintQuality;
import com.p020hp.jipp.model.Sides;
import kotlin.jvm.internal.Intrinsics;

public final class C1741o1 {

    public final EnumC1716j1 f1553a;

    public final String f1554b;

    public final String f1555c;

    public final PrintQuality f1556d;

    public final boolean f1557e;

    public final int f1558f;

    public C1741o1() {
        this(null, null, null, null, false, 0, 63);
    }

    public C1741o1(EnumC1716j1 colorSpace, String sides, String source, PrintQuality printQuality, boolean z, int i) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Intrinsics.checkNotNullParameter(sides, "sides");
        Intrinsics.checkNotNullParameter(source, "source");
        this.f1553a = colorSpace;
        this.f1554b = sides;
        this.f1555c = source;
        this.f1556d = printQuality;
        this.f1557e = z;
        this.f1558f = i;
    }

    public C1741o1(EnumC1716j1 enumC1716j1, String str, String str2, PrintQuality printQuality, boolean z, int i, int i2) {
        this((i2 & 1) != 0 ? EnumC1716j1.Rgb : enumC1716j1, (i2 & 2) != 0 ? Sides.oneSided : str, (i2 & 4) != 0 ? "auto" : null, null, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? 1 : i);
    }

    public final EnumC1716j1 m604a() {
        return this.f1553a;
    }

    public final PrintQuality m605b() {
        return this.f1556d;
    }

    public final String m606c() {
        return this.f1555c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1741o1)) {
            return false;
        }
        C1741o1 c1741o1 = (C1741o1) obj;
        return this.f1553a == c1741o1.f1553a && Intrinsics.areEqual(this.f1554b, c1741o1.f1554b) && Intrinsics.areEqual(this.f1555c, c1741o1.f1555c) && Intrinsics.areEqual(this.f1556d, c1741o1.f1556d) && this.f1557e == c1741o1.f1557e && this.f1558f == c1741o1.f1558f;
    }

    public int hashCode() {
        int iHashCode = ((((this.f1553a.hashCode() * 31) + this.f1554b.hashCode()) * 31) + this.f1555c.hashCode()) * 31;
        PrintQuality printQuality = this.f1556d;
        int iHashCode2 = (iHashCode + (printQuality == null ? 0 : printQuality.hashCode())) * 31;
        boolean z = this.f1557e;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return ((iHashCode2 + r1) * 31) + Integer.hashCode(this.f1558f);
    }

    public String toString() {
        return "OutputSettings(colorSpace=" + this.f1553a + ", sides=" + this.f1554b + ", source=" + this.f1555c + ", quality=" + this.f1556d + ", reversed=" + this.f1557e + ", copies=" + this.f1558f + ')';
    }
}
