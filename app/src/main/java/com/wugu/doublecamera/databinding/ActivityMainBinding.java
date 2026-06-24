package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class ActivityMainBinding implements ViewBinding {
    public final FragmentContainerView container;
    public final AppCompatImageView ivPhotoTransit;
    private final ConstraintLayout rootView;
    public final AppCompatImageView statusBarLogo;
    public final AppCompatTextView statusDeviceName;
    public final AppCompatTextView statusPhone;
    public final AppCompatImageView statusWechat;
    public final AppCompatTextView tvWechatNum;

    private ActivityMainBinding(ConstraintLayout constraintLayout, FragmentContainerView fragmentContainerView, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatImageView appCompatImageView3, AppCompatTextView appCompatTextView3) {
        this.rootView = constraintLayout;
        this.container = fragmentContainerView;
        this.ivPhotoTransit = appCompatImageView;
        this.statusBarLogo = appCompatImageView2;
        this.statusDeviceName = appCompatTextView;
        this.statusPhone = appCompatTextView2;
        this.statusWechat = appCompatImageView3;
        this.tvWechatNum = appCompatTextView3;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainBinding bind(View view) {
        int i = C1910R.id.container;
        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
        if (fragmentContainerView != null) {
            i = C1910R.id.iv_photo_transit;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView != null) {
                i = C1910R.id.status_bar_logo;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null) {
                    i = C1910R.id.status_device_name;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                    if (appCompatTextView != null) {
                        i = C1910R.id.status_phone;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                        if (appCompatTextView2 != null) {
                            i = C1910R.id.status_wechat;
                            AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView3 != null) {
                                i = C1910R.id.tv_wechat_num;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                if (appCompatTextView3 != null) {
                                    return new ActivityMainBinding((ConstraintLayout) view, fragmentContainerView, appCompatImageView, appCompatImageView2, appCompatTextView, appCompatTextView2, appCompatImageView3, appCompatTextView3);
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
