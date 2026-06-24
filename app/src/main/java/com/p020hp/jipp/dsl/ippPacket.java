package com.p020hp.jipp.dsl;

import androidx.core.app.NotificationCompat;
import com.p020hp.jipp.encoding.IppPacket;
import com.p020hp.jipp.model.Operation;
import com.p020hp.jipp.model.Status;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(message = "Use IppPacket builders")
@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0086\u0002J4\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0086\u0002¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/jipp/dsl/ippPacket;", "", "()V", "invoke", "Lcom/hp/jipp/encoding/IppPacket;", "operation", "Lcom/hp/jipp/model/Operation;", "requestId", "", "func", "Lkotlin/Function1;", "Lcom/hp/jipp/encoding/IppPacket$Builder;", "", "Lkotlin/ExtensionFunctionType;", NotificationCompat.CATEGORY_STATUS, "Lcom/hp/jipp/model/Status;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class ippPacket {
    public static final ippPacket INSTANCE = new ippPacket();

    private ippPacket() {
    }

    public static IppPacket invoke$default(ippPacket ipppacket, Operation operation, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        return ipppacket.invoke(operation, i, (Function1<? super IppPacket.Builder, Unit>) function1);
    }

    public final IppPacket invoke(Operation operation, int requestId, Function1<? super IppPacket.Builder, Unit> func) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        Intrinsics.checkNotNullParameter(func, "func");
        IppPacket.Builder builder = new IppPacket.Builder(operation.getCode(), 0, requestId, 2, (DefaultConstructorMarker) null);
        func.invoke(builder);
        return builder.build();
    }

    public static IppPacket invoke$default(ippPacket ipppacket, Status status, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        return ipppacket.invoke(status, i, (Function1<? super IppPacket.Builder, Unit>) function1);
    }

    public final IppPacket invoke(Status status, int requestId, Function1<? super IppPacket.Builder, Unit> func) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(func, "func");
        IppPacket.Builder builder = new IppPacket.Builder(status.getCode(), 0, requestId, 2, (DefaultConstructorMarker) null);
        func.invoke(builder);
        return builder.build();
    }
}
