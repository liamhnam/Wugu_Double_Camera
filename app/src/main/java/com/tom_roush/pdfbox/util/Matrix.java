package com.tom_roush.pdfbox.util;

import android.graphics.PointF;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.lang.reflect.Array;

public final class Matrix implements Cloneable {
    static final float[] DEFAULT_SINGLE = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private final float[] single;

    public Matrix() {
        float[] fArr = DEFAULT_SINGLE;
        float[] fArr2 = new float[fArr.length];
        this.single = fArr2;
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
    }

    public Matrix(COSArray cOSArray) {
        float[] fArr = new float[DEFAULT_SINGLE.length];
        this.single = fArr;
        fArr[0] = ((COSNumber) cOSArray.get(0)).floatValue();
        fArr[1] = ((COSNumber) cOSArray.get(1)).floatValue();
        fArr[3] = ((COSNumber) cOSArray.get(2)).floatValue();
        fArr[4] = ((COSNumber) cOSArray.get(3)).floatValue();
        fArr[6] = ((COSNumber) cOSArray.get(4)).floatValue();
        fArr[7] = ((COSNumber) cOSArray.get(5)).floatValue();
        fArr[8] = 1.0f;
    }

    public Matrix(float f, float f2, float f3, float f4, float f5, float f6) {
        float[] fArr = new float[DEFAULT_SINGLE.length];
        this.single = fArr;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[3] = f3;
        fArr[4] = f4;
        fArr[6] = f5;
        fArr[7] = f6;
        fArr[8] = 1.0f;
    }

    public Matrix(AffineTransform affineTransform) {
        float[] fArr = DEFAULT_SINGLE;
        float[] fArr2 = new float[fArr.length];
        this.single = fArr2;
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
        fArr2[0] = (float) affineTransform.getScaleX();
        fArr2[1] = (float) affineTransform.getShearY();
        fArr2[3] = (float) affineTransform.getShearX();
        fArr2[4] = (float) affineTransform.getScaleY();
        fArr2[6] = (float) affineTransform.getTranslateX();
        fArr2[7] = (float) affineTransform.getTranslateY();
    }

    @Deprecated
    public void reset() {
        float[] fArr = DEFAULT_SINGLE;
        System.arraycopy(fArr, 0, this.single, 0, fArr.length);
    }

    public AffineTransform createAffineTransform() {
        float[] fArr = this.single;
        return new AffineTransform(fArr[0], fArr[1], fArr[3], fArr[4], fArr[6], fArr[7]);
    }

    @Deprecated
    public void setFromAffineTransform(AffineTransform affineTransform) {
        this.single[0] = (float) affineTransform.getScaleX();
        this.single[1] = (float) affineTransform.getShearY();
        this.single[3] = (float) affineTransform.getShearX();
        this.single[4] = (float) affineTransform.getScaleY();
        this.single[6] = (float) affineTransform.getTranslateX();
        this.single[7] = (float) affineTransform.getTranslateY();
    }

    public float getValue(int i, int i2) {
        return this.single[(i * 3) + i2];
    }

    public void setValue(int i, int i2, float f) {
        this.single[(i * 3) + i2] = f;
    }

    public float[][] getValues() {
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 3, 3);
        float[] fArr2 = fArr[0];
        float[] fArr3 = this.single;
        fArr2[0] = fArr3[0];
        fArr2[1] = fArr3[1];
        fArr2[2] = fArr3[2];
        float[] fArr4 = fArr[1];
        fArr4[0] = fArr3[3];
        fArr4[1] = fArr3[4];
        fArr4[2] = fArr3[5];
        float[] fArr5 = fArr[2];
        fArr5[0] = fArr3[6];
        fArr5[1] = fArr3[7];
        fArr5[2] = fArr3[8];
        return fArr;
    }

    @Deprecated
    public double[][] getValuesAsDouble() {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 3, 3);
        double[] dArr2 = dArr[0];
        float[] fArr = this.single;
        dArr2[0] = fArr[0];
        dArr2[1] = fArr[1];
        dArr2[2] = fArr[2];
        double[] dArr3 = dArr[1];
        dArr3[0] = fArr[3];
        dArr3[1] = fArr[4];
        dArr3[2] = fArr[5];
        double[] dArr4 = dArr[2];
        dArr4[0] = fArr[6];
        dArr4[1] = fArr[7];
        dArr4[2] = fArr[8];
        return dArr;
    }

    public void concatenate(Matrix matrix) {
        matrix.multiply(this, this);
    }

    public void translate(Vector vector) {
        concatenate(getTranslateInstance(vector.getX(), vector.getY()));
    }

    public void translate(float f, float f2) {
        concatenate(getTranslateInstance(f, f2));
    }

    public void scale(float f, float f2) {
        concatenate(getScaleInstance(f, f2));
    }

    public void rotate(double d) {
        concatenate(getRotateInstance(d, 0.0f, 0.0f));
    }

    public Matrix multiply(Matrix matrix) {
        return multiply(matrix, new Matrix());
    }

    public Matrix multiply(Matrix matrix, Matrix matrix2) {
        float[] fArr;
        Matrix matrix3 = matrix2 == null ? new Matrix() : matrix2;
        if (matrix != null && (fArr = matrix.single) != null) {
            float[] fArr2 = this.single;
            if (this == matrix3) {
                float[] fArr3 = new float[fArr2.length];
                System.arraycopy(fArr2, 0, fArr3, 0, fArr2.length);
                fArr2 = fArr3;
            }
            if (matrix == matrix3) {
                float[] fArr4 = matrix.single;
                fArr = new float[fArr4.length];
                System.arraycopy(fArr4, 0, fArr, 0, fArr4.length);
            }
            float[] fArr5 = matrix3.single;
            float f = fArr2[0] * fArr[0];
            float f2 = fArr2[1];
            float f3 = fArr[3];
            float f4 = fArr2[2];
            float f5 = fArr[6];
            fArr5[0] = f + (f2 * f3) + (f4 * f5);
            float f6 = fArr2[0];
            float f7 = fArr[1] * f6;
            float f8 = fArr[4];
            float f9 = fArr[7];
            fArr5[1] = f7 + (f2 * f8) + (f4 * f9);
            float f10 = f6 * fArr[2];
            float f11 = fArr2[1];
            float f12 = fArr[5];
            float f13 = fArr[8];
            fArr5[2] = f10 + (f11 * f12) + (f4 * f13);
            float f14 = fArr2[3];
            float f15 = fArr[0];
            float f16 = fArr2[4];
            float f17 = (f14 * f15) + (f3 * f16);
            float f18 = fArr2[5];
            fArr5[3] = f17 + (f18 * f5);
            float f19 = fArr2[3];
            float f20 = fArr[1];
            fArr5[4] = (f19 * f20) + (f16 * f8) + (f18 * f9);
            float f21 = fArr[2];
            fArr5[5] = (f19 * f21) + (fArr2[4] * f12) + (f18 * f13);
            float f22 = fArr2[6] * f15;
            float f23 = fArr2[7];
            float f24 = f22 + (fArr[3] * f23);
            float f25 = fArr2[8];
            fArr5[6] = f24 + (f5 * f25);
            float f26 = fArr2[6];
            fArr5[7] = (f20 * f26) + (f23 * fArr[4]) + (f9 * f25);
            fArr5[8] = (f26 * f21) + (fArr2[7] * fArr[5]) + (f25 * f13);
        }
        return matrix3;
    }

    public void transform(PointF pointF) {
        float f = pointF.x;
        float f2 = pointF.y;
        float[] fArr = this.single;
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = fArr[3];
        float f6 = fArr[4];
        pointF.set((f3 * f) + (f5 * f2) + fArr[6], (f * f4) + (f2 * f6) + fArr[7]);
    }

    public PointF transformPoint(double d, double d2) {
        float[] fArr = this.single;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[3];
        float f4 = fArr[4];
        return new PointF((float) ((((double) f) * d) + (((double) f3) * d2) + ((double) fArr[6])), (float) ((d * ((double) f2)) + (d2 * ((double) f4)) + ((double) fArr[7])));
    }

    public Vector transform(Vector vector) {
        float[] fArr = this.single;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[3];
        float f4 = fArr[4];
        float f5 = fArr[6];
        float f6 = fArr[7];
        float x = vector.getX();
        float y = vector.getY();
        return new Vector((f * x) + (f3 * y) + f5, (x * f2) + (y * f4) + f6);
    }

    @Deprecated
    public Matrix extractScaling() {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        float[] fArr2 = this.single;
        fArr[0] = fArr2[0];
        fArr[4] = fArr2[4];
        return matrix;
    }

    public static Matrix getScaleInstance(float f, float f2) {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        fArr[0] = f;
        fArr[4] = f2;
        return matrix;
    }

    @Deprecated
    public Matrix extractTranslating() {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        float[] fArr2 = this.single;
        fArr[6] = fArr2[6];
        fArr[7] = fArr2[7];
        return matrix;
    }

    @Deprecated
    public static Matrix getTranslatingInstance(float f, float f2) {
        return getTranslateInstance(f, f2);
    }

    public static Matrix getTranslateInstance(float f, float f2) {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        fArr[6] = f;
        fArr[7] = f2;
        return matrix;
    }

    public static Matrix getRotateInstance(double d, float f, float f2) {
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        fArr[0] = fCos;
        fArr[1] = fSin;
        fArr[3] = -fSin;
        fArr[4] = fCos;
        fArr[6] = f;
        fArr[7] = f2;
        return matrix;
    }

    public static Matrix concatenate(Matrix matrix, Matrix matrix2) {
        Matrix matrixM1575clone = matrix.m1575clone();
        matrixM1575clone.concatenate(matrix2);
        return matrixM1575clone;
    }

    public Matrix m1575clone() {
        Matrix matrix = new Matrix();
        System.arraycopy(this.single, 0, matrix.single, 0, 9);
        return matrix;
    }

    public float getScalingFactorX() {
        float[] fArr = this.single;
        float f = fArr[0];
        return (fArr[1] == 0.0f && fArr[3] == 0.0f) ? f : (float) Math.sqrt(Math.pow(f, 2.0d) + Math.pow(this.single[1], 2.0d));
    }

    public float getScalingFactorY() {
        float[] fArr = this.single;
        return (fArr[1] == 0.0f && fArr[3] == 0.0f) ? fArr[4] : (float) Math.sqrt(Math.pow(fArr[3], 2.0d) + Math.pow(this.single[4], 2.0d));
    }

    public float getScaleX() {
        return this.single[0];
    }

    public float getShearY() {
        return this.single[1];
    }

    public float getShearX() {
        return this.single[3];
    }

    public float getScaleY() {
        return this.single[4];
    }

    public float getTranslateX() {
        return this.single[6];
    }

    public float getTranslateY() {
        return this.single[7];
    }

    @Deprecated
    public float getXPosition() {
        return this.single[6];
    }

    @Deprecated
    public float getYPosition() {
        return this.single[7];
    }

    public COSArray toCOSArray() {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) new COSFloat(this.single[0]));
        cOSArray.add((COSBase) new COSFloat(this.single[1]));
        cOSArray.add((COSBase) new COSFloat(this.single[3]));
        cOSArray.add((COSBase) new COSFloat(this.single[4]));
        cOSArray.add((COSBase) new COSFloat(this.single[6]));
        cOSArray.add((COSBase) new COSFloat(this.single[7]));
        return cOSArray;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        stringBuffer.append(this.single[0] + ",");
        stringBuffer.append(this.single[1] + ",");
        stringBuffer.append(this.single[3] + ",");
        stringBuffer.append(this.single[4] + ",");
        stringBuffer.append(this.single[6] + ",");
        stringBuffer.append(this.single[7] + "]");
        return stringBuffer.toString();
    }
}
