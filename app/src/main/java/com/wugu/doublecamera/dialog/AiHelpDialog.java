package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.databinding.DialogAiHelpBinding;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.ViewUtil;

public class AiHelpDialog extends Dialog {
    private DialogAiHelpBinding binding;
    private CountDownTimer countdownTimer;

    public AiHelpDialog(Context context) {
        super(context, C1910R.style.publicDialog);
        DialogAiHelpBinding dialogAiHelpBindingInflate = DialogAiHelpBinding.inflate(getLayoutInflater());
        this.binding = dialogAiHelpBindingInflate;
        setContentView(dialogAiHelpBindingInflate.getRoot());
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = 1080;
            attributes.height = 720;
            window.setAttributes(attributes);
        }
        ViewUtil.setImageDrawable(this.binding.btnCancel, 37);
        this.binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1603lambda$new$0$comwugudoublecameradialogAiHelpDialog(view);
            }
        });
        startCountdown();
    }

    void m1603lambda$new$0$comwugudoublecameradialogAiHelpDialog(View view) {
        cancelCountdown();
        dismiss();
    }

    private void startCountdown() {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimerC19401 countDownTimerC19401 = new CountDownTimerC19401(60000L, 1000L);
        this.countdownTimer = countDownTimerC19401;
        countDownTimerC19401.start();
    }

    class CountDownTimerC19401 extends CountDownTimer {
        CountDownTimerC19401(long j, long j2) {
            super(j, j2);
        }

        @Override
        public void onTick(long j) {
            final int i = (int) (j / 1000);
            if (i == 0) {
                return;
            }
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1604lambda$onTick$0$comwugudoublecameradialogAiHelpDialog$1(i);
                }
            });
        }

        void m1604lambda$onTick$0$comwugudoublecameradialogAiHelpDialog$1(int i) {
            AiHelpDialog.this.binding.tvCountdown.setText(String.valueOf(i));
        }

        @Override
        public void onFinish() {
            AiHelpDialog.this.dismiss();
        }
    }

    private void cancelCountdown() {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
