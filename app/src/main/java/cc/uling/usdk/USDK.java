package cc.uling.usdk;

import android.app.Application;
import cc.uling.usdk.board.UBoard;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class USDK {

    private static USDK f220c;

    private Application f222b;

    private boolean f221a = true;

    private Map<String, UBoard> f223d = new ConcurrentHashMap();

    private USDK() {
    }

    private void m159a(boolean z) {
        this.f221a = z;
    }

    public static USDK getInstance() {
        if (f220c == null) {
            synchronized (USDK.class) {
                if (f220c == null) {
                    f220c = new USDK();
                }
            }
        }
        return f220c;
    }

    public UBoard create(String str) {
        if (!this.f223d.containsKey(str)) {
            this.f223d.put(str, new UBoard());
        }
        return this.f223d.get(str);
    }

    public Application getApplication() {
        return this.f222b;
    }

    public void init(Application application) {
        this.f222b = application;
        C0673b.m179a().m185a(application);
    }

    public boolean isDebug() {
        return this.f221a;
    }
}
