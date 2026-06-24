package com.wugu.doublecamera.custom;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.View;

public class AutoSizeGLSurface extends GLSurfaceView {
    private int frameH;
    private int frameW;

    public AutoSizeGLSurface(Context context) {
        super(context);
        this.frameW = 0;
        this.frameH = 0;
    }

    public AutoSizeGLSurface(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.frameW = 0;
        this.frameH = 0;
    }

    public void setAspectRation(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width or height can not be negative.");
        }
        this.frameW = i;
        this.frameH = i2;
        requestLayout();
    }

    @Override
    protected void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i4 = this.frameW;
        if (i4 == 0 || (i3 = this.frameH) == 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        float f = size;
        float f2 = size2;
        float f3 = i4 / i3;
        if (f / f2 > f3) {
            setMeasuredDimension((int) (f2 * f3), size2);
        } else {
            setMeasuredDimension(size, (int) (f / f3));
        }
    }
}
