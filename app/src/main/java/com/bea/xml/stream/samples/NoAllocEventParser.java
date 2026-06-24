package com.bea.xml.stream.samples;

import com.bea.xml.stream.StaticAllocator;
import java.io.FileReader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

public class NoAllocEventParser {
    private static String filename;

    private static void printUsage() {
        System.out.println("usage: java com.bea.xml.stream.samples.EventParse <xmlfile>");
    }

    public static void main(String[] strArr) throws Exception {
        try {
            filename = strArr[0];
        } catch (ArrayIndexOutOfBoundsException unused) {
            printUsage();
            System.exit(0);
        }
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.bea.xml.stream.EventFactory");
        XMLInputFactory xMLInputFactoryNewInstance = XMLInputFactory.newInstance();
        xMLInputFactoryNewInstance.setEventAllocator(new StaticAllocator());
        XMLEventReader xMLEventReaderCreateXMLEventReader = xMLInputFactoryNewInstance.createXMLEventReader(new FileReader(filename));
        while (xMLEventReaderCreateXMLEventReader.hasNext()) {
            XMLEvent xMLEventNextEvent = xMLEventReaderCreateXMLEventReader.nextEvent();
            System.out.println(new StringBuffer("ID:").append(xMLEventNextEvent.hashCode()).append("[").append(xMLEventNextEvent).append("]").toString());
        }
    }
}
