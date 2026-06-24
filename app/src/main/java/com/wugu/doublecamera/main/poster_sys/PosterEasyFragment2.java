package com.wugu.doublecamera.main.poster_sys;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsoluteLayout;
import androidx.core.text.HtmlCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.faceunity.core.model.facebeauty.FaceBeautyFilterEnum;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.p020hp.jipp.model.TimeoutPredicate;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.PeRetakeAdapter;
import com.wugu.doublecamera.adapter.PosterEasyFrameAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.PhotoItem;
import com.wugu.doublecamera.bean.dto.RedeemCodeDto;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentPosterEasy2Binding;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.dialog.NumInputDialog;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.ICaptureListener;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.HomeFragment$$ExternalSyntheticLambda4;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.poster_sys.p024vm.PosterEasyVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import com.wugu.facebeauty.FaceBeautyMain;
import com.wugu.facebeauty.bean.BeautifyItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.internal.p040ws.RealWebSocket;
import org.apache.log4j.spi.Configurator;

public class PosterEasyFragment2 extends ABaseFragment<FragmentPosterEasy2Binding, PosterEasyVModel> implements ICaptureListener {
    private AnimatorSet animBtnEnter;
    private TranslateAnimation animDown;
    private ValueAnimator animPayPrintQrCodeLoading;
    private ValueAnimator animPayQrCodeLoading;
    private TranslateAnimation animPhoto;
    private ValueAnimator animPhotoQrCodeLoading;
    private BeautifyItem beautyItem;
    private int currentFrameH;
    private int currentFrameW;
    private String finalPhotoPath;
    private FrameInfo frameInfo;
    private Timer frameNextTimer;
    private ObjectAnimator leftArrowAnim;
    private MainVModel mainVModel;
    private Timer payTimer;
    private int retakeCount;
    private List<Bitmap> retakeList;
    private int retakePictureSelectedIndex;
    private ObjectAnimator rightArrowAnim;
    private final String TAG = "PosterEasyFragment";
    private final PosterEasyFrameAdapter frameAdapter = new PosterEasyFrameAdapter();
    private int currentFrameIndex = -1;
    private final PeRetakeAdapter peRetakeAdapter = new PeRetakeAdapter();
    private boolean isFirstStart = true;
    private int printCount = 1;
    private int payStep = 6;
    private boolean IS_SHOW_RE_CODE = false;
    private boolean IS_SHOW_MT_CODE = false;
    private boolean IS_SHOW_DY_CODE = false;
    private boolean IS_SHOW_UPLOAD_PRINT = false;
    private boolean IS_QRCODE_PAY = true;
    private boolean IS_SHOW_RETAKE_VIEW = true;

    static boolean lambda$readyToPhoto$34(View view, MotionEvent motionEvent) {
        return true;
    }

    static boolean lambda$showPayView$35(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        LoggerUtil.m1181d("PosterEasyFragment", "PosterEasyFragment onViewCreated");
        initView();
        initData();
        initObserver();
        initListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        LoggerUtil.m1181d("PosterEasyFragment", "PosterEasyFragment onPause");
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        LoggerUtil.m1181d("PosterEasyFragment", "onHiddenChanged " + z + ", frameInfo " + this.frameInfo);
        if (z) {
            return;
        }
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.reopenCamera();
        FrameInfo frameInfo = this.frameInfo;
        if (frameInfo != null) {
            OrderManager.setOrderFrameInfo(frameInfo.getFrameKey(), this.frameInfo.getPrice(), this.frameInfo.getFrameType());
        }
    }

    private void initView() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.setAdapter(this.frameAdapter);
        ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.setOrientation(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).rvRetake.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentPosterEasy2Binding) this.mViewBinding).rvRetake.setAdapter(this.peRetakeAdapter);
        ((FragmentPosterEasy2Binding) this.mViewBinding).rvRetake.setItemAnimator(null);
        if (!App.isNonCamera) {
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1087x537e07ee();
                }
            }, 7000L);
        }
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.updateDeviceStatus();
            }
        }, 8000L);
    }

    void m1087x537e07ee() {
        this.beautyItem = ((PosterEasyVModel) this.mViewModel).getDefaultBeauty();
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.initCamera(requireActivity(), this.beautyItem, 0, this);
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setCutPhoto(true);
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setShowPreviewAnim(false);
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setVisibility(0);
        this.currentFrameIndex = -1;
        updateUi();
        startHomeAnim();
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1086x42c83b2d();
            }
        }, 500L);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark.setVisibility(0);
    }

    void m1086x42c83b2d() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupHomeView.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowLeft.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowRight.setVisibility(0);
        frameChanged(0);
    }

    private void initData() {
        ((PosterEasyVModel) this.mViewModel).queryFrameList(0);
        ((PosterEasyVModel) this.mViewModel).initUiMap();
        int retakeCount = SpManager.getInstance().getRetakeCount();
        this.retakeCount = retakeCount;
        this.IS_SHOW_RETAKE_VIEW = retakeCount > 0;
        String paymentMethod = SpManager.getInstance().getPaymentMethod();
        this.IS_QRCODE_PAY = paymentMethod.contains(String.valueOf(1)) || paymentMethod.contains(String.valueOf(2));
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1058xafddc5b6();
            }
        });
    }

    void m1058xafddc5b6() {
        ThreadClock.sleep(20000L);
        LoggerUtil.m1181d("PosterEasyFragment", "preInitBeauty " + App.mIsInitBeautySuccess);
        if (App.mIsInitBeautySuccess != 1) {
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1057x9f27f8f5();
                }
            });
        }
    }

    void m1057x9f27f8f5() {
        FaceBeautyMain.registerFURender(requireContext(), new FaceBeautyMain.RegCallback() {
            @Override
            public final void onResult(boolean z) {
                PosterEasyFragment2.lambda$initData$2(z);
            }
        });
    }

    static void lambda$initData$2(boolean z) {
        if (z && App.mIsInitBeautySuccess == 0) {
            App.mIsInitBeautySuccess = 1;
        } else {
            if (z) {
                return;
            }
            App.mIsInitBeautySuccess = 2;
        }
    }

    private void initObserver() {
        ((PosterEasyVModel) this.mViewModel).getFrameListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1081xee494d23((List) obj);
            }
        });
        ((PosterEasyVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1082xfeff19e4((Integer) obj);
            }
        });
        ((PosterEasyVModel) this.mViewModel).getPhotoQrcodeLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1083xfb4e6a5((String) obj);
            }
        });
        ((PosterEasyVModel) this.mViewModel).getPrintOrderPayOkLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1084x206ab366((String) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1085x31208027((Integer) obj);
            }
        });
        ((PosterEasyVModel) this.mViewModel).getFinalPhotoPathLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.savePhotoFinish((String) obj);
            }
        });
        this.mainVModel.redeemCodeInfoLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1078x2b621d2f((RedeemCodeDto) obj);
            }
        });
        this.mainVModel.getRemoteControlLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1079x3c17e9f0((Integer) obj);
            }
        });
        this.mainVModel.digitalCameraResetLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1080x4ccdb6b1((Integer) obj);
            }
        });
    }

    void m1081xee494d23(List list) {
        this.frameAdapter.submitList(list);
        if (this.isFirstStart) {
            this.isFirstStart = false;
        } else {
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.reloadFrameList();
                }
            }, 500L);
        }
    }

    void m1082xfeff19e4(Integer num) {
        if (this.payStep == 4 && num.intValue() == 0) {
            LoggerUtil.m1181d("PosterEasyFragment", "countDown 0, finishBackHome");
            finishBackHome();
            return;
        }
        int i = this.payStep;
        if (i == 2) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvCountdown.setText((String) null);
            ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setCountAndStep(num.intValue(), 2);
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvPhotoCountdown.setText(String.valueOf(num));
            if (num.intValue() < 1) {
                ((FragmentPosterEasy2Binding) this.mViewBinding).tvPhotoCountdown.setText((String) null);
                startPhotoClickAnim();
                return;
            }
            return;
        }
        if (i == 0 || i == 1) {
            if (num.intValue() == 0) {
                ((FragmentPosterEasy2Binding) this.mViewBinding).tvCountdown.setText((String) null);
                if (this.payStep == 0) {
                    LoggerUtil.m1181d("PosterEasyFragment", "countDown 0, showHomeView");
                    createFrameNextTimer();
                    showHomeView();
                    return;
                } else {
                    LoggerUtil.m1181d("PosterEasyFragment", "countDown 0, default printCount 1");
                    printAddNumSelectedView(1);
                    this.printCount = 1;
                    showPrintingView(false);
                    return;
                }
            }
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
            return;
        }
        if (i == 3 && num.intValue() == 0) {
            LoggerUtil.m1181d("PosterEasyFragment", "countDown 0, confirm");
            confirm();
        } else {
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        }
    }

    void m1083xfb4e6a5(String str) {
        if (App.mIsIdle) {
            return;
        }
        cancelQrCodeAnim();
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoQrcode.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 600, null));
    }

    void m1084x206ab366(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        showPrintingView(false);
    }

    void m1085x31208027(Integer num) {
        LoggerUtil.m1181d("PosterEasyFragment", "mainVModel.getPayStepLD " + num);
        if (num.intValue() == 3) {
            int i = this.payStep;
            if (i == 0) {
                readyToPhoto();
                return;
            }
            if (i == 1) {
                showPrintingView(false);
                return;
            }
            if (App.mIsIdle && OrderManager.isPhotoNotOnPayment()) {
                String frameKey = OrderManager.getFrameKey();
                int i2 = 0;
                while (true) {
                    if (i2 >= this.frameAdapter.getItemCount()) {
                        i2 = -1;
                        break;
                    }
                    FrameInfo item = this.frameAdapter.getItem(i2);
                    if (item != null && frameKey.equals(item.getFrameKey())) {
                        break;
                    } else {
                        i2++;
                    }
                }
                LoggerUtil.m1181d("PosterEasyFragment", "isPhotoNotOnPayment " + i2 + " " + this.currentFrameIndex);
                if (i2 >= 0 && this.currentFrameIndex != i2) {
                    ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.setCurrentItem(i2, false);
                    AppUtil.runOnUIDelayed(new Runnable() {
                        @Override
                        public final void run() {
                            this.f$0.readyToPhoto();
                        }
                    }, 1000L);
                    return;
                } else {
                    readyToPhoto();
                    return;
                }
            }
            return;
        }
        if (num.intValue() == 5) {
            int i3 = this.payStep;
            if (i3 == 0) {
                ((FragmentPosterEasy2Binding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
            } else if (i3 == 1) {
                ((FragmentPosterEasy2Binding) this.mViewBinding).tvBalance2.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.mBalance)), 0));
            }
            ((PosterEasyVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPaymentTime());
            return;
        }
        if (num.intValue() == 6) {
            showLoadAnim(getString(C1910R.string.query_ing));
        }
    }

    void m1078x2b621d2f(RedeemCodeDto redeemCodeDto) {
        dismissLoadAnim();
    }

    void m1079x3c17e9f0(Integer num) {
        if (num.intValue() == 1) {
            updateDeviceStatus();
        } else if (num.intValue() == 2) {
            ((PosterEasyVModel) this.mViewModel).queryFrameList(2);
        } else if (num.intValue() == 4) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setBeautyItem(((PosterEasyVModel) this.mViewModel).updateBeauty());
        }
    }

    void m1080x4ccdb6b1(Integer num) {
        updateDeviceStatus();
    }

    private void initListener() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeReCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1059x23987e54(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivMtReCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1060x344e4b15(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivDyReCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1061x450417d6(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivUploadPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1062x55b9e497(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnCodeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1063x666fb158(view);
            }
        });
        this.peRetakeAdapter.addOnItemChildClickListener(C1910R.id.layout_root, new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1064x77257e19(baseQuickAdapter, view, i);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnStartPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1065x87db4ada(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1066xf77ae370(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnPayCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1067x830b031(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnPrintPayCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1068x18e67cf2(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnRetake.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1069x299c49b3(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1070x3a521674(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1071x4b07e335(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1072x5bbdaff6(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1073x6c737cb7(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1074x7d294978(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivNum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1075x8ddf1639(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivNum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1076xfd7eaecf(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1077xe347b90(view);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.registerOnPageChangeCallback(new C19591());
    }

    void m1059x23987e54(View view) {
        showKeyBoard();
    }

    void m1060x344e4b15(View view) {
        showMtdyView(true);
    }

    void m1061x450417d6(View view) {
        showMtdyView(false);
    }

    void m1062x55b9e497(View view) {
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.stopCamera();
        ((PosterEasyVModel) this.mViewModel).cancelCountdown();
        this.mainVModel.fragmentLD.postValue(29);
    }

    void m1063x666fb158(View view) {
        showHomeView();
        if (this.IS_SHOW_RE_CODE) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeReCode.setVisibility(0);
        }
        if (this.IS_SHOW_MT_CODE) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivMtReCode.setVisibility(0);
        }
        if (this.IS_SHOW_DY_CODE) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivDyReCode.setVisibility(0);
        }
        if (this.IS_SHOW_UPLOAD_PRINT) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivUploadPrint.setVisibility(0);
        }
    }

    void m1064x77257e19(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        selectRetakePicture(i);
    }

    void m1065x87db4ada(View view) {
        if (this.frameInfo == null || checkPrintCountZero()) {
            return;
        }
        cancelHomeAnim();
        cancelFrameTimer();
        if (OrderManager.isPhotoWithoutPay()) {
            readyToPhoto();
        } else {
            showPayView();
        }
    }

    void m1066xf77ae370(View view) {
        finishBackHome();
    }

    void m1067x830b031(View view) {
        LoggerUtil.m1181d("PosterEasyFragment", "btnPayCancel ");
        createFrameNextTimer();
        if (!this.IS_QRCODE_PAY) {
            OrderManager.disablePayDevice();
        }
        showHomeView();
        this.mainVModel.stopCheckOrderTask();
    }

    void m1068x18e67cf2(View view) {
        LoggerUtil.m1181d("PosterEasyFragment", "btnPrintPayCancel ");
        OrderManager.cancelPrintOrder();
        showAddPrintView();
        this.printCount = 1;
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvPrintPrice.setText((String) null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintPayView.setVisibility(8);
        cancelQrCodeAnim();
        this.mainVModel.stopCheckOrderTask();
    }

    void m1069x299c49b3(View view) {
        retake();
    }

    void m1070x3a521674(View view) {
        confirm();
    }

    void m1071x4b07e335(View view) {
        clickPrintCount(view, 1);
    }

    void m1072x5bbdaff6(View view) {
        clickPrintCount(view, 2);
    }

    void m1073x6c737cb7(View view) {
        clickPrintCount(view, 3);
    }

    void m1074x7d294978(View view) {
        clickPrintCount(view, 4);
    }

    void m1075x8ddf1639(View view) {
        clickPrintCount(view, 5);
    }

    void m1076xfd7eaecf(View view) {
        clickPrintCount(view, 6);
    }

    void m1077xe347b90(View view) {
        int printCount = this.printCount - this.frameInfo.getPrintCount();
        LoggerUtil.m1181d("PosterEasyFragment", "btnPrint " + this.printCount + " " + this.frameInfo.getPrintCount() + " " + this.frameInfo.getAddPrice());
        if (OrderManager.checkAddPrintBalance(this.frameInfo.getAddPrice(), printCount)) {
            showPrintingView(true);
        } else {
            showAddPrintPayView();
        }
    }

    class C19591 extends ViewPager2.OnPageChangeCallback {
        C19591() {
        }

        @Override
        public void onPageSelected(int i) {
            super.onPageSelected(i);
            PosterEasyFragment2.this.frameChanged(i);
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1102x3adb5dae();
                }
            }, 500L);
        }

        void m1102x3adb5dae() {
            ((FragmentPosterEasy2Binding) PosterEasyFragment2.this.mViewBinding).vpFrame.setBackground(null);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            super.onPageScrollStateChanged(i);
            if (i == 0) {
                AppUtil.runOnUIDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m1101x78811364();
                    }
                }, 300L);
            } else if (i == 1 || i == 2) {
                ((FragmentPosterEasy2Binding) PosterEasyFragment2.this.mViewBinding).vpFrame.setBackgroundColor(Color.parseColor("#E3D4CB"));
            }
        }

        void m1101x78811364() {
            SoundHelper.getInstance().playSoundEffect(14);
            ((FragmentPosterEasy2Binding) PosterEasyFragment2.this.mViewBinding).vpFrame.setBackground(null);
        }
    }

    private void updateUi() {
        ViewUtil.setViewGroupBg(((FragmentPosterEasy2Binding) this.mViewBinding).getRoot(), UiPosIndexEnum.PE_BG_ROOT);
        this.IS_SHOW_RE_CODE = ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeReCode, UiPosIndexEnum.PE_BTN_RECODE, C1910R.mipmap.ic_poster_code);
        this.IS_SHOW_MT_CODE = ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivMtReCode, UiPosIndexEnum.PE_BTN_MT_CODE, C1910R.mipmap.ic_poster_mt, false);
        this.IS_SHOW_DY_CODE = ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivDyReCode, UiPosIndexEnum.PE_BTN_DY_CODE, C1910R.mipmap.ic_poster_dy, false);
        this.IS_SHOW_UPLOAD_PRINT = ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivUploadPrint, UiPosIndexEnum.PE_IC_UPLOAD_PRINT, C1910R.mipmap.ic_poster_up, false);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeReCode.setVisibility(this.IS_SHOW_RE_CODE ? 0 : 8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivMtReCode.setVisibility(this.IS_SHOW_MT_CODE ? 0 : 8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivDyReCode.setVisibility(this.IS_SHOW_DY_CODE ? 0 : 8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivUploadPrint.setVisibility(this.IS_SHOW_UPLOAD_PRINT ? 0 : 8);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeTip, UiPosIndexEnum.PE_IC_HOME_TIP, C1910R.mipmap.ic_poster_home_tip);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeBg, UiPosIndexEnum.PE_BG_HOME, C1910R.mipmap.ic_poster_home_bg);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivBaseBg, UiPosIndexEnum.PE_BG_HOME, C1910R.mipmap.ic_poster_home_bg);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).btnStartPhoto, UiPosIndexEnum.PE_BTN_HOME_START, C1910R.mipmap.ic_poster_btn_home);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowLeft, UiPosIndexEnum.PE_HOME_ARROW_L, C1910R.mipmap.ic_poster_left);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowRight, UiPosIndexEnum.PE_HOME_ARROW_R, C1910R.mipmap.ic_poster_right);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivPayBg, UiPosIndexEnum.PE_BG_PAY, C1910R.mipmap.ic_poster_pay_tips);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivRetakeBg, UiPosIndexEnum.PE_BG_RETAKE, C1910R.mipmap.ic_poster_home_bg);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).btnPayCancel, UiPosIndexEnum.PE_BTN_BACK_HOME, C1910R.mipmap.ic_poster_back);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintBg, UiPosIndexEnum.PE_BG_PRINT, C1910R.mipmap.ic_poster_add_print_tips);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).btnPrint, UiPosIndexEnum.PE_BTN_PRINT, C1910R.mipmap.ic_poster_print);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintPayBg, UiPosIndexEnum.PE_BG_PRINT_PAY, C1910R.mipmap.ic_poster_print_pay_tips);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).btnPrintPayCancel, UiPosIndexEnum.PE_BTN_RETURN, C1910R.mipmap.ic_poster_back);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoQrcodeBg, UiPosIndexEnum.PE_BG_PHOTO_QRCODE, C1910R.mipmap.ic_poster_qr_tips);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).btnBackHome, UiPosIndexEnum.PE_BTN_BACK_HOME, C1910R.mipmap.ic_poster_back_home);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintDown, UiPosIndexEnum.PE_IC_PRINTING_ARROW, C1910R.mipmap.ic_poster_print_down);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoFreeze, UiPosIndexEnum.PE_IC_KACHA, C1910R.mipmap.ic_poster_photo_freeze);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark, UiPosIndexEnum.PE_WATER_MARK, C1910R.mipmap.ic_pe_water_mark);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).btnRetake, UiPosIndexEnum.PE_BTN_RETAKE, C1910R.mipmap.ic_poster_retake);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).btnConfirm, UiPosIndexEnum.PE_BTN_CONFIRM, C1910R.mipmap.ic_poster_confirm);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum1, UiPosIndexEnum.PE_BTN_NUM1, C1910R.mipmap.ic_poster_print_1);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum2, UiPosIndexEnum.PE_BTN_NUM2, C1910R.mipmap.ic_poster_print_2);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum3, UiPosIndexEnum.PE_BTN_NUM3, C1910R.mipmap.ic_poster_print_3);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum4, UiPosIndexEnum.PE_BTN_NUM4, C1910R.mipmap.ic_poster_print_4);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum5, UiPosIndexEnum.PE_BTN_NUM5, C1910R.mipmap.ic_poster_print_5);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum6, UiPosIndexEnum.PE_BTN_NUM6, C1910R.mipmap.ic_poster_print_6);
    }

    @Override
    public void finishCapture(boolean z) {
        LoggerUtil.m1181d("PosterEasyFragment", "finishCapture " + z);
        if (z) {
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1056xf8d4fefc();
                }
            }, 200L);
        } else {
            ToastHelper.getInstance().showToast(getString(C1910R.string.capture_error));
            ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.cameraPreview();
        }
    }

    void m1056xf8d4fefc() {
        Bitmap ivCameraBitmap = ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.getIvCameraBitmap();
        if (ivCameraBitmap == null) {
            return;
        }
        if (this.retakeList == null) {
            this.retakeList = new ArrayList();
        }
        this.retakeList.add(ivCameraBitmap);
        this.peRetakeAdapter.submitList(this.retakeList);
        int itemCount = this.peRetakeAdapter.getItemCount() - 1;
        this.retakePictureSelectedIndex = itemCount;
        this.peRetakeAdapter.setSelectedPosition(itemCount, true);
        if (this.IS_SHOW_RETAKE_VIEW) {
            showRetakeView();
        } else {
            confirm();
        }
    }

    @Override
    public void recordEnd(String str) {
        if (((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.isSystemCamera()) {
            this.mainVModel.saveVideo(str, this.currentFrameW, this.currentFrameH);
        } else {
            this.mainVModel.compoundAndSaveVideo(str, AppConfig.TEMP_DIR + "/ffmpeg" + AppUtil.getSystemTime("yyyyMMdd_HHmmss") + ".mp4");
        }
    }

    private void selectRetakePicture(int i) {
        if (this.retakePictureSelectedIndex == i) {
            return;
        }
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setIvCameraBitmapNotC(this.peRetakeAdapter.getItem(i));
        this.peRetakeAdapter.setSelectedPosition(i, true);
        this.retakePictureSelectedIndex = i;
    }

    private void startHomeAnim() {
        if (this.animBtnEnter == null) {
            this.animBtnEnter = AnimUtil.getBreathAnim(((FragmentPosterEasy2Binding) this.mViewBinding).btnStartPhoto, 2000);
        }
        if (this.leftArrowAnim == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowLeft, "translationX", 0.0f, -20.0f, 0.0f);
            this.leftArrowAnim = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(1000L);
            this.leftArrowAnim.setRepeatCount(-1);
            this.leftArrowAnim.setRepeatMode(2);
        }
        if (this.rightArrowAnim == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowRight, "translationX", 0.0f, 20.0f, 0.0f);
            this.rightArrowAnim = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(1000L);
            this.rightArrowAnim.setRepeatCount(-1);
            this.rightArrowAnim.setRepeatMode(2);
        }
        this.leftArrowAnim.start();
        this.rightArrowAnim.start();
        this.animBtnEnter.start();
    }

    private void startPhotoClickAnim() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoFreeze.setVisibility(0);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoFreeze, "alpha", 0.0f, 0.8f, 0.0f);
        objectAnimatorOfFloat.setDuration(600L);
        objectAnimatorOfFloat.start();
    }

    private void cancelHomeAnim() {
        AnimatorSet animatorSet = this.animBtnEnter;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ObjectAnimator objectAnimator = this.leftArrowAnim;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator objectAnimator2 = this.rightArrowAnim;
        if (objectAnimator2 != null) {
            objectAnimator2.cancel();
        }
    }

    public void frameChanged(int i) {
        LoggerUtil.m1181d("PosterEasyFragment", "frameChanged " + i + " " + this.currentFrameIndex);
        if (this.currentFrameIndex == i) {
            return;
        }
        this.currentFrameIndex = i;
        FrameInfo item = this.frameAdapter.getItem(i);
        if (item == null) {
            return;
        }
        this.frameInfo = item;
        OrderManager.setOrderFrameInfo(item.getFrameKey(), item.getPrice(), item.getFrameType());
        BitmapFactory.Options bitmapOptions = BitmapUtil.getBitmapOptions(item.getFramePath());
        if (bitmapOptions == null) {
            return;
        }
        boolean z = false;
        FramePhotoInfo framePhotoInfo = item.getPhotoInfoList().get(0);
        if (framePhotoInfo == null) {
            return;
        }
        String printerColorParam = item.getPrinterColorParam();
        if (TextUtils.isEmpty(printerColorParam) || (!printerColorParam.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) && !printerColorParam.equals(ExifInterface.GPS_MEASUREMENT_2D))) {
            z = true;
        }
        setFilterBlack(z);
        if (SpManager.getInstance().getIsPeShowDate()) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvDate.setText(AppUtil.getCountryTimeWeekStr(requireContext()));
        } else {
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvDate.setText("");
        }
        setLayoutCameraUi(framePhotoInfo, bitmapOptions.outWidth, bitmapOptions.outHeight);
        setCurrentPhotoInfo(framePhotoInfo, bitmapOptions.outWidth, bitmapOptions.outHeight);
        createFrameNextTimer();
    }

    private void setFilterBlack(boolean z) {
        BeautifyItem beautifyItem = this.beautyItem;
        if (beautifyItem == null) {
            return;
        }
        beautifyItem.filterParams.filterName = z ? FaceBeautyFilterEnum.HEIBAI_1 : "origin";
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setBeautyItem(this.beautyItem);
    }

    private void setLayoutCameraUi(FramePhotoInfo framePhotoInfo, int i, int i2) {
        AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.getLayoutParams();
        float f = i;
        float width = ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.getWidth() / f;
        int width2 = (int) (framePhotoInfo.getWidth() * width);
        int height = (int) (framePhotoInfo.getHeight() * width);
        if (f / i2 < 0.5625f) {
            layoutParams.height = height + 8;
            layoutParams.width = ((int) (height * 0.5625f)) + 8;
            layoutParams.y = (int) ((framePhotoInfo.getLocationY() * width) + 2);
            layoutParams.x = ((int) ((framePhotoInfo.getLocationX() * width) - ((layoutParams.width - width2) / 2))) + 2;
        } else {
            layoutParams.width = width2 + 8;
            layoutParams.height = ((int) (width2 / 0.5625f)) + 8;
            layoutParams.x = (int) ((framePhotoInfo.getLocationX() * width) + 2);
            layoutParams.y = ((int) ((framePhotoInfo.getLocationY() * width) - ((layoutParams.height - height) / 2))) + 2;
        }
        AbsoluteLayout.LayoutParams layoutParams2 = (AbsoluteLayout.LayoutParams) ((FragmentPosterEasy2Binding) this.mViewBinding).tvPhotoCountdown.getLayoutParams();
        AbsoluteLayout.LayoutParams layoutParams3 = (AbsoluteLayout.LayoutParams) ((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark.getLayoutParams();
        layoutParams2.x = (layoutParams.x + (layoutParams.width / 2)) - 100;
        layoutParams2.y = (layoutParams.y + (layoutParams.height / 2)) - 200;
        if (((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark.getWidth() <= 0 || ((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark.getWidth() >= 1080) {
            layoutParams3.x = 120;
        } else {
            layoutParams3.x = (1080 - ((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark.getWidth()) / 2;
        }
        layoutParams3.y = (layoutParams.y + (layoutParams.height / 2)) - 50;
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvPhotoCountdown.setLayoutParams(layoutParams2);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoFreeze.setLayoutParams(layoutParams2);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark.setLayoutParams(layoutParams3);
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setLayoutParams(layoutParams);
    }

    private void setCurrentPhotoInfo(FramePhotoInfo framePhotoInfo, final int i, final int i2) {
        final PhotoItem photoItem = new PhotoItem();
        photoItem.setHeight(framePhotoInfo.getHeight());
        photoItem.setWidth(framePhotoInfo.getWidth());
        photoItem.setDegrees(framePhotoInfo.getDegrees());
        photoItem.setX(framePhotoInfo.getLocationX());
        photoItem.setY(framePhotoInfo.getLocationY());
        photoItem.setPhotoName(Configurator.NULL);
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1088x9b00bbe7(photoItem, i, i2);
            }
        });
    }

    void m1088x9b00bbe7(PhotoItem photoItem, int i, int i2) {
        if (photoItem.getX() + photoItem.getWidth() > i || photoItem.getY() + photoItem.getHeight() > i2 || photoItem.getWidth() <= 0 || photoItem.getHeight() <= 0) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setOriginWH();
            return;
        }
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setCurrentPhotoInfo(photoItem, null);
        this.currentFrameW = photoItem.getWidth();
        this.currentFrameH = photoItem.getHeight();
    }

    public void readyToPhoto() {
        LoggerUtil.m1181d("PosterEasyFragment", "readyToPhoto");
        App.mIsIdle = false;
        OrderManager.mRedeemCodeDto = null;
        SoundHelper.getInstance().stopPlayBgm();
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setImageDrawable(null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return PosterEasyFragment2.lambda$readyToPhoto$34(view, motionEvent);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnPayCancel.setEnabled(true);
        this.payStep = 2;
        cancelHomeAnim();
        cancelPayTimer();
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupHomeView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowLeft.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowRight.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeReCode.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivMtReCode.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivDyReCode.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivUploadPrint.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPayView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupMtdyTipsView.setVisibility(8);
        ((PosterEasyVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPhotoReadyTime());
        SoundHelper.getInstance().playSoundEffect(10);
        this.mainVModel.stopCheckOrderTask();
    }

    public void savePhotoFinish(String str) {
        LoggerUtil.m1181d("PosterEasyFragment", "savePhotoFinish " + str);
        this.peRetakeAdapter.submitList(null);
        clearTempRetakeList();
        this.finalPhotoPath = str;
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setImageBitmap(BitmapFactory.decodeFile(str));
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvDate.setVisibility(4);
        showAddPrintView();
    }

    private void clickPrintCount(View view, int i) {
        if (this.printCount == i) {
            return;
        }
        AnimUtil.startBtnClickAnim(view);
        SoundHelper.getInstance().playSoundEffect(3);
        printAddNumSelectedView(i);
        this.printCount = i;
        if (i > this.frameInfo.getPrintCount() && this.frameInfo.getAddPrice() > 0) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvPrintPrice.setText(getString(C1910R.string.print_price, StringUtil.getPriceStr(this.frameInfo.getAddPrice() * (this.printCount - this.frameInfo.getPrintCount()))));
        } else {
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvPrintPrice.setText("");
        }
    }

    private void printAddNumSelectedView(int i) {
        LoggerUtil.m1181d("fragment", "printAddNumSelectedView " + i + " " + this.printCount);
        switch (this.printCount) {
            case 1:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum1, UiPosIndexEnum.PE_BTN_NUM1, C1910R.mipmap.ic_poster_print_1);
                break;
            case 2:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum2, UiPosIndexEnum.PE_BTN_NUM2, C1910R.mipmap.ic_poster_print_2);
                break;
            case 3:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum3, UiPosIndexEnum.PE_BTN_NUM3, C1910R.mipmap.ic_poster_print_3);
                break;
            case 4:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum4, UiPosIndexEnum.PE_BTN_NUM4, C1910R.mipmap.ic_poster_print_4);
                break;
            case 5:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum5, UiPosIndexEnum.PE_BTN_NUM5, C1910R.mipmap.ic_poster_print_5);
                break;
            case 6:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum6, UiPosIndexEnum.PE_BTN_NUM6, C1910R.mipmap.ic_poster_print_6);
                break;
        }
        switch (i) {
            case 1:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum1, UiPosIndexEnum.PE_BTN_NUM1_SEL, C1910R.mipmap.ic_poster_print_sel_1);
                break;
            case 2:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum2, UiPosIndexEnum.PE_BTN_NUM2_SEL, C1910R.mipmap.ic_poster_print_sel_2);
                break;
            case 3:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum3, UiPosIndexEnum.PE_BTN_NUM3_SEL, C1910R.mipmap.ic_poster_print_sel_3);
                break;
            case 4:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum4, UiPosIndexEnum.PE_BTN_NUM4_SEL, C1910R.mipmap.ic_poster_print_sel_4);
                break;
            case 5:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum5, UiPosIndexEnum.PE_BTN_NUM5_SEL, C1910R.mipmap.ic_poster_print_sel_5);
                break;
            case 6:
                ViewUtil.setImageDrawable(((FragmentPosterEasy2Binding) this.mViewBinding).ivNum6, UiPosIndexEnum.PE_BTN_NUM6_SEL, C1910R.mipmap.ic_poster_print_sel_6);
                break;
        }
    }

    private void retake() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.cameraPreview();
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupRetakeView.setVisibility(8);
        readyToPhoto();
        this.retakeCount--;
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvRetakeCount.setText(getString(C1910R.string.remain_count, Integer.valueOf(this.retakeCount)));
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void confirm() {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.main.poster_sys.PosterEasyFragment2.confirm():void");
    }

    private void startPrintAnim() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setVisibility(0);
        int printTime = ((PosterEasyVModel) this.mViewModel).getPrintTime() * this.printCount;
        TranslateAnimation translateAnimation = this.animDown;
        if (translateAnimation != null) {
            translateAnimation.cancel();
            this.animDown = null;
        }
        TranslateAnimation translateAnimation2 = this.animPhoto;
        if (translateAnimation2 != null) {
            translateAnimation2.cancel();
            this.animPhoto = null;
        }
        this.animDown = new TranslateAnimation(0.0f, 0.0f, -1525.0f, 0.0f);
        this.animPhoto = new TranslateAnimation(0.0f, 0.0f, 0.0f, 1525.0f);
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvCountdown.setText((String) null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintingView.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintPayView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setVisibility(4);
        ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.setVisibility(4);
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvDate.setVisibility(4);
        this.animPhoto.setInterpolator(new LinearInterpolator());
        long j = ((long) printTime) * 1000;
        this.animPhoto.setDuration(j);
        this.animPhoto.setFillAfter(true);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.startAnimation(this.animPhoto);
        this.animDown.setInterpolator(new LinearInterpolator());
        this.animDown.setDuration(j);
        this.animDown.setFillAfter(true);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintDown.startAnimation(this.animDown);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintDown.setVisibility(0);
        ((PosterEasyVModel) this.mViewModel).startCountdown(printTime + 20);
    }

    private void finishBackHome() {
        LoggerUtil.m1181d("PosterEasyFragment", "finishBackHome");
        TranslateAnimation translateAnimation = this.animDown;
        if (translateAnimation != null) {
            translateAnimation.cancel();
            this.animDown = null;
        }
        TranslateAnimation translateAnimation2 = this.animPhoto;
        if (translateAnimation2 != null) {
            translateAnimation2.cancel();
            this.animPhoto = null;
        }
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.reopenCamera();
        ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvDate.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvPrintPayPrice.setText("");
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoQrcode.setImageBitmap(null);
        showHomeView();
        OrderManager.clearOrderInfo();
        autoSelectNextFrame();
        App.mIsIdle = true;
        OrderManager.mPaymentMethod = 11;
        if (OrderManager.isExistFailedFile()) {
            ThreadHelper.getInstance().createThread(new HomeFragment$$ExternalSyntheticLambda4());
        }
    }

    private void createFrameNextTimer() {
        long chooseFrameTime = ((long) SpManager.getInstance().getChooseFrameTime()) * 1000;
        Timer timer = this.frameNextTimer;
        if (timer != null) {
            timer.cancel();
            this.frameNextTimer = null;
        }
        Timer timer2 = new Timer();
        this.frameNextTimer = timer2;
        timer2.schedule(new C19602(), chooseFrameTime);
    }

    class C19602 extends TimerTask {
        C19602() {
        }

        @Override
        public void run() {
            PosterEasyFragment2.this.frameNextTimer.cancel();
            PosterEasyFragment2.this.frameNextTimer = null;
            if (App.mIsIdle) {
                AppUtil.runOnUI(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m1103xb3f62b97();
                    }
                });
            }
        }

        void m1103xb3f62b97() {
            PosterEasyFragment2.this.autoSelectNextFrame();
        }
    }

    public void autoSelectNextFrame() {
        int currentItem = ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.getCurrentItem();
        int itemCount = this.frameAdapter.getItemCount();
        LoggerUtil.m1181d("PosterEasyFragment", "autoSelectNextFrame " + currentItem + " " + itemCount);
        if (currentItem == itemCount - 1) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.setCurrentItem(0, false);
        } else {
            ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.setCurrentItem(currentItem + 1, true);
        }
    }

    public void reloadFrameList() {
        if (this.payStep == 0) {
            showHomeView();
        }
        if (this.currentFrameIndex == 0) {
            this.currentFrameIndex = -1;
            frameChanged(0);
        } else {
            this.currentFrameIndex = 0;
            ((FragmentPosterEasy2Binding) this.mViewBinding).vpFrame.setCurrentItem(0, false);
        }
    }

    private void cancelFrameTimer() {
        Timer timer = this.frameNextTimer;
        if (timer != null) {
            timer.cancel();
            this.frameNextTimer = null;
        }
    }

    private void cancelPayTimer() {
        Timer timer = this.payTimer;
        if (timer != null) {
            timer.cancel();
            this.payTimer = null;
        }
    }

    public void showHomeView() {
        LoggerUtil.m1181d("PosterEasyFragment", "showHomeView ");
        printAddNumSelectedView(1);
        this.payStep = 6;
        this.printCount = 1;
        this.retakeCount = SpManager.getInstance().getRetakeCount();
        this.finalPhotoPath = null;
        ((PosterEasyVModel) this.mViewModel).cancelCountdown();
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.clearAnimation();
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintDown.clearAnimation();
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setImageDrawable(null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvCountdown.setText((String) null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setOnTouchListener(null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnRetake.setEnabled(true);
        ViewUtil.setImageViewGray(((FragmentPosterEasy2Binding) this.mViewBinding).btnRetake, false, 1.0f);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintDown.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupHomeView.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowLeft.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowRight.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPayView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintPayView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintingView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupMtdyTipsView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivWaterMark.setVisibility(0);
        if (this.IS_SHOW_RE_CODE) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeReCode.setVisibility(0);
        }
        if (this.IS_SHOW_MT_CODE) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivMtReCode.setVisibility(0);
        }
        if (this.IS_SHOW_DY_CODE) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivDyReCode.setVisibility(0);
        }
        if (this.IS_SHOW_UPLOAD_PRINT) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivUploadPrint.setVisibility(0);
        }
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvPrintPrice.setText((String) null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.cameraPreview();
        ((FragmentPosterEasy2Binding) this.mViewBinding).layoutCamera.setVisibility(0);
        startHomeAnim();
        this.mainVModel.stopCheckOrderTask();
        cancelQrCodeAnim();
        App.mIsIdle = true;
        SoundHelper.getInstance().startPlayBgm();
    }

    private void showPayView() {
        LoggerUtil.m1181d("PosterEasyFragment", "showPayView");
        this.payStep = 0;
        SoundHelper.getInstance().stopPlayBgm();
        startPayTimer();
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupHomeView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowLeft.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivArrowRight.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeReCode.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivMtReCode.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivDyReCode.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivUploadPrint.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPayView.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setImageDrawable(null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhoto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return PosterEasyFragment2.lambda$showPayView$35(view, motionEvent);
            }
        });
        if (this.IS_QRCODE_PAY) {
            OrderManager.mPaymentMethod = 1;
        } else {
            OrderManager.mPaymentMethod = 99;
        }
        OrderManager.createPhotoOrder(OrderManager.mPaymentMethod, false, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m1093x53750a4(str);
            }
        });
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvPayPrice.setText(HtmlCompat.fromHtml(getString(C1910R.string.payment_num, OrderManager.getOrderRealPriceStr()), 0));
        ((PosterEasyVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPaymentTime());
        SoundHelper.getInstance().playSoundEffect(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPayQrcode.setImageDrawable(null);
        if (this.IS_QRCODE_PAY) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvBalance.setText((CharSequence) null);
        } else {
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
        }
    }

    void m1093x53750a4(final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1092xf48183e3(str);
            }
        });
    }

    private void showAddPrintPayView() {
        LoggerUtil.m1181d("PosterEasyFragment", "showAddPrintPayView");
        this.payStep = 1;
        int printCount = this.printCount - this.frameInfo.getPrintCount();
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintPayView.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvPrintPayPrice.setText(HtmlCompat.fromHtml(getString(C1910R.string.payment_num, OrderManager.getShowPriceStr(this.frameInfo.getAddPrice() * printCount)), 0));
        ((PosterEasyVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPaymentTime());
        SoundHelper.getInstance().playSoundEffect(17);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintPayQrcode.setImageDrawable(null);
        if (this.IS_QRCODE_PAY) {
            if (this.animPayPrintQrCodeLoading == null) {
                this.animPayPrintQrCodeLoading = AnimUtil.getRotationAnim1(((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintPayQrcode, 490);
            }
            this.animPayPrintQrCodeLoading.start();
            ((FragmentPosterEasy2Binding) this.mViewBinding).tvBalance2.setText((CharSequence) null);
            OrderManager.createPrintOrder(printCount, this.frameInfo.getAddPrice(), false, new IStringListener() {
                @Override
                public final void setValue(String str) {
                    this.f$0.m1090xa13e4d2(str);
                }
            });
            return;
        }
        OrderManager.createPrintOrder(printCount, this.frameInfo.getAddPrice(), false, null);
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvBalance2.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.mBalance)), 0));
    }

    void m1090xa13e4d2(final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1089xf95e1811(str);
            }
        });
    }

    private void showRetakeView() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupRetakeView.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).tvRetakeCount.setText(getString(C1910R.string.remain_count, Integer.valueOf(this.retakeCount)));
        if (this.retakeCount <= 0) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).btnRetake.setEnabled(false);
            ViewUtil.setImageViewGray(((FragmentPosterEasy2Binding) this.mViewBinding).btnRetake, true, 0.0f);
        } else {
            SoundHelper.getInstance().playSoundEffect(16);
        }
        ((PosterEasyVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPhotoConfirmTime());
        this.payStep = 3;
    }

    private void showAddPrintView() {
        LoggerUtil.m1181d("PosterEasyFragment", "showAddPrintView");
        ((PosterEasyVModel) this.mViewModel).cancelCountdown();
        this.payStep = 1;
        printAddNumSelectedView(1);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupRetakeView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintView.setVisibility(0);
        ((PosterEasyVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPrintStartTime());
        SoundHelper.getInstance().playSoundEffect(11);
    }

    private void showPrintingView(boolean z) {
        this.payStep = 4;
        ((PosterEasyVModel) this.mViewModel).cancelCountdown();
        ActivityManager activityManager = (ActivityManager) requireContext().getSystemService(TimeoutPredicate.activity);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        LoggerUtil.m1181d("PosterEasyFragment", "hhy availableMemory " + ((memoryInfo.availMem / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE));
        if (z) {
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1094x3cced57b();
                }
            }, 2000L);
        } else {
            printerPrint(this.finalPhotoPath, this.printCount, this.frameInfo.getPrinterColorParam());
        }
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintPayView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupPrintingView.setVisibility(0);
        startPrintAnim();
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnBackHome.setEnabled(false);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1095x4d84a23c();
            }
        }, 5000L);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoQrcode.setImageDrawable(null);
        if (this.animPhotoQrCodeLoading == null) {
            this.animPhotoQrCodeLoading = AnimUtil.getRotationAnim1(((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoQrcode, 490);
        }
        this.animPhotoQrCodeLoading.start();
    }

    void m1094x3cced57b() {
        printerPrint(this.finalPhotoPath, this.printCount, this.frameInfo.getPrinterColorParam());
    }

    void m1095x4d84a23c() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnBackHome.setEnabled(true);
    }

    public void m1092xf48183e3(String str) {
        cancelQrCodeAnim();
        LoggerUtil.m1181d("PosterEasyFragment", "showPayQrCode " + this.IS_QRCODE_PAY + ", payStep " + this.payStep + ", " + str);
        if (this.IS_QRCODE_PAY) {
            int i = this.payStep;
            if (i == 0) {
                this.mainVModel.startCheckPhotoOrderTask();
                ((FragmentPosterEasy2Binding) this.mViewBinding).ivPayQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 400, null));
            } else if (i == 1) {
                this.mainVModel.startCheckPrintOrderTask();
                ((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintPayQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 400, null));
            }
        }
    }

    private void startPayTimer() {
        Timer timer = this.payTimer;
        if (timer != null) {
            timer.cancel();
            this.payTimer = null;
        }
        Timer timer2 = new Timer();
        this.payTimer = timer2;
        timer2.schedule(new C19613(), ((long) SpManager.getInstance().getPaymentTime()) * 1000);
    }

    class C19613 extends TimerTask {
        C19613() {
        }

        @Override
        public void run() {
            PosterEasyFragment2.this.payTimer.cancel();
            PosterEasyFragment2.this.payTimer = null;
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1104xb3f62b98();
                }
            });
        }

        void m1104xb3f62b98() {
            PosterEasyFragment2.this.showHomeView();
        }
    }

    private void showKeyBoard() {
        if (checkPrintCountZero()) {
            return;
        }
        if (OrderManager.mRedeemCodeDto != null) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_exist));
        } else {
            SoundHelper.getInstance().playSoundEffect(15);
            new NumInputDialog(requireContext(), new IStringListener() {
                @Override
                public final void setValue(String str) {
                    this.f$0.m1091xc2f01bb0(str);
                }
            }).show();
        }
    }

    void m1091xc2f01bb0(String str) {
        showLoadAnim(getString(C1910R.string.query_ing));
        this.payStep = 0;
        this.mainVModel.queryRedeemCode(str);
    }

    private void showMtdyView(boolean z) {
        if (z) {
            OrderManager.mPaymentMethod = 8;
        } else {
            OrderManager.mPaymentMethod = 9;
        }
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupMtdyTipsView.setVisibility(0);
        ((FragmentPosterEasy2Binding) this.mViewBinding).groupHomeView.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivDyReCode.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivUploadPrint.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivMtReCode.setVisibility(8);
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivHomeReCode.setVisibility(8);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).ivMtdyTips, z ? UiPosIndexEnum.PE_IC_MT_TIPS : UiPosIndexEnum.PE_IC_DY_TIPS, z ? C1910R.mipmap.ic_poster_mt_tips : C1910R.mipmap.ic_poster_dy_tips);
        ViewUtil.setUI(((FragmentPosterEasy2Binding) this.mViewBinding).btnCodeBack, UiPosIndexEnum.PE_BTN_RETURN, C1910R.mipmap.ic_poster_back);
        ((PosterEasyVModel) this.mViewModel).startCountdown(SpManager.getInstance().getPaymentTime());
        this.payStep = 0;
        OrderManager.createPhotoOrder(OrderManager.mPaymentMethod, false, null);
    }

    private void cancelQrCodeAnim() {
        ValueAnimator valueAnimator = this.animPayQrCodeLoading;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animPayQrCodeLoading.removeAllListeners();
            this.animPayQrCodeLoading.cancel();
            this.animPayQrCodeLoading = null;
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivPayQrcode.setRotation(0.0f);
        }
        ValueAnimator valueAnimator2 = this.animPayPrintQrCodeLoading;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            this.animPayPrintQrCodeLoading.removeAllListeners();
            this.animPayPrintQrCodeLoading.cancel();
            this.animPayPrintQrCodeLoading = null;
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivPrintPayQrcode.setRotation(0.0f);
        }
        ValueAnimator valueAnimator3 = this.animPhotoQrCodeLoading;
        if (valueAnimator3 == null || !valueAnimator3.isRunning()) {
            return;
        }
        this.animPhotoQrCodeLoading.removeAllListeners();
        this.animPhotoQrCodeLoading.cancel();
        this.animPhotoQrCodeLoading = null;
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivPhotoQrcode.setRotation(0.0f);
    }

    private boolean checkPrintCountZero() {
        int remainPrintCount = SpManager.getInstance().getRemainPrintCount();
        int remainPrinterSheet = SpManager.getInstance().getRemainPrinterSheet();
        LoggerUtil.m1181d("PosterEasyFragment", "remainSheet " + remainPrinterSheet + ", remainCount " + remainPrintCount);
        if (remainPrintCount >= 1 && remainPrinterSheet >= 1) {
            return false;
        }
        ToastHelper.getInstance().showToast(getString(C1910R.string.non_printer_sheet));
        return true;
    }

    public void updateDeviceStatus() {
        LoggerUtil.m1181d("PosterEasyFragment", "updateDeviceStatus");
        if (this.mViewBinding == 0) {
            return;
        }
        if (App.isNonCannon && App.isNonCamera) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivNonCamera.setVisibility(0);
        } else {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivNonCamera.setVisibility(8);
        }
        if (PrinterHelper.getInstance().isPrinterReady()) {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivNonPrinter.setVisibility(8);
        } else {
            ((FragmentPosterEasy2Binding) this.mViewBinding).ivNonPrinter.setVisibility(0);
        }
    }

    public void updateNetStatus(boolean z) {
        ((FragmentPosterEasy2Binding) this.mViewBinding).ivNonNetwork.setVisibility(z ? 8 : 0);
    }

    private void printerPrint(String str, int i, String str2) {
        LoggerUtil.m1181d("PosterEasyFragment", "printerPrint: " + str + ", printCount " + i);
        ((PosterEasyVModel) this.mViewModel).startPrint(str, i, str2);
    }

    private void clearTempRetakeList() {
        List<Bitmap> list = this.retakeList;
        if (list != null) {
            for (Bitmap bitmap : list) {
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
            this.retakeList.clear();
            this.retakeList = null;
        }
    }

    private void startAutoTest() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1100x83b7c1f0();
            }
        });
    }

    void m1100x83b7c1f0() {
        int i = 0;
        while (true) {
            i++;
            Log.d("hhy", "hhy startAutoTest " + i);
            ThreadClock.sleep(500L);
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1096x40e08eec();
                }
            });
            ThreadClock.sleep(8500L);
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1097x51965bad();
                }
            });
            ThreadClock.sleep(5500L);
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1098x624c286e();
                }
            });
            ThreadClock.sleep(45000L);
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1099x7301f52f();
                }
            });
            ThreadClock.sleep(2000L);
        }
    }

    void m1096x40e08eec() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnStartPhoto.performClick();
    }

    void m1097x51965bad() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnConfirm.performClick();
    }

    void m1098x624c286e() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnPrint.performClick();
    }

    void m1099x7301f52f() {
        ((FragmentPosterEasy2Binding) this.mViewBinding).btnBackHome.performClick();
    }
}
