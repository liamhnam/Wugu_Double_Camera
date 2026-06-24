package com.p020hp.jipp.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1293d2 = {"Lcom/hp/jipp/util/BuildError;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "s", "", "(Ljava/lang/String;)V", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class BuildError extends RuntimeException {
    public BuildError(String s) {
        super(s);
        Intrinsics.checkNotNullParameter(s, "s");
    }
}
