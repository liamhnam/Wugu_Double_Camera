package com.hiti.usb.service.network;

import android.content.Context;
import android.util.Pair;
import com.hiti.usb.utility.FileUtility;
import hiti.p035a.C2093a;
import hiti.p035a.EnumC2094b;
import p000a.p001a.p002a.p006d.C0014e;

public class FirmwareUtility {
    public static final int FROM_ASSET = 0;
    public static final int FROM_SD = 1;
    private static final boolean localLOG = false;
    private static final String tag = "FirmwareUtility";

    static class C15951 {
        static final int[] $SwitchMap$com$hiti$usb$app$ProductID;

        static {
            int[] iArr = new int[EnumC2094b.values().length];
            $SwitchMap$com$hiti$usb$app$ProductID = iArr;
            try {
                iArr[EnumC2094b.P310W.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hiti$usb$app$ProductID[EnumC2094b.P520L.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hiti$usb$app$ProductID[EnumC2094b.P525N.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hiti$usb$app$ProductID[EnumC2094b.P750L.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void deleteSDFW(Context context, EnumC2094b enumC2094b) {
        C0014e c0014e = new C0014e(context);
        c0014e.m62c();
        c0014e.m61a(enumC2094b, "");
        c0014e.m63d();
        FileUtility.DeleteFile(C2093a.m1272a(context, enumC2094b));
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getAppFWVersion(android.content.Context r6, hiti.p035a.EnumC2094b r7, boolean r8) {
        /*
            com.hiti.usb.utility.ResourceSearcher$RS_TYPE r0 = com.hiti.usb.utility.ResourceSearcher.RS_TYPE.STRING
            java.lang.String r1 = "version"
            int r0 = com.hiti.usb.utility.ResourceSearcher.getId(r6, r0, r1)
            com.hiti.usb.utility.ResourceSearcher$RS_TYPE r1 = com.hiti.usb.utility.ResourceSearcher.RS_TYPE.STRING
            java.lang.String r2 = "version_p520l"
            int r1 = com.hiti.usb.utility.ResourceSearcher.getId(r6, r1, r2)
            com.hiti.usb.utility.ResourceSearcher$RS_TYPE r2 = com.hiti.usb.utility.ResourceSearcher.RS_TYPE.STRING
            java.lang.String r3 = "version_p525n"
            int r2 = com.hiti.usb.utility.ResourceSearcher.getId(r6, r2, r3)
            com.hiti.usb.utility.ResourceSearcher$RS_TYPE r3 = com.hiti.usb.utility.ResourceSearcher.RS_TYPE.STRING
            java.lang.String r4 = "version_p310w"
            int r3 = com.hiti.usb.utility.ResourceSearcher.getId(r6, r3, r4)
            com.hiti.usb.utility.ResourceSearcher$RS_TYPE r4 = com.hiti.usb.utility.ResourceSearcher.RS_TYPE.STRING
            java.lang.String r5 = "version_p750l"
            int r4 = com.hiti.usb.utility.ResourceSearcher.getId(r6, r4, r5)
            int[] r5 = com.hiti.usb.service.network.FirmwareUtility.C15951.$SwitchMap$com$hiti$usb$app$ProductID
            int r7 = r7.ordinal()
            r7 = r5[r7]
            r5 = 1
            if (r7 == r5) goto L5d
            r3 = 2
            if (r7 == r3) goto L56
            r1 = 3
            if (r7 == r1) goto L4f
            r1 = 4
            if (r7 == r1) goto L48
            if (r0 == 0) goto L64
            java.lang.String r6 = r6.getString(r0)
            goto L66
        L48:
            if (r4 == 0) goto L64
            java.lang.String r6 = r6.getString(r4)
            goto L66
        L4f:
            if (r2 == 0) goto L64
            java.lang.String r6 = r6.getString(r2)
            goto L66
        L56:
            if (r1 == 0) goto L64
            java.lang.String r6 = r6.getString(r1)
            goto L66
        L5d:
            if (r3 == 0) goto L64
            java.lang.String r6 = r6.getString(r3)
            goto L66
        L64:
            java.lang.String r6 = "0"
        L66:
            if (r8 == 0) goto L6c
            java.lang.String r6 = simpleFwFormat(r6)
        L6c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hiti.usb.service.network.FirmwareUtility.getAppFWVersion(android.content.Context, hiti.a.b, boolean):java.lang.String");
    }

    public static Pair<Integer, String> getCurrentFWVersion(Context context, EnumC2094b enumC2094b, boolean z) {
        int i = 0;
        String appFWVersion = getAppFWVersion(context, enumC2094b, false);
        String sdFwVersion = getSdFwVersion(context, enumC2094b, false);
        if (sdFwVersion != null && Integer.parseInt(simpleFwFormat(sdFwVersion)) > Integer.parseInt(simpleFwFormat(appFWVersion))) {
            i = 1;
            appFWVersion = sdFwVersion;
        }
        if (z) {
            appFWVersion = simpleFwFormat(appFWVersion);
        }
        return new Pair<>(Integer.valueOf(i), appFWVersion);
    }

    public static String getSdFwVersion(Context context, EnumC2094b enumC2094b, boolean z) {
        C0014e c0014e = new C0014e(context);
        c0014e.m62c();
        String str = null;
        String strM60a = c0014e.m60a(enumC2094b).length() > 0 ? c0014e.m60a(enumC2094b) : null;
        if (FileUtility.GetFileSize(C2093a.m1272a(context, enumC2094b)) != 8388608) {
            c0014e.m61a(enumC2094b, "");
            c0014e.m63d();
        } else {
            str = strM60a;
        }
        return (str == null || !z) ? str : simpleFwFormat(str);
    }

    public static String simpleFwFormat(String str) {
        String strReplace = str.replace(".", "");
        return strReplace.length() >= 4 ? strReplace.substring(0, 4) : strReplace;
    }
}
