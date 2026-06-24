package org.xmlpull.p065v1.wrapper.classic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.xmlpull.p065v1.XmlSerializer;

public class XmlSerializerDelegate implements XmlSerializer {

    protected XmlSerializer f3795xs;

    public XmlSerializerDelegate(XmlSerializer xmlSerializer) {
        this.f3795xs = xmlSerializer;
    }

    @Override
    public String getName() {
        return this.f3795xs.getName();
    }

    @Override
    public void setPrefix(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.setPrefix(str, str2);
    }

    @Override
    public void setOutput(OutputStream outputStream, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.setOutput(outputStream, str);
    }

    @Override
    public void endDocument() throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.endDocument();
    }

    @Override
    public void comment(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.comment(str);
    }

    @Override
    public int getDepth() {
        return this.f3795xs.getDepth();
    }

    @Override
    public void setProperty(String str, Object obj) throws IllegalStateException, IllegalArgumentException {
        this.f3795xs.setProperty(str, obj);
    }

    @Override
    public void cdsect(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.cdsect(str);
    }

    @Override
    public void setFeature(String str, boolean z) throws IllegalStateException, IllegalArgumentException {
        this.f3795xs.setFeature(str, z);
    }

    @Override
    public void entityRef(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.entityRef(str);
    }

    @Override
    public void processingInstruction(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.processingInstruction(str);
    }

    @Override
    public void setOutput(Writer writer) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.setOutput(writer);
    }

    @Override
    public void docdecl(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.docdecl(str);
    }

    @Override
    public void flush() throws IOException {
        this.f3795xs.flush();
    }

    @Override
    public Object getProperty(String str) {
        return this.f3795xs.getProperty(str);
    }

    @Override
    public XmlSerializer startTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.f3795xs.startTag(str, str2);
    }

    @Override
    public void ignorableWhitespace(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.ignorableWhitespace(str);
    }

    @Override
    public XmlSerializer text(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.f3795xs.text(str);
    }

    @Override
    public boolean getFeature(String str) {
        return this.f3795xs.getFeature(str);
    }

    @Override
    public XmlSerializer attribute(String str, String str2, String str3) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.f3795xs.attribute(str, str2, str3);
    }

    @Override
    public void startDocument(String str, Boolean bool) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.startDocument(str, bool);
    }

    @Override
    public String getPrefix(String str, boolean z) throws IllegalArgumentException {
        return this.f3795xs.getPrefix(str, z);
    }

    @Override
    public String getNamespace() {
        return this.f3795xs.getNamespace();
    }

    @Override
    public XmlSerializer endTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.f3795xs.endTag(str, str2);
    }

    @Override
    public XmlSerializer text(char[] cArr, int i, int i2) throws IllegalStateException, IOException, IllegalArgumentException {
        return this.f3795xs.text(cArr, i, i2);
    }
}
