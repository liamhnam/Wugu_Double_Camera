package com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation;

import androidx.exifinterface.media.ExifInterface;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;

public class PDThreadBead implements COSObjectable {
    private final COSDictionary bead;

    public PDThreadBead(COSDictionary cOSDictionary) {
        this.bead = cOSDictionary;
    }

    public PDThreadBead() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.bead = cOSDictionary;
        cOSDictionary.setName("Type", "Bead");
        setNextBead(this);
        setPreviousBead(this);
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.bead;
    }

    public PDThread getThread() {
        COSDictionary cOSDictionary = (COSDictionary) this.bead.getDictionaryObject("T");
        if (cOSDictionary != null) {
            return new PDThread(cOSDictionary);
        }
        return null;
    }

    public void setThread(PDThread pDThread) {
        this.bead.setItem("T", pDThread);
    }

    public PDThreadBead getNextBead() {
        return new PDThreadBead((COSDictionary) this.bead.getDictionaryObject("N"));
    }

    protected final void setNextBead(PDThreadBead pDThreadBead) {
        this.bead.setItem("N", pDThreadBead);
    }

    public PDThreadBead getPreviousBead() {
        return new PDThreadBead((COSDictionary) this.bead.getDictionaryObject(ExifInterface.GPS_MEASUREMENT_INTERRUPTED));
    }

    protected final void setPreviousBead(PDThreadBead pDThreadBead) {
        this.bead.setItem(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, pDThreadBead);
    }

    public void appendBead(PDThreadBead pDThreadBead) {
        PDThreadBead nextBead = getNextBead();
        nextBead.setPreviousBead(pDThreadBead);
        pDThreadBead.setNextBead(nextBead);
        setNextBead(pDThreadBead);
        pDThreadBead.setPreviousBead(this);
    }

    public PDPage getPage() {
        COSDictionary cOSDictionary = (COSDictionary) this.bead.getDictionaryObject("P");
        if (cOSDictionary != null) {
            return new PDPage(cOSDictionary);
        }
        return null;
    }

    public void setPage(PDPage pDPage) {
        this.bead.setItem("P", pDPage);
    }

    public PDRectangle getRectangle() {
        COSArray cOSArray = (COSArray) this.bead.getDictionaryObject(COSName.f2296R);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setRectangle(PDRectangle pDRectangle) {
        this.bead.setItem(COSName.f2296R, pDRectangle);
    }
}
