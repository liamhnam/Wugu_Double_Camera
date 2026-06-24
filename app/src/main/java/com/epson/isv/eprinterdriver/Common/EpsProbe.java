package com.epson.isv.eprinterdriver.Common;

import android.os.Parcel;
import android.os.Parcelable;
import org.apache.log4j.spi.LocationInfo;

public class EpsProbe implements Parcelable {
    public static final Parcelable.Creator<EpsProbe> CREATOR = new Parcelable.Creator<EpsProbe>() {
        @Override
        public EpsProbe createFromParcel(Parcel parcel) {
            return new EpsProbe(parcel);
        }

        @Override
        public EpsProbe[] newArray(int i) {
            return new EpsProbe[i];
        }
    };
    public static final int EPS_PRB_BYADDR = 2;
    public static final int EPS_PRB_BYID = 1;
    private String identify;
    private String ipAddress;
    private EpsProtocol protocol;
    private int searchMethod;
    private int timeout;
    private int version;

    @Override
    public int describeContents() {
        return 0;
    }

    public EpsProbe(Parcel parcel) {
        this.version = 1;
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.version = parcel.readInt();
        this.searchMethod = parcel.readInt();
        this.timeout = parcel.readInt();
        this.identify = parcel.readString();
        this.protocol = (EpsProtocol) parcel.readParcelable(EpsProtocol.class.getClassLoader());
        this.ipAddress = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.version);
        parcel.writeInt(this.searchMethod);
        parcel.writeInt(this.timeout);
        parcel.writeString(this.identify);
        parcel.writeParcelable(this.protocol, i);
        parcel.writeString(this.ipAddress);
    }

    public EpsProbe(String str, int i) {
        this.version = 1;
        this.searchMethod = 1;
        this.timeout = i;
        this.identify = str;
    }

    public EpsProbe(String str, String str2, int i) {
        this.version = 1;
        this.searchMethod = 1;
        this.timeout = i;
        this.identify = "P#0040#D#" + str + LocationInfo.f2800NA + str2;
    }

    public EpsProbe(EpsProtocol epsProtocol, String str, int i) {
        this.version = 1;
        this.searchMethod = 2;
        this.timeout = i;
        this.protocol = epsProtocol;
        this.ipAddress = str;
    }

    public void setIDSearch(String str, int i) {
        this.searchMethod = 1;
        this.timeout = i;
        this.identify = str;
    }

    public void setIPSearch(EpsProtocol epsProtocol, String str, int i) {
        this.searchMethod = 2;
        this.timeout = i;
        this.protocol = epsProtocol;
        this.ipAddress = str;
    }

    public int getVersion() {
        return this.version;
    }

    public int getSearchMethod() {
        return this.searchMethod;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public String getIdentify() {
        return this.identify;
    }

    public EpsProtocol getProtocol() {
        return this.protocol;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }
}
