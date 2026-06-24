package org.greenrobot.greendao.p064rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDaoSession;
import rx.Observable;
import rx.Scheduler;

public class RxTransaction extends RxBase {
    private final AbstractDaoSession daoSession;

    @Override
    public Scheduler getScheduler() {
        return super.getScheduler();
    }

    public RxTransaction(AbstractDaoSession abstractDaoSession) {
        this.daoSession = abstractDaoSession;
    }

    public RxTransaction(AbstractDaoSession abstractDaoSession, Scheduler scheduler) {
        super(scheduler);
        this.daoSession = abstractDaoSession;
    }

    public Observable<Void> run(final Runnable runnable) {
        return wrap(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                RxTransaction.this.daoSession.runInTx(runnable);
                return null;
            }
        });
    }

    public <T> Observable<T> call(final Callable<T> callable) {
        return wrap(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return (T) RxTransaction.this.daoSession.callInTx(callable);
            }
        });
    }

    public AbstractDaoSession getDaoSession() {
        return this.daoSession;
    }
}
