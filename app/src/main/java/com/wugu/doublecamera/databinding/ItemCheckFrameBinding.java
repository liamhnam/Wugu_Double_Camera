package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class ItemCheckFrameBinding implements ViewBinding {
    public final AppCompatImageView imageFrame;
    private final ConstraintLayout rootView;
    public final StrokeTextView tvCut;
    public final StrokeTextView tvSize;

    private ItemCheckFrameBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, StrokeTextView strokeTextView, StrokeTextView strokeTextView2) {
        this.rootView = constraintLayout;
        this.imageFrame = appCompatImageView;
        this.tvCut = strokeTextView;
        this.tvSize = strokeTextView2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemCheckFrameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemCheckFrameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_check_frame, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemCheckFrameBinding bind(View view) {
        int i = C1910R.id.image_frame;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.tv_cut;
            StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
            if (strokeTextView != null) {
                i = C1910R.id.tv_size;
                StrokeTextView strokeTextView2 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                if (strokeTextView2 != null) {
                    return new ItemCheckFrameBinding((ConstraintLayout) view, appCompatImageView, strokeTextView, strokeTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
