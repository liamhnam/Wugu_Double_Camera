package com.wugu.doublecamera.main;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.adapter.PrintUsbAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.databinding.FragmentPrintUsbDriverBinding;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.dialog.PrintUsbDialog;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.main.p025vm.PrintUsbVModel;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.ToastHelper;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class PrintUsbDriverFragment extends ABaseFragment<FragmentPrintUsbDriverBinding, PrintUsbVModel> {
    private final PrintUsbAdapter printUsbAdapter = new PrintUsbAdapter();

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        initListener();
        initObserver();
        ((PrintUsbVModel) this.mViewModel).startCountdown(120);
        ((PrintUsbVModel) this.mViewModel).queryUsbPhotoList();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((PrintUsbVModel) this.mViewModel).cancelCountdown();
    }

    private void initView() {
        ViewUtil.setImageBackground(((FragmentPrintUsbDriverBinding) this.mViewBinding).btnBack, UiPosIndexEnum.ADMIN_BTN_BACK);
        ((FragmentPrintUsbDriverBinding) this.mViewBinding).rvPhoto.setLayoutManager(new GridLayoutManager(requireContext(), 8));
        ((FragmentPrintUsbDriverBinding) this.mViewBinding).rvPhoto.setAdapter(this.printUsbAdapter);
        ((FragmentPrintUsbDriverBinding) this.mViewBinding).rvPhoto.setItemAnimator(null);
    }

    private void initListener() {
        ((FragmentPrintUsbDriverBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1006xbc8ddca(view);
            }
        });
        this.printUsbAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1007x4f53fb8b(baseQuickAdapter, view, i);
            }
        });
    }

    void m1006xbc8ddca(View view) {
        requireActivity().onBackPressed();
    }

    void m1007x4f53fb8b(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        showSelectedPhotoDialog(this.printUsbAdapter.getItem(i));
    }

    private void initObserver() {
        ((PrintUsbVModel) this.mViewModel).getCountDownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1008x56aa3a6e((Integer) obj);
            }
        });
        LiveData<List<String>> usbFileListLD = ((PrintUsbVModel) this.mViewModel).getUsbFileListLD();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final PrintUsbAdapter printUsbAdapter = this.printUsbAdapter;
        Objects.requireNonNull(printUsbAdapter);
        usbFileListLD.observe(viewLifecycleOwner, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                printUsbAdapter.submitList((List) obj);
            }
        });
    }

    void m1008x56aa3a6e(Integer num) {
        if (num.intValue() == 0) {
            requireActivity().onBackPressed();
        }
    }

    private void showSelectedPhotoDialog(final String str) {
        if (!new File(str).exists()) {
            ToastHelper.getInstance().showToast("文件不存在");
        } else {
            ((PrintUsbVModel) this.mViewModel).startCountdown(120);
            new PrintUsbDialog(requireContext(), str, new IIntListener() {
                @Override
                public final void setValue(int i) {
                    this.f$0.m1010x22ff4953(str, i);
                }
            }).show();
        }
    }

    void m1010x22ff4953(String str, int i) {
        ((PrintUsbVModel) this.mViewModel).printUsbPhoto(str, i, PrinterHelper.getInstance().isPrintPdf());
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1009x9be90dd1();
            }
        });
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.dismissLoadAnim();
            }
        }, ((long) i) * 25000);
    }

    void m1009x9be90dd1() {
        showLoadAnim("打印中...");
    }
}
