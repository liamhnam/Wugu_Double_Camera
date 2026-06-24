package org.xmlpull.p065v1.builder;

public interface XmlUnparsedEntity extends XmlContainer {
    String getDeclarationBaseUri();

    String getName();

    XmlNotation getNotation();

    String getNotationName();

    String getPublicIdentifier();

    String getSystemIdentifier();
}
