package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CameraView;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentPosterEasy2Binding implements ViewBinding {
    public final AppCompatImageView btnBackHome;
    public final AppCompatImageView btnCodeBack;
    public final AppCompatImageView btnConfirm;
    public final AppCompatImageView btnPayCancel;
    public final AppCompatImageView btnPrint;
    public final AppCompatImageView btnPrintPayCancel;
    public final AppCompatImageView btnRetake;
    public final AppCompatImageView btnStartPhoto;
    public final Group groupHomeView;
    public final Group groupMtdyTipsView;
    public final Group groupPayView;
    public final Group groupPrintPayView;
    public final Group groupPrintView;
    public final Group groupPrintingView;
    public final Group groupRetakeView;
    public final AppCompatImageView ivArrowLeft;
    public final AppCompatImageView ivArrowRight;
    public final AppCompatImageView ivBaseBg;
    public final AppCompatImageView ivDyReCode;
    public final AppCompatImageView ivHomeBg;
    public final AppCompatImageView ivHomeReCode;
    public final AppCompatImageView ivHomeTip;
    public final AppCompatImageView ivMtReCode;
    public final AppCompatImageView ivMtdyTips;
    public final AppCompatImageView ivNonCamera;
    public final AppCompatImageView ivNonNetwork;
    public final AppCompatImageView ivNonPrinter;
    public final AppCompatImageView ivNum1;
    public final AppCompatImageView ivNum2;
    public final AppCompatImageView ivNum3;
    public final AppCompatImageView ivNum4;
    public final AppCompatImageView ivNum5;
    public final AppCompatImageView ivNum6;
    public final AppCompatImageView ivPayBg;
    public final AppCompatImageView ivPayQrcode;
    public final AppCompatImageView ivPhoto;
    public final AppCompatImageView ivPhotoFreeze;
    public final AppCompatImageView ivPhotoQrcode;
    public final AppCompatImageView ivPhotoQrcodeBg;
    public final AppCompatImageView ivPrintBg;
    public final AppCompatImageView ivPrintDown;
    public final AppCompatImageView ivPrintPayBg;
    public final AppCompatImageView ivPrintPayQrcode;
    public final AppCompatImageView ivRetakeBg;
    public final AppCompatImageView ivUploadPrint;
    public final AppCompatImageView ivWaterMark;
    public final ConstraintLayout layoutBottom;
    public final CameraView layoutCamera;
    private final AbsoluteLayout rootView;
    public final RecyclerView rvRetake;
    public final AppCompatTextView tvBalance;
    public final AppCompatTextView tvBalance2;
    public final StrokeTextView tvCountdown;
    public final AppCompatTextView tvDate;
    public final AppCompatTextView tvPayPrice;
    public final StrokeTextView tvPhotoCountdown;
    public final AppCompatTextView tvPrintPayPrice;
    public final StrokeTextView tvPrintPrice;
    public final StrokeTextView tvRetakeCount;
    public final ViewPager2 vpFrame;

    private FragmentPosterEasy2Binding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, Group group, Group group2, Group group3, Group group4, Group group5, Group group6, Group group7, AppCompatImageView appCompatImageView9, AppCompatImageView appCompatImageView10, AppCompatImageView appCompatImageView11, AppCompatImageView appCompatImageView12, AppCompatImageView appCompatImageView13, AppCompatImageView appCompatImageView14, AppCompatImageView appCompatImageView15, AppCompatImageView appCompatImageView16, AppCompatImageView appCompatImageView17, AppCompatImageView appCompatImageView18, AppCompatImageView appCompatImageView19, AppCompatImageView appCompatImageView20, AppCompatImageView appCompatImageView21, AppCompatImageView appCompatImageView22, AppCompatImageView appCompatImageView23, AppCompatImageView appCompatImageView24, AppCompatImageView appCompatImageView25, AppCompatImageView appCompatImageView26, AppCompatImageView appCompatImageView27, AppCompatImageView appCompatImageView28, AppCompatImageView appCompatImageView29, AppCompatImageView appCompatImageView30, AppCompatImageView appCompatImageView31, AppCompatImageView appCompatImageView32, AppCompatImageView appCompatImageView33, AppCompatImageView appCompatImageView34, AppCompatImageView appCompatImageView35, AppCompatImageView appCompatImageView36, AppCompatImageView appCompatImageView37, AppCompatImageView appCompatImageView38, AppCompatImageView appCompatImageView39, ConstraintLayout constraintLayout, CameraView cameraView, RecyclerView recyclerView, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, StrokeTextView strokeTextView, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, StrokeTextView strokeTextView2, AppCompatTextView appCompatTextView5, StrokeTextView strokeTextView3, StrokeTextView strokeTextView4, ViewPager2 viewPager2) {
        this.rootView = absoluteLayout;
        this.btnBackHome = appCompatImageView;
        this.btnCodeBack = appCompatImageView2;
        this.btnConfirm = appCompatImageView3;
        this.btnPayCancel = appCompatImageView4;
        this.btnPrint = appCompatImageView5;
        this.btnPrintPayCancel = appCompatImageView6;
        this.btnRetake = appCompatImageView7;
        this.btnStartPhoto = appCompatImageView8;
        this.groupHomeView = group;
        this.groupMtdyTipsView = group2;
        this.groupPayView = group3;
        this.groupPrintPayView = group4;
        this.groupPrintView = group5;
        this.groupPrintingView = group6;
        this.groupRetakeView = group7;
        this.ivArrowLeft = appCompatImageView9;
        this.ivArrowRight = appCompatImageView10;
        this.ivBaseBg = appCompatImageView11;
        this.ivDyReCode = appCompatImageView12;
        this.ivHomeBg = appCompatImageView13;
        this.ivHomeReCode = appCompatImageView14;
        this.ivHomeTip = appCompatImageView15;
        this.ivMtReCode = appCompatImageView16;
        this.ivMtdyTips = appCompatImageView17;
        this.ivNonCamera = appCompatImageView18;
        this.ivNonNetwork = appCompatImageView19;
        this.ivNonPrinter = appCompatImageView20;
        this.ivNum1 = appCompatImageView21;
        this.ivNum2 = appCompatImageView22;
        this.ivNum3 = appCompatImageView23;
        this.ivNum4 = appCompatImageView24;
        this.ivNum5 = appCompatImageView25;
        this.ivNum6 = appCompatImageView26;
        this.ivPayBg = appCompatImageView27;
        this.ivPayQrcode = appCompatImageView28;
        this.ivPhoto = appCompatImageView29;
        this.ivPhotoFreeze = appCompatImageView30;
        this.ivPhotoQrcode = appCompatImageView31;
        this.ivPhotoQrcodeBg = appCompatImageView32;
        this.ivPrintBg = appCompatImageView33;
        this.ivPrintDown = appCompatImageView34;
        this.ivPrintPayBg = appCompatImageView35;
        this.ivPrintPayQrcode = appCompatImageView36;
        this.ivRetakeBg = appCompatImageView37;
        this.ivUploadPrint = appCompatImageView38;
        this.ivWaterMark = appCompatImageView39;
        this.layoutBottom = constraintLayout;
        this.layoutCamera = cameraView;
        this.rvRetake = recyclerView;
        this.tvBalance = appCompatTextView;
        this.tvBalance2 = appCompatTextView2;
        this.tvCountdown = strokeTextView;
        this.tvDate = appCompatTextView3;
        this.tvPayPrice = appCompatTextView4;
        this.tvPhotoCountdown = strokeTextView2;
        this.tvPrintPayPrice = appCompatTextView5;
        this.tvPrintPrice = strokeTextView3;
        this.tvRetakeCount = strokeTextView4;
        this.vpFrame = viewPager2;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPosterEasy2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPosterEasy2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_poster_easy2, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPosterEasy2Binding bind(View view) {
        int i = C1910R.id.btn_back_home;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_code_back;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.btn_confirm;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.btn_pay_cancel;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView4 != null) {
                        i = C1910R.id.btn_print;
                        AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView5 != null) {
                            i = C1910R.id.btn_print_pay_cancel;
                            AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView6 != null) {
                                i = C1910R.id.btn_retake;
                                AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                if (appCompatImageView7 != null) {
                                    i = C1910R.id.btn_start_photo;
                                    AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView8 != null) {
                                        i = C1910R.id.group_home_view;
                                        Group group = (Group) ViewBindings.findChildViewById(view, i);
                                        if (group != null) {
                                            i = C1910R.id.group_mtdy_tips_view;
                                            Group group2 = (Group) ViewBindings.findChildViewById(view, i);
                                            if (group2 != null) {
                                                i = C1910R.id.group_pay_view;
                                                Group group3 = (Group) ViewBindings.findChildViewById(view, i);
                                                if (group3 != null) {
                                                    i = C1910R.id.group_print_pay_view;
                                                    Group group4 = (Group) ViewBindings.findChildViewById(view, i);
                                                    if (group4 != null) {
                                                        i = C1910R.id.group_print_view;
                                                        Group group5 = (Group) ViewBindings.findChildViewById(view, i);
                                                        if (group5 != null) {
                                                            i = C1910R.id.group_printing_view;
                                                            Group group6 = (Group) ViewBindings.findChildViewById(view, i);
                                                            if (group6 != null) {
                                                                i = C1910R.id.group_retake_view;
                                                                Group group7 = (Group) ViewBindings.findChildViewById(view, i);
                                                                if (group7 != null) {
                                                                    i = C1910R.id.iv_arrow_left;
                                                                    AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                    if (appCompatImageView9 != null) {
                                                                        i = C1910R.id.iv_arrow_right;
                                                                        AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                        if (appCompatImageView10 != null) {
                                                                            i = C1910R.id.iv_base_bg;
                                                                            AppCompatImageView appCompatImageView11 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                            if (appCompatImageView11 != null) {
                                                                                i = C1910R.id.iv_dy_re_code;
                                                                                AppCompatImageView appCompatImageView12 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                if (appCompatImageView12 != null) {
                                                                                    i = C1910R.id.iv_home_bg;
                                                                                    AppCompatImageView appCompatImageView13 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                    if (appCompatImageView13 != null) {
                                                                                        i = C1910R.id.iv_home_re_code;
                                                                                        AppCompatImageView appCompatImageView14 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                        if (appCompatImageView14 != null) {
                                                                                            i = C1910R.id.iv_home_tip;
                                                                                            AppCompatImageView appCompatImageView15 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                            if (appCompatImageView15 != null) {
                                                                                                i = C1910R.id.iv_mt_re_code;
                                                                                                AppCompatImageView appCompatImageView16 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                if (appCompatImageView16 != null) {
                                                                                                    i = C1910R.id.iv_mtdy_tips;
                                                                                                    AppCompatImageView appCompatImageView17 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                    if (appCompatImageView17 != null) {
                                                                                                        i = C1910R.id.iv_non_camera;
                                                                                                        AppCompatImageView appCompatImageView18 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                        if (appCompatImageView18 != null) {
                                                                                                            i = C1910R.id.iv_non_network;
                                                                                                            AppCompatImageView appCompatImageView19 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                            if (appCompatImageView19 != null) {
                                                                                                                i = C1910R.id.iv_non_printer;
                                                                                                                AppCompatImageView appCompatImageView20 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                if (appCompatImageView20 != null) {
                                                                                                                    i = C1910R.id.iv_num1;
                                                                                                                    AppCompatImageView appCompatImageView21 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                    if (appCompatImageView21 != null) {
                                                                                                                        i = C1910R.id.iv_num2;
                                                                                                                        AppCompatImageView appCompatImageView22 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                        if (appCompatImageView22 != null) {
                                                                                                                            i = C1910R.id.iv_num3;
                                                                                                                            AppCompatImageView appCompatImageView23 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                            if (appCompatImageView23 != null) {
                                                                                                                                i = C1910R.id.iv_num4;
                                                                                                                                AppCompatImageView appCompatImageView24 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                if (appCompatImageView24 != null) {
                                                                                                                                    i = C1910R.id.iv_num5;
                                                                                                                                    AppCompatImageView appCompatImageView25 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                    if (appCompatImageView25 != null) {
                                                                                                                                        i = C1910R.id.iv_num6;
                                                                                                                                        AppCompatImageView appCompatImageView26 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                        if (appCompatImageView26 != null) {
                                                                                                                                            i = C1910R.id.iv_pay_bg;
                                                                                                                                            AppCompatImageView appCompatImageView27 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                            if (appCompatImageView27 != null) {
                                                                                                                                                i = C1910R.id.iv_pay_qrcode;
                                                                                                                                                AppCompatImageView appCompatImageView28 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                if (appCompatImageView28 != null) {
                                                                                                                                                    i = C1910R.id.iv_photo;
                                                                                                                                                    AppCompatImageView appCompatImageView29 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                    if (appCompatImageView29 != null) {
                                                                                                                                                        i = C1910R.id.iv_photo_freeze;
                                                                                                                                                        AppCompatImageView appCompatImageView30 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                        if (appCompatImageView30 != null) {
                                                                                                                                                            i = C1910R.id.iv_photo_qrcode;
                                                                                                                                                            AppCompatImageView appCompatImageView31 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                            if (appCompatImageView31 != null) {
                                                                                                                                                                i = C1910R.id.iv_photo_qrcode_bg;
                                                                                                                                                                AppCompatImageView appCompatImageView32 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                if (appCompatImageView32 != null) {
                                                                                                                                                                    i = C1910R.id.iv_print_bg;
                                                                                                                                                                    AppCompatImageView appCompatImageView33 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                    if (appCompatImageView33 != null) {
                                                                                                                                                                        i = C1910R.id.iv_print_down;
                                                                                                                                                                        AppCompatImageView appCompatImageView34 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                        if (appCompatImageView34 != null) {
                                                                                                                                                                            i = C1910R.id.iv_print_pay_bg;
                                                                                                                                                                            AppCompatImageView appCompatImageView35 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                            if (appCompatImageView35 != null) {
                                                                                                                                                                                i = C1910R.id.iv_print_pay_qrcode;
                                                                                                                                                                                AppCompatImageView appCompatImageView36 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                if (appCompatImageView36 != null) {
                                                                                                                                                                                    i = C1910R.id.iv_retake_bg;
                                                                                                                                                                                    AppCompatImageView appCompatImageView37 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                    if (appCompatImageView37 != null) {
                                                                                                                                                                                        i = C1910R.id.iv_upload_print;
                                                                                                                                                                                        AppCompatImageView appCompatImageView38 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                        if (appCompatImageView38 != null) {
                                                                                                                                                                                            i = C1910R.id.iv_water_mark;
                                                                                                                                                                                            AppCompatImageView appCompatImageView39 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                            if (appCompatImageView39 != null) {
                                                                                                                                                                                                i = C1910R.id.layout_bottom;
                                                                                                                                                                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                if (constraintLayout != null) {
                                                                                                                                                                                                    i = C1910R.id.layout_camera;
                                                                                                                                                                                                    CameraView cameraView = (CameraView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                    if (cameraView != null) {
                                                                                                                                                                                                        i = C1910R.id.rv_retake;
                                                                                                                                                                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                        if (recyclerView != null) {
                                                                                                                                                                                                            i = C1910R.id.tv_balance;
                                                                                                                                                                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                            if (appCompatTextView != null) {
                                                                                                                                                                                                                i = C1910R.id.tv_balance2;
                                                                                                                                                                                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                if (appCompatTextView2 != null) {
                                                                                                                                                                                                                    i = C1910R.id.tv_countdown;
                                                                                                                                                                                                                    StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                    if (strokeTextView != null) {
                                                                                                                                                                                                                        i = C1910R.id.tv_date;
                                                                                                                                                                                                                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                        if (appCompatTextView3 != null) {
                                                                                                                                                                                                                            i = C1910R.id.tv_pay_price;
                                                                                                                                                                                                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                            if (appCompatTextView4 != null) {
                                                                                                                                                                                                                                i = C1910R.id.tv_photo_countdown;
                                                                                                                                                                                                                                StrokeTextView strokeTextView2 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                if (strokeTextView2 != null) {
                                                                                                                                                                                                                                    i = C1910R.id.tv_print_pay_price;
                                                                                                                                                                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                    if (appCompatTextView5 != null) {
                                                                                                                                                                                                                                        i = C1910R.id.tv_print_price;
                                                                                                                                                                                                                                        StrokeTextView strokeTextView3 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                        if (strokeTextView3 != null) {
                                                                                                                                                                                                                                            i = C1910R.id.tv_retake_count;
                                                                                                                                                                                                                                            StrokeTextView strokeTextView4 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                            if (strokeTextView4 != null) {
                                                                                                                                                                                                                                                i = C1910R.id.vp_frame;
                                                                                                                                                                                                                                                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                                                                if (viewPager2 != null) {
                                                                                                                                                                                                                                                    return new FragmentPosterEasy2Binding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, group, group2, group3, group4, group5, group6, group7, appCompatImageView9, appCompatImageView10, appCompatImageView11, appCompatImageView12, appCompatImageView13, appCompatImageView14, appCompatImageView15, appCompatImageView16, appCompatImageView17, appCompatImageView18, appCompatImageView19, appCompatImageView20, appCompatImageView21, appCompatImageView22, appCompatImageView23, appCompatImageView24, appCompatImageView25, appCompatImageView26, appCompatImageView27, appCompatImageView28, appCompatImageView29, appCompatImageView30, appCompatImageView31, appCompatImageView32, appCompatImageView33, appCompatImageView34, appCompatImageView35, appCompatImageView36, appCompatImageView37, appCompatImageView38, appCompatImageView39, constraintLayout, cameraView, recyclerView, appCompatTextView, appCompatTextView2, strokeTextView, appCompatTextView3, appCompatTextView4, strokeTextView2, appCompatTextView5, strokeTextView3, strokeTextView4, viewPager2);
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
