package p000a.p001a.p002a.p005c;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p000a.p001a.p002a.p007e.p008a.C0019b;

public class RunnableC0009b implements Runnable {

    private static final String f70e = "b";

    private int f71a;

    private ScheduledExecutorService f72b;

    private Handler f73c;

    private Thread f74d;

    public RunnableC0009b(int i, Handler handler) {
        this.f73c = null;
        this.f74d = null;
        i = i <= 0 ? 6 : i;
        this.f71a = i;
        this.f73c = handler;
        this.f72b = Executors.newScheduledThreadPool(i);
        Thread thread = new Thread(this);
        this.f74d = thread;
        thread.start();
    }

    public Future<?> m49a(AbstractRunnableC0008a abstractRunnableC0008a) {
        String str = f70e;
        Log.v(str, "addAndExcute()");
        if (abstractRunnableC0008a.getPeriod() != AbstractRunnableC0008a.EXEC_ONCE) {
            Log.v(str, "addAndExcute(periodic task) ");
            return this.f72b.scheduleAtFixedRate(abstractRunnableC0008a, abstractRunnableC0008a.getInitDelay(), abstractRunnableC0008a.getPeriod(), abstractRunnableC0008a.periodUnit);
        }
        if (abstractRunnableC0008a.getInitDelay() > 0) {
            Log.v(str, "addAndExcute(init dealy) " + abstractRunnableC0008a.getInitDelay());
            return this.f72b.schedule(abstractRunnableC0008a, abstractRunnableC0008a.getInitDelay(), abstractRunnableC0008a.periodUnit);
        }
        Log.v(str, "addAndExcute(EXEC_ONCE)");
        return this.f72b.submit(abstractRunnableC0008a);
    }

    public void m50a() {
        if (this.f74d.isAlive()) {
            Log.v(f70e, "Task manager is alive.");
            this.f74d.interrupt();
        }
    }

    @Override
    public void run() {
        boolean zAwaitTermination;
        while (true) {
            try {
                try {
                    if (Thread.interrupted()) {
                        break;
                    }
                    Log.i(f70e, "Task manager loop");
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    Log.i(f70e, "Task manager interrupt");
                    e.printStackTrace();
                    this.f72b.shutdown();
                    this.f72b.shutdownNow();
                    while (!this.f72b.awaitTermination(60L, TimeUnit.SECONDS)) {
                        try {
                            Log.e(f70e, "threadpool did not terminate");
                            C0019b.m90a();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                            Log.i(f70e, "terminate by exception, did not wait other thread end");
                        }
                    }
                    if (this.f73c == null) {
                        return;
                    }
                }
                Log.v(f70e, "pass message to service");
                Handler handler = this.f73c;
                handler.sendMessage(Message.obtain(handler, 10));
            } finally {
                while (true) {
                    try {
                        if (zAwaitTermination) {
                            break;
                        }
                    } catch (InterruptedException e3) {
                    }
                }
            }
        }
        this.f72b.shutdown();
        this.f72b.shutdownNow();
        while (!this.f72b.awaitTermination(60L, TimeUnit.SECONDS)) {
            try {
                Log.e(f70e, "threadpool did not terminate");
                C0019b.m90a();
            } catch (InterruptedException e4) {
                e4.printStackTrace();
                Log.i(f70e, "terminate by exception, did not wait other thread end");
            }
        }
        if (this.f73c == null) {
            return;
        }
        Log.v(f70e, "pass message to service");
        Handler handler2 = this.f73c;
        handler2.sendMessage(Message.obtain(handler2, 10));
    }
}
