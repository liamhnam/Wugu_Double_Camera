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

public final class FragmentEffectBinding implements ViewBinding {
    public final AppCompatImageView ivBg;
    public final AppCompatImageView ivEffect;
    public final AppCompatImageView ivFilter;
    public final AppCompatImageView ivMakeup;
    public final RecyclerView recyclerView;
    private final AbsoluteLayout rootView;

    private FragmentEffectBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, RecyclerView recyclerView) {
        this.rootView = absoluteLayout;
        this.ivBg = appCompatImageView;
        this.ivEffect = appCompatImageView2;
        this.ivFilter = appCompatImageView3;
        this.ivMakeup = appCompatImageView4;
        this.recyclerView = recyclerView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentEffectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentEffectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_effect, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentEffectBinding bind(View view) {
        int i = C1910R.id.iv_bg;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_effect;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.iv_filter;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.iv_makeup;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView4 != null) {
                        i = C1910R.id.recycler_view;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                        if (recyclerView != null) {
                            return new FragmentEffectBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, recyclerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
