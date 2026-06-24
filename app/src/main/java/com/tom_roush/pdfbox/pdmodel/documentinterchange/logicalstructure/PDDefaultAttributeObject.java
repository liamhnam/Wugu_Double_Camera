package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PDDefaultAttributeObject extends PDAttributeObject {
    public PDDefaultAttributeObject() {
    }

    public PDDefaultAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public List<String> getAttributeNames() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<COSName, COSBase>> it = getCOSObject().entrySet().iterator();
        while (it.hasNext()) {
            COSName key = it.next().getKey();
            if (!COSName.f2286O.equals(key)) {
                arrayList.add(key.getName());
            }
        }
        return arrayList;
    }

    public COSBase getAttributeValue(String str) {
        return getCOSObject().getDictionaryObject(str);
    }

    protected COSBase getAttributeValue(String str, COSBase cOSBase) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        return dictionaryObject == null ? cOSBase : dictionaryObject;
    }

    public void setAttribute(String str, COSBase cOSBase) {
        COSBase attributeValue = getAttributeValue(str);
        getCOSObject().setItem(COSName.getPDFName(str), cOSBase);
        potentiallyNotifyChanged(attributeValue, cOSBase);
    }

    @Override
    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append(super.toString()).append(", attributes={");
        Iterator<String> it = getAttributeNames().iterator();
        while (it.hasNext()) {
            String next = it.next();
            sbAppend.append(next).append('=').append(getAttributeValue(next));
            if (it.hasNext()) {
                sbAppend.append(", ");
            }
        }
        return sbAppend.append('}').toString();
    }
}
