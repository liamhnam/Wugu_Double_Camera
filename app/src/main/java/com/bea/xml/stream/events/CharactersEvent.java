package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.Characters;

public class CharactersEvent extends BaseEvent implements Characters {
    private String data;
    private boolean isCData;
    private boolean isIgnorable;
    private boolean isSpace;

    public CharactersEvent() {
        this.isCData = false;
        this.isSpace = false;
        this.isIgnorable = false;
        init();
    }

    public CharactersEvent(String str) {
        this.isCData = false;
        this.isSpace = false;
        this.isIgnorable = false;
        init();
        setData(str);
    }

    public CharactersEvent(String str, boolean z) {
        this.isCData = false;
        this.isSpace = false;
        this.isIgnorable = false;
        init();
        setData(str);
        this.isCData = z;
    }

    public void setSpace(boolean z) {
        this.isSpace = z;
    }

    @Override
    public boolean isWhiteSpace() {
        return this.isSpace;
    }

    @Override
    public boolean isIgnorableWhiteSpace() {
        return this.isIgnorable;
    }

    public void setIgnorable(boolean z) {
        this.isIgnorable = z;
    }

    protected void init() {
        setEventType(4);
    }

    @Override
    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public boolean hasData() {
        return this.data != null;
    }

    @Override
    public boolean isCData() {
        return this.isCData;
    }

    public char[] getDataAsArray() {
        return this.data.toCharArray();
    }

    @Override
    protected void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        char cCharAt;
        if (this.isCData) {
            writer.write("<![CDATA[");
            writer.write(getData());
            writer.write("]]>");
            return;
        }
        String data = getData();
        int length = data.length();
        if (length > 0) {
            int i = 0;
            while (i < length && (cCharAt = data.charAt(i)) != '&' && cCharAt != '<' && cCharAt != '>') {
                i++;
            }
            if (i == length) {
                writer.write(data);
                return;
            }
            if (i > 0) {
                writer.write(data, 0, i);
            }
            while (i < length) {
                char cCharAt2 = data.charAt(i);
                if (cCharAt2 == '&') {
                    writer.write("&amp;");
                } else if (cCharAt2 == '<') {
                    writer.write("&lt;");
                } else if (cCharAt2 == '>') {
                    writer.write("&gt;");
                } else {
                    writer.write(cCharAt2);
                }
                i++;
            }
        }
    }
}
