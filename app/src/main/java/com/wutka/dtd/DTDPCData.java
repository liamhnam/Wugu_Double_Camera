package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public class DTDPCData extends DTDItem {
    @Override
    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("#PCDATA");
        this.cardinal.write(printWriter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DTDPCData) {
            return super.equals(obj);
        }
        return false;
    }
}
