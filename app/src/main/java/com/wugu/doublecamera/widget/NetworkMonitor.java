package com.wugu.doublecamera.widget;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.util.Log;
import com.wugu.doublecamera.listener.IIntListener;

public class NetworkMonitor {
    private static final String TAG = "NetworkMonitor";
    private final ConnectivityManager connectivityManager;
    private final IIntListener listener;
    private ConnectivityManager.NetworkCallback networkCallback;

    public NetworkMonitor(Context context, IIntListener iIntListener) {
        this.listener = iIntListener;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public void startNetworkMonitoring() {
        IIntListener iIntListener;
        if (this.connectivityManager != null) {
            this.networkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    Log.d(NetworkMonitor.TAG, "Network available: " + network);
                    ThreadClock.sleep(1000L);
                    NetworkMonitor.this.checkNetworkCapabilities(network);
                }

                @Override
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    Log.d(NetworkMonitor.TAG, "Network capabilities changed: " + networkCapabilities);
                }

                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    Log.d(NetworkMonitor.TAG, "Network lost: " + network);
                    if (NetworkMonitor.this.listener != null) {
                        NetworkMonitor.this.listener.setValue(0);
                    }
                }
            };
            this.connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addTransportType(3).addTransportType(1).addTransportType(0).build(), this.networkCallback);
            if (!isNetworkAvailable() || (iIntListener = this.listener) == null) {
                return;
            }
            iIntListener.setValue(1);
        }
    }

    public void stopNetworkMonitoring() {
        ConnectivityManager.NetworkCallback networkCallback;
        ConnectivityManager connectivityManager = this.connectivityManager;
        if (connectivityManager == null || (networkCallback = this.networkCallback) == null) {
            return;
        }
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    public void checkNetworkCapabilities(Network network) {
        checkNetworkCapabilities(network, this.connectivityManager.getNetworkCapabilities(network));
    }

    private void checkNetworkCapabilities(Network network, NetworkCapabilities networkCapabilities) {
        if (networkCapabilities != null) {
            boolean zHasCapability = networkCapabilities.hasCapability(12);
            boolean zHasCapability2 = networkCapabilities.hasCapability(16);
            if (zHasCapability && zHasCapability2) {
                Log.d(TAG, "Network has internet access: " + network);
                IIntListener iIntListener = this.listener;
                if (iIntListener != null) {
                    iIntListener.setValue(1);
                    return;
                }
                return;
            }
            Log.d(TAG, "Network does not have internet access: " + network);
            IIntListener iIntListener2 = this.listener;
            if (iIntListener2 != null) {
                iIntListener2.setValue(0);
                return;
            }
            return;
        }
        IIntListener iIntListener3 = this.listener;
        if (iIntListener3 != null) {
            iIntListener3.setValue(0);
        }
        Log.d(TAG, "Network capabilities are null");
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
