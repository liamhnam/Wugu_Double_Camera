package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CircleView;
import com.wugu.doublecamera.custom.ColorPickerView;

public final class FragmentSignatureBinding implements ViewBinding {
    public final AppCompatImageView btnClear;
    public final AppCompatImageView btnRedo;
    public final AppCompatImageView btnUndo;
    public final AppCompatImageView ivBg;
    public final AppCompatImageView ivCustomColor;
    public final AppCompatImageView ivSize;
    public final ColorPickerView layoutColorPicker;
    public final ConstraintLayout layoutContent;
    private final ConstraintLayout rootView;
    public final CircleView viewBlack;
    public final CircleView viewBlue;
    public final CircleView viewGreen;
    public final CircleView viewPurple;
    public final CircleView viewRed;
    public final CircleView viewSize1;
    public final CircleView viewSize2;
    public final CircleView viewSize3;
    public final CircleView viewSize4;
    public final CircleView viewWhite;
    public final CircleView viewYellow;

    private FragmentSignatureBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, ColorPickerView colorPickerView, ConstraintLayout constraintLayout2, CircleView circleView, CircleView circleView2, CircleView circleView3, CircleView circleView4, CircleView circleView5, CircleView circleView6, CircleView circleView7, CircleView circleView8, CircleView circleView9, CircleView circleView10, CircleView circleView11) {
        this.rootView = constraintLayout;
        this.btnClear = appCompatImageView;
        this.btnRedo = appCompatImageView2;
        this.btnUndo = appCompatImageView3;
        this.ivBg = appCompatImageView4;
        this.ivCustomColor = appCompatImageView5;
        this.ivSize = appCompatImageView6;
        this.layoutColorPicker = colorPickerView;
        this.layoutContent = constraintLayout2;
        this.viewBlack = circleView;
        this.viewBlue = circleView2;
        this.viewGreen = circleView3;
        this.viewPurple = circleView4;
        this.viewRed = circleView5;
        this.viewSize1 = circleView6;
        this.viewSize2 = circleView7;
        this.viewSize3 = circleView8;
        this.viewSize4 = circleView9;
        this.viewWhite = circleView10;
        this.viewYellow = circleView11;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSignatureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSignatureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_signature, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSignatureBinding bind(View view) {
        int i = C1910R.id.btn_clear;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_redo;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.btn_undo;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.iv_bg;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView4 != null) {
                        i = C1910R.id.iv_custom_color;
                        AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView5 != null) {
                            i = C1910R.id.iv_size;
                            AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView6 != null) {
                                i = C1910R.id.layout_color_picker;
                                ColorPickerView colorPickerView = (ColorPickerView) ViewBindings.findChildViewById(view, i);
                                if (colorPickerView != null) {
                                    i = C1910R.id.layout_content;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                    if (constraintLayout != null) {
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
                                                            i = C1910R.id.view_size_1;
                                                            CircleView circleView6 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                            if (circleView6 != null) {
                                                                i = C1910R.id.view_size_2;
                                                                CircleView circleView7 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                                if (circleView7 != null) {
                                                                    i = C1910R.id.view_size_3;
                                                                    CircleView circleView8 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                                    if (circleView8 != null) {
                                                                        i = C1910R.id.view_size_4;
                                                                        CircleView circleView9 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                                        if (circleView9 != null) {
                                                                            i = C1910R.id.view_white;
                                                                            CircleView circleView10 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                                            if (circleView10 != null) {
                                                                                i = C1910R.id.view_yellow;
                                                                                CircleView circleView11 = (CircleView) ViewBindings.findChildViewById(view, i);
                                                                                if (circleView11 != null) {
                                                                                    return new FragmentSignatureBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, colorPickerView, constraintLayout, circleView, circleView2, circleView3, circleView4, circleView5, circleView6, circleView7, circleView8, circleView9, circleView10, circleView11);
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
