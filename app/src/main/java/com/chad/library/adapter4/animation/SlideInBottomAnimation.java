package com.chad.library.adapter4.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m1293d2 = {"Lcom/chad/library/adapter4/animation/SlideInBottomAnimation;", "Lcom/chad/library/adapter4/animation/ItemAnimator;", "duration", "", "(J)V", "interpolator", "Landroid/view/animation/DecelerateInterpolator;", "animator", "Landroid/animation/Animator;", "view", "Landroid/view/View;", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class SlideInBottomAnimation implements ItemAnimator {
    private final long duration;
    private final DecelerateInterpolator interpolator;

    public SlideInBottomAnimation() {
        this(0L, 1, null);
    }

    public SlideInBottomAnimation(long j) {
        this.duration = j;
        this.interpolator = new DecelerateInterpolator(1.3f);
    }

    public SlideInBottomAnimation(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 400L : j);
    }

    @Override
    public Animator animator(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0.0f);
        animator.setDuration(this.duration);
        animator.setInterpolator(this.interpolator);
        Intrinsics.checkNotNullExpressionValue(animator, "animator");
        return animator;
    }
}
