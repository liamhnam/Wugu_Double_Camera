package org.apache.log4j;

import org.apache.log4j.helpers.FileWatchdog;

class PropertyWatchdog extends FileWatchdog {
    PropertyWatchdog(String str) {
        super(str);
    }

    @Override
    public void doOnChange() throws Throwable {
        new PropertyConfigurator().doConfigure(this.filename, LogManager.getLoggerRepository());
    }
}
