package com.wugu.doublecamera.device.camera.camerausb.canon1500d;

import java.nio.ByteBuffer;

public class CameraCommandBean {
    private ByteBuffer buffer;
    private int commandCode;
    private boolean hasNextCommand = false;

    public int getCommandCode() {
        return this.commandCode;
    }

    public void setCommandCode(int i) {
        this.commandCode = i;
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public void setBuffer(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    public boolean isHasNextCommand() {
        return this.hasNextCommand;
    }

    public void setHasNextCommand(boolean z) {
        this.hasNextCommand = z;
    }
}
