package com.brother.sdk.common.device.datacontrol;

import java.io.Serializable;

public class DataControl implements Serializable {
    private static final long serialVersionUID = -6933181522373175035L;
    public boolean enable = false;
    public int version = 0;
    public DataControlCapabilities capabilities = new DataControlCapabilities();
}
