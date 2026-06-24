package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.p065v1.XmlPullParserException;

final class PermissionUtils {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    static String getAndroidNamespace() {
        return "http://schemas.android.com/apk/res/android";
    }

    PermissionUtils() {
    }

    public static boolean isSpecialPermission(String str) {
        return equalsPermission(str, Permission.MANAGE_EXTERNAL_STORAGE) || equalsPermission(str, Permission.REQUEST_INSTALL_PACKAGES) || equalsPermission(str, Permission.SYSTEM_ALERT_WINDOW) || equalsPermission(str, Permission.WRITE_SETTINGS) || equalsPermission(str, Permission.NOTIFICATION_SERVICE) || equalsPermission(str, Permission.PACKAGE_USAGE_STATS) || equalsPermission(str, Permission.SCHEDULE_EXACT_ALARM) || equalsPermission(str, Permission.BIND_NOTIFICATION_LISTENER_SERVICE) || equalsPermission(str, Permission.ACCESS_NOTIFICATION_POLICY) || equalsPermission(str, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) || equalsPermission(str, Permission.BIND_VPN_SERVICE);
    }

    public static boolean checkSelfPermission(Context context, String str) {
        return context.checkSelfPermission(str) == 0;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        if (AndroidVersion.getAndroidVersionCode() == 31) {
            try {
                return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", String.class).invoke(activity.getApplication().getPackageManager(), str)).booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return activity.shouldShowRequestPermissionRationale(str);
    }

    static void postDelayed(Runnable runnable, long j) {
        HANDLER.postDelayed(runnable, j);
    }

    static void postActivityResult(List<String> list, Runnable runnable) {
        long j = 300;
        long j2 = AndroidVersion.isAndroid11() ? 200L : 300L;
        String lowerCase = Build.MANUFACTURER.toLowerCase();
        if (lowerCase.contains("huawei")) {
            if (!AndroidVersion.isAndroid8()) {
                j = 500;
            }
        } else {
            j = (lowerCase.contains("xiaomi") && AndroidVersion.isAndroid11() && containsPermission(list, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)) ? 1000L : j2;
        }
        HANDLER.postDelayed(runnable, j);
    }

    static boolean isDebugMode(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static HashMap<String, Integer> getManifestPermissions(Context context) {
        HashMap<String, Integer> map = new HashMap<>();
        XmlResourceParser androidManifest = parseAndroidManifest(context);
        if (androidManifest != null) {
            do {
                try {
                    try {
                        if (androidManifest.getEventType() == 2 && "uses-permission".equals(androidManifest.getName())) {
                            map.put(androidManifest.getAttributeValue(getAndroidNamespace(), NamingTable.TAG), Integer.valueOf(androidManifest.getAttributeIntValue(getAndroidNamespace(), "maxSdkVersion", Integer.MAX_VALUE)));
                        }
                    } finally {
                        androidManifest.close();
                    }
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                }
            } while (androidManifest.next() != 1);
        }
        if (map.isEmpty()) {
            try {
                String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                if (strArr != null) {
                    for (String str : strArr) {
                        map.put(str, Integer.MAX_VALUE);
                    }
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return map;
    }

    static void optimizePermissionResults(Activity activity, String[] strArr, int[] iArr) {
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            boolean zIsSpecialPermission = PermissionApi.isSpecialPermission(str);
            boolean z = true;
            if (!AndroidVersion.isAndroid13() && (equalsPermission(str, Permission.POST_NOTIFICATIONS) || equalsPermission(str, Permission.NEARBY_WIFI_DEVICES) || equalsPermission(str, Permission.BODY_SENSORS_BACKGROUND) || equalsPermission(str, Permission.READ_MEDIA_IMAGES) || equalsPermission(str, Permission.READ_MEDIA_VIDEO) || equalsPermission(str, Permission.READ_MEDIA_AUDIO))) {
                zIsSpecialPermission = true;
            }
            if (!AndroidVersion.isAndroid12() && (equalsPermission(str, Permission.BLUETOOTH_SCAN) || equalsPermission(str, Permission.BLUETOOTH_CONNECT) || equalsPermission(str, Permission.BLUETOOTH_ADVERTISE))) {
                zIsSpecialPermission = true;
            }
            if (!AndroidVersion.isAndroid10() && (equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION) || equalsPermission(str, Permission.ACTIVITY_RECOGNITION) || equalsPermission(str, Permission.ACCESS_MEDIA_LOCATION))) {
                zIsSpecialPermission = true;
            }
            if (!AndroidVersion.isAndroid9() && equalsPermission(str, Permission.ACCEPT_HANDOVER)) {
                zIsSpecialPermission = true;
            }
            if (AndroidVersion.isAndroid8() || (!equalsPermission(str, Permission.ANSWER_PHONE_CALLS) && !equalsPermission(str, Permission.READ_PHONE_NUMBERS))) {
                z = zIsSpecialPermission;
            }
            if (z) {
                iArr[i] = PermissionApi.isGrantedPermission(activity, str) ? 0 : -1;
            }
        }
    }

    static <T> ArrayList<T> asArrayList(T... tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        if (tArr != null && tArr.length != 0) {
            for (T t : tArr) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @SafeVarargs
    static <T> ArrayList<T> asArrayLists(T[]... tArr) {
        ArrayList<T> arrayList = new ArrayList<>();
        if (tArr != null && tArr.length != 0) {
            for (T[] tArr2 : tArr) {
                arrayList.addAll(asArrayList(tArr2));
            }
        }
        return arrayList;
    }

    static Activity findActivity(Context context) {
        while (!(context instanceof Activity)) {
            if (!(context instanceof ContextWrapper) || (context = ((ContextWrapper) context).getBaseContext()) == null) {
                return null;
            }
        }
        return (Activity) context;
    }

    static int findApkPathCookie(Context context, String str) {
        AssetManager assets = context.getAssets();
        try {
            if (AndroidVersion.getTargetSdkVersionCode(context) >= 28 && AndroidVersion.getAndroidVersionCode() >= 28 && AndroidVersion.getAndroidVersionCode() < 30) {
                Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
                declaredMethod.setAccessible(true);
                Method method = (Method) declaredMethod.invoke(AssetManager.class, "findCookieForPath", new Class[]{String.class});
                if (method != null) {
                    method.setAccessible(true);
                    Integer num = (Integer) method.invoke(context.getAssets(), str);
                    if (num != null) {
                        return num.intValue();
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
        try {
            Integer num2 = (Integer) assets.getClass().getDeclaredMethod("addAssetPath", String.class).invoke(assets, str);
            if (num2 != null) {
                return num2.intValue();
            }
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
        }
        return 0;
    }

    static XmlResourceParser parseAndroidManifest(Context context) {
        int iFindApkPathCookie = findApkPathCookie(context, context.getApplicationInfo().sourceDir);
        if (iFindApkPathCookie == 0) {
            return null;
        }
        try {
            XmlResourceParser xmlResourceParserOpenXmlResourceParser = context.getAssets().openXmlResourceParser(iFindApkPathCookie, "AndroidManifest.xml");
            do {
                if (xmlResourceParserOpenXmlResourceParser.getEventType() == 2 && "manifest".equals(xmlResourceParserOpenXmlResourceParser.getName()) && TextUtils.equals(context.getPackageName(), xmlResourceParserOpenXmlResourceParser.getAttributeValue(null, "package"))) {
                    return xmlResourceParserOpenXmlResourceParser;
                }
            } while (xmlResourceParserOpenXmlResourceParser.next() != 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    static boolean isScopedStorage(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null || !bundle.containsKey("ScopedStorage")) {
                return false;
            }
            return Boolean.parseBoolean(String.valueOf(bundle.get("ScopedStorage")));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void lockActivityOrientation(Activity activity) {
        try {
            int i = activity.getResources().getConfiguration().orientation;
            if (i == 1) {
                activity.setRequestedOrientation(isActivityReverse(activity) ? 9 : 1);
            } else if (i == 2) {
                activity.setRequestedOrientation(isActivityReverse(activity) ? 8 : 0);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    static boolean isActivityReverse(Activity activity) {
        int rotation;
        if (AndroidVersion.isAndroid11()) {
            rotation = activity.getDisplay().getRotation();
        } else {
            rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        }
        return rotation == 2 || rotation == 3;
    }

    static boolean areActivityIntent(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, 65536).isEmpty();
    }

    public static Intent getApplicationDetailsIntent(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(getPackageNameUri(context));
        if (areActivityIntent(context, intent)) {
            return intent;
        }
        Intent intent2 = new Intent("android.settings.APPLICATION_SETTINGS");
        return !areActivityIntent(context, intent2) ? new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS") : intent2;
    }

    public static Uri getPackageNameUri(Context context) {
        return Uri.parse("package:" + context.getPackageName());
    }

    static Intent getSmartPermissionIntent(Context context, List<String> list) {
        if (list == null || list.isEmpty() || !PermissionApi.containsSpecialPermission(list)) {
            return getApplicationDetailsIntent(context);
        }
        int size = list.size();
        if (size == 1) {
            return PermissionApi.getPermissionIntent(context, list.get(0));
        }
        if (size == 2) {
            if (!AndroidVersion.isAndroid13() && containsPermission(list, Permission.NOTIFICATION_SERVICE) && containsPermission(list, Permission.POST_NOTIFICATIONS)) {
                return PermissionApi.getPermissionIntent(context, Permission.NOTIFICATION_SERVICE);
            }
        } else if (size == 3 && AndroidVersion.isAndroid11() && containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE) && containsPermission(list, Permission.READ_EXTERNAL_STORAGE) && containsPermission(list, Permission.WRITE_EXTERNAL_STORAGE)) {
            return PermissionApi.getPermissionIntent(context, Permission.MANAGE_EXTERNAL_STORAGE);
        }
        return getApplicationDetailsIntent(context);
    }

    static boolean equalsPermission(String str, String str2) {
        int length;
        if (str == null || str2 == null || (length = str.length()) != str2.length()) {
            return false;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean containsPermission(Collection<String> collection, String str) {
        if (collection != null && !collection.isEmpty() && str != null) {
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                if (equalsPermission(it.next(), str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
