package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import com.wugu.doublecamera.C1910R;

public final class ItemUploadFormSelectBinding implements ViewBinding {
    private final AppCompatImageView rootView;

    private ItemUploadFormSelectBinding(AppCompatImageView appCompatImageView) {
        this.rootView = appCompatImageView;
    }

    @Override
    public AppCompatImageView getRoot() {
        return this.rootView;
    }

    public static ItemUploadFormSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemUploadFormSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_upload_form_select, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemUploadFormSelectBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemUploadFormSelectBinding((AppCompatImageView) view);
    }
}
