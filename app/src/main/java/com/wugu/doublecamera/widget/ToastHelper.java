package com.wugu.doublecamera.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.wugu.doublecamera.C1910R;

public class ToastHelper {
    private Context context;
    private Toast customToast;
    private final Handler mainHandler;
    private String pendingText;
    private Runnable showRunnable;

    private static class Holder {
        public static ToastHelper instance = new ToastHelper();

        private Holder() {
        }
    }

    private ToastHelper() {
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public static ToastHelper getInstance() {
        return Holder.instance;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public void showToast(String str) {
        this.pendingText = str;
        Runnable runnable = this.showRunnable;
        if (runnable != null) {
            this.mainHandler.removeCallbacks(runnable);
        }
        Toast toast = this.customToast;
        if (toast != null) {
            toast.cancel();
        }
        Runnable runnable2 = new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1729lambda$showToast$0$comwugudoublecamerawidgetToastHelper();
            }
        };
        this.showRunnable = runnable2;
        this.mainHandler.postDelayed(runnable2, 100L);
    }

    void m1729lambda$showToast$0$comwugudoublecamerawidgetToastHelper() {
        showCustomToast(this.pendingText);
    }

    private void showCustomToast(String str) {
        Toast toast = this.customToast;
        if (toast != null) {
            toast.cancel();
        }
        this.customToast = new Toast(this.context);
        View viewInflate = LayoutInflater.from(this.context).inflate(C1910R.layout.layout_toast, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(C1910R.id.toast_message)).setText(str);
        this.customToast.setView(viewInflate);
        this.customToast.setGravity(17, 0, 0);
        this.customToast.setDuration(0);
        this.customToast.show();
    }
}
