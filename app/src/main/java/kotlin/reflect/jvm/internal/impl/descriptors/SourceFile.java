package kotlin.reflect.jvm.internal.impl.descriptors;

public interface SourceFile {
    public static final SourceFile NO_SOURCE_FILE = new SourceFile() {
        @Override
        public String getName() {
            return null;
        }
    };

    String getName();
}
