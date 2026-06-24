package com.wutka.dtd;

import java.io.PrintWriter;

public class DTDSystem extends DTDExternalID {
    @Override
    public void write(PrintWriter printWriter) {
        if (this.system != null) {
            printWriter.print("SYSTEM \"");
            printWriter.print(this.system);
            printWriter.print("\"");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DTDSystem) {
            return super.equals(obj);
        }
        return false;
    }
}
