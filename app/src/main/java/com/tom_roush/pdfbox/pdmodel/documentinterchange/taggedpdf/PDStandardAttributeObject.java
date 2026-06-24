package com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDGamma;

public abstract class PDStandardAttributeObject extends PDAttributeObject {
    protected static final float UNSPECIFIED = -1.0f;

    public PDStandardAttributeObject() {
    }

    public PDStandardAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public boolean isSpecified(String str) {
        return getCOSObject().getDictionaryObject(str) != null;
    }

    protected String getString(String str) {
        return getCOSObject().getString(str);
    }

    protected void setString(String str, String str2) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setString(str, str2);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    protected String[] getArrayOfString(String str) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        if (!(dictionaryObject instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        String[] strArr = new String[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            strArr[i] = ((COSName) cOSArray.getObject(i)).getName();
        }
        return strArr;
    }

    protected void setArrayOfString(String str, String[] strArr) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        COSArray cOSArray = new COSArray();
        for (String str2 : strArr) {
            cOSArray.add((COSBase) new COSString(str2));
        }
        getCOSObject().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    protected String getName(String str) {
        return getCOSObject().getNameAsString(str);
    }

    protected String getName(String str, String str2) {
        return getCOSObject().getNameAsString(str, str2);
    }

    protected Object getNameOrArrayOfName(String str, String str2) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        if (!(dictionaryObject instanceof COSArray)) {
            return dictionaryObject instanceof COSName ? ((COSName) dictionaryObject).getName() : str2;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        String[] strArr = new String[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSName) {
                strArr[i] = ((COSName) object).getName();
            }
        }
        return strArr;
    }

    protected void setName(String str, String str2) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setName(str, str2);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    protected void setArrayOfName(String str, String[] strArr) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        COSArray cOSArray = new COSArray();
        for (String str2 : strArr) {
            cOSArray.add((COSBase) COSName.getPDFName(str2));
        }
        getCOSObject().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    protected Object getNumberOrName(String str, String str2) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        if (dictionaryObject instanceof COSNumber) {
            return Float.valueOf(((COSNumber) dictionaryObject).floatValue());
        }
        return dictionaryObject instanceof COSName ? ((COSName) dictionaryObject).getName() : str2;
    }

    protected int getInteger(String str, int i) {
        return getCOSObject().getInt(str, i);
    }

    protected void setInteger(String str, int i) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setInt(str, i);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    protected float getNumber(String str, float f) {
        return getCOSObject().getFloat(str, f);
    }

    protected float getNumber(String str) {
        return getCOSObject().getFloat(str);
    }

    protected Object getNumberOrArrayOfNumber(String str, float f) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            float[] fArr = new float[cOSArray.size()];
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSNumber) {
                    fArr[i] = ((COSNumber) object).floatValue();
                }
            }
            return fArr;
        }
        if (dictionaryObject instanceof COSNumber) {
            return Float.valueOf(((COSNumber) dictionaryObject).floatValue());
        }
        if (f == UNSPECIFIED) {
            return null;
        }
        return Float.valueOf(f);
    }

    protected void setNumber(String str, float f) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setFloat(str, f);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    protected void setNumber(String str, int i) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setInt(str, i);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    protected void setArrayOfNumber(String str, float[] fArr) {
        COSArray cOSArray = new COSArray();
        for (float f : fArr) {
            cOSArray.add((COSBase) new COSFloat(f));
        }
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    protected PDGamma getColor(String str) {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(str);
        if (cOSArray != null) {
            return new PDGamma(cOSArray);
        }
        return null;
    }

    protected Object getColorOrFourColors(String str) {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(str);
        if (cOSArray == null) {
            return null;
        }
        if (cOSArray.size() == 3) {
            return new PDGamma(cOSArray);
        }
        if (cOSArray.size() == 4) {
            return new PDFourColours(cOSArray);
        }
        return null;
    }

    protected void setColor(String str, PDGamma pDGamma) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setItem(str, pDGamma);
        potentiallyNotifyChanged(dictionaryObject, pDGamma == null ? null : pDGamma.getCOSObject());
    }

    protected void setFourColors(String str, PDFourColours pDFourColours) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setItem(str, pDFourColours);
        potentiallyNotifyChanged(dictionaryObject, pDFourColours == null ? null : pDFourColours.getCOSObject());
    }
}
