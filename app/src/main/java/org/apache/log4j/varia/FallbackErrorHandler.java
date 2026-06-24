package org.apache.log4j.varia;

import java.io.InterruptedIOException;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class FallbackErrorHandler implements ErrorHandler {
    Appender backup;
    Vector loggers;
    Appender primary;

    @Override
    public void activateOptions() {
    }

    @Override
    public void error(String str) {
    }

    @Override
    public void setLogger(Logger logger) {
        LogLog.debug(new StringBuffer("FB: Adding logger [").append(logger.getName()).append("].").toString());
        if (this.loggers == null) {
            this.loggers = new Vector();
        }
        this.loggers.addElement(logger);
    }

    @Override
    public void error(String str, Exception exc, int i) {
        error(str, exc, i, null);
    }

    @Override
    public void error(String str, Exception exc, int i, LoggingEvent loggingEvent) {
        if (exc instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
        }
        LogLog.debug(new StringBuffer("FB: The following error reported: ").append(str).toString(), exc);
        LogLog.debug("FB: INITIATING FALLBACK PROCEDURE.");
        if (this.loggers != null) {
            for (int i2 = 0; i2 < this.loggers.size(); i2++) {
                Logger logger = (Logger) this.loggers.elementAt(i2);
                LogLog.debug(new StringBuffer("FB: Searching for [").append(this.primary.getName()).append("] in logger [").append(logger.getName()).append("].").toString());
                LogLog.debug(new StringBuffer("FB: Replacing [").append(this.primary.getName()).append("] by [").append(this.backup.getName()).append("] in logger [").append(logger.getName()).append("].").toString());
                logger.removeAppender(this.primary);
                LogLog.debug(new StringBuffer("FB: Adding appender [").append(this.backup.getName()).append("] to logger ").append(logger.getName()).toString());
                logger.addAppender(this.backup);
            }
        }
    }

    @Override
    public void setAppender(Appender appender) {
        LogLog.debug(new StringBuffer("FB: Setting primary appender to [").append(appender.getName()).append("].").toString());
        this.primary = appender;
    }

    @Override
    public void setBackupAppender(Appender appender) {
        LogLog.debug(new StringBuffer("FB: Setting backup appender to [").append(appender.getName()).append("].").toString());
        this.backup = appender;
    }
}
