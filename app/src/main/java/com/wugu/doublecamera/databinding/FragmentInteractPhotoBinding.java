package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CameraView;
import com.wugu.doublecamera.custom.CountdownView;

public final class FragmentInteractPhotoBinding implements ViewBinding {
    public final AppCompatImageView btnStartPhoto;
    public final CameraView layoutCamera;
    public final CountdownView layoutCountdown;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvFilter;

    private FragmentInteractPhotoBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, CameraView cameraView, CountdownView countdownView, RecyclerView recyclerView) {
        this.rootView = absoluteLayout;
        this.btnStartPhoto = appCompatImageView;
        this.layoutCamera = cameraView;
        this.layoutCountdown = countdownView;
        this.rvFilter = recyclerView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentInteractPhotoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentInteractPhotoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_interact_photo, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentInteractPhotoBinding bind(View view) {
        int i = C1910R.id.btn_start_photo;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.layout_camera;
            CameraView cameraView = (CameraView) ViewBindings.findChildViewById(view, i);
            if (cameraView != null) {
                i = C1910R.id.layout_countdown;
                CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
                if (countdownView != null) {
                    i = C1910R.id.rv_filter;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView != null) {
                        return new FragmentInteractPhotoBinding((AbsoluteLayout) view, appCompatImageView, cameraView, countdownView, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
