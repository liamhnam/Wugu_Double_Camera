package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MSEventEnum {
    public static final int BALANCE_UPDATE = 3;
    public static final int DISMISS_LOADING_DIALOG = 9;
    public static final int GOODS_UPDATE = 5;
    public static final int MQTT_CONNECTED = 1;
    public static final int MQTT_DISCONNECTED = 2;
    public static final int NET_CONNECTED = 6;
    public static final int NET_DISCONNECTED = 7;
    public static final int PAY_FINISH = 4;
    public static final int POSTER_E_UPDATE_BEAUTY_LEVEL = 18;
    public static final int PRINTER_STATUS_UPDATE = 19;
    public static final int PRINTER_TEST_PRINT = 12;
    public static final int PRINT_ORDER_PHOTO = 16;
    public static final int SCANNER_REC_MSG = 13;
    public static final int SHOW_LOADING_DIALOG = 8;
    public static final int START_BUSINESS = 11;
    public static final int STOP_BUSINESS = 10;
    public static final int UPDATE_FRAME = 17;
    public static final int UPDATE_PHONE = 0;
    public static final int UPLOAD_PRINT_REC = 15;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MSEvent {
    }
}
