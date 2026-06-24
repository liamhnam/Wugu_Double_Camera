package com.p020hp.mobile.common.query.delegate;

import com.p020hp.mobile.common.browsing.IDevice;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONObject;
import p066do.p026do.p028if.p029do.p071try.p072for.Cnew;

@Metadata(m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b&\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u00040\fJ\b\u0010\u000e\u001a\u00020\bH&¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/mobile/common/query/delegate/Delegate;", "", "()V", "execute", "", "device", "Lcom/hp/mobile/common/browsing/IDevice;", "module", "", "dynamicParam", "Lorg/json/JSONObject;", "callback", "Lkotlin/Function2;", "", "getQueryParam", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class Delegate {
    public static final String CONTENT_BODY_KEY = "CONTENT_BODY";
    public static final String CONTENT_HEADER_KEY = "CONTENT_HEADER";
    public static final String CONTENT_METHOD_KEY = "CONTENT_METHOD";
    public static final String CONTENT_PATH_KEY = "CONTENT_PATH";
    public static final Logger log;
    public static final Cnew proxy;

    public static final class C1656if extends Lambda implements Function2<JSONObject, Throwable, Unit> {

        public final Function2<String, Throwable, Unit> f3855for;

        public final long f764if;

        public C1656if(long j, Function2<? super String, ? super Throwable, Unit> function2) {
            super(2);
            this.f764if = j;
            this.f3855for = function2;
        }

        @Override
        public Unit invoke(JSONObject jSONObject, Throwable th) {
            JSONObject jSONObject2 = jSONObject;
            Throwable th2 = th;
            if (jSONObject2 != null) {
                long j = this.f764if;
                Function2<String, Throwable, Unit> function2 = this.f3855for;
                Delegate.log.invoke("Query(" + j + ") execute success. \n\t" + jSONObject2);
                function2.invoke(jSONObject2.toString(), null);
            }
            if (th2 != null) {
                long j2 = this.f764if;
                Function2<String, Throwable, Unit> function22 = this.f3855for;
                Delegate.log.m447e("Query(" + j2 + ") execute failed. \n\t" + th2.getMessage());
                function22.invoke(null, th2);
            }
            Delegate.log.invoke("Delegate(" + this.f764if + ") done.");
            return Unit.INSTANCE;
        }
    }

    static {
        Companion companion = new Companion();
        INSTANCE = companion;
        log = LoggerKt.logger(companion);
        proxy = new Cnew();
    }

    public static void execute$default(Delegate delegate, IDevice iDevice, String str, JSONObject jSONObject, Function2 function2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: execute");
        }
        if ((i & 4) != 0) {
            jSONObject = null;
        }
        delegate.execute(iDevice, str, jSONObject, function2);
    }

    public final void execute(IDevice device, String module, JSONObject dynamicParam, Function2<? super String, ? super Throwable, Unit> callback) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(callback, "callback");
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            log.invoke("Delegate(" + jCurrentTimeMillis + ") execute start.");
            proxy.m1264do(device, module, getQueryParam(), dynamicParam, new C1656if(jCurrentTimeMillis, callback));
        } catch (Throwable th) {
            log.m447e("Delegate(" + jCurrentTimeMillis + ") execute failed. \n\t" + th.getMessage());
            callback.invoke(null, th);
        }
    }

    public abstract String getQueryParam();
}
