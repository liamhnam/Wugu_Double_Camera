package com.epson.isv.eprinterdriver.Common;

import android.os.Parcel;
import android.os.Parcelable;

public class EpsInkInfo implements Parcelable {
    public static final Parcelable.Creator<EpsInkInfo> CREATOR = new Parcelable.Creator<EpsInkInfo>() {
        @Override
        public EpsInkInfo createFromParcel(Parcel parcel) {
            return new EpsInkInfo(parcel);
        }

        @Override
        public EpsInkInfo[] newArray(int i) {
            return new EpsInkInfo[i];
        }
    };
    public static final int EPS_INK_NUM = 20;
    private static final int EPS_MBOX_NUM = 10;
    public final int EPS_COLOR_BLACK;
    public final int EPS_COLOR_BLACK1;
    public final int EPS_COLOR_BLACK2;
    public final int EPS_COLOR_BLUE;
    public final int EPS_COLOR_CLEAN;
    public final int EPS_COLOR_CLEAR;
    public final int EPS_COLOR_COMPOSITE;
    public final int EPS_COLOR_CYAN;
    public final int EPS_COLOR_DARKYELLOW;
    public final int EPS_COLOR_DEEP_BLUE;
    public final int EPS_COLOR_GRAY;
    public final int EPS_COLOR_GREEN;
    public final int EPS_COLOR_LIGHTBLACK;
    public final int EPS_COLOR_LIGHTCYAN;
    public final int EPS_COLOR_LIGHTGRAY;
    public final int EPS_COLOR_LIGHTLIGHTBLACK;
    public final int EPS_COLOR_LIGHTMAGENTA;
    public final int EPS_COLOR_LIGHTYELLOW;
    public final int EPS_COLOR_MAGENTA;
    public final int EPS_COLOR_MATTEBLACK;
    public final int EPS_COLOR_ORANGE;
    public final int EPS_COLOR_PHOTOBLACK;
    public final int EPS_COLOR_RED;
    public final int EPS_COLOR_UNKNOWN;
    public final int EPS_COLOR_VIOLET;
    public final int EPS_COLOR_VIVID_LIGHTMAGENTA;
    public final int EPS_COLOR_VIVID_MAGENTA;
    public final int EPS_COLOR_WHITE;
    public final int EPS_COLOR_YELLOW;
    public final int EPS_INK_ST_END;
    public final int EPS_INK_ST_FAIL;
    public final int EPS_INK_ST_LOW;
    public final int EPS_INK_ST_NOREAD;
    public final int EPS_INK_ST_NORMAL;
    public final int EPS_INK_ST_NOTAVAIL;
    public final int EPS_INK_ST_NOTPRESENT;
    public final int EPS_MBOX_ST_END;
    public final int EPS_MBOX_ST_FAIL;
    public final int EPS_MBOX_ST_LOW;
    public final int EPS_MBOX_ST_NOREAD;
    public final int EPS_MBOX_ST_NORMAL;
    public final int EPS_MBOX_ST_NOTAVAIL;
    public final int EPS_MBOX_ST_NOTPRESENT;
    private int[] colors;
    private int mBoxNumber;
    private int[] mBoxRemaining;
    private int[] mBoxWaning;
    private int number;
    private int[] remaining;
    private int[] warning;

    @Override
    public int describeContents() {
        return 0;
    }

    public EpsInkInfo(Parcel parcel) {
        this.EPS_COLOR_BLACK = 0;
        this.EPS_COLOR_CYAN = 1;
        this.EPS_COLOR_MAGENTA = 2;
        this.EPS_COLOR_YELLOW = 3;
        this.EPS_COLOR_LIGHTCYAN = 4;
        this.EPS_COLOR_LIGHTMAGENTA = 5;
        this.EPS_COLOR_LIGHTYELLOW = 6;
        this.EPS_COLOR_DARKYELLOW = 7;
        this.EPS_COLOR_LIGHTBLACK = 8;
        this.EPS_COLOR_RED = 9;
        this.EPS_COLOR_VIOLET = 10;
        this.EPS_COLOR_MATTEBLACK = 11;
        this.EPS_COLOR_LIGHTLIGHTBLACK = 12;
        this.EPS_COLOR_PHOTOBLACK = 13;
        this.EPS_COLOR_CLEAR = 14;
        this.EPS_COLOR_GRAY = 15;
        this.EPS_COLOR_UNKNOWN = 16;
        this.EPS_COLOR_BLACK2 = 17;
        this.EPS_COLOR_ORANGE = 18;
        this.EPS_COLOR_GREEN = 19;
        this.EPS_COLOR_WHITE = 20;
        this.EPS_COLOR_CLEAN = 21;
        this.EPS_COLOR_COMPOSITE = 22;
        this.EPS_COLOR_BLACK1 = 23;
        this.EPS_COLOR_BLUE = 24;
        this.EPS_COLOR_DEEP_BLUE = 25;
        this.EPS_COLOR_VIVID_MAGENTA = 26;
        this.EPS_COLOR_VIVID_LIGHTMAGENTA = 27;
        this.EPS_COLOR_LIGHTGRAY = 28;
        this.EPS_INK_ST_NORMAL = 0;
        this.EPS_INK_ST_LOW = 1;
        this.EPS_INK_ST_END = 2;
        this.EPS_INK_ST_NOTPRESENT = -1;
        this.EPS_INK_ST_FAIL = -2;
        this.EPS_INK_ST_NOTAVAIL = -3;
        this.EPS_INK_ST_NOREAD = -4;
        this.EPS_MBOX_ST_NORMAL = 0;
        this.EPS_MBOX_ST_LOW = 1;
        this.EPS_MBOX_ST_END = 2;
        this.EPS_MBOX_ST_NOTPRESENT = -1;
        this.EPS_MBOX_ST_FAIL = -2;
        this.EPS_MBOX_ST_NOTAVAIL = -3;
        this.EPS_MBOX_ST_NOREAD = -4;
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.number = parcel.readInt();
        parcel.readIntArray(this.colors);
        parcel.readIntArray(this.remaining);
        parcel.readIntArray(this.warning);
        this.mBoxNumber = parcel.readInt();
        parcel.readIntArray(this.mBoxRemaining);
        parcel.readIntArray(this.mBoxWaning);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.number);
        parcel.writeIntArray(this.colors);
        parcel.writeIntArray(this.remaining);
        parcel.writeIntArray(this.warning);
        parcel.writeInt(this.mBoxNumber);
        parcel.writeIntArray(this.mBoxRemaining);
        parcel.writeIntArray(this.mBoxWaning);
    }

    public EpsInkInfo() {
        this.EPS_COLOR_BLACK = 0;
        this.EPS_COLOR_CYAN = 1;
        this.EPS_COLOR_MAGENTA = 2;
        this.EPS_COLOR_YELLOW = 3;
        this.EPS_COLOR_LIGHTCYAN = 4;
        this.EPS_COLOR_LIGHTMAGENTA = 5;
        this.EPS_COLOR_LIGHTYELLOW = 6;
        this.EPS_COLOR_DARKYELLOW = 7;
        this.EPS_COLOR_LIGHTBLACK = 8;
        this.EPS_COLOR_RED = 9;
        this.EPS_COLOR_VIOLET = 10;
        this.EPS_COLOR_MATTEBLACK = 11;
        this.EPS_COLOR_LIGHTLIGHTBLACK = 12;
        this.EPS_COLOR_PHOTOBLACK = 13;
        this.EPS_COLOR_CLEAR = 14;
        this.EPS_COLOR_GRAY = 15;
        this.EPS_COLOR_UNKNOWN = 16;
        this.EPS_COLOR_BLACK2 = 17;
        this.EPS_COLOR_ORANGE = 18;
        this.EPS_COLOR_GREEN = 19;
        this.EPS_COLOR_WHITE = 20;
        this.EPS_COLOR_CLEAN = 21;
        this.EPS_COLOR_COMPOSITE = 22;
        this.EPS_COLOR_BLACK1 = 23;
        this.EPS_COLOR_BLUE = 24;
        this.EPS_COLOR_DEEP_BLUE = 25;
        this.EPS_COLOR_VIVID_MAGENTA = 26;
        this.EPS_COLOR_VIVID_LIGHTMAGENTA = 27;
        this.EPS_COLOR_LIGHTGRAY = 28;
        this.EPS_INK_ST_NORMAL = 0;
        this.EPS_INK_ST_LOW = 1;
        this.EPS_INK_ST_END = 2;
        this.EPS_INK_ST_NOTPRESENT = -1;
        this.EPS_INK_ST_FAIL = -2;
        this.EPS_INK_ST_NOTAVAIL = -3;
        this.EPS_INK_ST_NOREAD = -4;
        this.EPS_MBOX_ST_NORMAL = 0;
        this.EPS_MBOX_ST_LOW = 1;
        this.EPS_MBOX_ST_END = 2;
        this.EPS_MBOX_ST_NOTPRESENT = -1;
        this.EPS_MBOX_ST_FAIL = -2;
        this.EPS_MBOX_ST_NOTAVAIL = -3;
        this.EPS_MBOX_ST_NOREAD = -4;
        this.number = 0;
        this.colors = new int[20];
        this.remaining = new int[20];
        this.warning = new int[20];
        this.mBoxNumber = 0;
        this.mBoxRemaining = new int[10];
        this.mBoxWaning = new int[10];
    }

    public EpsInkInfo(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        this.EPS_COLOR_BLACK = 0;
        this.EPS_COLOR_CYAN = 1;
        this.EPS_COLOR_MAGENTA = 2;
        this.EPS_COLOR_YELLOW = 3;
        this.EPS_COLOR_LIGHTCYAN = 4;
        this.EPS_COLOR_LIGHTMAGENTA = 5;
        this.EPS_COLOR_LIGHTYELLOW = 6;
        this.EPS_COLOR_DARKYELLOW = 7;
        this.EPS_COLOR_LIGHTBLACK = 8;
        this.EPS_COLOR_RED = 9;
        this.EPS_COLOR_VIOLET = 10;
        this.EPS_COLOR_MATTEBLACK = 11;
        this.EPS_COLOR_LIGHTLIGHTBLACK = 12;
        this.EPS_COLOR_PHOTOBLACK = 13;
        this.EPS_COLOR_CLEAR = 14;
        this.EPS_COLOR_GRAY = 15;
        this.EPS_COLOR_UNKNOWN = 16;
        this.EPS_COLOR_BLACK2 = 17;
        this.EPS_COLOR_ORANGE = 18;
        this.EPS_COLOR_GREEN = 19;
        this.EPS_COLOR_WHITE = 20;
        this.EPS_COLOR_CLEAN = 21;
        this.EPS_COLOR_COMPOSITE = 22;
        this.EPS_COLOR_BLACK1 = 23;
        this.EPS_COLOR_BLUE = 24;
        this.EPS_COLOR_DEEP_BLUE = 25;
        this.EPS_COLOR_VIVID_MAGENTA = 26;
        this.EPS_COLOR_VIVID_LIGHTMAGENTA = 27;
        this.EPS_COLOR_LIGHTGRAY = 28;
        this.EPS_INK_ST_NORMAL = 0;
        this.EPS_INK_ST_LOW = 1;
        this.EPS_INK_ST_END = 2;
        this.EPS_INK_ST_NOTPRESENT = -1;
        this.EPS_INK_ST_FAIL = -2;
        this.EPS_INK_ST_NOTAVAIL = -3;
        this.EPS_INK_ST_NOREAD = -4;
        this.EPS_MBOX_ST_NORMAL = 0;
        this.EPS_MBOX_ST_LOW = 1;
        this.EPS_MBOX_ST_END = 2;
        this.EPS_MBOX_ST_NOTPRESENT = -1;
        this.EPS_MBOX_ST_FAIL = -2;
        this.EPS_MBOX_ST_NOTAVAIL = -3;
        this.EPS_MBOX_ST_NOREAD = -4;
        this.number = i;
        this.colors = iArr;
        this.remaining = iArr2;
        this.warning = iArr3;
        this.mBoxNumber = 0;
        this.mBoxRemaining = new int[10];
        this.mBoxWaning = new int[10];
    }

    public int getNumber() {
        return this.number;
    }

    public int[] getColors() {
        return this.colors;
    }

    public int[] getRemaining() {
        return this.remaining;
    }

    public int[] getWarning() {
        return this.warning;
    }

    public int getMboxNumber() {
        return this.mBoxNumber;
    }

    public int[] getMboxRemaining() {
        return this.mBoxRemaining;
    }

    public int[] getMboxWaning() {
        return this.mBoxWaning;
    }

    public void setNumber(int i) {
        this.number = i;
    }

    protected void setMboxNumber(int i) {
        this.mBoxNumber = i;
    }

    public void setInkInfo(int i, int i2, int i3, int i4) {
        this.colors[i] = i2;
        this.remaining[i] = i3;
        this.warning[i] = i4;
    }

    protected void setMboxInfo(int i, int i2, int i3) {
        this.mBoxRemaining[i] = i2;
        this.mBoxWaning[i] = i3;
    }

    public String toString() {
        String str = "インク数：" + this.number + "\n";
        for (int i = 0; i < this.number; i++) {
            String str2 = (str + this.colors[i] + "  : ") + this.remaining[i];
            if (this.remaining[i] > 0) {
                str2 = str2 + "%";
            }
            str = str2 + " WR(" + this.warning[i] + ")\n";
        }
        for (int i2 = 0; i2 < this.mBoxNumber; i2++) {
            String str3 = (str + "MBox " + i2 + " : ") + this.mBoxRemaining[i2];
            if (this.mBoxRemaining[i2] > 0) {
                str3 = str3 + "%";
            }
            str = str3 + " WR(" + this.mBoxWaning[i2] + ")\n";
        }
        return str;
    }
}
