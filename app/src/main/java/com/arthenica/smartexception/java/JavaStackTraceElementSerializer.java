package com.arthenica.smartexception.java;

import com.arthenica.smartexception.AbstractExceptions;
import com.arthenica.smartexception.StackTraceElementSerializer;

public class JavaStackTraceElementSerializer implements StackTraceElementSerializer {
    @Override
    public String getModuleName(StackTraceElement stackTraceElement) {
        return "";
    }

    @Override
    public String getNativeMethodDefinition() {
        return "(Native Method)";
    }

    @Override
    public String getUnknownSourceDefinition() {
        return "(Unknown Source)";
    }

    @Override
    public String toString(StackTraceElement stackTraceElement, boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append(stackTraceElement.getClassName());
        sb.append(".");
        sb.append(stackTraceElement.getMethodName());
        if (stackTraceElement.isNativeMethod()) {
            sb.append(getNativeMethodDefinition());
        } else if (stackTraceElement.getFileName() != null && stackTraceElement.getFileName().length() > 0) {
            sb.append("(");
            sb.append(stackTraceElement.getFileName());
            if (stackTraceElement.getLineNumber() >= 0) {
                sb.append(":");
                sb.append(stackTraceElement.getLineNumber());
            }
            sb.append(")");
        } else {
            sb.append(getUnknownSourceDefinition());
        }
        if (z2) {
            sb.append(getPackageInformation(stackTraceElement));
        }
        return sb.toString();
    }

    @Override
    public String getPackageInformation(StackTraceElement stackTraceElement) {
        StringBuilder sb = new StringBuilder();
        String className = stackTraceElement.getClassName();
        Class<?> clsLoadClass = Exceptions.classLoader.loadClass(className);
        if (clsLoadClass != null) {
            sb.append(AbstractExceptions.packageInformation(AbstractExceptions.libraryName(clsLoadClass), AbstractExceptions.version(Exceptions.packageLoader, clsLoadClass, AbstractExceptions.packageName(className))));
        }
        return sb.toString();
    }
}
