package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.BtnBackCountdown;
import com.wugu.doublecamera.databinding.DialogSelectCameraBinding;
import com.wugu.doublecamera.listener.IIntListener;

public class SelectCameraDialog extends Dialog {
    private final DialogSelectCameraBinding binding;

    public SelectCameraDialog(Context context, final IIntListener iIntListener) {
        super(context, C1910R.style.publicDialog);
        DialogSelectCameraBinding dialogSelectCameraBindingInflate = DialogSelectCameraBinding.inflate(LayoutInflater.from(context));
        this.binding = dialogSelectCameraBindingInflate;
        setContentView(dialogSelectCameraBindingInflate.getRoot());
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
        }
        dialogSelectCameraBindingInflate.layoutBtnCountdown.setListener(new BtnBackCountdown.ICountdownEndListener() {
            @Override
            public final void countdownEnd() {
                this.f$0.dismiss();
            }
        });
        dialogSelectCameraBindingInflate.ivStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1609lambda$new$0$comwugudoublecameradialogSelectCameraDialog(iIntListener, view);
            }
        });
        dialogSelectCameraBindingInflate.ivTopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1610lambda$new$1$comwugudoublecameradialogSelectCameraDialog(iIntListener, view);
            }
        });
        dialogSelectCameraBindingInflate.layoutBtnCountdown.startCountdown(60);
    }

    void m1609lambda$new$0$comwugudoublecameradialogSelectCameraDialog(IIntListener iIntListener, View view) {
        iIntListener.setValue(1);
        dismiss();
    }

    void m1610lambda$new$1$comwugudoublecameradialogSelectCameraDialog(IIntListener iIntListener, View view) {
        iIntListener.setValue(2);
        dismiss();
    }
}
