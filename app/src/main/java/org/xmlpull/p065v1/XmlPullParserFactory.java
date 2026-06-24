package org.xmlpull.p065v1;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class XmlPullParserFactory {
    public static final String PROPERTY_NAME = "org.xmlpull.v1.XmlPullParserFactory";
    private static final String RESOURCE_NAME = "/META-INF/services/org.xmlpull.v1.XmlPullParserFactory";
    static final Class referenceContextClass = new XmlPullParserFactory().getClass();
    protected String classNamesLocation;
    protected Hashtable features = new Hashtable();
    protected Vector parserClasses;
    protected Vector serializerClasses;

    protected XmlPullParserFactory() {
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        this.features.put(str, new Boolean(z));
    }

    public boolean getFeature(String str) {
        Boolean bool = (Boolean) this.features.get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public void setNamespaceAware(boolean z) {
        this.features.put("http://xmlpull.org/v1/doc/features.html#process-namespaces", new Boolean(z));
    }

    public boolean isNamespaceAware() {
        return getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
    }

    public void setValidating(boolean z) {
        this.features.put(XmlPullParser.FEATURE_VALIDATION, new Boolean(z));
    }

    public boolean isValidating() {
        return getFeature(XmlPullParser.FEATURE_VALIDATION);
    }

    public XmlPullParser newPullParser() throws XmlPullParserException {
        Vector vector = this.parserClasses;
        if (vector == null) {
            throw new XmlPullParserException(new StringBuffer("Factory initialization was incomplete - has not tried ").append(this.classNamesLocation).toString());
        }
        if (vector.size() == 0) {
            throw new XmlPullParserException(new StringBuffer("No valid parser classes found in ").append(this.classNamesLocation).toString());
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.parserClasses.size(); i++) {
            Class cls = (Class) this.parserClasses.elementAt(i);
            try {
                XmlPullParser xmlPullParser = (XmlPullParser) cls.newInstance();
                Enumeration enumerationKeys = this.features.keys();
                while (enumerationKeys.hasMoreElements()) {
                    String str = (String) enumerationKeys.nextElement();
                    Boolean bool = (Boolean) this.features.get(str);
                    if (bool != null && bool.booleanValue()) {
                        xmlPullParser.setFeature(str, true);
                    }
                }
                return xmlPullParser;
            } catch (Exception e) {
                stringBuffer.append(new StringBuffer().append(cls.getName()).append(": ").append(e.toString()).append("; ").toString());
            }
        }
        throw new XmlPullParserException(new StringBuffer("could not create parser: ").append((Object) stringBuffer).toString());
    }

    public XmlSerializer newSerializer() throws XmlPullParserException {
        Vector vector = this.serializerClasses;
        if (vector == null) {
            throw new XmlPullParserException(new StringBuffer("Factory initialization incomplete - has not tried ").append(this.classNamesLocation).toString());
        }
        if (vector.size() == 0) {
            throw new XmlPullParserException(new StringBuffer("No valid serializer classes found in ").append(this.classNamesLocation).toString());
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.serializerClasses.size(); i++) {
            Class cls = (Class) this.serializerClasses.elementAt(i);
            try {
                return (XmlSerializer) cls.newInstance();
            } catch (Exception e) {
                stringBuffer.append(new StringBuffer().append(cls.getName()).append(": ").append(e.toString()).append("; ").toString());
            }
        }
        throw new XmlPullParserException(new StringBuffer("could not create serializer: ").append((Object) stringBuffer).toString());
    }

    public static XmlPullParserFactory newInstance() throws XmlPullParserException {
        return newInstance(null, null);
    }

    public static XmlPullParserFactory newInstance(String str, Class cls) throws XmlPullParserException {
        String string;
        Class<?> cls2;
        Object objNewInstance;
        boolean z;
        if (cls == null) {
            cls = referenceContextClass;
        }
        if (str == null || str.length() == 0 || "DEFAULT".equals(str)) {
            try {
                InputStream resourceAsStream = cls.getResourceAsStream(RESOURCE_NAME);
                if (resourceAsStream == null) {
                    throw new XmlPullParserException("resource not found: /META-INF/services/org.xmlpull.v1.XmlPullParserFactory make sure that parser implementing XmlPull API is available");
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    int i = resourceAsStream.read();
                    if (i < 0) {
                        break;
                    }
                    if (i > 32) {
                        stringBuffer.append((char) i);
                    }
                }
                resourceAsStream.close();
                str = stringBuffer.toString();
                string = new StringBuffer("resource /META-INF/services/org.xmlpull.v1.XmlPullParserFactory that contained '").append(str).append("'").toString();
            } catch (Exception e) {
                throw new XmlPullParserException(null, null, e);
            }
        } else {
            string = new StringBuffer("parameter classNames to newInstance() that contained '").append(str).append("'").toString();
        }
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        XmlPullParserFactory xmlPullParserFactory = null;
        int i2 = 0;
        while (i2 < str.length()) {
            int iIndexOf = str.indexOf(44, i2);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            String strSubstring = str.substring(i2, iIndexOf);
            try {
                cls2 = Class.forName(strSubstring);
                try {
                    objNewInstance = cls2.newInstance();
                } catch (Exception unused) {
                    objNewInstance = null;
                }
            } catch (Exception unused2) {
                cls2 = null;
            }
            if (cls2 != null) {
                boolean z2 = true;
                if (objNewInstance instanceof XmlPullParser) {
                    vector.addElement(cls2);
                    z = true;
                } else {
                    z = false;
                }
                if (objNewInstance instanceof XmlSerializer) {
                    vector2.addElement(cls2);
                    z = true;
                }
                if (!(objNewInstance instanceof XmlPullParserFactory)) {
                    z2 = z;
                } else if (xmlPullParserFactory == null) {
                    xmlPullParserFactory = (XmlPullParserFactory) objNewInstance;
                }
                if (!z2) {
                    throw new XmlPullParserException(new StringBuffer("incompatible class: ").append(strSubstring).toString());
                }
            }
            i2 = iIndexOf + 1;
        }
        if (xmlPullParserFactory == null) {
            xmlPullParserFactory = new XmlPullParserFactory();
        }
        xmlPullParserFactory.parserClasses = vector;
        xmlPullParserFactory.serializerClasses = vector2;
        xmlPullParserFactory.classNamesLocation = string;
        return xmlPullParserFactory;
    }
}
