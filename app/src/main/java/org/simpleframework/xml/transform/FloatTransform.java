package org.simpleframework.xml.transform;

class FloatTransform implements Transform<Float> {
    FloatTransform() {
    }

    @Override
    public Float read(String str) {
        return Float.valueOf(str);
    }

    @Override
    public String write(Float f) {
        return f.toString();
    }
}
