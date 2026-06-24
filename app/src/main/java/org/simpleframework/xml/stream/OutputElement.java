package org.simpleframework.xml.stream;

class OutputElement implements OutputNode {
    private String comment;
    private String name;
    private OutputNode parent;
    private String reference;
    private NamespaceMap scope;
    private String value;
    private NodeWriter writer;
    private OutputNodeMap table = new OutputNodeMap(this);
    private Mode mode = Mode.INHERIT;

    public OutputElement(OutputNode outputNode, NodeWriter nodeWriter, String str) {
        this.scope = new PrefixResolver(outputNode);
        this.writer = nodeWriter;
        this.parent = outputNode;
        this.name = str;
    }

    @Override
    public String getPrefix() {
        return getPrefix(true);
    }

    @Override
    public String getPrefix(boolean z) {
        String prefix = this.scope.getPrefix(this.reference);
        return (z && prefix == null) ? this.parent.getPrefix() : prefix;
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

    @Override
    public OutputNode getParent() {
        return this.parent;
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
    public String getComment() {
        return this.comment;
    }

    @Override
    public boolean isRoot() {
        return this.writer.isRoot(this);
    }

    @Override
    public Mode getMode() {
        return this.mode;
    }

    @Override
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public OutputNodeMap getAttributes() {
        return this.table;
    }

    @Override
    public void setComment(String str) {
        this.comment = str;
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
    public void setData(boolean z) {
        if (z) {
            this.mode = Mode.DATA;
        } else {
            this.mode = Mode.ESCAPE;
        }
    }

    @Override
    public OutputNode setAttribute(String str, String str2) {
        return this.table.put(str, str2);
    }

    @Override
    public OutputNode getChild(String str) throws Exception {
        return this.writer.writeElement(this, str);
    }

    @Override
    public void remove() throws Exception {
        this.writer.remove(this);
    }

    @Override
    public void commit() throws Exception {
        this.writer.commit(this);
    }

    @Override
    public boolean isCommitted() {
        return this.writer.isCommitted(this);
    }

    public String toString() {
        return String.format("element %s", this.name);
    }
}
