package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public abstract class DeferredScalarObserver<T, R> extends DeferredScalarDisposable<R> implements Observer<T> {
    private static final long serialVersionUID = -266195175408988651L;
    protected Disposable upstream;

    public DeferredScalarObserver(Observer<? super R> observer) {
        super(observer);
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override
    public void onError(Throwable th) {
        this.value = null;
        error(th);
    }

    @Override
    public void onComplete() {
        T t = this.value;
        if (t != null) {
            this.value = null;
            complete(t);
        } else {
            complete();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        this.upstream.dispose();
    }
}
