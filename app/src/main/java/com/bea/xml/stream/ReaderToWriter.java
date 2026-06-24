package com.bea.xml.stream;

import java.io.FileReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class ReaderToWriter {
    private XMLStreamWriter writer;

    public ReaderToWriter() {
    }

    public ReaderToWriter(XMLStreamWriter xMLStreamWriter) {
        this.writer = xMLStreamWriter;
    }

    public void setStreamWriter(XMLStreamWriter xMLStreamWriter) {
        this.writer = xMLStreamWriter;
    }

    public void write(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        System.out.println("wrote event");
        switch (xMLStreamReader.getEventType()) {
            case 1:
                String prefix = xMLStreamReader.getPrefix();
                if (xMLStreamReader.getNamespaceURI() == null) {
                    this.writer.writeStartElement(xMLStreamReader.getLocalName());
                } else if (prefix != null) {
                    this.writer.writeStartElement(xMLStreamReader.getPrefix(), xMLStreamReader.getLocalName(), xMLStreamReader.getNamespaceURI());
                } else {
                    this.writer.writeStartElement(xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName());
                }
                for (int i = 0; i < xMLStreamReader.getNamespaceCount(); i++) {
                    this.writer.writeNamespace(xMLStreamReader.getNamespacePrefix(i), xMLStreamReader.getNamespaceURI(i));
                }
                break;
            case 2:
                this.writer.writeEndElement();
                break;
            case 3:
                this.writer.writeProcessingInstruction(xMLStreamReader.getPITarget(), xMLStreamReader.getPIData());
                break;
            case 4:
            case 6:
                this.writer.writeCharacters(xMLStreamReader.getTextCharacters(), xMLStreamReader.getTextStart(), xMLStreamReader.getTextLength());
                break;
            case 5:
                this.writer.writeComment(xMLStreamReader.getText());
                break;
            case 7:
                String characterEncodingScheme = xMLStreamReader.getCharacterEncodingScheme();
                String version = xMLStreamReader.getVersion();
                if (characterEncodingScheme != null && version != null) {
                    this.writer.writeStartDocument(characterEncodingScheme, version);
                } else if (version != null) {
                    this.writer.writeStartDocument(xMLStreamReader.getVersion());
                }
                break;
            case 8:
                this.writer.writeEndDocument();
                break;
            case 9:
                this.writer.writeEntityRef(xMLStreamReader.getLocalName());
                break;
            case 11:
                this.writer.writeDTD(xMLStreamReader.getText());
                break;
            case 12:
                this.writer.writeCData(xMLStreamReader.getText());
                break;
        }
    }

    public XMLStreamWriter writeAll(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        while (xMLStreamReader.hasNext()) {
            write(xMLStreamReader);
            xMLStreamReader.next();
        }
        this.writer.flush();
        return this.writer;
    }

    public static void main(String[] strArr) throws Exception {
        XMLInputFactory xMLInputFactoryNewInstance = XMLInputFactory.newInstance();
        XMLOutputFactory xMLOutputFactoryNewInstance = XMLOutputFactory.newInstance();
        XMLStreamReader xMLStreamReaderCreateXMLStreamReader = xMLInputFactoryNewInstance.createXMLStreamReader(new FileReader(strArr[0]));
        XMLStreamWriter xMLStreamWriterCreateXMLStreamWriter = xMLOutputFactoryNewInstance.createXMLStreamWriter(System.out);
        ReaderToWriter readerToWriter = new ReaderToWriter(xMLStreamWriterCreateXMLStreamWriter);
        while (xMLStreamReaderCreateXMLStreamReader.hasNext()) {
            readerToWriter.write(xMLStreamReaderCreateXMLStreamReader);
            xMLStreamReaderCreateXMLStreamReader.next();
        }
        xMLStreamWriterCreateXMLStreamWriter.flush();
    }
}
