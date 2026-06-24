package org.xmlpull.p065v1.wrapper;

import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;
import org.xmlpull.p065v1.XmlPullParserFactory;
import org.xmlpull.p065v1.XmlSerializer;
import org.xmlpull.p065v1.wrapper.classic.StaticXmlPullParserWrapper;
import org.xmlpull.p065v1.wrapper.classic.StaticXmlSerializerWrapper;

public class XmlPullWrapperFactory {
    private static final boolean DEBUG = false;

    protected XmlPullParserFactory f3792f;

    public static XmlPullWrapperFactory newInstance() throws XmlPullParserException {
        return new XmlPullWrapperFactory(null);
    }

    public static XmlPullWrapperFactory newInstance(XmlPullParserFactory xmlPullParserFactory) throws XmlPullParserException {
        return new XmlPullWrapperFactory(xmlPullParserFactory);
    }

    public static XmlPullWrapperFactory newInstance(String str, Class cls) throws XmlPullParserException {
        return new XmlPullWrapperFactory(XmlPullParserFactory.newInstance(str, cls));
    }

    protected XmlPullWrapperFactory(XmlPullParserFactory xmlPullParserFactory) throws XmlPullParserException {
        if (xmlPullParserFactory != null) {
            this.f3792f = xmlPullParserFactory;
        } else {
            this.f3792f = XmlPullParserFactory.newInstance();
        }
    }

    public XmlPullParserFactory getFactory() throws XmlPullParserException {
        return this.f3792f;
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        this.f3792f.setFeature(str, z);
    }

    public boolean getFeature(String str) {
        return this.f3792f.getFeature(str);
    }

    public void setNamespaceAware(boolean z) {
        this.f3792f.setNamespaceAware(z);
    }

    public boolean isNamespaceAware() {
        return this.f3792f.isNamespaceAware();
    }

    public void setValidating(boolean z) {
        this.f3792f.setValidating(z);
    }

    public boolean isValidating() {
        return this.f3792f.isValidating();
    }

    public XmlPullParserWrapper newPullParserWrapper() throws XmlPullParserException {
        return new StaticXmlPullParserWrapper(this.f3792f.newPullParser());
    }

    public XmlPullParserWrapper newPullParserWrapper(XmlPullParser xmlPullParser) throws XmlPullParserException {
        return new StaticXmlPullParserWrapper(xmlPullParser);
    }

    public XmlSerializerWrapper newSerializerWrapper() throws XmlPullParserException {
        return new StaticXmlSerializerWrapper(this.f3792f.newSerializer(), this);
    }

    public XmlSerializerWrapper newSerializerWrapper(XmlSerializer xmlSerializer) throws XmlPullParserException {
        return new StaticXmlSerializerWrapper(xmlSerializer, this);
    }
}
