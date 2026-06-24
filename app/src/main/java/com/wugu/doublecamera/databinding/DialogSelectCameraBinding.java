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

public final class DialogSelectCameraBinding implements ViewBinding {
    public final AppCompatImageView ivStandard;
    public final AppCompatImageView ivTopView;
    public final BtnBackCountdown layoutBtnCountdown;
    private final ConstraintLayout rootView;
    public final AppCompatTextView tvStandard;
    public final AppCompatTextView tvTopView;

    private DialogSelectCameraBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, BtnBackCountdown btnBackCountdown, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2) {
        this.rootView = constraintLayout;
        this.ivStandard = appCompatImageView;
        this.ivTopView = appCompatImageView2;
        this.layoutBtnCountdown = btnBackCountdown;
        this.tvStandard = appCompatTextView;
        this.tvTopView = appCompatTextView2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogSelectCameraBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogSelectCameraBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.dialog_select_camera, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogSelectCameraBinding bind(View view) {
        int i = C1910R.id.iv_standard;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.iv_top_view;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.layout_btn_countdown;
                BtnBackCountdown btnBackCountdown = (BtnBackCountdown) ViewBindings.findChildViewById(view, i);
                if (btnBackCountdown != null) {
                    i = C1910R.id.tv_standard;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                    if (appCompatTextView != null) {
                        i = C1910R.id.tv_top_view;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                        if (appCompatTextView2 != null) {
                            return new DialogSelectCameraBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, btnBackCountdown, appCompatTextView, appCompatTextView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
