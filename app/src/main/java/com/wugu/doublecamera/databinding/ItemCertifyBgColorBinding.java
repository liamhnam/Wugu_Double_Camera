package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CircleView;

public final class ItemCertifyBgColorBinding implements ViewBinding {
    public final CircleView circleView;
    public final ConstraintLayout layoutRoot;
    private final ConstraintLayout rootView;

    private ItemCertifyBgColorBinding(ConstraintLayout constraintLayout, CircleView circleView, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.circleView = circleView;
        this.layoutRoot = constraintLayout2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemCertifyBgColorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemCertifyBgColorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.item_certify_bg_color, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemCertifyBgColorBinding bind(View view) {
        int i = C1910R.id.circle_view;
        CircleView circleView = (CircleView) ViewBindings.findChildViewById(view, i);
        if (circleView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            return new ItemCertifyBgColorBinding(constraintLayout, circleView, constraintLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
