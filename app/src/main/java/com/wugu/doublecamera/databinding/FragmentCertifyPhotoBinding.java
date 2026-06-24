package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CameraView;
import com.wugu.doublecamera.custom.CountdownView;

public final class FragmentCertifyPhotoBinding implements ViewBinding {
    public final AppCompatImageView btnConfirm;
    public final AppCompatImageView btnRetake;
    public final AppCompatImageView btnStartPhoto;
    public final AppCompatImageView ivBlue;
    public final AppCompatImageView ivCutoutPicture;
    public final AppCompatImageView ivRed;
    public final AppCompatImageView ivWhite;
    public final CameraView layoutCamera;
    public final CountdownView layoutCountdown;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvRetake;
    public final TextView tvRetakeCount;
    public final AppCompatImageView viewConfirmBg;

    private FragmentCertifyPhotoBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, CameraView cameraView, CountdownView countdownView, RecyclerView recyclerView, TextView textView, AppCompatImageView appCompatImageView8) {
        this.rootView = absoluteLayout;
        this.btnConfirm = appCompatImageView;
        this.btnRetake = appCompatImageView2;
        this.btnStartPhoto = appCompatImageView3;
        this.ivBlue = appCompatImageView4;
        this.ivCutoutPicture = appCompatImageView5;
        this.ivRed = appCompatImageView6;
        this.ivWhite = appCompatImageView7;
        this.layoutCamera = cameraView;
        this.layoutCountdown = countdownView;
        this.rvRetake = recyclerView;
        this.tvRetakeCount = textView;
        this.viewConfirmBg = appCompatImageView8;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCertifyPhotoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCertifyPhotoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_certify_photo, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCertifyPhotoBinding bind(View view) {
        int i = C1910R.id.btn_confirm;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_retake;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.btn_start_photo;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.iv_blue;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView4 != null) {
                        i = C1910R.id.iv_cutout_picture;
                        AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView5 != null) {
                            i = C1910R.id.iv_red;
                            AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView6 != null) {
                                i = C1910R.id.iv_white;
                                AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView7 != null) {
                                    i = C1910R.id.layout_camera;
                                    CameraView cameraView = (CameraView) ViewBindings.findChildViewById(view, i);
                                    if (cameraView != null) {
                                        i = C1910R.id.layout_countdown;
                                        CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
                                        if (countdownView != null) {
                                            i = C1910R.id.rv_retake;
                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                            if (recyclerView != null) {
                                                i = C1910R.id.tv_retake_count;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView != null) {
                                                    i = C1910R.id.view_confirm_bg;
                                                    AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                    if (appCompatImageView8 != null) {
                                                        return new FragmentCertifyPhotoBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, cameraView, countdownView, recyclerView, textView, appCompatImageView8);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
