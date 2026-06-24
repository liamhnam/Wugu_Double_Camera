package org.simpleframework.xml.transform;

class DoubleTransform implements Transform<Double> {
    DoubleTransform() {
    }

    @Override
    public Double read(String str) {
        return Double.valueOf(str);
    }

    @Override
    public String write(Double d) {
        return d.toString();
    }
}
