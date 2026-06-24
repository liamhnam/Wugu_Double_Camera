package com.wugu.doublecamera.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.brother.sdk.print.pdl.PrintImageUtil;
import com.wugu.doublecamera.C1910R;

public class ColorPickerView extends View {
    private static final float RADIUS_WIDTH_COLOR_RING_RATIO = 0.1066f;
    private static final float RADIUS_WIDTH_RATIO = 0.2583f;
    private static final float RADIUS_WIDTH_WHITE_RATIO = 0.272f;
    private static int colorTmp;
    private int centerWheelX;
    private int centerWheelY;
    private int centerX;
    private int centerY;
    private Bitmap colorRingBitmap;
    private Bitmap colorRingBtnBitmap;
    private PointF colorRingBtnPoint;
    private Matrix colorRingMatrix;
    private Paint colorRingPaint;
    private int colorRingRadius;
    private Bitmap colorWheelBitmap;
    private Paint colorWheelPaint;
    private int colorWheelRadius;
    private int currentColor;
    private PointF currentPoint;
    private final PointF downPointF;
    private Rect mColorWheelRect;
    private Context mContext;
    private Paint mGrayPaint;
    private Paint mPaint;
    private Bitmap markerBitmap;
    private Paint markerPaint;
    private PointF markerPoint;
    private OnColorPickerChangerListener onColorPickerChangerListener;
    private int ringWidth;
    private Paint whiteWheelPaint;
    private int whiteWheelRadius;

    public interface OnColorPickerChangerListener {
        void onColorPickerChanger(int i, int i2, int i3, int i4);
    }

    public ColorPickerView(Context context) {
        this(context, null);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.currentPoint = new PointF();
        this.markerPoint = new PointF();
        this.colorRingBtnPoint = new PointF();
        this.ringWidth = 10;
        this.downPointF = new PointF();
        this.mContext = context;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAlpha(100);
        this.colorWheelPaint = new Paint();
        Paint paint2 = new Paint();
        this.markerPaint = paint2;
        paint2.setAntiAlias(true);
        this.markerPaint.setDither(true);
        this.whiteWheelPaint = new Paint();
        this.colorRingPaint = new Paint();
        this.colorWheelPaint.setAntiAlias(true);
        this.colorWheelPaint.setDither(true);
        this.whiteWheelPaint.setAntiAlias(true);
        this.whiteWheelPaint.setDither(true);
        this.colorRingPaint.setAntiAlias(true);
        this.colorRingPaint.setDither(true);
        this.colorRingPaint.setStyle(Paint.Style.STROKE);
        this.colorRingMatrix = new Matrix();
        this.markerBitmap = BitmapFactory.decodeResource(getResources(), C1910R.mipmap.combined_shape);
        Paint paint3 = new Paint();
        this.mGrayPaint = paint3;
        paint3.setColor(-7829368);
        this.mGrayPaint.setAlpha(128);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawWhiteWheel(canvas);
        canvas.drawBitmap(this.colorWheelBitmap, this.mColorWheelRect.left, this.mColorWheelRect.top, (Paint) null);
        drawMarker(canvas);
        if (isEnabled()) {
            return;
        }
        drawGrayWheel(canvas);
    }

    private void drawColorRingBtn(Canvas canvas) {
        int width = this.colorRingBtnBitmap.getWidth();
        int height = this.colorRingBtnBitmap.getHeight();
        int i = this.centerWheelX - width;
        int i2 = (this.centerWheelY - this.colorRingRadius) - height;
        this.colorRingBtnPoint.x = i + (width / 2);
        this.colorRingBtnPoint.y = i2 + (height / 2);
        this.colorRingMatrix.preTranslate(this.colorRingBtnPoint.x, this.colorRingBtnPoint.y);
        canvas.drawBitmap(this.colorRingBtnBitmap, this.colorRingMatrix, null);
        this.colorRingMatrix.reset();
    }

    private void drawWhiteWheel(Canvas canvas) {
        this.whiteWheelPaint.setColor(-1);
        canvas.drawCircle(this.centerWheelX, this.centerWheelY, this.whiteWheelRadius, this.whiteWheelPaint);
    }

    private void drawGrayWheel(Canvas canvas) {
        canvas.drawCircle(this.centerWheelX, this.centerWheelY, this.whiteWheelRadius, this.mGrayPaint);
    }

    private void drawMarker(Canvas canvas) {
        float width = this.markerBitmap.getWidth();
        float height = this.markerBitmap.getHeight();
        float f = width / 2.0f;
        float f2 = this.markerPoint.x - f;
        float f3 = (this.markerPoint.y - height) + ((1.0f * height) / 10.0f);
        RectF rectF = new RectF(f2, f3, f2 + width, height + f3);
        this.markerPaint.setColor(this.currentColor);
        canvas.drawBitmap(this.markerBitmap, (Rect) null, rectF, (Paint) null);
        canvas.drawCircle(f2 + f, (f3 + f) - 4.0f, width / 3.0f, this.markerPaint);
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.centerX = i / 2;
        int i5 = i2 / 2;
        this.centerY = i5;
        if (i2 < i) {
            i = i2;
        }
        float f = i;
        int i6 = (int) (RADIUS_WIDTH_RATIO * f);
        this.colorWheelRadius = i6;
        int i7 = (int) (RADIUS_WIDTH_WHITE_RATIO * f);
        this.whiteWheelRadius = i7;
        int i8 = (int) (f * RADIUS_WIDTH_COLOR_RING_RATIO);
        this.colorRingRadius = i8;
        this.centerY = i5 - ((i5 - i8) / 2);
        int i9 = i7 - i6;
        this.ringWidth = i9;
        this.colorRingPaint.setStrokeWidth(i9);
        int i10 = this.centerX;
        int i11 = this.colorWheelRadius;
        int i12 = this.centerY;
        this.mColorWheelRect = new Rect(i10 - i11, i12 - i11, i10 + i11, i12 + i11);
        int i13 = this.colorWheelRadius;
        this.colorWheelBitmap = createColorWheelBitmap(i13 * 2, i13 * 2);
        this.centerWheelX = this.mColorWheelRect.left + this.colorWheelRadius;
        this.centerWheelY = this.mColorWheelRect.top + this.colorWheelRadius;
        this.markerPoint.x = this.centerWheelX;
        this.markerPoint.y = this.centerWheelY;
    }

    private Bitmap createColorRingBitmap(int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        int[] iArr = new int[13];
        float[] fArr = {0.0f, 1.0f, 1.0f};
        for (int i3 = 0; i3 < 13; i3++) {
            fArr[0] = 360 - ((i3 * 30) % PrintImageUtil.ROUND_ROTATE);
            iArr[i3] = Color.HSVToColor(fArr);
        }
        iArr[12] = iArr[0];
        float f = i / 2;
        float f2 = i2 / 2;
        this.colorRingPaint.setShader(new ComposeShader(new SweepGradient(f, f2, iArr, (float[]) null), new RadialGradient(f, f2, this.colorRingRadius, -1, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.CLAMP), PorterDuff.Mode.SRC_OVER));
        new Canvas(bitmapCreateBitmap).drawCircle(f, f2, this.colorRingRadius, this.colorRingPaint);
        return bitmapCreateBitmap;
    }

    private Bitmap createColorWheelBitmap(int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        int[] iArr = new int[13];
        float[] fArr = {0.0f, 1.0f, 1.0f};
        for (int i3 = 0; i3 < 13; i3++) {
            fArr[0] = 360 - ((i3 * 30) % PrintImageUtil.ROUND_ROTATE);
            iArr[i3] = Color.HSVToColor(fArr);
        }
        iArr[12] = iArr[0];
        float f = i / 2;
        float f2 = i2 / 2;
        this.colorWheelPaint.setShader(new ComposeShader(new SweepGradient(f, f2, iArr, (float[]) null), new RadialGradient(f, f2, this.colorWheelRadius, -1, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.CLAMP), PorterDuff.Mode.SRC_OVER));
        new Canvas(bitmapCreateBitmap).drawCircle(f, f2, this.colorWheelRadius, this.colorWheelPaint);
        this.currentColor = getColorAtPoint(this.markerPoint.x, this.markerPoint.y);
        return bitmapCreateBitmap;
    }

    @Override
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            colorTmp = this.currentColor;
            this.downPointF.x = motionEvent.getX();
            this.downPointF.y = motionEvent.getY();
        } else {
            if (actionMasked == 1) {
                if (colorTmp != this.currentColor) {
                    onColorPickerChanger();
                }
                return super.onTouchEvent(motionEvent);
            }
            if (actionMasked != 2) {
                return true;
            }
        }
        update(motionEvent);
        return true;
    }

    private void update(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        updateSelector(x, y);
        updateRingSelector(x, y);
    }

    private int getColorAtPoint(float f, float f2) {
        float f3 = f - this.centerWheelX;
        double dSqrt = Math.sqrt((f3 * f3) + (r7 * r7));
        float[] fArr = {0.0f, 0.0f, 1.0f};
        fArr[0] = ((float) ((Math.atan2(f2 - this.centerWheelY, -f3) / 3.141592653589793d) * 180.0d)) + 180.0f;
        fArr[1] = Math.max(0.0f, Math.min(1.0f, (float) (dSqrt / ((double) this.colorWheelRadius))));
        return Color.HSVToColor(fArr);
    }

    private float[] getHSVColorAtPoint(float f, float f2) {
        float f3 = f - this.centerWheelX;
        double dSqrt = Math.sqrt((f3 * f3) + (r7 * r7));
        float[] fArr = {0.0f, 0.0f, 1.0f};
        fArr[0] = ((float) ((Math.atan2(f2 - this.centerWheelY, -f3) / 3.141592653589793d) * 180.0d)) + 180.0f;
        fArr[1] = Math.max(0.0f, Math.min(1.0f, (float) (dSqrt / ((double) this.colorWheelRadius))));
        return fArr;
    }

    private void updateSelector(float f, float f2) {
        float f3 = f - this.centerWheelX;
        float f4 = f2 - this.centerWheelY;
        if (Math.sqrt((f3 * f3) + (f4 * f4)) > this.colorWheelRadius) {
            return;
        }
        this.colorRingMatrix.preRotate(getRotationBetweenLines(this.centerWheelX, this.centerWheelY, f, f2), this.centerWheelX, this.centerWheelY);
        this.currentPoint.x = f3 + this.centerWheelX;
        this.currentPoint.y = f4 + this.centerWheelY;
        this.markerPoint.x = this.currentPoint.x;
        this.markerPoint.y = this.currentPoint.y;
        this.currentColor = getColorAtPoint(f, f2);
        invalidate();
    }

    private void updateRingSelector(float f, float f2) {
        float f3 = this.downPointF.x - this.centerWheelX;
        float f4 = this.downPointF.y - this.centerWheelY;
        double dSqrt = Math.sqrt((f3 * f3) + (f4 * f4));
        int i = this.colorRingRadius;
        int i2 = this.ringWidth;
        if (dSqrt >= i + i2 || dSqrt <= i - i2) {
            return;
        }
        this.colorRingMatrix.preRotate(getRotationBetweenLines(this.centerWheelX, this.centerWheelY, f, f2), this.centerWheelX, this.centerWheelY);
        this.currentColor = getColorAtPoint(f, f2);
        float[] hSVColorAtPoint = getHSVColorAtPoint(f, f2);
        float f5 = hSVColorAtPoint[0];
        float f6 = this.colorWheelRadius * hSVColorAtPoint[1];
        double radians = (float) Math.toRadians(-f5);
        double d = f6;
        float fCos = (float) (((double) this.centerWheelX) + (Math.cos(radians) * d));
        float fSin = (float) (((double) this.centerWheelY) + (Math.sin(radians) * d));
        this.markerPoint.x = fCos;
        this.markerPoint.y = fSin;
        invalidate();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getRotationBetweenLines(float r8, float r9, float r10, float r11) {
        /*
            float r0 = r9 - r9
            double r0 = (double) r0
            r2 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 * r8
            float r2 = r2 - r8
            double r2 = (double) r2
            double r0 = r0 / r2
            float r2 = r11 - r9
            double r2 = (double) r2
            float r4 = r10 - r8
            double r4 = (double) r4
            double r2 = r2 / r4
            double r4 = r0 - r2
            double r4 = java.lang.Math.abs(r4)
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r0 = r0 * r2
            double r0 = r0 + r6
            double r4 = r4 / r0
            double r0 = java.lang.Math.atan(r4)
            r2 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r0 = r0 / r2
            r2 = 4640537203540230144(0x4066800000000000, double:180.0)
            double r0 = r0 * r2
            int r4 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            r5 = 4636033603912859648(0x4056800000000000, double:90.0)
            if (r4 <= 0) goto L3b
            int r7 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r7 >= 0) goto L3b
        L38:
            double r2 = r5 - r0
            goto L6a
        L3b:
            if (r4 <= 0) goto L44
            int r7 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r7 <= 0) goto L44
        L41:
            double r2 = r0 + r5
            goto L6a
        L44:
            int r8 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            r5 = 4643457506423603200(0x4070e00000000000, double:270.0)
            if (r8 >= 0) goto L52
            int r10 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r10 <= 0) goto L52
            goto L38
        L52:
            if (r8 >= 0) goto L59
            int r8 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r8 >= 0) goto L59
            goto L41
        L59:
            r0 = 0
            if (r4 != 0) goto L62
            int r8 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r8 >= 0) goto L62
            goto L69
        L62:
            if (r4 != 0) goto L69
            int r8 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r8 <= 0) goto L69
            goto L6a
        L69:
            r2 = r0
        L6a:
            int r8 = (int) r2
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.custom.ColorPickerView.getRotationBetweenLines(float, float, float, float):int");
    }

    private void onColorPickerChanger() {
        OnColorPickerChangerListener onColorPickerChangerListener = this.onColorPickerChangerListener;
        if (onColorPickerChangerListener != null) {
            int i = this.currentColor;
            onColorPickerChangerListener.onColorPickerChanger(i, (16711680 & i) >> 16, (65280 & i) >> 8, i & 255);
        }
    }

    public void setColor(int i) {
        float[] fArr = {0.0f, 0.0f, 1.0f};
        Color.colorToHSV(i, fArr);
        double radians = (float) Math.toRadians(-fArr[0]);
        double d = fArr[1] * this.colorWheelRadius;
        float fCos = (float) (((double) this.centerWheelX) + (Math.cos(radians) * d));
        float fSin = (float) (((double) this.centerWheelY) + (Math.sin(radians) * d));
        this.markerPoint.x = fCos;
        this.markerPoint.y = fSin;
        this.currentColor = getColorAtPoint(this.markerPoint.x, this.markerPoint.y);
        this.colorRingMatrix.preRotate(getRotationBetweenLines(this.centerWheelX, this.centerWheelY, this.markerPoint.x, this.markerPoint.y), this.centerWheelX, this.centerWheelY);
        invalidate();
    }

    public int getColor() {
        return this.currentColor;
    }

    public int[] getColorRGB() {
        int i = this.currentColor;
        return new int[]{(16711680 & i) >> 16, (65280 & i) >> 8, i & 255};
    }

    public void setOnColorPickerChangerListener(OnColorPickerChangerListener onColorPickerChangerListener) {
        this.onColorPickerChangerListener = onColorPickerChangerListener;
    }
}
