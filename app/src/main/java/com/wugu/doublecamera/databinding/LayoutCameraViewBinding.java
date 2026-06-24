package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.AutoSizeGLSurface;
import com.wugu.doublecamera.custom.CircleRevealView;

public final class LayoutCameraViewBinding implements ViewBinding {
    public final AutoSizeGLSurface glSurface;
    public final AppCompatImageView ivCamera;
    public final AppCompatImageView ivPhotoBorder;
    private final ConstraintLayout rootView;
    public final CircleRevealView vCircleAnim;
    public final View vHideBorderEnd;
    public final View vHideBorderStart;

    private LayoutCameraViewBinding(ConstraintLayout constraintLayout, AutoSizeGLSurface autoSizeGLSurface, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, CircleRevealView circleRevealView, View view, View view2) {
        this.rootView = constraintLayout;
        this.glSurface = autoSizeGLSurface;
        this.ivCamera = appCompatImageView;
        this.ivPhotoBorder = appCompatImageView2;
        this.vCircleAnim = circleRevealView;
        this.vHideBorderEnd = view;
        this.vHideBorderStart = view2;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutCameraViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutCameraViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.layout_camera_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutCameraViewBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = C1910R.id.gl_surface;
        AutoSizeGLSurface autoSizeGLSurface = (AutoSizeGLSurface) ViewBindings.findChildViewById(view, i);
        if (autoSizeGLSurface != null) {
            i = C1910R.id.iv_camera;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView != null) {
                i = C1910R.id.iv_photo_border;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null) {
                    i = C1910R.id.v_circle_anim;
                    CircleRevealView circleRevealView = (CircleRevealView) ViewBindings.findChildViewById(view, i);
                    if (circleRevealView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = C1910R.id.v_hide_border_end))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = C1910R.id.v_hide_border_start))) != null) {
                        return new LayoutCameraViewBinding((ConstraintLayout) view, autoSizeGLSurface, appCompatImageView, appCompatImageView2, circleRevealView, viewFindChildViewById, viewFindChildViewById2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
