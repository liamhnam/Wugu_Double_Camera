package com.p020hp.mobile.common.httpclient;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;

@Metadata(m1292d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, m1293d2 = {"setTimeouts", "Lokhttp3/OkHttpClient$Builder;", "timeout", "", "common-lib_release"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class HttpClientsKt {
    public static final OkHttpClient.Builder setTimeouts(OkHttpClient.Builder builder, long j) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return builder.readTimeout(j, TimeUnit.SECONDS).writeTimeout(j, TimeUnit.SECONDS).connectTimeout(j, TimeUnit.SECONDS);
    }
}
