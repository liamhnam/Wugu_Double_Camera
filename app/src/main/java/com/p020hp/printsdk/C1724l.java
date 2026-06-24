package com.p020hp.printsdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.p020hp.jipp.model.PrintScaling;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;

public final class C1724l extends AbstractC1704h {

    public static final a f1418f;

    public static final Logger f1419g;

    public final InputStream f1420c;

    public final b f1421d;

    public final Lazy f1422e;

    public static final class a {
        public static final int m572a(a aVar, double d, int i) {
            return (int) ((d * ((double) i)) / ((double) 72));
        }
    }

    public static final class b {

        public final C1700g0 f1423a;

        public final int f1424b;

        public final EnumC1716j1 f1425c;

        public final String f1426d;

        public final double f1427e;

        public final double f1428f;

        public final double f1429g;

        public final double f1430h;

        public b(C1700g0 outputSize, int i, EnumC1716j1 colorSpace, String scaling, double d, double d2, double d3, double d4) {
            Intrinsics.checkNotNullParameter(outputSize, "outputSize");
            Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
            Intrinsics.checkNotNullParameter(scaling, "scaling");
            this.f1423a = outputSize;
            this.f1424b = i;
            this.f1425c = colorSpace;
            this.f1426d = scaling;
            this.f1427e = d;
            this.f1428f = d2;
            this.f1429g = d3;
            this.f1430h = d4;
        }

        public final double m573a() {
            return this.f1428f;
        }

        public final double m574b() {
            return this.f1429g;
        }

        public final double m575c() {
            return this.f1430h;
        }

        public final double m576d() {
            return this.f1427e;
        }

        public final C1700g0 m577e() {
            return this.f1423a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return Intrinsics.areEqual(this.f1423a, bVar.f1423a) && this.f1424b == bVar.f1424b && this.f1425c == bVar.f1425c && Intrinsics.areEqual(this.f1426d, bVar.f1426d) && Intrinsics.areEqual((Object) Double.valueOf(this.f1427e), (Object) Double.valueOf(bVar.f1427e)) && Intrinsics.areEqual((Object) Double.valueOf(this.f1428f), (Object) Double.valueOf(bVar.f1428f)) && Intrinsics.areEqual((Object) Double.valueOf(this.f1429g), (Object) Double.valueOf(bVar.f1429g)) && Intrinsics.areEqual((Object) Double.valueOf(this.f1430h), (Object) Double.valueOf(bVar.f1430h));
        }

        public final String m578f() {
            return this.f1426d;
        }

        public int hashCode() {
            return (((((((((((((this.f1423a.hashCode() * 31) + Integer.hashCode(this.f1424b)) * 31) + this.f1425c.hashCode()) * 31) + this.f1426d.hashCode()) * 31) + Double.hashCode(this.f1427e)) * 31) + Double.hashCode(this.f1428f)) * 31) + Double.hashCode(this.f1429g)) * 31) + Double.hashCode(this.f1430h);
        }

        public String toString() {
            return "Options(outputSize=" + this.f1423a + ", dpi=" + this.f1424b + ", colorSpace=" + this.f1425c + ", scaling=" + this.f1426d + ", mediaTopMargin=" + this.f1427e + ", mediaBottomMargin=" + this.f1428f + ", mediaLeftMargin=" + this.f1429g + ", mediaRightMargin=" + this.f1430h + ')';
        }
    }

    public final class c extends AbstractC1760s1 {

        public final Bitmap f1431d;

        public final Bitmap f1432e;

        public final int f1433f;

        public final int f1434g;

        public final int f1435h;

        public final int f1436i;

        public final int f1437j;

        public final int f1438k;

        public final double f1439l;

        public final int f1440m;

        public final int f1441n;

        public Bitmap f1442o;

        public ByteBuffer f1443p;

        public static final class a extends Lambda implements Function0<String> {
            public a() {
                super(0);
            }

            @Override
            public String invoke() {
                StringBuilder sb = new StringBuilder("outputSize: ");
                sb.append(c.this.f1757a).append(" x ").append(c.this.f1758b).append(", originBitmap: ").append(c.this.f1431d.getWidth()).append(" x ").append(c.this.f1431d.getHeight()).append(", finalBitmap: ").append(c.this.f1432e.getWidth()).append(" x ").append(c.this.f1432e.getHeight()).append(", contentSize: ").append(c.this.f1437j).append(" x ").append(c.this.f1438k).append(", contentLeftMargin: ").append(c.this.f1433f).append(", contentRightMargin: ").append(c.this.f1434g).append(", contentTopMargin: ").append(c.this.f1435h).append(", contentBottomMargin: ");
                sb.append(c.this.f1436i).append(",, xOffset: ").append(c.this.f1440m).append(", yOffset: ").append(c.this.f1441n).append(", contentZoom: ").append(c.this.f1439l);
                return sb.toString();
            }
        }

        public static final class b extends Lambda implements Function0<String> {

            public final int f1446a;

            public final int f1447b;

            public final Bitmap f1448c;

            public final byte[] f1449d;

            public final long f1450e;

            public b(int i, int i2, Bitmap bitmap, byte[] bArr, long j) {
                super(0);
                this.f1446a = i;
                this.f1447b = i2;
                this.f1448c = bitmap;
                this.f1449d = bArr;
                this.f1450e = j;
            }

            @Override
            public String invoke() {
                return "render done, render yOffset: " + this.f1446a + ", swathHeight: " + this.f1447b + ", cachedBitmap: " + this.f1448c.getWidth() + " x " + this.f1448c.getHeight() + ", byteArray size: " + this.f1449d.length + ", " + (System.currentTimeMillis() - this.f1450e) + " ms";
            }
        }

        public c() {
            super(a.m572a(C1724l.f1418f, C1724l.this.m571d().m577e().m524b(), C1724l.this.mo568a()), a.m572a(C1724l.f1418f, C1724l.this.m571d().m577e().m522a(), C1724l.this.mo568a()));
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(C1724l.this.m570c());
            Intrinsics.checkNotNullExpressionValue(bitmapDecodeStream, "decodeStream(inputStream)");
            this.f1431d = bitmapDecodeStream;
            if (bitmapDecodeStream.getWidth() > bitmapDecodeStream.getHeight()) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90.0f);
                bitmapDecodeStream = Bitmap.createBitmap(bitmapDecodeStream, 0, 0, bitmapDecodeStream.getWidth(), bitmapDecodeStream.getHeight(), matrix, true);
            }
            Intrinsics.checkNotNullExpressionValue(bitmapDecodeStream, "if (originBitmap.width >…   originBitmap\n        }");
            this.f1432e = bitmapDecodeStream;
            int iM572a = a.m572a(C1724l.f1418f, C1724l.this.m571d().m574b(), C1724l.this.mo568a());
            this.f1433f = iM572a;
            int iM572a2 = a.m572a(C1724l.f1418f, C1724l.this.m571d().m575c(), C1724l.this.mo568a());
            this.f1434g = iM572a2;
            int iM572a3 = a.m572a(C1724l.f1418f, C1724l.this.m571d().m576d(), C1724l.this.mo568a());
            this.f1435h = iM572a3;
            int iM572a4 = a.m572a(C1724l.f1418f, C1724l.this.m571d().m573a(), C1724l.this.mo568a());
            this.f1436i = iM572a4;
            int iM654b = (m654b() - iM572a) - iM572a2;
            this.f1437j = iM654b;
            int iM653a = (m653a() - iM572a3) - iM572a4;
            this.f1438k = iM653a;
            double d = iM654b;
            double dMax = Intrinsics.areEqual(C1724l.this.m571d().m578f(), PrintScaling.fill) ? Math.max(d / ((double) bitmapDecodeStream.getWidth()), ((double) iM653a) / ((double) bitmapDecodeStream.getHeight())) : Math.min(d / ((double) bitmapDecodeStream.getWidth()), ((double) iM653a) / ((double) bitmapDecodeStream.getHeight()));
            this.f1439l = dMax;
            double d2 = 2;
            this.f1440m = MathKt.roundToInt((((double) m654b()) - (((double) bitmapDecodeStream.getWidth()) * dMax)) / d2);
            this.f1441n = MathKt.roundToInt((((double) m653a()) - (((double) bitmapDecodeStream.getHeight()) * dMax)) / d2);
            C1724l.f1419g.m446d(new a());
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void mo579a(int r17, int r18, com.p020hp.printsdk.EnumC1716j1 r19, byte[] r20) {
            /*
                Method dump skipped, instruction units count: 288
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1724l.c.mo579a(int, int, com.hp.printsdk.j1, byte[]):void");
        }
    }

    public static final class d extends Lambda implements Function0<c> {
        public d() {
            super(0);
        }

        @Override
        public c invoke() {
            return C1724l.this.new c();
        }
    }

    static {
        a aVar = new a();
        f1418f = aVar;
        f1419g = LoggerKt.logger(aVar);
    }

    public C1724l(InputStream inputStream, b options) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(options, "options");
        this.f1420c = inputStream;
        this.f1421d = options;
        this.f1422e = LazyKt.lazy(new d());
    }

    @Override
    public int mo568a() {
        return this.f1421d.f1424b;
    }

    public final InputStream m570c() {
        return this.f1420c;
    }

    public final b m571d() {
        return this.f1421d;
    }

    @Override
    public int getPageCount() {
        return 1;
    }

    @Override
    public Iterator<AbstractC1760s1> iterator() {
        return CollectionsKt.listOf((c) this.f1422e.getValue()).listIterator();
    }
}
