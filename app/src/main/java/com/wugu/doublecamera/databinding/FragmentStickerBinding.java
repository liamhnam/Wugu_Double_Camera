package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class FragmentStickerBinding implements ViewBinding {
    public final AppCompatImageView ivBg;
    private final ConstraintLayout rootView;
    public final RecyclerView rvItem;
    public final RecyclerView rvTab;

    private FragmentStickerBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, RecyclerView recyclerView, RecyclerView recyclerView2) {
        this.rootView = constraintLayout;
        this.ivBg = appCompatImageView;
        this.rvItem = recyclerView;
        this.rvTab = recyclerView2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentStickerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentStickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_sticker, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentStickerBinding bind(View view) {
        int i = C1910R.id.iv_bg;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.rv_item;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = C1910R.id.rv_tab;
                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView2 != null) {
                    return new FragmentStickerBinding((ConstraintLayout) view, appCompatImageView, recyclerView, recyclerView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
