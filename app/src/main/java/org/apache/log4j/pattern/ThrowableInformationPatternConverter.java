package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

public class ThrowableInformationPatternConverter extends LoggingEventPatternConverter {
    private int maxLines;

    @Override
    public boolean handlesThrowable() {
        return true;
    }

    private ThrowableInformationPatternConverter(String[] strArr) {
        super("Throwable", "throwable");
        this.maxLines = Integer.MAX_VALUE;
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        if ("none".equals(strArr[0])) {
            this.maxLines = 0;
        } else if ("short".equals(strArr[0])) {
            this.maxLines = 1;
        } else {
            try {
                this.maxLines = Integer.parseInt(strArr[0]);
            } catch (NumberFormatException unused) {
            }
        }
    }

    public static ThrowableInformationPatternConverter newInstance(String[] strArr) {
        return new ThrowableInformationPatternConverter(strArr);
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        ThrowableInformation throwableInformation;
        if (this.maxLines == 0 || (throwableInformation = loggingEvent.getThrowableInformation()) == null) {
            return;
        }
        String[] throwableStrRep = throwableInformation.getThrowableStrRep();
        int length = throwableStrRep.length;
        int i = this.maxLines;
        if (i < 0) {
            length += i;
        } else if (length > i) {
            length = i;
        }
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(throwableStrRep[i2]).append("\n");
        }
    }
}
