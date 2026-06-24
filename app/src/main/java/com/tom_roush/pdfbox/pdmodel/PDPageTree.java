package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class PDPageTree implements COSObjectable, Iterable<PDPage> {
    private final PDDocument document;
    private final COSDictionary root;

    public PDPageTree() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.root = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.PAGES);
        cOSDictionary.setItem(COSName.KIDS, (COSBase) new COSArray());
        cOSDictionary.setItem(COSName.COUNT, (COSBase) COSInteger.ZERO);
        this.document = null;
    }

    public PDPageTree(COSDictionary cOSDictionary) {
        this(cOSDictionary, null);
    }

    PDPageTree(COSDictionary cOSDictionary, PDDocument pDDocument) {
        if (cOSDictionary == null) {
            throw new IllegalArgumentException("root cannot be null");
        }
        if (COSName.PAGE.equals(cOSDictionary.getCOSName(COSName.TYPE))) {
            COSArray cOSArray = new COSArray();
            cOSArray.add((COSBase) cOSDictionary);
            COSDictionary cOSDictionary2 = new COSDictionary();
            this.root = cOSDictionary2;
            cOSDictionary2.setItem(COSName.KIDS, (COSBase) cOSArray);
            cOSDictionary2.setInt(COSName.COUNT, 1);
        } else {
            this.root = cOSDictionary;
        }
        this.document = pDDocument;
    }

    public static COSBase getInheritableAttribute(COSDictionary cOSDictionary, COSName cOSName) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(cOSName);
        if (dictionaryObject != null) {
            return dictionaryObject;
        }
        COSDictionary cOSDictionary2 = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.f2292P);
        if (cOSDictionary2 != null) {
            return getInheritableAttribute(cOSDictionary2, cOSName);
        }
        return null;
    }

    @Override
    public Iterator<PDPage> iterator() {
        return new PageIterator(this.root);
    }

    public List<COSDictionary> getKids(COSDictionary cOSDictionary) {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            return arrayList;
        }
        int size = cOSArray.size();
        for (int i = 0; i < size; i++) {
            arrayList.add((COSDictionary) cOSArray.getObject(i));
        }
        return arrayList;
    }

    private final class PageIterator implements Iterator<PDPage> {
        private final Queue<COSDictionary> queue;

        private PageIterator(COSDictionary cOSDictionary) {
            this.queue = new ArrayDeque();
            enqueueKids(cOSDictionary);
        }

        private void enqueueKids(COSDictionary cOSDictionary) {
            if (PDPageTree.this.isPageTreeNode(cOSDictionary)) {
                Iterator it = PDPageTree.this.getKids(cOSDictionary).iterator();
                while (it.hasNext()) {
                    enqueueKids((COSDictionary) it.next());
                }
                return;
            }
            this.queue.add(cOSDictionary);
        }

        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override
        public PDPage next() {
            COSDictionary cOSDictionaryPoll = this.queue.poll();
            PDPageTree.sanitizeType(cOSDictionaryPoll);
            return new PDPage(cOSDictionaryPoll, PDPageTree.this.document != null ? PDPageTree.this.document.getResourceCache() : null);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public PDPage get(int i) {
        COSDictionary cOSDictionary = get(i + 1, this.root, 0);
        sanitizeType(cOSDictionary);
        PDDocument pDDocument = this.document;
        return new PDPage(cOSDictionary, pDDocument != null ? pDDocument.getResourceCache() : null);
    }

    public static void sanitizeType(COSDictionary cOSDictionary) {
        COSName cOSName = cOSDictionary.getCOSName(COSName.TYPE);
        if (cOSName == null) {
            cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.PAGE);
        } else if (!COSName.PAGE.equals(cOSName)) {
            throw new IllegalStateException("Expected 'Page' but found " + cOSName);
        }
    }

    private COSDictionary get(int i, COSDictionary cOSDictionary, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        if (!isPageTreeNode(cOSDictionary)) {
            if (i2 == i) {
                return cOSDictionary;
            }
            throw new IllegalStateException();
        }
        if (i <= cOSDictionary.getInt(COSName.COUNT, 0) + i2) {
            for (COSDictionary cOSDictionary2 : getKids(cOSDictionary)) {
                if (isPageTreeNode(cOSDictionary2)) {
                    int i3 = cOSDictionary2.getInt(COSName.COUNT, 0) + i2;
                    if (i <= i3) {
                        return get(i, cOSDictionary2, i2);
                    }
                    i2 = i3;
                } else {
                    i2++;
                    if (i == i2) {
                        return get(i, cOSDictionary2, i2);
                    }
                }
            }
            throw new IllegalStateException();
        }
        throw new IndexOutOfBoundsException("Index out of bounds: " + i);
    }

    public boolean isPageTreeNode(COSDictionary cOSDictionary) {
        return cOSDictionary.getCOSName(COSName.TYPE) == COSName.PAGES || cOSDictionary.containsKey(COSName.KIDS);
    }

    public int indexOf(PDPage pDPage) {
        SearchContext searchContext = new SearchContext(pDPage);
        if (findPage(searchContext, this.root)) {
            return searchContext.index;
        }
        return -1;
    }

    private boolean findPage(SearchContext searchContext, COSDictionary cOSDictionary) {
        for (COSDictionary cOSDictionary2 : getKids(cOSDictionary)) {
            if (searchContext.found) {
                break;
            }
            if (isPageTreeNode(cOSDictionary2)) {
                findPage(searchContext, cOSDictionary2);
            } else {
                searchContext.visitPage(cOSDictionary2);
            }
        }
        return searchContext.found;
    }

    private static final class SearchContext {
        private boolean found;
        private int index;
        private final COSDictionary searched;

        private SearchContext(PDPage pDPage) {
            this.index = -1;
            this.searched = pDPage.getCOSObject();
        }

        public void visitPage(COSDictionary cOSDictionary) {
            this.index++;
            this.found = this.searched.equals(cOSDictionary);
        }
    }

    public int getCount() {
        return this.root.getInt(COSName.COUNT, 0);
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.root;
    }

    public void remove(int i) {
        remove(get(i + 1, this.root, 0));
    }

    public void remove(PDPage pDPage) {
        remove(pDPage.getCOSObject());
    }

    private void remove(COSDictionary cOSDictionary) {
        if (((COSArray) ((COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.f2292P)).getDictionaryObject(COSName.KIDS)).removeObject(cOSDictionary)) {
            do {
                cOSDictionary = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.f2292P);
                if (cOSDictionary != null) {
                    cOSDictionary.setInt(COSName.COUNT, cOSDictionary.getInt(COSName.COUNT) - 1);
                }
            } while (cOSDictionary != null);
        }
    }

    public void add(PDPage pDPage) {
        COSDictionary cOSObject = pDPage.getCOSObject();
        cOSObject.setItem(COSName.PARENT, (COSBase) this.root);
        ((COSArray) this.root.getDictionaryObject(COSName.KIDS)).add((COSBase) cOSObject);
        do {
            cOSObject = (COSDictionary) cOSObject.getDictionaryObject(COSName.PARENT, COSName.f2292P);
            if (cOSObject != null) {
                cOSObject.setInt(COSName.COUNT, cOSObject.getInt(COSName.COUNT) + 1);
            }
        } while (cOSObject != null);
    }

    public void insertBefore(PDPage pDPage, PDPage pDPage2) {
        COSDictionary cOSDictionary = (COSDictionary) pDPage2.getCOSObject().getDictionaryObject(COSName.PARENT);
        COSArray cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.KIDS);
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= cOSArray.size()) {
                break;
            }
            if (((COSDictionary) cOSArray.getObject(i)).equals(pDPage2.getCOSObject())) {
                cOSArray.add(i, pDPage.getCOSObject());
                pDPage.getCOSObject().setItem(COSName.PARENT, (COSBase) cOSDictionary);
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            throw new IllegalArgumentException("attempted to insert before orphan page");
        }
        increaseParents(cOSDictionary);
    }

    public void insertAfter(PDPage pDPage, PDPage pDPage2) {
        COSDictionary cOSDictionary = (COSDictionary) pDPage2.getCOSObject().getDictionaryObject(COSName.PARENT);
        COSArray cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.KIDS);
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= cOSArray.size()) {
                break;
            }
            if (((COSDictionary) cOSArray.getObject(i)).equals(pDPage2.getCOSObject())) {
                z = true;
                cOSArray.add(i + 1, pDPage.getCOSObject());
                pDPage.getCOSObject().setItem(COSName.PARENT, (COSBase) cOSDictionary);
                break;
            }
            i++;
        }
        if (!z) {
            throw new IllegalArgumentException("attempted to insert before orphan page");
        }
        increaseParents(cOSDictionary);
    }

    private void increaseParents(COSDictionary cOSDictionary) {
        do {
            cOSDictionary.setInt(COSName.COUNT, cOSDictionary.getInt(COSName.COUNT) + 1);
            cOSDictionary = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT);
        } while (cOSDictionary != null);
    }
}
