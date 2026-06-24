package com.brother.sdk.usb;

public enum BrotherUsbInterface {
    Printer(0),
    Scanner(1),
    Modem(2),
    RemoteSetup(3);

    private final int mValue;

    BrotherUsbInterface(int i) {
        this.mValue = i;
    }

    public int getInterfaceIndex() {
        return this.mValue;
    }
}
