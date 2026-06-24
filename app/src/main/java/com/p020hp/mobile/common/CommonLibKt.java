package com.p020hp.mobile.common;

import android.content.Context;
import kotlin.Metadata;

@Metadata(m1292d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, m1293d2 = {"CommonLib", "Lcom/hp/mobile/common/CommonLib;", "context", "Landroid/content/Context;", "common-lib_release"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class CommonLibKt {
    public static final CommonLib CommonLib() {
        return CommonLib.INSTANCE.get();
    }

    public static final Context context() {
        return CommonLib().getContext();
    }
}
