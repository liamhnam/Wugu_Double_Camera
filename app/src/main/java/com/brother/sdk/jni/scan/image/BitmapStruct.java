package com.brother.sdk.jni.scan.image;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BitmapStruct {
    public Bitmap body;
    public byte[] file;
    public byte[] info;

    public BitmapStruct(Bitmap bitmap) {
        this.body = null;
        if (bitmap == null) {
            throw new IllegalArgumentException();
        }
        this.body = bitmap;
        updateHeader();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException();
        }
        if (this.body == null) {
            throw new IllegalArgumentException();
        }
        updateHeader();
        byte[] bArr = this.file;
        outputStream.write(bArr, 0, bArr.length);
        byte[] bArr2 = this.info;
        outputStream.write(bArr2, 0, bArr2.length);
        int width = (this.body.getWidth() * 3) % 4;
        byte[] bArr3 = new byte[width];
        for (int height = this.body.getHeight(); height > 0; height--) {
            byte[] lineBytes = getLineBytes(height - 1);
            outputStream.write(lineBytes, 0, lineBytes.length);
            if (width > 0) {
                outputStream.write(bArr3, 0, width);
            }
        }
    }

    public void updateHeader() {
        Bitmap bitmap = this.body;
        if (bitmap == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = this.body.getHeight();
        int i = (width * height * 3) + 54 + (((width * 3) % 4) * height);
        this.file = ByteBuffer.allocate(14).order(ByteOrder.LITTLE_ENDIAN).put(SnmpConstants.GAUGE).put((byte) 77).putInt(i).putInt(0).putInt(54).array();
        this.info = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN).putInt(40).putInt(width).putInt(height).putShort((short) 1).putShort((short) 24).putInt(0).putInt(i).putInt(0).putInt(0).putInt(0).putInt(0).array();
    }

    private byte[] getLineBytes(int i) {
        int width = this.body.getWidth();
        int[] iArr = new int[width];
        byte[] bArr = new byte[width * 3];
        this.body.getPixels(iArr, 0, width, 0, i, width, 1);
        for (int i2 = 0; i2 < width; i2++) {
            int i3 = i2 * 3;
            bArr[i3] = (byte) Color.blue(iArr[i2]);
            bArr[i3 + 1] = (byte) Color.green(iArr[i2]);
            bArr[i3 + 2] = (byte) Color.red(iArr[i2]);
        }
        return bArr;
    }
}
