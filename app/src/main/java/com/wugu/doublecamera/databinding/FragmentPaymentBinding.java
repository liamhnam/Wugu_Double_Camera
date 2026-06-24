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

public final class FragmentPaymentBinding implements ViewBinding {
    public final AppCompatImageView btnCancel;
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
    public final AppCompatImageView ivFrame;
    public final AppCompatImageView ivPayIcon;
    public final AppCompatImageView ivPayment1;
    public final AppCompatImageView ivPayment2;
    public final AppCompatImageView ivPayment3;
    public final AppCompatImageView ivPayment4;
    public final AppCompatImageView ivPayment5;
    public final AppCompatImageView ivPayment6;
    public final AppCompatImageView ivPayment7;
    public final AppCompatImageView ivPayment8;
    public final AppCompatImageView ivPayment9;
    public final AppCompatImageView ivQrcode;
    public final NumKeyboard layoutKeyboard;
    public final AbsoluteLayout layoutPayment;
    public final ConstraintLayout layoutPaymentMethod;
    private final AbsoluteLayout rootView;
    public final AppCompatTextView tvBalance;
    public final StrokeTextView tvCountdown;
    public final AppCompatTextView tvPrice;
    public final AppCompatImageView viewPayment;

    private FragmentPaymentBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatEditText appCompatEditText, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, AppCompatImageView appCompatImageView9, AppCompatImageView appCompatImageView10, AppCompatImageView appCompatImageView11, AppCompatImageView appCompatImageView12, AppCompatImageView appCompatImageView13, AppCompatImageView appCompatImageView14, AppCompatImageView appCompatImageView15, AppCompatImageView appCompatImageView16, AppCompatImageView appCompatImageView17, AppCompatImageView appCompatImageView18, AppCompatImageView appCompatImageView19, AppCompatImageView appCompatImageView20, AppCompatImageView appCompatImageView21, AppCompatImageView appCompatImageView22, AppCompatImageView appCompatImageView23, NumKeyboard numKeyboard, AbsoluteLayout absoluteLayout2, ConstraintLayout constraintLayout, AppCompatTextView appCompatTextView, StrokeTextView strokeTextView, AppCompatTextView appCompatTextView2, AppCompatImageView appCompatImageView24) {
        this.rootView = absoluteLayout;
        this.btnCancel = appCompatImageView;
        this.etCodeInput = appCompatEditText;
        this.ivArrow1 = appCompatImageView2;
        this.ivArrow2 = appCompatImageView3;
        this.ivArrow3 = appCompatImageView4;
        this.ivArrow4 = appCompatImageView5;
        this.ivArrow5 = appCompatImageView6;
        this.ivArrow6 = appCompatImageView7;
        this.ivArrow7 = appCompatImageView8;
        this.ivArrow8 = appCompatImageView9;
        this.ivArrow9 = appCompatImageView10;
        this.ivCodeExBg = appCompatImageView11;
        this.ivFrame = appCompatImageView12;
        this.ivPayIcon = appCompatImageView13;
        this.ivPayment1 = appCompatImageView14;
        this.ivPayment2 = appCompatImageView15;
        this.ivPayment3 = appCompatImageView16;
        this.ivPayment4 = appCompatImageView17;
        this.ivPayment5 = appCompatImageView18;
        this.ivPayment6 = appCompatImageView19;
        this.ivPayment7 = appCompatImageView20;
        this.ivPayment8 = appCompatImageView21;
        this.ivPayment9 = appCompatImageView22;
        this.ivQrcode = appCompatImageView23;
        this.layoutKeyboard = numKeyboard;
        this.layoutPayment = absoluteLayout2;
        this.layoutPaymentMethod = constraintLayout;
        this.tvBalance = appCompatTextView;
        this.tvCountdown = strokeTextView;
        this.tvPrice = appCompatTextView2;
        this.viewPayment = appCompatImageView24;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPaymentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPaymentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_payment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPaymentBinding bind(View view) {
        int i = C1910R.id.btn_cancel;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.et_code_input;
            AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
            if (appCompatEditText != null) {
                i = C1910R.id.iv_arrow1;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null) {
                    i = C1910R.id.iv_arrow2;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView3 != null) {
                        i = C1910R.id.iv_arrow3;
                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView4 != null) {
                            i = C1910R.id.iv_arrow4;
                            AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView5 != null) {
                                i = C1910R.id.iv_arrow5;
                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView6 != null) {
                                    i = C1910R.id.iv_arrow6;
                                    AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView7 != null) {
                                        i = C1910R.id.iv_arrow7;
                                        AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                        if (appCompatImageView8 != null) {
                                            i = C1910R.id.iv_arrow8;
                                            AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                            if (appCompatImageView9 != null) {
                                                i = C1910R.id.iv_arrow9;
                                                AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatImageView10 != null) {
                                                    i = C1910R.id.iv_code_ex_bg;
                                                    AppCompatImageView appCompatImageView11 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                    if (appCompatImageView11 != null) {
                                                        i = C1910R.id.iv_frame;
                                                        AppCompatImageView appCompatImageView12 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                        if (appCompatImageView12 != null) {
                                                            i = C1910R.id.iv_pay_icon;
                                                            AppCompatImageView appCompatImageView13 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                            if (appCompatImageView13 != null) {
                                                                i = C1910R.id.iv_payment1;
                                                                AppCompatImageView appCompatImageView14 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                if (appCompatImageView14 != null) {
                                                                    i = C1910R.id.iv_payment2;
                                                                    AppCompatImageView appCompatImageView15 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                    if (appCompatImageView15 != null) {
                                                                        i = C1910R.id.iv_payment3;
                                                                        AppCompatImageView appCompatImageView16 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                        if (appCompatImageView16 != null) {
                                                                            i = C1910R.id.iv_payment4;
                                                                            AppCompatImageView appCompatImageView17 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                            if (appCompatImageView17 != null) {
                                                                                i = C1910R.id.iv_payment5;
                                                                                AppCompatImageView appCompatImageView18 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                if (appCompatImageView18 != null) {
                                                                                    i = C1910R.id.iv_payment6;
                                                                                    AppCompatImageView appCompatImageView19 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                    if (appCompatImageView19 != null) {
                                                                                        i = C1910R.id.iv_payment7;
                                                                                        AppCompatImageView appCompatImageView20 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                        if (appCompatImageView20 != null) {
                                                                                            i = C1910R.id.iv_payment8;
                                                                                            AppCompatImageView appCompatImageView21 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                            if (appCompatImageView21 != null) {
                                                                                                i = C1910R.id.iv_payment9;
                                                                                                AppCompatImageView appCompatImageView22 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                if (appCompatImageView22 != null) {
                                                                                                    i = C1910R.id.iv_qrcode;
                                                                                                    AppCompatImageView appCompatImageView23 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                    if (appCompatImageView23 != null) {
                                                                                                        i = C1910R.id.layout_keyboard;
                                                                                                        NumKeyboard numKeyboard = (NumKeyboard) ViewBindings.findChildViewById(view, i);
                                                                                                        if (numKeyboard != null) {
                                                                                                            i = C1910R.id.layout_payment;
                                                                                                            AbsoluteLayout absoluteLayout = (AbsoluteLayout) ViewBindings.findChildViewById(view, i);
                                                                                                            if (absoluteLayout != null) {
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
                                                                                                                                AppCompatImageView appCompatImageView24 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                if (appCompatImageView24 != null) {
                                                                                                                                    return new FragmentPaymentBinding((AbsoluteLayout) view, appCompatImageView, appCompatEditText, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, appCompatImageView11, appCompatImageView12, appCompatImageView13, appCompatImageView14, appCompatImageView15, appCompatImageView16, appCompatImageView17, appCompatImageView18, appCompatImageView19, appCompatImageView20, appCompatImageView21, appCompatImageView22, appCompatImageView23, numKeyboard, absoluteLayout, constraintLayout, appCompatTextView, strokeTextView, appCompatTextView2, appCompatImageView24);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
