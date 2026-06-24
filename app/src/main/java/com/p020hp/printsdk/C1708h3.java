package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpPrinter;
import java.net.URI;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;

public final class C1708h3 extends HpPrinter {

    public final UUID f1307a;

    public final String f1308b;

    public final URI f1309c;

    public final URI f1310d;

    public C1708h3(UUID id, String name, URI uri, URI uri2) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.f1307a = id;
        this.f1308b = name;
        this.f1309c = uri;
        this.f1310d = uri2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1708h3)) {
            return false;
        }
        C1708h3 c1708h3 = (C1708h3) obj;
        return Intrinsics.areEqual(this.f1307a, c1708h3.f1307a) && Intrinsics.areEqual(this.f1308b, c1708h3.f1308b) && Intrinsics.areEqual(this.f1309c, c1708h3.f1309c) && Intrinsics.areEqual(this.f1310d, c1708h3.f1310d);
    }

    @Override
    public UUID getId() {
        return this.f1307a;
    }

    @Override
    public URI getImg() {
        return this.f1310d;
    }

    @Override
    public String getName() {
        return this.f1308b;
    }

    @Override
    public URI getUri() {
        return this.f1309c;
    }

    public int hashCode() {
        int iHashCode = ((((this.f1307a.hashCode() * 31) + this.f1308b.hashCode()) * 31) + this.f1309c.hashCode()) * 31;
        URI uri = this.f1310d;
        return iHashCode + (uri == null ? 0 : uri.hashCode());
    }
}
