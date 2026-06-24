package p066do.p026do.p028if.p029do.p069for;

import android.net.nsd.NsdServiceInfo;
import com.p020hp.mobile.common.httpclient.HttpClients;
import com.p020hp.mobile.common.httpclient.HttpClientsKt;
import com.p020hp.mobile.common.identity.DeviceIdentity;
import com.p020hp.mobile.common.usb.IppUsbSocketFactory;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import javax.net.SocketFactory;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import p066do.p026do.p028if.p029do.p070new.p031do.InterfaceC2084do;
import p066do.p026do.p028if.p029do.p070new.p032if.InterfaceC2085do;
import retrofit2.Retrofit;

public abstract class AbstractC2071do {

    public static final Cdo f2503do = new Cdo();

    public static final Logger f2504if = LoggerKt.logger(LoggerKt.toTag("DeviceIdentity"));

    public static final class Cdo {

        public static final class C3377do extends AbstractC2071do {

            public final int f3918for = System.identityHashCode(this);

            public final InterfaceC2085do f3919new;

            public final InterfaceC2084do f3920try;

            @DebugMetadata(m1304c = "com.hp.mobile.common.identity.DeviceIdentityResolver$Companion$create$1", m1305f = "DeviceIdentityResolver.kt", m1306i = {0, 0}, m1307l = {114, 132}, m1308m = "getDeviceIdentityFromCdm", m1309n = {"this", "nsdServiceInfo"}, m1310s = {"L$0", "L$1"})
            public static final class C3378do extends ContinuationImpl {

                public int f3921case;

                public Object f3922for;

                public Object f2505if;

                public Object f3923new;

                public C3378do(Continuation<? super C3378do> continuation) {
                    super(continuation);
                }

                @Override
                public final Object invokeSuspend(Object obj) {
                    this.f3923new = obj;
                    this.f3921case |= Integer.MIN_VALUE;
                    return C3377do.this.mo1210do(null, this);
                }
            }

            @DebugMetadata(m1304c = "com.hp.mobile.common.identity.DeviceIdentityResolver$Companion$create$1", m1305f = "DeviceIdentityResolver.kt", m1306i = {0}, m1307l = {93}, m1308m = "getDeviceIdentityFromLedm", m1309n = {"this"}, m1310s = {"L$0"})
            public static final class Cif extends ContinuationImpl {

                public Object f3925for;

                public Object f2506if;

                public int f3927try;

                public Cif(Continuation<? super Cif> continuation) {
                    super(continuation);
                }

                @Override
                public final Object invokeSuspend(Object obj) {
                    this.f3925for = obj;
                    this.f3927try |= Integer.MIN_VALUE;
                    return C3377do.this.mo1211if(null, this);
                }
            }

            public C3377do(InterfaceC2085do interfaceC2085do, InterfaceC2084do interfaceC2084do) {
                this.f3919new = interfaceC2085do;
                this.f3920try = interfaceC2084do;
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Object mo1210do(android.net.nsd.NsdServiceInfo r14, kotlin.coroutines.Continuation<? super com.p020hp.mobile.common.identity.DeviceIdentity> r15) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 443
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p069for.AbstractC2071do.Cdo.C3377do.mo1210do(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Object mo1211if(android.net.nsd.NsdServiceInfo r8, kotlin.coroutines.Continuation<? super com.p020hp.mobile.common.identity.DeviceIdentity> r9) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 230
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p069for.AbstractC2071do.Cdo.C3377do.mo1211if(android.net.nsd.NsdServiceInfo, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public final AbstractC2071do m1212do(SocketFactory socketFactory) {
            Intrinsics.checkNotNullParameter(socketFactory, "socketFactory");
            Retrofit retrofitBuild = new Retrofit.Builder().client(HttpClientsKt.setTimeouts(socketFactory instanceof IppUsbSocketFactory ? HttpClients.INSTANCE.getUsbClient().newBuilder().socketFactory(socketFactory) : HttpClients.INSTANCE.getLanUnsecureClient().newBuilder(), 120L).build()).baseUrl("http://127.0.0.1").addConverterFactory(new C2072if()).build();
            Object objCreate = retrofitBuild.create(InterfaceC2085do.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(LEDMApi::class.java)");
            Object objCreate2 = retrofitBuild.create(InterfaceC2084do.class);
            Intrinsics.checkNotNullExpressionValue(objCreate2, "retrofit.create(CDMApi::class.java)");
            return new C3377do((InterfaceC2085do) objCreate, (InterfaceC2084do) objCreate2);
        }
    }

    public abstract Object mo1210do(NsdServiceInfo nsdServiceInfo, Continuation<? super DeviceIdentity> continuation);

    public abstract Object mo1211if(NsdServiceInfo nsdServiceInfo, Continuation<? super DeviceIdentity> continuation);
}
