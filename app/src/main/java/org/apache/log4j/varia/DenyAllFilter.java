package org.apache.log4j.varia;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class DenyAllFilter extends Filter {
    @Override
    public int decide(LoggingEvent loggingEvent) {
        return -1;
    }

    public String[] getOptionStrings() {
        return null;
    }

    public void setOption(String str, String str2) {
    }
}
