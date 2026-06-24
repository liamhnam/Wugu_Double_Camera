package com.p020hp.printsdk;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

public final class C1677c2 {

    public static final c f990N = new c();

    public final int f991A;

    public final int f992B;

    public final int f993C;

    public final int f994D;

    public final int f995E;

    public final g f996F;

    public final int f997G;

    public final byte[] f998H;

    public final String f999I;

    public final String f1000J;

    public final int f1001K;

    public final int f1002L;

    public final C1671b2 f1003M;

    public final String f1004a;

    public final String f1005b;

    public final String f1006c;

    public final h f1007d;

    public final boolean f1008e;

    public final int f1009f;

    public final int f1010g;

    public final boolean f1011h;

    public final h f1012i;

    public final d f1013j;

    public final e f1014k;

    public final int f1015l;

    public final int f1016m;

    public final f f1017n;

    public final int f1018o;

    public final int f1019p;

    public final boolean f1020q;

    public final int f1021r;

    public final int f1022s;

    public final int f1023t;

    public final int f1024u;

    public final a f1025v;

    public final b f1026w;

    public final int f1027x;

    public final int f1028y;

    public final int f1029z;

    public enum a {
        Chunky(0);


        public final int f1032a;

        a(int i) {
            this.f1032a = i;
        }
    }

    public enum b {
        Rgb(1),
        Black(3),
        Cmyk(6),
        Sgray(18),
        Srgb(19),
        AdobeRgb(20),
        Device1(48),
        Device2(49),
        Device3(50),
        Device4(51),
        Device5(52),
        Device6(53),
        Device7(54),
        Device8(55),
        Device9(56),
        Device10(57),
        Device11(58),
        Device12(59),
        Device13(60),
        Device14(61),
        Device15(62);


        public static final a f1033b = new a();

        public final int f1056a;

        public static final class a {
        }

        b(int i) {
            this.f1056a = i;
        }
    }

    public static final class c {
    }

    public enum d {
        ShortEdgeFirst(0),
        LongEdgeFirst(1);


        public final int f1060a;

        d(int i) {
            this.f1060a = i;
        }
    }

    public enum e {
        Auto(0),
        Main(1),
        Alternate(2),
        LargeCapacity(3),
        Manual(4),
        Envelope(5),
        Disc(6),
        Photo(7),
        Hagaki(8),
        MainRoll(9),
        AlternateRoll(10),
        Top(11),
        Middle(12),
        Bottom(13),
        Side(14),
        Left(15),
        Right(16),
        Center(17),
        Rear(18),
        ByPassTray(19),
        Tray1(20),
        Tray2(21),
        Tray3(22),
        Tray4(23),
        Tray5(24),
        Tray6(25),
        Tray7(26),
        Tray8(27),
        Tray9(28),
        Tray10(29),
        Tray11(30),
        Tray12(31),
        Tray13(32),
        Tray14(33),
        Tray15(34),
        Tray16(35),
        Tray17(36),
        Tray18(37),
        Tray19(38),
        Tray20(39),
        Roll1(40),
        Roll2(41),
        Roll3(42),
        Roll4(43),
        Roll5(44),
        Roll6(45),
        Roll7(46),
        Roll8(47),
        Roll9(48),
        Roll10(49);


        public final int f1112a;

        e(int i) {
            this.f1112a = i;
        }
    }

    public enum f {
        Portrait(0),
        Landscape(1),
        ReversePortrait(2),
        ReverseLandscape(3);


        public final int f1118a;

        f(int i) {
            this.f1118a = i;
        }
    }

    public enum g {
        Default(0),
        Draft(3),
        Normal(4),
        High(5);


        public final int f1124a;

        g(int i) {
            this.f1124a = i;
        }
    }

    public enum h {
        Never(0),
        AfterDocument(1),
        AfterJob(2),
        AfterSet(3),
        AfterPage(4);


        public final int f1131a;

        h(int i) {
            this.f1131a = i;
        }
    }

    public C1677c2(String mediaColor, String mediaType, String printContentOptimize, h cutMedia, boolean z, int i, int i2, boolean z2, h jog, d leadingEdge, e mediaPosition, int i3, int i4, f orientation, int i5, int i6, boolean z3, int i7, int i8, int i9, int i10, a colorOrder, b colorSpace, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, g printQuality, int i19, byte[] vendorData, String renderingIntent, String pageSizeName) {
        Intrinsics.checkNotNullParameter(mediaColor, "mediaColor");
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        Intrinsics.checkNotNullParameter(printContentOptimize, "printContentOptimize");
        Intrinsics.checkNotNullParameter(cutMedia, "cutMedia");
        Intrinsics.checkNotNullParameter(jog, "jog");
        Intrinsics.checkNotNullParameter(leadingEdge, "leadingEdge");
        Intrinsics.checkNotNullParameter(mediaPosition, "mediaPosition");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(colorOrder, "colorOrder");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        Intrinsics.checkNotNullParameter(printQuality, "printQuality");
        Intrinsics.checkNotNullParameter(vendorData, "vendorData");
        Intrinsics.checkNotNullParameter(renderingIntent, "renderingIntent");
        Intrinsics.checkNotNullParameter(pageSizeName, "pageSizeName");
        this.f1004a = mediaColor;
        this.f1005b = mediaType;
        this.f1006c = printContentOptimize;
        this.f1007d = cutMedia;
        this.f1008e = z;
        this.f1009f = i;
        this.f1010g = i2;
        this.f1011h = z2;
        this.f1012i = jog;
        this.f1013j = leadingEdge;
        this.f1014k = mediaPosition;
        this.f1015l = i3;
        this.f1016m = i4;
        this.f1017n = orientation;
        this.f1018o = i5;
        this.f1019p = i6;
        this.f1020q = z3;
        this.f1021r = i7;
        this.f1022s = i8;
        this.f1023t = i9;
        this.f1024u = i10;
        this.f1025v = colorOrder;
        this.f1026w = colorSpace;
        this.f1027x = i11;
        this.f1028y = i12;
        this.f1029z = i13;
        this.f991A = i14;
        this.f992B = i15;
        this.f993C = i16;
        this.f994D = i17;
        this.f995E = i18;
        this.f996F = printQuality;
        this.f997G = i19;
        this.f998H = vendorData;
        this.f999I = renderingIntent;
        this.f1000J = pageSizeName;
        this.f1001K = ((i10 * i7) + 7) / 8;
        this.f1002L = i10 / i9;
        this.f1003M = new C1671b2(i10 / 8, i7);
        if (vendorData.length > 1088) {
            throw new IllegalArgumentException("vendorData.size of " + vendorData.length + " must not be > 1088");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(C1677c2.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.hp.jipp.pdl.pwg.PwgHeader");
        }
        C1677c2 c1677c2 = (C1677c2) obj;
        return Intrinsics.areEqual(this.f1004a, c1677c2.f1004a) && Intrinsics.areEqual(this.f1005b, c1677c2.f1005b) && Intrinsics.areEqual(this.f1006c, c1677c2.f1006c) && this.f1007d == c1677c2.f1007d && this.f1008e == c1677c2.f1008e && this.f1009f == c1677c2.f1009f && this.f1010g == c1677c2.f1010g && this.f1011h == c1677c2.f1011h && this.f1012i == c1677c2.f1012i && this.f1013j == c1677c2.f1013j && this.f1014k == c1677c2.f1014k && this.f1015l == c1677c2.f1015l && this.f1016m == c1677c2.f1016m && this.f1017n == c1677c2.f1017n && this.f1018o == c1677c2.f1018o && this.f1019p == c1677c2.f1019p && this.f1020q == c1677c2.f1020q && this.f1021r == c1677c2.f1021r && this.f1022s == c1677c2.f1022s && this.f1023t == c1677c2.f1023t && this.f1024u == c1677c2.f1024u && this.f1025v == c1677c2.f1025v && this.f1026w == c1677c2.f1026w && this.f1027x == c1677c2.f1027x && this.f1028y == c1677c2.f1028y && this.f1029z == c1677c2.f1029z && this.f991A == c1677c2.f991A && this.f992B == c1677c2.f992B && this.f993C == c1677c2.f993C && this.f994D == c1677c2.f994D && this.f995E == c1677c2.f995E && this.f996F == c1677c2.f996F && this.f997G == c1677c2.f997G && Arrays.equals(this.f998H, c1677c2.f998H) && Intrinsics.areEqual(this.f999I, c1677c2.f999I) && Intrinsics.areEqual(this.f1000J, c1677c2.f1000J);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.f1004a.hashCode() * 31) + this.f1005b.hashCode()) * 31) + this.f1006c.hashCode()) * 31) + this.f1007d.hashCode()) * 31) + Boolean.hashCode(this.f1008e)) * 31) + this.f1009f) * 31) + this.f1010g) * 31) + Boolean.hashCode(this.f1011h)) * 31) + this.f1012i.hashCode()) * 31) + this.f1013j.hashCode()) * 31) + this.f1014k.hashCode()) * 31) + this.f1015l) * 31) + this.f1016m) * 31) + this.f1017n.hashCode()) * 31) + this.f1018o) * 31) + this.f1019p) * 31) + Boolean.hashCode(this.f1020q)) * 31) + this.f1021r) * 31) + this.f1022s) * 31) + this.f1023t) * 31) + this.f1024u) * 31) + this.f1025v.hashCode()) * 31) + this.f1026w.hashCode()) * 31) + this.f1027x) * 31) + this.f1028y) * 31) + this.f1029z) * 31) + this.f991A) * 31) + this.f992B) * 31) + this.f993C) * 31) + this.f994D) * 31) + this.f995E) * 31) + this.f996F.hashCode()) * 31) + this.f997G) * 31) + Arrays.hashCode(this.f998H)) * 31) + this.f999I.hashCode()) * 31) + this.f1000J.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PwgHeader(mediaColor=");
        sb.append(this.f1004a).append(", mediaType=").append(this.f1005b).append(", printContentOptimize=").append(this.f1006c).append(", cutMedia=").append(this.f1007d).append(", duplex=").append(this.f1008e).append(", hwResolutionX=").append(this.f1009f).append(", hwResolutionY=").append(this.f1010g).append(", insertSheet=").append(this.f1011h).append(", jog=").append(this.f1012i).append(", leadingEdge=").append(this.f1013j).append(", mediaPosition=").append(this.f1014k).append(", mediaWeightMetric=");
        sb.append(this.f1015l).append(", numCopies=").append(this.f1016m).append(", orientation=").append(this.f1017n).append(", pageSizeX=").append(this.f1018o).append(", pageSizeY=").append(this.f1019p).append(", tumble=").append(this.f1020q).append(", width=").append(this.f1021r).append(", height=").append(this.f1022s).append(", bitsPerColor=").append(this.f1023t).append(", bitsPerPixel=").append(this.f1024u).append(", colorOrder=").append(this.f1025v).append(", colorSpace=").append(this.f1026w);
        sb.append(", totalPageCount=").append(this.f1027x).append(", crossFeedTransform=").append(this.f1028y).append(", feedTransform=").append(this.f1029z).append(", imageBoxLeft=").append(this.f991A).append(", imageBoxTop=").append(this.f992B).append(", imageBoxRight=").append(this.f993C).append(", imageBoxBottom=").append(this.f994D).append(", alternatePrimary=").append(this.f995E).append(", printQuality=").append(this.f996F).append(", vendorIdentifier=").append(this.f997G).append(", vendorData=").append(Arrays.toString(this.f998H)).append(", renderingIntent=");
        sb.append(this.f999I).append(", pageSizeName=").append(this.f1000J).append(')');
        return sb.toString();
    }
}
