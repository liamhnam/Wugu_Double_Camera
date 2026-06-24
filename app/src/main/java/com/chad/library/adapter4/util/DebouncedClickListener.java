package com.chad.library.adapter4.util;

import android.view.View;
import com.chad.library.adapter4.BaseQuickAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\"\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J*\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J*\u0010\u0011\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J*\u0010\u0012\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H$R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/chad/library/adapter4/util/DebouncedClickListener;", "T", "", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemClickListener;", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemChildClickListener;", "interval", "", "(J)V", "mLastClickTime", "onClick", "", "adapter", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "view", "Landroid/view/View;", "position", "", "onItemClick", "onSingleClick", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
abstract class DebouncedClickListener<T> implements BaseQuickAdapter.OnItemClickListener<T>, BaseQuickAdapter.OnItemChildClickListener<T> {
    private final long interval;
    private long mLastClickTime;

    protected abstract void onSingleClick(BaseQuickAdapter<T, ?> adapter, View view, int position);

    public DebouncedClickListener(long j) {
        this.interval = j;
    }

    @Override
    public void onClick(BaseQuickAdapter<T, ?> adapter, View view, int position) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.mLastClickTime;
        if (j >= this.interval || j < 0) {
            this.mLastClickTime = jCurrentTimeMillis;
            onSingleClick(adapter, view, position);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter<T, ?> adapter, View view, int position) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.mLastClickTime;
        if (j >= this.interval || j < 0) {
            this.mLastClickTime = jCurrentTimeMillis;
            onSingleClick(adapter, view, position);
        }
    }
}
