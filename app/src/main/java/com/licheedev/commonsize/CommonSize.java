package com.licheedev.commonsize;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.TextView;

public class CommonSize {
    public static float getPx(Resources resources, int i) {
        return resources.getDimension(i);
    }

    public static float getPx(Context context, int i) {
        return getPx(context.getResources(), i);
    }

    public static void applyTextSize(TextView textView, int i) {
        textView.setTextSize(0, getPx(textView.getResources(), i));
    }

    public static float dp2px(Resources resources, float f) {
        return TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
    }

    public static int sp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(Context context, float f) {
        return f / context.getResources().getDisplayMetrics().density;
    }

    public static float px2sp(Context context, float f) {
        return f / context.getResources().getDisplayMetrics().scaledDensity;
    }
}
