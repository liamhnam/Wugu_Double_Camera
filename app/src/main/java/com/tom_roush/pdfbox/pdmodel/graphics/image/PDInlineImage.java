package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.filter.DecodeResult;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class PDInlineImage implements PDImage {
    private final byte[] decodedData;
    private final COSDictionary parameters;
    private final byte[] rawData;
    private final PDResources resources;

    @Override
    public String getSuffix() {
        return null;
    }

    public PDInlineImage(COSDictionary cOSDictionary, byte[] bArr, PDResources pDResources) throws IOException {
        this.parameters = cOSDictionary;
        this.resources = pDResources;
        this.rawData = bArr;
        List<String> filters = getFilters();
        DecodeResult decodeResultDecode = null;
        if (filters == null || filters.isEmpty()) {
            this.decodedData = bArr;
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            for (int i = 0; i < filters.size(); i++) {
                byteArrayOutputStream.reset();
                decodeResultDecode = FilterFactory.INSTANCE.getFilter(filters.get(i)).decode(byteArrayInputStream, byteArrayOutputStream, cOSDictionary, i);
                byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
            this.decodedData = byteArrayOutputStream.toByteArray();
        }
        if (decodeResultDecode != null) {
            cOSDictionary.addAll(decodeResultDecode.getParameters());
        }
    }

    @Override
    public COSBase getCOSObject() {
        return this.parameters;
    }

    @Override
    public int getBitsPerComponent() {
        if (isStencil()) {
            return 1;
        }
        return this.parameters.getInt(COSName.BPC, COSName.BITS_PER_COMPONENT, -1);
    }

    @Override
    public void setBitsPerComponent(int i) {
        this.parameters.setInt(COSName.BPC, i);
    }

    @Override
    public PDColorSpace getColorSpace() throws IOException {
        COSBase dictionaryObject = this.parameters.getDictionaryObject(COSName.f2247CS, COSName.COLORSPACE);
        if (dictionaryObject != null) {
            return createColorSpace(dictionaryObject);
        }
        if (isStencil()) {
            return PDDeviceGray.INSTANCE;
        }
        throw new IOException("could not determine inline image color space");
    }

    private COSBase toLongName(COSBase cOSBase) {
        if (COSName.RGB.equals(cOSBase)) {
            return COSName.DEVICERGB;
        }
        if (COSName.CMYK.equals(cOSBase)) {
            return COSName.DEVICECMYK;
        }
        return COSName.f2265G.equals(cOSBase) ? COSName.DEVICEGRAY : cOSBase;
    }

    private PDColorSpace createColorSpace(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSName) {
            return PDColorSpace.create(toLongName(cOSBase), this.resources);
        }
        if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            if (cOSArray.size() > 1) {
                COSBase cOSBase2 = cOSArray.get(0);
                if (COSName.f2267I.equals(cOSBase2) || COSName.INDEXED.equals(cOSBase2)) {
                    COSArray cOSArray2 = new COSArray();
                    cOSArray2.addAll(cOSArray);
                    cOSArray2.set(0, (COSBase) COSName.INDEXED);
                    cOSArray2.set(1, toLongName(cOSArray.get(1)));
                    return PDColorSpace.create(cOSArray2, this.resources);
                }
                throw new IOException("Illegal type of inline image color space: " + cOSBase2);
            }
        }
        throw new IOException("Illegal type of object for inline image color space: " + cOSBase);
    }

    @Override
    public void setColorSpace(PDColorSpace pDColorSpace) {
        this.parameters.setItem(COSName.f2247CS, pDColorSpace != null ? pDColorSpace.getCOSObject() : null);
    }

    @Override
    public int getHeight() {
        return this.parameters.getInt(COSName.f2266H, COSName.HEIGHT, -1);
    }

    @Override
    public void setHeight(int i) {
        this.parameters.setInt(COSName.f2266H, i);
    }

    @Override
    public int getWidth() {
        return this.parameters.getInt(COSName.f2321W, COSName.WIDTH, -1);
    }

    @Override
    public void setWidth(int i) {
        this.parameters.setInt(COSName.f2321W, i);
    }

    @Override
    public boolean getInterpolate() {
        return this.parameters.getBoolean(COSName.f2267I, COSName.INTERPOLATE, false);
    }

    @Override
    public void setInterpolate(boolean z) {
        this.parameters.setBoolean(COSName.f2267I, z);
    }

    public List<String> getFilters() {
        COSBase dictionaryObject = this.parameters.getDictionaryObject(COSName.f2260F, COSName.FILTER);
        if (dictionaryObject instanceof COSName) {
            COSName cOSName = (COSName) dictionaryObject;
            return new COSArrayList(cOSName.getName(), cOSName, this.parameters, COSName.FILTER);
        }
        if (dictionaryObject instanceof COSArray) {
            return COSArrayList.convertCOSNameCOSArrayToList((COSArray) dictionaryObject);
        }
        return null;
    }

    public void setFilters(List<String> list) {
        this.parameters.setItem(COSName.f2260F, (COSBase) COSArrayList.convertStringListToCOSNameCOSArray(list));
    }

    @Override
    public void setDecode(COSArray cOSArray) {
        this.parameters.setItem(COSName.f2248D, (COSBase) cOSArray);
    }

    @Override
    public COSArray getDecode() {
        return (COSArray) this.parameters.getDictionaryObject(COSName.f2248D, COSName.DECODE);
    }

    @Override
    public boolean isStencil() {
        return this.parameters.getBoolean(COSName.f2271IM, COSName.IMAGE_MASK, false);
    }

    @Override
    public void setStencil(boolean z) {
        this.parameters.setBoolean(COSName.f2271IM, z);
    }

    @Override
    public InputStream createInputStream() throws IOException {
        return new ByteArrayInputStream(this.decodedData);
    }

    @Override
    public InputStream createInputStream(List<String> list) throws IOException {
        List<String> filters = getFilters();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(this.rawData.length);
        for (int i = 0; i < filters.size(); i++) {
            byteArrayOutputStream.reset();
            if (list.contains(filters.get(i))) {
                break;
            }
            FilterFactory.INSTANCE.getFilter(filters.get(i)).decode(byteArrayInputStream, byteArrayOutputStream, this.parameters, i);
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    @Override
    public boolean isEmpty() {
        return this.decodedData.length == 0;
    }

    public byte[] getData() {
        return this.decodedData;
    }

    @Override
    public Bitmap getImage() throws IOException {
        return SampledImageReader.getRGBImage(this, getColorKeyMask());
    }

    @Override
    public Bitmap getStencilImage(Paint paint) throws IOException {
        if (!isStencil()) {
            throw new IllegalStateException("Image is not a stencil");
        }
        return SampledImageReader.getStencilImage(this, paint);
    }

    public COSArray getColorKeyMask() {
        COSBase dictionaryObject = this.parameters.getDictionaryObject(COSName.f2271IM, COSName.MASK);
        if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        }
        return null;
    }
}
