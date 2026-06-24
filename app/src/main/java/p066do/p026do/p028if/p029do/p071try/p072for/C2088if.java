package p066do.p026do.p028if.p029do.p071try.p072for;

import com.p020hp.mobile.common.browsing.ServiceType;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import p066do.p026do.p028if.p029do.p030if.C2076do;

public final class C2088if extends AbstractC2087do {
    @Override
    public ServiceType mo1260do() {
        return ServiceType.CDM;
    }

    @Override
    public JSONObject mo1261do(Response response, JSONObject fieldsPath) throws IOException {
        String strString;
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(fieldsPath, "fieldsPath");
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
            throw new IOException("Response body is null.");
        }
        if (strString.length() == 0) {
            strString = "{}";
        }
        return C2076do.m1227do(new JSONObject(strString), fieldsPath, false);
    }
}
