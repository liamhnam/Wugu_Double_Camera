package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.databinding.DialogShareFrameInputBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.utils.ViewUtil;

public class NumInputDialog extends Dialog {
    public NumInputDialog(Context context, final IStringListener iStringListener) {
        super(context, C1910R.style.publicDialog);
        DialogShareFrameInputBinding dialogShareFrameInputBindingInflate = DialogShareFrameInputBinding.inflate(LayoutInflater.from(context));
        setContentView(dialogShareFrameInputBindingInflate.getRoot());
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = 366;
            attributes.height = 484;
            window.setAttributes(attributes);
        }
        ViewUtil.setViewGroupBg(dialogShareFrameInputBindingInflate.getRoot(), UiPosIndexEnum.PAYMENT_CODE_EX_BG);
        ViewUtil.setImageDrawable(dialogShareFrameInputBindingInflate.btnCancel, 37);
        dialogShareFrameInputBindingInflate.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1605lambda$new$0$comwugudoublecameradialogNumInputDialog(view);
            }
        });
        dialogShareFrameInputBindingInflate.layoutNumKeyboard.setEdittext(dialogShareFrameInputBindingInflate.etInputCode, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m1606lambda$new$1$comwugudoublecameradialogNumInputDialog(iStringListener, str);
            }
        });
    }

    void m1605lambda$new$0$comwugudoublecameradialogNumInputDialog(View view) {
        dismiss();
    }

    void m1606lambda$new$1$comwugudoublecameradialogNumInputDialog(IStringListener iStringListener, String str) {
        iStringListener.setValue(str);
        dismiss();
    }
}
