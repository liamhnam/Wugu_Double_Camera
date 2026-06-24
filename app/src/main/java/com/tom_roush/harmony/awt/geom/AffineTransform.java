package com.tom_roush.harmony.awt.geom;

import android.graphics.Matrix;
import android.graphics.PointF;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AffineTransform implements Cloneable, Serializable {
    public static final int TYPE_FLIP = 64;
    public static final int TYPE_GENERAL_ROTATION = 16;
    public static final int TYPE_GENERAL_SCALE = 4;
    public static final int TYPE_GENERAL_TRANSFORM = 32;
    public static final int TYPE_IDENTITY = 0;
    public static final int TYPE_MASK_ROTATION = 24;
    public static final int TYPE_MASK_SCALE = 6;
    public static final int TYPE_QUADRANT_ROTATION = 8;
    public static final int TYPE_TRANSLATION = 1;
    public static final int TYPE_UNIFORM_SCALE = 2;
    static final int TYPE_UNKNOWN = -1;
    static final double ZERO = 1.0E-10d;
    private static final long serialVersionUID = 1330973210523860834L;
    double m00;
    double m01;
    double m02;
    double m10;
    double m11;
    double m12;
    transient int type;

    public AffineTransform() {
        this.type = 0;
        this.m11 = 1.0d;
        this.m00 = 1.0d;
        this.m12 = 0.0d;
        this.m02 = 0.0d;
        this.m01 = 0.0d;
        this.m10 = 0.0d;
    }

    public AffineTransform(AffineTransform affineTransform) {
        this.type = affineTransform.type;
        this.m00 = affineTransform.m00;
        this.m10 = affineTransform.m10;
        this.m01 = affineTransform.m01;
        this.m11 = affineTransform.m11;
        this.m02 = affineTransform.m02;
        this.m12 = affineTransform.m12;
    }

    public AffineTransform(float f, float f2, float f3, float f4, float f5, float f6) {
        this.type = -1;
        this.m00 = f;
        this.m10 = f2;
        this.m01 = f3;
        this.m11 = f4;
        this.m02 = f5;
        this.m12 = f6;
    }

    public AffineTransform(double d, double d2, double d3, double d4, double d5, double d6) {
        this.type = -1;
        this.m00 = d;
        this.m10 = d2;
        this.m01 = d3;
        this.m11 = d4;
        this.m02 = d5;
        this.m12 = d6;
    }

    public AffineTransform(float[] fArr) {
        this.type = -1;
        this.m00 = fArr[0];
        this.m10 = fArr[1];
        this.m01 = fArr[2];
        this.m11 = fArr[3];
        if (fArr.length > 4) {
            this.m02 = fArr[4];
            this.m12 = fArr[5];
        }
    }

    public AffineTransform(double[] dArr) {
        this.type = -1;
        this.m00 = dArr[0];
        this.m10 = dArr[1];
        this.m01 = dArr[2];
        this.m11 = dArr[3];
        if (dArr.length > 4) {
            this.m02 = dArr[4];
            this.m12 = dArr[5];
        }
    }

    public AffineTransform(Matrix matrix) {
        matrix.getValues(new float[9]);
        this.m00 = r0[0];
        this.m01 = r0[1];
        this.m02 = r0[2];
        this.m10 = r0[3];
        this.m11 = r0[4];
        this.m12 = r0[5];
    }

    public int getType() {
        int i;
        int i2 = this.type;
        if (i2 != -1) {
            return i2;
        }
        double d = this.m00;
        double d2 = this.m01;
        double d3 = this.m10;
        double d4 = this.m11;
        if ((d * d2) + (d3 * d4) != 0.0d) {
            return 32;
        }
        if (this.m02 == 0.0d && this.m12 == 0.0d) {
            i = 0;
            if (d == 1.0d && d4 == 1.0d && d2 == 0.0d && d3 == 0.0d) {
                return 0;
            }
        } else {
            i = 1;
        }
        if ((d * d4) - (d2 * d3) < 0.0d) {
            i |= 64;
        }
        double d5 = (d * d) + (d3 * d3);
        if (d5 != (d2 * d2) + (d4 * d4)) {
            i |= 4;
        } else if (d5 != 1.0d) {
            i |= 2;
        }
        return ((d == 0.0d && d4 == 0.0d) || (d3 == 0.0d && d2 == 0.0d && (d < 0.0d || d4 < 0.0d))) ? i | 8 : (d2 == 0.0d && d3 == 0.0d) ? i : i | 16;
    }

    public double getScaleX() {
        return this.m00;
    }

    public double getScaleY() {
        return this.m11;
    }

    public double getShearX() {
        return this.m01;
    }

    public double getShearY() {
        return this.m10;
    }

    public double getTranslateX() {
        return this.m02;
    }

    public double getTranslateY() {
        return this.m12;
    }

    public boolean isIdentity() {
        return getType() == 0;
    }

    public void getMatrix(double[] dArr) {
        dArr[0] = this.m00;
        dArr[1] = this.m10;
        dArr[2] = this.m01;
        dArr[3] = this.m11;
        if (dArr.length > 4) {
            dArr[4] = this.m02;
            dArr[5] = this.m12;
        }
    }

    public double getDeterminant() {
        return (this.m00 * this.m11) - (this.m01 * this.m10);
    }

    public void setTransform(double d, double d2, double d3, double d4, double d5, double d6) {
        this.type = -1;
        this.m00 = d;
        this.m10 = d2;
        this.m01 = d3;
        this.m11 = d4;
        this.m02 = d5;
        this.m12 = d6;
    }

    public void setTransform(AffineTransform affineTransform) {
        this.type = affineTransform.type;
        setTransform(affineTransform.m00, affineTransform.m10, affineTransform.m01, affineTransform.m11, affineTransform.m02, affineTransform.m12);
    }

    public void setToIdentity() {
        this.type = 0;
        this.m11 = 1.0d;
        this.m00 = 1.0d;
        this.m12 = 0.0d;
        this.m02 = 0.0d;
        this.m01 = 0.0d;
        this.m10 = 0.0d;
    }

    public void setToTranslation(double d, double d2) {
        this.m11 = 1.0d;
        this.m00 = 1.0d;
        this.m10 = 0.0d;
        this.m01 = 0.0d;
        this.m02 = d;
        this.m12 = d2;
        if (d == 0.0d && d2 == 0.0d) {
            this.type = 0;
        } else {
            this.type = 1;
        }
    }

    public void setToScale(double d, double d2) {
        this.m00 = d;
        this.m11 = d2;
        this.m12 = 0.0d;
        this.m02 = 0.0d;
        this.m01 = 0.0d;
        this.m10 = 0.0d;
        if (d != 1.0d || d2 != 1.0d) {
            this.type = -1;
        } else {
            this.type = 0;
        }
    }

    public void setToShear(double d, double d2) {
        this.m11 = 1.0d;
        this.m00 = 1.0d;
        this.m12 = 0.0d;
        this.m02 = 0.0d;
        this.m01 = d;
        this.m10 = d2;
        if (d != 0.0d || d2 != 0.0d) {
            this.type = -1;
        } else {
            this.type = 0;
        }
    }

    public void setToRotation(double d) {
        double dSin = Math.sin(d);
        double dCos = Math.cos(d);
        if (Math.abs(dCos) < ZERO) {
            dSin = dSin > 0.0d ? 1.0d : -1.0d;
            dCos = 0.0d;
        } else if (Math.abs(dSin) < ZERO) {
            dCos = dCos > 0.0d ? 1.0d : -1.0d;
            dSin = 0.0d;
        }
        this.m11 = dCos;
        this.m00 = dCos;
        this.m01 = -dSin;
        this.m10 = dSin;
        this.m12 = 0.0d;
        this.m02 = 0.0d;
        this.type = -1;
    }

    public void setToRotation(double d, double d2, double d3) {
        setToRotation(d);
        double d4 = this.m00;
        double d5 = this.m10;
        this.m02 = ((1.0d - d4) * d2) + (d3 * d5);
        this.m12 = (d3 * (1.0d - d4)) - (d2 * d5);
        this.type = -1;
    }

    public static AffineTransform getTranslateInstance(double d, double d2) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToTranslation(d, d2);
        return affineTransform;
    }

    public static AffineTransform getScaleInstance(double d, double d2) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToScale(d, d2);
        return affineTransform;
    }

    public static AffineTransform getShearInstance(double d, double d2) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToShear(d, d2);
        return affineTransform;
    }

    public static AffineTransform getRotateInstance(double d) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToRotation(d);
        return affineTransform;
    }

    public static AffineTransform getRotateInstance(double d, double d2, double d3) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToRotation(d, d2, d3);
        return affineTransform;
    }

    public void translate(double d, double d2) {
        concatenate(getTranslateInstance(d, d2));
    }

    public void scale(double d, double d2) {
        concatenate(getScaleInstance(d, d2));
    }

    public void shear(double d, double d2) {
        concatenate(getShearInstance(d, d2));
    }

    public void rotate(double d) {
        concatenate(getRotateInstance(d));
    }

    public void rotate(double d, double d2, double d3) {
        concatenate(getRotateInstance(d, d2, d3));
    }

    AffineTransform multiply(AffineTransform affineTransform, AffineTransform affineTransform2) {
        double d = affineTransform.m00;
        double d2 = affineTransform2.m00;
        double d3 = affineTransform.m10;
        double d4 = affineTransform2.m01;
        double d5 = (d * d2) + (d3 * d4);
        double d6 = affineTransform2.m10;
        double d7 = affineTransform2.m11;
        double d8 = (d3 * d7) + (d * d6);
        double d9 = affineTransform.m01;
        double d10 = affineTransform.m11;
        double d11 = (d9 * d2) + (d10 * d4);
        double d12 = (d10 * d7) + (d9 * d6);
        double d13 = affineTransform.m02;
        double d14 = affineTransform.m12;
        return new AffineTransform(d5, d8, d11, d12, affineTransform2.m02 + (d2 * d13) + (d4 * d14), (d13 * d6) + (d14 * d7) + affineTransform2.m12);
    }

    public void concatenate(AffineTransform affineTransform) {
        setTransform(multiply(affineTransform, this));
    }

    public void preConcatenate(AffineTransform affineTransform) {
        setTransform(multiply(this, affineTransform));
    }

    public AffineTransform createInverse() throws NoninvertibleTransformException {
        double determinant = getDeterminant();
        if (Math.abs(determinant) < ZERO) {
            throw new NoninvertibleTransformException("Determinant is zero");
        }
        double d = this.m11;
        double d2 = this.m10;
        double d3 = this.m01;
        double d4 = (-d3) / determinant;
        double d5 = this.m00;
        double d6 = this.m12;
        double d7 = this.m02;
        return new AffineTransform(d / determinant, (-d2) / determinant, d4, d5 / determinant, ((d3 * d6) - (d * d7)) / determinant, ((d2 * d7) - (d5 * d6)) / determinant);
    }

    public PointF transform(PointF pointF, PointF pointF2) {
        pointF2.set((float) ((((double) pointF.x) * this.m00) + (((double) pointF.y) * this.m01) + this.m02), (float) ((((double) pointF.x) * this.m10) + (((double) pointF.y) * this.m11) + this.m12));
        return pointF2;
    }

    public void transform(PointF[] pointFArr, int i, PointF[] pointFArr2, int i2, int i3) {
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            }
            int i4 = i + 1;
            PointF pointF = pointFArr[i];
            PointF pointF2 = pointFArr2[i2];
            if (pointF2 == null) {
                pointF2 = new PointF();
            }
            pointF2.set((float) ((((double) pointF.x) * this.m00) + (((double) pointF.y) * this.m01) + this.m02), (float) ((((double) pointF.x) * this.m10) + (((double) pointF.y) * this.m11) + this.m12));
            pointFArr2[i2] = pointF2;
            i2++;
            i = i4;
        }
    }

    public void transform(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        int i4;
        int i5;
        int i6 = 2;
        if (dArr == dArr2 && i < i2 && i2 < (i5 = i + (i4 = i3 * 2))) {
            i = i5 - 2;
            i2 = (i2 + i4) - 2;
            i6 = -2;
        }
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            }
            double d = dArr[i + 0];
            double d2 = dArr[i + 1];
            dArr2[i2 + 0] = (this.m00 * d) + (this.m01 * d2) + this.m02;
            dArr2[i2 + 1] = (d * this.m10) + (d2 * this.m11) + this.m12;
            i += i6;
            i2 += i6;
        }
    }

    public void transform(float[] fArr, int i, float[] fArr2, int i2, int i3) {
        int i4;
        int i5;
        int i6 = 2;
        if (fArr == fArr2 && i < i2 && i2 < (i5 = i + (i4 = i3 * 2))) {
            i = i5 - 2;
            i2 = (i2 + i4) - 2;
            i6 = -2;
        }
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            }
            double d = fArr[i + 0];
            double d2 = fArr[i + 1];
            fArr2[i2 + 0] = (float) ((this.m00 * d) + (this.m01 * d2) + this.m02);
            fArr2[i2 + 1] = (float) ((d * this.m10) + (d2 * this.m11) + this.m12);
            i += i6;
            i2 += i6;
        }
    }

    public void transform(float[] fArr, int i, double[] dArr, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        while (true) {
            i6--;
            if (i6 < 0) {
                return;
            }
            int i7 = i4 + 1;
            int i8 = i5 + 1;
            double d = fArr[i4];
            double d2 = fArr[i7];
            dArr[i5] = (this.m00 * d) + (this.m01 * d2) + this.m02;
            i5 = i8 + 1;
            dArr[i8] = (d * this.m10) + (d2 * this.m11) + this.m12;
            i4 = i7 + 1;
        }
    }

    public void transform(double[] dArr, int i, float[] fArr, int i2, int i3) {
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            }
            int i4 = i + 1;
            double d = dArr[i];
            i = i4 + 1;
            double d2 = dArr[i4];
            int i5 = i2 + 1;
            fArr[i2] = (float) ((this.m00 * d) + (this.m01 * d2) + this.m02);
            i2 = i5 + 1;
            fArr[i5] = (float) ((d * this.m10) + (d2 * this.m11) + this.m12);
        }
    }

    public PointF deltaTransform(PointF pointF, PointF pointF2) {
        if (pointF2 == null) {
            pointF2 = new PointF();
        }
        pointF2.set((float) ((((double) pointF.x) * this.m00) + (((double) pointF.y) * this.m01)), (float) ((((double) pointF.x) * this.m10) + (((double) pointF.y) * this.m11)));
        return pointF2;
    }

    public void deltaTransform(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            }
            int i4 = i + 1;
            double d = dArr[i];
            i = i4 + 1;
            double d2 = dArr[i4];
            int i5 = i2 + 1;
            dArr2[i2] = (this.m00 * d) + (this.m01 * d2);
            i2 = i5 + 1;
            dArr2[i5] = (d * this.m10) + (d2 * this.m11);
        }
    }

    public PointF inverseTransform(PointF pointF, PointF pointF2) throws NoninvertibleTransformException {
        double determinant = getDeterminant();
        if (Math.abs(determinant) < ZERO) {
            throw new NoninvertibleTransformException("Determinant is zero");
        }
        if (pointF2 == null) {
            pointF2 = new PointF();
        }
        double d = (float) (((double) pointF.x) - this.m02);
        double d2 = (float) (((double) pointF.y) - this.m12);
        pointF2.set((float) (((this.m11 * d) - (this.m01 * d2)) / determinant), (float) (((d2 * this.m00) - (d * this.m10)) / determinant));
        return pointF2;
    }

    public void inverseTransform(double[] dArr, int i, double[] dArr2, int i2, int i3) throws NoninvertibleTransformException {
        double determinant = getDeterminant();
        if (Math.abs(determinant) < ZERO) {
            throw new NoninvertibleTransformException("Determinant is zero");
        }
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        while (true) {
            i6--;
            if (i6 < 0) {
                return;
            }
            int i7 = i4 + 1;
            double d = dArr[i4] - this.m02;
            i4 = i7 + 1;
            double d2 = dArr[i7] - this.m12;
            int i8 = i5 + 1;
            dArr2[i5] = ((this.m11 * d) - (this.m01 * d2)) / determinant;
            i5 = i8 + 1;
            dArr2[i8] = ((d2 * this.m00) - (d * this.m10)) / determinant;
        }
    }

    public String toString() {
        return getClass().getName() + "[[" + this.m00 + ", " + this.m01 + ", " + this.m02 + "], [" + this.m10 + ", " + this.m11 + ", " + this.m12 + "]]";
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AffineTransform)) {
            return false;
        }
        AffineTransform affineTransform = (AffineTransform) obj;
        return this.m00 == affineTransform.m00 && this.m01 == affineTransform.m01 && this.m02 == affineTransform.m02 && this.m10 == affineTransform.m10 && this.m11 == affineTransform.m11 && this.m12 == affineTransform.m12;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.type = -1;
    }

    public Matrix toMatrix() {
        Matrix matrix = new Matrix();
        matrix.setValues(new float[]{(float) this.m00, (float) this.m01, (float) this.m02, (float) this.m10, (float) this.m11, (float) this.m12, 0.0f, 0.0f, 1.0f});
        return matrix;
    }

    public class NoninvertibleTransformException extends Exception {
        private static final long serialVersionUID = 6137225240503990466L;

        public NoninvertibleTransformException(String str) {
            super(str);
        }
    }
}
