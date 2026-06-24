package com.wugu.doublecamera.main;

import android.animation.ValueAnimator;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.StartPrintingInfo;
import com.wugu.doublecamera.databinding.FragmentPrintingBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.p025vm.PrintingVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;

public class PrintingFragment extends ABaseFragment<FragmentPrintingBinding, PrintingVModel> {
    private final int PRINT_DONE_BACK_TIME = 30;
    private AlertDialog alertDialog;
    private ValueAnimator animImage;
    private ValueAnimator animPhotoQrCodeLoading;
    private ValueAnimator animVideo;
    private FrameInfo frameInfo;
    private AppCompatImageView imageView;
    private AppCompatImageView ivVideoView;
    private MainVModel mainVModel;
    private VideoView videoView;

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        LoggerUtil.m1181d("fragment", "PrintingFragment Create");
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initObserver();
        initListener();
    }

    @Override
    public void onDestroyView() {
        ((PrintingVModel) this.mViewModel).cancelCountdown();
        this.mainVModel = null;
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.alertDialog.dismiss();
        }
        super.onDestroyView();
    }

    private void initView() {
        ViewUtil.setUiLocate(((FragmentPrintingBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.PRINTING_BG);
        ViewUtil.setUiLocate(((FragmentPrintingBinding) this.mViewBinding).btnBack, UiPosIndexEnum.PRINT_BTN_BACK_HOME);
        ViewUtil.setUiLocate(((FragmentPrintingBinding) this.mViewBinding).layoutCountdown, UiPosIndexEnum.PRINT_COUNTDOWN);
        if (App.mIsNetAvailable) {
            ViewUtil.setImageDrawableVisible(((FragmentPrintingBinding) this.mViewBinding).ivPhotoQrcode, UiPosIndexEnum.PRINT_QRCODE1);
        }
        ((FragmentPrintingBinding) this.mViewBinding).btnBack.setEnabled(false);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1695lambda$initView$0$comwugudoublecameramainPrintingFragment();
            }
        }, 5000L);
    }

    void m1695lambda$initView$0$comwugudoublecameramainPrintingFragment() {
        ((FragmentPrintingBinding) this.mViewBinding).btnBack.setEnabled(true);
    }

    private void addImageAndVideoView() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        AppCompatImageView appCompatImageView = new AppCompatImageView(requireContext());
        this.imageView = appCompatImageView;
        appCompatImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        BitmapFactory.Options bitmapOptions = BitmapUtil.getBitmapOptions(requireContext(), this.frameInfo.getFramePath());
        if (bitmapOptions == null) {
            return;
        }
        boolean z = this.frameInfo.getFrameType() == 3 || this.frameInfo.getFrameType() == 5;
        if (bitmapOptions.outWidth > bitmapOptions.outHeight) {
            i = 300;
            i3 = 730;
            i4 = 30;
            i2 = 450;
            i5 = 670;
            i6 = 300;
        } else {
            i = 200;
            i2 = 625;
            i3 = 800;
            i4 = 200;
            i5 = 420;
            i6 = 200;
        }
        if (z) {
            i4 += 200;
        }
        this.imageView.setLayoutParams(new AbsoluteLayout.LayoutParams(i5, i2, i4, i));
        ((FragmentPrintingBinding) this.mViewBinding).getRoot().addView(this.imageView);
        ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(this.imageView, 490);
        this.animImage = rotationAnim1;
        rotationAnim1.start();
        if (z) {
            return;
        }
        this.ivVideoView = new AppCompatImageView(requireContext());
        this.videoView = new VideoView(requireContext());
        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(i5, i2, i3, i6);
        this.videoView.setLayoutParams(layoutParams);
        this.ivVideoView.setLayoutParams(layoutParams);
        this.videoView.setVisibility(4);
        ((FragmentPrintingBinding) this.mViewBinding).getRoot().addView(this.videoView);
        ((FragmentPrintingBinding) this.mViewBinding).getRoot().addView(this.ivVideoView);
        ValueAnimator rotationAnim12 = AnimUtil.getRotationAnim1(this.ivVideoView, 490);
        this.animVideo = rotationAnim12;
        rotationAnim12.start();
    }

    private void initListener() {
        ((FragmentPrintingBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1011xb0f00601(view);
            }
        });
    }

    void m1011xb0f00601(View view) {
        backToHome();
    }

    private void initObserver() {
        ((PrintingVModel) this.mViewModel).getCountdownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1012xa0beb24((Integer) obj);
            }
        });
        ((PrintingVModel) this.mViewModel).getPhotoQrcodeLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1013xa4acada5((String) obj);
            }
        });
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1014x3f4d7026((String) obj);
            }
        });
        this.mainVModel.startPrintingLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.setPrintPhoto((StartPrintingInfo) obj);
            }
        });
        this.mainVModel.saveVideoFinishLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.playVideo((String) obj);
            }
        });
    }

    void m1012xa0beb24(Integer num) {
        if (num.intValue() < 0) {
            ((FragmentPrintingBinding) this.mViewBinding).layoutCountdown.setCount(0);
        } else {
            ((FragmentPrintingBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        }
        if (num.intValue() == 0) {
            backToHome();
        }
    }

    void m1013xa4acada5(String str) {
        LoggerUtil.m1181d("order", "photo qrcode: " + str);
        cancelPhotoQrCodeAnim();
        ((FragmentPrintingBinding) this.mViewBinding).ivPhotoQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 300, null));
    }

    void m1014x3f4d7026(String str) {
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        this.frameInfo = orderFrameInfo;
        if (orderFrameInfo == null) {
            return;
        }
        addImageAndVideoView();
    }

    private void backToHome() {
        VideoView videoView = this.videoView;
        if (videoView != null) {
            videoView.stopPlayback();
            this.videoView.suspend();
            this.videoView.setOnCompletionListener(null);
            this.videoView.setOnPreparedListener(null);
            this.videoView = null;
        }
        OrderManager.clearOrderInfo();
        ((FragmentPrintingBinding) this.mViewBinding).getRoot().removeAllViews();
        LoggerUtil.m1181d("printingFragment", "PrintingFragment Back");
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        if (fragmentActivityRequireActivity instanceof MainActivity) {
            ((MainActivity) fragmentActivityRequireActivity).backToHomeFragment();
        }
    }

    public void setPrintPhoto(final StartPrintingInfo startPrintingInfo) {
        SoundHelper.getInstance().playSoundEffect(12);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1015x3146740e(startPrintingInfo);
            }
        }, 1000L);
        if (App.mIsNetAvailable) {
            ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(((FragmentPrintingBinding) this.mViewBinding).ivPhotoQrcode, 490);
            this.animPhotoQrCodeLoading = rotationAnim1;
            rotationAnim1.start();
        }
    }

    void m1015x3146740e(StartPrintingInfo startPrintingInfo) {
        if (this.mViewBinding == 0) {
            return;
        }
        cancelImageAnim();
        Glide.with(requireContext()).load(startPrintingInfo.photoPath).into(this.imageView);
        startCountdown(((PrintingVModel) this.mViewModel).getPrintTime(startPrintingInfo.printCount) + 30);
        ((PrintingVModel) this.mViewModel).uploadImage(startPrintingInfo.photoPath);
        ((PrintingVModel) this.mViewModel).printAndFinishOrder(startPrintingInfo.photoPath, startPrintingInfo.printCount, this.frameInfo);
    }

    private void startCountdown(int i) {
        ((PrintingVModel) this.mViewModel).startCountdown(i);
        ((FragmentPrintingBinding) this.mViewBinding).layoutCountdown.setCount(i);
    }

    private void cancelPhotoQrCodeAnim() {
        ValueAnimator valueAnimator = this.animPhotoQrCodeLoading;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animPhotoQrCodeLoading.removeAllListeners();
            this.animPhotoQrCodeLoading.cancel();
        }
        ((FragmentPrintingBinding) this.mViewBinding).ivPhotoQrcode.setRotation(0.0f);
    }

    private void cancelImageAnim() {
        ValueAnimator valueAnimator = this.animImage;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animImage.removeAllListeners();
            this.animImage.cancel();
        }
        AppCompatImageView appCompatImageView = this.imageView;
        if (appCompatImageView != null) {
            appCompatImageView.setRotation(0.0f);
        }
    }

    private void cancelVideoAnim() {
        ValueAnimator valueAnimator = this.animVideo;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animVideo.removeAllListeners();
            this.animVideo.cancel();
        }
        AppCompatImageView appCompatImageView = this.ivVideoView;
        if (appCompatImageView != null) {
            appCompatImageView.setVisibility(8);
        }
    }

    public void playVideo(final String str) {
        LoggerUtil.m1181d("fragment", "playVideo " + str + App.mFrameVideoPath);
        if (TextUtils.isEmpty(App.mFrameVideoPath) || !App.mFrameVideoPath.equals(str) || this.videoView == null) {
            return;
        }
        App.mFrameVideoPath = null;
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1696lambda$playVideo$7$comwugudoublecameramainPrintingFragment(str);
            }
        }, 500L);
    }

    void m1696lambda$playVideo$7$comwugudoublecameramainPrintingFragment(String str) {
        cancelVideoAnim();
        VideoView videoView = this.videoView;
        if (videoView == null) {
            return;
        }
        videoView.setVisibility(0);
        this.videoView.setVideoPath(str);
        this.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public final void onPrepared(MediaPlayer mediaPlayer) {
                PrintingFragment.lambda$playVideo$6(mediaPlayer);
            }
        });
    }

    static void lambda$playVideo$6(MediaPlayer mediaPlayer) {
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}
