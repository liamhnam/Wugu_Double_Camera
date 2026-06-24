package com.tom_roush.pdfbox.pdmodel.interactive.form;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PDFieldTree implements Iterable<PDField> {
    private final PDAcroForm acroForm;

    public PDFieldTree(PDAcroForm pDAcroForm) {
        if (pDAcroForm == null) {
            throw new IllegalArgumentException("root cannot be null");
        }
        this.acroForm = pDAcroForm;
    }

    @Override
    public Iterator<PDField> iterator() {
        return new FieldIterator(this.acroForm);
    }

    private static final class FieldIterator implements Iterator<PDField> {
        private final Queue<PDField> queue;

        private FieldIterator(PDAcroForm pDAcroForm) {
            this.queue = new ArrayDeque();
            Iterator<PDField> it = pDAcroForm.getFields().iterator();
            while (it.hasNext()) {
                enqueueKids(it.next());
            }
        }

        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override
        public PDField next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return this.queue.poll();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void enqueueKids(PDField pDField) {
            this.queue.add(pDField);
            if (pDField instanceof PDNonTerminalField) {
                Iterator<PDField> it = ((PDNonTerminalField) pDField).getChildren().iterator();
                while (it.hasNext()) {
                    enqueueKids(it.next());
                }
            }
        }
    }
}
