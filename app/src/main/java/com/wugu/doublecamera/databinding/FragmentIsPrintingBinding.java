package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CountdownView;

public final class FragmentIsPrintingBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatImageView image;
    public final AppCompatImageView ivPhotoQrcode;
    public final CountdownView layoutCountdown;
    public final ConstraintLayout layoutPhotoQrcode;
    private final AbsoluteLayout rootView;
    public final AppCompatTextView tvUploadProcess;

    private FragmentIsPrintingBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, CountdownView countdownView, ConstraintLayout constraintLayout, AppCompatTextView appCompatTextView) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.image = appCompatImageView2;
        this.ivPhotoQrcode = appCompatImageView3;
        this.layoutCountdown = countdownView;
        this.layoutPhotoQrcode = constraintLayout;
        this.tvUploadProcess = appCompatTextView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentIsPrintingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentIsPrintingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_is_printing, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentIsPrintingBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.image;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.iv_photo_qrcode;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.layout_countdown;
                    CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
                    if (countdownView != null) {
                        i = C1910R.id.layout_photo_qrcode;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                        if (constraintLayout != null) {
                            i = C1910R.id.tv_upload_process;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                            if (appCompatTextView != null) {
                                return new FragmentIsPrintingBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, countdownView, constraintLayout, appCompatTextView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
