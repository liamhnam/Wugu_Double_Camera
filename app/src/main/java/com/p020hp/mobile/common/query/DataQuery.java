package com.p020hp.mobile.common.query;

import com.p020hp.mobile.common.browsing.IDevice;
import com.p020hp.mobile.common.query.delegate.Delegate;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.eclipse.paho.android.service.MqttServiceConstants;
import p066do.p026do.p028if.p029do.p071try.p033do.C2086do;

@Metadata(m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\f"}, m1293d2 = {"Lcom/hp/mobile/common/query/DataQuery;", "", "()V", "queryCalibration", "", "device", "Lcom/hp/mobile/common/browsing/IDevice;", "listener", "Lcom/hp/mobile/common/query/DataQuery$OnDataQueryListener;", "queryIdentity", "Companion", "OnDataQueryListener", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class DataQuery {
    public static final Logger log;

    @Metadata(m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, m1293d2 = {"Lcom/hp/mobile/common/query/DataQuery$OnDataQueryListener;", "", "onResult", "", "success", "", MqttServiceConstants.TRACE_ERROR, "", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public interface OnDataQueryListener {
        void onResult(String success, Throwable error);
    }

    public static final class Cfor extends Lambda implements Function2<String, Throwable, Unit> {

        public final OnDataQueryListener f3853for;

        public final IDevice f762if;

        public Cfor(IDevice iDevice, OnDataQueryListener onDataQueryListener) {
            super(2);
            this.f762if = iDevice;
            this.f3853for = onDataQueryListener;
        }

        @Override
        public Unit invoke(String str, Throwable th) {
            DataQuery.log.invoke("Query identity on " + this.f762if.getMakeAndModel() + " done.");
            this.f3853for.onResult(str, th);
            return Unit.INSTANCE;
        }
    }

    public static final class C1654if extends Lambda implements Function2<String, Throwable, Unit> {

        public final OnDataQueryListener f3854for;

        public final IDevice f763if;

        public C1654if(IDevice iDevice, OnDataQueryListener onDataQueryListener) {
            super(2);
            this.f763if = iDevice;
            this.f3854for = onDataQueryListener;
        }

        @Override
        public Unit invoke(String str, Throwable th) {
            DataQuery.log.invoke("Query calibration on " + this.f763if.getMakeAndModel() + " done.");
            this.f3854for.onResult(str, th);
            return Unit.INSTANCE;
        }
    }

    static {
        Companion companion = new Companion();
        INSTANCE = companion;
        log = LoggerKt.logger(companion);
    }

    public final void queryCalibration(IDevice device, OnDataQueryListener listener) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(listener, "listener");
        log.invoke("Query calibration on " + device.getMakeAndModel() + " start.");
        Delegate.execute$default(new C2086do(), device, "CALIBRATION", null, new C1654if(device, listener), 4, null);
    }

    public final void queryIdentity(IDevice device, OnDataQueryListener listener) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(listener, "listener");
        log.invoke("Query identity on " + device.getMakeAndModel() + " start.");
        Delegate.execute$default(new C2086do(), device, "IDENTITY", null, new Cfor(device, listener), 4, null);
    }
}
