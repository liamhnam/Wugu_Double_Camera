package androidx.profileinstaller;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.util.Arrays;

public class ProfileVersion {
    public static final int MAX_SUPPORTED_SDK = 33;
    public static final int MIN_SUPPORTED_SDK = 24;
    static final byte[] V015_S = {SnmpConstants.CONS_SEQ, 49, 53, 0};
    static final byte[] V010_P = {SnmpConstants.CONS_SEQ, 49, SnmpConstants.CONS_SEQ, 0};
    static final byte[] V009_O_MR1 = {SnmpConstants.CONS_SEQ, SnmpConstants.CONS_SEQ, 57, 0};
    static final byte[] V005_O = {SnmpConstants.CONS_SEQ, SnmpConstants.CONS_SEQ, 53, 0};
    static final byte[] V001_N = {SnmpConstants.CONS_SEQ, SnmpConstants.CONS_SEQ, 49, 0};
    static final byte[] METADATA_V001_N = {SnmpConstants.CONS_SEQ, SnmpConstants.CONS_SEQ, 49, 0};
    static final byte[] METADATA_V002 = {SnmpConstants.CONS_SEQ, SnmpConstants.CONS_SEQ, 50, 0};

    private ProfileVersion() {
    }

    static String dexKeySeparator(byte[] bArr) {
        return (Arrays.equals(bArr, V001_N) || Arrays.equals(bArr, V005_O)) ? ":" : "!";
    }
}
