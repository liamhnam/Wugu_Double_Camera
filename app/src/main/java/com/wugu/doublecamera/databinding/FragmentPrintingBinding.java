package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CountdownView;

public final class FragmentPrintingBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatImageView ivPhotoQrcode;
    public final CountdownView layoutCountdown;
    private final AbsoluteLayout rootView;

    private FragmentPrintingBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, CountdownView countdownView) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.ivPhotoQrcode = appCompatImageView2;
        this.layoutCountdown = countdownView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPrintingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPrintingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_printing, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPrintingBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_photo_qrcode;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.layout_countdown;
                CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
                if (countdownView != null) {
                    return new FragmentPrintingBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, countdownView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
