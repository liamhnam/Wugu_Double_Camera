package com.tom_roush.pdfbox.pdmodel.common;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class PDNameTreeNode<T extends COSObjectable> implements COSObjectable {
    private final COSDictionary node;
    private PDNameTreeNode parent;

    protected abstract T convertCOSToPD(COSBase cOSBase) throws IOException;

    protected abstract PDNameTreeNode<T> createChildNode(COSDictionary cOSDictionary);

    protected PDNameTreeNode() {
        this.node = new COSDictionary();
    }

    protected PDNameTreeNode(COSDictionary cOSDictionary) {
        this.node = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.node;
    }

    public PDNameTreeNode getParent() {
        return this.parent;
    }

    public void setParent(PDNameTreeNode pDNameTreeNode) {
        this.parent = pDNameTreeNode;
        calculateLimits();
    }

    public boolean isRootNode() {
        return this.parent == null;
    }

    public List<PDNameTreeNode<T>> getKids() {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(createChildNode((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setKids(List<? extends PDNameTreeNode<T>> list) {
        if (list != null && list.size() > 0) {
            Iterator<? extends PDNameTreeNode<T>> it = list.iterator();
            while (it.hasNext()) {
                it.next().setParent(this);
            }
            this.node.setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
            if (isRootNode()) {
                this.node.setItem(COSName.NAMES, (COSBase) null);
            }
        } else {
            this.node.setItem(COSName.KIDS, (COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) null);
        }
        calculateLimits();
    }

    private void calculateLimits() {
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        if (isRootNode()) {
            this.node.setItem(COSName.LIMITS, (COSBase) null);
            return;
        }
        List<PDNameTreeNode<T>> kids = getKids();
        if (kids != null && kids.size() > 0) {
            PDNameTreeNode<T> pDNameTreeNode = kids.get(0);
            PDNameTreeNode<T> pDNameTreeNode2 = kids.get(kids.size() - 1);
            setLowerLimit(pDNameTreeNode.getLowerLimit());
            setUpperLimit(pDNameTreeNode2.getUpperLimit());
            return;
        }
        try {
            Map<String, T> names = getNames();
            if (names != null && names.size() > 0) {
                Set<String> setKeySet = names.keySet();
                String[] strArr = (String[]) setKeySet.toArray(new String[setKeySet.size()]);
                setLowerLimit(strArr[0]);
                setUpperLimit(strArr[strArr.length - 1]);
            } else {
                this.node.setItem(COSName.LIMITS, (COSBase) null);
            }
        } catch (IOException e) {
            this.node.setItem(COSName.LIMITS, r1);
            Log.e("PdfBox-Android", "Error while calculating the Limits of a PageNameTreeNode:", e);
            r1 = "PdfBox-Android";
        }
    }

    public T getValue(String str) throws IOException {
        Map<String, T> names = getNames();
        if (names != null) {
            return names.get(str);
        }
        List<PDNameTreeNode<T>> kids = getKids();
        COSObjectable value = null;
        if (kids != null) {
            for (int i = 0; i < kids.size() && value == null; i++) {
                PDNameTreeNode<T> pDNameTreeNode = kids.get(i);
                if (pDNameTreeNode.getLowerLimit().compareTo(str) <= 0 && pDNameTreeNode.getUpperLimit().compareTo(str) >= 0) {
                    value = pDNameTreeNode.getValue(str);
                }
            }
        } else {
            Log.w("PdfBox-Android", "NameTreeNode does not have \"names\" nor \"kids\" objects.");
        }
        return (T) value;
    }

    public Map<String, T> getNames() throws IOException {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.NAMES);
        if (cOSArray == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < cOSArray.size(); i += 2) {
            linkedHashMap.put(((COSString) cOSArray.getObject(i)).getString(), convertCOSToPD(cOSArray.getObject(i + 1)));
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public void setNames(Map<String, T> map) {
        if (map == null) {
            this.node.setItem(COSName.NAMES, (COSObjectable) null);
            this.node.setItem(COSName.LIMITS, (COSObjectable) null);
            return;
        }
        COSArray cOSArray = new COSArray();
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            cOSArray.add((COSBase) new COSString(str));
            cOSArray.add(map.get(str));
        }
        this.node.setItem(COSName.NAMES, (COSBase) cOSArray);
        calculateLimits();
    }

    public String getUpperLimit() {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray != null) {
            return cOSArray.getString(1);
        }
        return null;
    }

    private void setUpperLimit(String str) {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) null);
            cOSArray.add((COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) cOSArray);
        }
        cOSArray.setString(1, str);
    }

    public String getLowerLimit() {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray != null) {
            return cOSArray.getString(0);
        }
        return null;
    }

    private void setLowerLimit(String str) {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) null);
            cOSArray.add((COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) cOSArray);
        }
        cOSArray.setString(0, str);
    }
}
