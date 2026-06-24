package com.p020hp.printsdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.p020hp.bgp.service.BackgroundPrintService;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class ServiceConnectionC1794z0 implements ServiceConnection {

    public final Function1<BackgroundPrintService.C1607b, Unit> f2053a;

    public final Context f2054b;

    public ServiceConnectionC1794z0(Function1<? super BackgroundPrintService.C1607b, Unit> function1, Context context) {
        this.f2053a = function1;
        this.f2054b = context;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
        this.f2053a.invoke(new BackgroundPrintService.C1607b(this.f2054b, this, ((BackgroundPrintService.BinderC1608c) service).f709a));
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Intrinsics.checkNotNullParameter(name, "name");
    }
}
