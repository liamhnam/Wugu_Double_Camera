package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GlyfCompositeDescript extends GlyfDescript {
    private boolean beingResolved;
    private final List<GlyfCompositeComp> components;
    private GlyphTable glyphTable;
    private boolean resolved;

    @Override
    public boolean isComposite() {
        return true;
    }

    public GlyfCompositeDescript(TTFDataStream tTFDataStream, GlyphTable glyphTable) throws IOException {
        GlyfCompositeComp glyfCompositeComp;
        super((short) -1, tTFDataStream);
        this.components = new ArrayList();
        this.beingResolved = false;
        this.resolved = false;
        this.glyphTable = glyphTable;
        do {
            glyfCompositeComp = new GlyfCompositeComp(tTFDataStream);
            this.components.add(glyfCompositeComp);
        } while ((glyfCompositeComp.getFlags() & 32) != 0);
        if ((glyfCompositeComp.getFlags() & OS2WindowsMetricsTable.FSTYPE_NO_SUBSETTING) != 0) {
            readInstructions(tTFDataStream, tTFDataStream.readUnsignedShort());
        }
    }

    @Override
    public void resolve() {
        if (this.resolved) {
            return;
        }
        if (this.beingResolved) {
            Log.e("PdfBox-Android", "Circular reference in GlyfCompositeDesc");
            return;
        }
        this.beingResolved = true;
        int pointCount = 0;
        int contourCount = 0;
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            glyfCompositeComp.setFirstIndex(pointCount);
            glyfCompositeComp.setFirstContour(contourCount);
            GlyphDescription glypDescription = getGlypDescription(glyfCompositeComp.getGlyphIndex());
            if (glypDescription != null) {
                glypDescription.resolve();
                pointCount += glypDescription.getPointCount();
                contourCount += glypDescription.getContourCount();
            }
        }
        this.resolved = true;
        this.beingResolved = false;
    }

    @Override
    public int getEndPtOfContours(int i) {
        GlyfCompositeComp compositeCompEndPt = getCompositeCompEndPt(i);
        if (compositeCompEndPt != null) {
            return getGlypDescription(compositeCompEndPt.getGlyphIndex()).getEndPtOfContours(i - compositeCompEndPt.getFirstContour()) + compositeCompEndPt.getFirstIndex();
        }
        return 0;
    }

    @Override
    public byte getFlags(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp != null) {
            return getGlypDescription(compositeComp.getGlyphIndex()).getFlags(i - compositeComp.getFirstIndex());
        }
        return (byte) 0;
    }

    @Override
    public short getXCoordinate(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp == null) {
            return (short) 0;
        }
        GlyphDescription glypDescription = getGlypDescription(compositeComp.getGlyphIndex());
        int firstIndex = i - compositeComp.getFirstIndex();
        return (short) (((short) compositeComp.scaleX(glypDescription.getXCoordinate(firstIndex), glypDescription.getYCoordinate(firstIndex))) + compositeComp.getXTranslate());
    }

    @Override
    public short getYCoordinate(int i) {
        GlyfCompositeComp compositeComp = getCompositeComp(i);
        if (compositeComp == null) {
            return (short) 0;
        }
        GlyphDescription glypDescription = getGlypDescription(compositeComp.getGlyphIndex());
        int firstIndex = i - compositeComp.getFirstIndex();
        return (short) (((short) compositeComp.scaleY(glypDescription.getXCoordinate(firstIndex), glypDescription.getYCoordinate(firstIndex))) + compositeComp.getYTranslate());
    }

    @Override
    public int getPointCount() {
        if (!this.resolved) {
            Log.e("PdfBox-Android", "getPointCount called on unresolved GlyfCompositeDescript");
        }
        GlyfCompositeComp glyfCompositeComp = this.components.get(r0.size() - 1);
        GlyphDescription glypDescription = getGlypDescription(glyfCompositeComp.getGlyphIndex());
        if (glypDescription == null) {
            Log.e("PdfBox-Android", "getGlypDescription(" + glyfCompositeComp.getGlyphIndex() + ") is null, returning 0");
            return 0;
        }
        return glyfCompositeComp.getFirstIndex() + glypDescription.getPointCount();
    }

    @Override
    public int getContourCount() {
        if (!this.resolved) {
            Log.e("PdfBox-Android", "getContourCount called on unresolved GlyfCompositeDescript");
        }
        GlyfCompositeComp glyfCompositeComp = this.components.get(r0.size() - 1);
        return glyfCompositeComp.getFirstContour() + getGlypDescription(glyfCompositeComp.getGlyphIndex()).getContourCount();
    }

    public int getComponentCount() {
        return this.components.size();
    }

    private GlyfCompositeComp getCompositeComp(int i) {
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            GlyphDescription glypDescription = getGlypDescription(glyfCompositeComp.getGlyphIndex());
            if (glyfCompositeComp.getFirstIndex() <= i && i < glyfCompositeComp.getFirstIndex() + glypDescription.getPointCount()) {
                return glyfCompositeComp;
            }
        }
        return null;
    }

    private GlyfCompositeComp getCompositeCompEndPt(int i) {
        for (GlyfCompositeComp glyfCompositeComp : this.components) {
            GlyphDescription glypDescription = getGlypDescription(glyfCompositeComp.getGlyphIndex());
            if (glyfCompositeComp.getFirstContour() <= i && i < glyfCompositeComp.getFirstContour() + glypDescription.getContourCount()) {
                return glyfCompositeComp;
            }
        }
        return null;
    }

    private GlyphDescription getGlypDescription(int i) {
        try {
            GlyphData glyph = this.glyphTable.getGlyph(i);
            if (glyph != null) {
                return glyph.getDescription();
            }
            return null;
        } catch (IOException e) {
            Log.e("PdfBox-Android", e.getMessage());
            return null;
        }
    }
}
