package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

public final class FunctionsKt {
    private static final Function1<Object, Object> IDENTITY = new Function1<Object, Object>() {
        @Override
        public final Object invoke(Object obj) {
            return obj;
        }
    };
    private static final Function1<Object, Boolean> ALWAYS_TRUE = new Function1<Object, Boolean>() {
        @Override
        public final Boolean invoke(Object obj) {
            return true;
        }
    };
    private static final Function1<Object, Object> ALWAYS_NULL = new Function1() {
        @Override
        public final Void invoke(Object obj) {
            return null;
        }
    };
    private static final Function1<Object, Unit> DO_NOTHING = new Function1<Object, Unit>() {
        public final void invoke2(Object obj) {
        }

        @Override
        public Unit invoke(Object obj) {
            invoke2(obj);
            return Unit.INSTANCE;
        }
    };
    private static final Function2<Object, Object, Unit> DO_NOTHING_2 = new Function2<Object, Object, Unit>() {
        public final void invoke2(Object obj, Object obj2) {
        }

        @Override
        public Unit invoke(Object obj, Object obj2) {
            invoke2(obj, obj2);
            return Unit.INSTANCE;
        }
    };
    private static final Function3<Object, Object, Object, Unit> DO_NOTHING_3 = new Function3<Object, Object, Object, Unit>() {
        public final void invoke2(Object obj, Object obj2, Object obj3) {
        }

        @Override
        public Unit invoke(Object obj, Object obj2, Object obj3) {
            invoke2(obj, obj2, obj3);
            return Unit.INSTANCE;
        }
    };

    public static final <T> Function1<T, Boolean> alwaysTrue() {
        return (Function1<T, Boolean>) ALWAYS_TRUE;
    }

    public static final Function3<Object, Object, Object, Unit> getDO_NOTHING_3() {
        return DO_NOTHING_3;
    }
}
