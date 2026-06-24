package com.wugu.doublecamera.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.AiThemeAdapter;
import com.wugu.doublecamera.adapter.FrameAiAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.AiThemeItem;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentChooseAiBinding;
import com.wugu.doublecamera.dialog.AiHelpDialog;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.main.p025vm.ChooseAiVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import java.io.File;
import java.util.List;

public class ChooseAiFragment extends ABaseFragment<FragmentChooseAiBinding, ChooseAiVModel> {
    private int lastThemeIndex;
    private MainVModel mainVModel;
    private final AiThemeAdapter themeAdapter = new AiThemeAdapter();
    private final FrameAiAdapter frameAiAdapter = new FrameAiAdapter();

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
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
        this.mainVModel = null;
        ((ChooseAiVModel) this.mViewModel).cancelCountdown();
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        ((ChooseAiVModel) this.mViewModel).resumeCountdown();
        OrderManager.clearOrderInfo();
    }

    private void initView() {
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrameTheme.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrameTheme.setAdapter(this.themeAdapter);
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrameTheme.setItemAnimator(null);
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrame.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrame.setAdapter(this.frameAiAdapter);
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrame.setItemAnimator(null);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(AnimationUtils.loadAnimation(requireContext(), C1910R.anim.frame_anim));
        layoutAnimationController.setOrder(0);
        layoutAnimationController.setDelay(0.1f);
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrame.setLayoutAnimation(layoutAnimationController);
        setBalanceText();
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrame.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1632lambda$initView$0$comwugudoublecameramainChooseAiFragment();
            }
        });
    }

    void m1632lambda$initView$0$comwugudoublecameramainChooseAiFragment() {
        ViewUtil.setUiLocate(((FragmentChooseAiBinding) this.mViewBinding).btnBack, 22, 19);
        ViewUtil.setUiLocate(((FragmentChooseAiBinding) this.mViewBinding).getRoot(), 29, UiPosIndexEnum.ADMIN_BG);
    }

    private void initData() {
        this.lastThemeIndex = 0;
        this.frameAiAdapter.setBgItemRes(App.mUiPosMap.get(28));
        ((ChooseAiVModel) this.mViewModel).queryFrameThemeList();
        ((ChooseAiVModel) this.mViewModel).startCountdown(SpManager.getInstance().getChooseFrameTime());
    }

    private void initListener() {
        ((FragmentChooseAiBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m916xd006e68b(view);
            }
        });
        ((FragmentChooseAiBinding) this.mViewBinding).btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m917x6aa7a90c(view);
            }
        });
        this.themeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m918x5486b8d(baseQuickAdapter, view, i);
            }
        });
        this.frameAiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m919x9fe92e0e(baseQuickAdapter, view, i);
            }
        });
    }

    void m916xd006e68b(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        requireActivity().onBackPressed();
    }

    void m917x6aa7a90c(View view) {
        ((ChooseAiVModel) this.mViewModel).pauseCountdown();
        showAiHelpDialog();
    }

    void m918x5486b8d(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SoundHelper.getInstance().playSoundEffect(1);
        if (i == this.lastThemeIndex) {
            return;
        }
        changeTheme(i);
        checkThemeItemView(view);
    }

    void m919x9fe92e0e(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        chooseFrame(i);
    }

    private void initObserver() {
        ((ChooseAiVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m920xf9051331((Integer) obj);
            }
        });
        ((ChooseAiVModel) this.mViewModel).getFrameThemeListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m922x2e469833((List) obj);
            }
        });
        ((ChooseAiVModel) this.mViewModel).getFrameListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m923xc8e75ab4((List) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m924x63881d35((Integer) obj);
            }
        });
    }

    void m920xf9051331(Integer num) {
        ((FragmentChooseAiBinding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        if (num.intValue() == 0) {
            requireActivity().onBackPressed();
        }
    }

    void m922x2e469833(List list) {
        this.themeAdapter.submitList(list);
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrameTheme.smoothScrollToPosition(0);
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrameTheme.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m921x93a5d5b2();
            }
        });
    }

    void m921x93a5d5b2() {
        changeTheme(0);
    }

    void m923xc8e75ab4(List list) {
        this.frameAiAdapter.submitList(list);
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrame.scrollToPosition(0);
        ((FragmentChooseAiBinding) this.mViewBinding).rvFrame.scheduleLayoutAnimation();
    }

    void m924x63881d35(Integer num) {
        setBalanceText();
    }

    private void setBalanceText() {
        if (OrderManager.mBalance == 0) {
            ((FragmentChooseAiBinding) this.mViewBinding).tvBalance.setVisibility(8);
        } else {
            ((FragmentChooseAiBinding) this.mViewBinding).tvBalance.setText(HtmlCompat.fromHtml(getString(C1910R.string.balance_value, StringUtil.getPriceStr(OrderManager.getTotalBalance())), 0));
            ((FragmentChooseAiBinding) this.mViewBinding).tvBalance.setVisibility(0);
        }
    }

    private void changeTheme(int i) {
        this.themeAdapter.setSelectedIndex(i);
        this.themeAdapter.notifyItemChanged(this.lastThemeIndex);
        this.themeAdapter.notifyItemChanged(i);
        this.lastThemeIndex = i;
        AiThemeItem item = this.themeAdapter.getItem(i);
        if (item == null) {
            return;
        }
        ((ChooseAiVModel) this.mViewModel).queryFrameList(item.themeKey);
        ((ChooseAiVModel) this.mViewModel).startCountdown(SpManager.getInstance().getChooseFrameTime());
    }

    private void checkThemeItemView(View view) {
        int width = view.getWidth();
        int left = view.getLeft();
        if (view.getRight() > ((FragmentChooseAiBinding) this.mViewBinding).rvFrameTheme.getWidth()) {
            ((FragmentChooseAiBinding) this.mViewBinding).rvFrameTheme.smoothScrollBy(width, 0);
        } else if (left < 0) {
            ((FragmentChooseAiBinding) this.mViewBinding).rvFrameTheme.smoothScrollBy(-width, 0);
        }
    }

    private void chooseFrame(int i) {
        SoundHelper.getInstance().playSoundEffect(2);
        FrameInfo item = this.frameAiAdapter.getItem(i);
        if (App.isNonCannon && App.isNonCamera) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.non_any_camera));
            return;
        }
        if (SpManager.getInstance().getRemainPrintCount() <= 0) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.non_printer_sheet));
            return;
        }
        if (item == null || TextUtils.isEmpty(item.getFramePath())) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.frame_error));
            return;
        }
        if (!new File(item.getFramePath()).exists()) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.frame_error));
            DbHelper.getInstance().deleteFileMd5(item.getFramePath());
        } else {
            if (App.isNonCannon) {
                App.selectedCameraIndex = 1;
            } else {
                App.selectedCameraIndex = 2;
            }
            postFrameItem(item);
        }
    }

    private void postFrameItem(FrameInfo frameInfo) {
        OrderManager.setOrderFrameInfo(frameInfo.getFrameKey(), SpManager.getInstance().getAiPrice(), frameInfo.getFrameType());
        ((ChooseAiVModel) this.mViewModel).pauseCountdown();
        this.mainVModel.chooseFrameOkLD.postValue(frameInfo.getFrameKey());
    }

    private void showAiHelpDialog() {
        AiHelpDialog aiHelpDialog = new AiHelpDialog(requireContext());
        aiHelpDialog.show();
        aiHelpDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.m925x3fa30bf5(dialogInterface);
            }
        });
    }

    void m925x3fa30bf5(DialogInterface dialogInterface) {
        ((ChooseAiVModel) this.mViewModel).resumeCountdown();
    }
}
