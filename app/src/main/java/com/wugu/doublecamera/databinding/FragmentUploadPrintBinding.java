package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CountdownView;
import com.wugu.doublecamera.custom.PaymentSelectView;
import com.wugu.doublecamera.custom.PictureSplicing;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentUploadPrintBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatImageView btnEnter;
    public final Group groupPrintCount;
    public final AppCompatImageView ivAdd;
    public final AppCompatImageView ivPrintCountText;
    public final AppCompatImageView ivQrcode;
    public final AppCompatImageView ivSub;
    public final CountdownView layoutCountdown;
    public final PaymentSelectView layoutPaymentSelect;
    public final PictureSplicing layoutSplicing;
    private final ConstraintLayout rootView;
    public final RecyclerView rvFrameIc;
    public final RecyclerView rvUploadPicture;
    public final StrokeTextView tvPrintCount;
    public final StrokeTextView tvPrintPrice;

    private FragmentUploadPrintBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, Group group, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, CountdownView countdownView, PaymentSelectView paymentSelectView, PictureSplicing pictureSplicing, RecyclerView recyclerView, RecyclerView recyclerView2, StrokeTextView strokeTextView, StrokeTextView strokeTextView2) {
        this.rootView = constraintLayout;
        this.btnBack = appCompatImageView;
        this.btnEnter = appCompatImageView2;
        this.groupPrintCount = group;
        this.ivAdd = appCompatImageView3;
        this.ivPrintCountText = appCompatImageView4;
        this.ivQrcode = appCompatImageView5;
        this.ivSub = appCompatImageView6;
        this.layoutCountdown = countdownView;
        this.layoutPaymentSelect = paymentSelectView;
        this.layoutSplicing = pictureSplicing;
        this.rvFrameIc = recyclerView;
        this.rvUploadPicture = recyclerView2;
        this.tvPrintCount = strokeTextView;
        this.tvPrintPrice = strokeTextView2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentUploadPrintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentUploadPrintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_upload_print, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentUploadPrintBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_enter;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.group_print_count;
                Group group = (Group) ViewBindings.findChildViewById(view, i);
                if (group != null) {
                    i = C1910R.id.iv_add;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView3 != null) {
                        i = C1910R.id.iv_print_count_text;
                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView4 != null) {
                            i = C1910R.id.iv_qrcode;
                            AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView5 != null) {
                                i = C1910R.id.iv_sub;
                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView6 != null) {
                                    i = C1910R.id.layout_countdown;
                                    CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
                                    if (countdownView != null) {
                                        i = C1910R.id.layout_payment_select;
                                        PaymentSelectView paymentSelectView = (PaymentSelectView) ViewBindings.findChildViewById(view, i);
                                        if (paymentSelectView != null) {
                                            i = C1910R.id.layout_splicing;
                                            PictureSplicing pictureSplicing = (PictureSplicing) ViewBindings.findChildViewById(view, i);
                                            if (pictureSplicing != null) {
                                                i = C1910R.id.rv_frame_ic;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                if (recyclerView != null) {
                                                    i = C1910R.id.rv_upload_picture;
                                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                    if (recyclerView2 != null) {
                                                        i = C1910R.id.tv_print_count;
                                                        StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                        if (strokeTextView != null) {
                                                            i = C1910R.id.tv_print_price;
                                                            StrokeTextView strokeTextView2 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                            if (strokeTextView2 != null) {
                                                                return new FragmentUploadPrintBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, group, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, countdownView, paymentSelectView, pictureSplicing, recyclerView, recyclerView2, strokeTextView, strokeTextView2);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
