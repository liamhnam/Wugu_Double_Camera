package com.epson.isv.eprinterdriver.Ctrl;

import android.os.Parcel;
import android.os.Parcelable;

class ImageAttribute implements Parcelable {
    public static final int BANDTYPE = 2;
    public static final int BMPTYPE = 0;
    public static final Parcelable.Creator<ImageAttribute> CREATOR = new Parcelable.Creator<ImageAttribute>() {
        @Override
        public ImageAttribute createFromParcel(Parcel parcel) {
            return new ImageAttribute(parcel);
        }

        @Override
        public ImageAttribute[] newArray(int i) {
            return new ImageAttribute[i];
        }
    };
    public static final int JPEGTYPE = 1;
    public static final int NATIVETYPE = 3;
    int bandHeight;
    String imageFileName;
    String imageFilePath;
    int imgType;
    String temporaryImageFilePath;

    @Override
    public int describeContents() {
        return 0;
    }

    public ImageAttribute(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.imgType = parcel.readInt();
        this.imageFilePath = parcel.readString();
        this.imageFileName = parcel.readString();
        this.temporaryImageFilePath = parcel.readString();
        this.bandHeight = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.imgType);
        parcel.writeString(this.imageFilePath);
        parcel.writeString(this.imageFileName);
        parcel.writeString(this.temporaryImageFilePath);
        parcel.writeInt(this.bandHeight);
    }

    public ImageAttribute(String str) {
        this.imgType = 3;
        this.imageFilePath = null;
        this.imageFileName = null;
        this.temporaryImageFilePath = str;
    }

    public ImageAttribute(int i, String str, String str2, String str3) {
        this.imgType = i;
        this.imageFilePath = str;
        this.imageFileName = str2;
        this.temporaryImageFilePath = str3;
    }

    public ImageAttribute(int i, int i2) {
        this.imgType = i;
        this.bandHeight = i2;
    }

    public int getImgType() {
        return this.imgType;
    }

    public String getImageFilePath() {
        return this.imageFilePath;
    }

    public String getImageFileName() {
        return this.imageFileName;
    }

    public String getImageFullPathName() {
        return this.imageFilePath;
    }

    public String getTemporaryImageFilePath() {
        return this.temporaryImageFilePath;
    }

    public int getBandHeight() {
        return this.bandHeight;
    }
}
