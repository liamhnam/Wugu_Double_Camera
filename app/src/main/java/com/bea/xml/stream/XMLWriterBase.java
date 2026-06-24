package com.bea.xml.stream;

import com.bea.xml.stream.util.NamespaceContextImpl;
import com.bea.xml.stream.util.Stack;
import com.p020hp.jipp.model.MaterialAmountUnit;
import com.p020hp.jipp.model.Media;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.HashSet;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XMLWriterBase extends ReaderToWriter implements XMLStreamWriter {
    protected static final String DEFAULTNS = "";
    private ConfigurationContextBase config;
    private CharsetEncoder encoder;
    private boolean isPrefixDefaulting;
    private HashSet needToWrite;
    private Writer writer;
    private boolean startElementOpened = false;
    private boolean isEmpty = false;
    private Stack localNameStack = new Stack();
    private Stack prefixStack = new Stack();
    private Stack uriStack = new Stack();
    protected NamespaceContextImpl context = new NamespaceContextImpl();
    private int defaultPrefixCount = 0;
    private HashSet setNeedsWritingNs = new HashSet();

    public XMLWriterBase() {
    }

    public XMLWriterBase(Writer writer) {
        this.writer = writer;
        setWriter(writer);
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
        setStreamWriter(this);
        if (writer instanceof OutputStreamWriter) {
            this.encoder = Charset.forName(((OutputStreamWriter) writer).getEncoding()).newEncoder();
        } else {
            this.encoder = null;
        }
    }

    public void setConfigurationContext(ConfigurationContextBase configurationContextBase) {
        this.config = configurationContextBase;
        this.isPrefixDefaulting = configurationContextBase.isPrefixDefaulting();
    }

    protected void write(String str) throws XMLStreamException {
        try {
            this.writer.write(str);
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    protected void write(char c) throws XMLStreamException {
        try {
            this.writer.write(c);
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    protected void write(char[] cArr) throws XMLStreamException {
        try {
            this.writer.write(cArr);
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    protected void write(char[] cArr, int i, int i2) throws XMLStreamException {
        try {
            this.writer.write(cArr, i, i2);
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    protected void writeCharactersInternal(char[] cArr, int i, int i2, boolean z) throws XMLStreamException {
        CharsetEncoder charsetEncoder;
        if (i2 == 0) {
            return;
        }
        int i3 = 0;
        while (i3 < i2) {
            char c = cArr[i3 + i];
            if (c == '\"') {
                if (z) {
                    break;
                } else {
                    i3++;
                }
            } else if (c != '&' && c != '<' && c != '>') {
                if (c >= ' ') {
                    if (c > 127 && (charsetEncoder = this.encoder) != null && !charsetEncoder.canEncode(c)) {
                        break;
                    } else {
                        i3++;
                    }
                } else if (z || !(c == '\t' || c == '\n')) {
                    break;
                } else {
                    i3++;
                }
            } else {
                break;
            }
        }
        if (i3 < i2) {
            slowWriteCharacters(cArr, i, i2, z);
        } else {
            write(cArr, i, i2);
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void slowWriteCharacters(char[] r6, int r7, int r8, boolean r9) throws javax.xml.stream.XMLStreamException {
        /*
            r5 = this;
            r0 = 0
        L1:
            if (r0 >= r8) goto L73
            int r1 = r0 + r7
            char r1 = r6[r1]
            r2 = 34
            if (r1 == r2) goto L65
            r2 = 38
            if (r1 == r2) goto L5f
            r2 = 60
            if (r1 == r2) goto L59
            r2 = 62
            if (r1 == r2) goto L53
            r2 = 32
            r3 = 59
            java.lang.String r4 = "&#"
            if (r1 >= r2) goto L37
            if (r9 != 0) goto L29
            r2 = 9
            if (r1 == r2) goto L6d
            r2 = 10
            if (r1 == r2) goto L6d
        L29:
            r5.write(r4)
            java.lang.String r1 = java.lang.Integer.toString(r1)
            r5.write(r1)
            r5.write(r3)
            goto L70
        L37:
            r2 = 127(0x7f, float:1.78E-43)
            if (r1 <= r2) goto L6d
            java.nio.charset.CharsetEncoder r2 = r5.encoder
            if (r2 == 0) goto L6d
            boolean r2 = r2.canEncode(r1)
            if (r2 != 0) goto L6d
            r5.write(r4)
            java.lang.String r1 = java.lang.Integer.toString(r1)
            r5.write(r1)
            r5.write(r3)
            goto L70
        L53:
            java.lang.String r1 = "&gt;"
            r5.write(r1)
            goto L70
        L59:
            java.lang.String r1 = "&lt;"
            r5.write(r1)
            goto L70
        L5f:
            java.lang.String r1 = "&amp;"
            r5.write(r1)
            goto L70
        L65:
            if (r9 == 0) goto L6d
            java.lang.String r1 = "&quot;"
            r5.write(r1)
            goto L70
        L6d:
            r5.write(r1)
        L70:
            int r0 = r0 + 1
            goto L1
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.XMLWriterBase.slowWriteCharacters(char[], int, int, boolean):void");
    }

    protected void closeStartElement() throws XMLStreamException {
        if (this.startElementOpened) {
            closeStartTag();
            this.startElementOpened = false;
        }
    }

    protected boolean isOpen() {
        return this.startElementOpened;
    }

    protected void closeStartTag() throws XMLStreamException {
        flushNamespace();
        clearNeedsWritingNs();
        if (this.isEmpty) {
            write("/>");
            this.isEmpty = false;
        } else {
            write(">");
        }
    }

    private void openStartElement() throws XMLStreamException {
        if (this.startElementOpened) {
            closeStartTag();
        } else {
            this.startElementOpened = true;
        }
    }

    protected String writeName(String str, String str2, String str3) throws XMLStreamException {
        if (!"".equals(str2)) {
            str = getPrefixInternal(str2);
        }
        if (!"".equals(str)) {
            write(str);
            write(":");
        }
        write(str3);
        return str;
    }

    private String getPrefixInternal(String str) {
        String prefix = this.context.getPrefix(str);
        return prefix == null ? "" : prefix;
    }

    protected String getURIInternal(String str) {
        String namespaceURI = this.context.getNamespaceURI(str);
        return namespaceURI == null ? "" : namespaceURI;
    }

    protected void openStartTag() throws XMLStreamException {
        write("<");
    }

    private boolean needToWrite(String str) {
        if (this.needToWrite == null) {
            this.needToWrite = new HashSet();
        }
        boolean zContains = this.needToWrite.contains(str);
        this.needToWrite.add(str);
        return zContains;
    }

    private void prepareNamespace(String str) throws XMLStreamException {
        if (this.isPrefixDefaulting && !"".equals(str) && getPrefix(str) == null) {
            this.defaultPrefixCount++;
            setPrefix(new StringBuffer("ns").append(this.defaultPrefixCount).toString(), str);
        }
    }

    private void removeNamespace(String str) {
        HashSet hashSet;
        if (!this.isPrefixDefaulting || (hashSet = this.needToWrite) == null) {
            return;
        }
        hashSet.remove(str);
    }

    private void flushNamespace() throws XMLStreamException {
        HashSet<String> hashSet;
        if (!this.isPrefixDefaulting || (hashSet = this.needToWrite) == null) {
            return;
        }
        for (String str : hashSet) {
            String prefix = this.context.getPrefix(str);
            if (prefix == null) {
                throw new XMLStreamException(new StringBuffer("Unable to default prefix with uri:").append(str).toString());
            }
            writeNamespace(prefix, str);
        }
        this.needToWrite.clear();
    }

    protected void writeStartElementInternal(String str, String str2) throws XMLStreamException {
        if (str == null) {
            throw new IllegalArgumentException("The namespace URI may not be null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("The local name  may not be null");
        }
        openStartElement();
        openStartTag();
        prepareNamespace(str);
        this.prefixStack.push(writeName("", str, str2));
        this.localNameStack.push(str2);
        this.uriStack.push(str);
    }

    @Override
    public void writeStartElement(String str, String str2) throws XMLStreamException {
        this.context.openScope();
        writeStartElementInternal(str, str2);
    }

    @Override
    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException {
        if (str3 == null) {
            throw new IllegalArgumentException("The namespace URI may not be null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("The local name may not be null");
        }
        if (str == null) {
            throw new IllegalArgumentException("The prefix may not be null");
        }
        this.context.openScope();
        prepareNamespace(str3);
        this.context.bindNamespace(str, str3);
        writeStartElementInternal(str3, str2);
    }

    @Override
    public void writeStartElement(String str) throws XMLStreamException {
        this.context.openScope();
        writeStartElement("", str);
    }

    @Override
    public void writeEmptyElement(String str, String str2) throws XMLStreamException {
        openStartElement();
        prepareNamespace(str);
        this.isEmpty = true;
        write("<");
        writeName("", str, str2);
    }

    @Override
    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException {
        openStartElement();
        prepareNamespace(str3);
        this.isEmpty = true;
        write("<");
        write(str);
        write(":");
        write(str2);
    }

    @Override
    public void writeEmptyElement(String str) throws XMLStreamException {
        writeEmptyElement("", str);
    }

    protected void openEndTag() throws XMLStreamException {
        write("</");
    }

    protected void closeEndTag() throws XMLStreamException {
        write(">");
    }

    @Override
    public void writeEndElement() throws XMLStreamException {
        if (isOpen()) {
            closeStartElement();
        }
        String str = (String) this.prefixStack.pop();
        String str2 = (String) this.localNameStack.pop();
        this.uriStack.pop();
        openEndTag();
        writeName(str, "", str2);
        closeEndTag();
        this.context.closeScope();
    }

    public void writeRaw(String str) throws XMLStreamException {
        closeStartElement();
        write(str);
    }

    @Override
    public void close() throws XMLStreamException {
        flush();
    }

    @Override
    public void flush() throws XMLStreamException {
        try {
            this.writer.flush();
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    @Override
    public void writeEndDocument() throws XMLStreamException {
        while (!this.localNameStack.isEmpty()) {
            writeEndElement();
        }
    }

    @Override
    public void writeAttribute(String str, String str2) throws XMLStreamException {
        writeAttribute("", str, str2);
    }

    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before an attribute");
        }
        prepareNamespace(str);
        write(" ");
        writeName("", str, str2);
        write("=\"");
        writeCharactersInternal(str3.toCharArray(), 0, str3.length(), true);
        write("\"");
    }

    @Override
    public void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException {
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before an attribute");
        }
        prepareNamespace(str2);
        this.context.bindNamespace(str, str2);
        write(" ");
        writeName(str, str2, str3);
        write("=\"");
        writeCharactersInternal(str4.toCharArray(), 0, str4.length(), true);
        write("\"");
    }

    public void writeNamespace(String str, String str2) throws XMLStreamException {
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before a namespace");
        }
        if (str == null || "".equals(str) || XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
            writeDefaultNamespace(str2);
            return;
        }
        if (needsWritingNs(str)) {
            write(" xmlns:");
            write(str);
            write("=\"");
            write(str2);
            write("\"");
            setPrefix(str, str2);
        }
    }

    private void clearNeedsWritingNs() {
        this.setNeedsWritingNs.clear();
    }

    private boolean needsWritingNs(String str) {
        boolean z = !this.setNeedsWritingNs.contains(str);
        if (z) {
            this.setNeedsWritingNs.add(str);
        }
        return z;
    }

    public void writeDefaultNamespace(String str) throws XMLStreamException {
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before the default namespace");
        }
        if (needsWritingNs("")) {
            write(" xmlns");
            write("=\"");
            write(str);
            write("\"");
            setPrefix("", str);
        }
    }

    public void writeComment(String str) throws XMLStreamException {
        closeStartElement();
        write("<!--");
        if (str != null) {
            write(str);
        }
        write("-->");
    }

    @Override
    public void writeProcessingInstruction(String str) throws XMLStreamException {
        closeStartElement();
        writeProcessingInstruction(str, null);
    }

    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        closeStartElement();
        write("<?");
        if (str != null) {
            write(str);
        }
        if (str2 != null) {
            write(' ');
            write(str2);
        }
        write("?>");
    }

    public void writeDTD(String str) throws XMLStreamException {
        write(str);
    }

    public void writeCData(String str) throws XMLStreamException {
        closeStartElement();
        write("<![CDATA[");
        if (str != null) {
            write(str);
        }
        write("]]>");
    }

    public void writeEntityRef(String str) throws XMLStreamException {
        closeStartElement();
        write("&");
        write(str);
        write(";");
    }

    public void writeStartDocument() throws XMLStreamException {
        write("<?xml version='1.0' encoding='utf-8'?>");
    }

    public void writeStartDocument(String str) throws XMLStreamException {
        write("<?xml version='");
        write(str);
        write("'?>");
    }

    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        write("<?xml version='");
        write(str2);
        write("' encoding='");
        write(str);
        write("'?>");
    }

    @Override
    public void writeCharacters(String str) throws XMLStreamException {
        closeStartElement();
        writeCharactersInternal(str.toCharArray(), 0, str.length(), false);
    }

    @Override
    public void writeCharacters(char[] cArr, int i, int i2) throws XMLStreamException {
        closeStartElement();
        writeCharactersInternal(cArr, i, i2, false);
    }

    @Override
    public String getPrefix(String str) throws XMLStreamException {
        return this.context.getPrefix(str);
    }

    @Override
    public void setPrefix(String str, String str2) throws XMLStreamException {
        needToWrite(str2);
        this.context.bindNamespace(str, str2);
    }

    @Override
    public void setDefaultNamespace(String str) throws XMLStreamException {
        needToWrite(str);
        this.context.bindDefaultNameSpace(str);
    }

    @Override
    public void setNamespaceContext(NamespaceContext namespaceContext) throws XMLStreamException {
        if (namespaceContext == null) {
            throw new NullPointerException("The namespace  context may not be null.");
        }
        this.context = new NamespaceContextImpl(namespaceContext);
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return this.context;
    }

    @Override
    public Object getProperty(String str) throws IllegalArgumentException {
        return this.config.getProperty(str);
    }

    public static void main(String[] strArr) throws Exception {
        XMLOutputFactory xMLOutputFactoryNewInstance = XMLOutputFactoryBase.newInstance();
        xMLOutputFactoryNewInstance.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, new Boolean(true));
        XMLStreamWriter xMLStreamWriterCreateXMLStreamWriter = xMLOutputFactoryNewInstance.createXMLStreamWriter(new OutputStreamWriter(new FileOutputStream("tmp"), "us-ascii"));
        xMLStreamWriterCreateXMLStreamWriter.writeStartDocument();
        xMLStreamWriterCreateXMLStreamWriter.setPrefix(Media.f727c, "http://c");
        xMLStreamWriterCreateXMLStreamWriter.setDefaultNamespace("http://d");
        xMLStreamWriterCreateXMLStreamWriter.writeStartElement("http://c", "a");
        xMLStreamWriterCreateXMLStreamWriter.writeAttribute(Media.f726b, "blah");
        xMLStreamWriterCreateXMLStreamWriter.writeEmptyElement("http://c", Media.f728d);
        xMLStreamWriterCreateXMLStreamWriter.writeEmptyElement("http://d", Media.f729e);
        xMLStreamWriterCreateXMLStreamWriter.writeEmptyElement("http://e", Media.f730f);
        xMLStreamWriterCreateXMLStreamWriter.writeEmptyElement("http://f", MaterialAmountUnit.f719g);
        xMLStreamWriterCreateXMLStreamWriter.writeAttribute("http://c", "chris", "fry");
        xMLStreamWriterCreateXMLStreamWriter.writeCharacters("foo bar foo");
        xMLStreamWriterCreateXMLStreamWriter.writeCharacters("bad char coming[");
        xMLStreamWriterCreateXMLStreamWriter.writeCharacters("$");
        xMLStreamWriterCreateXMLStreamWriter.writeCharacters("]");
        xMLStreamWriterCreateXMLStreamWriter.writeEndElement();
        xMLStreamWriterCreateXMLStreamWriter.flush();
    }
}
