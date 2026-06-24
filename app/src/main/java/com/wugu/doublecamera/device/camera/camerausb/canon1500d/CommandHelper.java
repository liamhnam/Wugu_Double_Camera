package com.wugu.doublecamera.device.camera.camerausb.canon1500d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CommandHelper {
    private int transactionId;

    private static class Holder {
        public static CommandHelper instance = new CommandHelper();

        private Holder() {
        }
    }

    private CommandHelper() {
        this.transactionId = 0;
    }

    public static CommandHelper getInstance() {
        return Holder.instance;
    }

    private ByteBuffer getBufferCommand(int i, int i2, int... iArr) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(24);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(24);
        byteBufferAllocate.putShort((short) 1);
        byteBufferAllocate.putShort((short) i2);
        byteBufferAllocate.putInt(i);
        for (int i3 = 0; i3 < iArr.length && i3 <= 2; i3++) {
            byteBufferAllocate.putInt(iArr[i3]);
        }
        return byteBufferAllocate;
    }

    private int getNextTransactionId(boolean z) {
        this.transactionId++;
        if (z) {
            this.transactionId = 0;
        }
        if (this.transactionId == -2) {
            this.transactionId = 1;
        }
        return this.transactionId;
    }

    private int getTransactionId() {
        return this.transactionId;
    }

    private ByteBuffer getBufferData(int i, int i2, int... iArr) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(32);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(32);
        byteBufferAllocate.putShort((short) 2);
        byteBufferAllocate.putShort((short) i2);
        byteBufferAllocate.putInt(i);
        for (int i3 = 0; i3 < iArr.length && i3 <= 4; i3++) {
            byteBufferAllocate.putInt(iArr[i3]);
        }
        return byteBufferAllocate;
    }

    public CameraCommandBean getDeviceInfoCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(4097);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(true), 4097, new int[0]));
        return cameraCommandBean;
    }

    public CameraCommandBean getOpenSessionCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(4098);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(true), 4098, 1));
        return cameraCommandBean;
    }

    public CameraCommandBean getCloseSessionCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(4099);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), 4099, new int[0]));
        return cameraCommandBean;
    }

    public CameraCommandBean getRemoteShootingModeCommand(int i) {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_SET_REMOTE_SHOOTING_MODE);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_EOS_SET_REMOTE_SHOOTING_MODE, i));
        return cameraCommandBean;
    }

    public CameraCommandBean getEventNotifyModeCommand(int i) {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_EVENT_NOTIFY_MODE);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_EOS_EVENT_NOTIFY_MODE, i));
        return cameraCommandBean;
    }

    public CameraCommandBean getEventDataCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_GET_EVENT_DATA);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_EOS_GET_EVENT_DATA, new int[0]));
        return cameraCommandBean;
    }

    public CameraCommandBean getDeviceSetPropCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_SET_DEVICE_PROP_VALUE);
        cameraCommandBean.setHasNextCommand(true);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_EOS_SET_DEVICE_PROP_VALUE, new int[0]));
        return cameraCommandBean;
    }

    public CameraCommandBean getDeviceSetPropData(int i, int i2) {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_SET_DEVICE_PROP_VALUE);
        cameraCommandBean.setBuffer(getBufferData(getTransactionId(), Constants.OPERATION_EOS_SET_DEVICE_PROP_VALUE, 12, i, i2));
        return cameraCommandBean;
    }

    public CameraCommandBean getLiveViewCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_GET_LIVE_VIEW_PICTURE);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_EOS_GET_LIVE_VIEW_PICTURE, 4194304));
        return cameraCommandBean;
    }

    public CameraCommandBean getTakePictureCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_TAKE_PICTURE);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_EOS_TAKE_PICTURE, new int[0]));
        return cameraCommandBean;
    }

    public CameraCommandBean getGetObjectCommand(int i) {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_GET_OBJECT);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_GET_OBJECT, i));
        return cameraCommandBean;
    }

    public CameraCommandBean getPressShutterCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_PRESS_SHUTTER_BTN);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_EOS_PRESS_SHUTTER_BTN, 1));
        return cameraCommandBean;
    }

    public CameraCommandBean getReleaseShutterCommand() {
        CameraCommandBean cameraCommandBean = new CameraCommandBean();
        cameraCommandBean.setCommandCode(Constants.OPERATION_EOS_RELEASE_SHUTTER_BTN);
        cameraCommandBean.setBuffer(getBufferCommand(getNextTransactionId(false), Constants.OPERATION_EOS_RELEASE_SHUTTER_BTN, 1));
        return cameraCommandBean;
    }
}
