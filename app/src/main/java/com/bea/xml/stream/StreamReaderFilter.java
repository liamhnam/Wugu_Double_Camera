package com.bea.xml.stream;

import com.bea.xml.stream.filters.NameFilter;
import com.bea.xml.stream.filters.TypeFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import javax.xml.namespace.QName;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StreamReaderFilter extends ReaderDelegate {
    private StreamFilter filter;

    public StreamReaderFilter(XMLStreamReader xMLStreamReader) {
        super(xMLStreamReader);
    }

    public StreamReaderFilter(XMLStreamReader xMLStreamReader, StreamFilter streamFilter) {
        super(xMLStreamReader);
        setFilter(streamFilter);
    }

    public void setFilter(StreamFilter streamFilter) {
        this.filter = streamFilter;
    }

    @Override
    public int next() throws XMLStreamException {
        if (hasNext()) {
            return super.next();
        }
        throw new IllegalStateException("next() may not be called  when there are no more  items to return");
    }

    @Override
    public boolean hasNext() throws XMLStreamException {
        while (super.hasNext()) {
            if (this.filter.accept(getDelegate())) {
                return true;
            }
            super.next();
        }
        return false;
    }

    public static void main(String[] strArr) throws Exception {
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        XMLInputFactory xMLInputFactoryNewInstance = XMLInputFactory.newInstance();
        TypeFilter typeFilter = new TypeFilter();
        typeFilter.addType(1);
        typeFilter.addType(2);
        XMLStreamReader xMLStreamReaderCreateFilteredReader = xMLInputFactoryNewInstance.createFilteredReader(xMLInputFactoryNewInstance.createXMLStreamReader(new FileReader(strArr[0])), typeFilter);
        while (xMLStreamReaderCreateFilteredReader.hasNext()) {
            System.out.println(xMLStreamReaderCreateFilteredReader.getLocalName());
            xMLStreamReaderCreateFilteredReader.next();
        }
        XMLStreamReader xMLStreamReaderCreateFilteredReader2 = xMLInputFactoryNewInstance.createFilteredReader(xMLInputFactoryNewInstance.createXMLStreamReader(new FileReader(strArr[0])), new NameFilter(new QName("banana", "B")));
        XMLStreamRecorder xMLStreamRecorder = new XMLStreamRecorder(new OutputStreamWriter(new FileOutputStream("out.stream")));
        while (xMLStreamReaderCreateFilteredReader2.hasNext()) {
            xMLStreamRecorder.write(xMLStreamReaderCreateFilteredReader2);
            xMLStreamReaderCreateFilteredReader2.next();
        }
        xMLStreamRecorder.flush();
    }
}
