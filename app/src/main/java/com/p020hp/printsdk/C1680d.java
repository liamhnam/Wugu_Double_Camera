package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.encoding.Tag;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class C1680d {

    public final UUID f1147a;

    public final C1662a f1148b;

    public final AttributeGroup f1149c;

    public final List<String> f1150d;

    public C1680d(UUID printerId, C1662a document, AttributeGroup attributes, List<String> requiredAttributes) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Intrinsics.checkNotNullParameter(requiredAttributes, "requiredAttributes");
        this.f1147a = printerId;
        this.f1148b = document;
        this.f1149c = attributes;
        this.f1150d = requiredAttributes;
    }

    public C1680d(UUID uuid, C1662a c1662a, AttributeGroup attributeGroup, List list, int i) {
        this(uuid, c1662a, (i & 4) != 0 ? AttributeGroup.INSTANCE.groupOf(Tag.jobAttributes, new Attribute[0]) : attributeGroup, (i & 8) != 0 ? CollectionsKt.emptyList() : null);
    }

    public static C1680d m484a(C1680d c1680d, UUID uuid, C1662a document, AttributeGroup attributes, List list, int i) {
        UUID printerId = (i & 1) != 0 ? c1680d.f1147a : null;
        if ((i & 2) != 0) {
            document = c1680d.f1148b;
        }
        if ((i & 4) != 0) {
            attributes = c1680d.f1149c;
        }
        List<String> requiredAttributes = (i & 8) != 0 ? c1680d.f1150d : null;
        c1680d.getClass();
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Intrinsics.checkNotNullParameter(requiredAttributes, "requiredAttributes");
        return new C1680d(printerId, document, attributes, requiredAttributes);
    }

    public final C1662a m485a() {
        return this.f1148b;
    }

    public final UUID m486b() {
        return this.f1147a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1680d)) {
            return false;
        }
        C1680d c1680d = (C1680d) obj;
        return Intrinsics.areEqual(this.f1147a, c1680d.f1147a) && Intrinsics.areEqual(this.f1148b, c1680d.f1148b) && Intrinsics.areEqual(this.f1149c, c1680d.f1149c) && Intrinsics.areEqual(this.f1150d, c1680d.f1150d);
    }

    public int hashCode() {
        return (((((this.f1147a.hashCode() * 31) + this.f1148b.hashCode()) * 31) + this.f1149c.hashCode()) * 31) + this.f1150d.hashCode();
    }

    public String toString() {
        return "PrintJobRequest(printerId=" + this.f1147a + ", document=" + this.f1148b + ", attributes=" + this.f1149c + ", requiredAttributes=" + this.f1150d + ')';
    }
}
