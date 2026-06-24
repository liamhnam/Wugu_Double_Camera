package com.brother.sdk.common.socket.print;

public enum PrintState {
    Success,
    ErrorPrintNoSupportedParameter,
    ErrorPrintInvalidArgument,
    ErrorPrintConnectionFailure,
    ErrorPrintCreatePDLFailure,
    ErrorPrintNoSupportedProtocol,
    ErrorPrintCancelJob,
    ErrorPrint,
    ErrorGCPConnectionFailure,
    ErrorGCPNotExist,
    ErrorGCPOffline,
    ErrorESConnectionFailure,
    ErrorESPaperEmpty,
    ErrorESOverHeat,
    ErrorESOutOfMemory,
    ErrorESNoSdCard,
    ErrorESNoDefine,
    ErrorUSBPrinterConnectionFailure,
    ErrorPrintNoResponse
}
