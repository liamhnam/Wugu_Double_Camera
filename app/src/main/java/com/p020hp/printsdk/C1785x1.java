package com.p020hp.printsdk;

import kotlin.jvm.internal.Intrinsics;

public final class C1785x1 {

    public final C1741o1 f1959a;

    public final int f1960b;

    public final String f1961c;

    public C1785x1(C1741o1 output, int i, String backSide) {
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(backSide, "backSide");
        this.f1959a = output;
        this.f1960b = i;
        this.f1961c = backSide;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1785x1)) {
            return false;
        }
        C1785x1 c1785x1 = (C1785x1) obj;
        return Intrinsics.areEqual(this.f1959a, c1785x1.f1959a) && this.f1960b == c1785x1.f1960b && Intrinsics.areEqual(this.f1961c, c1785x1.f1961c);
    }

    public int hashCode() {
        return (((this.f1959a.hashCode() * 31) + Integer.hashCode(this.f1960b)) * 31) + this.f1961c.hashCode();
    }

    public String toString() {
        return "PclmSettings(output=" + this.f1959a + ", stripHeight=" + this.f1960b + ", backSide=" + this.f1961c + ')';
    }
}
