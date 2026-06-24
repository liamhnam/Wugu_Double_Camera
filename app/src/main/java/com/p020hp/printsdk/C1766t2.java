package com.p020hp.printsdk;

import com.p020hp.open.printsdk.HpHistoryJob;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;

public final class C1766t2 extends HpHistoryJob {

    public final UUID f1794b;

    public final String f1795c;

    public long f1796d;

    public long f1797e;

    public final HpPrintJobState f1798f;

    public final UUID f1799g;

    public final long f1800h;

    public final String f1801i;

    public final String f1802j;

    public final String f1803k;

    public final String f1804l;

    public final String f1805m;

    public final String f1806n;

    public final String f1807o;

    public final String f1808p;

    public final String f1809q;

    public final String f1810r;

    public final String f1811s;

    public final int f1812t;

    public final String f1813u;

    public final String f1814v;

    public final int f1815w;

    public C1766t2(UUID id, String jobName, long j, long j2, HpPrintJobState jobStateInfo, UUID printerId, long j3, String jobType, String jobSize, String jobPath, String optionsCopies, String optionsMediaSize, String optionsColor, String optionsScale, String optionsQuality, String optionsSides, String optionsOrientation, String optionsPageRanges, int i, String optionsInputTrayType, String optionsPaperType, int i2) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(jobName, "jobName");
        Intrinsics.checkNotNullParameter(jobStateInfo, "jobStateInfo");
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(jobType, "jobType");
        Intrinsics.checkNotNullParameter(jobSize, "jobSize");
        Intrinsics.checkNotNullParameter(jobPath, "jobPath");
        Intrinsics.checkNotNullParameter(optionsCopies, "optionsCopies");
        Intrinsics.checkNotNullParameter(optionsMediaSize, "optionsMediaSize");
        Intrinsics.checkNotNullParameter(optionsColor, "optionsColor");
        Intrinsics.checkNotNullParameter(optionsScale, "optionsScale");
        Intrinsics.checkNotNullParameter(optionsQuality, "optionsQuality");
        Intrinsics.checkNotNullParameter(optionsSides, "optionsSides");
        Intrinsics.checkNotNullParameter(optionsOrientation, "optionsOrientation");
        Intrinsics.checkNotNullParameter(optionsPageRanges, "optionsPageRanges");
        Intrinsics.checkNotNullParameter(optionsInputTrayType, "optionsInputTrayType");
        Intrinsics.checkNotNullParameter(optionsPaperType, "optionsPaperType");
        this.f1794b = id;
        this.f1795c = jobName;
        this.f1796d = j;
        this.f1797e = j2;
        this.f1798f = jobStateInfo;
        this.f1799g = printerId;
        this.f1800h = j3;
        this.f1801i = jobType;
        this.f1802j = jobSize;
        this.f1803k = jobPath;
        this.f1804l = optionsCopies;
        this.f1805m = optionsMediaSize;
        this.f1806n = optionsColor;
        this.f1807o = optionsScale;
        this.f1808p = optionsQuality;
        this.f1809q = optionsSides;
        this.f1810r = optionsOrientation;
        this.f1811s = optionsPageRanges;
        this.f1812t = i;
        this.f1813u = optionsInputTrayType;
        this.f1814v = optionsPaperType;
        this.f1815w = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1766t2)) {
            return false;
        }
        C1766t2 c1766t2 = (C1766t2) obj;
        return Intrinsics.areEqual(this.f1794b, c1766t2.f1794b) && Intrinsics.areEqual(this.f1795c, c1766t2.f1795c) && this.f1796d == c1766t2.f1796d && this.f1797e == c1766t2.f1797e && Intrinsics.areEqual(this.f1798f, c1766t2.f1798f) && Intrinsics.areEqual(this.f1799g, c1766t2.f1799g) && this.f1800h == c1766t2.f1800h && Intrinsics.areEqual(this.f1801i, c1766t2.f1801i) && Intrinsics.areEqual(this.f1802j, c1766t2.f1802j) && Intrinsics.areEqual(this.f1803k, c1766t2.f1803k) && Intrinsics.areEqual(this.f1804l, c1766t2.f1804l) && Intrinsics.areEqual(this.f1805m, c1766t2.f1805m) && Intrinsics.areEqual(this.f1806n, c1766t2.f1806n) && Intrinsics.areEqual(this.f1807o, c1766t2.f1807o) && Intrinsics.areEqual(this.f1808p, c1766t2.f1808p) && Intrinsics.areEqual(this.f1809q, c1766t2.f1809q) && Intrinsics.areEqual(this.f1810r, c1766t2.f1810r) && Intrinsics.areEqual(this.f1811s, c1766t2.f1811s) && this.f1812t == c1766t2.f1812t && Intrinsics.areEqual(this.f1813u, c1766t2.f1813u) && Intrinsics.areEqual(this.f1814v, c1766t2.f1814v) && this.f1815w == c1766t2.f1815w;
    }

    @Override
    public long getCreateTime() {
        return this.f1796d;
    }

    @Override
    public UUID getId() {
        return this.f1794b;
    }

    @Override
    public long getJobDoneTime() {
        return this.f1800h;
    }

    @Override
    public String getJobName() {
        return this.f1795c;
    }

    @Override
    public String getJobPath() {
        return this.f1803k;
    }

    @Override
    public String getJobSize() {
        return this.f1802j;
    }

    @Override
    public HpPrintJobState getJobStateInfo() {
        return this.f1798f;
    }

    @Override
    public String getJobType() {
        return this.f1801i;
    }

    @Override
    public String getOptionsColor() {
        return this.f1806n;
    }

    @Override
    public String getOptionsCopies() {
        return this.f1804l;
    }

    @Override
    public int getOptionsDpi() {
        return this.f1815w;
    }

    @Override
    public String getOptionsInputTrayType() {
        return this.f1813u;
    }

    @Override
    public int getOptionsMediaMargin() {
        return this.f1812t;
    }

    @Override
    public String getOptionsMediaSize() {
        return this.f1805m;
    }

    @Override
    public String getOptionsOrientation() {
        return this.f1810r;
    }

    @Override
    public String getOptionsPageRanges() {
        return this.f1811s;
    }

    @Override
    public String getOptionsPaperType() {
        return this.f1814v;
    }

    @Override
    public String getOptionsQuality() {
        return this.f1808p;
    }

    @Override
    public String getOptionsScale() {
        return this.f1807o;
    }

    @Override
    public String getOptionsSides() {
        return this.f1809q;
    }

    @Override
    public UUID getPrinterId() {
        return this.f1799g;
    }

    @Override
    public long getSubmitTime() {
        return this.f1797e;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((this.f1794b.hashCode() * 31) + this.f1795c.hashCode()) * 31) + Long.hashCode(this.f1796d)) * 31) + Long.hashCode(this.f1797e)) * 31) + this.f1798f.hashCode()) * 31) + this.f1799g.hashCode()) * 31) + Long.hashCode(this.f1800h)) * 31) + this.f1801i.hashCode()) * 31) + this.f1802j.hashCode()) * 31) + this.f1803k.hashCode()) * 31) + this.f1804l.hashCode()) * 31) + this.f1805m.hashCode()) * 31) + this.f1806n.hashCode()) * 31) + this.f1807o.hashCode()) * 31) + this.f1808p.hashCode()) * 31) + this.f1809q.hashCode()) * 31) + this.f1810r.hashCode()) * 31) + this.f1811s.hashCode()) * 31) + Integer.hashCode(this.f1812t)) * 31) + this.f1813u.hashCode()) * 31) + this.f1814v.hashCode()) * 31) + Integer.hashCode(this.f1815w);
    }
}
