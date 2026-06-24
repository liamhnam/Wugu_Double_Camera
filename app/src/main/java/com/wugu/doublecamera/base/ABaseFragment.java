package com.wugu.doublecamera.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.wugu.doublecamera.dialog.LoadingDialog;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ABaseFragment<VB extends ViewBinding, VM extends ViewModel> extends Fragment implements View.OnClickListener {
    static final boolean $assertionsDisabled = false;
    private LoadingDialog mLoadingDialog;
    protected VB mViewBinding;
    protected VM mViewModel;

    @Override
    public void onClick(View view) {
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Type genericSuperclass;
        this.mLoadingDialog = new LoadingDialog(requireContext());
        try {
            genericSuperclass = getClass().getGenericSuperclass();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (genericSuperclass instanceof ParameterizedType) {
            this.mViewBinding = (VB) ((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]).getMethod("inflate", LayoutInflater.class, ViewGroup.class, Boolean.TYPE).invoke(null, getLayoutInflater(), viewGroup, false);
            this.mViewModel = (VM) new ViewModelProvider(this).get((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1]);
            this.mViewBinding.getRoot().setOnClickListener(this);
            return this.mViewBinding.getRoot();
        }
        throw new NullPointerException("null ParameterizedType type");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mViewBinding = null;
    }

    protected void showLoadAnim(String str) {
        this.mLoadingDialog.setText(str);
        this.mLoadingDialog.startAnim();
    }

    protected void setLoadImage(int i) {
        this.mLoadingDialog.setImage(i);
    }

    protected void dismissLoadAnim() {
        this.mLoadingDialog.stopAnim();
    }
}
