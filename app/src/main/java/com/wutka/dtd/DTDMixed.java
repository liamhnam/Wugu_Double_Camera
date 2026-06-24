package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class DTDMixed extends DTDContainer {
    @Override
    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("(");
        Enumeration enumerationElements = getItemsVec().elements();
        boolean z = true;
        while (enumerationElements.hasMoreElements()) {
            if (!z) {
                printWriter.print(" | ");
            }
            ((DTDItem) enumerationElements.nextElement()).write(printWriter);
            z = false;
        }
        printWriter.print(")");
        this.cardinal.write(printWriter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DTDMixed) {
            return super.equals(obj);
        }
        return false;
    }
}
