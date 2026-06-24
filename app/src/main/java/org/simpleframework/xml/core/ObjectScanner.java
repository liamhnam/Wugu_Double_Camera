package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

class ObjectScanner implements Scanner {
    private StructureBuilder builder;
    private Detail detail;
    private ClassScanner scanner;
    private Structure structure;
    private Support support;

    public ObjectScanner(Detail detail, Support support) throws Exception {
        this.scanner = new ClassScanner(detail, support);
        this.builder = new StructureBuilder(this, detail, support);
        this.support = support;
        this.detail = detail;
        scan(detail);
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
        return this.structure.getInstantiator();
    }

    @Override
    public Class getType() {
        return this.detail.getType();
    }

    @Override
    public Decorator getDecorator() {
        return this.scanner.getDecorator();
    }

    @Override
    public Caller getCaller(Context context) {
        return new Caller(this, context);
    }

    @Override
    public Section getSection() {
        return this.structure.getSection();
    }

    @Override
    public Version getRevision() {
        return this.structure.getRevision();
    }

    @Override
    public Order getOrder() {
        return this.scanner.getOrder();
    }

    @Override
    public Label getVersion() {
        return this.structure.getVersion();
    }

    @Override
    public Label getText() {
        return this.structure.getText();
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
        return this.structure.isPrimitive();
    }

    @Override
    public boolean isEmpty() {
        return this.scanner.getRoot() == null;
    }

    @Override
    public boolean isStrict() {
        return this.detail.isStrict();
    }

    private void scan(Detail detail) throws Exception {
        order(detail);
        field(detail);
        method(detail);
        validate(detail);
        commit(detail);
    }

    private void order(Detail detail) throws Exception {
        this.builder.assemble(detail.getType());
    }

    private void commit(Detail detail) throws Exception {
        Class type = detail.getType();
        if (this.structure == null) {
            this.structure = this.builder.build(type);
        }
        this.builder = null;
    }

    private void validate(Detail detail) throws Exception {
        Class type = detail.getType();
        this.builder.commit(type);
        this.builder.validate(type);
    }

    private void field(Detail detail) throws Exception {
        for (Contact contact : this.support.getFields(detail.getType(), detail.getOverride())) {
            Annotation annotation = contact.getAnnotation();
            if (annotation != null) {
                this.builder.process(contact, annotation);
            }
        }
    }

    private void method(Detail detail) throws Exception {
        for (Contact contact : this.support.getMethods(detail.getType(), detail.getOverride())) {
            Annotation annotation = contact.getAnnotation();
            if (annotation != null) {
                this.builder.process(contact, annotation);
            }
        }
    }
}
