package com.brother.sdk.network.wifidirect;

import com.brother.sdk.common.IConnector;

public abstract class WifiDirectDeviceManagerInterface {

    public enum ConnectionState {
        NO_STATUS,
        WIFI_OFF,
        NO_PEER_DISCOVERED,
        ESTABLISHED_WITH_ANOTHER_PEER,
        ESTABLISHED_WITH_ANOTHER_OLD_BROTHER_DEVICE_PEER,
        CONNECTED,
        CONNECTION_FAILED
    }

    public interface OnConnectionCheckListener {
        void onCompleted(ConnectionState connectionState);
    }

    public interface OnConnectionResultListener {
        void onConnectionResult(boolean z);
    }

    public interface OnConnectionValidateListener {
        void onValidationResult(boolean z);
    }

    public interface OnDeviceConnectionListener {
        void onConnectionFailure();

        void onDeviceConnected(IConnector iConnector);
    }

    public interface OnDeviceQueryListener {
        void onDeviceFound(WifiDirectInfo wifiDirectInfo);

        void onQueryStopped();
    }

    public static abstract class WifiP2pDeviceCategory {
        private static final WifiP2pDeviceCategory[] $VALUES;
        public static final WifiP2pDeviceCategory Copier;
        public static final WifiP2pDeviceCategory Fax;
        public static final WifiP2pDeviceCategory MFC;
        public static final WifiP2pDeviceCategory Printer;
        public static final WifiP2pDeviceCategory Scanner;
        private final int value;

        public abstract boolean support(boolean z);

        public static WifiP2pDeviceCategory valueOf(String str) {
            return (WifiP2pDeviceCategory) Enum.valueOf(WifiP2pDeviceCategory.class, str);
        }

        public static WifiP2pDeviceCategory[] values() {
            return (WifiP2pDeviceCategory[]) $VALUES.clone();
        }

        static {
            int i = 1;
            WifiP2pDeviceCategory wifiP2pDeviceCategory = new WifiP2pDeviceCategory("Printer", 0, i) {
                @Override
                public boolean support(boolean z) {
                    return z;
                }
            };
            Printer = wifiP2pDeviceCategory;
            int i2 = 2;
            WifiP2pDeviceCategory wifiP2pDeviceCategory2 = new WifiP2pDeviceCategory("Scanner", i, i2) {
                @Override
                public boolean support(boolean z) {
                    return !z;
                }
            };
            Scanner = wifiP2pDeviceCategory2;
            int i3 = 3;
            WifiP2pDeviceCategory wifiP2pDeviceCategory3 = new WifiP2pDeviceCategory("Fax", i2, i3) {
                @Override
                public boolean support(boolean z) {
                    return false;
                }
            };
            Fax = wifiP2pDeviceCategory3;
            int i4 = 4;
            WifiP2pDeviceCategory wifiP2pDeviceCategory4 = new WifiP2pDeviceCategory("Copier", i3, i4) {
                @Override
                public boolean support(boolean z) {
                    return z;
                }
            };
            Copier = wifiP2pDeviceCategory4;
            WifiP2pDeviceCategory wifiP2pDeviceCategory5 = new WifiP2pDeviceCategory("MFC", i4, 5) {
                @Override
                public boolean support(boolean z) {
                    return true;
                }
            };
            MFC = wifiP2pDeviceCategory5;
            $VALUES = new WifiP2pDeviceCategory[]{wifiP2pDeviceCategory, wifiP2pDeviceCategory2, wifiP2pDeviceCategory3, wifiP2pDeviceCategory4, wifiP2pDeviceCategory5};
        }

        private WifiP2pDeviceCategory(String str, int i, int i2) {
            this.value = i2;
        }

        public int toValue() {
            return this.value;
        }

        public static WifiP2pDeviceCategory fromValue(int i) {
            for (WifiP2pDeviceCategory wifiP2pDeviceCategory : values()) {
                if (wifiP2pDeviceCategory.toValue() == i) {
                    return wifiP2pDeviceCategory;
                }
            }
            return null;
        }
    }

    public enum MFCDeviceType {
        HL(0),
        DCP(1),
        MFC(2);

        private final int value;

        MFCDeviceType(int i) {
            this.value = i;
        }

        public int toValue() {
            return this.value;
        }

        public static MFCDeviceType fromValue(int i) {
            for (MFCDeviceType mFCDeviceType : values()) {
                if (mFCDeviceType.toValue() == i) {
                    return mFCDeviceType;
                }
            }
            return null;
        }

        public static boolean isKindsOfMFC(String str) {
            for (MFCDeviceType mFCDeviceType : values()) {
                if (str.startsWith(mFCDeviceType.toString() + "-")) {
                    return true;
                }
            }
            return false;
        }
    }
}
