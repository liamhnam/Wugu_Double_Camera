package com.wugu.doublecamera.main.poster_sys;

import android.animation.AnimatorSet;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.p020hp.jipp.model.TimeoutPredicate;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.SignCommand;
import com.wugu.doublecamera.bean.StartPrintingInfo;
import com.wugu.doublecamera.custom.PaletteView;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.SignatureFragment;
import com.wugu.doublecamera.main.StickerFragment;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.poster_sys.p024vm.PosterPrePrintVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FragmentUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.UartUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import com.wugu.stickerview.StickerView;
import java.util.Iterator;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PosterPrePrintFragment extends ABaseFragment<FragmentPosterPreprintBinding, PosterPrePrintVModel> {
    private AnimatorSet animatorSet;
    private FrameInfo frameInfo;
    private MainVModel mainVModel;
    private int printStep;
    private SignatureFragment signatureFragment;
    private StickerFragment stickerFragment;
    private int printCount = 1;
    private int stickerMemoryOccupy = 0;

    public static PosterPrePrintFragment startOnlyPrintFragment(String str) {
        PosterPrePrintFragment posterPrePrintFragment = new PosterPrePrintFragment();
        Bundle bundle = new Bundle();
        bundle.putString("filePath", str);
        posterPrePrintFragment.setArguments(bundle);
        return posterPrePrintFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initData();
        initListener();
        initObserver();
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                PosterPrePrintFragment.lambda$onViewCreated$0();
            }
        });
    }

    static void lambda$onViewCreated$0() {
        ThreadClock.sleep(1000L);
        SoundHelper.getInstance().playSoundEffect(11);
    }

    private void releaseAll() {
        cancelAllAnim();
        FragmentUtil.removeFragment(requireActivity(), this.stickerFragment);
        FragmentUtil.removeFragment(requireActivity(), this.signatureFragment);
        ((PosterPrePrintVModel) this.mViewModel).cancelCountdown();
        UartUtil.disablePayDevice();
        this.mainVModel = null;
    }

    private void initView() {
        startCountdown(SpManager.getInstance().getPrintStartTime());
        this.stickerFragment = new StickerFragment();
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
        ViewUtil.setUiLocate(((FragmentPosterPreprintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PRINT_BG_STICKER);
        ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).btnSign, UiPosIndexEnum.PRINT_BTN_SIGN);
        ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).btnPrint, UiPosIndexEnum.PHOTO_BTN_CONFIRM);
        ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintCountText, UiPosIndexEnum.PRINT_COUNT_TEXT);
        ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).ivSub, UiPosIndexEnum.PRINT_COUNT_SUB);
        ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).ivAdd, UiPosIndexEnum.PRINT_COUNT_ADD);
        ViewUtil.setImageBackground(((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrint, 1015);
        ViewUtil.setImageBackground(((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrint, 1016);
        ViewUtil.setImageBackground(((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrintSelected, 1017);
        ViewUtil.setImageBackground(((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrintSelected, 1017);
        ViewUtil.setViewGroupBg(((FragmentPosterPreprintBinding) this.mViewBinding).layoutPayment, 318);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.startBtnSignAnim();
            }
        }, 500L);
        setPrintStepView(1);
        ViewUtil.setImageViewGray(((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrint, true, 0.0f);
    }

    private void initData() {
        App.mPosterBlackPrint = false;
        String string = getArguments().getString("filePath");
        if (!TextUtils.isEmpty(string)) {
            this.printCount = 1;
            LoggerUtil.m1181d("PosterPrePrintFragment", "initData onlyPrintFilePath = " + this.frameInfo);
            if (this.frameInfo == null) {
                this.frameInfo = new FrameInfo();
            }
            this.frameInfo.setPrintCount(1);
            this.frameInfo.setFramePath(string);
            showPrintCount();
            ((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintFrame.setImageBitmap(BitmapUtil.getLocalBitmap(requireContext(), string));
            setImgToFrame();
        }
        this.printStep = 1;
        ((PosterPrePrintVModel) this.mViewModel).preGetPrinterSheets();
    }

    private void initListener() {
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1124xcef4e81d(view);
            }
        });
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1125x633357bc(view);
            }
        });
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1126xf771c75b(view);
            }
        });
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1127x8bb036fa(view);
            }
        });
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setCallback(new PaletteView.Callback() {
            @Override
            public final void onUndoRedoStatusChanged() {
                this.f$0.m1128x1feea699();
            }
        });
        ((FragmentPosterPreprintBinding) this.mViewBinding).btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1129xb42d1638(view);
            }
        });
        ((FragmentPosterPreprintBinding) this.mViewBinding).btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1130x486b85d7(view);
            }
        });
    }

    void m1124xcef4e81d(View view) {
        AnimUtil.startBtnClickAnim(view);
        SoundHelper.getInstance().playSoundEffect(3);
        this.printCount++;
        int printMaxCount = SpManager.getInstance().getPrintMaxCount();
        if (printMaxCount == 0) {
            printMaxCount = 99;
        }
        int iMin = Math.min(((PosterPrePrintVModel) this.mViewModel).getRemainSheets(), printMaxCount);
        if (this.printCount >= iMin) {
            this.printCount = iMin;
        }
        showPrintCount();
    }

    void m1125x633357bc(View view) {
        AnimUtil.startBtnClickAnim(view);
        SoundHelper.getInstance().playSoundEffect(3);
        int i = this.printCount - 1;
        this.printCount = i;
        if (i <= this.frameInfo.getPrintCount()) {
            this.printCount = this.frameInfo.getPrintCount();
        }
        showPrintCount();
    }

    void m1126xf771c75b(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrintSelected.getVisibility() == 0) {
            return;
        }
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrintSelected.setVisibility(0);
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrintSelected.setVisibility(8);
        setFrameBlack(false);
    }

    void m1127x8bb036fa(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrintSelected.getVisibility() == 0) {
            return;
        }
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrintSelected.setVisibility(8);
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrintSelected.setVisibility(0);
        setFrameBlack(true);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void m1128x1feea699() {
        /*
            r3 = this;
            com.wugu.doublecamera.widget.SoundHelper r0 = com.wugu.doublecamera.widget.SoundHelper.getInstance()
            r1 = 3
            r0.playSoundEffect(r1)
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canRedo()
            r2 = 0
            if (r0 != 0) goto L23
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canUndo()
            if (r0 != 0) goto L23
        L21:
            r1 = r2
            goto L6f
        L23:
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canRedo()
            if (r0 != 0) goto L3d
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canUndo()
            if (r0 == 0) goto L3d
            r1 = 1
            goto L6f
        L3d:
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canRedo()
            if (r0 == 0) goto L57
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canUndo()
            if (r0 != 0) goto L57
            r1 = 2
            goto L6f
        L57:
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding) r0
            com.wugu.doublecamera.custom.PaletteView r0 = r0.layoutPalette
            boolean r0 = r0.canRedo()
            if (r0 == 0) goto L21
            VB extends androidx.viewbinding.ViewBinding r0 = r3.mViewBinding
            com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding r0 = (com.wugu.doublecamera.databinding.FragmentPosterPreprintBinding) r0
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
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.main.poster_sys.PosterPrePrintFragment.m1128x1feea699():void");
    }

    void m1129xb42d1638(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.isEnabled()) {
            ViewUtil.setUiLocate(((FragmentPosterPreprintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PRINT_BG_STICKER);
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
            FragmentUtil.hideFragment(requireActivity(), this.signatureFragment);
            FragmentUtil.showFragment(((FragmentPosterPreprintBinding) this.mViewBinding).fragmentSticker.getId(), requireActivity(), this.stickerFragment, false);
            return;
        }
        if (this.signatureFragment == null) {
            this.signatureFragment = new SignatureFragment();
        }
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.clearFocus();
        ViewUtil.setUiLocate(((FragmentPosterPreprintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PRINT_BG_SIGN);
        FragmentUtil.showFragment(((FragmentPosterPreprintBinding) this.mViewBinding).fragmentSticker.getId(), requireActivity(), this.signatureFragment, false);
        FragmentUtil.hideFragment(requireActivity(), this.stickerFragment);
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setEnabled(true);
    }

    void m1130x486b85d7(View view) {
        AnimUtil.startBtnClickAnim(view);
        int i = this.printStep;
        if (i == 1) {
            setPrintStepView(4);
            return;
        }
        if (i == 2) {
            OrderManager.mPrintOrderId = null;
            this.mainVModel.stopCheckOrderTask();
            setPrintStepView(1);
        } else if (i == 4) {
            preToPrint();
        }
    }

    private void initObserver() {
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1134x47e82b14((String) obj);
            }
        });
        ((PosterPrePrintVModel) this.mViewModel).getCountdownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1135xdc269ab3((Integer) obj);
            }
        });
        ((PosterPrePrintVModel) this.mViewModel).getFrameSaveLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1132x9e5f04a((String) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1133x9e245fe9((Integer) obj);
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

    void m1134x47e82b14(String str) {
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        LoggerUtil.m1181d("PosterPrePrintFragment", "chooseFrameOkLD: " + this.frameInfo);
        if (orderFrameInfo == null) {
            return;
        }
        String framePath = orderFrameInfo.getFramePath();
        FrameInfo frameInfo = this.frameInfo;
        if (frameInfo != null) {
            framePath = frameInfo.getFramePath();
        }
        this.frameInfo = orderFrameInfo;
        this.printCount = orderFrameInfo.getPrintCount();
        this.frameInfo.setFramePath(framePath);
    }

    void m1135xdc269ab3(Integer num) {
        if (num.intValue() < 0) {
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutCountdown.setCount(0);
        } else {
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        }
        if (num.intValue() == 0) {
            preToPrint();
        }
    }

    void m1132x9e5f04a(String str) {
        if (this.mViewBinding == 0) {
            return;
        }
        this.mainVModel.startPrintingLD.postValue(new StartPrintingInfo(requireContext().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str, this.printCount));
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1131x75a780ab();
            }
        }, 160L);
    }

    void m1131x75a780ab() {
        dismissLoadAnim();
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutFrame.removeAllViews();
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.removeAllViews();
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.clear();
        ((FragmentPosterPreprintBinding) this.mViewBinding).getRoot().removeAllViews();
        releaseAll();
    }

    void m1133x9e245fe9(Integer num) {
        LoggerUtil.m1181d("PosterPrePrintFragment", "payStep: " + num);
        int iIntValue = num.intValue();
        if (iIntValue == 3) {
            print(this.printCount);
        } else {
            if (iIntValue != 5) {
                return;
            }
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPayment.setBalance(OrderManager.mBalance);
            startCountdown(SpManager.getInstance().getPaymentTime());
        }
    }

    private void setPrintStepView(int i) {
        this.printStep = i;
        if (i == 1) {
            cancelAllAnim();
            ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).btnPrint, UiPosIndexEnum.PHOTO_BTN_CONFIRM);
            setGroupFrameModifyVisible(true);
            setGroupPrintCountVisible(false);
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.setEnabled(true);
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPayment.setVisibility(8);
            startCountdown(SpManager.getInstance().getPrintStartTime());
            return;
        }
        if (i != 2) {
            if (i != 4) {
                return;
            }
            ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).btnPrint, UiPosIndexEnum.PRINT_BTN_PRINT);
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.setEnabled(false);
            setGroupFrameModifyVisible(false);
            setGroupPrintCountVisible(true);
            startCountdown(SpManager.getInstance().getPrintStartTime());
            return;
        }
        cancelAllAnim();
        ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).btnPrint, UiPosIndexEnum.PRINT_BTN_BACK);
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.setEnabled(false);
        setGroupFrameModifyVisible(false);
        setGroupPrintCountVisible(false);
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPayment.setVisibility(0);
        startCountdown(SpManager.getInstance().getPaymentTime());
    }

    private void setGroupFrameModifyVisible(boolean z) {
        ((FragmentPosterPreprintBinding) this.mViewBinding).fragmentSticker.setVisibility(z ? 0 : 8);
        ((FragmentPosterPreprintBinding) this.mViewBinding).btnSign.setVisibility(z ? 0 : 8);
    }

    private void setGroupPrintCountVisible(boolean z) {
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintCountText.setVisibility(z ? 0 : 8);
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivSub.setVisibility(z ? 0 : 8);
        ((FragmentPosterPreprintBinding) this.mViewBinding).tvPrintCount.setVisibility(z ? 0 : 8);
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivAdd.setVisibility(z ? 0 : 8);
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrint.setVisibility(z ? 0 : 8);
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrint.setVisibility(z ? 0 : 8);
        if (z) {
            return;
        }
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrintSelected.setVisibility(8);
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrintSelected.setVisibility(8);
    }

    private void setImgToFrame() {
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintFrame.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1138x50206419();
            }
        });
    }

    void m1138x50206419() {
        int i;
        int i2;
        BitmapFactory.Options bitmapOptions = BitmapUtil.getBitmapOptions(requireContext(), this.frameInfo.getFramePath());
        if (bitmapOptions == null) {
            return;
        }
        int i3 = bitmapOptions.outWidth;
        int i4 = bitmapOptions.outHeight;
        int width = ((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintFrame.getWidth();
        int height = ((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintFrame.getHeight();
        float f = width;
        float f2 = height;
        float f3 = i3;
        float f4 = i4;
        float f5 = f / f3;
        float f6 = f2 / f4;
        if (f3 / f4 > f / f2) {
            i2 = ((int) (f2 - (f4 * f5))) / 2;
            i = 0;
        } else {
            i = ((int) (f - (f3 * f6))) / 2;
            i2 = 0;
        }
        setLimitSize(height, width, i, i2);
        FragmentUtil.showFragment(((FragmentPosterPreprintBinding) this.mViewBinding).fragmentSticker.getId(), requireActivity(), this.stickerFragment, false);
        ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).ivColorPrint, this.frameInfo.getFramePath());
        ViewUtil.setImageDrawable(((FragmentPosterPreprintBinding) this.mViewBinding).ivBlackPrint, this.frameInfo.getFramePath());
    }

    private void setLimitSize(int i, int i2, int i3, int i4) {
        ViewGroup.LayoutParams layoutParams = ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.getLayoutParams();
        int i5 = i2 - (i3 * 2);
        layoutParams.width = i5;
        int i6 = i - (i4 * 2);
        layoutParams.height = i6;
        layoutParams2.width = i5;
        layoutParams2.height = i6;
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.setLayoutParams(layoutParams);
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setLayoutParams(layoutParams2);
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutFrame.postDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1139x25eb0f3b();
            }
        }, 100L);
    }

    void m1139x25eb0f3b() {
        ((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintFrame.bringToFront();
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.bringToFront();
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.bringToFront();
    }

    public void addSticker(String str) {
        int memoryClass = ((ActivityManager) requireContext().getSystemService(TimeoutPredicate.activity)).getMemoryClass() * 512;
        int size = ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.getStickerList().size();
        if (this.stickerMemoryOccupy > memoryClass || size > 15) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.sticker_limit));
            return;
        }
        Bitmap localBitmap = BitmapUtil.getLocalBitmap(requireContext(), str);
        if (localBitmap == null) {
            return;
        }
        this.stickerMemoryOccupy += localBitmap.getAllocationByteCount() / 1024;
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.addSticker(localBitmap, false);
    }

    private void startCountdown(int i) {
        ((PosterPrePrintVModel) this.mViewModel).startCountdown(i);
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutCountdown.setCount(i);
    }

    private void showPrintCount() {
        ((FragmentPosterPreprintBinding) this.mViewBinding).tvPrintCount.setText(String.valueOf(this.frameInfo.isNeedCut() ? this.printCount * 2 : this.printCount));
    }

    public void signPaintCommand(SignCommand signCommand) {
        if (signCommand.operate == 3) {
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setMode(PaletteView.Mode.DRAW);
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setPenColor(signCommand.paintColor);
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setPenRawSize(signCommand.paintSize);
        } else {
            if (signCommand.operate == 1) {
                ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.undo();
                return;
            }
            if (signCommand.operate == 2) {
                ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.redo();
            } else if (signCommand.operate == 4) {
                ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setMode(PaletteView.Mode.ERASER);
            } else if (signCommand.operate == 5) {
                ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.clear();
            }
        }
    }

    private void preToPrint() {
        startCountdown(SpManager.getInstance().getPrintStartTime());
        SoundHelper.getInstance().playSoundEffect(3);
        if (((PosterPrePrintVModel) this.mViewModel).getRemainSheets() <= 0) {
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
                    this.f$0.m1137xd363566e(str);
                }
            });
            int iMax = Math.max(0, this.frameInfo.getAddPrice() * (this.printCount - this.frameInfo.getPrintCount()));
            setPrintStepView(2);
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPayment.setPriceStr(OrderManager.getShowPriceStr(iMax));
            ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPayment.setBalance(OrderManager.mBalance);
            return;
        }
        print(this.frameInfo.getPrintCount());
    }

    void m1137xd363566e(final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1136x3f24e6cf(str);
            }
        });
    }

    void m1136x3f24e6cf(String str) {
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPayment.setQrCodeStr(str);
        this.mainVModel.startCheckPrintOrderTask();
        startCountdown(SpManager.getInstance().getPaymentTime());
    }

    private void print(int i) {
        ((PosterPrePrintVModel) this.mViewModel).cancelCountdown();
        this.printCount = i;
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.setEnabled(false);
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setEnabled(false);
        ((FragmentPosterPreprintBinding) this.mViewBinding).btnPrint.setEnabled(false);
        showLoadAnim(getString(C1910R.string.saving));
        ((PosterPrePrintVModel) this.mViewModel).saveFinalPhoto(this.frameInfo, ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.getWidth(), ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.getHeight(), ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.getStickerList(), ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.getDrawBitmap(), i, App.mPosterBlackPrint);
    }

    private void cancelAllAnim() {
        stopBtnSignAnim();
    }

    public void startBtnSignAnim() {
        if (this.animatorSet == null) {
            AnimatorSet breathAnim = AnimUtil.getBreathAnim(((FragmentPosterPreprintBinding) this.mViewBinding).btnSign, 2000);
            this.animatorSet = breathAnim;
            breathAnim.start();
        }
    }

    private void stopBtnSignAnim() {
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet == null) {
            return;
        }
        animatorSet.cancel();
    }

    private void setFrameBlack(boolean z) {
        App.mPosterBlackPrint = z;
        if (z) {
            ViewUtil.setImageViewGray(((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintFrame, true, 0.0f);
        } else {
            ViewUtil.setImageViewGray(((FragmentPosterPreprintBinding) this.mViewBinding).ivPrintFrame, false, 1.0f);
        }
        Iterator<StickerView> it = ((FragmentPosterPreprintBinding) this.mViewBinding).layoutSticker.getStickerList().iterator();
        while (it.hasNext()) {
            it.next().setImageGray(z);
        }
        ((FragmentPosterPreprintBinding) this.mViewBinding).layoutPalette.setGray(z);
    }
}
