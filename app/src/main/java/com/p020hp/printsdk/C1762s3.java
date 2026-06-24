package com.p020hp.printsdk;

import com.arthenica.ffmpegkit.StreamInformation;
import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.model.Orientation;
import com.p020hp.jipp.model.PrintColorMode;
import com.p020hp.jipp.model.Types;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.File;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

public final class C1762s3 implements CoroutineScope {

    public static final a f1763h;

    public static final Logger f1764i;

    public final C1700g0 f1765a;

    public final CoroutineContext f1766b;

    public final EnumC1744p f1767c;

    public final EnumC1716j1 f1768d;

    public final String f1769e;

    public final C1714j f1770f;

    public final Lazy f1771g;

    public static final class a {
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.DocumentPreview", m1305f = "DocumentPreview.kt", m1306i = {0, 0}, m1307l = {85}, m1308m = "get", m1309n = {"this", StreamInformation.KEY_INDEX}, m1310s = {"L$0", "I$0"})
    public static final class b extends ContinuationImpl {

        public Object f1772a;

        public int f1773b;

        public Object f1774c;

        public int f1776e;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1774c = obj;
            this.f1776e |= Integer.MIN_VALUE;
            return C1762s3.this.m656a(0, this);
        }
    }

    public static final class c extends Lambda implements Function0<Deferred<? extends C1729m>> {

        public final C1663a0 f1778b;

        public final File f1779c;

        public c(C1663a0 c1663a0, File file) {
            super(0);
            this.f1778b = c1663a0;
            this.f1779c = file;
        }

        @Override
        public Deferred<? extends C1729m> invoke() {
            C1762s3 c1762s3 = C1762s3.this;
            return BuildersKt__Builders_commonKt.async$default(c1762s3, null, null, new C1767t3(this.f1778b, this.f1779c, c1762s3, null), 3, null);
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.DocumentPreview", m1305f = "DocumentPreview.kt", m1306i = {}, m1307l = {106}, m1308m = "stop", m1309n = {}, m1310s = {})
    public static final class d extends ContinuationImpl {

        public Object f1780a;

        public int f1782c;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1780a = obj;
            this.f1782c |= Integer.MIN_VALUE;
            return C1762s3.this.m657a(this);
        }
    }

    static {
        a aVar = new a();
        f1763h = aVar;
        f1764i = LoggerKt.logger(aVar);
    }

    public C1762s3(CoroutineContext coroutineContext, C1663a0 service, File document, C1700g0 size, AttributeGroup attributes) {
        double d2;
        int iIntValue;
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.f1765a = size;
        this.f1766b = coroutineContext.plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null));
        Orientation orientation = (Orientation) attributes.getValue(Types.orientationRequested);
        orientation = orientation == null ? Orientation.none : orientation;
        EnumC1744p enumC1744p = Intrinsics.areEqual(orientation, Orientation.portrait) ? EnumC1744p.None : Intrinsics.areEqual(orientation, Orientation.landscape) ? EnumC1744p.All : EnumC1744p.Auto;
        this.f1767c = enumC1744p;
        String str = (String) attributes.getValue(Types.printColorMode);
        str = str == null ? "auto" : str;
        EnumC1716j1 enumC1716j1 = (Intrinsics.areEqual(str, "monochrome") || Intrinsics.areEqual(str, PrintColorMode.processMonochrome)) ? EnumC1716j1.Grayscale : EnumC1716j1.Rgba;
        this.f1768d = enumC1716j1;
        String str2 = (String) attributes.getValue(Types.printScaling);
        String str3 = str2 == null ? "auto" : str2;
        this.f1769e = str3;
        Attribute attribute = attributes.get(Types.mediaBottomMarginSupported);
        double dIntValue = ((double) ((attribute == null || (num4 = (Integer) CollectionsKt.firstOrNull((List) attribute)) == null) ? 0 : num4.intValue())) / 35.27777777777778d;
        Attribute attribute2 = attributes.get(Types.mediaTopMarginSupported);
        if (attribute2 == null || (num3 = (Integer) CollectionsKt.firstOrNull((List) attribute2)) == null) {
            d2 = dIntValue;
            iIntValue = 0;
        } else {
            iIntValue = num3.intValue();
            d2 = dIntValue;
        }
        double d3 = ((double) iIntValue) / 35.27777777777778d;
        Attribute attribute3 = attributes.get(Types.mediaLeftMarginSupported);
        double dIntValue2 = ((double) ((attribute3 == null || (num2 = (Integer) CollectionsKt.firstOrNull((List) attribute3)) == null) ? 0 : num2.intValue())) / 35.27777777777778d;
        Attribute attribute4 = attributes.get(Types.mediaRightMarginSupported);
        this.f1770f = new C1714j(size, 72, enumC1744p, enumC1716j1, 1, str3, d2, d3, dIntValue2, ((double) ((attribute4 == null || (num = (Integer) CollectionsKt.firstOrNull((List) attribute4)) == null) ? 0 : num.intValue())) / 35.27777777777778d, false, 1024);
        this.f1771g = LazyKt.lazy(new c(service, document));
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m656a(int r7, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1762s3.m656a(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f1766b;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m657a(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.p020hp.printsdk.C1762s3.d
            if (r0 == 0) goto L13
            r0 = r5
            com.hp.printsdk.s3$d r0 = (com.p020hp.printsdk.C1762s3.d) r0
            int r1 = r0.f1782c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1782c = r1
            goto L18
        L13:
            com.hp.printsdk.s3$d r0 = new com.hp.printsdk.s3$d
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.f1780a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f1782c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4c
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            com.hp.mobile.common.utils.Logger r5 = com.p020hp.printsdk.C1762s3.f1764i
            java.lang.String r2 = "document preview stop"
            r5.invoke(r2)
            kotlin.Lazy r5 = r4.f1771g
            java.lang.Object r5 = r5.getValue()
            kotlinx.coroutines.Deferred r5 = (kotlinx.coroutines.Deferred) r5
            r0.f1782c = r3
            java.lang.Object r5 = r5.await(r0)
            if (r5 != r1) goto L4c
            return r1
        L4c:
            com.hp.printsdk.h r5 = (com.p020hp.printsdk.AbstractC1704h) r5
            r5.close()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1762s3.m657a(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
