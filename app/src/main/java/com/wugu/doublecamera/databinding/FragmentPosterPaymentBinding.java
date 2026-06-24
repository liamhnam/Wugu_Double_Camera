package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.NumKeyboard;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentPosterPaymentBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatEditText etCodeInput;
    public final AppCompatImageView ivCodeExBg;
    public final AppCompatImageView ivPayment1;
    public final AppCompatImageView ivPayment2;
    public final AppCompatImageView ivPayment3;
    public final AppCompatImageView ivPayment4;
    public final AppCompatImageView ivPayment5;
    public final AppCompatImageView ivPayment6;
    public final AppCompatImageView ivPayment7;
    public final AppCompatImageView ivQrcode;
    public final NumKeyboard layoutKeyboard;
    public final ConstraintLayout layoutPaymentMethod;
    private final AbsoluteLayout rootView;
    public final AppCompatTextView tvBalance;
    public final StrokeTextView tvCountdown;
    public final AppCompatTextView tvPrice;
    public final AppCompatImageView viewPayment;

    private FragmentPosterPaymentBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatEditText appCompatEditText, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, AppCompatImageView appCompatImageView9, AppCompatImageView appCompatImageView10, NumKeyboard numKeyboard, ConstraintLayout constraintLayout, AppCompatTextView appCompatTextView, StrokeTextView strokeTextView, AppCompatTextView appCompatTextView2, AppCompatImageView appCompatImageView11) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.etCodeInput = appCompatEditText;
        this.ivCodeExBg = appCompatImageView2;
        this.ivPayment1 = appCompatImageView3;
        this.ivPayment2 = appCompatImageView4;
        this.ivPayment3 = appCompatImageView5;
        this.ivPayment4 = appCompatImageView6;
        this.ivPayment5 = appCompatImageView7;
        this.ivPayment6 = appCompatImageView8;
        this.ivPayment7 = appCompatImageView9;
        this.ivQrcode = appCompatImageView10;
        this.layoutKeyboard = numKeyboard;
        this.layoutPaymentMethod = constraintLayout;
        this.tvBalance = appCompatTextView;
        this.tvCountdown = strokeTextView;
        this.tvPrice = appCompatTextView2;
        this.viewPayment = appCompatImageView11;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPosterPaymentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPosterPaymentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_poster_payment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPosterPaymentBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.et_code_input;
            AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
            if (appCompatEditText != null) {
                i = C1910R.id.iv_code_ex_bg;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null) {
                    i = C1910R.id.iv_payment1;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView3 != null) {
                        i = C1910R.id.iv_payment2;
                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView4 != null) {
                            i = C1910R.id.iv_payment3;
                            AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView5 != null) {
                                i = C1910R.id.iv_payment4;
                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView6 != null) {
                                    i = C1910R.id.iv_payment5;
                                    AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView7 != null) {
                                        i = C1910R.id.iv_payment6;
                                        AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                        if (appCompatImageView8 != null) {
                                            i = C1910R.id.iv_payment7;
                                            AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                            if (appCompatImageView9 != null) {
                                                i = C1910R.id.iv_qrcode;
                                                AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatImageView10 != null) {
                                                    i = C1910R.id.layout_keyboard;
                                                    NumKeyboard numKeyboard = (NumKeyboard) ViewBindings.findChildViewById(view, i);
                                                    if (numKeyboard != null) {
                                                        i = C1910R.id.layout_payment_method;
                                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                        if (constraintLayout != null) {
                                                            i = C1910R.id.tv_balance;
                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                            if (appCompatTextView != null) {
                                                                i = C1910R.id.tv_countdown;
                                                                StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                if (strokeTextView != null) {
                                                                    i = C1910R.id.tv_price;
                                                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                    if (appCompatTextView2 != null) {
                                                                        i = C1910R.id.view_payment;
                                                                        AppCompatImageView appCompatImageView11 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                        if (appCompatImageView11 != null) {
                                                                            return new FragmentPosterPaymentBinding((AbsoluteLayout) view, appCompatImageView, appCompatEditText, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, numKeyboard, constraintLayout, appCompatTextView, strokeTextView, appCompatTextView2, appCompatImageView11);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
