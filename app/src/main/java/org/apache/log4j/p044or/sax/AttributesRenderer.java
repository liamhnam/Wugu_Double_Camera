package org.apache.log4j.p044or.sax;

import org.apache.log4j.p044or.ObjectRenderer;
import org.xml.sax.Attributes;

public class AttributesRenderer implements ObjectRenderer {
    @Override
    public String doRender(Object obj) {
        if (obj instanceof Attributes) {
            StringBuffer stringBuffer = new StringBuffer();
            Attributes attributes = (Attributes) obj;
            int length = attributes.getLength();
            boolean z = true;
            for (int i = 0; i < length; i++) {
                if (z) {
                    z = false;
                } else {
                    stringBuffer.append(", ");
                }
                stringBuffer.append(attributes.getQName(i));
                stringBuffer.append('=');
                stringBuffer.append(attributes.getValue(i));
            }
            return stringBuffer.toString();
        }
        try {
            return obj.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
