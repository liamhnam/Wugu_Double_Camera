package com.wugu.doublecamera.main;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.FrameMultiAdapter;
import com.wugu.doublecamera.adapter.FrameThemeAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FrameThemeInfo;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentChooseFrameBinding;
import com.wugu.doublecamera.dialog.SelectCameraDialog;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.main.p025vm.ChooseFrameVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChooseFrameFragment extends ABaseFragment<FragmentChooseFrameBinding, ChooseFrameVModel> {
    private int lastThemeIndex;
    private ObjectAnimator leftArrowAnim;
    private MainVModel mainVModel;
    private ObjectAnimator rightArrowAnim;
    private final FrameThemeAdapter themeAdapter = new FrameThemeAdapter();
    private final FrameMultiAdapter frameAdapter = new FrameMultiAdapter();
    private final List<FrameThemeInfo> frameList = new ArrayList();

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        LoggerUtil.m1181d("fragment", "ChooseFrameFragment create");
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initData();
        initListener();
        initObserver();
        SoundHelper.getInstance().playSoundEffect(7);
    }

    @Override
    public void onPause() {
        super.onPause();
        LoggerUtil.m1181d("fragment", "ChooseFrameFragment onPause");
        this.mainVModel = null;
        ((ChooseFrameVModel) this.mViewModel).cancelCountdown();
        ObjectAnimator objectAnimator = this.leftArrowAnim;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator objectAnimator2 = this.rightArrowAnim;
        if (objectAnimator2 != null) {
            objectAnimator2.cancel();
        }
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        ((ChooseFrameVModel) this.mViewModel).resumeCountdown();
    }

    private void initView() {
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.setAdapter(this.themeAdapter);
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.setItemAnimator(null);
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrame.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrame.setAdapter(this.frameAdapter);
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrame.setItemAnimator(null);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(AnimationUtils.loadAnimation(requireContext(), C1910R.anim.frame_anim));
        layoutAnimationController.setOrder(0);
        layoutAnimationController.setDelay(0.1f);
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrame.setLayoutAnimation(layoutAnimationController);
        if (AppUtil.getCPUSerial().equalsIgnoreCase("f2f110617a153812")) {
            ViewUtil.setViewPosition((ViewGroup) ((FragmentChooseFrameBinding) this.mViewBinding).rvFrame, 650, 180);
        }
        setBalanceText();
        ViewUtil.setUiLocate(((FragmentChooseFrameBinding) this.mViewBinding).btnBack, 22, 19);
        if (App.mChooseFragmentType == 28 || App.mChooseFragmentType == 212 || App.mChooseFragmentType == 211) {
            ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.setVisibility(8);
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(((FragmentChooseFrameBinding) this.mViewBinding).ivArrowLeft, "translationX", 0.0f, -20.0f, 0.0f);
        this.leftArrowAnim = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(1000L);
        this.leftArrowAnim.setRepeatCount(-1);
        this.leftArrowAnim.setRepeatMode(2);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(((FragmentChooseFrameBinding) this.mViewBinding).ivArrowRight, "translationX", 0.0f, 20.0f, 0.0f);
        this.rightArrowAnim = objectAnimatorOfFloat2;
        objectAnimatorOfFloat2.setDuration(1000L);
        this.rightArrowAnim.setRepeatCount(-1);
        this.rightArrowAnim.setRepeatMode(2);
        if (App.mSystemMode == 4) {
            ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.setVisibility(8);
            ((FragmentChooseFrameBinding) this.mViewBinding).rvFrame.setPadding(380, 0, 380, 0);
        }
    }

    private void initData() {
        this.lastThemeIndex = 0;
        this.frameAdapter.setBgItemRes(App.mUiPosMap.get(26));
        this.frameAdapter.setIcCutRes(App.mUiPosMap.get(25));
        ((ChooseFrameVModel) this.mViewModel).queryFrameThemeList();
        ((ChooseFrameVModel) this.mViewModel).startCountdown(SpManager.getInstance().getChooseFrameTime());
    }

    private void initListener() {
        ((FragmentChooseFrameBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m926xe40bec2b(view);
            }
        });
        this.themeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m927x1dd68e0a(baseQuickAdapter, view, i);
            }
        });
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    ChooseFrameFragment.this.checkThemeArrows();
                    ((ChooseFrameVModel) ChooseFrameFragment.this.mViewModel).startCountdown(SpManager.getInstance().getChooseFrameTime());
                }
            }
        });
        this.frameAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m928x57a12fe9(baseQuickAdapter, view, i);
            }
        });
    }

    void m926xe40bec2b(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        requireActivity().onBackPressed();
    }

    void m927x1dd68e0a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SoundHelper.getInstance().playSoundEffect(1);
        if (i == this.lastThemeIndex) {
            return;
        }
        changeTheme(i);
        checkThemeItemView(view);
    }

    void m928x57a12fe9(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        chooseFrame(i);
    }

    private void initObserver() {
        ((ChooseFrameVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m929xccf13e6((Integer) obj);
            }
        });
        ((ChooseFrameVModel) this.mViewModel).getFrameThemeListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m931x806457a4((List) obj);
            }
        });
        this.mainVModel.getRemoteControlLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m932xba2ef983((Integer) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m933xf3f99b62((Integer) obj);
            }
        });
    }

    void m929xccf13e6(Integer num) {
        ((FragmentChooseFrameBinding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        if (num.intValue() == 0) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            if (fragmentActivityRequireActivity instanceof MainActivity) {
                ((MainActivity) fragmentActivityRequireActivity).backToHomeFragment();
            }
        }
    }

    void m931x806457a4(List list) {
        this.frameList.clear();
        this.frameList.addAll(list);
        this.themeAdapter.submitList(this.frameList);
        if (this.frameList.isEmpty()) {
            return;
        }
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.smoothScrollToPosition(0);
        ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m930x4699b5c5();
            }
        });
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.checkThemeArrows();
            }
        }, 600L);
    }

    void m930x4699b5c5() {
        changeTheme(0);
    }

    void m932xba2ef983(Integer num) {
        if (num.intValue() == 2) {
            ((ChooseFrameVModel) this.mViewModel).queryFrameThemeList();
        }
    }

    void m933xf3f99b62(Integer num) {
        setBalanceText();
    }

    private void setBalanceText() {
        if (OrderManager.getTotalBalance() <= 0) {
            ((FragmentChooseFrameBinding) this.mViewBinding).tvBalance.setVisibility(8);
        } else {
            ((FragmentChooseFrameBinding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
            ((FragmentChooseFrameBinding) this.mViewBinding).tvBalance.setVisibility(0);
        }
    }

    private void changeTheme(int i) {
        this.themeAdapter.setSelectedIndex(i);
        this.themeAdapter.notifyItemChanged(this.lastThemeIndex);
        this.themeAdapter.notifyItemChanged(i);
        FrameThemeInfo frameThemeInfo = this.frameList.get(i);
        this.lastThemeIndex = i;
        if (!TextUtils.isEmpty(frameThemeInfo.getBackgroundPath()) && new File(frameThemeInfo.getBackgroundPath()).exists()) {
            ViewUtil.setViewGroupBg(((FragmentChooseFrameBinding) this.mViewBinding).getRoot(), frameThemeInfo.getBackgroundPath());
        } else {
            ViewUtil.setViewGroupBg(((FragmentChooseFrameBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.ADMIN_BG);
        }
        int chooseFrameTime = SpManager.getInstance().getChooseFrameTime();
        List<FrameInfo> frameInfoList = this.frameList.get(i).getFrameInfoList();
        this.frameAdapter.submitList(frameInfoList);
        if (frameInfoList != null && !frameInfoList.isEmpty()) {
            FrameInfo frameInfo = frameInfoList.get(0);
            ((FragmentChooseFrameBinding) this.mViewBinding).rvFrame.scrollToPosition(0);
            if (frameInfo.getFrameType() == 5) {
                chooseFrameTime = 300;
            } else {
                ((FragmentChooseFrameBinding) this.mViewBinding).rvFrame.scheduleLayoutAnimation();
            }
        }
        ((ChooseFrameVModel) this.mViewModel).startCountdown(chooseFrameTime);
    }

    private void checkThemeItemView(View view) {
        int width = view.getWidth();
        int left = view.getLeft();
        if (view.getRight() > ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.getWidth()) {
            ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.smoothScrollBy(width, 0);
        } else if (left < 0) {
            ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.smoothScrollBy(-width, 0);
        }
    }

    public void checkThemeArrows() {
        if (this.mViewBinding == 0) {
            return;
        }
        boolean zCanScrollHorizontally = ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.canScrollHorizontally(-1);
        boolean zCanScrollHorizontally2 = ((FragmentChooseFrameBinding) this.mViewBinding).rvFrameTheme.canScrollHorizontally(1);
        if (zCanScrollHorizontally) {
            ((FragmentChooseFrameBinding) this.mViewBinding).ivArrowLeft.setVisibility(0);
            if (!this.leftArrowAnim.isRunning()) {
                this.leftArrowAnim.start();
            }
        } else {
            ((FragmentChooseFrameBinding) this.mViewBinding).ivArrowLeft.setVisibility(4);
            if (this.leftArrowAnim.isRunning()) {
                this.leftArrowAnim.cancel();
            }
        }
        if (zCanScrollHorizontally2) {
            ((FragmentChooseFrameBinding) this.mViewBinding).ivArrowRight.setVisibility(0);
            if (this.rightArrowAnim.isRunning()) {
                return;
            }
            this.rightArrowAnim.start();
            return;
        }
        ((FragmentChooseFrameBinding) this.mViewBinding).ivArrowRight.setVisibility(4);
        if (this.rightArrowAnim.isRunning()) {
            this.rightArrowAnim.cancel();
        }
    }

    private void chooseFrame(int i) {
        SoundHelper.getInstance().playSoundEffect(2);
        FrameInfo frameInfo = this.frameList.get(this.lastThemeIndex).getFrameInfoList().get(i);
        if (frameInfo.getFrameType() == 5) {
            return;
        }
        if (App.isNonCannon && App.isNonCamera) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.non_any_camera));
            return;
        }
        if (SpManager.getInstance().getRemainPrintCount() <= 0) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.non_printer_sheet));
            return;
        }
        if (!App.isNonCannon && !App.isNonCamera) {
            showSelectCameraDialog(i);
            return;
        }
        if (TextUtils.isEmpty(frameInfo.getFramePath())) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.frame_error));
            return;
        }
        if (!new File(frameInfo.getFramePath()).exists()) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.frame_error));
            DbHelper.getInstance().deleteFileMd5(frameInfo.getFramePath());
        } else {
            if (App.isNonCannon) {
                App.selectedCameraIndex = 1;
            } else {
                App.selectedCameraIndex = 2;
            }
            postFrameItem(frameInfo);
        }
    }

    private void showSelectCameraDialog(final int i) {
        new SelectCameraDialog(requireContext(), new IIntListener() {
            @Override
            public final void setValue(int i2) {
                this.f$0.m934x59b23c1(i, i2);
            }
        }).show();
    }

    void m934x59b23c1(int i, int i2) {
        App.selectedCameraIndex = i2;
        postFrameItem(this.frameList.get(this.lastThemeIndex).getFrameInfoList().get(i));
    }

    private void postFrameItem(FrameInfo frameInfo) {
        int price = frameInfo.getPrice();
        if (frameInfo.getFrameType() == 1) {
            price = SpManager.getInstance().getAiPrice();
        }
        LoggerUtil.m1181d("chooseFrameFragment", "postFrameItem: " + frameInfo.getFrameKey() + ", " + price + ", " + frameInfo.getFrameType());
        OrderManager.setOrderFrameInfo(frameInfo.getFrameKey(), price, frameInfo.getFrameType());
        ((ChooseFrameVModel) this.mViewModel).pauseCountdown();
        this.mainVModel.chooseFrameOkLD.postValue(frameInfo.getFrameKey());
    }
}
