package com.proembed.service;

import android.os.Build;

public class XcFactory {
    public static AbstractC1848XC getRK() {
        String androidModle = VersionUtils.getAndroidModle();
        if (androidModle.contains("rk3399")) {
            return XC3399.INSTANCE;
        }
        if (androidModle.contains("rk3288") && "22".equals(Build.VERSION.SDK)) {
            return XC3288_5.INSTANCE;
        }
        if (androidModle.contains("rk3128")) {
            return XC3128.INSTANCE;
        }
        if (androidModle.contains("rk3288") && "25".equals(Build.VERSION.SDK)) {
            return XC3288_7.INSTANCE;
        }
        return XC3288_7.INSTANCE;
    }
}
