package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.Comment;

public class CommentEvent extends CharactersEvent implements Comment {
    public CommentEvent() {
        init();
    }

    public CommentEvent(String str) {
        init();
        setData(str);
    }

    @Override
    protected void init() {
        setEventType(5);
    }

    @Override
    public String getText() {
        return getData();
    }

    @Override
    protected void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<!--");
        String text = getText();
        if (text.length() > 0) {
            writer.write(text);
        }
        writer.write("-->");
    }
}
