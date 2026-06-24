package p066do.p026do.p028if.p029do.p071try.p072for;

import android.util.Base64;
import com.p020hp.mobile.common.CommonLibKt;
import com.p020hp.mobile.common.browsing.ConnectionType;
import com.p020hp.mobile.common.browsing.IDevice;
import com.p020hp.mobile.common.browsing.ServiceType;
import com.p020hp.mobile.common.httpclient.HttpClients;
import com.p020hp.mobile.common.httpclient.HttpClientsKt;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

public final class Cnew {

    public final OkHttpClient f2555do = HttpClientsKt.setTimeouts(HttpClients.INSTANCE.getLanUnsecureClient().newBuilder(), 10).build();

    public final OkHttpClient f2556if = HttpClientsKt.setTimeouts(HttpClients.INSTANCE.getUsbClient().newBuilder().socketFactory(CommonLibKt.CommonLib().getServicesBrowser().usbSocketFactory()), 10).build();

    public final Lazy f4027for = LazyKt.lazy(C2089do.f2557if);

    public final Lazy f4028new = LazyKt.lazy(C2090if.f2558if);

    public static final class C2089do extends Lambda implements Function0<C2088if> {

        public static final C2089do f2557if = new C2089do();

        public C2089do() {
            super(0);
        }

        @Override
        public C2088if invoke() {
            return new C2088if();
        }
    }

    public static final class C2090if extends Lambda implements Function0<Cfor> {

        public static final C2090if f2558if = new C2090if();

        public C2090if() {
            super(0);
        }

        @Override
        public Cfor invoke() {
            return new Cfor();
        }
    }

    public final Map<ServiceType, JSONObject> m1263do(String str, String str2) {
        Object objM1772constructorimpl;
        try {
            byte[] bytes = "b68fd7affb9b6e78".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
            byte[] bytes2 = "b68fd7affb9b6e78".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes2);
            byte[] bArrDecode = Base64.decode(str2, 0);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            byte[] bArrDoFinal = cipher.doFinal(bArrDecode);
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "cipher.doFinal(bytes)");
            JSONObject jSONObjectOptJSONObject = new JSONObject(new String(bArrDoFinal, Charsets.UTF_8)).optJSONObject(str);
            HashMap map = new HashMap();
            Object objOpt = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.opt("LEDM") : null;
            JSONObject jSONObject = objOpt instanceof JSONObject ? (JSONObject) objOpt : null;
            if (jSONObject != null) {
            }
            Object objOpt2 = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.opt("CDM") : null;
            JSONObject jSONObject2 = objOpt2 instanceof JSONObject ? (JSONObject) objOpt2 : null;
            if (jSONObject2 != null) {
                map.put(ServiceType.CDM, jSONObject2);
            }
            objM1772constructorimpl = Result.m1772constructorimpl(map);
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1775exceptionOrNullimpl(objM1772constructorimpl) != null) {
            objM1772constructorimpl = MapsKt.emptyMap();
        }
        return (Map) objM1772constructorimpl;
    }

    public final void m1264do(IDevice device, String module, String queryParamString, JSONObject jSONObject, Function2<? super JSONObject, ? super Throwable, Unit> callback) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(queryParamString, "queryParamString");
        Intrinsics.checkNotNullParameter(callback, "callback");
        OkHttpClient okHttpClient = device.connectionType() == ConnectionType.USB ? this.f2556if : this.f2555do;
        Map<ServiceType, JSONObject> mapM1263do = m1263do(module, queryParamString);
        if (device.isCDM()) {
            ((C2088if) this.f4027for.getValue()).m1262do(device, okHttpClient, mapM1263do, jSONObject, callback);
        } else if (device.isLEDM()) {
            ((Cfor) this.f4028new.getValue()).m1262do(device, okHttpClient, mapM1263do, jSONObject, callback);
        } else {
            callback.invoke(null, new IllegalArgumentException("Query failed, it's an unsupported device."));
        }
    }
}
