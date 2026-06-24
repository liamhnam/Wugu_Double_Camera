package com.epson.isv.eprinterdriver.Ctrl;

import com.epson.isv.eprinterdriver.Common.EpsStatus;

public interface IPrintListener {
    void onCleaningTime(int i);

    String onPageBegin(int i);

    boolean onPageFinished(int i);

    void onPrintAutoContinue();

    void onPrintBegin();

    void onPrintFinished(int i);

    void onPrintPause(int i, int i2, EpsStatus epsStatus);

    void onPrintResume();
}
