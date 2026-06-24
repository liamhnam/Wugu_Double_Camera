package com.brother.sdk.usb;

import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.Job;
import com.brother.sdk.common.device.Device;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.ISocketConnector;
import com.brother.sdk.common.socket.SocketClient;
import com.brother.sdk.common.util.Tool;
import java.io.IOException;
import java.util.Iterator;

public class UsbConnector implements ISocketConnector {
    private static final String USB_CONNECTION_ID = "USB Connected";
    private final BrUsbDevice mBrUsbDevice;
    private final Device mDevice;

    public UsbConnector(BrUsbDevice brUsbDevice, Device device) {
        this.mDevice = device;
        this.mBrUsbDevice = brUsbDevice;
    }

    static class C07601 {

        static final int[] f528xb20fb28f;

        static {
            int[] iArr = new int[SocketClient.ProtocolType.values().length];
            f528xb20fb28f = iArr;
            try {
                iArr[SocketClient.ProtocolType.Port9100.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f528xb20fb28f[SocketClient.ProtocolType.ScanCommand.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override
    public ISocket createConnectionSocket(SocketClient socketClient) {
        try {
            int i = C07601.f528xb20fb28f[socketClient.getType().ordinal()];
            if (i == 1) {
                BrUsbSocket brUsbSocket = new BrUsbSocket(this.mBrUsbDevice, BrotherUsbInterface.Printer);
                brUsbSocket.setSoTimeout(10000);
                return brUsbSocket;
            }
            if (i != 2) {
                return null;
            }
            BrUsbSocket brUsbSocket2 = new BrUsbSocket(this.mBrUsbDevice, BrotherUsbInterface.Scanner);
            brUsbSocket2.setSoTimeout(10000);
            return brUsbSocket2;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Device getDevice() {
        return this.mDevice;
    }

    @Override
    public boolean validate() {
        Iterator it = Tool.emptyIfNull(UsbControllerManager.getUsbController().findDevices()).iterator();
        while (it.hasNext()) {
            if (((BrUsbDevice) it.next()).equals(this.mBrUsbDevice)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Job.JobState submit(Job job) {
        if (job.bind(this)) {
            return job.commit();
        }
        return Job.JobState.ErrorJobConnectionFailure;
    }

    @Override
    public Object getConnectorIdentifier() {
        return this.mBrUsbDevice;
    }

    @Override
    public IUnknown queryInterface(String str) {
        if (IUnknown.f479ID.equals(str) || IConnector.f477ID.equals(str) || ISocketConnector.f506ID.equals(str)) {
            return this;
        }
        return null;
    }
}
