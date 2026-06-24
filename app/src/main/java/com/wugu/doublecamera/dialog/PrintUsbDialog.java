package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.BtnBackCountdown;
import com.wugu.doublecamera.databinding.DialogPrintUsbBinding;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.utils.StringUtil;

public class PrintUsbDialog extends Dialog {
    private final DialogPrintUsbBinding binding;

    public PrintUsbDialog(Context context, String str, final IIntListener iIntListener) {
        super(context, C1910R.style.publicDialog);
        DialogPrintUsbBinding dialogPrintUsbBindingInflate = DialogPrintUsbBinding.inflate(LayoutInflater.from(context));
        this.binding = dialogPrintUsbBindingInflate;
        setContentView(dialogPrintUsbBindingInflate.getRoot());
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
        }
        dialogPrintUsbBindingInflate.layoutBtnCountdown.setListener(new BtnBackCountdown.ICountdownEndListener() {
            @Override
            public final void countdownEnd() {
                this.f$0.dismiss();
            }
        });
        Glide.with(context).load(str).override(300, 300).into(dialogPrintUsbBindingInflate.ivPhoto);
        dialogPrintUsbBindingInflate.btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1608lambda$new$0$comwugudoublecameradialogPrintUsbDialog(iIntListener, view);
            }
        });
        dialogPrintUsbBindingInflate.layoutBtnCountdown.startCountdown(60);
    }

    void m1608lambda$new$0$comwugudoublecameradialogPrintUsbDialog(IIntListener iIntListener, View view) {
        iIntListener.setValue(StringUtil.strToInt(this.binding.etPrintNum.getText().toString(), 1));
        dismiss();
    }
}
