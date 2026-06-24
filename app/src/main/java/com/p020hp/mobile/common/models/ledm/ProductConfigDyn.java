package com.p020hp.mobile.common.models.ledm;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ProductConfigDyn", strict = false)
public class ProductConfigDyn {

    @Element(name = "ProductInformation", required = false)
    public ProductInformation information;
}
