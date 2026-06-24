package cc.uling.usdk.mgr;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TPoolManager {

    private ThreadPoolExecutor f451a;

    class SingletonHolder {

        private static TPoolManager f452a = new TPoolManager();

        private SingletonHolder() {
        }
    }

    private TPoolManager() {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        this.f451a = new ThreadPoolExecutor(Math.max(2, Math.min(iAvailableProcessors - 1, 4)), (iAvailableProcessors * 2) + 1, 1L, TimeUnit.HOURS, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static TPoolManager getInstance() {
        return SingletonHolder.f452a;
    }

    public void execute(Runnable runnable) {
        if (runnable != null) {
            this.f451a.execute(runnable);
        }
    }
}
