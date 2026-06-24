package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

class PrimitiveScanner implements Scanner {
    private final Detail detail;
    private final Section section = new EmptySection(this);

    @Override
    public Function getCommit() {
        return null;
    }

    @Override
    public Function getComplete() {
        return null;
    }

    @Override
    public Decorator getDecorator() {
        return null;
    }

    @Override
    public Instantiator getInstantiator() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Order getOrder() {
        return null;
    }

    @Override
    public Function getPersist() {
        return null;
    }

    @Override
    public Function getReplace() {
        return null;
    }

    @Override
    public Function getResolve() {
        return null;
    }

    @Override
    public Version getRevision() {
        return null;
    }

    @Override
    public Signature getSignature() {
        return null;
    }

    @Override
    public Label getText() {
        return null;
    }

    @Override
    public Function getValidate() {
        return null;
    }

    @Override
    public Label getVersion() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public boolean isStrict() {
        return true;
    }

    public PrimitiveScanner(Detail detail) {
        this.detail = detail;
    }

    @Override
    public List<Signature> getSignatures() {
        return new LinkedList();
    }

    @Override
    public ParameterMap getParameters() {
        return new ParameterMap();
    }

    @Override
    public Class getType() {
        return this.detail.getType();
    }

    @Override
    public Caller getCaller(Context context) {
        return new Caller(this, context);
    }

    @Override
    public Section getSection() {
        return this.section;
    }

    private static class EmptySection implements Section {
        private final List<String> list = new LinkedList();
        private final Scanner scanner;

        @Override
        public String getAttribute(String str) {
            return null;
        }

        @Override
        public Label getElement(String str) {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getPath(String str) {
            return null;
        }

        @Override
        public String getPrefix() {
            return null;
        }

        @Override
        public Section getSection(String str) {
            return null;
        }

        @Override
        public Label getText() {
            return null;
        }

        @Override
        public boolean isSection(String str) {
            return false;
        }

        public EmptySection(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public Iterator<String> iterator() {
            return this.list.iterator();
        }

        @Override
        public LabelMap getElements() {
            return new LabelMap(this.scanner);
        }

        @Override
        public LabelMap getAttributes() {
            return new LabelMap(this.scanner);
        }
    }
}
