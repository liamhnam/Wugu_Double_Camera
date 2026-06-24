package com.hiti.usb.bitmapmanager;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Debug;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.hiti.usb.utility.FileUtility;
import com.p020hp.jipp.model.TimeoutPredicate;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

public class BitmapMonitor {
    public static final float LEGAL_PHOTO_RATIO = 5.0f;

    public static boolean BitmapExist(Context context, String str) {
        boolean zFileExist = FileUtility.FileExist(str);
        if (!zFileExist) {
            return zFileExist;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            options.inJustDecodeBounds = true;
            CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(Uri.parse("file://" + str))), null, options, false);
            int i = options.outWidth;
            int i2 = options.outHeight;
            if (i <= 0 || i2 <= 0) {
                return false;
            }
            return zFileExist;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return zFileExist;
        }
    }

    private static int CalculateInSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i > 0 && i2 > 0) {
            while (true) {
                if (i2 > 0 && i / i5 <= i3 && i > 0 && i2 / i5 <= i4) {
                    break;
                }
                i5 *= 2;
            }
        }
        return i5;
    }

    public static BitmapMonitorResult ConvertToMutable(Bitmap bitmap) {
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        if (bitmap == null) {
            bitmapMonitorResult.SetResult(97);
            return bitmapMonitorResult;
        }
        try {
        } catch (FileNotFoundException e) {
            bitmapMonitorResult.SetResult(97);
            e.printStackTrace();
        } catch (IOException e2) {
            bitmapMonitorResult.SetResult(93);
            e2.printStackTrace();
        } catch (RuntimeException e3) {
            bitmapMonitorResult.SetResult(96);
            e3.printStackTrace();
        }
        if (!FileUtility.SDCardState()) {
            bitmapMonitorResult.SetResult(95);
            return bitmapMonitorResult;
        }
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temp.tmp");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0L, bitmap.getRowBytes() * height);
        bitmap.copyPixelsToBuffer(map);
        bitmap.recycle();
        System.gc();
        bitmapMonitorResult = CreateBitmap(width, height, config);
        if (bitmapMonitorResult.IsSuccess()) {
            Bitmap bitmapGetBitmap = bitmapMonitorResult.GetBitmap();
            map.position(0);
            bitmapGetBitmap.copyPixelsFromBuffer(map);
        }
        channel.close();
        randomAccessFile.close();
        file.delete();
        return bitmapMonitorResult;
    }

    public static BitmapMonitorResult Copy(Bitmap bitmap, Bitmap.Config config, boolean z) {
        Bitmap bitmapCopy;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        try {
            bitmapCopy = bitmap.copy(config, z);
        } catch (Exception e) {
            bitmapMonitorResult.SetResult(100);
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            bitmapMonitorResult.SetResult(99);
            e2.printStackTrace();
        }
        if (bitmapCopy == null) {
            bitmapMonitorResult.SetResult(96);
            return bitmapMonitorResult;
        }
        if (bitmapCopy.getWidth() <= 0 && bitmapCopy.getHeight() <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        bitmapMonitorResult.SetBitmap(bitmapCopy);
        bitmapMonitorResult.SetResult(0);
        return bitmapMonitorResult;
    }

    public static void CopyPixelsToFile(String str, Bitmap bitmap) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str);
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(byteBufferAllocate);
            FileOutputStream fileOutputStream = new FileOutputStream(file2.getPath());
            fileOutputStream.write(byteBufferAllocate.array());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CopyPixelsToFile(String str, byte[] bArr) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str).getPath());
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BitmapMonitorResult CreateBitmap(int i, int i2, Bitmap.Config config) {
        Bitmap bitmapCreateBitmap;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(i, i2, config);
        } catch (IllegalArgumentException e) {
            bitmapMonitorResult.SetResult(98);
            e.printStackTrace();
        } catch (Exception e2) {
            bitmapMonitorResult.SetResult(100);
            e2.printStackTrace();
        } catch (OutOfMemoryError e3) {
            bitmapMonitorResult.SetResult(99);
            e3.printStackTrace();
        }
        if (bitmapCreateBitmap == null) {
            bitmapMonitorResult.SetResult(96);
            return bitmapMonitorResult;
        }
        if (bitmapCreateBitmap.getWidth() <= 0 && bitmapCreateBitmap.getHeight() <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        bitmapMonitorResult.SetResult(0);
        bitmapMonitorResult.SetBitmap(bitmapCreateBitmap);
        return bitmapMonitorResult;
    }

    public static BitmapMonitorResult CreateBitmap(Bitmap bitmap, int i, int i2, int i3, int i4, Matrix matrix, boolean z) {
        Bitmap bitmapCreateBitmap;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i, i2, i3, i4, matrix, z);
        } catch (Exception e) {
            bitmapMonitorResult.SetResult(100);
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            bitmapMonitorResult.SetResult(99);
            e2.printStackTrace();
        }
        if (bitmapCreateBitmap == null) {
            bitmapMonitorResult.SetResult(96);
            return bitmapMonitorResult;
        }
        if (bitmapCreateBitmap.getWidth() <= 0 && bitmapCreateBitmap.getHeight() <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        bitmapMonitorResult.SetResult(0);
        bitmapMonitorResult.SetBitmap(bitmapCreateBitmap);
        return bitmapMonitorResult;
    }

    public static BitmapMonitorResult CreateBitmap(InputStream inputStream, Rect rect, BitmapFactory.Options options, boolean z) {
        Bitmap bitmapDecodeStream;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        if (inputStream == null) {
            bitmapMonitorResult.SetResult(97);
            return bitmapMonitorResult;
        }
        try {
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStream, rect, options);
        } catch (Exception e) {
            bitmapMonitorResult.SetResult(100);
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            bitmapMonitorResult.SetResult(99);
            e2.printStackTrace();
        }
        if (options.inJustDecodeBounds) {
            bitmapMonitorResult.SetResult(0);
            return bitmapMonitorResult;
        }
        if (bitmapDecodeStream == null) {
            bitmapMonitorResult.SetResult(96);
            return bitmapMonitorResult;
        }
        if (bitmapDecodeStream.getWidth() <= 0 && bitmapDecodeStream.getHeight() <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        if (z) {
            return ConvertToMutable(bitmapDecodeStream);
        }
        bitmapMonitorResult.SetResult(0);
        bitmapMonitorResult.SetBitmap(bitmapDecodeStream);
        return bitmapMonitorResult;
    }

    public static BitmapMonitorResult CreateBitmap(InputStream inputStream, boolean z) {
        Bitmap bitmapDecodeStream;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        if (inputStream == null) {
            bitmapMonitorResult.SetResult(97);
            return bitmapMonitorResult;
        }
        try {
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            bitmapMonitorResult.SetResult(100);
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            bitmapMonitorResult.SetResult(99);
            e2.printStackTrace();
        }
        if (bitmapDecodeStream == null) {
            bitmapMonitorResult.SetResult(96);
            return bitmapMonitorResult;
        }
        if (bitmapDecodeStream.getWidth() <= 0 && bitmapDecodeStream.getHeight() <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        if (z) {
            return ConvertToMutable(bitmapDecodeStream);
        }
        bitmapMonitorResult.SetResult(0);
        bitmapMonitorResult.SetBitmap(bitmapDecodeStream);
        return bitmapMonitorResult;
    }

    public static BitmapMonitorResult CreateBitmap(String str, boolean z) {
        Bitmap bitmapDecodeFile;
        int i;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        if (!FileUtility.SDCardState()) {
            i = 95;
        } else {
            if (FileUtility.FileExist(str)) {
                try {
                    bitmapDecodeFile = BitmapFactory.decodeFile(str);
                } catch (Exception e) {
                    bitmapMonitorResult.SetResult(100);
                    e.printStackTrace();
                } catch (OutOfMemoryError e2) {
                    bitmapMonitorResult.SetResult(99);
                    e2.printStackTrace();
                }
                if (bitmapDecodeFile == null) {
                    bitmapMonitorResult.SetResult(96);
                    return bitmapMonitorResult;
                }
                if (bitmapDecodeFile.getWidth() <= 0 && bitmapDecodeFile.getHeight() <= 0) {
                    bitmapMonitorResult.SetResult(98);
                    return bitmapMonitorResult;
                }
                if (z) {
                    return ConvertToMutable(bitmapDecodeFile);
                }
                bitmapMonitorResult.SetResult(0);
                bitmapMonitorResult.SetBitmap(bitmapDecodeFile);
                return bitmapMonitorResult;
            }
            i = 97;
        }
        bitmapMonitorResult.SetResult(i);
        return bitmapMonitorResult;
    }

    public static BitmapMonitorResult CreateCroppedBitmap(Context context, Uri uri, int i, int i2) {
        double d;
        double d2;
        Bitmap bitmapGetBitmap;
        double d3;
        double d4;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i3 = (int) (((i * 80.0f) / 100.0f) * 2.0f);
        int i4 = (int) (((i2 * 80.0f) / 100.0f) * 2.0f);
        if (!FileUtility.SDCardState()) {
            bitmapMonitorResult.SetResult(95);
            return bitmapMonitorResult;
        }
        if (i <= 0 && i2 <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        try {
            options.inJustDecodeBounds = true;
            bitmapMonitorResult = CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(uri)), null, options, false);
            if (!bitmapMonitorResult.IsSuccess()) {
                return bitmapMonitorResult;
            }
            bitmapMonitorResult.GetBitmap();
            int i5 = options.outWidth;
            int i6 = options.outHeight;
            int iResolveBitmapOrientation = ResolveBitmapOrientation(uri);
            if (IsHappenExifInterfaceOrientationVHChange(iResolveBitmapOrientation)) {
                i6 = i5;
                i5 = i6;
            }
            TrySystemGC();
            int iCalculateInSampleSize = CalculateInSampleSize(i5, i6, i3, i4);
            options.inJustDecodeBounds = false;
            options.inSampleSize = iCalculateInSampleSize;
            try {
                bitmapMonitorResult = CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(uri)), null, options, true);
                if (!bitmapMonitorResult.IsSuccess()) {
                    return bitmapMonitorResult;
                }
                if (iResolveBitmapOrientation != -1) {
                    BitmapMonitorResult bitmapMonitorResultGetExifOrientationBitmap = GetExifOrientationBitmap(bitmapMonitorResult.GetBitmap(), iResolveBitmapOrientation);
                    if (bitmapMonitorResultGetExifOrientationBitmap.IsSuccess() && bitmapMonitorResult.GetBitmap() != bitmapMonitorResultGetExifOrientationBitmap.GetBitmap()) {
                        bitmapMonitorResult.GetBitmap().recycle();
                        bitmapMonitorResult = bitmapMonitorResultGetExifOrientationBitmap;
                    }
                }
                Bitmap bitmapGetBitmap2 = bitmapMonitorResult.GetBitmap();
                int width = bitmapMonitorResult.GetBitmap().getWidth();
                int height = bitmapMonitorResult.GetBitmap().getHeight();
                if (width == i && height == i2) {
                    return bitmapMonitorResult;
                }
                double d5 = width;
                double d6 = i;
                double d7 = height;
                if (d5 > d7) {
                    d2 = i2;
                    d = d7;
                } else {
                    d = d5;
                    d2 = d6;
                }
                double d8 = d2 / d;
                int i7 = (int) (d5 * d8);
                int i8 = (int) (d7 * d8);
                Log.i("Cropped iWidth", String.valueOf(i7));
                Log.i("Cropped iHeight", String.valueOf(i8));
                Log.i("dScale", String.valueOf(d8));
                BitmapMonitorResult bitmapMonitorResultCreateScaledBitmap = CreateScaledBitmap(bitmapGetBitmap2, i7, i8, true);
                bitmapMonitorResultCreateScaledBitmap.SetPixelWarning(d8, iCalculateInSampleSize);
                if (!bitmapMonitorResultCreateScaledBitmap.IsSuccess() || bitmapGetBitmap2 == (bitmapGetBitmap = bitmapMonitorResultCreateScaledBitmap.GetBitmap())) {
                    return bitmapMonitorResultCreateScaledBitmap;
                }
                if (!bitmapGetBitmap2.isRecycled()) {
                    bitmapGetBitmap2.recycle();
                }
                double d9 = i7;
                if (Math.abs(d9 - d6) <= 1.0d) {
                    d4 = i8;
                    d3 = i2;
                } else {
                    d3 = d6;
                    d4 = d9;
                }
                if (d4 < d3) {
                    d8 = d3 / d4;
                    bitmapMonitorResultCreateScaledBitmap = CreateScaledBitmap(bitmapGetBitmap, (int) (d9 * d8), (int) (((double) i8) * d8), true);
                }
                bitmapMonitorResultCreateScaledBitmap.SetPixelWarning(d8, iCalculateInSampleSize);
                return bitmapMonitorResultCreateScaledBitmap;
            } catch (FileNotFoundException e) {
                bitmapMonitorResult.SetResult(97);
                e.printStackTrace();
                return bitmapMonitorResult;
            }
        } catch (FileNotFoundException e2) {
            bitmapMonitorResult.SetResult(97);
            e2.printStackTrace();
            return bitmapMonitorResult;
        }
    }

    public static BitmapMonitorResult CreateCroppedBitmapNew(Context context, Uri uri, int i, int i2) {
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i3 = (int) (((i * 80.0f) / 100.0f) * 2.0f);
        int i4 = (int) (((i2 * 80.0f) / 100.0f) * 2.0f);
        if (!FileUtility.SDCardState()) {
            bitmapMonitorResult.SetResult(95);
            return bitmapMonitorResult;
        }
        if (i <= 0 && i2 <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        try {
            options.inJustDecodeBounds = true;
            bitmapMonitorResult = CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(uri)), null, options, false);
            if (!bitmapMonitorResult.IsSuccess()) {
                return bitmapMonitorResult;
            }
            bitmapMonitorResult.GetBitmap();
            int i5 = options.outWidth;
            int i6 = options.outHeight;
            int iResolveBitmapOrientation = ResolveBitmapOrientation(uri);
            if (IsHappenExifInterfaceOrientationVHChange(iResolveBitmapOrientation)) {
                i6 = i5;
                i5 = i6;
            }
            TrySystemGC();
            int iCalculateInSampleSize = CalculateInSampleSize(i5, i6, i3, i4);
            options.inJustDecodeBounds = false;
            options.inSampleSize = iCalculateInSampleSize;
            try {
                bitmapMonitorResult = CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(uri)), null, options, true);
                if (!bitmapMonitorResult.IsSuccess()) {
                    return bitmapMonitorResult;
                }
                if (iResolveBitmapOrientation != -1) {
                    BitmapMonitorResult bitmapMonitorResultGetExifOrientationBitmap = GetExifOrientationBitmap(bitmapMonitorResult.GetBitmap(), iResolveBitmapOrientation);
                    if (bitmapMonitorResultGetExifOrientationBitmap.IsSuccess() && bitmapMonitorResult.GetBitmap() != bitmapMonitorResultGetExifOrientationBitmap.GetBitmap()) {
                        bitmapMonitorResult.GetBitmap().recycle();
                        bitmapMonitorResult = bitmapMonitorResultGetExifOrientationBitmap;
                    }
                }
                Bitmap bitmapGetBitmap = bitmapMonitorResult.GetBitmap();
                int width = bitmapMonitorResult.GetBitmap().getWidth();
                int height = bitmapMonitorResult.GetBitmap().getHeight();
                if (width == i && height == i2) {
                    return bitmapMonitorResult;
                }
                double d = width;
                double d2 = ((double) i) / d;
                double d3 = height;
                double d4 = ((double) i2) / d3;
                if (d4 > d2) {
                    d2 = d4;
                }
                BitmapMonitorResult bitmapMonitorResultCreateScaledBitmap = CreateScaledBitmap(bitmapGetBitmap, (int) (d * d2), (int) (d3 * d2), true);
                bitmapMonitorResultCreateScaledBitmap.SetPixelWarning(d2, iCalculateInSampleSize);
                if (!bitmapMonitorResultCreateScaledBitmap.IsSuccess() || bitmapGetBitmap == bitmapMonitorResultCreateScaledBitmap.GetBitmap()) {
                    return bitmapMonitorResultCreateScaledBitmap;
                }
                if (!bitmapGetBitmap.isRecycled()) {
                    bitmapGetBitmap.recycle();
                }
                bitmapMonitorResultCreateScaledBitmap.SetPixelWarning(d2, iCalculateInSampleSize);
                return bitmapMonitorResultCreateScaledBitmap;
            } catch (FileNotFoundException e) {
                bitmapMonitorResult.SetResult(97);
                e.printStackTrace();
                return bitmapMonitorResult;
            }
        } catch (FileNotFoundException e2) {
            bitmapMonitorResult.SetResult(97);
            e2.printStackTrace();
            return bitmapMonitorResult;
        }
    }

    public static BitmapMonitorResult CreateScaledBitmap(Bitmap bitmap, int i, int i2, boolean z) {
        Bitmap bitmapCreateScaledBitmap;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        if (bitmap == null) {
            bitmapMonitorResult.SetResult(97);
            return bitmapMonitorResult;
        }
        if (i <= 0 && i2 <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        try {
            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, z);
        } catch (Exception e) {
            bitmapMonitorResult.SetResult(100);
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            bitmapMonitorResult.SetResult(99);
            e2.printStackTrace();
        }
        if (bitmapCreateScaledBitmap == null) {
            bitmapMonitorResult.SetResult(96);
            return bitmapMonitorResult;
        }
        if (bitmapCreateScaledBitmap.getWidth() <= 0 && bitmapCreateScaledBitmap.getHeight() <= 0) {
            bitmapMonitorResult.SetResult(98);
            return bitmapMonitorResult;
        }
        bitmapMonitorResult.SetResult(0);
        bitmapMonitorResult.SetBitmap(bitmapCreateScaledBitmap);
        return bitmapMonitorResult;
    }

    public static BitmapMonitorResult DecodeImage(String str) {
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        if (str != null && !str.equals("")) {
            try {
                byte[] bArrDecode = Base64.decode(str, 0);
                Bitmap bitmapDecodeByteArray = bArrDecode != null ? BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length) : null;
                if (bitmapDecodeByteArray == null) {
                    bitmapMonitorResult.SetResult(96);
                } else {
                    bitmapMonitorResult.SetResult(0);
                }
                bitmapMonitorResult.SetBitmap(bitmapDecodeByteArray);
            } catch (IllegalArgumentException e) {
                bitmapMonitorResult.SetResult(96);
                e.printStackTrace();
            } catch (OutOfMemoryError e2) {
                bitmapMonitorResult.SetResult(99);
                e2.printStackTrace();
            }
        }
        return bitmapMonitorResult;
    }

    public static BitmapMonitorResult GetBitmapFromFile(Context context, String str, boolean z) {
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        if (!FileUtility.SDCardState()) {
            bitmapMonitorResult.SetResult(95);
            return bitmapMonitorResult;
        }
        if (!FileUtility.FileExist(str)) {
            bitmapMonitorResult.SetResult(97);
            return bitmapMonitorResult;
        }
        if (!str.contains("file://")) {
            str = "file://" + str;
        }
        try {
            return CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(Uri.parse(str))), null, options, z);
        } catch (FileNotFoundException e) {
            bitmapMonitorResult.SetResult(97);
            e.printStackTrace();
            return bitmapMonitorResult;
        }
    }

    public static BitmapMonitorResult GetExifOrientationBitmap(Bitmap bitmap, int i) {
        int i2;
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        if (i == 3) {
            i2 = 180;
        } else if (i == 6) {
            i2 = 90;
        } else {
            if (i != 8) {
                bitmapMonitorResult.SetBitmap(bitmap);
                bitmapMonitorResult.SetResult(0);
                return bitmapMonitorResult;
            }
            i2 = 270;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(i2);
        return CreateBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Map GetPhotoTwoSide(Context context, Uri uri) {
        int i;
        int i2;
        BitmapMonitorResult bitmapMonitorResultCreateBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int width = 0;
        try {
            CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(uri)), null, options, false);
            i2 = options.outWidth;
            try {
                i = options.outHeight;
            } catch (FileNotFoundException e) {
                e = e;
                i = 0;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            i = 0;
        }
        if (i2 == -1 && i == -1) {
            try {
                bitmapMonitorResultCreateBitmap = CreateBitmap((InputStream) new BufferedInputStream(context.getContentResolver().openInputStream(uri)), false);
            } catch (FileNotFoundException e3) {
                e = e3;
                width = i2;
                e.printStackTrace();
                i2 = width;
                if (i2 > 0) {
                }
                return null;
            }
            if (bitmapMonitorResultCreateBitmap.IsSuccess()) {
                width = bitmapMonitorResultCreateBitmap.GetBitmap().getWidth();
                try {
                    int height = bitmapMonitorResultCreateBitmap.GetBitmap().getHeight();
                    try {
                        bitmapMonitorResultCreateBitmap.GetBitmap().recycle();
                        i = height;
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        i = height;
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                }
                i2 = width;
            }
        }
        if (i2 > 0 || i <= 0) {
            return null;
        }
        HashMap map = new HashMap();
        map.put("Width", Integer.valueOf(i2));
        map.put("Height", Integer.valueOf(i));
        return map;
    }

    public static boolean IsHappenExifInterfaceOrientationVHChange(int i) {
        if (i == -1) {
            return false;
        }
        return i == 6 || i == 8;
    }

    public static BitmapMonitorResult IsLegalRatio(Context context, Uri uri) {
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (!new File(uri.getPath()).exists()) {
            bitmapMonitorResult.SetResult(97);
            return bitmapMonitorResult;
        }
        try {
            BitmapFactory.decodeStream(new BufferedInputStream(context.getContentResolver().openInputStream(uri)), null, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            if (i <= 0 || i2 <= 0) {
                bitmapMonitorResult.SetResult(96);
                return bitmapMonitorResult;
            }
            if (i2 > i && i2 / i > 5.0f) {
                bitmapMonitorResult.SetResult(94);
                return bitmapMonitorResult;
            }
            if (i <= i2 || i / i2 <= 5.0f) {
                bitmapMonitorResult.SetResult(0);
                return bitmapMonitorResult;
            }
            bitmapMonitorResult.SetResult(94);
            return bitmapMonitorResult;
        } catch (FileNotFoundException e) {
            bitmapMonitorResult.SetResult(97);
            e.printStackTrace();
            return bitmapMonitorResult;
        }
    }

    public static boolean IsPhotoLowQuality(Context context, Uri uri, int i, int i2) {
        BitmapMonitorResult bitmapMonitorResult = new BitmapMonitorResult();
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            options.inJustDecodeBounds = true;
            bitmapMonitorResult = CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(uri)), null, options, false);
            if (!bitmapMonitorResult.IsSuccess()) {
                return false;
            }
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            double d = i;
            double d2 = i2;
            if (d2 > d) {
                d = d2;
            }
            double d3 = i3;
            double d4 = i4;
            if (d4 < d3) {
                d3 = d4;
            }
            return d / 2.0d > d3;
        } catch (FileNotFoundException e) {
            bitmapMonitorResult.SetResult(97);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean IsVertical(Context context, Uri uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            CreateBitmap(new BufferedInputStream(context.getContentResolver().openInputStream(uri)), null, options, false);
            int i = options.outWidth;
            int i2 = options.outHeight;
            if (IsHappenExifInterfaceOrientationVHChange(ResolveBitmapOrientation(uri))) {
                i2 = i;
                i = i2;
            }
            return i <= i2;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static int ResolveBitmapOrientation(Uri uri) {
        if (uri.getPath() == null) {
            return -1;
        }
        return ResolveBitmapOrientation(new File(uri.getPath()));
    }

    public static int ResolveBitmapOrientation(File file) {
        try {
            return new ExifInterface(file.getAbsolutePath()).getAttributeInt("Orientation", 1);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void ShowHeapSize(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(TimeoutPredicate.activity);
        activityManager.getMemoryClass();
        activityManager.getLargeMemoryClass();
    }

    public static void TrySystemGC() {
        Log.e("Before GC", "Current Free Memory: " + String.valueOf(Runtime.getRuntime().freeMemory() / 1048576.0f) + "MB");
        Log.e("Before GC", "-Current Heap Memory: " + String.valueOf(Debug.getNativeHeapFreeSize() / 1048576.0f) + "MB");
        System.gc();
        Log.e("After GC", "Current Free Memory: " + String.valueOf(Runtime.getRuntime().freeMemory() / 1048576.0f) + "MB");
        Log.e("After GC", "-Current Heap Memory: " + String.valueOf(Debug.getNativeHeapFreeSize() / 1048576.0f) + "MB");
    }
}
