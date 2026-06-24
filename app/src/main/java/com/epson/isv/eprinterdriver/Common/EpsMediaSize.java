package com.epson.isv.eprinterdriver.Common;

import android.os.Parcel;
import android.os.Parcelable;

public class EpsMediaSize implements Parcelable {
    public static final Parcelable.Creator<EpsMediaSize> CREATOR = new Parcelable.Creator<EpsMediaSize>() {
        @Override
        public EpsMediaSize createFromParcel(Parcel parcel) {
            return new EpsMediaSize(parcel);
        }

        @Override
        public EpsMediaSize[] newArray(int i) {
            return new EpsMediaSize[i];
        }
    };
    int mediaSizeID;
    EpsMediaType[] mediaTypeArray;
    int numTypes;

    @Override
    public int describeContents() {
        return 0;
    }

    public EpsMediaSize(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.mediaSizeID = parcel.readInt();
        int i = parcel.readInt();
        this.numTypes = i;
        if (i > 0) {
            this.mediaTypeArray = new EpsMediaType[i];
            for (int i2 = 0; i2 < this.numTypes; i2++) {
                this.mediaTypeArray[i2] = (EpsMediaType) parcel.readParcelable(EpsMediaType.class.getClassLoader());
            }
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mediaSizeID);
        parcel.writeInt(this.numTypes);
        if (this.numTypes > 0) {
            for (int i2 = 0; i2 < this.numTypes; i2++) {
                parcel.writeParcelable(this.mediaTypeArray[i2], i);
            }
        }
    }

    public EpsMediaSize(int i, int i2, EpsMediaType[] epsMediaTypeArr) {
        this.mediaSizeID = i;
        this.numTypes = i2;
        this.mediaTypeArray = epsMediaTypeArr;
    }

    public int getMediaSizeID() {
        return this.mediaSizeID;
    }

    public int getNumTypes() {
        return this.numTypes;
    }

    public EpsMediaType[] getMediaTypeArray() {
        return this.mediaTypeArray;
    }
}
