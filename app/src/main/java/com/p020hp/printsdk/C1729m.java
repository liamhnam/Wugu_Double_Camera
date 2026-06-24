package com.p020hp.printsdk;

import android.os.ParcelFileDescriptor;
import com.p020hp.jipp.model.PrintScaling;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.p037io.CloseableKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

public final class C1729m extends AbstractC1704h {

    public static final a f1479l;

    public static final Logger f1480m;

    public final C1663a0 f1481c;

    public final C1714j f1482d;

    public final Function0<Boolean> f1483e;

    public final int f1484f;

    public InputStream f1485g;

    public File f1486h;

    public final Lazy f1487i = LazyKt.lazy(new c());

    public final Lazy f1488j = LazyKt.lazy(new d());

    public final Lazy f1489k = LazyKt.lazy(new e());

    public static final class a {
        public static final int m583a(a aVar, double d, int i) {
            return (int) ((d * ((double) i)) / ((double) 72));
        }

        public static final C1700g0 m584a(a aVar, C1700g0 c1700g0) {
            return new C1700g0(c1700g0.f1209b, c1700g0.f1208a);
        }

        public static final boolean m585b(a aVar, C1700g0 c1700g0) {
            return c1700g0.f1209b > c1700g0.f1208a;
        }
    }

    public final class b extends AbstractC1760s1 {

        public final int f1490d;

        public final int f1491e;

        public final int f1492f;

        public final double f1493g;

        public final boolean f1494h;

        public final C1729m f1495i;

        public static final class a extends Lambda implements Function0<String> {

            public final C1729m f1496a;

            public final C1700g0 f1497b;

            public final C1700g0 f1498c;

            public final b f1499d;

            public final double f1500e;

            public a(C1729m c1729m, C1700g0 c1700g0, C1700g0 c1700g02, b bVar, double d) {
                super(0);
                this.f1496a = c1729m;
                this.f1497b = c1700g0;
                this.f1498c = c1700g02;
                this.f1499d = bVar;
                this.f1500e = d;
            }

            @Override
            public String invoke() {
                return "For " + this.f1496a.f1482d + ", created page of pageSize=" + this.f1497b + ", rotatedPageSize=" + this.f1498c + ", rotate=" + this.f1499d.f1494h + ", contentZoom=" + this.f1500e + ", zoom=" + this.f1499d.f1493g + ", offset=(" + this.f1499d.f1491e + ", " + this.f1499d.f1492f + "), pixels=(" + this.f1499d.f1757a + ", " + this.f1499d.f1758b + ')';
            }
        }

        public b(C1729m c1729m, int i, C1700g0 pageSize) {
            double dMin;
            super(a.m583a(C1729m.f1479l, c1729m.f1482d.m552f().m524b(), c1729m.mo568a()), a.m583a(C1729m.f1479l, c1729m.f1482d.m552f().m522a(), c1729m.mo568a()));
            Intrinsics.checkNotNullParameter(pageSize, "pageSize");
            this.f1495i = c1729m;
            this.f1490d = i;
            int iOrdinal = c1729m.f1482d.m553g().ordinal();
            boolean z = true;
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    if (iOrdinal != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                } else if (a.m585b(C1729m.f1479l, pageSize) == a.m585b(C1729m.f1479l, c1729m.f1482d.m552f())) {
                }
                z = false;
            }
            this.f1494h = z;
            C1700g0 c1700g0M584a = z ? a.m584a(C1729m.f1479l, pageSize) : pageSize;
            C1700g0 c1700g0 = new C1700g0((c1729m.f1482d.m552f().m524b() - c1729m.f1482d.m549c()) - c1729m.f1482d.m550d(), (c1729m.f1482d.m552f().m522a() - c1729m.f1482d.m548b()) - c1729m.f1482d.m551e());
            String strM554h = c1729m.f1482d.m554h();
            int iHashCode = strM554h.hashCode();
            if (iHashCode == 101393) {
                if (!strM554h.equals(PrintScaling.fit)) {
                }
                double d = dMin;
                this.f1493g = (((double) c1729m.mo568a()) / ((double) 72)) * d;
                double d2 = 2;
                this.f1491e = a.m583a(C1729m.f1479l, (c1729m.f1482d.m552f().m524b() - (c1700g0M584a.m524b() * d)) / d2, c1729m.mo568a());
                this.f1492f = a.m583a(C1729m.f1479l, (c1729m.f1482d.m552f().m522a() - (c1700g0M584a.m522a() * d)) / d2, c1729m.mo568a());
                C1729m.f1480m.m446d(new a(c1729m, pageSize, c1700g0M584a, this, d));
            }
            if (iHashCode != 3143043) {
                if (iHashCode != 3387192 || !strM554h.equals("none")) {
                }
                double d3 = dMin;
                this.f1493g = (((double) c1729m.mo568a()) / ((double) 72)) * d3;
                double d22 = 2;
                this.f1491e = a.m583a(C1729m.f1479l, (c1729m.f1482d.m552f().m524b() - (c1700g0M584a.m524b() * d3)) / d22, c1729m.mo568a());
                this.f1492f = a.m583a(C1729m.f1479l, (c1729m.f1482d.m552f().m522a() - (c1700g0M584a.m522a() * d3)) / d22, c1729m.mo568a());
                C1729m.f1480m.m446d(new a(c1729m, pageSize, c1700g0M584a, this, d3));
            }
            if (strM554h.equals(PrintScaling.fill)) {
                dMin = Math.max(c1700g0.m524b() / c1700g0M584a.m524b(), c1700g0.m522a() / c1700g0M584a.m522a());
                double d32 = dMin;
                this.f1493g = (((double) c1729m.mo568a()) / ((double) 72)) * d32;
                double d222 = 2;
                this.f1491e = a.m583a(C1729m.f1479l, (c1729m.f1482d.m552f().m524b() - (c1700g0M584a.m524b() * d32)) / d222, c1729m.mo568a());
                this.f1492f = a.m583a(C1729m.f1479l, (c1729m.f1482d.m552f().m522a() - (c1700g0M584a.m522a() * d32)) / d222, c1729m.mo568a());
                C1729m.f1480m.m446d(new a(c1729m, pageSize, c1700g0M584a, this, d32));
            }
            dMin = (c1700g0M584a.m524b() > c1700g0.m524b() || c1700g0M584a.m522a() > c1700g0.m522a()) ? Math.min(c1700g0.m524b() / c1700g0M584a.m524b(), c1700g0.m522a() / c1700g0M584a.m522a()) : 1.0d;
            double d322 = dMin;
            this.f1493g = (((double) c1729m.mo568a()) / ((double) 72)) * d322;
            double d2222 = 2;
            this.f1491e = a.m583a(C1729m.f1479l, (c1729m.f1482d.m552f().m524b() - (c1700g0M584a.m524b() * d322)) / d2222, c1729m.mo568a());
            this.f1492f = a.m583a(C1729m.f1479l, (c1729m.f1482d.m552f().m522a() - (c1700g0M584a.m522a() * d322)) / d2222, c1729m.mo568a());
            C1729m.f1480m.m446d(new a(c1729m, pageSize, c1700g0M584a, this, d322));
        }

        @Override
        public void mo579a(int i, int i2, EnumC1716j1 colorSpace, byte[] byteArray) {
            Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
            Intrinsics.checkNotNullParameter(byteArray, "byteArray");
            if (!this.f1495i.f1483e.invoke().booleanValue()) {
                throw new CancellationException();
            }
            boolean z = this.f1494h;
            C1773v area = z ? new C1773v(this.f1490d, i - this.f1492f, -this.f1491e, i2, this.f1757a, this.f1493g, z) : new C1773v(this.f1490d, -this.f1491e, i - this.f1492f, this.f1757a, i2, this.f1493g, z);
            C1729m c1729m = this.f1495i;
            C1663a0 c1663a0 = c1729m.f1481c;
            InterfaceC1783x document = (InterfaceC1783x) c1729m.f1487i.getValue();
            ByteBuffer target = ByteBuffer.wrap(byteArray);
            Intrinsics.checkNotNullExpressionValue(target, "wrap(byteArray)");
            C1694f0 renderSettings = (C1694f0) this.f1495i.f1482d.f1355l.getValue();
            c1663a0.getClass();
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(area, "area");
            Intrinsics.checkNotNullParameter(target, "target");
            Intrinsics.checkNotNullParameter(renderSettings, "renderSettings");
            long jCurrentTimeMillis = System.currentTimeMillis();
            C1663a0.f875e.m446d(new C1675c0(document));
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(document.mo461a(area, renderSettings));
            byte[] bArr = new byte[131072];
            while (true) {
                try {
                    int i3 = autoCloseInputStream.read(bArr, 0, 131072);
                    if (i3 <= 0) {
                        break;
                    } else {
                        target.put(bArr, 0, i3);
                    }
                } finally {
                }
            }
            CloseableKt.closeFinally(autoCloseInputStream, null);
            C1663a0.f875e.m446d(new C1681d0(document));
            int i4 = area.f1878d * area.f1879e * renderSettings.f1197a.f1362a;
            if (target.position() != i4) {
                C1663a0.f875e.m447e("renderStripe() expected " + i4 + ", got " + target.position());
            }
            C1663a0.f875e.m446d(new C1688e0(area, target, jCurrentTimeMillis));
        }
    }

    public static final class c extends Lambda implements Function0<InterfaceC1783x> {
        public c() {
            super(0);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.p020hp.printsdk.InterfaceC1783x invoke() throws java.io.IOException {
            /*
                r7 = this;
                com.hp.printsdk.m r0 = com.p020hp.printsdk.C1729m.this
                java.io.InputStream r1 = r0.f1485g
                r2 = 0
                if (r1 == 0) goto La1
                com.hp.printsdk.a0 r0 = r0.f1481c
                r0.getClass()
                java.lang.String r3 = "inputStream"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
                android.content.Context r3 = r0.f876a
                java.io.File r3 = r3.getCacheDir()
                java.lang.String r4 = "convert"
                java.lang.String r5 = ""
                java.io.File r3 = java.io.File.createTempFile(r4, r5, r3)
                com.hp.mobile.common.utils.Logger r4 = com.p020hp.printsdk.C1663a0.f875e
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                java.lang.String r6 = "open(): created tempFile "
                r5.<init>(r6)
                java.lang.StringBuilder r5 = r5.append(r3)
                java.lang.String r5 = r5.toString()
                r4.invoke(r5)
                java.io.FileOutputStream r4 = new java.io.FileOutputStream
                java.lang.String r5 = "tempFile"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
                r4.<init>(r3)
                r5 = 0
                r6 = 2
                kotlin.p037io.ByteStreamsKt.copyTo$default(r1, r4, r5, r6, r2)     // Catch: java.lang.Throwable -> L94
                kotlin.p037io.CloseableKt.closeFinally(r1, r2)     // Catch: java.lang.Throwable -> L92
                kotlin.p037io.CloseableKt.closeFinally(r4, r2)
                com.hp.printsdk.y r0 = r0.f878c     // Catch: java.lang.Throwable -> L6d
                r1 = 268435456(0x10000000, float:2.524355E-29)
                android.os.ParcelFileDescriptor r1 = android.os.ParcelFileDescriptor.open(r3, r1)     // Catch: java.lang.Throwable -> L6d
                com.hp.printsdk.x r0 = r0.mo405a(r1)     // Catch: java.lang.Throwable -> L6d
                if (r0 == 0) goto L67
                java.lang.String r1 = "openDocument(ParcelFileD…scriptor.MODE_READ_ONLY))"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch: java.lang.Throwable -> L6d
                com.hp.printsdk.a0$a r1 = new com.hp.printsdk.a0$a     // Catch: java.lang.Throwable -> L6d
                com.hp.printsdk.b0 r4 = new com.hp.printsdk.b0     // Catch: java.lang.Throwable -> L6d
                r4.<init>(r3)     // Catch: java.lang.Throwable -> L6d
                r1.<init>(r0, r4)     // Catch: java.lang.Throwable -> L6d
                goto L68
            L67:
                r1 = r2
            L68:
                java.lang.Object r0 = kotlin.Result.m1772constructorimpl(r1)     // Catch: java.lang.Throwable -> L6d
                goto L76
            L6d:
                r0 = move-exception
                java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
                java.lang.Object r0 = kotlin.Result.m1772constructorimpl(r0)
            L76:
                java.lang.Throwable r1 = kotlin.Result.m1775exceptionOrNullimpl(r0)
                if (r1 == 0) goto L86
                com.hp.mobile.common.utils.Logger r4 = com.p020hp.printsdk.C1663a0.f875e
                java.lang.String r5 = "Failed to open input"
                r4.m448e(r5, r1)
                r3.delete()
            L86:
                boolean r1 = kotlin.Result.m1778isFailureimpl(r0)
                if (r1 == 0) goto L8d
                r0 = r2
            L8d:
                com.hp.printsdk.x r0 = (com.p020hp.printsdk.InterfaceC1783x) r0
                if (r0 != 0) goto Lb6
                goto La1
            L92:
                r0 = move-exception
                goto L9b
            L94:
                r0 = move-exception
                throw r0     // Catch: java.lang.Throwable -> L96
            L96:
                r2 = move-exception
                kotlin.p037io.CloseableKt.closeFinally(r1, r0)     // Catch: java.lang.Throwable -> L92
                throw r2     // Catch: java.lang.Throwable -> L92
            L9b:
                throw r0     // Catch: java.lang.Throwable -> L9c
            L9c:
                r1 = move-exception
                kotlin.p037io.CloseableKt.closeFinally(r4, r0)
                throw r1
            La1:
                com.hp.printsdk.m r0 = com.p020hp.printsdk.C1729m.this
                java.io.File r1 = r0.f1486h
                if (r1 == 0) goto Lad
                com.hp.printsdk.a0 r0 = r0.f1481c
                com.hp.printsdk.x r2 = r0.m460a(r1)
            Lad:
                if (r2 != 0) goto Lb5
                com.hp.printsdk.x$a r0 = new com.hp.printsdk.x$a
                r0.<init>()
                goto Lb6
            Lb5:
                r0 = r2
            Lb6:
                com.hp.printsdk.m r1 = com.p020hp.printsdk.C1729m.this
                com.hp.printsdk.n r2 = new com.hp.printsdk.n
                r2.<init>(r0)
                r1.m527a(r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1729m.c.invoke():java.lang.Object");
        }
    }

    public static final class d extends Lambda implements Function0<Integer> {
        public d() {
            super(0);
        }

        @Override
        public Integer invoke() {
            return Integer.valueOf(C1729m.m580a(C1729m.this).getPageCount());
        }
    }

    public static final class e extends Lambda implements Function0<List<? extends b>> {
        public e() {
            super(0);
        }

        @Override
        public final List<b> invoke() {
            b bVar;
            IntRange intRangeUntil = RangesKt.until(0, C1729m.m580a(C1729m.this).getPageCount());
            C1729m c1729m = C1729m.this;
            ArrayList arrayList = new ArrayList();
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                C1700g0 c1700g0Mo462a = C1729m.m580a(c1729m).mo462a(iNextInt);
                if (c1700g0Mo462a != null) {
                    Intrinsics.checkNotNullExpressionValue(c1700g0Mo462a, "getPageSize(index)");
                    bVar = new b(c1729m, iNextInt, c1700g0Mo462a);
                } else {
                    bVar = null;
                }
                if (bVar != null) {
                    arrayList.add(bVar);
                }
            }
            return arrayList;
        }
    }

    static {
        a aVar = new a();
        f1479l = aVar;
        f1480m = LoggerKt.logger(aVar);
    }

    public C1729m(C1663a0 c1663a0, C1714j c1714j, Function0<Boolean> function0) {
        this.f1481c = c1663a0;
        this.f1482d = c1714j;
        this.f1483e = function0;
        this.f1484f = c1714j.m547a();
    }

    public static final InterfaceC1783x m580a(C1729m c1729m) {
        return (InterfaceC1783x) c1729m.f1487i.getValue();
    }

    @Override
    public int mo568a() {
        return this.f1484f;
    }

    @Override
    public int getPageCount() {
        return ((Number) this.f1488j.getValue()).intValue();
    }

    @Override
    public Iterator<AbstractC1760s1> iterator() {
        return ((List) this.f1489k.getValue()).iterator();
    }
}
