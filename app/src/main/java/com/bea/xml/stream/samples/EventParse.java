package com.bea.xml.stream.samples;

import com.bea.xml.stream.XMLEventAllocatorBase;
import com.bea.xml.stream.util.ElementTypeNames;
import java.io.FileReader;
import java.util.Iterator;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;

public class EventParse {
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
        XMLInputFactory xMLInputFactoryNewInstance = XMLInputFactory.newInstance();
        System.out.println(new StringBuffer("FACTORY: ").append(xMLInputFactoryNewInstance).toString());
        xMLInputFactoryNewInstance.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
        XMLStreamReader xMLStreamReaderCreateXMLStreamReader = xMLInputFactoryNewInstance.createXMLStreamReader(new FileReader(filename));
        System.out.println(new StringBuffer("READER:  ").append(xMLStreamReaderCreateXMLStreamReader).append("\n").toString());
        while (xMLStreamReaderCreateXMLStreamReader.hasNext()) {
            printEvent(xMLStreamReaderCreateXMLStreamReader);
            xMLStreamReaderCreateXMLStreamReader.next();
        }
    }

    public static final String getEventTypeString(int i) {
        return ElementTypeNames.getEventTypeString(i);
    }

    private static void printEvent(XMLStreamReader xMLStreamReader) {
        System.out.print(new StringBuffer("EVENT:[").append(xMLStreamReader.getLocation().getLineNumber()).append("][").append(xMLStreamReader.getLocation().getColumnNumber()).append("] ").toString());
        System.out.print(getEventTypeString(xMLStreamReader.getEventType()));
        System.out.print(" [");
        int eventType = xMLStreamReader.getEventType();
        if (eventType == 9) {
            System.out.print(new StringBuffer().append(xMLStreamReader.getLocalName()).append("=").toString());
            if (xMLStreamReader.hasText()) {
                System.out.print(new StringBuffer("[").append(xMLStreamReader.getText()).append("]").toString());
            }
        } else if (eventType != 12) {
            switch (eventType) {
                case 1:
                    System.out.print("<");
                    printName(xMLStreamReader);
                    printNamespaces(XMLEventAllocatorBase.getNamespaces(xMLStreamReader));
                    printAttributes(xMLStreamReader);
                    System.out.print(">");
                    break;
                case 2:
                    System.out.print("</");
                    printName(xMLStreamReader);
                    printNamespaces(XMLEventAllocatorBase.getNamespaces(xMLStreamReader));
                    System.out.print(">");
                    break;
                case 3:
                    System.out.print("<?");
                    if (xMLStreamReader.hasText()) {
                        System.out.print(xMLStreamReader.getText());
                    }
                    System.out.print("?>");
                    break;
                case 4:
                case 6:
                    System.out.print(new String(xMLStreamReader.getTextCharacters(), xMLStreamReader.getTextStart(), xMLStreamReader.getTextLength()));
                    break;
                case 5:
                    System.out.print("<!--");
                    if (xMLStreamReader.hasText()) {
                        System.out.print(xMLStreamReader.getText());
                    }
                    System.out.print("-->");
                    break;
                case 7:
                    System.out.print("<?xml");
                    System.out.print(new StringBuffer(" version='").append(xMLStreamReader.getVersion()).append("'").toString());
                    System.out.print(new StringBuffer(" encoding='").append(xMLStreamReader.getCharacterEncodingScheme()).append("'").toString());
                    if (xMLStreamReader.isStandalone()) {
                        System.out.print(" standalone='yes'");
                    } else {
                        System.out.print(" standalone='no'");
                    }
                    System.out.print("?>");
                    break;
            }
        } else {
            System.out.print("<![CDATA[");
            if (xMLStreamReader.hasText()) {
                System.out.print(xMLStreamReader.getText());
            }
            System.out.print("]]>");
        }
        System.out.println("]");
    }

    private static void printEventType(int i) {
        System.out.print(new StringBuffer("EVENT TYPE(").append(i).append("):").toString());
        System.out.println(getEventTypeString(i));
    }

    private static void printName(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.hasName()) {
            printName(xMLStreamReader.getPrefix(), xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName());
        }
    }

    private static void printName(String str, String str2, String str3) {
        if (str2 != null && !"".equals(str2)) {
            System.out.print(new StringBuffer("['").append(str2).append("']:").toString());
        }
        if (str != null) {
            System.out.print(new StringBuffer().append(str).append(":").toString());
        }
        if (str3 != null) {
            System.out.print(str3);
        }
    }

    private static void printValue(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.hasText()) {
            System.out.println(new StringBuffer("HAS VALUE: ").append(xMLStreamReader.getText()).toString());
        } else {
            System.out.println("HAS NO VALUE");
        }
    }

    private static void printAttributes(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.getAttributeCount() > 0) {
            Iterator attributes = XMLEventAllocatorBase.getAttributes(xMLStreamReader);
            while (attributes.hasNext()) {
                System.out.print(" ");
                printAttribute((Attribute) attributes.next());
            }
        }
    }

    private static void printAttribute(Attribute attribute) {
        printName(attribute.getName().getPrefix(), attribute.getName().getNamespaceURI(), attribute.getName().getLocalPart());
        System.out.print(new StringBuffer("='").append(attribute.getValue()).append("'").toString());
    }

    private static void printNamespaces(Iterator it) {
        while (it.hasNext()) {
            System.out.print(" ");
            printNamespace((Namespace) it.next());
        }
    }

    private static void printNamespace(Namespace namespace) {
        if (namespace.isDefaultNamespaceDeclaration()) {
            System.out.print(new StringBuffer("xmlns='").append(namespace.getNamespaceURI()).append("'").toString());
        } else {
            System.out.print(new StringBuffer("xmlns:").append(namespace.getPrefix()).append("='").append(namespace.getNamespaceURI()).append("'").toString());
        }
    }
}
