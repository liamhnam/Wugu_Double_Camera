package com.wugu.doublecamera.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ProgressColorBar extends View {
    private Bitmap bitmapBg;
    private Bitmap bitmapFg;
    private final PorterDuffXfermode duffXFermode;
    private int mHeight;
    private Paint mPaintBg;
    private Paint mPaintFg;
    private ValueAnimator mValueAnimator;
    private int mWidth;
    private final int padding;
    private float process;
    private int round;
    private final int strokeWidth;

    public ProgressColorBar(Context context) {
        this(context, null);
    }

    public ProgressColorBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.duffXFermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.padding = 0;
        this.strokeWidth = 3;
        initView(context);
    }

    public void setBitmapBgFb(Bitmap bitmap, Bitmap bitmap2) {
        this.bitmapBg = bitmap;
        this.bitmapFg = bitmap2;
        invalidate();
    }

    private void initView(Context context) {
        setLayerType(1, null);
        Paint paint = new Paint();
        this.mPaintBg = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mPaintFg = paint2;
        paint2.setAntiAlias(true);
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
        drawProgress(canvas);
    }

    private void drawProgress(Canvas canvas) {
        Bitmap bitmap;
        if (this.bitmapFg == null || (bitmap = this.bitmapBg) == null) {
            return;
        }
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mPaintBg);
        if (this.process > 0.0f) {
            int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.mPaintFg, 31);
            this.process = Math.min(this.process, this.bitmapFg.getWidth());
            RectF rectF = new RectF(3.0f, 3.0f, this.process, (this.mHeight + 0) - 3);
            int i = this.round;
            canvas.drawRoundRect(rectF, i, i, this.mPaintFg);
            this.mPaintFg.setXfermode(this.duffXFermode);
            canvas.drawBitmap(this.bitmapFg, 0.0f, 0.0f, this.mPaintFg);
            this.mPaintFg.setXfermode(null);
            canvas.restoreToCount(iSaveLayer);
        }
    }

    private void resetProcessPaint() {
        Paint paint = this.mPaintFg;
        if (paint == null) {
            return;
        }
        paint.reset();
        this.mPaintFg.setColor(Color.parseColor("#FF9900"));
        this.mPaintFg.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaintFg.setAntiAlias(true);
        this.mPaintFg.setStrokeWidth(3.0f);
    }

    public void startCountdown(int i) {
        resetProcessPaint();
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
            this.mValueAnimator = null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, (this.mWidth - 0) - 3);
        this.mValueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(((long) i) * 1000);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.m864x6c38874b(valueAnimator2);
            }
        });
        this.mValueAnimator.start();
    }

    void m864x6c38874b(ValueAnimator valueAnimator) {
        this.process = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
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

    private int defaultHeight() {
        return (int) ((getContext().getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
    }
}
