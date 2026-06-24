package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;

class ClassSchema implements Schema {
    private final Caller caller;
    private final Decorator decorator;
    private final Instantiator factory;
    private final boolean primitive;
    private final Version revision;
    private final Section section;
    private final Label text;
    private final Class type;
    private final Label version;

    public ClassSchema(Scanner scanner, Context context) throws Exception {
        this.caller = scanner.getCaller(context);
        this.factory = scanner.getInstantiator();
        this.revision = scanner.getRevision();
        this.decorator = scanner.getDecorator();
        this.primitive = scanner.isPrimitive();
        this.version = scanner.getVersion();
        this.section = scanner.getSection();
        this.text = scanner.getText();
        this.type = scanner.getType();
    }

    @Override
    public boolean isPrimitive() {
        return this.primitive;
    }

    @Override
    public Instantiator getInstantiator() {
        return this.factory;
    }

    @Override
    public Label getVersion() {
        return this.version;
    }

    @Override
    public Version getRevision() {
        return this.revision;
    }

    @Override
    public Decorator getDecorator() {
        return this.decorator;
    }

    @Override
    public Caller getCaller() {
        return this.caller;
    }

    @Override
    public Section getSection() {
        return this.section;
    }

    @Override
    public Label getText() {
        return this.text;
    }

    public String toString() {
        return String.format("schema for %s", this.type);
    }
}
