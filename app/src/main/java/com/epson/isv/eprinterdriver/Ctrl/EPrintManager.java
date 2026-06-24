package com.epson.isv.eprinterdriver.Ctrl;

import android.content.Context;
import android.content.Intent;
import com.epson.isv.eprinterdriver.Common.EpsCapability;
import com.epson.isv.eprinterdriver.Common.EpsInkInfo;
import com.epson.isv.eprinterdriver.Common.EpsPrinter;
import com.epson.isv.eprinterdriver.Common.EpsProbe;
import com.epson.isv.eprinterdriver.Common.EpsProtocol;
import com.epson.isv.eprinterdriver.Common.EpsStatus;
import com.epson.isv.eprinterdriver.Common.ServiceDefine;

public class EPrintManager implements IEPrintStateListener {
    public static final int Error_PrintBusy = -100001;
    protected static final int PATTERN_HORIZONTAL_ALIGNMENT = 1;
    protected static final int PATTERN_VERTICAL_ALIGNMENT = 0;
    private static EPrintManager mEPntMgr;
    private EPrintStateReceiver mEPStateRev;
    private ISearchPrinterListener mSearchListener = null;
    private IPrintListener mPrintListener = null;
    private String mSearchPrinterName = null;
    private boolean mAutoRotation = true;
    private boolean mAutoScaling = true;
    boolean mSearchTargetPrinterOnly = false;
    private boolean mBPrintBusy = false;

    public enum EPRINT_FILETYPE {
        BMP,
        JPEG
    }

    private EPrintManager() {
        EPrintStateReceiver ePrintStateReceiver = new EPrintStateReceiver();
        this.mEPStateRev = ePrintStateReceiver;
        ePrintStateReceiver.addStateListener(this);
    }

    public static EPrintManager instance() {
        if (mEPntMgr == null) {
            mEPntMgr = new EPrintManager();
        }
        return mEPntMgr;
    }

    public void addSearchListener(ISearchPrinterListener iSearchPrinterListener) {
        this.mSearchListener = iSearchPrinterListener;
    }

    public void addPrintListener(IPrintListener iPrintListener) {
        this.mPrintListener = iPrintListener;
    }

    public int initEscprLib(Context context) {
        this.mEPStateRev.registerReceiver(context);
        return EPrintService.init(context);
    }

    public int releaseEscprLib(Context context) {
        this.mEPStateRev.unRegisterReceiver(context);
        return EPrintService.release();
    }

    public void setUsbPacketSize(int i) {
        UsbPrinter.setManualPacketSize(i);
    }

    public int findPrinter(String str, int i) {
        this.mSearchTargetPrinterOnly = true;
        this.mSearchPrinterName = str;
        return EPrintService.findPrinter(new EpsProtocol(208), i * 1000);
    }

    public int findPrinter(int i) {
        this.mSearchTargetPrinterOnly = false;
        return EPrintService.findPrinter(new EpsProtocol(208), i * 1000);
    }

    public int cancelFindPrinter() {
        return EPrintService.cancelFindPrinter();
    }

    public int probePrinter(String str, String str2, int i) {
        this.mSearchPrinterName = null;
        return EPrintService.probePrinter(new EpsProbe(str, str2, i * 1000));
    }

    public int startNozzleCheck() {
        if (this.mBPrintBusy) {
            return Error_PrintBusy;
        }
        this.mBPrintBusy = true;
        PageAttribute pageAttribute = new PageAttribute(0, 0, 2);
        pageAttribute.setCommandDataType(3);
        return EPrintService.startMaintenance(new JobAttribute(EPSetting.instance().getSelEpsPrinter(), pageAttribute, null, false, false, 0));
    }

    public int startHeadCleaning() {
        if (this.mBPrintBusy) {
            return Error_PrintBusy;
        }
        this.mBPrintBusy = true;
        PageAttribute pageAttribute = new PageAttribute(0, 0, 2);
        pageAttribute.setCommandDataType(2);
        return EPrintService.startMaintenance(new JobAttribute(EPSetting.instance().getSelEpsPrinter(), pageAttribute, null, false, false, 0));
    }

    protected IPrintListener getPrintListener() {
        return this.mPrintListener;
    }

    public int startPaperFeed() {
        if (this.mBPrintBusy) {
            return Error_PrintBusy;
        }
        this.mBPrintBusy = true;
        PageAttribute pageAttribute = new PageAttribute(0, 0, 2);
        pageAttribute.setCommandDataType(4);
        return EPrintService.startMaintenance(new JobAttribute(EPSetting.instance().getSelEpsPrinter(), pageAttribute, null, false, false, 0));
    }

    private JobAttribute getJobAttribute() {
        EPSetting ePSettingInstance = EPSetting.instance();
        PageAttribute selPageAttri = ePSettingInstance.getSelPageAttri();
        selPageAttri.setPrintLayout(ePSettingInstance.getBorderless() ? 1 : 2);
        selPageAttri.setMediaSizeIdx(ePSettingInstance.getMediaSizeID());
        selPageAttri.setMediaTypeIdx(ePSettingInstance.getMediaTypeID());
        selPageAttri.setPrintDirection(ePSettingInstance.getPrintDirection() == 1 ? 1 : 0);
        selPageAttri.setColorMode(ePSettingInstance.getColorMode() != 1 ? 0 : 1);
        selPageAttri.setPaperSource(ePSettingInstance.getPaperSource());
        int totalPages = ePSettingInstance.getTotalPages();
        selPageAttri.setDuplex(ePSettingInstance.getDuplexPrint() ? 2 : 0);
        selPageAttri.setUserDefSize(ePSettingInstance.getUserDefWidth(), ePSettingInstance.getUserDefHeight());
        return new JobAttribute(EPSetting.instance().getSelEpsPrinter(), selPageAttri, new ImageAttribute(ePSettingInstance.getTemporaryImageFilePath()), this.mAutoScaling, this.mAutoRotation, totalPages);
    }

    private JobAttribute getJobAttribute(String str, EPRINT_FILETYPE eprint_filetype) {
        EPSetting ePSettingInstance = EPSetting.instance();
        PageAttribute selPageAttri = ePSettingInstance.getSelPageAttri();
        selPageAttri.setPrintLayout(ePSettingInstance.getBorderless() ? 1 : 2);
        selPageAttri.setMediaSizeIdx(ePSettingInstance.getMediaSizeID());
        selPageAttri.setMediaTypeIdx(ePSettingInstance.getMediaTypeID());
        selPageAttri.setPrintDirection(ePSettingInstance.getPrintDirection() == 1 ? 1 : 0);
        selPageAttri.setColorMode(ePSettingInstance.getColorMode() != 1 ? 0 : 1);
        selPageAttri.setPaperSource(ePSettingInstance.getPaperSource());
        int totalPages = ePSettingInstance.getTotalPages();
        selPageAttri.setUserDefSize(ePSettingInstance.getUserDefWidth(), ePSettingInstance.getUserDefHeight());
        return new JobAttribute(EPSetting.instance().getSelEpsPrinter(), selPageAttri, new ImageAttribute(3, str, null, ePSettingInstance.getTemporaryImageFilePath()), this.mAutoScaling, this.mAutoRotation, totalPages);
    }

    private JobAttribute getJobAttribute(boolean z) {
        EPSetting ePSettingInstance = EPSetting.instance();
        PageAttribute selPageAttri = ePSettingInstance.getSelPageAttri();
        selPageAttri.setPrintLayout(ePSettingInstance.getBorderless() ? 1 : 2);
        selPageAttri.setMediaSizeIdx(ePSettingInstance.getMediaSizeID());
        selPageAttri.setMediaTypeIdx(ePSettingInstance.getMediaTypeID());
        selPageAttri.setPrintDirection(ePSettingInstance.getPrintDirection() == 1 ? 1 : 0);
        selPageAttri.setColorMode(ePSettingInstance.getColorMode() == 1 ? 1 : 0);
        selPageAttri.setColorGamut(ePSettingInstance.getColorGamut() != 1 ? 0 : 1);
        selPageAttri.setPaperSource(ePSettingInstance.getPaperSource());
        boolean fastCopy = ePSettingInstance.getFastCopy();
        int totalPages = ePSettingInstance.getTotalPages();
        selPageAttri.setDuplex(ePSettingInstance.getDuplexPrint() ? 2 : 0);
        selPageAttri.setUserDefSize(ePSettingInstance.getUserDefWidth(), ePSettingInstance.getUserDefHeight());
        return new JobAttribute(EPSetting.instance().getSelEpsPrinter(), selPageAttri, new ImageAttribute(2, EPSetting.instance().getBand()), fastCopy, totalPages);
    }

    public int startPrint() {
        if (this.mBPrintBusy) {
            return Error_PrintBusy;
        }
        this.mBPrintBusy = true;
        return EPrintService.startPrint(getJobAttribute(), null);
    }

    protected int startPrint(String str, EPRINT_FILETYPE eprint_filetype) {
        if (this.mBPrintBusy) {
            return Error_PrintBusy;
        }
        this.mBPrintBusy = true;
        return EPrintService.startPrint(getJobAttribute(str, eprint_filetype), null);
    }

    protected int startPrint(IPageRenderer iPageRenderer) {
        if (this.mBPrintBusy) {
            return Error_PrintBusy;
        }
        this.mBPrintBusy = true;
        JobAttribute jobAttribute = getJobAttribute(true);
        return EPrintService.startPrint(jobAttribute, new PageRender(iPageRenderer, jobAttribute));
    }

    protected int startPrint(IBandRenderer iBandRenderer) {
        if (this.mBPrintBusy) {
            return Error_PrintBusy;
        }
        this.mBPrintBusy = true;
        return EPrintService.startPrint(getJobAttribute(false), new BandRender(iBandRenderer));
    }

    public boolean isPrintBusy() {
        return this.mBPrintBusy;
    }

    public EpsCapability getCapability() {
        return EPrintService.getCapability(EPSetting.instance().getSelEpsPrinter());
    }

    public EpsStatus getStatus() {
        return EPrintService.getStatus(EPSetting.instance().getSelEpsPrinter());
    }

    public EpsInkInfo getInkInfo() {
        return EPrintService.getInkInfo(EPSetting.instance().getSelEpsPrinter());
    }

    public void cancelPrint() {
        EPrintService.cancelPrint();
    }

    public void continuePrint() {
        EPrintService.continuePrint();
    }

    @Override
    public void onEPrintStateNty(Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        int i = intent.getExtras().getInt(ServiceDefine.TaskType, -1);
        if (i == 1) {
            notifyPrintEvent(intent);
        } else {
            if (i != 2) {
                return;
            }
            notifySearchEvent(intent);
        }
    }

    private void notifyPrintEvent(Intent intent) {
        if (this.mPrintListener == null) {
        }
        switch (intent.getExtras().getInt(ServiceDefine.EventType)) {
            case 65537:
                this.mPrintListener.onPrintBegin();
                break;
            case 65539:
                this.mPrintListener.onPrintPause(intent.getExtras().getShort(ServiceDefine.PageNumCurrent), intent.getExtras().getInt(ServiceDefine.PauseReason), EPrintService.getPrintStatus());
                break;
            case 65540:
                this.mPrintListener.onPrintResume();
                break;
            case 65541:
                this.mBPrintBusy = false;
                switch (intent.getExtras().getInt(ServiceDefine.PrintStopReason)) {
                    case 131073:
                        this.mPrintListener.onPrintFinished(131073);
                        break;
                    case 131074:
                        this.mPrintListener.onPrintFinished(131074);
                        break;
                    case 131075:
                        this.mPrintListener.onPrintFinished(131075);
                        break;
                    case 131076:
                        this.mPrintListener.onPrintFinished(131076);
                        break;
                    case 131078:
                        this.mPrintListener.onPrintFinished(131078);
                        break;
                }
                break;
            case 65542:
                this.mPrintListener.onPrintAutoContinue();
                break;
            case 65543:
                this.mPrintListener.onCleaningTime(intent.getExtras().getInt(ServiceDefine.CleaningTime));
                break;
        }
    }

    private void notifySearchEvent(Intent intent) {
        if (this.mSearchListener == null) {
        }
        switch (intent.getExtras().getInt(ServiceDefine.EventType)) {
            case 131073:
                this.mSearchListener.onSearchBegin();
                break;
            case 131074:
                EpsPrinter epsPrinter = (EpsPrinter) intent.getExtras().getParcelable(ServiceDefine.EpsPrinter);
                if (this.mSearchTargetPrinterOnly) {
                    String str = this.mSearchPrinterName;
                    if (str != null && str.equalsIgnoreCase(epsPrinter.getModelName())) {
                        this.mSearchListener.onFindPrinter(epsPrinter);
                        EPrintService.cancelFindPrinter();
                        break;
                    }
                } else {
                    this.mSearchListener.onFindPrinter(epsPrinter);
                    break;
                }
                break;
            case 131075:
                this.mSearchPrinterName = null;
                switch (intent.getExtras().getInt(ServiceDefine.SearchFinishReason)) {
                    case 262145:
                        this.mSearchListener.onSearchFinished(262145);
                        break;
                    case 262146:
                        this.mSearchListener.onSearchFinished(262146);
                        break;
                    case 262147:
                        this.mSearchListener.onSearchFinished(262147);
                        break;
                }
                break;
        }
    }

    public void setAutoRotation(boolean z) {
        this.mAutoRotation = z;
    }

    public void setAutoScaling(boolean z) {
        this.mAutoScaling = z;
    }

    public boolean getAutoRotation() {
        return this.mAutoRotation;
    }

    public boolean getAutoScaling() {
        return this.mAutoScaling;
    }
}
