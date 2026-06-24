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

public final class LayoutBackCountdownBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    private final ConstraintLayout rootView;
    public final StrokeTextView tvCountdown;

    private LayoutBackCountdownBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, StrokeTextView strokeTextView) {
        this.rootView = constraintLayout;
        this.btnBack = appCompatImageView;
        this.tvCountdown = strokeTextView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutBackCountdownBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutBackCountdownBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_back_countdown, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutBackCountdownBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.tv_countdown;
            StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
            if (strokeTextView != null) {
                return new LayoutBackCountdownBinding((ConstraintLayout) view, appCompatImageView, strokeTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
