package com.wugu.doublecamera.device.camera.camerausb.canon1500d;

import java.nio.ByteBuffer;

public class DecodeDataUtil {
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void decodeEventData(java.nio.ByteBuffer r5, int r6, com.wugu.doublecamera.listener.ICameraListener r7) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.device.camera.camerausb.canon1500d.DecodeDataUtil.decodeEventData(java.nio.ByteBuffer, int, com.wugu.doublecamera.listener.ICameraListener):void");
    }

    private static void skip(ByteBuffer byteBuffer, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            byteBuffer.get();
        }
    }
}
