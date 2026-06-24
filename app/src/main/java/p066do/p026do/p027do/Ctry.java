package p066do.p026do.p027do;

import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;
import p066do.p026do.p027do.C2060if;
import p066do.p026do.p027do.p067goto.C2058do;
import p066do.p026do.p027do.p067goto.Cnew;

public class Ctry extends Thread {

    public long f2479a;

    public String f2480b;

    public ConcurrentLinkedQueue<C2060if> f3882break;

    public String f2481c;

    public File f3883case;

    public String f3884catch;

    public String f3885class;

    public long f3886const;

    public boolean f3887else;

    public long f3888final;

    public long f3890goto;

    public long f3891new;

    public Cfor f3892this;

    public volatile boolean f3893try;

    public final Object f2482if = new Object();

    public volatile boolean f3889for = true;

    public class C2061do implements Cnew {
        public C2061do(Ctry ctry) {
        }

        @Override
        public void mo445do(String str, int i) {
            Cnew cnew = C2058do.f2470do;
            if (cnew != null) {
                cnew.mo445do(str, i);
            }
        }
    }

    public Ctry(ConcurrentLinkedQueue<C2060if> concurrentLinkedQueue, String str, String str2, long j, long j2, long j3, String str3, String str4) {
        this.f3882break = concurrentLinkedQueue;
        this.f3884catch = str;
        this.f3885class = str2;
        this.f3886const = j;
        this.f3888final = j2;
        this.f2479a = j3;
        this.f2480b = str3;
        this.f2481c = str4;
    }

    public final void m1194do(C2060if c2060if) {
        Cfor cfor;
        Cnew cnew;
        String[] list;
        if (c2060if.m1193do()) {
            if (this.f3892this == null) {
                if (Cfor.f3869new == null) {
                    synchronized (Cfor.class) {
                        Cfor.f3869new = new Cfor();
                    }
                }
                Cfor cfor2 = Cfor.f3869new;
                this.f3892this = cfor2;
                cfor2.f3870for = new C2061do(this);
                this.f3892this.mo413do(this.f3884catch, this.f3885class, (int) this.f3888final, this.f2480b, this.f2481c);
                Cfor cfor3 = this.f3892this;
                boolean z = C2058do.f3871for;
                Cnew cnew2 = cfor3.f2468do;
                if (cnew2 != null) {
                    cnew2.mo414do(z);
                }
            }
            C2060if.Cdo cdo = c2060if.f2476do;
            if (cdo != C2060if.Cdo.WRITE) {
                if (cdo != C2060if.Cdo.FLUSH || (cfor = this.f3892this) == null || (cnew = cfor.f2468do) == null) {
                    return;
                }
                cnew.mo408do();
                return;
            }
            Celse celse = c2060if.f2477if;
            if (this.f3883case == null) {
                this.f3883case = new File(this.f3885class);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = this.f3891new;
            if (!(j < jCurrentTimeMillis && j + 86400000 > jCurrentTimeMillis)) {
                long jM1186do = Ccase.m1186do();
                long j2 = jM1186do - this.f3886const;
                File file = new File(this.f3885class);
                if (file.isDirectory() && (list = file.list()) != null) {
                    for (String str : list) {
                        try {
                            if (!TextUtils.isEmpty(str)) {
                                String[] strArrSplit = str.split("\\.");
                                if (strArrSplit.length > 0 && Long.valueOf(strArrSplit[0]).longValue() <= j2 && strArrSplit.length == 1) {
                                    new File(this.f3885class, str).delete();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.f3891new = jM1186do;
                Cfor cfor4 = this.f3892this;
                String strValueOf = String.valueOf(jM1186do);
                Cnew cnew3 = cfor4.f2468do;
                if (cnew3 != null) {
                    cnew3.mo411do(strValueOf);
                }
            }
            if (System.currentTimeMillis() - this.f3890goto > 60000) {
                this.f3887else = m1195do();
            }
            this.f3890goto = System.currentTimeMillis();
            if (this.f3887else) {
                Cfor cfor5 = this.f3892this;
                int i = celse.f3865case;
                String str2 = celse.f2466do;
                long j3 = celse.f3868try;
                String str3 = celse.f3867new;
                long j4 = celse.f3866for;
                boolean z2 = celse.f2467if;
                Cnew cnew4 = cfor5.f2468do;
                if (cnew4 != null) {
                    cnew4.mo409do(i, str2, j3, str3, j4, z2);
                }
            }
        }
    }

    public final boolean m1195do() {
        StatFs statFs;
        try {
            statFs = new StatFs(this.f3885class);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()) > this.f2479a;
    }

    public void m1196if() {
        if (this.f3893try) {
            return;
        }
        synchronized (this.f2482if) {
            this.f2482if.notify();
        }
    }

    @Override
    public void run() {
        super.run();
        while (this.f3889for) {
            synchronized (this.f2482if) {
                this.f3893try = true;
                try {
                    C2060if c2060ifPoll = this.f3882break.poll();
                    if (c2060ifPoll == null) {
                        this.f3893try = false;
                        this.f2482if.wait();
                        this.f3893try = true;
                    } else {
                        m1194do(c2060ifPoll);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.f3893try = false;
                }
            }
        }
    }
}
