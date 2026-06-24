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

public final class LayoutPaymentSelectBinding implements ViewBinding {
    public final AppCompatEditText etCodeInput;
    public final AppCompatImageView ivArrow1;
    public final AppCompatImageView ivArrow2;
    public final AppCompatImageView ivArrow3;
    public final AppCompatImageView ivArrow4;
    public final AppCompatImageView ivArrow5;
    public final AppCompatImageView ivArrow6;
    public final AppCompatImageView ivArrow7;
    public final AppCompatImageView ivArrow8;
    public final AppCompatImageView ivArrow9;
    public final AppCompatImageView ivCodeExBg;
    public final AppCompatImageView ivPayment1;
    public final AppCompatImageView ivPayment2;
    public final AppCompatImageView ivPayment3;
    public final AppCompatImageView ivPayment4;
    public final AppCompatImageView ivPayment5;
    public final AppCompatImageView ivPayment6;
    public final AppCompatImageView ivPayment7;
    public final AppCompatImageView ivPayment8;
    public final AppCompatImageView ivPayment9;
    public final AppCompatImageView ivPaymentIcon;
    public final AppCompatImageView ivPaymentQrcode;
    public final NumKeyboard layoutKeyboard;
    public final ConstraintLayout layoutPaymentMethod;
    private final AbsoluteLayout rootView;
    public final AppCompatTextView tvPaymentBalance;
    public final AppCompatTextView tvPaymentPrice;

    private LayoutPaymentSelectBinding(AbsoluteLayout absoluteLayout, AppCompatEditText appCompatEditText, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, AppCompatImageView appCompatImageView9, AppCompatImageView appCompatImageView10, AppCompatImageView appCompatImageView11, AppCompatImageView appCompatImageView12, AppCompatImageView appCompatImageView13, AppCompatImageView appCompatImageView14, AppCompatImageView appCompatImageView15, AppCompatImageView appCompatImageView16, AppCompatImageView appCompatImageView17, AppCompatImageView appCompatImageView18, AppCompatImageView appCompatImageView19, AppCompatImageView appCompatImageView20, AppCompatImageView appCompatImageView21, NumKeyboard numKeyboard, ConstraintLayout constraintLayout, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2) {
        this.rootView = absoluteLayout;
        this.etCodeInput = appCompatEditText;
        this.ivArrow1 = appCompatImageView;
        this.ivArrow2 = appCompatImageView2;
        this.ivArrow3 = appCompatImageView3;
        this.ivArrow4 = appCompatImageView4;
        this.ivArrow5 = appCompatImageView5;
        this.ivArrow6 = appCompatImageView6;
        this.ivArrow7 = appCompatImageView7;
        this.ivArrow8 = appCompatImageView8;
        this.ivArrow9 = appCompatImageView9;
        this.ivCodeExBg = appCompatImageView10;
        this.ivPayment1 = appCompatImageView11;
        this.ivPayment2 = appCompatImageView12;
        this.ivPayment3 = appCompatImageView13;
        this.ivPayment4 = appCompatImageView14;
        this.ivPayment5 = appCompatImageView15;
        this.ivPayment6 = appCompatImageView16;
        this.ivPayment7 = appCompatImageView17;
        this.ivPayment8 = appCompatImageView18;
        this.ivPayment9 = appCompatImageView19;
        this.ivPaymentIcon = appCompatImageView20;
        this.ivPaymentQrcode = appCompatImageView21;
        this.layoutKeyboard = numKeyboard;
        this.layoutPaymentMethod = constraintLayout;
        this.tvPaymentBalance = appCompatTextView;
        this.tvPaymentPrice = appCompatTextView2;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static LayoutPaymentSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutPaymentSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_payment_select, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutPaymentSelectBinding bind(View view) {
        int i = C1910R.id.et_code_input;
        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
        if (appCompatEditText != null) {
            i = C1910R.id.iv_arrow1;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView != null) {
                i = C1910R.id.iv_arrow2;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null) {
                    i = C1910R.id.iv_arrow3;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView3 != null) {
                        i = C1910R.id.iv_arrow4;
                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView4 != null) {
                            i = C1910R.id.iv_arrow5;
                            AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView5 != null) {
                                i = C1910R.id.iv_arrow6;
                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView6 != null) {
                                    i = C1910R.id.iv_arrow7;
                                    AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView7 != null) {
                                        i = C1910R.id.iv_arrow8;
                                        AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                        if (appCompatImageView8 != null) {
                                            i = C1910R.id.iv_arrow9;
                                            AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                            if (appCompatImageView9 != null) {
                                                i = C1910R.id.iv_code_ex_bg;
                                                AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatImageView10 != null) {
                                                    i = C1910R.id.iv_payment1;
                                                    AppCompatImageView appCompatImageView11 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                    if (appCompatImageView11 != null) {
                                                        i = C1910R.id.iv_payment2;
                                                        AppCompatImageView appCompatImageView12 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                        if (appCompatImageView12 != null) {
                                                            i = C1910R.id.iv_payment3;
                                                            AppCompatImageView appCompatImageView13 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                            if (appCompatImageView13 != null) {
                                                                i = C1910R.id.iv_payment4;
                                                                AppCompatImageView appCompatImageView14 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                if (appCompatImageView14 != null) {
                                                                    i = C1910R.id.iv_payment5;
                                                                    AppCompatImageView appCompatImageView15 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                    if (appCompatImageView15 != null) {
                                                                        i = C1910R.id.iv_payment6;
                                                                        AppCompatImageView appCompatImageView16 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                        if (appCompatImageView16 != null) {
                                                                            i = C1910R.id.iv_payment7;
                                                                            AppCompatImageView appCompatImageView17 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                            if (appCompatImageView17 != null) {
                                                                                i = C1910R.id.iv_payment8;
                                                                                AppCompatImageView appCompatImageView18 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                if (appCompatImageView18 != null) {
                                                                                    i = C1910R.id.iv_payment9;
                                                                                    AppCompatImageView appCompatImageView19 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                    if (appCompatImageView19 != null) {
                                                                                        i = C1910R.id.iv_payment_icon;
                                                                                        AppCompatImageView appCompatImageView20 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                        if (appCompatImageView20 != null) {
                                                                                            i = C1910R.id.iv_payment_qrcode;
                                                                                            AppCompatImageView appCompatImageView21 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                            if (appCompatImageView21 != null) {
                                                                                                i = C1910R.id.layout_keyboard;
                                                                                                NumKeyboard numKeyboard = (NumKeyboard) ViewBindings.findChildViewById(view, i);
                                                                                                if (numKeyboard != null) {
                                                                                                    i = C1910R.id.layout_payment_method;
                                                                                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                                    if (constraintLayout != null) {
                                                                                                        i = C1910R.id.tv_payment_balance;
                                                                                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                                                        if (appCompatTextView != null) {
                                                                                                            i = C1910R.id.tv_payment_price;
                                                                                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                                                            if (appCompatTextView2 != null) {
                                                                                                                return new LayoutPaymentSelectBinding((AbsoluteLayout) view, appCompatEditText, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, appCompatImageView11, appCompatImageView12, appCompatImageView13, appCompatImageView14, appCompatImageView15, appCompatImageView16, appCompatImageView17, appCompatImageView18, appCompatImageView19, appCompatImageView20, appCompatImageView21, numKeyboard, constraintLayout, appCompatTextView, appCompatTextView2);
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
