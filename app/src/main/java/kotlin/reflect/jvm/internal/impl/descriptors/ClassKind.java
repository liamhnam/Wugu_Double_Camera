package kotlin.reflect.jvm.internal.impl.descriptors;

import org.simpleframework.xml.strategy.Name;

public enum ClassKind {
    CLASS(Name.LABEL),
    INTERFACE("interface"),
    ENUM_CLASS("enum class"),
    ENUM_ENTRY(null),
    ANNOTATION_CLASS("annotation class"),
    OBJECT("object");

    private final String codeRepresentation;

    ClassKind(String str) {
        this.codeRepresentation = str;
    }

    public final boolean isSingleton() {
        return this == OBJECT || this == ENUM_ENTRY;
    }
}
