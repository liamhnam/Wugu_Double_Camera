package com.p020hp.printsdk;

import com.p020hp.mobile.common.identity.Json;
import com.p020hp.printsdk.cdm.Alerts;
import kotlin.coroutines.Continuation;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface InterfaceC1742o2 {
    @Json
    @GET
    Object m607a(@Url String str, Continuation<? super Alerts> continuation);
}
