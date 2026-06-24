package com.epson.isv.eprinterdriver.Ctrl;

import android.os.Parcel;
import android.os.Parcelable;
import com.epson.isv.eprinterdriver.Common.EpsProtocol;

class EpsCommMode implements Parcelable {
    public static final Parcelable.Creator<EpsCommMode> CREATOR = new Parcelable.Creator<EpsCommMode>() {
        @Override
        public EpsCommMode createFromParcel(Parcel parcel) {
            return new EpsCommMode(parcel);
        }

        @Override
        public EpsCommMode[] newArray(int i) {
            return new EpsCommMode[i];
        }
    };
    public static final int EPS_COMM_BID = 2;
    public static final int EPS_COMM_UNID = 1;
    public static final int EPS_PROTOCOL_ALL = 208;
    public static final int EPS_PROTOCOL_LPR = 64;
    public static final int EPS_PROTOCOL_NET = 192;
    public static final int EPS_PROTOCOL_RAW = 128;
    public static final int EPS_PROTOCOL_USB = 16;
    private int commMode;

    @Override
    public int describeContents() {
        return 0;
    }

    public EpsCommMode(Parcel parcel) {
        this.commMode = 0;
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.commMode = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.commMode);
    }

    public EpsCommMode(int i, int i2) {
        this.commMode = 0;
        this.commMode = i | (EpsProtocol.isSuppportUsbOtg() ? i2 : i2 & (-17));
    }

    public void reSetCommMode() {
        this.commMode = 0;
    }

    public void setCommMode(int i, int i2) {
        if (!EpsProtocol.isSuppportUsbOtg()) {
            i2 &= -17;
        }
        this.commMode = i | i2;
    }

    public int getCommMode() {
        return this.commMode;
    }
}
