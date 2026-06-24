package com.wugu.doublecamera.device.camera.camerausb.canon1500d;

import androidx.exifinterface.media.ExifInterface;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.device.camera.camerausb.CameraConnector;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import com.wugu.doublecamera.listener.ICameraListener;
import com.wugu.doublecamera.network.MqttHelper;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ToastHelper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.UShort;
import org.bouncycastle.asn1.cmc.BodyPartID;

public class CameraDataThread extends Thread {
    private final LinkedBlockingQueue<CameraCommandBean> bufferQueue;
    private int cameraStep;
    private final CameraUsbInfo cameraUsbInfo;
    private Timer captureTimer;
    private Timer eventDataTimer;
    private ICameraListener listener;
    private final boolean IS_DEBUG = false;
    private CameraCommandBean commandBean = null;
    private boolean isNextRec = false;

    public CameraDataThread(CameraUsbInfo cameraUsbInfo, LinkedBlockingQueue<CameraCommandBean> linkedBlockingQueue) {
        this.cameraUsbInfo = cameraUsbInfo;
        this.bufferQueue = linkedBlockingQueue;
    }

    public void setListener(ICameraListener iCameraListener) {
        this.listener = iCameraListener;
    }

    public void setCameraStep(int i) {
        this.cameraStep = i;
    }

    public int getCameraStep() {
        return this.cameraStep;
    }

    @Override
    public void run() {
        ByteBuffer buffer;
        int maxPacketInSize = this.cameraUsbInfo.getMaxPacketInSize();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(maxPacketInSize);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(maxPacketInSize);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
        int i = 0;
        while (!Thread.interrupted()) {
            if (!this.isNextRec) {
                try {
                    CameraCommandBean cameraCommandBeanTake = this.bufferQueue.take();
                    this.commandBean = cameraCommandBeanTake;
                    if (cameraCommandBeanTake != null && (buffer = cameraCommandBeanTake.getBuffer()) != null) {
                        buffer.rewind();
                        if (this.cameraUsbInfo.bulkTransferOut(buffer.array(), buffer.capacity(), 5000) < 0) {
                            if (this.cameraStep == 12 && (i = i + 1) > 3) {
                                long errorRebootTime = SpManager.getInstance().getErrorRebootTime();
                                long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                                if (jCurrentTimeMillis - errorRebootTime > 36000) {
                                    SpManager.getInstance().setErrorRebootTime(jCurrentTimeMillis);
                                    LoggerUtil.m1182e("CameraData", "发送失败次数过多，重启设备 ");
                                    AppUtil.runOnUI(new Runnable() {
                                        @Override
                                        public final void run() {
                                            ToastHelper.getInstance().showToast("相机通信失败，准备重启设备");
                                        }
                                    });
                                    ThreadClock.sleep(2000L);
                                    AppUtil.reboot();
                                } else {
                                    MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_DEVICE_DISCONNECT, ExifInterface.GPS_MEASUREMENT_2D);
                                    CameraConnector.getInstance().connectionClose();
                                }
                            }
                            ICameraListener iCameraListener = this.listener;
                            if (iCameraListener != null) {
                                iCameraListener.onError("发送失败, commandCode = " + String.format("%x", Integer.valueOf(this.commandBean.getCommandCode())));
                            }
                            ThreadClock.sleep(500L);
                            this.bufferQueue.add(this.commandBean);
                        }
                    }
                } catch (InterruptedException unused) {
                    ICameraListener iCameraListener2 = this.listener;
                    if (iCameraListener2 != null) {
                        iCameraListener2.onError("buffer queue take error ");
                        return;
                    }
                    return;
                }
            }
            if (!this.commandBean.isHasNextCommand()) {
                byteBufferAllocate.rewind();
                if (this.cameraUsbInfo.bulkTransferIn(byteBufferAllocate.array(), maxPacketInSize, 5000) > 0) {
                    int i2 = byteBufferAllocate.getInt();
                    if ((((long) i2) & BodyPartID.bodyIdMax) <= 67108864) {
                        if (i2 > maxPacketInSize) {
                            ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate((int) (((double) i2) * 1.5d));
                            byteBufferAllocate3.order(ByteOrder.LITTLE_ENDIAN);
                            byteBufferAllocate.rewind();
                            byteBufferAllocate3.put(byteBufferAllocate);
                            int i3 = i2 - maxPacketInSize;
                            while (i3 > 0) {
                                int iBulkTransferIn = this.cameraUsbInfo.bulkTransferIn(byteBufferAllocate2.array(), Math.min(i3, maxPacketInSize), 5000);
                                if (iBulkTransferIn <= 0) {
                                    break;
                                }
                                byteBufferAllocate2.rewind();
                                byteBufferAllocate3.put(byteBufferAllocate2.array(), 0, iBulkTransferIn);
                                i3 -= iBulkTransferIn;
                            }
                            if (i3 > 0) {
                                ICameraListener iCameraListener3 = this.listener;
                                if (iCameraListener3 != null) {
                                    iCameraListener3.onError("data rec error2");
                                }
                            } else {
                                recUsbBuffer(this.commandBean, byteBufferAllocate3);
                                byteBufferAllocate3.clear();
                            }
                        } else {
                            recUsbBuffer(this.commandBean, byteBufferAllocate);
                        }
                    }
                }
            }
        }
    }

    private void recUsbBuffer(CameraCommandBean cameraCommandBean, ByteBuffer byteBuffer) {
        if (cameraCommandBean == null) {
            ThreadClock.sleep(200L);
        }
        byteBuffer.rewind();
        int i = byteBuffer.getInt();
        int i2 = byteBuffer.getShort() & UShort.MAX_VALUE;
        int i3 = 65535 & byteBuffer.getShort();
        byteBuffer.getInt();
        this.isNextRec = i2 == 2;
        if (i3 == 8217) {
            ThreadClock.sleep(500L);
            if (!cameraCommandBean.isHasNextCommand()) {
                this.bufferQueue.add(cameraCommandBean);
                return;
            }
        }
        switch (cameraCommandBean.getCommandCode()) {
            case 4097:
                recGetDeviceInfo(i2, i3, byteBuffer);
                break;
            case 4098:
                recOpenSession(i2, i3);
                break;
            case 4099:
                recCloseSession(i2, i3);
                break;
            case Constants.OPERATION_GET_OBJECT:
                recGetObject(i2, i, byteBuffer);
                break;
            case Constants.OPERATION_EOS_TAKE_PICTURE:
                recTakePicture(i2, i3);
                break;
            case Constants.OPERATION_EOS_SET_DEVICE_PROP_VALUE:
                recSetPropValue(i2, i3);
                break;
            case Constants.OPERATION_EOS_SET_REMOTE_SHOOTING_MODE:
                recSetRemoteShooting(i2, i3);
                break;
            case Constants.OPERATION_EOS_EVENT_NOTIFY_MODE:
                recEventNotifyMode(i2, i3);
                break;
            case Constants.OPERATION_EOS_GET_EVENT_DATA:
                recGetEventData(i2, i3, i, byteBuffer);
                break;
            case Constants.OPERATION_EOS_GET_LIVE_VIEW_PICTURE:
                recGetLiveView(i2, i3, i, byteBuffer);
                break;
        }
    }

    private void startEventDataTask() {
        if (this.eventDataTimer != null) {
            return;
        }
        Timer timer = new Timer();
        this.eventDataTimer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CameraDataThread.this.bufferQueue.add(CommandHelper.getInstance().getEventDataCommand());
            }
        }, 0L, 700L);
    }

    private void startCaptureTimeOutTask() {
        Timer timer = this.captureTimer;
        if (timer != null) {
            timer.cancel();
            this.captureTimer = null;
        }
        Timer timer2 = new Timer();
        this.captureTimer = timer2;
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                if (CameraDataThread.this.eventDataTimer != null) {
                    CameraDataThread.this.eventDataTimer.cancel();
                    CameraDataThread.this.eventDataTimer = null;
                }
            }
        }, 5000L);
    }

    private void sendCameraEvent(int i) {
        ICameraListener iCameraListener = this.listener;
        if (iCameraListener != null) {
            iCameraListener.cameraEvent(i);
        }
    }

    private void recGetDeviceInfo(int i, int i2, ByteBuffer byteBuffer) {
        if (i == 3 && i2 == 8193 && this.cameraStep == 1) {
            sendCameraEvent(2);
        }
    }

    private void recOpenSession(int i, int i2) {
        if (i == 3 && i2 == 8193) {
            if (this.cameraStep == 2) {
                sendCameraEvent(3);
            }
        } else {
            ICameraListener iCameraListener = this.listener;
            if (iCameraListener != null) {
                iCameraListener.onError("open session fail");
            }
        }
    }

    private void recSetRemoteShooting(int i, int i2) {
        if (i == 3 && i2 == 8193) {
            int i3 = this.cameraStep;
            if (i3 == 3) {
                sendCameraEvent(4);
                return;
            } else {
                if (i3 == 11) {
                    this.bufferQueue.add(CommandHelper.getInstance().getCloseSessionCommand());
                    return;
                }
                return;
            }
        }
        ICameraListener iCameraListener = this.listener;
        if (iCameraListener != null) {
            iCameraListener.onError("set remote shooting mode fail");
        }
    }

    private void recEventNotifyMode(int i, int i2) {
        if (i == 3 && i2 == 8193) {
            int i3 = this.cameraStep;
            if (i3 == 4) {
                startEventDataTask();
                return;
            } else {
                if (i3 == 11) {
                    this.bufferQueue.add(CommandHelper.getInstance().getRemoteShootingModeCommand(0));
                    return;
                }
                return;
            }
        }
        ICameraListener iCameraListener = this.listener;
        if (iCameraListener != null) {
            iCameraListener.onError("set event mode fail");
        }
    }

    private void recGetEventData(int i, int i2, int i3, ByteBuffer byteBuffer) {
        if (i == 2 && i3 > 20) {
            DecodeDataUtil.decodeEventData(byteBuffer, i3, this.listener);
            return;
        }
        if (i == 3 && i2 == 8193) {
            int i4 = this.cameraStep;
            if (i4 == 4) {
                ICameraListener iCameraListener = this.listener;
                if (iCameraListener != null) {
                    iCameraListener.cameraEvent(5);
                    return;
                }
                return;
            }
            if (i4 == 7) {
                this.cameraStep = 8;
                ThreadClock.sleep(800L);
                sendCameraEvent(8);
                this.eventDataTimer.cancel();
                this.eventDataTimer = null;
            }
        }
    }

    private void recSetPropValue(int i, int i2) {
        if (i == 3 && i2 == 8193) {
            int i3 = this.cameraStep;
            if (i3 != 5) {
                if (i3 == 6) {
                    this.cameraStep = 7;
                    return;
                }
                return;
            } else {
                ICameraListener iCameraListener = this.listener;
                if (iCameraListener != null) {
                    iCameraListener.cameraEvent(6);
                    return;
                }
                return;
            }
        }
        ICameraListener iCameraListener2 = this.listener;
        if (iCameraListener2 != null) {
            iCameraListener2.onError("set prop fail");
        }
    }

    private void recGetLiveView(int i, int i2, int i3, ByteBuffer byteBuffer) {
        if (i != 3 || i2 != 8193) {
            if (i == 2) {
                parseLiveViewData(byteBuffer, i3);
                return;
            }
            return;
        }
        int i4 = this.cameraStep;
        if (i4 == 8) {
            sendCameraEvent(8);
        } else if (i4 == 11) {
            this.bufferQueue.add(CommandHelper.getInstance().getEventNotifyModeCommand(0));
        }
    }

    private void recTakePicture(int i, int i2) {
        LoggerUtil.m1181d("canon", "recTakePicture type= " + i + " result= " + i2);
        if (i == 3 && i2 == 8193) {
            startEventDataTask();
            startCaptureTimeOutTask();
        }
    }

    private void recGetObject(int i, int i2, ByteBuffer byteBuffer) {
        if (i == 2) {
            LoggerUtil.m1181d("canon", "recGetObject");
            ICameraListener iCameraListener = this.listener;
            if (iCameraListener != null) {
                iCameraListener.recBitmap(byteBuffer.array(), 12, i2 - 12, true);
            }
            Timer timer = this.eventDataTimer;
            if (timer != null) {
                timer.cancel();
                this.eventDataTimer = null;
            }
            Timer timer2 = this.captureTimer;
            if (timer2 != null) {
                timer2.cancel();
                this.captureTimer = null;
            }
        }
    }

    private void recCloseSession(int i, int i2) {
        if (i == 3 && i2 == 8193) {
            Timer timer = this.eventDataTimer;
            if (timer != null) {
                timer.cancel();
                this.eventDataTimer = null;
            }
            Timer timer2 = this.captureTimer;
            if (timer2 != null) {
                timer2.cancel();
                this.captureTimer = null;
            }
            CameraConnector.getInstance().connectionClose();
            return;
        }
        ICameraListener iCameraListener = this.listener;
        if (iCameraListener != null) {
            iCameraListener.onError("close session fail");
        }
    }

    private void parseLiveViewData(ByteBuffer byteBuffer, int i) {
        if (i < 1000) {
            return;
        }
        while (byteBuffer.hasRemaining()) {
            int i2 = byteBuffer.getInt();
            int i3 = byteBuffer.getInt();
            if (i2 < 8) {
                return;
            }
            if (i3 == 1) {
                ICameraListener iCameraListener = this.listener;
                if (iCameraListener != null) {
                    iCameraListener.recBitmap(byteBuffer.array(), byteBuffer.position(), i2 - 8, false);
                }
                byteBuffer.position((byteBuffer.position() + i2) - 8);
            } else if (i3 == 3) {
                byteBuffer.position(byteBuffer.position() + 4096);
            } else if (i3 != 4 && i3 != 5 && i3 != 6) {
                if (i3 == 7) {
                    byteBuffer.getInt();
                } else {
                    byteBuffer.position((byteBuffer.position() + i2) - 8);
                }
            }
            if (i - byteBuffer.position() < 8) {
                return;
            }
        }
    }
}
