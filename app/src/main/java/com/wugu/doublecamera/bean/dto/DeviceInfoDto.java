package com.wugu.doublecamera.bean.dto;

import java.util.Arrays;
import java.util.List;

public class DeviceInfoDto {
    public List<BeautyParams> BeautyParam;
    public CameraSettings CameraSetting;
    public CountdownSettings CountdownSetting;
    public PaymentMethod PaymentMethod;
    public SoundSettings SoundSetting;

    public static class PaymentMethod {
        public int Alipay;
        public int Cash;
        public int Coin;
        public int Credit;
        public int Discountcode;
        public int DouYin;
        public int GameCoin;
        public int MeiTuan;
        public int Wechat;
        public int XieCheng;
    }

    public String toString() {
        return this.CountdownSetting.toString() + "\n" + this.CameraSetting.toString() + "\n" + this.SoundSetting.toString();
    }

    public static class BeautyParams {
        public int Blur;
        public int Level;
        public int White;
        public int eyeBig;
        public int eyeBright;
        public int eyeCircle;
        public int faceThin;
        public int tooth;

        public String toString() {
            return "Level:" + this.Level + ".p = " + this.Blur + "," + this.White + "," + this.eyeBright + "," + this.tooth + "," + this.faceThin + "," + this.eyeBig + "," + this.eyeCircle;
        }
    }

    public static class CountdownSettings {
        public int AdminTime;
        public int BurstSelectTime;
        public int ChooseFrameTime;
        public int ModelselectTime;
        public int PaymentTime;
        public int PhotoConfirmTime;
        public int PhotoPreviewTime;
        public int PhotoReadyTime;
        public int PrintStartTime;
        public int ReplaceBgTime;
        public int UploadPrintTime;

        public String toString() {
            return "ModelSelectTime: " + this.ModelselectTime + ", ChooseFrameTime: " + this.ChooseFrameTime + ", UploadPrintTime: " + this.UploadPrintTime + ", PaymentTime: " + this.PaymentTime + ", PhotoPreviewTime: " + this.PhotoPreviewTime + ", PhotoReadyTime: " + this.PhotoReadyTime + ", PhotoConfirmTime: " + this.PhotoConfirmTime + ", BurstSelectTime: " + this.BurstSelectTime + ", PrintStartTime: " + this.PrintStartTime + ", ReplaceBgTime: " + this.ReplaceBgTime + ", AdminTime: " + this.AdminTime;
        }
    }

    public static class CameraSettings {
        public float AiAddPrice;
        public float AiPrice;
        public int AppModel;
        public int CameraExposure;
        public int CameraZoom;
        public String[] CashBaseValue;
        public int CoinBaseValue;
        public float HeadModelAddPrice;
        public float HeadModelPrice;
        public float IdModelAddPrice;
        public float IdModelPrice;
        public boolean IsMirror;
        public String MachineName;
        public String Phone;
        public int PhotoModel;
        public boolean PosterEasyCode;
        public int PrintMaxCount;
        public String PrinterModel;
        public float ReplaceBgAddPrice;
        public float ReplaceBgPrice;
        public int RetakeCount;
        public String SCoinBaseValue;
        public float UploadPrintAddPrice;
        public float UploadPrintPrice;

        public String toString() {
            return "Phone: " + this.Phone + ", MachineName: " + this.MachineName + ", PrinterModel: " + this.PrinterModel + ", PhotoModel: " + this.PhotoModel + ", CashBaseValue: " + Arrays.toString(this.CashBaseValue) + ", CoinBaseValue: " + this.CoinBaseValue + ", SCoinBaseValue: " + this.SCoinBaseValue + ", RetakeCount: " + this.RetakeCount + ", IsMirror: " + this.IsMirror + ", CameraExposure: " + this.CameraExposure + ", CameraZoom: " + this.CameraZoom;
        }
    }

    public static class SoundSettings {
        public List<String> HomeBgmUrl;
        public String SoundChooseFrame;
        public String SoundClickButtonUrl;
        public String SoundLookCamera;
        public String SoundPayPrint;
        public String SoundPayment;
        public String SoundPeChangeFrame;
        public String SoundPeRetake;
        public String SoundPeUseCode;
        public String SoundPhotoCountdownUrl;
        public String SoundPhotoUrl;
        public String SoundPrePrint;
        public String SoundPreview;
        public String SoundPrintFinish;
        public String SoundSelectFilterUrl;
        public String SoundSelectFrameUrl;
        public String SoundSelectThemeUrl;
        public String SoundStartPrint;

        public String toString() {
            return "HomeBgmUrl: [" + DeviceInfoDto$SoundSettings$$ExternalSyntheticBackport0.m836m(", ", this.HomeBgmUrl) + "], SoundClickButtonUrl: " + this.SoundClickButtonUrl + ", SoundSelectFrameUrl: " + this.SoundSelectFrameUrl + ", SoundSelectThemeUrl: " + this.SoundSelectThemeUrl + ", SoundSelectFilterUrl: " + this.SoundSelectFilterUrl + ", SoundPhotoUrl: " + this.SoundPhotoUrl + ", SoundPhotoCountdownUrl: " + this.SoundPhotoCountdownUrl + ", SoundChooseFrame: " + this.SoundChooseFrame + ", SoundPayment: " + this.SoundPayment + ", SoundPrintFinish: " + this.SoundPrintFinish + ", SoundPrePrint: " + this.SoundPrePrint + ", SoundStartPrint: " + this.SoundStartPrint + ", SoundLookCamera: " + this.SoundLookCamera + ", SoundPreview: " + this.SoundPreview + ", SoundPeUseCode: " + this.SoundPeUseCode + ", SoundPeChangeFrame: " + this.SoundPeChangeFrame + ", SoundPeRetake: " + this.SoundPeRetake + ", SoundPayPrint: " + this.SoundPayPrint;
        }
    }
}
