package com.wugu.doublecamera.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import com.wugu.doublecamera.C1910R;

public class AnimUtil {
    public static ValueAnimator getRotationAnim1(final ImageView imageView, int i) {
        imageView.setImageResource(C1910R.drawable.ic_loading);
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 12);
        valueAnimatorOfInt.setDuration(i);
        valueAnimatorOfInt.setRepeatCount(-1);
        valueAnimatorOfInt.setRepeatMode(1);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                imageView.setRotation(((Integer) valueAnimator.getAnimatedValue()).intValue() * 30);
            }
        });
        return valueAnimatorOfInt;
    }

    public static void startBtnClickAnim(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.05f, 1.0f, 1.05f, 1, 1.0f, 1, 1.0f);
        scaleAnimation.setDuration(100L);
        view.startAnimation(scaleAnimation);
    }

    public static void moveGroupView(ViewGroup viewGroup, int i, int i2, int i3) {
        viewGroup.getLocationOnScreen(new int[2]);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 0, i - r0[0], 1, 0.0f, 0, i2 - r0[1]);
        translateAnimation.setDuration(i3);
        translateAnimation.setFillAfter(true);
        viewGroup.startAnimation(translateAnimation);
    }

    public static void moveView(View view, int i, int i2, int i3) {
        view.getLocationOnScreen(new int[2]);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 0, i - r0[0], 1, 0.0f, 0, i2 - r0[1]);
        translateAnimation.setDuration(i3);
        translateAnimation.setFillAfter(true);
        view.startAnimation(translateAnimation);
    }

    public static AnimatorSet getBreathAnim(View view, int i) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.9f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.9f, 1.0f);
        objectAnimatorOfFloat.setRepeatCount(-1);
        objectAnimatorOfFloat2.setRepeatCount(-1);
        objectAnimatorOfFloat.setRepeatMode(2);
        objectAnimatorOfFloat2.setRepeatMode(2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setDuration(i);
        return animatorSet;
    }
}
