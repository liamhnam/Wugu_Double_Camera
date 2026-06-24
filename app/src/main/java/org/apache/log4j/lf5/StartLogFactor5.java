package org.apache.log4j.lf5;

import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class StartLogFactor5 {
    public static final void main(String[] strArr) {
        LogBrokerMonitor logBrokerMonitor = new LogBrokerMonitor(LogLevel.getLog4JLevels());
        logBrokerMonitor.setFrameSize(LF5Appender.getDefaultMonitorWidth(), LF5Appender.getDefaultMonitorHeight());
        logBrokerMonitor.setFontSize(12);
        logBrokerMonitor.show();
    }
}
