package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class PDStructureNode implements COSObjectable {
    private final COSDictionary dictionary;

    public static PDStructureNode create(COSDictionary cOSDictionary) {
        String nameAsString = cOSDictionary.getNameAsString(COSName.TYPE);
        if ("StructTreeRoot".equals(nameAsString)) {
            return new PDStructureTreeRoot(cOSDictionary);
        }
        if (nameAsString == null || PDStructureElement.TYPE.equals(nameAsString)) {
            return new PDStructureElement(cOSDictionary);
        }
        throw new IllegalArgumentException("Dictionary must not include a Type entry with a value that is neither StructTreeRoot nor StructElem.");
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    protected PDStructureNode(String str) {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, str);
    }

    protected PDStructureNode(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    public String getType() {
        return getCOSObject().getNameAsString(COSName.TYPE);
    }

    public List<Object> getKids() {
        ArrayList arrayList = new ArrayList();
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2274K);
        if (dictionaryObject instanceof COSArray) {
            Iterator<COSBase> it = ((COSArray) dictionaryObject).iterator();
            while (it.hasNext()) {
                Object objCreateObject = createObject(it.next());
                if (objCreateObject != null) {
                    arrayList.add(objCreateObject);
                }
            }
        } else {
            Object objCreateObject2 = createObject(dictionaryObject);
            if (objCreateObject2 != null) {
                arrayList.add(objCreateObject2);
            }
        }
        return arrayList;
    }

    public void setKids(List<Object> list) {
        getCOSObject().setItem(COSName.f2274K, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public void appendKid(PDStructureElement pDStructureElement) {
        appendObjectableKid(pDStructureElement);
        pDStructureElement.setParent(this);
    }

    protected void appendObjectableKid(COSObjectable cOSObjectable) {
        if (cOSObjectable == null) {
            return;
        }
        appendKid(cOSObjectable.getCOSObject());
    }

    protected void appendKid(COSBase cOSBase) {
        if (cOSBase == null) {
            return;
        }
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2274K);
        if (dictionaryObject == null) {
            getCOSObject().setItem(COSName.f2274K, cOSBase);
            return;
        }
        if (dictionaryObject instanceof COSArray) {
            ((COSArray) dictionaryObject).add(cOSBase);
            return;
        }
        COSArray cOSArray = new COSArray();
        cOSArray.add(dictionaryObject);
        cOSArray.add(cOSBase);
        getCOSObject().setItem(COSName.f2274K, (COSBase) cOSArray);
    }

    public void insertBefore(PDStructureElement pDStructureElement, Object obj) {
        insertObjectableBefore(pDStructureElement, obj);
    }

    protected void insertObjectableBefore(COSObjectable cOSObjectable, Object obj) {
        if (cOSObjectable == null) {
            return;
        }
        insertBefore(cOSObjectable.getCOSObject(), obj);
    }

    protected void insertBefore(COSBase cOSBase, Object obj) {
        COSBase dictionaryObject;
        COSBase cOSObject;
        if (cOSBase == null || obj == null || (dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2274K)) == null) {
            return;
        }
        if (obj instanceof COSObjectable) {
            cOSObject = ((COSObjectable) obj).getCOSObject();
        } else {
            cOSObject = obj instanceof COSInteger ? (COSBase) obj : null;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            cOSArray.add(cOSArray.indexOfObject(cOSObject), cOSBase.getCOSObject());
            return;
        }
        boolean zEquals = dictionaryObject.equals(cOSObject);
        if (!zEquals && (dictionaryObject instanceof COSObject)) {
            zEquals = ((COSObject) dictionaryObject).getObject().equals(cOSObject);
        }
        if (zEquals) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add(cOSBase);
            cOSArray2.add(cOSObject);
            getCOSObject().setItem(COSName.f2274K, (COSBase) cOSArray2);
        }
    }

    public boolean removeKid(PDStructureElement pDStructureElement) {
        boolean zRemoveObjectableKid = removeObjectableKid(pDStructureElement);
        if (zRemoveObjectableKid) {
            pDStructureElement.setParent(null);
        }
        return zRemoveObjectableKid;
    }

    protected boolean removeObjectableKid(COSObjectable cOSObjectable) {
        if (cOSObjectable == null) {
            return false;
        }
        return removeKid(cOSObjectable.getCOSObject());
    }

    protected boolean removeKid(COSBase cOSBase) {
        COSBase dictionaryObject;
        if (cOSBase == null || (dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2274K)) == null) {
            return false;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            boolean zRemoveObject = cOSArray.removeObject(cOSBase);
            if (cOSArray.size() == 1) {
                getCOSObject().setItem(COSName.f2274K, cOSArray.getObject(0));
            }
            return zRemoveObject;
        }
        boolean zEquals = dictionaryObject.equals(cOSBase);
        if (!zEquals && (dictionaryObject instanceof COSObject)) {
            zEquals = ((COSObject) dictionaryObject).getObject().equals(cOSBase);
        }
        if (!zEquals) {
            return false;
        }
        getCOSObject().setItem(COSName.f2274K, (COSBase) null);
        return true;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.Object createObject(com.tom_roush.pdfbox.cos.COSBase r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof com.tom_roush.pdfbox.cos.COSDictionary
            r1 = 0
            if (r0 == 0) goto L9
            r0 = r4
            com.tom_roush.pdfbox.cos.COSDictionary r0 = (com.tom_roush.pdfbox.cos.COSDictionary) r0
            goto L1c
        L9:
            boolean r0 = r4 instanceof com.tom_roush.pdfbox.cos.COSObject
            if (r0 == 0) goto L1b
            r0 = r4
            com.tom_roush.pdfbox.cos.COSObject r0 = (com.tom_roush.pdfbox.cos.COSObject) r0
            com.tom_roush.pdfbox.cos.COSBase r0 = r0.getObject()
            boolean r2 = r0 instanceof com.tom_roush.pdfbox.cos.COSDictionary
            if (r2 == 0) goto L1b
            com.tom_roush.pdfbox.cos.COSDictionary r0 = (com.tom_roush.pdfbox.cos.COSDictionary) r0
            goto L1c
        L1b:
            r0 = r1
        L1c:
            if (r0 == 0) goto L51
            com.tom_roush.pdfbox.cos.COSName r4 = com.tom_roush.pdfbox.cos.COSName.TYPE
            java.lang.String r4 = r0.getNameAsString(r4)
            if (r4 == 0) goto L4b
            java.lang.String r2 = "StructElem"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L2f
            goto L4b
        L2f:
            java.lang.String r2 = "OBJR"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L3d
            com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDObjectReference r4 = new com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDObjectReference
            r4.<init>(r0)
            return r4
        L3d:
            java.lang.String r2 = "MCR"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L60
            com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkedContentReference r4 = new com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkedContentReference
            r4.<init>(r0)
            return r4
        L4b:
            com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement r4 = new com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement
            r4.<init>(r0)
            return r4
        L51:
            boolean r0 = r4 instanceof com.tom_roush.pdfbox.cos.COSInteger
            if (r0 == 0) goto L60
            com.tom_roush.pdfbox.cos.COSInteger r4 = (com.tom_roush.pdfbox.cos.COSInteger) r4
            int r4 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            return r4
        L60:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureNode.createObject(com.tom_roush.pdfbox.cos.COSBase):java.lang.Object");
    }
}
