package org.simpleframework.xml.stream;

class InputAttribute implements InputNode {
    private String name;
    private InputNode parent;
    private String prefix;
    private String reference;
    private Object source;
    private String value;

    @Override
    public InputNode getAttribute(String str) {
        return null;
    }

    @Override
    public InputNode getNext() {
        return null;
    }

    @Override
    public InputNode getNext(String str) {
        return null;
    }

    @Override
    public boolean isElement() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public void skip() {
    }

    public InputAttribute(InputNode inputNode, String str, String str2) {
        this.parent = inputNode;
        this.value = str2;
        this.name = str;
    }

    public InputAttribute(InputNode inputNode, Attribute attribute) {
        this.reference = attribute.getReference();
        this.prefix = attribute.getPrefix();
        this.source = attribute.getSource();
        this.value = attribute.getValue();
        this.name = attribute.getName();
        this.parent = inputNode;
    }

    @Override
    public Object getSource() {
        return this.source;
    }

    @Override
    public InputNode getParent() {
        return this.parent;
    }

    @Override
    public Position getPosition() {
        return this.parent.getPosition();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getReference() {
        return this.reference;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public NodeMap<InputNode> getAttributes() {
        return new InputNodeMap(this);
    }

    public String toString() {
        return String.format("attribute %s='%s'", this.name, this.value);
    }
}
