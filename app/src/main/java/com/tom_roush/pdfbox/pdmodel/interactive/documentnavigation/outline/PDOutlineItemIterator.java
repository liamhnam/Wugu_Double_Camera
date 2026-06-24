package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline;

import java.util.Iterator;

class PDOutlineItemIterator implements Iterator<PDOutlineItem> {
    private PDOutlineItem currentItem;
    private final PDOutlineItem startingItem;

    PDOutlineItemIterator(PDOutlineItem pDOutlineItem) {
        this.startingItem = pDOutlineItem;
    }

    @Override
    public boolean hasNext() {
        PDOutlineItem pDOutlineItem;
        return this.startingItem != null && ((pDOutlineItem = this.currentItem) == null || !(pDOutlineItem.getNextSibling() == null || this.startingItem.equals(this.currentItem.getNextSibling())));
    }

    @Override
    public PDOutlineItem next() {
        PDOutlineItem pDOutlineItem = this.currentItem;
        if (pDOutlineItem == null) {
            this.currentItem = this.startingItem;
        } else {
            this.currentItem = pDOutlineItem.getNextSibling();
        }
        return this.currentItem;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
