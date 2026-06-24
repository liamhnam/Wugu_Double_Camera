package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionFactory;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import java.io.IOException;

public class PDJavascriptNameTreeNode extends PDNameTreeNode<PDActionJavaScript> {
    public PDJavascriptNameTreeNode() {
    }

    public PDJavascriptNameTreeNode(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Override
    public PDActionJavaScript convertCOSToPD(COSBase cOSBase) throws IOException {
        if (!(cOSBase instanceof COSDictionary)) {
            throw new IOException("Error creating Javascript object, expected a COSDictionary and not " + cOSBase);
        }
        return (PDActionJavaScript) PDActionFactory.createAction((COSDictionary) cOSBase);
    }

    @Override
    protected PDNameTreeNode createChildNode(COSDictionary cOSDictionary) {
        return new PDJavascriptNameTreeNode(cOSDictionary);
    }
}
