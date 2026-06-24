package com.p020hp.printsdk;

import com.p020hp.printsdk.C1790y1;
import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;

public final class C1665a2 extends Lambda implements Function1<C1790y1.a, Unit> {

    public final C1790y1 f883a;

    public C1665a2(C1790y1 c1790y1) {
        super(1);
        this.f883a = c1790y1;
    }

    @Override
    public Unit invoke(C1790y1.a aVar) throws IOException {
        C1790y1.a pdObject = aVar;
        Intrinsics.checkNotNullParameter(pdObject, "$this$pdObject");
        byte[] bytes = "q /Image Do Q\n".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        pdObject.f2021b = bytes;
        C1790y1 c1790y1 = this.f883a;
        StringBuilder sb = new StringBuilder("/Length ");
        byte[] bArr = pdObject.f2021b;
        Intrinsics.checkNotNull(bArr);
        c1790y1.write(sb.append(bArr.length).append('\n').toString());
        return Unit.INSTANCE;
    }
}
