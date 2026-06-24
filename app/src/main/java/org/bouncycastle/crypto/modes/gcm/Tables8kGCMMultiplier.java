package org.bouncycastle.crypto.modes.gcm;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;

public class Tables8kGCMMultiplier implements GCMMultiplier {

    private byte[] f3322H;

    private long[][][] f3323T;

    @Override
    public void init(byte[] bArr) {
        if (this.f3323T == null) {
            this.f3323T = (long[][][]) Array.newInstance((Class<?>) Long.TYPE, 32, 16, 2);
        } else if (GCMUtil.areEqual(this.f3322H, bArr) != 0) {
            return;
        }
        byte[] bArr2 = new byte[16];
        this.f3322H = bArr2;
        GCMUtil.copy(bArr, bArr2);
        for (int i = 0; i < 32; i++) {
            long[][][] jArr = this.f3323T;
            long[][] jArr2 = jArr[i];
            if (i == 0) {
                GCMUtil.asLongs(this.f3322H, jArr2[1]);
                long[] jArr3 = jArr2[1];
                GCMUtil.multiplyP3(jArr3, jArr3);
            } else {
                GCMUtil.multiplyP4(jArr[i - 1][1], jArr2[1]);
            }
            for (int i2 = 2; i2 < 16; i2 += 2) {
                GCMUtil.divideP(jArr2[i2 >> 1], jArr2[i2]);
                GCMUtil.xor(jArr2[i2], jArr2[1], jArr2[i2 + 1]);
            }
        }
    }

    @Override
    public void multiplyH(byte[] bArr) {
        long j = 0;
        long j2 = 0;
        for (int i = 15; i >= 0; i--) {
            long[][][] jArr = this.f3323T;
            int i2 = i + i;
            long[][] jArr2 = jArr[i2 + 1];
            byte b = bArr[i];
            long[] jArr3 = jArr2[b & SnmpConstants.SNMP_ERR_UNDOFAILED];
            long[] jArr4 = jArr[i2][(b & 240) >>> 4];
            j ^= jArr3[0] ^ jArr4[0];
            j2 ^= jArr4[1] ^ jArr3[1];
        }
        Pack.longToBigEndian(j, bArr, 0);
        Pack.longToBigEndian(j2, bArr, 8);
    }
}
