package com.hjq.permissions;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

public interface IPermissionInterceptor {
    default void requestPermissions(Activity activity, List<String> list, OnPermissionCallback onPermissionCallback) {
        PermissionFragment.beginRequest(activity, new ArrayList(list), this, onPermissionCallback);
    }

    default void grantedPermissions(Activity activity, List<String> list, List<String> list2, boolean z, OnPermissionCallback onPermissionCallback) {
        if (onPermissionCallback == null) {
            return;
        }
        onPermissionCallback.onGranted(list2, z);
    }

    default void deniedPermissions(Activity activity, List<String> list, List<String> list2, boolean z, OnPermissionCallback onPermissionCallback) {
        if (onPermissionCallback == null) {
            return;
        }
        onPermissionCallback.onDenied(list2, z);
    }
}
