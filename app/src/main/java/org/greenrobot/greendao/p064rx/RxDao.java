package org.greenrobot.greendao.p064rx;

import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDao;
import rx.Observable;
import rx.Scheduler;

public class RxDao<T, K> extends RxBase {
    private final AbstractDao<T, K> dao;

    @Override
    public Scheduler getScheduler() {
        return super.getScheduler();
    }

    public RxDao(AbstractDao<T, K> abstractDao) {
        this(abstractDao, null);
    }

    public RxDao(AbstractDao<T, K> abstractDao, Scheduler scheduler) {
        super(scheduler);
        this.dao = abstractDao;
    }

    public Observable<List<T>> loadAll() {
        return (Observable<List<T>>) wrap(new Callable<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return RxDao.this.dao.loadAll();
            }
        });
    }

    public Observable<T> load(final K k) {
        return (Observable<T>) wrap(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return (T) RxDao.this.dao.load(k);
            }
        });
    }

    public Observable<T> refresh(final T t) {
        return (Observable<T>) wrap(new Callable<T>() {
            @Override
            public T call() throws Exception {
                RxDao.this.dao.refresh(t);
                return (T) t;
            }
        });
    }

    public Observable<T> insert(final T t) {
        return (Observable<T>) wrap(new Callable<T>() {
            @Override
            public T call() throws Exception {
                RxDao.this.dao.insert(t);
                return (T) t;
            }
        });
    }

    public Observable<Iterable<T>> insertInTx(final Iterable<T> iterable) {
        return (Observable<Iterable<T>>) wrap(new Callable<Iterable<T>>() {
            @Override
            public Iterable<T> call() throws Exception {
                RxDao.this.dao.insertInTx(iterable);
                return iterable;
            }
        });
    }

    public Observable<Object[]> insertInTx(final T... tArr) {
        return wrap(new Callable<Object[]>() {
            @Override
            public Object[] call() throws Exception {
                RxDao.this.dao.insertInTx(tArr);
                return tArr;
            }
        });
    }

    public Observable<T> insertOrReplace(final T t) {
        return (Observable<T>) wrap(new Callable<T>() {
            @Override
            public T call() throws Exception {
                RxDao.this.dao.insertOrReplace(t);
                return (T) t;
            }
        });
    }

    public Observable<Iterable<T>> insertOrReplaceInTx(final Iterable<T> iterable) {
        return (Observable<Iterable<T>>) wrap(new Callable<Iterable<T>>() {
            @Override
            public Iterable<T> call() throws Exception {
                RxDao.this.dao.insertOrReplaceInTx(iterable);
                return iterable;
            }
        });
    }

    public Observable<Object[]> insertOrReplaceInTx(final T... tArr) {
        return wrap(new Callable<Object[]>() {
            @Override
            public Object[] call() throws Exception {
                RxDao.this.dao.insertOrReplaceInTx(tArr);
                return tArr;
            }
        });
    }

    public Observable<T> save(final T t) {
        return (Observable<T>) wrap(new Callable<T>() {
            @Override
            public T call() throws Exception {
                RxDao.this.dao.save(t);
                return (T) t;
            }
        });
    }

    public Observable<Iterable<T>> saveInTx(final Iterable<T> iterable) {
        return (Observable<Iterable<T>>) wrap(new Callable<Iterable<T>>() {
            @Override
            public Iterable<T> call() throws Exception {
                RxDao.this.dao.saveInTx(iterable);
                return iterable;
            }
        });
    }

    public Observable<Object[]> saveInTx(final T... tArr) {
        return wrap(new Callable<Object[]>() {
            @Override
            public Object[] call() throws Exception {
                RxDao.this.dao.saveInTx(tArr);
                return tArr;
            }
        });
    }

    public Observable<T> update(final T t) {
        return (Observable<T>) wrap(new Callable<T>() {
            @Override
            public T call() throws Exception {
                RxDao.this.dao.update(t);
                return (T) t;
            }
        });
    }

    public Observable<Iterable<T>> updateInTx(final Iterable<T> iterable) {
        return (Observable<Iterable<T>>) wrap(new Callable<Iterable<T>>() {
            @Override
            public Iterable<T> call() throws Exception {
                RxDao.this.dao.updateInTx(iterable);
                return iterable;
            }
        });
    }

    public Observable<Object[]> updateInTx(final T... tArr) {
        return wrap(new Callable<Object[]>() {
            @Override
            public Object[] call() throws Exception {
                RxDao.this.dao.updateInTx(tArr);
                return tArr;
            }
        });
    }

    public Observable<Void> delete(final T t) {
        return wrap(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                RxDao.this.dao.delete(t);
                return null;
            }
        });
    }

    public Observable<Void> deleteByKey(final K k) {
        return wrap(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                RxDao.this.dao.deleteByKey(k);
                return null;
            }
        });
    }

    public Observable<Void> deleteAll() {
        return wrap(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                RxDao.this.dao.deleteAll();
                return null;
            }
        });
    }

    public Observable<Void> deleteInTx(final Iterable<T> iterable) {
        return wrap(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                RxDao.this.dao.deleteInTx(iterable);
                return null;
            }
        });
    }

    public Observable<Void> deleteInTx(final T... tArr) {
        return wrap(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                RxDao.this.dao.deleteInTx(tArr);
                return null;
            }
        });
    }

    public Observable<Void> deleteByKeyInTx(final Iterable<K> iterable) {
        return wrap(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                RxDao.this.dao.deleteByKeyInTx(iterable);
                return null;
            }
        });
    }

    public Observable<Void> deleteByKeyInTx(final K... kArr) {
        return wrap(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                RxDao.this.dao.deleteByKeyInTx(kArr);
                return null;
            }
        });
    }

    public Observable<Long> count() {
        return wrap(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return Long.valueOf(RxDao.this.dao.count());
            }
        });
    }

    public AbstractDao<T, K> getDao() {
        return this.dao;
    }
}
