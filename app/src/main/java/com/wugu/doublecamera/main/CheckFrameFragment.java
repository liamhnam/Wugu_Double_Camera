package com.wugu.doublecamera.main;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.adapter.CheckFrameAdapter;
import com.wugu.doublecamera.adapter.FrameThemeAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.FrameThemeInfo;
import com.wugu.doublecamera.databinding.FragmentCheckFrameBinding;
import com.wugu.doublecamera.main.p025vm.CheckFrameVModel;
import com.wugu.doublecamera.widget.SoundHelper;
import java.util.ArrayList;
import java.util.List;

public class CheckFrameFragment extends ABaseFragment<FragmentCheckFrameBinding, CheckFrameVModel> {
    private int lastThemeIndex;
    private final FrameThemeAdapter themeAdapter = new FrameThemeAdapter();
    private final CheckFrameAdapter frameAdapter = new CheckFrameAdapter();
    private final List<FrameThemeInfo> frameList = new ArrayList();

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        initListener();
        initObserver();
        ((CheckFrameVModel) this.mViewModel).queryFrameThemeList();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((CheckFrameVModel) this.mViewModel).cancelCountdown();
    }

    private void initView() {
        ((FragmentCheckFrameBinding) this.mViewBinding).rvTheme.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentCheckFrameBinding) this.mViewBinding).rvTheme.setAdapter(this.themeAdapter);
        ((FragmentCheckFrameBinding) this.mViewBinding).rvTheme.setItemAnimator(null);
        ((FragmentCheckFrameBinding) this.mViewBinding).recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentCheckFrameBinding) this.mViewBinding).recyclerView.setAdapter(this.frameAdapter);
    }

    private void initListener() {
        ((FragmentCheckFrameBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m911x3bfd6ff0(view);
            }
        });
        this.themeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m912xb1779631(baseQuickAdapter, view, i);
            }
        });
    }

    void m911x3bfd6ff0(View view) {
        requireActivity().onBackPressed();
    }

    void m912xb1779631(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SoundHelper.getInstance().playSoundEffect(1);
        if (i == this.lastThemeIndex) {
            return;
        }
        changeTheme(i);
    }

    private void initObserver() {
        ((CheckFrameVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m913x332ebe94((Integer) obj);
            }
        });
        ((CheckFrameVModel) this.mViewModel).getFrameThemeListLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m915x1e230b16((List) obj);
            }
        });
    }

    void m913x332ebe94(Integer num) {
        ((FragmentCheckFrameBinding) this.mViewBinding).tvCountdown.setText(String.valueOf(num));
        if (num.intValue() == 0) {
            requireActivity().onBackPressed();
        }
    }

    void m915x1e230b16(List list) {
        this.frameList.clear();
        this.frameList.addAll(list);
        this.themeAdapter.submitList(this.frameList);
        if (this.frameList.isEmpty()) {
            return;
        }
        ((FragmentCheckFrameBinding) this.mViewBinding).rvTheme.smoothScrollToPosition(0);
        ((FragmentCheckFrameBinding) this.mViewBinding).rvTheme.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m914xa8a8e4d5();
            }
        });
    }

    void m914xa8a8e4d5() {
        changeTheme(0);
    }

    private void changeTheme(int i) {
        this.themeAdapter.setSelectedIndex(i);
        this.themeAdapter.notifyItemChanged(this.lastThemeIndex);
        this.themeAdapter.notifyItemChanged(i);
        this.lastThemeIndex = i;
        this.frameAdapter.submitList(this.frameList.get(i).getFrameInfoList());
        ((FragmentCheckFrameBinding) this.mViewBinding).recyclerView.scrollToPosition(0);
        ((CheckFrameVModel) this.mViewModel).startCountdown(300);
    }
}
