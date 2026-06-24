package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public abstract class PatternConverter {
    static String[] SPACES = {" ", "  ", "    ", "        ", "                ", "                                "};
    boolean leftAlign;
    int max;
    int min;
    public PatternConverter next;

    protected abstract String convert(LoggingEvent loggingEvent);

    protected PatternConverter() {
        this.min = -1;
        this.max = Integer.MAX_VALUE;
        this.leftAlign = false;
    }

    protected PatternConverter(FormattingInfo formattingInfo) {
        this.min = -1;
        this.max = Integer.MAX_VALUE;
        this.leftAlign = false;
        this.min = formattingInfo.min;
        this.max = formattingInfo.max;
        this.leftAlign = formattingInfo.leftAlign;
    }

    public void format(StringBuffer stringBuffer, LoggingEvent loggingEvent) {
        String strConvert = convert(loggingEvent);
        if (strConvert == null) {
            int i = this.min;
            if (i > 0) {
                spacePad(stringBuffer, i);
                return;
            }
            return;
        }
        int length = strConvert.length();
        int i2 = this.max;
        if (length > i2) {
            stringBuffer.append(strConvert.substring(length - i2));
            return;
        }
        int i3 = this.min;
        if (length < i3) {
            if (this.leftAlign) {
                stringBuffer.append(strConvert);
                spacePad(stringBuffer, this.min - length);
                return;
            } else {
                spacePad(stringBuffer, i3 - length);
                stringBuffer.append(strConvert);
                return;
            }
        }
        stringBuffer.append(strConvert);
    }

    public void spacePad(StringBuffer stringBuffer, int i) {
        while (i >= 32) {
            stringBuffer.append(SPACES[5]);
            i -= 32;
        }
        for (int i2 = 4; i2 >= 0; i2--) {
            if (((1 << i2) & i) != 0) {
                stringBuffer.append(SPACES[i2]);
            }
        }
    }
}
