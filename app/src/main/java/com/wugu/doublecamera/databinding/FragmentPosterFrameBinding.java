package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentPosterFrameBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatImageView btnEnter;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvFrame;
    public final StrokeTextView tvCountdown;

    private FragmentPosterFrameBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, RecyclerView recyclerView, StrokeTextView strokeTextView) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.btnEnter = appCompatImageView2;
        this.rvFrame = recyclerView;
        this.tvCountdown = strokeTextView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPosterFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPosterFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_poster_frame, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPosterFrameBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_enter;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.rv_frame;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = C1910R.id.tv_countdown;
                    StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                    if (strokeTextView != null) {
                        return new FragmentPosterFrameBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, recyclerView, strokeTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
