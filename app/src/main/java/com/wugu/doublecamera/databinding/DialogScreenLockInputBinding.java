package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.NumKeyboard;

public final class DialogScreenLockInputBinding implements ViewBinding {
    public final AppCompatEditText etInputCode;
    public final NumKeyboard layoutNumKeyboard;
    private final ConstraintLayout rootView;

    private DialogScreenLockInputBinding(ConstraintLayout constraintLayout, AppCompatEditText appCompatEditText, NumKeyboard numKeyboard) {
        this.rootView = constraintLayout;
        this.etInputCode = appCompatEditText;
        this.layoutNumKeyboard = numKeyboard;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogScreenLockInputBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogScreenLockInputBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.dialog_screen_lock_input, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogScreenLockInputBinding bind(View view) {
        int i = C1910R.id.et_input_code;
        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
        if (appCompatEditText != null) {
            i = C1910R.id.layout_num_keyboard;
            NumKeyboard numKeyboard = (NumKeyboard) ViewBindings.findChildViewById(view, i);
            if (numKeyboard != null) {
                return new DialogScreenLockInputBinding((ConstraintLayout) view, appCompatEditText, numKeyboard);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
