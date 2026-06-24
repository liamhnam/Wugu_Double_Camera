package org.xmlpull.p065v1.builder.impl;

import org.xmlpull.p065v1.builder.XmlBuilderException;
import org.xmlpull.p065v1.builder.XmlNamespace;

public class XmlNamespaceImpl implements XmlNamespace {
    private String namespaceName;
    private String prefix;

    XmlNamespaceImpl(String str, String str2) {
        this.prefix = str;
        if (str2 == null) {
            throw new XmlBuilderException("namespace name can not be null");
        }
        this.namespaceName = str2;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getNamespaceName() {
        return this.namespaceName;
    }
}
