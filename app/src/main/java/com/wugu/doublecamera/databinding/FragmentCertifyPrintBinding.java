package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CountdownView;
import com.wugu.doublecamera.custom.PrintPaymentView;

public final class FragmentCertifyPrintBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatImageView btnPrint;
    public final AppCompatImageView ivPhotoQrcode;
    public final CountdownView layoutCountdown;
    public final PrintPaymentView layoutPayment;
    public final ConstraintLayout layoutPhotoQrcode;
    public final RecyclerView recyclerView;
    private final AbsoluteLayout rootView;
    public final AppCompatTextView tvUploadProcess;

    private FragmentCertifyPrintBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, CountdownView countdownView, PrintPaymentView printPaymentView, ConstraintLayout constraintLayout, RecyclerView recyclerView, AppCompatTextView appCompatTextView) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.btnPrint = appCompatImageView2;
        this.ivPhotoQrcode = appCompatImageView3;
        this.layoutCountdown = countdownView;
        this.layoutPayment = printPaymentView;
        this.layoutPhotoQrcode = constraintLayout;
        this.recyclerView = recyclerView;
        this.tvUploadProcess = appCompatTextView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCertifyPrintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCertifyPrintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_certify_print, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCertifyPrintBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_print;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.iv_photo_qrcode;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.layout_countdown;
                    CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
                    if (countdownView != null) {
                        i = C1910R.id.layout_payment;
                        PrintPaymentView printPaymentView = (PrintPaymentView) ViewBindings.findChildViewById(view, i);
                        if (printPaymentView != null) {
                            i = C1910R.id.layout_photo_qrcode;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                            if (constraintLayout != null) {
                                i = C1910R.id.recycler_view;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                if (recyclerView != null) {
                                    i = C1910R.id.tv_upload_process;
                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatTextView != null) {
                                        return new FragmentCertifyPrintBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, countdownView, printPaymentView, constraintLayout, recyclerView, appCompatTextView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
