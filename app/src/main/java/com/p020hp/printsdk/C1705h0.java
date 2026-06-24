package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.encoding.KeyValues;
import com.p020hp.jipp.encoding.KeywordOrName;
import com.p020hp.jipp.encoding.MutableAttributeGroup;
import com.p020hp.jipp.model.MediaCol;
import com.p020hp.jipp.model.Orientation;
import com.p020hp.jipp.model.OutputBin;
import com.p020hp.jipp.model.Sides;
import com.p020hp.jipp.model.Types;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.p037io.CloseableKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

public final class C1705h0 implements CoroutineScope {

    public static final a f1223c;

    public static final Logger f1224d;

    public final Function1<Continuation<? super C1663a0>, Object> f1225a;

    public final CoroutineContext f1226b;

    public static final class a {
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.FormatConverter", m1305f = "FormatConverter.kt", m1306i = {0, 0, 1, 1, 2, 2}, m1307l = {64, 71, 79}, m1308m = "convert", m1309n = {"this", MqttCmdEnum.C2S_PRINTER_ERROR, "this", MqttCmdEnum.C2S_PRINTER_ERROR, "this", MqttCmdEnum.C2S_PRINTER_ERROR}, m1310s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
    public static final class b extends ContinuationImpl {

        public Object f1227a;

        public Object f1228b;

        public Object f1229c;

        public int f1231e;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1229c = obj;
            this.f1231e |= Integer.MIN_VALUE;
            return C1705h0.this.m535a((C1680d) null, (C1687e) null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.FormatConverter$convert$4", m1305f = "FormatConverter.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public Object f1232a;

        public final AbstractC1704h f1233b;

        public final PipedOutputStream f1234c;

        public final Function2<AbstractC1704h, OutputStream, Unit> f1235d;

        public final Ref.ObjectRef<Throwable> f1236e;

        public static final class a extends Lambda implements Function1<Throwable, Unit> {

            public final CoroutineScope f1237a;

            public final PipedOutputStream f1238b;

            public a(CoroutineScope coroutineScope, PipedOutputStream pipedOutputStream) {
                super(1);
                this.f1237a = coroutineScope;
                this.f1238b = pipedOutputStream;
            }

            @Override
            public Unit invoke(Throwable th) {
                Throwable th2 = th;
                PipedOutputStream pipedOutputStream = this.f1238b;
                try {
                    C1705h0.f1224d.invoke("pipedOutput.close()" + (th2 == null ? "" : " completion " + th2));
                    pipedOutputStream.close();
                    Result.m1772constructorimpl(Unit.INSTANCE);
                } catch (Throwable th3) {
                    Result.m1772constructorimpl(ResultKt.createFailure(th3));
                }
                return Unit.INSTANCE;
            }
        }

        public c(AbstractC1704h abstractC1704h, PipedOutputStream pipedOutputStream, Function2<? super AbstractC1704h, ? super OutputStream, Unit> function2, Ref.ObjectRef<Throwable> objectRef, Continuation<? super c> continuation) {
            super(2, continuation);
            this.f1233b = abstractC1704h;
            this.f1234c = pipedOutputStream;
            this.f1235d = function2;
            this.f1236e = objectRef;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            c cVar = new c(this.f1233b, this.f1234c, this.f1235d, this.f1236e, continuation);
            cVar.f1232a = obj;
            return cVar;
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objM1772constructorimpl;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.f1232a;
            AbstractC1704h abstractC1704h = this.f1233b;
            PipedOutputStream pipedOutputStream = this.f1234c;
            Function2<AbstractC1704h, OutputStream, Unit> function2 = this.f1235d;
            try {
                Job job = (Job) coroutineScope.getCoroutineContext().get(Job.INSTANCE);
                if (job != null) {
                    job.invokeOnCompletion(new a(coroutineScope, pipedOutputStream));
                }
                try {
                    try {
                        function2.invoke(abstractC1704h, pipedOutputStream);
                        CloseableKt.closeFinally(pipedOutputStream, null);
                        AutoCloseableKt.closeFinally(abstractC1704h, null);
                        C1705h0.f1224d.invoke("convertPdf() processing complete");
                        objM1772constructorimpl = Result.m1772constructorimpl(Unit.INSTANCE);
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
            }
            Ref.ObjectRef<Throwable> objectRef = this.f1236e;
            ?? M1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
            if (M1775exceptionOrNullimpl != 0) {
                C1705h0.f1224d.invoke("convertPdf() failure: " + ((Object) M1775exceptionOrNullimpl));
                objectRef.element = M1775exceptionOrNullimpl;
            }
            return Unit.INSTANCE;
        }
    }

    public static final class d extends FilterInputStream {

        public final Ref.ObjectRef<Throwable> f1239a;

        public d(PipedInputStream pipedInputStream, Ref.ObjectRef<Throwable> objectRef) {
            super(pipedInputStream);
            this.f1239a = objectRef;
        }

        @Override
        public void close() throws IOException {
            C1705h0.f1224d.invoke("FilterInputStream.close()");
            super.close();
        }

        @Override
        public int read() throws Throwable {
            Throwable th = this.f1239a.element;
            if (th == null) {
                return super.read();
            }
            throw th;
        }

        @Override
        public int read(byte[] b) throws Throwable {
            Intrinsics.checkNotNullParameter(b, "b");
            Throwable th = this.f1239a.element;
            if (th == null) {
                return super.read(b);
            }
            throw th;
        }

        @Override
        public int read(byte[] b, int i, int i2) throws Throwable {
            Intrinsics.checkNotNullParameter(b, "b");
            Throwable th = this.f1239a.element;
            if (th == null) {
                return super.read(b, i, i2);
            }
            throw th;
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.FormatConverter", m1305f = "FormatConverter.kt", m1306i = {0, 0, 0, 1, 1, 1, 1}, m1307l = {422, 423}, m1308m = "getPdf", m1309n = {"this", "$this$getPdf", "options", "this", "$this$getPdf", "options", "connection"}, m1310s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    public static final class e extends ContinuationImpl {

        public Object f1240a;

        public Object f1241b;

        public Object f1242c;

        public Object f1243d;

        public Object f1244e;

        public int f1246g;

        public e(Continuation<? super e> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1244e = obj;
            this.f1246g |= Integer.MIN_VALUE;
            return C1705h0.m529a(C1705h0.this, (C1680d) null, (C1714j) null, this);
        }
    }

    public static final class f extends Lambda implements Function0<Boolean> {
        public f() {
            super(0);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Boolean invoke() {
            /*
                r2 = this;
                com.hp.printsdk.h0 r0 = com.p020hp.printsdk.C1705h0.this
                kotlin.coroutines.CoroutineContext r0 = r0.f1226b
                kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.INSTANCE
                kotlin.coroutines.CoroutineContext$Element r0 = r0.get(r1)
                kotlinx.coroutines.Job r0 = (kotlinx.coroutines.Job) r0
                if (r0 == 0) goto L16
                boolean r0 = r0.isActive()
                r1 = 1
                if (r0 != r1) goto L16
                goto L17
            L16:
                r1 = 0
            L17:
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1705h0.f.invoke():java.lang.Object");
        }
    }

    public static final class g extends Lambda implements Function0<Unit> {

        public final C1663a0 f1248a;

        public g(C1663a0 c1663a0) {
            super(0);
            this.f1248a = c1663a0;
        }

        @Override
        public Unit invoke() {
            this.f1248a.close();
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.FormatConverter", m1305f = "FormatConverter.kt", m1306i = {0, 0, 0, 0, 0}, m1307l = {101}, m1308m = "pdfToImagePdf", m1309n = {"this", "$this$pdfToImagePdf", "colorSpace", "options", "pdfDoc"}, m1310s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    public static final class h extends ContinuationImpl {

        public Object f1249a;

        public Object f1250b;

        public Object f1251c;

        public Object f1252d;

        public Object f1253e;

        public Object f1254f;

        public int f1256h;

        public h(Continuation<? super h> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1254f = obj;
            this.f1256h |= Integer.MIN_VALUE;
            return C1705h0.m528a(C1705h0.this, (C1680d) null, (C1687e) null, this);
        }
    }

    public static final class i extends Lambda implements Function2<AbstractC1704h, OutputStream, Unit> {

        public final C1714j f1257a;

        public final PDDocument f1258b;

        public final EnumC1716j1 f1259c;

        public static final class a extends Lambda implements Function0<String> {

            public final byte[] f1260a;

            public a(byte[] bArr) {
                super(0);
                this.f1260a = bArr;
            }

            @Override
            public String invoke() {
                return "ByteArray.size: " + this.f1260a.length;
            }
        }

        public i(C1714j c1714j, PDDocument pDDocument, EnumC1716j1 enumC1716j1) {
            super(2);
            this.f1257a = c1714j;
            this.f1258b = pDDocument;
            this.f1259c = enumC1716j1;
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void m543a(com.p020hp.printsdk.AbstractC1704h r19, java.io.OutputStream r20) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 256
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1705h0.i.m543a(com.hp.printsdk.h, java.io.OutputStream):void");
        }

        @Override
        public Unit invoke(AbstractC1704h abstractC1704h, OutputStream outputStream) throws IOException {
            m543a(abstractC1704h, outputStream);
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.FormatConverter", m1305f = "FormatConverter.kt", m1306i = {0, 0, 0, 0, 0}, m1307l = {344}, m1308m = "pdfToPwg", m1309n = {"this", "$this$pdfToPwg", MqttCmdEnum.C2S_PRINTER_ERROR, "colorSpace", "copies"}, m1310s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
    public static final class j extends ContinuationImpl {

        public Object f1261a;

        public Object f1262b;

        public Object f1263c;

        public Object f1264d;

        public int f1265e;

        public Object f1266f;

        public int f1268h;

        public j(Continuation<? super j> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1266f = obj;
            this.f1268h |= Integer.MIN_VALUE;
            return C1705h0.m530b(C1705h0.this, (C1680d) null, (C1687e) null, this);
        }
    }

    public static final class k extends Lambda implements Function2<AbstractC1704h, OutputStream, Unit> {

        public final C1687e f1270b;

        public final C1680d f1271c;

        public final EnumC1716j1 f1272d;

        public final int f1273e;

        public k(C1687e c1687e, C1680d c1680d, EnumC1716j1 enumC1716j1, int i) {
            super(2);
            this.f1270b = c1687e;
            this.f1271c = c1680d;
            this.f1272d = enumC1716j1;
            this.f1273e = i;
        }

        @Override
        public Unit invoke(AbstractC1704h abstractC1704h, OutputStream outputStream) {
            AbstractC1704h convert = abstractC1704h;
            OutputStream output = outputStream;
            Intrinsics.checkNotNullParameter(convert, "$this$convert");
            Intrinsics.checkNotNullParameter(output, "output");
            boolean zM537a = C1705h0.this.m537a(this.f1270b);
            String str = (String) this.f1271c.f1149c.getValue(Types.sides);
            if (str == null) {
                str = Sides.oneSided;
            }
            C1741o1 c1741o1 = new C1741o1(this.f1272d, str, null, null, zM537a, this.f1273e, 12);
            String str2 = (String) this.f1270b.f1179a.getValue(Types.pwgRasterDocumentSheetBack);
            if (str2 == null) {
                str2 = "normal";
            }
            Orientation orientation = (Orientation) this.f1270b.f1179a.getValue(Types.orientationRequested);
            if (orientation == null) {
                orientation = Orientation.portrait;
            }
            C1683d2 c1683d2 = new C1683d2(c1741o1, str2, orientation);
            C1705h0.f1224d.invoke("pdfToPwg() writing with " + c1683d2);
            new C1696f2(output, c1683d2, null, 4).m512a(convert);
            return Unit.INSTANCE;
        }
    }

    public static final class l extends Lambda implements Function2<AbstractC1704h, OutputStream, Unit> {

        public final C1687e f1275b;

        public final C1680d f1276c;

        public final boolean f1277d;

        public final int f1278e;

        public final int f1279f;

        public l(C1687e c1687e, C1680d c1680d, boolean z, int i, int i2) {
            super(2);
            this.f1275b = c1687e;
            this.f1276c = c1680d;
            this.f1277d = z;
            this.f1278e = i;
            this.f1279f = i2;
        }

        @Override
        public Unit invoke(AbstractC1704h abstractC1704h, OutputStream outputStream) {
            AbstractC1704h convert = abstractC1704h;
            OutputStream output = outputStream;
            Intrinsics.checkNotNullParameter(convert, "$this$convert");
            Intrinsics.checkNotNullParameter(output, "output");
            boolean zM537a = C1705h0.this.m537a(this.f1275b);
            String str = (String) this.f1276c.f1149c.getValue(Types.sides);
            if (str == null) {
                str = Sides.oneSided;
            }
            C1741o1 c1741o1 = new C1741o1(this.f1277d ? EnumC1716j1.Rgb : EnumC1716j1.Grayscale, str, null, null, zM537a, this.f1278e, 12);
            int i = this.f1279f;
            String str2 = (String) this.f1275b.f1179a.getValue(Types.pclmRasterBackSide);
            if (str2 == null) {
                str2 = "normal";
            }
            C1785x1 c1785x1 = new C1785x1(c1741o1, i, str2);
            C1705h0.f1224d.invoke("jpgToPclm() writing with " + c1785x1);
            new C1790y1(output, c1785x1).m689a(convert);
            return Unit.INSTANCE;
        }
    }

    static {
        a aVar = new a();
        f1223c = aVar;
        f1224d = LoggerKt.logger(aVar);
    }

    public C1705h0(CoroutineContext context, Function1<? super Continuation<? super C1663a0>, ? extends Object> openConnection) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(openConnection, "openConnection");
        this.f1225a = openConnection;
        this.f1226b = context.plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null));
    }

    public static final Object m528a(C1705h0 c1705h0, C1680d c1680d, C1687e c1687e, Continuation continuation) {
        return c1705h0.m540b(null, null, continuation);
    }

    public static final Object m529a(C1705h0 c1705h0, C1680d c1680d, C1714j c1714j, Continuation continuation) {
        return c1705h0.m536a((C1680d) null, (C1714j) null, (Continuation<? super AbstractC1704h>) continuation);
    }

    public static final Object m530b(C1705h0 c1705h0, C1680d c1680d, C1687e c1687e, Continuation continuation) {
        return c1705h0.m542c(null, null, continuation);
    }

    public final AttributeGroup m531a(AttributeGroup attributeGroup, int i2, boolean z) {
        MutableAttributeGroup mutable = attributeGroup.toMutable();
        Integer num = (Integer) mutable.getValue(Types.copies);
        if ((num != null ? num.intValue() : 1) > 1) {
            mutable.put(Types.jobPagesPerSet.mo418of(Integer.valueOf(i2)));
            mutable.drop(Types.copies);
        }
        mutable.drop(Types.orientationRequested);
        mutable.drop(Types.pageRanges);
        mutable.drop(Types.printScaling);
        if (z) {
            mutable.drop(Types.mediaCol);
        }
        return mutable;
    }

    public final InputStream m534a(AbstractC1704h abstractC1704h, Function2<? super AbstractC1704h, ? super OutputStream, Unit> function2) {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream, 65536);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new c(abstractC1704h, pipedOutputStream, function2, objectRef, null), 2, null);
        return new d(pipedInputStream, objectRef);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m535a(com.p020hp.printsdk.C1680d r25, com.p020hp.printsdk.C1687e r26, kotlin.coroutines.Continuation<? super com.p020hp.printsdk.C1680d> r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 628
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1705h0.m535a(com.hp.printsdk.d, com.hp.printsdk.e, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final C1714j m538b(C1680d c1680d, C1687e c1687e, int i2, EnumC1716j1 enumC1716j1) {
        Integer mediaRightMargin;
        Integer mediaLeftMargin;
        Integer mediaBottomMargin;
        Integer mediaTopMargin;
        MediaCol mediaCol = (MediaCol) c1680d.f1149c.getValue(Types.mediaCol);
        int iIntValue = (mediaCol == null || (mediaTopMargin = mediaCol.getMediaTopMargin()) == null) ? 0 : mediaTopMargin.intValue();
        MediaCol mediaCol2 = (MediaCol) c1680d.f1149c.getValue(Types.mediaCol);
        int iIntValue2 = (mediaCol2 == null || (mediaBottomMargin = mediaCol2.getMediaBottomMargin()) == null) ? 0 : mediaBottomMargin.intValue();
        MediaCol mediaCol3 = (MediaCol) c1680d.f1149c.getValue(Types.mediaCol);
        int iIntValue3 = (mediaCol3 == null || (mediaLeftMargin = mediaCol3.getMediaLeftMargin()) == null) ? 0 : mediaLeftMargin.intValue();
        MediaCol mediaCol4 = (MediaCol) c1680d.f1149c.getValue(Types.mediaCol);
        int iIntValue4 = (mediaCol4 == null || (mediaRightMargin = mediaCol4.getMediaRightMargin()) == null) ? 0 : mediaRightMargin.intValue();
        C1700g0 c1700g0M532a = m532a(c1680d, c1687e);
        Orientation orientation = (Orientation) c1680d.f1149c.getValue(Types.orientationRequested);
        EnumC1744p enumC1744p = Intrinsics.areEqual(orientation, Orientation.portrait) ? EnumC1744p.None : Intrinsics.areEqual(orientation, Orientation.landscape) ? EnumC1744p.All : EnumC1744p.Auto;
        String str = (String) c1680d.f1149c.getValue(Types.printScaling);
        if (str == null) {
            str = "auto";
        }
        String str2 = str;
        double d2 = ((double) iIntValue2) / 35.27777777777778d;
        double d3 = ((double) iIntValue) / 35.27777777777778d;
        double d4 = ((double) iIntValue3) / 35.27777777777778d;
        double d5 = ((double) iIntValue4) / 35.27777777777778d;
        Boolean bool = (Boolean) c1680d.f1149c.getValue(C1748p3.f1582a);
        return new C1714j(c1700g0M532a, i2, enumC1744p, enumC1716j1, 0, str2, d2, d3, d4, d5, bool != null ? bool.booleanValue() : false, 16);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m542c(com.p020hp.printsdk.C1680d r24, com.p020hp.printsdk.C1687e r25, kotlin.coroutines.Continuation<? super com.p020hp.printsdk.C1680d> r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1705h0.m542c(com.hp.printsdk.d, com.hp.printsdk.e, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f1226b;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m540b(com.p020hp.printsdk.C1680d r18, com.p020hp.printsdk.C1687e r19, kotlin.coroutines.Continuation<? super com.p020hp.printsdk.C1680d> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1705h0.m540b(com.hp.printsdk.d, com.hp.printsdk.e, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.p020hp.printsdk.C1680d m541c(com.p020hp.printsdk.C1680d r27, com.p020hp.printsdk.C1687e r28) {
        /*
            Method dump skipped, instruction units count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1705h0.m541c(com.hp.printsdk.d, com.hp.printsdk.e):com.hp.printsdk.d");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m539b(com.p020hp.printsdk.C1680d r21, com.p020hp.printsdk.C1687e r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1705h0.m539b(com.hp.printsdk.d, com.hp.printsdk.e):java.lang.Object");
    }

    public final boolean m537a(C1687e c1687e) {
        boolean z;
        boolean z2;
        List values = c1687e.f1179a.getValues(Types.printerOutputTray);
        if ((values instanceof Collection) && values.isEmpty()) {
            z = false;
        } else {
            Iterator it = values.iterator();
            while (it.hasNext()) {
                String str = (String) ((KeyValues) it.next()).get((Object) "pagedelivery");
                if (str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "faceUp", false, 2, (Object) null)) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        if (z) {
            return true;
        }
        List values2 = c1687e.f1179a.getValues(Types.outputBinSupported);
        if ((values2 instanceof Collection) && values2.isEmpty()) {
            z2 = false;
        } else {
            Iterator it2 = values2.iterator();
            while (it2.hasNext()) {
                if (Intrinsics.areEqual(((KeywordOrName) it2.next()).getKeyword(), OutputBin.faceUp)) {
                    z2 = true;
                    break;
                }
            }
            z2 = false;
        }
        return z2;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m536a(com.p020hp.printsdk.C1680d r6, com.p020hp.printsdk.C1714j r7, kotlin.coroutines.Continuation<? super com.p020hp.printsdk.AbstractC1704h> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.p020hp.printsdk.C1705h0.e
            if (r0 == 0) goto L13
            r0 = r8
            com.hp.printsdk.h0$e r0 = (com.p020hp.printsdk.C1705h0.e) r0
            int r1 = r0.f1246g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1246g = r1
            goto L18
        L13:
            com.hp.printsdk.h0$e r0 = new com.hp.printsdk.h0$e
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f1244e
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f1246g
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L55
            if (r2 == r4) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r6 = r0.f1243d
            com.hp.printsdk.a0 r6 = (com.p020hp.printsdk.C1663a0) r6
            java.lang.Object r7 = r0.f1242c
            com.hp.printsdk.j r7 = (com.p020hp.printsdk.C1714j) r7
            java.lang.Object r1 = r0.f1241b
            com.hp.printsdk.d r1 = (com.p020hp.printsdk.C1680d) r1
            java.lang.Object r0 = r0.f1240a
            com.hp.printsdk.h0 r0 = (com.p020hp.printsdk.C1705h0) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L83
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            java.lang.Object r6 = r0.f1242c
            r7 = r6
            com.hp.printsdk.j r7 = (com.p020hp.printsdk.C1714j) r7
            java.lang.Object r6 = r0.f1241b
            com.hp.printsdk.d r6 = (com.p020hp.printsdk.C1680d) r6
            java.lang.Object r2 = r0.f1240a
            com.hp.printsdk.h0 r2 = (com.p020hp.printsdk.C1705h0) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6a
        L55:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super com.hp.printsdk.a0>, java.lang.Object> r8 = r5.f1225a
            r0.f1240a = r5
            r0.f1241b = r6
            r0.f1242c = r7
            r0.f1246g = r4
            java.lang.Object r8 = r8.invoke(r0)
            if (r8 != r1) goto L69
            return r1
        L69:
            r2 = r5
        L6a:
            com.hp.printsdk.a0 r8 = (com.p020hp.printsdk.C1663a0) r8
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super com.hp.printsdk.a0>, java.lang.Object> r4 = r2.f1225a
            r0.f1240a = r2
            r0.f1241b = r6
            r0.f1242c = r7
            r0.f1243d = r8
            r0.f1246g = r3
            java.lang.Object r0 = r4.invoke(r0)
            if (r0 != r1) goto L7f
            return r1
        L7f:
            r1 = r6
            r6 = r8
            r8 = r0
            r0 = r2
        L83:
            com.hp.printsdk.a0 r8 = (com.p020hp.printsdk.C1663a0) r8
            com.hp.printsdk.a r1 = r1.f1148b
            java.io.InputStream r1 = r1.f870a
            com.hp.printsdk.h0$f r2 = new com.hp.printsdk.h0$f
            r2.<init>()
            com.hp.printsdk.m r0 = new com.hp.printsdk.m
            java.lang.String r3 = "pdfRender"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r3)
            java.lang.String r3 = "input"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "options"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r3)
            java.lang.String r3 = "isActive"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            r0.<init>(r8, r7, r2)
            r0.f1485g = r1
            com.hp.printsdk.h0$g r7 = new com.hp.printsdk.h0$g
            r7.<init>(r6)
            r0.m527a(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1705h0.m536a(com.hp.printsdk.d, com.hp.printsdk.j, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final AbstractC1704h m533a(C1680d c1680d, C1687e c1687e, int i2, EnumC1716j1 enumC1716j1) throws IOException {
        PDDocument pDDocumentLoad = PDDocument.load(c1680d.f1148b.f870a);
        Intrinsics.checkNotNullExpressionValue(pDDocumentLoad, "load(document.input)");
        return new C1739o(pDDocumentLoad, m538b(c1680d, c1687e, i2, enumC1716j1)).m526a(c1680d.f1149c.getValues(Types.pageRanges));
    }

    public final C1700g0 m532a(C1680d c1680d, C1687e c1687e) {
        Integer yDimension;
        Integer xDimension;
        MediaCol mediaCol = (MediaCol) c1680d.f1149c.getValue(Types.mediaCol);
        if (mediaCol == null) {
            mediaCol = (MediaCol) c1687e.f1179a.getValue(Types.mediaColDefault);
        }
        if (mediaCol != null) {
            MediaCol.MediaSize mediaSize = mediaCol.getMediaSize();
            C1700g0 c1700g0 = null;
            Double dValueOf = (mediaSize == null || (xDimension = mediaSize.getXDimension()) == null) ? null : Double.valueOf(((double) xDimension.intValue()) / 35.27777777777778d);
            MediaCol.MediaSize mediaSize2 = mediaCol.getMediaSize();
            Double dValueOf2 = (mediaSize2 == null || (yDimension = mediaSize2.getYDimension()) == null) ? null : Double.valueOf(((double) yDimension.intValue()) / 35.27777777777778d);
            if (dValueOf != null && dValueOf2 != null) {
                c1700g0 = new C1700g0(dValueOf.doubleValue(), dValueOf2.doubleValue());
            }
            if (c1700g0 != null) {
                return c1700g0;
            }
        }
        throw new IllegalArgumentException("No destination media size found");
    }
}
