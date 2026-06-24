package kotlin.reflect.jvm.internal.calls;

import com.arthenica.ffmpegkit.StreamInformation;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.Typography;

@Metadata(m1292d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u001aI\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\u0010\u000b\u001a$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u0014²\u0006\u0014\u0010\u0015\u001a\u00020\u000f\"\b\b\u0000\u0010\u0001*\u00020\u0002X\u008a\u0084\u0002²\u0006\u0014\u0010\u0016\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0002X\u008a\u0084\u0002"}, m1293d2 = {"createAnnotationInstance", "T", "", "annotationClass", "Ljava/lang/Class;", "values", "", "", "methods", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/Map;Ljava/util/List;)Ljava/lang/Object;", "throwIllegalArgumentType", "", StreamInformation.KEY_INDEX, "", NamingTable.TAG, "expectedJvmType", "transformKotlinToJvm", "expectedType", "kotlin-reflection", "hashCode", "toString"}, m1294k = 2, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class AnnotationConstructorCallerKt {
    public static final Object transformKotlinToJvm(Object obj, Class<?> cls) {
        if (obj instanceof Class) {
            return null;
        }
        if (obj instanceof KClass) {
            obj = JvmClassMappingKt.getJavaClass((KClass) obj);
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr instanceof Class[]) {
                return null;
            }
            if (!(objArr instanceof KClass[])) {
                obj = objArr;
            } else {
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
                }
                KClass[] kClassArr = (KClass[]) obj;
                ArrayList arrayList = new ArrayList(kClassArr.length);
                for (KClass kClass : kClassArr) {
                    arrayList.add(JvmClassMappingKt.getJavaClass(kClass));
                }
                obj = arrayList.toArray(new Class[0]);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
            }
        }
        if (cls.isInstance(obj)) {
            return obj;
        }
        return null;
    }

    public static final Void throwIllegalArgumentType(int i, String str, Class<?> cls) {
        KClass orCreateKotlinClass;
        String qualifiedName;
        if (Intrinsics.areEqual(cls, Class.class)) {
            orCreateKotlinClass = Reflection.getOrCreateKotlinClass(KClass.class);
        } else {
            orCreateKotlinClass = (cls.isArray() && Intrinsics.areEqual(cls.getComponentType(), Class.class)) ? Reflection.getOrCreateKotlinClass(KClass[].class) : JvmClassMappingKt.getKotlinClass(cls);
        }
        if (Intrinsics.areEqual(orCreateKotlinClass.getQualifiedName(), Reflection.getOrCreateKotlinClass(Object[].class).getQualifiedName())) {
            StringBuilder sbAppend = new StringBuilder().append(orCreateKotlinClass.getQualifiedName()).append(Typography.less);
            Class<?> componentType = JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "kotlinClass.java.componentType");
            qualifiedName = sbAppend.append(JvmClassMappingKt.getKotlinClass(componentType).getQualifiedName()).append(Typography.greater).toString();
        } else {
            qualifiedName = orCreateKotlinClass.getQualifiedName();
        }
        throw new IllegalArgumentException("Argument #" + i + ' ' + str + " is not of the required type " + qualifiedName);
    }

    public static Object createAnnotationInstance$default(Class cls, Map map, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            Set setKeySet = map.keySet();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
            Iterator it = setKeySet.iterator();
            while (it.hasNext()) {
                arrayList.add(cls.getDeclaredMethod((String) it.next(), new Class[0]));
            }
            list = arrayList;
        }
        return createAnnotationInstance(cls, map, list);
    }

    public static final <T> boolean createAnnotationInstance$equals(Class<T> cls, List<Method> list, Map<String, ? extends Object> map, Object obj) throws IllegalAccessException, InvocationTargetException {
        boolean zAreEqual;
        boolean z;
        KClass annotationClass;
        Class javaClass = null;
        Annotation annotation = obj instanceof Annotation ? (Annotation) obj : null;
        if (annotation != null && (annotationClass = JvmClassMappingKt.getAnnotationClass(annotation)) != null) {
            javaClass = JvmClassMappingKt.getJavaClass(annotationClass);
        }
        if (!Intrinsics.areEqual(javaClass, cls)) {
            return false;
        }
        List<Method> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            z = true;
        } else {
            for (Method method : list2) {
                Object obj2 = map.get(method.getName());
                Object objInvoke = method.invoke(obj, new Object[0]);
                if (obj2 instanceof boolean[]) {
                    boolean[] zArr = (boolean[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.BooleanArray");
                    }
                    zAreEqual = Arrays.equals(zArr, (boolean[]) objInvoke);
                } else if (obj2 instanceof char[]) {
                    char[] cArr = (char[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.CharArray");
                    }
                    zAreEqual = Arrays.equals(cArr, (char[]) objInvoke);
                } else if (obj2 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.ByteArray");
                    }
                    zAreEqual = Arrays.equals(bArr, (byte[]) objInvoke);
                } else if (obj2 instanceof short[]) {
                    short[] sArr = (short[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.ShortArray");
                    }
                    zAreEqual = Arrays.equals(sArr, (short[]) objInvoke);
                } else if (obj2 instanceof int[]) {
                    int[] iArr = (int[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.IntArray");
                    }
                    zAreEqual = Arrays.equals(iArr, (int[]) objInvoke);
                } else if (obj2 instanceof float[]) {
                    float[] fArr = (float[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.FloatArray");
                    }
                    zAreEqual = Arrays.equals(fArr, (float[]) objInvoke);
                } else if (obj2 instanceof long[]) {
                    long[] jArr = (long[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.LongArray");
                    }
                    zAreEqual = Arrays.equals(jArr, (long[]) objInvoke);
                } else if (obj2 instanceof double[]) {
                    double[] dArr = (double[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.DoubleArray");
                    }
                    zAreEqual = Arrays.equals(dArr, (double[]) objInvoke);
                } else if (obj2 instanceof Object[]) {
                    Object[] objArr = (Object[]) obj2;
                    if (objInvoke == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<*>");
                    }
                    zAreEqual = Arrays.equals(objArr, (Object[]) objInvoke);
                } else {
                    zAreEqual = Intrinsics.areEqual(obj2, objInvoke);
                }
                if (!zAreEqual) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    public static final <T> T createAnnotationInstance(final Class<T> annotationClass, final Map<String, ? extends Object> values, final List<Method> methods) {
        Intrinsics.checkNotNullParameter(annotationClass, "annotationClass");
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(methods, "methods");
        final Lazy lazy = LazyKt.lazy(new Function0<Integer>() {
            {
                super(0);
            }

            @Override
            public final Integer invoke() {
                int iHashCode;
                Iterator<T> it = values.entrySet().iterator();
                int iHashCode2 = 0;
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof boolean[]) {
                        iHashCode = Arrays.hashCode((boolean[]) value);
                    } else if (value instanceof char[]) {
                        iHashCode = Arrays.hashCode((char[]) value);
                    } else if (value instanceof byte[]) {
                        iHashCode = Arrays.hashCode((byte[]) value);
                    } else if (value instanceof short[]) {
                        iHashCode = Arrays.hashCode((short[]) value);
                    } else if (value instanceof int[]) {
                        iHashCode = Arrays.hashCode((int[]) value);
                    } else if (value instanceof float[]) {
                        iHashCode = Arrays.hashCode((float[]) value);
                    } else if (value instanceof long[]) {
                        iHashCode = Arrays.hashCode((long[]) value);
                    } else if (value instanceof double[]) {
                        iHashCode = Arrays.hashCode((double[]) value);
                    } else {
                        iHashCode = value instanceof Object[] ? Arrays.hashCode((Object[]) value) : value.hashCode();
                    }
                    iHashCode2 += iHashCode ^ (str.hashCode() * 127);
                }
                return Integer.valueOf(iHashCode2);
            }
        });
        final Lazy lazy2 = LazyKt.lazy(new Function0<String>() {
            {
                super(0);
            }

            @Override
            public final String invoke() {
                Class<T> cls = annotationClass;
                Map<String, Object> map = values;
                StringBuilder sb = new StringBuilder();
                sb.append('@');
                sb.append(cls.getCanonicalName());
                CollectionsKt.joinTo$default(map.entrySet(), sb, ", ", "(", ")", 0, null, new Function1<Map.Entry<? extends String, ? extends Object>, CharSequence>() {
                    @Override
                    public CharSequence invoke(Map.Entry<? extends String, ? extends Object> entry) {
                        return invoke2((Map.Entry<String, ? extends Object>) entry);
                    }

                    public final CharSequence invoke2(Map.Entry<String, ? extends Object> entry) {
                        String string;
                        Intrinsics.checkNotNullParameter(entry, "entry");
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value instanceof boolean[]) {
                            string = Arrays.toString((boolean[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else if (value instanceof char[]) {
                            string = Arrays.toString((char[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else if (value instanceof byte[]) {
                            string = Arrays.toString((byte[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else if (value instanceof short[]) {
                            string = Arrays.toString((short[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else if (value instanceof int[]) {
                            string = Arrays.toString((int[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else if (value instanceof float[]) {
                            string = Arrays.toString((float[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else if (value instanceof long[]) {
                            string = Arrays.toString((long[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else if (value instanceof double[]) {
                            string = Arrays.toString((double[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else if (value instanceof Object[]) {
                            string = Arrays.toString((Object[]) value);
                            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                        } else {
                            string = value.toString();
                        }
                        return key + '=' + string;
                    }
                }, 48, null);
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            }
        });
        T t = (T) Proxy.newProxyInstance(annotationClass.getClassLoader(), new Class[]{annotationClass}, new InvocationHandler() {
            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r4, java.lang.reflect.Method r5, java.lang.Object[] r6) {
                /*
                    r3 = this;
                    java.lang.String r4 = r5.getName()
                    if (r4 == 0) goto L4a
                    int r0 = r4.hashCode()
                    r1 = -1776922004(0xffffffff9616526c, float:-1.2142911E-25)
                    if (r0 == r1) goto L3a
                    r1 = 147696667(0x8cdac1b, float:1.23784505E-33)
                    if (r0 == r1) goto L26
                    r1 = 1444986633(0x5620bf09, float:4.4185588E13)
                    if (r0 == r1) goto L1a
                    goto L4a
                L1a:
                    java.lang.String r0 = "annotationType"
                    boolean r0 = r4.equals(r0)
                    if (r0 != 0) goto L23
                    goto L4a
                L23:
                    java.lang.Class<T> r4 = r1
                    goto L83
                L26:
                    java.lang.String r0 = "hashCode"
                    boolean r0 = r4.equals(r0)
                    if (r0 != 0) goto L2f
                    goto L4a
                L2f:
                    kotlin.Lazy<java.lang.Integer> r4 = r4
                    int r4 = kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.m3058access$createAnnotationInstance$lambda2(r4)
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                    goto L83
                L3a:
                    java.lang.String r0 = "toString"
                    boolean r0 = r4.equals(r0)
                    if (r0 != 0) goto L43
                    goto L4a
                L43:
                    kotlin.Lazy<java.lang.String> r4 = r3
                    java.lang.String r4 = kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.m3059access$createAnnotationInstance$lambda3(r4)
                    goto L83
                L4a:
                    java.lang.String r0 = "equals"
                    boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
                    r1 = 0
                    if (r0 == 0) goto L75
                    if (r6 == 0) goto L5a
                    int r0 = r6.length
                    r2 = 1
                    if (r0 != r2) goto L5a
                    goto L5b
                L5a:
                    r2 = r1
                L5b:
                    if (r2 == 0) goto L75
                    java.lang.Class<T> r4 = r1
                    java.util.List<java.lang.reflect.Method> r5 = r5
                    java.util.Map<java.lang.String, java.lang.Object> r0 = r2
                    java.lang.String r1 = "args"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)
                    java.lang.Object r6 = kotlin.collections.ArraysKt.single(r6)
                    boolean r4 = kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.access$createAnnotationInstance$equals(r4, r5, r0, r6)
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
                    goto L83
                L75:
                    java.util.Map<java.lang.String, java.lang.Object> r0 = r2
                    boolean r0 = r0.containsKey(r4)
                    if (r0 == 0) goto L84
                    java.util.Map<java.lang.String, java.lang.Object> r5 = r2
                    java.lang.Object r4 = r5.get(r4)
                L83:
                    return r4
                L84:
                    kotlin.reflect.jvm.internal.KotlinReflectionInternalError r4 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    java.lang.String r2 = "Method is not supported: "
                    r0.<init>(r2)
                    java.lang.StringBuilder r5 = r0.append(r5)
                    java.lang.String r0 = " (args: "
                    java.lang.StringBuilder r5 = r5.append(r0)
                    if (r6 != 0) goto L9b
                    java.lang.Object[] r6 = new java.lang.Object[r1]
                L9b:
                    java.util.List r6 = kotlin.collections.ArraysKt.toList(r6)
                    java.lang.StringBuilder r5 = r5.append(r6)
                    r6 = 41
                    java.lang.StringBuilder r5 = r5.append(r6)
                    java.lang.String r5 = r5.toString()
                    r4.<init>(r5)
                    throw r4
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$result$1.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
            }
        });
        if (t != null) {
            return t;
        }
        throw new NullPointerException("null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance");
    }

    public static final int m3060createAnnotationInstance$lambda2(Lazy<Integer> lazy) {
        return lazy.getValue().intValue();
    }

    public static final String m3061createAnnotationInstance$lambda3(Lazy<String> lazy) {
        return lazy.getValue();
    }
}
