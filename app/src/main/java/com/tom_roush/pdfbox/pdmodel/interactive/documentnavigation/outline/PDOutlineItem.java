package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline;

import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAction;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionFactory;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination;
import java.io.IOException;

public final class PDOutlineItem extends PDOutlineNode {
    private static final int BOLD_FLAG = 2;
    private static final int ITALIC_FLAG = 1;

    public PDOutlineItem() {
    }

    public PDOutlineItem(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void insertSiblingAfter(PDOutlineItem pDOutlineItem) {
        requireSingleNode(pDOutlineItem);
        PDOutlineNode parent = getParent();
        pDOutlineItem.setParent(parent);
        PDOutlineItem nextSibling = getNextSibling();
        setNextSibling(pDOutlineItem);
        pDOutlineItem.setPreviousSibling(this);
        if (nextSibling != null) {
            pDOutlineItem.setNextSibling(nextSibling);
            nextSibling.setPreviousSibling(pDOutlineItem);
        } else if (parent != null) {
            getParent().setLastChild(pDOutlineItem);
        }
        updateParentOpenCountForAddedChild(pDOutlineItem);
    }

    public void insertSiblingBefore(PDOutlineItem pDOutlineItem) {
        requireSingleNode(pDOutlineItem);
        PDOutlineNode parent = getParent();
        pDOutlineItem.setParent(parent);
        PDOutlineItem previousSibling = getPreviousSibling();
        setPreviousSibling(pDOutlineItem);
        pDOutlineItem.setNextSibling(this);
        if (previousSibling != null) {
            previousSibling.setNextSibling(pDOutlineItem);
            pDOutlineItem.setPreviousSibling(previousSibling);
        } else if (parent != null) {
            getParent().setFirstChild(pDOutlineItem);
        }
        updateParentOpenCountForAddedChild(pDOutlineItem);
    }

    public PDOutlineItem getPreviousSibling() {
        return getOutlineItem(COSName.PREV);
    }

    void setPreviousSibling(PDOutlineNode pDOutlineNode) {
        getCOSObject().setItem(COSName.PREV, pDOutlineNode);
    }

    public PDOutlineItem getNextSibling() {
        return getOutlineItem(COSName.NEXT);
    }

    void setNextSibling(PDOutlineNode pDOutlineNode) {
        getCOSObject().setItem(COSName.NEXT, pDOutlineNode);
    }

    public String getTitle() {
        return getCOSObject().getString(COSName.TITLE);
    }

    public void setTitle(String str) {
        getCOSObject().setString(COSName.TITLE, str);
    }

    public PDDestination getDestination() throws IOException {
        return PDDestination.create(getCOSObject().getDictionaryObject(COSName.DEST));
    }

    public void setDestination(PDDestination pDDestination) {
        getCOSObject().setItem(COSName.DEST, pDDestination);
    }

    public void setDestination(PDPage pDPage) {
        PDPageXYZDestination pDPageXYZDestination;
        if (pDPage != null) {
            pDPageXYZDestination = new PDPageXYZDestination();
            pDPageXYZDestination.setPage(pDPage);
        } else {
            pDPageXYZDestination = null;
        }
        setDestination(pDPageXYZDestination);
    }

    public PDPage findDestinationPage(PDDocument pDDocument) throws IOException {
        PDPageDestination pDPageDestinationFindNamedDestinationPage;
        int pageNumber;
        PDDestination destination = getDestination();
        if (destination == null) {
            PDAction action = getAction();
            if (action instanceof PDActionGoTo) {
                destination = ((PDActionGoTo) action).getDestination();
            }
        }
        if (destination == null) {
            return null;
        }
        if (destination instanceof PDNamedDestination) {
            pDPageDestinationFindNamedDestinationPage = pDDocument.getDocumentCatalog().findNamedDestinationPage((PDNamedDestination) destination);
            if (pDPageDestinationFindNamedDestinationPage == null) {
                return null;
            }
        } else if (destination instanceof PDPageDestination) {
            pDPageDestinationFindNamedDestinationPage = (PDPageDestination) destination;
        } else {
            throw new IOException("Error: Unknown destination type " + destination);
        }
        PDPage page = pDPageDestinationFindNamedDestinationPage.getPage();
        return (page != null || (pageNumber = pDPageDestinationFindNamedDestinationPage.getPageNumber()) == -1) ? page : pDDocument.getPage(pageNumber);
    }

    public PDAction getAction() {
        return PDActionFactory.createAction((COSDictionary) getCOSObject().getDictionaryObject(COSName.f2228A));
    }

    public void setAction(PDAction pDAction) {
        getCOSObject().setItem(COSName.f2228A, pDAction);
    }

    public PDStructureElement getStructureElement() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.f2303SE);
        if (cOSDictionary != null) {
            return new PDStructureElement(cOSDictionary);
        }
        return null;
    }

    public void setStructureElement(PDStructureElement pDStructureElement) {
        getCOSObject().setItem(COSName.f2303SE, pDStructureElement);
    }

    public PDColor getTextColor() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2238C);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.growToSize(3, new COSFloat(0.0f));
            getCOSObject().setItem(COSName.f2238C, (COSBase) cOSArray);
        }
        return new PDColor(cOSArray, PDDeviceRGB.INSTANCE);
    }

    public void setTextColor(PDColor pDColor) {
        getCOSObject().setItem(COSName.f2238C, (COSBase) pDColor.toCOSArray());
    }

    public void setTextColor(AWTColor aWTColor) {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) new COSFloat(aWTColor.getRed() / 255.0f));
        cOSArray.add((COSBase) new COSFloat(aWTColor.getGreen() / 255.0f));
        cOSArray.add((COSBase) new COSFloat(aWTColor.getBlue() / 255.0f));
        getCOSObject().setItem(COSName.f2238C, (COSBase) cOSArray);
    }

    public boolean isItalic() {
        return getCOSObject().getFlag(COSName.f2260F, 1);
    }

    public void setItalic(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 1, z);
    }

    public boolean isBold() {
        return getCOSObject().getFlag(COSName.f2260F, 2);
    }

    public void setBold(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 2, z);
    }
}
