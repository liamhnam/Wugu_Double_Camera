package org.eclipse.paho.client.mqttv3.internal.websocket;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.p040ws.WebSocketProtocol;

public class WebSocketFrame {
    public static final int frameLengthOverhead = 6;
    private boolean closeFlag;
    private boolean fin;
    private byte opcode;
    private byte[] payload;

    public byte getOpcode() {
        return this.opcode;
    }

    public boolean isFin() {
        return this.fin;
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public boolean isCloseFlag() {
        return this.closeFlag;
    }

    public WebSocketFrame(byte b, boolean z, byte[] bArr) {
        this.closeFlag = false;
        this.opcode = b;
        this.fin = z;
        this.payload = bArr;
    }

    public WebSocketFrame(byte[] bArr) {
        byte[] bArr2;
        int i = 0;
        this.closeFlag = false;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        setFinAndOpCode(byteBufferWrap.get());
        byte b = byteBufferWrap.get();
        boolean z = (b & 128) != 0;
        int i2 = (byte) (b & ByteCompanionObject.MAX_VALUE);
        int i3 = i2 == 127 ? 8 : i2 == 126 ? 2 : 0;
        while (true) {
            i3--;
            if (i3 <= 0) {
                break;
            } else {
                i2 |= (byteBufferWrap.get() & UByte.MAX_VALUE) << (i3 * 8);
            }
        }
        if (z) {
            bArr2 = new byte[4];
            byteBufferWrap.get(bArr2, 0, 4);
        } else {
            bArr2 = null;
        }
        byte[] bArr3 = new byte[i2];
        this.payload = bArr3;
        byteBufferWrap.get(bArr3, 0, i2);
        if (!z) {
            return;
        }
        while (true) {
            byte[] bArr4 = this.payload;
            if (i >= bArr4.length) {
                return;
            }
            bArr4[i] = (byte) (bArr4[i] ^ bArr2[i % 4]);
            i++;
        }
    }

    private void setFinAndOpCode(byte b) {
        this.fin = (b & 128) != 0;
        this.opcode = (byte) (b & SnmpConstants.SNMP_ERR_UNDOFAILED);
    }

    public WebSocketFrame(InputStream inputStream) throws IOException {
        byte[] bArr;
        int i = 0;
        this.closeFlag = false;
        setFinAndOpCode((byte) inputStream.read());
        byte b = this.opcode;
        if (b != 2) {
            if (b == 8) {
                this.closeFlag = true;
                return;
            }
            throw new IOException(new StringBuffer("Invalid Frame: Opcode: ").append((int) this.opcode).toString());
        }
        byte b2 = (byte) inputStream.read();
        boolean z = (b2 & 128) != 0;
        int i2 = (byte) (b2 & ByteCompanionObject.MAX_VALUE);
        int i3 = i2 != 127 ? i2 == 126 ? 2 : 0 : 8;
        i2 = i3 > 0 ? 0 : i2;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            } else {
                i2 |= (((byte) inputStream.read()) & UByte.MAX_VALUE) << (i3 * 8);
            }
        }
        if (z) {
            bArr = new byte[4];
            inputStream.read(bArr, 0, 4);
        } else {
            bArr = null;
        }
        byte[] bArr2 = bArr;
        this.payload = new byte[i2];
        int i4 = 0;
        int i5 = i2;
        while (i4 != i2) {
            int i6 = inputStream.read(this.payload, i4, i5);
            i4 += i6;
            i5 -= i6;
        }
        if (!z) {
            return;
        }
        while (true) {
            byte[] bArr3 = this.payload;
            if (i >= bArr3.length) {
                return;
            }
            bArr3[i] = (byte) (bArr3[i] ^ bArr2[i % 4]);
            i++;
        }
    }

    public byte[] encodeFrame() {
        byte[] bArr = this.payload;
        int length = bArr.length + 6;
        if (bArr.length > 65535) {
            length += 8;
        } else if (bArr.length >= 126) {
            length += 2;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        appendFinAndOpCode(byteBufferAllocate, this.opcode, this.fin);
        byte[] bArrGenerateMaskingKey = generateMaskingKey();
        appendLengthAndMask(byteBufferAllocate, this.payload.length, bArrGenerateMaskingKey);
        int i = 0;
        while (true) {
            byte[] bArr2 = this.payload;
            if (i < bArr2.length) {
                byte b = (byte) (bArr2[i] ^ bArrGenerateMaskingKey[i % 4]);
                bArr2[i] = b;
                byteBufferAllocate.put(b);
                i++;
            } else {
                byteBufferAllocate.flip();
                return byteBufferAllocate.array();
            }
        }
    }

    public static void appendLengthAndMask(ByteBuffer byteBuffer, int i, byte[] bArr) {
        if (bArr != null) {
            appendLength(byteBuffer, i, true);
            byteBuffer.put(bArr);
        } else {
            appendLength(byteBuffer, i, false);
        }
    }

    private static void appendLength(ByteBuffer byteBuffer, int i, boolean z) {
        if (i < 0) {
            throw new IllegalArgumentException("Length cannot be negative");
        }
        int i2 = z ? -128 : 0;
        if (i <= 65535) {
            if (i >= 126) {
                byteBuffer.put((byte) (i2 | WebSocketProtocol.PAYLOAD_SHORT));
                byteBuffer.put((byte) (i >> 8));
                byteBuffer.put((byte) (i & 255));
                return;
            }
            byteBuffer.put((byte) (i | i2));
            return;
        }
        byteBuffer.put((byte) (i2 | 127));
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) ((i >> 24) & 255));
        byteBuffer.put((byte) ((i >> 16) & 255));
        byteBuffer.put((byte) ((i >> 8) & 255));
        byteBuffer.put((byte) (i & 255));
    }

    public static void appendFinAndOpCode(ByteBuffer byteBuffer, byte b, boolean z) {
        byteBuffer.put((byte) ((b & SnmpConstants.SNMP_ERR_UNDOFAILED) | (z ? (byte) 128 : (byte) 0)));
    }

    public static byte[] generateMaskingKey() {
        SecureRandom secureRandom = new SecureRandom();
        return new byte[]{(byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255)};
    }
}
