package io.reactivex.internal.observers;

public final class BlockingLastObserver<T> extends BlockingBaseObserver<T> {
    @Override
    public void onNext(T t) {
        this.value = t;
    }

    @Override
    public void onError(Throwable th) {
        this.value = null;
        this.error = th;
        countDown();
    }
}
