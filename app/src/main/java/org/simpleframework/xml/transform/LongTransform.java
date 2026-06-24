package org.simpleframework.xml.transform;

class LongTransform implements Transform<Long> {
    LongTransform() {
    }

    @Override
    public Long read(String str) {
        return Long.valueOf(str);
    }

    @Override
    public String write(Long l) {
        return l.toString();
    }
}
