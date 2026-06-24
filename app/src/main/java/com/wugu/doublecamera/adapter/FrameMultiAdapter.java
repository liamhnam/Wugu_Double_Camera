package com.wugu.doublecamera.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.brother.sdk.print.pdl.PrintImageUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter4.BaseMultiItemAdapter;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.BuildConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.databinding.ItemFrameCertifyBinding;
import com.wugu.doublecamera.databinding.ItemFrameNormalBinding;
import com.wugu.doublecamera.databinding.ItemFrameUpprintBinding;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.QrCodeUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import java.io.File;
import java.util.List;

public class FrameMultiAdapter extends BaseMultiItemAdapter<FrameInfo> {
    public String bgPath;
    public String icCutPath;

    public FrameMultiAdapter() {
        addNormalVH();
        addCertifyVH();
        addUpPrintVH();
        addReplaceBgVH();
    }

    @Override
    protected int getItemViewType(int i, List<? extends FrameInfo> list) {
        return list.get(i).getFrameType();
    }

    private static class NormalVH extends RecyclerView.ViewHolder {
        public final ItemFrameNormalBinding viewBinding;

        public NormalVH(ItemFrameNormalBinding itemFrameNormalBinding) {
            super(itemFrameNormalBinding.getRoot());
            this.viewBinding = itemFrameNormalBinding;
        }
    }

    private static class CertifyVH extends RecyclerView.ViewHolder {
        public final ItemFrameCertifyBinding viewBinding;

        public CertifyVH(ItemFrameCertifyBinding itemFrameCertifyBinding) {
            super(itemFrameCertifyBinding.getRoot());
            this.viewBinding = itemFrameCertifyBinding;
        }
    }

    private static class UpPrintVH extends RecyclerView.ViewHolder {
        public final ItemFrameUpprintBinding viewBinding;

        public UpPrintVH(ItemFrameUpprintBinding itemFrameUpprintBinding) {
            super(itemFrameUpprintBinding.getRoot());
            this.viewBinding = itemFrameUpprintBinding;
        }
    }

    private void addNormalVH() {
        addItemType(0, new BaseMultiItemAdapter.OnMultiItemAdapterListener<FrameInfo, NormalVH>() {
            @Override
            public NormalVH onCreate(Context context, ViewGroup viewGroup, int i) {
                return new NormalVH(ItemFrameNormalBinding.inflate(LayoutInflater.from(context), viewGroup, false));
            }

            @Override
            public void onBind(NormalVH normalVH, int i, FrameInfo frameInfo) {
                if (frameInfo == null) {
                    return;
                }
                ViewUtil.setViewGroupBg(normalVH.viewBinding.getRoot(), FrameMultiAdapter.this.bgPath);
                if (App.mSystemMode == 4) {
                    normalVH.viewBinding.tvFramePrice.setVisibility(8);
                } else if (frameInfo.getPrice() <= 0 && AppUtil.getCPUSerial().equalsIgnoreCase("f2f110617a153812")) {
                    normalVH.viewBinding.tvFramePrice.setVisibility(8);
                } else {
                    normalVH.viewBinding.tvFramePrice.setText(StringUtil.getPriceStr(frameInfo.getPrice()));
                }
                if (FrameMultiAdapter.this.icCutPath == null) {
                    normalVH.viewBinding.ivCutIcon.setImageResource(C1910R.mipmap.frame_item_cut);
                } else {
                    ViewUtil.setImageDrawable(normalVH.viewBinding.ivCutIcon, FrameMultiAdapter.this.icCutPath);
                }
                if (frameInfo.isNeedCut()) {
                    normalVH.viewBinding.ivCutIcon.setVisibility(0);
                    normalVH.viewBinding.ivFrameExample.setBackground(null);
                    normalVH.viewBinding.ivFrameExample.setImageBitmap(null);
                    normalVH.viewBinding.ivFrameExample.setPadding(60, 0, 60, 0);
                    FrameMultiAdapter.this.loadCutBitmap(normalVH.viewBinding.ivFrameExample, frameInfo.getFramePath());
                    return;
                }
                normalVH.viewBinding.ivCutIcon.setVisibility(8);
                normalVH.viewBinding.ivFrameExample.setBackgroundColor(Color.parseColor("#E3E3E3"));
                normalVH.viewBinding.ivFrameExample.setPadding(0, 0, 0, 0);
                Glide.with(normalVH.viewBinding.ivFrameExample).load(frameInfo.getFramePath()).diskCacheStrategy(DiskCacheStrategy.ALL).override(PrintImageUtil.ROUND_ROTATE, 400).into(normalVH.viewBinding.ivFrameExample);
            }
        });
    }

    private void addCertifyVH() {
        addItemType(3, new BaseMultiItemAdapter.OnMultiItemAdapterListener<FrameInfo, CertifyVH>() {
            @Override
            public CertifyVH onCreate(Context context, ViewGroup viewGroup, int i) {
                return new CertifyVH(ItemFrameCertifyBinding.inflate(LayoutInflater.from(context), viewGroup, false));
            }

            @Override
            public void onBind(CertifyVH certifyVH, int i, FrameInfo frameInfo) {
                if (frameInfo == null) {
                    return;
                }
                ViewUtil.setViewGroupBg(certifyVH.viewBinding.getRoot(), FrameMultiAdapter.this.bgPath);
                certifyVH.viewBinding.tvFramePrice.setText(StringUtil.getPriceStr(frameInfo.getPrice()));
                String[] strArrSplit = frameInfo.getFrameName().split("-");
                if (strArrSplit.length == 2) {
                    certifyVH.viewBinding.tvFrameName.setText(strArrSplit[0]);
                    certifyVH.viewBinding.tvSize.setText(strArrSplit[1]);
                }
                Glide.with(certifyVH.viewBinding.ivFrameExample).load(frameInfo.getFramePath()).diskCacheStrategy(DiskCacheStrategy.ALL).override(PrintImageUtil.ROUND_ROTATE, 400).into(certifyVH.viewBinding.ivFrameExample);
            }
        });
    }

    private void addUpPrintVH() {
        addItemType(5, new BaseMultiItemAdapter.OnMultiItemAdapterListener<FrameInfo, UpPrintVH>() {
            @Override
            public UpPrintVH onCreate(Context context, ViewGroup viewGroup, int i) {
                return new UpPrintVH(ItemFrameUpprintBinding.inflate(LayoutInflater.from(context), viewGroup, false));
            }

            @Override
            public void onBind(UpPrintVH upPrintVH, int i, FrameInfo frameInfo) {
                if (frameInfo == null) {
                    return;
                }
                upPrintVH.viewBinding.ivQrcode.setImageBitmap(QrCodeUtil.getBitmapQrCode(BuildConfig.UP_PRINT_URL + App.mAppId, 400, null));
            }
        });
    }

    private void addReplaceBgVH() {
        addItemType(6, new BaseMultiItemAdapter.OnMultiItemAdapterListener<FrameInfo, NormalVH>() {
            @Override
            public NormalVH onCreate(Context context, ViewGroup viewGroup, int i) {
                return new NormalVH(ItemFrameNormalBinding.inflate(LayoutInflater.from(context), viewGroup, false));
            }

            @Override
            public void onBind(NormalVH normalVH, int i, FrameInfo frameInfo) {
                if (frameInfo == null) {
                    return;
                }
                ViewUtil.setViewGroupBg(normalVH.viewBinding.getRoot(), FrameMultiAdapter.this.bgPath);
                normalVH.viewBinding.tvFramePrice.setText(StringUtil.getPriceStr(frameInfo.getPrice()));
                if (FrameMultiAdapter.this.icCutPath == null) {
                    normalVH.viewBinding.ivCutIcon.setImageResource(C1910R.mipmap.frame_item_cut);
                } else {
                    ViewUtil.setImageDrawable(normalVH.viewBinding.ivCutIcon, FrameMultiAdapter.this.icCutPath);
                }
                if (frameInfo.isNeedCut()) {
                    normalVH.viewBinding.ivCutIcon.setVisibility(0);
                    normalVH.viewBinding.ivFrameExample.setBackground(null);
                    normalVH.viewBinding.ivFrameExample.setImageBitmap(null);
                    normalVH.viewBinding.ivFrameExample.setPadding(60, 0, 60, 0);
                    FrameMultiAdapter.this.loadCutBitmap(normalVH.viewBinding.ivFrameExample, frameInfo.getFramePath());
                    return;
                }
                normalVH.viewBinding.ivCutIcon.setVisibility(8);
                normalVH.viewBinding.ivFrameExample.setBackgroundColor(Color.parseColor("#E3E3E3"));
                normalVH.viewBinding.ivFrameExample.setPadding(0, 0, 0, 0);
                Glide.with(normalVH.viewBinding.ivFrameExample).load(frameInfo.getFramePath()).diskCacheStrategy(DiskCacheStrategy.ALL).override(PrintImageUtil.ROUND_ROTATE, 400).into(normalVH.viewBinding.ivFrameExample);
            }
        });
    }

    public void setIcCutRes(UiPosition uiPosition) {
        if (uiPosition == null || TextUtils.isEmpty(uiPosition.resPath)) {
            this.icCutPath = AppConfig.BASE_FOLDER + "default_ui/2-对半切两张.png";
        } else {
            this.icCutPath = uiPosition.resPath;
        }
        if (new File(this.icCutPath).exists()) {
            return;
        }
        this.icCutPath = null;
    }

    public void setBgItemRes(UiPosition uiPosition) {
        if (uiPosition != null) {
            this.bgPath = uiPosition.resPath;
        }
    }

    public void loadCutBitmap(final AppCompatImageView appCompatImageView, String str) {
        Glide.with(appCompatImageView).asBitmap().load(str).diskCacheStrategy(DiskCacheStrategy.ALL).override(300, 400).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                appCompatImageView.setImageBitmap(BitmapUtil.createSplitBitmap(bitmap, 5));
            }
        });
    }
}
