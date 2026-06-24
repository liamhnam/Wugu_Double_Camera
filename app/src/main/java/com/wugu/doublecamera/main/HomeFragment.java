package com.wugu.doublecamera.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentHomeBinding;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.main.p025vm.HomeVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.network.MqttHelper;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import com.wugu.facebeauty.FaceBeautyMain;
import com.wugu.facebeauty.SurfaceRender;

public class HomeFragment extends ABaseFragment<FragmentHomeBinding, HomeVModel> implements View.OnClickListener {
    private AnimatorSet animBtnEnter;
    private MainVModel mainVModel;
    private AppCompatImageView[] modelImageViews;
    private boolean stopModelAnim = true;
    private final int[] tabAry = {111, 14, 18, 15, 16, UiPosIndexEnum.HOME_REPLACE_BG_TAB};

    private void test() {
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        LoggerUtil.m1181d("HomeFragment", "onViewCreated");
        initView();
        initObserver();
        initListener();
        preInitBeauty();
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        LoggerUtil.m1181d("HomeFragment", "onHiddenChanged " + z);
        if (z) {
            return;
        }
        initView();
        startEnterBtnAnim();
        if (OrderManager.isExistFailedFile()) {
            ThreadHelper.getInstance().createThread(new HomeFragment$$ExternalSyntheticLambda4());
        }
    }

    @Override
    public void onDestroyView() {
        LoggerUtil.m1181d("HomeFragment", "onDestroyView");
        super.onDestroyView();
    }

    private void initView() {
        UiPosition btnUiInfo = ((HomeVModel) this.mViewModel).getBtnUiInfo();
        UiPosition bgUiInfo = ((HomeVModel) this.mViewModel).getBgUiInfo();
        ViewUtil.setViewPosition(((FragmentHomeBinding) this.mViewBinding).btnEnter, btnUiInfo.f2441x, btnUiInfo.f2442y);
        ViewUtil.setImageDrawable(((FragmentHomeBinding) this.mViewBinding).btnEnter, btnUiInfo.resPath);
        ViewUtil.setViewGroupBg(((FragmentHomeBinding) this.mViewBinding).getRoot(), bgUiInfo.resPath);
        ((FragmentHomeBinding) this.mViewBinding).ivNonNetwork.setVisibility(App.mIsNetAvailable ? 8 : 0);
        showIdle(true);
        setBalanceText();
    }

    private void initListener() {
        ((FragmentHomeBinding) this.mViewBinding).btnEnter.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).btnBack.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).ivIpTab.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).ivHeadTab.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).ivCertifyTab.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).ivPrintTab.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).ivCardTab.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).ivAiTab.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).ivReplaceTab.setOnClickListener(this);
        ((FragmentHomeBinding) this.mViewBinding).vLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1633lambda$initListener$0$comwugudoublecameramainHomeFragment(view);
            }
        });
    }

    void m1633lambda$initListener$0$comwugudoublecameramainHomeFragment(View view) {
        this.mainVModel.lockScreenLD.postValue(1);
    }

    private void initObserver() {
        ((HomeVModel) this.mViewModel).getUiDbOkLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1634lambda$initObserver$1$comwugudoublecameramainHomeFragment((Integer) obj);
            }
        });
        ((HomeVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1635lambda$initObserver$2$comwugudoublecameramainHomeFragment((Integer) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1636lambda$initObserver$3$comwugudoublecameramainHomeFragment((Integer) obj);
            }
        });
    }

    void m1634lambda$initObserver$1$comwugudoublecameramainHomeFragment(Integer num) {
        initOtherView();
    }

    void m1635lambda$initObserver$2$comwugudoublecameramainHomeFragment(Integer num) {
        ((FragmentHomeBinding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        if (num.intValue() == 0) {
            showIdle(true);
        }
    }

    void m1636lambda$initObserver$3$comwugudoublecameramainHomeFragment(Integer num) {
        setBalanceText();
    }

    @Override
    public void onClick(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        if (view == ((FragmentHomeBinding) this.mViewBinding).btnBack) {
            startEnterBtnAnim();
            showIdle(true);
            return;
        }
        if (view == ((FragmentHomeBinding) this.mViewBinding).btnEnter) {
            if (App.mSystemMode == 2) {
                App.mChooseFragmentType = 31;
                showIdle(false);
                this.mainVModel.fragmentLD.postValue(Integer.valueOf(App.mChooseFragmentType));
                return;
            } else {
                showTabs();
                test();
                return;
            }
        }
        if (view == ((FragmentHomeBinding) this.mViewBinding).ivHeadTab) {
            App.mChooseFragmentType = 27;
            showIdle(false);
            this.mainVModel.fragmentLD.postValue(Integer.valueOf(App.mChooseFragmentType));
            return;
        }
        if (view == ((FragmentHomeBinding) this.mViewBinding).ivCertifyTab) {
            if (!App.mIsNetAvailable) {
                ToastHelper.getInstance().showToast(getString(C1910R.string.not_network));
                return;
            }
            App.mChooseFragmentType = 28;
            showIdle(false);
            this.mainVModel.fragmentLD.postValue(Integer.valueOf(App.mChooseFragmentType));
            return;
        }
        if (view == ((FragmentHomeBinding) this.mViewBinding).ivIpTab) {
            App.mChooseFragmentType = 212;
            showIdle(false);
            this.mainVModel.fragmentLD.postValue(Integer.valueOf(App.mChooseFragmentType));
            return;
        }
        if (view == ((FragmentHomeBinding) this.mViewBinding).ivCardTab) {
            return;
        }
        if (view == ((FragmentHomeBinding) this.mViewBinding).ivAiTab) {
            if (!App.mIsNetAvailable) {
                ToastHelper.getInstance().showToast(getString(C1910R.string.not_network));
                return;
            }
            App.mChooseFragmentType = 211;
            showIdle(false);
            this.mainVModel.fragmentLD.postValue(Integer.valueOf(App.mChooseFragmentType));
            return;
        }
        if (view == ((FragmentHomeBinding) this.mViewBinding).ivPrintTab) {
            if (!MqttHelper.getInstance().isConnected()) {
                ToastHelper.getInstance().showToast(getString(C1910R.string.not_mqtt_server));
                return;
            }
            App.mChooseFragmentType = 29;
            showIdle(false);
            this.mainVModel.fragmentLD.postValue(Integer.valueOf(App.mChooseFragmentType));
            return;
        }
        if (view == ((FragmentHomeBinding) this.mViewBinding).ivReplaceTab) {
            if (!MqttHelper.getInstance().isConnected()) {
                ToastHelper.getInstance().showToast(getString(C1910R.string.not_mqtt_server));
                return;
            }
            App.mChooseFragmentType = 213;
            showIdle(false);
            this.mainVModel.fragmentLD.postValue(Integer.valueOf(App.mChooseFragmentType));
        }
    }

    private void initOtherView() {
        ViewUtil.setUiLocate(((FragmentHomeBinding) this.mViewBinding).getRoot(), 12);
        ViewUtil.setUiLocate(((FragmentHomeBinding) this.mViewBinding).btnEnter, 11);
        ViewUtil.setUiLocate(((FragmentHomeBinding) this.mViewBinding).btnBack, 19);
        for (int i : this.tabAry) {
            AppCompatImageView imageViewByEnum = getImageViewByEnum(i);
            if (imageViewByEnum != null) {
                ViewUtil.setUiLocate(imageViewByEnum, i);
            }
        }
    }

    private void showTabs() {
        ((FragmentHomeBinding) this.mViewBinding).btnEnter.setVisibility(8);
        cancelEnterBtnAnim();
        if (checkIsOnlyOneTab()) {
            showIdle(false);
            return;
        }
        ((FragmentHomeBinding) this.mViewBinding).btnBack.setVisibility(0);
        ((FragmentHomeBinding) this.mViewBinding).tvCountdown.setVisibility(0);
        for (int i : this.tabAry) {
            AppCompatImageView imageViewByEnum = getImageViewByEnum(i);
            if (imageViewByEnum != null) {
                showTab(imageViewByEnum, i);
            }
        }
        ViewUtil.setUiLocate(((FragmentHomeBinding) this.mViewBinding).getRoot(), 13);
        startLoopAnim();
        ((HomeVModel) this.mViewModel).startCountdown(SpManager.getInstance().getModelSelectTime());
    }

    private void showIdle(boolean z) {
        ((HomeVModel) this.mViewModel).cancelCountdown();
        ((FragmentHomeBinding) this.mViewBinding).tvCountdown.setVisibility(8);
        for (int i : this.tabAry) {
            AppCompatImageView imageViewByEnum = getImageViewByEnum(i);
            if (imageViewByEnum != null) {
                imageViewByEnum.setVisibility(8);
            }
        }
        if (!z) {
            ((FragmentHomeBinding) this.mViewBinding).btnEnter.setVisibility(8);
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1639lambda$showIdle$4$comwugudoublecameramainHomeFragment();
                }
            }, 1000L);
        } else {
            ((FragmentHomeBinding) this.mViewBinding).btnBack.setVisibility(8);
            ((FragmentHomeBinding) this.mViewBinding).btnEnter.setVisibility(0);
            ViewUtil.setUiLocate(((FragmentHomeBinding) this.mViewBinding).getRoot(), 12);
        }
        this.stopModelAnim = true;
    }

    void m1639lambda$showIdle$4$comwugudoublecameramainHomeFragment() {
        ViewUtil.setUiLocate(((FragmentHomeBinding) this.mViewBinding).getRoot(), 12);
    }

    private boolean checkIsOnlyOneTab() {
        int i = 14;
        int i2 = 0;
        for (int i3 : this.tabAry) {
            UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i3));
            if (uiPosition != null && uiPosition.isVisible) {
                i2++;
                i = i3;
            }
        }
        if (i2 > 1) {
            return false;
        }
        int i4 = 27;
        if (i2 == 1) {
            if (i == 111) {
                i4 = 212;
            } else if (i != 113) {
                switch (i) {
                    case 15:
                        i4 = 28;
                        break;
                    case 16:
                        i4 = 29;
                        break;
                    case 17:
                        i4 = 210;
                        break;
                    case 18:
                        i4 = 211;
                        break;
                }
            } else {
                i4 = 213;
            }
        }
        App.mChooseFragmentType = i4;
        this.mainVModel.fragmentLD.postValue(Integer.valueOf(App.mChooseFragmentType));
        return true;
    }

    private void showTab(ImageView imageView, int i) {
        UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(i));
        if (uiPosition != null) {
            imageView.setVisibility(uiPosition.isVisible ? 0 : 8);
        }
    }

    private void setBalanceText() {
        if (OrderManager.mBalance == 0) {
            ((FragmentHomeBinding) this.mViewBinding).tvBalance.setVisibility(8);
        } else {
            ((FragmentHomeBinding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.mBalance)), 0));
            ((FragmentHomeBinding) this.mViewBinding).tvBalance.setVisibility(0);
        }
    }

    private void preInitBeauty() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1638lambda$preInitBeauty$7$comwugudoublecameramainHomeFragment();
            }
        });
    }

    void m1638lambda$preInitBeauty$7$comwugudoublecameramainHomeFragment() {
        ThreadClock.sleep(9000L);
        LoggerUtil.m1181d("fragment", "preInitBeauty " + App.mIsInitBeautySuccess);
        if (App.mIsInitBeautySuccess != 1) {
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1637lambda$preInitBeauty$6$comwugudoublecameramainHomeFragment();
                }
            });
            ThreadClock.sleep(10000L);
        }
        renderBeautyImage();
    }

    void m1637lambda$preInitBeauty$6$comwugudoublecameramainHomeFragment() {
        FaceBeautyMain.registerFURender(requireContext(), new FaceBeautyMain.RegCallback() {
            @Override
            public final void onResult(boolean z) {
                HomeFragment.lambda$preInitBeauty$5(z);
            }
        });
    }

    static void lambda$preInitBeauty$5(boolean z) {
        if (z && App.mIsInitBeautySuccess == 0) {
            App.mIsInitBeautySuccess = 1;
        } else {
            if (z) {
                return;
            }
            App.mIsInitBeautySuccess = 2;
        }
    }

    private void renderBeautyImage() {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m938x92ca8e9e();
            }
        });
    }

    void m938x92ca8e9e() {
        final SurfaceRender surfaceRenderPreInit = FaceBeautyMain.preInit(App.getInstance(), ((FragmentHomeBinding) this.mViewBinding).glSurfaceView);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m937x62a639d(surfaceRenderPreInit);
            }
        }, 8000L);
    }

    void m937x62a639d(SurfaceRender surfaceRender) {
        if (surfaceRender != null) {
            surfaceRender.onPause();
            surfaceRender.onDestroy();
        }
        ((FragmentHomeBinding) this.mViewBinding).glSurfaceView.setVisibility(8);
        startEnterBtnAnim();
        updateDeviceStatus();
    }

    public void updateDeviceStatus() {
        if (App.isNonCannon && App.isNonCamera) {
            ((FragmentHomeBinding) this.mViewBinding).ivNonCamera.setVisibility(0);
        } else {
            ((FragmentHomeBinding) this.mViewBinding).ivNonCamera.setVisibility(8);
        }
        if (PrinterHelper.getInstance().isPrinterReady()) {
            ((FragmentHomeBinding) this.mViewBinding).ivNonPrinter.setVisibility(8);
        } else {
            ((FragmentHomeBinding) this.mViewBinding).ivNonPrinter.setVisibility(0);
        }
    }

    private void startEnterBtnAnim() {
        if (this.animBtnEnter == null) {
            this.animBtnEnter = AnimUtil.getBreathAnim(((FragmentHomeBinding) this.mViewBinding).btnEnter, 2000);
        }
        this.animBtnEnter.start();
    }

    private void cancelEnterBtnAnim() {
        AnimatorSet animatorSet = this.animBtnEnter;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }

    private void startLoopAnim() {
        if (this.modelImageViews == null) {
            this.modelImageViews = new AppCompatImageView[this.tabAry.length];
            for (int i = 0; i < this.tabAry.length; i++) {
                UiPosition uiPosition = App.mUiPosMap.get(Integer.valueOf(this.tabAry[i]));
                if (uiPosition != null && uiPosition.isVisible) {
                    this.modelImageViews[i] = getImageViewByEnum(this.tabAry[i]);
                }
            }
        }
        if (this.stopModelAnim) {
            this.stopModelAnim = false;
            ((FragmentHomeBinding) this.mViewBinding).ivIpTab.post(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1640lambda$startLoopAnim$10$comwugudoublecameramainHomeFragment();
                }
            });
        }
    }

    void m1640lambda$startLoopAnim$10$comwugudoublecameramainHomeFragment() {
        playScaleAnimation(this.modelImageViews[0], 0);
    }

    private AppCompatImageView getImageViewByEnum(int i) {
        if (i == 111) {
            return ((FragmentHomeBinding) this.mViewBinding).ivIpTab;
        }
        if (i != 113) {
            switch (i) {
                case 14:
                    return ((FragmentHomeBinding) this.mViewBinding).ivHeadTab;
                case 15:
                    return ((FragmentHomeBinding) this.mViewBinding).ivCertifyTab;
                case 16:
                    return ((FragmentHomeBinding) this.mViewBinding).ivPrintTab;
                case 17:
                    return ((FragmentHomeBinding) this.mViewBinding).ivCardTab;
                case 18:
                    return ((FragmentHomeBinding) this.mViewBinding).ivAiTab;
                default:
                    return null;
            }
        }
        return ((FragmentHomeBinding) this.mViewBinding).ivReplaceTab;
    }

    public void playScaleAnimation(AppCompatImageView appCompatImageView, final int i) {
        if (this.stopModelAnim) {
            return;
        }
        if (appCompatImageView == null) {
            if (i < this.modelImageViews.length - 1) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m935x56d27596(i);
                    }
                }, 10L);
                return;
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m936xe372a097();
                    }
                }, 1000L);
                return;
            }
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(appCompatImageView, "scaleX", 1.0f, 1.05f, 1.0f);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(appCompatImageView, "scaleY", 1.0f, 1.05f, 1.0f);
        objectAnimatorOfFloat2.setDuration(200L);
        objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.addListener(new C19461(i));
        animatorSet.start();
    }

    void m935x56d27596(int i) {
        int i2 = i + 1;
        playScaleAnimation(this.modelImageViews[i2], i2);
    }

    void m936xe372a097() {
        playScaleAnimation(this.modelImageViews[0], 0);
    }

    class C19461 extends AnimatorListenerAdapter {
        final int val$index;

        C19461(int i) {
            this.val$index = i;
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            if (this.val$index < HomeFragment.this.modelImageViews.length - 1) {
                Handler handler = new Handler();
                final int i = this.val$index;
                handler.postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m939xa0eb49e9(i);
                    }
                }, 10L);
                return;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m940x862cb8aa();
                }
            }, 1000L);
        }

        void m939xa0eb49e9(int i) {
            HomeFragment homeFragment = HomeFragment.this;
            int i2 = i + 1;
            homeFragment.playScaleAnimation(homeFragment.modelImageViews[i2], i2);
        }

        void m940x862cb8aa() {
            HomeFragment homeFragment = HomeFragment.this;
            homeFragment.playScaleAnimation(homeFragment.modelImageViews[0], 0);
        }
    }

    public void netStatusChange(boolean z) {
        ((FragmentHomeBinding) this.mViewBinding).ivNonNetwork.setVisibility(z ? 8 : 0);
    }

    public void serverStatusChange() {
        ((FragmentHomeBinding) this.mViewBinding).ivNonServer.setVisibility(MqttHelper.getInstance().isConnected() ? 8 : 0);
    }
}
