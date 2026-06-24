package com.brother.sdk.network.discovery.mfc;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.device.printer.PrinterModelType;
import com.brother.sdk.common.presets.BrotherDeviceMasterSpec;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnOctets;
import com.brother.sdk.network.discovery.NetworkConnectorDescriptor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrotherMFCNetworkConnectorDescriptor extends NetworkConnectorDescriptor {
    private static final List<String> BROTHER_MFC_INITIALIZE_LIST = new ArrayList<String>() {
        private static final long serialVersionUID = 1;

        {
            add(ConnectorDescriptor.DeviceQueryKey.ModelName);
            add(ConnectorDescriptor.DeviceQueryKey.NodeName);
            add(ConnectorDescriptor.DeviceQueryKey.VendorName);
            add(ConnectorDescriptor.DeviceQueryKey.Print.EngineType);
            add(ConnectorDescriptor.DeviceQueryKey.Print.HBP.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Print.Color);
            add(ConnectorDescriptor.DeviceQueryKey.Print.PostScript.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Phoenix.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Fax.Support);
            add(ConnectorDescriptor.DeviceQueryKey.WidiMac);
        }
    };
    private static final List<String> BROTHER_MFC_BUILDUP_LIST = new ArrayList<String>() {
        private static final long serialVersionUID = 1;

        {
            add(ConnectorDescriptor.DeviceQueryKey.InterfaceMacAddress);
            add(ConnectorDescriptor.DeviceQueryKey.NodeName);
            add(ConnectorDescriptor.DeviceQueryKey.LanMac);
            add(ConnectorDescriptor.DeviceQueryKey.WlanMac);
            add(ConnectorDescriptor.DeviceQueryKey.WidiMac);
            add(ConnectorDescriptor.DeviceQueryKey.Location);
            add(ConnectorDescriptor.DeviceQueryKey.Print.Duplex);
            add(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.AutoRotate);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.Duplex);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.Color);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.MultiFeed);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.Protocol);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.AutoDocumentSize.FormedSupport);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.Flatbed.WidthMax);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.Flatbed.HeightMax);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.WidthMax);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.HeightMax);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.WidthMax);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.HeightMax);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.WidthMin);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.HeightMin);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.WidthMin);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.HeightMin);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.CornerScan.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.SkewAdjust);
            add(ConnectorDescriptor.DeviceQueryKey.Phoenix.Enabled);
            add(ConnectorDescriptor.DeviceQueryKey.Phoenix.Version);
            add(ConnectorDescriptor.DeviceQueryKey.Fax.CurrentReceiveMode);
            add(ConnectorDescriptor.DeviceQueryKey.Fax.MaxMemoryCount);
            add(ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Print.CopyPrint.Support);
            add(ConnectorDescriptor.DeviceQueryKey.Scan.CopyScan.Support);
        }
    };
    private static final List<String> BROTHER_MFC_MINIMUM_CONDITION_LIST = new ArrayList<String>() {
        private static final long serialVersionUID = 1;

        {
            add(ConnectorDescriptor.DeviceQueryKey.ModelName);
            add(ConnectorDescriptor.DeviceQueryKey.Print.EngineType);
        }
    };

    public BrotherMFCNetworkConnectorDescriptor(String str) {
        super(str);
        for (String str2 : BROTHER_MFC_INITIALIZE_LIST) {
            queryValue(str2, BrotherMFCMIBTable.TABLE.getMIBObject(str2));
        }
    }

    @Override
    public IConnector createConnector(CountrySpec countrySpec) {
        for (String str : BROTHER_MFC_BUILDUP_LIST) {
            queryValue(str, BrotherMFCMIBTable.TABLE.getMIBObject(str));
        }
        return super.createConnector(countrySpec);
    }

    @Override
    public String getModelName() {
        AsnObject connectorValue = getConnectorValue(ConnectorDescriptor.DeviceQueryKey.ModelName);
        if (connectorValue != null && (connectorValue instanceof AsnOctets)) {
            return ((AsnOctets) connectorValue).getValue();
        }
        return super.getModelName();
    }

    @Override
    public String getVendorName() {
        return getStringValue(ConnectorDescriptor.DeviceQueryKey.VendorName);
    }

    @Override
    public boolean support(ConnectorDescriptor.Function function) {
        BrotherDeviceMasterSpec brotherDeviceMasterSpec;
        boolean z = false;
        if (!satisfyMinimumSupportCondition()) {
            return false;
        }
        int i = C07324.$SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function[function.ordinal()];
        if (i == 1) {
            boolean z2 = support(ConnectorDescriptor.DeviceQueryKey.Print.PostScript.Support);
            if (!z2 && support(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Support)) {
                z2 = true;
            }
            if (z2 || (brotherDeviceMasterSpec = BrotherDeviceMasterSpec.getBrotherDeviceMasterSpec(getModelName())) == null) {
                z = z2;
            } else if (brotherDeviceMasterSpec.printModelType == PrinterModelType.PRINT_INKJET) {
                z = true;
            }
            if (z || PrinterModelType.fromValue(getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Print.EngineType)) == PrinterModelType.PRINT_INKJET) {
                return z;
            }
            boolean zSupport = support(ConnectorDescriptor.DeviceQueryKey.Print.HBP.Support);
            boolean zSupport2 = support(ConnectorDescriptor.DeviceQueryKey.Print.Color);
            if (!zSupport || zSupport2) {
                return z;
            }
        } else if (i != 2) {
            if (i != 3) {
                if (i == 4) {
                    if (!support(ConnectorDescriptor.DeviceQueryKey.Scan.Support)) {
                        return false;
                    }
                } else if (i != 5 || !support(ConnectorDescriptor.DeviceQueryKey.DeviceStatus.Code)) {
                    return false;
                }
            } else if (!support(ConnectorDescriptor.DeviceQueryKey.Fax.Support)) {
                return false;
            }
        } else if (!support(ConnectorDescriptor.DeviceQueryKey.Phoenix.Support)) {
            return false;
        }
        return true;
    }

    static class C07324 {
        static final int[] $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function;

        static {
            int[] iArr = new int[ConnectorDescriptor.Function.values().length];
            $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function = iArr;
            try {
                iArr[ConnectorDescriptor.Function.Print.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function[ConnectorDescriptor.Function.Phoenix.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function[ConnectorDescriptor.Function.Fax.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function[ConnectorDescriptor.Function.Scan.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function[ConnectorDescriptor.Function.DeviceStatus.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private boolean satisfyMinimumSupportCondition() {
        Iterator<String> it = BROTHER_MFC_MINIMUM_CONDITION_LIST.iterator();
        while (it.hasNext()) {
            if (getConnectorValue(it.next()) == null) {
                return false;
            }
        }
        return true;
    }
}
