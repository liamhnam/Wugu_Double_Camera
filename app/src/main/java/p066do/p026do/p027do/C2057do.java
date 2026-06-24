package p066do.p026do.p027do;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentLinkedQueue;
import p066do.p026do.p027do.p067goto.C2059if;

public class C2057do {

    public static C2057do f3856catch;

    public Ctry f3857break;

    public long f3858case;

    public ConcurrentLinkedQueue<C2060if> f2464do = new ConcurrentLinkedQueue<>();

    public long f3859else;

    public String f3860for;

    public String f3861goto;

    public String f2465if;

    public long f3862new;

    public String f3863this;

    public long f3864try;

    public C2057do(C2059if c2059if) {
        new SimpleDateFormat("yyyy-MM-dd");
        if (!c2059if.m1192do()) {
            throw new NullPointerException("config's param is invalid");
        }
        this.f3860for = c2059if.f2475if;
        this.f2465if = c2059if.f2474do;
        this.f3862new = c2059if.f3877new;
        this.f3858case = c2059if.f3873case;
        this.f3864try = c2059if.f3875for;
        this.f3859else = c2059if.f3879try;
        this.f3861goto = new String(c2059if.f3876goto);
        this.f3863this = new String(c2059if.f3878this);
        m1187do();
    }

    public final void m1187do() {
        if (this.f3857break == null) {
            Ctry ctry = new Ctry(this.f2464do, this.f2465if, this.f3860for, this.f3862new, this.f3864try, this.f3858case, this.f3861goto, this.f3863this);
            this.f3857break = ctry;
            ctry.setName("logan-thread");
            this.f3857break.start();
        }
    }
}
