package org.simpleframework.xml.core;

import java.util.List;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

class DefaultScanner implements Scanner {
    private Detail detail;
    private Scanner scanner;

    public DefaultScanner(Detail detail, Support support) throws Exception {
        this.detail = new DefaultDetail(detail, DefaultType.FIELD);
        this.scanner = new ObjectScanner(this.detail, support);
    }

    @Override
    public Signature getSignature() {
        return this.scanner.getSignature();
    }

    @Override
    public List<Signature> getSignatures() {
        return this.scanner.getSignatures();
    }

    @Override
    public ParameterMap getParameters() {
        return this.scanner.getParameters();
    }

    @Override
    public Instantiator getInstantiator() {
        return this.scanner.getInstantiator();
    }

    @Override
    public Class getType() {
        return this.scanner.getType();
    }

    @Override
    public Decorator getDecorator() {
        return this.scanner.getDecorator();
    }

    @Override
    public Caller getCaller(Context context) {
        return this.scanner.getCaller(context);
    }

    @Override
    public Section getSection() {
        return this.scanner.getSection();
    }

    @Override
    public Version getRevision() {
        return this.scanner.getRevision();
    }

    @Override
    public Order getOrder() {
        return this.scanner.getOrder();
    }

    @Override
    public Label getVersion() {
        return this.scanner.getVersion();
    }

    @Override
    public Label getText() {
        return this.scanner.getText();
    }

    @Override
    public String getName() {
        return this.detail.getName();
    }

    @Override
    public Function getCommit() {
        return this.scanner.getCommit();
    }

    @Override
    public Function getValidate() {
        return this.scanner.getValidate();
    }

    @Override
    public Function getPersist() {
        return this.scanner.getPersist();
    }

    @Override
    public Function getComplete() {
        return this.scanner.getComplete();
    }

    @Override
    public Function getReplace() {
        return this.scanner.getReplace();
    }

    @Override
    public Function getResolve() {
        return this.scanner.getResolve();
    }

    @Override
    public boolean isPrimitive() {
        return this.scanner.isPrimitive();
    }

    @Override
    public boolean isEmpty() {
        return this.scanner.isEmpty();
    }

    @Override
    public boolean isStrict() {
        return this.scanner.isStrict();
    }
}
