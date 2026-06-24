package com.wugu.doublecamera.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import com.google.gson.GsonBuilder;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.CutoutDto;
import com.wugu.doublecamera.listener.IAiEnhancedListener;
import com.wugu.doublecamera.listener.IBitmapListener;
import com.wugu.doublecamera.listener.IDownFileListener;
import com.wugu.doublecamera.network.ProgressResponseBody;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.http.HttpHost;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private final String TAG;
    public IHttpApi mHttpApi;

    private static class InstanceHolder {
        private static final HttpManager instance = new HttpManager();

        private InstanceHolder() {
        }
    }

    private HttpManager() {
        this.TAG = "HttpManager";
    }

    public static HttpManager getInstance() {
        return InstanceHolder.instance;
    }

    public void init() {
        if (this.mHttpApi != null) {
            return;
        }
        OkHttpClient.Builder builderWriteTimeout = new OkHttpClient.Builder().addInterceptor(new CommonParamsInterceptor()).connectTimeout(20L, TimeUnit.SECONDS).readTimeout(90L, TimeUnit.SECONDS).writeTimeout(90L, TimeUnit.SECONDS);
        if (AppUtil.isDebug()) {
            builderWriteTimeout.addInterceptor(httpLoggingInterceptor());
        }
        this.mHttpApi = (IHttpApi) new Retrofit.Builder().baseUrl(AppConfig.HTTP_HOST).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.m1287io())).client(builderWriteTimeout.build()).build().create(IHttpApi.class);
    }

    private HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public final void log(String str) {
                this.f$0.m1174x2e322b97(str);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    void m1174x2e322b97(String str) {
        Log.d("HttpManager", str);
    }

    public void downLoadFile(String str, final String str2, final IDownFileListener iDownFileListener) {
        new OkHttpClient.Builder().writeTimeout(90L, TimeUnit.SECONDS).readTimeout(90L, TimeUnit.SECONDS).addInterceptor(new ProgressResponseInterceptor(new ProgressResponseBody.ProgressListener() {
            @Override
            public final void onProgressChanged(long j, long j2) {
                HttpManager.lambda$downLoadFile$1(iDownFileListener, j, j2);
            }
        })).build().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException iOException) {
                LoggerUtil.m1182e("HttpManager", "down fail " + iOException);
                IDownFileListener iDownFileListener2 = iDownFileListener;
                if (iDownFileListener2 != null) {
                    iDownFileListener2.fail(1);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    IDownFileListener iDownFileListener2 = iDownFileListener;
                    if (iDownFileListener2 != null) {
                        iDownFileListener2.fail(1);
                        return;
                    }
                    return;
                }
                if (response.isSuccessful()) {
                    try {
                        HttpManager.this.saveFile(response.body().byteStream(), str2);
                        IDownFileListener iDownFileListener3 = iDownFileListener;
                        if (iDownFileListener3 != null) {
                            iDownFileListener3.success();
                            return;
                        }
                        return;
                    } catch (IOException e) {
                        Log.e("HttpManager", "save fail " + e);
                        IDownFileListener iDownFileListener4 = iDownFileListener;
                        if (iDownFileListener4 != null) {
                            iDownFileListener4.fail(2);
                            return;
                        }
                        return;
                    }
                }
                Log.e("HttpManager", "onResponse fail " + response);
                IDownFileListener iDownFileListener5 = iDownFileListener;
                if (iDownFileListener5 != null) {
                    iDownFileListener5.fail(2);
                }
            }
        });
    }

    static void lambda$downLoadFile$1(IDownFileListener iDownFileListener, long j, long j2) {
        int i = (int) ((j * 100) / j2);
        if (iDownFileListener != null) {
            iDownFileListener.progress(i);
        }
    }

    public void saveFile(InputStream inputStream, String str) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(FileUtil.createFile(str));
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                fileOutputStream.write(bArr, 0, i);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
                return;
            }
        }
    }

    public void downloadAiBitmap(List<String> list, List<String> list2, final IAiEnhancedListener iAiEnhancedListener) {
        DownloadTask[] downloadTaskArr = new DownloadTask[list.size()];
        for (int i = 0; i < list.size(); i++) {
            downloadTaskArr[i] = new DownloadTask.Builder(list.get(i), list2.get(i), StringUtil.getFileNameFromPath(list.get(i))).build();
        }
        final AtomicInteger atomicInteger = new AtomicInteger(0);
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
                LoggerUtil.m1181d("ai", "downloadAiBitmap finish " + file.getPath());
                if (endCause == EndCause.COMPLETED || (exc instanceof IOException)) {
                    IAiEnhancedListener iAiEnhancedListener2 = iAiEnhancedListener;
                    if (iAiEnhancedListener2 != null) {
                        iAiEnhancedListener2.onResult(atomicInteger.get(), file.getPath());
                    }
                    atomicInteger.incrementAndGet();
                }
            }
        });
    }

    public void uploadCutPicture(boolean z, final File file, final IBitmapListener iBitmapListener) {
        if (file == null || !file.exists()) {
            return;
        }
        LoggerUtil.m1181d(HttpHost.DEFAULT_SCHEME_NAME, "uploadCutPicture " + file.getName() + " useZuoT " + z);
        MultipartBody.Part partCreateFormData = MultipartBody.Part.createFormData("image_file", file.getName(), RequestBody.create(file, MediaType.parse("image/jpeg")));
        if (!z) {
            getInstance().mHttpApi.cutoutPicture2(partCreateFormData, file.getName()).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<ResponseBody>() {
                @Override
                public void onNext(ResponseBody responseBody) {
                    String strString;
                    super.onNext(responseBody);
                    try {
                        strString = responseBody.string();
                    } catch (IOException e) {
                        e.printStackTrace();
                        strString = "";
                    }
                    if (!strString.equals("")) {
                        try {
                            byte[] bArrDecode = Base64.decode(strString, 0);
                            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
                            IBitmapListener iBitmapListener2 = iBitmapListener;
                            if (iBitmapListener2 != null) {
                                iBitmapListener2.onBitmapResult(bitmapDecodeByteArray, 1);
                                return;
                            }
                            return;
                        } catch (Exception unused) {
                            return;
                        }
                    }
                    IBitmapListener iBitmapListener3 = iBitmapListener;
                    if (iBitmapListener3 != null) {
                        iBitmapListener3.onBitmapResult(null, 0);
                    }
                }

                @Override
                public void onError(Throwable th) {
                    LoggerUtil.m1181d(HttpHost.DEFAULT_SCHEME_NAME, "cutoutPicture Error " + th);
                    IBitmapListener iBitmapListener2 = iBitmapListener;
                    if (iBitmapListener2 != null) {
                        iBitmapListener2.onBitmapResult(null, 0);
                    }
                }
            });
        } else {
            LoggerUtil.m1181d(HttpHost.DEFAULT_SCHEME_NAME, "cutoutPicture " + file.getName());
            getInstance().mHttpApi.cutoutPicture(partCreateFormData).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<CutoutDto>() {
                @Override
                public void onNext(CutoutDto cutoutDto) {
                    if (cutoutDto == null) {
                        IBitmapListener iBitmapListener2 = iBitmapListener;
                        if (iBitmapListener2 != null) {
                            iBitmapListener2.onBitmapResult(null, 0);
                            return;
                        }
                        return;
                    }
                    if (cutoutDto.status == 429) {
                        LoggerUtil.m1181d(HttpHost.DEFAULT_SCHEME_NAME, "cutoutPicture 冲突 " + cutoutDto.message);
                        ThreadClock.sleep(500L);
                        HttpManager.this.uploadCutPicture(true, file, iBitmapListener);
                    } else if (cutoutDto.status == 200 && cutoutDto.data.state == 1) {
                        Bitmap base64Bitmap = BitmapUtil.getBase64Bitmap(cutoutDto.data.image);
                        IBitmapListener iBitmapListener3 = iBitmapListener;
                        if (iBitmapListener3 != null) {
                            iBitmapListener3.onBitmapResult(base64Bitmap, 1);
                        }
                    }
                }

                @Override
                public void onError(Throwable th) {
                    LoggerUtil.m1181d(HttpHost.DEFAULT_SCHEME_NAME, "cutoutPicture Error " + th);
                    IBitmapListener iBitmapListener2 = iBitmapListener;
                    if (iBitmapListener2 != null) {
                        iBitmapListener2.onBitmapResult(null, 0);
                    }
                }
            });
        }
    }
}
