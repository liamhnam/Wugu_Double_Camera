package com.epson.isv.eprinterdriver.Ctrl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.epson.isv.eprinterdriver.Common.ServiceIntent;

public class EPrintStateReceiver extends BroadcastReceiver {
    private static IEPrintStateListener mStateListener;

    public void registerReceiver(Context context) {
        context.registerReceiver(this, new IntentFilter(ServiceIntent.TargetComponentName));
    }

    public void unRegisterReceiver(Context context) {
        context.unregisterReceiver(this);
    }

    public void addStateListener(IEPrintStateListener iEPrintStateListener) {
        mStateListener = iEPrintStateListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        IEPrintStateListener iEPrintStateListener = mStateListener;
        if (iEPrintStateListener != null) {
            iEPrintStateListener.onEPrintStateNty(intent);
        }
    }
}
