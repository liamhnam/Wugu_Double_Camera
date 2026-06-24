package com.brother.sdk.common;

import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnInteger;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnOctets;
import com.p020hp.jipp.model.MaterialPurpose;
import com.p020hp.jipp.model.Media;
import java.util.HashMap;

public abstract class ConnectorDescriptor {
    private static final String DEFAULT_VENDOR_NAME = "Brother";
    private HashMap<String, AsnObject> mHashMap = new HashMap<>();

    public static abstract class DeviceQueryObject {
        public abstract boolean canQuery(ConnectorDescriptor connectorDescriptor);

        public abstract boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject);

        public abstract void initializeQuery();
    }

    public enum Function {
        Print,
        Scan,
        Fax,
        Phoenix,
        DeviceStatus
    }

    public abstract IConnector createConnector(CountrySpec countrySpec);

    public abstract String getDescriptorIdentifier();

    public abstract String getModelName();

    public String getVendorName() {
        return DEFAULT_VENDOR_NAME;
    }

    public abstract boolean support(Function function);

    public void setConnectorValue(String str, AsnObject asnObject) {
        this.mHashMap.put(str, asnObject);
    }

    public AsnObject getConnectorValue(String str) {
        if (this.mHashMap.containsKey(str)) {
            return this.mHashMap.get(str);
        }
        return null;
    }

    public Integer getIntegerValue(String str) {
        if (!this.mHashMap.containsKey(str)) {
            return null;
        }
        AsnObject asnObject = this.mHashMap.get(str);
        if (asnObject instanceof AsnInteger) {
            return Integer.valueOf(((AsnInteger) asnObject).getValue());
        }
        return null;
    }

    public String getStringValue(String str) {
        if (!this.mHashMap.containsKey(str)) {
            return null;
        }
        AsnObject asnObject = this.mHashMap.get(str);
        if (asnObject instanceof AsnOctets) {
            return ((AsnOctets) asnObject).getValue();
        }
        return null;
    }

    public boolean support(String str) {
        Integer integerValue = getIntegerValue(str);
        return integerValue != null && integerValue.intValue() == 1;
    }

    public static class DeviceQueryKey {
        static final String PREFIX = "device";
        public static final String SerialNumber = getPrefix() + "serial";
        public static final String VendorID = getPrefix() + "vendorid";
        public static final String StatusDisplay = getPrefix() + "statusdisplay";
        public static final String InterfaceMacAddress = getPrefix() + "interfacemac";
        public static final String ModelName = getPrefix() + "modelname";
        public static final String IPAddress = getPrefix() + "ipaddress";
        public static final String NodeName = getPrefix() + "nodename";
        public static final String Contact = getPrefix() + "contact";
        public static final String Location = getPrefix() + "location";
        public static final String LanMac = getPrefix() + "lanmac";
        public static final String WlanMac = getPrefix() + "wlanmac";
        public static final String WidiMac = getPrefix() + "widimac";
        public static final String VendorName = getPrefix() + "vendorname";

        public static String getPrefix() {
            return "device.";
        }

        public static class Print {
            static final String PREFIX = DeviceQueryKey.getPrefix() + "print";
            public static final String Color = getPrefix() + "color";
            public static final String Duplex = getPrefix() + "duplex";
            public static final String EngineType = getPrefix() + "engine";

            public static String getPrefix() {
                return PREFIX + ".";
            }

            public static class PostScript {
                static final String PREFIX = Print.getPrefix() + "postscript";
                public static final String Support = getPrefix() + MaterialPurpose.support;

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }

            public static class HBP {
                static final String PREFIX = Print.getPrefix() + "hbp";
                public static final String Support = getPrefix() + MaterialPurpose.support;

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }

            public static class BrotherCommonPDL {
                static final String PREFIX = Print.getPrefix() + "brjpc";
                public static final String Support = getPrefix() + MaterialPurpose.support;
                public static final String AutoRotate = getPrefix() + "autorotate";

                public static String getPrefix() {
                    return PREFIX + ".";
                }

                public static class Papers {
                    static final String PREFIX = BrotherCommonPDL.getPrefix() + "papers";
                    public static final String Support = getPrefix() + MaterialPurpose.support;

                    public static final String f475A3 = getPrefix() + "a3";

                    public static final String f476B4 = getPrefix() + "b4";
                    public static final String Ledger = getPrefix() + Media.ledger;
                    public static final String Legal = getPrefix() + "legal";

                    private static String getPrefix() {
                        return PREFIX + ".";
                    }
                }
            }

            public static class CopyPrint {
                static final String PREFIX = Scan.getPrefix() + "copyprint";
                public static final String Support = getPrefix() + MaterialPurpose.support;

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }
        }

        public static class Scan {
            static final String PREFIX = DeviceQueryKey.getPrefix() + "scan";
            public static final String Support = getPrefix() + MaterialPurpose.support;
            public static final String Color = getPrefix() + "color";
            public static final String Duplex = getPrefix() + "duplex";
            public static final String AutoCrop = getPrefix() + "autocrop";
            public static final String MultiFeed = getPrefix() + "multifeed";
            public static final String CarrierSheet = getPrefix() + "carriersheet";
            public static final String MainResMax = getPrefix() + "mainresmax";
            public static final String SkewAdjust = getPrefix() + "skewadjust";
            public static final String Protocol = getPrefix() + "protocol";
            public static final String ColorType = getPrefix() + "colortype";
            public static final String DetectJam = getPrefix() + "detectjam";

            public static String getPrefix() {
                return PREFIX + ".";
            }

            public static class AutoDocumentSize {
                static final String PREFIX = Scan.getPrefix() + "autodocsize";
                public static final String FormedSupport = getPrefix() + "formedsupport";
                public static final String UnformedSupport = getPrefix() + "unformedsupport";

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }

            public static class LongDocument {
                static final String PREFIX = Scan.getPrefix() + "longdocument";
                public static final String Support = getPrefix() + MaterialPurpose.support;
                public static final String Max = getPrefix() + "max";

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }

            public static class CopyScan {
                static final String PREFIX = Scan.getPrefix() + "copyscan";
                public static final String Support = getPrefix() + MaterialPurpose.support;

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }

            public static class Flatbed {
                static final String PREFIX = Scan.getPrefix() + "flatbed";
                public static final String WidthMax = getPrefix() + "widthmax";
                public static final String HeightMax = getPrefix() + "heightmax";

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }

            public static class ADFFront {
                static final String PREFIX = Scan.getPrefix() + "adffront";
                public static final String WidthMax = getPrefix() + "widthmax";
                public static final String HeightMax = getPrefix() + "heightmax";
                public static final String WidthMin = getPrefix() + "widthmin";
                public static final String HeightMin = getPrefix() + "heightmin";

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }

            public static class ADFBack {
                static final String PREFIX = Scan.getPrefix() + "adfback";
                public static final String WidthMax = getPrefix() + "widthmax";
                public static final String HeightMax = getPrefix() + "heightmax";
                public static final String WidthMin = getPrefix() + "widthmin";
                public static final String HeightMin = getPrefix() + "heightmin";

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }

            public static class CornerScan {
                static final String PREFIX = Scan.getPrefix() + "cornerscan";
                public static final String Support = getPrefix() + MaterialPurpose.support;

                public static String getPrefix() {
                    return PREFIX + ".";
                }

                public static class ExcessSize {
                    static final String PREFIX = CornerScan.getPrefix() + "excesssize";
                    public static final String Top = getPrefix() + "top";
                    public static final String Bottom = getPrefix() + "bottom";
                    public static final String Left = getPrefix() + "left";
                    public static final String Right = getPrefix() + "right";

                    private static String getPrefix() {
                        return PREFIX + ".";
                    }
                }
            }
        }

        public static class Phoenix {
            static final String PREFIX = DeviceQueryKey.getPrefix() + "phoenix";
            public static final String Support = getPrefix() + MaterialPurpose.support;
            public static final String Enabled = getPrefix() + "enabled";
            public static final String Version = getPrefix() + "version";

            private static String getPrefix() {
                return PREFIX + ".";
            }
        }

        public static class DeviceStatus {
            static final String PREFIX = DeviceQueryKey.getPrefix() + "devicestatus";
            public static final String Code = getPrefix() + "code";
            public static final String DisplayText = getPrefix() + "displaytext";
            public static final String Type = getPrefix() + "type";

            private static String getPrefix() {
                return PREFIX + ".";
            }
        }

        public static class Fax {
            static final String PREFIX = DeviceQueryKey.getPrefix() + "fax";
            public static final String Support = getPrefix() + MaterialPurpose.support;
            public static final String CurrentReceiveMode = getPrefix() + "currentreceivemode";
            public static final String MaxMemoryCount = getPrefix() + "maxmemorycount";

            public static String getPrefix() {
                return PREFIX + ".";
            }

            public static class ReceiveMode {
                static final String PREFIX = Fax.getPrefix() + "receivemode";
                public static final String Support = getPrefix() + MaterialPurpose.support;
                public static final String Print = getPrefix() + "print";
                public static final String Storage = getPrefix() + "storage";
                public static final String StoragePrint = getPrefix() + "storageprint";
                public static final String Cache = getPrefix() + "cache";
                public static final String CachePrint = getPrefix() + "cacheprint";

                private static String getPrefix() {
                    return PREFIX + ".";
                }
            }
        }
    }
}
