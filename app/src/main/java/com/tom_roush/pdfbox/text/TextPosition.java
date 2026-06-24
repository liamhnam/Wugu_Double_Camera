package com.tom_roush.pdfbox.text;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.util.Matrix;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.p040ws.WebSocketProtocol;

public final class TextPosition {
    private static final Map<Integer, String> DIACRITICS = createDiacritics();
    private final int[] charCodes;
    private float direction = -1.0f;
    private final float endX;
    private final float endY;
    private final PDFont font;
    private final float fontSize;
    private final int fontSizePt;
    private final float maxHeight;
    private final float pageHeight;
    private final float pageWidth;
    private final int rotation;
    private final Matrix textMatrix;
    private String unicode;
    private final float widthOfSpace;
    private float[] widths;

    private final float f2395x;

    private final float f2396y;

    private static Map<Integer, String> createDiacritics() {
        HashMap map = new HashMap(31);
        map.put(96, "̀");
        map.put(715, "̀");
        map.put(39, "́");
        map.put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_CUSTOM), "́");
        map.put(714, "́");
        map.put(94, "̂");
        map.put(710, "̂");
        map.put(Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), "̃");
        map.put(713, "̄");
        map.put(176, "̊");
        map.put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_SIZE), "̋");
        map.put(711, "̌");
        map.put(712, "̍");
        map.put(34, "̎");
        map.put(699, "̒");
        map.put(700, "̓");
        map.put(1158, "̓");
        map.put(1370, "̓");
        map.put(Integer.valueOf(TypedValues.TransitionType.TYPE_FROM), "̔");
        map.put(1157, "̔");
        map.put(1369, "̔");
        map.put(724, "̝");
        map.put(725, "̞");
        map.put(726, "̟");
        map.put(727, "̠");
        map.put(690, "̡");
        map.put(716, "̩");
        map.put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_REDO), "̫");
        map.put(717, "̱");
        map.put(95, "̲");
        map.put(8270, "͙");
        return map;
    }

    public TextPosition(int i, float f, float f2, Matrix matrix, float f3, float f4, float f5, float f6, float f7, String str, int[] iArr, PDFont pDFont, float f8, int i2) {
        this.textMatrix = matrix;
        this.endX = f3;
        this.endY = f4;
        this.rotation = i;
        this.maxHeight = f5;
        this.pageHeight = f2;
        this.pageWidth = f;
        this.widths = new float[]{f6};
        this.widthOfSpace = f7;
        this.unicode = str;
        this.charCodes = iArr;
        this.font = pDFont;
        this.fontSize = f8;
        this.fontSizePt = i2;
        float f9 = i;
        this.f2395x = getXRot(f9);
        if (i == 0 || i == 180) {
            this.f2396y = f2 - getYLowerLeftRot(f9);
        } else {
            this.f2396y = f - getYLowerLeftRot(f9);
        }
    }

    public String getUnicode() {
        return this.unicode;
    }

    public int[] getCharacterCodes() {
        return this.charCodes;
    }

    public Matrix getTextMatrix() {
        return this.textMatrix;
    }

    public float getDir() {
        if (this.direction < 0.0f) {
            float scaleY = this.textMatrix.getScaleY();
            float shearY = this.textMatrix.getShearY();
            float shearX = this.textMatrix.getShearX();
            float scaleX = this.textMatrix.getScaleX();
            if (scaleY > 0.0f && Math.abs(shearY) < scaleX && Math.abs(shearX) < scaleY && scaleX > 0.0f) {
                this.direction = 0.0f;
            } else if (scaleY < 0.0f && Math.abs(shearY) < Math.abs(scaleX) && Math.abs(shearX) < Math.abs(scaleY) && scaleX < 0.0f) {
                this.direction = 180.0f;
            } else if (Math.abs(scaleY) < Math.abs(shearX) && shearY > 0.0f && shearX < 0.0f && Math.abs(scaleX) < shearY) {
                this.direction = 90.0f;
            } else if (Math.abs(scaleY) < shearX && shearY < 0.0f && shearX > 0.0f && Math.abs(scaleX) < Math.abs(shearY)) {
                this.direction = 270.0f;
            } else {
                this.direction = 0.0f;
            }
        }
        return this.direction;
    }

    private float getXRot(float f) {
        if (f == 0.0f) {
            return this.textMatrix.getTranslateX();
        }
        if (f == 90.0f) {
            return this.textMatrix.getTranslateY();
        }
        if (f == 180.0f) {
            return this.pageWidth - this.textMatrix.getTranslateX();
        }
        if (f == 270.0f) {
            return this.pageHeight - this.textMatrix.getTranslateY();
        }
        return 0.0f;
    }

    public float getX() {
        return this.f2395x;
    }

    public float getXDirAdj() {
        return getXRot(getDir());
    }

    private float getYLowerLeftRot(float f) {
        if (f == 0.0f) {
            return this.textMatrix.getTranslateY();
        }
        if (f == 90.0f) {
            return this.pageWidth - this.textMatrix.getTranslateX();
        }
        if (f == 180.0f) {
            return this.pageHeight - this.textMatrix.getTranslateY();
        }
        if (f == 270.0f) {
            return this.textMatrix.getTranslateX();
        }
        return 0.0f;
    }

    public float getY() {
        return this.f2396y;
    }

    public float getYDirAdj() {
        float f;
        float yLowerLeftRot;
        float dir = getDir();
        if (dir == 0.0f || dir == 180.0f) {
            f = this.pageHeight;
            yLowerLeftRot = getYLowerLeftRot(dir);
        } else {
            f = this.pageWidth;
            yLowerLeftRot = getYLowerLeftRot(dir);
        }
        return f - yLowerLeftRot;
    }

    private float getWidthRot(float f) {
        if (f == 90.0f || f == 270.0f) {
            return Math.abs(this.endY - this.textMatrix.getTranslateY());
        }
        return Math.abs(this.endX - this.textMatrix.getTranslateX());
    }

    public float getWidth() {
        return getWidthRot(this.rotation);
    }

    public float getWidthDirAdj() {
        return getWidthRot(getDir());
    }

    public float getHeight() {
        return this.maxHeight;
    }

    public float getHeightDir() {
        return this.maxHeight;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public float getFontSizeInPt() {
        return this.fontSizePt;
    }

    public PDFont getFont() {
        return this.font;
    }

    public float getWidthOfSpace() {
        return this.widthOfSpace;
    }

    public float getXScale() {
        return this.textMatrix.getScalingFactorX();
    }

    public float getYScale() {
        return this.textMatrix.getScalingFactorY();
    }

    public float[] getIndividualWidths() {
        return this.widths;
    }

    public boolean contains(TextPosition textPosition) {
        double xDirAdj = getXDirAdj();
        double widthDirAdj = getWidthDirAdj();
        double d = xDirAdj + widthDirAdj;
        double xDirAdj2 = textPosition.getXDirAdj();
        double widthDirAdj2 = ((double) textPosition.getWidthDirAdj()) + xDirAdj2;
        if (widthDirAdj2 <= xDirAdj || xDirAdj2 >= d) {
            return false;
        }
        double yDirAdj = getYDirAdj();
        double yDirAdj2 = textPosition.getYDirAdj();
        if (((double) textPosition.getHeightDir()) + yDirAdj2 < yDirAdj || yDirAdj2 > yDirAdj + ((double) getHeightDir())) {
            return false;
        }
        return (xDirAdj2 <= xDirAdj || widthDirAdj2 <= d) ? xDirAdj2 >= xDirAdj || widthDirAdj2 >= d || (widthDirAdj2 - xDirAdj) / widthDirAdj > 0.15d : (d - xDirAdj2) / widthDirAdj > 0.15d;
    }

    public void mergeDiacritic(TextPosition textPosition) {
        if (textPosition.getUnicode().length() > 1) {
            return;
        }
        float xDirAdj = textPosition.getXDirAdj();
        float f = textPosition.widths[0] + xDirAdj;
        float xDirAdj2 = getXDirAdj();
        int length = this.unicode.length();
        float f2 = xDirAdj2;
        boolean z = false;
        for (int i = 0; i < length && !z; i++) {
            float[] fArr = this.widths;
            if (i >= fArr.length) {
                Log.i("PdfBox-Android", "diacritic " + textPosition.getUnicode() + " on ligature " + this.unicode + " is not supported yet and is ignored (PDFBOX-2831)");
                return;
            }
            float f3 = fArr[i];
            float f4 = f2 + f3;
            if (xDirAdj >= f2 || f > f4) {
                if (xDirAdj < f2 && f > f4) {
                    insertDiacritic(i, textPosition);
                } else if (xDirAdj >= f2 && f <= f4) {
                    insertDiacritic(i, textPosition);
                } else if (xDirAdj >= f2 && f > f4 && i == length - 1) {
                    insertDiacritic(i, textPosition);
                } else {
                    f2 += this.widths[i];
                }
            } else if (i == 0) {
                insertDiacritic(i, textPosition);
            } else {
                int i2 = i - 1;
                if ((f - f2) / f3 >= (f2 - xDirAdj) / fArr[i2]) {
                    insertDiacritic(i, textPosition);
                } else {
                    insertDiacritic(i2, textPosition);
                }
            }
            z = true;
            f2 += this.widths[i];
        }
    }

    private void insertDiacritic(int i, TextPosition textPosition) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.unicode.substring(0, i));
        float[] fArr = this.widths;
        float[] fArr2 = new float[fArr.length + 1];
        System.arraycopy(fArr, 0, fArr2, 0, i);
        sb.append(this.unicode.charAt(i));
        fArr2[i] = this.widths[i];
        sb.append(combineDiacritic(textPosition.getUnicode()));
        int i2 = i + 1;
        fArr2[i2] = 0.0f;
        String str = this.unicode;
        sb.append(str.substring(i2, str.length()));
        System.arraycopy(this.widths, i2, fArr2, i + 2, (r1.length - i) - 1);
        this.unicode = sb.toString();
        this.widths = fArr2;
    }

    private String combineDiacritic(String str) {
        int iCodePointAt = str.codePointAt(0);
        Map<Integer, String> map = DIACRITICS;
        if (map.containsKey(Integer.valueOf(iCodePointAt))) {
            return map.get(Integer.valueOf(iCodePointAt));
        }
        return Normalizer.normalize(str, Normalizer.Form.NFKC).trim();
    }

    public boolean isDiacritic() {
        String unicode = getUnicode();
        if (unicode.length() != 1) {
            return false;
        }
        int type = Character.getType(unicode.charAt(0));
        return type == 6 || type == 27 || type == 4;
    }

    public String toString() {
        return getUnicode();
    }
}
