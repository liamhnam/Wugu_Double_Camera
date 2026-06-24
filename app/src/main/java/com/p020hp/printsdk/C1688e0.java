package com.p020hp.printsdk;

import java.nio.ByteBuffer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class C1688e0 extends Lambda implements Function0<String> {

    public final C1773v f1185a;

    public final ByteBuffer f1186b;

    public final long f1187c;

    public C1688e0(C1773v c1773v, ByteBuffer byteBuffer, long j) {
        super(0);
        this.f1185a = c1773v;
        this.f1186b = byteBuffer;
        this.f1187c = j;
    }

    @Override
    public String invoke() {
        return "renderStripe() " + this.f1185a + " done, " + this.f1186b.position() + " bytes, " + (System.currentTimeMillis() - this.f1187c) + " ms";
    }
}
