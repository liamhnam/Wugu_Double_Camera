package p066do.p026do.p028if.p029do.p030if;

import com.p020hp.mobile.common.browsing.ServiceType;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import p066do.p026do.p028if.p029do.p030if.Cgoto;

@DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryUSB$start$1$1$1", m1305f = "ServiceDiscoveryUSB.kt", m1306i = {}, m1307l = {69}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class Cthis extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public final Cgoto f4016for;

    public int f2549if;

    public final ServiceType f4017new;

    public Cthis(Cgoto cgoto, ServiceType serviceType, Continuation<? super Cthis> continuation) {
        super(2, continuation);
        this.f4016for = cgoto;
        this.f4017new = serviceType;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Cthis(this.f4016for, this.f4017new, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new Cthis(this.f4016for, this.f4017new, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f2549if;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Cgoto cgoto = this.f4016for;
            ServiceType serviceType = this.f4017new;
            List<Cgoto.C2080do> list = cgoto.f3973break;
            this.f2549if = 1;
            if (cgoto.m1246do(serviceType, list, this) == coroutine_suspended) {
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
