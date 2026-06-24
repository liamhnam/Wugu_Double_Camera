package com.arthenica.smartexception;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Set;

public class ThrowableWrapper {
    private final ThrowableWrapper cause;
    private final String className;
    private final String message;
    private final StackTraceElementWrapper[] stackTrace;
    private final ThrowableWrapper[] suppressed;

    public ThrowableWrapper(Throwable th) {
        this(th, Collections.newSetFromMap(new IdentityHashMap()));
    }

    public ThrowableWrapper(Throwable th, Set<Throwable> set) {
        set.add(th);
        this.message = th.getMessage();
        if (th.getCause() != null && !set.contains(th.getCause())) {
            this.cause = new ThrowableWrapper(th.getCause(), set);
        } else {
            this.cause = null;
        }
        this.className = th.getClass().getName();
        Throwable[] suppressed = th.getSuppressed();
        LinkedList linkedList = new LinkedList();
        int length = suppressed.length;
        for (int i = 0; i < length; i++) {
            if (!set.contains(suppressed[i])) {
                linkedList.add(new ThrowableWrapper(suppressed[i], set));
            }
        }
        this.suppressed = (ThrowableWrapper[]) linkedList.toArray(new ThrowableWrapper[0]);
        StackTraceElement[] stackTrace = th.getStackTrace();
        this.stackTrace = new StackTraceElementWrapper[stackTrace.length];
        int length2 = stackTrace.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.stackTrace[i2] = new StackTraceElementWrapper(stackTrace[i2]);
        }
    }

    public ThrowableWrapper(String str, ThrowableWrapper throwableWrapper, String str2, ThrowableWrapper[] throwableWrapperArr, StackTraceElementWrapper[] stackTraceElementWrapperArr) {
        this.message = str;
        this.cause = throwableWrapper;
        this.className = str2;
        this.suppressed = throwableWrapperArr;
        this.stackTrace = stackTraceElementWrapperArr;
    }

    public String getMessage() {
        return this.message;
    }

    public ThrowableWrapper getCause() {
        return this.cause;
    }

    public String getClassName() {
        return this.className;
    }

    public ThrowableWrapper[] getSuppressed() {
        return this.suppressed;
    }

    public StackTraceElementWrapper[] getStackTrace() {
        return this.stackTrace;
    }
}
