package com.liulishuo.okdownload.core;

public abstract class NamedRunnable implements Runnable {
    protected final String name;

    protected abstract void canceled(InterruptedException interruptedException);

    protected abstract void execute() throws InterruptedException;

    protected abstract void finished();

    public NamedRunnable(String str) {
        this.name = str;
    }

    @Override
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.name);
        try {
            try {
                execute();
            } catch (InterruptedException e) {
                canceled(e);
            }
        } finally {
            Thread.currentThread().setName(name);
            finished();
        }
    }
}
