package com.wugu.doublecamera.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.Gson;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.OrderFilesQrcode;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.bean.p023vo.OrderFilesVo;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ThreadClock;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class OrderFileUtil {
    public static String getOrderFileUrl() {
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit = OrderManager.mOrderFileNames.split(";");
        if (strArrSplit.length > 0) {
            for (String str : strArrSplit) {
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str.endsWith(".mp4") ? "order/videos/" : "order/images/").append(str).append(";");
                }
            }
            if (sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    public static void updateOrderFileUrl(final OrderFilesVo orderFilesVo, final boolean z) {
        LoggerUtil.m1181d("order", "updateOrderFileUrl filesVo " + orderFilesVo.orderId + ", " + orderFilesVo.url);
        HttpManager.getInstance().mHttpApi.updateOrderFileUrl(orderFilesVo).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
            @Override
            public void onNext(BaseDto<Object> baseDto) {
                LoggerUtil.m1181d("order", "updateOrderFileUrl onNext " + baseDto.errCode + ", " + baseDto.errMsg);
            }

            @Override
            public void onError(Throwable th) {
                LoggerUtil.m1182e("order", "updateOrderFileUrl error " + th.getMessage());
                ThreadClock.sleep(1000L);
                if (z) {
                    OrderFileUtil.updateOrderFileUrl(orderFilesVo, false);
                }
            }
        });
    }

    public static void uploadOrderBitmap(Bitmap bitmap, String str, IIntListener iIntListener) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        LoggerUtil.m1181d("order", "uploadOrderBitmap " + str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ArrayList arrayList = new ArrayList();
        arrayList.add(MultipartBody.Part.createFormData("files", str, RequestBody.create(byteArray, MediaType.parse("multipart/form-data"))));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(str);
        uploadOrderFiles(arrayList, arrayList2, iIntListener);
    }

    public static void uploadOrderFilePaths(List<String> list, IIntListener iIntListener) {
        ArrayList arrayList = new ArrayList();
        LoggerUtil.m1181d("order", "uploadOrderFilePaths " + list.size());
        for (String str : list) {
            File file = new File(str);
            LoggerUtil.m1181d("order", "uploadOrderFilePaths " + str + "  exists=" + file.exists());
            if (file.exists()) {
                arrayList.add(MultipartBody.Part.createFormData("files", file.getName(), RequestBody.create(file, MediaType.parse("multipart/form-data"))));
            }
        }
        uploadOrderFiles(arrayList, list, iIntListener);
    }

    private static void uploadOrderFiles(List<MultipartBody.Part> list, final List<String> list2, final IIntListener iIntListener) {
        if (list == null || list.isEmpty()) {
            return;
        }
        HttpManager.getInstance().mHttpApi.uploadOrderFile(list).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<List<String>>>() {
            @Override
            public void onNext(BaseDto<List<String>> baseDto) {
                if (baseDto != null && baseDto.errCode == 0 && baseDto.data != null) {
                    LoggerUtil.m1181d("upload", "ok ");
                    IIntListener iIntListener2 = iIntListener;
                    if (iIntListener2 != null) {
                        iIntListener2.setValue(1);
                        return;
                    }
                    return;
                }
                if (baseDto != null) {
                    LoggerUtil.m1182e("upload", "fail " + baseDto.errCode + ", " + baseDto.errMsg);
                }
                IIntListener iIntListener3 = iIntListener;
                if (iIntListener3 != null) {
                    iIntListener3.setValue(2);
                }
                OrderManager.addFailedFiles(list2);
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
                LoggerUtil.m1182e("upload", "onError " + th);
                IIntListener iIntListener2 = iIntListener;
                if (iIntListener2 != null) {
                    iIntListener2.setValue(2);
                }
                OrderManager.addFailedFiles(list2);
            }
        });
    }

    public static void appendOrderFileNames(List<String> list) {
        if (list == null) {
            return;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                OrderManager.mOrderFileNames += str + ";";
                LoggerUtil.m1181d("order", "appendOrderFileNames " + str);
            }
        }
    }

    public static void appendOrderFileName(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        OrderManager.mOrderFileNames += str + ";";
        LoggerUtil.m1181d("order", "appendOrderFileName " + str);
    }

    public static String getOrderFileQrCodeStr() {
        String[] strArrSplit = OrderManager.mOrderFileNames.split(";");
        String str = null;
        if (strArrSplit.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String str2 = null;
        for (String str3 : strArrSplit) {
            if (!TextUtils.isEmpty(str3)) {
                if (str3.startsWith(TypedValues.AttributesType.S_FRAME) && !str3.endsWith(".mp4")) {
                    str = "order/images/" + str3;
                } else if (!str3.endsWith(".mp4")) {
                    arrayList.add("order/images/" + str3);
                } else {
                    str2 = "order/videos/" + str3;
                }
            }
        }
        String str4 = "http://uspic.pipiphoto.com#/pages/Order/OrderResult/OrderResult?detail=" + new Gson().toJson(new OrderFilesQrcode(AppConfig.HTTP_HOST + "order", arrayList, str, str2));
        LoggerUtil.m1181d("order", "sampleUrl " + str4);
        return !TextUtils.isEmpty(OrderManager.mOrderId) ? AppConfig.PHOTO_PRE_URL + OrderManager.mOrderId : str4;
    }

    public static String getOrderArCodeStr() {
        if (TextUtils.isEmpty(OrderManager.mOrderId)) {
            return null;
        }
        return "https://piceapi.pipiphoto.com/ar?id=" + OrderManager.mOrderId;
    }

    public static void setOrderInfo(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        if (TextUtils.isEmpty(SpManager.getInstance().getCurrentOrderFrame())) {
            SpManager.getInstance().setCurrentOrderFrame(str, str2, str3);
            LoggerUtil.m1181d("order", "setCurrentOrderFrame " + str2 + " " + str3);
        } else {
            LoggerUtil.m1181d("order", "setCurrentOrderFrame null");
            SpManager.getInstance().setCurrentOrderFrame(null, null, null);
        }
    }
}
