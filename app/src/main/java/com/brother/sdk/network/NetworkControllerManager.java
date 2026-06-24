package com.brother.sdk.network;

import android.content.Context;
import com.brother.sdk.BrotherAndroidLib;
import com.brother.sdk.network.wifi.WifiNetworkController;
import com.brother.sdk.network.wifidirect.WifiDirectNetworkController;
import java.lang.ref.WeakReference;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetworkControllerManager {
    private static WifiDirectNetworkController mWifiDirectInstance;
    private static WifiNetworkController mWifiInstance;

    public static WifiNetworkController getWifiNetworkController() {
        if (mWifiInstance == null) {
            mWifiInstance = new WifiNetworkController(BrotherAndroidLib.getAndroidContext());
        }
        return mWifiInstance;
    }

    public static WifiDirectNetworkController getWifiDirectNetworkController() {
        if (mWifiDirectInstance == null) {
            mWifiDirectInstance = new WifiDirectNetworkController(BrotherAndroidLib.getAndroidContext());
        }
        return mWifiDirectInstance;
    }

    public static abstract class NetworkController {
        protected InetAddress BROADCAST_ADDRESS_DEFAULT;
        protected WeakReference<Context> mContext;

        public abstract InetAddress getBroadcastAddress();

        public abstract boolean isConnected();

        public abstract void startControl();

        public abstract void stopControl();

        public NetworkController(Context context) {
            this.mContext = new WeakReference<>(context);
            try {
                this.BROADCAST_ADDRESS_DEFAULT = InetAddress.getByName("255.255.255.255");
            } catch (UnknownHostException unused) {
                this.BROADCAST_ADDRESS_DEFAULT = null;
            }
        }

        public static InetAddress getBroadcastAddress(InetAddress inetAddress, InetAddress inetAddress2) {
            if (inetAddress == null) {
                return inetAddress2;
            }
            try {
                NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(inetAddress);
                if (byInetAddress == null) {
                    return inetAddress2;
                }
                InetAddress broadcastAddressOverGINGERBREAD = getBroadcastAddressOverGINGERBREAD(byInetAddress);
                if (broadcastAddressOverGINGERBREAD != null) {
                    return broadcastAddressOverGINGERBREAD;
                }
            } catch (NoSuchMethodError | SocketException unused) {
            }
            return inetAddress2;
        }

        protected static byte[] toReverse(byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            int length = bArr.length - 1;
            int length2 = bArr.length;
            int i = 0;
            while (i < length2) {
                bArr2[length] = bArr[i];
                i++;
                length--;
            }
            return bArr2;
        }

        private static InetAddress getBroadcastAddressOverGINGERBREAD(NetworkInterface networkInterface) {
            for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                if (interfaceAddress.getAddress() instanceof Inet4Address) {
                    return interfaceAddress.getBroadcast();
                }
            }
            return null;
        }
    }
}
