package org.simpleframework.xml.stream;

import org.xmlpull.p065v1.XmlPullParser;

class PullReader implements EventReader {
    private XmlPullParser parser;
    private EventNode peek;

    public PullReader(XmlPullParser xmlPullParser) {
        this.parser = xmlPullParser;
    }

    @Override
    public EventNode peek() throws Exception {
        if (this.peek == null) {
            this.peek = next();
        }
        return this.peek;
    }

    @Override
    public EventNode next() throws Exception {
        EventNode eventNode = this.peek;
        if (eventNode == null) {
            return read();
        }
        this.peek = null;
        return eventNode;
    }

    private EventNode read() throws Exception {
        int next = this.parser.next();
        if (next == 1) {
            return null;
        }
        if (next == 2) {
            return start();
        }
        if (next == 4) {
            return text();
        }
        if (next == 3) {
            return end();
        }
        return read();
    }

    private Text text() throws Exception {
        return new Text(this.parser);
    }

    private Start start() throws Exception {
        Start start = new Start(this.parser);
        return start.isEmpty() ? build(start) : start;
    }

    private Start build(Start start) throws Exception {
        int attributeCount = this.parser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            Entry entryAttribute = attribute(i);
            if (!entryAttribute.isReserved()) {
                start.add(entryAttribute);
            }
        }
        return start;
    }

    private Entry attribute(int i) throws Exception {
        return new Entry(this.parser, i);
    }

    private End end() throws Exception {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final String name;
        private final String prefix;
        private final String reference;
        private final XmlPullParser source;
        private final String value;

        @Override
        public boolean isReserved() {
            return false;
        }

        public Entry(XmlPullParser xmlPullParser, int i) {
            this.reference = xmlPullParser.getAttributeNamespace(i);
            this.prefix = xmlPullParser.getAttributePrefix(i);
            this.value = xmlPullParser.getAttributeValue(i);
            this.name = xmlPullParser.getAttributeName(i);
            this.source = xmlPullParser;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getReference() {
            return this.reference;
        }

        @Override
        public String getPrefix() {
            return this.prefix;
        }

        @Override
        public Object getSource() {
            return this.source;
        }
    }

    private static class Start extends EventElement {
        private final int line;
        private final String name;
        private final String prefix;
        private final String reference;
        private final XmlPullParser source;

        public Start(XmlPullParser xmlPullParser) {
            this.reference = xmlPullParser.getNamespace();
            this.line = xmlPullParser.getLineNumber();
            this.prefix = xmlPullParser.getPrefix();
            this.name = xmlPullParser.getName();
            this.source = xmlPullParser;
        }

        @Override
        public int getLine() {
            return this.line;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getReference() {
            return this.reference;
        }

        @Override
        public String getPrefix() {
            return this.prefix;
        }

        @Override
        public Object getSource() {
            return this.source;
        }
    }

    private static class Text extends EventToken {
        private final XmlPullParser source;
        private final String text;

        @Override
        public boolean isText() {
            return true;
        }

        public Text(XmlPullParser xmlPullParser) {
            this.text = xmlPullParser.getText();
            this.source = xmlPullParser;
        }

        @Override
        public String getValue() {
            return this.text;
        }

        @Override
        public Object getSource() {
            return this.source;
        }
    }

    private static class End extends EventToken {
        @Override
        public boolean isEnd() {
            return true;
        }

        private End() {
        }
    }
}
