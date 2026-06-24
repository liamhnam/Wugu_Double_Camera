package com.chad.library.adapter4;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.eclipse.paho.android.service.MqttServiceConstants;

@Metadata(m1292d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00030\u0005B\u0011\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\u001e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015H\u0016J\u0016\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\u00020\u00122\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018H\u0004J\u001f\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00028\u00012\b\u0010\t\u001a\u0004\u0018\u00018\u0000H$¢\u0006\u0002\u0010\u001bJ-\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00028\u00012\b\u0010\t\u001a\u0004\u0018\u00018\u00002\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018H\u0016¢\u0006\u0002\u0010\u001dJ'\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0004¢\u0006\u0002\u0010\u001eJ5\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\u00022\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018H\u0004¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020$H\u0016J\u0019\u0010%\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002J\u001f\u0010\f\u001a\u00020\u000f2\b\u0010&\u001a\u0004\u0018\u00018\u00002\b\u0010'\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010(J\u0018\u0010)\u001a\u00020\u000f2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0018H\u0016R(\u0010\t\u001a\u0004\u0018\u00018\u00002\b\u0010\b\u001a\u0004\u0018\u00018\u00008F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0007R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\r¨\u0006+"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseSingleItemAdapter;", "T", "", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "mItem", "(Ljava/lang/Object;)V", "value", "item", "getItem", "()Ljava/lang/Object;", "setItem", "Ljava/lang/Object;", "add", "", "data", "position", "", "addAll", "collection", "", "getItemCount", "items", "", "onBindViewHolder", "holder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;Ljava/lang/Object;)V", "payloads", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;Ljava/lang/Object;Ljava/util/List;)V", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/lang/Object;)V", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/lang/Object;Ljava/util/List;)V", "remove", "removeAt", "removeAtRange", "range", "Lkotlin/ranges/IntRange;", "set", "t", MqttServiceConstants.PAYLOAD, "(Ljava/lang/Object;Ljava/lang/Object;)V", "submitList", "list", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public abstract class BaseSingleItemAdapter<T, VH extends RecyclerView.ViewHolder> extends BaseQuickAdapter<Object, VH> {
    private T mItem;

    public BaseSingleItemAdapter() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        this(defaultConstructorMarker, 1, defaultConstructorMarker);
    }

    @Override
    protected final int getItemCount(List<? extends Object> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return 1;
    }

    protected abstract void onBindViewHolder(VH holder, T item);

    public BaseSingleItemAdapter(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj);
    }

    public BaseSingleItemAdapter(T t) {
        super(null, 1, null);
        this.mItem = t;
    }

    public void onBindViewHolder(VH holder, T item, List<? extends Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        onBindViewHolder(holder, item);
    }

    @Override
    protected final void onBindViewHolder(VH holder, int position, Object item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        onBindViewHolder(holder, this.mItem);
    }

    @Override
    protected final void onBindViewHolder(VH holder, int position, Object item, List<? extends Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        onBindViewHolder(holder, this.mItem, payloads);
    }

    public final void setItem(T t, Object payload) {
        this.mItem = t;
        notifyItemChanged(0, payload);
    }

    public final T getItem() {
        return this.mItem;
    }

    public final void setItem(T t) {
        this.mItem = t;
        notifyItemChanged(0);
    }

    @Override
    public void submitList(List<? extends Object> list) {
        throw new RuntimeException("Please use setItem()");
    }

    @Override
    public void add(Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        throw new RuntimeException("Please use setItem()");
    }

    @Override
    public void add(int position, Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        throw new RuntimeException("Please use setItem()");
    }

    @Override
    public void addAll(Collection<? extends Object> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        throw new RuntimeException("Please use setItem()");
    }

    @Override
    public void addAll(int position, Collection<? extends Object> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        throw new RuntimeException("Please use setItem()");
    }

    @Override
    public void remove(Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        throw new RuntimeException("Please use setItem()");
    }

    @Override
    public void removeAtRange(IntRange range) {
        Intrinsics.checkNotNullParameter(range, "range");
        throw new RuntimeException("Please use setItem()");
    }

    @Override
    public void removeAt(int position) {
        throw new RuntimeException("Please use setItem()");
    }

    @Override
    public void set(int position, Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        throw new RuntimeException("Please use setItem()");
    }
}
