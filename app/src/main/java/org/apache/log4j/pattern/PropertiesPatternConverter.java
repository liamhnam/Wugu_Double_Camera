package org.apache.log4j.pattern;

import java.util.Set;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.MDCKeySetExtractor;
import org.apache.log4j.spi.LoggingEvent;

public final class PropertiesPatternConverter extends LoggingEventPatternConverter {
    private final String option;

    private PropertiesPatternConverter(String[] strArr) {
        super((strArr == null || strArr.length <= 0) ? "Properties" : new StringBuffer("Property{").append(strArr[0]).append("}").toString(), "property");
        if (strArr != null && strArr.length > 0) {
            this.option = strArr[0];
        } else {
            this.option = null;
        }
    }

    public static PropertiesPatternConverter newInstance(String[] strArr) {
        return new PropertiesPatternConverter(strArr);
    }

    @Override
    public void format(LoggingEvent loggingEvent, StringBuffer stringBuffer) {
        String str = this.option;
        if (str == null) {
            stringBuffer.append("{");
            try {
                Set propertyKeySet = MDCKeySetExtractor.INSTANCE.getPropertyKeySet(loggingEvent);
                if (propertyKeySet != null) {
                    for (Object obj : propertyKeySet) {
                        stringBuffer.append("{").append(obj).append(",").append(loggingEvent.getMDC(obj.toString())).append("}");
                    }
                }
            } catch (Exception e) {
                LogLog.error("Unexpected exception while extracting MDC keys", e);
            }
            stringBuffer.append("}");
            return;
        }
        Object mdc = loggingEvent.getMDC(str);
        if (mdc != null) {
            stringBuffer.append(mdc);
        }
    }
}
