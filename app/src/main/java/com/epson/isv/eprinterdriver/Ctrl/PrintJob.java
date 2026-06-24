package com.epson.isv.eprinterdriver.Ctrl;

class PrintJob {
    JobAttribute jobAttribute;
    int jobID;
    int printJobStatus = 0;
    IRenderRequest renderCBRequest;

    public static final class PrintJobStatus {
        public static final int PrintJob_Finish = 6;
        public static final int PrintJob_Init = 0;
        public static final int PrintJob_ServicePause = 4;
        public static final int PrintJob_UserCancel = 5;
        public static final int PrintJob_UserPause = 3;
        public static final int PrintJob_Wait = 1;
        public static final int PrintJob_Work = 2;
    }

    public PrintJob(JobAttribute jobAttribute, int i, IRenderRequest iRenderRequest) {
        this.jobAttribute = jobAttribute;
        this.jobID = i;
        this.renderCBRequest = iRenderRequest;
    }

    public int getPrintJobStatus() {
        return this.printJobStatus;
    }

    public void setPrintJobStatus(int i) {
        this.printJobStatus = i;
    }

    public JobAttribute getJobAttribute() {
        return this.jobAttribute;
    }

    public int getJobID() {
        return this.jobID;
    }

    public IRenderRequest getRenderCBRequest() {
        return this.renderCBRequest;
    }
}
