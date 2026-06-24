package com.wugu.doublecamera.main;

import android.animation.AnimatorSet;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpContextBasisFace;
import com.bumptech.glide.Glide;
import com.p020hp.jipp.model.TimeoutPredicate;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.SignCommand;
import com.wugu.doublecamera.bean.StartPrintingInfo;
import com.wugu.doublecamera.custom.PaletteView;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentPrintBinding;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.dialog.AddTextDialog;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.PrePrintVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FragmentUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.SizeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.UartUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import java.io.File;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PrePrintFragment extends ABaseFragment<FragmentPrintBinding, PrePrintVModel> {
    private AnimatorSet animBtnQrCode;
    private AnimatorSet animBtnSign;
    private FrameInfo frameInfo;
    private MainVModel mainVModel;
    private List<String> photoNameList;
    private int printStep;
    private SignatureFragment signatureFragment;
    private StickerFragment stickerFragment;
    private int printCount = 1;
    private int stickerMemoryOccupy = 0;
    private boolean isOnlyPrint = false;
    private int countdownTime = 0;

    public static PrePrintFragment startOnlyPrintFragment(String str) {
        PrePrintFragment prePrintFragment = new PrePrintFragment();
        Bundle bundle = new Bundle();
        bundle.putString("filePath", str);
        prePrintFragment.setArguments(bundle);
        return prePrintFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        LoggerUtil.m1181d("fragment", "PrePrintFragment Create");
        initView();
        initData();
        initListener();
        initObserver();
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                PrePrintFragment.lambda$onViewCreated$0();
            }
        });
    }

    static void lambda$onViewCreated$0() {
        PrinterHelper.getInstance().refreshPrinter(false);
        ThreadClock.sleep(1000L);
        SoundHelper.getInstance().playSoundEffect(11);
    }

    private void releaseAll() {
        cancelAllAnim();
        FragmentUtil.removeFragment(requireActivity(), this.stickerFragment);
        FragmentUtil.removeFragment(requireActivity(), this.signatureFragment);
        ((PrePrintVModel) this.mViewModel).cancelCountdown();
        this.mainVModel.stopCheckOrderTask();
        UartUtil.disablePayDevice();
        this.mainVModel = null;
        LoggerUtil.m1181d("PrePrintFragment", "releaseAll");
    }

    private void initView() {
        startCountdown(SpManager.getInstance().getPrintStartTime());
        if (App.mSystemMode == 4) {
            ((FragmentPrintBinding) this.mViewBinding).btnSign.setVisibility(8);
            setGroupPrintCountVisible(false);
            ViewUtil.setUiCustomLocate(((FragmentPrintBinding) this.mViewBinding).btnPrint, UiPosIndexEnum.PRINT_BTN_PRINT, SnmpContextBasisFace.MSS, 600);
        } else {
            this.stickerFragment = new StickerFragment();
            FragmentUtil.showFragment(((FragmentPrintBinding) this.mViewBinding).fragmentSticker.getId(), requireActivity(), this.stickerFragment, false);
            ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).btnPrint, UiPosIndexEnum.PRINT_BTN_PRINT);
        }
        ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PRINT_BG_STICKER);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).layoutFrame, UiPosIndexEnum.PRINT_FRAME_POS);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).layoutCountdown, UiPosIndexEnum.PRINT_COUNTDOWN);
        ViewUtil.setViewPosition(((FragmentPrintBinding) this.mViewBinding).fragmentSticker, UiPosIndexEnum.PRINT_STICKER_SIGN_POS);
        ViewUtil.setUI(((FragmentPrintBinding) this.mViewBinding).btnSign, UiPosIndexEnum.PRINT_BTN_SIGN);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).btnAddText, UiPosIndexEnum.PRINT_BTN_ADD_TEXT);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).ivPrintCountText, UiPosIndexEnum.PRINT_COUNT_TEXT);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).ivSub, UiPosIndexEnum.PRINT_COUNT_SUB);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).tvPrintCount, UiPosIndexEnum.PRINT_COUNT_NUM);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).ivAdd, UiPosIndexEnum.PRINT_COUNT_ADD);
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).tvPrintPrice, UiPosIndexEnum.PRINT_PRICE_POS);
        ViewUtil.setUI(((FragmentPrintBinding) this.mViewBinding).btnAddQrcode, UiPosIndexEnum.PRINT_BTN_ADD_QRCODE, C1910R.mipmap.ic_add_qrcode);
        ViewUtil.setViewGroupBg(((FragmentPrintBinding) this.mViewBinding).layoutPayment, 318);
        ((FragmentPrintBinding) this.mViewBinding).tvPrintPrice.setTextColor(Color.parseColor(SpManager.getInstance().getPriceTextColor()));
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.startBtnSignAnim();
            }
        }, 500L);
    }

    private void initListener() {
        ((FragmentPrintBinding) this.mViewBinding).ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m990xae3474b6(view);
            }
        });
        ((FragmentPrintBinding) this.mViewBinding).ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m991x48d53737(view);
            }
        });
        ((FragmentPrintBinding) this.mViewBinding).btnAddQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m992xe375f9b8(view);
            }
        });
        ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setCallback(new PaletteView.Callback() {
            @Override
            public final void onUndoRedoStatusChanged() {
                this.f$0.m993x7e16bc39();
            }
        });
        ((FragmentPrintBinding) this.mViewBinding).btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m994x18b77eba(view);
            }
        });
        ((FragmentPrintBinding) this.mViewBinding).btnAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m995xb358413b(view);
            }
        });
        ((FragmentPrintBinding) this.mViewBinding).btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m996x4df903bc(view);
            }
        });
    }

    void m990xae3474b6(View view) {
        AnimUtil.startBtnClickAnim(view);
        SoundHelper.getInstance().playSoundEffect(3);
        this.printCount++;
        int printMaxCount = SpManager.getInstance().getPrintMaxCount();
        if (printMaxCount == 0) {
            printMaxCount = 99;
        }
        int iMin = Math.min(((PrePrintVModel) this.mViewModel).getRemainSheets(), printMaxCount);
        if (this.printCount >= iMin) {
            this.printCount = iMin;
        }
        if (this.printCount > this.frameInfo.getPrintCount()) {
            ((FragmentPrintBinding) this.mViewBinding).tvPrintPrice.setText(getString(C1910R.string.print_price, StringUtil.getPriceStr(this.frameInfo.getAddPrice() * (this.printCount - this.frameInfo.getPrintCount()))));
        }
        showPrintCount();
    }

    void m991x48d53737(View view) {
        AnimUtil.startBtnClickAnim(view);
        SoundHelper.getInstance().playSoundEffect(3);
        int i = this.printCount - 1;
        this.printCount = i;
        if (i > this.frameInfo.getPrintCount()) {
            ((FragmentPrintBinding) this.mViewBinding).tvPrintPrice.setText(getString(C1910R.string.print_price, StringUtil.getPriceStr(this.frameInfo.getAddPrice() * (this.printCount - this.frameInfo.getPrintCount()))));
        } else if (this.printCount <= this.frameInfo.getPrintCount()) {
            ((FragmentPrintBinding) this.mViewBinding).tvPrintPrice.setText("");
            this.printCount = this.frameInfo.getPrintCount();
        }
        showPrintCount();
    }

    void m992xe375f9b8(View view) {
        ((FragmentPrintBinding) this.mViewBinding).layoutSticker.addSticker(QrCodeUtil.getBitmapQrCode(OrderFileUtil.getOrderFileQrCodeStr(), 400, null), false);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void m993x7e16bc39() {
        /*
            r3 = this;
            com.wugu.doublecamera.widget.SoundHelper r0 = com.wugu.doublecamera.widget.SoundHelper.getInstance()
            r1 = 3
            r0.playSoundEffect(r1)
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPrintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPrintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canRedo()
            r2 = 0
            if (r0 != 0) goto L23
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPrintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPrintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canUndo()
            if (r0 != 0) goto L23
        L21:
            r1 = r2
            goto L6f
        L23:
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPrintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPrintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canRedo()
            if (r0 != 0) goto L3d
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPrintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPrintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canUndo()
            if (r0 == 0) goto L3d
            r1 = 1
            goto L6f
        L3d:
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPrintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPrintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canRedo()
            if (r0 == 0) goto L57
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPrintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPrintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canUndo()
            if (r0 != 0) goto L57
            r1 = 2
            goto L6f
        L57:
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPrintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPrintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canRedo()
            if (r0 == 0) goto L21
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPrintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPrintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canUndo()
            if (r0 == 0) goto L21
        L6f:
            com.wugu.doublecamera.main.vm.MainVModel r0 = r3.mainVModel
            com.wugu.doublecamera.widget.NotStickMutableLiveData<java.lang.Integer> r0 = r0.signPaintUndoRedo
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.postValue(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.main.PrePrintFragment.m993x7e16bc39():void");
    }

    void m994x18b77eba(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (((FragmentPrintBinding) this.mViewBinding).layoutPalette.isEnabled()) {
            ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PRINT_BG_STICKER);
            ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
            FragmentUtil.hideFragment(requireActivity(), this.signatureFragment);
            FragmentUtil.showFragment(((FragmentPrintBinding) this.mViewBinding).fragmentSticker.getId(), requireActivity(), this.stickerFragment, false);
            return;
        }
        if (this.signatureFragment == null) {
            this.signatureFragment = new SignatureFragment();
        }
        ((FragmentPrintBinding) this.mViewBinding).layoutSticker.clearFocus();
        ViewUtil.setUiLocate(((FragmentPrintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PRINT_BG_SIGN);
        FragmentUtil.showFragment(((FragmentPrintBinding) this.mViewBinding).fragmentSticker.getId(), requireActivity(), this.signatureFragment, false);
        FragmentUtil.hideFragment(requireActivity(), this.stickerFragment);
        ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setEnabled(true);
    }

    void m995xb358413b(View view) {
        showAddTextDialog();
    }

    void m996x4df903bc(View view) {
        AnimUtil.startBtnClickAnim(view);
        if (this.printStep == 2) {
            LoggerUtil.m1181d("fragment", "PrePrintFragment back print payment");
            OrderManager.mPrintOrderId = null;
            setPrintStepView(1);
            UartUtil.disablePayDevice();
            return;
        }
        preToPrint();
    }

    private void lambda$initListener$8(View view) {
        payFinish();
    }

    private void initObserver() {
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1002x41b5ab60((String) obj);
            }
        });
        this.mainVModel.photoUrlLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m997x7b2b3668((List) obj);
            }
        });
        ((PrePrintVModel) this.mViewModel).getCountdownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m998x15cbf8e9((Integer) obj);
            }
        });
        ((PrePrintVModel) this.mViewModel).getFrameSaveLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1000x4b0d7deb((String) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1001xe5ae406c((Integer) obj);
            }
        });
        this.mainVModel.stickerAddLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.addSticker((String) obj);
            }
        });
        this.mainVModel.signCommand.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.signPaintCommand((SignCommand) obj);
            }
        });
    }

    void m1002x41b5ab60(String str) {
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        this.frameInfo = orderFrameInfo;
        if (orderFrameInfo == null) {
            return;
        }
        if (orderFrameInfo.getFrameType() == 2) {
            ((FragmentPrintBinding) this.mViewBinding).btnAddText.setVisibility(0);
        }
        if (this.isOnlyPrint) {
            FrameInfo frameInfo = this.frameInfo;
            if (frameInfo != null) {
                frameInfo.setFrameKey(frameInfo.getFrameKey());
                return;
            }
            return;
        }
        this.printCount = this.frameInfo.getPrintCount();
        showPrintCount();
        if (this.frameInfo.getFrameType() != 1 && this.frameInfo.getFrameType() != 3) {
            ((FragmentPrintBinding) this.mViewBinding).ivPrintFrame.setImageBitmap(BitmapUtil.getLocalBitmap(requireContext(), this.frameInfo.getFramePath()));
        }
        setImgToFrame();
    }

    void m997x7b2b3668(List list) {
        boolean zIsNeedCut;
        boolean zIsBrokenLine;
        if (this.isOnlyPrint || list.isEmpty()) {
            return;
        }
        this.photoNameList = list;
        setImgToFrame();
        FrameInfo frameInfo = this.frameInfo;
        if (frameInfo != null) {
            zIsNeedCut = frameInfo.isNeedCut();
            zIsBrokenLine = this.frameInfo.isBrokenLine();
        } else {
            zIsNeedCut = false;
            zIsBrokenLine = false;
        }
        ((PrePrintVModel) this.mViewModel).uploadNegPhotos(list, zIsNeedCut, zIsBrokenLine);
    }

    void m998x15cbf8e9(Integer num) {
        if (this.printStep == 1) {
            this.countdownTime = num.intValue();
            if (num.intValue() == 15) {
                ToastHelper.getInstance().showToast(getString(C1910R.string.countdown_almost_over));
            } else if (num.intValue() == 1) {
                ((FragmentPrintBinding) this.mViewBinding).layoutSticker.setEnabled(false);
                ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
            }
        }
        if (num.intValue() < 0) {
            ((FragmentPrintBinding) this.mViewBinding).layoutCountdown.setCount(0);
        } else {
            ((FragmentPrintBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        }
        if (num.intValue() == 0) {
            int i = this.printStep;
            if (i == 1) {
                this.printCount = this.frameInfo.getPrintCount();
                preToPrint();
            } else if (i == 2) {
                ((FragmentPrintBinding) this.mViewBinding).btnPrint.performClick();
            }
        }
    }

    void m1000x4b0d7deb(String str) {
        LoggerUtil.m1181d("PrePrintFragment", "FrameSaveLD ");
        if (this.mViewBinding == 0 || this.mainVModel == null) {
            return;
        }
        String str2 = requireContext().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str;
        this.mainVModel.startPrintingLD.postValue(new StartPrintingInfo(str2, this.printCount));
        LoggerUtil.m1181d("PrePrintFragment", "PrePrintFragment FrameSaveLD " + str2);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m999xb06cbb6a();
            }
        }, 160L);
    }

    void m999xb06cbb6a() {
        dismissLoadAnim();
        ((FragmentPrintBinding) this.mViewBinding).layoutFrame.removeAllViews();
        ((FragmentPrintBinding) this.mViewBinding).layoutSticker.removeAllViews();
        ((FragmentPrintBinding) this.mViewBinding).layoutPalette.clear();
        ((FragmentPrintBinding) this.mViewBinding).getRoot().removeAllViews();
        releaseAll();
    }

    void m1001xe5ae406c(Integer num) {
        LoggerUtil.m1181d("prePrintFragment", "payStepLd " + num);
        int iIntValue = num.intValue();
        if (iIntValue == 3) {
            payFinish();
        } else {
            if (iIntValue != 5) {
                return;
            }
            ((FragmentPrintBinding) this.mViewBinding).layoutPayment.setBalance(OrderManager.mBalance);
            startCountdown(SpManager.getInstance().getPaymentTime());
        }
    }

    private void initData() {
        this.countdownTime = SpManager.getInstance().getPrintStartTime();
        this.printStep = 1;
        this.isOnlyPrint = false;
        if (getArguments() != null) {
            String string = getArguments().getString("filePath");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            this.isOnlyPrint = true;
            this.printCount = 1;
            FrameInfo frameInfo = new FrameInfo();
            this.frameInfo = frameInfo;
            frameInfo.setPrintCount(1);
            this.frameInfo.setPrice(SpManager.getInstance().getUploadPrintPrice());
            this.frameInfo.setoPrice(SpManager.getInstance().getUploadPrintPrice());
            this.frameInfo.setAddPrice(SpManager.getInstance().getUploadPrintAddPrice());
            this.frameInfo.setAddOPrice(SpManager.getInstance().getUploadPrintAddPrice());
            this.frameInfo.setFramePath(string);
            this.frameInfo.setFrameName("upload");
            showPrintCount();
            ((FragmentPrintBinding) this.mViewBinding).ivPrintFrame.setImageBitmap(BitmapUtil.getLocalBitmap(requireContext(), string));
            setImgToFrame();
        }
    }

    private void showPrintCount() {
        ((FragmentPrintBinding) this.mViewBinding).tvPrintCount.setText(String.valueOf(this.frameInfo.isNeedCut() ? this.printCount * 2 : this.printCount));
    }

    private void setImgToFrame() {
        FrameInfo frameInfo;
        if (this.isOnlyPrint || !((frameInfo = this.frameInfo) == null || frameInfo.getPhotoInfoList() == null || this.photoNameList == null)) {
            if (this.frameInfo.isNeedCut()) {
                int size = this.photoNameList.size();
                for (int i = 0; i < size; i++) {
                    List<String> list = this.photoNameList;
                    list.add(list.get(i));
                }
            }
            if (this.frameInfo.isBrokenLine()) {
                int size2 = this.photoNameList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    List<String> list2 = this.photoNameList;
                    list2.add(list2.get(i2));
                }
            }
            ((FragmentPrintBinding) this.mViewBinding).ivPrintFrame.post(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1003xae4e963c();
                }
            });
        }
    }

    void m1003xae4e963c() {
        int i;
        int i2;
        float f;
        BitmapFactory.Options bitmapOptions = BitmapUtil.getBitmapOptions(requireContext(), this.frameInfo.getFramePath());
        if (bitmapOptions == null) {
            return;
        }
        int i3 = bitmapOptions.outWidth;
        int i4 = bitmapOptions.outHeight;
        int width = ((FragmentPrintBinding) this.mViewBinding).ivPrintFrame.getWidth();
        int height = ((FragmentPrintBinding) this.mViewBinding).ivPrintFrame.getHeight();
        float f2 = width;
        float f3 = height;
        float f4 = i3;
        float f5 = i4;
        float f6 = f2 / f4;
        float f7 = f3 / f5;
        if (f4 / f5 > f2 / f3) {
            i2 = ((int) (f3 - (f5 * f6))) / 2;
            i = 0;
        } else {
            i = ((int) (f2 - (f4 * f7))) / 2;
            f6 = f7;
            i2 = 0;
        }
        if (this.photoNameList != null) {
            File filesDir = requireContext().getFilesDir();
            int i5 = 0;
            while (i5 < this.photoNameList.size()) {
                if (this.frameInfo.getPhotoInfoList().size() <= i5) {
                    return;
                }
                if (this.frameInfo.getPhotoInfoList().get(i5) == null) {
                    f = f6;
                } else {
                    int locationX = (int) (r7.getLocationX() * f6);
                    int locationY = (int) (r7.getLocationY() * f6);
                    f = f6;
                    AppCompatImageView appCompatImageView = new AppCompatImageView(requireContext());
                    appCompatImageView.setLayoutParams(new ViewGroup.LayoutParams((int) (((double) (r7.getWidth() * f6)) + 2.5d), (int) (((double) (r7.getHeight() * f6)) + 2.5d)));
                    appCompatImageView.setX(locationX + i);
                    appCompatImageView.setY(locationY + i2);
                    File file = new File(filesDir, this.photoNameList.get(i5));
                    if (file.exists()) {
                        Glide.with(requireContext()).load(file).into(appCompatImageView);
                    }
                    ((FragmentPrintBinding) this.mViewBinding).layoutFrame.addView(appCompatImageView);
                }
                i5++;
                f6 = f;
            }
        }
        setLimitSize(height, width, i, i2);
        ((FragmentPrintBinding) this.mViewBinding).btnAddText.setVisibility(this.frameInfo.getFrameType() != 2 ? 8 : 0);
    }

    private void setLimitSize(int i, int i2, int i3, int i4) {
        ViewGroup.LayoutParams layoutParams = ((FragmentPrintBinding) this.mViewBinding).layoutSticker.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = ((FragmentPrintBinding) this.mViewBinding).layoutPalette.getLayoutParams();
        int i5 = i2 - (i3 * 2);
        layoutParams.width = i5;
        int i6 = i - (i4 * 2);
        layoutParams.height = i6;
        layoutParams2.width = i5;
        layoutParams2.height = i6;
        ((FragmentPrintBinding) this.mViewBinding).layoutSticker.setLayoutParams(layoutParams);
        ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setLayoutParams(layoutParams2);
        ((FragmentPrintBinding) this.mViewBinding).layoutFrame.postDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1004x4b41fada();
            }
        }, 100L);
    }

    void m1004x4b41fada() {
        ((FragmentPrintBinding) this.mViewBinding).ivPrintFrame.bringToFront();
        ((FragmentPrintBinding) this.mViewBinding).layoutSticker.bringToFront();
        ((FragmentPrintBinding) this.mViewBinding).layoutPalette.bringToFront();
    }

    public void addSticker(String str) {
        int memoryClass = ((ActivityManager) requireContext().getSystemService(TimeoutPredicate.activity)).getMemoryClass() * 512;
        int size = ((FragmentPrintBinding) this.mViewBinding).layoutSticker.getStickerList().size();
        if (this.stickerMemoryOccupy > memoryClass || size > 15) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.sticker_limit));
            return;
        }
        Bitmap localBitmap = BitmapUtil.getLocalBitmap(requireContext(), str);
        if (localBitmap == null) {
            return;
        }
        this.stickerMemoryOccupy += localBitmap.getAllocationByteCount() / 1024;
        ((FragmentPrintBinding) this.mViewBinding).layoutSticker.addSticker(localBitmap, false);
    }

    private void preToPrint() {
        SoundHelper.getInstance().playSoundEffect(3);
        if (((PrePrintVModel) this.mViewModel).getRemainSheets() <= 0) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.non_printer_sheet));
            return;
        }
        if (this.printCount > this.frameInfo.getPrintCount()) {
            int printCount = this.printCount - this.frameInfo.getPrintCount();
            if (OrderManager.checkAddPrintBalance(this.frameInfo.getAddPrice(), printCount)) {
                print(this.printCount);
                return;
            }
            OrderManager.createPrintOrder(printCount, this.frameInfo.getAddPrice(), false, new IStringListener() {
                @Override
                public final void setValue(String str) {
                    this.f$0.m1694lambda$preToPrint$18$comwugudoublecameramainPrePrintFragment(str);
                }
            });
            int iMax = Math.max(0, this.frameInfo.getAddPrice() * (this.printCount - this.frameInfo.getPrintCount()));
            setPrintStepView(2);
            ((FragmentPrintBinding) this.mViewBinding).layoutPayment.setPriceStr(OrderManager.getShowPriceStr(iMax));
            ((FragmentPrintBinding) this.mViewBinding).layoutPayment.setBalance(OrderManager.mBalance);
            return;
        }
        print(this.frameInfo.getPrintCount());
    }

    void m1694lambda$preToPrint$18$comwugudoublecameramainPrePrintFragment(final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1693lambda$preToPrint$17$comwugudoublecameramainPrePrintFragment(str);
            }
        });
    }

    void m1693lambda$preToPrint$17$comwugudoublecameramainPrePrintFragment(String str) {
        ((FragmentPrintBinding) this.mViewBinding).layoutPayment.setQrCodeStr(str);
        this.mainVModel.startCheckPrintOrderTask();
        startCountdown(SpManager.getInstance().getPaymentTime());
    }

    private void setPrintStepView(int i) {
        this.printStep = i;
        LoggerUtil.m1181d("fragment", "PrePrintFragment setPrintStepView " + i);
        if (i == 1) {
            cancelAllAnim();
            ViewUtil.setImageDrawable(((FragmentPrintBinding) this.mViewBinding).btnPrint, UiPosIndexEnum.PRINT_BTN_PRINT);
            setGroupFrameModifyVisible(true);
            setGroupPrintCountVisible(true);
            ((FragmentPrintBinding) this.mViewBinding).layoutPayment.setVisibility(8);
            startCountdown(this.countdownTime);
            this.mainVModel.stopCheckOrderTask();
            return;
        }
        if (i != 2) {
            return;
        }
        cancelAllAnim();
        ViewUtil.setImageDrawable(((FragmentPrintBinding) this.mViewBinding).btnPrint, UiPosIndexEnum.PRINT_BTN_BACK);
        setGroupFrameModifyVisible(false);
        setGroupPrintCountVisible(false);
        ((FragmentPrintBinding) this.mViewBinding).layoutPayment.setVisibility(0);
        startCountdown(SpManager.getInstance().getPaymentTime());
    }

    private void setGroupFrameModifyVisible(boolean z) {
        ((FragmentPrintBinding) this.mViewBinding).fragmentSticker.setVisibility(z ? 0 : 8);
        ((FragmentPrintBinding) this.mViewBinding).btnSign.setVisibility(z ? 0 : 8);
        ((FragmentPrintBinding) this.mViewBinding).btnAddText.setVisibility(this.frameInfo.getFrameType() == 2 && z ? 0 : 8);
    }

    private void setGroupPrintCountVisible(boolean z) {
        ((FragmentPrintBinding) this.mViewBinding).ivPrintCountText.setVisibility(z ? 0 : 8);
        ((FragmentPrintBinding) this.mViewBinding).ivSub.setVisibility(z ? 0 : 8);
        ((FragmentPrintBinding) this.mViewBinding).tvPrintCount.setVisibility(z ? 0 : 8);
        ((FragmentPrintBinding) this.mViewBinding).ivAdd.setVisibility(z ? 0 : 8);
        ((FragmentPrintBinding) this.mViewBinding).tvPrintPrice.setVisibility(z ? 0 : 8);
    }

    private void payFinish() {
        LoggerUtil.m1181d("PrePrintFragment", "payFinish");
        print(this.printCount);
    }

    private void print(int i) {
        ((PrePrintVModel) this.mViewModel).cancelCountdown();
        this.printCount = i;
        ((FragmentPrintBinding) this.mViewBinding).layoutSticker.setEnabled(false);
        ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
        ((FragmentPrintBinding) this.mViewBinding).btnPrint.setEnabled(false);
        showLoadAnim(getString(C1910R.string.saving));
        LoggerUtil.m1181d("fragment", "PrePrintFragment print " + i);
        ((PrePrintVModel) this.mViewModel).saveFinalPhoto(this.frameInfo, ((FragmentPrintBinding) this.mViewBinding).layoutSticker.getWidth(), ((FragmentPrintBinding) this.mViewBinding).layoutSticker.getHeight(), this.photoNameList, ((FragmentPrintBinding) this.mViewBinding).layoutSticker.getStickerList(), ((FragmentPrintBinding) this.mViewBinding).layoutPalette.getDrawBitmap(), i);
    }

    private void cancelAllAnim() {
        stopBtnSignAnim();
    }

    public void signPaintCommand(SignCommand signCommand) {
        if (((FragmentPrintBinding) this.mViewBinding).layoutPalette.isEnabled()) {
            if (signCommand.operate == 3) {
                ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setMode(PaletteView.Mode.DRAW);
                ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setPenColor(signCommand.paintColor);
                ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setPenRawSize(signCommand.paintSize);
            } else {
                if (signCommand.operate == 1) {
                    ((FragmentPrintBinding) this.mViewBinding).layoutPalette.undo();
                    return;
                }
                if (signCommand.operate == 2) {
                    ((FragmentPrintBinding) this.mViewBinding).layoutPalette.redo();
                } else if (signCommand.operate == 4) {
                    ((FragmentPrintBinding) this.mViewBinding).layoutPalette.setMode(PaletteView.Mode.ERASER);
                } else if (signCommand.operate == 5) {
                    ((FragmentPrintBinding) this.mViewBinding).layoutPalette.clear();
                }
            }
        }
    }

    private void startCountdown(int i) {
        ((PrePrintVModel) this.mViewModel).startCountdown(i);
        ((FragmentPrintBinding) this.mViewBinding).layoutCountdown.setCount(i);
    }

    private void showAddTextDialog() {
        new AddTextDialog(requireContext(), new AddTextDialog.ITextInfoListener() {
            @Override
            public final void setValue(String str, int i, int i2) {
                this.f$0.m1005x449730d8(str, i, i2);
            }
        }).show();
    }

    void m1005x449730d8(String str, int i, int i2) {
        ((FragmentPrintBinding) this.mViewBinding).layoutSticker.addSticker(BitmapUtil.getTextBitmap(str, SizeUtil.sp2px(requireContext(), i), i2), true);
    }

    public void startBtnSignAnim() {
        if (this.animBtnSign == null) {
            AnimatorSet breathAnim = AnimUtil.getBreathAnim(((FragmentPrintBinding) this.mViewBinding).btnSign, 2000);
            this.animBtnSign = breathAnim;
            breathAnim.start();
        }
        if (this.animBtnQrCode == null) {
            AnimatorSet breathAnim2 = AnimUtil.getBreathAnim(((FragmentPrintBinding) this.mViewBinding).btnAddQrcode, 2000);
            this.animBtnQrCode = breathAnim2;
            breathAnim2.start();
        }
    }

    private void stopBtnSignAnim() {
        AnimatorSet animatorSet = this.animBtnSign;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.animBtnQrCode;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
    }
}
