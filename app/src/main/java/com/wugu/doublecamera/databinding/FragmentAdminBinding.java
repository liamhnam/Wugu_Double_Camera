package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.NumKeyboard;
import com.wugu.doublecamera.custom.StrokeTextView;

public final class FragmentAdminBinding implements ViewBinding {
    public final AppCompatSeekBar barBlur;
    public final AppCompatSeekBar barWhite;
    public final AppCompatImageView btnBack;
    public final CheckBox cbLockScreen;
    public final CheckBox cbPeShowDate;
    public final CheckBox cbUseOutside;
    public final AppCompatEditText etInputCode;
    public final Group groupInput;
    public final Group groupItem;
    public final AppCompatImageView ivChangePwd;
    public final AppCompatImageView ivExit;
    public final AppCompatImageView ivPowerOff;
    public final AppCompatImageView ivReboot;
    public final NumKeyboard layoutNumKeyboard;
    public final RadioButton rbtnForeign;
    public final RadioButton rbtnLocal;
    public final RadioButton rbtnNone;
    public final RadioButton rbtnTest;
    public final RadioGroup rgServer;
    private final ConstraintLayout rootView;
    public final StrokeTextView tvCountdown;
    public final StrokeTextView tvVersion;
    public final View vCheck;

    private FragmentAdminBinding(ConstraintLayout constraintLayout, AppCompatSeekBar appCompatSeekBar, AppCompatSeekBar appCompatSeekBar2, AppCompatImageView appCompatImageView, CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, AppCompatEditText appCompatEditText, Group group, Group group2, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, NumKeyboard numKeyboard, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioGroup radioGroup, StrokeTextView strokeTextView, StrokeTextView strokeTextView2, View view) {
        this.rootView = constraintLayout;
        this.barBlur = appCompatSeekBar;
        this.barWhite = appCompatSeekBar2;
        this.btnBack = appCompatImageView;
        this.cbLockScreen = checkBox;
        this.cbPeShowDate = checkBox2;
        this.cbUseOutside = checkBox3;
        this.etInputCode = appCompatEditText;
        this.groupInput = group;
        this.groupItem = group2;
        this.ivChangePwd = appCompatImageView2;
        this.ivExit = appCompatImageView3;
        this.ivPowerOff = appCompatImageView4;
        this.ivReboot = appCompatImageView5;
        this.layoutNumKeyboard = numKeyboard;
        this.rbtnForeign = radioButton;
        this.rbtnLocal = radioButton2;
        this.rbtnNone = radioButton3;
        this.rbtnTest = radioButton4;
        this.rgServer = radioGroup;
        this.tvCountdown = strokeTextView;
        this.tvVersion = strokeTextView2;
        this.vCheck = view;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAdminBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAdminBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_admin, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAdminBinding bind(View view) {
        View viewFindChildViewById;
        int i = C1910R.id.bar_blur;
        AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) ViewBindings.findChildViewById(view, i);
        if (appCompatSeekBar != null) {
            i = C1910R.id.bar_white;
            AppCompatSeekBar appCompatSeekBar2 = (AppCompatSeekBar) ViewBindings.findChildViewById(view, i);
            if (appCompatSeekBar2 != null) {
                i = C1910R.id.btn_back;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView != null) {
                    i = C1910R.id.cb_lock_screen;
                    CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, i);
                    if (checkBox != null) {
                        i = C1910R.id.cb_pe_show_date;
                        CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(view, i);
                        if (checkBox2 != null) {
                            i = C1910R.id.cb_use_outside;
                            CheckBox checkBox3 = (CheckBox) ViewBindings.findChildViewById(view, i);
                            if (checkBox3 != null) {
                                i = C1910R.id.et_input_code;
                                AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
                                if (appCompatEditText != null) {
                                    i = C1910R.id.group_input;
                                    Group group = (Group) ViewBindings.findChildViewById(view, i);
                                    if (group != null) {
                                        i = C1910R.id.group_item;
                                        Group group2 = (Group) ViewBindings.findChildViewById(view, i);
                                        if (group2 != null) {
                                            i = C1910R.id.iv_change_pwd;
                                            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                            if (appCompatImageView2 != null) {
                                                i = C1910R.id.iv_exit;
                                                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatImageView3 != null) {
                                                    i = C1910R.id.iv_power_off;
                                                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                    if (appCompatImageView4 != null) {
                                                        i = C1910R.id.iv_reboot;
                                                        AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                                        if (appCompatImageView5 != null) {
                                                            i = C1910R.id.layout_num_keyboard;
                                                            NumKeyboard numKeyboard = (NumKeyboard) ViewBindings.findChildViewById(view, i);
                                                            if (numKeyboard != null) {
                                                                i = C1910R.id.rbtn_foreign;
                                                                RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, i);
                                                                if (radioButton != null) {
                                                                    i = C1910R.id.rbtn_local;
                                                                    RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(view, i);
                                                                    if (radioButton2 != null) {
                                                                        i = C1910R.id.rbtn_none;
                                                                        RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(view, i);
                                                                        if (radioButton3 != null) {
                                                                            i = C1910R.id.rbtn_test;
                                                                            RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(view, i);
                                                                            if (radioButton4 != null) {
                                                                                i = C1910R.id.rg_server;
                                                                                RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, i);
                                                                                if (radioGroup != null) {
                                                                                    i = C1910R.id.tv_countdown;
                                                                                    StrokeTextView strokeTextView = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                                    if (strokeTextView != null) {
                                                                                        i = C1910R.id.tv_version;
                                                                                        StrokeTextView strokeTextView2 = (StrokeTextView) ViewBindings.findChildViewById(view, i);
                                                                                        if (strokeTextView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = C1910R.id.v_check))) != null) {
                                                                                            return new FragmentAdminBinding((ConstraintLayout) view, appCompatSeekBar, appCompatSeekBar2, appCompatImageView, checkBox, checkBox2, checkBox3, appCompatEditText, group, group2, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, numKeyboard, radioButton, radioButton2, radioButton3, radioButton4, radioGroup, strokeTextView, strokeTextView2, viewFindChildViewById);
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
