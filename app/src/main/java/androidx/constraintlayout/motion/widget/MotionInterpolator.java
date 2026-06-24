package androidx.constraintlayout.motion.widget;

import android.view.animation.Interpolator;

public abstract class MotionInterpolator implements Interpolator {
    @Override
    public abstract float getInterpolation(float v);

    public abstract float getVelocity();
}
