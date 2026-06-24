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
import com.wugu.doublecamera.custom.CountdownView;
import com.wugu.doublecamera.custom.PictureSplicing;

public final class FragmentPicSelectBinding implements ViewBinding {
    public final AppCompatImageView btnEnter;
    public final CountdownView layoutCountdown;
    public final PictureSplicing layoutSplicing;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvPicture;

    private FragmentPicSelectBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, CountdownView countdownView, PictureSplicing pictureSplicing, RecyclerView recyclerView) {
        this.rootView = absoluteLayout;
        this.btnEnter = appCompatImageView;
        this.layoutCountdown = countdownView;
        this.layoutSplicing = pictureSplicing;
        this.rvPicture = recyclerView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPicSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPicSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_pic_select, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPicSelectBinding bind(View view) {
        int i = C1910R.id.btn_enter;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.layout_countdown;
            CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
            if (countdownView != null) {
                i = C1910R.id.layout_splicing;
                PictureSplicing pictureSplicing = (PictureSplicing) ViewBindings.findChildViewById(view, i);
                if (pictureSplicing != null) {
                    i = C1910R.id.rv_picture;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView != null) {
                        return new FragmentPicSelectBinding((AbsoluteLayout) view, appCompatImageView, countdownView, pictureSplicing, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
