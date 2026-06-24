package org.apache.log4j;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class WriterAppender extends AppenderSkeleton {
    protected String encoding;
    protected boolean immediateFlush;

    protected QuietWriter f2787qw;

    @Override
    public void activateOptions() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    public WriterAppender() {
        this.immediateFlush = true;
    }

    public WriterAppender(Layout layout, OutputStream outputStream) {
        this(layout, new OutputStreamWriter(outputStream));
    }

    public WriterAppender(Layout layout, Writer writer) {
        this.immediateFlush = true;
        this.layout = layout;
        setWriter(writer);
    }

    public void setImmediateFlush(boolean z) {
        this.immediateFlush = z;
    }

    public boolean getImmediateFlush() {
        return this.immediateFlush;
    }

    @Override
    public void append(LoggingEvent loggingEvent) {
        if (checkEntryConditions()) {
            subAppend(loggingEvent);
        }
    }

    protected boolean checkEntryConditions() {
        if (this.closed) {
            LogLog.warn("Not allowed to write to a closed appender.");
            return false;
        }
        if (this.f2787qw == null) {
            this.errorHandler.error(new StringBuffer("No output stream or file set for the appender named [").append(this.name).append("].").toString());
            return false;
        }
        if (this.layout != null) {
            return true;
        }
        this.errorHandler.error(new StringBuffer("No layout set for the appender named [").append(this.name).append("].").toString());
        return false;
    }

    @Override
    public synchronized void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        writeFooter();
        reset();
    }

    protected void closeWriter() {
        QuietWriter quietWriter = this.f2787qw;
        if (quietWriter != null) {
            try {
                quietWriter.close();
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error(new StringBuffer("Could not close ").append(this.f2787qw).toString(), e);
            }
        }
    }

    protected OutputStreamWriter createWriter(OutputStream outputStream) {
        OutputStreamWriter outputStreamWriter;
        String encoding = getEncoding();
        if (encoding != null) {
            try {
                outputStreamWriter = new OutputStreamWriter(outputStream, encoding);
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.warn("Error initializing output writer.");
                LogLog.warn("Unsupported encoding?");
                outputStreamWriter = null;
            }
        } else {
            outputStreamWriter = null;
        }
        return outputStreamWriter == null ? new OutputStreamWriter(outputStream) : outputStreamWriter;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    @Override
    public synchronized void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) {
            LogLog.warn("You have tried to set a null error-handler.");
        } else {
            this.errorHandler = errorHandler;
            QuietWriter quietWriter = this.f2787qw;
            if (quietWriter != null) {
                quietWriter.setErrorHandler(errorHandler);
            }
        }
    }

    public synchronized void setWriter(Writer writer) {
        reset();
        this.f2787qw = new QuietWriter(writer, this.errorHandler);
        writeHeader();
    }

    protected void subAppend(LoggingEvent loggingEvent) {
        String[] throwableStrRep;
        this.f2787qw.write(this.layout.format(loggingEvent));
        if (this.layout.ignoresThrowable() && (throwableStrRep = loggingEvent.getThrowableStrRep()) != null) {
            for (String str : throwableStrRep) {
                this.f2787qw.write(str);
                this.f2787qw.write(Layout.LINE_SEP);
            }
        }
        if (shouldFlush(loggingEvent)) {
            this.f2787qw.flush();
        }
    }

    protected void reset() {
        closeWriter();
        this.f2787qw = null;
    }

    protected void writeFooter() {
        String footer;
        QuietWriter quietWriter;
        if (this.layout == null || (footer = this.layout.getFooter()) == null || (quietWriter = this.f2787qw) == null) {
            return;
        }
        quietWriter.write(footer);
        this.f2787qw.flush();
    }

    protected void writeHeader() {
        String header;
        QuietWriter quietWriter;
        if (this.layout == null || (header = this.layout.getHeader()) == null || (quietWriter = this.f2787qw) == null) {
            return;
        }
        quietWriter.write(header);
    }

    protected boolean shouldFlush(LoggingEvent loggingEvent) {
        return this.immediateFlush;
    }
}
