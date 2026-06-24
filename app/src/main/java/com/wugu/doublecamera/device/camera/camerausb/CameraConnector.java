package com.wugu.doublecamera.device.camera.camerausb;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import com.wugu.doublecamera.device.camera.camerausb.canon1500d.CameraCommandBean;
import com.wugu.doublecamera.device.camera.camerausb.canon1500d.CameraDataThread;
import com.wugu.doublecamera.device.camera.camerausb.canon1500d.CameraUsbInfo;
import com.wugu.doublecamera.listener.ICameraListener;
import com.wugu.doublecamera.listener.IDeviceListener;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class CameraConnector {
    private final String ACTION_USB_PERMISSION;
    private Activity activity;
    private UsbEndpoint bulkIn;
    private UsbEndpoint bulkOut;
    private ICameraListener cameraListener;
    private CameraUsbInfo cameraUsbInfo;
    private final LinkedBlockingQueue<CameraCommandBean> commandQueue;
    private CameraDataThread dataThread;
    private IDeviceListener deviceListener;
    private final BroadcastReceiver permissionRec;
    private UsbDevice usbDevice;
    private UsbInterface usbInterface;
    private UsbManager usbManager;

    private static class Holder {
        public static CameraConnector instance = new CameraConnector();

        private Holder() {
        }
    }

    private CameraConnector() {
        this.ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
        this.commandQueue = new LinkedBlockingQueue<>();
        this.permissionRec = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ("com.android.example.USB_PERMISSION".equals(intent.getAction())) {
                    context.unregisterReceiver(this);
                    synchronized (this) {
                        UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                        if (!intent.getBooleanExtra("permission", false) || usbDevice == null) {
                            if (CameraConnector.this.cameraListener != null) {
                                CameraConnector.this.cameraListener.onError("NOT PERMISSION");
                            }
                        } else {
                            CameraConnector.this.searchDevice(usbDevice);
                        }
                    }
                }
            }
        };
    }

    public static CameraConnector getInstance() {
        return Holder.instance;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        this.usbManager = (UsbManager) activity.getSystemService("usb");
        trySearchDevice();
    }

    public void setCameraListener(ICameraListener iCameraListener) {
        this.cameraListener = iCameraListener;
        CameraDataThread cameraDataThread = this.dataThread;
        if (cameraDataThread != null) {
            cameraDataThread.setListener(iCameraListener);
        }
    }

    public void setDeviceListener(IDeviceListener iDeviceListener) {
        this.deviceListener = iDeviceListener;
    }

    public void research() {
        connectionClose();
        trySearchDevice();
    }

    public void openDevice() {
        UsbDevice usbDevice = this.usbDevice;
        if (usbDevice == null) {
            ICameraListener iCameraListener = this.cameraListener;
            if (iCameraListener != null) {
                iCameraListener.connectedDevice(false);
                return;
            }
            return;
        }
        if (this.cameraUsbInfo != null) {
            return;
        }
        UsbDeviceConnection usbDeviceConnectionOpenDevice = this.usbManager.openDevice(usbDevice);
        if (usbDeviceConnectionOpenDevice != null) {
            if (usbDeviceConnectionOpenDevice.claimInterface(this.usbInterface, true)) {
                this.cameraUsbInfo = new CameraUsbInfo(this.usbInterface, usbDeviceConnectionOpenDevice, this.bulkOut, this.bulkIn);
                CameraDataThread cameraDataThread = this.dataThread;
                if (cameraDataThread != null) {
                    cameraDataThread.interrupt();
                    this.dataThread = null;
                }
                ThreadClock.sleep(500L);
                CameraDataThread cameraDataThread2 = new CameraDataThread(this.cameraUsbInfo, this.commandQueue);
                this.dataThread = cameraDataThread2;
                cameraDataThread2.start();
                ICameraListener iCameraListener2 = this.cameraListener;
                if (iCameraListener2 != null) {
                    iCameraListener2.connectedDevice(true);
                }
                this.dataThread.setListener(this.cameraListener);
                return;
            }
            usbDeviceConnectionOpenDevice.close();
        }
        ICameraListener iCameraListener3 = this.cameraListener;
        if (iCameraListener3 != null) {
            iCameraListener3.connectedDevice(false);
        }
    }

    public void connectionClose() {
        this.commandQueue.clear();
        CameraDataThread cameraDataThread = this.dataThread;
        if (cameraDataThread != null) {
            cameraDataThread.interrupt();
            this.dataThread.setListener(null);
            this.dataThread = null;
        }
        CameraUsbInfo cameraUsbInfo = this.cameraUsbInfo;
        if (cameraUsbInfo != null) {
            cameraUsbInfo.getUsbDeviceConnection().releaseInterface(this.cameraUsbInfo.getUsbInterface());
            this.cameraUsbInfo.getUsbDeviceConnection().close();
            this.cameraUsbInfo = null;
        }
    }

    private void trySearchDevice() {
        Activity activity = this.activity;
        if (activity != null) {
            final UsbDevice usbDevice = (UsbDevice) activity.getIntent().getParcelableExtra("device");
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m874x2931d46b(usbDevice);
                }
            });
        }
    }

    void m874x2931d46b(UsbDevice usbDevice) {
        if (usbDevice != null) {
            searchDevice(usbDevice);
        } else {
            findAndConnectCompatibleDevice(this.activity.getApplicationContext());
        }
    }

    private void findAndConnectCompatibleDevice(Context context) {
        UsbDevice usbDeviceLookupCompatibleDevice = lookupCompatibleDevice();
        if (usbDeviceLookupCompatibleDevice != null) {
            requestPermission(context, usbDeviceLookupCompatibleDevice);
            return;
        }
        IDeviceListener iDeviceListener = this.deviceListener;
        if (iDeviceListener != null) {
            iDeviceListener.onNoCameraFound();
        }
    }

    private UsbDevice lookupCompatibleDevice() {
        Iterator<Map.Entry<String, UsbDevice>> it = this.usbManager.getDeviceList().entrySet().iterator();
        while (it.hasNext()) {
            UsbDevice value = it.next().getValue();
            if (value.getVendorId() == 1193) {
                return value;
            }
        }
        return null;
    }

    private void requestPermission(Context context, UsbDevice usbDevice) {
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent("com.android.example.USB_PERMISSION"), 1073741824);
        context.registerReceiver(this.permissionRec, new IntentFilter("com.android.example.USB_PERMISSION"));
        this.usbManager.requestPermission(usbDevice, broadcast);
    }

    public void searchDevice(UsbDevice usbDevice) {
        int interfaceCount = usbDevice.getInterfaceCount();
        int i = 0;
        while (true) {
            UsbEndpoint usbEndpoint = null;
            if (i < interfaceCount) {
                UsbInterface usbInterface = usbDevice.getInterface(i);
                if (usbInterface.getEndpointCount() == 3) {
                    int endpointCount = usbInterface.getEndpointCount();
                    UsbEndpoint usbEndpoint2 = null;
                    for (int i2 = 0; i2 < endpointCount; i2++) {
                        UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                        if (endpoint.getType() == 2) {
                            if (endpoint.getDirection() == 128) {
                                usbEndpoint = endpoint;
                            } else if (endpoint.getDirection() == 0) {
                                usbEndpoint2 = endpoint;
                            }
                        }
                    }
                    if (usbEndpoint != null && usbEndpoint2 != null && usbDevice.getVendorId() == 1193) {
                        this.usbDevice = usbDevice;
                        this.usbInterface = usbInterface;
                        this.bulkIn = usbEndpoint;
                        this.bulkOut = usbEndpoint2;
                        IDeviceListener iDeviceListener = this.deviceListener;
                        if (iDeviceListener != null) {
                            iDeviceListener.foundCamera();
                            return;
                        }
                        return;
                    }
                }
                i++;
            } else {
                this.usbDevice = null;
                this.usbInterface = null;
                this.bulkIn = null;
                this.bulkOut = null;
                IDeviceListener iDeviceListener2 = this.deviceListener;
                if (iDeviceListener2 != null) {
                    iDeviceListener2.onNoCameraFound();
                    return;
                }
                return;
            }
        }
    }

    public void sendData(CameraCommandBean cameraCommandBean) {
        this.commandQueue.add(cameraCommandBean);
    }

    public void clearCommandQueue() {
        this.commandQueue.clear();
    }

    public void setCameraStep(int i) {
        CameraDataThread cameraDataThread = this.dataThread;
        if (cameraDataThread == null) {
            return;
        }
        cameraDataThread.setCameraStep(i);
    }

    public int getCameraStep() {
        CameraDataThread cameraDataThread = this.dataThread;
        if (cameraDataThread == null) {
            return 1;
        }
        return cameraDataThread.getCameraStep();
    }
}
