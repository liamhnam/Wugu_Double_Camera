package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public final class CompletableFromObservable<T> extends Completable {
    final ObservableSource<T> observable;

    public CompletableFromObservable(ObservableSource<T> observableSource) {
        this.observable = observableSource;
    }

    @Override
    protected void subscribeActual(CompletableObserver completableObserver) {
        this.observable.subscribe(new CompletableFromObservableObserver(completableObserver));
    }

    static final class CompletableFromObservableObserver<T> implements Observer<T> {

        final CompletableObserver f2589co;

        @Override
        public void onNext(T t) {
        }

        CompletableFromObservableObserver(CompletableObserver completableObserver) {
            this.f2589co = completableObserver;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            this.f2589co.onSubscribe(disposable);
        }

        @Override
        public void onError(Throwable th) {
            this.f2589co.onError(th);
        }

        @Override
        public void onComplete() {
            this.f2589co.onComplete();
        }
    }
}
