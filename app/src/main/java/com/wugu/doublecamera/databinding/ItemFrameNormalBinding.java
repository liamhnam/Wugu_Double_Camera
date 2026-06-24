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

public final class ItemFrameNormalBinding implements ViewBinding {
    public final AppCompatImageView ivCutIcon;
    public final AppCompatImageView ivFrameExample;
    private final ConstraintLayout rootView;
    public final StrokeTextView tvFrameCount;
    public final StrokeTextView tvFramePrice;

    private ItemFrameNormalBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, StrokeTextView strokeTextView, StrokeTextView strokeTextView2) {
        this.rootView = constraintLayout;
        this.ivCutIcon = appCompatImageView;
        this.ivFrameExample = appCompatImageView2;
        this.tvFrameCount = strokeTextView;
        this.tvFramePrice = strokeTextView2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemFrameNormalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemFrameNormalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_frame_normal, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemFrameNormalBinding bind(View view) {
        int i = C1910R.id.iv_cut_icon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_frame_example;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.tv_frame_count;
                StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                if (strokeTextView != null) {
                    i = C1910R.id.tv_frame_price;
                    StrokeTextView strokeTextView2 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                    if (strokeTextView2 != null) {
                        return new ItemFrameNormalBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, strokeTextView, strokeTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
