package p066do.p026do.p027do;

import com.p020hp.hlog.CHlogProtocol;
import p066do.p026do.p027do.p067goto.Cnew;

public class Cfor implements Cnew {

    public static Cfor f3869new;

    public Cnew f2468do;

    public Cnew f3870for;

    public boolean f2469if;

    @Override
    public void mo408do() {
        Cnew cnew = this.f2468do;
        if (cnew != null) {
            cnew.mo408do();
        }
    }

    @Override
    public void mo409do(int i, String str, long j, String str2, long j2, boolean z) {
        Cnew cnew = this.f2468do;
        if (cnew != null) {
            cnew.mo409do(i, str, j, str2, j2, z);
        }
    }

    @Override
    public void mo410do(Cnew cnew) {
        this.f3870for = cnew;
    }

    @Override
    public void mo411do(String str) {
        Cnew cnew = this.f2468do;
        if (cnew != null) {
            cnew.mo411do(str);
        }
    }

    @Override
    public void mo413do(String str, String str2, int i, String str3, String str4) {
        if (this.f2469if) {
            return;
        }
        if (!CHlogProtocol.f3805case) {
            this.f2468do = null;
            return;
        }
        if (CHlogProtocol.f3806try == null) {
            synchronized (CHlogProtocol.class) {
                if (CHlogProtocol.f3806try == null) {
                    CHlogProtocol.f3806try = new CHlogProtocol();
                }
            }
        }
        CHlogProtocol cHlogProtocol = CHlogProtocol.f3806try;
        this.f2468do = cHlogProtocol;
        cHlogProtocol.mo410do(this.f3870for);
        this.f2468do.mo413do(str, str2, i, str3, str4);
        this.f2469if = true;
    }

    @Override
    public void mo414do(boolean z) {
        Cnew cnew = this.f2468do;
        if (cnew != null) {
            cnew.mo414do(z);
        }
    }
}
