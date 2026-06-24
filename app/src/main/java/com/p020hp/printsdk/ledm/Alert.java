package com.p020hp.printsdk.ledm;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Alert", strict = false)
public class Alert {

    @Element(name = "StringId", required = false)
    public int stringId;
}
