package p066do.p026do.p028if.p029do.p071try.p072for;

import com.p020hp.mobile.common.browsing.ServiceType;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import p066do.p026do.p028if.p029do.p030if.C2076do;
import p066do.p026do.p028if.p029do.p071try.p034if.C2092if;

public final class Cfor extends AbstractC2087do {
    @Override
    public ServiceType mo1260do() {
        return ServiceType.LEDM;
    }

    @Override
    public JSONObject mo1261do(Response response, JSONObject fieldsPath) throws JSONException, IOException {
        String strString;
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(fieldsPath, "fieldsPath");
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
            throw new IOException("Response body is null.");
        }
        JSONObject jSONObject = new C2092if(new C2092if.Cdo(new Regex("[\t\n]").replace(strString, ""))).f4034class;
        if (jSONObject != null) {
            return C2076do.m1227do(jSONObject, fieldsPath, true);
        }
        throw new JSONException("XML to Json error");
    }
}
