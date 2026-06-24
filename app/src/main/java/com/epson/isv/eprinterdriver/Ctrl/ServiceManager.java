package com.epson.isv.eprinterdriver.Ctrl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.epson.isv.eprinterdriver.Common.EpsCapability;
import com.epson.isv.eprinterdriver.Common.EpsInkInfo;
import com.epson.isv.eprinterdriver.Common.EpsPrinter;
import com.epson.isv.eprinterdriver.Common.EpsProbe;
import com.epson.isv.eprinterdriver.Common.EpsProtocol;
import com.epson.isv.eprinterdriver.Common.EpsStatus;
import com.epson.isv.eprinterdriver.Common.ErrorCode;
import java.util.ArrayList;
import java.util.Iterator;

class ServiceManager {
    public static final int MSG_NEWPRINTJOB_NTY = 3;
    public static final int MSG_PRINTSTATE_NTY = 2;
    public static final int MSG_QUERYSTATE_NTY = 1;
    public static final int MSG_SEARCHSTATE_NTY = 0;
    public static final int MSG_TASKFINISHED_NTY = 4;
    private Context context;
    private int curServiceStatus;
    private PrintJobSvr printJobSvr;
    private SearchPrinterJobSvr searchJobSvr;
    private ArrayList<PrintJob> printJobList = new ArrayList<>();
    private PrintJob curPrintJob = null;
    private SearchJob curSearchJob = new SearchJob();
    private Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            ServiceManager.this.handleCallBackMessage(message);
        }
    };

    public static final class ServiceStatus {
        public static final int Service_Close = 100;
        public static final int Service_Error = 4;
        public static final int Service_Idle = 0;
        public static final int Service_PrintJob_Exec = 1;
        public static final int Service_QueryJob_Exec = 3;
        public static final int Service_SearchJob_Exec = 2;
    }

    public ServiceManager(Context context) {
        this.curServiceStatus = 0;
        this.context = context;
        this.curServiceStatus = 0;
        init();
    }

    public void init() {
        this.searchJobSvr = new SearchPrinterJobSvr(this.messageHandler);
        PrintJobSvr printJobSvr = new PrintJobSvr(this.messageHandler);
        this.printJobSvr = printJobSvr;
        printJobSvr.startJobSvr();
    }

    public void release() {
        PrintJobSvr printJobSvr = this.printJobSvr;
        if (printJobSvr != null) {
            printJobSvr.stopJobSvr();
        }
    }

    public int startEscprService(Context context) {
        this.context = context;
        if (EscprLibWrapper.LibInitDriver(context, new EpsCommMode(2, 208), this.searchJobSvr, this.printJobSvr, 5000L) == 0) {
            return 0;
        }
        this.curServiceStatus = 4;
        return 0;
    }

    public int stopEscprService() {
        EscprLibWrapper.LibReleaseDriver();
        return 0;
    }

    public synchronized int addPrintTask(PrintJob printJob) {
        synchronized (this) {
            if (this.curServiceStatus == 4) {
                return 4098;
            }
            printJob.setPrintJobStatus(1);
            this.printJobList.add(printJob);
            Message message = new Message();
            message.what = 3;
            this.messageHandler.sendMessage(message);
            return 0;
        }
    }

    public int cancelPrintTask(int i, int i2) {
        PrintJob next;
        synchronized (this) {
            int i3 = this.curServiceStatus;
            if (i3 == 4) {
                return 4098;
            }
            if (i3 == 1 && this.curPrintJob.jobID == i) {
                int i4 = this.curPrintJob.printJobStatus;
                if (i4 != 2 && i4 != 4) {
                    if (i4 != 5) {
                        return 4098;
                    }
                    return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_CANCELING;
                }
                int iCancelPrintTask = this.printJobSvr.cancelPrintTask(i2);
                if (iCancelPrintTask != 0) {
                    return iCancelPrintTask == 4098 ? iCancelPrintTask : iCancelPrintTask;
                }
                this.curPrintJob.printJobStatus = 5;
                return iCancelPrintTask;
            }
            Iterator<PrintJob> it = this.printJobList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next.jobID == i) {
                    break;
                }
            }
            if (next == null) {
                return 16386;
            }
            this.printJobList.remove(next);
            return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_CANCELOK;
        }
    }

    public int continuePrintTask(int i) {
        PrintJob next;
        synchronized (this) {
            int i2 = this.curServiceStatus;
            if (i2 == 4) {
                return 4098;
            }
            if (i2 == 1 && this.curPrintJob.jobID == i) {
                int i3 = this.curPrintJob.printJobStatus;
                if (i3 == 2) {
                    return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_EXECUTING;
                }
                if (i3 != 4) {
                    if (i3 != 5) {
                        return 4098;
                    }
                    return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_CANCELING;
                }
                int iContinuePrintTask = this.printJobSvr.continuePrintTask();
                if (iContinuePrintTask != 0) {
                    return iContinuePrintTask == 4098 ? iContinuePrintTask : iContinuePrintTask;
                }
                this.curPrintJob.printJobStatus = 2;
                return iContinuePrintTask;
            }
            Iterator<PrintJob> it = this.printJobList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next.jobID == i) {
                    break;
                }
            }
            if (next == null) {
                return 16386;
            }
            if (next.printJobStatus != 3) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_WAITING;
            }
            next.printJobStatus = 1;
            return 0;
        }
    }

    public int pausePrintTask(int i) {
        PrintJob next;
        synchronized (this) {
            int i2 = this.curServiceStatus;
            if (i2 == 4) {
                return 4098;
            }
            if (i2 == 1 && this.curPrintJob.jobID == i) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_EXECUTING;
            }
            Iterator<PrintJob> it = this.printJobList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next.jobID == i) {
                    break;
                }
            }
            if (next == null) {
                return 16386;
            }
            if (next.printJobStatus != 1) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_PAUSEING;
            }
            next.printJobStatus = 3;
            return 0;
        }
    }

    public int findPrinter(EpsProtocol epsProtocol, int i) {
        synchronized (this) {
            int i2 = this.curServiceStatus;
            if (i2 == 4) {
                return 4098;
            }
            if (i2 == 2) {
                if (this.curSearchJob.getSearchJobStatus() == 2) {
                    return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_SEARCH_CANCELING;
                }
                return 8193;
            }
            if (i2 != 0) {
                return 4097;
            }
            this.curServiceStatus = 2;
            this.curSearchJob.setSearchJobStatus(1);
            this.searchJobSvr.findPrinter(epsProtocol, i);
            return 0;
        }
    }

    public int probePrinter(EpsProbe epsProbe) {
        synchronized (this) {
            int i = this.curServiceStatus;
            if (i == 4) {
                return 4098;
            }
            if (i == 2) {
                if (this.curSearchJob.getSearchJobStatus() == 2) {
                    return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_SEARCH_CANCELING;
                }
                return 8193;
            }
            if (i != 0) {
                return 4097;
            }
            this.curServiceStatus = 2;
            this.curSearchJob.setSearchJobStatus(1);
            this.searchJobSvr.probePrinter(epsProbe);
            return 0;
        }
    }

    public int cancelFindPrinter() {
        synchronized (this) {
            int i = this.curServiceStatus;
            if (i == 4) {
                return 4098;
            }
            if (i != 2) {
                return 8194;
            }
            if (this.curSearchJob.getSearchJobStatus() == 2) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_SEARCH_CANCELING;
            }
            this.curSearchJob.setSearchJobStatus(2);
            return this.searchJobSvr.cancelFindPrinter();
        }
    }

    public EpsCapability getCapability(EpsPrinter epsPrinter) {
        if ((epsPrinter != null ? EscprLibWrapper.LibSetPrinter(epsPrinter) : 0) != 0) {
            return null;
        }
        EpsCapability epsCapability = new EpsCapability();
        if (EscprLibWrapper.LibGetCapability(epsCapability) != 0) {
            return null;
        }
        return epsCapability;
    }

    public EpsStatus getStatus(EpsPrinter epsPrinter) {
        if ((epsPrinter != null ? EscprLibWrapper.LibSetPrinter(epsPrinter) : 0) != 0) {
            return null;
        }
        EpsStatus epsStatus = new EpsStatus();
        if (EscprLibWrapper.LibGetStatus(epsStatus) != 0) {
            return null;
        }
        return epsStatus;
    }

    public EpsInkInfo getInkInfo(EpsPrinter epsPrinter) {
        if ((epsPrinter != null ? EscprLibWrapper.LibSetPrinter(epsPrinter) : 0) != 0) {
            return null;
        }
        EpsInkInfo epsInkInfo = new EpsInkInfo();
        if (EscprLibWrapper.LibGetInkInfo(epsInkInfo) != 0) {
            return null;
        }
        return epsInkInfo;
    }

    public EpsStatus getPrintStatus() {
        EpsStatus epsStatus = new EpsStatus();
        if (EscprLibWrapper.LibGetStatus(epsStatus) != 0) {
            return null;
        }
        return epsStatus;
    }

    public int getCurrentJobID() {
        PrintJob printJob = this.curPrintJob;
        if (printJob != null) {
            return printJob.jobID;
        }
        return -1;
    }

    public void handleCallBackMessage(Message message) {
        synchronized (this) {
            int i = message.what;
            if (i == 0) {
                searchStateNtyHandler((SearchJobState) message.obj);
            } else if (i == 2) {
                printStateNtyHandler((PrintJobState) message.obj);
            } else if (i == 3 || i == 4) {
                tryExecPrintJob();
            }
        }
    }

    private void searchStateNtyHandler(SearchJobState searchJobState) {
        ServiceJobStateNty.SearchJobNotify(this.context, searchJobState);
        if (searchJobState.getEventType() != 2) {
            return;
        }
        this.curSearchJob.setSearchJobStatus(0);
        this.curServiceStatus = 0;
    }

    private void printStateNtyHandler(PrintJobState printJobState) {
        ServiceJobStateNty.PrintJobNotify(this.context, printJobState, this.curPrintJob.getJobID());
        int eventType = printJobState.getEventType();
        if (eventType == 2) {
            this.curPrintJob.setPrintJobStatus(4);
        } else {
            if (eventType != 4) {
                return;
            }
            this.printJobList.remove(this.curPrintJob);
            this.curPrintJob = null;
            this.curServiceStatus = 0;
            tryExecPrintJob();
        }
    }

    private void tryExecPrintJob() {
        if (this.curServiceStatus != 0 || this.printJobList.size() <= 0) {
            return;
        }
        this.curServiceStatus = 1;
        this.curPrintJob = null;
        Iterator<PrintJob> it = this.printJobList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PrintJob next = it.next();
            if (next.printJobStatus == 1) {
                this.curPrintJob = next;
                break;
            }
        }
        PrintJob printJob = this.curPrintJob;
        if (printJob == null) {
            this.curServiceStatus = 0;
            return;
        }
        printJob.setPrintJobStatus(2);
        if (this.printJobSvr.startPrintTask(this.curPrintJob.getJobAttribute(), this.curPrintJob.getRenderCBRequest()) != 0) {
            this.curPrintJob.setPrintJobStatus(1);
            this.curPrintJob = null;
            this.curServiceStatus = 0;
        }
    }
}
