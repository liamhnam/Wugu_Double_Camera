package com.wugu.doublecamera.main;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.BuildConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.UploadFrameIcAdapter;
import com.wugu.doublecamera.adapter.UploadPictureAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.PictureSelectItem;
import com.wugu.doublecamera.bean.UpFrameIcItem;
import com.wugu.doublecamera.bean.dto.RedeemCodeDto;
import com.wugu.doublecamera.custom.PaymentSelectView;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentUploadPrintBinding;
import com.wugu.doublecamera.dialog.PaymentDialog;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.UploadPrintVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Objects;
import org.eclipse.paho.android.service.MqttServiceConstants;

public class UploadPrintFragment extends ABaseFragment<FragmentUploadPrintBinding, UploadPrintVModel> {
    public static final String FRAME_KEY = "00000000000000000000";
    public static final int STEP_PRINTING = 4;
    public static final int STEP_PROCESS = 2;
    public static final int STEP_SELECTED_PAYMENT = 1;
    public static final int STEP_SHOW_PAYMENT = 0;
    public static final int STEP_WAIT_PRINT_PAY = 3;
    public static final String TAG = "UploadPrintFragment";
    private int PRINT_ADD_PRICE;
    private ValueAnimator animQrCodeLoading;
    private FrameInfo frameInfo;
    private MainVModel mainVModel;
    private PaymentDialog paymentDialog;
    private final UploadPictureAdapter uploadPictureAdapter = new UploadPictureAdapter();
    private final UploadFrameIcAdapter frameIcAdapter = new UploadFrameIcAdapter();
    private int printCount = 1;
    private int step = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Step {
    }

    public static UploadPrintFragment startUpPrintFragment(String str) {
        UploadPrintFragment uploadPrintFragment = new UploadPrintFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.PAYLOAD, str);
        uploadPrintFragment.setArguments(bundle);
        LoggerUtil.m1181d(TAG, "startUpPrintFragment " + str);
        return uploadPrintFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initData();
        initListener();
        initObserver();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mainVModel = null;
        ((UploadPrintVModel) this.mViewModel).cancelCountdown();
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutSplicing.releaseAllView();
        OrderManager.clearOrderInfo();
    }

    private void initView() {
        ((FragmentUploadPrintBinding) this.mViewBinding).rvUploadPicture.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        ((FragmentUploadPrintBinding) this.mViewBinding).rvUploadPicture.setAdapter(this.uploadPictureAdapter);
        ((FragmentUploadPrintBinding) this.mViewBinding).rvUploadPicture.setItemAnimator(null);
        ((FragmentUploadPrintBinding) this.mViewBinding).rvFrameIc.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        ((FragmentUploadPrintBinding) this.mViewBinding).rvFrameIc.setAdapter(this.frameIcAdapter);
        ((FragmentUploadPrintBinding) this.mViewBinding).rvFrameIc.setItemAnimator(null);
        ViewUtil.setViewGroupBg(((FragmentUploadPrintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.UPLOAD_UP_BG, C1910R.mipmap.upload_bg_upload);
        ViewUtil.setViewGroupBg(((FragmentUploadPrintBinding) this.mViewBinding).rvUploadPicture, 903, C1910R.mipmap.upload_bg_pic_list);
        ViewUtil.setViewGroupBg(((FragmentUploadPrintBinding) this.mViewBinding).rvFrameIc, 905, C1910R.mipmap.upload_bg_frame_list);
        ViewUtil.setImageDrawable(((FragmentUploadPrintBinding) this.mViewBinding).btnEnter, UiPosIndexEnum.PRINT_BTN_PRINT, C1910R.mipmap.ic_poster_print);
        ViewUtil.setImageDrawable(((FragmentUploadPrintBinding) this.mViewBinding).btnBack, 902, C1910R.mipmap.upload_back_home);
        ViewUtil.setImageDrawable(((FragmentUploadPrintBinding) this.mViewBinding).ivPrintCountText, UiPosIndexEnum.UPLOAD_ADD_PRINT_TEXT, C1910R.mipmap.upload_print_text);
        ViewUtil.setImageDrawable(((FragmentUploadPrintBinding) this.mViewBinding).ivSub, UiPosIndexEnum.PRINT_COUNT_SUB);
        ViewUtil.setImageDrawable(((FragmentUploadPrintBinding) this.mViewBinding).ivAdd, UiPosIndexEnum.PRINT_COUNT_ADD);
        ((FragmentUploadPrintBinding) this.mViewBinding).ivQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(BuildConfig.UP_PRINT_URL + App.mAppId, 400, null));
    }

    private void initData() {
        startCountdown(300);
        ((UploadPrintVModel) this.mViewModel).queryFrameIcList();
        ((UploadPrintVModel) this.mViewModel).preGetPrinterSheets();
        int uploadPrintPrice = SpManager.getInstance().getUploadPrintPrice();
        this.PRINT_ADD_PRICE = SpManager.getInstance().getUploadPrintAddPrice();
        OrderManager.setOrderFrameInfo(FRAME_KEY, uploadPrintPrice, 5);
        App.mIsIdle = false;
        if (getArguments() != null) {
            String string = getArguments().getString(MqttServiceConstants.PAYLOAD);
            LoggerUtil.m1181d(TAG, "initData payload " + string);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            final String[] strArrSplit = string.split(",");
            if (strArrSplit.length != 2) {
                LoggerUtil.m1182e(TAG, "initData error " + string);
                ToastHelper.getInstance().showToast(getString(C1910R.string.error));
            } else {
                AppUtil.runOnUIDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m1702lambda$initData$0$comwugudoublecameramainUploadPrintFragment(strArrSplit);
                    }
                }, 500L);
            }
        }
    }

    void m1702lambda$initData$0$comwugudoublecameramainUploadPrintFragment(String[] strArr) {
        uploadFinish();
        ((UploadPrintVModel) this.mViewModel).downloadUpPictures(strArr[1].split(";"));
    }

    private void initListener() {
        ((FragmentUploadPrintBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1041x2fa76ba0(view);
            }
        });
        ((FragmentUploadPrintBinding) this.mViewBinding).btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1042x69720d7f(view);
            }
        });
        this.uploadPictureAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1043xa33caf5e(baseQuickAdapter, view, i);
            }
        });
        this.frameIcAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1044xdd07513d(baseQuickAdapter, view, i);
            }
        });
        ((FragmentUploadPrintBinding) this.mViewBinding).ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1045x16d1f31c(view);
            }
        });
        ((FragmentUploadPrintBinding) this.mViewBinding).ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1046x509c94fb(view);
            }
        });
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutPaymentSelect.setListener(new PaymentSelectView.IPaymentSelectViewListener() {
            @Override
            public void paymentClick(int i) {
                UploadPrintFragment.this.step = 1;
                ViewUtil.setImageDrawable(((FragmentUploadPrintBinding) UploadPrintFragment.this.mViewBinding).btnBack, UiPosIndexEnum.PRINT_BTN_BACK);
                if (i == 1 || i == 2 || i == 4 || i == 5 || i == 7) {
                    ((UploadPrintVModel) UploadPrintFragment.this.mViewModel).createOrder(i);
                }
            }

            @Override
            public void redeemCode(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                ((UploadPrintVModel) UploadPrintFragment.this.mViewModel).pauseCountdown();
                UploadPrintFragment uploadPrintFragment = UploadPrintFragment.this;
                uploadPrintFragment.showLoadAnim(uploadPrintFragment.getString(C1910R.string.query_ing));
                UploadPrintFragment.this.mainVModel.queryRedeemCode(str);
            }
        });
    }

    void m1041x2fa76ba0(View view) {
        LoggerUtil.m1181d(TAG, "btnBack " + this.step);
        if (this.step == 1) {
            this.mainVModel.stopCheckOrderTask();
            ((FragmentUploadPrintBinding) this.mViewBinding).layoutPaymentSelect.resetView();
            this.step = 0;
            ViewUtil.setImageDrawable(((FragmentUploadPrintBinding) this.mViewBinding).btnBack, 902, C1910R.mipmap.upload_back_home);
            return;
        }
        ((MainActivity) requireActivity()).backToHomeFragment();
    }

    void m1042x69720d7f(View view) {
        clickPrint();
    }

    void m1043xa33caf5e(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        PictureSelectItem item = this.uploadPictureAdapter.getItem(i);
        if (item == null) {
            return;
        }
        clickUpPicture(item.getPhotoName(), false);
    }

    void m1044xdd07513d(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        clickFrameIc(i);
    }

    void m1045x16d1f31c(View view) {
        AnimUtil.startBtnClickAnim(view);
        SoundHelper.getInstance().playSoundEffect(3);
        this.printCount++;
        int printMaxCount = SpManager.getInstance().getPrintMaxCount();
        if (printMaxCount == 0) {
            printMaxCount = 99;
        }
        int iMin = Math.min(((UploadPrintVModel) this.mViewModel).getRemainSheets(), printMaxCount);
        if (this.printCount >= iMin) {
            this.printCount = iMin;
        }
        if (this.printCount > this.frameInfo.getPrintCount()) {
            ((FragmentUploadPrintBinding) this.mViewBinding).tvPrintPrice.setText(getString(C1910R.string.print_price, StringUtil.getPriceStr(this.PRINT_ADD_PRICE * (this.printCount - this.frameInfo.getPrintCount()))));
        }
        showPrintCount();
    }

    void m1046x509c94fb(View view) {
        AnimUtil.startBtnClickAnim(view);
        SoundHelper.getInstance().playSoundEffect(3);
        int i = this.printCount - 1;
        this.printCount = i;
        if (i > this.frameInfo.getPrintCount()) {
            ((FragmentUploadPrintBinding) this.mViewBinding).tvPrintPrice.setText(getString(C1910R.string.print_price, StringUtil.getPriceStr(this.PRINT_ADD_PRICE * (this.printCount - this.frameInfo.getPrintCount()))));
        } else if (this.printCount <= this.frameInfo.getPrintCount()) {
            ((FragmentUploadPrintBinding) this.mViewBinding).tvPrintPrice.setText("");
            this.printCount = this.frameInfo.getPrintCount();
        }
        showPrintCount();
    }

    private void initObserver() {
        ((UploadPrintVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1049x5ca78f8((Integer) obj);
            }
        });
        LiveData<List<PictureSelectItem>> pictureDownLD = ((UploadPrintVModel) this.mViewModel).getPictureDownLD();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final UploadPictureAdapter uploadPictureAdapter = this.uploadPictureAdapter;
        Objects.requireNonNull(uploadPictureAdapter);
        pictureDownLD.observe(viewLifecycleOwner, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                uploadPictureAdapter.submitList((List) obj);
            }
        });
        LiveData<List<UpFrameIcItem>> frameIcLD = ((UploadPrintVModel) this.mViewModel).getFrameIcLD();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final UploadFrameIcAdapter uploadFrameIcAdapter = this.frameIcAdapter;
        Objects.requireNonNull(uploadFrameIcAdapter);
        frameIcLD.observe(viewLifecycleOwner2, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                uploadFrameIcAdapter.submitList((List) obj);
            }
        });
        ((UploadPrintVModel) this.mViewModel).getQrcodeLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1050x3f951ad7((String) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1051x795fbcb6((Integer) obj);
            }
        });
        this.mainVModel.uploadPrintRecLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1047xefdf4ae((String) obj);
            }
        });
        this.mainVModel.redeemCodeInfoLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1048x48c8968d((RedeemCodeDto) obj);
            }
        });
    }

    void m1049x5ca78f8(Integer num) {
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        if (num.intValue() == 0) {
            LoggerUtil.m1181d(TAG, "count 0, step = " + this.step);
            int i = this.step;
            if (i == 2 || i == 3) {
                this.printCount = this.frameInfo.getPrintCount();
                startPrint();
            } else {
                ((MainActivity) requireActivity()).backToHomeFragment();
            }
        }
    }

    void m1050x3f951ad7(String str) {
        stopQrCodeLoadAnim();
        if (this.step == 4) {
            ((FragmentUploadPrintBinding) this.mViewBinding).ivQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 400, null));
            return;
        }
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutPaymentSelect.setQrCodeStr(str);
        if (OrderManager.isOnlinePayment()) {
            this.mainVModel.startCheckPhotoOrderTask();
        }
    }

    void m1051x795fbcb6(Integer num) {
        LoggerUtil.m1181d(TAG, "uploadPrint getPayStepLD " + num + Thread.currentThread().getName());
        if (num.intValue() == 3) {
            if (this.step == 3) {
                startPrint();
                return;
            } else {
                payFinish();
                return;
            }
        }
        if (num.intValue() == 5) {
            PaymentDialog paymentDialog = this.paymentDialog;
            if (paymentDialog != null && paymentDialog.isShowing()) {
                this.paymentDialog.updateBalance();
            } else {
                ((FragmentUploadPrintBinding) this.mViewBinding).layoutPaymentSelect.updateBalanceValue();
            }
        }
    }

    void m1047xefdf4ae(String str) {
        LoggerUtil.m1181d(TAG, "uploadPrint rec mqtt " + str);
        uploadFinish();
        String[] strArrSplit = str.split(",");
        if (strArrSplit.length != 2) {
            LoggerUtil.m1182e(TAG, "S2C_UP_PRINT_START error " + str);
            ToastHelper.getInstance().showToast(getString(C1910R.string.error));
        } else {
            ((UploadPrintVModel) this.mViewModel).downloadUpPictures(strArrSplit[1].split(";"));
        }
    }

    void m1048x48c8968d(RedeemCodeDto redeemCodeDto) {
        dismissLoadAnim();
        ((UploadPrintVModel) this.mViewModel).resumeCountdown();
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutPaymentSelect.resetView();
    }

    private void showPrintCount() {
        ((FragmentUploadPrintBinding) this.mViewBinding).tvPrintCount.setText(String.valueOf(this.printCount));
    }

    private void startCountdown(int i) {
        ((UploadPrintVModel) this.mViewModel).startCountdown(i);
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutCountdown.setCount(i);
    }

    private void uploadFinish() {
        LoggerUtil.m1181d(TAG, "uploadFinish ");
        ((FragmentUploadPrintBinding) this.mViewBinding).ivQrcode.setImageDrawable(null);
        if (OrderManager.isPhotoWithoutPay()) {
            payFinish();
        } else {
            ViewUtil.setViewGroupBg(((FragmentUploadPrintBinding) this.mViewBinding).getRoot(), 900, C1910R.mipmap.upload_bg_payment);
            ((FragmentUploadPrintBinding) this.mViewBinding).layoutPaymentSelect.setVisibility(0);
        }
    }

    private void payFinish() {
        LoggerUtil.m1181d(TAG, "payFinish ");
        App.mIsIdle = false;
        OrderManager.mRedeemCodeDto = null;
        startCountdown(300);
        this.step = 2;
        ((FragmentUploadPrintBinding) this.mViewBinding).ivQrcode.setVisibility(8);
        ((FragmentUploadPrintBinding) this.mViewBinding).btnBack.setVisibility(8);
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutPaymentSelect.setVisibility(8);
        ((FragmentUploadPrintBinding) this.mViewBinding).groupPrintCount.setVisibility(0);
        ((FragmentUploadPrintBinding) this.mViewBinding).rvUploadPicture.setVisibility(0);
        ((FragmentUploadPrintBinding) this.mViewBinding).rvFrameIc.setVisibility(0);
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutSplicing.setVisibility(0);
        ((FragmentUploadPrintBinding) this.mViewBinding).btnEnter.setVisibility(0);
        ViewUtil.setViewGroupBg(((FragmentUploadPrintBinding) this.mViewBinding).getRoot(), 901, C1910R.mipmap.upload_bg_process);
        ViewUtil.setImageDrawable(((FragmentUploadPrintBinding) this.mViewBinding).btnBack, 902, C1910R.mipmap.upload_back_home);
        ((FragmentUploadPrintBinding) this.mViewBinding).rvFrameIc.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1052xc5b6a0a7();
            }
        });
    }

    void m1052xc5b6a0a7() {
        clickFrameIc(0);
    }

    private void clickPrint() {
        int i;
        AnimUtil.startBtnClickAnim(((FragmentUploadPrintBinding) this.mViewBinding).btnEnter);
        if (this.frameInfo == null) {
            return;
        }
        if (((UploadPrintVModel) this.mViewModel).getRemainSheets() <= 0) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.non_printer_sheet));
            return;
        }
        final int printCount = this.printCount - this.frameInfo.getPrintCount();
        if (printCount > 0 && (i = this.PRINT_ADD_PRICE) > 0) {
            if (OrderManager.checkAddPrintBalance(i, printCount)) {
                startPrint();
                return;
            } else {
                this.step = 3;
                OrderManager.createPrintOrder(printCount, this.PRINT_ADD_PRICE, false, new IStringListener() {
                    @Override
                    public final void setValue(String str) {
                        this.f$0.m1040xc1f4a188(printCount, str);
                    }
                });
                return;
            }
        }
        startPrint();
    }

    void m1040xc1f4a188(final int i, final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1039x8829ffa9(i, str);
            }
        });
    }

    void m1039x8829ffa9(int i, String str) {
        startCountdown(SpManager.getInstance().getPaymentTime());
        PaymentDialog paymentDialog = new PaymentDialog(requireContext(), StringUtil.getPriceStr(i * this.PRINT_ADD_PRICE));
        this.paymentDialog = paymentDialog;
        paymentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.m1038x4e5f5dca(dialogInterface);
            }
        });
        this.paymentDialog.show();
        if (OrderManager.isOnlinePayment()) {
            this.paymentDialog.setQrCode(str);
            this.mainVModel.startCheckPrintOrderTask();
        }
    }

    void m1038x4e5f5dca(DialogInterface dialogInterface) {
        this.mainVModel.stopCheckOrderTask();
    }

    private void startPrint() {
        PaymentDialog paymentDialog = this.paymentDialog;
        if (paymentDialog != null && paymentDialog.isShowing()) {
            this.paymentDialog.dismiss();
            this.paymentDialog = null;
        }
        this.mainVModel.stopCheckOrderTask();
        this.step = 4;
        ((FragmentUploadPrintBinding) this.mViewBinding).btnBack.setEnabled(false);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1053xb8d7174d();
            }
        }, 5000L);
        showLoadAnim(getString(C1910R.string.processing_photo));
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutSplicing.compoundImage(new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m1055x2c6c5b0b(str);
            }
        });
    }

    void m1053xb8d7174d() {
        ((FragmentUploadPrintBinding) this.mViewBinding).btnBack.setEnabled(true);
    }

    void m1055x2c6c5b0b(String str) {
        dismissLoadAnim();
        LoggerUtil.m1181d(TAG, "UploadPrintFragment FrameSaveLD " + str);
        ((UploadPrintVModel) this.mViewModel).printerPrint(str, this.printCount, this.frameInfo);
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1054xf2a1b92c();
            }
        });
    }

    void m1054xf2a1b92c() {
        startCountdown(((UploadPrintVModel) this.mViewModel).getPrintTime(this.printCount) + 30);
        ((FragmentUploadPrintBinding) this.mViewBinding).rvFrameIc.setVisibility(8);
        ((FragmentUploadPrintBinding) this.mViewBinding).rvUploadPicture.setVisibility(8);
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutSplicing.setVisibility(8);
        ((FragmentUploadPrintBinding) this.mViewBinding).btnEnter.setVisibility(8);
        ((FragmentUploadPrintBinding) this.mViewBinding).groupPrintCount.setVisibility(8);
        ViewUtil.setViewGroupBg(((FragmentUploadPrintBinding) this.mViewBinding).getRoot(), 906, C1910R.mipmap.upload_bg_photo_code);
        ((FragmentUploadPrintBinding) this.mViewBinding).ivQrcode.setImageBitmap(null);
        startQrCodeLoadAnim();
        ((FragmentUploadPrintBinding) this.mViewBinding).ivQrcode.setVisibility(0);
        ((FragmentUploadPrintBinding) this.mViewBinding).btnBack.setVisibility(0);
    }

    private void clickUpPicture(String str, boolean z) {
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutSplicing.setPicture(str, z);
    }

    private void clickFrameIc(int i) {
        this.frameIcAdapter.setSelectedPosition(i);
        UpFrameIcItem item = this.frameIcAdapter.getItem(i);
        if (item == null) {
            return;
        }
        ((FragmentUploadPrintBinding) this.mViewBinding).layoutSplicing.setFrameInfo(item.frameInfo);
        if (item.frameInfo == null || item.frameInfo.getPhotoInfoList() == null) {
            return;
        }
        this.frameInfo = item.frameInfo;
        if (((UploadPrintVModel) this.mViewModel).isAllDownDone()) {
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1037xd7982dfe();
                }
            }, 100L);
        }
    }

    void m1037xd7982dfe() {
        boolean zIsNeedCut = this.frameInfo.isNeedCut();
        int size = this.frameInfo.getPhotoInfoList().size();
        int itemCount = this.uploadPictureAdapter.getItemCount();
        int i = zIsNeedCut ? (size + 1) / 2 : size;
        for (int i2 = 0; i2 < size; i2++) {
            PictureSelectItem item = this.uploadPictureAdapter.getItem((zIsNeedCut ? i2 % i : i2 % itemCount) % itemCount);
            if (item == null || TextUtils.isEmpty(item.getPhotoName())) {
                return;
            }
            clickUpPicture(item.getPhotoName(), true);
        }
    }

    private void startQrCodeLoadAnim() {
        if (this.animQrCodeLoading == null) {
            ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(((FragmentUploadPrintBinding) this.mViewBinding).ivQrcode, 490);
            this.animQrCodeLoading = rotationAnim1;
            rotationAnim1.start();
        }
    }

    private void stopQrCodeLoadAnim() {
        ValueAnimator valueAnimator = this.animQrCodeLoading;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ((FragmentUploadPrintBinding) this.mViewBinding).ivQrcode.setRotation(0.0f);
    }
}
