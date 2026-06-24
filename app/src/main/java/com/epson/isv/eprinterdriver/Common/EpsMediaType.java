package com.epson.isv.eprinterdriver.Common;

import android.os.Parcel;
import android.os.Parcelable;

public class EpsMediaType implements Parcelable {
    public static final Parcelable.Creator<EpsMediaType> CREATOR = new Parcelable.Creator<EpsMediaType>() {
        @Override
        public EpsMediaType createFromParcel(Parcel parcel) {
            return new EpsMediaType(parcel);
        }

        @Override
        public EpsMediaType[] newArray(int i) {
            return new EpsMediaType[i];
        }
    };
    int layout;
    int mediaTypeID;
    int paperSource;
    int quality;

    @Override
    public int describeContents() {
        return 0;
    }

    public EpsMediaType(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.mediaTypeID = parcel.readInt();
        this.layout = parcel.readInt();
        this.quality = parcel.readInt();
        this.paperSource = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mediaTypeID);
        parcel.writeInt(this.layout);
        parcel.writeInt(this.quality);
        parcel.writeInt(this.paperSource);
    }

    public EpsMediaType(int i, int i2, int i3, int i4) {
        this.mediaTypeID = i;
        this.layout = i2;
        this.quality = i3;
        this.paperSource = i4;
    }

    public int getMediaTypeID() {
        return this.mediaTypeID;
    }

    public int getLayout() {
        return this.layout;
    }

    public int getQuality() {
        return this.quality;
    }

    public int getPaperSource() {
        return this.paperSource;
    }
}
