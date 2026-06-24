package com.chad.library.adapter4.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001b\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m1293d2 = {"Lcom/chad/library/adapter4/animation/AlphaInAnimation;", "Lcom/chad/library/adapter4/animation/ItemAnimator;", "duration", "", "mFrom", "", "(JF)V", "interpolator", "Landroid/view/animation/LinearInterpolator;", "animator", "Landroid/animation/Animator;", "view", "Landroid/view/View;", "Companion", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class AlphaInAnimation implements ItemAnimator {
    private static final float DEFAULT_ALPHA_FROM = 0.0f;
    private final long duration;
    private final LinearInterpolator interpolator;
    private final float mFrom;

    public AlphaInAnimation() {
        this(0L, 0.0f, 3, null);
    }

    public AlphaInAnimation(long j) {
        this(j, 0.0f, 2, null);
    }

    public AlphaInAnimation(long j, float f) {
        this.duration = j;
        this.mFrom = f;
        this.interpolator = new LinearInterpolator();
    }

    public AlphaInAnimation(long j, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 300L : j, (i & 2) != 0 ? 0.0f : f);
    }

    @Override
    public Animator animator(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", this.mFrom, 1.0f);
        animator.setDuration(this.duration);
        animator.setInterpolator(this.interpolator);
        Intrinsics.checkNotNullExpressionValue(animator, "animator");
        return animator;
    }
}
