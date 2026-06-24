package com.p020hp.mobile.common.browsing;

import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.p020hp.jipp.model.PrinterServiceType;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/DeviceInfo;", "", "infos", "", "Lcom/hp/mobile/common/browsing/DeviceInfo$Info;", "(Ljava/util/List;)V", "getInfos", "()Ljava/util/List;", "component1", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "", "Companion", "Info", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class DeviceInfo {
    public static final String ACTION_IPP_ONLY = "ippOnly";
    public static final String USB_DESCRIPTOR_MASK = "usbDescriptorMask";
    public static DeviceInfo deviceInfo = null;
    public static final String encDeviceInfo = "7vrdENuKY0zkrZ7X8fKGBPnR+byvoLpW95aK0oQ7FQBbsxBiyM5AEGqoE57E0YymNriK/bEwK2yh2xGJSklp1xedQZ6EU9f576/nrp5dv7Ml8QaX/ramczeB3lP+4xr0t7aFe8GqQJKEnLUIFvmPTx58ApNJpyqwy53I6QJpc0oTz6F5crNUtg/Mo8/LyZUzb7aUV9aHVc0OXYRojL0MoDVKJvj3E1isEWbNnnwvniR76KHn48sbHmkAkEnllqyYd89VdbrqKtPVy61RecfYCjj6JKA6ME1+OBgzguTxPbOFysreA8ajRim1jOzPUClxEH/U11GRKV5XubbtI2E2A7JSnUalE3K+Pwwwe9B0jih043BOBhNpZDcEjz4bNZGiwZH90Cp4I8WlyKzYjUTU+s/mt7xJYYNjUUdR960UdQ9P9HovwE6zDj4w/e90PF+bYqEBqFB57Gzn9/2H04fkalwd85pqnHbKCoAJVu9vEf2reMNgQI3fr4MHVKmhg9R5rDJXcUe9EYke0xSf1/eIAK1L2eRuPYVHfSIo/JTxWNSYv3Fmrjm1opK4dz0HBjdx/eXj26m4AMLxTZfCQcoTPoogfbopL4++ZimxrXd7Th1DWavosDyCDz3ABns7mZAZY/YSoat1pwUPTBipOn9C6wqxRJWgpGsh3jS6FgSptDBumRaWlaIe+n5yzznnzA94wBPgbZPWajSHA8qS7yFsDjDlhT4wFNNwhRb1Ol1GN3rGVyjtApLS8iHiNJXB/DA7ULJqK9eMk79dw5/7kIfDckhWAW6xYNj9trqRZUvOTuYms9/EmzBwnWIKKFyjFhJWY1s5wS+vPEXE0K96rkUEJXFSZoUR87TSkitcRaqYUrm7s9iEidPp8ZPD9cAAJUCRVaRzg9elawfdWVgFpcKxgsfYlYgaS2UxN6lkYxG+SexMtNfSEcCXGM5qkz7H9M4oEIk2dApqLDwsYA7eMtvkM3AhW4xv+wGuo/P9DtrEvhmWTfEj/MMjwO22zgdLPVqn5EqRZWaNvC7gGd/lEhwE1ABziDD6j/ajtdOysHv7lTPYwbu1w79eE7/T+EQiRAcjgP1pseeJmPl7QvYSJ4E7sAJrVbJ0AK2s7alxFoMgkH/yG4HDEkL0rT28HM2j9WNQWE1TfbPRUaVjSVZDoSPcgc7XONLj+EvDvyPiMCzDDG8hfOmqeRSOPj+4yz/y7mMyTrnKqAD+4zwCcjgR9fNZWigvCdKnnIuYO3n2v1sG4URA5A2QRXCPthB+E0FcjxAOTGIuc9hl7F4gTpDQHiJ/Ir6I2MKo1yGzBjmYrdnup0UsbcAG/HprVnuTqFfdFfvlNTIohpNPqCsptF605SPlwPc98b7NdYmWzYka8/vylAVUXshiT8OtxwWIteoIgrqQGc4cfzQBKsh8M8l5bjj5K2u7Oc6Amp96l7uxfXEqnEf5Zgqrma13AII+balQU3hEc0mlIemcWRR/N3vCY77vXeuYpeE6nd73jdpbrbxsr4FlqO/B88IwFfK2SzbqI2RFuOfy1kr+pJOv53jgM2BytYYWW3P57yp/I01PEN5QhLmEbYxEzKDrTnVRzIqhXuZKxoo7SuubB1Gdw9fJwBuGddZ9LYnF0LpwQUjF8ftRb97XB3GsQX+DP8bGvz0G/FlOrRqbaNkAfZFzI5p1M3gPgMVFMKyhdz8R9oWidGBDWCDImba3u5c9AuwqcVMQZhkX+hY9FDQdPP0CUIMTwZMt3WunGBE5Oik7IWE2lsIwHKTSrCONLyTmBF4rd/VU+wM8";

    @SerializedName("infos")
    public final List<Info> infos;

    public static final Companion INSTANCE = new Companion(null);
    public static final Logger log = LoggerKt.logger(LoggerKt.toTag("DeviceInfo"));

    @Metadata(m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\rJ\u0006\u0010\u000e\u001a\u00020\u0007J\r\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/DeviceInfo$Companion;", "", "()V", "ACTION_IPP_ONLY", "", "USB_DESCRIPTOR_MASK", "deviceInfo", "Lcom/hp/mobile/common/browsing/DeviceInfo;", "encDeviceInfo", "log", "Lcom/hp/mobile/common/utils/Logger;", "decryptDeviceInfo", "data", "decryptDeviceInfo$common_lib_release", "get", "requiresProductNumber", "", "requiresProductNumber$common_lib_release", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String decryptDeviceInfo$common_lib_release(String data) {
            Object objM1772constructorimpl;
            Intrinsics.checkNotNullParameter(data, "data");
            if (!(StringsKt.trim((CharSequence) data).toString().length() > 0)) {
                return null;
            }
            try {
                byte[] bytes = "deviceInfoShould".getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes);
                byte[] bytes2 = "deviceInfoShould".getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                SecretKeySpec secretKeySpec = new SecretKeySpec(bytes2, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                Intrinsics.checkNotNullExpressionValue(cipher, "getInstance(\"AES/CBC/PKCS5PADDING\")");
                cipher.init(2, secretKeySpec, ivParameterSpec);
                byte[] bArrDoFinal = cipher.doFinal(Base64.decode(data, 0));
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "cipher.doFinal(Base64.de…de(data, Base64.DEFAULT))");
                objM1772constructorimpl = Result.m1772constructorimpl(new String(bArrDoFinal, Charsets.UTF_8));
            } catch (Throwable th) {
                objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
            if (thM1775exceptionOrNullimpl != null) {
                DeviceInfo.log.m447e("Failed to read DeviceInfo " + thM1775exceptionOrNullimpl.getLocalizedMessage());
            }
            return (String) (Result.m1775exceptionOrNullimpl(objM1772constructorimpl) == null ? objM1772constructorimpl : null);
        }

        public final DeviceInfo get() {
            Object objM1772constructorimpl;
            DeviceInfo deviceInfo = DeviceInfo.deviceInfo;
            if (deviceInfo != null) {
                return deviceInfo;
            }
            try {
                String strDecryptDeviceInfo$common_lib_release = DeviceInfo.INSTANCE.decryptDeviceInfo$common_lib_release(DeviceInfo.encDeviceInfo);
                DeviceInfo deviceInfo2 = strDecryptDeviceInfo$common_lib_release != null ? (DeviceInfo) new Gson().fromJson(strDecryptDeviceInfo$common_lib_release, DeviceInfo.class) : null;
                if (deviceInfo2 == null) {
                    deviceInfo2 = new DeviceInfo(CollectionsKt.emptyList());
                }
                objM1772constructorimpl = Result.m1772constructorimpl(deviceInfo2);
            } catch (Throwable th) {
                objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
            if (thM1775exceptionOrNullimpl != null) {
                DeviceInfo.log.m447e("Failed to read DeviceInfo " + thM1775exceptionOrNullimpl.getLocalizedMessage());
            }
            if (Result.m1775exceptionOrNullimpl(objM1772constructorimpl) != null) {
                objM1772constructorimpl = new DeviceInfo(CollectionsKt.emptyList());
            }
            DeviceInfo deviceInfo3 = (DeviceInfo) objM1772constructorimpl;
            Companion companion = DeviceInfo.INSTANCE;
            DeviceInfo.deviceInfo = deviceInfo3;
            return deviceInfo3;
        }

        public final boolean requiresProductNumber$common_lib_release() {
            Object next;
            Iterator<T> it = get().getInfos().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (!StringsKt.isBlank(StringsKt.trim((CharSequence) ((Info) next).getProductNumber()).toString())) {
                    break;
                }
            }
            return next != null;
        }
    }

    @Metadata(m1292d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\tHÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003Je\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006'"}, m1293d2 = {"Lcom/hp/mobile/common/browsing/DeviceInfo$Info;", "", "level", "", "makeAndModel", "", "mProductName", "productNumber", "services", "", "action", "data", "extra", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getAction", "()Ljava/util/List;", "getData", "()Ljava/lang/String;", "getExtra", "getLevel", "()I", "getMProductName", "getMakeAndModel", "getProductNumber", "getServices", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Info {

        @SerializedName("action")
        public final List<String> action;

        @SerializedName("data")
        public final String data;

        @SerializedName("extra")
        public final String extra;

        @SerializedName("level")
        public final int level;

        @SerializedName("mProductName")
        public final String mProductName;

        @SerializedName("makeAndModel")
        public final String makeAndModel;

        @SerializedName("productNumber")
        public final String productNumber;

        @SerializedName("services")
        public final List<String> services;

        public Info(int i, String makeAndModel, String mProductName, String productNumber, List<String> services, List<String> action, String data, String extra) {
            Intrinsics.checkNotNullParameter(makeAndModel, "makeAndModel");
            Intrinsics.checkNotNullParameter(mProductName, "mProductName");
            Intrinsics.checkNotNullParameter(productNumber, "productNumber");
            Intrinsics.checkNotNullParameter(services, "services");
            Intrinsics.checkNotNullParameter(action, "action");
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(extra, "extra");
            this.level = i;
            this.makeAndModel = makeAndModel;
            this.mProductName = mProductName;
            this.productNumber = productNumber;
            this.services = services;
            this.action = action;
            this.data = data;
            this.extra = extra;
        }

        public Info(int i, String str, String str2, String str3, List list, List list2, String str4, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 1 : i, str, str2, str3, list, list2, str4, str5);
        }

        public final int getLevel() {
            return this.level;
        }

        public final String getMakeAndModel() {
            return this.makeAndModel;
        }

        public final String getMProductName() {
            return this.mProductName;
        }

        public final String getProductNumber() {
            return this.productNumber;
        }

        public final List<String> component5() {
            return this.services;
        }

        public final List<String> component6() {
            return this.action;
        }

        public final String getData() {
            return this.data;
        }

        public final String getExtra() {
            return this.extra;
        }

        public final Info copy(int level, String makeAndModel, String mProductName, String productNumber, List<String> services, List<String> action, String data, String extra) {
            Intrinsics.checkNotNullParameter(makeAndModel, "makeAndModel");
            Intrinsics.checkNotNullParameter(mProductName, "mProductName");
            Intrinsics.checkNotNullParameter(productNumber, "productNumber");
            Intrinsics.checkNotNullParameter(services, "services");
            Intrinsics.checkNotNullParameter(action, "action");
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(extra, "extra");
            return new Info(level, makeAndModel, mProductName, productNumber, services, action, data, extra);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Info)) {
                return false;
            }
            Info info = (Info) other;
            return this.level == info.level && Intrinsics.areEqual(this.makeAndModel, info.makeAndModel) && Intrinsics.areEqual(this.mProductName, info.mProductName) && Intrinsics.areEqual(this.productNumber, info.productNumber) && Intrinsics.areEqual(this.services, info.services) && Intrinsics.areEqual(this.action, info.action) && Intrinsics.areEqual(this.data, info.data) && Intrinsics.areEqual(this.extra, info.extra);
        }

        public final List<String> getAction() {
            return this.action;
        }

        public final String getData() {
            return this.data;
        }

        public final String getExtra() {
            return this.extra;
        }

        public final int getLevel() {
            return this.level;
        }

        public final String getMProductName() {
            return this.mProductName;
        }

        public final String getMakeAndModel() {
            return this.makeAndModel;
        }

        public final String getProductNumber() {
            return this.productNumber;
        }

        public final List<String> getServices() {
            return this.services;
        }

        public int hashCode() {
            return (((((((((((((Integer.hashCode(this.level) * 31) + this.makeAndModel.hashCode()) * 31) + this.mProductName.hashCode()) * 31) + this.productNumber.hashCode()) * 31) + this.services.hashCode()) * 31) + this.action.hashCode()) * 31) + this.data.hashCode()) * 31) + this.extra.hashCode();
        }

        public String toString() {
            return "Info(level=" + this.level + ", makeAndModel=" + this.makeAndModel + ", mProductName=" + this.mProductName + ", productNumber=" + this.productNumber + ", services=" + this.services + ", action=" + this.action + ", data=" + this.data + ", extra=" + this.extra + ')';
        }
    }

    public DeviceInfo(List<Info> infos) {
        Intrinsics.checkNotNullParameter(infos, "infos");
        this.infos = infos;
    }

    public static DeviceInfo copy$default(DeviceInfo deviceInfo2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = deviceInfo2.infos;
        }
        return deviceInfo2.copy(list);
    }

    public final List<Info> component1() {
        return this.infos;
    }

    public final DeviceInfo copy(List<Info> infos) {
        Intrinsics.checkNotNullParameter(infos, "infos");
        return new DeviceInfo(infos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DeviceInfo) && Intrinsics.areEqual(this.infos, ((DeviceInfo) other).infos);
    }

    public final List<Info> getInfos() {
        return this.infos;
    }

    public int hashCode() {
        return this.infos.hashCode();
    }

    public String toString() {
        return "DeviceInfo(infos=" + this.infos + ')';
    }
}
