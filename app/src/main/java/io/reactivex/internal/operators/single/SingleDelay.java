package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;

public final class SingleDelay<T> extends Single<T> {
    final boolean delayError;
    final Scheduler scheduler;
    final SingleSource<? extends T> source;
    final long time;
    final TimeUnit unit;

    public SingleDelay(SingleSource<? extends T> singleSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.source = singleSource;
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override
    protected void subscribeActual(SingleObserver<? super T> singleObserver) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        singleObserver.onSubscribe(sequentialDisposable);
        this.source.subscribe(new Delay(sequentialDisposable, singleObserver));
    }

    final class Delay implements SingleObserver<T> {
        final SingleObserver<? super T> downstream;

        private final SequentialDisposable f2649sd;

        Delay(SequentialDisposable sequentialDisposable, SingleObserver<? super T> singleObserver) {
            this.f2649sd = sequentialDisposable;
            this.downstream = singleObserver;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            this.f2649sd.replace(disposable);
        }

        @Override
        public void onSuccess(T t) {
            this.f2649sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnSuccess(t), SingleDelay.this.time, SingleDelay.this.unit));
        }

        @Override
        public void onError(Throwable th) {
            this.f2649sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnError(th), SingleDelay.this.delayError ? SingleDelay.this.time : 0L, SingleDelay.this.unit));
        }

        final class OnSuccess implements Runnable {
            private final T value;

            OnSuccess(T t) {
                this.value = t;
            }

            @Override
            public void run() {
                Delay.this.downstream.onSuccess(this.value);
            }
        }

        final class OnError implements Runnable {

            private final Throwable f2650e;

            OnError(Throwable th) {
                this.f2650e = th;
            }

            @Override
            public void run() {
                Delay.this.downstream.onError(this.f2650e);
            }
        }
    }
}
