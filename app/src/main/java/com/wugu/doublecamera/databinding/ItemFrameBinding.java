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

public final class ItemFrameBinding implements ViewBinding {
    public final AppCompatImageView ivFrameExample;
    private final ConstraintLayout rootView;
    public final StrokeTextView tvFramePrice;

    private ItemFrameBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, StrokeTextView strokeTextView) {
        this.rootView = constraintLayout;
        this.ivFrameExample = appCompatImageView;
        this.tvFramePrice = strokeTextView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_frame, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemFrameBinding bind(View view) {
        int i = C1910R.id.iv_frame_example;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.tv_frame_price;
            StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
            if (strokeTextView != null) {
                return new ItemFrameBinding((ConstraintLayout) view, appCompatImageView, strokeTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
