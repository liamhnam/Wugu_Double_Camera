package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class ItemPosterFrameBinding implements ViewBinding {
    public final AppCompatImageView ivFrame;
    public final AppCompatImageView ivFrameSelected;
    public final AppCompatImageView ivPriceIcon;
    private final ConstraintLayout rootView;
    public final StrokeTextView tvFramePrice;
    public final AppCompatTextView tvFramePriceInvalid;

    private ItemPosterFrameBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, StrokeTextView strokeTextView, AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.ivFrame = appCompatImageView;
        this.ivFrameSelected = appCompatImageView2;
        this.ivPriceIcon = appCompatImageView3;
        this.tvFramePrice = strokeTextView;
        this.tvFramePriceInvalid = appCompatTextView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemPosterFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPosterFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_poster_frame, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemPosterFrameBinding bind(View view) {
        int i = C1910R.id.iv_frame;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_frame_selected;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.iv_price_icon;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.tv_frame_price;
                    StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                    if (strokeTextView != null) {
                        i = C1910R.id.tv_frame_price_invalid;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                        if (appCompatTextView != null) {
                            return new ItemPosterFrameBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, strokeTextView, appCompatTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
