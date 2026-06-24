package org.simpleframework.xml.transform;

class ShortTransform implements Transform<Short> {
    ShortTransform() {
    }

    @Override
    public Short read(String str) {
        return Short.valueOf(str);
    }

    @Override
    public String write(Short sh) {
        return sh.toString();
    }
}
