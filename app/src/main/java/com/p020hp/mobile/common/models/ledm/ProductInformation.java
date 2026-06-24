package com.p020hp.mobile.common.models.ledm;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ProductInformation", strict = false)
public class ProductInformation {

    @Element(name = "MakeAndModel", required = false)
    public String model;

    @Element(name = "ProductNumber", required = false)
    public String sku;

    @Element(name = "UUID", required = false)
    public String uuid;
}
