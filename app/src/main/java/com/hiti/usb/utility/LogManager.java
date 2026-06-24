package com.hiti.usb.utility;

import android.util.Log;

public class LogManager {
    private int m_iEnble = 0;

    public LogManager(int i) {
        SetEnable(i);
    }

    public void SetEnable(int i) {
        this.m_iEnble = i;
    }

    public void m403e(String str, String str2) {
        if (this.m_iEnble == 1) {
            Log.e(str, str2);
        }
    }

    public void m404i(String str, String str2) {
        if (this.m_iEnble == 1) {
            Log.i(str, str2);
        }
    }
}
