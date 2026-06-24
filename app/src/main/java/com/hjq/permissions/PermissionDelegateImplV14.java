package com.hjq.permissions;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import androidx.core.app.NotificationManagerCompat;

class PermissionDelegateImplV14 implements PermissionDelegate {
    @Override
    public boolean isPermissionPermanentDenied(Activity activity, String str) {
        return false;
    }

    PermissionDelegateImplV14() {
    }

    @Override
    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.NOTIFICATION_SERVICE)) {
            return isGrantedNotifyPermission(context);
        }
        if (PermissionUtils.equalsPermission(str, Permission.PACKAGE_USAGE_STATS)) {
            return isGrantedPackagePermission(context);
        }
        if (PermissionUtils.equalsPermission(str, Permission.BIND_NOTIFICATION_LISTENER_SERVICE)) {
            return isGrantedNotificationListenerPermission(context);
        }
        if (PermissionUtils.equalsPermission(str, Permission.BIND_VPN_SERVICE)) {
            return isGrantedVpnPermission(context);
        }
        if (AndroidVersion.isAndroid13() || !PermissionUtils.equalsPermission(str, Permission.POST_NOTIFICATIONS)) {
            return true;
        }
        return isGrantedNotifyPermission(context);
    }

    @Override
    public Intent getPermissionIntent(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.NOTIFICATION_SERVICE)) {
            return getNotifyPermissionIntent(context);
        }
        if (PermissionUtils.equalsPermission(str, Permission.PACKAGE_USAGE_STATS)) {
            return getPackagePermissionIntent(context);
        }
        if (PermissionUtils.equalsPermission(str, Permission.BIND_NOTIFICATION_LISTENER_SERVICE)) {
            return getNotificationListenerIntent(context);
        }
        if (PermissionUtils.equalsPermission(str, Permission.BIND_VPN_SERVICE)) {
            return getVpnPermissionIntent(context);
        }
        if (!AndroidVersion.isAndroid13() && PermissionUtils.equalsPermission(str, Permission.POST_NOTIFICATIONS)) {
            return getNotifyPermissionIntent(context);
        }
        return PermissionUtils.getApplicationDetailsIntent(context);
    }

    private static boolean isGrantedNotifyPermission(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    private static Intent getNotifyPermissionIntent(Context context) {
        Intent intent;
        if (AndroidVersion.isAndroid8()) {
            intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        } else {
            intent = null;
        }
        return (intent == null || !PermissionUtils.areActivityIntent(context, intent)) ? PermissionUtils.getApplicationDetailsIntent(context) : intent;
    }

    private static boolean isGrantedNotificationListenerPermission(Context context) {
        if (AndroidVersion.isAndroid4_3()) {
            return NotificationManagerCompat.getEnabledListenerPackages(context).contains(context.getPackageName());
        }
        return true;
    }

    private static Intent getNotificationListenerIntent(Context context) {
        Intent intent;
        if (AndroidVersion.isAndroid5_1()) {
            intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        } else {
            intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        }
        return !PermissionUtils.areActivityIntent(context, intent) ? PermissionUtils.getApplicationDetailsIntent(context) : intent;
    }

    private static boolean isGrantedPackagePermission(Context context) {
        int iCheckOpNoThrow;
        if (!AndroidVersion.isAndroid5()) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        if (AndroidVersion.isAndroid10()) {
            iCheckOpNoThrow = appOpsManager.unsafeCheckOpNoThrow("android:get_usage_stats", context.getApplicationInfo().uid, context.getPackageName());
        } else {
            iCheckOpNoThrow = appOpsManager.checkOpNoThrow("android:get_usage_stats", context.getApplicationInfo().uid, context.getPackageName());
        }
        return iCheckOpNoThrow == 0;
    }

    private static Intent getPackagePermissionIntent(Context context) {
        Intent intent;
        if (AndroidVersion.isAndroid5()) {
            intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            if (AndroidVersion.isAndroid10()) {
                intent.setData(PermissionUtils.getPackageNameUri(context));
            }
        } else {
            intent = null;
        }
        return (intent == null || !PermissionUtils.areActivityIntent(context, intent)) ? PermissionUtils.getApplicationDetailsIntent(context) : intent;
    }

    private static boolean isGrantedVpnPermission(Context context) {
        return VpnService.prepare(context) == null;
    }

    private static Intent getVpnPermissionIntent(Context context) {
        Intent intentPrepare = VpnService.prepare(context);
        return (intentPrepare == null || !PermissionUtils.areActivityIntent(context, intentPrepare)) ? PermissionUtils.getApplicationDetailsIntent(context) : intentPrepare;
    }
}
