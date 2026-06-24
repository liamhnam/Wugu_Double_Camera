package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.epson.isv.eprinterdriver.Common.ErrorCode;

class PrintJobSvr implements IPrintJobStateCallback {
    private static final int CycleTimes = 1;
    private Handler hostHandler;
    private JobAttribute jobAttri;
    private PrintableArea pntArea;
    private IRenderRequest renderCBRequest;
    private boolean bUserCancel = false;
    private boolean bBusyCheck = false;
    private PrintThread printThread = new PrintThread();
    private int jobStatus = 0;

    private static final class PrintJobSvrStatus {
        public static final int PrintJobSvr_Cancel = 3;
        public static final int PrintJobSvr_Idle = 0;
        public static final int PrintJobSvr_PauseWaitUser = 2;
        public static final int PrintJobSvr_Printing = 1;

        private PrintJobSvrStatus() {
        }
    }

    private class PrintThread extends Thread {
        private boolean isStop;
        private boolean isSuspend;

        private PrintThread() {
            this.isStop = false;
            this.isSuspend = false;
        }

        @Override
        public void run() {
            this.isStop = false;
            while (!this.isStop) {
                try {
                    synchronized (this) {
                        synchronized (PrintJobSvr.this) {
                            PrintJobSvr.this.setJobStatus(0);
                            this.isSuspend = true;
                        }
                        while (this.isSuspend && !this.isStop) {
                            wait();
                        }
                        if (this.isStop) {
                            return;
                        }
                    }
                    if (PrintJobSvr.this.jobAttri.getPageAttribute().getColorPlane() == 3) {
                        PrintJobSvr.this.maintenanceProxy();
                    } else if (PrintJobSvr.this.renderCBRequest == null) {
                        PrintJobSvr.this.printJobProxy();
                    } else {
                        PrintJobSvr.this.printBandJobProxy();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void svrResume() {
            this.isSuspend = false;
            notify();
        }

        public synchronized void svrStop() {
            this.isStop = true;
            notify();
        }
    }

    private class BandRequestThread extends Thread {
        private BandRequestThread() {
        }

        @Override
        public void run() {
            int sheets = PrintJobSvr.this.jobAttri.getSheets();
            IRenderRequest iRenderRequest = PrintJobSvr.this.renderCBRequest;
            if (PrintJobSvr.this.jobAttri.getImageAttribute().getImgType() != 2) {
                return;
            }
            int bandHeight = PrintJobSvr.this.jobAttri.getImageAttribute().getBandHeight();
            if (bandHeight == 0) {
                bandHeight = 64;
            }
            int printableWidth = PrintJobSvr.this.pntArea.getPrintableWidth();
            int printableHeight = PrintJobSvr.this.pntArea.getPrintableHeight();
            DrawInfo drawInfo = new DrawInfo(printableWidth, printableHeight);
            drawInfo.setTotalPages((short) sheets);
            int i = 1;
            int i2 = -1;
            if (makeBandBegin(new EpsBandHeader(printableWidth, printableHeight, bandHeight, (byte) 1)) != 0) {
                EscprLibWrapper.nativeMakeBandFinish(-1);
                return;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(printableWidth, bandHeight, Bitmap.Config.ARGB_8888);
            if (bitmapCreateBitmap == null) {
                EscprLibWrapper.nativeMakeBandFinish(-1);
                return;
            }
            PrintCache printCache = new PrintCache(Environment.getExternalStorageDirectory() + "/band.cache");
            printCache.enableCache(PrintJobSvr.this.jobAttri.isFastCopy());
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            int i3 = 1;
            while (i3 <= sheets) {
                if (printCache.isCacheEnabled() && i3 > i) {
                    printCache.enableCache(false);
                    iRenderRequest = printCache;
                }
                printCache.rewind();
                if (makeBandCtrl(new EpsBandCtrl(65537)) != 0) {
                    EscprLibWrapper.nativeMakeBandFinish(i2);
                    return;
                }
                drawInfo.setCurPage((short) i3);
                Rect rect = new Rect(0, 0, 0, 0);
                EpsBandData epsBandData = new EpsBandData(bitmapCreateBitmap, rect, bandHeight);
                int i4 = printableHeight;
                while (i4 > 0) {
                    int i5 = i4 >= bandHeight ? bandHeight : i4;
                    int i6 = sheets;
                    int i7 = bandHeight;
                    rect.set(0, rect.bottom, printableWidth, rect.bottom + i5);
                    drawInfo.setDrawBandRect(rect);
                    epsBandData.setValidBand(i5);
                    bitmapCreateBitmap.eraseColor(-1);
                    iRenderRequest.OnDraw(canvas, drawInfo);
                    if (printCache.isCacheEnabled()) {
                        printCache.cacheBand(epsBandData);
                    }
                    if (makeBandData(epsBandData) != 0) {
                        EscprLibWrapper.nativeMakeBandFinish(-1);
                        return;
                    }
                    i4 -= i5;
                    i2 = -1;
                    sheets = i6;
                    bandHeight = i7;
                }
                int i8 = sheets;
                int i9 = bandHeight;
                int i10 = i2;
                if (makeBandCtrl(new EpsBandCtrl(65538)) != 0) {
                    EscprLibWrapper.nativeMakeBandFinish(i10);
                    return;
                }
                i3++;
                i2 = i10;
                sheets = i8;
                bandHeight = i9;
                i = 1;
            }
            makeBandFinish(0);
            bitmapCreateBitmap.recycle();
            printCache.close(true);
        }

        private int makeBandBegin(EpsBandHeader epsBandHeader) {
            return EscprLibWrapper.nativeMakeBandBegin(epsBandHeader);
        }

        private int makeBandCtrl(EpsBandCtrl epsBandCtrl) {
            return EscprLibWrapper.nativeMakeBandCtrl(epsBandCtrl);
        }

        private int makeBandData(EpsBandData epsBandData) {
            return EscprLibWrapper.nativeMakeBandData(epsBandData);
        }

        private int makeBandFinish(int i) {
            return EscprLibWrapper.nativeMakeBandFinish(i);
        }
    }

    public PrintJobSvr(Handler handler) {
        this.hostHandler = handler;
    }

    public void startJobSvr() {
        if (this.printThread.isAlive()) {
            return;
        }
        this.printThread.start();
    }

    public void stopJobSvr() {
        try {
            if (this.printThread.isAlive()) {
                this.printThread.svrStop();
                this.printThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int startPrintTask(JobAttribute jobAttribute, IRenderRequest iRenderRequest) {
        int i = this.jobStatus;
        if (i != 0) {
            if (i == 1) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_EXECUTING;
            }
            if (i == 2) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_WAITING;
            }
            if (i == 3) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_CANCELING;
            }
        }
        setUserCancelValue(false);
        setJobStatus(1);
        this.jobAttri = jobAttribute;
        this.renderCBRequest = iRenderRequest;
        this.printThread.svrResume();
        return 0;
    }

    public int cancelPrintTask(int i) {
        int i2 = this.jobStatus;
        if (i2 == 0) {
            return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_NOTEXIST;
        }
        if (i2 == 3) {
            return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_CANCELING;
        }
        int jobStatus = setJobStatus(3);
        if (jobStatus != 0) {
            return jobStatus;
        }
        if (!getBusyCheckValue()) {
            return EscprLibWrapper.LibCancelPrint(i) == 0 ? 0 : 4098;
        }
        setUserCancelValue(true);
        return 0;
    }

    public int continuePrintTask() {
        int i = this.jobStatus;
        if (i == 0) {
            return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_NOTEXIST;
        }
        if (i == 3) {
            return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_CANCELING;
        }
        if (i == 1) {
            return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_EXECUTING;
        }
        setJobStatus(1);
        return EscprLibWrapper.LibContinuePrint() == 0 ? 0 : 4098;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void maintenanceProxy() {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epson.isv.eprinterdriver.Ctrl.PrintJobSvr.maintenanceProxy():void");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void printJobProxy() {
        /*
            Method dump skipped, instruction units count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epson.isv.eprinterdriver.Ctrl.PrintJobSvr.printJobProxy():void");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void printBandJobProxy() {
        /*
            Method dump skipped, instruction units count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epson.isv.eprinterdriver.Ctrl.PrintJobSvr.printBandJobProxy():void");
    }

    @Override
    public void printJobStateNty(PrintJobState printJobState) {
        if (printJobState.getEventType() == 2) {
            setJobStatus(2);
        }
        printStateNotify(printJobState);
    }

    public synchronized int setJobStatus(int i) {
        if (i == 3) {
            int i2 = this.jobStatus;
            if (i2 != 1 && i2 != 2) {
                return ErrorCode.InterfaceRetError.EPRINTSVRIF_ERR_PRINT_NOTEXIST;
            }
            this.jobStatus = i;
            return 0;
        }
        this.jobStatus = i;
        return 0;
    }

    private void printStateNotify(PrintJobState printJobState) {
        Message message = new Message();
        message.what = 2;
        message.obj = printJobState;
        this.hostHandler.sendMessage(message);
    }

    private synchronized void setBusyCheckValue(boolean z) {
        this.bBusyCheck = z;
    }

    private synchronized boolean getBusyCheckValue() {
        return this.bBusyCheck;
    }

    private synchronized void setUserCancelValue(boolean z) {
        this.bUserCancel = z;
    }

    private synchronized boolean getUserCancelValue() {
        return this.bUserCancel;
    }
}
