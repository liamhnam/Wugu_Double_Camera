package org.simpleframework.xml.transform;

class BooleanTransform implements Transform<Boolean> {
    BooleanTransform() {
    }

    @Override
    public Boolean read(String str) {
        return Boolean.valueOf(str);
    }

    @Override
    public String write(Boolean bool) {
        return bool.toString();
    }
}
