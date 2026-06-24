package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;

public class DTDAny extends DTDItem {
    @Override
    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("ANY");
        this.cardinal.write(printWriter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DTDAny) {
            return super.equals(obj);
        }
        return false;
    }
}
