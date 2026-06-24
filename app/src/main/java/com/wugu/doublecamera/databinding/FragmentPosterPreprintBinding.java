package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
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

public final class FragmentPosterPreprintBinding implements ViewBinding {
    public final AppCompatImageView btnPrint;
    public final AppCompatImageView btnSign;
    public final FragmentContainerView fragmentSticker;
    public final AppCompatImageView ivAdd;
    public final AppCompatImageView ivBlackPrint;
    public final AppCompatImageView ivBlackPrintSelected;
    public final AppCompatImageView ivColorPrint;
    public final AppCompatImageView ivColorPrintSelected;
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

    private FragmentPosterPreprintBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, FragmentContainerView fragmentContainerView, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, AppCompatImageView appCompatImageView9, AppCompatImageView appCompatImageView10, CountdownView countdownView, ConstraintLayout constraintLayout, PaletteView paletteView, PrintPaymentView printPaymentView, StickerLayout stickerLayout, StrokeTextView strokeTextView) {
        this.rootView = absoluteLayout;
        this.btnPrint = appCompatImageView;
        this.btnSign = appCompatImageView2;
        this.fragmentSticker = fragmentContainerView;
        this.ivAdd = appCompatImageView3;
        this.ivBlackPrint = appCompatImageView4;
        this.ivBlackPrintSelected = appCompatImageView5;
        this.ivColorPrint = appCompatImageView6;
        this.ivColorPrintSelected = appCompatImageView7;
        this.ivPrintCountText = appCompatImageView8;
        this.ivPrintFrame = appCompatImageView9;
        this.ivSub = appCompatImageView10;
        this.layoutCountdown = countdownView;
        this.layoutFrame = constraintLayout;
        this.layoutPalette = paletteView;
        this.layoutPayment = printPaymentView;
        this.layoutSticker = stickerLayout;
        this.tvPrintCount = strokeTextView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPosterPreprintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPosterPreprintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_poster_preprint, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPosterPreprintBinding bind(View view) {
        int i = C1910R.id.btn_print;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_sign;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.fragment_sticker;
                FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
                if (fragmentContainerView != null) {
                    i = C1910R.id.iv_add;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView3 != null) {
                        i = C1910R.id.iv_black_print;
                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView4 != null) {
                            i = C1910R.id.iv_black_print_selected;
                            AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView5 != null) {
                                i = C1910R.id.iv_color_print;
                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView6 != null) {
                                    i = C1910R.id.iv_color_print_selected;
                                    AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView7 != null) {
                                        i = C1910R.id.iv_print_count_text;
                                        AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                        if (appCompatImageView8 != null) {
                                            i = C1910R.id.iv_print_frame;
                                            AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                            if (appCompatImageView9 != null) {
                                                i = C1910R.id.iv_sub;
                                                AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatImageView10 != null) {
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
                                                                            return new FragmentPosterPreprintBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, fragmentContainerView, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, countdownView, constraintLayout, paletteView, printPaymentView, stickerLayout, strokeTextView);
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
