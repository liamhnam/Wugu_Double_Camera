package com.brother.sdk.common.socket;

import java.io.IOException;

public abstract class SocketClient {
    public abstract boolean bind(ISocket iSocket) throws IOException;

    public abstract void cancel() throws IOException;

    public abstract void close() throws IOException;

    public abstract ProtocolType getType();

    public enum ProtocolType {
        Undefined(-1),
        SNMP(0),
        LPR(1),
        Port9100(2),
        ScanCommand(3);

        private int mStatus;

        ProtocolType(int i) {
            this.mStatus = i;
        }

        public int toValue() {
            return this.mStatus;
        }

        public static ProtocolType fromValue(int i) {
            for (ProtocolType protocolType : values()) {
                if (protocolType.mStatus == i) {
                    return protocolType;
                }
            }
            return Undefined;
        }
    }
}
