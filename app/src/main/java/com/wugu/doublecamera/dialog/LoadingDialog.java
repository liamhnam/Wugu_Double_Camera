package com.wugu.doublecamera.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.databinding.DialogLoadingBinding;

public class LoadingDialog extends Dialog {
    private boolean isShowDots;
    private String loadingText;
    private final DialogLoadingBinding mBinding;
    private float mCurrentDegree;
    private Handler mHandler;
    private Runnable mRotationRunnable;
    private float perDegree;
    private int postDelay;

    static float access$016(LoadingDialog loadingDialog, float f) {
        float f2 = loadingDialog.mCurrentDegree + f;
        loadingDialog.mCurrentDegree = f2;
        return f2;
    }

    static float access$024(LoadingDialog loadingDialog, float f) {
        float f2 = loadingDialog.mCurrentDegree - f;
        loadingDialog.mCurrentDegree = f2;
        return f2;
    }

    public LoadingDialog(Context context) {
        super(context, C1910R.style.dialog_loading);
        this.mCurrentDegree = 0.0f;
        this.perDegree = 30.0f;
        this.postDelay = 70;
        this.isShowDots = false;
        this.loadingText = "";
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRotationRunnable = new Runnable() {
            @Override
            public void run() {
                LoadingDialog loadingDialog = LoadingDialog.this;
                LoadingDialog.access$016(loadingDialog, loadingDialog.perDegree);
                if (LoadingDialog.this.mCurrentDegree > 360.0f) {
                    LoadingDialog.access$024(LoadingDialog.this, 360.0f);
                }
                LoadingDialog.this.mBinding.ivLoading.setRotation(LoadingDialog.this.mCurrentDegree);
                LoadingDialog.this.mHandler.postDelayed(this, LoadingDialog.this.postDelay);
                if (LoadingDialog.this.isShowDots && ((int) LoadingDialog.this.mCurrentDegree) % 120 == 0) {
                    LoadingDialog.this.updateLoadingText();
                }
            }
        };
        DialogLoadingBinding dialogLoadingBindingInflate = DialogLoadingBinding.inflate(getLayoutInflater());
        this.mBinding = dialogLoadingBindingInflate;
        setContentView(dialogLoadingBindingInflate.getRoot());
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
    }

    public void setText(String str) {
        this.loadingText = str;
        this.mBinding.tvLoadingTx.setText(str);
    }

    public void updateLoadingText() {
        int i = (((int) this.mCurrentDegree) / 120) % 4;
        StringBuilder sb = new StringBuilder(this.loadingText);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(".");
        }
        this.mBinding.tvLoadingTx.setText(sb.toString());
    }

    @Override
    public void dismiss() {
        this.mHandler.removeCallbacks(this.mRotationRunnable);
        super.dismiss();
    }

    public void setImage(int i) {
        this.mBinding.ivLoading.setImageResource(i);
        this.perDegree = 2.0f;
        this.postDelay = 5;
        this.isShowDots = true;
    }

    public void startAnim() {
        if (isShowing()) {
            return;
        }
        show();
        this.mHandler.post(this.mRotationRunnable);
    }

    public void stopAnim() {
        this.mHandler.removeCallbacks(this.mRotationRunnable);
        dismiss();
    }
}
