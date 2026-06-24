package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class LayoutPictureSplicingBinding implements ViewBinding {
    public final AppCompatImageView ivFrame;
    public final FrameLayout layoutPicture;
    public final FrameLayout layoutStroke;
    private final ConstraintLayout rootView;

    private LayoutPictureSplicingBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.rootView = constraintLayout;
        this.ivFrame = appCompatImageView;
        this.layoutPicture = frameLayout;
        this.layoutStroke = frameLayout2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutPictureSplicingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutPictureSplicingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_picture_splicing, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutPictureSplicingBinding bind(View view) {
        int i = C1910R.id.iv_frame;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.layout_picture;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = C1910R.id.layout_stroke;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout2 != null) {
                    return new LayoutPictureSplicingBinding((ConstraintLayout) view, appCompatImageView, frameLayout, frameLayout2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
