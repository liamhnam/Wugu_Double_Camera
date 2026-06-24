package com.p020hp.printsdk;

import com.p020hp.mobile.common.identity.Xml;
import com.p020hp.printsdk.ledm.ProductStatusDyn;
import kotlin.coroutines.Continuation;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface InterfaceC1667a4 {
    @Xml
    @GET
    Object m464a(@Url String str, Continuation<? super ProductStatusDyn> continuation);
}
