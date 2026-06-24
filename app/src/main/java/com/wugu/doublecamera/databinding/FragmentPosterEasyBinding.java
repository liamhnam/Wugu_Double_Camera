package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.CameraView;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentPosterEasyBinding implements ViewBinding {
    public final AppCompatImageView btnBackHome;
    public final AppCompatImageView btnPayCancel;
    public final AppCompatImageView btnPrint;
    public final AppCompatImageView btnPrintPayCancel;
    public final AppCompatImageView btnStartPhoto;
    public final Group groupHomeView;
    public final Group groupPayView;
    public final Group groupPrintPayView;
    public final Group groupPrintView;
    public final Group groupPrintingView;
    public final AppCompatImageView ivAdd;
    public final AppCompatImageView ivArrowLeft;
    public final AppCompatImageView ivArrowRight;
    public final AppCompatImageView ivHomeBg;
    public final AppCompatImageView ivHomeReCode;
    public final AppCompatImageView ivNonCamera;
    public final AppCompatImageView ivNonNetwork;
    public final AppCompatImageView ivNonPrinter;
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
    public final AppCompatImageView ivSub;
    public final AppCompatImageView ivWaterMark;
    public final ConstraintLayout layoutBottom;
    public final CameraView layoutCamera;
    private final AbsoluteLayout rootView;
    public final AppCompatTextView tvBalance;
    public final AppCompatTextView tvBalance2;
    public final StrokeTextView tvCountdown;
    public final AppCompatTextView tvDate;
    public final AppCompatTextView tvPayPrice;
    public final StrokeTextView tvPhotoCountdown;
    public final StrokeTextView tvPrintCount;
    public final AppCompatTextView tvPrintPayPrice;
    public final StrokeTextView tvPrintPrice;
    public final ViewPager2 vpFrame;

    private FragmentPosterEasyBinding(AbsoluteLayout absoluteLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, Group group, Group group2, Group group3, Group group4, Group group5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, AppCompatImageView appCompatImageView9, AppCompatImageView appCompatImageView10, AppCompatImageView appCompatImageView11, AppCompatImageView appCompatImageView12, AppCompatImageView appCompatImageView13, AppCompatImageView appCompatImageView14, AppCompatImageView appCompatImageView15, AppCompatImageView appCompatImageView16, AppCompatImageView appCompatImageView17, AppCompatImageView appCompatImageView18, AppCompatImageView appCompatImageView19, AppCompatImageView appCompatImageView20, AppCompatImageView appCompatImageView21, AppCompatImageView appCompatImageView22, AppCompatImageView appCompatImageView23, AppCompatImageView appCompatImageView24, AppCompatImageView appCompatImageView25, ConstraintLayout constraintLayout, CameraView cameraView, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, StrokeTextView strokeTextView, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, StrokeTextView strokeTextView2, StrokeTextView strokeTextView3, AppCompatTextView appCompatTextView5, StrokeTextView strokeTextView4, ViewPager2 viewPager2) {
        this.rootView = absoluteLayout;
        this.btnBackHome = appCompatImageView;
        this.btnPayCancel = appCompatImageView2;
        this.btnPrint = appCompatImageView3;
        this.btnPrintPayCancel = appCompatImageView4;
        this.btnStartPhoto = appCompatImageView5;
        this.groupHomeView = group;
        this.groupPayView = group2;
        this.groupPrintPayView = group3;
        this.groupPrintView = group4;
        this.groupPrintingView = group5;
        this.ivAdd = appCompatImageView6;
        this.ivArrowLeft = appCompatImageView7;
        this.ivArrowRight = appCompatImageView8;
        this.ivHomeBg = appCompatImageView9;
        this.ivHomeReCode = appCompatImageView10;
        this.ivNonCamera = appCompatImageView11;
        this.ivNonNetwork = appCompatImageView12;
        this.ivNonPrinter = appCompatImageView13;
        this.ivPayBg = appCompatImageView14;
        this.ivPayQrcode = appCompatImageView15;
        this.ivPhoto = appCompatImageView16;
        this.ivPhotoFreeze = appCompatImageView17;
        this.ivPhotoQrcode = appCompatImageView18;
        this.ivPhotoQrcodeBg = appCompatImageView19;
        this.ivPrintBg = appCompatImageView20;
        this.ivPrintDown = appCompatImageView21;
        this.ivPrintPayBg = appCompatImageView22;
        this.ivPrintPayQrcode = appCompatImageView23;
        this.ivSub = appCompatImageView24;
        this.ivWaterMark = appCompatImageView25;
        this.layoutBottom = constraintLayout;
        this.layoutCamera = cameraView;
        this.tvBalance = appCompatTextView;
        this.tvBalance2 = appCompatTextView2;
        this.tvCountdown = strokeTextView;
        this.tvDate = appCompatTextView3;
        this.tvPayPrice = appCompatTextView4;
        this.tvPhotoCountdown = strokeTextView2;
        this.tvPrintCount = strokeTextView3;
        this.tvPrintPayPrice = appCompatTextView5;
        this.tvPrintPrice = strokeTextView4;
        this.vpFrame = viewPager2;
    }

    @Override
    public AbsoluteLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPosterEasyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPosterEasyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_poster_easy, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPosterEasyBinding bind(View view) {
        int i = C1910R.id.btn_back_home;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = C1910R.id.btn_pay_cancel;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = C1910R.id.btn_print;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = C1910R.id.btn_print_pay_cancel;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView4 != null) {
                        i = C1910R.id.btn_start_photo;
                        AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView5 != null) {
                            i = C1910R.id.group_home_view;
                            Group group = (Group) ViewBindings.findChildViewById(view, i);
                            if (group != null) {
                                i = C1910R.id.group_pay_view;
                                Group group2 = (Group) ViewBindings.findChildViewById(view, i);
                                if (group2 != null) {
                                    i = C1910R.id.group_print_pay_view;
                                    Group group3 = (Group) ViewBindings.findChildViewById(view, i);
                                    if (group3 != null) {
                                        i = C1910R.id.group_print_view;
                                        Group group4 = (Group) ViewBindings.findChildViewById(view, i);
                                        if (group4 != null) {
                                            i = C1910R.id.group_printing_view;
                                            Group group5 = (Group) ViewBindings.findChildViewById(view, i);
                                            if (group5 != null) {
                                                i = C1910R.id.iv_add;
                                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatImageView6 != null) {
                                                    i = C1910R.id.iv_arrow_left;
                                                    AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                    if (appCompatImageView7 != null) {
                                                        i = C1910R.id.iv_arrow_right;
                                                        AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                        if (appCompatImageView8 != null) {
                                                            i = C1910R.id.iv_home_bg;
                                                            AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                            if (appCompatImageView9 != null) {
                                                                i = C1910R.id.iv_home_re_code;
                                                                AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                if (appCompatImageView10 != null) {
                                                                    i = C1910R.id.iv_non_camera;
                                                                    AppCompatImageView appCompatImageView11 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                    if (appCompatImageView11 != null) {
                                                                        i = C1910R.id.iv_non_network;
                                                                        AppCompatImageView appCompatImageView12 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                        if (appCompatImageView12 != null) {
                                                                            i = C1910R.id.iv_non_printer;
                                                                            AppCompatImageView appCompatImageView13 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                            if (appCompatImageView13 != null) {
                                                                                i = C1910R.id.iv_pay_bg;
                                                                                AppCompatImageView appCompatImageView14 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                if (appCompatImageView14 != null) {
                                                                                    i = C1910R.id.iv_pay_qrcode;
                                                                                    AppCompatImageView appCompatImageView15 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                    if (appCompatImageView15 != null) {
                                                                                        i = C1910R.id.iv_photo;
                                                                                        AppCompatImageView appCompatImageView16 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                        if (appCompatImageView16 != null) {
                                                                                            i = C1910R.id.iv_photo_freeze;
                                                                                            AppCompatImageView appCompatImageView17 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                            if (appCompatImageView17 != null) {
                                                                                                i = C1910R.id.iv_photo_qrcode;
                                                                                                AppCompatImageView appCompatImageView18 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                if (appCompatImageView18 != null) {
                                                                                                    i = C1910R.id.iv_photo_qrcode_bg;
                                                                                                    AppCompatImageView appCompatImageView19 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                    if (appCompatImageView19 != null) {
                                                                                                        i = C1910R.id.iv_print_bg;
                                                                                                        AppCompatImageView appCompatImageView20 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                        if (appCompatImageView20 != null) {
                                                                                                            i = C1910R.id.iv_print_down;
                                                                                                            AppCompatImageView appCompatImageView21 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                            if (appCompatImageView21 != null) {
                                                                                                                i = C1910R.id.iv_print_pay_bg;
                                                                                                                AppCompatImageView appCompatImageView22 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                if (appCompatImageView22 != null) {
                                                                                                                    i = C1910R.id.iv_print_pay_qrcode;
                                                                                                                    AppCompatImageView appCompatImageView23 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                    if (appCompatImageView23 != null) {
                                                                                                                        i = C1910R.id.iv_sub;
                                                                                                                        AppCompatImageView appCompatImageView24 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                        if (appCompatImageView24 != null) {
                                                                                                                            i = C1910R.id.iv_water_mark;
                                                                                                                            AppCompatImageView appCompatImageView25 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                            if (appCompatImageView25 != null) {
                                                                                                                                i = C1910R.id.layout_bottom;
                                                                                                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                                                                if (constraintLayout != null) {
                                                                                                                                    i = C1910R.id.layout_camera;
                                                                                                                                    CameraView cameraView = (CameraView) ViewBindings.findChildViewById(view, i);
                                                                                                                                    if (cameraView != null) {
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
                                                                                                                                                                i = C1910R.id.tv_print_count;
                                                                                                                                                                StrokeTextView strokeTextView3 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                if (strokeTextView3 != null) {
                                                                                                                                                                    i = C1910R.id.tv_print_pay_price;
                                                                                                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                    if (appCompatTextView5 != null) {
                                                                                                                                                                        i = C1910R.id.tv_print_price;
                                                                                                                                                                        StrokeTextView strokeTextView4 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                        if (strokeTextView4 != null) {
                                                                                                                                                                            i = C1910R.id.vp_frame;
                                                                                                                                                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                            if (viewPager2 != null) {
                                                                                                                                                                                return new FragmentPosterEasyBinding((AbsoluteLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, group, group2, group3, group4, group5, appCompatImageView6, appCompatImageView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, appCompatImageView11, appCompatImageView12, appCompatImageView13, appCompatImageView14, appCompatImageView15, appCompatImageView16, appCompatImageView17, appCompatImageView18, appCompatImageView19, appCompatImageView20, appCompatImageView21, appCompatImageView22, appCompatImageView23, appCompatImageView24, appCompatImageView25, constraintLayout, cameraView, appCompatTextView, appCompatTextView2, strokeTextView, appCompatTextView3, appCompatTextView4, strokeTextView2, strokeTextView3, appCompatTextView5, strokeTextView4, viewPager2);
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
