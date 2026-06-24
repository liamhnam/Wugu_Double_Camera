package org.simpleframework.xml.stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

class DocumentReader implements EventReader {
    private static final String RESERVED = "xml";
    private EventNode peek;
    private NodeExtractor queue;
    private NodeStack stack;

    public DocumentReader(Document document) {
        this.queue = new NodeExtractor(document);
        NodeStack nodeStack = new NodeStack();
        this.stack = nodeStack;
        nodeStack.push(document);
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
        org.w3c.dom.Node nodePeek = this.queue.peek();
        if (nodePeek == null) {
            return end();
        }
        return read(nodePeek);
    }

    private EventNode read(org.w3c.dom.Node node) throws Exception {
        org.w3c.dom.Node parentNode = node.getParentNode();
        org.w3c.dom.Node pVar = this.stack.top();
        if (parentNode != pVar) {
            if (pVar != null) {
                this.stack.pop();
            }
            return end();
        }
        if (node != null) {
            this.queue.poll();
        }
        return convert(node);
    }

    private EventNode convert(org.w3c.dom.Node node) throws Exception {
        if (node.getNodeType() == 1) {
            if (node != null) {
                this.stack.push(node);
            }
            return start(node);
        }
        return text(node);
    }

    private Start start(org.w3c.dom.Node node) {
        Start start = new Start(node);
        return start.isEmpty() ? build(start) : start;
    }

    private Start build(Start start) {
        NamedNodeMap attributes = start.getAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            Entry entryAttribute = attribute(attributes.item(i));
            if (!entryAttribute.isReserved()) {
                start.add(entryAttribute);
            }
        }
        return start;
    }

    private Entry attribute(org.w3c.dom.Node node) {
        return new Entry(node);
    }

    private Text text(org.w3c.dom.Node node) {
        return new Text(node);
    }

    private End end() {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final org.w3c.dom.Node node;

        public Entry(org.w3c.dom.Node node) {
            this.node = node;
        }

        @Override
        public String getName() {
            return this.node.getLocalName();
        }

        @Override
        public String getValue() {
            return this.node.getNodeValue();
        }

        @Override
        public String getPrefix() {
            return this.node.getPrefix();
        }

        @Override
        public String getReference() {
            return this.node.getNamespaceURI();
        }

        @Override
        public boolean isReserved() {
            String prefix = getPrefix();
            String name = getName();
            if (prefix != null) {
                return prefix.startsWith("xml");
            }
            return name.startsWith("xml");
        }

        @Override
        public Object getSource() {
            return this.node;
        }
    }

    private static class Start extends EventElement {
        private final Element element;

        public Start(org.w3c.dom.Node node) {
            this.element = (Element) node;
        }

        @Override
        public String getName() {
            return this.element.getLocalName();
        }

        @Override
        public String getPrefix() {
            return this.element.getPrefix();
        }

        @Override
        public String getReference() {
            return this.element.getNamespaceURI();
        }

        public NamedNodeMap getAttributes() {
            return this.element.getAttributes();
        }

        @Override
        public Object getSource() {
            return this.element;
        }
    }

    private static class Text extends EventToken {
        private final org.w3c.dom.Node node;

        @Override
        public boolean isText() {
            return true;
        }

        public Text(org.w3c.dom.Node node) {
            this.node = node;
        }

        @Override
        public String getValue() {
            return this.node.getNodeValue();
        }

        @Override
        public Object getSource() {
            return this.node;
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
