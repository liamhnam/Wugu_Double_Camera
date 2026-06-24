package com.chad.library.adapter4.dragswipe;

import android.graphics.Canvas;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter4.dragswipe.listener.DragAndSwipeDataCallback;
import com.chad.library.adapter4.dragswipe.listener.OnItemDragListener;
import com.chad.library.adapter4.dragswipe.listener.OnItemSwipeListener;
import com.chad.library.adapter4.viewholder.StateLayoutVH;
import com.p020hp.jipp.model.Media;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001e\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0016J\u0006\u0010$\u001a\u00020\u0006J\u0018\u0010%\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0016J\u0006\u0010&\u001a\u00020\u0006J\u0012\u0010'\u001a\u00020\u00062\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u0010\u0010(\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010)\u001a\u00020\bH\u0016J\b\u0010*\u001a\u00020\bH\u0016J@\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u00020\bH\u0016J \u00103\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#2\u0006\u00104\u001a\u00020#H\u0016J@\u00105\u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#2\u0006\u00106\u001a\u00020\u00062\u0006\u00104\u001a\u00020#2\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u0006H\u0016J\u001a\u0010:\u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u00101\u001a\u00020\u0006H\u0016J\u0018\u0010;\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010<\u001a\u00020\u0006H\u0016J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\u0004J\u000e\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u0006J\u0010\u0010A\u001a\u00020\u00002\b\u0010B\u001a\u0004\u0018\u00010\u0016J\u0010\u0010C\u001a\u00020\u00002\b\u0010D\u001a\u0004\u0018\u00010\u0018J\u000e\u0010E\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\bJ\u000e\u0010F\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\bJ\u000e\u0010G\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\u0006J\u0010\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020#H\u0016J\u0010\u0010I\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u0006H\u0016J\u0010\u0010L\u001a\u00020\u00002\u0006\u0010J\u001a\u00020#H\u0016J\u0010\u0010L\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u0006H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006M"}, m1293d2 = {"Lcom/chad/library/adapter4/dragswipe/QuickDragAndSwipe;", "Landroidx/recyclerview/widget/ItemTouchHelper$Callback;", "()V", "_dataCallback", "Lcom/chad/library/adapter4/dragswipe/listener/DragAndSwipeDataCallback;", "_dragMoveFlags", "", "_isItemViewSwipeEnabled", "", "_isLongPressDragEnabled", "_itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "_swipeMoveFlags", "dataCallback", "getDataCallback", "()Lcom/chad/library/adapter4/dragswipe/listener/DragAndSwipeDataCallback;", "isDrag", "isSwipe", "itemTouchHelper", "getItemTouchHelper", "()Landroidx/recyclerview/widget/ItemTouchHelper;", "mOnItemDragListener", "Lcom/chad/library/adapter4/dragswipe/listener/OnItemDragListener;", "mOnItemSwipeListener", "Lcom/chad/library/adapter4/dragswipe/listener/OnItemSwipeListener;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "attachToRecyclerView", "clearView", "", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getDragMoveFlags", "getMovementFlags", "getSwipeMoveFlags", "getViewHolderPosition", "isEmptyView", "isItemViewSwipeEnabled", "isLongPressDragEnabled", "onChildDraw", Media.f727c, "Landroid/graphics/Canvas;", "dX", "", "dY", "actionState", "isCurrentlyActive", "onMove", TypedValues.AttributesType.S_TARGET, "onMoved", "fromPos", "toPos", "x", "y", "onSelectedChanged", "onSwiped", "direction", "setDataCallback", "callback", "setDragMoveFlags", "dragMoveFlags", "setItemDragListener", "onItemDragListener", "setItemSwipeListener", "onItemSwipeListener", "setItemViewSwipeEnabled", "setLongPressDragEnabled", "setSwipeMoveFlags", "swipeMoveFlags", "startDrag", "holder", "position", "startSwipe", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public class QuickDragAndSwipe extends ItemTouchHelper.Callback {
    private DragAndSwipeDataCallback _dataCallback;
    private int _dragMoveFlags;
    private int _swipeMoveFlags;
    private boolean isDrag;
    private boolean isSwipe;
    private OnItemDragListener mOnItemDragListener;
    private OnItemSwipeListener mOnItemSwipeListener;
    private RecyclerView recyclerView;
    private final ItemTouchHelper _itemTouchHelper = new ItemTouchHelper(this);
    private boolean _isLongPressDragEnabled = true;
    private boolean _isItemViewSwipeEnabled = true;

    protected final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    protected final void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public final DragAndSwipeDataCallback getDataCallback() {
        DragAndSwipeDataCallback dragAndSwipeDataCallback = this._dataCallback;
        if (dragAndSwipeDataCallback == null) {
            throw new IllegalStateException("Please set _adapterImpl".toString());
        }
        Intrinsics.checkNotNull(dragAndSwipeDataCallback);
        return dragAndSwipeDataCallback;
    }

    public final ItemTouchHelper get_itemTouchHelper() {
        return this._itemTouchHelper;
    }

    public QuickDragAndSwipe attachToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        if (Intrinsics.areEqual(this.recyclerView, recyclerView)) {
            return this;
        }
        this.recyclerView = recyclerView;
        this._itemTouchHelper.attachToRecyclerView(recyclerView);
        return this;
    }

    public final QuickDragAndSwipe setDragMoveFlags(int dragMoveFlags) {
        this._dragMoveFlags = dragMoveFlags;
        return this;
    }

    public final int get_dragMoveFlags() {
        return this._dragMoveFlags;
    }

    public final QuickDragAndSwipe setSwipeMoveFlags(int swipeMoveFlags) {
        this._swipeMoveFlags = swipeMoveFlags;
        return this;
    }

    public final int get_swipeMoveFlags() {
        return this._swipeMoveFlags;
    }

    public final QuickDragAndSwipe setLongPressDragEnabled(boolean isLongPressDragEnabled) {
        this._isLongPressDragEnabled = isLongPressDragEnabled;
        return this;
    }

    public final QuickDragAndSwipe setItemViewSwipeEnabled(boolean isItemViewSwipeEnabled) {
        this._isItemViewSwipeEnabled = isItemViewSwipeEnabled;
        return this;
    }

    public QuickDragAndSwipe startDrag(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        this._itemTouchHelper.startDrag(holder);
        return this;
    }

    public QuickDragAndSwipe startDrag(int position) {
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition;
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null && (viewHolderFindViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(position)) != null) {
            Intrinsics.checkNotNullExpressionValue(viewHolderFindViewHolderForAdapterPosition, "recyclerView?.findViewHo…(position) ?: return this");
            this._itemTouchHelper.startDrag(viewHolderFindViewHolderForAdapterPosition);
        }
        return this;
    }

    public QuickDragAndSwipe startSwipe(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        this._itemTouchHelper.startSwipe(holder);
        return this;
    }

    public QuickDragAndSwipe startSwipe(int position) {
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition;
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null && (viewHolderFindViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(position)) != null) {
            Intrinsics.checkNotNullExpressionValue(viewHolderFindViewHolderForAdapterPosition, "recyclerView?.findViewHo…(position) ?: return this");
            this._itemTouchHelper.startSwipe(viewHolderFindViewHolderForAdapterPosition);
        }
        return this;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState == 1) {
            this.isSwipe = true;
            OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
            if (onItemSwipeListener != null) {
                onItemSwipeListener.onItemSwipeStart(viewHolder, getViewHolderPosition(viewHolder));
            }
        } else if (actionState == 2) {
            this.isDrag = true;
            OnItemDragListener onItemDragListener = this.mOnItemDragListener;
            if (onItemDragListener != null) {
                onItemDragListener.onItemDragStart(viewHolder, getViewHolderPosition(viewHolder));
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        if (isEmptyView(viewHolder)) {
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }
        return ItemTouchHelper.Callback.makeMovementFlags(this._dragMoveFlags, this._swipeMoveFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        return viewHolder.getItemViewType() == target.getItemViewType();
    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        int bindingAdapterPosition2 = target.getBindingAdapterPosition();
        if (bindingAdapterPosition == -1 || bindingAdapterPosition2 == -1) {
            return;
        }
        DragAndSwipeDataCallback dragAndSwipeDataCallback = this._dataCallback;
        if (dragAndSwipeDataCallback != null) {
            dragAndSwipeDataCallback.dataMove(bindingAdapterPosition, bindingAdapterPosition2);
        }
        OnItemDragListener onItemDragListener = this.mOnItemDragListener;
        if (onItemDragListener != null) {
            onItemDragListener.onItemDragMoving(viewHolder, bindingAdapterPosition, target, bindingAdapterPosition2);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        if (bindingAdapterPosition == -1) {
            return;
        }
        DragAndSwipeDataCallback dragAndSwipeDataCallback = this._dataCallback;
        if (dragAndSwipeDataCallback != null) {
            dragAndSwipeDataCallback.dataRemoveAt(bindingAdapterPosition);
        }
        OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
        if (onItemSwipeListener != null) {
            onItemSwipeListener.onItemSwiped(viewHolder, direction, bindingAdapterPosition);
        }
    }

    @Override
    public boolean get_isLongPressDragEnabled() {
        return this._isLongPressDragEnabled;
    }

    @Override
    public boolean get_isItemViewSwipeEnabled() {
        return this._isItemViewSwipeEnabled;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        OnItemSwipeListener onItemSwipeListener;
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState != 1 || (onItemSwipeListener = this.mOnItemSwipeListener) == null) {
            return;
        }
        onItemSwipeListener.onItemSwipeMoving(c, viewHolder, dX, dY, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        super.clearView(recyclerView, viewHolder);
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        if (this.isSwipe) {
            OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
            if (onItemSwipeListener != null) {
                onItemSwipeListener.onItemSwipeEnd(viewHolder, bindingAdapterPosition);
            }
            this.isSwipe = false;
        }
        if (this.isDrag) {
            OnItemDragListener onItemDragListener = this.mOnItemDragListener;
            if (onItemDragListener != null) {
                onItemDragListener.onItemDragEnd(viewHolder, bindingAdapterPosition);
            }
            this.isDrag = false;
        }
    }

    private final boolean isEmptyView(RecyclerView.ViewHolder viewHolder) {
        return viewHolder instanceof StateLayoutVH;
    }

    private final int getViewHolderPosition(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            return viewHolder.getBindingAdapterPosition();
        }
        return -1;
    }

    public final QuickDragAndSwipe setItemDragListener(OnItemDragListener onItemDragListener) {
        this.mOnItemDragListener = onItemDragListener;
        return this;
    }

    public final QuickDragAndSwipe setItemSwipeListener(OnItemSwipeListener onItemSwipeListener) {
        this.mOnItemSwipeListener = onItemSwipeListener;
        return this;
    }

    public final QuickDragAndSwipe setDataCallback(DragAndSwipeDataCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this._dataCallback = callback;
        return this;
    }
}
