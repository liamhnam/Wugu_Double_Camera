package com.bea.xml.stream;

import javax.xml.XMLConstants;
import javax.xml.stream.events.Namespace;

public class NamespaceBase extends AttributeBase implements Namespace {
    boolean declaresDefaultNamespace;

    @Override
    public int getEventType() {
        return 13;
    }

    @Override
    public boolean isAttribute() {
        return false;
    }

    @Override
    public boolean isNamespace() {
        return true;
    }

    public NamespaceBase(String str, String str2) {
        super(XMLConstants.XMLNS_ATTRIBUTE, str, str2);
        this.declaresDefaultNamespace = false;
    }

    public NamespaceBase(String str) {
        super(XMLConstants.XMLNS_ATTRIBUTE, "", str);
        this.declaresDefaultNamespace = true;
    }

    @Override
    public String getPrefix() {
        return this.declaresDefaultNamespace ? "" : super.getLocalName();
    }

    @Override
    public String getNamespaceURI() {
        return super.getValue();
    }

    @Override
    public boolean isDefaultNamespaceDeclaration() {
        return this.declaresDefaultNamespace;
    }

    @Override
    public String toString() {
        if (this.declaresDefaultNamespace) {
            return new StringBuffer("xmlns='").append(getNamespaceURI()).append("'").toString();
        }
        return new StringBuffer("xmlns:").append(getPrefix()).append("='").append(getNamespaceURI()).append("'").toString();
    }
}
