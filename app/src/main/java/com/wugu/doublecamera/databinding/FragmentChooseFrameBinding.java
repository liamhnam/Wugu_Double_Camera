package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentChooseFrameBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatImageView ivArrowLeft;
    public final AppCompatImageView ivArrowRight;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvFrame;
    public final RecyclerView rvFrameTheme;
    public final AppCompatTextView tvBalance;
    public final StrokeTextView tvCountdown;

    private FragmentChooseFrameBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, RecyclerView recyclerView, RecyclerView recyclerView2, AppCompatTextView appCompatTextView, StrokeTextView strokeTextView) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.ivArrowLeft = appCompatImageView2;
        this.ivArrowRight = appCompatImageView3;
        this.rvFrame = recyclerView;
        this.rvFrameTheme = recyclerView2;
        this.tvBalance = appCompatTextView;
        this.tvCountdown = strokeTextView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChooseFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChooseFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_choose_frame, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentChooseFrameBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_arrow_left;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.iv_arrow_right;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.rv_frame;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView != null) {
                        i = C1910R.id.rv_frame_theme;
                        RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                        if (recyclerView2 != null) {
                            i = C1910R.id.tv_balance;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                            if (appCompatTextView != null) {
                                i = C1910R.id.tv_countdown;
                                StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                if (strokeTextView != null) {
                                    return new FragmentChooseFrameBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, recyclerView, recyclerView2, appCompatTextView, strokeTextView);
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
