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
import com.wugu.doublecamera.custom.BtnBackCountdown;

public final class DialogUploadPrintBinding implements ViewBinding {
    public final AppCompatImageView ivQrcode;
    public final BtnBackCountdown layoutBtnCountdown;
    private final ConstraintLayout rootView;
    public final AppCompatTextView tvLoading;

    private DialogUploadPrintBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, BtnBackCountdown btnBackCountdown, AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.ivQrcode = appCompatImageView;
        this.layoutBtnCountdown = btnBackCountdown;
        this.tvLoading = appCompatTextView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogUploadPrintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogUploadPrintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.dialog_upload_print, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogUploadPrintBinding bind(View view) {
        int i = C1910R.id.iv_qrcode;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.layout_btn_countdown;
            BtnBackCountdown btnBackCountdown = (BtnBackCountdown) ViewBindings.findChildViewById(view, i);
            if (btnBackCountdown != null) {
                i = C1910R.id.tv_loading;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                if (appCompatTextView != null) {
                    return new DialogUploadPrintBinding((ConstraintLayout) view, appCompatImageView, btnBackCountdown, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
