package org.xmlpull.p065v1.builder;

import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;

public interface XmlPullElement extends XmlElement {
    @Override
    Iterator children();

    boolean fullyConstructed();

    XmlPullParser nextChildAsPullParser() throws XmlPullParserException, IOException;

    XmlPullElement readNextChild() throws XmlPullParserException, IOException;

    boolean skipNextChild() throws XmlPullParserException, IOException;
}
