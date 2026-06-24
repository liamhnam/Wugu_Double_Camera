package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;

class InputNodeMap extends LinkedHashMap<String, InputNode> implements NodeMap<InputNode> {
    private final InputNode source;

    protected InputNodeMap(InputNode inputNode) {
        this.source = inputNode;
    }

    public InputNodeMap(InputNode inputNode, EventNode eventNode) {
        this.source = inputNode;
        build(eventNode);
    }

    private void build(EventNode eventNode) {
        for (Attribute attribute : eventNode) {
            InputAttribute inputAttribute = new InputAttribute(this.source, attribute);
            if (!attribute.isReserved()) {
                put(inputAttribute.getName(), inputAttribute);
            }
        }
    }

    @Override
    public InputNode getNode() {
        return this.source;
    }

    @Override
    public String getName() {
        return this.source.getName();
    }

    @Override
    public InputNode put(String str, String str2) {
        InputAttribute inputAttribute = new InputAttribute(this.source, str, str2);
        if (str != null) {
            put(str, inputAttribute);
        }
        return inputAttribute;
    }

    @Override
    public InputNode remove(String str) {
        return (InputNode) super.remove((Object) str);
    }

    @Override
    public InputNode get(String str) {
        return (InputNode) super.get((Object) str);
    }

    @Override
    public Iterator<String> iterator() {
        return keySet().iterator();
    }
}
