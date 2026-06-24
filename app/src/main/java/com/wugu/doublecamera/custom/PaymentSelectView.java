package com.wugu.doublecamera.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsoluteLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.text.HtmlCompat;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.LayoutPaymentSelectBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ToastHelper;

public class PaymentSelectView extends AbsoluteLayout {
    private ValueAnimator animQrCodeLoading;
    private LayoutPaymentSelectBinding binding;
    private IPaymentSelectViewListener listener;
    private int paymentMethod;

    public interface IPaymentSelectViewListener {
        void paymentClick(int i);

        void redeemCode(String str);
    }

    public PaymentSelectView(Context context) {
        super(context);
        initView();
    }

    public PaymentSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    private void initView() {
        int i = 1;
        LayoutPaymentSelectBinding layoutPaymentSelectBindingInflate = LayoutPaymentSelectBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.binding = layoutPaymentSelectBindingInflate;
        int i2 = 0;
        AppCompatImageView[] appCompatImageViewArr = {layoutPaymentSelectBindingInflate.ivPayment1, this.binding.ivPayment2, this.binding.ivPayment3, this.binding.ivPayment4, this.binding.ivPayment5, this.binding.ivPayment6, this.binding.ivPayment7, this.binding.ivPayment8, this.binding.ivPayment9};
        AppCompatImageView[] appCompatImageViewArr2 = {this.binding.ivArrow1, this.binding.ivArrow2, this.binding.ivArrow3, this.binding.ivArrow4, this.binding.ivArrow5, this.binding.ivArrow6, this.binding.ivArrow7, this.binding.ivArrow8, this.binding.ivArrow9};
        String[] strArrSplit = SpManager.getInstance().getPaymentMethod().split("-");
        int length = strArrSplit.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int iStrToInt = StringUtil.strToInt(strArrSplit[i3], -1);
            if (iStrToInt != -1) {
                AppCompatImageView appCompatImageView = appCompatImageViewArr[i4];
                AppCompatImageView appCompatImageView2 = appCompatImageViewArr2[i4];
                i4++;
                appCompatImageView.setVisibility(i2);
                appCompatImageView2.setVisibility(i2);
                if (iStrToInt == i) {
                    ViewUtil.setUiLocate(appCompatImageView, 311);
                    ViewUtil.setUiLocate(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                } else if (iStrToInt == 2) {
                    ViewUtil.setUiLocate(appCompatImageView, 312);
                    ViewUtil.setUiLocate(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                } else if (iStrToInt == 3) {
                    ViewUtil.setUiLocate(appCompatImageView, 313);
                    ViewUtil.setUiLocate(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                } else if (iStrToInt == 5) {
                    ViewUtil.setUiLocate(appCompatImageView, 314);
                    ViewUtil.setUiLocate(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                } else if (iStrToInt == 4) {
                    ViewUtil.setUiLocate(appCompatImageView, 315);
                    ViewUtil.setUiLocate(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                } else if (iStrToInt == 6) {
                    ViewUtil.setUiLocate(appCompatImageView, 316);
                    ViewUtil.setUiLocate(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                } else if (iStrToInt == 7) {
                    ViewUtil.setUiLocate(appCompatImageView, 317);
                    ViewUtil.setUiLocate(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                } else if (iStrToInt == 8) {
                    ViewUtil.setImageDrawable(appCompatImageView, UiPosIndexEnum.PAYMENT_MEI_TUAN_IC, C1910R.mipmap.ic_pay_mei_tuan);
                    ViewUtil.setImageDrawable(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                } else if (iStrToInt == 9) {
                    ViewUtil.setImageDrawable(appCompatImageView, UiPosIndexEnum.PAYMENT_DOU_YIN_IC, C1910R.mipmap.ic_pay_dou_yin);
                    ViewUtil.setImageDrawable(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                }
            }
            i3++;
            i = 1;
            i2 = 0;
        }
        ViewUtil.setViewGroupBg(this.binding.getRoot(), 310);
        initListener();
    }

    private void initListener() {
        this.binding.ivPayment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m843x5cad7144(view);
            }
        });
        this.binding.ivArrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m844x96781323(view);
            }
        });
        this.binding.ivPayment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m854xd042b502(view);
            }
        });
        this.binding.ivArrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m855xa0d56e1(view);
            }
        });
        this.binding.ivPayment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m856x43d7f8c0(view);
            }
        });
        this.binding.ivArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m857x7da29a9f(view);
            }
        });
        this.binding.ivPayment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m858xb76d3c7e(view);
            }
        });
        this.binding.ivArrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m859xf137de5d(view);
            }
        });
        this.binding.ivPayment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m860x2b02803c(view);
            }
        });
        this.binding.ivArrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m861x64cd221b(view);
            }
        });
        this.binding.ivPayment6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m845x84c99a8f(view);
            }
        });
        this.binding.ivArrow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m846xbe943c6e(view);
            }
        });
        this.binding.ivPayment7.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m847xf85ede4d(view);
            }
        });
        this.binding.ivArrow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m848x3229802c(view);
            }
        });
        this.binding.ivPayment8.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m849x6bf4220b(view);
            }
        });
        this.binding.ivArrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m850xa5bec3ea(view);
            }
        });
        this.binding.ivPayment9.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m851xdf8965c9(view);
            }
        });
        this.binding.ivArrow9.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m852x195407a8(view);
            }
        });
        this.binding.layoutKeyboard.setEdittext(this.binding.etCodeInput, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m853x531ea987(str);
            }
        });
    }

    void m843x5cad7144(View view) {
        payMethodClick(0);
    }

    void m844x96781323(View view) {
        payMethodClick(0);
    }

    void m854xd042b502(View view) {
        payMethodClick(1);
    }

    void m855xa0d56e1(View view) {
        payMethodClick(1);
    }

    void m856x43d7f8c0(View view) {
        payMethodClick(2);
    }

    void m857x7da29a9f(View view) {
        payMethodClick(2);
    }

    void m858xb76d3c7e(View view) {
        payMethodClick(3);
    }

    void m859xf137de5d(View view) {
        payMethodClick(3);
    }

    void m860x2b02803c(View view) {
        payMethodClick(4);
    }

    void m861x64cd221b(View view) {
        payMethodClick(4);
    }

    void m845x84c99a8f(View view) {
        payMethodClick(5);
    }

    void m846xbe943c6e(View view) {
        payMethodClick(5);
    }

    void m847xf85ede4d(View view) {
        payMethodClick(6);
    }

    void m848x3229802c(View view) {
        payMethodClick(6);
    }

    void m849x6bf4220b(View view) {
        payMethodClick(7);
    }

    void m850xa5bec3ea(View view) {
        payMethodClick(7);
    }

    void m851xdf8965c9(View view) {
        payMethodClick(8);
    }

    void m852x195407a8(View view) {
        payMethodClick(8);
    }

    void m853x531ea987(String str) {
        if (TextUtils.isEmpty(str)) {
            resetView();
            return;
        }
        IPaymentSelectViewListener iPaymentSelectViewListener = this.listener;
        if (iPaymentSelectViewListener != null) {
            iPaymentSelectViewListener.redeemCode(str);
        }
    }

    public void setListener(IPaymentSelectViewListener iPaymentSelectViewListener) {
        this.listener = iPaymentSelectViewListener;
    }

    public void setQrCodeStr(String str) {
        cancelQrCodeAnim();
        this.binding.ivPaymentQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 400, null));
    }

    public void resetView() {
        this.binding.layoutPaymentMethod.setVisibility(0);
        this.binding.tvPaymentPrice.setVisibility(8);
        this.binding.tvPaymentBalance.setVisibility(8);
        this.binding.ivPaymentIcon.setVisibility(8);
        this.binding.ivPaymentQrcode.setVisibility(8);
        this.binding.ivCodeExBg.setVisibility(8);
        this.binding.etCodeInput.setVisibility(8);
        this.binding.layoutKeyboard.setVisibility(8);
        ViewUtil.setViewGroupBg(this.binding.getRoot(), 310);
    }

    public void updateBalanceValue() {
        this.binding.tvPaymentBalance.setText(HtmlCompat.fromHtml(getContext().getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
    }

    private void payMethodClick(int i) {
        String[] strArrSplit = SpManager.getInstance().getPaymentMethod().split("-");
        if (i > strArrSplit.length) {
            return;
        }
        int iStrToInt = StringUtil.strToInt(strArrSplit[i], -1);
        this.paymentMethod = iStrToInt;
        if (iStrToInt == -1) {
            return;
        }
        LoggerUtil.m1181d("order", "payMethodClick " + this.paymentMethod);
        OrderManager.mPaymentMethod = this.paymentMethod;
        setPayValueText(OrderManager.getOrderRealPriceStr(), StringUtil.getPriceStr(OrderManager.getFramePrice()));
        IPaymentSelectViewListener iPaymentSelectViewListener = this.listener;
        if (iPaymentSelectViewListener != null) {
            iPaymentSelectViewListener.paymentClick(this.paymentMethod);
        }
        int i2 = this.paymentMethod;
        if (i2 == 1) {
            showQrCode(UiPosIndexEnum.PAYMENT_WECHAT_IC);
            return;
        }
        if (i2 == 2) {
            showQrCode(UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC);
            return;
        }
        if (i2 == 3) {
            showCodeExchange();
            return;
        }
        if (i2 == 5) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_COIN_IC, i2);
            return;
        }
        if (i2 == 4) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_CASH_IC, i2);
            return;
        }
        if (i2 == 6) {
            showGameCoin();
            return;
        }
        if (i2 == 7) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_CARD_IC, i2);
        } else if (i2 == 8) {
            showMeiTuanDouYin(true);
        } else if (i2 == 9) {
            showMeiTuanDouYin(false);
        }
    }

    private void showQrCode(int i) {
        this.binding.layoutPaymentMethod.setVisibility(8);
        this.binding.tvPaymentPrice.setVisibility(0);
        this.binding.ivPaymentIcon.setVisibility(0);
        this.binding.ivPaymentQrcode.setVisibility(0);
        ViewUtil.setUI(this.binding.ivPaymentIcon, i);
        ViewUtil.setUiLocate(this.binding.getRoot(), 318);
        ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(this.binding.ivPaymentQrcode, 490);
        this.animQrCodeLoading = rotationAnim1;
        rotationAnim1.start();
    }

    private void showCodeExchange() {
        if (OrderManager.mRedeemCodeDto != null) {
            ToastHelper.getInstance().showToast(getContext().getString(C1910R.string.redeem_exist));
            return;
        }
        this.binding.layoutPaymentMethod.setVisibility(8);
        this.binding.getRoot().setBackground(null);
        this.binding.ivCodeExBg.setVisibility(0);
        this.binding.etCodeInput.setVisibility(0);
        this.binding.layoutKeyboard.setVisibility(0);
        ViewUtil.setUiLocate(this.binding.ivCodeExBg, UiPosIndexEnum.PAYMENT_CODE_EX_BG);
        ViewUtil.setUiLocate(this.binding.etCodeInput, UiPosIndexEnum.PAYMENT_CODE_INPUT);
        ViewUtil.setUiLocate(this.binding.layoutKeyboard, UiPosIndexEnum.PAYMENT_KEYBOARD_POS);
    }

    private void showMeiTuanDouYin(boolean z) {
        this.binding.layoutPaymentMethod.setVisibility(8);
        if (z) {
            ViewUtil.setViewGroupBg(this.binding.getRoot(), UiPosIndexEnum.PAYMENT_MEI_TUAN_BG, C1910R.mipmap.ic_pay_mei_tuan_bg);
        } else {
            ViewUtil.setViewGroupBg(this.binding.getRoot(), UiPosIndexEnum.PAYMENT_DOU_YIN_BG, C1910R.mipmap.ic_pay_dou_yin_bg);
        }
    }

    private void showCashCoinCard(int i, int i2) {
        this.binding.tvPaymentBalance.setText(HtmlCompat.fromHtml(getContext().getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
        this.binding.layoutPaymentMethod.setVisibility(8);
        this.binding.tvPaymentPrice.setVisibility(0);
        this.binding.ivPaymentIcon.setVisibility(0);
        this.binding.tvPaymentBalance.setVisibility(0);
        ViewUtil.setUiLocate(this.binding.tvPaymentBalance, UiPosIndexEnum.PAYMENT_BALANCE);
        ViewUtil.setUI(this.binding.ivPaymentIcon, i);
        ViewUtil.setUiLocate(this.binding.getRoot(), 318);
    }

    private void showGameCoin() {
        this.binding.tvPaymentPrice.setText(C1910R.string.pls_input_game_coin);
        this.binding.layoutPaymentMethod.setVisibility(8);
        this.binding.tvPaymentPrice.setVisibility(0);
        this.binding.ivPaymentIcon.setVisibility(0);
        ViewUtil.setUI(this.binding.ivPaymentIcon, UiPosIndexEnum.PAYMENT_GAME_COIN_IC);
        ViewUtil.setUiLocate(this.binding.getRoot(), 318);
    }

    private void setPayValueText(String str, String str2) {
        SpannableString spannableString;
        if (str.equals(str2)) {
            spannableString = new SpannableString(str);
            spannableString.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), 0, str.length(), 33);
            spannableString.setSpan(new RelativeSizeSpan(1.2f), 0, str.length(), 33);
        } else {
            SpannableString spannableString2 = new SpannableString(str + " " + str2);
            int length = spannableString2.length();
            spannableString2.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), 0, str.length(), 33);
            spannableString2.setSpan(new RelativeSizeSpan(1.2f), 0, str.length(), 33);
            spannableString2.setSpan(new ForegroundColorSpan(-7829368), str.length() + 1, length, 33);
            spannableString2.setSpan(new StrikethroughSpan(), str.length() + 1, length, 33);
            spannableString2.setSpan(new RelativeSizeSpan(0.6f), str.length() + 1, length, 33);
            spannableString = spannableString2;
        }
        this.binding.tvPaymentPrice.setText(spannableString);
    }

    private void cancelQrCodeAnim() {
        ValueAnimator valueAnimator = this.animQrCodeLoading;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animQrCodeLoading.removeAllListeners();
            this.animQrCodeLoading.cancel();
        }
        this.binding.ivPaymentQrcode.setRotation(0.0f);
    }
}
