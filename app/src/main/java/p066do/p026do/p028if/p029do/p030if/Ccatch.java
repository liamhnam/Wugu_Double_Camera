package p066do.p026do.p028if.p029do.p030if;

import com.p020hp.jipp.model.PowerState;
import com.p020hp.mobile.common.usb.UsbBgService;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB$stop$2$1", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {}, m1307l = {PowerState.Code.noChange}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class Ccatch extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public final UsbBgService.Connection f3936for;

    public int f2514if;

    public final Cgoto f3937new;

    @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB$stop$2$1$1", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class C2075do extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public final Cgoto f3938for;

        public final UsbBgService.Connection f2515if;

        public C2075do(UsbBgService.Connection connection, Cgoto cgoto, Continuation<? super C2075do> continuation) {
            super(2, continuation);
            this.f2515if = connection;
            this.f3938for = cgoto;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C2075do(this.f2515if, this.f3938for, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new C2075do(this.f2515if, this.f3938for, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            this.f2515if.getService().getLiveDataForUsbDevice().removeObserver(this.f3938for.f3979this);
            this.f2515if.close();
            return Unit.INSTANCE;
        }
    }

    public Ccatch(UsbBgService.Connection connection, Cgoto cgoto, Continuation<? super Ccatch> continuation) {
        super(2, continuation);
        this.f3936for = connection;
        this.f3937new = cgoto;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Ccatch(this.f3936for, this.f3937new, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new Ccatch(this.f3936for, this.f3937new, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f2514if;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C2075do c2075do = new C2075do(this.f3936for, this.f3937new, null);
            this.f2514if = 1;
            if (BuildersKt.withContext(main, c2075do, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
