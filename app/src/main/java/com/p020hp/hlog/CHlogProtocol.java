package com.p020hp.hlog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import p066do.p026do.p027do.Cnew;
import p066do.p026do.p027do.p067goto.C2058do;

public class CHlogProtocol implements Cnew {

    public static boolean f3805case;

    public static CHlogProtocol f3806try;

    public boolean f714do;

    public p066do.p026do.p027do.p067goto.Cnew f3807for;

    public boolean f715if;

    public Set<Integer> f3808new = Collections.synchronizedSet(new HashSet());

    static {
        try {
            System.loadLibrary("logan");
            f3805case = true;
        } catch (Throwable th) {
            th.printStackTrace();
            f3805case = false;
        }
    }

    public final native void clogan_debug(boolean z);

    public final native void clogan_flush();

    public final native int clogan_init(String str, String str2, int i, String str3, String str4);

    public final native int clogan_open(String str);

    public final native int clogan_write(int i, String str, long j, String str2, long j2, int i2);

    @Override
    public void mo408do() {
        if (this.f715if && f3805case) {
            try {
                clogan_flush();
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mo409do(int i, String str, long j, String str2, long j2, boolean z) {
        if (this.f715if && f3805case) {
            try {
                int iClogan_write = clogan_write(i, str, j, str2, j2, z ? 1 : 0);
                if (iClogan_write != -4010 || C2058do.f3871for) {
                    m412do("clogan_write", iClogan_write);
                }
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
                m412do("clogan_write", -4060);
            }
        }
    }

    @Override
    public void mo410do(p066do.p026do.p027do.p067goto.Cnew cnew) {
        this.f3807for = cnew;
    }

    @Override
    public void mo411do(String str) {
        if (this.f714do && f3805case) {
            try {
                int iClogan_open = clogan_open(str);
                this.f715if = true;
                m412do("clogan_open", iClogan_open);
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
                m412do("clogan_open", -2070);
            }
        }
    }

    public final void m412do(String str, int i) {
        if (i < 0) {
            if ("clogan_write".endsWith(str) && i != -4060) {
                if (this.f3808new.contains(Integer.valueOf(i))) {
                    return;
                } else {
                    this.f3808new.add(Integer.valueOf(i));
                }
            }
            p066do.p026do.p027do.p067goto.Cnew cnew = this.f3807for;
            if (cnew != null) {
                cnew.mo445do(str, i);
            }
        }
    }

    @Override
    public void mo413do(String str, String str2, int i, String str3, String str4) {
        if (this.f714do) {
            return;
        }
        if (!f3805case) {
            m412do("logan_loadso", -5020);
            return;
        }
        try {
            int iClogan_init = clogan_init(str, str2, i, str3, str4);
            this.f714do = true;
            m412do("clogan_init", iClogan_init);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            m412do("clogan_init", -1060);
        }
    }

    @Override
    public void mo414do(boolean z) {
        if (this.f714do && f3805case) {
            try {
                clogan_debug(z);
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }
        }
    }
}
