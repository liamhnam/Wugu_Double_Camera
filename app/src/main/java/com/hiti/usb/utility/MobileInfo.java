package com.hiti.usb.utility;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.hjq.permissions.Permission;
import com.p020hp.jipp.encoding.IppPacket;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MobileInfo {
    public static String GetDateStamp() {
        return new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date(System.currentTimeMillis()));
    }

    public static String GetHmsSStamp() {
        return new SimpleDateFormat("HHmmssSSS", Locale.US).format(new Date(System.currentTimeMillis()));
    }

    public static String GetIMEI(Context context) {
        String str = Build.SERIAL;
        if (str == null) {
            str = "9999900000";
        }
        Log.i("strIMEI", str);
        return str;
    }

    public static Location GetLocation(Context context, boolean z) {
        String bestProvider;
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        Location lastKnownLocation = ((locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("network")) && (bestProvider = locationManager.getBestProvider(new Criteria(), true)) != null && ActivityCompat.checkSelfPermission(context, Permission.ACCESS_COARSE_LOCATION) == 0) ? locationManager.getLastKnownLocation(bestProvider) : null;
        if (z) {
            return null;
        }
        return lastKnownLocation;
    }

    public static String GetMapAppServerCountry() {
        return Locale.getDefault().getCountry().contains("AU") ? "en-au" : Locale.getDefault().getCountry().contains("CN") ? "cn" : Locale.getDefault().getCountry().contains("TW") ? "tw" : Locale.getDefault().getCountry().contains("HK") ? "hk" : Locale.getDefault().getCountry().contains("JP") ? "jp" : Locale.getDefault().getCountry().contains("RU") ? "ru" : Locale.getDefault().getCountry().contains("KR") ? "kr" : Locale.getDefault().getCountry().contains("US") ? IppPacket.DEFAULT_LANGUAGE : "en";
    }

    public static String GetMapAppServerLanguage() {
        if (Locale.getDefault().getLanguage().contains("zh")) {
            return Locale.getDefault().getCountry().contains("CN") ? "cn" : (Locale.getDefault().getCountry().contains("TW") || Locale.getDefault().getCountry().contains("HK")) ? "tw" : "en";
        }
        if (Locale.getDefault().getLanguage().contains("en")) {
            return "en";
        }
        String str = "ja";
        if (!Locale.getDefault().getLanguage().contains("ja")) {
            str = "ko";
            if (!Locale.getDefault().getLanguage().contains("ko")) {
                str = "fr";
                if (!Locale.getDefault().getLanguage().contains("fr")) {
                    str = "es";
                    if (!Locale.getDefault().getLanguage().contains("es")) {
                        str = "ru";
                        if (!Locale.getDefault().getLanguage().contains("ru")) {
                            str = "pt";
                            if (!Locale.getDefault().getLanguage().contains("pt")) {
                                str = "de";
                                if (!Locale.getDefault().getLanguage().contains("de")) {
                                    str = "it";
                                    if (!Locale.getDefault().getLanguage().contains("it")) {
                                        str = "ar";
                                        if (!Locale.getDefault().getLanguage().contains("ar")) {
                                            return "en";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return str;
    }

    public static String GetTimeReocrd() {
        return new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis()));
    }

    public static String GetTimeStamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date(System.currentTimeMillis()));
    }

    public static String MakeRandString(int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            str = str + String.valueOf((int) (Math.random() * 10.0d));
        }
        return str;
    }

    public static String MakeRandStringNoZeroPrefix(int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            str = str + String.valueOf((int) (Math.random() * 10.0d));
        }
        String strSubstring = str.substring(0, 1);
        if (strSubstring.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)) {
            strSubstring = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        return strSubstring + str.substring(1, i);
    }
}
