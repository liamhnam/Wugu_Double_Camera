package com.wugu.doublecamera.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import com.wugu.doublecamera.C1910R;

public class StrokeTextView extends AppCompatTextView {
    private AppCompatTextView backGroundText;

    public StrokeTextView(Context context) {
        this(context, null);
    }

    public StrokeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StrokeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.backGroundText = new AppCompatTextView(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1910R.styleable.StrokeTextView);
        float dimension = typedArrayObtainStyledAttributes.getDimension(C1910R.styleable.StrokeTextView_strokeWidth, 2.0f);
        int color = typedArrayObtainStyledAttributes.getColor(C1910R.styleable.StrokeTextView_strokeColor, Color.parseColor("#FF0000"));
        typedArrayObtainStyledAttributes.recycle();
        init(dimension, color);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.backGroundText.setLayoutParams(layoutParams);
        super.setLayoutParams(layoutParams);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        CharSequence text = this.backGroundText.getText();
        if (text == null || !text.equals(getText())) {
            this.backGroundText.setText(getText());
            postInvalidate();
        }
        this.backGroundText.measure(i, i2);
        super.onMeasure(i, i2);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.backGroundText.layout(i, i2, i3, i4);
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.backGroundText.draw(canvas);
        super.onDraw(canvas);
    }

    public void setText(String str) {
        super.setText((CharSequence) str);
        this.backGroundText.setText(str);
        invalidate();
    }

    @Override
    public void setTextSize(float f) {
        super.setTextSize(f);
        this.backGroundText.setTextSize(f);
    }

    public void setType(Typeface typeface, int i) {
        super.setTypeface(typeface, i);
        this.backGroundText.setTypeface(typeface, i);
        invalidate();
    }

    public void init(float f, int i) {
        TextPaint paint = this.backGroundText.getPaint();
        paint.setStrokeWidth(f);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.backGroundText.setTextColor(i);
        this.backGroundText.setGravity(getGravity());
    }
}
