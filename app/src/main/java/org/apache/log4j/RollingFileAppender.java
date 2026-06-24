package org.apache.log4j;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggingEvent;

public class RollingFileAppender extends FileAppender {
    protected int maxBackupIndex;
    protected long maxFileSize;
    private long nextRollover;

    public RollingFileAppender() {
        this.maxFileSize = 10485760L;
        this.maxBackupIndex = 1;
        this.nextRollover = 0L;
    }

    public RollingFileAppender(Layout layout, String str, boolean z) throws IOException {
        super(layout, str, z);
        this.maxFileSize = 10485760L;
        this.maxBackupIndex = 1;
        this.nextRollover = 0L;
    }

    public RollingFileAppender(Layout layout, String str) throws IOException {
        super(layout, str);
        this.maxFileSize = 10485760L;
        this.maxBackupIndex = 1;
        this.nextRollover = 0L;
    }

    public int getMaxBackupIndex() {
        return this.maxBackupIndex;
    }

    public long getMaximumFileSize() {
        return this.maxFileSize;
    }

    public void rollOver() {
        if (this.f2787qw != null) {
            long count = ((CountingQuietWriter) this.f2787qw).getCount();
            LogLog.debug(new StringBuffer("rolling over count=").append(count).toString());
            this.nextRollover = count + this.maxFileSize;
        }
        LogLog.debug(new StringBuffer("maxBackupIndex=").append(this.maxBackupIndex).toString());
        boolean z = true;
        if (this.maxBackupIndex > 0) {
            File file = new File(new StringBuffer().append(this.fileName).append('.').append(this.maxBackupIndex).toString());
            boolean zDelete = file.exists() ? file.delete() : true;
            for (int i = this.maxBackupIndex - 1; i >= 1 && zDelete; i--) {
                File file2 = new File(new StringBuffer().append(this.fileName).append(".").append(i).toString());
                if (file2.exists()) {
                    File file3 = new File(new StringBuffer().append(this.fileName).append('.').append(i + 1).toString());
                    LogLog.debug(new StringBuffer("Renaming file ").append(file2).append(" to ").append(file3).toString());
                    zDelete = file2.renameTo(file3);
                }
            }
            if (zDelete) {
                File file4 = new File(new StringBuffer().append(this.fileName).append(".1").toString());
                closeFile();
                File file5 = new File(this.fileName);
                LogLog.debug(new StringBuffer("Renaming file ").append(file5).append(" to ").append(file4).toString());
                zDelete = file5.renameTo(file4);
                if (!zDelete) {
                    try {
                        setFile(this.fileName, true, this.bufferedIO, this.bufferSize);
                    } catch (IOException e) {
                        if (e instanceof InterruptedIOException) {
                            Thread.currentThread().interrupt();
                        }
                        LogLog.error(new StringBuffer("setFile(").append(this.fileName).append(", true) call failed.").toString(), e);
                    }
                }
            }
            z = zDelete;
        }
        if (z) {
            try {
                setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
                this.nextRollover = 0L;
            } catch (IOException e2) {
                if (e2 instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error(new StringBuffer("setFile(").append(this.fileName).append(", false) call failed.").toString(), e2);
            }
        }
    }

    @Override
    public synchronized void setFile(String str, boolean z, boolean z2, int i) throws IOException {
        super.setFile(str, z, this.bufferedIO, this.bufferSize);
        if (z) {
            ((CountingQuietWriter) this.f2787qw).setCount(new File(str).length());
        }
    }

    public void setMaxBackupIndex(int i) {
        this.maxBackupIndex = i;
    }

    public void setMaximumFileSize(long j) {
        this.maxFileSize = j;
    }

    public void setMaxFileSize(String str) {
        this.maxFileSize = OptionConverter.toFileSize(str, this.maxFileSize + 1);
    }

    @Override
    protected void setQWForFiles(Writer writer) {
        this.f2787qw = new CountingQuietWriter(writer, this.errorHandler);
    }

    @Override
    protected void subAppend(LoggingEvent loggingEvent) {
        super.subAppend(loggingEvent);
        if (this.fileName == null || this.f2787qw == null) {
            return;
        }
        long count = ((CountingQuietWriter) this.f2787qw).getCount();
        if (count < this.maxFileSize || count < this.nextRollover) {
            return;
        }
        rollOver();
    }
}
