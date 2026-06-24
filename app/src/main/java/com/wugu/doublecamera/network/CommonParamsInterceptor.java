package com.wugu.doublecamera.network;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.utils.AesUtil;
import com.wugu.doublecamera.utils.StringUtil;
import java.io.IOException;
import java.util.Date;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        HttpUrl httpUrlBuild;
        Request request = chain.request();
        String currentTimestamp = getCurrentTimestamp();
        String encodeSN = getEncodeSN(currentTimestamp);
        String upperCase = StringUtil.getMd5(encodeSN + currentTimestamp + AppConfig.MACHINE_KEY).toUpperCase();
        if (request.url().encodedPath().contains("/api/v1/File/uploadFile")) {
            httpUrlBuild = request.url().newBuilder().addQueryParameter("timestamp", currentTimestamp).addQueryParameter("sign", getUploadMd5(currentTimestamp)).addQueryParameter("type", ExifInterface.GPS_MEASUREMENT_3D).build();
        } else if (request.url().encodedPath().contains("/api/v1/Device/autoRegister") || request.url().encodedPath().contains("/api.mirpop.top/")) {
            httpUrlBuild = request.url().newBuilder().build();
        } else {
            httpUrlBuild = request.url().newBuilder().addQueryParameter("sn", encodeSN).addQueryParameter("timestamp", currentTimestamp).addQueryParameter("hash", upperCase).build();
        }
        return chain.proceed(request.newBuilder().url(httpUrlBuild).build());
    }

    private String getUploadMd5(String str) {
        String str2 = App.mUploadKey;
        if (TextUtils.isEmpty(str2)) {
            str2 = "~1ikK>u&h;q6)zak2p*g5k<@j@@]G%};";
        }
        return StringUtil.getMd5("UPLOADFILE__" + str + "$$" + str2 + "__UPLOADFILE").toUpperCase();
    }

    private String getCurrentTimestamp() {
        return String.valueOf(new Date().getTime() / 1000);
    }

    private String getEncodeSN(String str) {
        String strEncrypt = AesUtil.encrypt(App.mCPUSerial + "@" + str, AppConfig.MACHINE_KEY);
        if (TextUtils.isEmpty(strEncrypt)) {
            return null;
        }
        return strEncrypt.replace('+', '_').replace('/', '~');
    }
}
