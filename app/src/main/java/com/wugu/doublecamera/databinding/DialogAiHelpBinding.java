package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class DialogAiHelpBinding implements ViewBinding {
    public final AppCompatImageView btnCancel;
    private final ConstraintLayout rootView;
    public final StrokeTextView tvCountdown;
    public final AppCompatTextView tvMsg;

    private DialogAiHelpBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, StrokeTextView strokeTextView, AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.btnCancel = appCompatImageView;
        this.tvCountdown = strokeTextView;
        this.tvMsg = appCompatTextView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogAiHelpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogAiHelpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.dialog_ai_help, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogAiHelpBinding bind(View view) {
        int i = C1910R.id.btn_cancel;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.tv_countdown;
            StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
            if (strokeTextView != null) {
                i = C1910R.id.tv_msg;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                if (appCompatTextView != null) {
                    return new DialogAiHelpBinding((ConstraintLayout) view, appCompatImageView, strokeTextView, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
