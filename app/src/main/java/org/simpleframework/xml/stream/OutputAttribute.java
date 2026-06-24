package org.simpleframework.xml.stream;

class OutputAttribute implements OutputNode {
    private String name;
    private String reference;
    private NamespaceMap scope;
    private OutputNode source;
    private String value;

    @Override
    public void commit() {
    }

    @Override
    public OutputNode getChild(String str) {
        return null;
    }

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public boolean isCommitted() {
        return true;
    }

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public void remove() {
    }

    @Override
    public OutputNode setAttribute(String str, String str2) {
        return null;
    }

    @Override
    public void setComment(String str) {
    }

    @Override
    public void setData(boolean z) {
    }

    @Override
    public void setMode(Mode mode) {
    }

    public OutputAttribute(OutputNode outputNode, String str, String str2) {
        this.scope = outputNode.getNamespaces();
        this.source = outputNode;
        this.value = str2;
        this.name = str;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String str) {
        this.value = str;
    }

    @Override
    public void setName(String str) {
        this.name = str;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public OutputNode getParent() {
        return this.source;
    }

    @Override
    public NodeMap<OutputNode> getAttributes() {
        return new OutputNodeMap(this);
    }

    @Override
    public Mode getMode() {
        return Mode.INHERIT;
    }

    @Override
    public String getPrefix() {
        return this.scope.getPrefix(this.reference);
    }

    @Override
    public String getPrefix(boolean z) {
        return this.scope.getPrefix(this.reference);
    }

    @Override
    public String getReference() {
        return this.reference;
    }

    @Override
    public void setReference(String str) {
        this.reference = str;
    }

    @Override
    public NamespaceMap getNamespaces() {
        return this.scope;
    }

    public String toString() {
        return String.format("attribute %s='%s'", this.name, this.value);
    }
}
