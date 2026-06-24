package p066do.p026do.p028if.p029do.p070new.p031do;

import com.p020hp.mobile.common.identity.Json;
import com.p020hp.mobile.common.models.cdm.CDMServicesDiscovery;
import com.p020hp.mobile.common.models.cdm.CDMSystemIdentity;
import kotlin.coroutines.Continuation;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface InterfaceC2084do {
    @Json
    @GET
    Object m1257do(@Url String str, Continuation<? super CDMServicesDiscovery> continuation);

    @Json
    @GET
    Object m1258if(@Url String str, Continuation<? super CDMSystemIdentity> continuation);
}
