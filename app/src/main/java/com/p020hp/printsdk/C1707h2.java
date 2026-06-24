package com.p020hp.printsdk;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.p020hp.mobile.common.CommonLibKt;
import com.p020hp.mobile.common.browsing.Device;
import com.p020hp.mobile.common.browsing.DeviceKt;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.open.printsdk.CoreManager;
import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.open.printsdk.PrinterWithJobs;
import com.p020hp.open.printsdk.state.printer.HpCalibrationState;
import com.p020hp.printsdk.base.SdkDelegate;
import java.io.Closeable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

public final class C1707h2 implements SdkDelegate, Closeable {

    public final CoroutineScope f1281a;

    public final Context f1282b;

    public final C1698f4 f1283c;

    public final Logger f1284d;

    public final InterfaceC1737n2 f1285e;

    public C1787x3 f1286f;

    public LiveData<List<HpPrinter>> f1287g;

    public List<? extends HpPrinter> f1288h;

    public final MediatorLiveData<List<HpPrinter>> f1289i;

    public final InterfaceC1742o2 f1290j;

    public final InterfaceC1667a4 f1291k;

    public static final class a extends Lambda implements Function1<Device, Unit> {

        public final Function1<HpPrinter, Unit> f1292a;

        public final C1707h2 f1293b;

        public a(Function1<? super HpPrinter, Unit> function1, C1707h2 c1707h2) {
            super(1);
            this.f1292a = function1;
            this.f1293b = c1707h2;
        }

        @Override
        public Unit invoke(Device device) {
            Unit unit;
            Device device2 = device;
            if (device2 != null) {
                this.f1293b.printer(device2, this.f1292a);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                this.f1292a.invoke(null);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.SdkDelegateImpl$getPrinterCalibrationStatus$1", m1305f = "SdkDelegateImpl.kt", m1306i = {}, m1307l = {217, 220, 236, 241}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public Object f1294a;

        public int f1295b;

        public Object f1296c;

        public final String f1297d;

        public final C1707h2 f1298e;

        public final Function1<HpCalibrationState, Unit> f1299f;

        @DebugMetadata(m1304c = "com.hp.printsdk.SdkDelegateImpl$getPrinterCalibrationStatus$1$2$1", m1305f = "SdkDelegateImpl.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public final Function1<HpCalibrationState, Unit> f1300a;

            public a(Function1<? super HpCalibrationState, Unit> function1, Continuation<? super a> continuation) {
                super(2, continuation);
                this.f1300a = function1;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new a(this.f1300a, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new a(this.f1300a, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                this.f1300a.invoke(null);
                return Unit.INSTANCE;
            }
        }

        @DebugMetadata(m1304c = "com.hp.printsdk.SdkDelegateImpl$getPrinterCalibrationStatus$1$3$1", m1305f = "SdkDelegateImpl.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class C3361b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public final List<HpCalibrationState> f1301a;

            public final Function1<HpCalibrationState, Unit> f1302b;

            public C3361b(List<? extends HpCalibrationState> list, Function1<? super HpCalibrationState, Unit> function1, Continuation<? super C3361b> continuation) {
                super(2, continuation);
                this.f1301a = list;
                this.f1302b = function1;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C3361b(this.f1301a, this.f1302b, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new C3361b(this.f1301a, this.f1302b, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                Function1<HpCalibrationState, Unit> function1;
                HpCalibrationState hpCalibrationState;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                if (this.f1301a.contains(HpCalibrationState.AlignmentNotFinished)) {
                    function1 = this.f1302b;
                    hpCalibrationState = HpCalibrationState.AlignmentNotFinished;
                } else if (this.f1301a.contains(HpCalibrationState.CalibrationRequired)) {
                    function1 = this.f1302b;
                    hpCalibrationState = HpCalibrationState.CalibrationRequired;
                } else {
                    function1 = this.f1302b;
                    hpCalibrationState = HpCalibrationState.None;
                }
                function1.invoke(hpCalibrationState);
                return Unit.INSTANCE;
            }
        }

        public b(String str, C1707h2 c1707h2, Function1<? super HpCalibrationState, Unit> function1, Continuation<? super b> continuation) {
            super(2, continuation);
            this.f1297d = str;
            this.f1298e = c1707h2;
            this.f1299f = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            b bVar = new b(this.f1297d, this.f1298e, this.f1299f, continuation);
            bVar.f1296c = obj;
            return bVar;
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            b bVar = new b(this.f1297d, this.f1298e, this.f1299f, continuation);
            bVar.f1296c = coroutineScope;
            return bVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 462
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1707h2.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.SdkDelegateImpl$wakeUp$1", m1305f = "SdkDelegateImpl.kt", m1306i = {0, 1}, m1307l = {115, 116}, m1308m = "invokeSuspend", m1309n = {"$this$launch", "$this$launch"}, m1310s = {"L$0", "L$0"})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1303a;

        public Object f1304b;

        public final UUID f1306d;

        public c(UUID uuid, Continuation<? super c> continuation) {
            super(2, continuation);
            this.f1306d = uuid;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            c cVar = C1707h2.this.new c(this.f1306d, continuation);
            cVar.f1304b = obj;
            return cVar;
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            c cVar = C1707h2.this.new c(this.f1306d, continuation);
            cVar.f1304b = coroutineScope;
            return cVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 632
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1707h2.c.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public C1707h2(CoroutineScope scope, Context context) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f1281a = scope;
        this.f1282b = context;
        CoreManager.INSTANCE.checkSdkIsInitialized$print_core_thirdPartyRelease();
        this.f1283c = new C1698f4();
        Logger logger = new Logger("SdkDelegateImplCL");
        this.f1284d = logger;
        C1752q2 c1752q2 = C1752q2.f1624a;
        this.f1285e = c1752q2;
        this.f1286f = new C1787x3(scope, context, c1752q2);
        this.f1288h = CollectionsKt.emptyList();
        this.f1289i = new MediatorLiveData<>();
        this.f1290j = C1685d4.f1173a.m490a();
        this.f1291k = C1685d4.f1173a.m491b();
        logger.invoke("******* Current sdk version is 2.4.4 *******");
    }

    public static final void m544a(MediatorLiveData this_apply, HpPrintJob hpPrintJob) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.setValue(hpPrintJob);
    }

    public static final void m545a(C1707h2 this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.f1288h = it;
        this$0.f1289i.setValue(it);
        this$0.f1284d.invoke("mdns printer list: " + this$0.f1288h);
    }

    @Override
    public void cancelPrintJob(UUID printerId, UUID jobId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(jobId, "jobId");
        C1787x3 c1787x3 = this.f1286f;
        c1787x3.getClass();
        Intrinsics.checkNotNullParameter(jobId, "jobId");
        BuildersKt__Builders_commonKt.launch$default(c1787x3.f1365a, Dispatchers.getIO(), null, new C1712i2(c1787x3, jobId, null), 2, null);
    }

    @Override
    public void close() {
        this.f1286f.close();
    }

    @Override
    public void delDoneJobHistory(UUID[] jobIds, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(jobIds, "jobIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f1285e.delDoneJobHistory((UUID[]) Arrays.copyOf(jobIds, jobIds.length), callback);
    }

    @Override
    public void getIpPrinter(String ip, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CommonLibKt.CommonLib().getServicesBrowser().addDeviceByIP(ip, new a(callback, this));
    }

    @Override
    public void getPrinterCalibrationStatus(String ipAddress, Function1<? super HpCalibrationState, Unit> callback) {
        Intrinsics.checkNotNullParameter(ipAddress, "ipAddress");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f1284d.invoke("Get calibration status from " + ipAddress);
        BuildersKt__Builders_commonKt.launch$default(this.f1281a, Dispatchers.getIO(), null, new b(ipAddress, this, callback, null), 2, null);
    }

    @Override
    public void jobHistory(UUID[] printerIds, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(printerIds, "printerIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f1285e.jobHistory((UUID[]) Arrays.copyOf(printerIds, printerIds.length), callback);
    }

    @Override
    public void jobs(UUID[] printerIds, int i, int i2, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(printerIds, "printerIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f1285e.jobs((UUID[]) Arrays.copyOf(printerIds, printerIds.length), i * i2, i2, callback);
    }

    @Override
    public void printer(Device device, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(callback, "callback");
        C1687e printer = C1687e.f1178e.m506a(DeviceKt.getPrintServices(device));
        if (printer != null) {
            C1787x3 c1787x3 = this.f1286f;
            c1787x3.getClass();
            Intrinsics.checkNotNullParameter(printer, "printer");
            Intrinsics.checkNotNullParameter(callback, "callback");
            BuildersKt__Builders_commonKt.launch$default(c1787x3.f1365a, Dispatchers.getIO(), null, new C1732m2(callback, c1787x3, printer, null), 2, null);
        }
    }

    @Override
    public LiveData<List<HpPrinter>> printers() {
        MutableLiveData<List<HpPrinter>> mutableLiveDataM682a;
        if (this.f1287g == null) {
            MediatorLiveData<List<HpPrinter>> mediatorLiveData = this.f1289i;
            C1787x3 c1787x3 = this.f1286f;
            if (c1787x3.f1969j != null) {
                mutableLiveDataM682a = c1787x3.m682a();
            } else {
                MutableLiveData<List<HpPrinter>> mutableLiveData = new MutableLiveData<>();
                Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
                c1787x3.f1969j = mutableLiveData;
                c1787x3.m683a(100L, new C1792y3(c1787x3, mutableLiveData));
                mutableLiveDataM682a = mutableLiveData;
            }
            this.f1287g = mutableLiveDataM682a;
            mediatorLiveData.addSource(mutableLiveDataM682a, new Observer() {
                @Override
                public final void onChanged(Object obj) {
                    C1707h2.m545a(this.f$0, (List) obj);
                }
            });
        }
        return this.f1289i;
    }

    @Override
    public void reCheckAllUSBDevices() {
        CommonLibKt.CommonLib().getServicesBrowser().reCheckAllUSBDevices();
    }

    @Override
    public void refreshPrinter(HpPrinter printer, List<String> requested, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(requested, "requested");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f1284d.invoke("Do refresh printer " + printer.getType());
        this.f1286f.m562a(printer.getId(), requested, callback);
    }

    @Override
    public void refreshPrinter(HpPrinter printer, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(callback, "callback");
        refreshPrinter(printer, CollectionsKt.listOf("all"), callback);
    }

    @Override
    public void refreshPrinterList() {
        C1787x3 c1787x3 = this.f1286f;
        if (c1787x3.f1969j != null) {
            if (c1787x3.f1970k != null) {
                CommonLibKt.CommonLib().getServicesBrowser().stop(c1787x3.f1968i);
                Job job = c1787x3.f1970k;
                if (job == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("discoveryJob");
                    job = null;
                }
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                BuildersKt__Builders_commonKt.launch$default(c1787x3.f1964e, Dispatchers.getMain(), null, new C1797z3(c1787x3, null), 2, null);
            }
        }
    }

    @Override
    public void stopDiscoverPrinters() {
        this.f1286f.m685b();
    }

    @Override
    public void wakeUp(UUID printerId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        BuildersKt__Builders_commonKt.launch$default(this.f1281a, Dispatchers.getIO(), null, new c(printerId, null), 2, null);
    }

    @Override
    public LiveData<HpPrintJob> job(UUID printerId, UUID jobId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(jobId, "jobId");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        C1787x3 c1787x3 = this.f1286f;
        c1787x3.getClass();
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(jobId, "jobId");
        mediatorLiveData.addSource(c1787x3.m559a((Function1) new C1722k2(c1787x3, printerId, jobId, null)), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                C1707h2.m544a(mediatorLiveData, (HpPrintJob) obj);
            }
        });
        return mediatorLiveData;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.lifecycle.LiveData<com.p020hp.open.printsdk.HpPrintJob> print(com.p020hp.open.printsdk.HpPrintRequest r8) {
        /*
            Method dump skipped, instruction units count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1707h2.print(com.hp.open.printsdk.HpPrintRequest):androidx.lifecycle.LiveData");
    }
}
