package com.wugu.facebeauty;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import com.faceunity.core.callback.OperateCallback;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.utils.FULogger;
import com.wugu.facebeauty.bean.BeautifyItem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FaceBeautyMain {

    public interface RegCallback {
        void onResult(boolean z);
    }

    public static void registerFURender(Context context, final RegCallback regCallback) {
        FURenderManager.setKitDebug(FULogger.LogLevel.OFF);
        FURenderManager.setCoreDebug(FULogger.LogLevel.OFF);
        FURenderManager.registerFURender(context, authpack.m1184A(), new OperateCallback() {
            @Override
            public void onSuccess(int i, String str) {
                RegCallback regCallback2 = regCallback;
                if (regCallback2 != null) {
                    regCallback2.onResult(true);
                }
            }

            @Override
            public void onFail(int i, String str) {
                RegCallback regCallback2 = regCallback;
                if (regCallback2 != null) {
                    regCallback2.onResult(false);
                }
            }
        });
    }

    public static void writeCrashLog(String str) {
        File file = new File("/mnt/sdcard/WgDCamera/crash");
        if (!file.exists()) {
            file.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String str2 = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        String str3 = simpleDateFormat2.format(new Date(System.currentTimeMillis())) + " >> ";
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath() + File.separator + "beauty_" + str2 + ".log", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.print(str3);
            printWriter.println(str);
            printWriter.flush();
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception unused) {
        }
    }

    public static SurfaceRender preInit(Application application, GLSurfaceView gLSurfaceView) {
        BeautifyItem beautifyItem = new BeautifyItem();
        beautifyItem.filterParams.filterName = "origin";
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(application.getResources(), C2052R.mipmap.demo);
        if (bitmapDecodeResource == null) {
            return null;
        }
        gLSurfaceView.setVisibility(0);
        return new SurfaceRender(gLSurfaceView, bitmapDecodeResource, beautifyItem, true, 4, null, null, null);
    }
}
