package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.spi.LocationInfo;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class DTDCardinal implements DTDOutput {
    public String name;
    public int type;
    public static final DTDCardinal NONE = new DTDCardinal(0, "NONE");
    public static final DTDCardinal OPTIONAL = new DTDCardinal(1, "OPTIONAL");
    public static final DTDCardinal ZEROMANY = new DTDCardinal(2, "ZEROMANY");
    public static final DTDCardinal ONEMANY = new DTDCardinal(3, "ONEMANY");

    public DTDCardinal(int i, String str) {
        this.type = i;
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DTDCardinal) && ((DTDCardinal) obj).type == this.type;
    }

    @Override
    public void write(PrintWriter printWriter) throws IOException {
        if (this == NONE) {
            return;
        }
        if (this == OPTIONAL) {
            printWriter.print(LocationInfo.f2800NA);
        } else if (this == ZEROMANY) {
            printWriter.print("*");
        } else if (this == ONEMANY) {
            printWriter.print(MqttTopic.SINGLE_LEVEL_WILDCARD);
        }
    }
}
