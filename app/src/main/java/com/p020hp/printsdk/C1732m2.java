package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpPrinter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$toHpPrinter$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {63}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
public final class C1732m2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    public int f1505a;

    public final Function1<HpPrinter, Unit> f1506b;

    public final AbstractC1717j2 f1507c;

    public final C1687e f1508d;

    @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$toHpPrinter$1$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {64, 64}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public Object f1509a;

        public int f1510b;

        public final Function1<HpPrinter, Unit> f1511c;

        public final AbstractC1717j2 f1512d;

        public final C1687e f1513e;

        public a(Function1<? super HpPrinter, Unit> function1, AbstractC1717j2 abstractC1717j2, C1687e c1687e, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f1511c = function1;
            this.f1512d = abstractC1717j2;
            this.f1513e = c1687e;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new a(this.f1511c, this.f1512d, this.f1513e, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new a(this.f1511c, this.f1512d, this.f1513e, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Function1<HpPrinter, Unit> function1;
            Function1 function12;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1510b;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function1<HpPrinter, Unit> function13 = this.f1511c;
                AbstractC1717j2 abstractC1717j2 = this.f1512d;
                this.f1509a = function13;
                this.f1510b = 1;
                Object objMo561a = abstractC1717j2.mo561a(this);
                if (objMo561a == coroutine_suspended) {
                    return coroutine_suspended;
                }
                function1 = function13;
                obj = objMo561a;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    function12 = (Function1) this.f1509a;
                    ResultKt.throwOnFailure(obj);
                    function12.invoke(obj);
                    return Unit.INSTANCE;
                }
                function1 = (Function1) this.f1509a;
                ResultKt.throwOnFailure(obj);
            }
            C1687e c1687e = this.f1513e;
            this.f1509a = function1;
            this.f1510b = 2;
            obj = ((InterfaceC1699g) obj).mo514a(c1687e, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            function12 = function1;
            function12.invoke(obj);
            return Unit.INSTANCE;
        }
    }

    public C1732m2(Function1<? super HpPrinter, Unit> function1, AbstractC1717j2 abstractC1717j2, C1687e c1687e, Continuation<? super C1732m2> continuation) {
        super(2, continuation);
        this.f1506b = function1;
        this.f1507c = abstractC1717j2;
        this.f1508d = c1687e;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new C1732m2(this.f1506b, this.f1507c, this.f1508d, continuation);
    }

    @Override
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new C1732m2(this.f1506b, this.f1507c, this.f1508d, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.f1505a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            a aVar = new a(this.f1506b, this.f1507c, this.f1508d, null);
            this.f1505a = 1;
            if (BuildersKt.withContext(main, aVar, this) == coroutine_suspended) {
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
