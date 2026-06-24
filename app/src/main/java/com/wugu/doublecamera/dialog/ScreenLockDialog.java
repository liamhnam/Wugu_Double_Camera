package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.databinding.DialogScreenLockInputBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.utils.ViewUtil;

public class ScreenLockDialog extends Dialog {
    public ScreenLockDialog(Context context, final IStringListener iStringListener) {
        super(context, C1910R.style.FullScreenDialog);
        final DialogScreenLockInputBinding dialogScreenLockInputBindingInflate = DialogScreenLockInputBinding.inflate(LayoutInflater.from(context));
        setContentView(dialogScreenLockInputBindingInflate.getRoot());
        ViewUtil.setUiLocate(dialogScreenLockInputBindingInflate.getRoot(), UiPosIndexEnum.ADMIN_BG);
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
        }
        setCanceledOnTouchOutside(false);
        dialogScreenLockInputBindingInflate.layoutNumKeyboard.setEdittext(dialogScreenLockInputBindingInflate.etInputCode, new IStringListener() {
            @Override
            public final void setValue(String str) {
                ScreenLockDialog.lambda$new$0(iStringListener, dialogScreenLockInputBindingInflate, str);
            }
        });
    }

    static void lambda$new$0(IStringListener iStringListener, DialogScreenLockInputBinding dialogScreenLockInputBinding, String str) {
        iStringListener.setValue(str);
        dialogScreenLockInputBinding.etInputCode.setText((CharSequence) null);
    }
}
