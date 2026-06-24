package org.apache.log4j.rewrite;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public class MapRewritePolicy implements RewritePolicy {
    @Override
    public LoggingEvent rewrite(LoggingEvent loggingEvent) {
        Object message = loggingEvent.getMessage();
        if (!(message instanceof Map)) {
            return loggingEvent;
        }
        HashMap map = new HashMap(loggingEvent.getProperties());
        Map map2 = (Map) message;
        Object obj = map2.get("message");
        Object obj2 = obj == null ? message : obj;
        for (Map.Entry entry : map2.entrySet()) {
            if (!"message".equals(entry.getKey())) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return new LoggingEvent(loggingEvent.getFQNOfLoggerClass(), loggingEvent.getLogger() != null ? loggingEvent.getLogger() : Logger.getLogger(loggingEvent.getLoggerName()), loggingEvent.getTimeStamp(), loggingEvent.getLevel(), obj2, loggingEvent.getThreadName(), loggingEvent.getThrowableInformation(), loggingEvent.getNDC(), loggingEvent.getLocationInformation(), map);
    }
}
