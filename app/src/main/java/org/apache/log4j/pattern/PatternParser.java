package org.apache.log4j.pattern;

import com.arthenica.ffmpegkit.StreamInformation;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.p020hp.jipp.model.MaterialAmountUnit;
import com.p020hp.jipp.model.Media;
import com.p020hp.jipp.model.PrintRenderingIntent;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.simpleframework.xml.strategy.Name;

public final class PatternParser {
    private static final int CONVERTER_STATE = 1;
    private static final int DOT_STATE = 3;
    private static final char ESCAPE_CHAR = '%';
    private static final Map FILENAME_PATTERN_RULES;
    private static final int LITERAL_STATE = 0;
    private static final int MAX_STATE = 5;
    private static final int MIN_STATE = 4;
    private static final Map PATTERN_LAYOUT_RULES;
    static Class class$org$apache$log4j$pattern$ClassNamePatternConverter;
    static Class class$org$apache$log4j$pattern$DatePatternConverter;
    static Class class$org$apache$log4j$pattern$FileDatePatternConverter;
    static Class class$org$apache$log4j$pattern$FileLocationPatternConverter;
    static Class class$org$apache$log4j$pattern$FullLocationPatternConverter;
    static Class class$org$apache$log4j$pattern$IntegerPatternConverter;
    static Class class$org$apache$log4j$pattern$LevelPatternConverter;
    static Class class$org$apache$log4j$pattern$LineLocationPatternConverter;
    static Class class$org$apache$log4j$pattern$LineSeparatorPatternConverter;
    static Class class$org$apache$log4j$pattern$LoggerPatternConverter;
    static Class class$org$apache$log4j$pattern$MessagePatternConverter;
    static Class class$org$apache$log4j$pattern$MethodLocationPatternConverter;
    static Class class$org$apache$log4j$pattern$NDCPatternConverter;
    static Class class$org$apache$log4j$pattern$PropertiesPatternConverter;
    static Class class$org$apache$log4j$pattern$RelativeTimePatternConverter;
    static Class class$org$apache$log4j$pattern$SequenceNumberPatternConverter;
    static Class class$org$apache$log4j$pattern$ThreadPatternConverter;

    static Class f2799x905a6f24;

    static {
        HashMap map = new HashMap(17);
        Class clsClass$ = class$org$apache$log4j$pattern$LoggerPatternConverter;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.pattern.LoggerPatternConverter");
            class$org$apache$log4j$pattern$LoggerPatternConverter = clsClass$;
        }
        map.put(Media.f727c, clsClass$);
        Class clsClass$2 = class$org$apache$log4j$pattern$LoggerPatternConverter;
        if (clsClass$2 == null) {
            clsClass$2 = class$("org.apache.log4j.pattern.LoggerPatternConverter");
            class$org$apache$log4j$pattern$LoggerPatternConverter = clsClass$2;
        }
        map.put("logger", clsClass$2);
        Class clsClass$3 = class$org$apache$log4j$pattern$ClassNamePatternConverter;
        if (clsClass$3 == null) {
            clsClass$3 = class$("org.apache.log4j.pattern.ClassNamePatternConverter");
            class$org$apache$log4j$pattern$ClassNamePatternConverter = clsClass$3;
        }
        map.put("C", clsClass$3);
        Class clsClass$4 = class$org$apache$log4j$pattern$ClassNamePatternConverter;
        if (clsClass$4 == null) {
            clsClass$4 = class$("org.apache.log4j.pattern.ClassNamePatternConverter");
            class$org$apache$log4j$pattern$ClassNamePatternConverter = clsClass$4;
        }
        map.put(Name.LABEL, clsClass$4);
        Class clsClass$5 = class$org$apache$log4j$pattern$DatePatternConverter;
        if (clsClass$5 == null) {
            clsClass$5 = class$("org.apache.log4j.pattern.DatePatternConverter");
            class$org$apache$log4j$pattern$DatePatternConverter = clsClass$5;
        }
        map.put(Media.f728d, clsClass$5);
        Class clsClass$6 = class$org$apache$log4j$pattern$DatePatternConverter;
        if (clsClass$6 == null) {
            clsClass$6 = class$("org.apache.log4j.pattern.DatePatternConverter");
            class$org$apache$log4j$pattern$DatePatternConverter = clsClass$6;
        }
        map.put("date", clsClass$6);
        Class clsClass$7 = class$org$apache$log4j$pattern$FileLocationPatternConverter;
        if (clsClass$7 == null) {
            clsClass$7 = class$("org.apache.log4j.pattern.FileLocationPatternConverter");
            class$org$apache$log4j$pattern$FileLocationPatternConverter = clsClass$7;
        }
        map.put(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, clsClass$7);
        Class clsClass$8 = class$org$apache$log4j$pattern$FileLocationPatternConverter;
        if (clsClass$8 == null) {
            clsClass$8 = class$("org.apache.log4j.pattern.FileLocationPatternConverter");
            class$org$apache$log4j$pattern$FileLocationPatternConverter = clsClass$8;
        }
        map.put("file", clsClass$8);
        Class clsClass$9 = class$org$apache$log4j$pattern$FullLocationPatternConverter;
        if (clsClass$9 == null) {
            clsClass$9 = class$("org.apache.log4j.pattern.FullLocationPatternConverter");
            class$org$apache$log4j$pattern$FullLocationPatternConverter = clsClass$9;
        }
        map.put(MaterialAmountUnit.f721l, clsClass$9);
        Class clsClass$10 = class$org$apache$log4j$pattern$LineLocationPatternConverter;
        if (clsClass$10 == null) {
            clsClass$10 = class$("org.apache.log4j.pattern.LineLocationPatternConverter");
            class$org$apache$log4j$pattern$LineLocationPatternConverter = clsClass$10;
        }
        map.put("L", clsClass$10);
        Class clsClass$11 = class$org$apache$log4j$pattern$LineLocationPatternConverter;
        if (clsClass$11 == null) {
            clsClass$11 = class$("org.apache.log4j.pattern.LineLocationPatternConverter");
            class$org$apache$log4j$pattern$LineLocationPatternConverter = clsClass$11;
        }
        map.put("line", clsClass$11);
        Class clsClass$12 = class$org$apache$log4j$pattern$MessagePatternConverter;
        if (clsClass$12 == null) {
            clsClass$12 = class$("org.apache.log4j.pattern.MessagePatternConverter");
            class$org$apache$log4j$pattern$MessagePatternConverter = clsClass$12;
        }
        map.put(MaterialAmountUnit.f722m, clsClass$12);
        Class clsClass$13 = class$org$apache$log4j$pattern$MessagePatternConverter;
        if (clsClass$13 == null) {
            clsClass$13 = class$("org.apache.log4j.pattern.MessagePatternConverter");
            class$org$apache$log4j$pattern$MessagePatternConverter = clsClass$13;
        }
        map.put("message", clsClass$13);
        Class clsClass$14 = class$org$apache$log4j$pattern$LineSeparatorPatternConverter;
        if (clsClass$14 == null) {
            clsClass$14 = class$("org.apache.log4j.pattern.LineSeparatorPatternConverter");
            class$org$apache$log4j$pattern$LineSeparatorPatternConverter = clsClass$14;
        }
        map.put("n", clsClass$14);
        Class clsClass$15 = class$org$apache$log4j$pattern$MethodLocationPatternConverter;
        if (clsClass$15 == null) {
            clsClass$15 = class$("org.apache.log4j.pattern.MethodLocationPatternConverter");
            class$org$apache$log4j$pattern$MethodLocationPatternConverter = clsClass$15;
        }
        map.put("M", clsClass$15);
        Class clsClass$16 = class$org$apache$log4j$pattern$MethodLocationPatternConverter;
        if (clsClass$16 == null) {
            clsClass$16 = class$("org.apache.log4j.pattern.MethodLocationPatternConverter");
            class$org$apache$log4j$pattern$MethodLocationPatternConverter = clsClass$16;
        }
        map.put("method", clsClass$16);
        Class clsClass$17 = class$org$apache$log4j$pattern$LevelPatternConverter;
        if (clsClass$17 == null) {
            clsClass$17 = class$("org.apache.log4j.pattern.LevelPatternConverter");
            class$org$apache$log4j$pattern$LevelPatternConverter = clsClass$17;
        }
        map.put(TtmlNode.TAG_P, clsClass$17);
        Class clsClass$18 = class$org$apache$log4j$pattern$LevelPatternConverter;
        if (clsClass$18 == null) {
            clsClass$18 = class$("org.apache.log4j.pattern.LevelPatternConverter");
            class$org$apache$log4j$pattern$LevelPatternConverter = clsClass$18;
        }
        map.put("level", clsClass$18);
        Class clsClass$19 = class$org$apache$log4j$pattern$RelativeTimePatternConverter;
        if (clsClass$19 == null) {
            clsClass$19 = class$("org.apache.log4j.pattern.RelativeTimePatternConverter");
            class$org$apache$log4j$pattern$RelativeTimePatternConverter = clsClass$19;
        }
        map.put(PDPageLabelRange.STYLE_ROMAN_LOWER, clsClass$19);
        Class clsClass$20 = class$org$apache$log4j$pattern$RelativeTimePatternConverter;
        if (clsClass$20 == null) {
            clsClass$20 = class$("org.apache.log4j.pattern.RelativeTimePatternConverter");
            class$org$apache$log4j$pattern$RelativeTimePatternConverter = clsClass$20;
        }
        map.put(PrintRenderingIntent.relative, clsClass$20);
        Class clsClass$21 = class$org$apache$log4j$pattern$ThreadPatternConverter;
        if (clsClass$21 == null) {
            clsClass$21 = class$("org.apache.log4j.pattern.ThreadPatternConverter");
            class$org$apache$log4j$pattern$ThreadPatternConverter = clsClass$21;
        }
        map.put("t", clsClass$21);
        Class clsClass$22 = class$org$apache$log4j$pattern$ThreadPatternConverter;
        if (clsClass$22 == null) {
            clsClass$22 = class$("org.apache.log4j.pattern.ThreadPatternConverter");
            class$org$apache$log4j$pattern$ThreadPatternConverter = clsClass$22;
        }
        map.put("thread", clsClass$22);
        Class clsClass$23 = class$org$apache$log4j$pattern$NDCPatternConverter;
        if (clsClass$23 == null) {
            clsClass$23 = class$("org.apache.log4j.pattern.NDCPatternConverter");
            class$org$apache$log4j$pattern$NDCPatternConverter = clsClass$23;
        }
        map.put("x", clsClass$23);
        Class clsClass$24 = class$org$apache$log4j$pattern$NDCPatternConverter;
        if (clsClass$24 == null) {
            clsClass$24 = class$("org.apache.log4j.pattern.NDCPatternConverter");
            class$org$apache$log4j$pattern$NDCPatternConverter = clsClass$24;
        }
        map.put("ndc", clsClass$24);
        Class clsClass$25 = class$org$apache$log4j$pattern$PropertiesPatternConverter;
        if (clsClass$25 == null) {
            clsClass$25 = class$("org.apache.log4j.pattern.PropertiesPatternConverter");
            class$org$apache$log4j$pattern$PropertiesPatternConverter = clsClass$25;
        }
        map.put("X", clsClass$25);
        Class clsClass$26 = class$org$apache$log4j$pattern$PropertiesPatternConverter;
        if (clsClass$26 == null) {
            clsClass$26 = class$("org.apache.log4j.pattern.PropertiesPatternConverter");
            class$org$apache$log4j$pattern$PropertiesPatternConverter = clsClass$26;
        }
        map.put("properties", clsClass$26);
        Class clsClass$27 = class$org$apache$log4j$pattern$SequenceNumberPatternConverter;
        if (clsClass$27 == null) {
            clsClass$27 = class$("org.apache.log4j.pattern.SequenceNumberPatternConverter");
            class$org$apache$log4j$pattern$SequenceNumberPatternConverter = clsClass$27;
        }
        map.put("sn", clsClass$27);
        Class clsClass$28 = class$org$apache$log4j$pattern$SequenceNumberPatternConverter;
        if (clsClass$28 == null) {
            clsClass$28 = class$("org.apache.log4j.pattern.SequenceNumberPatternConverter");
            class$org$apache$log4j$pattern$SequenceNumberPatternConverter = clsClass$28;
        }
        map.put("sequenceNumber", clsClass$28);
        Class clsClass$29 = f2799x905a6f24;
        if (clsClass$29 == null) {
            clsClass$29 = class$("org.apache.log4j.pattern.ThrowableInformationPatternConverter");
            f2799x905a6f24 = clsClass$29;
        }
        map.put("throwable", clsClass$29);
        PATTERN_LAYOUT_RULES = new ReadOnlyMap(map);
        HashMap map2 = new HashMap(4);
        Class clsClass$30 = class$org$apache$log4j$pattern$FileDatePatternConverter;
        if (clsClass$30 == null) {
            clsClass$30 = class$("org.apache.log4j.pattern.FileDatePatternConverter");
            class$org$apache$log4j$pattern$FileDatePatternConverter = clsClass$30;
        }
        map2.put(Media.f728d, clsClass$30);
        Class clsClass$31 = class$org$apache$log4j$pattern$FileDatePatternConverter;
        if (clsClass$31 == null) {
            clsClass$31 = class$("org.apache.log4j.pattern.FileDatePatternConverter");
            class$org$apache$log4j$pattern$FileDatePatternConverter = clsClass$31;
        }
        map2.put("date", clsClass$31);
        Class clsClass$32 = class$org$apache$log4j$pattern$IntegerPatternConverter;
        if (clsClass$32 == null) {
            clsClass$32 = class$("org.apache.log4j.pattern.IntegerPatternConverter");
            class$org$apache$log4j$pattern$IntegerPatternConverter = clsClass$32;
        }
        map2.put("i", clsClass$32);
        Class clsClass$33 = class$org$apache$log4j$pattern$IntegerPatternConverter;
        if (clsClass$33 == null) {
            clsClass$33 = class$("org.apache.log4j.pattern.IntegerPatternConverter");
            class$org$apache$log4j$pattern$IntegerPatternConverter = clsClass$33;
        }
        map2.put(StreamInformation.KEY_INDEX, clsClass$33);
        FILENAME_PATTERN_RULES = new ReadOnlyMap(map2);
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private PatternParser() {
    }

    public static Map getPatternLayoutRules() {
        return PATTERN_LAYOUT_RULES;
    }

    public static Map getFileNamePatternRules() {
        return FILENAME_PATTERN_RULES;
    }

    private static int extractConverter(char c, String str, int i, StringBuffer stringBuffer, StringBuffer stringBuffer2) {
        stringBuffer.setLength(0);
        if (!Character.isUnicodeIdentifierStart(c)) {
            return i;
        }
        stringBuffer.append(c);
        while (i < str.length() && Character.isUnicodeIdentifierPart(str.charAt(i))) {
            stringBuffer.append(str.charAt(i));
            stringBuffer2.append(str.charAt(i));
            i++;
        }
        return i;
    }

    private static int extractOptions(String str, int i, List list) {
        int iIndexOf;
        while (i < str.length() && str.charAt(i) == '{' && (iIndexOf = str.indexOf(125, i)) != -1) {
            list.add(str.substring(i + 1, iIndexOf));
            i = iIndexOf + 1;
        }
        return i;
    }

    public static void parse(String str, List list, List list2, Map map, Map map2) {
        FormattingInfo formattingInfo;
        if (str == null) {
            throw new NullPointerException("pattern");
        }
        StringBuffer stringBuffer = new StringBuffer(32);
        int length = str.length();
        FormattingInfo formattingInfo2 = FormattingInfo.getDefault();
        int iFinalizeConverter = 0;
        char c = 0;
        while (iFinalizeConverter < length) {
            int i = iFinalizeConverter + 1;
            char cCharAt = str.charAt(iFinalizeConverter);
            char c2 = 1;
            if (c == 0) {
                if (i == length) {
                    stringBuffer.append(cCharAt);
                } else if (cCharAt == '%') {
                    if (str.charAt(i) == '%') {
                        stringBuffer.append(cCharAt);
                        i++;
                    } else {
                        if (stringBuffer.length() != 0) {
                            list.add(new LiteralPatternConverter(stringBuffer.toString()));
                            list2.add(FormattingInfo.getDefault());
                        }
                        stringBuffer.setLength(0);
                        stringBuffer.append(cCharAt);
                        formattingInfo2 = FormattingInfo.getDefault();
                        iFinalizeConverter = i;
                        c = c2;
                    }
                } else {
                    stringBuffer.append(cCharAt);
                }
                iFinalizeConverter = i;
            } else if (c != 1) {
                c2 = 5;
                if (c == 3) {
                    stringBuffer.append(cCharAt);
                    if (cCharAt >= '0' && cCharAt <= '9') {
                        formattingInfo2 = new FormattingInfo(formattingInfo2.isLeftAligned(), formattingInfo2.getMinLength(), cCharAt - '0');
                        iFinalizeConverter = i;
                        c = c2;
                    } else {
                        LogLog.error(new StringBuffer("Error occured in position ").append(i).append(".\n Was expecting digit, instead got char \"").append(cCharAt).append("\".").toString());
                        iFinalizeConverter = i;
                    }
                } else if (c == 4) {
                    stringBuffer.append(cCharAt);
                    if (cCharAt >= '0' && cCharAt <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo2.isLeftAligned(), (formattingInfo2.getMinLength() * 10) + (cCharAt - '0'), formattingInfo2.getMaxLength());
                        iFinalizeConverter = i;
                        formattingInfo2 = formattingInfo;
                    } else if (cCharAt == '.') {
                        iFinalizeConverter = i;
                        c = 3;
                    } else {
                        iFinalizeConverter = finalizeConverter(cCharAt, str, i, stringBuffer, formattingInfo2, map, map2, list, list2);
                        formattingInfo2 = FormattingInfo.getDefault();
                        stringBuffer.setLength(0);
                    }
                } else if (c != 5) {
                    iFinalizeConverter = i;
                } else {
                    stringBuffer.append(cCharAt);
                    if (cCharAt >= '0' && cCharAt <= '9') {
                        formattingInfo = new FormattingInfo(formattingInfo2.isLeftAligned(), formattingInfo2.getMinLength(), (formattingInfo2.getMaxLength() * 10) + (cCharAt - '0'));
                        iFinalizeConverter = i;
                        formattingInfo2 = formattingInfo;
                    } else {
                        iFinalizeConverter = finalizeConverter(cCharAt, str, i, stringBuffer, formattingInfo2, map, map2, list, list2);
                        formattingInfo2 = FormattingInfo.getDefault();
                        stringBuffer.setLength(0);
                    }
                }
                c = 0;
            } else {
                stringBuffer.append(cCharAt);
                if (cCharAt == '-') {
                    formattingInfo2 = new FormattingInfo(true, formattingInfo2.getMinLength(), formattingInfo2.getMaxLength());
                    iFinalizeConverter = i;
                } else if (cCharAt == '.') {
                    iFinalizeConverter = i;
                    c = 3;
                } else if (cCharAt >= '0' && cCharAt <= '9') {
                    formattingInfo2 = new FormattingInfo(formattingInfo2.isLeftAligned(), cCharAt - '0', formattingInfo2.getMaxLength());
                    iFinalizeConverter = i;
                    c = 4;
                } else {
                    iFinalizeConverter = finalizeConverter(cCharAt, str, i, stringBuffer, formattingInfo2, map, map2, list, list2);
                    formattingInfo2 = FormattingInfo.getDefault();
                    stringBuffer.setLength(0);
                    c = 0;
                }
            }
        }
        if (stringBuffer.length() != 0) {
            list.add(new LiteralPatternConverter(stringBuffer.toString()));
            list2.add(FormattingInfo.getDefault());
        }
    }

    private static PatternConverter createConverter(String str, StringBuffer stringBuffer, Map map, Map map2, List list) {
        Class clsLoadClass;
        String strSubstring = str;
        Object obj = null;
        for (int length = str.length(); length > 0 && obj == null; length--) {
            strSubstring = strSubstring.substring(0, length);
            if (map != null) {
                obj = map.get(strSubstring);
            }
            if (obj == null && map2 != null) {
                obj = map2.get(strSubstring);
            }
        }
        if (obj == null) {
            LogLog.error(new StringBuffer("Unrecognized format specifier [").append(str).append("]").toString());
            return null;
        }
        if (obj instanceof Class) {
            clsLoadClass = (Class) obj;
        } else if (obj instanceof String) {
            try {
                clsLoadClass = Loader.loadClass((String) obj);
            } catch (ClassNotFoundException e) {
                LogLog.warn(new StringBuffer("Class for conversion pattern %").append(strSubstring).append(" not found").toString(), e);
                return null;
            }
        } else {
            LogLog.warn(new StringBuffer("Bad map entry for conversion pattern %").append(strSubstring).append(".").toString());
            return null;
        }
        try {
            Object objInvoke = clsLoadClass.getMethod("newInstance", Class.forName("[Ljava.lang.String;")).invoke(null, (String[]) list.toArray(new String[list.size()]));
            if (objInvoke instanceof PatternConverter) {
                stringBuffer.delete(0, stringBuffer.length() - (str.length() - strSubstring.length()));
                return (PatternConverter) objInvoke;
            }
            LogLog.warn(new StringBuffer().append("Class ").append(clsLoadClass.getName()).append(" does not extend PatternConverter.").toString());
            return null;
        } catch (Exception e2) {
            LogLog.error(new StringBuffer("Error creating converter for ").append(str).toString(), e2);
            try {
                PatternConverter patternConverter = (PatternConverter) clsLoadClass.newInstance();
                stringBuffer.delete(0, stringBuffer.length() - (str.length() - strSubstring.length()));
                return patternConverter;
            } catch (Exception e3) {
                LogLog.error(new StringBuffer("Error creating converter for ").append(str).toString(), e3);
            }
        }
    }

    private static int finalizeConverter(char c, String str, int i, StringBuffer stringBuffer, FormattingInfo formattingInfo, Map map, Map map2, List list, List list2) {
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3 = new StringBuffer();
        int iExtractConverter = extractConverter(c, str, i, stringBuffer3, stringBuffer);
        String string = stringBuffer3.toString();
        ArrayList arrayList = new ArrayList();
        int iExtractOptions = extractOptions(str, iExtractConverter, arrayList);
        PatternConverter patternConverterCreateConverter = createConverter(string, stringBuffer, map, map2, arrayList);
        if (patternConverterCreateConverter == null) {
            if (string == null || string.length() == 0) {
                stringBuffer2 = new StringBuffer("Empty conversion specifier starting at position ");
            } else {
                stringBuffer2 = new StringBuffer("Unrecognized conversion specifier [");
                stringBuffer2.append(string);
                stringBuffer2.append("] starting at position ");
            }
            stringBuffer2.append(Integer.toString(iExtractOptions));
            stringBuffer2.append(" in conversion pattern.");
            LogLog.error(stringBuffer2.toString());
            list.add(new LiteralPatternConverter(stringBuffer.toString()));
            list2.add(FormattingInfo.getDefault());
        } else {
            list.add(patternConverterCreateConverter);
            list2.add(formattingInfo);
            if (stringBuffer.length() > 0) {
                list.add(new LiteralPatternConverter(stringBuffer.toString()));
                list2.add(FormattingInfo.getDefault());
            }
        }
        stringBuffer.setLength(0);
        return iExtractOptions;
    }

    private static class ReadOnlyMap implements Map {
        private final Map map;

        public ReadOnlyMap(Map map) {
            this.map = map;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsKey(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override
        public boolean containsValue(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override
        public Set entrySet() {
            return this.map.entrySet();
        }

        @Override
        public Object get(Object obj) {
            return this.map.get(obj);
        }

        @Override
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override
        public Set keySet() {
            return this.map.keySet();
        }

        @Override
        public Object put(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Map map) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        public Collection values() {
            return this.map.values();
        }
    }
}
