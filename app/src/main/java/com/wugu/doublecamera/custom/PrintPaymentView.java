package com.wugu.doublecamera.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AbsoluteLayout;
import androidx.core.text.HtmlCompat;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.LayoutPrintPayBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;

public class PrintPaymentView extends AbsoluteLayout {
    private ValueAnimator animQrCodeLoading;
    private LayoutPrintPayBinding binding;

    public PrintPaymentView(Context context) {
        super(context);
        initView();
    }

    public PrintPaymentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    private void initView() {
        LayoutPrintPayBinding layoutPrintPayBindingInflate = LayoutPrintPayBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.binding = layoutPrintPayBindingInflate;
        ViewUtil.setUiLocate(layoutPrintPayBindingInflate.tvPaymentText, UiPosIndexEnum.PRINT_PAY_PRICE);
        ViewUtil.setUiLocate(this.binding.tvBalance, UiPosIndexEnum.PRINT_PAY_BALANCE);
    }

    public void setPrice(int i) {
        this.binding.tvPaymentText.setText(StringUtil.getPriceStr(i));
    }

    public void setPriceStr(String str) {
        this.binding.tvPaymentText.setText(str);
    }

    public void setBalance(int i) {
        this.binding.tvBalance.setText(HtmlCompat.fromHtml(getContext().getString(C1910R.string.balance_value, StringUtil.getPriceStr(i)), 0));
    }

    public void setQrCodeStr(String str) {
        stopQrCodeLoadAnim();
        this.binding.ivPayQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 300, null));
    }

    @Override
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean zIsOnlinePayment = OrderManager.isOnlinePayment();
        LoggerUtil.m1181d("PrintPaymentView", "setVisibility: " + OrderManager.mPaymentMethod);
        if (OrderManager.mPaymentMethod == 3 || OrderManager.mPaymentMethod == 11 || OrderManager.mPaymentMethod == 8 || OrderManager.mPaymentMethod == 9) {
            String paymentMethod = SpManager.getInstance().getPaymentMethod();
            zIsOnlinePayment = paymentMethod.contains(String.valueOf(1)) || paymentMethod.contains(String.valueOf(2));
        }
        if (!zIsOnlinePayment) {
            this.binding.ivPayQrcode.setVisibility(8);
            this.binding.tvBalance.setVisibility(0);
            return;
        }
        this.binding.tvBalance.setVisibility(8);
        if (i == 0) {
            startQrCodeLoadAnim();
        } else {
            stopQrCodeLoadAnim();
        }
    }

    private void startQrCodeLoadAnim() {
        if (this.animQrCodeLoading == null) {
            ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(this.binding.ivPayQrcode, 490);
            this.animQrCodeLoading = rotationAnim1;
            rotationAnim1.start();
        }
    }

    private void stopQrCodeLoadAnim() {
        ValueAnimator valueAnimator = this.animQrCodeLoading;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.binding.ivPayQrcode.setRotation(0.0f);
    }
}
