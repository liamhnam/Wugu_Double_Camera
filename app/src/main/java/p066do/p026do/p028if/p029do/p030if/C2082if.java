package p066do.p026do.p028if.p029do.p030if;

import android.net.nsd.NsdServiceInfo;
import com.p020hp.mobile.common.browsing.Device;
import com.p020hp.mobile.common.browsing.ServiceAdapter;
import com.p020hp.mobile.common.browsing.ServiceInfo;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

public final class C2082if {

    public final ServiceAdapter f2541do;

    public final Map<String, Device> f4002for;

    public final Logger f2542if;

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceAdapterHolder", m1305f = "ServiceAdapterHolder.kt", m1306i = {0}, m1307l = {56}, m1308m = "reportFound", m1309n = {"device"}, m1310s = {"L$0"})
    public static final class Cdo extends ContinuationImpl {

        public Object f4003for;

        public Object f2543if;

        public int f4005try;

        public Cdo(Continuation<? super Cdo> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f4003for = obj;
            this.f4005try |= Integer.MIN_VALUE;
            return C2082if.this.m1250do((NsdServiceInfo) null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceAdapterHolder", m1305f = "ServiceAdapterHolder.kt", m1306i = {}, m1307l = {UiPosIndexEnum.HOME_REPLACE_BG_TAB}, m1308m = "reportLost", m1309n = {}, m1310s = {})
    public static final class Cfor extends ContinuationImpl {

        public Object f2544if;

        public int f4007new;

        public Cfor(Continuation<? super Cfor> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f2544if = obj;
            this.f4007new |= Integer.MIN_VALUE;
            return C2082if.this.m1254if(null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceAdapterHolder", m1305f = "ServiceAdapterHolder.kt", m1306i = {0}, m1307l = {82}, m1308m = "reportFound", m1309n = {"this"}, m1310s = {"L$0"})
    public static final class Cif extends ContinuationImpl {

        public int f4008case;

        public Object f4009for;

        public Object f2545if;

        public Object f4010new;

        public Cif(Continuation<? super Cif> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f4010new = obj;
            this.f4008case |= Integer.MIN_VALUE;
            return C2082if.this.m1251do((List<ServiceInfo>) null, this);
        }
    }

    public C2082if(ServiceAdapter adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.f2541do = adapter;
        this.f2542if = new Logger(LoggerKt.toTag("ServiceAdapterHolder"));
        this.f4002for = new LinkedHashMap();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1250do(android.net.nsd.NsdServiceInfo r8, kotlin.coroutines.Continuation<? super com.p020hp.mobile.common.browsing.Device> r9) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.C2082if.m1250do(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1251do(java.util.List<com.p020hp.mobile.common.browsing.ServiceInfo> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r9 instanceof p066do.p026do.p028if.p029do.p030if.C2082if.Cif
            if (r0 == 0) goto L13
            r0 = r9
            do.do.if.do.if.if$if r0 = (p066do.p026do.p028if.p029do.p030if.C2082if.Cif) r0
            int r1 = r0.f4008case
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4008case = r1
            goto L18
        L13:
            do.do.if.do.if.if$if r0 = new do.do.if.do.if.if$if
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f4010new
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f4008case
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r8 = r0.f4009for
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r2 = r0.f2545if
            do.do.if.do.if.if r2 = (p066do.p026do.p028if.p029do.p030if.C2082if) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L41
        L31:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L39:
            kotlin.ResultKt.throwOnFailure(r9)
            java.util.Iterator r8 = r8.iterator()
            r2 = r7
        L41:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L89
            java.lang.Object r9 = r8.next()
            com.hp.mobile.common.browsing.ServiceInfo r9 = (com.p020hp.mobile.common.browsing.ServiceInfo) r9
            java.util.Map<java.lang.String, com.hp.mobile.common.browsing.Device> r4 = r2.f4002for
            java.lang.String r5 = com.p020hp.mobile.common.browsing.ServiceInfoKt.idPerConnection(r9)
            java.lang.Object r4 = r4.get(r5)
            com.hp.mobile.common.browsing.Device r4 = (com.p020hp.mobile.common.browsing.Device) r4
            if (r4 == 0) goto L5f
            r4.addService$common_lib_release(r9)
            goto L41
        L5f:
            java.lang.String r4 = com.p020hp.mobile.common.browsing.ServiceInfoKt.idPerConnection(r9)
            com.hp.mobile.common.browsing.Device r5 = new com.hp.mobile.common.browsing.Device
            r5.<init>()
            r5.addService$common_lib_release(r9)
            java.util.Map<java.lang.String, com.hp.mobile.common.browsing.Device> r6 = r2.f4002for
            r6.put(r4, r5)
            com.hp.mobile.common.browsing.ServiceAdapter r4 = r2.f2541do
            java.util.Map<java.lang.String, com.hp.mobile.common.browsing.Device> r6 = r2.f4002for
            java.util.Collection r6 = r6.values()
            java.util.List r6 = kotlin.collections.CollectionsKt.toList(r6)
            r0.f2545if = r2
            r0.f4009for = r8
            r0.f4008case = r3
            java.lang.Object r9 = r4.superOnFound$common_lib_release(r6, r5, r9, r0)
            if (r9 != r1) goto L41
            return r1
        L89:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.C2082if.m1251do(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<String> m1252do() {
        return ArraysKt.asList(this.f2541do.serviceGroup().getServiceTypes());
    }

    public final synchronized List<ServiceInfo> m1253do(List<String> serviceTypes) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(serviceTypes, "serviceTypes");
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = this.f4002for.values().iterator();
        while (it.hasNext()) {
            arrayList2.addAll(((Device) it.next()).cachedServices$common_lib_release());
        }
        arrayList = new ArrayList();
        for (Object obj : arrayList2) {
            if (serviceTypes.contains(((ServiceInfo) obj).getServiceType())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1254if(android.net.nsd.NsdServiceInfo r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof p066do.p026do.p028if.p029do.p030if.C2082if.Cfor
            if (r0 == 0) goto L13
            r0 = r7
            do.do.if.do.if.if$for r0 = (p066do.p026do.p028if.p029do.p030if.C2082if.Cfor) r0
            int r1 = r0.f4007new
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4007new = r1
            goto L18
        L13:
            do.do.if.do.if.if$for r0 = new do.do.if.do.if.if$for
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f2544if
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f4007new
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r7)
            goto L76
        L29:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L31:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r7 = p066do.p026do.p028if.p029do.p030if.C2076do.m1757for(r6)
            if (r7 == 0) goto L76
            java.util.Map<java.lang.String, com.hp.mobile.common.browsing.Device> r2 = r5.f4002for
            java.lang.Object r7 = r2.get(r7)
            com.hp.mobile.common.browsing.Device r7 = (com.p020hp.mobile.common.browsing.Device) r7
            if (r7 == 0) goto L76
            java.lang.String r2 = p066do.p026do.p028if.p029do.p030if.C2076do.m1758new(r6)
            com.hp.mobile.common.browsing.ServiceInfo r2 = r7.removeService$common_lib_release(r2)
            if (r2 == 0) goto L76
            boolean r4 = com.p020hp.mobile.common.browsing.DeviceKt.isEmpty(r7)
            if (r4 == 0) goto L61
            java.util.Map<java.lang.String, com.hp.mobile.common.browsing.Device> r4 = r5.f4002for
            java.lang.String r6 = p066do.p026do.p028if.p029do.p030if.C2076do.m1757for(r6)
            java.util.Map r4 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r4)
            r4.remove(r6)
        L61:
            com.hp.mobile.common.browsing.ServiceAdapter r6 = r5.f2541do
            java.util.Map<java.lang.String, com.hp.mobile.common.browsing.Device> r4 = r5.f4002for
            java.util.Collection r4 = r4.values()
            java.util.List r4 = kotlin.collections.CollectionsKt.toList(r4)
            r0.f4007new = r3
            java.lang.Object r6 = r6.superOnLost$common_lib_release(r4, r7, r2, r0)
            if (r6 != r1) goto L76
            return r1
        L76:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.C2082if.m1254if(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
