package com.liulishuo.okdownload.core.download;

import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.exception.FileBusyAfterRunException;
import com.liulishuo.okdownload.core.exception.InterruptException;
import com.liulishuo.okdownload.core.exception.PreAllocateException;
import com.liulishuo.okdownload.core.exception.ResumeFailedException;
import com.liulishuo.okdownload.core.exception.ServerCanceledException;
import com.liulishuo.okdownload.core.file.MultiPointOutputStream;
import java.io.IOException;
import java.net.SocketException;

public class DownloadCache {
    private volatile boolean isFileBusyAfterRun;
    private volatile boolean isPreAllocateFailed;
    private volatile boolean isPreconditionFailed;
    private volatile boolean isServerCanceled;
    private volatile boolean isUnknownError;
    private volatile boolean isUserCanceled;
    private final MultiPointOutputStream outputStream;
    private volatile IOException realCause;
    private String redirectLocation;

    DownloadCache(MultiPointOutputStream multiPointOutputStream) {
        this.outputStream = multiPointOutputStream;
    }

    private DownloadCache() {
        this.outputStream = null;
    }

    MultiPointOutputStream getOutputStream() {
        MultiPointOutputStream multiPointOutputStream = this.outputStream;
        if (multiPointOutputStream != null) {
            return multiPointOutputStream;
        }
        throw new IllegalArgumentException();
    }

    void setRedirectLocation(String str) {
        this.redirectLocation = str;
    }

    String getRedirectLocation() {
        return this.redirectLocation;
    }

    boolean isPreconditionFailed() {
        return this.isPreconditionFailed;
    }

    public boolean isUserCanceled() {
        return this.isUserCanceled;
    }

    boolean isServerCanceled() {
        return this.isServerCanceled;
    }

    boolean isUnknownError() {
        return this.isUnknownError;
    }

    boolean isFileBusyAfterRun() {
        return this.isFileBusyAfterRun;
    }

    public boolean isPreAllocateFailed() {
        return this.isPreAllocateFailed;
    }

    IOException getRealCause() {
        return this.realCause;
    }

    ResumeFailedCause getResumeFailedCause() {
        return ((ResumeFailedException) this.realCause).getResumeFailedCause();
    }

    public boolean isInterrupt() {
        return this.isPreconditionFailed || this.isUserCanceled || this.isServerCanceled || this.isUnknownError || this.isFileBusyAfterRun || this.isPreAllocateFailed;
    }

    public void setPreconditionFailed(IOException iOException) {
        this.isPreconditionFailed = true;
        this.realCause = iOException;
    }

    void setUserCanceled() {
        this.isUserCanceled = true;
    }

    public void setFileBusyAfterRun() {
        this.isFileBusyAfterRun = true;
    }

    public void setServerCanceled(IOException iOException) {
        this.isServerCanceled = true;
        this.realCause = iOException;
    }

    public void setUnknownError(IOException iOException) {
        this.isUnknownError = true;
        this.realCause = iOException;
    }

    public void setPreAllocateFailed(IOException iOException) {
        this.isPreAllocateFailed = true;
        this.realCause = iOException;
    }

    public void catchException(IOException iOException) {
        if (isUserCanceled()) {
            return;
        }
        if (iOException instanceof ResumeFailedException) {
            setPreconditionFailed(iOException);
            return;
        }
        if (iOException instanceof ServerCanceledException) {
            setServerCanceled(iOException);
            return;
        }
        if (iOException == FileBusyAfterRunException.SIGNAL) {
            setFileBusyAfterRun();
            return;
        }
        if (iOException instanceof PreAllocateException) {
            setPreAllocateFailed(iOException);
        } else if (iOException != InterruptException.SIGNAL) {
            setUnknownError(iOException);
            if (iOException instanceof SocketException) {
                return;
            }
            iOException.printStackTrace();
        }
    }

    static class PreError extends DownloadCache {
        PreError(IOException iOException) {
            super(null);
            setUnknownError(iOException);
        }
    }
}
