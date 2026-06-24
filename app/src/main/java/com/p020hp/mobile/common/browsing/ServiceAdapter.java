package com.p020hp.mobile.common.browsing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

@Metadata(m1292d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\fJ\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0013\u0010\u0012\u001a\u00020\u0013H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0006H\u0017J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0006H\u0017J\b\u0010\u0019\u001a\u00020\u001aH'J1\u0010\u001b\u001a\u00020\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J1\u0010!\u001a\u00020\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0080@ø\u0001\u0000¢\u0006\u0004\b\"\u0010 R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/ServiceAdapter;", "", "()V", "flowForDevices", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "Lcom/hp/mobile/common/browsing/Device;", "liveDataForDevices", "Landroidx/lifecycle/MutableLiveData;", "log", "Lcom/hp/mobile/common/utils/Logger;", "asFlow", "Lkotlinx/coroutines/flow/Flow;", "asLiveData", "Landroidx/lifecycle/LiveData;", "equals", "", "other", "notifyClear", "", "notifyClear$common_lib_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onFound", "device", "onLost", "serviceGroup", "Lcom/hp/mobile/common/browsing/ServicesGroup;", "superOnFound", "devices", "serviceInfo", "Lcom/hp/mobile/common/browsing/ServiceInfo;", "superOnFound$common_lib_release", "(Ljava/util/List;Lcom/hp/mobile/common/browsing/Device;Lcom/hp/mobile/common/browsing/ServiceInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "superOnLost", "superOnLost$common_lib_release", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class ServiceAdapter {
    public final Logger log = new Logger(LoggerKt.toTag("ServiceAdapter"));
    public final MutableLiveData<List<Device>> liveDataForDevices = new MutableLiveData<>();
    public final MutableSharedFlow<List<Device>> flowForDevices = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);

    public final Flow<List<Device>> asFlow() {
        return this.flowForDevices;
    }

    public final LiveData<List<Device>> asLiveData() {
        return this.liveDataForDevices;
    }

    public boolean equals(Object other) {
        ServiceAdapter serviceAdapter = other instanceof ServiceAdapter ? (ServiceAdapter) other : null;
        return (serviceAdapter != null ? serviceAdapter.serviceGroup() : null) == serviceGroup();
    }

    public final Object notifyClear$common_lib_release(Continuation<? super Unit> continuation) {
        List<Device> value = this.liveDataForDevices.getValue();
        if (value == null || value.isEmpty()) {
            return Unit.INSTANCE;
        }
        this.log.m446d("DISCO-FLOW notifyClear()");
        this.liveDataForDevices.setValue(CollectionsKt.emptyList());
        Object objEmit = this.flowForDevices.emit(CollectionsKt.emptyList(), continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public void onFound(Device device) {
        Intrinsics.checkNotNullParameter(device, "device");
    }

    public void onLost(Device device) {
        Intrinsics.checkNotNullParameter(device, "device");
    }

    public abstract ServicesGroup serviceGroup();

    public final Object superOnFound$common_lib_release(List<Device> list, Device device, ServiceInfo serviceInfo, Continuation<? super Unit> continuation) {
        this.log.m446d("DISCO-FLOW superOnFound() - " + list.size() + " devices, for " + device.getMakeAndModel() + " with " + ServiceInfoKt.identifier(serviceInfo));
        onFound(device);
        this.liveDataForDevices.setValue(list);
        Object objEmit = this.flowForDevices.emit(list, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public final Object superOnLost$common_lib_release(List<Device> list, Device device, ServiceInfo serviceInfo, Continuation<? super Unit> continuation) {
        this.log.m446d("DISCO-FLOW superOnLost() - " + list.size() + " devices, for " + device.getMakeAndModel() + " with " + ServiceInfoKt.identifier(serviceInfo));
        onLost(device);
        this.liveDataForDevices.setValue(list);
        Object objEmit = this.flowForDevices.emit(list, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }
}
