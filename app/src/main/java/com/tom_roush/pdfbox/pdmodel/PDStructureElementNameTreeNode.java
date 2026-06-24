package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement;
import java.io.IOException;

public class PDStructureElementNameTreeNode extends PDNameTreeNode<PDStructureElement> {
    public PDStructureElementNameTreeNode() {
    }

    public PDStructureElementNameTreeNode(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Override
    public PDStructureElement convertCOSToPD(COSBase cOSBase) throws IOException {
        return new PDStructureElement((COSDictionary) cOSBase);
    }

    @Override
    protected PDNameTreeNode createChildNode(COSDictionary cOSDictionary) {
        return new PDStructureElementNameTreeNode(cOSDictionary);
    }
}
