package com.brother.sdk.common.device;

import com.brother.sdk.common.device.datacontrol.DataControl;
import com.brother.sdk.common.device.fax.Fax;
import com.brother.sdk.common.device.printer.Printer;
import com.brother.sdk.common.device.scanner.Scanner;
import java.io.Serializable;

public class Device implements Serializable {
    private static final long serialVersionUID = -3516519158293504640L;
    public DataControl dataControl;
    public Fax fax;
    public String macaddress;
    public String nodeName;
    public Printer printer;
    public Scanner scanner;
    public String modelName = "Unknown Device";
    public CountrySpec countrySpec = CountrySpec.Others;

    public void updateCountrySpec(CountrySpec countrySpec) {
        this.countrySpec = countrySpec;
    }
}
