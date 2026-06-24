package com.epson.isv.eprinterdriver.Ctrl;

import com.epson.isv.eprinterdriver.Common.EpsPrinter;

public interface ISearchPrinterListener {
    void onFindPrinter(EpsPrinter epsPrinter);

    void onSearchBegin();

    void onSearchFinished(int i);
}
