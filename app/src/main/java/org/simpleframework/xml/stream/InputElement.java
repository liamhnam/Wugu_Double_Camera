package org.simpleframework.xml.stream;

class InputElement implements InputNode {
    private final InputNodeMap map;
    private final EventNode node;
    private final InputNode parent;
    private final NodeReader reader;

    @Override
    public boolean isElement() {
        return true;
    }

    public InputElement(InputNode inputNode, NodeReader nodeReader, EventNode eventNode) {
        this.map = new InputNodeMap(this, eventNode);
        this.reader = nodeReader;
        this.parent = inputNode;
        this.node = eventNode;
    }

    @Override
    public Object getSource() {
        return this.node.getSource();
    }

    @Override
    public InputNode getParent() {
        return this.parent;
    }

    @Override
    public Position getPosition() {
        return new InputPosition(this.node);
    }

    @Override
    public String getName() {
        return this.node.getName();
    }

    @Override
    public String getPrefix() {
        return this.node.getPrefix();
    }

    @Override
    public String getReference() {
        return this.node.getReference();
    }

    @Override
    public boolean isRoot() {
        return this.reader.isRoot(this);
    }

    @Override
    public InputNode getAttribute(String str) {
        return this.map.get(str);
    }

    @Override
    public NodeMap<InputNode> getAttributes() {
        return this.map;
    }

    @Override
    public String getValue() throws Exception {
        return this.reader.readValue(this);
    }

    @Override
    public InputNode getNext() throws Exception {
        return this.reader.readElement(this);
    }

    @Override
    public InputNode getNext(String str) throws Exception {
        return this.reader.readElement(this, str);
    }

    @Override
    public void skip() throws Exception {
        this.reader.skipElement(this);
    }

    @Override
    public boolean isEmpty() throws Exception {
        if (this.map.isEmpty()) {
            return this.reader.isEmpty(this);
        }
        return false;
    }

    public String toString() {
        return String.format("element %s", getName());
    }
}
