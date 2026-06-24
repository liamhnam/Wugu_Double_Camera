package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class LayoutCountdownBinding implements ViewBinding {
    public final AppCompatImageView ivCountBg;
    private final ConstraintLayout rootView;
    public final StrokeTextView tvCount;

    private LayoutCountdownBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, StrokeTextView strokeTextView) {
        this.rootView = constraintLayout;
        this.ivCountBg = appCompatImageView;
        this.tvCount = strokeTextView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutCountdownBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutCountdownBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_countdown, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutCountdownBinding bind(View view) {
        int i = C1910R.id.iv_count_bg;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.tv_count;
            StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
            if (strokeTextView != null) {
                return new LayoutCountdownBinding((ConstraintLayout) view, appCompatImageView, strokeTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
