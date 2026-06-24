package kotlin.reflect.jvm.internal.impl.load.java.structure;

public interface JavaRecordComponent extends JavaMember {
    JavaType getType();

    boolean isVararg();
}
