package com.wugu.doublecamera.custom;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.wugu.doublecamera.databinding.LayoutBackCountdownBinding;

public class BtnBackCountdown extends ConstraintLayout {
    private LayoutBackCountdownBinding binding;
    private CountDownTimer countdownTimer;
    private ICountdownEndListener listener;

    public interface ICountdownEndListener {
        void countdownEnd();
    }

    public BtnBackCountdown(Context context) {
        this(context, null);
    }

    public BtnBackCountdown(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        LayoutBackCountdownBinding layoutBackCountdownBindingInflate = LayoutBackCountdownBinding.inflate(LayoutInflater.from(context), this, true);
        this.binding = layoutBackCountdownBindingInflate;
        layoutBackCountdownBindingInflate.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1577lambda$initView$0$comwugudoublecameracustomBtnBackCountdown(view);
            }
        });
    }

    void m1577lambda$initView$0$comwugudoublecameracustomBtnBackCountdown(View view) {
        cancelCountdown();
        ICountdownEndListener iCountdownEndListener = this.listener;
        if (iCountdownEndListener != null) {
            iCountdownEndListener.countdownEnd();
        }
    }

    public void setListener(ICountdownEndListener iCountdownEndListener) {
        this.listener = iCountdownEndListener;
    }

    public void setAlpha(float f, float f2) {
        this.binding.btnBack.setAlpha(f);
        this.binding.tvCountdown.setAlpha(f2);
    }

    public void startCountdown(int i) {
        this.binding.tvCountdown.setText(String.valueOf(i));
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 1000L) {
            @Override
            public void onTick(long j) {
                BtnBackCountdown.this.binding.tvCountdown.setText(String.valueOf((int) (j / 1000)));
            }

            @Override
            public void onFinish() {
                if (BtnBackCountdown.this.listener != null) {
                    BtnBackCountdown.this.listener.countdownEnd();
                }
            }
        };
        this.countdownTimer = countDownTimer2;
        countDownTimer2.start();
    }

    public void cancelCountdown() {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
