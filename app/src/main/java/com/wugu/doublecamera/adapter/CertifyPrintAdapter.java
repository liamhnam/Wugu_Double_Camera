package com.wugu.doublecamera.adapter;

import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.CertifyPrintItem;
import com.wugu.doublecamera.databinding.ItemCertifyPrintBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;

public class CertifyPrintAdapter extends ABaseAdapter<ItemCertifyPrintBinding, CertifyPrintItem> {
    private String addIcPath;
    private String printTextIcPath;
    private String subIcPath;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemCertifyPrintBinding> vBViewHolder, int i, CertifyPrintItem certifyPrintItem) {
        if (certifyPrintItem == null) {
            return;
        }
        ItemCertifyPrintBinding itemCertifyPrintBinding = (ItemCertifyPrintBinding) vBViewHolder.getVBinding();
        Glide.with(itemCertifyPrintBinding.ivPrintCountText).load(this.printTextIcPath).into(itemCertifyPrintBinding.ivPrintCountText);
        Glide.with(itemCertifyPrintBinding.ivAdd).load(this.addIcPath).into(itemCertifyPrintBinding.ivAdd);
        Glide.with(itemCertifyPrintBinding.ivSub).load(this.subIcPath).into(itemCertifyPrintBinding.ivSub);
        itemCertifyPrintBinding.tvPrintCount.setText(String.valueOf(certifyPrintItem.printCount));
        Glide.with(itemCertifyPrintBinding.ivFrameExample).load(certifyPrintItem.filePath).into(itemCertifyPrintBinding.ivFrameExample);
        if (certifyPrintItem.price > 0) {
            itemCertifyPrintBinding.tvPrintPrice.setVisibility(0);
            itemCertifyPrintBinding.tvPrintPrice.setText(itemCertifyPrintBinding.getRoot().getContext().getString(C1910R.string.print_price, StringUtil.getPriceStr(certifyPrintItem.price)));
        } else {
            itemCertifyPrintBinding.tvPrintPrice.setVisibility(8);
        }
        ViewUtil.setUiLocate(itemCertifyPrintBinding.getRoot(), UiPosIndexEnum.CERTIFY_PRINT_ITEM_BG);
    }

    public void setIcPath(String str, String str2, String str3) {
        this.printTextIcPath = str;
        this.addIcPath = str2;
        this.subIcPath = str3;
    }
}
