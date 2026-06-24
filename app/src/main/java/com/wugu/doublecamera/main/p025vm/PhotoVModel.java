package com.wugu.doublecamera.main.p025vm;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.wujieai.AiBaseDto;
import com.wugu.doublecamera.bean.dto.wujieai.AiCreateTaskDto;
import com.wugu.doublecamera.bean.dto.wujieai.AiTaskInfoDto;
import com.wugu.doublecamera.bean.p023vo.AiCreateTaskVo;
import com.wugu.doublecamera.bean.p023vo.OrderAiTaskVo;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.dbSheet.FilterEntity;
import com.wugu.doublecamera.database.dbSheet.MakeupEntity;
import com.wugu.doublecamera.listener.IAiEnhancedListener;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.facebeauty.bean.BeautifyItem;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PhotoVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private int currentCountdown;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<Integer> saveImageFinishLD = new MutableLiveData<>();
    private final MutableLiveData<BeautifyItem> beautyItemLD = new MutableLiveData<>();
    private final MutableLiveData<String> aiCreateIdLD = new MutableLiveData<>();
    private final List<String> imageList = new ArrayList();
    private final BeautifyItem beautyItem = new BeautifyItem();

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public LiveData<Integer> getSaveImageFinishLD() {
        return this.saveImageFinishLD;
    }

    public LiveData<BeautifyItem> getBeautyItemLD() {
        return this.beautyItemLD;
    }

    public LiveData<String> getAiCreateIdLD() {
        return this.aiCreateIdLD;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.currentCountdown = i;
        LoggerUtil.m1181d("photoVM", "startCountdown " + i);
        CountDownTimer countDownTimer2 = new CountDownTimer((((long) i) * 1000) + 200, 1000L) {
            @Override
            public void onTick(long j) {
                PhotoVModel.this.currentCountdown = (int) (j / 1000);
                if (PhotoVModel.this.currentCountdown == 0) {
                    return;
                }
                PhotoVModel.this.countdownLD.postValue(Integer.valueOf(PhotoVModel.this.currentCountdown));
            }

            @Override
            public void onFinish() {
                PhotoVModel.this.countdownLD.postValue(0);
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

    public void pauseCountdown() {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void resumeCountdown() {
        startCountdown(this.currentCountdown);
    }

    public List<String> getImageList() {
        return this.imageList;
    }

    public void aiEnhance(final Context context, final Bitmap bitmap, final String str) {
        pauseCountdown();
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.m1712lambda$aiEnhance$1$comwugudoublecameramainvmPhotoVModel(context, bitmap, str);
            }
        });
    }

    void m1712lambda$aiEnhance$1$comwugudoublecameramainvmPhotoVModel(Context context, Bitmap bitmap, final String str) throws Throwable {
        final String aiFrameName = AppConfig.getAiFrameName(".jpg");
        File file = new File(context.getFilesDir(), aiFrameName);
        LoggerUtil.m1181d("PhotoVM", "aiEnhance save " + aiFrameName);
        FileUtil.saveBitmap(bitmap, file);
        OrderFileUtil.appendOrderFileName(aiFrameName);
        OrderFileUtil.uploadOrderBitmap(BitmapUtil.compressBitmap(bitmap, 1000.0f), aiFrameName, new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.m1711lambda$aiEnhance$0$comwugudoublecameramainvmPhotoVModel(aiFrameName, str, i);
            }
        });
    }

    void m1711lambda$aiEnhance$0$comwugudoublecameramainvmPhotoVModel(String str, String str2, int i) {
        if (i == 1) {
            String str3 = AppConfig.HTTP_HOST + "order/images/" + str;
            AiCreateTaskVo aiCreateTaskVo = new AiCreateTaskVo();
            ArrayList arrayList = new ArrayList();
            arrayList.add(str3);
            aiCreateTaskVo.images = arrayList;
            aiCreateTaskVo.model_id = str2;
            HttpManager.getInstance().mHttpApi.aiCreateTask(aiCreateTaskVo).subscribe(new ABaseHttpObserver<AiBaseDto<AiCreateTaskDto>>() {
                @Override
                public void onNext(AiBaseDto<AiCreateTaskDto> aiBaseDto) {
                    if (aiBaseDto == null) {
                        PhotoVModel.this.aiCreateIdLD.postValue(null);
                        return;
                    }
                    if (aiBaseDto.data == null || aiBaseDto.code != 200) {
                        LoggerUtil.m1182e("PhotoVM", "AI生成图片失败 " + aiBaseDto.code + " " + aiBaseDto.msg);
                        PhotoVModel.this.aiCreateIdLD.postValue(null);
                    } else if (aiBaseDto.data.task_id == null) {
                        LoggerUtil.m1182e("PhotoVM", "AI生成图片失败，id为空 ");
                        PhotoVModel.this.aiCreateIdLD.postValue(null);
                    } else {
                        PhotoVModel.this.aiEnhanced(aiBaseDto.data.task_id);
                    }
                }
            });
        }
    }

    public void aiEnhanced(String str) {
        App.mSbAiPath.setLength(0);
        HttpManager.getInstance().mHttpApi.updateAiTaskId(new OrderAiTaskVo(OrderManager.mOrderId, str)).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<AiBaseDto<Object>>() {
            @Override
            public void onNext(AiBaseDto<Object> aiBaseDto) {
                super.onNext(aiBaseDto);
            }
        });
        queryAiTaskInfo(str, new IAiEnhancedListener() {
            @Override
            public void onProgress(int i, int i2) {
            }

            @Override
            public void onResult(int i, String str2) {
                LoggerUtil.m1181d("PhotoVM", "aiEnhanced onResult " + i + " " + str2);
                App.mSbAiPath.append(str2).append(",");
                if (i == 3) {
                    App.mSbAiPath.deleteCharAt(App.mSbAiPath.length() - 1);
                    PhotoVModel.this.aiCreateIdLD.postValue(App.mSbAiPath.toString());
                }
            }

            @Override
            public void onFail(String str2) {
                PhotoVModel.this.aiCreateIdLD.postValue(null);
            }
        });
    }

    public void queryAiTaskInfo(final String str, final IAiEnhancedListener iAiEnhancedListener) {
        HttpManager.getInstance().mHttpApi.aiGetTaskInfo(str).subscribe(new ABaseHttpObserver<AiBaseDto<AiTaskInfoDto>>() {
            @Override
            public void onNext(AiBaseDto<AiTaskInfoDto> aiBaseDto) {
                if (aiBaseDto == null) {
                    LoggerUtil.m1182e("PhotoVM", "aiEnhanced failed null");
                    PhotoVModel.this.aiCreateIdLD.postValue(null);
                    return;
                }
                if (aiBaseDto.data == null || aiBaseDto.code != 200) {
                    LoggerUtil.m1182e("PhotoVM", "aiEnhanced failed " + aiBaseDto.code + " " + aiBaseDto.msg);
                    PhotoVModel.this.aiCreateIdLD.postValue(null);
                    return;
                }
                if (aiBaseDto.data.is_finish) {
                    if (!aiBaseDto.data.success) {
                        PhotoVModel.this.aiCreateIdLD.postValue(null);
                        LoggerUtil.m1182e("PhotoVM", "aiEnhanced failed " + aiBaseDto.data.infer_message);
                        return;
                    }
                    String absolutePath = App.getInstance().getFilesDir().getAbsolutePath();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (int i = 0; i < aiBaseDto.data.images.size(); i++) {
                        LoggerUtil.m1181d("PhotoVM", "aiEnhanced finish " + i + " " + aiBaseDto.data.images.get(i));
                        arrayList.add(aiBaseDto.data.images.get(i));
                        arrayList2.add(absolutePath + MqttTopic.TOPIC_LEVEL_SEPARATOR);
                    }
                    HttpManager.getInstance().downloadAiBitmap(arrayList, arrayList2, iAiEnhancedListener);
                    return;
                }
                iAiEnhancedListener.onProgress(0, 15);
                ThreadClock.sleep(1500L);
                PhotoVModel.this.queryAiTaskInfo(str, iAiEnhancedListener);
            }
        });
    }

    public void saveImage(final Context context, final Bitmap bitmap, final String str, final int i) {
        if (i >= this.imageList.size()) {
            return;
        }
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.m1713lambda$saveImage$2$comwugudoublecameramainvmPhotoVModel(context, str, bitmap, i);
            }
        });
    }

    void m1713lambda$saveImage$2$comwugudoublecameramainvmPhotoVModel(Context context, String str, Bitmap bitmap, int i) throws Throwable {
        boolean zSaveBitmap;
        File file = new File(context.getFilesDir(), str);
        LoggerUtil.m1181d("fragment", "saveImage " + str);
        if (App.mSystemMode == 4) {
            zSaveBitmap = FileUtil.saveBitmap(bitmap, file, 90);
        } else {
            zSaveBitmap = FileUtil.saveBitmap(bitmap, file);
        }
        if (zSaveBitmap) {
            this.imageList.set(i, str);
        } else {
            this.imageList.set(i, "");
        }
        this.saveImageFinishLD.postValue(Integer.valueOf(i));
    }

    public void setFilterIndex(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1159xb8295521(str);
            }
        });
    }

    void m1159xb8295521(String str) {
        FilterEntity filterByName = DbHelper.getInstance().getFilterByName(str);
        if (filterByName == null) {
            return;
        }
        int filterType = filterByName.getFilterType();
        this.beautyItem.filterParams.filterType = filterType;
        if (filterType == 3) {
            this.beautyItem.filterParams.filterName = "origin";
        } else {
            this.beautyItem.filterParams.filterIntensity = Math.min(filterByName.getFilterIntensity() / 100.0f, 1.0f);
            if (this.beautyItem.filterParams.filterType == 2) {
                this.beautyItem.filterParams.animationFilterIndex = StringUtil.strToInt(filterByName.getFilterKey(), 0);
            } else {
                this.beautyItem.filterParams.filterName = filterByName.getFilterKey();
            }
        }
        this.beautyItemLD.postValue(this.beautyItem);
    }

    public void setMakeupIndex(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1160x64ff671(str);
            }
        });
    }

    void m1160x64ff671(String str) {
        MakeupEntity makeupByName = DbHelper.getInstance().getMakeupByName(str);
        if (makeupByName == null) {
            return;
        }
        this.beautyItem.makeupParams.makeupBundlePath = makeupByName.getMakeupBundlePath();
        this.beautyItem.makeupParams.makeupType = makeupByName.getMakeupType();
        this.beautyItem.makeupParams.makeupJsonPath = makeupByName.getMakeupJsonPath();
        this.beautyItem.makeupParams.intensity = Math.min(makeupByName.getMakeupIntensity() / 100.0f, 1.0f);
        this.beautyItemLD.postValue(this.beautyItem);
    }

    public void setBeautyFace(final int i) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1714lambda$setBeautyFace$5$comwugudoublecameramainvmPhotoVModel(i);
            }
        });
    }

    void m1714lambda$setBeautyFace$5$comwugudoublecameramainvmPhotoVModel(int i) {
        getBeautyLevelItem(i);
        this.beautyItemLD.postValue(this.beautyItem);
    }

    public BeautifyItem getBeautyLevelItem(int i) {
        if (DbHelper.getInstance().getBeautyByLevel(i) == null) {
            return this.beautyItem;
        }
        if (TextUtils.isEmpty(this.beautyItem.filterParams.filterName)) {
            this.beautyItem.filterParams.filterName = "origin";
            this.beautyItem.filterParams.filterIntensity = 0.0f;
        }
        this.beautyItem.beautyParams.blur = Math.min(r5.getBlur() / 100.0f, 6.0f);
        this.beautyItem.beautyParams.white = Math.min(r5.getWhite() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.red = Math.min(r5.getRed() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.sharpen = Math.min(r5.getSharpen() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.clarity = Math.min(r5.getClarity() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeBright = Math.min(r5.getEyeBright() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.tooth = Math.min(r5.getTooth() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removePouch = Math.min(r5.getRemovePouch() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removeLawPat = Math.min(r5.getRemoveLawPat() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.removeSpot = Math.min(r5.getRemoveSpot() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.faceThin = Math.min(r5.getFaceThin() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeBig = Math.min(r5.getEyeBig() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.eyeCircle = Math.min(r5.getEyeCircle() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.faceThree = Math.min(r5.getFaceThree() / 100.0f, 1.0f);
        this.beautyItem.beautyParams.noseThin = Math.min(r5.getNose() / 100.0f, 1.0f);
        return this.beautyItem;
    }
}
