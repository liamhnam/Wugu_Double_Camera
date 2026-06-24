package org.apache.log4j.varia;

import org.apache.log4j.Level;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class LevelRangeFilter extends Filter {
    boolean acceptOnMatch = false;
    Level levelMax;
    Level levelMin;

    @Override
    public int decide(LoggingEvent loggingEvent) {
        if (this.levelMin != null && !loggingEvent.getLevel().isGreaterOrEqual(this.levelMin)) {
            return -1;
        }
        if (this.levelMax == null || loggingEvent.getLevel().toInt() <= this.levelMax.toInt()) {
            return this.acceptOnMatch ? 1 : 0;
        }
        return -1;
    }

    public Level getLevelMax() {
        return this.levelMax;
    }

    public Level getLevelMin() {
        return this.levelMin;
    }

    public boolean getAcceptOnMatch() {
        return this.acceptOnMatch;
    }

    public void setLevelMax(Level level) {
        this.levelMax = level;
    }

    public void setLevelMin(Level level) {
        this.levelMin = level;
    }

    public void setAcceptOnMatch(boolean z) {
        this.acceptOnMatch = z;
    }
}
