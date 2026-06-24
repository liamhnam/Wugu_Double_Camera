package p066do.p026do.p028if.p029do.p030if;

import com.p020hp.mobile.common.usb.UsbBgService;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB$start$2", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {0, 1, 2}, m1307l = {UiPosIndexEnum.PAYMENT_KEYBOARD_POS, 78, 80}, m1308m = "invokeSuspend", m1309n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, m1310s = {"L$0", "L$0", "L$0"})
public final class Cbreak extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public Object f3928for;

    public Object f2511if;

    public int f3929new;

    public final Cgoto f3930try;

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB$start$2$1$1$1", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class C2074do extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public final Cgoto f3931for;

        public final UsbBgService.Connection f2512if;

        public C2074do(UsbBgService.Connection connection, Cgoto cgoto, Continuation<? super C2074do> continuation) {
            super(2, continuation);
            this.f2512if = connection;
            this.f3931for = cgoto;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C2074do(this.f2512if, this.f3931for, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new C2074do(this.f2512if, this.f3931for, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            this.f2512if.getService().getLiveDataForUsbDevice().observeForever(this.f3931for.f3979this);
            return Unit.INSTANCE;
        }
    }

    public Cbreak(Cgoto cgoto, Continuation<? super Cbreak> continuation) {
        super(2, continuation);
        this.f3930try = cgoto;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Cbreak(this.f3930try, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new Cbreak(this.f3930try, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.f3929new
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L43
            if (r1 == r4) goto L37
            if (r1 == r3) goto L28
            if (r1 != r2) goto L20
            java.lang.Object r0 = r8.f3928for
            java.lang.Object r1 = r8.f2511if
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1d
            goto L8e
        L1d:
            r9 = move-exception
            goto L9c
        L20:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L28:
            java.lang.Object r1 = r8.f3928for
            do.do.if.do.if.goto r1 = (p066do.p026do.p028if.p029do.p030if.Cgoto) r1
            java.lang.Object r3 = r8.f2511if
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L92
            goto L71
        L34:
            r1 = r3
            goto L9c
        L37:
            java.lang.Object r1 = r8.f3928for
            do.do.if.do.if.goto r1 = (p066do.p026do.p028if.p029do.p030if.Cgoto) r1
            java.lang.Object r4 = r8.f2511if
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto L59
        L43:
            kotlin.ResultKt.throwOnFailure(r9)
            do.do.if.do.if.goto r9 = r8.f3930try
            kotlinx.coroutines.sync.Mutex r1 = r9.f3975else
            r8.f2511if = r1
            r8.f3928for = r9
            r8.f3929new = r4
            java.lang.Object r4 = r1.lock(r5, r8)
            if (r4 != r0) goto L57
            return r0
        L57:
            r4 = r1
            r1 = r9
        L59:
            com.hp.mobile.common.usb.UsbBgService$Connection r9 = r1.f3980try     // Catch: java.lang.Throwable -> L9a
            if (r9 != 0) goto L94
            com.hp.mobile.common.usb.UsbBgService$Companion r9 = com.p020hp.mobile.common.usb.UsbBgService.INSTANCE     // Catch: java.lang.Throwable -> L9a
            android.content.Context r6 = com.p020hp.mobile.common.CommonLibKt.context()     // Catch: java.lang.Throwable -> L9a
            r8.f2511if = r4     // Catch: java.lang.Throwable -> L9a
            r8.f3928for = r1     // Catch: java.lang.Throwable -> L9a
            r8.f3929new = r3     // Catch: java.lang.Throwable -> L9a
            java.lang.Object r9 = r9.connect(r6, r8)     // Catch: java.lang.Throwable -> L9a
            if (r9 != r0) goto L70
            return r0
        L70:
            r3 = r4
        L71:
            r4 = r9
            com.hp.mobile.common.usb.UsbBgService$Connection r4 = (com.hp.mobile.common.usb.UsbBgService.Connection) r4     // Catch: java.lang.Throwable -> L92
            r1.f3980try = r4     // Catch: java.lang.Throwable -> L92
            kotlinx.coroutines.MainCoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Throwable -> L92
            do.do.if.do.if.break$do r7 = new do.do.if.do.if.break$do     // Catch: java.lang.Throwable -> L92
            r7.<init>(r4, r1, r5)     // Catch: java.lang.Throwable -> L92
            r8.f2511if = r3     // Catch: java.lang.Throwable -> L92
            r8.f3928for = r9     // Catch: java.lang.Throwable -> L92
            r8.f3929new = r2     // Catch: java.lang.Throwable -> L92
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r8)     // Catch: java.lang.Throwable -> L92
            if (r1 != r0) goto L8c
            return r0
        L8c:
            r0 = r9
            r1 = r3
        L8e:
            com.hp.mobile.common.usb.UsbBgService$Connection r0 = (com.hp.mobile.common.usb.UsbBgService.Connection) r0     // Catch: java.lang.Throwable -> L1d
            r4 = r1
            goto L94
        L92:
            r9 = move-exception
            goto L34
        L94:
            r4.unlock(r5)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L9a:
            r9 = move-exception
            r1 = r4
        L9c:
            r1.unlock(r5)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Cbreak.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
