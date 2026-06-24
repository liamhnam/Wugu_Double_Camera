package com.brother.sdk.scan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.brother.sdk.common.socket.scan.ScanImage;
import com.brother.sdk.jni.scan.image.BitmapUtil;
import com.brother.sdk.jni.scan.image.NativeScanImageUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class CornerScanImageAdjuster {
    public static final int MN_SLEEP_CNT = 5;
    public static final int M_N_TIME_SLEEP = 10;

    CornerScanImageAdjuster() {
    }

    BlankImageCheckResult adjustImageWithBlankAndDeskewCheck(String str, ScanImage.ImageCornerInfo imageCornerInfo, String str2, boolean z, boolean z2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Size imageSize = getImageSize(str);
        int i8 = imageCornerInfo.LeftTopX;
        int i9 = imageCornerInfo.LeftTopY;
        int i10 = imageCornerInfo.LeftBottomX;
        int i11 = imageCornerInfo.LeftBottomY;
        int i12 = imageCornerInfo.RightTopX;
        int i13 = imageCornerInfo.RightTopY;
        int i14 = imageCornerInfo.RightBottomX;
        int i15 = imageCornerInfo.RightBottomY;
        if (i8 == 0 && i9 == 0 && i10 == 0 && i11 == 0 && i12 == 0 && i13 == 0 && i14 == 0 && i15 == 0) {
            int i16 = imageSize.height;
            int i17 = imageSize.width;
            i14 = imageSize.width;
            i = imageSize.height;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i7 = 0;
            i5 = i16;
            i6 = i17;
        } else {
            i = i15;
            i2 = i8;
            i3 = i9;
            i4 = i10;
            i5 = i11;
            i6 = i12;
            i7 = i13;
        }
        return BlankImageCheckResult.fromValue(NativeScanImageUtil.adjustImageWithBlankAndDeskewCheck(str, str2, imageSize.width, imageSize.height, i2, i3, i4, i5, i6, i7, i14, i, z, z2));
    }

    boolean convertJpegFileToBmpFile(String str, String str2) throws Throwable {
        Size imageSize = getImageSize(str2);
        boolean z = NativeScanImageUtil.convertJpegToBmp(str, str2, imageSize.width, imageSize.height) == 1;
        Size imageSize2 = getImageSize(str);
        if (z && imageSize2.width > 0 && imageSize2.height > 0) {
            return true;
        }
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str2);
        boolean zSaveAsBitmapFile = BitmapUtil.saveAsBitmapFile(bitmapDecodeFile, str);
        bitmapDecodeFile.recycle();
        return zSaveAsBitmapFile;
    }

    int convertBmpFileToJpegFile(String str, String str2, boolean z) {
        Bitmap bitmapDecodeFile;
        Bitmap bitmapCreateBitmap;
        try {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                try {
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmapDecodeFile = BitmapFactory.decodeFile(str2, options);
                } catch (OutOfMemoryError unused) {
                    System.gc();
                    Thread.sleep(50L);
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    bitmapDecodeFile = BitmapFactory.decodeFile(str2, options);
                }
                if (bitmapDecodeFile == null) {
                    return -3;
                }
                if (z) {
                    System.gc();
                    Thread.sleep(10L);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(180.0f);
                    int height = bitmapDecodeFile.getHeight();
                    int width = bitmapDecodeFile.getWidth();
                    try {
                        bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, width, height, matrix, true);
                    } catch (OutOfMemoryError unused2) {
                        System.gc();
                        Thread.sleep(50L);
                        try {
                            bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, width, height, matrix, true);
                        } catch (OutOfMemoryError unused3) {
                            bitmapCreateBitmap = null;
                        }
                    }
                    if (bitmapDecodeFile != bitmapCreateBitmap) {
                        bitmapDecodeFile.recycle();
                    }
                    if (bitmapCreateBitmap == null) {
                        return -4;
                    }
                    bitmapDecodeFile = bitmapCreateBitmap;
                }
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                try {
                    bitmapDecodeFile.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    bitmapDecodeFile.recycle();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return 0;
                } catch (Throwable th) {
                    fileOutputStream.close();
                    throw th;
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                return -2;
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return -1;
        } catch (IOException e3) {
            e3.printStackTrace();
            return 0;
        } catch (InterruptedException e4) {
            e4.printStackTrace();
            return 0;
        }
    }

    private Size getImageSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Size(options.outWidth, options.outHeight);
    }

    private static class Size {
        int height;
        int width;

        Size(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    public enum BlankImageCheckResult {
        BlankImage(0),
        NotBlankImage(1);

        private int mState;

        BlankImageCheckResult(int i) {
            this.mState = i;
        }

        static BlankImageCheckResult fromValue(int i) {
            for (BlankImageCheckResult blankImageCheckResult : values()) {
                if (blankImageCheckResult.mState == i) {
                    return blankImageCheckResult;
                }
            }
            return NotBlankImage;
        }
    }
}
