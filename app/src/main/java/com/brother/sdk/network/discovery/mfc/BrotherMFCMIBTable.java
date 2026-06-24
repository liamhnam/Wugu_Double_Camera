package com.brother.sdk.network.discovery.mfc;

import android.util.SparseArray;
import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.fax.FaxReceiveMode;
import com.brother.sdk.common.device.printer.PrinterModelType;
import com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject;
import com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryTable;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnInteger;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnOctets;
import java.util.HashMap;

public class BrotherMFCMIBTable extends DeviceMIBQueryTable {
    public static final BrotherMFCMIBTable TABLE = new BrotherMFCMIBTable();

    @Override
    protected void initializeTable(HashMap<String, DeviceMIBQueryObject> map) {
        map.put(ConnectorDescriptor.DeviceQueryKey.SerialNumber, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.5.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.VendorID, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.2.1.1.2"));
        map.put(ConnectorDescriptor.DeviceQueryKey.StatusDisplay, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.11.2.3.9.1.1.3"));
        map.put(ConnectorDescriptor.DeviceQueryKey.InterfaceMacAddress, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.2.1.2.2.1.6.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.NodeName, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.1240.2.3.4.1.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.ModelName, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.2.1.25.3.2.1.3.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.LanMac, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.4.1.2435.2.4.4.1240.5.2.1.1.5.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.WlanMac, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.4.1.2435.2.4.4.1240.5.2.1.1.5.2"));
        map.put(ConnectorDescriptor.DeviceQueryKey.WidiMac, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.4.1.2435.2.4.4.1240.5.2.1.1.5.3"));
        map.put(ConnectorDescriptor.DeviceQueryKey.InterfaceMacAddress, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.2.1.2.2.1.6.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Location, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.2.1.1.6"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Contact, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.2.1.1.4"));
        map.put(ConnectorDescriptor.DeviceQueryKey.IPAddress, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.1240.2.3.4.5.2.3"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.EngineType, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.2.1.43.10.2.1.2.1.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.Color, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.2.1.43.10.2.1.6.1.1") {
            @Override
            public boolean canQuery(ConnectorDescriptor connectorDescriptor) {
                AsnObject connectorValue = connectorDescriptor.getConnectorValue(ConnectorDescriptor.DeviceQueryKey.Print.EngineType);
                if (connectorValue == null || connectorValue.getRespType() != 2) {
                    return true;
                }
                return !PrinterModelType.fromValue(((AsnInteger) connectorValue).getValue()).isDocumentScannerModel();
            }

            @Override
            public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
                if (connectorDescriptor == null || str == null || asnObject == null || asnObject.getRespType() != 2 || ((AsnInteger) asnObject).getValue() != 4) {
                    return false;
                }
                connectorDescriptor.setConnectorValue(str, new AsnInteger(1));
                return true;
            }
        });
        int i = 5;
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.Duplex, new DeviceMIBQueryObject("1.3.6.1.2.1.43.13.4.1.9.1", i) {
            private static final int COLOR_SUPPORT_VALUE1 = 3;
            private static final int COLOR_SUPPORT_VALUE2 = 4;

            @Override
            public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
                if (connectorDescriptor == null || str == null || asnObject == null || asnObject.getRespType() != 2) {
                    return false;
                }
                int value = ((AsnInteger) asnObject).getValue();
                if (value != 3 && value != 4) {
                    return false;
                }
                connectorDescriptor.setConnectorValue(str, new AsnInteger(1));
                return true;
            }
        });
        int i2 = 45;
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.PostScript.Support, new DeviceMIBQueryObject("1.3.6.1.2.1.43.15.1.1.2.1", i2) {
            private static final int POSTSCRIPT_SUPPORT_VALUE = 6;

            @Override
            public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
                if (connectorDescriptor == null || str == null || asnObject == null || asnObject.getRespType() != 2 || ((AsnInteger) asnObject).getValue() != 6) {
                    return false;
                }
                connectorDescriptor.setConnectorValue(str, new AsnInteger(1));
                return true;
            }
        });
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.HBP.Support, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.1.1.7") {
            private static final String HBP_SUPPORT_VALUE1 = "HBP";
            private static final String HBP_SUPPORT_VALUE2 = "PCL";
            private static final String HBP_SUPPORT_VALUE3 = "XL2HB";

            @Override
            public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
                if (connectorDescriptor == null || str == null || asnObject == null || asnObject.getRespType() != 4) {
                    return false;
                }
                String value = ((AsnOctets) asnObject).getValue();
                int iIndexOf = value.indexOf("CMD:") + 4;
                int iIndexOf2 = value.indexOf(";", iIndexOf);
                if (iIndexOf < 0 || iIndexOf2 < 0) {
                    return false;
                }
                String strSubstring = value.substring(iIndexOf, iIndexOf2);
                if (!strSubstring.contains(HBP_SUPPORT_VALUE1) && !strSubstring.contains(HBP_SUPPORT_VALUE2) && !strSubstring.contains(HBP_SUPPORT_VALUE3)) {
                    return false;
                }
                connectorDescriptor.setConnectorValue(str, new AsnInteger(1));
                return true;
            }
        });
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Support, new DeviceMIBQueryObject("1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.70.1.1.2", i2) {
            private static final int BRJPC_SUPPORT_VALUE = 17;

            @Override
            public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
                if (connectorDescriptor == null || str == null || asnObject == null || asnObject.getRespType() != 2 || ((AsnInteger) asnObject).getValue() != 17) {
                    return false;
                }
                connectorDescriptor.setConnectorValue(str, new AsnInteger(1));
                return true;
            }
        });
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.AutoRotate, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.2.1") {
            @Override
            public boolean canQuery(ConnectorDescriptor connectorDescriptor) {
                AsnObject connectorValue = connectorDescriptor.getConnectorValue(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Support);
                return connectorValue == null || connectorValue.getRespType() != 2 || ((AsnInteger) connectorValue).getValue() == 1;
            }
        });
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.Support, new DeviceMIBQueryObject("1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.71.12.1.2", i2) {
            private HashMap<String, String> mConditionTable = new HashMap<>();

            @Override
            public void initializeQuery() {
                this.mConditionTable.put(MediaSize.A3.name, ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.f475A3);
                this.mConditionTable.put(MediaSize.B4.name, ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.f476B4);
                this.mConditionTable.put(MediaSize.Ledger.name, ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.Ledger);
                this.mConditionTable.put(MediaSize.Legal.name, ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.Legal);
            }

            @Override
            public boolean canQuery(ConnectorDescriptor connectorDescriptor) {
                AsnObject connectorValue = connectorDescriptor.getConnectorValue(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Support);
                return connectorValue == null || connectorValue.getRespType() != 2 || ((AsnInteger) connectorValue).getValue() == 1;
            }

            @Override
            public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
                if (connectorDescriptor == null || str == null || asnObject == null || asnObject.getRespType() != 4) {
                    return false;
                }
                String value = ((AsnOctets) asnObject).getValue();
                if (!this.mConditionTable.containsKey(value)) {
                    return false;
                }
                connectorDescriptor.setConnectorValue(this.mConditionTable.get(value), new AsnInteger(1));
                this.mConditionTable.remove(value);
                return this.mConditionTable.size() == 0;
            }
        });
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.Support, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.2.101.3.11.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.MultiFeed, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.6"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.Duplex, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.2"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.SkewAdjust, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.21"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.Protocol, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.22"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.AutoCrop, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.5"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.CarrierSheet, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.9"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.MainResMax, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.10"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.Color, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ColorType, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.23"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.DetectJam, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.24"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.WidthMax, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.13"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.WidthMin, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.17"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.HeightMax, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.14"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.HeightMin, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.18"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.WidthMax, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.15"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.WidthMin, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.19"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.HeightMax, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.16"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.HeightMin, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.20"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.AutoDocumentSize.FormedSupport, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.3"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.AutoDocumentSize.UnformedSupport, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.4"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.CornerScan.Support, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.25"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.CornerScan.ExcessSize.Top, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.26"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.CornerScan.ExcessSize.Bottom, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.27"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.CornerScan.ExcessSize.Left, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.29"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.CornerScan.ExcessSize.Right, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.28"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.Flatbed.WidthMax, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.11"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.Flatbed.HeightMax, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.12"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.LongDocument.Support, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.7"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.LongDocument.Max, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.1.2.69.1.8"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Phoenix.Support, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.4.3.2435.5.39.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Phoenix.Enabled, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.4.3.2435.5.39.2"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Phoenix.Version, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.4.3.2435.5.39.3"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Fax.Support, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.2.101.2.1.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Fax.CurrentReceiveMode, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.5.61.7"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Fax.MaxMemoryCount, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.5.61.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.Support, new DeviceMIBQueryObject("1.3.6.1.4.1.2435.2.3.9.4.2.1.5.5.61.6.1.2", i) {
            private SparseArray<String> mConditionTable = new SparseArray<>();

            @Override
            public void initializeQuery() {
                this.mConditionTable.put(FaxReceiveMode.Print.val, ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.Print);
                this.mConditionTable.put(FaxReceiveMode.Storage.val, ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.Storage);
                this.mConditionTable.put(FaxReceiveMode.StoragePrint.val, ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.StoragePrint);
                this.mConditionTable.put(FaxReceiveMode.Cache.val, ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.Cache);
                this.mConditionTable.put(FaxReceiveMode.CachePrint.val, ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.CachePrint);
            }

            @Override
            public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
                if (connectorDescriptor == null || str == null || asnObject == null || asnObject.getRespType() != 2) {
                    return false;
                }
                Integer numValueOf = Integer.valueOf(((AsnInteger) asnObject).getValue());
                String str2 = this.mConditionTable.get(numValueOf.intValue());
                if (str2 == null) {
                    return false;
                }
                connectorDescriptor.setConnectorValue(str2, new AsnInteger(1));
                this.mConditionTable.remove(numValueOf.intValue());
                return this.mConditionTable.size() == 0;
            }
        });
        map.put(ConnectorDescriptor.DeviceQueryKey.DeviceStatus.Code, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.5.6"));
        map.put(ConnectorDescriptor.DeviceQueryKey.DeviceStatus.Type, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.2.1.25.3.2.1.5.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.DeviceStatus.DisplayText, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.TableEntry, "1.3.6.1.2.1.43.16.5.1.2.1.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Scan.CopyScan.Support, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.5.65.1"));
        map.put(ConnectorDescriptor.DeviceQueryKey.Print.CopyPrint.Support, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.4.1.2435.2.3.9.4.2.1.5.5.65.2"));
        map.put(ConnectorDescriptor.DeviceQueryKey.VendorName, new DeviceMIBQueryObject(DeviceMIBQueryObject.MIBType.Scalar, "1.3.6.1.2.1.1.1") {
            @Override
            public boolean endQuery(ConnectorDescriptor connectorDescriptor, String str, AsnObject asnObject) {
                String value;
                int iIndexOf;
                if (connectorDescriptor == null || str == null || asnObject == null || asnObject.getRespType() != 4 || (value = ((AsnOctets) asnObject).getValue()) == null || (iIndexOf = value.indexOf(" ")) <= 0) {
                    return true;
                }
                connectorDescriptor.setConnectorValue(str, new AsnOctets(value.substring(0, iIndexOf).trim()));
                return true;
            }
        });
    }
}
