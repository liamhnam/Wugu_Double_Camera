package com.epson.isv.eprinterdriver.Common;

import android.os.Parcel;
import android.os.Parcelable;

public class EpsProtocol implements Parcelable {
    public static final Parcelable.Creator<EpsProtocol> CREATOR = new Parcelable.Creator<EpsProtocol>() {
        @Override
        public EpsProtocol createFromParcel(Parcel parcel) {
            return new EpsProtocol(parcel);
        }

        @Override
        public EpsProtocol[] newArray(int i) {
            return new EpsProtocol[i];
        }
    };
    public static final int EPS_PROTOCOL_ALL = 208;
    public static final int EPS_PROTOCOL_LPR = 64;
    public static final int EPS_PROTOCOL_NET = 192;
    public static final int EPS_PROTOCOL_RAW = 128;
    protected static final int EPS_PROTOCOL_USB = 16;
    private int protocol;

    public static boolean isSuppportUsbOtg() {
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public EpsProtocol(Parcel parcel) {
        this.protocol = 0;
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.protocol = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.protocol);
    }

    public EpsProtocol(int i) {
        this.protocol = 0;
        this.protocol = isSuppportUsbOtg() ? i : i & (-17);
    }

    public void reSetProtocol() {
        this.protocol = 0;
    }

    public void setProtocol(int i) {
        if (!isSuppportUsbOtg()) {
            i &= -17;
        }
        this.protocol = i | this.protocol;
    }

    public int getProtocol() {
        return this.protocol;
    }
}
