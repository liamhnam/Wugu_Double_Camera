package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDDictionaryWrapper;

public class PDUserProperty extends PDDictionaryWrapper {
    private final PDUserAttributeObject userAttributeObject;

    public PDUserProperty(PDUserAttributeObject pDUserAttributeObject) {
        this.userAttributeObject = pDUserAttributeObject;
    }

    public PDUserProperty(COSDictionary cOSDictionary, PDUserAttributeObject pDUserAttributeObject) {
        super(cOSDictionary);
        this.userAttributeObject = pDUserAttributeObject;
    }

    public String getName() {
        return getCOSObject().getNameAsString(COSName.f2284N);
    }

    public void setName(String str) {
        potentiallyNotifyChanged(getName(), str);
        getCOSObject().setName(COSName.f2284N, str);
    }

    public COSBase getValue() {
        return getCOSObject().getDictionaryObject(COSName.f2320V);
    }

    public void setValue(COSBase cOSBase) {
        potentiallyNotifyChanged(getValue(), cOSBase);
        getCOSObject().setItem(COSName.f2320V, cOSBase);
    }

    public String getFormattedValue() {
        return getCOSObject().getString(COSName.f2260F);
    }

    public void setFormattedValue(String str) {
        potentiallyNotifyChanged(getFormattedValue(), str);
        getCOSObject().setString(COSName.f2260F, str);
    }

    public boolean isHidden() {
        return getCOSObject().getBoolean(COSName.f2266H, false);
    }

    public void setHidden(boolean z) {
        potentiallyNotifyChanged(Boolean.valueOf(isHidden()), Boolean.valueOf(z));
        getCOSObject().setBoolean(COSName.f2266H, z);
    }

    public String toString() {
        return "Name=" + getName() + ", Value=" + getValue() + ", FormattedValue=" + getFormattedValue() + ", Hidden=" + isHidden();
    }

    private void potentiallyNotifyChanged(Object obj, Object obj2) {
        if (isEntryChanged(obj, obj2)) {
            this.userAttributeObject.userPropertyChanged(this);
        }
    }

    private boolean isEntryChanged(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 != null;
        }
        return !obj.equals(obj2);
    }

    @Override
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        PDUserAttributeObject pDUserAttributeObject = this.userAttributeObject;
        return iHashCode + (pDUserAttributeObject == null ? 0 : pDUserAttributeObject.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        PDUserProperty pDUserProperty = (PDUserProperty) obj;
        PDUserAttributeObject pDUserAttributeObject = this.userAttributeObject;
        if (pDUserAttributeObject == null) {
            if (pDUserProperty.userAttributeObject != null) {
                return false;
            }
        } else if (!pDUserAttributeObject.equals(pDUserProperty.userAttributeObject)) {
            return false;
        }
        return true;
    }
}
