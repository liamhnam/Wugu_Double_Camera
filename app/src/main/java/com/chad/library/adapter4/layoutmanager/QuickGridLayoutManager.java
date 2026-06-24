package com.chad.library.adapter4.layoutmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.fullspan.FullSpanAdapterType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001!B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ$\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011H\u0017J\u0012\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J \u0010\u001b\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\f\u0010\u001c\u001a\b\u0018\u00010\u001dR\u00020\u001aH\u0016J\u0012\u0010\u001e\u001a\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R\u0014\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00060\u0013R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m1293d2 = {"Lcom/chad/library/adapter4/layoutmanager/QuickGridLayoutManager;", "Landroidx/recyclerview/widget/GridLayoutManager;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "spanCount", "(Landroid/content/Context;I)V", "orientation", "reverseLayout", "", "(Landroid/content/Context;IIZ)V", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "fullSpanSizeLookup", "Lcom/chad/library/adapter4/layoutmanager/QuickGridLayoutManager$FullSpanSizeLookup;", "onAdapterChanged", "", "oldAdapter", "newAdapter", "onAttachedToWindow", "view", "Landroidx/recyclerview/widget/RecyclerView;", "onDetachedFromWindow", "recycler", "Landroidx/recyclerview/widget/RecyclerView$Recycler;", "setSpanSizeLookup", "spanSizeLookup", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "FullSpanSizeLookup", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public class QuickGridLayoutManager extends GridLayoutManager {
    private RecyclerView.Adapter<?> adapter;
    private final FullSpanSizeLookup fullSpanSizeLookup;

    public QuickGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        FullSpanSizeLookup fullSpanSizeLookup = new FullSpanSizeLookup();
        this.fullSpanSizeLookup = fullSpanSizeLookup;
        fullSpanSizeLookup.setOriginalSpanSizeLookup(getSpanSizeLookup());
        super.setSpanSizeLookup(fullSpanSizeLookup);
    }

    public QuickGridLayoutManager(Context context, int i) {
        super(context, i);
        Intrinsics.checkNotNullParameter(context, "context");
        FullSpanSizeLookup fullSpanSizeLookup = new FullSpanSizeLookup();
        this.fullSpanSizeLookup = fullSpanSizeLookup;
        fullSpanSizeLookup.setOriginalSpanSizeLookup(getSpanSizeLookup());
        super.setSpanSizeLookup(fullSpanSizeLookup);
    }

    public QuickGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
        Intrinsics.checkNotNullParameter(context, "context");
        FullSpanSizeLookup fullSpanSizeLookup = new FullSpanSizeLookup();
        this.fullSpanSizeLookup = fullSpanSizeLookup;
        fullSpanSizeLookup.setOriginalSpanSizeLookup(getSpanSizeLookup());
        super.setSpanSizeLookup(fullSpanSizeLookup);
    }

    @Override
    public void onAdapterChanged(RecyclerView.Adapter<?> oldAdapter, RecyclerView.Adapter<?> newAdapter) {
        this.adapter = newAdapter;
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        this.adapter = view != null ? view.getAdapter() : null;
    }

    @Override
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        this.adapter = null;
    }

    @Override
    public void setSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        this.fullSpanSizeLookup.setOriginalSpanSizeLookup(spanSizeLookup);
    }

    @Metadata(m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, m1293d2 = {"Lcom/chad/library/adapter4/layoutmanager/QuickGridLayoutManager$FullSpanSizeLookup;", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "(Lcom/chad/library/adapter4/layoutmanager/QuickGridLayoutManager;)V", "originalSpanSizeLookup", "getOriginalSpanSizeLookup", "()Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "setOriginalSpanSizeLookup", "(Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;)V", "getSpanSize", "", "position", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    private final class FullSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        private GridLayoutManager.SpanSizeLookup originalSpanSizeLookup;

        public FullSpanSizeLookup() {
        }

        public final GridLayoutManager.SpanSizeLookup getOriginalSpanSizeLookup() {
            return this.originalSpanSizeLookup;
        }

        public final void setOriginalSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
            this.originalSpanSizeLookup = spanSizeLookup;
        }

        @Override
        public int getSpanSize(int position) {
            RecyclerView.Adapter adapter = QuickGridLayoutManager.this.adapter;
            if (adapter == null) {
                return 1;
            }
            if (adapter instanceof ConcatAdapter) {
                Pair<RecyclerView.Adapter<? extends RecyclerView.ViewHolder>, Integer> wrappedAdapterAndPosition = ((ConcatAdapter) adapter).getWrappedAdapterAndPosition(position);
                Intrinsics.checkNotNullExpressionValue(wrappedAdapterAndPosition, "adapter.getWrappedAdapterAndPosition(position)");
                RecyclerView.Adapter adapter2 = (RecyclerView.Adapter) wrappedAdapterAndPosition.first;
                if (adapter2 instanceof FullSpanAdapterType) {
                    return QuickGridLayoutManager.this.getSpanCount();
                }
                if (adapter2 instanceof BaseQuickAdapter) {
                    Object obj = wrappedAdapterAndPosition.second;
                    Intrinsics.checkNotNullExpressionValue(obj, "pair.second");
                    if (((BaseQuickAdapter) adapter2).isFullSpanItem(adapter2.getItemViewType(((Number) obj).intValue()))) {
                        return QuickGridLayoutManager.this.getSpanCount();
                    }
                    GridLayoutManager.SpanSizeLookup spanSizeLookup = this.originalSpanSizeLookup;
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
                GridLayoutManager.SpanSizeLookup spanSizeLookup2 = this.originalSpanSizeLookup;
                if (spanSizeLookup2 != null) {
                    return spanSizeLookup2.getSpanSize(position);
                }
                return 1;
            }
            if (adapter instanceof FullSpanAdapterType) {
                return QuickGridLayoutManager.this.getSpanCount();
            }
            if (adapter instanceof BaseQuickAdapter) {
                if (((BaseQuickAdapter) adapter).isFullSpanItem(adapter.getItemViewType(position))) {
                    return QuickGridLayoutManager.this.getSpanCount();
                }
                GridLayoutManager.SpanSizeLookup spanSizeLookup3 = this.originalSpanSizeLookup;
                if (spanSizeLookup3 != null) {
                    return spanSizeLookup3.getSpanSize(position);
                }
                return 1;
            }
            GridLayoutManager.SpanSizeLookup spanSizeLookup4 = this.originalSpanSizeLookup;
            if (spanSizeLookup4 != null) {
                return spanSizeLookup4.getSpanSize(position);
            }
            return 1;
        }
    }
}
