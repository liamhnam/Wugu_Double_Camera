package com.p020hp.printsdk.ledm;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "ProductStatusDyn", strict = false)
public class ProductStatusDyn {

    @ElementList(name = "AlertTable", required = false)
    public List<Alert> alerts;
}
