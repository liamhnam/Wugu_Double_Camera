package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import com.wugu.doublecamera.C1910R;

public final class ItemUploadFrameIcSelectBinding implements ViewBinding {
    private final AppCompatImageView rootView;

    private ItemUploadFrameIcSelectBinding(AppCompatImageView appCompatImageView) {
        this.rootView = appCompatImageView;
    }

    @Override
    public AppCompatImageView getRoot() {
        return this.rootView;
    }

    public static ItemUploadFrameIcSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemUploadFrameIcSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_upload_frame_ic_select, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemUploadFrameIcSelectBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemUploadFrameIcSelectBinding((AppCompatImageView) view);
    }
}
