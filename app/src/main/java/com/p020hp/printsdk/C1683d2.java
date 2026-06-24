package com.p020hp.printsdk;

import com.p020hp.jipp.model.MediaSource;
import com.p020hp.jipp.model.Orientation;
import com.p020hp.jipp.model.PwgRasterDocumentSheetBack;
import com.p020hp.jipp.model.Sides;
import com.p020hp.printsdk.C1677c2;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class C1683d2 {

    public static final a f1159g = new a();

    public final C1741o1 f1160a;

    public final String f1161b;

    public final Orientation f1162c;

    public final C1677c2.e f1163d;

    public final C1677c2.b f1164e;

    public final C1677c2.g f1165f;

    public static final class a {
        public static final C1677c2.e m488a(a aVar, String str) {
            int iHashCode = str.hashCode();
            switch (iHashCode) {
                case -2048782384:
                    if (str.equals("envelope")) {
                        return C1677c2.e.Envelope;
                    }
                    break;
                case -1408024454:
                    if (str.equals(MediaSource.alternate)) {
                        return C1677c2.e.Alternate;
                    }
                    break;
                case -1383228885:
                    if (str.equals("bottom")) {
                        return C1677c2.e.Bottom;
                    }
                    break;
                case -1364013995:
                    if (str.equals("center")) {
                        return C1677c2.e.Center;
                    }
                    break;
                case -1224788943:
                    if (str.equals(MediaSource.hagaki)) {
                        return C1677c2.e.Hagaki;
                    }
                    break;
                case -1081415738:
                    if (str.equals("manual")) {
                        return C1677c2.e.Manual;
                    }
                    break;
                case -1074341483:
                    if (str.equals("middle")) {
                        return C1677c2.e.Middle;
                    }
                    break;
                case -1066801099:
                    if (str.equals(MediaSource.tray20)) {
                        return C1677c2.e.Tray20;
                    }
                    break;
                case -297252847:
                    if (str.equals(MediaSource.mainRoll)) {
                        return C1677c2.e.MainRoll;
                    }
                    break;
                case -254566928:
                    if (str.equals(MediaSource.alternateRoll)) {
                        return C1677c2.e.AlternateRoll;
                    }
                    break;
                case 115029:
                    if (str.equals("top")) {
                        return C1677c2.e.Top;
                    }
                    break;
                case 3005871:
                    if (str.equals("auto")) {
                        return C1677c2.e.Auto;
                    }
                    break;
                case 3083669:
                    if (str.equals("disc")) {
                        return C1677c2.e.Disc;
                    }
                    break;
                case 3317767:
                    if (str.equals("left")) {
                        return C1677c2.e.Left;
                    }
                    break;
                case 3343801:
                    if (str.equals("main")) {
                        return C1677c2.e.Main;
                    }
                    break;
                case 3496356:
                    if (str.equals("rear")) {
                        return C1677c2.e.Rear;
                    }
                    break;
                case 3530071:
                    if (str.equals("side")) {
                        return C1677c2.e.Side;
                    }
                    break;
                case 106642994:
                    if (str.equals("photo")) {
                        return C1677c2.e.Photo;
                    }
                    break;
                case 108511772:
                    if (str.equals("right")) {
                        return C1677c2.e.Right;
                    }
                    break;
                case 127910268:
                    if (str.equals(MediaSource.byPassTray)) {
                        return C1677c2.e.ByPassTray;
                    }
                    break;
                case 1377042799:
                    if (str.equals(MediaSource.roll10)) {
                        return C1677c2.e.Roll10;
                    }
                    break;
                case 1800163084:
                    if (str.equals("large-capacity")) {
                        return C1677c2.e.LargeCapacity;
                    }
                    break;
                default:
                    switch (iHashCode) {
                        case -1066801130:
                            if (str.equals("tray-10")) {
                                return C1677c2.e.Tray10;
                            }
                            break;
                        case -1066801129:
                            if (str.equals(MediaSource.tray11)) {
                                return C1677c2.e.Tray11;
                            }
                            break;
                        case -1066801128:
                            if (str.equals(MediaSource.tray12)) {
                                return C1677c2.e.Tray12;
                            }
                            break;
                        case -1066801127:
                            if (str.equals(MediaSource.tray13)) {
                                return C1677c2.e.Tray13;
                            }
                            break;
                        case -1066801126:
                            if (str.equals(MediaSource.tray14)) {
                                return C1677c2.e.Tray14;
                            }
                            break;
                        case -1066801125:
                            if (str.equals(MediaSource.tray15)) {
                                return C1677c2.e.Tray15;
                            }
                            break;
                        case -1066801124:
                            if (str.equals(MediaSource.tray16)) {
                                return C1677c2.e.Tray16;
                            }
                            break;
                        case -1066801123:
                            if (str.equals(MediaSource.tray17)) {
                                return C1677c2.e.Tray17;
                            }
                            break;
                        case -1066801122:
                            if (str.equals(MediaSource.tray18)) {
                                return C1677c2.e.Tray18;
                            }
                            break;
                        case -1066801121:
                            if (str.equals(MediaSource.tray19)) {
                                return C1677c2.e.Tray19;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case -925410591:
                                    if (str.equals(MediaSource.roll1)) {
                                        return C1677c2.e.Roll1;
                                    }
                                    break;
                                case -925410590:
                                    if (str.equals(MediaSource.roll2)) {
                                        return C1677c2.e.Roll2;
                                    }
                                    break;
                                case -925410589:
                                    if (str.equals(MediaSource.roll3)) {
                                        return C1677c2.e.Roll3;
                                    }
                                    break;
                                case -925410588:
                                    if (str.equals(MediaSource.roll4)) {
                                        return C1677c2.e.Roll4;
                                    }
                                    break;
                                case -925410587:
                                    if (str.equals(MediaSource.roll5)) {
                                        return C1677c2.e.Roll5;
                                    }
                                    break;
                                case -925410586:
                                    if (str.equals(MediaSource.roll6)) {
                                        return C1677c2.e.Roll6;
                                    }
                                    break;
                                case -925410585:
                                    if (str.equals(MediaSource.roll7)) {
                                        return C1677c2.e.Roll7;
                                    }
                                    break;
                                case -925410584:
                                    if (str.equals(MediaSource.roll8)) {
                                        return C1677c2.e.Roll8;
                                    }
                                    break;
                                case -925410583:
                                    if (str.equals(MediaSource.roll9)) {
                                        return C1677c2.e.Roll9;
                                    }
                                    break;
                                default:
                                    switch (iHashCode) {
                                        case -865696934:
                                            if (str.equals("tray-1")) {
                                                return C1677c2.e.Tray1;
                                            }
                                            break;
                                        case -865696933:
                                            if (str.equals("tray-2")) {
                                                return C1677c2.e.Tray2;
                                            }
                                            break;
                                        case -865696932:
                                            if (str.equals("tray-3")) {
                                                return C1677c2.e.Tray3;
                                            }
                                            break;
                                        case -865696931:
                                            if (str.equals("tray-4")) {
                                                return C1677c2.e.Tray4;
                                            }
                                            break;
                                        case -865696930:
                                            if (str.equals("tray-5")) {
                                                return C1677c2.e.Tray5;
                                            }
                                            break;
                                        case -865696929:
                                            if (str.equals("tray-6")) {
                                                return C1677c2.e.Tray6;
                                            }
                                            break;
                                        case -865696928:
                                            if (str.equals("tray-7")) {
                                                return C1677c2.e.Tray7;
                                            }
                                            break;
                                        case -865696927:
                                            if (str.equals("tray-8")) {
                                                return C1677c2.e.Tray8;
                                            }
                                            break;
                                        case -865696926:
                                            if (str.equals("tray-9")) {
                                                return C1677c2.e.Tray9;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
            throw new IllegalArgumentException(str + " is not a recognized media source type");
        }
    }

    public static final class b {

        public static final a f1166c = new a();

        public static final Map<Pair<String, String>, b> f1167d = MapsKt.mapOf(TuplesKt.m1300to(TuplesKt.m1300to(Sides.twoSidedLongEdge, "flipped"), new b(1, -1)), TuplesKt.m1300to(TuplesKt.m1300to(Sides.twoSidedLongEdge, "rotated"), new b(-1, -1)), TuplesKt.m1300to(TuplesKt.m1300to(Sides.twoSidedShortEdge, "flipped"), new b(-1, 1)), TuplesKt.m1300to(TuplesKt.m1300to(Sides.twoSidedShortEdge, PwgRasterDocumentSheetBack.manualTumble), new b(-1, -1)));

        public static final b f1168e = new b(1, 1);

        public final int f1169a;

        public final int f1170b;

        public static final class a {
        }

        public b(int i, int i2) {
            this.f1169a = i;
            this.f1170b = i2;
        }
    }

    public C1683d2() {
        this(null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 7);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public C1683d2(com.p020hp.printsdk.C1741o1 r2, java.lang.String r3, com.p020hp.jipp.model.Orientation r4) throws java.io.IOException {
        /*
            r1 = this;
            java.lang.String r0 = "output"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "sheetBack"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "orientation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r1.<init>()
            r1.f1160a = r2
            r1.f1161b = r3
            r1.f1162c = r4
            com.hp.printsdk.d2$a r3 = com.p020hp.printsdk.C1683d2.f1159g
            java.lang.String r4 = r2.m606c()
            com.hp.printsdk.c2$e r3 = com.p020hp.printsdk.C1683d2.a.m488a(r3, r4)
            r1.f1163d = r3
            com.hp.printsdk.j1 r3 = r2.m604a()
            java.lang.String r4 = "from"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
            int r4 = r3.ordinal()
            r0 = 1
            if (r4 == r0) goto L50
            r0 = 2
            if (r4 != r0) goto L3b
            com.hp.printsdk.c2$b r3 = com.p020hp.printsdk.C1677c2.b.Sgray
            goto L52
        L3b:
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "No conversion available for "
            r4.<init>(r0)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L50:
            com.hp.printsdk.c2$b r3 = com.p020hp.printsdk.C1677c2.b.Srgb
        L52:
            r1.f1164e = r3
            com.hp.jipp.model.PrintQuality r2 = r2.m605b()
            if (r2 == 0) goto L7e
            com.hp.jipp.model.PrintQuality r3 = com.p020hp.jipp.model.PrintQuality.draft
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r3 == 0) goto L65
            com.hp.printsdk.c2$g r2 = com.p020hp.printsdk.C1677c2.g.Draft
            goto L7c
        L65:
            com.hp.jipp.model.PrintQuality r3 = com.p020hp.jipp.model.PrintQuality.normal
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r3 == 0) goto L70
            com.hp.printsdk.c2$g r2 = com.p020hp.printsdk.C1677c2.g.Normal
            goto L7c
        L70:
            com.hp.jipp.model.PrintQuality r3 = com.p020hp.jipp.model.PrintQuality.high
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 == 0) goto L7b
            com.hp.printsdk.c2$g r2 = com.p020hp.printsdk.C1677c2.g.High
            goto L7c
        L7b:
            r2 = 0
        L7c:
            if (r2 != 0) goto L80
        L7e:
            com.hp.printsdk.c2$g r2 = com.p020hp.printsdk.C1677c2.g.Default
        L80:
            r1.f1165f = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1683d2.<init>(com.hp.printsdk.o1, java.lang.String, com.hp.jipp.model.Orientation):void");
    }

    public C1683d2(C1741o1 c1741o1, String str, Orientation orientation, int i) {
        this((i & 1) != 0 ? new C1741o1(null, null, null, null, false, 0, 63) : null, (i & 2) != 0 ? "normal" : null, (i & 4) != 0 ? Orientation.portrait : null);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.p020hp.printsdk.C1677c2 m487a(com.p020hp.printsdk.AbstractC1755r1 r42, com.p020hp.printsdk.AbstractC1760s1 r43, int r44) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1683d2.m487a(com.hp.printsdk.r1, com.hp.printsdk.s1, int):com.hp.printsdk.c2");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1683d2)) {
            return false;
        }
        C1683d2 c1683d2 = (C1683d2) obj;
        return Intrinsics.areEqual(this.f1160a, c1683d2.f1160a) && Intrinsics.areEqual(this.f1161b, c1683d2.f1161b) && Intrinsics.areEqual(this.f1162c, c1683d2.f1162c);
    }

    public int hashCode() {
        return (((this.f1160a.hashCode() * 31) + this.f1161b.hashCode()) * 31) + this.f1162c.hashCode();
    }

    public String toString() {
        return "PwgSettings(output=" + this.f1160a + ", sheetBack=" + this.f1161b + ", orientation=" + this.f1162c + ')';
    }
}
