package org.apache.log4j.lf5.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;

public class AdapterLogRecord extends LogRecord {
    private static LogLevel severeLevel;

    private static StringWriter f2793sw = new StringWriter();

    private static PrintWriter f2792pw = new PrintWriter(f2793sw);

    @Override
    public void setCategory(String str) {
        super.setCategory(str);
        super.setLocation(getLocationInfo(str));
    }

    @Override
    public boolean isSevereLevel() {
        LogLevel logLevel = severeLevel;
        if (logLevel == null) {
            return false;
        }
        return logLevel.equals(getLevel());
    }

    public static void setSevereLevel(LogLevel logLevel) {
        severeLevel = logLevel;
    }

    public static LogLevel getSevereLevel() {
        return severeLevel;
    }

    protected String getLocationInfo(String str) {
        return parseLine(stackTraceToString(new Throwable()), str);
    }

    protected String stackTraceToString(Throwable th) {
        String string;
        synchronized (f2793sw) {
            th.printStackTrace(f2792pw);
            string = f2793sw.toString();
            f2793sw.getBuffer().setLength(0);
        }
        return string;
    }

    protected String parseLine(String str, String str2) {
        int iIndexOf = str.indexOf(str2);
        if (iIndexOf == -1) {
            return null;
        }
        String strSubstring = str.substring(iIndexOf);
        return strSubstring.substring(0, strSubstring.indexOf(")") + 1);
    }
}
