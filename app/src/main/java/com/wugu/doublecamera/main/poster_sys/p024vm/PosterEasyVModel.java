package com.wugu.doublecamera.main.poster_sys.p024vm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.faceunity.core.model.facebeauty.FaceBeautyFilterEnum;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.database.dbSheet.UiPositionEntity;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.widget.NotStickMutableLiveData;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.facebeauty.bean.BeautifyItem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PosterEasyVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private boolean updateOk;
    private final MutableLiveData<List<FrameInfo>> frameListLD = new MutableLiveData<>();
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<String> finalPhotoPathLD = new MutableLiveData<>();
    private final NotStickMutableLiveData<String> photoQrcodeLD = new NotStickMutableLiveData<>();
    private final NotStickMutableLiveData<String> getPrintOrderPayOkLD = new NotStickMutableLiveData<>();
    private final BeautifyItem beautyItem = new BeautifyItem();

    public LiveData<List<FrameInfo>> getFrameListLD() {
        return this.frameListLD;
    }

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public LiveData<String> getFinalPhotoPathLD() {
        return this.finalPhotoPathLD;
    }

    public LiveData<String> getPhotoQrcodeLD() {
        return this.photoQrcodeLD;
    }

    public LiveData<String> getPrintOrderPayOkLD() {
        return this.getPrintOrderPayOkLD;
    }

    public void queryFrameList(final int i) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1140x55853f60(i);
            }
        });
    }

    void m1140x55853f60(int i) {
        ThreadClock.sleep(((long) i) * 1000);
        while (!App.mIsIdle) {
            ThreadClock.sleep(3000L);
        }
        this.frameListLD.postValue(getFrameListFromDb());
    }

    public void initUiMap() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                PosterEasyVModel.lambda$initUiMap$1();
            }
        });
    }

    static void lambda$initUiMap$1() {
        App.mUiPosMap.clear();
        for (UiPositionEntity uiPositionEntity : DbHelper.getInstance().getAllUiPositionEntity()) {
            App.mUiPosMap.put(Integer.valueOf(uiPositionEntity.getIndex()), new UiPosition(uiPositionEntity));
        }
    }

    private List<FrameInfo> getFrameListFromDb() {
        ArrayList arrayList = new ArrayList();
        for (FrameEntity frameEntity : DbHelper.getInstance().getFramesByType(0)) {
            if (frameEntity.getIsEnable() && !TextUtils.isEmpty(frameEntity.getFrameLocalPath())) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<FramePhotoEntity> it = DbHelper.getInstance().getFramePhotos(frameEntity.getFrameKey()).iterator();
                while (it.hasNext()) {
                    arrayList2.add(new FramePhotoInfo(it.next()));
                }
                if (!arrayList2.isEmpty()) {
                    arrayList.add(new FrameInfo(frameEntity, arrayList2));
                }
            }
        }
        return arrayList;
    }

    public BeautifyItem getDefaultBeauty() {
        int defaultBeautyLevel = SpManager.getInstance().getDefaultBeautyLevel(2);
        LoggerUtil.m1181d("PosterEasyVModel", "getDefaultBeauty " + defaultBeautyLevel);
        if (DbHelper.getInstance().getBeautyByLevel(defaultBeautyLevel) == null) {
            return this.beautyItem;
        }
        if (TextUtils.isEmpty(this.beautyItem.filterParams.filterName)) {
            this.beautyItem.filterParams.filterName = FaceBeautyFilterEnum.HEIBAI_1;
            this.beautyItem.filterParams.filterIntensity = 1.0f;
        }
        this.beautyItem.beautyParams.blur = Math.min(r0.getBlur() / 100.0f, 6.0f);
        this.beautyItem.beautyParams.white = Math.min(r0.getWhite() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.red = Math.min(r0.getRed() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.sharpen = Math.min(r0.getSharpen() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.clarity = Math.min(r0.getClarity() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeBright = Math.min(r0.getEyeBright() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.tooth = Math.min(r0.getTooth() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removePouch = Math.min(r0.getRemovePouch() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removeLawPat = Math.min(r0.getRemoveLawPat() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removeSpot = Math.min(r0.getRemoveSpot() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.faceThin = Math.min(r0.getFaceThin() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeBig = Math.min(r0.getEyeBig() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeCircle = Math.min(r0.getEyeCircle() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.faceThree = Math.min(r0.getFaceThree() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.noseThin = Math.min(r0.getNose() / 100.0f, 1.0f);
        return this.beautyItem;
    }

    public BeautifyItem updateBeauty() {
        int defaultBeautyLevel = SpManager.getInstance().getDefaultBeautyLevel(2);
        LoggerUtil.m1181d("PosterEasyVModel", "updateBeauty " + defaultBeautyLevel);
        if (this.beautyItem == null) {
            return null;
        }
        if (DbHelper.getInstance().getBeautyByLevel(defaultBeautyLevel) == null) {
            return this.beautyItem;
        }
        this.beautyItem.beautyParams.blur = Math.min(r0.getBlur() / 100.0f, 6.0f);
        this.beautyItem.beautyParams.white = Math.min(r0.getWhite() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.red = Math.min(r0.getRed() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.sharpen = Math.min(r0.getSharpen() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.clarity = Math.min(r0.getClarity() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeBright = Math.min(r0.getEyeBright() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.tooth = Math.min(r0.getTooth() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removePouch = Math.min(r0.getRemovePouch() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removeLawPat = Math.min(r0.getRemoveLawPat() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removeSpot = Math.min(r0.getRemoveSpot() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.faceThin = Math.min(r0.getFaceThin() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeBig = Math.min(r0.getEyeBig() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeCircle = Math.min(r0.getEyeCircle() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.faceThree = Math.min(r0.getFaceThree() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.noseThin = Math.min(r0.getNose() / 100.0f, 1.0f);
        return this.beautyItem;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 1000L) {
            @Override
            public void onTick(long j) {
                int i2 = (int) (j / 1000);
                if (i2 == 0) {
                    return;
                }
                PosterEasyVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                PosterEasyVModel.this.countdownLD.postValue(0);
            }
        };
        this.countdownTimer = countDownTimer2;
        countDownTimer2.start();
    }

    public void cancelCountdown() {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public int getPrintTime() {
        return PrinterHelper.getInstance().getPrintTime();
    }

    public void savePhotoAndFrame(final Context context, final Bitmap bitmap, final FrameInfo frameInfo, final boolean z, final boolean z2, final boolean z3) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.m1141x6f690db0(z, z2, z3, context, bitmap, frameInfo);
            }
        });
    }

    void m1141x6f690db0(boolean z, boolean z2, boolean z3, Context context, Bitmap bitmap, FrameInfo frameInfo) throws Throwable {
        String orderArCodeStr;
        Bitmap bitmapQrCode;
        Bitmap bitmapQrCode2;
        LoggerUtil.m1181d("PosterEasyVModel", "savePhotoAndFrame " + z + ", qr " + z2 + ", ar " + z3);
        String subFrameName = AppConfig.getSubFrameName();
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, subFrameName);
        Bitmap bitmapCopyBitmap = BitmapUtil.copyBitmap(bitmap);
        if (FileUtil.saveBitmap(bitmapCopyBitmap, file, 90)) {
            OrderFileUtil.appendOrderFileName(subFrameName);
            Bitmap localBitmap = BitmapUtil.getLocalBitmap(context, frameInfo.getFramePath());
            int width = localBitmap.getWidth();
            int height = localBitmap.getHeight();
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, localBitmap.getConfig());
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            FramePhotoInfo framePhotoInfo = frameInfo.getPhotoInfoList().get(0);
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (bitmapDecodeFile == null) {
                return;
            }
            canvas.drawBitmap(bitmapDecodeFile, (Rect) null, new Rect(framePhotoInfo.getLocationX(), framePhotoInfo.getLocationY(), framePhotoInfo.getLocationX() + framePhotoInfo.getWidth(), framePhotoInfo.getLocationY() + framePhotoInfo.getHeight()), (Paint) null);
            bitmapDecodeFile.recycle();
            canvas.drawBitmap(localBitmap, 0.0f, 0.0f, (Paint) null);
            if (z2 && width > 1800 && height > 2600 && (bitmapQrCode2 = QrCodeUtil.getBitmapQrCode(OrderFileUtil.getOrderFileQrCodeStr(), 120, null)) != null) {
                canvas.drawBitmap(bitmapQrCode2, 1655.0f, 2440.0f, (Paint) null);
                bitmapQrCode2.recycle();
            }
            if (z3 && width > 1800 && height > 2600 && (orderArCodeStr = OrderFileUtil.getOrderArCodeStr()) != null && (bitmapQrCode = QrCodeUtil.getBitmapQrCode(orderArCodeStr, 186, null)) != null) {
                canvas.drawBitmap(bitmapQrCode, 1467.0f, 2380.0f, (Paint) null);
                bitmapQrCode.recycle();
            }
            localBitmap.recycle();
            if (SpManager.getInstance().getIsPeShowDate()) {
                Paint paint = new Paint();
                paint.setColor(Color.parseColor("#1E1E1E"));
                paint.setTextSize(46.0f);
                paint.setTypeface(Typeface.defaultFromStyle(1));
                canvas.drawText(AppUtil.getCountryTimeWeekStr(context), 110.0f, 110.0f, paint);
            }
            String frameName = AppConfig.getFrameName();
            File file2 = new File(filesDir, frameName);
            FileUtil.saveBitmap(bitmapCreateBitmap, file2, 90);
            bitmapCreateBitmap.recycle();
            OrderFileUtil.appendOrderFileName(frameName);
            this.finalPhotoPathLD.postValue(file2.getAbsolutePath());
            OrderFileUtil.uploadOrderBitmap(bitmapCopyBitmap, subFrameName, null);
            uploadOrderInfo(file2.getAbsolutePath());
            bitmapCopyBitmap.recycle();
            if (z) {
                genBlackPdfFile(file2.getAbsolutePath());
            }
        }
    }

    public void genBlackPdfFile(String str) throws Throwable {
        Bitmap bitmapCreateBitmap;
        FileOutputStream fileOutputStream;
        if (str == null || !new File(str).exists()) {
            LoggerUtil.m1182e("PosterEasyVModel", "genBlackPdfFile null");
            return;
        }
        String strReplace = str.replace(".jpg", ".pdf");
        PdfDocument pdfDocument = new PdfDocument();
        Bitmap bitmap = null;
        try {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            try {
                if (bitmapDecodeFile == null) {
                    LoggerUtil.m1182e("PosterEasyVModel", "Failed to decode bitmap");
                    if (bitmapDecodeFile != null) {
                        bitmapDecodeFile.recycle();
                    }
                    pdfDocument.close();
                    return;
                }
                ColorMatrix colorMatrix = new ColorMatrix();
                colorMatrix.setSaturation(0.0f);
                Paint paint = new Paint();
                paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), Bitmap.Config.RGB_565);
                try {
                    new Canvas(bitmapCreateBitmap).drawBitmap(bitmapDecodeFile, 0.0f, 0.0f, paint);
                    PdfDocument.Page pageStartPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), 1).create());
                    pageStartPage.getCanvas().drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
                    pdfDocument.finishPage(pageStartPage);
                    try {
                        fileOutputStream = new FileOutputStream(strReplace);
                    } catch (IOException e) {
                        LoggerUtil.m1182e("PosterEasyVModel", "Failed to write PDF " + e);
                    }
                    try {
                        pdfDocument.writeTo(fileOutputStream);
                        fileOutputStream.close();
                        if (bitmapDecodeFile != null) {
                            bitmapDecodeFile.recycle();
                        }
                        if (bitmapCreateBitmap != null) {
                            bitmapCreateBitmap.recycle();
                        }
                        pdfDocument.close();
                    } catch (Throwable th) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bitmap = bitmapDecodeFile;
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    if (bitmapCreateBitmap != null) {
                        bitmapCreateBitmap.recycle();
                    }
                    pdfDocument.close();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bitmapCreateBitmap = null;
            }
        } catch (Throwable th5) {
            th = th5;
            bitmapCreateBitmap = null;
        }
    }

    private void uploadOrderInfo(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        this.updateOk = false;
        OrderFileUtil.uploadOrderFilePaths(arrayList, new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.m1144x806edba1(i);
            }
        });
    }

    void m1144x806edba1(int i) {
        if (i == 1) {
            this.updateOk = true;
        }
    }

    public void startPrint(final String str, final int i, final String str2) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1143x8afa008c(i, str, str2);
            }
        });
        showPhotoQrCode();
    }

    void m1143x8afa008c(int i, String str, String str2) {
        System.gc();
        finishOrder(PrinterHelper.getInstance().getPrinterRemainSheet(), i);
        ThreadClock.sleep(1000L);
        if (PrinterHelper.getInstance().isPrintPdf()) {
            ThreadClock.sleep(5000L);
            String strReplace = str.replace(".jpg", ".pdf");
            File file = new File(strReplace);
            if (file.exists()) {
                PrinterHelper.getInstance().addPrintTask(strReplace, i, false);
                return;
            }
            ThreadClock.sleep(3000L);
            if (file.exists()) {
                PrinterHelper.getInstance().addPrintTask(strReplace, i, false);
                return;
            } else {
                LoggerUtil.m1182e("PosterEasyVModel", "printerPrint pdf not exist");
                return;
            }
        }
        PrinterHelper.getInstance().addPrintTask(str, i, TextUtils.isEmpty(str2) || !str2.equals(ExifInterface.GPS_MEASUREMENT_2D));
    }

    public void finishOrder(int i, int i2) {
        int iMax = Math.max(0, i - i2);
        SpManager.getInstance().setRemainPrinterSheet(iMax);
        OrderManager.finishOrder(iMax, SpManager.getInstance().getRemainPrintCount() - i2);
    }

    public void showPhotoQrCode() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1142xb0e62e4b();
            }
        });
    }

    void m1142xb0e62e4b() {
        SoundHelper.getInstance().playSoundEffect(12);
        ThreadClock.sleep(5000L);
        if (this.updateOk) {
            this.photoQrcodeLD.postValue(OrderFileUtil.getOrderFileQrCodeStr());
        } else {
            ThreadClock.sleep(15000L);
            this.photoQrcodeLD.postValue(OrderFileUtil.getOrderFileQrCodeStr());
        }
    }
}
