package com.p020hp.mobile.common.usb;

import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;

@Metadata(m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, m1293d2 = {"Lcom/hp/mobile/common/usb/Interface;", "", "mId", "", "mClass", "mSubClass", "mProtocol", "(IIII)V", "getMClass", "()I", "getMId", "getMProtocol", "getMSubClass", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class Interface {
    public final int mClass;
    public final int mId;
    public final int mProtocol;
    public final int mSubClass;

    public Interface(int i, int i2, int i3, int i4) {
        this.mId = i;
        this.mClass = i2;
        this.mSubClass = i3;
        this.mProtocol = i4;
    }

    public static Interface copy$default(Interface r0, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = r0.mId;
        }
        if ((i5 & 2) != 0) {
            i2 = r0.mClass;
        }
        if ((i5 & 4) != 0) {
            i3 = r0.mSubClass;
        }
        if ((i5 & 8) != 0) {
            i4 = r0.mProtocol;
        }
        return r0.copy(i, i2, i3, i4);
    }

    public final int getMId() {
        return this.mId;
    }

    public final int getMClass() {
        return this.mClass;
    }

    public final int getMSubClass() {
        return this.mSubClass;
    }

    public final int getMProtocol() {
        return this.mProtocol;
    }

    public final Interface copy(int mId, int mClass, int mSubClass, int mProtocol) {
        return new Interface(mId, mClass, mSubClass, mProtocol);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Interface)) {
            return false;
        }
        Interface r5 = (Interface) other;
        return this.mId == r5.mId && this.mClass == r5.mClass && this.mSubClass == r5.mSubClass && this.mProtocol == r5.mProtocol;
    }

    public final int getMClass() {
        return this.mClass;
    }

    public final int getMId() {
        return this.mId;
    }

    public final int getMProtocol() {
        return this.mProtocol;
    }

    public final int getMSubClass() {
        return this.mSubClass;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mId) * 31) + Integer.hashCode(this.mClass)) * 31) + Integer.hashCode(this.mSubClass)) * 31) + Integer.hashCode(this.mProtocol);
    }

    public String toString() {
        return "Interface(mId=" + this.mId + ", mClass=" + this.mClass + ", mSubClass=" + this.mSubClass + ", mProtocol=" + this.mProtocol + ')';
    }
}
