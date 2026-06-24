package com.faceunity.core.media.rgba;

import android.opengl.GLES20;
import com.faceunity.core.callback.OnColorReadCallback;
import java.nio.ByteBuffer;
import kotlin.UByte;

public class RGBAPicker {
    public static void readRgba(int i, int i2, OnColorReadCallback onColorReadCallback) {
        byte[] bArr = new byte[4];
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(4);
        GLES20.glReadPixels(i, i2, 1, 1, 6408, 5121, byteBufferAllocateDirect);
        byteBufferAllocateDirect.rewind();
        byteBufferAllocateDirect.get(bArr);
        onColorReadCallback.onReadRgba(bArr[0] & UByte.MAX_VALUE, bArr[1] & UByte.MAX_VALUE, bArr[2] & UByte.MAX_VALUE, bArr[3] & UByte.MAX_VALUE);
    }
}
