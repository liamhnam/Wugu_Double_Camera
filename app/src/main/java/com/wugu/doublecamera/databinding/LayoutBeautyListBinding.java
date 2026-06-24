package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class LayoutBeautyListBinding implements ViewBinding {
    public final AppCompatImageView ivBeautyNone;
    public final AppCompatImageView ivBeautyOne;
    public final AppCompatImageView ivBeautyThree;
    public final AppCompatImageView ivBeautyTitle;
    public final AppCompatImageView ivBeautyTwo;
    private final ConstraintLayout rootView;

    private LayoutBeautyListBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5) {
        this.rootView = constraintLayout;
        this.ivBeautyNone = appCompatImageView;
        this.ivBeautyOne = appCompatImageView2;
        this.ivBeautyThree = appCompatImageView3;
        this.ivBeautyTitle = appCompatImageView4;
        this.ivBeautyTwo = appCompatImageView5;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutBeautyListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutBeautyListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_beauty_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutBeautyListBinding bind(View view) {
        int i = C1910R.id.iv_beauty_none;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_beauty_one;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.iv_beauty_three;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.iv_beauty_title;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView4 != null) {
                        i = C1910R.id.iv_beauty_two;
                        AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView5 != null) {
                            return new LayoutBeautyListBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
