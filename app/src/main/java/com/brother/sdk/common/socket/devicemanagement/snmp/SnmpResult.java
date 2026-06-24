package com.brother.sdk.common.socket.devicemanagement.snmp;

import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.varbind;

public class SnmpResult {
    public SnmpState state = SnmpState.SuccessRequest;
    public String address = null;
    public varbind[] results = null;

    public enum SnmpState {
        SuccessRequest,
        ErrorRequestNoSuchName,
        ErrorRequestTimeout,
        ErrorRequestInvalid,
        ErrorRequestCancel
    }
}
