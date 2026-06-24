package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class RelativeTimePatternConverter extends LoggingEventPatternConverter {
    private CachedTimestamp lastTimestamp;

    public RelativeTimePatternConverter() {
        super("Time", "time");
        this.lastTimestamp = new CachedTimestamp(0L, "");
    }

    public static RelativeTimePatternConverter newInstance(String[] strArr) {
        return new RelativeTimePatternConverter();
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        long j = loggingEvent.timeStamp;
        if (this.lastTimestamp.format(j, stringBuffer)) {
            return;
        }
        String string = Long.toString(j - LoggingEvent.getStartTime());
        stringBuffer.append(string);
        this.lastTimestamp = new CachedTimestamp(j, string);
    }

    private static final class CachedTimestamp {
        private final String formatted;
        private final long timestamp;

        public CachedTimestamp(long j, String str) {
            this.timestamp = j;
            this.formatted = str;
        }

        public boolean format(long j, StringBuffer stringBuffer) {
            if (j != this.timestamp) {
                return false;
            }
            stringBuffer.append(this.formatted);
            return true;
        }
    }
}
