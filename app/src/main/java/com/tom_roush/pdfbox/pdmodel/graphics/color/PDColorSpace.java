package com.tom_roush.pdfbox.pdmodel.graphics.color;

import android.graphics.Bitmap;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.pdmodel.MissingResourceException;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.IOException;

public abstract class PDColorSpace implements COSObjectable {
    protected COSArray array;

    public abstract float[] getDefaultDecode(int i);

    public abstract PDColor getInitialColor();

    public abstract String getName();

    public abstract int getNumberOfComponents();

    public abstract float[] toRGB(float[] fArr) throws IOException;

    public abstract Bitmap toRGBImage(Bitmap bitmap) throws IOException;

    public static PDColorSpace create(COSBase cOSBase) throws IOException {
        return create(cOSBase, null);
    }

    public static PDColorSpace create(COSBase cOSBase, PDResources pDResources) throws IOException {
        return create(cOSBase, pDResources, false);
    }

    public static PDColorSpace create(COSBase cOSBase, PDResources pDResources, boolean z) throws IOException {
        COSName cOSName;
        if (cOSBase instanceof COSObject) {
            return create(((COSObject) cOSBase).getObject(), pDResources);
        }
        if (cOSBase instanceof COSName) {
            COSName cOSName2 = (COSName) cOSBase;
            if (pDResources != null) {
                if (cOSName2.equals(COSName.DEVICECMYK) && pDResources.hasColorSpace(COSName.DEFAULT_CMYK)) {
                    cOSName = COSName.DEFAULT_CMYK;
                } else if (cOSName2.equals(COSName.DEVICERGB) && pDResources.hasColorSpace(COSName.DEFAULT_RGB)) {
                    cOSName = COSName.DEFAULT_RGB;
                } else {
                    cOSName = (cOSName2.equals(COSName.DEVICEGRAY) && pDResources.hasColorSpace(COSName.DEFAULT_GRAY)) ? COSName.DEFAULT_GRAY : null;
                }
                if (pDResources.hasColorSpace(cOSName) && !z) {
                    return pDResources.getColorSpace(cOSName, true);
                }
            }
            if (cOSName2 == COSName.DEVICERGB) {
                return PDDeviceRGB.INSTANCE;
            }
            if (cOSName2 == COSName.DEVICEGRAY) {
                return PDDeviceGray.INSTANCE;
            }
            if (pDResources != null) {
                if (!pDResources.hasColorSpace(cOSName2)) {
                    throw new MissingResourceException("Missing color space: " + cOSName2.getName());
                }
                return pDResources.getColorSpace(cOSName2);
            }
            throw new MissingResourceException("Unknown color space: " + cOSName2.getName());
        }
        if (cOSBase instanceof COSArray) {
            COSName cOSName3 = (COSName) ((COSArray) cOSBase).get(0);
            if (cOSName3 == COSName.DEVICECMYK || cOSName3 == COSName.DEVICERGB || cOSName3 == COSName.DEVICEGRAY) {
                return create(cOSName3, pDResources);
            }
            Log.e("PdfBox-Android", "Invalid color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
            return PDDeviceRGB.INSTANCE;
        }
        throw new IOException("Expected a name or array but got: " + cOSBase);
    }

    @Override
    public COSBase getCOSObject() {
        return this.array;
    }
}
