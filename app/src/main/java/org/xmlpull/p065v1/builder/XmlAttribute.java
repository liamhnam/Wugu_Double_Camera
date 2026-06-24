package org.xmlpull.p065v1.builder;

public interface XmlAttribute {
    String getName();

    XmlNamespace getNamespace();

    String getNamespaceName();

    XmlElement getOwner();

    String getType();

    String getValue();

    boolean isSpecified();
}
