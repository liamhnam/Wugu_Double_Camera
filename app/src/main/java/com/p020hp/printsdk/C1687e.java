package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.model.Types;
import com.p020hp.mobile.common.browsing.ServiceInfo;
import java.net.URI;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

public class C1687e {

    public static final a f1178e = new a();

    public final AttributeGroup f1179a;

    public final Lazy f1180b;

    public final String f1181c;

    public final Lazy f1182d;

    public static final class a {
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final com.p020hp.printsdk.C1687e m506a(java.util.List<com.p020hp.mobile.common.browsing.ServiceInfo> r13) {
            /*
                Method dump skipped, instruction units count: 481
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1687e.a.m506a(java.util.List):com.hp.printsdk.e");
        }

        public final URI m507a(String str, ServiceInfo serviceInfo, String str2) {
            return C1740o0.m600a(str + "://" + StringsKt.trimStart(serviceInfo.getHost().getHostAddress().toString(), '/') + ':' + serviceInfo.getPort() + '/' + StringsKt.trimStart(str2, '/'));
        }

        public final URI m508b(String str, ServiceInfo serviceInfo, String str2) {
            return C1740o0.m600a(str + "://[" + StringsKt.trimStart(serviceInfo.getHost().getHostAddress().toString(), '/') + "]:" + serviceInfo.getPort() + '/' + StringsKt.trimStart(str2, '/'));
        }
    }

    public static final class b extends Lambda implements Function0<UUID> {
        public b() {
            super(0);
        }

        @Override
        public UUID invoke() {
            URI uri = (URI) C1687e.this.f1179a.getValue(Types.printerUuid);
            UUID uuidM601a = uri != null ? C1740o0.m601a(uri) : null;
            Intrinsics.checkNotNull(uuidM601a);
            return uuidM601a;
        }
    }

    public static final class c extends Lambda implements Function0<URI> {
        public c() {
            super(0);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.net.URI invoke() {
            /*
                r6 = this;
                com.hp.printsdk.e r0 = com.p020hp.printsdk.C1687e.this
                com.hp.jipp.encoding.AttributeGroup r0 = r0.f1179a
                com.hp.jipp.encoding.UriType$Set r1 = com.p020hp.jipp.model.Types.printerUriSupported
                java.util.List r0 = r0.getValues(r1)
                java.util.Iterator r1 = r0.iterator()
            Le:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L3c
                java.lang.Object r2 = r1.next()
                r3 = r2
                java.net.URI r3 = (java.net.URI) r3
                java.lang.String r4 = r3.getScheme()
                java.lang.String r5 = "ipps"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
                if (r4 == 0) goto L38
                java.lang.String r3 = r3.getHost()
                java.lang.String r4 = "it.host"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                boolean r3 = com.p020hp.mobile.common.browsing.ConnectionTypeKt.isUsbHost(r3)
                if (r3 != 0) goto L38
                r3 = 1
                goto L39
            L38:
                r3 = 0
            L39:
                if (r3 == 0) goto Le
                goto L3d
            L3c:
                r2 = 0
            L3d:
                java.net.URI r2 = (java.net.URI) r2
                if (r2 != 0) goto L67
                java.util.Iterator r0 = r0.iterator()
            L45:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L5f
                java.lang.Object r1 = r0.next()
                r2 = r1
                java.net.URI r2 = (java.net.URI) r2
                java.lang.String r1 = r2.getScheme()
                java.lang.String r3 = "ipp"
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r3)
                if (r1 == 0) goto L45
                goto L67
            L5f:
                java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
                java.lang.String r1 = "Collection contains no element matching the predicate."
                r0.<init>(r1)
                throw r0
            L67:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1687e.c.invoke():java.lang.Object");
        }
    }

    public C1687e(AttributeGroup attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.f1179a = attributes;
        this.f1180b = LazyKt.lazy(new b());
        String string = attributes.getString(Types.printerDnsSdName);
        if (string == null) {
            string = attributes.getString(Types.printerName);
            Intrinsics.checkNotNull(string);
        }
        this.f1181c = string;
        this.f1182d = LazyKt.lazy(new c());
    }

    public final AttributeGroup m503a() {
        return this.f1179a;
    }

    public UUID m504b() {
        return (UUID) this.f1180b.getValue();
    }

    public URI m505c() {
        return (URI) this.f1182d.getValue();
    }

    public boolean equals(Object obj) {
        boolean z = obj instanceof C1687e;
        C1687e c1687e = z ? (C1687e) obj : null;
        if (Intrinsics.areEqual(c1687e != null ? c1687e.m504b() : null, m504b())) {
            C1687e c1687e2 = z ? (C1687e) obj : null;
            if (Intrinsics.areEqual(c1687e2 != null ? c1687e2.f1179a : null, this.f1179a)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.f1179a.hashCode();
    }

    public String toString() {
        return "Printer(" + this.f1181c + ", " + C1740o0.m599a(m504b()) + ", " + m505c() + ", " + this.f1179a.size() + " attrs)";
    }
}
