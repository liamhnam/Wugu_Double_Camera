package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class LayoutPosterPrintPayBinding implements ViewBinding {
    public final AppCompatImageView ivPayQrcode;
    private final AbsoluteLayout rootView;
    public final AppCompatTextView tvBalance;
    public final AppCompatTextView tvPaymentText;

    private LayoutPosterPrintPayBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2) {
        this.rootView = absoluteLayout;
        this.ivPayQrcode = appCompatImageView;
        this.tvBalance = appCompatTextView;
        this.tvPaymentText = appCompatTextView2;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static LayoutPosterPrintPayBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutPosterPrintPayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_poster_print_pay, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutPosterPrintPayBinding bind(View view) {
        int i = C1910R.id.iv_pay_qrcode;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.tv_balance;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
            if (appCompatTextView != null) {
                i = C1910R.id.tv_payment_text;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                if (appCompatTextView2 != null) {
                    return new LayoutPosterPrintPayBinding((AbsoluteLayout) view, appCompatImageView, appCompatTextView, appCompatTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
