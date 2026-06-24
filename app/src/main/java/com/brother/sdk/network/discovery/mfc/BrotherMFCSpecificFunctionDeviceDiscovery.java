package com.brother.sdk.network.discovery.mfc;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.device.printer.PrinterModelType;
import com.brother.sdk.common.presets.BrotherDeviceMasterSpec;
import com.brother.sdk.network.discovery.NetworkConnectorDescriptor;
import com.brother.sdk.network.discovery.NetworkConnectorDiscovery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrotherMFCSpecificFunctionDeviceDiscovery extends NetworkConnectorDiscovery {
    private static final int DEVICE_DISCOVERY_TIMEOUT = 500;
    private ConnectorDescriptor.Function mDeviceFunction;

    @Override
    protected int getDiscoveryTimeout() {
        return 500;
    }

    public BrotherMFCSpecificFunctionDeviceDiscovery(String str, ConnectorDescriptor.Function function) {
        super(Arrays.asList(str));
        this.mDeviceFunction = function;
    }

    static class C07341 {
        static final int[] $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function;

        static {
            int[] iArr = new int[ConnectorDescriptor.Function.values().length];
            $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function = iArr;
            try {
                iArr[ConnectorDescriptor.Function.Print.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function[ConnectorDescriptor.Function.Scan.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override
    protected NetworkConnectorDescriptor createWifiConnectorDescriptor(String str) {
        NetworkConnectorDescriptor printerValidateConnectorDescriptor;
        int i = C07341.$SwitchMap$com$brother$sdk$common$ConnectorDescriptor$Function[this.mDeviceFunction.ordinal()];
        if (i == 1) {
            printerValidateConnectorDescriptor = new PrinterValidateConnectorDescriptor(str);
            if (!printerValidateConnectorDescriptor.support(this.mDeviceFunction)) {
                return null;
            }
        } else {
            if (i != 2) {
                return null;
            }
            printerValidateConnectorDescriptor = new ScannerValidateConnectorDescriptor(str);
            if (!printerValidateConnectorDescriptor.support(this.mDeviceFunction)) {
                return null;
            }
        }
        return printerValidateConnectorDescriptor;
    }

    @Override
    protected List<String> getInitialDeviceFilter() {
        return BrotherMFCNetworkConnectorFilter.Filter;
    }

    private static class PrinterValidateConnectorDescriptor extends NetworkConnectorDescriptor {
        private static final List<String> BROTHER_PRINTER_BUILDUP_LIST = new ArrayList<String>() {
            private static final long serialVersionUID = 1;

            {
                add(ConnectorDescriptor.DeviceQueryKey.ModelName);
                add(ConnectorDescriptor.DeviceQueryKey.SerialNumber);
                add(ConnectorDescriptor.DeviceQueryKey.Print.EngineType);
                add(ConnectorDescriptor.DeviceQueryKey.Print.HBP.Support);
                add(ConnectorDescriptor.DeviceQueryKey.Print.Color);
                add(ConnectorDescriptor.DeviceQueryKey.Print.PostScript.Support);
                add(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Support);
                add(ConnectorDescriptor.DeviceQueryKey.Print.Duplex);
                add(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.Support);
                add(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.AutoRotate);
            }
        };

        public PrinterValidateConnectorDescriptor(String str) {
            super(str);
            for (String str2 : BROTHER_PRINTER_BUILDUP_LIST) {
                queryValue(str2, BrotherMFCMIBTable.TABLE.getMIBObject(str2));
            }
        }

        @Override
        public boolean support(ConnectorDescriptor.Function function) {
            if (function != ConnectorDescriptor.Function.Print) {
                return false;
            }
            boolean z = getConnectorValue(ConnectorDescriptor.DeviceQueryKey.ModelName) != null;
            if (hasAnyPrintingFunctions()) {
                return z;
            }
            return false;
        }

        private boolean hasAnyPrintingFunctions() {
            if (getConnectorValue(ConnectorDescriptor.DeviceQueryKey.Print.PostScript.Support) != null || getConnectorValue(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Support) != null) {
                return true;
            }
            BrotherDeviceMasterSpec brotherDeviceMasterSpec = BrotherDeviceMasterSpec.getBrotherDeviceMasterSpec(getModelName());
            if (brotherDeviceMasterSpec != null && brotherDeviceMasterSpec.printModelType == PrinterModelType.PRINT_INKJET) {
                return true;
            }
            if (PrinterModelType.fromValue(getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Print.EngineType)) != PrinterModelType.PRINT_INKJET) {
                return support(ConnectorDescriptor.DeviceQueryKey.Print.HBP.Support) && !support(ConnectorDescriptor.DeviceQueryKey.Print.Color);
            }
            return false;
        }
    }

    private static class ScannerValidateConnectorDescriptor extends NetworkConnectorDescriptor {
        private static final List<String> BROTHER_SCANNER_BUILDUP_LIST = new ArrayList<String>() {
            private static final long serialVersionUID = 1;

            {
                add(ConnectorDescriptor.DeviceQueryKey.ModelName);
                add(ConnectorDescriptor.DeviceQueryKey.SerialNumber);
                add(ConnectorDescriptor.DeviceQueryKey.Print.EngineType);
                add(ConnectorDescriptor.DeviceQueryKey.Scan.Support);
                add(ConnectorDescriptor.DeviceQueryKey.Scan.Duplex);
                add(ConnectorDescriptor.DeviceQueryKey.Scan.Color);
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
            }
        };

        public ScannerValidateConnectorDescriptor(String str) {
            super(str);
            for (String str2 : BROTHER_SCANNER_BUILDUP_LIST) {
                queryValue(str2, BrotherMFCMIBTable.TABLE.getMIBObject(str2));
            }
        }

        @Override
        public boolean support(ConnectorDescriptor.Function function) {
            if (function != ConnectorDescriptor.Function.Scan) {
                return false;
            }
            getConnectorValue(ConnectorDescriptor.DeviceQueryKey.ModelName);
            getConnectorValue(ConnectorDescriptor.DeviceQueryKey.Print.EngineType);
            getConnectorValue(ConnectorDescriptor.DeviceQueryKey.Scan.Support);
            getConnectorValue(ConnectorDescriptor.DeviceQueryKey.Scan.AutoDocumentSize.FormedSupport);
            return true;
        }
    }
}
