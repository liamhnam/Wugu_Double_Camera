package com.p020hp.open.printsdk;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import com.arthenica.ffmpegkit.StreamInformation;
import com.p020hp.mobile.common.CommonLibKt;
import com.p020hp.mobile.common.browsing.Device;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.open.printsdk.state.printer.HpCalibrationState;
import com.p020hp.printsdk.C1707h2;
import com.p020hp.printsdk.C1707h2.c;
import com.p020hp.printsdk.C1712i2;
import com.p020hp.printsdk.C1787x3;
import com.p020hp.printsdk.C1797z3;
import com.p020hp.printsdk.base.SdkDelegate;
import com.proembed.service.Constant;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(m1292d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J;\u0010\u000f\u001a\u00020\u000b2\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0011\"\u00020\r2\u0018\u0010\u0012\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016¢\u0006\u0002\u0010\u0016J5\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016J5\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00192#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010 ¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016J\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J;\u0010%\u001a\u00020\u000b2\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0011\"\u00020\r2\u0018\u0010\u0012\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016¢\u0006\u0002\u0010\u0016JM\u0010'\u001a\u00020\u000b2\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0011\"\u00020\r2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u001a\u0010\u0012\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016¢\u0006\u0002\u0010+J\b\u0010,\u001a\u00020\u000bH\u0014J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010.\u001a\u00020/H\u0016J5\u0010\u001d\u001a\u00020\u000b2\u0006\u00100\u001a\u0002012#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016J\u0014\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00140#H\u0016J\b\u00103\u001a\u00020\u000bH\u0016J5\u00104\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001a2#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016JC\u00104\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001a2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00190\u00142#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016J\b\u00106\u001a\u00020\u000bH\u0016J\b\u00107\u001a\u00020\u000bH\u0016J\u0010\u00108\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, m1293d2 = {"Lcom/hp/open/printsdk/SdkViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "Lcom/hp/printsdk/base/SdkDelegate;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "log", "Lcom/hp/mobile/common/utils/Logger;", "sdkDelegateImpl", "Lcom/hp/printsdk/SdkDelegateImpl;", "cancelPrintJob", "", "printerId", "Ljava/util/UUID;", "jobId", "delDoneJobHistory", "jobIds", "", "callback", "Lkotlin/Function1;", "", "Lcom/hp/open/printsdk/PrinterWithJobs;", "([Ljava/util/UUID;Lkotlin/jvm/functions/Function1;)V", "getIpPrinter", Constant.ETH_SET_IP, "", "Lcom/hp/open/printsdk/HpPrinter;", "Lkotlin/ParameterName;", NamingTable.TAG, MqttCmdEnum.C2S_PRINTER_ERROR, "getPrinterCalibrationStatus", "ipAddress", "Lcom/hp/open/printsdk/state/printer/HpCalibrationState;", "state", "job", "Landroidx/lifecycle/LiveData;", "Lcom/hp/open/printsdk/HpPrintJob;", "jobHistory", "printerIds", "jobs", StreamInformation.KEY_INDEX, "", "pageSize", "([Ljava/util/UUID;IILkotlin/jvm/functions/Function1;)V", "onCleared", "print", "request", "Lcom/hp/open/printsdk/HpPrintRequest;", "device", "Lcom/hp/mobile/common/browsing/Device;", "printers", "reCheckAllUSBDevices", "refreshPrinter", "requested", "refreshPrinterList", "stopDiscoverPrinters", "wakeUp", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public class SdkViewModel extends AndroidViewModel implements SdkDelegate {

    public final Logger f813a;

    public final C1707h2 f814b;

    public SdkViewModel(Application app) {
        super(app);
        Intrinsics.checkNotNullParameter(app, "app");
        this.f813a = LoggerKt.logger(this);
        this.f814b = new C1707h2(ViewModelKt.getViewModelScope(this), app);
    }

    @Override
    public void delDoneJobHistory(UUID[] jobIds, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(jobIds, "jobIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f813a.invoke("Call delDoneJobHistory() with job ids: " + ArraysKt.joinToString$default(jobIds, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
        C1707h2 c1707h2 = this.f814b;
        UUID[] jobIds2 = (UUID[]) Arrays.copyOf(jobIds, jobIds.length);
        c1707h2.getClass();
        Intrinsics.checkNotNullParameter(jobIds2, "jobIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        c1707h2.f1285e.delDoneJobHistory((UUID[]) Arrays.copyOf(jobIds2, jobIds2.length), callback);
    }

    @Override
    public void getIpPrinter(String ip, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(callback, "callback");
        C1707h2 c1707h2 = this.f814b;
        c1707h2.getClass();
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CommonLibKt.CommonLib().getServicesBrowser().addDeviceByIP(ip, new C1707h2.a(callback, c1707h2));
    }

    @Override
    public void getPrinterCalibrationStatus(String ipAddress, Function1<? super HpCalibrationState, Unit> callback) {
        Intrinsics.checkNotNullParameter(ipAddress, "ipAddress");
        Intrinsics.checkNotNullParameter(callback, "callback");
        C1707h2 c1707h2 = this.f814b;
        c1707h2.getClass();
        Intrinsics.checkNotNullParameter(ipAddress, "ipAddress");
        Intrinsics.checkNotNullParameter(callback, "callback");
        c1707h2.f1284d.invoke("Get calibration status from " + ipAddress);
        BuildersKt__Builders_commonKt.launch$default(c1707h2.f1281a, Dispatchers.getIO(), null, new C1707h2.b(ipAddress, c1707h2, callback, null), 2, null);
    }

    @Override
    public LiveData<HpPrintJob> job(UUID printerId, UUID jobId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(jobId, "jobId");
        this.f813a.invoke("Call job() with printer id: " + printerId + ", job id: " + jobId);
        return this.f814b.job(printerId, jobId);
    }

    @Override
    public void jobHistory(UUID[] printerIds, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(printerIds, "printerIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f813a.invoke("Call jobHistory() with printer ids: " + ArraysKt.joinToString$default(printerIds, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
        C1707h2 c1707h2 = this.f814b;
        UUID[] printerIds2 = (UUID[]) Arrays.copyOf(printerIds, printerIds.length);
        c1707h2.getClass();
        Intrinsics.checkNotNullParameter(printerIds2, "printerIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        c1707h2.f1285e.jobHistory((UUID[]) Arrays.copyOf(printerIds2, printerIds2.length), callback);
    }

    @Override
    public void jobs(UUID[] printerIds, int index, int pageSize, Function1<? super List<PrinterWithJobs>, Unit> callback) {
        Intrinsics.checkNotNullParameter(printerIds, "printerIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f813a.invoke("Call jobHistory() with printer ids: " + ArraysKt.joinToString$default(printerIds, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
        C1707h2 c1707h2 = this.f814b;
        UUID[] printerIds2 = (UUID[]) Arrays.copyOf(printerIds, printerIds.length);
        c1707h2.getClass();
        Intrinsics.checkNotNullParameter(printerIds2, "printerIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        c1707h2.f1285e.jobs((UUID[]) Arrays.copyOf(printerIds2, printerIds2.length), index * pageSize, pageSize, callback);
    }

    @Override
    public void onCleared() {
        super.onCleared();
        this.f813a.invoke("Call onCleared()");
        this.f814b.f1286f.close();
    }

    @Override
    public void printer(Device device, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f814b.printer(device, callback);
    }

    @Override
    public LiveData<List<HpPrinter>> printers() {
        this.f813a.invoke("Call printers()");
        return this.f814b.printers();
    }

    @Override
    public void reCheckAllUSBDevices() {
        this.f814b.getClass();
        CommonLibKt.CommonLib().getServicesBrowser().reCheckAllUSBDevices();
    }

    @Override
    public void refreshPrinter(HpPrinter printer, List<String> requested, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(requested, "requested");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f813a.invoke("Call refreshPrinter() with printer id: " + printer.getId() + " , requested " + requested);
        this.f814b.refreshPrinter(printer, requested, callback);
    }

    @Override
    public void refreshPrinter(HpPrinter printer, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f813a.invoke("Call refreshPrinter() with printer id: " + printer.getId());
        this.f814b.refreshPrinter(printer, callback);
    }

    @Override
    public void refreshPrinterList() {
        this.f813a.invoke("Call refreshPrinterList()");
        C1787x3 c1787x3 = this.f814b.f1286f;
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
        this.f814b.f1286f.m685b();
    }

    @Override
    public void wakeUp(UUID printerId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        C1707h2 c1707h2 = this.f814b;
        c1707h2.getClass();
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        BuildersKt__Builders_commonKt.launch$default(c1707h2.f1281a, Dispatchers.getIO(), null, c1707h2.new c(printerId, null), 2, null);
    }

    @Override
    public void cancelPrintJob(UUID printerId, UUID jobId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(jobId, "jobId");
        this.f813a.invoke("Call cancelPrintJob() with printer id: " + printerId + ", job id: " + jobId);
        C1707h2 c1707h2 = this.f814b;
        c1707h2.getClass();
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(jobId, "jobId");
        C1787x3 c1787x3 = c1707h2.f1286f;
        c1787x3.getClass();
        Intrinsics.checkNotNullParameter(jobId, "jobId");
        BuildersKt__Builders_commonKt.launch$default(c1787x3.f1365a, Dispatchers.getIO(), null, new C1712i2(c1787x3, jobId, null), 2, null);
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.lifecycle.LiveData<com.p020hp.open.printsdk.HpPrintJob> print(com.p020hp.open.printsdk.HpPrintRequest r9) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.open.printsdk.SdkViewModel.print(com.hp.open.printsdk.HpPrintRequest):androidx.lifecycle.LiveData");
    }
}
