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
import com.wugu.doublecamera.custom.CircleView;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class ItemFrameCertifyBinding implements ViewBinding {
    public final AppCompatImageView ivFrameExample;
    private final ConstraintLayout rootView;
    public final AppCompatTextView tvFrameName;
    public final StrokeTextView tvFramePrice;
    public final AppCompatTextView tvSize;
    public final CircleView vBlue;
    public final CircleView vRed;
    public final CircleView vWhite;

    private ItemFrameCertifyBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatTextView appCompatTextView, StrokeTextView strokeTextView, AppCompatTextView appCompatTextView2, CircleView circleView, CircleView circleView2, CircleView circleView3) {
        this.rootView = constraintLayout;
        this.ivFrameExample = appCompatImageView;
        this.tvFrameName = appCompatTextView;
        this.tvFramePrice = strokeTextView;
        this.tvSize = appCompatTextView2;
        this.vBlue = circleView;
        this.vRed = circleView2;
        this.vWhite = circleView3;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemFrameCertifyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemFrameCertifyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_frame_certify, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemFrameCertifyBinding bind(View view) {
        int i = C1910R.id.iv_frame_example;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.tv_frame_name;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
            if (appCompatTextView != null) {
                i = C1910R.id.tv_frame_price;
                StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                if (strokeTextView != null) {
                    i = C1910R.id.tv_size;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                    if (appCompatTextView2 != null) {
                        i = C1910R.id.v_blue;
                        CircleView circleView = (CircleView) ViewBindings.findChildViewById(view, i);
                        if (circleView != null) {
                            i = C1910R.id.v_red;
                            CircleView circleView2 = (CircleView) ViewBindings.findChildViewById(view, i);
                            if (circleView2 != null) {
                                i = C1910R.id.v_white;
                                CircleView circleView3 = (CircleView) ViewBindings.findChildViewById(view, i);
                                if (circleView3 != null) {
                                    return new ItemFrameCertifyBinding((ConstraintLayout) view, appCompatImageView, appCompatTextView, strokeTextView, appCompatTextView2, circleView, circleView2, circleView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
