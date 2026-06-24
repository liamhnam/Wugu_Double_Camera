package com.wugu.doublecamera.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.wugu.doublecamera.dialog.LoadingDialog;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class ABaseActivity<VB extends ViewBinding, VM extends ViewModel> extends AppCompatActivity {
    private LoadingDialog mLoadingDialog;
    protected VB mViewBinding;
    protected VM mViewModel;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mLoadingDialog = new LoadingDialog(this);
        try {
            Type genericSuperclass = getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                VB vb = (VB) ((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]).getMethod("inflate", LayoutInflater.class).invoke(null, getLayoutInflater());
                this.mViewBinding = vb;
                setContentView(vb.getRoot());
                this.mViewModel = (VM) new ViewModelProvider(this).get((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1]);
                return;
            }
            throw new NullPointerException("null ParameterizedType type");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showLoadAnim(String str) {
        this.mLoadingDialog.setText(str);
        this.mLoadingDialog.startAnim();
    }

    protected void dismissLoadAnim() {
        this.mLoadingDialog.stopAnim();
    }
}
