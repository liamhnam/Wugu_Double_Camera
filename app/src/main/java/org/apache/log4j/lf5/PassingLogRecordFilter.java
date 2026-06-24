package org.apache.log4j.lf5;

public class PassingLogRecordFilter implements LogRecordFilter {
    @Override
    public boolean passes(LogRecord logRecord) {
        return true;
    }

    public void reset() {
    }
}
