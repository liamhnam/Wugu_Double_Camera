package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.BtnBackCountdown;
import com.wugu.doublecamera.custom.CircleView;

public final class DialogAddTextBinding implements ViewBinding {
    public final AppCompatEditText etInput;
    public final BtnBackCountdown layoutBtnCountdown;
    private final ConstraintLayout rootView;
    public final AppCompatSeekBar seekbarFontSize;
    public final AppCompatTextView tvColor;
    public final AppCompatTextView tvOk;
    public final AppCompatTextView tvSize;
    public final CircleView viewBlack;
    public final CircleView viewBlue;
    public final CircleView viewGreen;
    public final CircleView viewPurple;
    public final CircleView viewRed;
    public final CircleView viewWhite;
    public final CircleView viewYellow;

    private DialogAddTextBinding(ConstraintLayout constraintLayout, AppCompatEditText appCompatEditText, BtnBackCountdown btnBackCountdown, AppCompatSeekBar appCompatSeekBar, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, CircleView circleView, CircleView circleView2, CircleView circleView3, CircleView circleView4, CircleView circleView5, CircleView circleView6, CircleView circleView7) {
        this.rootView = constraintLayout;
        this.etInput = appCompatEditText;
        this.layoutBtnCountdown = btnBackCountdown;
        this.seekbarFontSize = appCompatSeekBar;
        this.tvColor = appCompatTextView;
        this.tvOk = appCompatTextView2;
        this.tvSize = appCompatTextView3;
        this.viewBlack = circleView;
        this.viewBlue = circleView2;
        this.viewGreen = circleView3;
        this.viewPurple = circleView4;
        this.viewRed = circleView5;
        this.viewWhite = circleView6;
        this.viewYellow = circleView7;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogAddTextBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogAddTextBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.dialog_add_text, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogAddTextBinding bind(View view) {
        int i = C1910R.id.et_input;
        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
        if (appCompatEditText != null) {
            i = C1910R.id.layout_btn_countdown;
            BtnBackCountdown btnBackCountdown = (BtnBackCountdown) ViewBindings.findChildViewById(view, i);
            if (btnBackCountdown != null) {
                i = C1910R.id.seekbar_font_size;
                AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) ViewBindings.findChildViewById(view, i);
                if (appCompatSeekBar != null) {
                    i = C1910R.id.tv_color;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                    if (appCompatTextView != null) {
                        i = C1910R.id.tv_ok;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                        if (appCompatTextView2 != null) {
                            i = C1910R.id.tv_size;
                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                            if (appCompatTextView3 != null) {
                                i = C1910R.id.view_black;
                                CircleView circleView = (CircleView) ViewBindings.findChildViewById(view, i);
                                if (circleView != null) {
                                    i = C1910R.id.view_blue;
                                    CircleView circleView2 = (CircleView) ViewBindings.findChildViewById(view, i);
                                    if (circleView2 != null) {
                                        i = C1910R.id.view_green;
                                        CircleView circleView3 = (CircleView) ViewBindings.findChildViewById(view, i);
                                        if (circleView3 != null) {
                                            i = C1910R.id.view_purple;
                                            CircleView circleView4 = (CircleView) ViewBindings.findChildViewById(view, i);
                                            if (circleView4 != null) {
                                                i = C1910R.id.view_red;
                                                CircleView circleView5 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                if (circleView5 != null) {
                                                    i = C1910R.id.view_white;
                                                    CircleView circleView6 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                    if (circleView6 != null) {
                                                        i = C1910R.id.view_yellow;
                                                        CircleView circleView7 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                        if (circleView7 != null) {
                                                            return new DialogAddTextBinding((ConstraintLayout) view, appCompatEditText, btnBackCountdown, appCompatSeekBar, appCompatTextView, appCompatTextView2, appCompatTextView3, circleView, circleView2, circleView3, circleView4, circleView5, circleView6, circleView7);
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
