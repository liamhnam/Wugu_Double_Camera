package org.apache.log4j.pattern;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Date;

public final class IntegerPatternConverter extends PatternConverter {
    private static final IntegerPatternConverter INSTANCE = new IntegerPatternConverter();

    private IntegerPatternConverter() {
        super("Integer", TypedValues.Custom.S_INT);
    }

    public static IntegerPatternConverter newInstance(String[] strArr) {
        return INSTANCE;
    }

    @Override
    public void format(Object obj, StringBuffer stringBuffer) {
        if (obj instanceof Integer) {
            stringBuffer.append(obj.toString());
        }
        if (obj instanceof Date) {
            stringBuffer.append(Long.toString(((Date) obj).getTime()));
        }
    }
}
