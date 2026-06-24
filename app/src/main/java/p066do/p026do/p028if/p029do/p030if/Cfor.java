package p066do.p026do.p028if.p029do.p030if;

import android.net.nsd.NsdServiceInfo;
import com.google.gson.Gson;
import com.p020hp.mobile.common.browsing.ConnectionType;
import com.p020hp.mobile.common.browsing.NsAttributeKey;
import com.p020hp.mobile.common.browsing.ServiceType;
import com.p020hp.mobile.common.identity.DeviceIdentity;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import p066do.p026do.p028if.p029do.p069for.AbstractC2071do;

public final class Cfor {

    public DeviceIdentity f3957case;

    public final String f2527do;

    public final List<C2078do> f3958else;

    public final Logger f3959for;

    public final List<C2078do> f3960goto;

    public final InterfaceC2073a f2528if;

    public AbstractC2071do f3961new;

    public final List<C2078do> f3962this;

    public boolean f3963try;

    public static final class C2078do {

        public final NsdServiceInfo f2529do;

        public boolean f2530if;

        public C2078do(NsdServiceInfo nsdServiceInfo) {
            Intrinsics.checkNotNullParameter(nsdServiceInfo, "nsdServiceInfo");
            this.f2529do = nsdServiceInfo;
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceBundle", m1305f = "ServiceBundle.kt", m1306i = {0, 0}, m1307l = {117}, m1308m = "addService", m1309n = {"this", "serviceInfo"}, m1310s = {"L$0", "L$1"})
    public static final class C3382for extends ContinuationImpl {

        public int f3964case;

        public Object f3965for;

        public Object f2531if;

        public Object f3966new;

        public C3382for(Continuation<? super C3382for> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f3966new = obj;
            this.f3964case |= Integer.MIN_VALUE;
            return Cfor.this.m1236do(null, this);
        }
    }

    public class C2079if {

        public static final int[] f2532do;

        static {
            int[] iArr = new int[ServiceType.values().length];
            ServiceType serviceType = ServiceType.IPP;
            iArr[4] = 1;
            ServiceType serviceType2 = ServiceType.IPP_SECURE;
            iArr[5] = 2;
            ServiceType serviceType3 = ServiceType.ESCL;
            iArr[0] = 3;
            ServiceType serviceType4 = ServiceType.ESCL_SECURE;
            iArr[1] = 4;
            ServiceType serviceType5 = ServiceType.LEDM;
            iArr[2] = 5;
            ServiceType serviceType6 = ServiceType.CDM;
            iArr[3] = 6;
            f2532do = iArr;
        }
    }

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceBundle", m1305f = "ServiceBundle.kt", m1306i = {0, 0, 0, 1, 1, 1}, m1307l = {163, 164}, m1308m = "getDeviceIdentityFromService", m1309n = {"this", "serviceInfo", "serviceType", "this", "serviceInfo", "serviceType"}, m1310s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    public static final class Cnew extends ContinuationImpl {

        public int f3969else;

        public Object f3970for;

        public Object f2533if;

        public Object f3971new;

        public Object f3972try;

        public Cnew(Continuation<? super Cnew> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f3972try = obj;
            this.f3969else |= Integer.MIN_VALUE;
            return Cfor.this.m1239if(null, this);
        }
    }

    public Cfor(String serviceName, InterfaceC2073a socketFactoryProvider) {
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        Intrinsics.checkNotNullParameter(socketFactoryProvider, "socketFactoryProvider");
        this.f2527do = serviceName;
        this.f2528if = socketFactoryProvider;
        this.f3959for = LoggerKt.logger(LoggerKt.toTag("ServiceBundle"));
        this.f3957case = C2076do.m1221do();
        this.f3958else = new ArrayList();
        this.f3960goto = new ArrayList();
        this.f3962this = new ArrayList();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1236do(android.net.nsd.NsdServiceInfo r6, kotlin.coroutines.Continuation<? super java.util.List<android.net.nsd.NsdServiceInfo>> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof p066do.p026do.p028if.p029do.p030if.Cfor.C3382for
            if (r0 == 0) goto L13
            r0 = r7
            do.do.if.do.if.for$for r0 = (p066do.p026do.p028if.p029do.p030if.Cfor.C3382for) r0
            int r1 = r0.f3964case
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f3964case = r1
            goto L18
        L13:
            do.do.if.do.if.for$for r0 = new do.do.if.do.if.for$for
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f3966new
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f3964case
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r6 = r0.f3965for
            android.net.nsd.NsdServiceInfo r6 = (android.net.nsd.NsdServiceInfo) r6
            java.lang.Object r0 = r0.f2531if
            do.do.if.do.if.for r0 = (p066do.p026do.p028if.p029do.p030if.Cfor) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L62
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r7)
            com.hp.mobile.common.utils.Logger r7 = r5.f3959for
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "DISCO-FLOW addService() "
            r2.<init>(r4)
            java.lang.String r4 = p066do.p026do.p028if.p029do.p030if.C2076do.m1758new(r6)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r7.m446d(r2)
            r0.f2531if = r5
            r0.f3965for = r6
            r0.f3964case = r3
            java.lang.Object r7 = r5.m1239if(r6, r0)
            if (r7 != r1) goto L61
            return r1
        L61:
            r0 = r5
        L62:
            android.net.nsd.NsdServiceInfo r7 = (android.net.nsd.NsdServiceInfo) r7
            if (r7 == 0) goto L98
            do.do.if.do.if.for$do r1 = new do.do.if.do.if.for$do
            r1.<init>(r7)
            java.lang.String r6 = r6.getServiceType()
            java.lang.String r7 = "serviceInfo.serviceType"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            com.hp.mobile.common.browsing.ServiceType r6 = p066do.p026do.p028if.p029do.p030if.C2076do.m1220do(r6)
            if (r6 != 0) goto L7d
            r6 = -1
            goto L85
        L7d:
            int[] r7 = p066do.p026do.p028if.p029do.p030if.Cfor.C2079if.f2532do
            int r6 = r6.ordinal()
            r6 = r7[r6]
        L85:
            switch(r6) {
                case 1: goto L8f;
                case 2: goto L8f;
                case 3: goto L8c;
                case 4: goto L8c;
                case 5: goto L89;
                case 6: goto L89;
                default: goto L88;
            }
        L88:
            goto L98
        L89:
            java.util.List<do.do.if.do.if.for$do> r6 = r0.f3962this
            goto L91
        L8c:
            java.util.List<do.do.if.do.if.for$do> r6 = r0.f3958else
            goto L91
        L8f:
            java.util.List<do.do.if.do.if.for$do> r6 = r0.f3960goto
        L91:
            boolean r6 = r6.add(r1)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
        L98:
            java.util.List r6 = r0.m1237do()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Cfor.m1236do(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<NsdServiceInfo> m1237do() {
        if (!(((this.f3962this.isEmpty() ^ true) || this.f3963try) && ((C2076do.m1233if() && !this.f3957case.isEmpty()) || !C2076do.m1233if()))) {
            this.f3959for.m446d("DISCO-FLOW checkIfCanReport() - currently recognized as non HP device, retained until secondary services or deviceInfo available.");
            return CollectionsKt.emptyList();
        }
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) this.f3960goto, (Iterable) this.f3958else), (Iterable) this.f3962this);
        ArrayList<C2078do> arrayList = new ArrayList();
        for (Object obj : listPlus) {
            C2078do c2078do = (C2078do) obj;
            if (!c2078do.f2530if && m1238do(c2078do.f2529do)) {
                arrayList.add(obj);
            }
        }
        for (C2078do c2078do2 : arrayList) {
            if (!c2078do2.f2530if) {
                c2078do2.f2530if = true;
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (C2078do c2078do3 : arrayList) {
            NsdServiceInfo nsdServiceInfo = c2078do3.f2529do;
            if (!this.f3957case.isEmpty()) {
                this.f3959for.m446d("DISCO-FLOW checkIfCanReport() -  add necessary attributes for " + C2076do.m1758new(c2078do3.f2529do));
                String key = NsAttributeKey.PRINTER_IDENTITY.getKey();
                DeviceIdentity deviceIdentity = this.f3957case;
                Intrinsics.checkNotNullParameter(deviceIdentity, "<this>");
                nsdServiceInfo.setAttribute(key, deviceIdentity.isEmpty() ? null : new Gson().toJson(deviceIdentity));
                if (!this.f3963try && Intrinsics.areEqual(C2076do.m1223do(nsdServiceInfo, NsAttributeKey.CONNECT_TYPE.getKey()), ConnectionType.IP.getType())) {
                    nsdServiceInfo.setAttribute(NsAttributeKey.MAKE_AND_MODEL.getKey(), this.f3957case.getModel());
                    nsdServiceInfo.setAttribute(NsAttributeKey.UUID.getKey(), this.f3957case.getUuid());
                }
            }
            arrayList2.add(nsdServiceInfo);
        }
        Logger logger = this.f3959for;
        StringBuilder sb = new StringBuilder("DISCO-FLOW checkIfCanReport() -  found ");
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(C2076do.m1758new((NsdServiceInfo) it.next()));
        }
        logger.m446d(sb.append(arrayList3).append(" services to report").toString());
        return arrayList2;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1239if(android.net.nsd.NsdServiceInfo r10, kotlin.coroutines.Continuation<? super android.net.nsd.NsdServiceInfo> r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 493
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Cfor.m1239if(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean m1238do(NsdServiceInfo nsdServiceInfo) {
        boolean zM1234if;
        String serviceType = nsdServiceInfo.getServiceType();
        Intrinsics.checkNotNullExpressionValue(serviceType, "this.serviceType");
        ServiceType serviceTypeM1220do = C2076do.m1220do(serviceType);
        switch (serviceTypeM1220do == null ? -1 : C2079if.f2532do[serviceTypeM1220do.ordinal()]) {
            case -1:
                zM1234if = true;
                break;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
            case 2:
            case 3:
            case 4:
                zM1234if = C2076do.m1234if(nsdServiceInfo);
                break;
            case 5:
            case 6:
                zM1234if = false;
                break;
        }
        return !zM1234if;
    }
}
