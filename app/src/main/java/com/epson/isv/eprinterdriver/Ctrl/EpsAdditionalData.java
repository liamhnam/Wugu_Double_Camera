package com.epson.isv.eprinterdriver.Ctrl;

import android.os.Parcel;
import android.os.Parcelable;

class EpsAdditionalData implements Parcelable {
    public static final Parcelable.Creator<EpsAdditionalData> CREATOR = new Parcelable.Creator<EpsAdditionalData>() {
        @Override
        public EpsAdditionalData createFromParcel(Parcel parcel) {
            return new EpsAdditionalData(parcel);
        }

        @Override
        public EpsAdditionalData[] newArray(int i) {
            return new EpsAdditionalData[i];
        }
    };
    public static final int EPS_ADDDATA_NONE = 0;
    public static final int EPS_ADDDATA_QRCODE = 1;
    private Object addDataObj;
    private int dataType;

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public EpsAdditionalData(Parcel parcel) {
        readFromParcel(parcel);
    }

    public class EpsQRCode {
        char[] source;
        short srcLength;
        short version = 1;
        int width;
        int xPos;
        int yPos;

        public EpsQRCode(Parcel parcel) {
            readFromParcel(parcel);
        }

        public void readFromParcel(Parcel parcel) {
            this.version = (short) parcel.readInt();
            parcel.readCharArray(this.source);
            this.srcLength = (short) parcel.readInt();
            this.xPos = parcel.readInt();
            this.yPos = parcel.readInt();
            this.width = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.version);
            parcel.writeCharArray(this.source);
            parcel.writeInt(this.srcLength);
            parcel.writeInt(this.xPos);
            parcel.writeInt(this.yPos);
            parcel.writeInt(this.width);
        }

        public EpsQRCode(char[] cArr, short s, int i, int i2, int i3) {
            this.source = cArr;
            this.srcLength = s;
            this.xPos = i;
            this.yPos = i2;
            this.width = i3;
        }

        public short getVersion() {
            return this.version;
        }

        public char[] getSource() {
            return this.source;
        }

        public short getSrcLength() {
            return this.srcLength;
        }

        public int getXPos() {
            return this.xPos;
        }

        public int getYPos() {
            return this.yPos;
        }

        public int getWidth() {
            return this.width;
        }
    }

    public EpsAdditionalData(int i, Object obj) {
        this.dataType = i;
        this.addDataObj = obj;
    }

    public int getDataType() {
        return this.dataType;
    }

    public Object getAddDataObj() {
        return this.addDataObj;
    }
}
