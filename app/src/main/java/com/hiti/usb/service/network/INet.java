package com.hiti.usb.service.network;

import com.hiti.usb.service.PrinterService;

public interface INet {

    public interface IThumb {
        boolean isStop();
    }

    public interface IUpload {
        void connectFail(Result result);

        void disconnect();

        void downloadFirmwareDone(PrinterService printerService, String str, String str2);

        void downloadFwStart();

        void uploadDataDone(String str);

        void uploadDataStart();
    }

    public enum Result {
        VerifyFail,
        Uploaded
    }
}
