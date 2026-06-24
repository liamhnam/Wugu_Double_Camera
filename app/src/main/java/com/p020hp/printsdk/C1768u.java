package com.p020hp.printsdk;

import com.p020hp.jipp.model.PrintScaling;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.p037io.CloseableKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

public final class C1768u extends AbstractC1704h {

    public static final a f1820j;

    public static final Logger f1821k;

    public final C1758s f1822c;

    public final C1714j f1823d;

    public final Function0<Boolean> f1824e;

    public final int f1825f;

    public final InterfaceC1749q f1826g;

    public final Lazy f1827h;

    public final Lazy f1828i;

    public static final class a {
        public static final int m660a(a aVar, double d, int i) {
            return (int) ((d * ((double) i)) / ((double) 72));
        }

        public static final C1700g0 m661a(a aVar, C1700g0 c1700g0) {
            return new C1700g0(c1700g0.f1209b, c1700g0.f1208a);
        }

        public static final boolean m662b(a aVar, C1700g0 c1700g0) {
            return c1700g0.f1209b > c1700g0.f1208a;
        }
    }

    public final class b extends AbstractC1760s1 {

        public final int f1829d;

        public final int f1830e;

        public final int f1831f;

        public final double f1832g;

        public final boolean f1833h;

        public final C1768u f1834i;

        public static final class a extends Lambda implements Function0<String> {

            public final C1768u f1835a;

            public final C1700g0 f1836b;

            public final C1700g0 f1837c;

            public final b f1838d;

            public final double f1839e;

            public a(C1768u c1768u, C1700g0 c1700g0, C1700g0 c1700g02, b bVar, double d) {
                super(0);
                this.f1835a = c1768u;
                this.f1836b = c1700g0;
                this.f1837c = c1700g02;
                this.f1838d = bVar;
                this.f1839e = d;
            }

            @Override
            public String invoke() {
                return "For " + this.f1835a.f1823d + ", created page of pageSize=" + this.f1836b + ", rotatedPageSize=" + this.f1837c + ", rotate=" + this.f1838d.f1833h + ", contentZoom=" + this.f1839e + ", zoom=" + this.f1838d.f1832g + ", offset=(" + this.f1838d.f1830e + ", " + this.f1838d.f1831f + "), pixels=(" + this.f1838d.f1757a + ", " + this.f1838d.f1758b + ')';
            }
        }

        public b(C1768u c1768u, int i, C1700g0 pageSize) {
            double dMin;
            super(a.m660a(C1768u.f1820j, c1768u.f1823d.m552f().m524b(), c1768u.mo568a()), a.m660a(C1768u.f1820j, c1768u.f1823d.m552f().m522a(), c1768u.mo568a()));
            Intrinsics.checkNotNullParameter(pageSize, "pageSize");
            this.f1834i = c1768u;
            this.f1829d = i;
            int iOrdinal = c1768u.f1823d.m553g().ordinal();
            boolean z = true;
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    if (iOrdinal != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                } else if (a.m662b(C1768u.f1820j, pageSize) == a.m662b(C1768u.f1820j, c1768u.f1823d.m552f())) {
                }
                z = false;
            }
            this.f1833h = z;
            C1700g0 c1700g0M661a = z ? a.m661a(C1768u.f1820j, pageSize) : pageSize;
            C1700g0 c1700g0 = new C1700g0((c1768u.f1823d.m552f().m524b() - c1768u.f1823d.m549c()) - c1768u.f1823d.m550d(), (c1768u.f1823d.m552f().m522a() - c1768u.f1823d.m548b()) - c1768u.f1823d.m551e());
            String strM554h = c1768u.f1823d.m554h();
            int iHashCode = strM554h.hashCode();
            if (iHashCode == 101393) {
                if (!strM554h.equals(PrintScaling.fit)) {
                }
                double d = dMin;
                this.f1832g = (((double) c1768u.mo568a()) / ((double) 72)) * d;
                double d2 = 2;
                this.f1830e = a.m660a(C1768u.f1820j, (c1768u.f1823d.m552f().m524b() - (c1700g0M661a.m524b() * d)) / d2, c1768u.mo568a());
                this.f1831f = a.m660a(C1768u.f1820j, (c1768u.f1823d.m552f().m522a() - (c1700g0M661a.m522a() * d)) / d2, c1768u.mo568a());
                C1768u.f1821k.m446d(new a(c1768u, pageSize, c1700g0M661a, this, d));
            }
            if (iHashCode != 3143043) {
                if (iHashCode != 3387192 || !strM554h.equals("none")) {
                }
                double d3 = dMin;
                this.f1832g = (((double) c1768u.mo568a()) / ((double) 72)) * d3;
                double d22 = 2;
                this.f1830e = a.m660a(C1768u.f1820j, (c1768u.f1823d.m552f().m524b() - (c1700g0M661a.m524b() * d3)) / d22, c1768u.mo568a());
                this.f1831f = a.m660a(C1768u.f1820j, (c1768u.f1823d.m552f().m522a() - (c1700g0M661a.m522a() * d3)) / d22, c1768u.mo568a());
                C1768u.f1821k.m446d(new a(c1768u, pageSize, c1700g0M661a, this, d3));
            }
            if (strM554h.equals(PrintScaling.fill)) {
                dMin = Math.max(c1700g0.m524b() / c1700g0M661a.m524b(), c1700g0.m522a() / c1700g0M661a.m522a());
                double d32 = dMin;
                this.f1832g = (((double) c1768u.mo568a()) / ((double) 72)) * d32;
                double d222 = 2;
                this.f1830e = a.m660a(C1768u.f1820j, (c1768u.f1823d.m552f().m524b() - (c1700g0M661a.m524b() * d32)) / d222, c1768u.mo568a());
                this.f1831f = a.m660a(C1768u.f1820j, (c1768u.f1823d.m552f().m522a() - (c1700g0M661a.m522a() * d32)) / d222, c1768u.mo568a());
                C1768u.f1821k.m446d(new a(c1768u, pageSize, c1700g0M661a, this, d32));
            }
            dMin = (c1700g0M661a.m524b() > c1700g0.m524b() || c1700g0M661a.m522a() > c1700g0.m522a()) ? Math.min(c1700g0.m524b() / c1700g0M661a.m524b(), c1700g0.m522a() / c1700g0M661a.m522a()) : 1.0d;
            double d322 = dMin;
            this.f1832g = (((double) c1768u.mo568a()) / ((double) 72)) * d322;
            double d2222 = 2;
            this.f1830e = a.m660a(C1768u.f1820j, (c1768u.f1823d.m552f().m524b() - (c1700g0M661a.m524b() * d322)) / d2222, c1768u.mo568a());
            this.f1831f = a.m660a(C1768u.f1820j, (c1768u.f1823d.m552f().m522a() - (c1700g0M661a.m522a() * d322)) / d2222, c1768u.mo568a());
            C1768u.f1821k.m446d(new a(c1768u, pageSize, c1700g0M661a, this, d322));
        }

        @Override
        public void mo579a(int i, int i2, EnumC1716j1 colorSpace, byte[] byteArray) {
            Unit unit;
            Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
            Intrinsics.checkNotNullParameter(byteArray, "byteArray");
            if (!this.f1834i.f1824e.invoke().booleanValue()) {
                throw new CancellationException();
            }
            C1773v area = this.f1833h ? new C1773v(this.f1829d, i - this.f1831f, -this.f1830e, i2, this.f1757a, this.f1832g, true) : new C1773v(this.f1829d, -this.f1830e, i - this.f1831f, this.f1757a, i2, this.f1832g, false);
            c out = new c(byteArray);
            C1768u c1768u = this.f1834i;
            try {
                C1758s c1758s = c1768u.f1822c;
                C1694f0 renderSettings = (C1694f0) c1768u.f1823d.f1355l.getValue();
                c1758s.getClass();
                Intrinsics.checkNotNullParameter(out, "out");
                Intrinsics.checkNotNullParameter(area, "area");
                Intrinsics.checkNotNullParameter(renderSettings, "renderSettings");
                C1758s.f1704f.m446d(new C1763t(area));
                C1793z c1793zM639b = c1758s.m639b(area.f1875a);
                if (c1793zM639b != null) {
                    c1793zM639b.m693a(out, area, renderSettings);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    C1758s.f1704f.m447e("renderPageStripe failed");
                }
                CloseableKt.closeFinally(out, null);
            } finally {
            }
        }
    }

    public static final class c extends OutputStream {

        public final byte[] f1840a;

        public int f1841b;

        public c(byte[] byteArray) {
            Intrinsics.checkNotNullParameter(byteArray, "byteArray");
            this.f1840a = byteArray;
        }

        @Override
        public void write(int i) {
            byte[] bArr = this.f1840a;
            int i2 = this.f1841b;
            bArr[i2] = (byte) i;
            this.f1841b = i2 + 1;
        }

        @Override
        public void write(byte[] b, int i, int i2) {
            Intrinsics.checkNotNullParameter(b, "b");
            System.arraycopy(b, i, this.f1840a, this.f1841b, i2);
            this.f1841b += i2;
        }
    }

    public static final class d extends Lambda implements Function0<Integer> {
        public d() {
            super(0);
        }

        @Override
        public Integer invoke() {
            return Integer.valueOf(C1768u.this.f1826g.getPageCount());
        }
    }

    public static final class e extends Lambda implements Function0<List<? extends b>> {
        public e() {
            super(0);
        }

        @Override
        public final List<b> invoke() {
            IntRange intRangeUntil = RangesKt.until(0, C1768u.this.f1826g.getPageCount());
            C1768u c1768u = C1768u.this;
            ArrayList arrayList = new ArrayList();
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                C1700g0 c1700g0Mo610a = c1768u.f1826g.mo610a(iNextInt);
                b bVar = c1700g0Mo610a != null ? new b(c1768u, iNextInt, c1700g0Mo610a) : null;
                if (bVar != null) {
                    arrayList.add(bVar);
                }
            }
            return arrayList;
        }
    }

    static {
        a aVar = new a();
        f1820j = aVar;
        f1821k = LoggerKt.logger(aVar);
    }

    public C1768u(C1758s pdfRender, C1714j options, Function0<Boolean> isActive) {
        Intrinsics.checkNotNullParameter(pdfRender, "pdfRender");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(isActive, "isActive");
        this.f1822c = pdfRender;
        this.f1823d = options;
        this.f1824e = isActive;
        this.f1825f = options.m547a();
        this.f1826g = pdfRender;
        this.f1827h = LazyKt.lazy(new d());
        this.f1828i = LazyKt.lazy(new e());
    }

    @Override
    public int mo568a() {
        return this.f1825f;
    }

    @Override
    public int getPageCount() {
        return ((Number) this.f1827h.getValue()).intValue();
    }

    @Override
    public Iterator<AbstractC1760s1> iterator() {
        return ((List) this.f1828i.getValue()).iterator();
    }
}
