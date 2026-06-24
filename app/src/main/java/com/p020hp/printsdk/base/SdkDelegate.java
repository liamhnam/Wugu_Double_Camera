package com.p020hp.printsdk.base;

import androidx.lifecycle.LiveData;
import com.arthenica.ffmpegkit.StreamInformation;
import com.p020hp.mobile.common.browsing.Device;
import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrintRequest;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.open.printsdk.PrinterWithJobs;
import com.p020hp.open.printsdk.state.printer.HpCalibrationState;
import com.proembed.service.Constant;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(m1292d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J;\u0010\u0007\u001a\u00020\u00032\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t\"\u00020\u00052\u0018\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\u00030\u000bH&¢\u0006\u0002\u0010\u000eJ5\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112#\u0010\n\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00030\u000bH&J&\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00112\u0014\u0010\n\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u0004\u0012\u00020\u00030\u000bH&J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J;\u0010\u001c\u001a\u00020\u00032\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t\"\u00020\u00052\u0018\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\u00030\u000bH&¢\u0006\u0002\u0010\u000eJM\u0010\u001e\u001a\u00020\u00032\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t\"\u00020\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u001a\u0010\n\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0004\u0012\u00020\u00030\u000bH&¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010$\u001a\u00020%H&J5\u0010\u0015\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'2#\u0010\n\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00030\u000bH&J\u0014\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\f0\u001aH&J\b\u0010)\u001a\u00020\u0003H&J5\u0010*\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00122#\u0010\n\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00030\u000bH&JE\u0010*\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00122\u000e\b\u0002\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110\f2#\u0010\n\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00030\u000bH&J\b\u0010,\u001a\u00020\u0003H&J\b\u0010-\u001a\u00020\u0003H&J\u0010\u0010.\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006/"}, m1293d2 = {"Lcom/hp/printsdk/base/SdkDelegate;", "", "cancelPrintJob", "", "printerId", "Ljava/util/UUID;", "jobId", "delDoneJobHistory", "jobIds", "", "callback", "Lkotlin/Function1;", "", "Lcom/hp/open/printsdk/PrinterWithJobs;", "([Ljava/util/UUID;Lkotlin/jvm/functions/Function1;)V", "getIpPrinter", Constant.ETH_SET_IP, "", "Lcom/hp/open/printsdk/HpPrinter;", "Lkotlin/ParameterName;", NamingTable.TAG, MqttCmdEnum.C2S_PRINTER_ERROR, "getPrinterCalibrationStatus", "ipAddress", "Lcom/hp/open/printsdk/state/printer/HpCalibrationState;", "job", "Landroidx/lifecycle/LiveData;", "Lcom/hp/open/printsdk/HpPrintJob;", "jobHistory", "printerIds", "jobs", StreamInformation.KEY_INDEX, "", "pageSize", "([Ljava/util/UUID;IILkotlin/jvm/functions/Function1;)V", "print", "request", "Lcom/hp/open/printsdk/HpPrintRequest;", "device", "Lcom/hp/mobile/common/browsing/Device;", "printers", "reCheckAllUSBDevices", "refreshPrinter", "requested", "refreshPrinterList", "stopDiscoverPrinters", "wakeUp", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public interface SdkDelegate {
    void cancelPrintJob(UUID printerId, UUID jobId);

    void delDoneJobHistory(UUID[] jobIds, Function1<? super List<PrinterWithJobs>, Unit> callback);

    void getIpPrinter(String ip, Function1<? super HpPrinter, Unit> callback);

    void getPrinterCalibrationStatus(String ipAddress, Function1<? super HpCalibrationState, Unit> callback);

    LiveData<HpPrintJob> job(UUID printerId, UUID jobId);

    void jobHistory(UUID[] printerIds, Function1<? super List<PrinterWithJobs>, Unit> callback);

    void jobs(UUID[] printerIds, int index, int pageSize, Function1<? super List<PrinterWithJobs>, Unit> callback);

    LiveData<HpPrintJob> print(HpPrintRequest request);

    void printer(Device device, Function1<? super HpPrinter, Unit> callback);

    LiveData<List<HpPrinter>> printers();

    void reCheckAllUSBDevices();

    void refreshPrinter(HpPrinter printer, List<String> requested, Function1<? super HpPrinter, Unit> callback);

    void refreshPrinter(HpPrinter printer, Function1<? super HpPrinter, Unit> callback);

    void refreshPrinterList();

    void stopDiscoverPrinters();

    void wakeUp(UUID printerId);
}
