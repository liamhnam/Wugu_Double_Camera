package com.tom_roush.pdfbox.pdmodel;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDFontFactory;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import java.io.IOException;
import java.util.Collections;

public final class PDResources implements COSObjectable {
    private final ResourceCache cache;
    private final COSDictionary resources;

    public PDResources() {
        this.resources = new COSDictionary();
        this.cache = null;
    }

    public PDResources(COSDictionary cOSDictionary) {
        if (cOSDictionary == null) {
            throw new IllegalArgumentException("resourceDictionary is null");
        }
        this.resources = cOSDictionary;
        this.cache = null;
    }

    public PDResources(COSDictionary cOSDictionary, ResourceCache resourceCache) {
        if (cOSDictionary == null) {
            throw new IllegalArgumentException("resourceDictionary is null");
        }
        this.resources = cOSDictionary;
        this.cache = resourceCache;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.resources;
    }

    public PDFont getFont(COSName cOSName) throws IOException {
        PDFont font;
        COSObject indirect = getIndirect(COSName.FONT, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache != null && indirect != null && (font = resourceCache.getFont(indirect)) != null) {
            return font;
        }
        COSDictionary cOSDictionary = (COSDictionary) get(COSName.FONT, cOSName);
        PDFont pDFontCreateFont = cOSDictionary != null ? PDFontFactory.createFont(cOSDictionary) : null;
        ResourceCache resourceCache2 = this.cache;
        if (resourceCache2 != null) {
            resourceCache2.put(indirect, pDFontCreateFont);
        }
        return pDFontCreateFont;
    }

    public PDColorSpace getColorSpace(COSName cOSName) throws IOException {
        return getColorSpace(cOSName, false);
    }

    public PDColorSpace getColorSpace(COSName cOSName, boolean z) throws IOException {
        PDColorSpace pDColorSpaceCreate;
        PDColorSpace colorSpace;
        COSObject indirect = getIndirect(COSName.FONT, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache != null && indirect != null && (colorSpace = resourceCache.getColorSpace(indirect)) != null) {
            return colorSpace;
        }
        COSBase cOSBase = get(COSName.COLORSPACE, cOSName);
        if (cOSBase != null) {
            pDColorSpaceCreate = PDColorSpace.create(cOSBase, this, z);
        } else {
            pDColorSpaceCreate = PDColorSpace.create(cOSName, this, z);
        }
        ResourceCache resourceCache2 = this.cache;
        if (resourceCache2 != null) {
            resourceCache2.put(indirect, pDColorSpaceCreate);
        }
        return pDColorSpaceCreate;
    }

    public boolean hasColorSpace(COSName cOSName) {
        return get(COSName.COLORSPACE, cOSName) != null;
    }

    public PDExtendedGraphicsState getExtGState(COSName cOSName) {
        PDExtendedGraphicsState extGState;
        COSObject indirect = getIndirect(COSName.EXT_G_STATE, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache != null && indirect != null && (extGState = resourceCache.getExtGState(indirect)) != null) {
            return extGState;
        }
        COSDictionary cOSDictionary = (COSDictionary) get(COSName.EXT_G_STATE, cOSName);
        PDExtendedGraphicsState pDExtendedGraphicsState = cOSDictionary != null ? new PDExtendedGraphicsState(cOSDictionary) : null;
        ResourceCache resourceCache2 = this.cache;
        if (resourceCache2 != null) {
            resourceCache2.put(indirect, pDExtendedGraphicsState);
        }
        return pDExtendedGraphicsState;
    }

    public PDShading getShading(COSName cOSName) throws IOException {
        PDShading shading;
        COSObject indirect = getIndirect(COSName.SHADING, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache != null && indirect != null && (shading = resourceCache.getShading(indirect)) != null) {
            return shading;
        }
        COSDictionary cOSDictionary = (COSDictionary) get(COSName.SHADING, cOSName);
        PDShading pDShadingCreate = cOSDictionary != null ? PDShading.create(cOSDictionary) : null;
        ResourceCache resourceCache2 = this.cache;
        if (resourceCache2 != null) {
            resourceCache2.put(indirect, pDShadingCreate);
        }
        return pDShadingCreate;
    }

    public PDAbstractPattern getPattern(COSName cOSName) throws IOException {
        PDAbstractPattern pattern;
        COSObject indirect = getIndirect(COSName.PATTERN, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache != null && indirect != null && (pattern = resourceCache.getPattern(indirect)) != null) {
            return pattern;
        }
        COSDictionary cOSDictionary = (COSDictionary) get(COSName.PATTERN, cOSName);
        PDAbstractPattern pDAbstractPatternCreate = cOSDictionary != null ? PDAbstractPattern.create(cOSDictionary) : null;
        ResourceCache resourceCache2 = this.cache;
        if (resourceCache2 != null) {
            resourceCache2.put(indirect, pDAbstractPatternCreate);
        }
        return pDAbstractPatternCreate;
    }

    public PDPropertyList getProperties(COSName cOSName) {
        PDPropertyList properties;
        COSObject indirect = getIndirect(COSName.PROPERTIES, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache != null && indirect != null && (properties = resourceCache.getProperties(indirect)) != null) {
            return properties;
        }
        COSDictionary cOSDictionary = (COSDictionary) get(COSName.PROPERTIES, cOSName);
        PDPropertyList pDPropertyListCreate = cOSDictionary != null ? PDPropertyList.create(cOSDictionary) : null;
        ResourceCache resourceCache2 = this.cache;
        if (resourceCache2 != null) {
            resourceCache2.put(indirect, pDPropertyListCreate);
        }
        return pDPropertyListCreate;
    }

    public boolean isImageXObject(COSName cOSName) {
        COSBase object = get(COSName.XOBJECT, cOSName);
        if (object == null) {
            return false;
        }
        if (object instanceof COSObject) {
            object = ((COSObject) object).getObject();
        }
        if (object instanceof COSStream) {
            return COSName.IMAGE.equals(((COSStream) object).getCOSName(COSName.SUBTYPE));
        }
        return false;
    }

    public PDXObject getXObject(COSName cOSName) throws IOException {
        PDXObject pDXObjectCreateXObject;
        PDXObject xObject;
        COSObject indirect = getIndirect(COSName.XOBJECT, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache != null && indirect != null && (xObject = resourceCache.getXObject(indirect)) != null) {
            return xObject;
        }
        COSBase cOSBase = get(COSName.XOBJECT, cOSName);
        if (cOSBase == null) {
            pDXObjectCreateXObject = null;
        } else if (cOSBase instanceof COSObject) {
            pDXObjectCreateXObject = PDXObject.createXObject(((COSObject) cOSBase).getObject(), this);
        } else {
            pDXObjectCreateXObject = PDXObject.createXObject(cOSBase, this);
        }
        ResourceCache resourceCache2 = this.cache;
        if (resourceCache2 != null && !(pDXObjectCreateXObject instanceof PDImageXObject)) {
            resourceCache2.put(indirect, pDXObjectCreateXObject);
        }
        return pDXObjectCreateXObject;
    }

    private COSObject getIndirect(COSName cOSName, COSName cOSName2) {
        COSDictionary cOSDictionary = (COSDictionary) this.resources.getDictionaryObject(cOSName);
        if (cOSDictionary == null) {
            return null;
        }
        COSBase item = cOSDictionary.getItem(cOSName2);
        if (item instanceof COSObject) {
            return (COSObject) item;
        }
        return null;
    }

    private COSBase get(COSName cOSName, COSName cOSName2) {
        COSDictionary cOSDictionary = (COSDictionary) this.resources.getDictionaryObject(cOSName);
        if (cOSDictionary == null) {
            return null;
        }
        return cOSDictionary.getDictionaryObject(cOSName2);
    }

    public Iterable<COSName> getColorSpaceNames() {
        return getNames(COSName.COLORSPACE);
    }

    public Iterable<COSName> getXObjectNames() {
        return getNames(COSName.XOBJECT);
    }

    public Iterable<COSName> getFontNames() {
        return getNames(COSName.FONT);
    }

    public Iterable<COSName> getPropertiesNames() {
        return getNames(COSName.PROPERTIES);
    }

    public Iterable<COSName> getShadingNames() {
        return getNames(COSName.SHADING);
    }

    public Iterable<COSName> getPatternNames() {
        return getNames(COSName.PATTERN);
    }

    public Iterable<COSName> getExtGStateNames() {
        return getNames(COSName.EXT_G_STATE);
    }

    private Iterable<COSName> getNames(COSName cOSName) {
        COSDictionary cOSDictionary = (COSDictionary) this.resources.getDictionaryObject(cOSName);
        if (cOSDictionary == null) {
            return Collections.emptySet();
        }
        return cOSDictionary.keySet();
    }

    public COSName add(PDFont pDFont) {
        return add(COSName.FONT, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, pDFont);
    }

    public COSName add(PDColorSpace pDColorSpace) {
        return add(COSName.COLORSPACE, "cs", pDColorSpace);
    }

    public COSName add(PDExtendedGraphicsState pDExtendedGraphicsState) {
        return add(COSName.EXT_G_STATE, "gs", pDExtendedGraphicsState);
    }

    public COSName add(PDShading pDShading) {
        return add(COSName.SHADING, "sh", pDShading);
    }

    public COSName add(PDAbstractPattern pDAbstractPattern) {
        return add(COSName.PATTERN, TtmlNode.TAG_P, pDAbstractPattern);
    }

    public COSName add(PDPropertyList pDPropertyList) {
        if (pDPropertyList instanceof PDOptionalContentGroup) {
            return add(COSName.PROPERTIES, "oc", pDPropertyList);
        }
        return add(COSName.PROPERTIES, "Prop", pDPropertyList);
    }

    public COSName add(PDImageXObject pDImageXObject) {
        return add(COSName.XOBJECT, "Im", pDImageXObject);
    }

    public COSName add(PDFormXObject pDFormXObject) {
        return add(COSName.XOBJECT, StandardStructureTypes.FORM, pDFormXObject);
    }

    public COSName add(PDXObject pDXObject, String str) {
        return add(COSName.XOBJECT, str, pDXObject);
    }

    private COSName add(COSName cOSName, String str, COSObjectable cOSObjectable) {
        COSDictionary cOSDictionary = (COSDictionary) this.resources.getDictionaryObject(cOSName);
        if (cOSDictionary != null && cOSDictionary.containsValue(cOSObjectable.getCOSObject())) {
            return cOSDictionary.getKeyForValue(cOSObjectable.getCOSObject());
        }
        COSName cOSNameCreateKey = createKey(cOSName, str);
        put(cOSName, cOSNameCreateKey, cOSObjectable);
        return cOSNameCreateKey;
    }

    private COSName createKey(COSName cOSName, String str) {
        String str2;
        COSDictionary cOSDictionary = (COSDictionary) this.resources.getDictionaryObject(cOSName);
        if (cOSDictionary == null) {
            return COSName.getPDFName(str + 1);
        }
        int size = cOSDictionary.keySet().size();
        do {
            size++;
            str2 = str + size;
        } while (cOSDictionary.containsKey(str2));
        return COSName.getPDFName(str2);
    }

    private void put(COSName cOSName, COSName cOSName2, COSObjectable cOSObjectable) {
        COSDictionary cOSDictionary = (COSDictionary) this.resources.getDictionaryObject(cOSName);
        if (cOSDictionary == null) {
            cOSDictionary = new COSDictionary();
            this.resources.setItem(cOSName, (COSBase) cOSDictionary);
        }
        cOSDictionary.setItem(cOSName2, cOSObjectable);
    }

    public void put(COSName cOSName, PDFont pDFont) {
        put(COSName.FONT, cOSName, pDFont);
    }

    public void put(COSName cOSName, PDColorSpace pDColorSpace) {
        put(COSName.COLORSPACE, cOSName, pDColorSpace);
    }

    public void put(COSName cOSName, PDExtendedGraphicsState pDExtendedGraphicsState) {
        put(COSName.EXT_G_STATE, cOSName, pDExtendedGraphicsState);
    }

    public void put(COSName cOSName, PDShading pDShading) {
        put(COSName.SHADING, cOSName, pDShading);
    }

    public void put(COSName cOSName, PDAbstractPattern pDAbstractPattern) {
        put(COSName.PATTERN, cOSName, pDAbstractPattern);
    }

    public void put(COSName cOSName, PDPropertyList pDPropertyList) {
        put(COSName.PROPERTIES, cOSName, pDPropertyList);
    }

    public void put(COSName cOSName, PDXObject pDXObject) {
        put(COSName.XOBJECT, cOSName, pDXObject);
    }

    public ResourceCache getResourceCache() {
        return this.cache;
    }
}
