package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.databinding.DialogTestPrintBinding;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.ToastHelper;
import java.io.File;

public class TestPrintDialog extends Dialog {
    private final DialogTestPrintBinding binding;
    private CountDownTimer countdownTimer;

    public TestPrintDialog(Context context, String str, int i) {
        super(context, C1910R.style.dialog_loading);
        DialogTestPrintBinding dialogTestPrintBindingInflate = DialogTestPrintBinding.inflate(getLayoutInflater());
        this.binding = dialogTestPrintBindingInflate;
        setContentView(dialogTestPrintBindingInflate.getRoot());
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
        if (!TextUtils.isEmpty(str)) {
            if (new File(str).exists()) {
                ViewUtil.setImageDrawable(dialogTestPrintBindingInflate.ivPicture, str);
            } else {
                ToastHelper.getInstance().showToast("打印文件不存在");
            }
        }
        setCanceledOnTouchOutside(true);
        startCountdown((i * 25) + 20);
    }

    private void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 1000L) {
            @Override
            public void onTick(long j) {
                TestPrintDialog.this.binding.tvCountdown.setText(String.valueOf((int) (j / 1000)));
            }

            @Override
            public void onFinish() {
                TestPrintDialog.this.dismiss();
            }
        };
        this.countdownTimer = countDownTimer2;
        countDownTimer2.start();
    }

    @Override
    public void dismiss() {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.dismiss();
    }
}
