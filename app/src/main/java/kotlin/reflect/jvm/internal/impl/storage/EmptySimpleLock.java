package kotlin.reflect.jvm.internal.impl.storage;

public final class EmptySimpleLock implements SimpleLock {
    public static final EmptySimpleLock INSTANCE = new EmptySimpleLock();

    @Override
    public void lock() {
    }

    @Override
    public void unlock() {
    }

    private EmptySimpleLock() {
    }
}
