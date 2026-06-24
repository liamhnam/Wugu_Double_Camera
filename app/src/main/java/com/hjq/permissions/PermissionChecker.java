package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Build;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.p065v1.XmlPullParserException;

final class PermissionChecker {
    PermissionChecker() {
    }

    static boolean checkActivityStatus(Activity activity, boolean z) {
        if (activity == null) {
            if (z) {
                throw new IllegalArgumentException("The instance of the context must be an activity object");
            }
            return false;
        }
        if (activity.isFinishing()) {
            if (z) {
                throw new IllegalStateException("The activity has been finishing, please manually determine the status of the activity");
            }
            return false;
        }
        if (!activity.isDestroyed()) {
            return true;
        }
        if (z) {
            throw new IllegalStateException("The activity has been destroyed, please manually determine the status of the activity");
        }
        return false;
    }

    static boolean checkPermissionArgument(List<String> list, boolean z) {
        if (list == null || list.isEmpty()) {
            if (z) {
                throw new IllegalArgumentException("The requested permission cannot be empty");
            }
            return false;
        }
        if (Build.VERSION.SDK_INT <= 32 && z) {
            ArrayList arrayList = new ArrayList();
            Field[] declaredFields = Permission.class.getDeclaredFields();
            if (declaredFields.length == 0) {
                return true;
            }
            for (Field field : declaredFields) {
                if (String.class.equals(field.getType())) {
                    try {
                        arrayList.add((String) field.get(null));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (String str : list) {
                if (!PermissionUtils.containsPermission(arrayList, str)) {
                    throw new IllegalArgumentException("The " + str + " is not a dangerous permission or special permission, please do not apply dynamically");
                }
            }
        }
        return true;
    }

    static void checkMediaLocationPermission(Context context, List<String> list) {
        if (PermissionUtils.containsPermission(list, Permission.ACCESS_MEDIA_LOCATION)) {
            for (String str : list) {
                if (!PermissionUtils.equalsPermission(str, Permission.ACCESS_MEDIA_LOCATION) && !PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_IMAGES) && !PermissionUtils.equalsPermission(str, Permission.READ_EXTERNAL_STORAGE) && !PermissionUtils.equalsPermission(str, Permission.WRITE_EXTERNAL_STORAGE) && !PermissionUtils.equalsPermission(str, Permission.MANAGE_EXTERNAL_STORAGE)) {
                    throw new IllegalArgumentException("Because it includes access media location permissions, do not apply for permissions unrelated to access media location");
                }
            }
            if (AndroidVersion.getTargetSdkVersionCode(context) >= 33) {
                if (!PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) && !PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE)) {
                    throw new IllegalArgumentException("You must add android.permission.READ_MEDIA_IMAGES or android.permission.MANAGE_EXTERNAL_STORAGE rights to apply for android.permission.ACCESS_MEDIA_LOCATION rights");
                }
            } else if (!PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE) && !PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE)) {
                throw new IllegalArgumentException("You must add android.permission.READ_EXTERNAL_STORAGE or android.permission.MANAGE_EXTERNAL_STORAGE rights to apply for android.permission.ACCESS_MEDIA_LOCATION rights");
            }
        }
    }

    static void checkStoragePermission(Context context, List<String> list) {
        if (PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_VIDEO) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_AUDIO) || PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE) || PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE) || PermissionUtils.containsPermission(list, Permission.WRITE_EXTERNAL_STORAGE)) {
            if (AndroidVersion.getTargetSdkVersionCode(context) >= 33 && (PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE) || PermissionUtils.containsPermission(list, Permission.WRITE_EXTERNAL_STORAGE))) {
                throw new IllegalArgumentException("When targetSdkVersion >= 33 should use android.permission.READ_MEDIA_IMAGES, android.permission.READ_MEDIA_VIDEO, android.permission.READ_MEDIA_AUDIO instead of android.permission.READ_EXTERNAL_STORAGE, android.permission.WRITE_EXTERNAL_STORAGE");
            }
            if (PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) || PermissionUtils.containsPermission(list, Permission.ACCESS_MEDIA_LOCATION)) {
                return;
            }
            boolean zIsScopedStorage = PermissionUtils.isScopedStorage(context);
            XmlResourceParser androidManifest = PermissionUtils.parseAndroidManifest(context);
            if (androidManifest == null) {
                return;
            }
            while (true) {
                try {
                    try {
                        if (androidManifest.getEventType() == 2 && MimeTypes.BASE_TYPE_APPLICATION.equals(androidManifest.getName())) {
                            int targetSdkVersionCode = AndroidVersion.getTargetSdkVersionCode(context);
                            boolean attributeBooleanValue = androidManifest.getAttributeBooleanValue(PermissionUtils.getAndroidNamespace(), "requestLegacyExternalStorage", false);
                            if (targetSdkVersionCode >= 29 && !attributeBooleanValue && (PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE) || !zIsScopedStorage)) {
                                throw new IllegalStateException("Please register the android:requestLegacyExternalStorage=\"true\" attribute in the AndroidManifest.xml file, otherwise it will cause incompatibility with the old version");
                            }
                            if (targetSdkVersionCode >= 30 && !PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE) && !zIsScopedStorage) {
                                throw new IllegalArgumentException("The storage permission application is abnormal. If you have adapted the scope storage, please register the <meta-data android:name=\"ScopedStorage\" android:value=\"true\" /> attribute in the AndroidManifest.xml file. If there is no adaptation scope storage, please use android.permission.MANAGE_EXTERNAL_STORAGE to apply for permission");
                            }
                        } else if (androidManifest.next() == 1) {
                            break;
                        }
                    } finally {
                        androidManifest.close();
                    }
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void checkBodySensorsPermission(List<String> list) {
        if (PermissionUtils.containsPermission(list, Permission.BODY_SENSORS_BACKGROUND)) {
            if (PermissionUtils.containsPermission(list, Permission.BODY_SENSORS_BACKGROUND) && !PermissionUtils.containsPermission(list, Permission.BODY_SENSORS)) {
                throw new IllegalArgumentException("Applying for background sensor permissions must contain android.permission.BODY_SENSORS");
            }
            for (String str : list) {
                if (PermissionUtils.equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION)) {
                    throw new IllegalArgumentException("Applying for permissions android.permission.BODY_SENSORS_BACKGROUND and android.permission.ACCESS_BACKGROUND_LOCATION at the same time is not supported");
                }
                if (PermissionUtils.equalsPermission(str, Permission.ACCESS_MEDIA_LOCATION)) {
                    throw new IllegalArgumentException("Applying for permissions android.permission.BODY_SENSORS_BACKGROUND and android.permission.ACCESS_MEDIA_LOCATION at the same time is not supported");
                }
            }
        }
    }

    static void checkLocationPermission(Context context, List<String> list) {
        if (AndroidVersion.getTargetSdkVersionCode(context) >= 31 && PermissionUtils.containsPermission(list, Permission.ACCESS_FINE_LOCATION) && !PermissionUtils.containsPermission(list, Permission.ACCESS_COARSE_LOCATION)) {
            throw new IllegalArgumentException("If your app targets Android 12 or higher and requests the ACCESS_FINE_LOCATION runtime permission, you must also request the ACCESS_COARSE_LOCATION permission. You must include both permissions in a single runtime request.");
        }
        if (PermissionUtils.containsPermission(list, Permission.ACCESS_BACKGROUND_LOCATION)) {
            if (PermissionUtils.containsPermission(list, Permission.ACCESS_COARSE_LOCATION) && !PermissionUtils.containsPermission(list, Permission.ACCESS_FINE_LOCATION)) {
                throw new IllegalArgumentException("Applying for background positioning permissions must include android.permission.ACCESS_FINE_LOCATION");
            }
            for (String str : list) {
                if (!PermissionUtils.equalsPermission(str, Permission.ACCESS_FINE_LOCATION) && !PermissionUtils.equalsPermission(str, Permission.ACCESS_COARSE_LOCATION) && !PermissionUtils.equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION)) {
                    throw new IllegalArgumentException("Because it includes background location permissions, do not apply for permissions unrelated to location");
                }
            }
        }
    }

    static void checkTargetSdkVersion(Context context, List<String> list) {
        int i;
        if (PermissionUtils.containsPermission(list, Permission.POST_NOTIFICATIONS) || PermissionUtils.containsPermission(list, Permission.NEARBY_WIFI_DEVICES) || PermissionUtils.containsPermission(list, Permission.BODY_SENSORS_BACKGROUND) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_VIDEO) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_AUDIO)) {
            i = 33;
        } else if (PermissionUtils.containsPermission(list, Permission.BLUETOOTH_SCAN) || PermissionUtils.containsPermission(list, Permission.BLUETOOTH_CONNECT) || PermissionUtils.containsPermission(list, Permission.BLUETOOTH_ADVERTISE) || PermissionUtils.containsPermission(list, Permission.SCHEDULE_EXACT_ALARM)) {
            i = 31;
        } else if (PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE)) {
            i = 30;
        } else if (PermissionUtils.containsPermission(list, Permission.ACCESS_BACKGROUND_LOCATION) || PermissionUtils.containsPermission(list, Permission.ACTIVITY_RECOGNITION) || PermissionUtils.containsPermission(list, Permission.ACCESS_MEDIA_LOCATION)) {
            i = 29;
        } else if (PermissionUtils.containsPermission(list, Permission.ACCEPT_HANDOVER)) {
            i = 28;
        } else {
            i = (PermissionUtils.containsPermission(list, Permission.REQUEST_INSTALL_PACKAGES) || PermissionUtils.containsPermission(list, Permission.ANSWER_PHONE_CALLS) || PermissionUtils.containsPermission(list, Permission.READ_PHONE_NUMBERS)) ? 26 : 23;
        }
        if (AndroidVersion.getTargetSdkVersionCode(context) < i) {
            throw new RuntimeException("The targetSdkVersion SDK must be " + i + " or more, if you do not want to upgrade targetSdkVersion, please apply with the old permissions");
        }
    }

    static void checkManifestPermissions(Context context, List<String> list) {
        HashMap<String, Integer> manifestPermissions = PermissionUtils.getManifestPermissions(context);
        if (manifestPermissions.isEmpty()) {
            throw new IllegalStateException("No permissions are registered in the AndroidManifest.xml file");
        }
        int i = context.getApplicationInfo().minSdkVersion;
        for (String str : list) {
            if (!PermissionUtils.equalsPermission(str, Permission.NOTIFICATION_SERVICE) && !PermissionUtils.equalsPermission(str, Permission.BIND_NOTIFICATION_LISTENER_SERVICE) && !PermissionUtils.equalsPermission(str, Permission.BIND_VPN_SERVICE)) {
                if (PermissionUtils.equalsPermission(str, Permission.BODY_SENSORS_BACKGROUND)) {
                    checkManifestPermission(manifestPermissions, Permission.BODY_SENSORS, Integer.MAX_VALUE);
                }
                if (PermissionUtils.equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION)) {
                    if (AndroidVersion.getTargetSdkVersionCode(context) >= 31) {
                        checkManifestPermission(manifestPermissions, Permission.ACCESS_FINE_LOCATION, 30);
                        checkManifestPermission(manifestPermissions, Permission.ACCESS_COARSE_LOCATION, Integer.MAX_VALUE);
                    } else {
                        checkManifestPermission(manifestPermissions, Permission.ACCESS_FINE_LOCATION, Integer.MAX_VALUE);
                    }
                }
                if (i < 33) {
                    if (PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_IMAGES) || PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_VIDEO) || PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_AUDIO)) {
                        checkManifestPermission(manifestPermissions, Permission.READ_EXTERNAL_STORAGE, 32);
                    }
                    if (PermissionUtils.equalsPermission(str, Permission.NEARBY_WIFI_DEVICES)) {
                        checkManifestPermission(manifestPermissions, Permission.ACCESS_FINE_LOCATION, 32);
                    }
                }
                if (i < 31) {
                    if (PermissionUtils.equalsPermission(str, Permission.BLUETOOTH_SCAN)) {
                        checkManifestPermission(manifestPermissions, "android.permission.BLUETOOTH_ADMIN", 30);
                        checkManifestPermission(manifestPermissions, Permission.ACCESS_FINE_LOCATION, 30);
                    }
                    if (PermissionUtils.equalsPermission(str, Permission.BLUETOOTH_CONNECT)) {
                        checkManifestPermission(manifestPermissions, "android.permission.BLUETOOTH", 30);
                    }
                    if (PermissionUtils.equalsPermission(str, Permission.BLUETOOTH_ADVERTISE)) {
                        checkManifestPermission(manifestPermissions, "android.permission.BLUETOOTH_ADMIN", 30);
                    }
                }
                if (i < 30 && PermissionUtils.equalsPermission(str, Permission.MANAGE_EXTERNAL_STORAGE)) {
                    checkManifestPermission(manifestPermissions, Permission.READ_EXTERNAL_STORAGE, 29);
                    checkManifestPermission(manifestPermissions, Permission.WRITE_EXTERNAL_STORAGE, 29);
                }
                if (i < 29 && PermissionUtils.equalsPermission(str, Permission.ACTIVITY_RECOGNITION)) {
                    checkManifestPermission(manifestPermissions, Permission.BODY_SENSORS, 28);
                }
                if (i < 26 && PermissionUtils.equalsPermission(str, Permission.READ_PHONE_NUMBERS)) {
                    checkManifestPermission(manifestPermissions, Permission.READ_PHONE_STATE, 25);
                }
                checkManifestPermission(manifestPermissions, str, Integer.MAX_VALUE);
            }
        }
    }

    static void checkManifestPermission(HashMap<String, Integer> map, String str, int i) {
        if (!map.containsKey(str)) {
            throw new IllegalStateException("Please register permissions in the AndroidManifest.xml file <uses-permission android:name=\"" + str + "\" />");
        }
        Integer num = map.get(str);
        if (num != null && num.intValue() < i) {
            throw new IllegalArgumentException("The AndroidManifest.xml file <uses-permission android:name=\"" + str + "\" android:maxSdkVersion=\"" + num + "\" /> does not meet the requirements, " + (i != Integer.MAX_VALUE ? new StringBuilder("the minimum requirement for maxSdkVersion is ").append(i) : new StringBuilder("please delete the android:maxSdkVersion=\"").append(num).append("\" attribute")).toString());
        }
    }

    static void optimizeDeprecatedPermission(List<String> list) {
        if (!AndroidVersion.isAndroid13()) {
            if (PermissionUtils.containsPermission(list, Permission.POST_NOTIFICATIONS) && !PermissionUtils.containsPermission(list, Permission.NOTIFICATION_SERVICE)) {
                list.add(Permission.NOTIFICATION_SERVICE);
            }
            if (PermissionUtils.containsPermission(list, Permission.NEARBY_WIFI_DEVICES) && !PermissionUtils.containsPermission(list, Permission.ACCESS_FINE_LOCATION)) {
                list.add(Permission.ACCESS_FINE_LOCATION);
            }
            if ((PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_VIDEO) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_AUDIO)) && !PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE)) {
                list.add(Permission.READ_EXTERNAL_STORAGE);
            }
        }
        if (!AndroidVersion.isAndroid12() && PermissionUtils.containsPermission(list, Permission.BLUETOOTH_SCAN) && !PermissionUtils.containsPermission(list, Permission.ACCESS_FINE_LOCATION)) {
            list.add(Permission.ACCESS_FINE_LOCATION);
        }
        if (PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE)) {
            if (PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE) || PermissionUtils.containsPermission(list, Permission.WRITE_EXTERNAL_STORAGE)) {
                throw new IllegalArgumentException("If you have applied for MANAGE_EXTERNAL_STORAGE permissions, do not apply for the READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE permissions");
            }
            if (!AndroidVersion.isAndroid11()) {
                list.add(Permission.READ_EXTERNAL_STORAGE);
                list.add(Permission.WRITE_EXTERNAL_STORAGE);
            }
        }
        if (!AndroidVersion.isAndroid10() && PermissionUtils.containsPermission(list, Permission.ACTIVITY_RECOGNITION) && !PermissionUtils.containsPermission(list, Permission.BODY_SENSORS)) {
            list.add(Permission.BODY_SENSORS);
        }
        if (AndroidVersion.isAndroid8() || !PermissionUtils.containsPermission(list, Permission.READ_PHONE_NUMBERS) || PermissionUtils.containsPermission(list, Permission.READ_PHONE_STATE)) {
            return;
        }
        list.add(Permission.READ_PHONE_STATE);
    }
}
