package com.epson.isv.eprinterdriver.Ctrl;

import android.os.Parcel;
import android.os.Parcelable;
import com.epson.isv.eprinterdriver.Common.EpsPrinter;

class JobAttribute implements Parcelable {
    public static final Parcelable.Creator<JobAttribute> CREATOR = new Parcelable.Creator<JobAttribute>() {
        @Override
        public JobAttribute createFromParcel(Parcel parcel) {
            return new JobAttribute(parcel);
        }

        @Override
        public JobAttribute[] newArray(int i) {
            return new JobAttribute[i];
        }
    };
    EpsAdditionalData additionData;
    boolean bAdditionData;
    boolean bAutoRotation;
    boolean bAutoScalling;
    boolean bFastCopy;
    ImageAttribute imageAttribute;
    PageAttribute pageAttribute;
    EpsPrinter printer;
    int sheets;

    @Override
    public int describeContents() {
        return 0;
    }

    public JobAttribute(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel.readByte() == 1) {
            this.bAdditionData = true;
            this.additionData = (EpsAdditionalData) parcel.readParcelable(EpsAdditionalData.class.getClassLoader());
        } else {
            this.bAdditionData = false;
            this.additionData = null;
        }
        this.printer = (EpsPrinter) parcel.readParcelable(EpsPrinter.class.getClassLoader());
        this.pageAttribute = (PageAttribute) parcel.readParcelable(PageAttribute.class.getClassLoader());
        this.imageAttribute = (ImageAttribute) parcel.readParcelable(ImageAttribute.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.bAutoScalling = true;
        } else {
            this.bAutoScalling = false;
        }
        if (parcel.readByte() == 1) {
            this.bAutoRotation = true;
        } else {
            this.bAutoRotation = false;
        }
        this.sheets = parcel.readInt();
        if (parcel.readByte() == 1) {
            this.bFastCopy = true;
        } else {
            this.bFastCopy = false;
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (this.bAdditionData) {
            parcel.writeByte((byte) 1);
            parcel.writeParcelable(this.additionData, i);
        } else {
            parcel.writeByte((byte) 0);
        }
        parcel.writeParcelable(this.printer, i);
        parcel.writeParcelable(this.pageAttribute, i);
        parcel.writeParcelable(this.imageAttribute, i);
        if (this.bAutoScalling) {
            parcel.writeByte((byte) 1);
        } else {
            parcel.writeByte((byte) 0);
        }
        if (this.bAutoRotation) {
            parcel.writeByte((byte) 1);
        } else {
            parcel.writeByte((byte) 0);
        }
        parcel.writeInt(this.sheets);
        if (this.bFastCopy) {
            parcel.writeByte((byte) 1);
        } else {
            parcel.writeByte((byte) 0);
        }
    }

    public JobAttribute(EpsPrinter epsPrinter, PageAttribute pageAttribute, ImageAttribute imageAttribute, boolean z, boolean z2, int i) {
        this.bAdditionData = false;
        this.printer = epsPrinter;
        this.pageAttribute = pageAttribute;
        this.imageAttribute = imageAttribute;
        this.bAutoScalling = z;
        this.bAutoRotation = z2;
        this.sheets = i;
    }

    public JobAttribute(EpsPrinter epsPrinter, PageAttribute pageAttribute, ImageAttribute imageAttribute, EpsAdditionalData epsAdditionalData, boolean z, boolean z2, int i) {
        this.bAdditionData = true;
        this.printer = epsPrinter;
        this.pageAttribute = pageAttribute;
        this.imageAttribute = imageAttribute;
        this.additionData = epsAdditionalData;
        this.bAutoScalling = z;
        this.bAutoRotation = z2;
        this.sheets = i;
    }

    public JobAttribute(EpsPrinter epsPrinter, PageAttribute pageAttribute, ImageAttribute imageAttribute, boolean z, int i) {
        this.bAdditionData = true;
        this.printer = epsPrinter;
        this.pageAttribute = pageAttribute;
        this.imageAttribute = imageAttribute;
        this.additionData = null;
        this.bAutoScalling = false;
        this.bAutoRotation = false;
        this.bFastCopy = z;
        this.sheets = i;
    }

    public EpsPrinter getPrinter() {
        return this.printer;
    }

    public PageAttribute getPageAttribute() {
        return this.pageAttribute;
    }

    public ImageAttribute getImageAttribute() {
        return this.imageAttribute;
    }

    public EpsAdditionalData getAdditionData() {
        return this.additionData;
    }

    public boolean isAdditionData() {
        return this.bAdditionData;
    }

    public boolean isAutoScalling() {
        return this.bAutoScalling;
    }

    public boolean isAutoRotation() {
        return this.bAutoRotation;
    }

    public int getSheets() {
        return this.sheets;
    }

    public void setFastCopy(boolean z) {
        this.bFastCopy = z;
    }

    public boolean isFastCopy() {
        return this.bFastCopy;
    }
}
