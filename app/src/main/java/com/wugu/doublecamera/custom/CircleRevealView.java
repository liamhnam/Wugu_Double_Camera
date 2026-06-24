package com.wugu.doublecamera.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.view.ViewCompat;
import com.wugu.doublecamera.utils.AppUtil;

public class CircleRevealView extends View {
    private ValueAnimator animator;
    private ValueAnimator animatorEnd;
    private PorterDuffXfermode duffXfermode;
    private boolean isStart;
    private Paint mPaint;
    private float radius;

    public CircleRevealView(Context context) {
        super(context);
        this.radius = 0.0f;
        this.isStart = false;
        init();
    }

    public CircleRevealView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.radius = 0.0f;
        this.isStart = false;
        init();
    }

    public CircleRevealView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.radius = 0.0f;
        this.isStart = false;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(-1);
        ValueAnimator valueAnimatorOfFloat = ObjectAnimator.ofFloat(500.0f, 0.0f);
        this.animator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(300L);
        this.animator.setInterpolator(new LinearInterpolator());
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.m1582lambda$init$0$comwugudoublecameracustomCircleRevealView(valueAnimator);
            }
        });
        this.animator.addListener(new C19171());
        ValueAnimator valueAnimatorOfFloat2 = ObjectAnimator.ofFloat(0.0f, 500.0f);
        this.animatorEnd = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(300L);
        this.animatorEnd.setInterpolator(new LinearInterpolator());
        this.animatorEnd.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.m1583lambda$init$1$comwugudoublecameracustomCircleRevealView(valueAnimator);
            }
        });
        this.animatorEnd.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                CircleRevealView.this.isStart = false;
            }
        });
        this.duffXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
    }

    void m1582lambda$init$0$comwugudoublecameracustomCircleRevealView(ValueAnimator valueAnimator) {
        this.radius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    class C19171 extends AnimatorListenerAdapter {
        C19171() {
        }

        void m841x20bdf864() {
            CircleRevealView.this.animatorEnd.start();
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m841x20bdf864();
                }
            }, 600L);
        }
    }

    void m1583lambda$init$1$comwugudoublecameracustomCircleRevealView(ValueAnimator valueAnimator) {
        this.radius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void startAnimation() {
        this.isStart = true;
        this.animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isStart) {
            int iSaveLayer = canvas.saveLayer(null, null, 31);
            this.mPaint.setColor(-1);
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, this.radius, this.mPaint);
            this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaint.setXfermode(this.duffXfermode);
            canvas.drawPaint(this.mPaint);
            this.mPaint.setXfermode(null);
            canvas.restoreToCount(iSaveLayer);
        }
    }
}
