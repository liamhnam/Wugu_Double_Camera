package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class ItemPeRetakePicBinding implements ViewBinding {
    public final AppCompatImageView ivPhoto;
    public final ConstraintLayout layoutRoot;
    private final ConstraintLayout rootView;

    private ItemPeRetakePicBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.ivPhoto = appCompatImageView;
        this.layoutRoot = constraintLayout2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemPeRetakePicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPeRetakePicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_pe_retake_pic, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemPeRetakePicBinding bind(View view) {
        int i = C1910R.id.iv_photo;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            return new ItemPeRetakePicBinding(constraintLayout, appCompatImageView, constraintLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
