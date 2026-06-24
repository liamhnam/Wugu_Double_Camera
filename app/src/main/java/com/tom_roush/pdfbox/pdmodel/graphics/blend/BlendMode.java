package com.tom_roush.pdfbox.pdmodel.graphics.blend;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import java.util.HashMap;
import java.util.Map;

public abstract class BlendMode {
    private static final Map<COSName, BlendMode> BLEND_MODES;
    public static final SeparableBlendMode COLOR_BURN;
    public static final SeparableBlendMode COLOR_DODGE;
    public static final SeparableBlendMode COMPATIBLE;
    public static final SeparableBlendMode DARKEN;
    public static final SeparableBlendMode DIFFERENCE;
    public static final SeparableBlendMode EXCLUSION;
    public static final SeparableBlendMode HARD_LIGHT;
    public static final SeparableBlendMode LIGHTEN;
    public static final SeparableBlendMode MULTIPLY;
    public static final SeparableBlendMode NORMAL;
    public static final SeparableBlendMode OVERLAY;
    public static final SeparableBlendMode SCREEN;
    public static final SeparableBlendMode SOFT_LIGHT;

    public static BlendMode getInstance(COSBase cOSBase) {
        BlendMode blendMode;
        if (cOSBase instanceof COSName) {
            blendMode = BLEND_MODES.get(cOSBase);
        } else {
            BlendMode blendMode2 = null;
            if (cOSBase instanceof COSArray) {
                COSArray cOSArray = (COSArray) cOSBase;
                for (int i = 0; i < cOSArray.size() && (blendMode2 = BLEND_MODES.get((COSName) cOSArray.get(i))) == null; i++) {
                }
            }
            blendMode = blendMode2;
        }
        return blendMode != null ? blendMode : COMPATIBLE;
    }

    static {
        SeparableBlendMode separableBlendMode = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return f;
            }
        };
        NORMAL = separableBlendMode;
        COMPATIBLE = separableBlendMode;
        MULTIPLY = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return f * f2;
            }
        };
        SCREEN = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return (f + f2) - (f * f2);
            }
        };
        OVERLAY = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return ((double) f2) <= 0.5d ? f2 * 2.0f * f : (((f + f2) - (f * f2)) * 2.0f) - 1.0f;
            }
        };
        DARKEN = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return Math.min(f, f2);
            }
        };
        LIGHTEN = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return Math.max(f, f2);
            }
        };
        COLOR_DODGE = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                if (f < 1.0f) {
                    return Math.min(1.0f, f2 / (1.0f - f));
                }
                return 1.0f;
            }
        };
        COLOR_BURN = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                if (f > 0.0f) {
                    return 1.0f - Math.min(1.0f, (1.0f - f2) / f);
                }
                return 0.0f;
            }
        };
        HARD_LIGHT = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return ((double) f) <= 0.5d ? f2 * 2.0f * f : (((f + f2) - (f * f2)) * 2.0f) - 1.0f;
            }
        };
        SOFT_LIGHT = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                if (f <= 0.5d) {
                    return f2 - (((1.0f - (f * 2.0f)) * f2) * (1.0f - f2));
                }
                double d = f2;
                return f2 + (((f * 2.0f) - 1.0f) * ((d <= 0.25d ? ((((16.0f * f2) - 12.0f) * f2) + 4.0f) * f2 : (float) Math.sqrt(d)) - f2));
            }
        };
        DIFFERENCE = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return Math.abs(f2 - f);
            }
        };
        EXCLUSION = new SeparableBlendMode() {
            @Override
            public float blendChannel(float f, float f2) {
                return (f2 + f) - ((f2 * 2.0f) * f);
            }
        };
        BLEND_MODES = createBlendModeMap();
    }

    private static Map<COSName, BlendMode> createBlendModeMap() {
        HashMap map = new HashMap(13);
        map.put(COSName.NORMAL, NORMAL);
        map.put(COSName.COMPATIBLE, COMPATIBLE);
        map.put(COSName.MULTIPLY, MULTIPLY);
        map.put(COSName.SCREEN, SCREEN);
        map.put(COSName.OVERLAY, OVERLAY);
        map.put(COSName.DARKEN, DARKEN);
        map.put(COSName.LIGHTEN, LIGHTEN);
        map.put(COSName.COLOR_DODGE, COLOR_DODGE);
        map.put(COSName.COLOR_BURN, COLOR_BURN);
        map.put(COSName.HARD_LIGHT, HARD_LIGHT);
        map.put(COSName.SOFT_LIGHT, SOFT_LIGHT);
        map.put(COSName.DIFFERENCE, DIFFERENCE);
        map.put(COSName.EXCLUSION, EXCLUSION);
        return map;
    }

    BlendMode() {
    }
}
