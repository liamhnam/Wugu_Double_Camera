package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedHashMap;

class Collector implements Criteria {
    private final Registry alias;
    private final Registry registry;

    public Collector() {
        this.registry = new Registry();
        this.alias = new Registry();
    }

    @Override
    public Variable get(Object obj) {
        return this.registry.get(obj);
    }

    @Override
    public Variable get(Label label) throws Exception {
        if (label == null) {
            return null;
        }
        return this.registry.get(label.getKey());
    }

    @Override
    public Variable resolve(String str) {
        return this.alias.get(str);
    }

    @Override
    public Variable remove(Object obj) throws Exception {
        return (Variable) this.registry.remove(obj);
    }

    @Override
    public Iterator<Object> iterator() {
        return this.registry.iterator();
    }

    @Override
    public void set(Label label, Object obj) throws Exception {
        Variable variable = new Variable(label, obj);
        if (label != null) {
            String[] paths = label.getPaths();
            Object key = label.getKey();
            for (String str : paths) {
                this.alias.put(str, variable);
            }
            this.registry.put(key, variable);
        }
    }

    @Override
    public void commit(Object obj) throws Exception {
        for (Variable variable : this.registry.values()) {
            variable.getContact().set(obj, variable.getValue());
        }
    }

    private static class Registry extends LinkedHashMap<Object, Variable> {
        private Registry() {
        }

        public Iterator<Object> iterator() {
            return keySet().iterator();
        }
    }
}
