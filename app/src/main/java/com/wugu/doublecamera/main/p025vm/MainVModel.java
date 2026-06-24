package com.wugu.doublecamera.main.p025vm;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.FFmpegSession;
import com.arthenica.ffmpegkit.ReturnCode;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.PhotoAddedItem;
import com.wugu.doublecamera.bean.SignCommand;
import com.wugu.doublecamera.bean.StartPrintingInfo;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.bean.dto.OrderStatusDto;
import com.wugu.doublecamera.bean.dto.RedeemCodeDto;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.enums.FrameRemarkEnum;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.NotStickMutableLiveData;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class MainVModel extends ViewModel {
    private static final String TAG = "MainVModel";
    private CountDownTimer countdownTimer;
    private volatile boolean isCheckingOrderStatus;
    private volatile boolean isCheckingPrintOrderStatus;
    public final MutableLiveData<Integer> fragmentLD = new MutableLiveData<>();
    public final MutableLiveData<Integer> lockScreenLD = new MutableLiveData<>();
    public final MutableLiveData<String> chooseFrameOkLD = new MutableLiveData<>();
    public final MutableLiveData<List<String>> photoUrlLD = new MutableLiveData<>();
    public final MutableLiveData<List<String>> picSelectUrlLD = new MutableLiveData<>();
    public final MutableLiveData<StartPrintingInfo> startPrintingLD = new MutableLiveData<>();
    public final NotStickMutableLiveData<Integer> digitalCameraResetLD = new NotStickMutableLiveData<>();
    public final NotStickMutableLiveData<String> stickerAddLD = new NotStickMutableLiveData<>();
    public final NotStickMutableLiveData<PhotoAddedItem> photoAddedLD = new NotStickMutableLiveData<>();
    public final NotStickMutableLiveData<SignCommand> signCommand = new NotStickMutableLiveData<>();
    public final NotStickMutableLiveData<Integer> signPaintUndoRedo = new NotStickMutableLiveData<>();
    public final MutableLiveData<String> saveVideoFinishLD = new MutableLiveData<>();
    public final NotStickMutableLiveData<String> uploadPrintRecLD = new NotStickMutableLiveData<>();
    public final NotStickMutableLiveData<String> uploadPrintLD = new NotStickMutableLiveData<>();
    public final NotStickMutableLiveData<RedeemCodeDto> redeemCodeInfoLD = new NotStickMutableLiveData<>();
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final NotStickMutableLiveData<Integer> paymentStepLD = new NotStickMutableLiveData<>();
    private final NotStickMutableLiveData<Integer> remoteControlLD = new NotStickMutableLiveData<>();
    private final List<String> tempVideoList = new ArrayList();
    private final List<String> finalVideoList = new ArrayList();
    private final AtomicInteger videoProcessCount = new AtomicInteger(0);

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public LiveData<Integer> getPayStepLD() {
        return this.paymentStepLD;
    }

    public void postPayStep(int i) {
        this.paymentStepLD.postValue(Integer.valueOf(i));
    }

    public LiveData<Integer> getRemoteControlLD() {
        return this.remoteControlLD;
    }

    public void postRemoteControl(int i) {
        this.remoteControlLD.postValue(Integer.valueOf(i));
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
                MainVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                MainVModel.this.countdownLD.postValue(0);
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

    public void queryRedeemCode(String str) {
        LoggerUtil.m1181d(TAG, "queryRedeemCodeInfo " + str);
        if (str.equals("246709") && SpManager.getInstance().getIsLockScreen()) {
            OrderManager.mRedeemCodeDto = new RedeemCodeDto(8);
            OrderManager.mRedeemCodeDto.setCode("246709");
            OrderManager.mRedeemCodeDto.setAmount(0.0f);
            this.redeemCodeInfoLD.postValue(OrderManager.mRedeemCodeDto);
            return;
        }
        HttpManager.getInstance().mHttpApi.getRedeemCode(str).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<RedeemCodeDto>>() {
            @Override
            public void onNext(BaseDto<RedeemCodeDto> baseDto) {
                if (baseDto == null) {
                    MainVModel.this.redeemCodeInfoLD.postValue(null);
                    return;
                }
                if (baseDto.errCode == 0 && baseDto.data != null) {
                    OrderManager.mRedeemCodeDto = baseDto.data;
                    LoggerUtil.m1181d(MainVModel.TAG, "redeemCode " + OrderManager.mRedeemCodeDto.getType() + ", " + OrderManager.mRedeemCodeDto.getAmount());
                    MainVModel.this.redeemCodeInfoLD.postValue(OrderManager.mRedeemCodeDto);
                } else {
                    int i = 3;
                    if (baseDto.errCode != 300021 && baseDto.errCode != 300022) {
                        i = baseDto.errCode == 300023 ? 5 : 0;
                    }
                    LoggerUtil.m1182e(MainVModel.TAG, "queryRedeemCodeInfo error " + baseDto.errCode + ", " + baseDto.errMsg);
                    MainVModel.this.redeemCodeInfoLD.postValue(new RedeemCodeDto(i));
                }
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
                LoggerUtil.m1182e(MainVModel.TAG, "queryRedeemCodeInfo onError " + th.getMessage());
                MainVModel.this.redeemCodeInfoLD.postValue(null);
            }
        });
    }

    public void queryXieChengCode(final String str) {
        LoggerUtil.m1181d(TAG, "queryXieChengCode " + str);
        HttpManager.getInstance().mHttpApi.queryXieChengCode(str).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<RedeemCodeDto>>() {
            @Override
            public void onNext(BaseDto<RedeemCodeDto> baseDto) {
                if (baseDto == null) {
                    MainVModel.this.redeemCodeInfoLD.postValue(null);
                    return;
                }
                if (baseDto.errCode == 0) {
                    OrderManager.mRedeemCodeDto = new RedeemCodeDto(9);
                    OrderManager.mRedeemCodeDto.setCode(str);
                    MainVModel.this.redeemCodeInfoLD.postValue(OrderManager.mRedeemCodeDto);
                } else {
                    int i = 3;
                    if (baseDto.errCode != 300021 && baseDto.errCode != 300022) {
                        i = baseDto.errCode == 300023 ? 5 : 0;
                    }
                    LoggerUtil.m1182e(MainVModel.TAG, "queryRedeemCodeInfo error " + baseDto.errCode + ", " + baseDto.errMsg);
                    MainVModel.this.redeemCodeInfoLD.postValue(new RedeemCodeDto(i));
                }
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
                LoggerUtil.m1182e(MainVModel.TAG, "queryRedeemCodeInfo onError " + th.getMessage());
                MainVModel.this.redeemCodeInfoLD.postValue(null);
            }
        });
    }

    public void queryMtDyCode() {
        LoggerUtil.m1181d(TAG, "queryMtDyCode " + OrderManager.mPaymentMethod + ", " + OrderManager.mMeiTuanDouYinCode);
        HttpManager.getInstance().mHttpApi.queryMtDyCode(OrderManager.mMeiTuanDouYinCode, OrderManager.mPaymentMethod == 9 ? "DY" : "MTDZ").subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
            @Override
            public void onNext(BaseDto<Object> baseDto) {
                int i;
                if (baseDto == null) {
                    MainVModel.this.redeemCodeInfoLD.postValue(null);
                    return;
                }
                if (baseDto.errCode == 0 && baseDto.data != null) {
                    OrderManager.mRedeemCodeDto = new RedeemCodeDto(OrderManager.mPaymentMethod == 9 ? 7 : 6);
                    OrderManager.mRedeemCodeDto.setCode(OrderManager.mMeiTuanDouYinCode);
                    MainVModel.this.redeemCodeInfoLD.postValue(OrderManager.mRedeemCodeDto);
                } else {
                    if (baseDto.errCode == 300021) {
                        i = 4;
                    } else {
                        if (baseDto.errCode != 100400) {
                            int i2 = baseDto.errCode;
                        }
                        i = 0;
                    }
                    MainVModel.this.redeemCodeInfoLD.postValue(new RedeemCodeDto(i));
                }
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
                MainVModel.this.redeemCodeInfoLD.postValue(null);
            }
        });
    }

    public void saveVideo(final String str, final int i, final int i2) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1710lambda$saveVideo$0$comwugudoublecameramainvmMainVModel(str, i, i2);
            }
        });
    }

    void m1710lambda$saveVideo$0$comwugudoublecameramainvmMainVModel(String str, int i, int i2) {
        int i3;
        int i4;
        int i5;
        ThreadClock.sleep(1000L);
        int[] mp4WH = FileUtil.getMp4WH(str);
        int i6 = mp4WH[0];
        if (i6 == -1 || (i3 = mp4WH[1]) == -1) {
            return;
        }
        float f = i / i2;
        float f2 = i3;
        float f3 = i6;
        if (f > f2 / f3) {
            i5 = (int) (f2 / f);
            i4 = i3;
        } else {
            i4 = (int) (f3 * f);
            i5 = i6;
        }
        int i7 = (i3 - i4) / 2;
        int i8 = (i6 - i5) / 2;
        int videoZoom = SpManager.getInstance().getVideoZoom();
        int i9 = i / videoZoom;
        int i10 = i2 / videoZoom;
        if (i9 % 2 != 0) {
            i9++;
        }
        if (i10 % 2 != 0) {
            i10++;
        }
        String str2 = AppConfig.TEMP_DIR + "/ffmpeg" + StringUtil.getFileNameFromPath(str);
        String str3 = "-i " + str + " -vf crop=" + i5 + ":" + i4 + ":" + i8 + ":" + i7 + ",scale=" + i10 + ":" + i9 + ",transpose=2 -c:v libx264 " + str2;
        this.tempVideoList.add(str2);
        this.videoProcessCount.incrementAndGet();
        LoggerUtil.m1181d("FFMPEG", "saveVideo " + str3);
        try {
            FFmpegSession fFmpegSessionExecute = FFmpegKit.execute(str3);
            if (ReturnCode.isSuccess(fFmpegSessionExecute.getReturnCode())) {
                FileUtil.deleteFileFolder(str);
                ThreadClock.sleep(500L);
                FileUtil.notifyFile(str2);
            } else {
                LoggerUtil.m1182e("FFMPEG", String.format("Command failed with state %s and rc %s.%s", fFmpegSessionExecute.getState(), fFmpegSessionExecute.getReturnCode(), fFmpegSessionExecute.getFailStackTrace()));
            }
        } catch (Exception e) {
            LoggerUtil.m1182e("FFMPEG", "saveVideo error " + e.getMessage());
        }
        this.videoProcessCount.decrementAndGet();
    }

    public void compoundAndSaveVideo(final String str, final String str2) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1156x4e1ebf78(str, str2);
            }
        });
    }

    void m1156x4e1ebf78(String str, String str2) {
        ThreadClock.sleep(2000L);
        String str3 = "-y -r 13 -f image2 -i " + str + "/%03d.jpg -c:v libx264 " + str2;
        LoggerUtil.m1181d("FFMPEG", "compoundAndSaveVideo " + str3);
        this.videoProcessCount.incrementAndGet();
        this.tempVideoList.add(str2);
        try {
            FFmpegSession fFmpegSessionExecute = FFmpegKit.execute(str3);
            if (ReturnCode.isSuccess(fFmpegSessionExecute.getReturnCode())) {
                LoggerUtil.m1181d("compound", "compoundAndSaveVideo finish ");
            } else {
                LoggerUtil.m1182e("FFMPEG", "compoundAndSaveVideo error " + fFmpegSessionExecute.getLogsAsString());
            }
        } catch (Exception e) {
            LoggerUtil.m1182e("FFMPEG", " compoundAndSaveVideo error " + e.getMessage());
        }
        this.videoProcessCount.decrementAndGet();
    }

    public void retakeConfirm(final String str, final int i, final boolean z) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1709lambda$retakeConfirm$2$comwugudoublecameramainvmMainVModel(i, z, str);
            }
        });
    }

    void m1709lambda$retakeConfirm$2$comwugudoublecameramainvmMainVModel(int i, boolean z, String str) {
        ThreadClock.sleep(1000L);
        LoggerUtil.m1181d("FFMPEG", "retakeConfirm " + i + " " + z + " " + this.tempVideoList.size());
        if (i < this.tempVideoList.size()) {
            this.finalVideoList.add(this.tempVideoList.get(i));
        } else if (this.tempVideoList.size() > 0) {
            this.finalVideoList.add(this.tempVideoList.get(0));
        }
        this.tempVideoList.clear();
        if (z) {
            String str2 = AppConfig.FFMPEG_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + AppConfig.getMp4Name();
            OrderFileUtil.appendOrderFileName(StringUtil.getFileNameFromPath(str2));
            for (int i2 = 0; i2 < 20 && this.videoProcessCount.get() > 0; i2++) {
                ThreadClock.sleep(2000L);
            }
            ThreadClock.sleep(1000L);
            compoundFrameMp4(str2, str, this.finalVideoList);
            this.finalVideoList.clear();
        }
    }

    private void compoundFrameMp4(String str, String str2, List<String> list) {
        App.mFrameVideoPath = str;
        int videoZoom = SpManager.getInstance().getVideoZoom();
        FrameEntity frameByKey = DbHelper.getInstance().getFrameByKey(str2);
        List<FramePhotoEntity> framePhotos = DbHelper.getInstance().getFramePhotos(str2);
        LoggerUtil.m1181d("compound", "compoundFrameMp4 " + str + ", " + videoZoom);
        if (frameByKey == null || framePhotos == null) {
            return;
        }
        String frameLocalPath = frameByKey.getFrameLocalPath();
        try {
            Bitmap localBitmap = BitmapUtil.getLocalBitmap(App.getInstance(), frameLocalPath);
            Matrix matrix = new Matrix();
            float f = videoZoom != 1 ? videoZoom != 2 ? 0.25f : 0.5f : 1.0f;
            matrix.setScale(f, f);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), matrix, true);
            String str3 = AppConfig.TEMP_DIR + "/temp_frame" + StringUtil.getFileNameFromPath(frameLocalPath);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str3));
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 80, fileOutputStream);
            fileOutputStream.close();
            bitmapCreateBitmap.recycle();
            StringBuilder sb = new StringBuilder("-i ");
            sb.append(str3);
            int i = (frameByKey.getIsNeedCut() && AppConfig.isBelongHeadSys()) ? 2 : 1;
            String remark = frameByKey.getRemark();
            if (!TextUtils.isEmpty(remark) && remark.contains(FrameRemarkEnum.IS_BROKEN_LINE)) {
                String remarkValueByKey = StringUtil.getRemarkValueByKey(remark, FrameRemarkEnum.IS_BROKEN_LINE);
                if (!TextUtils.isEmpty(remarkValueByKey) && remarkValueByKey.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                    i *= 2;
                }
            }
            for (int i2 = 0; i2 < i; i2++) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    sb.append(" -i ").append(it.next());
                }
            }
            sb.append(" -i ").append(str3);
            sb.append(" -filter_complex \"");
            for (FramePhotoEntity framePhotoEntity : framePhotos) {
                sb.append("overlay=");
                sb.append(framePhotoEntity.getX() / videoZoom);
                sb.append(":");
                sb.append(framePhotoEntity.getY() / videoZoom);
                sb.append(",");
            }
            sb.append("overlay=0:0\" -c:v libx264 -an -r 15 -shortest -pix_fmt yuv420p ");
            sb.append(str);
            LoggerUtil.m1181d("FFMPEG", "compoundFrameMp4 " + ((Object) sb));
            FFmpegSession fFmpegSessionExecute = FFmpegKit.execute(sb.toString());
            if (ReturnCode.isSuccess(fFmpegSessionExecute.getReturnCode())) {
                ThreadClock.sleep(1000L);
                LoggerUtil.m1181d("compound", "compoundFrameMp4 finish ");
                this.saveVideoFinishLD.postValue(str);
                uploadVideo(str);
            } else {
                LoggerUtil.m1182e("FFMPEG", "compoundFrameMp4 error " + fFmpegSessionExecute.getLogsAsString());
            }
            if (FileUtil.deleteFolderAllFiles(AppConfig.TEMP_DIR)) {
                return;
            }
            LoggerUtil.m1182e(TAG, "del TEMP_DIR fail, try again");
            ThreadClock.sleep(1000L);
            FileUtil.deleteFolderAllFiles(AppConfig.TEMP_DIR);
        } catch (Exception e) {
            LoggerUtil.m1181d("compound", "compoundFrameMp4 error " + e);
        }
    }

    public void uploadVideo(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        OrderFileUtil.uploadOrderFilePaths(arrayList, null);
    }

    public void testPrint() {
        PrinterHelper.getInstance().addPrintTask(AppConfig.TEST_PRINT_FILE_PATH, 1, false);
    }

    public void startCheckPhotoOrderTask() {
        LoggerUtil.m1181d(TAG, "startCheckOrderTask " + this.isCheckingOrderStatus);
        if (this.isCheckingOrderStatus) {
            return;
        }
        this.isCheckingOrderStatus = true;
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1157xa7c38001();
            }
        });
    }

    void m1157xa7c38001() {
        ThreadClock.sleep(3000L);
        int i = 5;
        while (TextUtils.isEmpty(OrderManager.mOrderId)) {
            ThreadClock.sleep(2000L);
            int i2 = i - 1;
            if (i < 0) {
                return;
            } else {
                i = i2;
            }
        }
        getPhotoOrderStatus();
    }

    public void startCheckPrintOrderTask() {
        LoggerUtil.m1181d(TAG, "startCheckPrintOrderTask " + this.isCheckingPrintOrderStatus);
        if (this.isCheckingPrintOrderStatus) {
            return;
        }
        this.isCheckingPrintOrderStatus = true;
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1158xe8ce721b();
            }
        });
    }

    void m1158xe8ce721b() {
        ThreadClock.sleep(3000L);
        int i = 5;
        while (TextUtils.isEmpty(OrderManager.mPrintOrderId)) {
            ThreadClock.sleep(2000L);
            int i2 = i - 1;
            if (i < 0) {
                return;
            } else {
                i = i2;
            }
        }
        getPrintOrderStatus();
    }

    public void stopCheckOrderTask() {
        LoggerUtil.m1181d(TAG, "stopCheckOrderTask");
        this.isCheckingOrderStatus = false;
        this.isCheckingPrintOrderStatus = false;
    }

    public void getPhotoOrderStatus() {
        LoggerUtil.m1181d(TAG, "getOrderStatus " + this.isCheckingOrderStatus);
        if (this.isCheckingOrderStatus) {
            HttpManager.getInstance().mHttpApi.getOrderInfo(OrderManager.mOrderId).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<OrderStatusDto>>() {
                @Override
                public void onNext(BaseDto<OrderStatusDto> baseDto) {
                    if (baseDto == null || baseDto.data == null) {
                        return;
                    }
                    LoggerUtil.m1181d(MainVModel.TAG, "orderStatus=" + baseDto.data.orderStatus + ", orderId " + baseDto.data.orderId);
                    ThreadClock.sleep(500L);
                    if (!MainVModel.this.isCheckingOrderStatus || TextUtils.isEmpty(OrderManager.mOrderId)) {
                        return;
                    }
                    if (baseDto.data.orderStatus == 2) {
                        LoggerUtil.m1181d(MainVModel.TAG, "orderStatus=2, pay success");
                        MainVModel.this.stopCheckOrderTask();
                        MainVModel.this.remoteControlLD.postValue(3);
                    } else if (baseDto.data.orderStatus == 1) {
                        ThreadClock.sleep(3000L);
                        MainVModel.this.getPhotoOrderStatus();
                    }
                }

                @Override
                public void onError(Throwable th) {
                    LoggerUtil.m1182e(MainVModel.TAG, "getOrderStatus=" + th);
                    ThreadClock.sleep(3000L);
                    MainVModel.this.getPhotoOrderStatus();
                }
            });
        }
    }

    public void getPrintOrderStatus() {
        LoggerUtil.m1181d(TAG, "getPrintOrderStatus " + this.isCheckingPrintOrderStatus);
        if (this.isCheckingPrintOrderStatus) {
            HttpManager.getInstance().mHttpApi.getOrderInfo(OrderManager.mPrintOrderId).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<OrderStatusDto>>() {
                @Override
                public void onNext(BaseDto<OrderStatusDto> baseDto) {
                    if (baseDto == null || baseDto.data == null) {
                        return;
                    }
                    LoggerUtil.m1181d(MainVModel.TAG, "printOrderStatus=" + baseDto.data.orderStatus + ", mPrintOrderId " + OrderManager.mPrintOrderId + ", orderId " + baseDto.data.orderId);
                    ThreadClock.sleep(500L);
                    if (!MainVModel.this.isCheckingPrintOrderStatus || TextUtils.isEmpty(OrderManager.mPrintOrderId)) {
                        return;
                    }
                    if (baseDto.data.orderStatus == 2) {
                        LoggerUtil.m1181d(MainVModel.TAG, "printOrderStatus=2, pay success");
                        MainVModel.this.stopCheckOrderTask();
                        MainVModel.this.remoteControlLD.postValue(3);
                    } else if (baseDto.data.orderStatus == 1) {
                        ThreadClock.sleep(3000L);
                        MainVModel.this.getPrintOrderStatus();
                    }
                }

                @Override
                public void onError(Throwable th) {
                    LoggerUtil.m1182e(MainVModel.TAG, "getPrintOrderStatus=" + th);
                    ThreadClock.sleep(3000L);
                    MainVModel.this.getPrintOrderStatus();
                }
            });
        }
    }
}
