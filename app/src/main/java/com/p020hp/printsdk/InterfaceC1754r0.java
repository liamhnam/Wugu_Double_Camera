package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.IppInputStream;
import com.p020hp.jipp.encoding.IppPacket;
import com.p020hp.jipp.model.Status;
import com.p020hp.jipp.model.Types;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p037io.CloseableKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public interface InterfaceC1754r0 {

    public static final a f1659a = a.f1660a;

    public static final class a {

        public static final a f1660a;

        public static final Logger f1661b;

        public static final class C3368a implements InterfaceC1754r0 {

            public final InterfaceC1693f f1662b;

            @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppTransport$Companion$from$1$send$2", m1305f = "IppTransport.kt", m1306i = {0, 0}, m1307l = {63}, m1308m = "invokeSuspend", m1309n = {"$this$withContext", "start"}, m1310s = {"L$0", "J$0"})
            public static final class C3369a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IppPacket>, Object> {

                public long f1663a;

                public int f1664b;

                public Object f1665c;

                public final C1687e f1666d;

                public final IppPacket f1667e;

                public final InputStream f1668f;

                public final InterfaceC1693f f1669g;

                public final Long f1670h;

                public C3369a(C1687e c1687e, IppPacket ippPacket, InputStream inputStream, InterfaceC1693f interfaceC1693f, Long l, Continuation<? super C3369a> continuation) {
                    super(2, continuation);
                    this.f1666d = c1687e;
                    this.f1667e = ippPacket;
                    this.f1668f = inputStream;
                    this.f1669g = interfaceC1693f;
                    this.f1670h = l;
                }

                @Override
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C3369a c3369a = new C3369a(this.f1666d, this.f1667e, this.f1668f, this.f1669g, this.f1670h, continuation);
                    c3369a.f1665c = obj;
                    return c3369a;
                }

                @Override
                public Object invoke(CoroutineScope coroutineScope, Continuation<? super IppPacket> continuation) {
                    return ((C3369a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override
                public final Object invokeSuspend(Object obj) throws Throwable {
                    long jCurrentTimeMillis;
                    Object objMo510a;
                    Object objM1772constructorimpl;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.f1664b;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.f1665c;
                        jCurrentTimeMillis = System.currentTimeMillis();
                        a.f1661b.invoke("Sending to " + this.f1666d.f1181c + ": " + this.f1667e.getOperation() + (this.f1668f != null ? " +stream" : ""));
                        a.f1661b.invoke(this.f1667e.prettyPrint(120, "  "));
                        InterfaceC1693f interfaceC1693f = this.f1669g;
                        C1687e c1687e = this.f1666d;
                        InputStream inputStreamM598a = C1740o0.m598a(this.f1667e);
                        InputStream byteArrayInputStream = this.f1668f;
                        if (byteArrayInputStream == null) {
                            byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
                        }
                        SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStreamM598a, byteArrayInputStream);
                        Long l = this.f1670h;
                        this.f1665c = coroutineScope;
                        this.f1663a = jCurrentTimeMillis;
                        this.f1664b = 1;
                        objMo510a = interfaceC1693f.mo510a(c1687e, sequenceInputStream, l, this);
                        if (objMo510a == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        long j = this.f1663a;
                        ResultKt.throwOnFailure(obj);
                        jCurrentTimeMillis = j;
                        objMo510a = obj;
                    }
                    Closeable closeable = (Closeable) objMo510a;
                    C1687e c1687e2 = this.f1666d;
                    IppPacket ippPacket = this.f1667e;
                    try {
                        try {
                            IppPacket packet = new IppInputStream((InputStream) closeable).readPacket();
                            a.f1661b.invoke("Received " + packet.getStatus() + " (" + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms)");
                            a.f1661b.invoke(packet.prettyPrint(120, "  "));
                            objM1772constructorimpl = Result.m1772constructorimpl(packet);
                        } catch (Throwable th) {
                            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m1775exceptionOrNullimpl(objM1772constructorimpl) != null) {
                            a.f1661b.invoke("Read response packet failed for " + c1687e2.f1181c + ": " + ippPacket.getOperation());
                        }
                        C1740o0.m596a(Status.INSTANCE);
                        IppPacket ippPacket2 = new IppPacket(0, 1000, 1, (List) null, 9, (DefaultConstructorMarker) null);
                        if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
                            objM1772constructorimpl = ippPacket2;
                        }
                        IppPacket ippPacket3 = (IppPacket) objM1772constructorimpl;
                        CloseableKt.closeFinally(closeable, null);
                        return ippPacket3;
                    } finally {
                    }
                }
            }

            public C3368a(InterfaceC1693f interfaceC1693f) {
                this.f1662b = interfaceC1693f;
            }

            @Override
            public Object mo621a(C1687e c1687e, IppPacket.Builder builder, InputStream inputStream, Long l, Continuation<? super IppPacket> continuation) {
                return m623a(c1687e, builder.build(), inputStream, l, continuation);
            }

            public Object m623a(C1687e c1687e, IppPacket ippPacket, InputStream inputStream, Long l, Continuation<? super IppPacket> continuation) {
                return BuildersKt.withContext(Dispatchers.getIO(), new C3369a(c1687e, ippPacket, inputStream, this.f1662b, l, null), continuation);
            }
        }

        static {
            a aVar = new a();
            f1660a = aVar;
            f1661b = LoggerKt.logger(aVar);
        }

        public final InterfaceC1754r0 m622a(InterfaceC1693f printerTransport) {
            Intrinsics.checkNotNullParameter(printerTransport, "printerTransport");
            return new C3368a(printerTransport);
        }
    }

    public static final class b {
        public static final IppPacket.Builder m624a(URI printerUri, String userName, int i) {
            Intrinsics.checkNotNullParameter(printerUri, "printerUri");
            Intrinsics.checkNotNullParameter(userName, "userName");
            return IppPacket.INSTANCE.cancelJob(printerUri, i).putOperationAttributes(Types.requestingUserName.m440of(userName));
        }

        public static final boolean m627a(int i) {
            return !(i % 2 == 1);
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final boolean m629b(java.lang.String r9) {
            /*
                com.hp.printsdk.y0 r0 = com.p020hp.printsdk.C1789y0.f2012a
                java.util.List<java.lang.String> r0 = com.p020hp.printsdk.C1789y0.f2014c
                java.util.ArrayList r1 = new java.util.ArrayList
                r2 = 10
                int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r2)
                r1.<init>(r2)
                java.util.Iterator r0 = r0.iterator()
            L13:
                boolean r2 = r0.hasNext()
                java.lang.String r3 = "this as java.lang.String).toLowerCase(locale)"
                java.lang.String r4 = "US"
                if (r2 == 0) goto L34
                java.lang.Object r2 = r0.next()
                java.lang.String r2 = (java.lang.String) r2
                java.util.Locale r5 = java.util.Locale.US
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r4)
                java.lang.String r2 = r2.toLowerCase(r5)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                r1.add(r2)
                goto L13
            L34:
                boolean r0 = r1.isEmpty()
                r2 = 0
                if (r0 == 0) goto L3c
                goto L6b
            L3c:
                java.util.Iterator r0 = r1.iterator()
            L40:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L6b
                java.lang.Object r1 = r0.next()
                java.lang.String r1 = (java.lang.String) r1
                r5 = 1
                if (r9 == 0) goto L67
                java.util.Locale r6 = java.util.Locale.US
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)
                java.lang.String r6 = r9.toLowerCase(r6)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r3)
                if (r6 == 0) goto L67
                r7 = 2
                r8 = 0
                boolean r1 = kotlin.text.StringsKt.contains$default(r6, r1, r2, r7, r8)
                if (r1 != r5) goto L67
                r1 = r5
                goto L68
            L67:
                r1 = r2
            L68:
                if (r1 == 0) goto L40
                r2 = r5
            L6b:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.InterfaceC1754r0.b.m629b(java.lang.String):boolean");
        }

        public static final boolean m628a(String str) {
            C1789y0 c1789y0 = C1789y0.f2012a;
            List<String> list = C1789y0.f2013b;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str2 : list) {
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                String lowerCase = str2.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                arrayList.add(lowerCase);
            }
            return CollectionsKt.contains(arrayList, str);
        }

        public static final C1777v3 m625a(C1687e c1687e) {
            Intrinsics.checkNotNullParameter(c1687e, "<this>");
            return new C1777v3(c1687e.m504b(), c1687e.f1181c, c1687e.m505c(), c1687e);
        }
    }

    Object mo621a(C1687e c1687e, IppPacket.Builder builder, InputStream inputStream, Long l, Continuation<? super IppPacket> continuation);
}
