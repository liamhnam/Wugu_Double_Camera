package org.apache.log4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;

public class FileAppender extends WriterAppender {
    protected int bufferSize;
    protected boolean bufferedIO;
    protected boolean fileAppend;
    protected String fileName;

    public FileAppender() {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
    }

    public FileAppender(Layout layout, String str, boolean z, boolean z2, int i) throws IOException {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
        this.layout = layout;
        setFile(str, z, z2, i);
    }

    public FileAppender(Layout layout, String str, boolean z) throws IOException {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
        this.layout = layout;
        setFile(str, z, false, this.bufferSize);
    }

    public FileAppender(Layout layout, String str) throws IOException {
        this(layout, str, true);
    }

    public void setFile(String str) {
        this.fileName = str.trim();
    }

    public boolean getAppend() {
        return this.fileAppend;
    }

    public String getFile() {
        return this.fileName;
    }

    @Override
    public void activateOptions() {
        String str = this.fileName;
        if (str != null) {
            try {
                setFile(str, this.fileAppend, this.bufferedIO, this.bufferSize);
                return;
            } catch (IOException e) {
                this.errorHandler.error(new StringBuffer("setFile(").append(this.fileName).append(",").append(this.fileAppend).append(") call failed.").toString(), e, 4);
                return;
            }
        }
        LogLog.warn(new StringBuffer("File option not set for appender [").append(this.name).append("].").toString());
        LogLog.warn("Are you using FileAppender instead of ConsoleAppender?");
    }

    protected void closeFile() {
        if (this.f2787qw != null) {
            try {
                this.f2787qw.close();
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error(new StringBuffer("Could not close ").append(this.f2787qw).toString(), e);
            }
        }
    }

    public boolean getBufferedIO() {
        return this.bufferedIO;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setAppend(boolean z) {
        this.fileAppend = z;
    }

    public void setBufferedIO(boolean z) {
        this.bufferedIO = z;
        if (z) {
            this.immediateFlush = false;
        }
    }

    public void setBufferSize(int i) {
        this.bufferSize = i;
    }

    public synchronized void setFile(String str, boolean z, boolean z2, int i) throws IOException {
        FileOutputStream fileOutputStream;
        LogLog.debug(new StringBuffer("setFile called: ").append(str).append(", ").append(z).toString());
        if (z2) {
            setImmediateFlush(false);
        }
        reset();
        try {
            fileOutputStream = new FileOutputStream(str, z);
        } catch (FileNotFoundException e) {
            String parent = new File(str).getParent();
            if (parent != null) {
                File file = new File(parent);
                if (!file.exists() && file.mkdirs()) {
                    fileOutputStream = new FileOutputStream(str, z);
                } else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
        Writer writerCreateWriter = createWriter(fileOutputStream);
        if (z2) {
            writerCreateWriter = new BufferedWriter(writerCreateWriter, i);
        }
        setQWForFiles(writerCreateWriter);
        this.fileName = str;
        this.fileAppend = z;
        this.bufferedIO = z2;
        this.bufferSize = i;
        writeHeader();
        LogLog.debug("setFile ended");
    }

    protected void setQWForFiles(Writer writer) {
        this.f2787qw = new QuietWriter(writer, this.errorHandler);
    }

    @Override
    protected void reset() {
        closeFile();
        this.fileName = null;
        super.reset();
    }
}
