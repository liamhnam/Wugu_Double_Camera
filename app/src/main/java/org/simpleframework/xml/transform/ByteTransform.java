package org.simpleframework.xml.transform;

class ByteTransform implements Transform<Byte> {
    ByteTransform() {
    }

    @Override
    public Byte read(String str) {
        return Byte.valueOf(str);
    }

    @Override
    public String write(Byte b) {
        return b.toString();
    }
}
