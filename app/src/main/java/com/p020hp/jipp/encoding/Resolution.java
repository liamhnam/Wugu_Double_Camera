package com.p020hp.jipp.encoding;

import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u000f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\t¨\u0006\u001b"}, m1293d2 = {"Lcom/hp/jipp/encoding/Resolution;", "", "crossFeedResolution", "", "feedResolution", "unit", "Lcom/hp/jipp/encoding/ResolutionUnit;", "(IILcom/hp/jipp/encoding/ResolutionUnit;)V", "getCrossFeedResolution", "()I", "getFeedResolution", "getUnit", "()Lcom/hp/jipp/encoding/ResolutionUnit;", "x", "getX", "y", "getY", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class Resolution {
    private final int crossFeedResolution;
    private final int feedResolution;
    private final ResolutionUnit unit;

    public static Resolution copy$default(Resolution resolution, int i, int i2, ResolutionUnit resolutionUnit, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = resolution.crossFeedResolution;
        }
        if ((i3 & 2) != 0) {
            i2 = resolution.feedResolution;
        }
        if ((i3 & 4) != 0) {
            resolutionUnit = resolution.unit;
        }
        return resolution.copy(i, i2, resolutionUnit);
    }

    public final int getCrossFeedResolution() {
        return this.crossFeedResolution;
    }

    public final int getFeedResolution() {
        return this.feedResolution;
    }

    public final ResolutionUnit getUnit() {
        return this.unit;
    }

    public final Resolution copy(int crossFeedResolution, int feedResolution, ResolutionUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new Resolution(crossFeedResolution, feedResolution, unit);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Resolution)) {
            return false;
        }
        Resolution resolution = (Resolution) other;
        return this.crossFeedResolution == resolution.crossFeedResolution && this.feedResolution == resolution.feedResolution && Intrinsics.areEqual(this.unit, resolution.unit);
    }

    public int hashCode() {
        int iHashCode = ((Integer.hashCode(this.crossFeedResolution) * 31) + Integer.hashCode(this.feedResolution)) * 31;
        ResolutionUnit resolutionUnit = this.unit;
        return iHashCode + (resolutionUnit != null ? resolutionUnit.hashCode() : 0);
    }

    public Resolution(int i, int i2, ResolutionUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.crossFeedResolution = i;
        this.feedResolution = i2;
        this.unit = unit;
    }

    public final int getCrossFeedResolution() {
        return this.crossFeedResolution;
    }

    public final int getFeedResolution() {
        return this.feedResolution;
    }

    public final ResolutionUnit getUnit() {
        return this.unit;
    }

    public final int getX() {
        return this.crossFeedResolution;
    }

    public final int getY() {
        return this.feedResolution;
    }

    public String toString() {
        return new StringBuilder().append(getX()).append('x').append(getY()).append(' ').append(this.unit).toString();
    }
}
