package com.brother.sdk.network.wifidirect;

public class WifiDirectInfo {
    String mIpAddress;
    String mModelName;
    String mWifiDirectDeviceAddress;
    String mWifiDirectDeviceName;

    public void setWifiDirectDeviceAddress(String str) {
        this.mWifiDirectDeviceAddress = str;
    }

    public String getWifiDirectDeviceAddress() {
        return this.mWifiDirectDeviceAddress;
    }

    public void setWifiDirectDeviceName(String str) {
        this.mWifiDirectDeviceName = str;
    }

    public String getWifiDirectDeviceName() {
        return this.mWifiDirectDeviceName;
    }

    public void setModelName(String str) {
        this.mModelName = str;
    }

    public String getModelName() {
        return this.mModelName;
    }

    public void setIPAddress(String str) {
        this.mIpAddress = str;
    }

    public String getIPAddress() {
        return this.mIpAddress;
    }
}
