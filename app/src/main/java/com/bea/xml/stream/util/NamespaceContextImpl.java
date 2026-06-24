package com.bea.xml.stream.util;

import com.p020hp.jipp.model.Media;
import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class NamespaceContextImpl implements NamespaceContext {
    SymbolTable prefixTable;
    NamespaceContext rootContext;
    SymbolTable uriTable;

    public NamespaceContextImpl() {
        this.prefixTable = new SymbolTable();
        this.uriTable = new SymbolTable();
        init();
    }

    public NamespaceContextImpl(NamespaceContext namespaceContext) {
        this.prefixTable = new SymbolTable();
        this.uriTable = new SymbolTable();
        this.rootContext = null;
        init();
    }

    public void init() {
        bindNamespace(XMLConstants.XML_NS_PREFIX, XMLConstants.XML_NS_URI);
        bindNamespace(XMLConstants.XMLNS_ATTRIBUTE, XMLConstants.XML_NS_URI);
    }

    public void openScope() {
        this.prefixTable.openScope();
        this.uriTable.openScope();
    }

    public void closeScope() {
        this.prefixTable.closeScope();
        this.uriTable.closeScope();
    }

    public void bindNamespace(String str, String str2) {
        this.prefixTable.put(str, str2);
        this.uriTable.put(str2, str);
    }

    public int getDepth() {
        return this.prefixTable.getDepth();
    }

    @Override
    public String getNamespaceURI(String str) {
        NamespaceContext namespaceContext;
        String str2 = this.prefixTable.get(str);
        return (str2 != null || (namespaceContext = this.rootContext) == null) ? str2 : namespaceContext.getNamespaceURI(str);
    }

    @Override
    public String getPrefix(String str) {
        NamespaceContext namespaceContext;
        String str2 = this.uriTable.get(str);
        return (str2 != null || (namespaceContext = this.rootContext) == null) ? str2 : namespaceContext.getPrefix(str);
    }

    public void bindDefaultNameSpace(String str) {
        bindNamespace("", str);
    }

    public void unbindDefaultNameSpace() {
        bindNamespace("", null);
    }

    public void unbindNamespace(String str, String str2) {
        this.prefixTable.put(str, null);
        this.prefixTable.put(str2, null);
    }

    public String getDefaultNameSpace() {
        return getNamespaceURI("");
    }

    @Override
    public Iterator getPrefixes(String str) {
        return this.uriTable.getAll(str).iterator();
    }

    public static void main(String[] strArr) throws Exception {
        NamespaceContextImpl namespaceContextImpl = new NamespaceContextImpl();
        namespaceContextImpl.openScope();
        namespaceContextImpl.bindNamespace("a", "uri");
        namespaceContextImpl.bindNamespace(Media.f726b, "uri");
        System.out.println(new StringBuffer("a=").append(namespaceContextImpl.getNamespaceURI("a")).toString());
        System.out.println(new StringBuffer("uri=").append(namespaceContextImpl.getPrefix("uri")).toString());
        Iterator prefixes = namespaceContextImpl.getPrefixes("uri");
        while (prefixes.hasNext()) {
            System.out.println(new StringBuffer("1 uri->").append(prefixes.next()).toString());
        }
        namespaceContextImpl.openScope();
        namespaceContextImpl.bindNamespace("a", "uri2");
        Iterator prefixes2 = namespaceContextImpl.getPrefixes("uri");
        while (prefixes2.hasNext()) {
            System.out.println(new StringBuffer("2 uri->").append(prefixes2.next()).toString());
        }
        namespaceContextImpl.closeScope();
        namespaceContextImpl.closeScope();
    }
}
