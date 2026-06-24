package org.xmlpull.p065v1.builder.impl;

import org.xmlpull.p065v1.builder.XmlAttribute;
import org.xmlpull.p065v1.builder.XmlElement;
import org.xmlpull.p065v1.builder.XmlNamespace;

public class XmlAttributeImpl implements XmlAttribute {
    private boolean default_;
    private String name_;
    private XmlNamespace namespace_;
    private XmlElement owner_;
    private String prefix_;
    private String type_;
    private String value_;

    XmlAttributeImpl(XmlElement xmlElement, String str, String str2) {
        this.type_ = "CDATA";
        this.owner_ = xmlElement;
        this.name_ = str;
        if (str2 == null) {
            throw new IllegalArgumentException("attribute value can not be null");
        }
        this.value_ = str2;
    }

    XmlAttributeImpl(XmlElement xmlElement, XmlNamespace xmlNamespace, String str, String str2) {
        this(xmlElement, str, str2);
        this.namespace_ = xmlNamespace;
    }

    XmlAttributeImpl(XmlElement xmlElement, String str, XmlNamespace xmlNamespace, String str2, String str3) {
        this(xmlElement, xmlNamespace, str2, str3);
        this.type_ = str;
    }

    XmlAttributeImpl(XmlElement xmlElement, String str, XmlNamespace xmlNamespace, String str2, String str3, boolean z) {
        this(xmlElement, xmlNamespace, str2, str3);
        if (str == null) {
            throw new IllegalArgumentException("attribute type can not be null");
        }
        this.type_ = str;
        this.default_ = !z;
    }

    @Override
    public XmlElement getOwner() {
        return this.owner_;
    }

    @Override
    public XmlNamespace getNamespace() {
        return this.namespace_;
    }

    @Override
    public String getNamespaceName() {
        XmlNamespace xmlNamespace = this.namespace_;
        if (xmlNamespace != null) {
            return xmlNamespace.getNamespaceName();
        }
        return null;
    }

    @Override
    public String getName() {
        return this.name_;
    }

    @Override
    public String getValue() {
        return this.value_;
    }

    @Override
    public String getType() {
        return this.type_;
    }

    @Override
    public boolean isSpecified() {
        return !this.default_;
    }

    public String toString() {
        return new StringBuffer("name=").append(this.name_).append(" value=").append(this.value_).toString();
    }
}
