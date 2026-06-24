package com.faceunity.core.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThreadHelper {
    private final ThreadPoolExecutor mExecutorService;
    private final Handler mMainHandler;
    private Handler mWorkHandler;

    public static abstract class Callback<T> {
        protected void onFailure(Throwable th) {
        }

        protected void onFinish() {
        }

        protected void onStart() {
        }

        protected void onSuccess(T t) {
        }
    }

    private ThreadHelper() {
        this.mMainHandler = new Handler(Looper.getMainLooper());
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "AsyncTask #" + this.mCount.getAndIncrement());
            }
        };
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Math.max(2, Math.min(iAvailableProcessors - 1, 4)), (iAvailableProcessors * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), threadFactory);
        this.mExecutorService = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    public static ThreadHelper getInstance() {
        return ThreadHelperHolder.INSTANCE;
    }

    private synchronized void ensureSubHandler() {
        if (this.mWorkHandler == null) {
            HandlerThread handlerThread = new HandlerThread("WorkHandler");
            handlerThread.start();
            this.mWorkHandler = new Handler(handlerThread.getLooper());
        }
    }

    public <T> void enqueueOnUiThread(final Callable<T> callable, final Callback<T> callback) {
        if (callable != null) {
            this.mExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    Handler handler;
                    Runnable runnable;
                    try {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        if (callback != null) {
                            ThreadHelper.this.mMainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onStart();
                                    countDownLatch.countDown();
                                }
                            });
                        }
                        countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
                        final Object objCall = callable.call();
                        if (callback != null) {
                            ThreadHelper.this.mMainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onSuccess(objCall);
                                }
                            });
                        }
                    } catch (Throwable th) {
                        try {
                            if (callback != null) {
                                ThreadHelper.this.mMainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.onFailure(th);
                                    }
                                });
                            }
                            if (callback == null) {
                                return;
                            }
                            handler = ThreadHelper.this.mMainHandler;
                            runnable = new Runnable() {
                                @Override
                                public void run() {
                                    callback.onFinish();
                                }
                            };
                        } catch (Throwable th2) {
                            if (callback != null) {
                                ThreadHelper.this.mMainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.onFinish();
                                    }
                                });
                            }
                            throw th2;
                        }
                    }
                    if (callback != null) {
                        handler = ThreadHelper.this.mMainHandler;
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                callback.onFinish();
                            }
                        };
                        handler.post(runnable);
                    }
                }
            });
        }
    }

    public <T> void enqueue(final Callable<T> callable, final Callback<T> callback) {
        if (callable != null) {
            this.mExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Callback callback2 = callback;
                        if (callback2 != null) {
                            callback2.onStart();
                        }
                        Object objCall = callable.call();
                        Callback callback3 = callback;
                        if (callback3 != null) {
                            callback3.onSuccess(objCall);
                        }
                        Callback callback4 = callback;
                        if (callback4 == null) {
                        }
                    } catch (Throwable th) {
                        try {
                            Callback callback5 = callback;
                            if (callback5 != null) {
                                callback5.onFailure(th);
                            }
                        } finally {
                            Callback callback6 = callback;
                            if (callback6 != null) {
                                callback6.onFinish();
                            }
                        }
                    }
                }
            });
        }
    }

    public void execute(Runnable runnable) {
        if (runnable != null) {
            this.mExecutorService.execute(runnable);
        }
    }

    public <T> Future<T> submit(Callable<T> callable) {
        if (callable != null) {
            return this.mExecutorService.submit(callable);
        }
        return null;
    }

    public boolean postDelayed(Runnable runnable, long j) {
        ensureSubHandler();
        return this.mWorkHandler.postDelayed(runnable, j);
    }

    public boolean postAtTime(Runnable runnable, long j) {
        ensureSubHandler();
        return this.mWorkHandler.postAtTime(runnable, j);
    }

    public void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            this.mMainHandler.post(runnable);
        }
    }

    public boolean runOnUiPostDelayed(Runnable runnable, long j) {
        if (runnable != null) {
            return this.mMainHandler.postDelayed(runnable, j);
        }
        return false;
    }

    public boolean runOnUiPostAtTime(Runnable runnable, long j) {
        if (runnable != null) {
            return this.mMainHandler.postAtTime(runnable, j);
        }
        return false;
    }

    public void removeUiCallbacks(Runnable runnable) {
        if (runnable != null) {
            this.mMainHandler.removeCallbacks(runnable);
        }
    }

    public void removeWorkCallbacks(Runnable runnable) {
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    public void removeUiAllTasks() {
        this.mMainHandler.removeCallbacksAndMessages(null);
    }

    public void shutdown() {
        if (!this.mExecutorService.isShutdown()) {
            this.mExecutorService.shutdown();
        }
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.getLooper().quitSafely();
        }
    }

    private static class ThreadHelperHolder {
        private static final ThreadHelper INSTANCE = new ThreadHelper();

        private ThreadHelperHolder() {
        }
    }
}
