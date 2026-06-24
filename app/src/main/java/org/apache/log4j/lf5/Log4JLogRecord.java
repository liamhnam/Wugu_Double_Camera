package org.apache.log4j.lf5;

import org.apache.log4j.spi.ThrowableInformation;

public class Log4JLogRecord extends LogRecord {
    @Override
    public boolean isSevereLevel() {
        return LogLevel.ERROR.equals(getLevel()) || LogLevel.FATAL.equals(getLevel());
    }

    public void setThrownStackTrace(ThrowableInformation throwableInformation) {
        String[] throwableStrRep = throwableInformation.getThrowableStrRep();
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : throwableStrRep) {
            stringBuffer.append(new StringBuffer().append(str).append("\n").toString());
        }
        this._thrownStackTrace = stringBuffer.toString();
    }
}
