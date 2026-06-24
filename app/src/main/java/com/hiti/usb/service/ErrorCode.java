package com.hiti.usb.service;

public class ErrorCode {
    public String description;
    public int value;
    public static final ErrorCode ERR_CODE_SUCCESS = new ErrorCode(0, "Success");
    public static final ErrorCode ERR_CODE_SERVICE_IS_BUSY = new ErrorCode(1, "Service is busy");
    public static final ErrorCode ERR_CODE_SERVICE_NOT_START = new ErrorCode(2, "Service is not start");
    public static final ErrorCode ERR_CODE_SERVICE_NOT_BIND = new ErrorCode(3, "Service is not bind");
    public static final ErrorCode ERR_CODE_SERVICE_NOT_STOP = new ErrorCode(4, "Service is not stop");
    public static final ErrorCode ERR_CODE_SERVICE_NOT_SUPPORT = new ErrorCode(5, "Service is not support");
    public static final ErrorCode ERR_CODE_SERVICE_IS_STOPPING = new ErrorCode(6, "Service is stopping");
    public static final ErrorCode ERR_CODE_NO_RETURN_RESULT = new ErrorCode(7, "No result returned");
    public static final ErrorCode ERR_CODE_USB_FD_NEGATIVE = new ErrorCode(16, "Service can't connect device");
    public static final ErrorCode ERR_CODE_USB_NO_DEVICE = new ErrorCode(17, "No device found");
    public static final ErrorCode ERR_CODE_FIRMWARE_NO_BIN = new ErrorCode(18, "No firmware binary found");
    public static final ErrorCode ERR_CODE_INVALID_PARAMETER = new ErrorCode(19, "Invalid parameter");
    public static final ErrorCode ERR_CODE_USB_CLAIM_INTERFACE_FAIL = new ErrorCode(20, "USB claim interface failed");

    public ErrorCode(int i) {
        this.value = i;
    }

    public ErrorCode(int i, String str) {
        this.value = i;
        this.description = str;
    }

    public static boolean HITI_ERROR(ErrorCode errorCode) {
        return !errorCode.equals(ERR_CODE_SUCCESS);
    }

    public boolean equals(Object obj) {
        return (obj instanceof ErrorCode) && this.value == ((ErrorCode) obj).value;
    }
}
