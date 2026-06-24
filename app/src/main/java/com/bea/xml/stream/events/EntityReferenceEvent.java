package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;

public class EntityReferenceEvent extends BaseEvent implements EntityReference {

    private EntityDeclaration f468ed;
    private String name;
    private String replacementText;

    public String getBaseURI() {
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

    public EntityReferenceEvent() {
        init();
    }

    public EntityReferenceEvent(String str, EntityDeclaration entityDeclaration) {
        init();
        this.name = str;
        this.f468ed = entityDeclaration;
    }

    public String getReplacementText() {
        return this.f468ed.getReplacementText();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setReplacementText(String str) {
        this.replacementText = str;
    }

    @Override
    public EntityDeclaration getDeclaration() {
        return this.f468ed;
    }

    protected void init() {
        setEventType(9);
    }

    @Override
    protected void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write(38);
        writer.write(getName());
        writer.write(59);
    }

    @Override
    public String toString() {
        String replacementText = getReplacementText();
        if (replacementText == null) {
            replacementText = "";
        }
        return new StringBuffer("&").append(getName()).append(":='").append(replacementText).append("'").toString();
    }
}
