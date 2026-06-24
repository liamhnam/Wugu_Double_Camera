package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.BeautySelectView;
import com.wugu.doublecamera.custom.CameraView;
import com.wugu.doublecamera.custom.CountdownView;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentPhotoBinding implements ViewBinding {
    public final AppCompatImageView btnConfirm;
    public final AppCompatImageView btnRetake;
    public final AppCompatImageView btnStartPhoto;
    public final FragmentContainerView fragmentPhotoAdded;
    public final BeautySelectView layoutBeauty;
    public final CameraView layoutCamera;
    public final CountdownView layoutCountdown;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvPhoto;
    public final RecyclerView rvRetake;
    public final StrokeTextView tvPhotoTimes;
    public final TextView tvRetakeCount;

    private FragmentPhotoBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, FragmentContainerView fragmentContainerView, BeautySelectView beautySelectView, CameraView cameraView, CountdownView countdownView, RecyclerView recyclerView, RecyclerView recyclerView2, StrokeTextView strokeTextView, TextView textView) {
        this.rootView = absoluteLayout;
        this.btnConfirm = appCompatImageView;
        this.btnRetake = appCompatImageView2;
        this.btnStartPhoto = appCompatImageView3;
        this.fragmentPhotoAdded = fragmentContainerView;
        this.layoutBeauty = beautySelectView;
        this.layoutCamera = cameraView;
        this.layoutCountdown = countdownView;
        this.rvPhoto = recyclerView;
        this.rvRetake = recyclerView2;
        this.tvPhotoTimes = strokeTextView;
        this.tvRetakeCount = textView;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPhotoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPhotoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_photo, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPhotoBinding bind(View view) {
        int i = C1910R.id.btn_confirm;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_retake;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.btn_start_photo;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.fragment_photo_added;
                    FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
                    if (fragmentContainerView != null) {
                        i = C1910R.id.layout_beauty;
                        BeautySelectView beautySelectView = (BeautySelectView) ViewBindings.findChildViewById(view, i);
                        if (beautySelectView != null) {
                            i = C1910R.id.layout_camera;
                            CameraView cameraView = (CameraView) ViewBindings.findChildViewById(view, i);
                            if (cameraView != null) {
                                i = C1910R.id.layout_countdown;
                                CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, i);
                                if (countdownView != null) {
                                    i = C1910R.id.rv_photo;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                    if (recyclerView != null) {
                                        i = C1910R.id.rv_retake;
                                        RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                        if (recyclerView2 != null) {
                                            i = C1910R.id.tv_photo_times;
                                            StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                            if (strokeTextView != null) {
                                                i = C1910R.id.tv_retake_count;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView != null) {
                                                    return new FragmentPhotoBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, fragmentContainerView, beautySelectView, cameraView, countdownView, recyclerView, recyclerView2, strokeTextView, textView);
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
