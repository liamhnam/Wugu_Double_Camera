package com.p020hp.printsdk;

import android.os.Parcel;
import android.os.Parcelable;
import com.arthenica.ffmpegkit.StreamInformation;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1700g0 implements Parcelable {
    public static final Parcelable.Creator<C1700g0> CREATOR = new a();

    public final double f1208a;

    public final double f1209b;

    public static final class a implements Parcelable.Creator<C1700g0> {
        @Override
        public C1700g0 createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new C1700g0(parcel.readDouble(), parcel.readDouble());
        }

        @Override
        public C1700g0[] newArray(int i) {
            return new C1700g0[i];
        }
    }

    public static final class b extends Lambda implements Function0<Double> {
        public b() {
            super(0);
        }

        @Override
        public Double invoke() {
            C1700g0 c1700g0 = C1700g0.this;
            return Double.valueOf(c1700g0.f1208a / c1700g0.f1209b);
        }
    }

    public C1700g0(double d, double d2) {
        this.f1208a = d;
        this.f1209b = d2;
        LazyKt.lazy(new b());
        m523a(StreamInformation.KEY_WIDTH, d);
        m523a(StreamInformation.KEY_HEIGHT, d2);
    }

    public final double m522a() {
        return this.f1209b;
    }

    public final void m523a(String str, double d) {
        boolean z = false;
        if (d >= 0.0d) {
            if ((Double.isInfinite(d) || Double.isNaN(d)) ? false : true) {
                z = true;
            }
        }
        if (!z) {
            throw new IllegalArgumentException(("invalid " + str + ": " + d).toString());
        }
    }

    public final double m524b() {
        return this.f1208a;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1700g0)) {
            return false;
        }
        C1700g0 c1700g0 = (C1700g0) obj;
        return Intrinsics.areEqual((Object) Double.valueOf(this.f1208a), (Object) Double.valueOf(c1700g0.f1208a)) && Intrinsics.areEqual((Object) Double.valueOf(this.f1209b), (Object) Double.valueOf(c1700g0.f1209b));
    }

    public int hashCode() {
        return (Double.hashCode(this.f1208a) * 31) + Double.hashCode(this.f1209b);
    }

    public String toString() {
        return "Size(width=" + this.f1208a + ", height=" + this.f1209b + ')';
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeDouble(this.f1208a);
        out.writeDouble(this.f1209b);
    }
}
