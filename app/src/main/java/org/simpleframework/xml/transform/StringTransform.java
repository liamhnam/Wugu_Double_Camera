package org.simpleframework.xml.transform;

class StringTransform implements Transform<String> {
    @Override
    public String read(String str) {
        return str;
    }

    @Override
    public String write(String str) {
        return str;
    }

    StringTransform() {
    }
}
