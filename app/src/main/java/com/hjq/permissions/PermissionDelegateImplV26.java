package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

class PermissionDelegateImplV26 extends PermissionDelegateImplV23 {
    PermissionDelegateImplV26() {
    }

    @Override
    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.REQUEST_INSTALL_PACKAGES)) {
            return isGrantedInstallPermission(context);
        }
        if (PermissionUtils.equalsPermission(str, Permission.READ_PHONE_NUMBERS) || PermissionUtils.equalsPermission(str, Permission.ANSWER_PHONE_CALLS)) {
            return PermissionUtils.checkSelfPermission(context, str);
        }
        return super.isGrantedPermission(context, str);
    }

    @Override
    public boolean isPermissionPermanentDenied(Activity activity, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.REQUEST_INSTALL_PACKAGES)) {
            return false;
        }
        if (PermissionUtils.equalsPermission(str, Permission.READ_PHONE_NUMBERS) || PermissionUtils.equalsPermission(str, Permission.ANSWER_PHONE_CALLS)) {
            return (PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
        }
        return super.isPermissionPermanentDenied(activity, str);
    }

    @Override
    public Intent getPermissionIntent(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.REQUEST_INSTALL_PACKAGES)) {
            return getInstallPermissionIntent(context);
        }
        return super.getPermissionIntent(context, str);
    }

    private static boolean isGrantedInstallPermission(Context context) {
        return context.getPackageManager().canRequestPackageInstalls();
    }

    private static Intent getInstallPermissionIntent(Context context) {
        Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        return !PermissionUtils.areActivityIntent(context, intent) ? PermissionUtils.getApplicationDetailsIntent(context) : intent;
    }
}
