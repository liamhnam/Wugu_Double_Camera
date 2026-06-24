package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.databinding.DialogStopBusinessBinding;
import com.wugu.doublecamera.utils.ViewUtil;

public class StopBusinessDialog extends Dialog {
    public StopBusinessDialog(Context context) {
        super(context, C1910R.style.FullScreenDialog);
        DialogStopBusinessBinding dialogStopBusinessBindingInflate = DialogStopBusinessBinding.inflate(LayoutInflater.from(context));
        setContentView(dialogStopBusinessBindingInflate.getRoot());
        ViewUtil.setUiLocate(dialogStopBusinessBindingInflate.ivView, 110);
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
        }
        setCanceledOnTouchOutside(false);
    }
}
