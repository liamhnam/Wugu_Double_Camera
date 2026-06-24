package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

public final class LifecycleKt$$ExternalSyntheticBackportWithForwarding0 {
    public static boolean m143m(AtomicReference atomicReference, Object obj, Object obj2) {
        while (!atomicReference.compareAndSet(obj, obj2)) {
            if (atomicReference.get() != obj) {
                return false;
            }
        }
        return true;
    }
}
