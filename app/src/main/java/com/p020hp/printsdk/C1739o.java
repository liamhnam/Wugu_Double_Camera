package com.p020hp.printsdk;

import android.graphics.Bitmap;
import com.p020hp.jipp.model.PrintScaling;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.font.FontMappers;
import com.tom_roush.pdfbox.rendering.PDFRenderer;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.p037io.CloseableKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

public final class C1739o extends AbstractC1704h {

    public static final a f1520g;

    public static final Logger f1521h;

    public final PDDocument f1522c;

    public final C1714j f1523d;

    public final Lazy f1524e;

    public final Lazy f1525f;

    public static final class a {
        public static final int m591a(a aVar, double d, int i) {
            return (int) ((d * ((double) i)) / ((double) 72));
        }

        public static final C1700g0 m592a(a aVar, C1700g0 c1700g0) {
            return new C1700g0(c1700g0.f1209b, c1700g0.f1208a);
        }

        public static final boolean m593b(a aVar, C1700g0 c1700g0) {
            return c1700g0.f1209b > c1700g0.f1208a;
        }
    }

    public static final class c extends Lambda implements Function0<Integer> {
        public c() {
            super(0);
        }

        @Override
        public Integer invoke() {
            return Integer.valueOf(C1739o.this.f1522c.getPages().getCount());
        }
    }

    public static final class d extends Lambda implements Function0<PDFRenderer> {
        public d() {
            super(0);
        }

        @Override
        public PDFRenderer invoke() {
            return new PDFRenderer(C1739o.this.f1522c);
        }
    }

    static {
        a aVar = new a();
        f1520g = aVar;
        f1521h = LoggerKt.logger(aVar);
    }

    public C1739o(PDDocument pdDocument, C1714j options) {
        Intrinsics.checkNotNullParameter(pdDocument, "pdDocument");
        Intrinsics.checkNotNullParameter(options, "options");
        this.f1522c = pdDocument;
        this.f1523d = options;
        FontMappers.set(new C1730m0());
        this.f1524e = LazyKt.lazy(new c());
        this.f1525f = LazyKt.lazy(new d());
    }

    @Override
    public int mo568a() {
        return this.f1523d.f1345b;
    }

    public final C1714j m590c() {
        return this.f1523d;
    }

    @Override
    public int getPageCount() {
        return ((Number) this.f1524e.getValue()).intValue();
    }

    @Override
    public Iterator<AbstractC1760s1> iterator() {
        IntRange intRangeUntil = RangesKt.until(0, this.f1522c.getPages().getCount());
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            double d2 = 72;
            arrayList.add(new b(this, ((IntIterator) it).nextInt(), new C1700g0((int) ((((double) this.f1522c.getPage(r2).getMediaBox().getWidth()) * ((double) this.f1523d.f1345b)) / d2), (int) ((((double) this.f1522c.getPage(r2).getMediaBox().getHeight()) * ((double) this.f1523d.f1345b)) / d2))));
        }
        return arrayList.iterator();
    }

    public final class b extends AbstractC1760s1 {

        public final int f1526d;

        public final int f1527e;

        public final int f1528f;

        public final double f1529g;

        public final boolean f1530h;

        public final Lazy f1531i;

        public final int f1532j;

        public final int f1533k;

        public final int f1534l;

        public final int f1535m;

        public Bitmap f1536n;

        public ByteBuffer f1537o;

        public final C1739o f1538p;

        public static final class a extends Lambda implements Function0<String> {

            public final C1739o f1539a;

            public final b f1540b;

            public final C1700g0 f1541c;

            public final double f1542d;

            public a(C1739o c1739o, b bVar, C1700g0 c1700g0, double d) {
                super(0);
                this.f1539a = c1739o;
                this.f1540b = bVar;
                this.f1541c = c1700g0;
                this.f1542d = d;
            }

            @Override
            public String invoke() {
                StringBuilder sb = new StringBuilder("For ");
                sb.append(this.f1539a.f1523d).append(", created page of pageSize=(").append(this.f1540b.f1757a).append(", ").append(this.f1540b.f1758b).append("), rotatedPageSize=").append(this.f1541c).append(", rotate=").append(this.f1540b.f1530h).append(", contentZoom=").append(this.f1542d).append(", zoom=").append(this.f1540b.f1529g).append(", offset=(").append(this.f1540b.f1527e).append(", ").append(this.f1540b.f1528f).append("), pixels=(").append(this.f1540b.f1757a).append(", ").append(this.f1540b.f1758b).append(')');
                return sb.toString();
            }
        }

        public static final class C3367b extends Lambda implements Function0<String> {

            public final int f1543a;

            public final int f1544b;

            public final Bitmap f1545c;

            public final byte[] f1546d;

            public final long f1547e;

            public C3367b(int i, int i2, Bitmap bitmap, byte[] bArr, long j) {
                super(0);
                this.f1543a = i;
                this.f1544b = i2;
                this.f1545c = bitmap;
                this.f1546d = bArr;
                this.f1547e = j;
            }

            @Override
            public String invoke() {
                return "render done, render yOffset: " + this.f1543a + ", swathHeight: " + this.f1544b + ", cachedBitmap: " + this.f1545c.getWidth() + " x " + this.f1545c.getHeight() + ", byteArray size: " + this.f1546d.length + ", " + (System.currentTimeMillis() - this.f1547e) + " ms";
            }
        }

        public static final class c extends Lambda implements Function0<Bitmap> {

            public final C1739o f1548a;

            public final b f1549b;

            public c(C1739o c1739o, b bVar) {
                super(0);
                this.f1548a = c1739o;
                this.f1549b = bVar;
            }

            @Override
            public Bitmap invoke() {
                return ((PDFRenderer) this.f1548a.f1525f.getValue()).renderImageWithDPI(this.f1549b.f1526d, this.f1548a.f1523d.f1345b);
            }
        }

        public b(C1739o c1739o, int i, C1700g0 pageSize) {
            double dMin;
            super(a.m591a(C1739o.f1520g, c1739o.m590c().m552f().m524b(), c1739o.mo568a()), a.m591a(C1739o.f1520g, c1739o.m590c().m552f().m522a(), c1739o.mo568a()));
            Intrinsics.checkNotNullParameter(pageSize, "pageSize");
            this.f1538p = c1739o;
            this.f1526d = i;
            int iOrdinal = c1739o.m590c().m553g().ordinal();
            boolean z = true;
            if (iOrdinal != 0 && (iOrdinal != 1 || a.m593b(C1739o.f1520g, pageSize) == a.m593b(C1739o.f1520g, c1739o.m590c().m552f()))) {
                z = false;
            }
            this.f1530h = z;
            this.f1531i = LazyKt.lazy(new c(c1739o, this));
            this.f1532j = a.m591a(C1739o.f1520g, c1739o.m590c().m549c(), c1739o.mo568a());
            this.f1533k = a.m591a(C1739o.f1520g, c1739o.m590c().m550d(), c1739o.mo568a());
            this.f1534l = a.m591a(C1739o.f1520g, c1739o.m590c().m551e(), c1739o.mo568a());
            this.f1535m = a.m591a(C1739o.f1520g, c1739o.m590c().m548b(), c1739o.mo568a());
            C1700g0 c1700g0M592a = z ? a.m592a(C1739o.f1520g, pageSize) : pageSize;
            C1700g0 c1700g0 = new C1700g0((c1739o.m590c().m552f().m524b() - c1739o.m590c().m549c()) - c1739o.m590c().m550d(), (c1739o.m590c().m552f().m522a() - c1739o.m590c().m548b()) - c1739o.m590c().m551e());
            String strM554h = c1739o.m590c().m554h();
            int iHashCode = strM554h.hashCode();
            if (iHashCode == 101393) {
                if (!strM554h.equals(PrintScaling.fit)) {
                }
                double d = dMin;
                this.f1529g = (((double) c1739o.mo568a()) / ((double) 72)) * d;
                double d2 = 2;
                this.f1527e = a.m591a(C1739o.f1520g, (c1739o.m590c().m552f().m524b() - (c1700g0M592a.m524b() * d)) / d2, c1739o.mo568a());
                this.f1528f = a.m591a(C1739o.f1520g, (c1739o.m590c().m552f().m522a() - (c1700g0M592a.m522a() * d)) / d2, c1739o.mo568a());
                C1739o.f1521h.m446d(new a(c1739o, this, c1700g0M592a, d));
            }
            if (iHashCode != 3143043) {
                if (iHashCode != 3387192 || !strM554h.equals("none")) {
                }
                double d3 = dMin;
                this.f1529g = (((double) c1739o.mo568a()) / ((double) 72)) * d3;
                double d22 = 2;
                this.f1527e = a.m591a(C1739o.f1520g, (c1739o.m590c().m552f().m524b() - (c1700g0M592a.m524b() * d3)) / d22, c1739o.mo568a());
                this.f1528f = a.m591a(C1739o.f1520g, (c1739o.m590c().m552f().m522a() - (c1700g0M592a.m522a() * d3)) / d22, c1739o.mo568a());
                C1739o.f1521h.m446d(new a(c1739o, this, c1700g0M592a, d3));
            }
            if (strM554h.equals(PrintScaling.fill)) {
                dMin = Math.max(c1700g0.m524b() / c1700g0M592a.m524b(), c1700g0.m522a() / c1700g0M592a.m522a());
                double d32 = dMin;
                this.f1529g = (((double) c1739o.mo568a()) / ((double) 72)) * d32;
                double d222 = 2;
                this.f1527e = a.m591a(C1739o.f1520g, (c1739o.m590c().m552f().m524b() - (c1700g0M592a.m524b() * d32)) / d222, c1739o.mo568a());
                this.f1528f = a.m591a(C1739o.f1520g, (c1739o.m590c().m552f().m522a() - (c1700g0M592a.m522a() * d32)) / d222, c1739o.mo568a());
                C1739o.f1521h.m446d(new a(c1739o, this, c1700g0M592a, d32));
            }
            dMin = (c1700g0M592a.m524b() > c1700g0.m524b() || c1700g0M592a.m522a() > c1700g0.m522a()) ? Math.min(c1700g0.m524b() / c1700g0M592a.m524b(), c1700g0.m522a() / c1700g0M592a.m522a()) : 1.0d;
            double d322 = dMin;
            this.f1529g = (((double) c1739o.mo568a()) / ((double) 72)) * d322;
            double d2222 = 2;
            this.f1527e = a.m591a(C1739o.f1520g, (c1739o.m590c().m552f().m524b() - (c1700g0M592a.m524b() * d322)) / d2222, c1739o.mo568a());
            this.f1528f = a.m591a(C1739o.f1520g, (c1739o.m590c().m552f().m522a() - (c1700g0M592a.m522a() * d322)) / d2222, c1739o.mo568a());
            C1739o.f1521h.m446d(new a(c1739o, this, c1700g0M592a, d322));
        }

        public final void m594a(ByteBuffer byteBuffer, byte[] bArr, int i) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            try {
                Function3<byte[], Integer, OutputStream, Unit> function3M558a = EnumC1716j1.Rgba.m558a(this.f1538p.f1523d.f1347d);
                for (int i2 = 0; i2 < i; i2++) {
                    int i3 = this.f1757a;
                    for (int i4 = 0; i4 < i3; i4++) {
                        byte[] bArrArray = byteBuffer.array();
                        Intrinsics.checkNotNullExpressionValue(bArrArray, "array()");
                        function3M558a.invoke(bArrArray, Integer.valueOf(((this.f1757a * i2) + i4) * EnumC1716j1.Rgba.f1362a), byteArrayOutputStream);
                    }
                }
                System.arraycopy(byteArrayOutputStream.toByteArray(), 0, bArr, 0, bArr.length);
                CloseableKt.closeFinally(byteArrayOutputStream, null);
            } finally {
            }
        }

        public final Bitmap m595c() {
            Object value = this.f1531i.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-renderImage>(...)");
            return (Bitmap) value;
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void mo579a(int r16, int r17, com.p020hp.printsdk.EnumC1716j1 r18, byte[] r19) {
            /*
                Method dump skipped, instruction units count: 351
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1739o.b.mo579a(int, int, com.hp.printsdk.j1, byte[]):void");
        }
    }
}
