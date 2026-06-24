package com.epson.isv.eprinterdriver.Ctrl;

import android.os.Parcel;
import android.os.Parcelable;

class PrintableArea implements Parcelable {
    public static final Parcelable.Creator<PrintableArea> CREATOR = new Parcelable.Creator<PrintableArea>() {
        @Override
        public PrintableArea createFromParcel(Parcel parcel) {
            return new PrintableArea(parcel);
        }

        @Override
        public PrintableArea[] newArray(int i) {
            return new PrintableArea[i];
        }
    };
    private int printableHeight;
    private int printableWidth;

    @Override
    public int describeContents() {
        return 0;
    }

    public PrintableArea(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.printableWidth = parcel.readInt();
        this.printableHeight = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.printableWidth);
        parcel.writeInt(this.printableHeight);
    }

    public PrintableArea() {
        this.printableWidth = 0;
        this.printableHeight = 0;
    }

    public int getPrintableWidth() {
        return this.printableWidth;
    }

    public void setPrintableWidth(int i) {
        this.printableWidth = i;
    }

    public int getPrintableHeight() {
        return this.printableHeight;
    }

    public void setPrintableHeight(int i) {
        this.printableHeight = i;
    }
}
