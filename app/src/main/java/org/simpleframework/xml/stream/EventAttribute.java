package org.simpleframework.xml.stream;

abstract class EventAttribute implements Attribute {
    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public String getReference() {
        return null;
    }

    @Override
    public Object getSource() {
        return null;
    }

    @Override
    public boolean isReserved() {
        return false;
    }

    EventAttribute() {
    }
}
