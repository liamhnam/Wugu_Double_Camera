package com.wugu.doublecamera.network;

import com.wugu.doublecamera.bean.dto.AllResDto;
import com.wugu.doublecamera.bean.dto.ApkPatchDto;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.bean.dto.CutoutDto;
import com.wugu.doublecamera.bean.dto.DeviceAllConfigDto;
import com.wugu.doublecamera.bean.dto.MqttInfoDto;
import com.wugu.doublecamera.bean.dto.OrderDto;
import com.wugu.doublecamera.bean.dto.OrderStatusDto;
import com.wugu.doublecamera.bean.dto.RedeemCodeDto;
import com.wugu.doublecamera.bean.dto.SettingInfoDto;
import com.wugu.doublecamera.bean.dto.wujieai.AiBaseDto;
import com.wugu.doublecamera.bean.dto.wujieai.AiCreateTaskDto;
import com.wugu.doublecamera.bean.dto.wujieai.AiModelDto;
import com.wugu.doublecamera.bean.dto.wujieai.AiTaskInfoDto;
import com.wugu.doublecamera.bean.p023vo.AiCreateTaskVo;
import com.wugu.doublecamera.bean.p023vo.CreateOrderVo;
import com.wugu.doublecamera.bean.p023vo.OrderAiTaskVo;
import com.wugu.doublecamera.bean.p023vo.OrderCompensateVo;
import com.wugu.doublecamera.bean.p023vo.OrderFilesVo;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IHttpApi {
    @Headers({"X-Merchant-ID: wuguai", "X-Merchant-Secret: 2QYZr5GBaL4T", "Content-Type: application/json"})
    @POST("http://api.mirpop.top/ext/infer")
    Observable<AiBaseDto<AiCreateTaskDto>> aiCreateTask(@Body AiCreateTaskVo aiCreateTaskVo);

    @Headers({"X-Merchant-ID: wuguai", "X-Merchant-Secret: 2QYZr5GBaL4T", "Content-Type: application/json"})
    @GET("http://api.mirpop.top/ext/infer/merchant")
    Observable<AiBaseDto<Object>> aiGetMerchant();

    @Headers({"X-Merchant-ID: wuguai", "X-Merchant-Secret: 2QYZr5GBaL4T", "Content-Type: application/json"})
    @GET("http://api.mirpop.top/ext/infer/models?page=1&size=100")
    Observable<AiBaseDto<AiModelDto>> aiGetModelList();

    @Headers({"X-Merchant-ID: wuguai", "X-Merchant-Secret: 2QYZr5GBaL4T", "Content-Type: application/json"})
    @GET("http://api.mirpop.top/ext/infer/task/{task_id}")
    Observable<AiBaseDto<AiTaskInfoDto>> aiGetTaskInfo(@Path("task_id") String str);

    @GET("api/v1/Device/autoRegister")
    Observable<BaseDto<Object>> autoRegisterDevice(@Query("newSn") String str, @Query("cpSn") String str2, @Query("key") String str3);

    @POST("api/v1/Orders/VerifyPaymentV1")
    Observable<BaseDto<Object>> cashPayOrder(@Query("OrderId") String str, @Query("RealAmount") float f, @Query("PaymentType") int i);

    @POST("api/v1/Orders/createOrder")
    Observable<BaseDto<OrderDto>> createOrder(@Body CreateOrderVo createOrderVo);

    @Headers({"X-API-KEY: wx79ltwtx2a1oxlxk"})
    @POST("https://techsz.aoscdn.com/api/tasks/visual/segmentation?sync=1&return_type=2")
    @Multipart
    Observable<CutoutDto> cutoutPicture(@Part MultipartBody.Part part);

    @POST("http://yx.fongtop.com/Machine/Matting.aspx?action=1")
    @Multipart
    Observable<ResponseBody> cutoutPicture2(@Part MultipartBody.Part part, @Query("id") String str);

    @GET("api/v1/Orders/finishedOrder")
    Observable<BaseDto<OrderDto>> finishOrder(@Query("OrderId") String str);

    @GET("api/v1/Device/getConfig")
    Observable<BaseDto<DeviceAllConfigDto>> getDeviceAllConfig();

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=8")
    Observable<BaseDto<AllResDto>> getEffectList();

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=6")
    Observable<BaseDto<AllResDto>> getFilterList();

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=0")
    Observable<BaseDto<AllResDto>> getFrameList();

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=7")
    Observable<BaseDto<AllResDto>> getMakeupList();

    @GET("api/v1/Device/getSubscribe")
    Observable<BaseDto<MqttInfoDto>> getMqttInfo();

    @GET("api/v1/Device/getVersions")
    Observable<BaseDto<List<ApkPatchDto>>> getNewestBsPatchList(@Query("version") int i);

    @GET("api/v1/Orders/getOrder")
    Observable<BaseDto<OrderStatusDto>> getOrderInfo(@Query("OrderId") String str);

    @POST("api/v1/Orders/useCoupon")
    Observable<BaseDto<RedeemCodeDto>> getRedeemCode(@Query("code") String str);

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=10")
    Observable<BaseDto<AllResDto>> getReplaceBgList();

    @GET("api/v1/Device/getDeviceInfo")
    Observable<BaseDto<SettingInfoDto>> getSettingInfo();

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=9")
    Observable<BaseDto<AllResDto>> getStickerFolderList();

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=2")
    Observable<BaseDto<AllResDto>> getStickerList();

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=1")
    Observable<BaseDto<AllResDto>> getThemeBgList();

    @GET("api/v1/Resource/getResourceEasy?PageIndex=1&PageSize=150&Type=5")
    Observable<BaseDto<AllResDto>> getThemeBtnList();

    @POST("api/v1/Orders/orderCompensate")
    Observable<BaseDto<Object>> orderCompensate(@Body OrderCompensateVo orderCompensateVo);

    @GET("api/v1/Orders/open/coupon/use")
    Observable<BaseDto<Object>> queryMtDyCode(@Query("code") String str, @Query("channel") String str2);

    @GET("api/v1/xc/coupon/use")
    Observable<BaseDto<RedeemCodeDto>> queryXieChengCode(@Query("code") String str);

    @GET("api/v1/Device/register")
    Observable<BaseDto<Object>> register();

    @POST("api/v1/Orders/updateTaskId")
    Observable<AiBaseDto<Object>> updateAiTaskId(@Body OrderAiTaskVo orderAiTaskVo);

    @POST("api/v1/Orders/updateSample")
    Observable<BaseDto<Object>> updateOrderFileUrl(@Body OrderFilesVo orderFilesVo);

    @PUT("api/v1/Device/updatePhotoPaper")
    Observable<BaseDto<Object>> updatePrinterPaper(@Query("printerPaper") int i, @Query("buyPrinterPaper") int i2);

    @GET("api/v1/Device/appilyVersion")
    Observable<BaseDto<Object>> uploadApkVersion(@Query("version") int i);

    @POST("api/v1/File/uploadLogs")
    @Multipart
    Observable<BaseDto<Object>> uploadLogs(@Part MultipartBody.Part part);

    @POST("api/v1/File/uploadFile")
    @Multipart
    Observable<BaseDto<List<String>>> uploadOrderFile(@Part List<MultipartBody.Part> list);
}
