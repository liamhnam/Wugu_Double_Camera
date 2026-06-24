package com.wugu.doublecamera.device.camera.camerausb.canon1500d;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Constants {
    public static final int CANON_VENDOR_ID = 1193;
    public static final int EVENT_EOS_BULB_EXPOSURE_TIME = 49556;
    public static final int EVENT_EOS_CAMERA_STATUS = 49547;
    public static final int EVENT_EOS_DEVICE_PROP_CHANGED = 49545;
    public static final int EVENT_EOS_DEVICE_PROP_DESC_CHANGED = 49546;
    public static final int EVENT_EOS_OBJECT_ADDED = 49537;
    public static final int EVENT_EOS_OBJECT_ADDED2 = 49575;
    public static final int EVENT_EOS_WILL_SOON_SHUTDOWN = 49549;
    public static final int FORMAT_JPEG = 14337;
    public static final int OPERATION_CLOSE_SESSION = 4099;
    public static final int OPERATION_EOS_EVENT_NOTIFY_MODE = 37141;
    public static final int OPERATION_EOS_GET_EVENT_DATA = 37142;
    public static final int OPERATION_EOS_GET_LIVE_VIEW_PICTURE = 37203;
    public static final int OPERATION_EOS_PRESS_SHUTTER_BTN = 37160;
    public static final int OPERATION_EOS_RELEASE_SHUTTER_BTN = 37161;
    public static final int OPERATION_EOS_SET_DEVICE_PROP_VALUE = 37136;
    public static final int OPERATION_EOS_SET_REMOTE_SHOOTING_MODE = 37140;
    public static final int OPERATION_EOS_TAKE_PICTURE = 37135;
    public static final int OPERATION_GET_DEVICE_INFO = 4097;
    public static final int OPERATION_GET_OBJECT = 4105;
    public static final int OPERATION_OPEN_SESSION = 4098;
    public static final int PROPERTY_EOS_AF_MODE = 53512;
    public static final int PROPERTY_EOS_APERTURE_VALUE = 53505;
    public static final int PROPERTY_EOS_AVAILABLE_SHOTS = 53531;
    public static final int PROPERTY_EOS_COLOR_TEMPERATURE = 53514;
    public static final int PROPERTY_EOS_EVF_MODE = 53683;
    public static final int PROPERTY_EOS_EVF_OUTPUT_DEVICE = 53680;
    public static final int PROPERTY_EOS_EXPOSURE_COMPENSATION = 53508;
    public static final int PROPERTY_EOS_ISO_SPEED = 53507;
    public static final int PROPERTY_EOS_METERING_MODE = 53511;
    public static final int PROPERTY_EOS_PICTURE_STYLE = 53520;
    public static final int PROPERTY_EOS_SHOOTING_MODE = 53509;
    public static final int PROPERTY_EOS_SHUTTER_SPEED = 53506;
    public static final int PROPERTY_EOS_WHITE_BALANCE = 53513;
    public static final int PROPERTY_WHITE_BALANCE = 20485;
    public static final int RESPONSE_BUSY = 8217;
    public static final int RESPONSE_OK = 8193;
    public static final int TYPE_COMMAND = 1;
    public static final int TYPE_DATA = 2;
    public static final int TYPE_RESPONSE = 3;

    public static String constantToString(Class<?> cls, int i) {
        String str = String.format("0x%04x", Integer.valueOf(i));
        Field[] declaredFields = cls.getDeclaredFields();
        int length = declaredFields.length;
        for (int i2 = 0; i2 < length; i2++) {
            Field field = declaredFields[i2];
            if (field.getType() == Integer.TYPE && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                try {
                    if (field.getInt(null) == i) {
                        return field.getName() + "(" + str + ")";
                    }
                    continue;
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        return str;
    }
}
