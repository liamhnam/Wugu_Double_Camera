package com.wugu.doublecamera.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;
import java.lang.reflect.ParameterizedType;

public abstract class ABaseAdapter<VB extends ViewBinding, T> extends BaseQuickAdapter<T, VBViewHolder<VB>> {
    static final boolean $assertionsDisabled = false;

    @Override
    public VBViewHolder<VB> onCreateViewHolder(Context context, ViewGroup viewGroup, int i) {
        ViewBinding viewBinding = null;
        try {
            viewBinding = (ViewBinding) ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getMethod("inflate", LayoutInflater.class, ViewGroup.class, Boolean.TYPE).invoke(null, LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new VBViewHolder<>(viewBinding, viewBinding.getRoot());
    }

    public static class VBViewHolder<VB extends ViewBinding> extends QuickViewHolder {
        private final VB mVB;

        public VBViewHolder(VB vb, View view) {
            super(view);
            this.mVB = vb;
        }

        public VB getVBinding() {
            return this.mVB;
        }
    }
}
