package p066do.p026do.p028if.p029do.p030if;

import android.net.nsd.NsdServiceInfo;
import com.p020hp.mobile.common.browsing.ServiceType;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import p066do.p026do.p028if.p029do.p030if.Cfor;

public final class Cnew {

    public final InterfaceC2073a f2546do;

    public final List<Cfor> f4012for;

    public final Logger f2547if;

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceBundler", m1305f = "ServiceBundler.kt", m1306i = {0}, m1307l = {39}, m1308m = "notifyFound", m1309n = {"serviceBundle"}, m1310s = {"L$0"})
    public static final class C2083do extends ContinuationImpl {

        public Object f4013for;

        public Object f2548if;

        public int f4015try;

        public C2083do(Continuation<? super C2083do> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f4013for = obj;
            this.f4015try |= Integer.MIN_VALUE;
            return Cnew.this.m1255do(null, this);
        }
    }

    public Cnew() {
        this(null, 1);
    }

    public Cnew(InterfaceC2073a socketFactoryProvider) {
        Intrinsics.checkNotNullParameter(socketFactoryProvider, "socketFactoryProvider");
        this.f2546do = socketFactoryProvider;
        this.f2547if = LoggerKt.logger(LoggerKt.toTag("ServiceBundler"));
        this.f4012for = new ArrayList();
    }

    public Cnew(InterfaceC2073a interfaceC2073a, int i) {
        this((i & 1) != 0 ? InterfaceC2073a.f2509do.m1214do() : null);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m1255do(android.net.nsd.NsdServiceInfo r8, kotlin.coroutines.Continuation<? super kotlin.Pair<com.p020hp.mobile.common.identity.DeviceIdentity, ? extends java.util.List<android.net.nsd.NsdServiceInfo>>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof p066do.p026do.p028if.p029do.p030if.Cnew.C2083do
            if (r0 == 0) goto L13
            r0 = r9
            do.do.if.do.if.new$do r0 = (p066do.p026do.p028if.p029do.p030if.Cnew.C2083do) r0
            int r1 = r0.f4015try
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4015try = r1
            goto L18
        L13:
            do.do.if.do.if.new$do r0 = new do.do.if.do.if.new$do
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f4013for
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f4015try
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r8 = r0.f2548if
            do.do.if.do.if.for r8 = (p066do.p026do.p028if.p029do.p030if.Cfor) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L91
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L35:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.String r9 = r8.getServiceName()
            java.util.List<do.do.if.do.if.for> r2 = r7.f4012for
            java.util.Iterator r2 = r2.iterator()
        L42:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L58
            java.lang.Object r4 = r2.next()
            r5 = r4
            do.do.if.do.if.for r5 = (p066do.p026do.p028if.p029do.p030if.Cfor) r5
            java.lang.String r5 = r5.f2527do
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r9)
            if (r5 == 0) goto L42
            goto L59
        L58:
            r4 = 0
        L59:
            do.do.if.do.if.for r4 = (p066do.p026do.p028if.p029do.p030if.Cfor) r4
            if (r4 != 0) goto L84
            do.do.if.do.if.for r2 = new do.do.if.do.if.for
            java.lang.String r4 = "serviceName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r4)
            do.do.if.do.if.a r4 = r7.f2546do
            r2.<init>(r9, r4)
            com.hp.mobile.common.utils.Logger r4 = r7.f2547if
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "DISCO-FLOW notifyFound() - create new ServiceBundle for "
            r5.<init>(r6)
            java.lang.StringBuilder r9 = r5.append(r9)
            java.lang.String r9 = r9.toString()
            r4.m446d(r9)
            java.util.List<do.do.if.do.if.for> r9 = r7.f4012for
            r9.add(r2)
            goto L85
        L84:
            r2 = r4
        L85:
            r0.f2548if = r2
            r0.f4015try = r3
            java.lang.Object r9 = r2.m1236do(r8, r0)
            if (r9 != r1) goto L90
            return r1
        L90:
            r8 = r2
        L91:
            java.util.List r9 = (java.util.List) r9
            com.hp.mobile.common.identity.DeviceIdentity r8 = r8.f3957case
            kotlin.Pair r0 = new kotlin.Pair
            r0.<init>(r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Cnew.m1255do(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean m1256do(NsdServiceInfo nsdServiceInfo) {
        Object obj;
        Object next;
        Intrinsics.checkNotNullParameter(nsdServiceInfo, "nsdServiceInfo");
        Iterator<T> it = this.f4012for.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((Cfor) next).f2527do, nsdServiceInfo.getServiceName())) {
                break;
            }
        }
        Cfor cfor = (Cfor) next;
        if (cfor != null) {
            Intrinsics.checkNotNullParameter(nsdServiceInfo, "nsdServiceInfo");
            String serviceType = nsdServiceInfo.getServiceType();
            Intrinsics.checkNotNullExpressionValue(serviceType, "nsdServiceInfo.serviceType");
            ServiceType serviceTypeM1220do = C2076do.m1220do(serviceType);
            switch (serviceTypeM1220do == null ? -1 : Cfor.C2079if.f2532do[serviceTypeM1220do.ordinal()]) {
                case 1:
                case 2:
                    Iterator<T> it2 = cfor.f3960goto.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Object next2 = it2.next();
                            if (Intrinsics.areEqual(C2076do.m1758new(((Cfor.C2078do) next2).f2529do), C2076do.m1758new(nsdServiceInfo))) {
                                obj = next2;
                            }
                        }
                    }
                    Cfor.C2078do c2078do = (Cfor.C2078do) obj;
                    if (c2078do != null) {
                        return cfor.f3960goto.remove(c2078do);
                    }
                    break;
                case 3:
                case 4:
                    Iterator<T> it3 = cfor.f3958else.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            Object next3 = it3.next();
                            if (Intrinsics.areEqual(C2076do.m1758new(((Cfor.C2078do) next3).f2529do), C2076do.m1758new(nsdServiceInfo))) {
                                obj = next3;
                            }
                        }
                    }
                    Cfor.C2078do c2078do2 = (Cfor.C2078do) obj;
                    if (c2078do2 != null) {
                        return cfor.f3958else.remove(c2078do2);
                    }
                    break;
                case 5:
                case 6:
                    Iterator<T> it4 = cfor.f3962this.iterator();
                    while (true) {
                        if (it4.hasNext()) {
                            Object next4 = it4.next();
                            if (Intrinsics.areEqual(C2076do.m1758new(((Cfor.C2078do) next4).f2529do), C2076do.m1758new(nsdServiceInfo))) {
                                obj = next4;
                            }
                        }
                    }
                    Cfor.C2078do c2078do3 = (Cfor.C2078do) obj;
                    if (c2078do3 != null) {
                        return cfor.f3962this.remove(c2078do3);
                    }
                    break;
            }
        }
        return false;
    }
}
