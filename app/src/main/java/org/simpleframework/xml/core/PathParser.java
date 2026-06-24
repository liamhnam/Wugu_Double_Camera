package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class PathParser implements Expression {
    protected boolean attribute;
    protected String cache;
    protected int count;
    protected char[] data;
    protected String location;
    protected int off;
    protected String path;
    protected int start;
    protected Style style;
    protected Type type;
    protected Cache<String> attributes = new ConcurrentCache();
    protected Cache<String> elements = new ConcurrentCache();
    protected List<Integer> indexes = new ArrayList();
    protected List<String> prefixes = new ArrayList();
    protected List<String> names = new ArrayList();
    protected StringBuilder builder = new StringBuilder();

    private boolean isSpecial(char c) {
        return c == '_' || c == '-' || c == ':';
    }

    public PathParser(String str, Type type, Format format) throws Exception {
        this.style = format.getStyle();
        this.type = type;
        this.path = str;
        parse(str);
    }

    @Override
    public boolean isEmpty() {
        return isEmpty(this.location);
    }

    @Override
    public boolean isPath() {
        return this.names.size() > 1;
    }

    @Override
    public boolean isAttribute() {
        return this.attribute;
    }

    @Override
    public int getIndex() {
        return this.indexes.get(0).intValue();
    }

    @Override
    public String getPrefix() {
        return this.prefixes.get(0);
    }

    @Override
    public String getFirst() {
        return this.names.get(0);
    }

    @Override
    public String getLast() {
        return this.names.get(this.names.size() - 1);
    }

    @Override
    public String getPath() {
        return this.location;
    }

    @Override
    public String getElement(String str) {
        if (!isEmpty(this.location)) {
            String strFetch = this.elements.fetch(str);
            if (strFetch == null && (strFetch = getElementPath(this.location, str)) != null) {
                this.elements.cache(str, strFetch);
            }
            return strFetch;
        }
        return this.style.getElement(str);
    }

    protected String getElementPath(String str, String str2) {
        String element = this.style.getElement(str2);
        return isEmpty(element) ? str : isEmpty(str) ? element : str + MqttTopic.TOPIC_LEVEL_SEPARATOR + element + "[1]";
    }

    @Override
    public String getAttribute(String str) {
        if (!isEmpty(this.location)) {
            String strFetch = this.attributes.fetch(str);
            if (strFetch == null && (strFetch = getAttributePath(this.location, str)) != null) {
                this.attributes.cache(str, strFetch);
            }
            return strFetch;
        }
        return this.style.getAttribute(str);
    }

    protected String getAttributePath(String str, String str2) {
        String attribute = this.style.getAttribute(str2);
        return isEmpty(str) ? attribute : str + "/@" + attribute;
    }

    @Override
    public Iterator<String> iterator() {
        return this.names.iterator();
    }

    @Override
    public Expression getPath(int i) {
        return getPath(i, 0);
    }

    @Override
    public Expression getPath(int i, int i2) {
        int size = (this.names.size() - 1) - i2;
        if (size >= i) {
            return new PathSection(i, size);
        }
        return new PathSection(i, i);
    }

    private void parse(String str) throws Exception {
        if (str != null) {
            int length = str.length();
            this.count = length;
            char[] cArr = new char[length];
            this.data = cArr;
            str.getChars(0, length, cArr, 0);
        }
        path();
    }

    private void path() throws Exception {
        char c = this.data[this.off];
        if (c == '/') {
            throw new PathException("Path '%s' in %s references document root", this.path, this.type);
        }
        if (c == '.') {
            skip();
        }
        while (this.off < this.count) {
            if (this.attribute) {
                throw new PathException("Path '%s' in %s references an invalid attribute", this.path, this.type);
            }
            segment();
        }
        truncate();
        build();
    }

    private void build() {
        int size = this.names.size();
        int i = size - 1;
        for (int i2 = 0; i2 < size; i2++) {
            String str = this.prefixes.get(i2);
            String str2 = this.names.get(i2);
            int iIntValue = this.indexes.get(i2).intValue();
            if (i2 > 0) {
                this.builder.append('/');
            }
            if (this.attribute && i2 == i) {
                this.builder.append('@');
                this.builder.append(str2);
            } else {
                if (str != null) {
                    this.builder.append(str);
                    this.builder.append(':');
                }
                this.builder.append(str2);
                this.builder.append('[');
                this.builder.append(iIntValue);
                this.builder.append(']');
            }
        }
        this.location = this.builder.toString();
    }

    private void skip() throws Exception {
        char[] cArr = this.data;
        if (cArr.length > 1) {
            int i = this.off;
            if (cArr[i + 1] != '/') {
                throw new PathException("Path '%s' in %s has an illegal syntax", this.path, this.type);
            }
            this.off = i + 1;
        }
        int i2 = this.off + 1;
        this.off = i2;
        this.start = i2;
    }

    private void segment() throws Exception {
        char c = this.data[this.off];
        if (c == '/') {
            throw new PathException("Invalid path expression '%s' in %s", this.path, this.type);
        }
        if (c == '@') {
            attribute();
        } else {
            element();
        }
        align();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void element() throws java.lang.Exception {
        /*
            r5 = this;
            int r0 = r5.off
            r1 = 0
        L3:
            int r2 = r5.off
            int r3 = r5.count
            if (r2 >= r3) goto L46
            char[] r3 = r5.data
            int r4 = r2 + 1
            r5.off = r4
            char r2 = r3[r2]
            boolean r3 = r5.isValid(r2)
            if (r3 != 0) goto L43
            r3 = 64
            if (r2 != r3) goto L22
            int r2 = r5.off
            int r2 = r2 + (-1)
            r5.off = r2
            goto L46
        L22:
            r3 = 91
            if (r2 != r3) goto L2a
            r5.index()
            goto L46
        L2a:
            r3 = 47
            if (r2 != r3) goto L2f
            goto L46
        L2f:
            org.simpleframework.xml.core.PathException r0 = new org.simpleframework.xml.core.PathException
            java.lang.Character r1 = java.lang.Character.valueOf(r2)
            java.lang.String r2 = r5.path
            org.simpleframework.xml.strategy.Type r3 = r5.type
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2, r3}
            java.lang.String r2 = "Illegal character '%s' in element for '%s' in %s"
            r0.<init>(r2, r1)
            throw r0
        L43:
            int r1 = r1 + 1
            goto L3
        L46:
            r5.element(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simpleframework.xml.core.PathParser.element():void");
    }

    private void attribute() throws Exception {
        char c;
        int i = this.off + 1;
        this.off = i;
        do {
            int i2 = this.off;
            if (i2 >= this.count) {
                if (i2 <= i) {
                    throw new PathException("Attribute reference in '%s' for %s is empty", this.path, this.type);
                }
                this.attribute = true;
                attribute(i, i2 - i);
                return;
            }
            char[] cArr = this.data;
            this.off = i2 + 1;
            c = cArr[i2];
        } while (isValid(c));
        throw new PathException("Illegal character '%s' in attribute for '%s' in %s", Character.valueOf(c), this.path, this.type);
    }

    private void index() throws Exception {
        int i = 0;
        if (this.data[this.off - 1] == '[') {
            while (true) {
                int i2 = this.off;
                if (i2 >= this.count) {
                    break;
                }
                char[] cArr = this.data;
                this.off = i2 + 1;
                char c = cArr[i2];
                if (!isDigit(c)) {
                    break;
                } else {
                    i = ((i * 10) + c) - 48;
                }
            }
        }
        char[] cArr2 = this.data;
        int i3 = this.off;
        this.off = i3 + 1;
        if (cArr2[i3 - 1] != ']') {
            throw new PathException("Invalid index for path '%s' in %s", this.path, this.type);
        }
        this.indexes.add(Integer.valueOf(i));
    }

    private void truncate() throws Exception {
        int i = this.off;
        int i2 = i - 1;
        char[] cArr = this.data;
        if (i2 >= cArr.length) {
            this.off = i - 1;
        } else if (cArr[i - 1] == '/') {
            this.off = i - 1;
        }
    }

    private void align() throws Exception {
        if (this.names.size() > this.indexes.size()) {
            this.indexes.add(1);
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isValid(char c) {
        return isLetter(c) || isSpecial(c);
    }

    private boolean isLetter(char c) {
        return Character.isLetterOrDigit(c);
    }

    private void element(int i, int i2) {
        String str = new String(this.data, i, i2);
        if (i2 > 0) {
            element(str);
        }
    }

    private void attribute(int i, int i2) {
        String str = new String(this.data, i, i2);
        if (i2 > 0) {
            attribute(str);
        }
    }

    private void element(String str) {
        String strSubstring;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0) {
            strSubstring = str.substring(0, iIndexOf);
            str = str.substring(iIndexOf + 1);
        } else {
            strSubstring = null;
        }
        String element = this.style.getElement(str);
        this.prefixes.add(strSubstring);
        this.names.add(element);
    }

    private void attribute(String str) {
        String attribute = this.style.getAttribute(str);
        this.prefixes.add(null);
        this.names.add(attribute);
    }

    @Override
    public String toString() {
        int i = this.off - this.start;
        if (this.cache == null) {
            this.cache = new String(this.data, this.start, i);
        }
        return this.cache;
    }

    private class PathSection implements Expression {
        private int begin;
        private List<String> cache = new ArrayList();
        private int end;
        private String path;
        private String section;

        public PathSection(int i, int i2) {
            this.begin = i;
            this.end = i2;
        }

        @Override
        public boolean isEmpty() {
            return this.begin == this.end;
        }

        @Override
        public boolean isPath() {
            return this.end - this.begin >= 1;
        }

        @Override
        public boolean isAttribute() {
            return PathParser.this.attribute && this.end >= PathParser.this.names.size() - 1;
        }

        @Override
        public String getPath() {
            if (this.section == null) {
                this.section = getCanonicalPath();
            }
            return this.section;
        }

        @Override
        public String getElement(String str) {
            String path = getPath();
            return path != null ? PathParser.this.getElementPath(path, str) : str;
        }

        @Override
        public String getAttribute(String str) {
            String path = getPath();
            return path != null ? PathParser.this.getAttributePath(path, str) : str;
        }

        @Override
        public int getIndex() {
            return PathParser.this.indexes.get(this.begin).intValue();
        }

        @Override
        public String getPrefix() {
            return PathParser.this.prefixes.get(this.begin);
        }

        @Override
        public String getFirst() {
            return PathParser.this.names.get(this.begin);
        }

        @Override
        public String getLast() {
            return PathParser.this.names.get(this.end);
        }

        @Override
        public Expression getPath(int i) {
            return getPath(i, 0);
        }

        @Override
        public Expression getPath(int i, int i2) {
            return PathParser.this.new PathSection(this.begin + i, this.end - i2);
        }

        @Override
        public Iterator<String> iterator() {
            if (this.cache.isEmpty()) {
                for (int i = this.begin; i <= this.end; i++) {
                    String str = PathParser.this.names.get(i);
                    if (str != null) {
                        this.cache.add(str);
                    }
                }
            }
            return this.cache.iterator();
        }

        private String getCanonicalPath() {
            int i = 0;
            int iIndexOf = 0;
            while (i < this.begin) {
                iIndexOf = PathParser.this.location.indexOf(47, iIndexOf + 1);
                i++;
            }
            int iIndexOf2 = iIndexOf;
            while (i <= this.end) {
                iIndexOf2 = PathParser.this.location.indexOf(47, iIndexOf2 + 1);
                if (iIndexOf2 == -1) {
                    iIndexOf2 = PathParser.this.location.length();
                }
                i++;
            }
            return PathParser.this.location.substring(iIndexOf + 1, iIndexOf2);
        }

        private String getFragment() {
            int i = PathParser.this.start;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 > this.end) {
                    break;
                }
                if (i >= PathParser.this.count) {
                    i++;
                    break;
                }
                int i4 = i + 1;
                if (PathParser.this.data[i] == '/' && (i2 = i2 + 1) == this.begin) {
                    i = i4;
                    i3 = i;
                } else {
                    i = i4;
                }
            }
            return new String(PathParser.this.data, i3, (i - 1) - i3);
        }

        @Override
        public String toString() {
            if (this.path == null) {
                this.path = getFragment();
            }
            return this.path;
        }
    }
}
