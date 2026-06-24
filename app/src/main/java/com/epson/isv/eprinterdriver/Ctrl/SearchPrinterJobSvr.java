package com.epson.isv.eprinterdriver.Ctrl;

import android.os.Handler;
import android.os.Message;
import com.epson.isv.eprinterdriver.Common.EpsPrinter;
import com.epson.isv.eprinterdriver.Common.EpsProbe;
import com.epson.isv.eprinterdriver.Common.EpsProtocol;
import com.epson.isv.eprinterdriver.Common.ErrorCode;

class SearchPrinterJobSvr implements IPrinterFindCallback {
    private Handler hostHandler;
    private int jobStatus = 0;

    private static final class SerarchJobSvrStatus {
        public static final int SearchJobSvr_Cancel = 3;
        public static final int SearchJobSvr_FindExec = 1;
        public static final int SearchJobSvr_Idle = 0;
        public static final int SearchJobSvr_ProbeExec = 2;

        private SerarchJobSvrStatus() {
        }
    }

    public SearchPrinterJobSvr(Handler handler) {
        this.hostHandler = handler;
    }

    private class FindThread extends Thread {
        private EpsProtocol protocol;
        private int timeout;

        public FindThread(EpsProtocol epsProtocol, int i) {
            this.protocol = epsProtocol;
            this.timeout = i;
        }

        @Override
        public void run() {
            SearchJobState searchJobState;
            SearchPrinterJobSvr.this.searchStateNty(new SearchJobState(0));
            int iLibFindPrinter = EscprLibWrapper.LibFindPrinter(this.protocol, this.timeout);
            SearchPrinterJobSvr.this.setJobStatus(0);
            if (iLibFindPrinter == 0) {
                searchJobState = new SearchJobState(2, 0, iLibFindPrinter);
            } else if (iLibFindPrinter == 42) {
                searchJobState = new SearchJobState(2, 1, iLibFindPrinter);
            } else {
                searchJobState = new SearchJobState(2, 2, iLibFindPrinter);
            }
            SearchPrinterJobSvr.this.searchStateNty(searchJobState);
        }
    }

    private class ProbeThread extends Thread {
        private EpsProbe probe;

        public ProbeThread(EpsProbe epsProbe) {
            this.probe = epsProbe;
        }

        @Override
        public void run() {
            SearchJobState searchJobState;
            SearchPrinterJobSvr.this.searchStateNty(new SearchJobState(0));
            int iLibProbePrinter = EscprLibWrapper.LibProbePrinter(this.probe);
            SearchPrinterJobSvr.this.setJobStatus(0);
            if (iLibProbePrinter == 0) {
                searchJobState = new SearchJobState(2, 0, iLibProbePrinter);
            } else if (iLibProbePrinter == 42) {
                searchJobState = new SearchJobState(2, 1, iLibProbePrinter);
            } else {
                searchJobState = new SearchJobState(2, 2, iLibProbePrinter);
            }
            SearchPrinterJobSvr.this.searchStateNty(searchJobState);
        }
    }

    public int findPrinter(EpsProtocol epsProtocol, int i) {
        int i2 = this.jobStatus;
        if (i2 != 0) {
            if (i2 == 1 || i2 == 2) {
                return 8193;
            }
            if (i2 == 3) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_SEARCH_CANCELING;
            }
        }
        this.jobStatus = 1;
        new FindThread(epsProtocol, i).start();
        return 0;
    }

    public int probePrinter(EpsProbe epsProbe) {
        int i = this.jobStatus;
        if (i != 0) {
            if (i == 1 || i == 2) {
                return 8193;
            }
            if (i == 3) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_SEARCH_CANCELING;
            }
        }
        setJobStatus(2);
        new ProbeThread(epsProbe).start();
        return 0;
    }

    public int cancelFindPrinter() {
        int i = this.jobStatus;
        if (i == 0) {
            return 8194;
        }
        if (i == 3) {
            return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_SEARCH_CANCELING;
        }
        int jobStatus = setJobStatus(3);
        if (jobStatus != 0) {
            return jobStatus;
        }
        int iLibCancelFindPrinter = EscprLibWrapper.LibCancelFindPrinter();
        return (iLibCancelFindPrinter == 0 || iLibCancelFindPrinter == -1305) ? 0 : 4098;
    }

    @Override
    public int printerFindNty(EpsPrinter epsPrinter) {
        if (this.jobStatus == 3) {
            return 0;
        }
        searchStateNty(new SearchJobState(1, epsPrinter));
        return 0;
    }

    public synchronized int setJobStatus(int i) {
        int i2 = this.jobStatus;
        if (i2 == 3 && i2 != 1 && i2 != 2) {
            this.jobStatus = 0;
            return 8194;
        }
        this.jobStatus = i;
        return 0;
    }

    public void searchStateNty(SearchJobState searchJobState) {
        Message message = new Message();
        message.what = 0;
        message.obj = searchJobState;
        this.hostHandler.sendMessage(message);
    }
}
