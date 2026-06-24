package com.bea.xml.stream;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class ReadOnlyNamespaceContextBase implements NamespaceContext {
    private String[] prefixes;
    private String[] uris;

    private String checkNull(String str) {
        return str == null ? "" : str;
    }

    public ReadOnlyNamespaceContextBase(String[] strArr, String[] strArr2, int i) {
        String[] strArr3 = new String[i];
        this.prefixes = strArr3;
        this.uris = new String[i];
        System.arraycopy(strArr, 0, strArr3, 0, strArr3.length);
        String[] strArr4 = this.uris;
        System.arraycopy(strArr2, 0, strArr4, 0, strArr4.length);
    }

    @Override
    public String getNamespaceURI(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Prefix may not be null.");
        }
        if (str.length() > 0) {
            for (int length = this.uris.length - 1; length >= 0; length--) {
                if (str.equals(this.prefixes[length])) {
                    return this.uris[length];
                }
            }
            if (XMLConstants.XML_NS_PREFIX.equals(str)) {
                return XMLConstants.XML_NS_URI;
            }
            if (XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
                return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
            }
            return null;
        }
        for (int length2 = this.uris.length - 1; length2 >= 0; length2--) {
            if (this.prefixes[length2] == null) {
                return this.uris[length2];
            }
        }
        return null;
    }

    @Override
    public String getPrefix(String str) {
        if (str == null) {
            throw new IllegalArgumentException("uri may not be null");
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("uri may not be empty string");
        }
        for (int length = this.uris.length - 1; length >= 0; length--) {
            if (str.equals(this.uris[length])) {
                String str2 = this.prefixes[length];
                if (str2 == null) {
                    for (int length2 = this.uris.length - 1; length2 > length; length2--) {
                        if (this.prefixes[length2] == null) {
                            break;
                        }
                    }
                    return "";
                }
                for (int length3 = this.uris.length - 1; length3 > length; length3--) {
                    if (str2.equals(this.prefixes[length3])) {
                        break;
                    }
                }
                return str2;
            }
        }
        if (XMLConstants.XML_NS_URI.equals(str)) {
            return XMLConstants.XML_NS_PREFIX;
        }
        if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(str)) {
            return XMLConstants.XMLNS_ATTRIBUTE;
        }
        return null;
    }

    public String getDefaultNameSpace() {
        for (int length = this.uris.length - 1; length >= 0; length--) {
            if (this.prefixes[length] == null) {
                return this.uris[length];
            }
        }
        return null;
    }

    @Override
    public Iterator getPrefixes(String str) {
        if (str == null) {
            throw new IllegalArgumentException("uri may not be null");
        }
        if ("".equals(str)) {
            throw new IllegalArgumentException("uri may not be empty string");
        }
        HashSet hashSet = new HashSet();
        for (int length = this.uris.length - 1; length >= 0; length--) {
            String strCheckNull = checkNull(this.prefixes[length]);
            if (str.equals(this.uris[length]) && !hashSet.contains(strCheckNull)) {
                if (strCheckNull.length() == 0) {
                    for (int length2 = this.uris.length - 1; length2 > length; length2--) {
                        if (this.prefixes[length2] == null) {
                            break;
                        }
                    }
                    hashSet.add(strCheckNull);
                } else {
                    for (int length3 = this.uris.length - 1; length3 > length; length3--) {
                        if (strCheckNull.equals(this.prefixes[length3])) {
                            break;
                        }
                    }
                    hashSet.add(strCheckNull);
                }
            }
        }
        return hashSet.iterator();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.uris.length; i++) {
            stringBuffer.append(new StringBuffer("[").append(checkNull(this.prefixes[i])).append("<->").append(this.uris[i]).append("]").toString());
        }
        return stringBuffer.toString();
    }

    public static void main(String[] strArr) throws Exception {
        MXParser mXParser = new MXParser();
        mXParser.setInput(new FileReader(strArr[0]));
        while (mXParser.hasNext()) {
            if (mXParser.isStartElement()) {
                System.out.println(new StringBuffer("context[").append(mXParser.getNamespaceContext()).append("]").toString());
                Iterator prefixes = mXParser.getNamespaceContext().getPrefixes("a");
                while (prefixes.hasNext()) {
                    System.out.println(new StringBuffer("Found prefix:").append(prefixes.next()).toString());
                }
            }
            mXParser.next();
        }
    }
}
