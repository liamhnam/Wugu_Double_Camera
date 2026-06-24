package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PDUserAttributeObject extends PDAttributeObject {
    public static final String OWNER_USER_PROPERTIES = "UserProperties";

    public void userPropertyChanged(PDUserProperty pDUserProperty) {
    }

    public PDUserAttributeObject() {
        setOwner(OWNER_USER_PROPERTIES);
    }

    public PDUserAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public List<PDUserProperty> getOwnerUserProperties() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2292P);
        ArrayList arrayList = new ArrayList(cOSArray.size());
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(new PDUserProperty((COSDictionary) cOSArray.getObject(i), this));
        }
        return arrayList;
    }

    public void setUserProperties(List<PDUserProperty> list) {
        COSArray cOSArray = new COSArray();
        Iterator<PDUserProperty> it = list.iterator();
        while (it.hasNext()) {
            cOSArray.add(it.next());
        }
        getCOSObject().setItem(COSName.f2292P, (COSBase) cOSArray);
    }

    public void addUserProperty(PDUserProperty pDUserProperty) {
        ((COSArray) getCOSObject().getDictionaryObject(COSName.f2292P)).add(pDUserProperty);
        notifyChanged();
    }

    public void removeUserProperty(PDUserProperty pDUserProperty) {
        if (pDUserProperty == null) {
            return;
        }
        ((COSArray) getCOSObject().getDictionaryObject(COSName.f2292P)).remove(pDUserProperty.getCOSObject());
        notifyChanged();
    }

    @Override
    public String toString() {
        return super.toString() + ", userProperties=" + getOwnerUserProperties();
    }
}
