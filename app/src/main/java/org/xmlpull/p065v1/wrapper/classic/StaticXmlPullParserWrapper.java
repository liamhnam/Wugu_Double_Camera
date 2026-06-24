package org.xmlpull.p065v1.wrapper.classic;

import java.io.IOException;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;
import org.xmlpull.p065v1.util.XmlPullUtil;
import org.xmlpull.p065v1.wrapper.XmlPullParserWrapper;

public class StaticXmlPullParserWrapper extends XmlPullParserDelegate implements XmlPullParserWrapper {
    public StaticXmlPullParserWrapper(XmlPullParser xmlPullParser) {
        super(xmlPullParser);
    }

    @Override
    public String getAttributeValue(String str) {
        return XmlPullUtil.getAttributeValue(this.f3794pp, str);
    }

    @Override
    public String getRequiredAttributeValue(String str) throws XmlPullParserException, IOException {
        return XmlPullUtil.getRequiredAttributeValue(this.f3794pp, null, str);
    }

    @Override
    public String getRequiredAttributeValue(String str, String str2) throws XmlPullParserException, IOException {
        return XmlPullUtil.getRequiredAttributeValue(this.f3794pp, str, str2);
    }

    @Override
    public String getRequiredElementText(String str, String str2) throws XmlPullParserException, IOException {
        String strNextText;
        if (str2 == null) {
            throw new XmlPullParserException("name for element can not be null");
        }
        nextStartTag(str, str2);
        if (isNil()) {
            nextEndTag(str, str2);
            strNextText = null;
        } else {
            strNextText = this.f3794pp.nextText();
        }
        this.f3794pp.require(3, str, str2);
        return strNextText;
    }

    @Override
    public boolean isNil() throws XmlPullParserException, IOException {
        return "true".equals(this.f3794pp.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil"));
    }

    @Override
    public String getPITarget() throws IllegalStateException {
        return XmlPullUtil.getPITarget(this.f3794pp);
    }

    @Override
    public String getPIData() throws IllegalStateException {
        return XmlPullUtil.getPIData(this.f3794pp);
    }

    @Override
    public boolean matches(int i, String str, String str2) throws XmlPullParserException {
        return XmlPullUtil.matches(this.f3794pp, i, str, str2);
    }

    @Override
    public void nextStartTag() throws XmlPullParserException, IOException {
        if (this.f3794pp.nextTag() != 2) {
            throw new XmlPullParserException(new StringBuffer("expected START_TAG and not ").append(this.f3794pp.getPositionDescription()).toString());
        }
    }

    @Override
    public void nextStartTag(String str) throws XmlPullParserException, IOException {
        this.f3794pp.nextTag();
        this.f3794pp.require(2, null, str);
    }

    @Override
    public void nextStartTag(String str, String str2) throws XmlPullParserException, IOException {
        this.f3794pp.nextTag();
        this.f3794pp.require(2, str, str2);
    }

    @Override
    public void nextEndTag() throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.f3794pp);
    }

    @Override
    public void nextEndTag(String str) throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.f3794pp, null, str);
    }

    @Override
    public void nextEndTag(String str, String str2) throws XmlPullParserException, IOException {
        XmlPullUtil.nextEndTag(this.f3794pp, str, str2);
    }

    @Override
    public String nextText(String str, String str2) throws XmlPullParserException, IOException {
        return XmlPullUtil.nextText(this.f3794pp, str, str2);
    }

    @Override
    public void skipSubTree() throws XmlPullParserException, IOException {
        XmlPullUtil.skipSubTree(this.f3794pp);
    }

    @Override
    public double readDouble() throws XmlPullParserException, IOException {
        String strNextText = this.f3794pp.nextText();
        try {
            return Double.parseDouble(strNextText);
        } catch (NumberFormatException e) {
            if (strNextText.equals("INF") || strNextText.toLowerCase().equals("infinity")) {
                return Double.POSITIVE_INFINITY;
            }
            if (strNextText.equals("-INF") || strNextText.toLowerCase().equals("-infinity")) {
                return Double.NEGATIVE_INFINITY;
            }
            if (strNextText.equals("NaN")) {
                return Double.NaN;
            }
            throw new XmlPullParserException(new StringBuffer("can't parse double value '").append(strNextText).append("'").toString(), this, e);
        }
    }

    @Override
    public float readFloat() throws XmlPullParserException, IOException {
        String strNextText = this.f3794pp.nextText();
        try {
            return Float.parseFloat(strNextText);
        } catch (NumberFormatException e) {
            if (strNextText.equals("INF") || strNextText.toLowerCase().equals("infinity")) {
                return Float.POSITIVE_INFINITY;
            }
            if (strNextText.equals("-INF") || strNextText.toLowerCase().equals("-infinity")) {
                return Float.NEGATIVE_INFINITY;
            }
            if (strNextText.equals("NaN")) {
                return Float.NaN;
            }
            throw new XmlPullParserException(new StringBuffer("can't parse float value '").append(strNextText).append("'").toString(), this, e);
        }
    }

    @Override
    public int readInt() throws XmlPullParserException, IOException {
        try {
            return Integer.parseInt(this.f3794pp.nextText());
        } catch (NumberFormatException e) {
            throw new XmlPullParserException("can't parse int value", this, e);
        }
    }

    @Override
    public String readString() throws XmlPullParserException, IOException {
        return this.f3794pp.nextText();
    }

    @Override
    public double readDoubleElement(String str, String str2) throws XmlPullParserException, IOException {
        this.f3794pp.require(2, str, str2);
        return readDouble();
    }

    @Override
    public float readFloatElement(String str, String str2) throws XmlPullParserException, IOException {
        this.f3794pp.require(2, str, str2);
        return readFloat();
    }

    @Override
    public int readIntElement(String str, String str2) throws XmlPullParserException, IOException {
        this.f3794pp.require(2, str, str2);
        return readInt();
    }

    @Override
    public String readStringElemet(String str, String str2) throws XmlPullParserException, IOException {
        this.f3794pp.require(2, str, str2);
        return readString();
    }
}
