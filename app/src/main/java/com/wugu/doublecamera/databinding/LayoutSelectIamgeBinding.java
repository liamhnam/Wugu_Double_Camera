package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class LayoutSelectIamgeBinding implements ViewBinding {
    public final AppCompatImageView ivPhoto;
    public final AppCompatImageView ivSelected;
    public final AppCompatImageView ivStroke;
    private final ConstraintLayout rootView;

    private LayoutSelectIamgeBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3) {
        this.rootView = constraintLayout;
        this.ivPhoto = appCompatImageView;
        this.ivSelected = appCompatImageView2;
        this.ivStroke = appCompatImageView3;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSelectIamgeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutSelectIamgeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_select_iamge, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutSelectIamgeBinding bind(View view) {
        int i = C1910R.id.iv_photo;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_selected;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.iv_stroke;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    return new LayoutSelectIamgeBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
