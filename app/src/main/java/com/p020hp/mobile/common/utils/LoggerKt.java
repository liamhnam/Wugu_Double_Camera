package com.p020hp.mobile.common.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1292d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0006\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0001H\u0000¨\u0006\b"}, m1293d2 = {"componentName", "", "packageName", "logger", "Lcom/hp/mobile/common/utils/Logger;", "tag", "", "toTag", "common-lib_release"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class LoggerKt {
    public static final String componentName(String str) {
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "com.hp.mobile.common", false, 2, (Object) null) ? "CommonLib" : StringsKt.contains$default((CharSequence) str, (CharSequence) "com.hp.mobile.discoverylib", false, 2, (Object) null) ? "DiscoLib" : StringsKt.contains$default((CharSequence) str, (CharSequence) "com.hp.mobile.scan.sdk", false, 2, (Object) null) ? "ScanSDK" : (StringsKt.contains$default((CharSequence) str, (CharSequence) "com.hp.bgp", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "com.hp.open.printsdk", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "com.hp.printsdk", false, 2, (Object) null)) ? "PrintSDK" : (StringsKt.contains$default((CharSequence) str, (CharSequence) "com.hp.open.printui", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "com.hp.printui", false, 2, (Object) null)) ? "PrintUI" : Logger.LOG_TAG_PREFIX;
    }

    public static final Logger logger(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        Package r1 = obj.getClass().getPackage();
        String name = r1 != null ? r1.getName() : null;
        if (name != null) {
            componentName(name);
        }
        return new Logger(Logger.LOG_TAG_PREFIX);
    }

    public static final Logger logger(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return new Logger(tag);
    }

    public static final String toTag(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Logger.libTag + str;
    }
}
