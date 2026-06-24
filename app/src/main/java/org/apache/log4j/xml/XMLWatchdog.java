package org.apache.log4j.xml;

import org.apache.log4j.LogManager;
import org.apache.log4j.helpers.FileWatchdog;

class XMLWatchdog extends FileWatchdog {
    XMLWatchdog(String str) {
        super(str);
    }

    @Override
    public void doOnChange() {
        new DOMConfigurator().doConfigure(this.filename, LogManager.getLoggerRepository());
    }
}
