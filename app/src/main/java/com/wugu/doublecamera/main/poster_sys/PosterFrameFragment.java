package com.wugu.doublecamera.main.poster_sys;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.PosterFrameAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FrameThemeInfo;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentPosterFrameBinding;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.poster_sys.p024vm.PosterFrameVModel;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import java.util.ArrayList;
import java.util.List;

public class PosterFrameFragment extends ABaseFragment<FragmentPosterFrameBinding, PosterFrameVModel> {
    private int currentThemeIndex;
    private final PosterFrameAdapter frameAdapter = new PosterFrameAdapter();
    private final List<FrameThemeInfo> frameList = new ArrayList();
    private MainVModel mainVModel;

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mainVModel = (MainVModel) new ViewModelProvider(requireActivity()).get(MainVModel.class);
        initView();
        initData();
        initObserver();
        initListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mainVModel = null;
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        ((PosterFrameVModel) this.mViewModel).resumeCountdown();
    }

    private void initView() {
        ((FragmentPosterFrameBinding) this.mViewBinding).rvFrame.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentPosterFrameBinding) this.mViewBinding).rvFrame.setAdapter(this.frameAdapter);
        ((FragmentPosterFrameBinding) this.mViewBinding).rvFrame.setItemAnimator(null);
        this.frameAdapter.setBgItemRes(App.mUiPosMap.get(26));
        ViewUtil.setUiLocate(((FragmentPosterFrameBinding) this.mViewBinding).btnBack, 22);
        ViewUtil.setUiLocate(((FragmentPosterFrameBinding) this.mViewBinding).btnEnter, 1003);
    }

    private void initData() {
        ((PosterFrameVModel) this.mViewModel).queryFrameThemeList();
        ((PosterFrameVModel) this.mViewModel).startCountdown(SpManager.getInstance().getModelSelectTime());
        SoundHelper.getInstance().playSoundEffect(7);
    }

    private void initObserver() {
        ((PosterFrameVModel) this.mViewModel).getFrameThemeListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1108xb2d6fa3b((List) obj);
            }
        });
        ((PosterFrameVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1109xc38cc6fc((Integer) obj);
            }
        });
    }

    void m1108xb2d6fa3b(List list) {
        this.frameList.clear();
        this.frameList.addAll(list);
        if (this.frameList.size() == 0) {
            return;
        }
        selectTheme(0);
    }

    void m1109xc38cc6fc(Integer num) {
        ((FragmentPosterFrameBinding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        if (num.intValue() == 0) {
            ((FragmentPosterFrameBinding) this.mViewBinding).btnBack.performClick();
        }
    }

    private void initListener() {
        ((FragmentPosterFrameBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1105x8811b49b(view);
            }
        });
        ((FragmentPosterFrameBinding) this.mViewBinding).btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1106x98c7815c(view);
            }
        });
        this.frameAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1107xa97d4e1d(baseQuickAdapter, view, i);
            }
        });
    }

    void m1105x8811b49b(View view) {
        SoundHelper.getInstance().playSoundEffect(3);
        requireActivity().onBackPressed();
    }

    void m1106x98c7815c(View view) {
        selectFrame(this.frameAdapter.getSelectedIndex());
    }

    void m1107xa97d4e1d(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        int selectedIndex = this.frameAdapter.getSelectedIndex();
        if (selectedIndex == i) {
            return;
        }
        this.frameAdapter.setSelectedIndex(i);
        this.frameAdapter.notifyItemChanged(selectedIndex);
        this.frameAdapter.notifyItemChanged(i);
    }

    private void selectTheme(int i) {
        SoundHelper.getInstance().playSoundEffect(1);
        ((FragmentPosterFrameBinding) this.mViewBinding).rvFrame.setVisibility(0);
        ViewUtil.setViewGroupBg(((FragmentPosterFrameBinding) this.mViewBinding).getRoot(), this.frameList.get(i).getBackgroundPath());
        this.currentThemeIndex = i;
        this.frameAdapter.submitList(this.frameList.get(i).getFrameInfoList());
        ((FragmentPosterFrameBinding) this.mViewBinding).rvFrame.scrollToPosition(0);
        ((FragmentPosterFrameBinding) this.mViewBinding).rvFrame.scheduleLayoutAnimation();
        ((PosterFrameVModel) this.mViewModel).startCountdown(SpManager.getInstance().getChooseFrameTime());
    }

    private void selectFrame(int i) {
        SoundHelper.getInstance().playSoundEffect(2);
        FrameInfo frameInfo = this.frameList.get(this.currentThemeIndex).getFrameInfoList().get(i);
        if (App.isNonCannon && App.isNonCamera) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.non_any_camera));
            return;
        }
        if (SpManager.getInstance().getRemainPrintCount() <= 0) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.non_printer_sheet));
            return;
        }
        if (App.isNonCannon) {
            App.selectedCameraIndex = 1;
        } else {
            App.selectedCameraIndex = 2;
        }
        ((PosterFrameVModel) this.mViewModel).pauseCountdown();
        OrderManager.setOrderFrameInfo(frameInfo.getFrameKey(), frameInfo.getPrice(), frameInfo.getFrameType());
        this.mainVModel.chooseFrameOkLD.postValue(frameInfo.getFrameKey());
    }
}
