package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentChooseAiBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatButton btnInstructions;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvFrame;
    public final RecyclerView rvFrameTheme;
    public final AppCompatTextView tvBalance;
    public final StrokeTextView tvCountdown;

    private FragmentChooseAiBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatButton appCompatButton, RecyclerView recyclerView, RecyclerView recyclerView2, AppCompatTextView appCompatTextView, StrokeTextView strokeTextView) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.btnInstructions = appCompatButton;
        this.rvFrame = recyclerView;
        this.rvFrameTheme = recyclerView2;
        this.tvBalance = appCompatTextView;
        this.tvCountdown = strokeTextView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChooseAiBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChooseAiBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_choose_ai, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentChooseAiBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_instructions;
            AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, i);
            if (appCompatButton != null) {
                i = C1910R.id.rv_frame;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = C1910R.id.rv_frame_theme;
                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView2 != null) {
                        i = C1910R.id.tv_balance;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                        if (appCompatTextView != null) {
                            i = C1910R.id.tv_countdown;
                            StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                            if (strokeTextView != null) {
                                return new FragmentChooseAiBinding((AbsoluteLayout) view, appCompatImageView, appCompatButton, recyclerView, recyclerView2, appCompatTextView, strokeTextView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
