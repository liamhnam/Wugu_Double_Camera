package org.simpleframework.xml.transform;

class EnumTransform implements Transform<Enum> {
    private final Class type;

    public EnumTransform(Class cls) {
        this.type = cls;
    }

    @Override
    public Enum read(String str) throws Exception {
        return Enum.valueOf(this.type, str);
    }

    @Override
    public String write(Enum r1) throws Exception {
        return r1.name();
    }
}
