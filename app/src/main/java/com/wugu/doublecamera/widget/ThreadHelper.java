package com.wugu.doublecamera.widget;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadHelper {
    private final int CORE_POOL_SIZE;
    private final ThreadFactory DEFAULT_THREAD_FACTORY;
    private final int KEEP_ALIVE_TIME;
    private final int MAXIMUM_POOL_SIZE;
    private final ThreadPoolExecutor mThreadPool;
    private final BlockingQueue<Runnable> mWorkQueue;

    private static class Holder {
        public static ThreadHelper instance = new ThreadHelper();

        private Holder() {
        }
    }

    private ThreadHelper() {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors() * 2;
        this.CORE_POOL_SIZE = iAvailableProcessors;
        this.MAXIMUM_POOL_SIZE = 32;
        this.KEEP_ALIVE_TIME = 1;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(16);
        this.mWorkQueue = linkedBlockingQueue;
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "ThreadPool#" + this.mCount.getAndIncrement());
                thread.setPriority(5);
                return thread;
            }
        };
        this.DEFAULT_THREAD_FACTORY = threadFactory;
        this.mThreadPool = new ThreadPoolExecutor(iAvailableProcessors, 32, 1L, TimeUnit.SECONDS, linkedBlockingQueue, threadFactory, new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public static ThreadHelper getInstance() {
        return Holder.instance;
    }

    public ThreadPoolExecutor getThreadPool() {
        return this.mThreadPool;
    }

    public void createThread(Runnable runnable) {
        try {
            this.mThreadPool.execute(runnable);
        } catch (Exception unused) {
        }
    }
}
