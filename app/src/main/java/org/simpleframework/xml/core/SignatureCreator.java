package org.simpleframework.xml.core;

import java.util.List;

class SignatureCreator implements Creator {
    private final List<Parameter> list;
    private final Signature signature;
    private final Class type;

    public SignatureCreator(Signature signature) {
        this.type = signature.getType();
        this.list = signature.getAll();
        this.signature = signature;
    }

    @Override
    public Class getType() {
        return this.type;
    }

    @Override
    public Signature getSignature() {
        return this.signature;
    }

    @Override
    public Object getInstance() throws Exception {
        return this.signature.create();
    }

    @Override
    public Object getInstance(Criteria criteria) throws Exception {
        Object[] array = this.list.toArray();
        for (int i = 0; i < this.list.size(); i++) {
            array[i] = getVariable(criteria, i);
        }
        return this.signature.create(array);
    }

    private Object getVariable(Criteria criteria, int i) throws Exception {
        Variable variableRemove = criteria.remove(this.list.get(i).getKey());
        if (variableRemove != null) {
            return variableRemove.getValue();
        }
        return null;
    }

    @Override
    public double getScore(Criteria criteria) throws Exception {
        Signature signatureCopy = this.signature.copy();
        for (Object obj : criteria) {
            Parameter parameter = signatureCopy.get(obj);
            Variable variable = criteria.get(obj);
            Contact contact = variable.getContact();
            if (parameter != null && !Support.isAssignable(variable.getValue().getClass(), parameter.getType())) {
                return -1.0d;
            }
            if (contact.isReadOnly() && parameter == null) {
                return -1.0d;
            }
        }
        return getPercentage(criteria);
    }

    private double getPercentage(Criteria criteria) throws Exception {
        double d = 0.0d;
        for (Parameter parameter : this.list) {
            if (criteria.get(parameter.getKey()) != null) {
                d += 1.0d;
            } else if (parameter.isRequired() || parameter.isPrimitive()) {
                return -1.0d;
            }
        }
        return getAdjustment(d);
    }

    private double getAdjustment(double d) {
        double size = ((double) this.list.size()) / 1000.0d;
        if (d > 0.0d) {
            return size + (d / ((double) this.list.size()));
        }
        return d / ((double) this.list.size());
    }

    public String toString() {
        return this.signature.toString();
    }
}
