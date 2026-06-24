package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;

public final class FragmentInitBoardBinding implements ViewBinding {
    public final AppCompatButton btnHeadSys;
    public final AppCompatButton btnPeSys;
    public final AppCompatButton btnPoSys;
    public final RadioGroup rgHeadSys;
    public final RadioGroup rgPeSys;
    public final RadioGroup rgPoSys;
    private final ConstraintLayout rootView;
    public final Spinner spinnerHeadSys;
    public final Spinner spinnerPeSys;
    public final Spinner spinnerPoSys;
    public final AppCompatTextView tvHeadSys;
    public final AppCompatTextView tvPeSys;
    public final AppCompatTextView tvPoSys;
    public final AppCompatTextView tvServerName;
    public final AppCompatTextView tvTitle;
    public final View vHead;
    public final View vPe;
    public final View vPo;

    private FragmentInitBoardBinding(ConstraintLayout constraintLayout, AppCompatButton appCompatButton, AppCompatButton appCompatButton2, AppCompatButton appCompatButton3, RadioGroup radioGroup, RadioGroup radioGroup2, RadioGroup radioGroup3, Spinner spinner, Spinner spinner2, Spinner spinner3, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, AppCompatTextView appCompatTextView5, View view, View view2, View view3) {
        this.rootView = constraintLayout;
        this.btnHeadSys = appCompatButton;
        this.btnPeSys = appCompatButton2;
        this.btnPoSys = appCompatButton3;
        this.rgHeadSys = radioGroup;
        this.rgPeSys = radioGroup2;
        this.rgPoSys = radioGroup3;
        this.spinnerHeadSys = spinner;
        this.spinnerPeSys = spinner2;
        this.spinnerPoSys = spinner3;
        this.tvHeadSys = appCompatTextView;
        this.tvPeSys = appCompatTextView2;
        this.tvPoSys = appCompatTextView3;
        this.tvServerName = appCompatTextView4;
        this.tvTitle = appCompatTextView5;
        this.vHead = view;
        this.vPe = view2;
        this.vPo = view3;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentInitBoardBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentInitBoardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.fragment_init_board, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentInitBoardBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        int i = C1910R.id.btn_head_sys;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, i);
        if (appCompatButton != null) {
            i = C1910R.id.btn_pe_sys;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, i);
            if (appCompatButton2 != null) {
                i = C1910R.id.btn_po_sys;
                AppCompatButton appCompatButton3 = (AppCompatButton) ViewBindings.findChildViewById(view, i);
                if (appCompatButton3 != null) {
                    i = C1910R.id.rg_head_sys;
                    RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, i);
                    if (radioGroup != null) {
                        i = C1910R.id.rg_pe_sys;
                        RadioGroup radioGroup2 = (RadioGroup) ViewBindings.findChildViewById(view, i);
                        if (radioGroup2 != null) {
                            i = C1910R.id.rg_po_sys;
                            RadioGroup radioGroup3 = (RadioGroup) ViewBindings.findChildViewById(view, i);
                            if (radioGroup3 != null) {
                                i = C1910R.id.spinner_head_sys;
                                Spinner spinner = (Spinner) ViewBindings.findChildViewById(view, i);
                                if (spinner != null) {
                                    i = C1910R.id.spinner_pe_sys;
                                    Spinner spinner2 = (Spinner) ViewBindings.findChildViewById(view, i);
                                    if (spinner2 != null) {
                                        i = C1910R.id.spinner_po_sys;
                                        Spinner spinner3 = (Spinner) ViewBindings.findChildViewById(view, i);
                                        if (spinner3 != null) {
                                            i = C1910R.id.tv_head_sys;
                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                            if (appCompatTextView != null) {
                                                i = C1910R.id.tv_pe_sys;
                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                if (appCompatTextView2 != null) {
                                                    i = C1910R.id.tv_po_sys;
                                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                    if (appCompatTextView3 != null) {
                                                        i = C1910R.id.tv_server_name;
                                                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                        if (appCompatTextView4 != null) {
                                                            i = C1910R.id.tv_title;
                                                            AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                                            if (appCompatTextView5 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = C1910R.id.v_head))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = C1910R.id.v_pe))) != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(view, (i = C1910R.id.v_po))) != null) {
                                                                return new FragmentInitBoardBinding((ConstraintLayout) view, appCompatButton, appCompatButton2, appCompatButton3, radioGroup, radioGroup2, radioGroup3, spinner, spinner2, spinner3, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3);
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
