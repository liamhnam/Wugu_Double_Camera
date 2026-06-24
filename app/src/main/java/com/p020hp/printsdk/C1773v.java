package com.p020hp.printsdk;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

public final class C1773v implements Parcelable {
    public static final Parcelable.Creator<C1773v> CREATOR = new a();

    public final int f1875a;

    public final int f1876b;

    public final int f1877c;

    public final int f1878d;

    public final int f1879e;

    public final double f1880f;

    public final boolean f1881g;

    public static final class a implements Parcelable.Creator<C1773v> {
        @Override
        public C1773v createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new C1773v(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readDouble(), parcel.readByte() != 0);
        }

        @Override
        public C1773v[] newArray(int i) {
            return new C1773v[i];
        }
    }

    public C1773v(int i, int i2, int i3, int i4, int i5, double d, boolean z) {
        this.f1875a = i;
        this.f1876b = i2;
        this.f1877c = i3;
        this.f1878d = i4;
        this.f1879e = i5;
        this.f1880f = d;
        this.f1881g = z;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("bad page: " + i).toString());
        }
        if (!(i4 > 0)) {
            throw new IllegalArgumentException(("bad width: " + i3).toString());
        }
        if (!(i5 > 0)) {
            throw new IllegalArgumentException(("bad height: " + i3).toString());
        }
        if (!(d >= 0.0d)) {
            throw new IllegalArgumentException(("bad zoom: " + i3).toString());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1773v)) {
            return false;
        }
        C1773v c1773v = (C1773v) obj;
        return this.f1875a == c1773v.f1875a && this.f1876b == c1773v.f1876b && this.f1877c == c1773v.f1877c && this.f1878d == c1773v.f1878d && this.f1879e == c1773v.f1879e && Intrinsics.areEqual((Object) Double.valueOf(this.f1880f), (Object) Double.valueOf(c1773v.f1880f)) && this.f1881g == c1773v.f1881g;
    }

    public int hashCode() {
        int iHashCode = ((((((((((Integer.hashCode(this.f1875a) * 31) + Integer.hashCode(this.f1876b)) * 31) + Integer.hashCode(this.f1877c)) * 31) + Integer.hashCode(this.f1878d)) * 31) + Integer.hashCode(this.f1879e)) * 31) + Double.hashCode(this.f1880f)) * 31;
        boolean z = this.f1881g;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("Area(p=").append(this.f1875a).append(", x=").append(this.f1876b).append(", y=").append(this.f1877c).append(", w=").append(this.f1878d).append(", h=").append(this.f1879e).append(", z=");
        String str = String.format("%.3f", Arrays.copyOf(new Object[]{Double.valueOf(this.f1880f)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
        return sbAppend.append(str).append(", r=").append(this.f1881g).append(')').toString();
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeInt(this.f1875a);
        out.writeInt(this.f1876b);
        out.writeInt(this.f1877c);
        out.writeInt(this.f1878d);
        out.writeInt(this.f1879e);
        out.writeDouble(this.f1880f);
        out.writeByte(this.f1881g ? (byte) 1 : (byte) 0);
    }
}
