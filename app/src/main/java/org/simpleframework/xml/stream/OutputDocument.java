package org.simpleframework.xml.stream;

class OutputDocument implements OutputNode {
    private String comment;
    private String name;
    private String reference;
    private OutputStack stack;
    private String value;
    private NodeWriter writer;
    private OutputNodeMap table = new OutputNodeMap(this);
    private Mode mode = Mode.INHERIT;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public NamespaceMap getNamespaces() {
        return null;
    }

    @Override
    public OutputNode getParent() {
        return null;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public String getPrefix(boolean z) {
        return null;
    }

    @Override
    public boolean isRoot() {
        return true;
    }

    public OutputDocument(NodeWriter nodeWriter, OutputStack outputStack) {
        this.writer = nodeWriter;
        this.stack = outputStack;
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
    public String getValue() throws Exception {
        return this.value;
    }

    @Override
    public String getComment() {
        return this.comment;
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
    public OutputNode setAttribute(String str, String str2) {
        return this.table.put(str, str2);
    }

    @Override
    public NodeMap<OutputNode> getAttributes() {
        return this.table;
    }

    @Override
    public void setName(String str) {
        this.name = str;
    }

    @Override
    public void setValue(String str) {
        this.value = str;
    }

    @Override
    public void setComment(String str) {
        this.comment = str;
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
    public OutputNode getChild(String str) throws Exception {
        return this.writer.writeElement(this, str);
    }

    @Override
    public void remove() throws Exception {
        if (this.stack.isEmpty()) {
            throw new NodeException("No root node");
        }
        this.stack.bottom().remove();
    }

    @Override
    public void commit() throws Exception {
        if (this.stack.isEmpty()) {
            throw new NodeException("No root node");
        }
        this.stack.bottom().commit();
    }

    @Override
    public boolean isCommitted() {
        return this.stack.isEmpty();
    }
}
