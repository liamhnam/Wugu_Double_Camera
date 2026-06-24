package org.xmlpull.p065v1.builder;

public interface XmlNotation extends XmlContainer {
    String getDeclarationBaseUri();

    String getName();

    String getPublicIdentifier();

    String getSystemIdentifier();
}
