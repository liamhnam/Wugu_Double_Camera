package com.p020hp.printsdk;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

public final class C1694f0 implements Parcelable {

    public final EnumC1716j1 f1197a;

    public final int f1198b;

    public final boolean f1199c;

    public static final b f1196d = new b();
    public static final Parcelable.Creator<C1694f0> CREATOR = new a();

    public static final class a implements Parcelable.Creator<C1694f0> {
        @Override
        public C1694f0 createFromParcel(Parcel parcel) {
            EnumC1716j1 enumC1716j1;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            int i = parcel.readInt();
            if (i == 0) {
                enumC1716j1 = EnumC1716j1.Rgba;
            } else if (i == 1) {
                enumC1716j1 = EnumC1716j1.Rgb;
            } else {
                if (i != 2) {
                    throw new IllegalStateException(("Invalid ColorSpace value " + i).toString());
                }
                enumC1716j1 = EnumC1716j1.Grayscale;
            }
            int i2 = parcel.readInt();
            String string = parcel.readString();
            if (string == null) {
                string = "false";
            }
            return new C1694f0(enumC1716j1, i2, Boolean.parseBoolean(string));
        }

        @Override
        public C1694f0[] newArray(int i) {
            return new C1694f0[i];
        }
    }

    public static final class b {
    }

    public C1694f0(EnumC1716j1 colorSpace, int i, boolean z) {
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        this.f1197a = colorSpace;
        this.f1198b = i;
        this.f1199c = z;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1694f0)) {
            return false;
        }
        C1694f0 c1694f0 = (C1694f0) obj;
        return this.f1197a == c1694f0.f1197a && this.f1198b == c1694f0.f1198b && this.f1199c == c1694f0.f1199c;
    }

    public int hashCode() {
        int iHashCode = ((this.f1197a.hashCode() * 31) + Integer.hashCode(this.f1198b)) * 31;
        boolean z = this.f1199c;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "PdfRenderSettings(colorSpace=" + this.f1197a + ", renderMode=" + this.f1198b + " ,especialElementEnhancement=" + this.f1199c + ')';
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(out, "out");
        int iOrdinal = this.f1197a.ordinal();
        if (iOrdinal != 0) {
            i2 = 1;
            if (iOrdinal != 1) {
                i2 = 2;
                if (iOrdinal != 2) {
                    throw new NoWhenBranchMatchedException();
                }
            }
        } else {
            i2 = 0;
        }
        out.writeInt(i2);
        out.writeInt(this.f1198b);
        out.writeString(String.valueOf(this.f1199c));
    }
}
