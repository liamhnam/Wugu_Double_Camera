package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class LayoutNumKeyboardBinding implements ViewBinding {
    public final AppCompatImageView button0;
    public final AppCompatImageView button1;
    public final AppCompatImageView button2;
    public final AppCompatImageView button3;
    public final AppCompatImageView button4;
    public final AppCompatImageView button5;
    public final AppCompatImageView button6;
    public final AppCompatImageView button7;
    public final AppCompatImageView button8;
    public final AppCompatImageView button9;
    public final AppCompatImageView buttonConfirm;
    public final AppCompatImageView buttonDelete;
    private final ConstraintLayout rootView;

    private LayoutNumKeyboardBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, AppCompatImageView appCompatImageView9, AppCompatImageView appCompatImageView10, AppCompatImageView appCompatImageView11, AppCompatImageView appCompatImageView12) {
        this.rootView = constraintLayout;
        this.button0 = appCompatImageView;
        this.button1 = appCompatImageView2;
        this.button2 = appCompatImageView3;
        this.button3 = appCompatImageView4;
        this.button4 = appCompatImageView5;
        this.button5 = appCompatImageView6;
        this.button6 = appCompatImageView7;
        this.button7 = appCompatImageView8;
        this.button8 = appCompatImageView9;
        this.button9 = appCompatImageView10;
        this.buttonConfirm = appCompatImageView11;
        this.buttonDelete = appCompatImageView12;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutNumKeyboardBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutNumKeyboardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_num_keyboard, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutNumKeyboardBinding bind(View view) {
        int i = C1910R.id.button0;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.button1;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.button2;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.button3;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView4 != null) {
                        i = C1910R.id.button4;
                        AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView5 != null) {
                            i = C1910R.id.button5;
                            AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView6 != null) {
                                i = C1910R.id.button6;
                                AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView7 != null) {
                                    i = C1910R.id.button7;
                                    AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView8 != null) {
                                        i = C1910R.id.button8;
                                        AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                        if (appCompatImageView9 != null) {
                                            i = C1910R.id.button9;
                                            AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                            if (appCompatImageView10 != null) {
                                                i = C1910R.id.buttonConfirm;
                                                AppCompatImageView appCompatImageView11 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatImageView11 != null) {
                                                    i = C1910R.id.buttonDelete;
                                                    AppCompatImageView appCompatImageView12 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                    if (appCompatImageView12 != null) {
                                                        return new LayoutNumKeyboardBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, appCompatImageView11, appCompatImageView12);
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
