package io.reactivex.internal.subscribers;

public final class BlockingLastSubscriber<T> extends BlockingBaseSubscriber<T> {
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
