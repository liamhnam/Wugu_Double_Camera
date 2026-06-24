package com.wugu.doublecamera.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.content.ContextCompat;
import com.wugu.doublecamera.C1910R;

public class CountdownBar extends View {
    private int countdown;
    private int mHeight;
    private Paint mPaint;
    private Paint mPaintRoundRect;
    private Paint mPaintText;
    private Paint mPaintTextStroke;
    private ValueAnimator mValueAnimator;
    private int mWidth;
    private int padding;
    private float process;
    private int round;
    private int strokeWidth;
    private int textSize;
    private final int textSpace;

    public CountdownBar(Context context) {
        this(context, null);
    }

    public CountdownBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CountdownBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.padding = 5;
        this.strokeWidth = 3;
        this.textSize = 35;
        this.textSpace = 100;
        init(context);
    }

    private void init(Context context) {
        Paint paint = new Paint();
        this.mPaintRoundRect = paint;
        paint.setColor(getResources().getColor(C1910R.color.theme_pink));
        this.mPaintRoundRect.setAntiAlias(true);
        this.mPaintRoundRect.setStyle(Paint.Style.STROKE);
        this.mPaintRoundRect.setStrokeWidth(this.strokeWidth);
        Paint paint2 = new Paint();
        this.mPaint = paint2;
        paint2.setColor(Color.parseColor("#FF9900"));
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(this.strokeWidth);
        Paint paint3 = new Paint();
        this.mPaintText = paint3;
        paint3.setAntiAlias(true);
        this.mPaintText.setStyle(Paint.Style.FILL);
        this.mPaintText.setColor(ContextCompat.getColor(context, C1910R.color.white));
        this.mPaintText.setTextSize(sp2px(this.textSize));
        Paint paint4 = new Paint();
        this.mPaintTextStroke = paint4;
        paint4.setAntiAlias(true);
        this.mPaintTextStroke.setStyle(Paint.Style.STROKE);
        this.mPaintTextStroke.setColor(ContextCompat.getColor(context, C1910R.color.theme_pink));
        this.mPaintTextStroke.setTextSize(sp2px(this.textSize));
        this.mPaintTextStroke.setStrokeWidth(3.0f);
    }

    public void setPadding(int i) {
        this.padding = i;
    }

    public void setStrokeWidth(int i) {
        this.strokeWidth = i;
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 || mode == Integer.MIN_VALUE) {
            this.mWidth = size;
        } else {
            this.mWidth = 0;
        }
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            this.mHeight = defaultHeight();
        } else {
            this.mHeight = size2;
        }
        int i3 = this.mHeight;
        this.round = i3 / 2;
        setMeasuredDimension(this.mWidth, i3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawProgress(canvas);
        updateText(canvas);
    }

    private void drawBackground(Canvas canvas) {
        int i = this.padding;
        RectF rectF = new RectF(i, i, (this.mWidth - i) - 100, this.mHeight - i);
        int i2 = this.round;
        canvas.drawRoundRect(rectF, i2, i2, this.mPaintRoundRect);
    }

    private void drawProgress(Canvas canvas) {
        if (this.process > 0.0f) {
            int i = this.padding;
            int i2 = this.strokeWidth;
            RectF rectF = new RectF(i + i2, i + i2, this.process, (this.mHeight - i) - i2);
            int i3 = this.round;
            canvas.drawRoundRect(rectF, i3, i3, this.mPaint);
        }
    }

    private void updateText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = this.mPaintText.getFontMetrics();
        float width = (getWidth() - 100) + 10;
        float height = (getHeight() / 2) + (((int) Math.ceil(fontMetrics.descent - fontMetrics.ascent)) / 4) + 3;
        canvas.drawText(String.valueOf(this.countdown), width, height, this.mPaintTextStroke);
        canvas.drawText(String.valueOf(this.countdown), width, height, this.mPaintText);
    }

    private void resetProcessPaint() {
        Paint paint = this.mPaint;
        if (paint == null) {
            return;
        }
        paint.reset();
        this.mPaint.setColor(Color.parseColor("#FF9900"));
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(this.strokeWidth);
    }

    public void startCountdown(int i) {
        resetProcessPaint();
        this.countdown = i;
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
            this.mValueAnimator = null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, ((this.mWidth - this.padding) - this.strokeWidth) - 100);
        this.mValueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(((long) i) * 1000);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.m842xcb01f370(valueAnimator2);
            }
        });
        this.mValueAnimator.start();
    }

    void m842xcb01f370(ValueAnimator valueAnimator) {
        this.process = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void setCountdownValue(int i) {
        this.countdown = i;
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            invalidate();
        }
    }

    public void resumeCountdown() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.resume();
        }
    }

    public void pauseCountdown() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.pause();
        }
    }

    public void cancelCountdown() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mValueAnimator.setTarget(null);
        }
        this.process = 0.0f;
    }

    private int sp2px(int i) {
        return (int) TypedValue.applyDimension(2, i, getResources().getDisplayMetrics());
    }

    private int defaultHeight() {
        return (int) ((getContext().getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
    }
}
