package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.NumKeyboard;

public final class DialogShareFrameInputBinding implements ViewBinding {
    public final AppCompatImageView btnCancel;
    public final AppCompatEditText etInputCode;
    public final NumKeyboard layoutNumKeyboard;
    private final ConstraintLayout rootView;

    private DialogShareFrameInputBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatEditText appCompatEditText, NumKeyboard numKeyboard) {
        this.rootView = constraintLayout;
        this.btnCancel = appCompatImageView;
        this.etInputCode = appCompatEditText;
        this.layoutNumKeyboard = numKeyboard;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogShareFrameInputBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogShareFrameInputBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.dialog_share_frame_input, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogShareFrameInputBinding bind(View view) {
        int i = C1910R.id.btn_cancel;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.et_input_code;
            AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
            if (appCompatEditText != null) {
                i = C1910R.id.layout_num_keyboard;
                NumKeyboard numKeyboard = (NumKeyboard) ViewBindings.findChildViewById(view, i);
                if (numKeyboard != null) {
                    return new DialogShareFrameInputBinding((ConstraintLayout) view, appCompatImageView, appCompatEditText, numKeyboard);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
