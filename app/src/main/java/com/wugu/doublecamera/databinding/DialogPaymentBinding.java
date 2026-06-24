package com.wugu.doublecamera.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.PrintPaymentView;

public final class DialogPaymentBinding implements ViewBinding {
    public final PrintPaymentView layoutPayment;
    private final ConstraintLayout rootView;

    private DialogPaymentBinding(ConstraintLayout constraintLayout, PrintPaymentView printPaymentView) {
        this.rootView = constraintLayout;
        this.layoutPayment = printPaymentView;
    }

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogPaymentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogPaymentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(C1910R.layout.dialog_payment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogPaymentBinding bind(View view) {
        int i = C1910R.id.layout_payment;
        PrintPaymentView printPaymentView = (PrintPaymentView) ViewBindings.findChildViewById(view, i);
        if (printPaymentView != null) {
            return new DialogPaymentBinding((ConstraintLayout) view, printPaymentView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
