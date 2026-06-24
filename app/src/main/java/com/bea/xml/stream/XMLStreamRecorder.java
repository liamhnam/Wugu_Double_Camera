package com.bea.xml.stream;

import com.bea.xml.stream.util.ElementTypeNames;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import okhttp3.HttpUrl;

public class XMLStreamRecorder extends XMLWriterBase {
    public XMLStreamRecorder() {
    }

    public XMLStreamRecorder(Writer writer) {
        super(writer);
    }

    @Override
    protected String writeName(String str, String str2, String str3) throws XMLStreamException {
        if (!"".equals(str2)) {
            write(new StringBuffer("['").append(str2).append("':").toString());
        } else {
            write("[");
        }
        String strWriteName = super.writeName(str, str2, str3);
        write(']');
        return strWriteName;
    }

    protected void writeType(int i) throws XMLStreamException {
        closeStartElement();
        write('[');
        write(ElementTypeNames.getEventTypeString(i));
        write(']');
    }

    @Override
    protected void openStartTag() throws XMLStreamException {
        write('[');
    }

    @Override
    protected void closeStartTag() throws XMLStreamException {
        write("];\n");
    }

    @Override
    protected void openEndTag() throws XMLStreamException {
        write('[');
    }

    @Override
    protected void closeEndTag() throws XMLStreamException {
        write(']');
    }

    @Override
    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        write("[[ATTRIBUTE]");
        writeName("", str, str2);
        write("=");
        writeCharactersInternal(str3.toCharArray(), 0, str3.length(), true);
        write("]");
    }

    @Override
    public void writeNamespace(String str, String str2) throws XMLStreamException {
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before a namespace");
        }
        if (str == null || "".equals(str) || XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
            writeDefaultNamespace(str2);
            return;
        }
        write("[[NAMESPACE][");
        write("xmlns:");
        write(str);
        write("]=[");
        write(str2);
        write("]");
        setPrefix(str, str2);
        write(']');
    }

    @Override
    public void writeDefaultNamespace(String str) throws XMLStreamException {
        write("[[DEFAULT][");
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before the default namespace");
        }
        write("xmlns]");
        write("=[");
        write(str);
        write("]");
        setPrefix("", str);
        write(']');
    }

    @Override
    public void writeComment(String str) throws XMLStreamException {
        closeStartElement();
        write("[");
        if (str != null) {
            write(str);
        }
        write("]");
    }

    @Override
    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        closeStartElement();
        write("[");
        if (str != null) {
            write(new StringBuffer("[").append(str).append("]").toString());
        }
        if (str2 != null) {
            write(new StringBuffer(",[").append(str2).append("]").toString());
        }
        write("]");
    }

    @Override
    public void writeDTD(String str) throws XMLStreamException {
        write("[");
        super.write(str);
        write("]");
    }

    @Override
    public void writeCData(String str) throws XMLStreamException {
        write("[");
        if (str != null) {
            write(str);
        }
        write("]");
    }

    @Override
    public void writeEntityRef(String str) throws XMLStreamException {
        write("[");
        super.writeEntityRef(str);
        write("]");
    }

    @Override
    public void writeStartDocument() throws XMLStreamException {
        write("[[1.0],[utf-8]]");
    }

    @Override
    public void writeStartDocument(String str) throws XMLStreamException {
        write("[[");
        write(str);
        write("],[utf-8]]");
    }

    @Override
    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        write("[[");
        write(str2);
        write("],[");
        write(str);
        write("]]");
    }

    @Override
    protected void writeCharactersInternal(char[] cArr, int i, int i2, boolean z) throws XMLStreamException {
        if (i2 == 0) {
            write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        write("[");
        write(cArr, i, i2);
        write("]");
    }

    @Override
    public void write(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        writeType(xMLStreamReader.getEventType());
        super.write(xMLStreamReader);
        if (isOpen()) {
            return;
        }
        write(";\n");
    }

    public static void main(String[] strArr) throws Exception {
        XMLInputFactory xMLInputFactoryNewInstance = XMLInputFactory.newInstance();
        XMLOutputFactory.newInstance();
        XMLStreamReader xMLStreamReaderCreateXMLStreamReader = xMLInputFactoryNewInstance.createXMLStreamReader(new FileReader(strArr[0]));
        XMLStreamRecorder xMLStreamRecorder = new XMLStreamRecorder(new OutputStreamWriter(new FileOutputStream("out.stream")));
        while (xMLStreamReaderCreateXMLStreamReader.hasNext()) {
            xMLStreamRecorder.write(xMLStreamReaderCreateXMLStreamReader);
            xMLStreamReaderCreateXMLStreamReader.next();
        }
        xMLStreamRecorder.write(xMLStreamReaderCreateXMLStreamReader);
        xMLStreamRecorder.flush();
    }
}
