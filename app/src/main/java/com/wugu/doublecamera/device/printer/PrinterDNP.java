package com.wugu.doublecamera.device.printer;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IPrinterStatusListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import jp.p036co.dnp.photocolorcnvlib.DNPPhotoColorCnv;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PrinterDNP extends APrinter {
    private String colorParamName;
    private int deviceType;
    private DNPPhotoPrint mPrint;
    private final String TAG = "PrinterDNP";
    private final int PORT_NUM = 0;
    private final int printerSolutionSet = 300;
    private int printerMediaSize = 55;

    @Override
    public int getPrintTime() {
        return 22;
    }

    @Override
    public void printFinish() {
    }

    @Override
    public void init(IPrinterStatusListener iPrinterStatusListener) {
        if (this.mPrint == null) {
            this.mPrint = new DNPPhotoPrint(App.getInstance());
        }
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 4, 2);
        if (this.mPrint.GetPrinterPortNum(iArr) <= 0) {
            if (iPrinterStatusListener != null) {
                iPrinterStatusListener.printerStatus(1, "未发现打印机");
                return;
            }
            return;
        }
        this.deviceType = iArr[0][0];
        LoggerUtil.m1181d("PrinterDNP", "deviceType: " + this.deviceType);
        int i = this.deviceType;
        if (i == 0) {
            if (iPrinterStatusListener != null) {
                iPrinterStatusListener.printerStatus(1, "未发现打印机");
                return;
            }
            return;
        }
        if (i == 20) {
            this.printerMediaSize = 8;
            this.mPrint.SetRewindMode(0, 1);
        }
        this.mPrint.GetFirmwVersion(0, new char[256]);
        int remainSheets = getRemainSheets();
        if (App.mCurrentPrintSheets != remainSheets) {
            updatePrinterPaper(remainSheets, true);
        }
        String printErrorStr = getPrintErrorStr(this.mPrint.GetStatus(0));
        if (!TextUtils.isEmpty(printErrorStr)) {
            LoggerUtil.m1181d("PrinterDNP", "init errorStr: " + printErrorStr);
            if (iPrinterStatusListener != null) {
                iPrinterStatusListener.printerStatus(8, printErrorStr);
                return;
            }
            return;
        }
        if (remainSheets <= 0) {
            if (iPrinterStatusListener != null) {
                iPrinterStatusListener.printerStatus(2, "打印纸缺失");
            }
        } else {
            this.mPrint.SetResolution(0, 300);
            this.mPrint.SetMediaSize(0, this.printerMediaSize);
            this.mPrint.SetOvercoatFinish(0, 0);
            if (iPrinterStatusListener != null) {
                iPrinterStatusListener.printerStatus(3, null);
            }
        }
    }

    public void updatePrinterPaper(final int i, final boolean z) {
        LoggerUtil.m1181d("PrinterDNP", "remaining sheets: " + i);
        HttpManager.getInstance().mHttpApi.updatePrinterPaper(i, -1).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
            @Override
            public void onNext(BaseDto<Object> baseDto) {
                App.mCurrentPrintSheets = i;
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
                if (z) {
                    ThreadClock.sleep(10000L);
                    PrinterDNP.this.updatePrinterPaper(i, false);
                }
            }
        });
    }

    @Override
    public void print(String str, int i, boolean z) {
        byte[] pictureBytes;
        int i2;
        this.mPrint.SetPQTY(0, i);
        this.mPrint.SetCutterMode(0, z ? 120 : 0);
        File file = new File(str);
        if (!file.exists()) {
            LoggerUtil.m1182e("PrinterDNP", "file not exists=");
            return;
        }
        int[] pictureFileWH = FileUtil.getPictureFileWH(file);
        if (pictureFileWH.length != 2) {
            return;
        }
        int[] printSize = getPrintSize(this.printerMediaSize, 300);
        int i3 = printSize[0];
        if (i3 > pictureFileWH[1] && (i2 = printSize[1]) > pictureFileWH[0]) {
            pictureBytes = BitmapUtil.getBitmapBgrData(getClosestBitmap(BitmapUtil.getResizedBitmapFromPath(str, i2, i3)));
        } else {
            pictureBytes = getPictureBytes(file);
        }
        byte[] bArr = pictureBytes;
        byte[] lut = null;
        if (!TextUtils.isEmpty(this.colorParamName) && !this.colorParamName.equals("无")) {
            LoggerUtil.m1181d("PrinterDNP", "dnp color params " + this.colorParamName);
            try {
                FileInputStream fileInputStream = new FileInputStream(AppConfig.PRINTER_PARAM_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.colorParamName);
                try {
                    lut = getLut(fileInputStream);
                    fileInputStream.close();
                } finally {
                }
            } catch (IOException unused) {
            }
            if (lut != null) {
                DNPPhotoColorCnv.ColorConvert(printSize[0], printSize[1], bArr, lut, false);
            }
        } else if (SpManager.getInstance().getDnpDefaultColorParams()) {
            LoggerUtil.m1181d("PrinterDNP", "dnp use default color params");
            try {
                InputStream inputStreamOpen = App.getInstance().getAssets().open("dnp_ft_b.raw");
                try {
                    lut = getLut(inputStreamOpen);
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                } finally {
                }
            } catch (IOException unused2) {
            }
            if (lut != null) {
                DNPPhotoColorCnv.ColorConvert(printSize[0], printSize[1], bArr, lut, false);
            }
        }
        LoggerUtil.m1181d("PrinterDNP", "SendImageData");
        this.mPrint.SendImageData(0, bArr, 0, 0, printSize[0], printSize[1]);
    }

    @Override
    public int getRemainSheets() {
        int iGetMediaCounter;
        DNPPhotoPrint dNPPhotoPrint = this.mPrint;
        if (dNPPhotoPrint == null) {
            return 0;
        }
        if (this.deviceType == 20) {
            iGetMediaCounter = dNPPhotoPrint.GetMediaCounterH(0);
        } else {
            iGetMediaCounter = dNPPhotoPrint.GetMediaCounter(0) - 50;
        }
        LoggerUtil.m1181d("PrinterDNP", "getRemainSheets: " + iGetMediaCounter);
        return Math.max(iGetMediaCounter, 0);
    }

    @Override
    public boolean isPrinting() {
        return this.mPrint.GetStatus(0) == 65538;
    }

    private byte[] getPictureBytes(File file) {
        return BitmapUtil.getBitmapBgrData(getClosestBitmap(BitmapUtil.getLocalBitmap(file)));
    }

    private Bitmap getClosestBitmap(Bitmap bitmap) {
        Bitmap bitmapCreateBitmap;
        int i;
        int[] printSize = getPrintSize(this.printerMediaSize, 300);
        if (bitmap.getHeight() > bitmap.getWidth()) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90.0f);
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
        } else {
            bitmapCreateBitmap = bitmap;
        }
        int height = bitmapCreateBitmap.getHeight();
        int width = bitmapCreateBitmap.getWidth();
        int i2 = printSize[0];
        if (width < i2 || height < (i = printSize[1])) {
            return Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, Math.min(width, i2), Math.min(height, printSize[1]));
        }
        Bitmap scaleMarginBitmap = BitmapUtil.getScaleMarginBitmap(bitmapCreateBitmap, i2, i, 25, 20, 25, 20);
        Matrix matrix2 = new Matrix();
        matrix2.preScale(1.0f, -1.0f);
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(scaleMarginBitmap, 0, 0, scaleMarginBitmap.getWidth(), scaleMarginBitmap.getHeight(), matrix2, false);
        bitmapCreateBitmap.recycle();
        scaleMarginBitmap.recycle();
        return bitmapCreateBitmap2;
    }

    private byte[] getLut(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i >= 0) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private int[] getPrintSize(int i, int i2) {
        int[] iArr = {1844, UiPosIndexEnum.PE_IC_MT_TIPS};
        if (i2 == 300) {
            if (this.deviceType == 35) {
                switch (i) {
                    case 54:
                        iArr[0] = 1266;
                        iArr[1] = 1236;
                        break;
                    case 55:
                        iArr[0] = 1266;
                        iArr[1] = 1836;
                        break;
                    case 57:
                        iArr[0] = 1408;
                        iArr[1] = 1386;
                        break;
                    case 58:
                        iArr[0] = 1408;
                        iArr[1] = 1836;
                        break;
                    case 59:
                        iArr[0] = 1408;
                        iArr[1] = 2436;
                        break;
                }
            } else if (i == 1) {
                iArr[0] = 1548;
                iArr[1] = 1088;
            } else if (i == 2) {
                iArr[0] = 1548;
                iArr[1] = 2138;
            } else if (i == 4) {
                iArr[0] = 1844;
                iArr[1] = 2436;
            } else if (i == 11) {
                iArr[0] = 1844;
                iArr[1] = 1836;
            }
        }
        return iArr;
    }

    @Override
    public void updateColorParam(Object obj) {
        this.colorParamName = (String) obj;
    }

    @Override
    public String getPrinterSerial() {
        char[] cArr;
        int iGetSerialNo;
        DNPPhotoPrint dNPPhotoPrint = this.mPrint;
        if (dNPPhotoPrint != null && (iGetSerialNo = dNPPhotoPrint.GetSerialNo(0, (cArr = new char[256]))) > 0) {
            return new String(cArr, 0, iGetSerialNo);
        }
        return null;
    }

    private String getPrintErrorStr(int i) {
        LoggerUtil.m1181d("PrinterDNP", "getPrintErrorStr: " + i);
        try {
            if ((65536 & i) > 0) {
                switch (i) {
                    case 65537:
                    case 65538:
                        return null;
                    case DNPPhotoPrint.STATUS_USUALLY_PAPER_END:
                        return "缺纸";
                    case DNPPhotoPrint.STATUS_USUALLY_RIBBON_END:
                        return "缺色带";
                    case DNPPhotoPrint.STATUS_USUALLY_COOLING:
                        return "冷却";
                    case DNPPhotoPrint.STATUS_USUALLY_MOTCOOLING:
                        return "电机冷却";
                    default:
                        LoggerUtil.m1181d("PrinterDNP", "unknown status: " + i);
                        break;
                }
            } else {
                if ((131072 & i) > 0) {
                    switch (i) {
                        case 131073:
                            return "盖子打开";
                        case 131074:
                            return "卡纸";
                        case 131076:
                            return "色带异常";
                        case 131080:
                            return "纸张定义错误";
                        case 131088:
                            return "数据异常";
                        case DNPPhotoPrint.STATUS_SETTING_SCRAPBOX_ERR:
                            return "废纸箱异常";
                        default:
                            return "废纸箱异常";
                    }
                }
                if ((262144 & i) > 0) {
                    switch (i) {
                        case 262145:
                            return "打印头电压异常";
                        case 262146:
                            return "打印头位置错误";
                        case 262148:
                            return "风扇异常";
                        case DNPPhotoPrint.STATUS_HARDWARE_ERR04:
                            return "切刀异常";
                        case DNPPhotoPrint.STATUS_HARDWARE_ERR05:
                            return "压紧轮异常";
                        case DNPPhotoPrint.STATUS_HARDWARE_ERR06:
                            return "打印头温度异常";
                        case DNPPhotoPrint.STATUS_HARDWARE_ERR07:
                            return "介质温度异常";
                        case DNPPhotoPrint.STATUS_HARDWARE_ERR08:
                            return "色带张力异常";
                        case DNPPhotoPrint.STATUS_HARDWARE_ERR09:
                            return "RFID模块错误";
                        case DNPPhotoPrint.STATUS_HARDWARE_ERR10:
                            return "电机温度异常";
                        default:
                            return "其它异常";
                    }
                }
                if ((524288 & i) > 0) {
                    return "系统错误";
                }
                if (i < 0) {
                    return "断开";
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
