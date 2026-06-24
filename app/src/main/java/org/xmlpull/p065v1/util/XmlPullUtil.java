package org.xmlpull.p065v1.util;

import java.io.IOException;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;
import org.xmlpull.p065v1.XmlSerializer;

public class XmlPullUtil {
    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    private static boolean isS(char c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t';
    }

    private XmlPullUtil() {
    }

    public static String getAttributeValue(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("", str);
    }

    public static String getPITarget(XmlPullParser xmlPullParser) throws IllegalStateException {
        try {
            int eventType = xmlPullParser.getEventType();
            if (eventType != 8) {
                throw new IllegalStateException(new StringBuffer("parser must be on processing instruction and not ").append(XmlPullParser.TYPES[eventType]).append(xmlPullParser.getPositionDescription()).toString());
            }
            String text = xmlPullParser.getText();
            for (int i = 0; i < text.length(); i++) {
                if (isS(text.charAt(i))) {
                    return text.substring(0, i);
                }
            }
            return text;
        } catch (XmlPullParserException e) {
            throw new IllegalStateException(new StringBuffer("could not determine parser state: ").append(e).append(xmlPullParser.getPositionDescription()).toString());
        }
    }

    public static String getPIData(XmlPullParser xmlPullParser) throws IllegalStateException {
        try {
            int eventType = xmlPullParser.getEventType();
            if (eventType != 8) {
                throw new IllegalStateException(new StringBuffer("parser must be on processing instruction and not ").append(XmlPullParser.TYPES[eventType]).append(xmlPullParser.getPositionDescription()).toString());
            }
            String text = xmlPullParser.getText();
            int i = -1;
            for (int i2 = 0; i2 < text.length(); i2++) {
                if (isS(text.charAt(i2))) {
                    i = i2;
                } else if (i > 0) {
                    return text.substring(i2);
                }
            }
            return "";
        } catch (XmlPullParserException e) {
            throw new IllegalStateException(new StringBuffer("could not determine parser state: ").append(e).append(xmlPullParser.getPositionDescription()).toString());
        }
    }

    public static void skipSubTree(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, null);
        int i = 1;
        while (i > 0) {
            int next = xmlPullParser.next();
            if (next == 3) {
                i--;
            } else if (next == 2) {
                i++;
            }
        }
    }

    public static void nextStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.nextTag() != 2) {
            throw new XmlPullParserException(new StringBuffer("expected START_TAG and not ").append(xmlPullParser.getPositionDescription()).toString());
        }
    }

    public static void nextStartTag(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.nextTag();
        xmlPullParser.require(2, null, str);
    }

    public static void nextStartTag(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        xmlPullParser.nextTag();
        xmlPullParser.require(2, str, str2);
    }

    public static void nextEndTag(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        xmlPullParser.nextTag();
        xmlPullParser.require(3, str, str2);
    }

    public static String nextText(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        if (str2 == null) {
            throw new XmlPullParserException("name for element can not be null");
        }
        xmlPullParser.require(2, str, str2);
        return xmlPullParser.nextText();
    }

    public static String getRequiredAttributeValue(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(str, str2);
        if (attributeValue != null) {
            return attributeValue;
        }
        throw new XmlPullParserException(new StringBuffer("required attribute ").append(str2).append(" is not present").toString());
    }

    public static void nextEndTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.nextTag() != 3) {
            throw new XmlPullParserException(new StringBuffer("expected END_TAG and not").append(xmlPullParser.getPositionDescription()).toString());
        }
    }

    public static boolean matches(XmlPullParser xmlPullParser, int i, String str, String str2) throws XmlPullParserException {
        return i == xmlPullParser.getEventType() && (str == null || str.equals(xmlPullParser.getNamespace())) && (str2 == null || str2.equals(xmlPullParser.getName()));
    }

    public static void writeSimpleElement(XmlSerializer xmlSerializer, String str, String str2, String str3) throws XmlPullParserException, IOException {
        if (str2 == null) {
            throw new XmlPullParserException("name for element can not be null");
        }
        xmlSerializer.startTag(str, str2);
        if (str3 == null) {
            xmlSerializer.attribute("http://www.w3.org/2001/XMLSchema-instance", "nil", "true");
        } else {
            xmlSerializer.text(str3);
        }
        xmlSerializer.endTag(str, str2);
    }
}
