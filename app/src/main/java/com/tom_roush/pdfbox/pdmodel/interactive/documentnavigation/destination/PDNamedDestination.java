package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import java.io.IOException;

public class PDNamedDestination extends PDDestination {
    private COSBase namedDestination;

    public PDNamedDestination(COSString cOSString) {
        this.namedDestination = cOSString;
    }

    public PDNamedDestination(COSName cOSName) {
        this.namedDestination = cOSName;
    }

    public PDNamedDestination() {
    }

    public PDNamedDestination(String str) {
        this.namedDestination = new COSString(str);
    }

    @Override
    public COSBase getCOSObject() {
        return this.namedDestination;
    }

    public String getNamedDestination() {
        COSBase cOSBase = this.namedDestination;
        if (cOSBase instanceof COSString) {
            return ((COSString) cOSBase).getString();
        }
        if (cOSBase instanceof COSName) {
            return ((COSName) cOSBase).getName();
        }
        return null;
    }

    public void setNamedDestination(String str) throws IOException {
        if (str == null) {
            this.namedDestination = null;
        } else {
            this.namedDestination = new COSString(str);
        }
    }
}
