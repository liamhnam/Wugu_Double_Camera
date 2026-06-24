package com.liulishuo.okdownload.core.download;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.exception.FileBusyAfterRunException;
import com.liulishuo.okdownload.core.exception.ServerCanceledException;
import java.io.IOException;

public class BreakpointRemoteCheck {
    ResumeFailedCause failedCause;
    private final BreakpointInfo info;
    private long instanceLength;
    private boolean isAcceptRange;
    private boolean isResumable;
    private final DownloadTask task;

    public BreakpointRemoteCheck(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        this.task = downloadTask;
        this.info = breakpointInfo;
    }

    public String toString() {
        return "isAcceptRange[" + this.isAcceptRange + "] isResumable[" + this.isResumable + "] failedCause[" + this.failedCause + "] instanceLength[" + this.instanceLength + "] " + super.toString();
    }

    public ResumeFailedCause getCause() {
        return this.failedCause;
    }

    public ResumeFailedCause getCauseOrThrow() {
        ResumeFailedCause resumeFailedCause = this.failedCause;
        if (resumeFailedCause != null) {
            return resumeFailedCause;
        }
        throw new IllegalStateException("No cause find with isResumable: " + this.isResumable);
    }

    public boolean isResumable() {
        return this.isResumable;
    }

    public boolean isAcceptRange() {
        return this.isAcceptRange;
    }

    public long getInstanceLength() {
        return this.instanceLength;
    }

    public void check() throws IOException {
        DownloadStrategy downloadStrategy = OkDownload.with().downloadStrategy();
        ConnectTrial connectTrialCreateConnectTrial = createConnectTrial();
        connectTrialCreateConnectTrial.executeTrial();
        boolean zIsAcceptRange = connectTrialCreateConnectTrial.isAcceptRange();
        boolean zIsChunked = connectTrialCreateConnectTrial.isChunked();
        long instanceLength = connectTrialCreateConnectTrial.getInstanceLength();
        String responseEtag = connectTrialCreateConnectTrial.getResponseEtag();
        String responseFilename = connectTrialCreateConnectTrial.getResponseFilename();
        int responseCode = connectTrialCreateConnectTrial.getResponseCode();
        downloadStrategy.validFilenameFromResponse(responseFilename, this.task, this.info);
        this.info.setChunked(zIsChunked);
        this.info.setEtag(responseEtag);
        if (OkDownload.with().downloadDispatcher().isFileConflictAfterRun(this.task)) {
            throw FileBusyAfterRunException.SIGNAL;
        }
        ResumeFailedCause preconditionFailedCause = downloadStrategy.getPreconditionFailedCause(responseCode, this.info.getTotalOffset() != 0, this.info, responseEtag);
        this.isResumable = preconditionFailedCause == null;
        this.failedCause = preconditionFailedCause;
        this.instanceLength = instanceLength;
        this.isAcceptRange = zIsAcceptRange;
        if (downloadStrategy.isServerCanceled(responseCode, this.info.getTotalOffset() != 0)) {
            throw new ServerCanceledException(responseCode, this.info.getTotalOffset());
        }
    }

    ConnectTrial createConnectTrial() {
        return new ConnectTrial(this.task, this.info);
    }
}
