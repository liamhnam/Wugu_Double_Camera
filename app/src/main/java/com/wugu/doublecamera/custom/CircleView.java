package com.wugu.doublecamera.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.wugu.doublecamera.C1910R;

public class CircleView extends View {
    private int circleColor;
    private final Paint paint;

    public CircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C1910R.styleable.CircleView, 0, 0);
        try {
            this.circleColor = typedArrayObtainStyledAttributes.getColor(C1910R.styleable.CircleView_circleColor, ViewCompat.MEASURED_STATE_MASK);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setColor(-5592406);
        this.paint.setStyle(Paint.Style.STROKE);
        float f = 1;
        this.paint.setStrokeWidth(f);
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int iMin = (int) ((Math.min(width, height) / 2) - (f / 2.0f));
        int paddingLeft = getPaddingLeft() + (width / 2);
        int paddingTop = getPaddingTop() + (height / 2);
        float f2 = paddingLeft;
        float f3 = paddingTop;
        canvas.drawCircle(f2, f3, iMin, this.paint);
        this.paint.setColor(this.circleColor);
        this.paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(f2, f3, (iMin - 1) + 0.2f, this.paint);
    }

    public int getColor() {
        return this.circleColor;
    }

    public void setColor(int i) {
        this.circleColor = i;
        invalidate();
    }
}
