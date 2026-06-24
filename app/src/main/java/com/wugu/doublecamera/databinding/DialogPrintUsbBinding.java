package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.BtnBackCountdown;

public final class DialogPrintUsbBinding implements ViewBinding {
    public final AppCompatButton btnPrint;
    public final AppCompatEditText etPrintNum;
    public final AppCompatImageView ivPhoto;
    public final BtnBackCountdown layoutBtnCountdown;
    private final ConstraintLayout rootView;
    public final AppCompatTextView tvTitle;

    private DialogPrintUsbBinding(ConstraintLayout constraintLayout, AppCompatButton appCompatButton, AppCompatEditText appCompatEditText, AppCompatImageView appCompatImageView, BtnBackCountdown btnBackCountdown, AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.btnPrint = appCompatButton;
        this.etPrintNum = appCompatEditText;
        this.ivPhoto = appCompatImageView;
        this.layoutBtnCountdown = btnBackCountdown;
        this.tvTitle = appCompatTextView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogPrintUsbBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogPrintUsbBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.dialog_print_usb, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogPrintUsbBinding bind(View view) {
        int i = C1910R.id.btn_print;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, i);
        if (appCompatButton != null) {
            i = C1910R.id.et_print_num;
            AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
            if (appCompatEditText != null) {
                i = C1910R.id.iv_photo;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView != null) {
                    i = C1910R.id.layout_btn_countdown;
                    BtnBackCountdown btnBackCountdown = (BtnBackCountdown) ViewBindings.findChildViewById(view, i);
                    if (btnBackCountdown != null) {
                        i = C1910R.id.tv_title;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                        if (appCompatTextView != null) {
                            return new DialogPrintUsbBinding((ConstraintLayout) view, appCompatButton, appCompatEditText, appCompatImageView, btnBackCountdown, appCompatTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
