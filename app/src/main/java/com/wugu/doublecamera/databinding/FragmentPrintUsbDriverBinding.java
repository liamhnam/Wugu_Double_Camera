package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class FragmentPrintUsbDriverBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    private final ConstraintLayout rootView;
    public final RecyclerView rvPhoto;
    public final AppCompatTextView tvTitle;

    private FragmentPrintUsbDriverBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, RecyclerView recyclerView, AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.btnBack = appCompatImageView;
        this.rvPhoto = recyclerView;
        this.tvTitle = appCompatTextView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPrintUsbDriverBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPrintUsbDriverBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_print_usb_driver, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPrintUsbDriverBinding bind(View view) {
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.rv_photo;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = C1910R.id.tv_title;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                if (appCompatTextView != null) {
                    return new FragmentPrintUsbDriverBinding((ConstraintLayout) view, appCompatImageView, recyclerView, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
