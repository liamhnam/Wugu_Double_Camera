package p066do.p026do.p027do.p067goto;

import android.os.Looper;
import android.text.TextUtils;
import java.io.File;
import p066do.p026do.p027do.C2057do;
import p066do.p026do.p027do.C2060if;
import p066do.p026do.p027do.Celse;
import p066do.p026do.p027do.Ctry;

public class C2058do {

    public static Cnew f2470do = null;

    public static boolean f3871for = false;

    public static C2057do f2471if;

    public static C2059if f3872new;

    public static void m1188do() {
        C2057do c2057do = f2471if;
        if (c2057do == null) {
            throw new RuntimeException("Please initialize Logan first");
        }
        if (TextUtils.isEmpty(c2057do.f3860for)) {
            return;
        }
        C2060if c2060if = new C2060if();
        c2060if.f2476do = C2060if.Cdo.FLUSH;
        c2057do.f2464do.add(c2060if);
        Ctry ctry = c2057do.f3857break;
        if (ctry != null) {
            ctry.m1196if();
        }
    }

    public static void m1189do(String str, String str2, int i) {
        if (f2471if == null) {
            throw new RuntimeException("Please initialize Logan first");
        }
        if (f3872new.f3874else > i) {
            return;
        }
        String str3 = str + ":" + str2;
        C2057do c2057do = f2471if;
        c2057do.getClass();
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        C2060if c2060if = new C2060if();
        c2060if.f2476do = C2060if.Cdo.WRITE;
        Celse celse = new Celse();
        String name = Thread.currentThread().getName();
        long id = Thread.currentThread().getId();
        boolean z = Looper.getMainLooper() == Looper.myLooper();
        celse.f2466do = str3;
        celse.f3868try = System.currentTimeMillis();
        celse.f3865case = i;
        celse.f2467if = z;
        celse.f3866for = id;
        celse.f3867new = name;
        c2060if.f2477if = celse;
        if (c2057do.f2464do.size() < c2057do.f3859else) {
            c2057do.f2464do.add(c2060if);
            Ctry ctry = c2057do.f3857break;
            if (ctry != null) {
                ctry.m1196if();
            }
        }
    }

    public static File[] m1190if() {
        C2057do c2057do = f2471if;
        if (c2057do == null) {
            throw new RuntimeException("Please initialize Logan first");
        }
        c2057do.getClass();
        File file = new File(c2057do.f3860for);
        if (file.exists()) {
            return file.listFiles();
        }
        return null;
    }
}
