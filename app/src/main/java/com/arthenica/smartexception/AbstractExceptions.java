package com.arthenica.smartexception;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractExceptions {
    public static final boolean DEFAULT_IGNORE_ALL_CAUSES = false;
    public static final int DEFAULT_MAX_DEPTH = 10;
    public static final boolean DEFAULT_PRINT_MODULE_NAME = true;
    public static final boolean DEFAULT_PRINT_PACKAGE_INFORMATION = false;
    public static final boolean DEFAULT_PRINT_SUPPRESSED_EXCEPTIONS = true;
    static StackTraceElementSerializer stackTraceElementSerializer;
    static final Set<String> rootPackageSet = Collections.synchronizedSet(new HashSet());
    static final Set<String> groupPackageSet = Collections.synchronizedSet(new HashSet());
    static final Set<String> ignorePackageSet = Collections.synchronizedSet(new HashSet());
    static final Set<String> ignoreCausePackageSet = Collections.synchronizedSet(new HashSet());
    static boolean ignoreAllCauses = false;
    static boolean printPackageInformation = false;
    static boolean printModuleName = true;
    static boolean printSuppressedExceptions = true;

    public static boolean getPrintModuleName() {
        return printModuleName;
    }

    public static void setPrintModuleName(boolean z) {
        printModuleName = z;
    }

    public static void registerRootPackage(String str) {
        rootPackageSet.add(str);
    }

    public static void clearRootPackages() {
        rootPackageSet.clear();
    }

    public static void registerGroupPackage(String str) {
        groupPackageSet.add(str);
    }

    public static void clearGroupPackages() {
        groupPackageSet.clear();
    }

    public static StackTraceElementSerializer getStackTraceElementSerializer() {
        return stackTraceElementSerializer;
    }

    public static void setStackTraceElementSerializer(StackTraceElementSerializer stackTraceElementSerializer2) {
        stackTraceElementSerializer = stackTraceElementSerializer2;
    }

    public static void registerIgnorePackage(String str, boolean z) {
        ignorePackageSet.add(str);
        if (z) {
            ignoreCausePackageSet.add(str);
        }
    }

    public static void clearIgnorePackages() {
        ignorePackageSet.clear();
        ignoreCausePackageSet.clear();
    }

    public static boolean getIgnoreAllCauses() {
        return ignoreAllCauses;
    }

    public static void setIgnoreAllCauses(boolean z) {
        ignoreAllCauses = z;
    }

    public static boolean isPrintPackageInformation() {
        return printPackageInformation;
    }

    public static void setPrintPackageInformation(boolean z) {
        printPackageInformation = z;
    }

    public static boolean getPrintSuppressedExceptions() {
        return printSuppressedExceptions;
    }

    public static void setPrintSuppressedExceptions(boolean z) {
        printSuppressedExceptions = z;
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper) {
        return getStackTraceString(throwableWrapper, rootPackageSet, groupPackageSet, ignorePackageSet, 0, ignoreAllCauses, printPackageInformation);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, boolean z) {
        return getStackTraceString(throwableWrapper, rootPackageSet, groupPackageSet, ignorePackageSet, 0, z, printPackageInformation);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, Set<String> set, Set<String> set2, Set<String> set3) {
        return getStackTraceString(throwableWrapper, set, set2, set3, 0, ignoreAllCauses, printPackageInformation);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, Set<String> set, Set<String> set2, Set<String> set3, boolean z) {
        return getStackTraceString(throwableWrapper, set, set2, set3, 0, z, printPackageInformation);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, Set<String> set, Set<String> set2, Set<String> set3, boolean z, boolean z2) {
        return getStackTraceString(throwableWrapper, set, set2, set3, 0, z, z2);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, Set<String> set, Set<String> set2, Set<String> set3, boolean z, boolean z2, boolean z3) {
        return getStackTraceString(throwableWrapper, set, set2, set3, 0, z, z2, printModuleName, z3);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, String str) {
        return getStackTraceString(throwableWrapper, (Set<String>) Collections.singleton(str), new HashSet(), new HashSet(), 0, ignoreAllCauses, printPackageInformation);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, String str, String str2) {
        return getStackTraceString(throwableWrapper, (Set<String>) Collections.singleton(str), (Set<String>) Collections.singleton(str2), new HashSet(), 0, ignoreAllCauses, printPackageInformation);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, int i) {
        return getStackTraceString(throwableWrapper, new HashSet(), new HashSet(), new HashSet(), i, ignoreAllCauses, printPackageInformation);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, int i, boolean z) {
        return getStackTraceString(throwableWrapper, new HashSet(), new HashSet(), new HashSet(), i, z, printPackageInformation);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, int i, boolean z, boolean z2) {
        return getStackTraceString(throwableWrapper, new HashSet(), new HashSet(), new HashSet(), i, z, z2);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, int i, boolean z, boolean z2, boolean z3) {
        return getStackTraceString(throwableWrapper, new HashSet(), new HashSet(), new HashSet(), i, z, z2, printModuleName, z3);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, Set<String> set, Set<String> set2, Set<String> set3, int i, boolean z, boolean z2) {
        return getStackTraceString(throwableWrapper, set, set2, set3, i, z, z2, printModuleName);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, Set<String> set, Set<String> set2, Set<String> set3, int i, boolean z, boolean z2, boolean z3) {
        return getStackTraceString(throwableWrapper, false, false, set, set2, set3, i, z, z2, z3, printSuppressedExceptions);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, Set<String> set, Set<String> set2, Set<String> set3, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        return getStackTraceString(throwableWrapper, false, false, set, set2, set3, i, z, z2, z3, z4);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, boolean z, boolean z2, Set<String> set, Set<String> set2, Set<String> set3, int i, boolean z3, boolean z4, boolean z5, boolean z6) {
        return getStackTraceString(throwableWrapper, "", z, z2, set, set2, set3, i, z3, z4, z5, z6);
    }

    public static String getStackTraceString(ThrowableWrapper throwableWrapper, String str, boolean z, boolean z2, Set<String> set, Set<String> set2, Set<String> set3, int i, boolean z3, boolean z4, boolean z5, boolean z6) {
        StackTraceElement[] stackTrace;
        StringBuilder sb;
        int i2;
        StackTraceElement[] stackTraceElementArr;
        StringBuilder sb2 = new StringBuilder();
        if (throwableWrapper == null) {
            return "";
        }
        String className = throwableWrapper.getClassName();
        if (i > 0) {
            stackTrace = getStackTrace(throwableWrapper, i);
        } else {
            stackTrace = getStackTrace(throwableWrapper, set, set3);
        }
        StackTraceElement[] stackTraceElementArr2 = stackTrace;
        String message = throwableWrapper.getMessage();
        if (isEmpty(message)) {
            message = throwableWrapper.getMessage();
        }
        if (z) {
            sb2.append(System.lineSeparator());
            sb2.append(str);
            sb2.append("Caused by: ");
        } else if (z2) {
            sb2.append(System.lineSeparator());
            sb2.append(str);
            sb2.append("Suppressed: ");
        }
        sb2.append(className);
        if (!isEmpty(message)) {
            sb2.append(": ");
            sb2.append(message);
        }
        int length = stackTraceElementArr2.length;
        int i3 = 0;
        int i4 = 0;
        String str2 = null;
        StackTraceElement stackTraceElement = null;
        while (i4 < length) {
            StackTraceElement stackTraceElement2 = stackTraceElementArr2[i4];
            String containingPackage = getContainingPackage(stackTraceElement2.getClassName(), set2);
            if (containingPackage != null) {
                if (containingPackage.equals(str2)) {
                    i2 = i4;
                    stackTraceElementArr = stackTraceElementArr2;
                    i3++;
                } else {
                    stackTraceElementArr = stackTraceElementArr2;
                    i2 = i4;
                    appendStackTraceGroupElement(sb2, str2, i3, stackTraceElement, z5, z4, str);
                    sb2.append(System.lineSeparator());
                    sb2.append(str);
                    sb2.append("\tat ");
                    i3 = 1;
                    stackTraceElement = stackTraceElement2;
                    str2 = containingPackage;
                }
            } else {
                i2 = i4;
                stackTraceElementArr = stackTraceElementArr2;
                int iAppendStackTraceGroupElement = appendStackTraceGroupElement(sb2, str2, i3, stackTraceElement, z5, z4, str);
                sb2.append(System.lineSeparator());
                sb2.append(str);
                sb2.append("\tat ");
                if (stackTraceElementSerializer == null) {
                    throw new IllegalArgumentException("Stack trace element serializer not initialized.");
                }
                sb2.append(str);
                sb2.append(stackTraceElementSerializer.toString(stackTraceElement2, z5, z4));
                i3 = iAppendStackTraceGroupElement;
                str2 = null;
            }
            i4 = i2 + 1;
            stackTraceElementArr2 = stackTraceElementArr;
        }
        appendStackTraceGroupElement(sb2, str2, i3, stackTraceElement, z5, z4, str);
        ThrowableWrapper[] suppressed = throwableWrapper.getSuppressed();
        if (suppressed != null && suppressed.length > 0 && z6) {
            int length2 = suppressed.length;
            int i5 = 0;
            while (i5 < length2) {
                StringBuilder sb3 = sb2;
                sb3.append(getStackTraceString(suppressed[i5], str + "\t", false, true, set, set2, set3, i, z3, z4, z5, z6));
                i5++;
                className = className;
                sb2 = sb3;
            }
        }
        String str3 = className;
        StringBuilder sb4 = sb2;
        ThrowableWrapper cause = throwableWrapper.getCause();
        if (cause == null || containsPackage(str3, ignoreCausePackageSet) || z3) {
            sb = sb4;
        } else {
            sb = sb4;
            sb.append(getStackTraceString(cause, str, true, false, set, set2, set3, i, z3, z4, z5, z6));
        }
        return sb.toString();
    }

    public static int appendStackTraceGroupElement(StringBuilder sb, String str, int i, StackTraceElement stackTraceElement, boolean z, boolean z2, String str2) {
        if (i <= 0) {
            return 0;
        }
        if (stackTraceElementSerializer == null) {
            throw new IllegalArgumentException("Stack trace element serializer not initialized.");
        }
        sb.append(str2);
        if (i == 1) {
            sb.append(stackTraceElementSerializer.toString(stackTraceElement, z, z2));
            return 0;
        }
        sb.append(String.format("%s%s ... %d more", stackTraceElementSerializer.getModuleName(stackTraceElement), str, Integer.valueOf(i - 1)));
        if (!z2) {
            return 0;
        }
        sb.append(stackTraceElementSerializer.getPackageInformation(stackTraceElement));
        return 0;
    }

    public static boolean containsPackage(String str, Set<String> set) {
        return getContainingPackage(str, set) != null;
    }

    public static String getContainingPackage(String str, Set<String> set) {
        for (String str2 : set) {
            if (str.startsWith(str2)) {
                return str2;
            }
        }
        return null;
    }

    public static String getAllMessages(Throwable th) {
        StringBuilder sb = new StringBuilder();
        getAllMessages(th, sb);
        return sb.toString();
    }

    public static void getAllMessages(Throwable th, StringBuilder sb) {
        if (th != null) {
            String message = th.getMessage();
            if (!isEmpty(message)) {
                if (sb.length() != 0) {
                    sb.append(System.lineSeparator());
                    sb.append(" - Caused by: ");
                }
                sb.append(message);
            }
            getAllMessages(th.getCause(), sb);
        }
    }

    public static StackTraceElement[] getStackTrace(ThrowableWrapper throwableWrapper, int i) {
        ArrayList arrayList = new ArrayList();
        if (throwableWrapper != null) {
            StackTraceElementWrapper[] stackTrace = throwableWrapper.getStackTrace();
            for (int i2 = 0; i2 < stackTrace.length && i2 < i; i2++) {
                arrayList.add(stackTrace[i2].getStackTraceElement());
            }
        }
        return (StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]);
    }

    public static StackTraceElement[] getStackTrace(ThrowableWrapper throwableWrapper, Set<String> set, Set<String> set2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (throwableWrapper != null) {
            for (StackTraceElementWrapper stackTraceElementWrapper : throwableWrapper.getStackTrace()) {
                String className = stackTraceElementWrapper.getStackTraceElement().getClassName();
                if (!isEmpty(className)) {
                    if (containsPackage(className, set)) {
                        arrayList.addAll(arrayList2);
                        arrayList.add(stackTraceElementWrapper.getStackTraceElement());
                    } else if (!containsPackage(className, set2)) {
                        arrayList2.add(stackTraceElementWrapper.getStackTraceElement());
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(arrayList2);
        }
        return (StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]);
    }

    public static boolean containsCause(Throwable th, Class<?> cls) {
        return containsCause(th, cls, null);
    }

    public static boolean containsCause(Throwable th, Class<?> cls, String str) {
        return searchCause(th, cls, str, 10) != null;
    }

    public static Throwable getCause(Throwable th) {
        return getCause(th, 10);
    }

    public static Throwable getCause(Throwable th, int i) {
        Throwable cause;
        if (th == null) {
            return null;
        }
        return (i > 0 && (cause = th.getCause()) != null) ? getCause(cause, i - 1) : th;
    }

    public static Throwable searchCause(Throwable th, Class<?> cls) {
        return searchCause(th, cls, null, 10);
    }

    public static Throwable searchCause(Throwable th, Class<?> cls, String str) {
        return searchCause(th, cls, str, 10);
    }

    public static Throwable searchCause(Throwable th, Class<?> cls, String str, int i) {
        Throwable cause;
        if (th == null) {
            return null;
        }
        if (isEmpty(str)) {
            if (th.getClass().equals(cls)) {
                return th;
            }
        } else if (th.getClass().equals(cls) && getAllMessages(th).toLowerCase().contains(str.toLowerCase())) {
            return th;
        }
        if (i > 0 && (cause = th.getCause()) != null) {
            return searchCause(cause, cls, str, i - 1);
        }
        return null;
    }

    public static Throwable searchCause(Throwable th, Class<?> cls, int i) {
        Throwable cause;
        if (th == null) {
            return null;
        }
        if (th.getClass().equals(cls)) {
            return th;
        }
        if (i > 0 && (cause = th.getCause()) != null) {
            return searchCause(cause, cls, i - 1);
        }
        return null;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String packageName(String str) {
        int iLastIndexOf;
        return (str != null && (iLastIndexOf = str.lastIndexOf(".")) >= 0) ? str.substring(0, iLastIndexOf) : "";
    }

    public static String version(PackageLoader packageLoader, Class<?> cls, String str) {
        try {
            Package r0 = cls.getPackage();
            if (r0 != null) {
                return r0.getImplementationVersion();
            }
            Package r1 = packageLoader.getPackage(cls.getClassLoader(), str);
            if (r1 != null) {
                return r1.getImplementationVersion();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String libraryName(Class<?> cls) {
        String string;
        int iLastIndexOf;
        if (cls == null) {
            return null;
        }
        try {
            URL resource = cls.getClassLoader().getResource(cls.getName().replace('.', '/') + ".class");
            if (resource == null || (iLastIndexOf = (string = resource.toString()).lastIndexOf(33)) <= 0) {
                return null;
            }
            String strSubstring = string.substring(0, iLastIndexOf);
            int iLastIndexOf2 = strSubstring.lastIndexOf(47);
            if (iLastIndexOf2 > 0) {
                strSubstring = strSubstring.substring(iLastIndexOf2 + 1);
            }
            int iLastIndexOf3 = strSubstring.lastIndexOf(92);
            return iLastIndexOf3 > 0 ? strSubstring.substring(iLastIndexOf3 + 1) : strSubstring;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String packageInformation(String str, String str2) {
        boolean z = str != null;
        boolean z2 = str2 != null;
        if (!z && !z2) {
            return "";
        }
        StringBuilder sb = new StringBuilder(" [");
        if (z) {
            sb.append(str);
        }
        if (z2) {
            if (z) {
                if (!str.contains(str2)) {
                    sb.append(":");
                    sb.append(str2);
                }
            } else {
                sb.append(str2);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
