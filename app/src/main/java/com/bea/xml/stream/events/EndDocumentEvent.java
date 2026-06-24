package com.bea.xml.stream.events;

import java.io.Writer;
import javax.xml.stream.events.EndDocument;

public class EndDocumentEvent extends BaseEvent implements EndDocument {
    @Override
    protected void doWriteAsEncodedUnicode(Writer writer) {
    }

    @Override
    public String toString() {
        return "<? EndDocument ?>";
    }

    public EndDocumentEvent() {
        init();
    }

    protected void init() {
        setEventType(8);
    }
}
