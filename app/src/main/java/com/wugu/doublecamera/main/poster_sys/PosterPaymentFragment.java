package com.wugu.doublecamera.main.poster_sys;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.dto.RedeemCodeDto;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentPosterPaymentBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.PaymentVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.UartUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ToastHelper;

public class PosterPaymentFragment extends ABaseFragment<FragmentPosterPaymentBinding, PaymentVModel> {
    private ValueAnimator animQrCodeLoading;
    private MainVModel mainVModel;

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initListener();
        initObserver();
        ((PaymentVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPaymentTime());
        SoundHelper.getInstance().playSoundEffect(8);
    }

    @Override
    public void onPause() {
        super.onPause();
        UartUtil.disablePayDevice();
        cancelQrCodeAnim();
        this.mainVModel = null;
        ((PaymentVModel) this.mViewModel).cancelCountdown();
    }

    private void initView() {
        AppCompatImageView[] appCompatImageViewArr = {((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment1, ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment2, ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment3, ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment4, ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment5, ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment6, ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment7};
        int i = 0;
        for (String str : SpManager.getInstance().getPaymentMethod().split("-")) {
            int iStrToInt = StringUtil.strToInt(str, -1);
            if (iStrToInt != -1) {
                AppCompatImageView appCompatImageView = appCompatImageViewArr[i];
                i++;
                appCompatImageView.setVisibility(0);
                if (iStrToInt == 1) {
                    ViewUtil.setUiLocate(appCompatImageView, 311);
                } else if (iStrToInt == 2) {
                    ViewUtil.setUiLocate(appCompatImageView, 312);
                } else if (iStrToInt == 3) {
                    ViewUtil.setUiLocate(appCompatImageView, 313);
                } else if (iStrToInt == 5) {
                    ViewUtil.setUiLocate(appCompatImageView, 314);
                } else if (iStrToInt == 4) {
                    ViewUtil.setUiLocate(appCompatImageView, 315);
                } else if (iStrToInt == 6) {
                    ViewUtil.setUiLocate(appCompatImageView, 316);
                } else if (iStrToInt == 7) {
                    ViewUtil.setUiLocate(appCompatImageView, 317);
                }
            }
        }
        ViewUtil.setImageDrawable(((FragmentPosterPaymentBinding) this.mViewBinding).viewPayment, 36);
        ViewUtil.setImageDrawable(((FragmentPosterPaymentBinding) this.mViewBinding).btnBack, 37);
    }

    private void initListener() {
        ((FragmentPosterPaymentBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1110xc574dbb2(view);
            }
        });
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1111x7fea7c33(view);
            }
        });
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1112x3a601cb4(view);
            }
        });
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1113xf4d5bd35(view);
            }
        });
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1114xaf4b5db6(view);
            }
        });
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1115x69c0fe37(view);
            }
        });
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1116x24369eb8(view);
            }
        });
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivPayment7.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1117xdeac3f39(view);
            }
        });
        ((FragmentPosterPaymentBinding) this.mViewBinding).layoutKeyboard.setEdittext(((FragmentPosterPaymentBinding) this.mViewBinding).etCodeInput, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m1118x9921dfba(str);
            }
        });
    }

    void m1110xc574dbb2(View view) {
        pressBtnBack();
    }

    void m1111x7fea7c33(View view) {
        payMethodClick(0);
    }

    void m1112x3a601cb4(View view) {
        payMethodClick(1);
    }

    void m1113xf4d5bd35(View view) {
        payMethodClick(2);
    }

    void m1114xaf4b5db6(View view) {
        payMethodClick(3);
    }

    void m1115x69c0fe37(View view) {
        payMethodClick(4);
    }

    void m1116x24369eb8(View view) {
        payMethodClick(5);
    }

    void m1117xdeac3f39(View view) {
        payMethodClick(6);
    }

    void m1118x9921dfba(String str) {
        LoggerUtil.m1181d("fragment", "code input " + str);
        if (TextUtils.isEmpty(str)) {
            dismissCodeBoard();
            return;
        }
        ((PaymentVModel) this.mViewModel).pauseCountdown();
        showLoadAnim(getString(C1910R.string.query_ing));
        this.mainVModel.queryRedeemCode(str);
    }

    private void initObserver() {
        ((PaymentVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1121x570d1edd((Integer) obj);
            }
        });
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                OrderManager.getOrderFrameInfo();
            }
        });
        this.mainVModel.redeemCodeInfoLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1119x81d13a66((RedeemCodeDto) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1120x3c46dae7((Integer) obj);
            }
        });
    }

    void m1121x570d1edd(Integer num) {
        ((FragmentPosterPaymentBinding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        if (num.intValue() == 0) {
            cancelPayment();
        }
    }

    void m1119x81d13a66(RedeemCodeDto redeemCodeDto) {
        dismissLoadAnim();
        ((PaymentVModel) this.mViewModel).resumeCountdown();
        dismissCodeBoard();
    }

    void m1120x3c46dae7(Integer num) {
        if (num.intValue() == 5) {
            refreshBalance();
        }
    }

    private void cancelPayment() {
        LoggerUtil.m1181d("PosterPaymentFragment", "cancelPayment");
        App.mIsIdle = true;
        requireActivity().onBackPressed();
    }

    private void pressBtnBack() {
        if (((FragmentPosterPaymentBinding) this.mViewBinding).tvPrice.getVisibility() == 0) {
            dismissCodeBoard();
        } else {
            cancelPayment();
        }
    }

    private void dismissCodeBoard() {
        ((FragmentPosterPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(0);
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivCodeExBg.setVisibility(8);
        ((FragmentPosterPaymentBinding) this.mViewBinding).etCodeInput.setVisibility(8);
        ((FragmentPosterPaymentBinding) this.mViewBinding).etCodeInput.setText((CharSequence) null);
        ((FragmentPosterPaymentBinding) this.mViewBinding).layoutKeyboard.setVisibility(8);
        ((FragmentPosterPaymentBinding) this.mViewBinding).tvPrice.setVisibility(8);
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivQrcode.setVisibility(8);
        ((FragmentPosterPaymentBinding) this.mViewBinding).tvBalance.setText((CharSequence) null);
        try {
            ViewUtil.setImageDrawable(((FragmentPosterPaymentBinding) this.mViewBinding).viewPayment, App.mUiPosMap.get(36).resPath);
        } catch (NullPointerException unused) {
        }
    }

    private void refreshBalance() {
        ((PaymentVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPaymentTime());
        if (!OrderManager.isOnlinePayment()) {
            ((FragmentPosterPaymentBinding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
        } else {
            ((FragmentPosterPaymentBinding) this.mViewBinding).tvBalance.setText((CharSequence) null);
        }
    }

    private void payMethodClick(int i) {
        String[] strArrSplit = SpManager.getInstance().getPaymentMethod().split("-");
        if (i > strArrSplit.length) {
            return;
        }
        OrderManager.mPaymentMethod = StringUtil.strToInt(strArrSplit[i], -1);
        if (OrderManager.mPaymentMethod == -1) {
            return;
        }
        ((FragmentPosterPaymentBinding) this.mViewBinding).tvPrice.setVisibility(0);
        try {
            ViewUtil.setImageDrawable(((FragmentPosterPaymentBinding) this.mViewBinding).viewPayment, App.mUiPosMap.get(318).resPath);
        } catch (NullPointerException unused) {
        }
        setPayValueText(OrderManager.getOrderRealPriceStr(), StringUtil.getPriceStr(OrderManager.getFramePrice()));
        if (OrderManager.mPaymentMethod == 1) {
            showQrCode(UiPosIndexEnum.PAYMENT_WECHAT_IC);
            return;
        }
        if (OrderManager.mPaymentMethod == 2) {
            showQrCode(UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC);
            return;
        }
        if (OrderManager.mPaymentMethod == 3) {
            showCodeExchange();
            return;
        }
        if (OrderManager.mPaymentMethod == 5) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_COIN_IC);
            return;
        }
        if (OrderManager.mPaymentMethod == 4) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_CASH_IC);
        } else if (OrderManager.mPaymentMethod == 6) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_GAME_COIN_IC);
        } else if (OrderManager.mPaymentMethod == 7) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_CARD_IC);
        }
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
        ((FragmentPosterPaymentBinding) this.mViewBinding).tvPrice.setText(spannableString);
    }

    private void showQrCode(int i) {
        ((FragmentPosterPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(8);
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivQrcode.setVisibility(0);
        ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(((FragmentPosterPaymentBinding) this.mViewBinding).ivQrcode, 490);
        this.animQrCodeLoading = rotationAnim1;
        rotationAnim1.start();
        OrderManager.createPhotoOrder(OrderManager.mPaymentMethod, false, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m1123xd7e65b04(str);
            }
        });
    }

    void m1123xd7e65b04(final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1122x1d70ba83(str);
            }
        });
    }

    void m1122x1d70ba83(String str) {
        cancelQrCodeAnim();
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 400, null));
        this.mainVModel.startCheckPhotoOrderTask();
    }

    private void showCodeExchange() {
        if (OrderManager.mRedeemCodeDto != null) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_exist));
            return;
        }
        ((FragmentPosterPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(8);
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivCodeExBg.setVisibility(0);
        ((FragmentPosterPaymentBinding) this.mViewBinding).etCodeInput.setVisibility(0);
        ((FragmentPosterPaymentBinding) this.mViewBinding).layoutKeyboard.setVisibility(0);
        ViewUtil.setUiLocate(((FragmentPosterPaymentBinding) this.mViewBinding).ivCodeExBg, UiPosIndexEnum.PAYMENT_CODE_EX_BG);
    }

    private void showCashCoinCard(int i) {
        ((FragmentPosterPaymentBinding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
        ((FragmentPosterPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(8);
        ((FragmentPosterPaymentBinding) this.mViewBinding).tvBalance.setVisibility(0);
        OrderManager.createPhotoOrder(OrderManager.mPaymentMethod, false, null);
    }

    private void cancelQrCodeAnim() {
        ValueAnimator valueAnimator = this.animQrCodeLoading;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animQrCodeLoading.removeAllListeners();
            this.animQrCodeLoading.cancel();
        }
        ((FragmentPosterPaymentBinding) this.mViewBinding).ivQrcode.setRotation(0.0f);
    }
}
