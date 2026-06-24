package org.apache.log4j;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class DailyRollingFileAppender extends FileAppender {
    static final int HALF_DAY = 2;
    static final int TOP_OF_DAY = 3;
    static final int TOP_OF_HOUR = 1;
    static final int TOP_OF_MINUTE = 0;
    static final int TOP_OF_MONTH = 5;
    static final int TOP_OF_TROUBLE = -1;
    static final int TOP_OF_WEEK = 4;
    static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
    int checkPeriod;
    private String datePattern;
    private long nextCheck;
    Date now;

    RollingCalendar f2782rc;
    private String scheduledFilename;
    SimpleDateFormat sdf;

    public DailyRollingFileAppender() {
        this.datePattern = "'.'yyyy-MM-dd";
        this.nextCheck = System.currentTimeMillis() - 1;
        this.now = new Date();
        this.f2782rc = new RollingCalendar();
        this.checkPeriod = -1;
    }

    public DailyRollingFileAppender(Layout layout, String str, String str2) throws IOException {
        super(layout, str, true);
        this.datePattern = "'.'yyyy-MM-dd";
        this.nextCheck = System.currentTimeMillis() - 1;
        this.now = new Date();
        this.f2782rc = new RollingCalendar();
        this.checkPeriod = -1;
        this.datePattern = str2;
        activateOptions();
    }

    public void setDatePattern(String str) {
        this.datePattern = str;
    }

    public String getDatePattern() {
        return this.datePattern;
    }

    @Override
    public void activateOptions() {
        super.activateOptions();
        if (this.datePattern != null && this.fileName != null) {
            this.now.setTime(System.currentTimeMillis());
            this.sdf = new SimpleDateFormat(this.datePattern);
            int iComputeCheckPeriod = computeCheckPeriod();
            printPeriodicity(iComputeCheckPeriod);
            this.f2782rc.setType(iComputeCheckPeriod);
            this.scheduledFilename = new StringBuffer().append(this.fileName).append(this.sdf.format(new Date(new File(this.fileName).lastModified()))).toString();
            return;
        }
        LogLog.error(new StringBuffer("Either File or DatePattern options are not set for appender [").append(this.name).append("].").toString());
    }

    void printPeriodicity(int i) {
        if (i == 0) {
            LogLog.debug(new StringBuffer("Appender [").append(this.name).append("] to be rolled every minute.").toString());
            return;
        }
        if (i == 1) {
            LogLog.debug(new StringBuffer("Appender [").append(this.name).append("] to be rolled on top of every hour.").toString());
            return;
        }
        if (i == 2) {
            LogLog.debug(new StringBuffer("Appender [").append(this.name).append("] to be rolled at midday and midnight.").toString());
            return;
        }
        if (i == 3) {
            LogLog.debug(new StringBuffer("Appender [").append(this.name).append("] to be rolled at midnight.").toString());
            return;
        }
        if (i == 4) {
            LogLog.debug(new StringBuffer("Appender [").append(this.name).append("] to be rolled at start of week.").toString());
        } else if (i == 5) {
            LogLog.debug(new StringBuffer("Appender [").append(this.name).append("] to be rolled at start of every month.").toString());
        } else {
            LogLog.warn(new StringBuffer("Unknown periodicity for appender [").append(this.name).append("].").toString());
        }
    }

    int computeCheckPeriod() {
        RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone, Locale.getDefault());
        Date date = new Date(0L);
        if (this.datePattern == null) {
            return -1;
        }
        for (int i = 0; i <= 5; i++) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
            simpleDateFormat.setTimeZone(gmtTimeZone);
            String str = simpleDateFormat.format(date);
            rollingCalendar.setType(i);
            String str2 = simpleDateFormat.format(new Date(rollingCalendar.getNextCheckMillis(date)));
            if (str != null && str2 != null && !str.equals(str2)) {
                return i;
            }
        }
        return -1;
    }

    void rollOver() throws IOException {
        if (this.datePattern == null) {
            this.errorHandler.error("Missing DatePattern option in rollOver().");
            return;
        }
        String string = new StringBuffer().append(this.fileName).append(this.sdf.format(this.now)).toString();
        if (this.scheduledFilename.equals(string)) {
            return;
        }
        closeFile();
        File file = new File(this.scheduledFilename);
        if (file.exists()) {
            file.delete();
        }
        if (new File(this.fileName).renameTo(file)) {
            LogLog.debug(new StringBuffer().append(this.fileName).append(" -> ").append(this.scheduledFilename).toString());
        } else {
            LogLog.error(new StringBuffer("Failed to rename [").append(this.fileName).append("] to [").append(this.scheduledFilename).append("].").toString());
        }
        try {
            setFile(this.fileName, true, this.bufferedIO, this.bufferSize);
        } catch (IOException unused) {
            this.errorHandler.error(new StringBuffer("setFile(").append(this.fileName).append(", true) call failed.").toString());
        }
        this.scheduledFilename = string;
    }

    @Override
    protected void subAppend(LoggingEvent loggingEvent) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis >= this.nextCheck) {
            this.now.setTime(jCurrentTimeMillis);
            this.nextCheck = this.f2782rc.getNextCheckMillis(this.now);
            try {
                rollOver();
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("rollOver() failed.", e);
            }
        }
        super.subAppend(loggingEvent);
    }
}
