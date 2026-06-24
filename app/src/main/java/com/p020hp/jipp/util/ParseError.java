package com.p020hp.jipp.util;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/util/ParseError;", "Ljava/io/IOException;", "s", "", "(Ljava/lang/String;)V", "t", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class ParseError extends IOException {
    public ParseError(String s) {
        super(s);
        Intrinsics.checkNotNullParameter(s, "s");
    }

    public ParseError(String s, Throwable t) {
        super(s, t);
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(t, "t");
    }
}
