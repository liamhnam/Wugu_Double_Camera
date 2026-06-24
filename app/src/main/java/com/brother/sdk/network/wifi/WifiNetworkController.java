package com.brother.sdk.network.wifi;

import android.content.Context;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.brother.sdk.network.NetworkControllerManager;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WifiNetworkController extends NetworkControllerManager.NetworkController {
    private Context mContext;
    private WifiManager mWifiManager;

    @Override
    public void startControl() {
    }

    @Override
    public void stopControl() {
    }

    public WifiNetworkController(Context context) {
        super(context);
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
    }

    @Override
    public boolean isConnected() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = this.mWifiManager;
        return wifiManager != null && wifiManager.getWifiState() == 3 && (connectionInfo = this.mWifiManager.getConnectionInfo()) != null && connectionInfo.getSupplicantState() == SupplicantState.COMPLETED;
    }

    @Override
    public InetAddress getBroadcastAddress() {
        try {
            if (this.mWifiManager.getDhcpInfo().ipAddress > 0) {
                return NetworkControllerManager.NetworkController.getBroadcastAddress(InetAddress.getByAddress(toReverse(BigInteger.valueOf(r0.ipAddress).toByteArray())), this.BROADCAST_ADDRESS_DEFAULT);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return this.BROADCAST_ADDRESS_DEFAULT;
    }
}
