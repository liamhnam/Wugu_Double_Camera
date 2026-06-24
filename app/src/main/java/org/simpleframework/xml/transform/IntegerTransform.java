package org.simpleframework.xml.transform;

class IntegerTransform implements Transform<Integer> {
    IntegerTransform() {
    }

    @Override
    public Integer read(String str) {
        return Integer.valueOf(str);
    }

    @Override
    public String write(Integer num) {
        return num.toString();
    }
}
