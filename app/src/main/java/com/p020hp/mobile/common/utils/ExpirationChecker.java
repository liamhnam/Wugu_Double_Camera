package com.p020hp.mobile.common.utils;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.p020hp.jipp.model.JobPasswordEncryption;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¨\u0006\r"}, m1293d2 = {"Lcom/hp/mobile/common/utils/ExpirationChecker;", "", "()V", "hasExpired", "", "expDate", "", "expDateDigest", "isValid", "digest", JobPasswordEncryption.md5, "s", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class ExpirationChecker {
    public static final byte[] byteMsg;
    public static final String md5;
    public static final String message;
    public static final String salt;

    public static final Companion INSTANCE = new Companion(null);
    public static final Logger log = LoggerKt.logger(LoggerKt.toTag("ExpirationChecker"));

    @Metadata(m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m1293d2 = {"Lcom/hp/mobile/common/utils/ExpirationChecker$Companion;", "", "()V", "byteMsg", "", "log", "Lcom/hp/mobile/common/utils/Logger;", JobPasswordEncryption.md5, "", "message", "getMessage", "()Ljava/lang/String;", "salt", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getMessage() {
            return ExpirationChecker.message;
        }
    }

    static {
        byte[] bArr = {89, 79, 85, 82, 32, 83, SnmpConstants.OPAQUE, 75, 32, 84, 82, 73, SnmpConstants.COUNTER, 76, 32, 80, SnmpConstants.NSAP_ADDRESS, 82, 73, 79, SnmpConstants.OPAQUE, 32, 72, SnmpConstants.COUNTER, 83, 32, SnmpConstants.NSAP_ADDRESS, 88, 80, 73, 82, SnmpConstants.NSAP_ADDRESS, SnmpConstants.OPAQUE, 46, 32, 80, 76, SnmpConstants.NSAP_ADDRESS, SnmpConstants.COUNTER, 83, SnmpConstants.NSAP_ADDRESS, 44, 32, SnmpConstants.TIMETICKS, 79, 78, 84, SnmpConstants.COUNTER, SnmpConstants.TIMETICKS, 84, 32, 72, 80, 32, SnmpConstants.COUNTER64, 79, 82, 32, SnmpConstants.COUNTER64, 85, 82, 84, 72, SnmpConstants.NSAP_ADDRESS, 82, 32, 85, 83, SnmpConstants.NSAP_ADDRESS, 46, 76, SnmpConstants.NSAP_ADDRESS, SnmpConstants.OPAQUE, 77, 124, SnmpConstants.TIMETICKS, SnmpConstants.OPAQUE, 77, 124, 80, SnmpConstants.TIMETICKS, 76, 77, 124, 85, 83, SnmpConstants.TIMETICKS, SnmpConstants.COUNTER, 78, 77, SnmpConstants.OPAQUE, 53};
        byteMsg = bArr;
        salt = new String(ArraysKt.copyOfRange(bArr, 70, 89), Charsets.UTF_8);
        md5 = new String(ArraysKt.copyOfRange(bArr, 89, 92), Charsets.UTF_8);
        message = new String(ArraysKt.copyOfRange(bArr, 0, 70), Charsets.UTF_8);
    }

    public static boolean hasExpired$default(ExpirationChecker expirationChecker, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        if ((i & 2) != 0) {
            str2 = "f9b3cbfd6c84bc3586f7a532989e7bbf";
        }
        return expirationChecker.hasExpired(str, str2);
    }

    private final boolean isValid(String expDate, String digest) {
        return Intrinsics.areEqual(md5(expDate + salt), digest);
    }

    private final String md5(String s) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(md5);
            byte[] bytes = s.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            messageDigest.update(bytes, 0, s.length());
            String string = new BigInteger(1, messageDigest.digest()).toString(16);
            Intrinsics.checkNotNullExpressionValue(string, "BigInteger(1, m.digest()).toString(16)");
            return string;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public final boolean hasExpired() {
        return hasExpired$default(this, null, null, 3, null);
    }

    public final boolean hasExpired(String expDate) {
        Intrinsics.checkNotNullParameter(expDate, "expDate");
        return hasExpired$default(this, expDate, null, 2, null);
    }

    public final boolean hasExpired(String expDate, String expDateDigest) {
        Object objM1772constructorimpl;
        Intrinsics.checkNotNullParameter(expDate, "expDate");
        Intrinsics.checkNotNullParameter(expDateDigest, "expDateDigest");
        if (expDate.length() == 0) {
            return false;
        }
        if (!isValid(expDate, expDateDigest)) {
            return true;
        }
        try {
            objM1772constructorimpl = Result.m1772constructorimpl(Long.valueOf(Long.parseLong(expDate)));
        } catch (Throwable th) {
            objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
            objM1772constructorimpl = -1L;
        }
        if (((Number) objM1772constructorimpl).longValue() - new Date().getTime() > 0) {
            return false;
        }
        log.m447e("Expire ERROR " + message);
        return true;
    }
}
