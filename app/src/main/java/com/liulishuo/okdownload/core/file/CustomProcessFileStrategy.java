package com.liulishuo.okdownload.core.file;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;

public class CustomProcessFileStrategy extends ProcessFileStrategy {
    @Override
    public MultiPointOutputStream createProcessStream(DownloadTask downloadTask, BreakpointInfo breakpointInfo, DownloadStore downloadStore) {
        return new CustomMultiPointOutputStream(downloadTask, breakpointInfo, downloadStore);
    }
}
