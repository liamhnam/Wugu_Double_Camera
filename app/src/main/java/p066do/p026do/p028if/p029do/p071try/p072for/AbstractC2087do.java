package p066do.p026do.p028if.p029do.p071try.p072for;

import com.p020hp.mobile.common.browsing.ServiceType;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONObject;

public abstract class AbstractC2087do {

    public static final Cdo f2551do;

    public static final Logger f2552if;

    public static final class Cdo {
    }

    public static final class Cif implements Callback {

        public final Function2<JSONObject, Throwable, Unit> f2553do;

        public final AbstractC2087do f4025for;

        public final String f2554if;

        public final JSONObject f4026new;

        public Cif(Function2<? super JSONObject, ? super Throwable, Unit> function2, String str, AbstractC2087do abstractC2087do, JSONObject jSONObject) {
            this.f2553do = function2;
            this.f2554if = str;
            this.f4025for = abstractC2087do;
            this.f4026new = jSONObject;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(e, "e");
            call.cancel();
            this.f2553do.invoke(null, e);
        }

        @Override
        public void onResponse(Call call, Response response) {
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(response, "response");
            if (!response.isSuccessful()) {
                this.f2553do.invoke(null, new IOException("Call to " + this.f2554if + " failed with " + response.code()));
                return;
            }
            try {
                this.f2553do.invoke(this.f4025for.mo1261do(response, this.f4026new), null);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    static {
        Cdo cdo = new Cdo();
        f2551do = cdo;
        f2552if = LoggerKt.logger(cdo);
    }

    public abstract ServiceType mo1260do();

    public abstract JSONObject mo1261do(Response response, JSONObject jSONObject);

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m1262do(com.p020hp.mobile.common.browsing.IDevice r19, okhttp3.OkHttpClient r20, java.util.Map<com.p020hp.mobile.common.browsing.ServiceType, ? extends org.json.JSONObject> r21, org.json.JSONObject r22, kotlin.jvm.functions.Function2<? super org.json.JSONObject, ? super java.lang.Throwable, kotlin.Unit> r23) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p071try.p072for.AbstractC2087do.m1262do(com.hp.mobile.common.browsing.IDevice, okhttp3.OkHttpClient, java.util.Map, org.json.JSONObject, kotlin.jvm.functions.Function2):void");
    }
}
