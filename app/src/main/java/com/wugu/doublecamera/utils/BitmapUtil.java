package com.wugu.doublecamera.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Base64;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtil {
    public static Bitmap getLocalBitmap(File file) {
        try {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getLocalBitmap(Context context, String str) {
        if (str.startsWith("file:///android_asset/")) {
            Bitmap bitmapDecodeStream = null;
            try {
                InputStream inputStreamOpen = context.getAssets().open(str.substring(22));
                try {
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
                    if (inputStreamOpen == null) {
                        return bitmapDecodeStream;
                    }
                    inputStreamOpen.close();
                    return bitmapDecodeStream;
                } finally {
                }
            } catch (IOException e) {
                e.printStackTrace();
                return bitmapDecodeStream;
            }
        }
        return BitmapFactory.decodeFile(str);
    }

    public static Bitmap getLocalBitmap(Context context, String str, int i, int i2, int i3, int i4) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        if (bitmapDecodeFile == null || i < 0 || i2 < 0 || i3 > bitmapDecodeFile.getWidth() || i4 > bitmapDecodeFile.getHeight()) {
            return null;
        }
        return Bitmap.createBitmap(bitmapDecodeFile, i, i2, i3 - i, i4 - i2);
    }

    public static Bitmap copyBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        new Canvas(bitmapCreateBitmap).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static Bitmap getResizedBitmapFromPath(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int iCalculateInSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        options.inSampleSize = iCalculateInSampleSize;
        return Bitmap.createScaledBitmap(BitmapFactory.decodeFile(str, options), i, i2, true);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static byte[] getBitmapRgbData(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i * 3];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            int i4 = i2 * 3;
            bArr[i4] = (byte) ((i3 >> 16) & 255);
            bArr[i4 + 1] = (byte) ((i3 >> 8) & 255);
            bArr[i4 + 2] = (byte) (i3 & 255);
        }
        return bArr;
    }

    public static byte[] getBitmapBgrData(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i * 3];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            int i4 = i2 * 3;
            bArr[i4] = (byte) (i3 & 255);
            bArr[i4 + 1] = (byte) ((i3 >> 8) & 255);
            bArr[i4 + 2] = (byte) ((i3 >> 16) & 255);
        }
        return bArr;
    }

    public static Bitmap compressBitmap(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f2 = width;
        if (f2 <= f && height <= f) {
            return bitmap;
        }
        if (width <= height) {
            f2 = height;
        }
        float f3 = f / f2;
        Matrix matrix = new Matrix();
        matrix.postScale(f3, f3);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static BitmapFactory.Options getBitmapOptions(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("file:///android_asset/")) {
            try {
                InputStream inputStreamOpen = context.getAssets().open(str.replace("file:///android_asset/", ""));
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStreamOpen, null, options);
                inputStreamOpen.close();
                return options;
            } catch (IOException unused) {
                return null;
            }
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options2);
        return options2;
    }

    public static BitmapFactory.Options getBitmapOptions(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options;
    }

    public static Bitmap getScaleMarginBitmap(Bitmap bitmap, int i, int i2, int i3, int i4, int i5, int i6) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth() + i3 + i5, bitmap.getHeight() + i4 + i6, bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bitmap, i3, i4, (Paint) null);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateBitmap, i, i2, true);
        bitmapCreateBitmap.recycle();
        return bitmapCreateScaledBitmap;
    }

    public static Bitmap getTextBitmap(String str, int i, int i2) {
        Paint paint = new Paint(1);
        paint.setTextSize(i);
        paint.setColor(i2);
        paint.setTypeface(Typeface.DEFAULT);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap((int) paint.measureText(str), (int) (paint.descent() - paint.ascent()), Bitmap.Config.ARGB_8888);
        new Canvas(bitmapCreateBitmap).drawText(str, 0.0f, bitmapCreateBitmap.getHeight() - paint.descent(), paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap addGrayBackgroundAndBorder(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth() + 4;
        int height = bitmap.getHeight() + 4;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#E3E3E3"));
        float f = width;
        float f2 = height;
        canvas.drawRect(0.0f, 0.0f, f, f2, paint);
        canvas.drawBitmap(bitmap, 2.0f, 2.0f, (Paint) null);
        paint.setColor(Color.parseColor("#E3E3E3"));
        canvas.drawRect(0.0f, 0.0f, f, 2.0f, paint);
        canvas.drawRect(0.0f, height - 2, f, f2, paint);
        canvas.drawRect(0.0f, 0.0f, 2.0f, f2, paint);
        canvas.drawRect(width - 2, 0.0f, f, f2, paint);
        if (z) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap createSplitBitmap(Bitmap bitmap, int i) {
        Bitmap bitmapCreateBitmap;
        Bitmap bitmapCreateBitmap2;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        Matrix matrix2 = new Matrix();
        int i2 = width / 2;
        float f = i2;
        int i3 = height / 2;
        float f2 = i3;
        matrix.postScale(0.9f, 0.9f, f, f2);
        matrix2.postScale(0.9f, 0.9f, f, f2);
        if (height > width) {
            bitmapCreateBitmap2 = Bitmap.createBitmap(bitmap, 0, 0, i2, height);
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i2, 0, i2, height);
            matrix2.postRotate(i, 0.0f, height);
        } else {
            Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(bitmap, 0, 0, width, i3);
            Bitmap bitmapCreateBitmap4 = Bitmap.createBitmap(bitmap, 0, i3, width, i3);
            matrix2.postRotate(i, f, f2);
            bitmapCreateBitmap = bitmapCreateBitmap4;
            bitmapCreateBitmap2 = bitmapCreateBitmap3;
        }
        Bitmap bitmapAddGrayBackgroundAndBorder = addGrayBackgroundAndBorder(bitmapCreateBitmap2, true);
        Bitmap bitmapAddGrayBackgroundAndBorder2 = addGrayBackgroundAndBorder(bitmapCreateBitmap, true);
        Bitmap bitmapCreateBitmap5 = Bitmap.createBitmap(bitmapAddGrayBackgroundAndBorder, 0, 0, bitmapAddGrayBackgroundAndBorder.getWidth(), bitmapAddGrayBackgroundAndBorder.getHeight(), matrix, true);
        Bitmap bitmapCreateBitmap6 = Bitmap.createBitmap(bitmapAddGrayBackgroundAndBorder2, 0, 0, bitmapAddGrayBackgroundAndBorder2.getWidth(), bitmapAddGrayBackgroundAndBorder2.getHeight(), matrix2, true);
        Bitmap bitmapCreateBitmap7 = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap7);
        if (height > width) {
            canvas.drawBitmap(bitmapCreateBitmap5, 0.0f, 15.0f, (Paint) null);
            canvas.drawBitmap(bitmapCreateBitmap6, i2 - 20, 17.0f, (Paint) null);
        } else {
            canvas.drawBitmap(bitmapCreateBitmap5, 30.0f, 0.0f, (Paint) null);
            canvas.drawBitmap(bitmapCreateBitmap6, 10.0f, i3 - 30, (Paint) null);
        }
        bitmapAddGrayBackgroundAndBorder.recycle();
        bitmapAddGrayBackgroundAndBorder2.recycle();
        bitmapCreateBitmap5.recycle();
        bitmapCreateBitmap6.recycle();
        return bitmapCreateBitmap7;
    }

    public static Bitmap getColorBitmap(String str, int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(Color.parseColor(str));
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static Bitmap combineBitmaps(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap2 == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(Math.max(bitmap.getWidth(), bitmap2.getWidth()), Math.max(bitmap.getHeight(), bitmap2.getHeight()), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static Bitmap getBase64Bitmap(String str) {
        if (str.startsWith("data:image")) {
            str = str.substring(str.indexOf(",") + 1);
        }
        byte[] bArrDecode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    public static Bitmap getGrayBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < i; i2++) {
            int iRed = (int) ((((double) Color.red(iArr[i2])) * 0.299d) + (((double) Color.green(iArr[i2])) * 0.587d) + (((double) Color.blue(iArr[i2])) * 0.114d));
            iArr[i2] = Color.rgb(iRed, iRed, iRed);
        }
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    public static Bitmap getHitiPrintBitmap(String str) {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        if (bitmapDecodeFile == null) {
            LoggerUtil.m1182e("BitmapUtil", "getHitiPrintBitmap bitmap is null");
            return null;
        }
        if (bitmapDecodeFile.getWidth() < bitmapDecodeFile.getHeight()) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90.0f);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix, true);
            bitmapDecodeFile.recycle();
            bitmapDecodeFile = bitmapCreateBitmap;
        }
        float width = bitmapDecodeFile.getWidth() / bitmapDecodeFile.getHeight();
        int i = 1844;
        float f = 1844;
        int i2 = UiPosIndexEnum.PE_IC_MT_TIPS;
        float f2 = UiPosIndexEnum.PE_IC_MT_TIPS;
        if (width > f / f2) {
            i2 = (int) (f / width);
        } else {
            i = (int) (f2 * width);
        }
        Matrix matrix2 = new Matrix();
        matrix2.postScale(i / bitmapDecodeFile.getWidth(), i2 / bitmapDecodeFile.getHeight());
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix2, true);
        bitmapDecodeFile.recycle();
        return bitmapCreateBitmap2;
    }
}
