package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageTree;

public abstract class PDPageDestination extends PDDestination {
    protected COSArray array;

    protected PDPageDestination() {
        this.array = new COSArray();
    }

    protected PDPageDestination(COSArray cOSArray) {
        this.array = cOSArray;
    }

    public PDPage getPage() {
        if (this.array.size() > 0) {
            COSBase object = this.array.getObject(0);
            if (object instanceof COSDictionary) {
                return new PDPage((COSDictionary) object);
            }
        }
        return null;
    }

    public void setPage(PDPage pDPage) {
        this.array.set(0, pDPage);
    }

    public int getPageNumber() {
        if (this.array.size() > 0) {
            COSBase object = this.array.getObject(0);
            if (object instanceof COSNumber) {
                return ((COSNumber) object).intValue();
            }
        }
        return -1;
    }

    @Deprecated
    public int findPageNumber() {
        if (this.array.size() > 0) {
            COSBase object = this.array.getObject(0);
            if (object instanceof COSNumber) {
                return ((COSNumber) object).intValue();
            }
            if (object instanceof COSDictionary) {
                COSBase dictionaryObject = object;
                while (true) {
                    COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
                    if (cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.f2292P) != null) {
                        dictionaryObject = cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.f2292P);
                    } else {
                        return new PDPageTree(cOSDictionary).indexOf(new PDPage((COSDictionary) object)) + 1;
                    }
                }
            }
        }
        return -1;
    }

    public int retrievePageNumber() {
        if (this.array.size() > 0) {
            COSBase object = this.array.getObject(0);
            if (object instanceof COSNumber) {
                return ((COSNumber) object).intValue();
            }
            if (object instanceof COSDictionary) {
                COSBase dictionaryObject = object;
                while (true) {
                    COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
                    if (cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.f2292P) != null) {
                        dictionaryObject = cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.f2292P);
                    } else {
                        return new PDPageTree(cOSDictionary).indexOf(new PDPage((COSDictionary) object));
                    }
                }
            }
        }
        return -1;
    }

    public void setPageNumber(int i) {
        this.array.set(0, i);
    }

    @Override
    public COSArray getCOSObject() {
        return this.array;
    }
}
