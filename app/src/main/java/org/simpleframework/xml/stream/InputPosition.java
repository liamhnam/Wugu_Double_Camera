package org.simpleframework.xml.stream;

class InputPosition implements Position {
    private EventNode source;

    public InputPosition(EventNode eventNode) {
        this.source = eventNode;
    }

    @Override
    public int getLine() {
        return this.source.getLine();
    }

    @Override
    public String toString() {
        return String.format("line %s", Integer.valueOf(getLine()));
    }
}
