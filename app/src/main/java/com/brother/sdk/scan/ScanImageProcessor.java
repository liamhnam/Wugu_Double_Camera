package com.brother.sdk.scan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.brother.sdk.common.socket.scan.ScanImage;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class ScanImageProcessor extends Thread implements ScanImage.ScanImageListener {
    private Context mAndroidContext;
    private boolean mInProcessing;
    private ScanImageProcessorContext mProcessorContext;
    private ScanJobController mScanJobController;
    private File mStorageFolder;
    private Object mLockObject = new Object();
    private CornerScanImageAdjuster mAdjuster = new CornerScanImageAdjuster();
    private List<ScanImage> mImages = new ArrayList();

    public static class ScanImageProcessorContext {
        public Duplex duplex = Duplex.Simplex;
        public boolean whitePageRemove = false;
        public int noJpegImageSaveQuality = 50;
    }

    ScanImageProcessor(Context context, ScanImageProcessorContext scanImageProcessorContext, ScanJobController scanJobController) {
        this.mAndroidContext = context;
        this.mProcessorContext = scanImageProcessorContext;
        this.mScanJobController = scanJobController;
        File outputFolder = scanJobController.getOutputFolder();
        this.mStorageFolder = outputFolder;
        if (outputFolder.exists()) {
            return;
        }
        this.mStorageFolder.mkdirs();
    }

    @Override
    public void run() {
        byte[] byteArray;
        boolean z;
        File fileCreateScanTemporaryFolder = null;
        try {
            try {
                this.mInProcessing = true;
                fileCreateScanTemporaryFolder = createScanTemporaryFolder();
                while (true) {
                    ScanImage scanImage = getScanImage();
                    if (scanImage == null) {
                        break;
                    }
                    if (scanImage.Format != ScanCommandParameters.ScanImageDataFormat.Jpeg) {
                        ScanCommandParameters.ScanSize scanSize = new ScanCommandParameters.ScanSize();
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(convertBitmapArrayToPixels(scanImage.Data, scanImage.Format, scanImage.Stride, scanSize), 0, scanSize.Width, scanSize.Width, scanSize.Height, Bitmap.Config.ARGB_8888);
                        if (bitmapCreateBitmap.getHeight() < scanImage.Height) {
                            bitmapCreateBitmap = paintGrayAreaToWhite(bitmapCreateBitmap, scanImage);
                            z = true;
                        } else {
                            z = false;
                        }
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, this.mProcessorContext.noJpegImageSaveQuality, byteArrayOutputStream);
                        byteArray = byteArrayOutputStream.toByteArray();
                    } else {
                        byte[] bArr = scanImage.Data;
                        if (scanImage.LineCount <= 0 || scanImage.LineCount >= scanImage.Height) {
                            byteArray = bArr;
                            z = false;
                        } else {
                            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                            Bitmap bitmapPaintGrayAreaToWhite = paintGrayAreaToWhite(bitmapDecodeByteArray, scanImage);
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            bitmapPaintGrayAreaToWhite.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
                            byteArray = byteArrayOutputStream2.toByteArray();
                            bitmapDecodeByteArray.recycle();
                            bitmapPaintGrayAreaToWhite.recycle();
                            z = true;
                        }
                    }
                    if (z) {
                        Integer jfifStartIndex = getJfifStartIndex(scanImage.Data, 50);
                        Integer jfifStartIndex2 = getJfifStartIndex(byteArray, 50);
                        if (jfifStartIndex.intValue() != -1 && jfifStartIndex2.intValue() != -1) {
                            byteArray[jfifStartIndex2.intValue() + 11] = scanImage.Data[jfifStartIndex.intValue() + 11];
                            byteArray[jfifStartIndex2.intValue() + 12] = scanImage.Data[jfifStartIndex.intValue() + 12];
                            byteArray[jfifStartIndex2.intValue() + 13] = scanImage.Data[jfifStartIndex.intValue() + 13];
                            byteArray[jfifStartIndex2.intValue() + 14] = scanImage.Data[jfifStartIndex.intValue() + 14];
                            byteArray[jfifStartIndex2.intValue() + 15] = scanImage.Data[jfifStartIndex.intValue() + 15];
                        }
                    }
                    int i = scanImage.PageIndex;
                    File fileCreateSaveFile = createSaveFile(i);
                    FileOutputStream fileOutputStream = new FileOutputStream(fileCreateSaveFile);
                    try {
                        fileOutputStream.write(byteArray, 0, byteArray.length);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        boolean z2 = this.mProcessorContext.duplex == Duplex.FlipOnShortEdge && i % 2 == 0;
                        if (scanImage.CornerInfo != null || z2) {
                            adjustImageWithBlankAndDeskewCheck(fileCreateSaveFile, fileCreateScanTemporaryFolder, byteArray, scanImage.CornerInfo, z2);
                        }
                        this.mScanJobController.onImageReadToFile(fileCreateSaveFile.getPath(), i);
                        if (!this.mInProcessing && this.mImages.size() <= 0) {
                            break;
                        }
                    } catch (Throwable th) {
                        fileOutputStream.close();
                        throw th;
                    }
                }
                if (fileCreateScanTemporaryFolder == null) {
                    return;
                }
            } catch (Throwable th2) {
                if (fileCreateScanTemporaryFolder != null) {
                    clearFilesInFolder(fileCreateScanTemporaryFolder);
                }
                throw th2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (fileCreateScanTemporaryFolder == null) {
                return;
            }
        }
        clearFilesInFolder(fileCreateScanTemporaryFolder);
    }

    @Override
    public void onScanImageRead(ScanImage scanImage) {
        synchronized (this.mLockObject) {
            this.mImages.add(scanImage);
            this.mLockObject.notify();
        }
    }

    void cancel() {
        this.mInProcessing = false;
        interrupt();
    }

    void finish() throws InterruptedException {
        this.mInProcessing = false;
        synchronized (this.mLockObject) {
            this.mLockObject.notify();
        }
        join();
    }

    private ScanImage getScanImage() throws InterruptedException {
        ScanImage scanImage;
        synchronized (this.mLockObject) {
            while (this.mImages.size() < 1 && this.mInProcessing) {
                this.mLockObject.wait();
            }
            if (this.mImages.size() > 0) {
                scanImage = this.mImages.get(0);
                this.mImages.remove(0);
            } else {
                scanImage = null;
            }
        }
        return scanImage;
    }

    private int[] convertBitmapArrayToPixels(byte[] bArr, ScanCommandParameters.ScanImageDataFormat scanImageDataFormat, int i, ScanCommandParameters.ScanSize scanSize) {
        int[] iArr;
        int i2 = 0;
        if (scanImageDataFormat == ScanCommandParameters.ScanImageDataFormat.Bitmap_BlackAndWhite) {
            scanSize.Width = i * 8;
            scanSize.Height = bArr.length / i;
            int[] iArr2 = new int[bArr.length * 8];
            for (int i3 = 0; i3 < bArr.length; i3++) {
                for (int i4 = 0; i4 < 8; i4++) {
                    if (((bArr[i3] >> ((8 - i4) - 1)) & 1) == 1) {
                        iArr2[(i3 * 8) + i4] = (int) (-16777216);
                    } else {
                        iArr2[(i3 * 8) + i4] = (int) (-1);
                    }
                }
            }
            return iArr2;
        }
        if (scanImageDataFormat == ScanCommandParameters.ScanImageDataFormat.Bitmap_Gray8) {
            scanSize.Width = i;
            scanSize.Height = bArr.length / i;
            int[] iArr3 = new int[bArr.length];
            while (i2 < bArr.length) {
                byte b = bArr[i2];
                iArr3[i2] = (int) (((long) b) | ((long) (b << 16)) | (-16777216) | ((long) (b << 8)));
                i2++;
            }
            return iArr3;
        }
        if (scanImageDataFormat == ScanCommandParameters.ScanImageDataFormat.Bitmap_RGB24) {
            scanSize.Width = i;
            scanSize.Height = bArr.length / i;
            int length = bArr.length / 3;
            iArr = new int[length];
            while (i2 < length) {
                int i5 = i2 * 3;
                iArr[i2] = (int) (((long) (bArr[i5] << 16)) | (-16777216) | ((long) (bArr[i5 + 1] << 8)) | ((long) bArr[i5 + 2]));
                i2++;
            }
        } else if (scanImageDataFormat == ScanCommandParameters.ScanImageDataFormat.Bitmap_BGR24) {
            scanSize.Width = i;
            scanSize.Height = bArr.length / i;
            int length2 = bArr.length / 3;
            iArr = new int[length2];
            while (i2 < length2) {
                int i6 = i2 * 3;
                iArr[i2] = (int) (((long) (bArr[i6 + 2] << 16)) | (-16777216) | ((long) (bArr[i6 + 1] << 8)) | ((long) bArr[i6]));
                i2++;
            }
        } else {
            if (scanImageDataFormat != ScanCommandParameters.ScanImageDataFormat.Bitmap_R_G_B_24) {
                return null;
            }
            scanSize.Width = i;
            scanSize.Height = bArr.length / i;
            int length3 = bArr.length / 3;
            iArr = new int[length3];
            while (i2 < length3) {
                iArr[i2] = (int) (((long) (bArr[i2] << 16)) | (-16777216) | ((long) (bArr[i2 + length3] << 8)) | ((long) bArr[(length3 * 2) + i2]));
                i2++;
            }
        }
        return iArr;
    }

    private File createSaveFile(int i) throws IOException {
        String strReplaceFirst = this.mStorageFolder.getPath() + "/Scan" + SimpleDateFormat.getDateInstance(3).format(new Date()).replace(MqttTopic.TOPIC_LEVEL_SEPARATOR, "") + "_" + String.format("%03d", Integer.valueOf(i)) + ".jpq";
        File file = new File(strReplaceFirst);
        while (!file.createNewFile()) {
            String str = String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i));
            i++;
            strReplaceFirst = strReplaceFirst.replaceFirst(str, String.format(Locale.ENGLISH, "%03d", Integer.valueOf(i)));
            file = new File(strReplaceFirst);
        }
        return file;
    }

    private void clearFilesInFolder(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                file2.delete();
            }
        }
    }

    private File createScanTemporaryFolder() throws IOException {
        File externalCacheDir = this.mAndroidContext.getExternalCacheDir();
        if (externalCacheDir == null || !externalCacheDir.canWrite()) {
            throw new IllegalAccessError("WRITE_EXTERNAL_STORAGE permission must be allowed.");
        }
        File file = new File(externalCacheDir.getPath() + "/ScnTmp");
        if (!file.exists()) {
            file.mkdir();
        } else {
            clearFilesInFolder(file);
        }
        File file2 = new File(file.getPath() + "/.nomedia");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        return file;
    }

    private boolean adjustImageWithBlankAndDeskewCheck(File file, File file2, byte[] bArr, ScanImage.ImageCornerInfo imageCornerInfo, boolean z) throws Throwable {
        String str = file2.getPath() + File.separator + "input.bmp";
        this.mAdjuster.convertJpegFileToBmpFile(str, file.getPath());
        String str2 = file2.getPath() + File.separator + "output.bmp";
        if (imageCornerInfo != null) {
            this.mAdjuster.adjustImageWithBlankAndDeskewCheck(str, imageCornerInfo, str2, false, true);
            str = str2;
        }
        File fileCreateTempFile = File.createTempFile("ScanOutputTemp", ".jpg", file2);
        this.mAdjuster.convertBmpFileToJpegFile(fileCreateTempFile.getPath(), str, z);
        fileCreateTempFile.renameTo(file);
        return true;
    }

    private Bitmap paintGrayAreaToWhite(Bitmap bitmap, ScanImage scanImage) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(scanImage.Width, scanImage.Height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setColor(-1);
        canvas.drawRect(0.0f, 0.0f, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), paint);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), scanImage.LineCount);
        if (scanImage.LineCount <= 0) {
            rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        canvas.drawBitmap(bitmap, rect, rect, paint);
        canvas.save();
        canvas.restore();
        return bitmapCreateBitmap;
    }

    private Integer getJfifStartIndex(byte[] bArr, Integer num) {
        byte[] bArr2 = {-1, -32};
        byte[] bArr3 = {74, SnmpConstants.COUNTER64, 73, SnmpConstants.COUNTER64, 0};
        for (int i = 0; i < num.intValue(); i++) {
            boolean zEquals = Arrays.equals(bArr2, getArrayRange(bArr, Integer.valueOf(i), Integer.valueOf(i + 2)));
            int i2 = i + 4;
            boolean zEquals2 = Arrays.equals(bArr3, getArrayRange(bArr, Integer.valueOf(i2), Integer.valueOf(i2 + 5)));
            if (zEquals && zEquals2) {
                return Integer.valueOf(i);
            }
        }
        return -1;
    }

    private byte[] getArrayRange(byte[] bArr, Integer num, Integer num2) {
        byte[] bArr2 = new byte[num2.intValue() - num.intValue()];
        for (int i = 0; i < num2.intValue() - num.intValue(); i++) {
            bArr2[i] = bArr[num.intValue() + i];
        }
        return bArr2;
    }
}
