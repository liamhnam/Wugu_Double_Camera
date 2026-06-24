package com.brother.sdk.common.socket.scan;

public class ScanErrorException extends Exception {
    private static final long serialVersionUID = 8799272652173026177L;
    private ScanState mStatus;

    public ScanState error() {
        return this.mStatus;
    }

    public ScanErrorException(String str, ScanState scanState) {
        super(str);
        this.mStatus = scanState;
    }
}
