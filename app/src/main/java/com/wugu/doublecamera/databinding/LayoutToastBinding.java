package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.wugu.doublecamera.C1910R;

public final class LayoutToastBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView toastMessage;

    private LayoutToastBinding(TextView textView, TextView textView2) {
        this.rootView = textView;
        this.toastMessage = textView2;
    }

    @Override
    public TextView getRoot() {
        return this.rootView;
    }

    public static LayoutToastBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutToastBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_toast, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutToastBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        TextView textView = (TextView) view;
        return new LayoutToastBinding(textView, textView);
    }
}
