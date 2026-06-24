package org.xmlpull.p065v1.builder.adapter;

import java.util.Iterator;
import org.xmlpull.p065v1.builder.XmlAttribute;
import org.xmlpull.p065v1.builder.XmlContainer;
import org.xmlpull.p065v1.builder.XmlDocument;
import org.xmlpull.p065v1.builder.XmlElement;
import org.xmlpull.p065v1.builder.XmlNamespace;

public class XmlElementAdapter implements XmlElement {
    private XmlContainer parent;
    private XmlElement target;

    public XmlElementAdapter(XmlElement xmlElement) {
        this.target = xmlElement;
        if (xmlElement.getParent() != null) {
            XmlContainer parent = xmlElement.getParent();
            if (parent instanceof XmlDocument) {
                ((XmlDocument) parent).setDocumentElement(this);
            }
            if (parent instanceof XmlElement) {
                ((XmlElement) parent).replaceChild(this, xmlElement);
            }
        }
        Iterator itChildren = xmlElement.children();
        while (itChildren.hasNext()) {
            fixParent(itChildren.next());
        }
    }

    private void fixParent(Object obj) {
        if (obj instanceof XmlElement) {
            fixElementParent((XmlElement) obj);
        }
    }

    private XmlElement fixElementParent(XmlElement xmlElement) {
        xmlElement.setParent(this);
        return xmlElement;
    }

    @Override
    public XmlContainer getParent() {
        return this.parent;
    }

    @Override
    public void setParent(XmlContainer xmlContainer) {
        this.parent = xmlContainer;
    }

    @Override
    public XmlNamespace newNamespace(String str, String str2) {
        return this.target.newNamespace(str, str2);
    }

    @Override
    public XmlAttribute findAttribute(String str, String str2) {
        return this.target.findAttribute(str, str2);
    }

    @Override
    public Iterator attributes() {
        return this.target.attributes();
    }

    @Override
    public void removeAllChildren() {
        this.target.removeAllChildren();
    }

    @Override
    public XmlAttribute addAttribute(String str, String str2, String str3, String str4, String str5, boolean z) {
        return this.target.addAttribute(str, str2, str3, str4, str5, z);
    }

    @Override
    public XmlNamespace lookupNamespaceByPrefix(String str) {
        return this.target.lookupNamespaceByPrefix(str);
    }

    @Override
    public XmlAttribute addAttribute(XmlNamespace xmlNamespace, String str, String str2) {
        return this.target.addAttribute(xmlNamespace, str, str2);
    }

    @Override
    public String getNamespaceName() {
        return this.target.getNamespaceName();
    }

    @Override
    public void ensureChildrenCapacity(int i) {
        this.target.ensureChildrenCapacity(i);
    }

    @Override
    public Iterator namespaces() {
        return this.target.namespaces();
    }

    @Override
    public void removeAllAttributes() {
        this.target.removeAllAttributes();
    }

    @Override
    public XmlNamespace getNamespace() {
        return this.target.getNamespace();
    }

    @Override
    public String getBaseUri() {
        return this.target.getBaseUri();
    }

    @Override
    public void removeAttribute(XmlAttribute xmlAttribute) {
        this.target.removeAttribute(xmlAttribute);
    }

    @Override
    public XmlNamespace declareNamespace(String str, String str2) {
        return this.target.declareNamespace(str, str2);
    }

    @Override
    public void removeAllNamespaceDeclarations() {
        this.target.removeAllNamespaceDeclarations();
    }

    @Override
    public boolean hasAttributes() {
        return this.target.hasAttributes();
    }

    @Override
    public XmlAttribute addAttribute(String str, XmlNamespace xmlNamespace, String str2, String str3, boolean z) {
        return this.target.addAttribute(str, xmlNamespace, str2, str3, z);
    }

    @Override
    public XmlNamespace declareNamespace(XmlNamespace xmlNamespace) {
        return this.target.declareNamespace(xmlNamespace);
    }

    @Override
    public XmlAttribute addAttribute(String str, String str2) {
        return this.target.addAttribute(str, str2);
    }

    @Override
    public boolean hasNamespaceDeclarations() {
        return this.target.hasNamespaceDeclarations();
    }

    @Override
    public XmlNamespace lookupNamespaceByName(String str) {
        return this.target.lookupNamespaceByName(str);
    }

    @Override
    public XmlNamespace newNamespace(String str) {
        return this.target.newNamespace(str);
    }

    @Override
    public void setBaseUri(String str) {
        this.target.setBaseUri(str);
    }

    @Override
    public void setNamespace(XmlNamespace xmlNamespace) {
        this.target.setNamespace(xmlNamespace);
    }

    @Override
    public void ensureNamespaceDeclarationsCapacity(int i) {
        this.target.ensureNamespaceDeclarationsCapacity(i);
    }

    @Override
    public String getName() {
        return this.target.getName();
    }

    @Override
    public void setName(String str) {
        this.target.setName(str);
    }

    @Override
    public XmlAttribute addAttribute(String str, XmlNamespace xmlNamespace, String str2, String str3) {
        return this.target.addAttribute(str, xmlNamespace, str2, str3);
    }

    @Override
    public void ensureAttributeCapacity(int i) {
        this.target.ensureAttributeCapacity(i);
    }

    @Override
    public XmlAttribute addAttribute(XmlAttribute xmlAttribute) {
        return this.target.addAttribute(xmlAttribute);
    }

    @Override
    public XmlElement findElementByName(String str, XmlElement xmlElement) {
        return this.target.findElementByName(str, xmlElement);
    }

    @Override
    public XmlElement newElement(XmlNamespace xmlNamespace, String str) {
        return this.target.newElement(xmlNamespace, str);
    }

    @Override
    public XmlElement addElement(XmlElement xmlElement) {
        return fixElementParent(this.target.addElement(xmlElement));
    }

    @Override
    public XmlElement addElement(String str) {
        return fixElementParent(this.target.addElement(str));
    }

    @Override
    public XmlElement findElementByName(String str, String str2) {
        return this.target.findElementByName(str, str2);
    }

    @Override
    public void addChild(Object obj) {
        this.target.addChild(obj);
        fixParent(obj);
    }

    @Override
    public void insertChild(int i, Object obj) {
        this.target.insertChild(i, obj);
        fixParent(obj);
    }

    @Override
    public XmlElement findElementByName(String str) {
        return this.target.findElementByName(str);
    }

    @Override
    public XmlElement findElementByName(String str, String str2, XmlElement xmlElement) {
        return this.target.findElementByName(str, str2, xmlElement);
    }

    @Override
    public void removeChild(Object obj) {
        this.target.removeChild(obj);
    }

    @Override
    public Iterator children() {
        return this.target.children();
    }

    @Override
    public boolean hasChild(Object obj) {
        return this.target.hasChild(obj);
    }

    @Override
    public XmlElement newElement(String str, String str2) {
        return this.target.newElement(str, str2);
    }

    @Override
    public XmlElement addElement(XmlNamespace xmlNamespace, String str) {
        return fixElementParent(this.target.addElement(xmlNamespace, str));
    }

    @Override
    public boolean hasChildren() {
        return this.target.hasChildren();
    }

    @Override
    public void addChild(int i, Object obj) {
        this.target.addChild(i, obj);
        fixParent(obj);
    }

    @Override
    public void replaceChild(Object obj, Object obj2) {
        this.target.replaceChild(obj, obj2);
        fixParent(obj);
    }

    @Override
    public XmlElement newElement(String str) {
        return this.target.newElement(str);
    }
}
