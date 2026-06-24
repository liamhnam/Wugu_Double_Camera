package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.databinding.DialogPaymentBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;

public class PaymentDialog extends Dialog {
    private final DialogPaymentBinding binding;

    public PaymentDialog(Context context, String str) {
        super(context, C1910R.style.publicDialog);
        DialogPaymentBinding dialogPaymentBindingInflate = DialogPaymentBinding.inflate(LayoutInflater.from(context));
        this.binding = dialogPaymentBindingInflate;
        setContentView(dialogPaymentBindingInflate.getRoot());
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = UiPosIndexEnum.KEYBOARD_0;
            attributes.height = 600;
            window.setAttributes(attributes);
        }
        ViewUtil.setViewGroupBg(dialogPaymentBindingInflate.layoutPayment, 318);
        dialogPaymentBindingInflate.layoutPayment.setPriceStr(str);
        dialogPaymentBindingInflate.layoutPayment.setVisibility(0);
        updateBalance();
    }

    @Override
    public void dismiss() {
        this.binding.layoutPayment.setVisibility(8);
        super.dismiss();
    }

    public void setQrCode(String str) {
        this.binding.layoutPayment.setQrCodeStr(str);
    }

    public void updateBalance() {
        this.binding.layoutPayment.setBalance(OrderManager.getTotalBalance());
    }
}
