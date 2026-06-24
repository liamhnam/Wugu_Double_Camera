package p066do.p026do.p027do;

import android.text.TextUtils;

public class C2060if {

    public Cdo f2476do;

    public Celse f2477if;

    public enum Cdo {
        WRITE,
        FLUSH
    }

    public boolean m1193do() {
        Celse celse;
        Cdo cdo = this.f2476do;
        return cdo != null && ((cdo == Cdo.WRITE && (celse = this.f2477if) != null && (TextUtils.isEmpty(celse.f2466do) ^ true)) || this.f2476do == Cdo.FLUSH);
    }
}
