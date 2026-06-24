package org.xmlpull.p065v1.builder.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.p065v1.builder.XmlBuilderException;
import org.xmlpull.p065v1.builder.XmlComment;
import org.xmlpull.p065v1.builder.XmlDoctype;
import org.xmlpull.p065v1.builder.XmlDocument;
import org.xmlpull.p065v1.builder.XmlElement;
import org.xmlpull.p065v1.builder.XmlNamespace;
import org.xmlpull.p065v1.builder.XmlNotation;
import org.xmlpull.p065v1.builder.XmlProcessingInstruction;

public class XmlDocumentImpl implements XmlDocument {
    private String characterEncoding;
    private List children = new ArrayList();
    private XmlElement root;
    private Boolean standalone;
    private String version;

    public XmlDocumentImpl(String str, Boolean bool, String str2) {
        this.version = str;
        this.standalone = bool;
        this.characterEncoding = str2;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public Boolean isStandalone() {
        return this.standalone;
    }

    @Override
    public String getCharacterEncodingScheme() {
        return this.characterEncoding;
    }

    @Override
    public void setCharacterEncodingScheme(String str) {
        this.characterEncoding = str;
    }

    @Override
    public XmlProcessingInstruction newProcessingInstruction(String str, String str2) {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public XmlProcessingInstruction addProcessingInstruction(String str, String str2) {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public Iterator children() {
        return this.children.iterator();
    }

    @Override
    public void remocveAllUnparsedEntities() {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public void setDocumentElement(XmlElement xmlElement) {
        boolean z = false;
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i) == this.root) {
                this.children.set(i, xmlElement);
                z = true;
            }
        }
        if (!z) {
            this.children.add(xmlElement);
        }
        this.root = xmlElement;
        xmlElement.setParent(this);
    }

    @Override
    public void insertChild(int i, Object obj) {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public XmlComment addComment(String str) {
        XmlCommentImpl xmlCommentImpl = new XmlCommentImpl(this, str);
        this.children.add(xmlCommentImpl);
        return xmlCommentImpl;
    }

    @Override
    public XmlDoctype newDoctype(String str, String str2) {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public Iterator unparsedEntities() {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public void removeAllChildren() {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public XmlComment newComment(String str) {
        return new XmlCommentImpl(null, str);
    }

    @Override
    public void removeAllNotations() {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public XmlDoctype addDoctype(String str, String str2) {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public void addChild(Object obj) {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public XmlNotation addNotation(String str, String str2, String str3, String str4) {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public String getBaseUri() {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public Iterator notations() {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public XmlElement addDocumentElement(String str) {
        return addDocumentElement(null, str);
    }

    @Override
    public XmlElement addDocumentElement(XmlNamespace xmlNamespace, String str) {
        XmlElementImpl xmlElementImpl = new XmlElementImpl(xmlNamespace, str);
        if (getDocumentElement() != null) {
            throw new XmlBuilderException("document already has root element");
        }
        setDocumentElement(xmlElementImpl);
        return xmlElementImpl;
    }

    @Override
    public boolean isAllDeclarationsProcessed() {
        throw new XmlBuilderException("not implemented");
    }

    @Override
    public XmlElement getDocumentElement() {
        return this.root;
    }
}
