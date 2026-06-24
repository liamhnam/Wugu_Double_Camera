package com.wugu.doublecamera.service;

import android.text.TextUtils;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.wujieai.AiBaseDto;
import com.wugu.doublecamera.bean.dto.wujieai.AiModelDto;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.dbSheet.FileMd5Entity;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.StringUtil;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class AiModelDownUtil {
    public static void syncAiModels() {
        HttpManager.getInstance().mHttpApi.aiGetMerchant().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<AiBaseDto<Object>>() {
        });
        HttpManager.getInstance().mHttpApi.aiGetModelList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<AiBaseDto<AiModelDto>>() {
            @Override
            public void onNext(AiBaseDto<AiModelDto> aiBaseDto) {
                String filePath;
                if (aiBaseDto != null) {
                    if (aiBaseDto.code != 200 || aiBaseDto.data == null) {
                        LoggerUtil.m1182e("SyncAi", "SyncAiModels fail " + aiBaseDto.data + " " + aiBaseDto.msg);
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    List<FrameEntity> framesByType = DbHelper.getInstance().getFramesByType(1);
                    String str = AppConfig.FRAME_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                    for (AiModelDto.ModelDetail modelDetail : aiBaseDto.data.lists) {
                        String str2 = modelDetail.model_id;
                        if (!TextUtils.isEmpty(str2) && !str2.contains("请勿使用") && !str2.contains("下架模版")) {
                            arrayList4.add(str2);
                            String strValueOf = String.valueOf(modelDetail.type);
                            String strValueOf2 = String.valueOf(modelDetail.type);
                            if (modelDetail.styles != null && !modelDetail.styles.isEmpty()) {
                                strValueOf = modelDetail.styles.get(0).f2447id;
                                strValueOf2 = modelDetail.styles.get(0).name;
                            }
                            String str3 = strValueOf;
                            String str4 = strValueOf2;
                            String str5 = modelDetail.example.get(0);
                            if (!TextUtils.isEmpty(str5)) {
                                String fileNameFromPath = StringUtil.getFileNameFromPath(str5);
                                arrayList3.add(fileNameFromPath);
                                FileMd5Entity fileMd5ByLikeName = DbHelper.getInstance().getFileMd5ByLikeName(fileNameFromPath);
                                if (fileMd5ByLikeName == null) {
                                    if (!arrayList.contains(str5)) {
                                        LoggerUtil.m1181d("picture", "add ai frame " + modelDetail.name + ", " + str5);
                                        arrayList.add(str5);
                                        arrayList2.add(str);
                                    }
                                    filePath = null;
                                } else {
                                    filePath = fileMd5ByLikeName.getFilePath();
                                }
                                DbHelper.getInstance().insertFrame(new FrameEntity(null, str2, modelDetail.name, str5, filePath, str3, 1, 0, 0, 0, 0, 0, 0, 1, false, false, null, str4, null, 0, true));
                            }
                        }
                    }
                    if (framesByType != null && !framesByType.isEmpty()) {
                        for (FrameEntity frameEntity : framesByType) {
                            if (!arrayList4.contains(frameEntity.getFrameKey())) {
                                LoggerUtil.m1181d("picture", "del db ai frame " + frameEntity.getFrameKey() + ", " + frameEntity.getFrameName());
                                DbHelper.getInstance().deleteFrame(frameEntity);
                            }
                        }
                    }
                    AiModelDownUtil.deleteNotUseAiPicture(arrayList3);
                    AiModelDownUtil.startPictureDownload(arrayList, arrayList2);
                }
            }
        });
    }

    public static void deleteNotUseAiPicture(List<String> list) {
        List<FileMd5Entity> fileMd5ByType = DbHelper.getInstance().getFileMd5ByType(9);
        if (fileMd5ByType == null || fileMd5ByType.isEmpty()) {
            return;
        }
        for (FileMd5Entity fileMd5Entity : fileMd5ByType) {
            if (!TextUtils.isEmpty(fileMd5Entity.getFileMD5()) && !list.contains(StringUtil.getFileNameFromPath(fileMd5Entity.getFilePath()))) {
                LoggerUtil.m1181d("picture", "del file " + fileMd5Entity.getFilePath());
                DbHelper.getInstance().deleteFileMd5(fileMd5Entity);
                FileUtil.deleteFileFolder(fileMd5Entity.getFilePath());
            }
        }
    }

    public static void startPictureDownload(List<String> list, List<String> list2) {
        if (list.size() != list2.size() || list.size() == 0) {
            return;
        }
        LoggerUtil.m1181d("download", "startPictureDownload ai " + App.mDownCount.addAndGet(list.size()));
        DownloadTask[] downloadTaskArr = new DownloadTask[list.size()];
        for (int i = 0; i < list.size(); i++) {
            downloadTaskArr[i] = new DownloadTask.Builder(list.get(i), list2.get(i), StringUtil.getFileNameFromPath(list.get(i))).build();
        }
        DownloadTask.enqueue(downloadTaskArr, new DownloadListener2() {
            @Override
            public void taskStart(DownloadTask downloadTask) {
            }

            @Override
            public void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
                File file = downloadTask.getFile();
                if (file == null) {
                    LoggerUtil.m1182e("download", "Down fail url=" + downloadTask.getUrl() + ", cause=" + exc);
                    return;
                }
                if (endCause == EndCause.COMPLETED || (exc instanceof IOException)) {
                    String url = downloadTask.getUrl();
                    String absolutePath = file.getAbsolutePath();
                    String fileMD5 = FileUtil.getFileMD5(file);
                    LoggerUtil.m1181d("download", "taskEnd localPath " + absolutePath + ", cause " + exc);
                    if (!TextUtils.isEmpty(fileMD5)) {
                        fileMD5 = fileMD5.trim().toUpperCase();
                    }
                    FrameEntity frameByNetUrl = DbHelper.getInstance().getFrameByNetUrl(url);
                    if (frameByNetUrl != null) {
                        frameByNetUrl.setFrameLocalPath(absolutePath);
                        DbHelper.getInstance().insertFrame(frameByNetUrl);
                    }
                    DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, absolutePath, fileMD5, 9));
                }
            }
        });
    }
}
