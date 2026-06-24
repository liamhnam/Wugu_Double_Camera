package com.p020hp.printsdk.exception;

import kotlin.Metadata;

@Metadata(m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, m1293d2 = {"Lcom/hp/printsdk/exception/BaseException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "()V", "errorCode", "", "getErrorCode", "()I", "toString", "", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class BaseException extends RuntimeException {
    public abstract int getErrorCode();

    @Override
    public String toString() {
        String string;
        Throwable cause = getCause();
        return (cause == null || (string = new StringBuilder("Error code: ").append(getErrorCode()).append(", ").append(getMessage()).append(", ").append(cause).toString()) == null) ? "Error code: " + getErrorCode() + ", " + getMessage() : string;
    }
}
