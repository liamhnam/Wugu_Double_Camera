package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDStructureElementNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.common.COSDictionaryMap;
import com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.common.PDNumberTreeNode;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class PDStructureTreeRoot extends PDStructureNode {
    private static final String TYPE = "StructTreeRoot";

    public PDStructureTreeRoot() {
        super(TYPE);
    }

    public PDStructureTreeRoot(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public COSArray getKArray() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2274K);
        if (dictionaryObject == null) {
            return null;
        }
        if (dictionaryObject instanceof COSDictionary) {
            COSBase dictionaryObject2 = ((COSDictionary) dictionaryObject).getDictionaryObject(COSName.f2274K);
            if (dictionaryObject2 instanceof COSArray) {
                return (COSArray) dictionaryObject2;
            }
            return null;
        }
        return (COSArray) dictionaryObject;
    }

    public COSBase getK() {
        return getCOSObject().getDictionaryObject(COSName.f2274K);
    }

    public void setK(COSBase cOSBase) {
        getCOSObject().setItem(COSName.f2274K, cOSBase);
    }

    public PDNameTreeNode getIDTree() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.ID_TREE);
        if (cOSDictionary != null) {
            return new PDStructureElementNameTreeNode(cOSDictionary);
        }
        return null;
    }

    public void setIDTree(PDNameTreeNode pDNameTreeNode) {
        getCOSObject().setItem(COSName.ID_TREE, pDNameTreeNode);
    }

    public PDNumberTreeNode getParentTree() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.PARENT_TREE);
        if (cOSDictionary != null) {
            return new PDNumberTreeNode(cOSDictionary, COSBase.class);
        }
        return null;
    }

    public void setParentTree(PDNumberTreeNode pDNumberTreeNode) {
        getCOSObject().setItem(COSName.PARENT_TREE, pDNumberTreeNode);
    }

    public int getParentTreeNextKey() {
        return getCOSObject().getInt(COSName.PARENT_TREE_NEXT_KEY);
    }

    public void setParentTreeNextKey(int i) {
        getCOSObject().setInt(COSName.PARENT_TREE_NEXT_KEY, i);
    }

    public Map<String, Object> getRoleMap() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.ROLE_MAP);
        if (dictionaryObject instanceof COSDictionary) {
            try {
                return COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dictionaryObject);
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        }
        return new Hashtable();
    }

    public void setRoleMap(Map<String, String> map) {
        COSDictionary cOSDictionary = new COSDictionary();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            cOSDictionary.setName(entry.getKey(), entry.getValue());
        }
        getCOSObject().setItem(COSName.ROLE_MAP, (COSBase) cOSDictionary);
    }
}
