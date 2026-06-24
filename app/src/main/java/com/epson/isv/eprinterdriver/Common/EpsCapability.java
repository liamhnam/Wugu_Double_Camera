package com.epson.isv.eprinterdriver.Common;

import android.os.Parcel;
import android.os.Parcelable;

public class EpsCapability implements Parcelable {
    public static final Parcelable.Creator<EpsCapability> CREATOR = new Parcelable.Creator<EpsCapability>() {
        @Override
        public EpsCapability createFromParcel(Parcel parcel) {
            return new EpsCapability(parcel);
        }

        @Override
        public EpsCapability[] newArray(int i) {
            return new EpsCapability[i];
        }
    };
    protected static final int EPS_IR_150X150 = 4;
    protected static final int EPS_IR_300X300 = 8;
    public static final int EPS_IR_360X360 = 1;
    int jpegSizeLimit;
    EpsMediaSize[] mediaSizeArray;
    int numSizes;
    int resolution;

    @Override
    public int describeContents() {
        return 0;
    }

    public EpsCapability(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        int i = parcel.readInt();
        this.numSizes = i;
        if (i > 0) {
            this.mediaSizeArray = new EpsMediaSize[i];
            for (int i2 = 0; i2 < this.numSizes; i2++) {
                this.mediaSizeArray[i2] = (EpsMediaSize) parcel.readParcelable(EpsMediaSize.class.getClassLoader());
            }
        }
        this.jpegSizeLimit = parcel.readInt();
        this.resolution = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.numSizes);
        if (this.numSizes > 0) {
            for (int i2 = 0; i2 < this.numSizes; i2++) {
                parcel.writeParcelable(this.mediaSizeArray[i2], i);
            }
        }
        parcel.writeInt(this.jpegSizeLimit);
        parcel.writeInt(this.resolution);
    }

    public EpsCapability() {
        this.numSizes = 0;
        this.mediaSizeArray = null;
        this.jpegSizeLimit = 0;
        this.resolution = 1;
    }

    public EpsCapability(int i, EpsMediaSize[] epsMediaSizeArr, int i2) {
        this.numSizes = i;
        this.mediaSizeArray = epsMediaSizeArr;
        this.jpegSizeLimit = i2;
        this.resolution = 1;
    }

    public EpsCapability(int i, EpsMediaSize[] epsMediaSizeArr, int i2, int i3) {
        this.numSizes = i;
        this.mediaSizeArray = epsMediaSizeArr;
        this.jpegSizeLimit = i2;
        this.resolution = i3;
    }

    public void setEpsCapability(EpsCapability epsCapability) {
        this.numSizes = epsCapability.getNumSizes();
        this.mediaSizeArray = epsCapability.getMediaSizeArray();
        this.jpegSizeLimit = epsCapability.getJpegSizeLimit();
        this.resolution = epsCapability.getResolution();
    }

    public int getNumSizes() {
        return this.numSizes;
    }

    public EpsMediaSize[] getMediaSizeArray() {
        return this.mediaSizeArray;
    }

    public int getJpegSizeLimit() {
        return this.jpegSizeLimit;
    }

    public int getResolution() {
        return this.resolution;
    }

    public void setResolution(int i) {
        this.resolution = i;
    }
}
