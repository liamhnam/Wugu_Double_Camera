package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

public final class InvalidModuleException extends IllegalStateException {
    public InvalidModuleException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
