package com.arthenica.ffmpegkit;

import com.arthenica.smartexception.java.Exceptions;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractSession implements Session {
    public static final int DEFAULT_TIMEOUT_FOR_ASYNCHRONOUS_MESSAGES_IN_TRANSMIT = 5000;
    protected static final AtomicLong sessionIdGenerator = new AtomicLong(1);
    protected final String[] arguments;
    protected final LogCallback logCallback;
    protected final LogRedirectionStrategy logRedirectionStrategy;
    protected final long sessionId = sessionIdGenerator.getAndIncrement();
    protected final Date createTime = new Date();
    protected Date startTime = null;
    protected Date endTime = null;
    protected final List<Log> logs = new LinkedList();
    protected final Object logsLock = new Object();
    protected Future<?> future = null;
    protected SessionState state = SessionState.CREATED;
    protected ReturnCode returnCode = null;
    protected String failStackTrace = null;

    protected AbstractSession(String[] strArr, LogCallback logCallback, LogRedirectionStrategy logRedirectionStrategy) {
        this.logCallback = logCallback;
        this.arguments = strArr;
        this.logRedirectionStrategy = logRedirectionStrategy;
        FFmpegKitConfig.addSession(this);
    }

    @Override
    public LogCallback getLogCallback() {
        return this.logCallback;
    }

    @Override
    public long getSessionId() {
        return this.sessionId;
    }

    @Override
    public Date getCreateTime() {
        return this.createTime;
    }

    @Override
    public Date getStartTime() {
        return this.startTime;
    }

    @Override
    public Date getEndTime() {
        return this.endTime;
    }

    @Override
    public long getDuration() {
        Date date = this.startTime;
        Date date2 = this.endTime;
        if (date == null || date2 == null) {
            return 0L;
        }
        return date2.getTime() - date.getTime();
    }

    @Override
    public String[] getArguments() {
        return this.arguments;
    }

    @Override
    public String getCommand() {
        return FFmpegKitConfig.argumentsToString(this.arguments);
    }

    @Override
    public List<Log> getAllLogs(int i) {
        waitForAsynchronousMessagesInTransmit(i);
        if (thereAreAsynchronousMessagesInTransmit()) {
            android.util.Log.i("ffmpeg-kit", String.format("getAllLogs was called to return all logs but there are still logs being transmitted for session id %d.", Long.valueOf(this.sessionId)));
        }
        return getLogs();
    }

    @Override
    public List<Log> getAllLogs() {
        return getAllLogs(5000);
    }

    @Override
    public List<Log> getLogs() {
        LinkedList linkedList;
        synchronized (this.logsLock) {
            linkedList = new LinkedList(this.logs);
        }
        return linkedList;
    }

    @Override
    public String getAllLogsAsString(int i) {
        waitForAsynchronousMessagesInTransmit(i);
        if (thereAreAsynchronousMessagesInTransmit()) {
            android.util.Log.i("ffmpeg-kit", String.format("getAllLogsAsString was called to return all logs but there are still logs being transmitted for session id %d.", Long.valueOf(this.sessionId)));
        }
        return getLogsAsString();
    }

    @Override
    public String getAllLogsAsString() {
        return getAllLogsAsString(5000);
    }

    @Override
    public String getLogsAsString() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.logsLock) {
            Iterator<Log> it = this.logs.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getMessage());
            }
        }
        return sb.toString();
    }

    @Override
    public String getOutput() {
        return getAllLogsAsString();
    }

    @Override
    public SessionState getState() {
        return this.state;
    }

    @Override
    public ReturnCode getReturnCode() {
        return this.returnCode;
    }

    @Override
    public String getFailStackTrace() {
        return this.failStackTrace;
    }

    @Override
    public LogRedirectionStrategy getLogRedirectionStrategy() {
        return this.logRedirectionStrategy;
    }

    @Override
    public boolean thereAreAsynchronousMessagesInTransmit() {
        return FFmpegKitConfig.messagesInTransmit(this.sessionId) != 0;
    }

    @Override
    public void addLog(Log log) {
        synchronized (this.logsLock) {
            this.logs.add(log);
        }
    }

    @Override
    public Future<?> getFuture() {
        return this.future;
    }

    @Override
    public void cancel() {
        if (this.state == SessionState.RUNNING) {
            FFmpegKit.cancel(this.sessionId);
        }
    }

    protected void waitForAsynchronousMessagesInTransmit(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (thereAreAsynchronousMessagesInTransmit() && System.currentTimeMillis() < ((long) i) + jCurrentTimeMillis) {
            synchronized (this) {
                try {
                    wait(100L);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    void setFuture(Future<?> future) {
        this.future = future;
    }

    void startRunning() {
        this.state = SessionState.RUNNING;
        this.startTime = new Date();
    }

    void complete(ReturnCode returnCode) {
        this.returnCode = returnCode;
        this.state = SessionState.COMPLETED;
        this.endTime = new Date();
    }

    void fail(Exception exc) {
        this.failStackTrace = Exceptions.getStackTraceString(exc);
        this.state = SessionState.FAILED;
        this.endTime = new Date();
    }
}
