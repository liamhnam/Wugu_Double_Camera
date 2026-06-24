package com.wugu.doublecamera.databinding;

import android.opengl.GLSurfaceView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentHomeBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final AppCompatImageView btnEnter;
    public final GLSurfaceView glSurfaceView;
    public final AppCompatImageView ivAiTab;
    public final AppCompatImageView ivCardTab;
    public final AppCompatImageView ivCertifyTab;
    public final AppCompatImageView ivHeadTab;
    public final AppCompatImageView ivIpTab;
    public final AppCompatImageView ivNonCamera;
    public final AppCompatImageView ivNonNetwork;
    public final AppCompatImageView ivNonPrinter;
    public final AppCompatImageView ivNonServer;
    public final AppCompatImageView ivPrintTab;
    public final AppCompatImageView ivReplaceTab;
    private final AbsoluteLayout rootView;
    public final AppCompatTextView tvBalance;
    public final StrokeTextView tvCountdown;
    public final View vLock;

    private FragmentHomeBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, GLSurfaceView gLSurfaceView, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, AppCompatImageView appCompatImageView9, AppCompatImageView appCompatImageView10, AppCompatImageView appCompatImageView11, AppCompatImageView appCompatImageView12, AppCompatImageView appCompatImageView13, AppCompatTextView appCompatTextView, StrokeTextView strokeTextView, View view) {
        this.rootView = absoluteLayout;
        this.btnBack = appCompatImageView;
        this.btnEnter = appCompatImageView2;
        this.glSurfaceView = gLSurfaceView;
        this.ivAiTab = appCompatImageView3;
        this.ivCardTab = appCompatImageView4;
        this.ivCertifyTab = appCompatImageView5;
        this.ivHeadTab = appCompatImageView6;
        this.ivIpTab = appCompatImageView7;
        this.ivNonCamera = appCompatImageView8;
        this.ivNonNetwork = appCompatImageView9;
        this.ivNonPrinter = appCompatImageView10;
        this.ivNonServer = appCompatImageView11;
        this.ivPrintTab = appCompatImageView12;
        this.ivReplaceTab = appCompatImageView13;
        this.tvBalance = appCompatTextView;
        this.tvCountdown = strokeTextView;
        this.vLock = view;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentHomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_home, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentHomeBinding bind(View view) {
        View viewFindChildViewById;
        int i = C1910R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_enter;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.gl_surface_view;
                GLSurfaceView gLSurfaceView = (GLSurfaceView) ViewBindings.findChildViewById(view, i);
                if (gLSurfaceView != null) {
                    i = C1910R.id.iv_ai_tab;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView3 != null) {
                        i = C1910R.id.iv_card_tab;
                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView4 != null) {
                            i = C1910R.id.iv_certify_tab;
                            AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView5 != null) {
                                i = C1910R.id.iv_head_tab;
                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView6 != null) {
                                    i = C1910R.id.iv_ip_tab;
                                    AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView7 != null) {
                                        i = C1910R.id.iv_non_camera;
                                        AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                        if (appCompatImageView8 != null) {
                                            i = C1910R.id.iv_non_network;
                                            AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                            if (appCompatImageView9 != null) {
                                                i = C1910R.id.iv_non_printer;
                                                AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatImageView10 != null) {
                                                    i = C1910R.id.iv_non_server;
                                                    AppCompatImageView appCompatImageView11 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                    if (appCompatImageView11 != null) {
                                                        i = C1910R.id.iv_print_tab;
                                                        AppCompatImageView appCompatImageView12 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                        if (appCompatImageView12 != null) {
                                                            i = C1910R.id.iv_replace_tab;
                                                            AppCompatImageView appCompatImageView13 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                            if (appCompatImageView13 != null) {
                                                                i = C1910R.id.tv_balance;
                                                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                if (appCompatTextView != null) {
                                                                    i = C1910R.id.tv_countdown;
                                                                    StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                    if (strokeTextView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = C1910R.id.v_lock))) != null) {
                                                                        return new FragmentHomeBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, gLSurfaceView, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, appCompatImageView11, appCompatImageView12, appCompatImageView13, appCompatTextView, strokeTextView, viewFindChildViewById);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
