package com.chad.library.adapter4.util;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter4.BaseQuickAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aX\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000b\u001aN\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\r¨\u0006\u000e"}, m1293d2 = {"addOnDebouncedChildClick", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "T", "VH", "", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "id", "", "time", "", "block", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemChildClickListener;", "setOnDebouncedItemClick", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemClickListener;", "com.github.CymChad.brvah"}, m1294k = 2, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class ItemClickUtilsKt {
    public static BaseQuickAdapter setOnDebouncedItemClick$default(BaseQuickAdapter baseQuickAdapter, long j, BaseQuickAdapter.OnItemClickListener onItemClickListener, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 500;
        }
        return setOnDebouncedItemClick(baseQuickAdapter, j, onItemClickListener);
    }

    public static final <T, VH extends RecyclerView.ViewHolder> BaseQuickAdapter<T, VH> setOnDebouncedItemClick(BaseQuickAdapter<T, VH> baseQuickAdapter, long j, final BaseQuickAdapter.OnItemClickListener<T> block) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return baseQuickAdapter.setOnItemClickListener(new DebouncedClickListener<T>(j) {
            @Override
            protected void onSingleClick(BaseQuickAdapter<T, ?> adapter, View view, int position) {
                Intrinsics.checkNotNullParameter(adapter, "adapter");
                Intrinsics.checkNotNullParameter(view, "view");
                block.onClick(adapter, view, position);
            }
        });
    }

    public static BaseQuickAdapter addOnDebouncedChildClick$default(BaseQuickAdapter baseQuickAdapter, int i, long j, BaseQuickAdapter.OnItemChildClickListener onItemChildClickListener, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j = 500;
        }
        return addOnDebouncedChildClick(baseQuickAdapter, i, j, onItemChildClickListener);
    }

    public static final <T, VH extends RecyclerView.ViewHolder> BaseQuickAdapter<T, VH> addOnDebouncedChildClick(BaseQuickAdapter<T, VH> baseQuickAdapter, int i, long j, final BaseQuickAdapter.OnItemChildClickListener<T> block) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return baseQuickAdapter.addOnItemChildClickListener(i, new DebouncedClickListener<T>(j) {
            @Override
            protected void onSingleClick(BaseQuickAdapter<T, ?> adapter, View view, int position) {
                Intrinsics.checkNotNullParameter(adapter, "adapter");
                Intrinsics.checkNotNullParameter(view, "view");
                block.onItemClick(adapter, view, position);
            }
        });
    }
}
