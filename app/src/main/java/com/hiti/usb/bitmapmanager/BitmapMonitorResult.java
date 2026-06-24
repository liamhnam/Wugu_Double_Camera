package com.hiti.usb.bitmapmanager;

import android.content.Context;
import android.graphics.Bitmap;
import com.hiti.usb.utility.ResourceSearcher;

public class BitmapMonitorResult {
    public static final int ERROR_FILE_FORMAT = 96;
    public static final int ERROR_OUT_OF_MEMORY = 99;
    public static final int ERROR_PHOTO_RATIO = 94;
    public static final int ERROR_SD_CARD_MAYBE_FULL = 93;
    public static final int ERROR_SD_CARD_NOT_EXIST = 95;
    public static final int ERROR_SIZE_IS_ZERO = 98;
    public static final int ERROR_SOURCE_NOT_FOUND = 97;
    public static final int ERROR_UNKNOWN = 100;
    public static final int SUCCESS = 0;
    public static final int WARNING = -1;
    private double m_dScale = 0.0d;
    private int m_iSampleSize = 0;
    private Bitmap m_Bmp = null;
    private int m_iResult = -1;

    public static String GetError(Context context, int i) {
        ResourceSearcher.RS_TYPE rs_type;
        String str;
        if (i != -1 && i != 0) {
            if (i == 99) {
                rs_type = ResourceSearcher.RS_TYPE.STRING;
                str = "CREATE_BITMAP_OUT_OF_MEMORY";
            } else if (i == 98) {
                rs_type = ResourceSearcher.RS_TYPE.STRING;
                str = "CREATE_BITMAP_SIZE_IS_ZERO";
            } else if (i == 97) {
                rs_type = ResourceSearcher.RS_TYPE.STRING;
                str = "CREATE_BITMAP_SOURCE_NOT_FOUND";
            } else if (i == 96) {
                rs_type = ResourceSearcher.RS_TYPE.STRING;
                str = "CREATE_BITMAP_FILE_FORMAT";
            } else if (i == 95) {
                rs_type = ResourceSearcher.RS_TYPE.STRING;
                str = "CREATE_BITMAP_SD_CARD_NOT_EXIST";
            } else if (i == 94) {
                rs_type = ResourceSearcher.RS_TYPE.STRING;
                str = "CREATE_BITMAP_ERROR_PHOTO_RATIO";
            } else if (i == 93) {
                rs_type = ResourceSearcher.RS_TYPE.STRING;
                str = "CREATE_BITMAP_SD_CARD_MAYBE_FULL";
            } else if (i == 100) {
                rs_type = ResourceSearcher.RS_TYPE.STRING;
                str = "CREATE_BITMAP_ERROR_UNKNOWN";
            }
            return context.getString(ResourceSearcher.getId(context, rs_type, str));
        }
        return "";
    }

    public Bitmap GetBitmap() {
        if (this.m_iResult == 0) {
            return this.m_Bmp;
        }
        return null;
    }

    public String GetError(Context context) {
        return GetError(context, this.m_iResult);
    }

    public int GetResult() {
        return this.m_iResult;
    }

    public int GetSampleSize() {
        return this.m_iSampleSize;
    }

    public double GetScale() {
        return this.m_dScale;
    }

    public boolean IsSuccess() {
        return this.m_iResult == 0;
    }

    public void SetBitmap(Bitmap bitmap) {
        this.m_Bmp = bitmap;
    }

    public void SetPixelWarning(double d, int i) {
        if (d > 1.0d) {
            SetScale(d);
            SetSampleSize(i);
        }
    }

    public void SetResult(int i) {
        Bitmap bitmap;
        this.m_iResult = i;
        if (i == 0 || (bitmap = this.m_Bmp) == null) {
            return;
        }
        bitmap.recycle();
        this.m_Bmp = null;
    }

    public void SetSampleSize(int i) {
        this.m_iSampleSize = i;
    }

    public void SetScale(double d) {
        this.m_dScale = d;
    }
}
