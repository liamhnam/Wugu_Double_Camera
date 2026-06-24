package org.xmlpull.mxp1;

import java.util.Enumeration;
import org.xmlpull.mxp1_serializer.MXSerializer;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;
import org.xmlpull.p065v1.XmlPullParserFactory;
import org.xmlpull.p065v1.XmlSerializer;

public class MXParserFactory extends XmlPullParserFactory {
    protected static boolean stringCachedParserAvailable = true;

    @Override
    public XmlPullParser newPullParser() throws XmlPullParserException {
        MXParser mXParserCachingStrings;
        if (stringCachedParserAvailable) {
            try {
                mXParserCachingStrings = new MXParserCachingStrings();
            } catch (Exception unused) {
                stringCachedParserAvailable = false;
                mXParserCachingStrings = null;
            }
        } else {
            mXParserCachingStrings = null;
        }
        if (mXParserCachingStrings == null) {
            mXParserCachingStrings = new MXParser();
        }
        Enumeration enumerationKeys = this.features.keys();
        while (enumerationKeys.hasMoreElements()) {
            String str = (String) enumerationKeys.nextElement();
            Boolean bool = (Boolean) this.features.get(str);
            if (bool != null && bool.booleanValue()) {
                mXParserCachingStrings.setFeature(str, true);
            }
        }
        return mXParserCachingStrings;
    }

    @Override
    public XmlSerializer newSerializer() throws XmlPullParserException {
        return new MXSerializer();
    }
}
