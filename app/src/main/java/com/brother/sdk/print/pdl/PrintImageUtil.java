package com.brother.sdk.print.pdl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.brother.sdk.common.OutOfMemoryException;
import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.Resolution;
import com.brother.sdk.common.device.VerticalAlignment;
import com.brother.sdk.common.device.printer.PaperOrientation;
import com.brother.sdk.common.device.printer.PrintCustomScaling;
import com.brother.sdk.common.device.printer.PrintMargin;
import com.brother.sdk.common.device.printer.PrintOrientation;
import com.brother.sdk.common.device.printer.PrintOrigin;
import com.brother.sdk.common.device.printer.PrintQuality;
import com.brother.sdk.common.device.printer.PrintScale;
import com.brother.sdk.jni.print.image.NativePrintImageUtil;
import com.brother.sdk.print.PrintParameters;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintImageUtil {
    public static final int HALF_ROTATE = 180;
    public static final int LEFT_ROTATE = 90;
    public static final int RIGHT_ROTATE = 270;
    public static final int ROUND_ROTATE = 360;
    public static final int mn_SLEEP_TIME_UNDER_OOM = 2000;

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) throws OutOfMemoryException {
        Bitmap bitmapCreateBitmap;
        if (i == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i);
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap == bitmapCreateBitmap) {
                return bitmap;
            }
            bitmap.recycle();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            try {
                bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                if (bitmap == bitmapCreateBitmap) {
                    return bitmap;
                }
                bitmap.recycle();
            } catch (OutOfMemoryError unused) {
                throw new OutOfMemoryException();
            }
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap createPrintableBitmap(Bitmap bitmap, int i, int i2, PrintImageParameters printImageParameters) throws OutOfMemoryException {
        float f;
        float f2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ConvertSetting2 convertSetting2 = new ConvertSetting2(width, height, i, i2, printImageParameters);
        Bitmap bitmapCreateBitmap = createBitmap(i, i2);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(-1);
        Matrix matrix = new Matrix();
        if (convertSetting2.rotateFlag) {
            matrix.setRotate(90.0f);
            matrix.postTranslate(height, 0.0f);
        }
        if (printImageParameters.scale == PrintScale.CustomScaling) {
            PrintCustomScaling printCustomScaling = printImageParameters.customScaling;
            if (printCustomScaling != null) {
                if (printCustomScaling.hAlignment == HorizontalAlignment.CENTER) {
                    f = ((float) (((double) i) - (((double) convertSetting2.mUseInputWidth) * printCustomScaling.scaleX))) / 2.0f;
                } else {
                    f = printCustomScaling.hAlignment == HorizontalAlignment.RIGHT ? (float) (((double) i) - (((double) convertSetting2.mUseInputWidth) * printCustomScaling.scaleX)) : 1.0f;
                }
                if (printCustomScaling.vAlignment == VerticalAlignment.CENTER) {
                    f2 = ((float) (((double) i2) - (((double) convertSetting2.mUseInputHeight) * printCustomScaling.scaleY))) / 2.0f;
                } else {
                    f2 = printCustomScaling.vAlignment == VerticalAlignment.BOTTOM ? (float) (((double) i2) - (((double) convertSetting2.mUseInputHeight) * printCustomScaling.scaleY)) : 0.0f;
                }
                Matrix matrix2 = new Matrix();
                matrix2.setScale((float) printCustomScaling.scaleX, (float) printCustomScaling.scaleY);
                if (f > 0.0f || f2 > 0.0f || f < 0.0f || f2 < 0.0f) {
                    matrix2.postTranslate(f, f2);
                }
                matrix.postConcat(matrix2);
            }
        } else if (printImageParameters.scale == PrintScale.NoScalingAtPrintableAreaCenter) {
            Matrix matrix3 = new Matrix();
            matrix3.setTranslate((i - convertSetting2.mUseInputWidth) / 2.0f, (i2 - convertSetting2.mUseInputHeight) / 2.0f);
            matrix.postConcat(matrix3);
        } else if (printImageParameters.scale != PrintScale.NoScaling) {
            RectF rectF = new RectF();
            RectF rectF2 = new RectF();
            rectF2.set(0.0f, 0.0f, i, i2);
            if (convertSetting2.rotateFlag) {
                rectF.set(convertSetting2.inputArea.top, convertSetting2.inputArea.left, convertSetting2.inputArea.bottom, convertSetting2.inputArea.right);
            } else {
                rectF.set(convertSetting2.inputArea.left, convertSetting2.inputArea.top, convertSetting2.inputArea.right, convertSetting2.inputArea.bottom);
            }
            Matrix matrix4 = new Matrix();
            if (printImageParameters.scale == PrintScale.FitToPrintableArea) {
                matrix4.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
            } else {
                matrix4.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            }
            matrix.postConcat(matrix4);
        }
        if (printImageParameters.origin.inchX > 0.0d || printImageParameters.origin.inchY > 0.0d) {
            int i3 = (int) (printImageParameters.origin.inchX * ((double) printImageParameters.resolution.width));
            int i4 = (int) (printImageParameters.origin.inchY * ((double) printImageParameters.resolution.height));
            Matrix matrix5 = new Matrix();
            matrix5.setTranslate(i3, i4);
            matrix.postConcat(matrix5);
            canvas.clipRect(i3, i4, i, i2);
        }
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, matrix, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, PrintImageParameters printImageParameters) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (!(printImageParameters.paperOrientation == PaperOrientation.Landscape && width < height)) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(90.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i, int i2, PrintParameters printParameters) throws OutOfMemoryException {
        ConvertSetting convertSetting = new ConvertSetting(bitmap.getWidth(), bitmap.getHeight(), i, i2, printParameters.orientation, printParameters.margin);
        RectF rectF = new RectF();
        RectF rectF2 = new RectF();
        rectF2.set(0.0f, 0.0f, i, i2);
        if (convertSetting.rotateFlag) {
            Bitmap bitmapRotateBitmap = rotateBitmap(bitmap, 90);
            if (bitmapRotateBitmap != bitmap) {
                bitmap.recycle();
                bitmap = bitmapRotateBitmap;
            }
            rectF.set(convertSetting.inputArea.top, convertSetting.inputArea.left, convertSetting.inputArea.bottom, convertSetting.inputArea.right);
        } else {
            rectF.set(convertSetting.inputArea.left, convertSetting.inputArea.top, convertSetting.inputArea.right, convertSetting.inputArea.bottom);
        }
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        Bitmap bitmapCreateBitmap = createBitmap(i, i2);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(-1);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        if (printParameters.quality == PrintQuality.Document) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.set(new float[]{1.04f, 0.0f, 0.0f, 0.0f, -0.02f, 0.0f, 1.04f, 0.0f, 0.0f, -0.02f, 0.0f, 0.0f, 1.04f, 0.0f, -0.02f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        }
        canvas.drawBitmap(bitmap, matrix, paint);
        return bitmapCreateBitmap;
    }

    public static int[] resizeImageFile(String str, String str2, int i, int i2, PrintParameters printParameters) throws OutOfMemoryException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int[] iArrCalculateSizeOfImageFile = NativePrintImageUtil.calculateSizeOfImageFile(str);
        ConvertSetting convertSetting = new ConvertSetting(iArrCalculateSizeOfImageFile[0], iArrCalculateSizeOfImageFile[1], i, i2, printParameters.orientation, printParameters.margin);
        if (convertSetting.rotateFlag) {
            int i8 = convertSetting.inputArea.top;
            i5 = i8;
            i6 = convertSetting.inputArea.left;
            i7 = convertSetting.inputArea.bottom - convertSetting.inputArea.top;
            i3 = convertSetting.inputArea.right - convertSetting.inputArea.left;
            i4 = 1;
        } else {
            int i9 = convertSetting.inputArea.left;
            int i10 = convertSetting.inputArea.top;
            int i11 = convertSetting.inputArea.right - convertSetting.inputArea.left;
            i3 = convertSetting.inputArea.bottom - convertSetting.inputArea.top;
            i4 = 0;
            i5 = i9;
            i6 = i10;
            i7 = i11;
        }
        return NativePrintImageUtil.createImageFileFilledWithImageRect(str, str2, i5, i6, i7, i3, i, i2, i4, !printParameters.printContent.isImage ? 1 : 0);
    }

    public static File createWhiteJpegFile(int i, int i2, File file) throws OutOfMemoryException {
        FileOutputStream fileOutputStream;
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            File fileCreateTempFile = null;
            try {
                fileCreateTempFile = File.createTempFile("temp", ".jpq", file);
                new Canvas(bitmapCreateBitmap).drawColor(-1);
                fileOutputStream = new FileOutputStream(fileCreateTempFile);
                try {
                } finally {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)) {
                throw new IOException();
            }
            fileOutputStream.flush();
            return fileCreateTempFile;
        } catch (OutOfMemoryError unused) {
            throw new OutOfMemoryException();
        }
    }

    public static class DecodedBitmap {
        public Bitmap bitmap;
        public double scale;

        public DecodedBitmap(Bitmap bitmap, double d) {
            this.bitmap = bitmap;
            this.scale = d;
        }

        public void updatePrintParametersWithDecodedInformation(PrintParameters printParameters) {
            if (this.scale > 1.0d) {
                if (printParameters.scale == PrintScale.NoScaling) {
                    double d = this.scale;
                    printParameters.customScaling = new PrintCustomScaling(d, d);
                    printParameters.scale = PrintScale.CustomScaling;
                } else if (printParameters.scale == PrintScale.NoScalingAtPrintableAreaCenter) {
                    double d2 = this.scale;
                    printParameters.customScaling = new PrintCustomScaling(d2, d2, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    printParameters.scale = PrintScale.CustomScaling;
                } else {
                    if (printParameters.scale != PrintScale.CustomScaling || printParameters.customScaling == null) {
                        return;
                    }
                    printParameters.customScaling.scaleX *= this.scale;
                    printParameters.customScaling.scaleY *= this.scale;
                }
            }
        }

        public boolean needRescalingToAdjust() {
            return this.scale > 1.0d;
        }
    }

    public static Bitmap decodeFileToBitmap(String str, PrintParameters printParameters) throws OutOfMemoryException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i = options.outWidth * options.outHeight;
        if (printParameters.quality != PrintQuality.Draft) {
            options.inSampleSize = 1;
        } else {
            long j = i;
            if (j < 3200000) {
                options.inSampleSize = 1;
            } else if (j < 12800000) {
                options.inSampleSize = 2;
            } else {
                options.inSampleSize = 3;
            }
        }
        options.inJustDecodeBounds = false;
        if (printParameters.quality != PrintQuality.Draft) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        } else if (printParameters.printContent.isImage) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            System.gc();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                return BitmapFactory.decodeFile(str, options);
            } catch (OutOfMemoryError unused2) {
                throw new OutOfMemoryException();
            }
        }
    }

    public static DecodedBitmap decodeFileToDecodedBitmap(String str, PrintParameters printParameters) throws OutOfMemoryException {
        Bitmap bitmapDecodeFile;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = 1;
        double d = options.inSampleSize;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        try {
            bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            System.gc();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
            } catch (OutOfMemoryError unused2) {
                throw new OutOfMemoryException();
            }
        }
        return new DecodedBitmap(bitmapDecodeFile, d);
    }

    public static Bitmap createBitmap(int i, int i2) throws OutOfMemoryException {
        try {
            return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            try {
                return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError e3) {
                e3.printStackTrace();
                System.gc();
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
                try {
                    return Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
                } catch (OutOfMemoryError unused) {
                    throw new OutOfMemoryException();
                }
            }
        }
    }

    static class ConvertSetting {
        public Rect inputArea = new Rect();
        public boolean mLongSideFittingFlag;
        public int mUseInputHeight;
        public int mUseInputWidth;
        public boolean rotateFlag;
        public float scale;

        public ConvertSetting(int i, int i2, int i3, int i4, PrintOrientation printOrientation, PrintMargin printMargin) {
            this.mLongSideFittingFlag = false;
            this.rotateFlag = false;
            int i5 = C07541.f527xb4e52d13[printOrientation.ordinal()];
            if (i5 != 1) {
                if (i5 == 2) {
                    this.rotateFlag = true;
                }
            } else if (i4 >= i3) {
                this.rotateFlag = i2 < i;
            } else {
                this.rotateFlag = i2 > i;
            }
            if (this.rotateFlag) {
                this.mUseInputWidth = i2;
                this.mUseInputHeight = i;
            } else {
                this.mUseInputWidth = i;
                this.mUseInputHeight = i2;
            }
            float f = i4;
            float f2 = i3;
            if (this.mUseInputHeight / f >= this.mUseInputWidth / f2) {
                if (printMargin == PrintMargin.Borderless) {
                    this.scale = f2 / this.mUseInputWidth;
                    this.mLongSideFittingFlag = false;
                } else {
                    this.scale = f / this.mUseInputHeight;
                    this.mLongSideFittingFlag = true;
                }
            } else if (printMargin == PrintMargin.Borderless) {
                this.scale = f / this.mUseInputHeight;
                this.mLongSideFittingFlag = true;
            } else {
                this.scale = f2 / this.mUseInputWidth;
                this.mLongSideFittingFlag = false;
            }
            if (printMargin == PrintMargin.Borderless) {
                if (this.mLongSideFittingFlag) {
                    int i6 = (int) (((double) ((this.mUseInputWidth - (f2 / this.scale)) / 2.0f)) + 0.5d);
                    if (this.rotateFlag) {
                        this.inputArea.left = 0;
                        this.inputArea.right = i;
                        this.inputArea.top = i6;
                        this.inputArea.bottom = i2 - i6;
                        return;
                    }
                    this.inputArea.left = i6;
                    this.inputArea.right = i - i6;
                    this.inputArea.top = 0;
                    this.inputArea.bottom = i2;
                    return;
                }
                int i7 = (int) (((double) ((this.mUseInputHeight - (f / this.scale)) / 2.0f)) + 0.5d);
                if (this.rotateFlag) {
                    this.inputArea.left = i7;
                    this.inputArea.right = i - i7;
                    this.inputArea.top = 0;
                    this.inputArea.bottom = i2;
                    return;
                }
                this.inputArea.left = 0;
                this.inputArea.right = i;
                this.inputArea.top = i7;
                this.inputArea.bottom = i2 - i7;
                return;
            }
            this.inputArea.left = 0;
            this.inputArea.right = i;
            this.inputArea.top = 0;
            this.inputArea.bottom = i2;
        }
    }

    static class C07541 {

        static final int[] f527xb4e52d13;

        static {
            int[] iArr = new int[PrintOrientation.values().length];
            f527xb4e52d13 = iArr;
            try {
                iArr[PrintOrientation.AutoRotation.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f527xb4e52d13[PrintOrientation.Landscape.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f527xb4e52d13[PrintOrientation.Portrait.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static class ConvertSetting2 {
        public Rect inputArea = new Rect();
        public boolean mLongSideFittingFlag;
        public int mUseInputHeight;
        public int mUseInputWidth;
        public boolean rotateFlag;
        public float scale;

        public ConvertSetting2(int i, int i2, int i3, int i4, PrintImageParameters printImageParameters) {
            this.mLongSideFittingFlag = false;
            this.rotateFlag = false;
            if (i4 >= i3) {
                this.rotateFlag = i2 < i;
            } else {
                this.rotateFlag = i2 > i;
            }
            if (this.rotateFlag) {
                this.mUseInputWidth = i2;
                this.mUseInputHeight = i;
            } else {
                this.mUseInputWidth = i;
                this.mUseInputHeight = i2;
            }
            float f = i4;
            float f2 = i3;
            if (this.mUseInputHeight / f >= this.mUseInputWidth / f2) {
                if (printImageParameters.scale == PrintScale.UniformFillPrintableArea) {
                    this.scale = f2 / this.mUseInputWidth;
                    this.mLongSideFittingFlag = false;
                } else {
                    this.scale = f / this.mUseInputHeight;
                    this.mLongSideFittingFlag = true;
                }
            } else if (printImageParameters.scale == PrintScale.UniformFillPrintableArea) {
                this.scale = f / this.mUseInputHeight;
                this.mLongSideFittingFlag = true;
            } else {
                this.scale = f2 / this.mUseInputWidth;
                this.mLongSideFittingFlag = false;
            }
            if (printImageParameters.scale == PrintScale.UniformFillPrintableArea) {
                if (this.mLongSideFittingFlag) {
                    int i5 = (int) (((double) ((this.mUseInputWidth - (f2 / this.scale)) / 2.0f)) + 0.5d);
                    if (this.rotateFlag) {
                        this.inputArea.left = 0;
                        this.inputArea.right = i;
                        this.inputArea.top = i5;
                        this.inputArea.bottom = i2 - i5;
                        return;
                    }
                    this.inputArea.left = i5;
                    this.inputArea.right = i - i5;
                    this.inputArea.top = 0;
                    this.inputArea.bottom = i2;
                    return;
                }
                int i6 = (int) (((double) ((this.mUseInputHeight - (f / this.scale)) / 2.0f)) + 0.5d);
                if (this.rotateFlag) {
                    this.inputArea.left = i6;
                    this.inputArea.right = i - i6;
                    this.inputArea.top = 0;
                    this.inputArea.bottom = i2;
                    return;
                }
                this.inputArea.left = 0;
                this.inputArea.right = i;
                this.inputArea.top = i6;
                this.inputArea.bottom = i2 - i6;
                return;
            }
            this.inputArea.left = 0;
            this.inputArea.right = i;
            this.inputArea.top = 0;
            this.inputArea.bottom = i2;
        }
    }

    public static class PrintImageParameters {
        public PrintCustomScaling customScaling;
        public PrintOrientation orientation;
        public PrintOrigin origin;
        public PaperOrientation paperOrientation;
        public PrintQuality quality;
        public Resolution resolution;
        public PrintScale scale;

        public PrintImageParameters(PrintParameters printParameters) {
            this.scale = printParameters.scale;
            this.customScaling = printParameters.customScaling;
            this.origin = printParameters.origin;
            this.orientation = printParameters.orientation;
            this.quality = printParameters.quality;
            this.resolution = printParameters.resolution;
            this.paperOrientation = printParameters.paperOrientation;
        }
    }
}
