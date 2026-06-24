package androidx.core.util;

import java.util.Objects;

public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Predicate() {
            @Override
            public final boolean test(Object obj) {
                return Predicate.lambda$and$0(this.f$0, predicate, obj);
            }
        };
    }

    static boolean lambda$and$0(Predicate _this, Predicate predicate, Object obj) {
        return _this.test(obj) && predicate.test(obj);
    }

    static boolean lambda$negate$1(Predicate _this, Object obj) {
        return !_this.test(obj);
    }

    default Predicate<T> negate() {
        return new Predicate() {
            @Override
            public final boolean test(Object obj) {
                return Predicate.lambda$negate$1(this.f$0, obj);
            }
        };
    }

    default Predicate<T> m134or(final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Predicate() {
            @Override
            public final boolean test(Object obj) {
                return Predicate.lambda$or$2(this.f$0, predicate, obj);
            }
        };
    }

    static boolean lambda$or$2(Predicate _this, Predicate predicate, Object obj) {
        return _this.test(obj) || predicate.test(obj);
    }

    static <T> Predicate<T> isEqual(final Object obj) {
        if (obj == null) {
            return new Predicate() {
                @Override
                public final boolean test(Object obj2) {
                    return Objects.isNull(obj2);
                }
            };
        }
        return new Predicate() {
            @Override
            public final boolean test(Object obj2) {
                return obj.equals(obj2);
            }
        };
    }

    static <T> Predicate<T> not(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate.negate();
    }
}
