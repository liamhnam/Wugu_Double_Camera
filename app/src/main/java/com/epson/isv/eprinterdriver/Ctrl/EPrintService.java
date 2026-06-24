package com.epson.isv.eprinterdriver.Ctrl;

import android.content.Context;
import com.epson.isv.eprinterdriver.Common.EpsCapability;
import com.epson.isv.eprinterdriver.Common.EpsInkInfo;
import com.epson.isv.eprinterdriver.Common.EpsPrinter;
import com.epson.isv.eprinterdriver.Common.EpsProbe;
import com.epson.isv.eprinterdriver.Common.EpsProtocol;
import com.epson.isv.eprinterdriver.Common.EpsStatus;

class EPrintService {
    private static Context mContext;
    private static ServiceManager svrMgr;

    EPrintService() {
    }

    public static int init(Context context) {
        ServiceManager serviceManager = new ServiceManager(context);
        svrMgr = serviceManager;
        mContext = context;
        return serviceManager.startEscprService(context);
    }

    public static int release() {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager == null) {
            return 0;
        }
        serviceManager.stopEscprService();
        svrMgr.release();
        return 0;
    }

    public static int findPrinter(EpsProtocol epsProtocol, int i) {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager != null) {
            return serviceManager.findPrinter(epsProtocol, i);
        }
        return -1;
    }

    public static int probePrinter(EpsProbe epsProbe) {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager != null) {
            return serviceManager.probePrinter(epsProbe);
        }
        return -1;
    }

    public static int cancelFindPrinter() {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager != null) {
            return serviceManager.cancelFindPrinter();
        }
        return -1;
    }

    public static EpsCapability getCapability(EpsPrinter epsPrinter) {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager != null) {
            return serviceManager.getCapability(epsPrinter);
        }
        return null;
    }

    public static EpsStatus getStatus(EpsPrinter epsPrinter) {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager != null) {
            return serviceManager.getStatus(epsPrinter);
        }
        return null;
    }

    public static EpsInkInfo getInkInfo(EpsPrinter epsPrinter) {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager != null) {
            return serviceManager.getInkInfo(epsPrinter);
        }
        return null;
    }

    public static EpsStatus getPrintStatus() {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager != null) {
            return serviceManager.getPrintStatus();
        }
        return null;
    }

    public static int startPrint(JobAttribute jobAttribute, IRenderRequest iRenderRequest) {
        if (svrMgr == null) {
            return -1;
        }
        jobAttribute.getPageAttribute().setColorPlane(0);
        return svrMgr.addPrintTask(new PrintJob(jobAttribute, 0, iRenderRequest));
    }

    public static int cancelPrint() {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager == null) {
            return -1;
        }
        return svrMgr.cancelPrintTask(serviceManager.getCurrentJobID(), 0);
    }

    public static int continuePrint() {
        ServiceManager serviceManager = svrMgr;
        if (serviceManager == null) {
            return -1;
        }
        return svrMgr.continuePrintTask(serviceManager.getCurrentJobID());
    }

    public static int startMaintenance(JobAttribute jobAttribute) {
        if (svrMgr == null) {
            return -1;
        }
        jobAttribute.getPageAttribute().setColorPlane(3);
        return svrMgr.addPrintTask(new PrintJob(jobAttribute, 0, null));
    }
}
