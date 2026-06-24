package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import com.wugu.doublecamera.C1910R;

public final class ItemReplaceBgBinding implements ViewBinding {
    private final AppCompatImageView rootView;

    private ItemReplaceBgBinding(AppCompatImageView appCompatImageView) {
        this.rootView = appCompatImageView;
    }

    @Override
    public AppCompatImageView getRoot() {
        return this.rootView;
    }

    public static ItemReplaceBgBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemReplaceBgBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_replace_bg, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemReplaceBgBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemReplaceBgBinding((AppCompatImageView) view);
    }
}
