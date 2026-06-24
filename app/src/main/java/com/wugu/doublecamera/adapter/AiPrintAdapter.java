package com.wugu.doublecamera.adapter;

import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseAdapter;
import com.wugu.doublecamera.bean.CertifyPrintItem;
import com.wugu.doublecamera.databinding.ItemAiPrintBinding;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import java.util.Locale;

public class AiPrintAdapter extends ABaseAdapter<ItemAiPrintBinding, CertifyPrintItem> {
    private String addIcPath;
    private String printTextIcPath;
    private String subIcPath;

    @Override
    public void onBindViewHolder(ABaseAdapter.VBViewHolder<ItemAiPrintBinding> vBViewHolder, int i, CertifyPrintItem certifyPrintItem) {
        if (certifyPrintItem == null) {
            return;
        }
        ItemAiPrintBinding itemAiPrintBinding = (ItemAiPrintBinding) vBViewHolder.getVBinding();
        Glide.with(itemAiPrintBinding.ivPrintCountText).load(this.printTextIcPath).into(itemAiPrintBinding.ivPrintCountText);
        Glide.with(itemAiPrintBinding.ivAdd).load(this.addIcPath).into(itemAiPrintBinding.ivAdd);
        Glide.with(itemAiPrintBinding.ivSub).load(this.subIcPath).into(itemAiPrintBinding.ivSub);
        itemAiPrintBinding.tvPrintCount.setText(String.valueOf(certifyPrintItem.printCount));
        if (!TextUtils.isEmpty(certifyPrintItem.filePath)) {
            itemAiPrintBinding.tvLoading.setVisibility(8);
            Glide.with(itemAiPrintBinding.ivFrameExample).load(certifyPrintItem.filePath).into(itemAiPrintBinding.ivFrameExample);
        } else {
            itemAiPrintBinding.tvLoading.setText(getContext().getString(C1910R.string.generating) + String.format(Locale.getDefault(), "(%d%%)", Integer.valueOf(certifyPrintItem.aiPercent)));
        }
        if (certifyPrintItem.price > 0) {
            itemAiPrintBinding.tvPrintPrice.setVisibility(0);
            itemAiPrintBinding.tvPrintPrice.setText(itemAiPrintBinding.getRoot().getContext().getString(C1910R.string.print_price, StringUtil.getPriceStr(certifyPrintItem.price)));
        } else {
            itemAiPrintBinding.tvPrintPrice.setVisibility(8);
        }
        ViewUtil.setUiLocate(itemAiPrintBinding.getRoot(), UiPosIndexEnum.CERTIFY_PRINT_ITEM_BG);
    }

    public void setIcPath(String str, String str2, String str3) {
        this.printTextIcPath = str;
        this.addIcPath = str2;
        this.subIcPath = str3;
    }
}
