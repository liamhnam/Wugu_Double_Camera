package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.EntityDeclaration;

public class EntityDeclarationEvent extends BaseEvent implements EntityDeclaration {
    protected final String name;
    protected final String replacementText;

    @Override
    public String getBaseURI() {
        return null;
    }

    @Override
    public String getNotationName() {
        return null;
    }

    @Override
    public String getPublicId() {
        return null;
    }

    @Override
    public String getSystemId() {
        return null;
    }

    public EntityDeclarationEvent(String str, String str2) {
        super(15);
        this.name = str;
        this.replacementText = str2;
    }

    @Override
    public String getReplacementText() {
        return this.replacementText;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    protected void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<!ENTITY ");
        writer.write(getName());
        writer.write(34);
        writer.write(getReplacementText());
        writer.write("\">");
    }
}
