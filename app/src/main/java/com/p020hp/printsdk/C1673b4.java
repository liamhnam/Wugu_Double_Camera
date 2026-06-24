package com.p020hp.printsdk;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.open.printsdk.HpPrintFile;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.open.printsdk.options.PrintOptions;
import com.p020hp.printsdk.base.Preview;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

public final class C1673b4 implements Preview, CoroutineScope {

    public static final a f925i;

    public static final Logger f926j;

    public final HpPrintFile f927a;

    public final CoroutineContext f928b;

    public final Context f929c;

    public final C1757r3 f930d;

    public final Mutex f931e;

    public C1762s3 f932f;

    public double f933g;

    public PrintOptions f934h;

    public static final class a {
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.preview.PreviewImpl$getPreviewBitmaps$1", m1305f = "PreviewImpl.kt", m1306i = {0, 1, 2}, m1307l = {145, 61, 63}, m1308m = "invokeSuspend", m1309n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, m1310s = {"L$0", "L$0", "L$0"})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public Object f935a;

        public Object f936b;

        public Object f937c;

        public int f938d;

        public int f939e;

        public final int f941g;

        public final Function1<Bitmap, Unit> f942h;

        public static final class a extends Lambda implements Function0<String> {

            public final Bitmap f943a;

            public a(Bitmap bitmap) {
                super(0);
                this.f943a = bitmap;
            }

            @Override
            public String invoke() {
                return "Got one, " + this.f943a.getWidth() + 'x' + this.f943a.getHeight();
            }
        }

        @DebugMetadata(m1304c = "com.hp.printsdk.preview.PreviewImpl$getPreviewBitmaps$1$1$1$1$2", m1305f = "PreviewImpl.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class C3356b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public final Function1<Bitmap, Unit> f944a;

            public final Bitmap f945b;

            public C3356b(Function1<? super Bitmap, Unit> function1, Bitmap bitmap, Continuation<? super C3356b> continuation) {
                super(2, continuation);
                this.f944a = function1;
                this.f945b = bitmap;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C3356b(this.f944a, this.f945b, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new C3356b(this.f944a, this.f945b, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                this.f944a.invoke(this.f945b);
                return Unit.INSTANCE;
            }
        }

        public b(int i, Function1<? super Bitmap, Unit> function1, Continuation<? super b> continuation) {
            super(2, continuation);
            this.f941g = i;
            this.f942h = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return C1673b4.this.new b(this.f941g, this.f942h, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return C1673b4.this.new b(this.f941g, this.f942h, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 260
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1673b4.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.preview.PreviewImpl$stop$1", m1305f = "PreviewImpl.kt", m1306i = {}, m1307l = {95}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f946a;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return C1673b4.this.new c(continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return C1673b4.this.new c(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f946a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                C1762s3 c1762s3 = C1673b4.this.f932f;
                if (c1762s3 != null) {
                    this.f946a = 1;
                    if (c1762s3.m657a(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            C1673b4.this.f932f = null;
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.preview.PreviewImpl", m1305f = "PreviewImpl.kt", m1306i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4}, m1307l = {145, ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT, 108, 117, 120}, m1308m = "updatePreviewer-0E7RQCE", m1309n = {"this", "attributes", "onUpdatePreviewerCompleted", "$this$withLock_u24default$iv", "attributes", "onUpdatePreviewerCompleted", "$this$withLock_u24default$iv", "$this$updatePreviewer_0E7RQCE_u24lambda_u2d2_u24lambda_u2d0", "mediaRatio", "attributes", "onUpdatePreviewerCompleted", "$this$withLock_u24default$iv", "$this$updatePreviewer_0E7RQCE_u24lambda_u2d2_u24lambda_u2d0", "file", "mediaRatio", "attributes", "onUpdatePreviewerCompleted", "$this$withLock_u24default$iv", "$this$updatePreviewer_0E7RQCE_u24lambda_u2d2_u24lambda_u2d0", "file", NotificationCompat.CATEGORY_SERVICE, "screenSize", "$this$withLock_u24default$iv"}, m1310s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "D$0", "L$0", "L$1", "L$2", "L$3", "L$4", "D$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0"})
    public static final class d extends ContinuationImpl {

        public Object f948a;

        public Object f949b;

        public Object f950c;

        public Object f951d;

        public Object f952e;

        public Object f953f;

        public Object f954g;

        public double f955h;

        public Object f956i;

        public int f958k;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            this.f956i = obj;
            this.f958k |= Integer.MIN_VALUE;
            Object objM476a = C1673b4.this.m476a(null, null, this);
            return objM476a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM476a : Result.m1771boximpl(objM476a);
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.preview.PreviewImpl$updatePreviewer$2$1$1", m1305f = "PreviewImpl.kt", m1306i = {}, m1307l = {DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f959a;

        public final Function1<Continuation<? super Unit>, Object> f960b;

        public e(Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super e> continuation) {
            super(2, continuation);
            this.f960b = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new e(this.f960b, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new e(this.f960b, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f959a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function1<Continuation<? super Unit>, Object> function1 = this.f960b;
                this.f959a = 1;
                if (function1.invoke(this) == coroutine_suspended) {
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

    @DebugMetadata(m1304c = "com.hp.printsdk.preview.PreviewImpl$updatePrintOptions$1", m1305f = "PreviewImpl.kt", m1306i = {}, m1307l = {82}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f961a;

        public final HpPrinter f962b;

        public final C1673b4 f963c;

        public final PrintOptions f964d;

        public final Function0<Unit> f965e;

        @DebugMetadata(m1304c = "com.hp.printsdk.preview.PreviewImpl$updatePrintOptions$1$1", m1305f = "PreviewImpl.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {

            public final Function0<Unit> f966a;

            public a(Function0<Unit> function0, Continuation<? super a> continuation) {
                super(1, continuation);
                this.f966a = function0;
            }

            @Override
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new a(this.f966a, continuation);
            }

            @Override
            public Object invoke(Continuation<? super Unit> continuation) {
                return new a(this.f966a, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                this.f966a.invoke();
                return Unit.INSTANCE;
            }
        }

        public f(HpPrinter hpPrinter, C1673b4 c1673b4, PrintOptions printOptions, Function0<Unit> function0, Continuation<? super f> continuation) {
            super(2, continuation);
            this.f962b = hpPrinter;
            this.f963c = c1673b4;
            this.f964d = printOptions;
            this.f965e = function0;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new f(this.f962b, this.f963c, this.f964d, this.f965e, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new f(this.f962b, this.f963c, this.f964d, this.f965e, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f961a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HpPrinter hpPrinter = this.f962b;
                if (hpPrinter instanceof C1777v3) {
                    C1673b4 c1673b4 = this.f963c;
                    AttributeGroup attributeGroupM672a = ((C1777v3) hpPrinter).m672a(this.f964d);
                    a aVar = new a(this.f965e, null);
                    this.f961a = 1;
                    if (c1673b4.m476a(attributeGroupM672a, aVar, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    C1673b4.f926j.m447e("The printer with uuid " + this.f962b.getId() + " is not found!");
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ((Result) obj).getValue();
            }
            return Unit.INSTANCE;
        }
    }

    static {
        a aVar = new a();
        f925i = aVar;
        f926j = LoggerKt.logger(aVar);
    }

    public C1673b4(AppCompatActivity activity, HpPrintFile file) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(file, "file");
        this.f927a = file;
        this.f928b = LifecycleOwnerKt.getLifecycleScope(activity).getCoroutineContext();
        Context context = activity.getApplicationContext();
        this.f929c = context;
        CoroutineContext coroutineContext = getCoroutineContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        this.f930d = new C1757r3(coroutineContext, context, file.getUri());
        this.f931e = MutexKt.Mutex$default(false, 1, null);
        this.f933g = RangesKt.coerceAtMost(((double) context.getResources().getDisplayMetrics().widthPixels) / 1.2d, 600.0d);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m476a(com.p020hp.jipp.encoding.AttributeGroup r23, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r24, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1673b4.m476a(com.hp.jipp.encoding.AttributeGroup, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f928b;
    }

    @Override
    public void getPreviewBitmaps(int i, Function1<? super Bitmap, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new b(i, callback, null), 2, null);
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Double getPreviewRatio() {
        /*
            r5 = this;
            com.hp.open.printsdk.options.PrintOptions r0 = r5.f934h
            if (r0 == 0) goto L3a
            com.hp.open.printsdk.options.MediaSize r0 = r0.getF830c()
            if (r0 == 0) goto L3a
            com.hp.jipp.model.MediaCol$MediaSize r0 = r0.getF827c()
            if (r0 == 0) goto L3a
            java.lang.Integer r1 = r0.getXDimension()
            java.lang.Integer r0 = r0.getYDimension()
            if (r1 == 0) goto L32
            if (r0 == 0) goto L32
            int r2 = r0.intValue()
            if (r2 <= 0) goto L32
            int r1 = r1.intValue()
            double r1 = (double) r1
            int r0 = r0.intValue()
            double r3 = (double) r0
            double r1 = r1 / r3
            java.lang.Double r0 = java.lang.Double.valueOf(r1)
            goto L33
        L32:
            r0 = 0
        L33:
            if (r0 == 0) goto L3a
            double r0 = r0.doubleValue()
            goto L3f
        L3a:
            r0 = 4604543946291052875(0x3fe6a052bf5a814b, double:0.7070707070707071)
        L3f:
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1673b4.getPreviewRatio():java.lang.Double");
    }

    @Override
    public int getTotalPreviewPageCount() {
        return this.f927a.getF780b();
    }

    @Override
    public void stop() {
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new c(null), 2, null);
    }

    @Override
    public void updatePrintOptions(HpPrinter printer, PrintOptions options, Function0<Unit> onUpdateCompleted) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(onUpdateCompleted, "onUpdateCompleted");
        this.f934h = options;
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new f(printer, this, options, onUpdateCompleted, null), 2, null);
    }
}
