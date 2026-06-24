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

public final class FragmentCheckFrameBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final RecyclerView recyclerView;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvTheme;
    public final StrokeTextView tvCountdown;

    private FragmentCheckFrameBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, RecyclerView recyclerView, RecyclerView recyclerView2, StrokeTextView strokeTextView) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.recyclerView = recyclerView;
        this.rvTheme = recyclerView2;
        this.tvCountdown = strokeTextView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCheckFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCheckFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_check_frame, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCheckFrameBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.recycler_view;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = C1910R.id.rv_theme;
                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView2 != null) {
                    i = C1910R.id.tv_countdown;
                    StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                    if (strokeTextView != null) {
                        return new FragmentCheckFrameBinding((AbsoluteLayout) view, appCompatImageView, recyclerView, recyclerView2, strokeTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
