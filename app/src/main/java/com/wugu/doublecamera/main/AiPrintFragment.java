package com.wugu.doublecamera.main;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.AiPrintAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.CertifyPrintItem;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentAiPrintBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.AiPrintVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ToastHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class AiPrintFragment extends ABaseFragment<FragmentAiPrintBinding, AiPrintVModel> {
    private ValueAnimator animPhotoQrCodeLoading;
    private boolean isDefaultPrint;
    private MainVModel mainVModel;
    private int paymentPrice;
    private int printStep;
    private int totalCount;
    private final AiPrintAdapter printAdapter = new AiPrintAdapter();
    private final int addPrice = SpManager.getInstance().getAiAddPrice();

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
        OrderManager.clearOrderInfo();
        ((AiPrintVModel) this.mViewModel).cancelCountdown();
        this.mainVModel = null;
    }

    private void initView() {
        ((FragmentAiPrintBinding) this.mViewBinding).recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentAiPrintBinding) this.mViewBinding).recyclerView.setAdapter(this.printAdapter);
        ((FragmentAiPrintBinding) this.mViewBinding).recyclerView.setItemAnimator(null);
        ViewUtil.setUiLocate(((FragmentAiPrintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.CERTIFY_BG_PREVIEW);
        ViewUtil.setUiLocate(((FragmentAiPrintBinding) this.mViewBinding).layoutCountdown, UiPosIndexEnum.PRINT_COUNTDOWN);
        try {
            this.printAdapter.setIcPath(((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.CERTIFY_PRINT_COUNT_IC)))).resPath, ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.CERTIFY_ADD_IC)))).resPath, ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.CERTIFY_SUB_IC)))).resPath);
            String str = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(318))).resPath;
            String str2 = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_BACK_HOME)))).resPath;
            String str3 = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(19))).resPath;
            String str4 = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_PRINT)))).resPath;
            ViewUtil.setViewGroupBg(((FragmentAiPrintBinding) this.mViewBinding).layoutPayment, str);
            ViewUtil.setImageDrawable(((FragmentAiPrintBinding) this.mViewBinding).btnBack, str2);
            ViewUtil.setImageDrawable(((FragmentAiPrintBinding) this.mViewBinding).btnClose, str3);
            ViewUtil.setImageDrawable(((FragmentAiPrintBinding) this.mViewBinding).btnPrint, str4);
        } catch (NullPointerException unused) {
        }
        ((FragmentAiPrintBinding) this.mViewBinding).btnPrint.setEnabled(false);
        ViewUtil.setImageViewGray(((FragmentAiPrintBinding) this.mViewBinding).btnPrint, true, 0.0f);
    }

    private void initData() {
        startCountdown(SpManager.getInstance().getPrintStartTime());
        this.printStep = 1;
        ArrayList arrayList = new ArrayList();
        String[] strArrSplit = App.mSbAiPath.toString().split(",");
        for (int i = 0; i < strArrSplit.length; i++) {
            CertifyPrintItem certifyPrintItem = new CertifyPrintItem(strArrSplit[i], 0, 100);
            if (i == 0) {
                certifyPrintItem.printCount = 1;
            }
            arrayList.add(certifyPrintItem);
            if (!TextUtils.isEmpty(certifyPrintItem.filePath) && !((FragmentAiPrintBinding) this.mViewBinding).btnPrint.isEnabled()) {
                ((FragmentAiPrintBinding) this.mViewBinding).btnPrint.setEnabled(true);
                ViewUtil.setImageViewGray(((FragmentAiPrintBinding) this.mViewBinding).btnPrint, false, 1.0f);
            }
        }
        this.printAdapter.submitList(arrayList);
    }

    private void initObserver() {
        ((AiPrintVModel) this.mViewModel).getCountdownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1628lambda$initObserver$0$comwugudoublecameramainAiPrintFragment((Integer) obj);
            }
        });
        ((AiPrintVModel) this.mViewModel).getPhotoQrcodeLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1629lambda$initObserver$1$comwugudoublecameramainAiPrintFragment((String) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1630lambda$initObserver$2$comwugudoublecameramainAiPrintFragment((Integer) obj);
            }
        });
    }

    void m1628lambda$initObserver$0$comwugudoublecameramainAiPrintFragment(Integer num) {
        if (num.intValue() < 0) {
            ((FragmentAiPrintBinding) this.mViewBinding).layoutCountdown.setCount(0);
        } else {
            ((FragmentAiPrintBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        }
        if (num.intValue() == 0) {
            if (this.printStep == 1) {
                startPrint(true);
            } else {
                ((FragmentAiPrintBinding) this.mViewBinding).btnBack.performClick();
            }
        }
    }

    void m1629lambda$initObserver$1$comwugudoublecameramainAiPrintFragment(String str) {
        cancelPhotoQrCodeAnim();
        ((FragmentAiPrintBinding) this.mViewBinding).ivPhotoQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 300, null));
    }

    void m1630lambda$initObserver$2$comwugudoublecameramainAiPrintFragment(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 3) {
            startPrint(false);
        } else {
            if (iIntValue != 5) {
                return;
            }
            ((FragmentAiPrintBinding) this.mViewBinding).layoutPayment.setBalance(OrderManager.mBalance);
            startCountdown(SpManager.getInstance().getPaymentTime());
        }
    }

    private void initListener() {
        ((FragmentAiPrintBinding) this.mViewBinding).btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1622lambda$initListener$3$comwugudoublecameramainAiPrintFragment(view);
            }
        });
        ((FragmentAiPrintBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1623lambda$initListener$4$comwugudoublecameramainAiPrintFragment(view);
            }
        });
        ((FragmentAiPrintBinding) this.mViewBinding).btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1624lambda$initListener$5$comwugudoublecameramainAiPrintFragment(view);
            }
        });
        this.printAdapter.addOnItemChildClickListener(C1910R.id.iv_add, new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1625lambda$initListener$6$comwugudoublecameramainAiPrintFragment(baseQuickAdapter, view, i);
            }
        });
        this.printAdapter.addOnItemChildClickListener(C1910R.id.iv_sub, new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1626lambda$initListener$7$comwugudoublecameramainAiPrintFragment(baseQuickAdapter, view, i);
            }
        });
        this.printAdapter.addOnItemChildClickListener(C1910R.id.iv_frame_example, new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m1627lambda$initListener$8$comwugudoublecameramainAiPrintFragment(baseQuickAdapter, view, i);
            }
        });
    }

    void m1622lambda$initListener$3$comwugudoublecameramainAiPrintFragment(View view) {
        clickEnter();
    }

    void m1623lambda$initListener$4$comwugudoublecameramainAiPrintFragment(View view) {
        if (this.printStep == 2) {
            OrderManager.mPrintOrderId = null;
            this.mainVModel.stopCheckOrderTask();
            showFrame();
        } else {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            if (fragmentActivityRequireActivity instanceof MainActivity) {
                ((MainActivity) fragmentActivityRequireActivity).backToHomeFragment();
            }
        }
    }

    void m1624lambda$initListener$5$comwugudoublecameramainAiPrintFragment(View view) {
        ((FragmentAiPrintBinding) this.mViewBinding).ivDetail.recycle();
        ((FragmentAiPrintBinding) this.mViewBinding).ivDetail.setVisibility(8);
        ((FragmentAiPrintBinding) this.mViewBinding).btnClose.setVisibility(8);
    }

    void m1625lambda$initListener$6$comwugudoublecameramainAiPrintFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnimUtil.startBtnClickAnim(view);
        checkSelectedPrice(i, true);
    }

    void m1626lambda$initListener$7$comwugudoublecameramainAiPrintFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnimUtil.startBtnClickAnim(view);
        checkSelectedPrice(i, false);
    }

    void m1627lambda$initListener$8$comwugudoublecameramainAiPrintFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (this.printAdapter.getItem(i) == null) {
            return;
        }
        showBigPhoto(this.printAdapter.getItem(i).filePath);
    }

    private void lambda$initListener$9(View view) {
        startPrint(false);
    }

    private void startCountdown(int i) {
        ((AiPrintVModel) this.mViewModel).startCountdown(i);
        ((FragmentAiPrintBinding) this.mViewBinding).layoutCountdown.setCount(i);
    }

    private void showBigPhoto(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ((FragmentAiPrintBinding) this.mViewBinding).ivDetail.setImage(ImageSource.uri(str));
        ((FragmentAiPrintBinding) this.mViewBinding).ivDetail.setVisibility(0);
        ((FragmentAiPrintBinding) this.mViewBinding).btnClose.setVisibility(0);
    }

    private void checkSelectedPrice(int i, boolean z) {
        boolean z2;
        CertifyPrintItem item = this.printAdapter.getItem(i);
        if (item == null || TextUtils.isEmpty(item.filePath)) {
            return;
        }
        if (z) {
            item.printCount = Math.min(5, item.printCount + 1);
        } else {
            item.printCount = Math.max(0, item.printCount - 1);
            if (item.printCount == 0) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.printAdapter.getItemCount()) {
                        z2 = false;
                        break;
                    }
                    CertifyPrintItem item2 = this.printAdapter.getItem(i2);
                    if (i2 != i && item2 != null && item2.printCount > 0) {
                        z2 = true;
                        break;
                    }
                    i2++;
                }
                if (!z2) {
                    item.printCount = 1;
                    ToastHelper.getInstance().showToast(getString(C1910R.string.at_least_one));
                }
            }
        }
        this.totalCount = 0;
        this.paymentPrice = 0;
        for (int i3 = 0; i3 < this.printAdapter.getItemCount(); i3++) {
            CertifyPrintItem item3 = this.printAdapter.getItem(i3);
            if (item3 != null) {
                item3.price = 0;
                if (this.paymentPrice > 0) {
                    item3.price = item3.printCount * this.addPrice;
                } else if (this.totalCount + item3.printCount > 1) {
                    item3.price = ((this.totalCount + item3.printCount) - 1) * this.addPrice;
                }
                int i4 = this.totalCount + item3.printCount;
                this.totalCount = i4;
                this.paymentPrice = Math.max(0, (i4 - 1) * this.addPrice);
                this.printAdapter.notifyItemChanged(i3);
            }
        }
    }

    private void clickEnter() {
        if (OrderManager.checkAddPrintBalance(this.addPrice, this.totalCount - 1)) {
            startPrint(false);
        } else {
            showAddPrintPayment();
        }
    }

    private void showFrame() {
        this.printStep = 1;
        ((FragmentAiPrintBinding) this.mViewBinding).recyclerView.setVisibility(0);
        ((FragmentAiPrintBinding) this.mViewBinding).btnPrint.setVisibility(0);
        ((FragmentAiPrintBinding) this.mViewBinding).layoutPayment.setVisibility(8);
        ((FragmentAiPrintBinding) this.mViewBinding).btnBack.setVisibility(8);
        startCountdown(SpManager.getInstance().getPrintStartTime());
    }

    private void showAddPrintPayment() {
        this.printStep = 2;
        ((FragmentAiPrintBinding) this.mViewBinding).recyclerView.setVisibility(8);
        ((FragmentAiPrintBinding) this.mViewBinding).btnPrint.setVisibility(8);
        ((FragmentAiPrintBinding) this.mViewBinding).layoutPayment.setVisibility(0);
        ((FragmentAiPrintBinding) this.mViewBinding).btnBack.setVisibility(0);
        ((FragmentAiPrintBinding) this.mViewBinding).layoutPayment.setPriceStr(StringUtil.getPriceStr(this.paymentPrice));
        ((FragmentAiPrintBinding) this.mViewBinding).layoutPayment.setBalance(OrderManager.mBalance);
        startCountdown(SpManager.getInstance().getPaymentTime());
        OrderManager.createPrintOrder(this.totalCount - 1, this.addPrice, false, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m883x428c0083(str);
            }
        });
    }

    void m883x428c0083(final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m882x7f9f9724(str);
            }
        });
    }

    void m882x7f9f9724(String str) {
        ((FragmentAiPrintBinding) this.mViewBinding).layoutPayment.setQrCodeStr(str);
        this.mainVModel.startCheckPrintOrderTask();
        startCountdown(SpManager.getInstance().getPaymentTime());
    }

    private void showPrinting() {
        ((FragmentAiPrintBinding) this.mViewBinding).recyclerView.setVisibility(8);
        ((FragmentAiPrintBinding) this.mViewBinding).btnPrint.setVisibility(8);
        ((FragmentAiPrintBinding) this.mViewBinding).layoutPayment.setVisibility(8);
        ((FragmentAiPrintBinding) this.mViewBinding).btnBack.setVisibility(0);
        ((FragmentAiPrintBinding) this.mViewBinding).btnBack.setEnabled(false);
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m884x354f5cd1();
            }
        }, 8000L);
        ((FragmentAiPrintBinding) this.mViewBinding).layoutPhotoQrcode.setVisibility(0);
        ViewUtil.setUiLocate(((FragmentAiPrintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.CERTIFY_PRINT_BG);
        if (this.isDefaultPrint) {
            this.totalCount = 1;
        }
        startCountdown((this.totalCount * 24) + 30);
    }

    void m884x354f5cd1() {
        ((FragmentAiPrintBinding) this.mViewBinding).btnBack.setEnabled(true);
    }

    private void startPrint(boolean z) {
        ((FragmentAiPrintBinding) this.mViewBinding).ivDetail.recycle();
        ((FragmentAiPrintBinding) this.mViewBinding).ivDetail.setVisibility(8);
        ((FragmentAiPrintBinding) this.mViewBinding).btnClose.setVisibility(8);
        ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(((FragmentAiPrintBinding) this.mViewBinding).ivPhotoQrcode, 490);
        this.animPhotoQrCodeLoading = rotationAnim1;
        rotationAnim1.start();
        this.isDefaultPrint = z;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<CertifyPrintItem> it = this.printAdapter.getItems().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CertifyPrintItem next = it.next();
            if (z) {
                if (next.printCount > 0) {
                    arrayList.add(next.filePath);
                    arrayList2.add(1);
                    break;
                }
            } else if (next.printCount > 0) {
                arrayList.add(next.filePath);
                arrayList2.add(Integer.valueOf(next.printCount));
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (CertifyPrintItem certifyPrintItem : this.printAdapter.getItems()) {
            if (!TextUtils.isEmpty(certifyPrintItem.filePath)) {
                arrayList3.add(certifyPrintItem.filePath);
            }
        }
        if (arrayList3.isEmpty()) {
            return;
        }
        ((AiPrintVModel) this.mViewModel).uploadOrderFiles(arrayList3);
        this.printStep = 3;
        showPrinting();
        ((AiPrintVModel) this.mViewModel).print(arrayList, (Integer[]) arrayList2.toArray(new Integer[0]));
    }

    private void cancelPhotoQrCodeAnim() {
        ValueAnimator valueAnimator = this.animPhotoQrCodeLoading;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animPhotoQrCodeLoading.removeAllListeners();
            this.animPhotoQrCodeLoading.cancel();
        }
        ((FragmentAiPrintBinding) this.mViewBinding).ivPhotoQrcode.setRotation(0.0f);
    }
}
