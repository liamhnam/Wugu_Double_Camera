package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CountdownView;
import com.wugu.doublecamera.custom.PaletteView;
import com.wugu.doublecamera.custom.PrintPaymentView;
import com.wugu.doublecamera.custom.StrokeTextView;
import com.wugu.stickerview.StickerLayout;

public final class FragmentPrintBinding implements ViewBinding {
    public final AppCompatImageView btnAddQrcode;
    public final AppCompatButton btnAddText;
    public final AppCompatImageView btnPrint;
    public final AppCompatImageView btnSign;
    public final FragmentContainerView fragmentSticker;
    public final AppCompatImageView ivAdd;
    public final AppCompatImageView ivPrintCountText;
    public final AppCompatImageView ivPrintFrame;
    public final AppCompatImageView ivSub;
    public final CountdownView layoutCountdown;
    public final ConstraintLayout layoutFrame;
    public final PaletteView layoutPalette;
    public final PrintPaymentView layoutPayment;
    public final StickerLayout layoutSticker;
    private final AbsoluteLayout rootView;
    public final StrokeTextView tvPrintCount;
    public final StrokeTextView tvPrintPrice;

    private FragmentPrintBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatButton appCompatButton, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, FragmentContainerView fragmentContainerView, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, CountdownView countdownView, ConstraintLayout constraintLayout, PaletteView paletteView, PrintPaymentView printPaymentView, StickerLayout stickerLayout, StrokeTextView strokeTextView, StrokeTextView strokeTextView2) {
        this.rootView = absoluteLayout;
        this.btnAddQrcode = appCompatImageView;
        this.btnAddText = appCompatButton;
        this.btnPrint = appCompatImageView2;
        this.btnSign = appCompatImageView3;
        this.fragmentSticker = fragmentContainerView;
        this.ivAdd = appCompatImageView4;
        this.ivPrintCountText = appCompatImageView5;
        this.ivPrintFrame = appCompatImageView6;
        this.ivSub = appCompatImageView7;
        this.layoutCountdown = countdownView;
        this.layoutFrame = constraintLayout;
        this.layoutPalette = paletteView;
        this.layoutPayment = printPaymentView;
        this.layoutSticker = stickerLayout;
        this.tvPrintCount = strokeTextView;
        this.tvPrintPrice = strokeTextView2;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPrintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPrintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_print, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPrintBinding bind(View view) {
        int i = C1910R.id.btn_add_qrcode;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_add_text;
            AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, i);
            if (appCompatButton != null) {
                i = C1910R.id.btn_print;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null) {
                    i = C1910R.id.btn_sign;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView3 != null) {
                        i = C1910R.id.fragment_sticker;
                        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
                        if (fragmentContainerView != null) {
                            i = C1910R.id.iv_add;
                            AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView4 != null) {
                                i = C1910R.id.iv_print_count_text;
                                AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView5 != null) {
                                    i = C1910R.id.iv_print_frame;
                                    AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView6 != null) {
                                        i = C1910R.id.iv_sub;
                                        AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                        if (appCompatImageView7 != null) {
                                            i = C1910R.id.layout_countdown;
                                            CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
                                            if (countdownView != null) {
                                                i = C1910R.id.layout_frame;
                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                if (constraintLayout != null) {
                                                    i = C1910R.id.layout_palette;
                                                    PaletteView paletteView = (PaletteView) ViewBindings.findChildViewById(view, i);
                                                    if (paletteView != null) {
                                                        i = C1910R.id.layout_payment;
                                                        PrintPaymentView printPaymentView = (PrintPaymentView) ViewBindings.findChildViewById(view, i);
                                                        if (printPaymentView != null) {
                                                            i = C1910R.id.layout_sticker;
                                                            StickerLayout stickerLayout = (StickerLayout) ViewBindings.findChildViewById(view, i);
                                                            if (stickerLayout != null) {
                                                                i = C1910R.id.tv_print_count;
                                                                StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                if (strokeTextView != null) {
                                                                    i = C1910R.id.tv_print_price;
                                                                    StrokeTextView strokeTextView2 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                    if (strokeTextView2 != null) {
                                                                        return new FragmentPrintBinding((AbsoluteLayout) view, appCompatImageView, appCompatButton, appCompatImageView2, appCompatImageView3, fragmentContainerView, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, countdownView, constraintLayout, paletteView, printPaymentView, stickerLayout, strokeTextView, strokeTextView2);
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
