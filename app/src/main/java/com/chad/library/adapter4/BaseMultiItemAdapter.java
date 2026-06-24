package com.chad.library.adapter4;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.C0844R;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00040\u0003:\u0003+,-B\u0015\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J2\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000\"\b\b\u0001\u0010\u000e*\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u000e0\fJ\u001e\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u001e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0014J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J'\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0002\u0010\u001dJ5\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00018\u00002\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H\u0014¢\u0006\u0002\u0010\u001fJ \u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0010H\u0014J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u001c\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tJ\u0010\u0010(\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u0010\u0010)\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0004H\u0016R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, m1293d2 = {"Lcom/chad/library/adapter4/BaseMultiItemAdapter;", "T", "", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "items", "", "(Ljava/util/List;)V", "onItemViewTypeListener", "Lcom/chad/library/adapter4/BaseMultiItemAdapter$OnItemViewTypeListener;", "typeViewHolders", "Landroid/util/SparseArray;", "Lcom/chad/library/adapter4/BaseMultiItemAdapter$OnMultiItemAdapterListener;", "addItemType", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "itemViewType", "", "listener", "findListener", "holder", "getItemViewType", "position", "list", "isFullSpanItem", "", "itemType", "onBindViewHolder", "", "item", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/lang/Object;)V", "payloads", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/lang/Object;Ljava/util/List;)V", "onCreateViewHolder", "context", "Landroid/content/Context;", "parent", "Landroid/view/ViewGroup;", "viewType", "onFailedToRecycleView", "onItemViewType", "onViewAttachedToWindow", "onViewDetachedFromWindow", "onViewRecycled", "OnItemViewTypeListener", "OnMultiItem", "OnMultiItemAdapterListener", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public abstract class BaseMultiItemAdapter<T> extends BaseQuickAdapter<T, RecyclerView.ViewHolder> {
    private OnItemViewTypeListener<T> onItemViewTypeListener;
    private final SparseArray<OnMultiItemAdapterListener<T, RecyclerView.ViewHolder>> typeViewHolders;

    @Metadata(m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\bæ\u0080\u0001\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseMultiItemAdapter$OnItemViewTypeListener;", "T", "", "onItemViewType", "", "position", "list", "", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnItemViewTypeListener<T> {
        int onItemViewType(int position, List<? extends T> list);
    }

    public BaseMultiItemAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BaseMultiItemAdapter(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
    }

    public BaseMultiItemAdapter(List<? extends T> items) {
        super(items);
        Intrinsics.checkNotNullParameter(items, "items");
        this.typeViewHolders = new SparseArray<>(1);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(Context context, ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> onMultiItemAdapterListener = this.typeViewHolders.get(viewType);
        if (onMultiItemAdapterListener == null) {
            throw new IllegalArgumentException("ViewType: " + viewType + " not found onViewHolderListener，please use addItemType() first!");
        }
        Context context2 = parent.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "parent.context");
        RecyclerView.ViewHolder viewHolderOnCreate = onMultiItemAdapterListener.onCreate(context2, parent, viewType);
        viewHolderOnCreate.itemView.setTag(C0844R.id.BaseQuickAdapter_key_multi, onMultiItemAdapterListener);
        return viewHolderOnCreate;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, T item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> onMultiItemAdapterListenerFindListener = findListener(holder);
        if (onMultiItemAdapterListenerFindListener != null) {
            onMultiItemAdapterListenerFindListener.onBind(holder, position, item);
        }
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, T item, List<? extends Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position, item);
            return;
        }
        OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> onMultiItemAdapterListenerFindListener = findListener(holder);
        if (onMultiItemAdapterListenerFindListener != null) {
            onMultiItemAdapterListenerFindListener.onBind(holder, position, item, payloads);
        }
    }

    public final <V extends RecyclerView.ViewHolder> BaseMultiItemAdapter<T> addItemType(int itemViewType, OnMultiItemAdapterListener<T, V> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (listener instanceof OnMultiItem) {
            ((OnMultiItem) listener).setWeakA$com_github_CymChad_brvah(new WeakReference<>(this));
        }
        this.typeViewHolders.put(itemViewType, listener);
        return this;
    }

    public final BaseMultiItemAdapter<T> onItemViewType(OnItemViewTypeListener<T> listener) {
        this.onItemViewTypeListener = listener;
        return this;
    }

    @Override
    protected int getItemViewType(int position, List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        OnItemViewTypeListener<T> onItemViewTypeListener = this.onItemViewTypeListener;
        return onItemViewTypeListener != null ? onItemViewTypeListener.onItemViewType(position, list) : super.getItemViewType(position, list);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onViewAttachedToWindow(holder);
        OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> onMultiItemAdapterListenerFindListener = findListener(holder);
        if (onMultiItemAdapterListenerFindListener != null) {
            onMultiItemAdapterListenerFindListener.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onViewDetachedFromWindow(holder);
        OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> onMultiItemAdapterListenerFindListener = findListener(holder);
        if (onMultiItemAdapterListenerFindListener != null) {
            onMultiItemAdapterListenerFindListener.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onViewRecycled(holder);
        OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> onMultiItemAdapterListenerFindListener = findListener(holder);
        if (onMultiItemAdapterListenerFindListener != null) {
            onMultiItemAdapterListenerFindListener.onViewRecycled(holder);
        }
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> onMultiItemAdapterListenerFindListener = findListener(holder);
        if (onMultiItemAdapterListenerFindListener != null) {
            return onMultiItemAdapterListenerFindListener.onFailedToRecycleView(holder);
        }
        return false;
    }

    @Override
    public boolean isFullSpanItem(int itemType) {
        if (super.isFullSpanItem(itemType)) {
            return true;
        }
        OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> onMultiItemAdapterListener = this.typeViewHolders.get(itemType);
        return onMultiItemAdapterListener != null && onMultiItemAdapterListener.isFullSpanItem(itemType);
    }

    private final OnMultiItemAdapterListener<T, RecyclerView.ViewHolder> findListener(RecyclerView.ViewHolder holder) {
        Object tag = holder.itemView.getTag(C0844R.id.BaseQuickAdapter_key_multi);
        if (tag instanceof OnMultiItemAdapterListener) {
            return (OnMultiItemAdapterListener) tag;
        }
        return null;
    }

    @Metadata(m1292d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0002*\u00020\u00032\u00020\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J'\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00022\u0006\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00018\u0001H&¢\u0006\u0002\u0010\u000eJ5\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00022\u0006\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00018\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010H\u0016¢\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u00028\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH&¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u0010\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseMultiItemAdapter$OnMultiItemAdapterListener;", "T", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "isFullSpanItem", "", "itemType", "", "onBind", "", "holder", "position", "item", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/lang/Object;)V", "payloads", "", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/lang/Object;Ljava/util/List;)V", "onCreate", "context", "Landroid/content/Context;", "parent", "Landroid/view/ViewGroup;", "viewType", "(Landroid/content/Context;Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onFailedToRecycleView", "onViewAttachedToWindow", "onViewDetachedFromWindow", "onViewRecycled", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnMultiItemAdapterListener<T, V extends RecyclerView.ViewHolder> {
        default boolean isFullSpanItem(int itemType) {
            return false;
        }

        void onBind(V holder, int position, T item);

        V onCreate(Context context, ViewGroup parent, int viewType);

        default boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            return false;
        }

        default void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
        }

        default void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
        }

        default void onViewRecycled(RecyclerView.ViewHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
        }

        default void onBind(V holder, int position, T item, List<? extends Object> payloads) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(payloads, "payloads");
            onBind(holder, position, item);
        }
    }

    @Metadata(m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u0002*\b\b\u0002\u0010\u0003*\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005B\u0005¢\u0006\u0002\u0010\u0006R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR(\u0010\u000f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b\u0018\u00010\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseMultiItemAdapter$OnMultiItem;", "T", "", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/chad/library/adapter4/BaseMultiItemAdapter$OnMultiItemAdapterListener;", "()V", "adapter", "Lcom/chad/library/adapter4/BaseMultiItemAdapter;", "getAdapter", "()Lcom/chad/library/adapter4/BaseMultiItemAdapter;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "weakA", "Ljava/lang/ref/WeakReference;", "getWeakA$com_github_CymChad_brvah", "()Ljava/lang/ref/WeakReference;", "setWeakA$com_github_CymChad_brvah", "(Ljava/lang/ref/WeakReference;)V", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public static abstract class OnMultiItem<T, V extends RecyclerView.ViewHolder> implements OnMultiItemAdapterListener<T, V> {
        private WeakReference<BaseMultiItemAdapter<T>> weakA;

        public final WeakReference<BaseMultiItemAdapter<T>> getWeakA$com_github_CymChad_brvah() {
            return this.weakA;
        }

        public final void setWeakA$com_github_CymChad_brvah(WeakReference<BaseMultiItemAdapter<T>> weakReference) {
            this.weakA = weakReference;
        }

        public final BaseMultiItemAdapter<T> getAdapter() {
            WeakReference<BaseMultiItemAdapter<T>> weakReference = this.weakA;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        public final Context getContext() {
            BaseMultiItemAdapter<T> baseMultiItemAdapter;
            WeakReference<BaseMultiItemAdapter<T>> weakReference = this.weakA;
            if (weakReference == null || (baseMultiItemAdapter = weakReference.get()) == null) {
                return null;
            }
            return baseMultiItemAdapter.getContext();
        }
    }
}
