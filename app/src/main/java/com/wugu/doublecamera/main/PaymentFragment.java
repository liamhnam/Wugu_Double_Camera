package com.wugu.doublecamera.main;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.util.Property;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.dto.RedeemCodeDto;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentPaymentBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.PaymentVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ToastHelper;

public class PaymentFragment extends ABaseFragment<FragmentPaymentBinding, PaymentVModel> {
    private ValueAnimator animQrCodeLoading;
    private FrameInfo frameInfo;
    private MainVModel mainVModel;

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        LoggerUtil.m1181d("fragment", "PaymentFragment Create");
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
        this.mainVModel.stopCheckOrderTask();
        OrderManager.disablePayDevice();
        dismissLoadAnim();
        cancelQrCodeAnim();
        this.mainVModel = null;
        ((PaymentVModel) this.mViewModel).cancelCountdown();
        LoggerUtil.m1181d("fragment", "PaymentFragment Pause");
    }

    private void initView() {
        int i = 0;
        int i2 = 1;
        AppCompatImageView[] appCompatImageViewArr = {((FragmentPaymentBinding) this.mViewBinding).ivPayment1, ((FragmentPaymentBinding) this.mViewBinding).ivPayment2, ((FragmentPaymentBinding) this.mViewBinding).ivPayment3, ((FragmentPaymentBinding) this.mViewBinding).ivPayment4, ((FragmentPaymentBinding) this.mViewBinding).ivPayment5, ((FragmentPaymentBinding) this.mViewBinding).ivPayment6, ((FragmentPaymentBinding) this.mViewBinding).ivPayment7, ((FragmentPaymentBinding) this.mViewBinding).ivPayment8, ((FragmentPaymentBinding) this.mViewBinding).ivPayment9};
        AppCompatImageView[] appCompatImageViewArr2 = {((FragmentPaymentBinding) this.mViewBinding).ivArrow1, ((FragmentPaymentBinding) this.mViewBinding).ivArrow2, ((FragmentPaymentBinding) this.mViewBinding).ivArrow3, ((FragmentPaymentBinding) this.mViewBinding).ivArrow4, ((FragmentPaymentBinding) this.mViewBinding).ivArrow5, ((FragmentPaymentBinding) this.mViewBinding).ivArrow6, ((FragmentPaymentBinding) this.mViewBinding).ivArrow7, ((FragmentPaymentBinding) this.mViewBinding).ivArrow8, ((FragmentPaymentBinding) this.mViewBinding).ivArrow9};
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).viewPayment, 36);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).btnCancel, 37);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).tvCountdown, 38);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).ivFrame, 39);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).layoutPayment, 310);
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
                appCompatImageView.setVisibility(i);
                appCompatImageView2.setVisibility(i);
                if (iStrToInt == i2) {
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
                } else if (iStrToInt == 12) {
                    ViewUtil.setImageDrawable(appCompatImageView, UiPosIndexEnum.PAYMENT_XIE_CHENG_IC, C1910R.mipmap.ic_pay_ctrip);
                    ViewUtil.setImageDrawable(appCompatImageView2, UiPosIndexEnum.PAYMENT_ARROW_IC);
                }
            }
            i3++;
            i = 0;
            i2 = 1;
        }
    }

    private void initListener() {
        ((FragmentPaymentBinding) this.mViewBinding).btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1661lambda$initListener$0$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1662lambda$initListener$1$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1663lambda$initListener$2$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1664lambda$initListener$3$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1665lambda$initListener$4$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1666lambda$initListener$5$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1667lambda$initListener$6$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1668lambda$initListener$7$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1669lambda$initListener$8$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1670lambda$initListener$9$comwugudoublecameramainPaymentFragment(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m958x536095e6(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m959x164cff45(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m960xd93968a4(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment7.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m961x9c25d203(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m962x5f123b62(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment8.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m963x21fea4c1(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m964xe4eb0e20(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivPayment9.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m965xa7d7777f(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).ivArrow9.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m966x6ac3e0de(view);
            }
        });
        ((FragmentPaymentBinding) this.mViewBinding).layoutKeyboard.setEdittext(((FragmentPaymentBinding) this.mViewBinding).etCodeInput, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m967x2db04a3d(str);
            }
        });
    }

    void m1661lambda$initListener$0$comwugudoublecameramainPaymentFragment(View view) {
        cancelPayment();
    }

    void m1662lambda$initListener$1$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(0);
    }

    void m1663lambda$initListener$2$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(0);
    }

    void m1664lambda$initListener$3$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(1);
    }

    void m1665lambda$initListener$4$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(1);
    }

    void m1666lambda$initListener$5$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(2);
    }

    void m1667lambda$initListener$6$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(2);
    }

    void m1668lambda$initListener$7$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(3);
    }

    void m1669lambda$initListener$8$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(3);
    }

    void m1670lambda$initListener$9$comwugudoublecameramainPaymentFragment(View view) {
        payMethodClick(4);
    }

    void m958x536095e6(View view) {
        payMethodClick(4);
    }

    void m959x164cff45(View view) {
        payMethodClick(5);
    }

    void m960xd93968a4(View view) {
        payMethodClick(5);
    }

    void m961x9c25d203(View view) {
        payMethodClick(6);
    }

    void m962x5f123b62(View view) {
        payMethodClick(6);
    }

    void m963x21fea4c1(View view) {
        payMethodClick(7);
    }

    void m964xe4eb0e20(View view) {
        payMethodClick(7);
    }

    void m965xa7d7777f(View view) {
        payMethodClick(8);
    }

    void m966x6ac3e0de(View view) {
        payMethodClick(8);
    }

    void m967x2db04a3d(String str) {
        if (TextUtils.isEmpty(str)) {
            dismissCodeBoard();
            return;
        }
        ((PaymentVModel) this.mViewModel).pauseCountdown();
        showLoadAnim(getString(C1910R.string.query_ing));
        if (OrderManager.mPaymentMethod == 3) {
            this.mainVModel.queryRedeemCode(str);
        } else {
            this.mainVModel.queryXieChengCode(str);
        }
    }

    private void lambda$initListener$20(View view) {
        this.mainVModel.postPayStep(3);
    }

    private void initObserver() {
        ((PaymentVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m968x6f68e468((Integer) obj);
            }
        });
        this.mainVModel.redeemCodeInfoLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m969x32554dc7((RedeemCodeDto) obj);
            }
        });
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m970xf541b726((String) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m971xb82e2085((Integer) obj);
            }
        });
    }

    void m968x6f68e468(Integer num) {
        ((FragmentPaymentBinding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        if (num.intValue() == 0) {
            LoggerUtil.m1181d("PaymentFragment", "getCountDownLD 0");
            cancelPayment();
        }
    }

    void m969x32554dc7(RedeemCodeDto redeemCodeDto) {
        dismissLoadAnim();
        ((PaymentVModel) this.mViewModel).resumeCountdown();
        dismissCodeBoard();
    }

    void m970xf541b726(String str) {
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        this.frameInfo = orderFrameInfo;
        if (orderFrameInfo == null) {
            return;
        }
        Glide.with(this).asBitmap().load(this.frameInfo.getFramePath()).diskCacheStrategy(DiskCacheStrategy.ALL).override(300, 400).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                Bitmap bitmapAddGrayBackgroundAndBorder;
                if (PaymentFragment.this.frameInfo.isNeedCut()) {
                    bitmapAddGrayBackgroundAndBorder = BitmapUtil.createSplitBitmap(bitmap, 5);
                } else {
                    bitmapAddGrayBackgroundAndBorder = BitmapUtil.addGrayBackgroundAndBorder(bitmap, false);
                }
                ((FragmentPaymentBinding) PaymentFragment.this.mViewBinding).ivFrame.setImageBitmap(bitmapAddGrayBackgroundAndBorder);
                PaymentFragment.this.startFrameAnim();
            }
        });
    }

    void m971xb82e2085(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 3) {
            ((PaymentVModel) this.mViewModel).cancelCountdown();
            return;
        }
        if (iIntValue == 5) {
            refreshBalance();
        } else {
            if (iIntValue != 6) {
                return;
            }
            LoggerUtil.m1181d("PaymentFragment", "SCANNER_REC");
            ((PaymentVModel) this.mViewModel).pauseCountdown();
        }
    }

    private void cancelPayment() {
        LoggerUtil.m1181d("PaymentFragment", "cancelPayment");
        App.mIsIdle = true;
        ((PaymentVModel) this.mViewModel).cancelCountdown();
        requireActivity().onBackPressed();
    }

    private void dismissCodeBoard() {
        ((FragmentPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(0);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).layoutPayment, 318);
        ((FragmentPaymentBinding) this.mViewBinding).ivCodeExBg.setVisibility(8);
        ((FragmentPaymentBinding) this.mViewBinding).etCodeInput.setVisibility(8);
        ((FragmentPaymentBinding) this.mViewBinding).layoutKeyboard.setVisibility(8);
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
        LoggerUtil.m1181d("paymentFragment", "payMethodClick " + OrderManager.mPaymentMethod);
        ((PaymentVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPaymentTime());
        setPayValueText(OrderManager.getOrderRealPriceStr(), StringUtil.getPriceStr(OrderManager.getFramePrice()));
        if (OrderManager.mPaymentMethod == 1) {
            showQrCode(UiPosIndexEnum.PAYMENT_WECHAT_IC, 1);
            return;
        }
        if (OrderManager.mPaymentMethod == 2) {
            showQrCode(UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC, 2);
            return;
        }
        if (OrderManager.mPaymentMethod == 3) {
            showCodeExchange();
            return;
        }
        if (OrderManager.mPaymentMethod == 5) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_COIN_IC, OrderManager.mPaymentMethod);
            return;
        }
        if (OrderManager.mPaymentMethod == 4) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_CASH_IC, OrderManager.mPaymentMethod);
            return;
        }
        if (OrderManager.mPaymentMethod == 6) {
            showGameCoin();
            return;
        }
        if (OrderManager.mPaymentMethod == 7) {
            showCashCoinCard(UiPosIndexEnum.PAYMENT_CARD_IC, OrderManager.mPaymentMethod);
            return;
        }
        if (OrderManager.mPaymentMethod == 8) {
            showMeiTuanDouYin(true);
        } else if (OrderManager.mPaymentMethod == 9) {
            showMeiTuanDouYin(false);
        } else if (OrderManager.mPaymentMethod == 12) {
            showCodeExchange();
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
        ((FragmentPaymentBinding) this.mViewBinding).tvPrice.setText(spannableString);
    }

    private void showQrCode(int i, int i2) {
        if (this.frameInfo == null) {
            return;
        }
        ((FragmentPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(8);
        ((FragmentPaymentBinding) this.mViewBinding).tvPrice.setVisibility(0);
        ((FragmentPaymentBinding) this.mViewBinding).ivPayIcon.setVisibility(0);
        ((FragmentPaymentBinding) this.mViewBinding).ivQrcode.setVisibility(0);
        ViewUtil.setUI(((FragmentPaymentBinding) this.mViewBinding).ivPayIcon, i);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).layoutPayment, 318);
        ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(((FragmentPaymentBinding) this.mViewBinding).ivQrcode, 490);
        this.animQrCodeLoading = rotationAnim1;
        rotationAnim1.start();
        OrderManager.createPhotoOrder(i2, false, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m1672lambda$showQrCode$26$comwugudoublecameramainPaymentFragment(str);
            }
        });
    }

    void m1672lambda$showQrCode$26$comwugudoublecameramainPaymentFragment(final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1671lambda$showQrCode$25$comwugudoublecameramainPaymentFragment(str);
            }
        });
    }

    void m1671lambda$showQrCode$25$comwugudoublecameramainPaymentFragment(String str) {
        if (this.mViewBinding == 0) {
            return;
        }
        cancelQrCodeAnim();
        ((FragmentPaymentBinding) this.mViewBinding).ivQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 400, null));
        this.mainVModel.startCheckPhotoOrderTask();
    }

    private void showCodeExchange() {
        if (OrderManager.mRedeemCodeDto != null) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_exist));
            return;
        }
        ((FragmentPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(8);
        ((FragmentPaymentBinding) this.mViewBinding).layoutPayment.setBackground(null);
        ((FragmentPaymentBinding) this.mViewBinding).ivCodeExBg.setVisibility(0);
        ((FragmentPaymentBinding) this.mViewBinding).etCodeInput.setVisibility(0);
        ((FragmentPaymentBinding) this.mViewBinding).layoutKeyboard.setVisibility(0);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).ivCodeExBg, UiPosIndexEnum.PAYMENT_CODE_EX_BG);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).etCodeInput, UiPosIndexEnum.PAYMENT_CODE_INPUT);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).layoutKeyboard, UiPosIndexEnum.PAYMENT_KEYBOARD_POS);
    }

    private void showMeiTuanDouYin(boolean z) {
        ((FragmentPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(8);
        if (z) {
            ViewUtil.setViewGroupBg(((FragmentPaymentBinding) this.mViewBinding).layoutPayment, UiPosIndexEnum.PAYMENT_MEI_TUAN_BG, C1910R.mipmap.ic_pay_mei_tuan_bg);
        } else {
            ViewUtil.setViewGroupBg(((FragmentPaymentBinding) this.mViewBinding).layoutPayment, UiPosIndexEnum.PAYMENT_DOU_YIN_BG, C1910R.mipmap.ic_pay_dou_yin_bg);
        }
    }

    private void showCashCoinCard(int i, int i2) {
        ((FragmentPaymentBinding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
        ((FragmentPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(8);
        ((FragmentPaymentBinding) this.mViewBinding).tvPrice.setVisibility(0);
        ((FragmentPaymentBinding) this.mViewBinding).ivPayIcon.setVisibility(0);
        ((FragmentPaymentBinding) this.mViewBinding).tvBalance.setVisibility(0);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).tvBalance, UiPosIndexEnum.PAYMENT_BALANCE);
        ViewUtil.setUI(((FragmentPaymentBinding) this.mViewBinding).ivPayIcon, i);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).layoutPayment, 318);
        OrderManager.createPhotoOrder(i2, false, null);
    }

    private void showGameCoin() {
        ((FragmentPaymentBinding) this.mViewBinding).tvPrice.setText(C1910R.string.pls_input_game_coin);
        ((FragmentPaymentBinding) this.mViewBinding).layoutPaymentMethod.setVisibility(8);
        ((FragmentPaymentBinding) this.mViewBinding).tvPrice.setVisibility(0);
        ((FragmentPaymentBinding) this.mViewBinding).ivPayIcon.setVisibility(0);
        ViewUtil.setUI(((FragmentPaymentBinding) this.mViewBinding).ivPayIcon, UiPosIndexEnum.PAYMENT_GAME_COIN_IC);
        ViewUtil.setUiLocate(((FragmentPaymentBinding) this.mViewBinding).layoutPayment, 318);
        OrderManager.createPhotoOrder(OrderManager.mPaymentMethod, false, null);
    }

    private void refreshBalance() {
        ((PaymentVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPaymentTime());
        if (!OrderManager.isOnlinePayment()) {
            ((FragmentPaymentBinding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
        } else {
            ((FragmentPaymentBinding) this.mViewBinding).tvBalance.setText((CharSequence) null);
        }
    }

    public void startFrameAnim() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(((FragmentPaymentBinding) this.mViewBinding).ivFrame, (Property<AppCompatImageView, Float>) View.SCALE_X, 0.5f, 0.9f, 1.04f, 1.02f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(((FragmentPaymentBinding) this.mViewBinding).ivFrame, (Property<AppCompatImageView, Float>) View.SCALE_Y, 0.5f, 0.9f, 1.04f, 1.02f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(((FragmentPaymentBinding) this.mViewBinding).ivFrame, (Property<AppCompatImageView, Float>) View.TRANSLATION_X, -30.0f, 0.0f);
        ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(((FragmentPaymentBinding) this.mViewBinding).ivFrame, (Property<AppCompatImageView, Float>) View.TRANSLATION_Y, 30.0f, 0.0f);
        ((FragmentPaymentBinding) this.mViewBinding).ivFrame.setPivotX(0.0f);
        ((FragmentPaymentBinding) this.mViewBinding).ivFrame.setPivotY(((FragmentPaymentBinding) this.mViewBinding).viewPayment.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet.setDuration(1100L);
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.start();
        animatorSet2.setDuration(500L);
        animatorSet2.playTogether(objectAnimatorOfFloat3, objectAnimatorOfFloat4);
        animatorSet2.start();
    }

    private void cancelQrCodeAnim() {
        ValueAnimator valueAnimator = this.animQrCodeLoading;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animQrCodeLoading.removeAllListeners();
            this.animQrCodeLoading.cancel();
        }
        ((FragmentPaymentBinding) this.mViewBinding).ivQrcode.setRotation(0.0f);
    }
}
