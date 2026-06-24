package com.bea.xml.stream.samples;

import java.io.FileReader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

public class EventWrite {
    private static String filename;

    private static void printUsage() {
        System.out.println("usage: java com.bea.xml.stream.samples.EventWrite <xmlfile>");
    }

    public static void main(String[] strArr) throws Exception {
        try {
            filename = strArr[0];
        } catch (ArrayIndexOutOfBoundsException unused) {
            printUsage();
            System.exit(0);
        }
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        System.setProperty("javax.xml.stream.XMLOutputFactory", "com.bea.xml.stream.XMLOutputFactoryBase");
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.bea.xml.stream.EventFactory");
        XMLInputFactory xMLInputFactoryNewInstance = XMLInputFactory.newInstance();
        XMLOutputFactory xMLOutputFactoryNewInstance = XMLOutputFactory.newInstance();
        xMLInputFactoryNewInstance.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
        XMLEventReader xMLEventReaderCreateXMLEventReader = xMLInputFactoryNewInstance.createXMLEventReader(new FileReader(filename));
        XMLEventWriter xMLEventWriterCreateXMLEventWriter = xMLOutputFactoryNewInstance.createXMLEventWriter(System.out);
        xMLEventWriterCreateXMLEventWriter.add(xMLEventReaderCreateXMLEventReader);
        xMLEventWriterCreateXMLEventWriter.flush();
    }
}
