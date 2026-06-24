package com.p020hp.printsdk;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;

public final class C1662a {

    public final InputStream f870a;

    public final String f871b;

    public final String f872c;

    public final String f873d;

    public C1662a(InputStream input, String mimeType, String name, String path) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        this.f870a = input;
        this.f871b = mimeType;
        this.f872c = name;
        this.f873d = path;
    }

    public static C1662a m458a(C1662a c1662a, InputStream input, String mimeType, String str, String str2, int i) {
        if ((i & 1) != 0) {
            input = c1662a.f870a;
        }
        if ((i & 2) != 0) {
            mimeType = c1662a.f871b;
        }
        String name = (i & 4) != 0 ? c1662a.f872c : null;
        String path = (i & 8) != 0 ? c1662a.f873d : null;
        c1662a.getClass();
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        return new C1662a(input, mimeType, name, path);
    }

    public final String m459a() {
        return this.f872c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1662a)) {
            return false;
        }
        C1662a c1662a = (C1662a) obj;
        return Intrinsics.areEqual(this.f870a, c1662a.f870a) && Intrinsics.areEqual(this.f871b, c1662a.f871b) && Intrinsics.areEqual(this.f872c, c1662a.f872c) && Intrinsics.areEqual(this.f873d, c1662a.f873d);
    }

    public int hashCode() {
        return (((((this.f870a.hashCode() * 31) + this.f871b.hashCode()) * 31) + this.f872c.hashCode()) * 31) + this.f873d.hashCode();
    }

    public String toString() {
        return "Document(input=" + this.f870a + ", mimeType=" + this.f871b + ", name=" + this.f872c + ", path=" + this.f873d + ')';
    }
}
