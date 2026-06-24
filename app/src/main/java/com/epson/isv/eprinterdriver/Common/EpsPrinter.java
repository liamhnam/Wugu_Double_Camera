package com.epson.isv.eprinterdriver.Common;

import android.os.Parcel;
import android.os.Parcelable;

public class EpsPrinter implements Parcelable {
    public static final Parcelable.Creator<EpsPrinter> CREATOR = new Parcelable.Creator<EpsPrinter>() {
        @Override
        public EpsPrinter createFromParcel(Parcel parcel) {
            return new EpsPrinter(parcel);
        }

        @Override
        public EpsPrinter[] newArray(int i) {
            return new EpsPrinter[i];
        }
    };
    protected static final int EPS_LANG_ESCPAGE = 2;
    protected static final int EPS_LANG_ESCPAGE_COLOR = 3;
    public static final int EPS_LANG_ESCPR = 1;
    protected static final int EPS_LANG_UNKNOWN = 0;
    private String friendlyName;
    private int language;
    private String location;
    private String macAddress;
    private String manufacturerName;
    private String modelName;
    private String printerID;
    private EpsProtocol protocol;
    private String serialNo;
    private int supportFuncs;

    @Override
    public int describeContents() {
        return 0;
    }

    public EpsPrinter(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.supportFuncs = parcel.readInt();
        this.protocol = (EpsProtocol) parcel.readParcelable(EpsProtocol.class.getClassLoader());
        this.manufacturerName = parcel.readString();
        this.modelName = parcel.readString();
        this.friendlyName = parcel.readString();
        this.location = parcel.readString();
        this.printerID = parcel.readString();
        this.language = parcel.readInt();
        this.macAddress = parcel.readString();
        this.serialNo = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.supportFuncs);
        parcel.writeParcelable(this.protocol, i);
        parcel.writeString(this.manufacturerName);
        parcel.writeString(this.modelName);
        parcel.writeString(this.friendlyName);
        parcel.writeString(this.location);
        parcel.writeString(this.printerID);
        parcel.writeInt(this.language);
        parcel.writeString(this.macAddress);
        parcel.writeString(this.serialNo);
    }

    public EpsPrinter(int i, EpsProtocol epsProtocol, String str, String str2, String str3, String str4, String str5) {
        this.supportFuncs = i;
        this.protocol = epsProtocol;
        this.manufacturerName = str;
        this.modelName = str2;
        this.friendlyName = str3;
        this.location = str4;
        this.printerID = str5;
        this.language = 1;
        this.macAddress = "";
        this.serialNo = "";
    }

    public int getSupportFuncs() {
        return this.supportFuncs;
    }

    public EpsProtocol getEpsProtocol() {
        return this.protocol;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public String getModelName() {
        return this.modelName;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public String getLocation() {
        return this.location;
    }

    public String getPrinterID() {
        return this.printerID;
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int i) {
        this.language = i;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(String str) {
        this.serialNo = str;
    }
}
