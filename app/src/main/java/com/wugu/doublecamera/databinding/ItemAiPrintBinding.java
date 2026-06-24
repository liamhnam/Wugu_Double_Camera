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

public final class ItemAiPrintBinding implements ViewBinding {
    public final AppCompatImageView ivAdd;
    public final AppCompatImageView ivFrameExample;
    public final AppCompatImageView ivPrintCountText;
    public final AppCompatImageView ivSub;
    private final ConstraintLayout rootView;
    public final AppCompatTextView tvLoading;
    public final StrokeTextView tvPrintCount;
    public final AppCompatTextView tvPrintPrice;

    private ItemAiPrintBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatTextView appCompatTextView, StrokeTextView strokeTextView, AppCompatTextView appCompatTextView2) {
        this.rootView = constraintLayout;
        this.ivAdd = appCompatImageView;
        this.ivFrameExample = appCompatImageView2;
        this.ivPrintCountText = appCompatImageView3;
        this.ivSub = appCompatImageView4;
        this.tvLoading = appCompatTextView;
        this.tvPrintCount = strokeTextView;
        this.tvPrintPrice = appCompatTextView2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemAiPrintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAiPrintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_ai_print, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemAiPrintBinding bind(View view) {
        int i = C1910R.id.iv_add;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_frame_example;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.iv_print_count_text;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.iv_sub;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView4 != null) {
                        i = C1910R.id.tv_loading;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                        if (appCompatTextView != null) {
                            i = C1910R.id.tv_print_count;
                            StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                            if (strokeTextView != null) {
                                i = C1910R.id.tv_print_price;
                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                if (appCompatTextView2 != null) {
                                    return new ItemAiPrintBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatTextView, strokeTextView, appCompatTextView2);
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
