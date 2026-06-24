package com.wugu.doublecamera.main;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.adapter.CertifyPrintAdapter;
import com.wugu.doublecamera.base.ABaseFragment;
import com.wugu.doublecamera.bean.CertifyPrintItem;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.FragmentCertifyPrintBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.CertifyPrintVModel;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.utils.AnimUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ToastHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class CertifyPrintFragment extends ABaseFragment<FragmentCertifyPrintBinding, CertifyPrintVModel> {
    private ValueAnimator animPhotoQrCodeLoading;
    private final CertifyPrintAdapter certifyPrintAdapter = new CertifyPrintAdapter();
    private FrameInfo frameInfo;
    private boolean isDefaultPrint;
    private MainVModel mainVModel;
    private int paymentPrice;
    private int printStep;
    private int totalCount;

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
        ((CertifyPrintVModel) this.mViewModel).cancelCountdown();
        this.mainVModel = null;
    }

    private void initView() {
        ((FragmentCertifyPrintBinding) this.mViewBinding).recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((FragmentCertifyPrintBinding) this.mViewBinding).recyclerView.setAdapter(this.certifyPrintAdapter);
        ((FragmentCertifyPrintBinding) this.mViewBinding).recyclerView.setItemAnimator(null);
        ViewUtil.setUiLocate(((FragmentCertifyPrintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.CERTIFY_BG_PREVIEW);
        ViewUtil.setUiLocate(((FragmentCertifyPrintBinding) this.mViewBinding).layoutCountdown, UiPosIndexEnum.PRINT_COUNTDOWN);
        try {
            this.certifyPrintAdapter.setIcPath(((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.CERTIFY_PRINT_COUNT_IC)))).resPath, ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.CERTIFY_ADD_IC)))).resPath, ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.CERTIFY_SUB_IC)))).resPath);
            String str = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(318))).resPath;
            String str2 = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_BACK_HOME)))).resPath;
            String str3 = ((UiPosition) Objects.requireNonNull(App.mUiPosMap.get(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_PRINT)))).resPath;
            ViewUtil.setViewGroupBg(((FragmentCertifyPrintBinding) this.mViewBinding).layoutPayment, str);
            ViewUtil.setImageDrawable(((FragmentCertifyPrintBinding) this.mViewBinding).btnBack, str2);
            ViewUtil.setImageDrawable(((FragmentCertifyPrintBinding) this.mViewBinding).btnPrint, str3);
        } catch (NullPointerException unused) {
        }
    }

    private void initData() {
        startCountdown(SpManager.getInstance().getPrintStartTime());
        this.printStep = 1;
    }

    private void initObserver() {
        ((CertifyPrintVModel) this.mViewModel).getCountdownLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m904xea91b4c2((Integer) obj);
            }
        });
        ((CertifyPrintVModel) this.mViewModel).getPhotoQrcodeLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m905xea1b4ec3((String) obj);
            }
        });
        this.mainVModel.chooseFrameOkLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m906xe9a4e8c4((String) obj);
            }
        });
        this.mainVModel.photoUrlLD.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m907xe92e82c5((List) obj);
            }
        });
        this.mainVModel.getPayStepLD().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m908xe8b81cc6((Integer) obj);
            }
        });
    }

    void m904xea91b4c2(Integer num) {
        if (num.intValue() < 0) {
            ((FragmentCertifyPrintBinding) this.mViewBinding).layoutCountdown.setCount(0);
        } else {
            ((FragmentCertifyPrintBinding) this.mViewBinding).layoutCountdown.setCount(num.intValue());
        }
        if (num.intValue() == 0) {
            if (this.printStep == 1) {
                startPrint(true);
            } else {
                ((FragmentCertifyPrintBinding) this.mViewBinding).btnBack.performClick();
            }
        }
    }

    void m905xea1b4ec3(String str) {
        cancelPhotoQrCodeAnim();
        ((FragmentCertifyPrintBinding) this.mViewBinding).ivPhotoQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(str, 300, null));
    }

    void m906xe9a4e8c4(String str) {
        this.frameInfo = OrderManager.getOrderFrameInfo();
    }

    void m907xe92e82c5(List list) {
        if (list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        String absolutePath = requireContext().getFilesDir().getAbsolutePath();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = absolutePath + MqttTopic.TOPIC_LEVEL_SEPARATOR + str;
            if (!str.startsWith("plate_")) {
                arrayList.add(new CertifyPrintItem(str2, 0));
            }
        }
        if (!arrayList.isEmpty() && arrayList.size() > App.mCertifySelectIndex) {
            ((CertifyPrintItem) arrayList.get(App.mCertifySelectIndex)).printCount = 1;
        }
        this.certifyPrintAdapter.submitList(arrayList);
    }

    void m908xe8b81cc6(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 3) {
            startPrint(false);
        } else {
            if (iIntValue != 5) {
                return;
            }
            ((FragmentCertifyPrintBinding) this.mViewBinding).layoutPayment.setBalance(OrderManager.mBalance);
            startCountdown(SpManager.getInstance().getPaymentTime());
        }
    }

    private void initListener() {
        ((FragmentCertifyPrintBinding) this.mViewBinding).btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m900xf73cb525(view);
            }
        });
        ((FragmentCertifyPrintBinding) this.mViewBinding).btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m901xf6c64f26(view);
            }
        });
        this.certifyPrintAdapter.addOnItemChildClickListener(C1910R.id.iv_add, new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m902xf64fe927(baseQuickAdapter, view, i);
            }
        });
        this.certifyPrintAdapter.addOnItemChildClickListener(C1910R.id.iv_sub, new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m903xf5d98328(baseQuickAdapter, view, i);
            }
        });
    }

    void m900xf73cb525(View view) {
        clickPrint();
    }

    void m901xf6c64f26(View view) {
        if (this.printStep == 2) {
            OrderManager.mPrintOrderId = null;
            this.mainVModel.stopCheckOrderTask();
            showFrame();
            return;
        }
        requireActivity().onBackPressed();
    }

    void m902xf64fe927(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnimUtil.startBtnClickAnim(view);
        checkSelectedPrice(i, true);
    }

    void m903xf5d98328(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnimUtil.startBtnClickAnim(view);
        checkSelectedPrice(i, false);
    }

    private void lambda$initListener$9(View view) {
        startPrint(false);
    }

    private void startCountdown(int i) {
        ((CertifyPrintVModel) this.mViewModel).startCountdown(i);
        ((FragmentCertifyPrintBinding) this.mViewBinding).layoutCountdown.setCount(i);
    }

    private void checkSelectedPrice(int i, boolean z) {
        boolean z2;
        CertifyPrintItem item = this.certifyPrintAdapter.getItem(i);
        if (item == null || this.frameInfo == null) {
            return;
        }
        if (z) {
            item.printCount = Math.min(5, item.printCount + 1);
        } else {
            item.printCount = Math.max(0, item.printCount - 1);
            if (item.printCount == 0) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.certifyPrintAdapter.getItemCount()) {
                        z2 = false;
                        break;
                    }
                    CertifyPrintItem item2 = this.certifyPrintAdapter.getItem(i2);
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
        for (int i3 = 0; i3 < this.certifyPrintAdapter.getItemCount(); i3++) {
            CertifyPrintItem item3 = this.certifyPrintAdapter.getItem(i3);
            if (item3 != null) {
                item3.price = 0;
                if (this.paymentPrice > 0) {
                    item3.price = item3.printCount * this.frameInfo.getAddPrice();
                } else if (this.totalCount + item3.printCount > this.frameInfo.getPrintCount()) {
                    item3.price = ((this.totalCount + item3.printCount) - this.frameInfo.getPrintCount()) * this.frameInfo.getAddPrice();
                }
                int i4 = this.totalCount + item3.printCount;
                this.totalCount = i4;
                this.paymentPrice = Math.max(0, (i4 - this.frameInfo.getPrintCount()) * this.frameInfo.getAddPrice());
                this.certifyPrintAdapter.notifyItemChanged(i3);
            }
        }
    }

    private void clickPrint() {
        FrameInfo frameInfo = this.frameInfo;
        if (frameInfo == null) {
            return;
        }
        if (OrderManager.checkAddPrintBalance(frameInfo.getAddPrice(), this.totalCount - this.frameInfo.getPrintCount())) {
            startPrint(false);
        } else {
            showAddPrintPayment();
        }
    }

    private void showFrame() {
        this.printStep = 1;
        ((FragmentCertifyPrintBinding) this.mViewBinding).recyclerView.setVisibility(0);
        ((FragmentCertifyPrintBinding) this.mViewBinding).btnPrint.setVisibility(0);
        ((FragmentCertifyPrintBinding) this.mViewBinding).layoutPayment.setVisibility(8);
        ((FragmentCertifyPrintBinding) this.mViewBinding).btnBack.setVisibility(8);
        startCountdown(SpManager.getInstance().getPrintStartTime());
    }

    private void showAddPrintPayment() {
        this.printStep = 2;
        ((FragmentCertifyPrintBinding) this.mViewBinding).recyclerView.setVisibility(8);
        ((FragmentCertifyPrintBinding) this.mViewBinding).btnPrint.setVisibility(8);
        ((FragmentCertifyPrintBinding) this.mViewBinding).layoutPayment.setVisibility(0);
        ((FragmentCertifyPrintBinding) this.mViewBinding).btnBack.setVisibility(0);
        ((FragmentCertifyPrintBinding) this.mViewBinding).layoutPayment.setPriceStr(OrderManager.getShowPriceStr(this.paymentPrice));
        ((FragmentCertifyPrintBinding) this.mViewBinding).layoutPayment.setBalance(OrderManager.mBalance);
        startCountdown(SpManager.getInstance().getPaymentTime());
        OrderManager.createPrintOrder(this.totalCount - this.frameInfo.getPrintCount(), this.frameInfo.getAddPrice(), false, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m910x1d324db7(str);
            }
        });
    }

    void m910x1d324db7(final String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m909x1da8b3b6(str);
            }
        });
    }

    void m909x1da8b3b6(String str) {
        ((FragmentCertifyPrintBinding) this.mViewBinding).layoutPayment.setQrCodeStr(str);
        this.mainVModel.startCheckPrintOrderTask();
        startCountdown(SpManager.getInstance().getPaymentTime());
    }

    private void showPrinting() {
        ((FragmentCertifyPrintBinding) this.mViewBinding).recyclerView.setVisibility(8);
        ((FragmentCertifyPrintBinding) this.mViewBinding).btnPrint.setVisibility(8);
        ((FragmentCertifyPrintBinding) this.mViewBinding).layoutPayment.setVisibility(8);
        ((FragmentCertifyPrintBinding) this.mViewBinding).btnBack.setVisibility(0);
        ((FragmentCertifyPrintBinding) this.mViewBinding).layoutPhotoQrcode.setVisibility(0);
        ViewUtil.setUiLocate(((FragmentCertifyPrintBinding) this.mViewBinding).getRoot(), UiPosIndexEnum.CERTIFY_PRINT_BG);
        ValueAnimator rotationAnim1 = AnimUtil.getRotationAnim1(((FragmentCertifyPrintBinding) this.mViewBinding).ivPhotoQrcode, 490);
        this.animPhotoQrCodeLoading = rotationAnim1;
        rotationAnim1.start();
        if (this.isDefaultPrint) {
            this.totalCount = this.frameInfo.getPrintCount();
        }
        startCountdown((this.totalCount * 24) + 30);
    }

    private void cancelPhotoQrCodeAnim() {
        ValueAnimator valueAnimator = this.animPhotoQrCodeLoading;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animPhotoQrCodeLoading.removeAllListeners();
            this.animPhotoQrCodeLoading.cancel();
        }
        ((FragmentCertifyPrintBinding) this.mViewBinding).ivPhotoQrcode.setRotation(0.0f);
    }

    private void startPrint(boolean z) {
        this.isDefaultPrint = z;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<CertifyPrintItem> it = this.certifyPrintAdapter.getItems().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CertifyPrintItem next = it.next();
            if (z) {
                if (next.printCount > 0) {
                    arrayList.add(next.filePath);
                    arrayList2.add(Integer.valueOf(this.frameInfo.getPrintCount()));
                    break;
                }
            } else if (next.printCount > 0) {
                arrayList.add(next.filePath);
                arrayList2.add(Integer.valueOf(next.printCount));
            }
        }
        this.printStep = 3;
        showPrinting();
        ((CertifyPrintVModel) this.mViewModel).print(arrayList, (Integer[]) arrayList2.toArray(new Integer[0]));
    }
}
