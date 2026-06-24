package com.chad.library.adapter4;

import android.animation.Animator;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.chad.library.adapter4.animation.AlphaInAnimation;
import com.chad.library.adapter4.animation.ItemAnimator;
import com.chad.library.adapter4.animation.ScaleInAnimation;
import com.chad.library.adapter4.animation.SlideInBottomAnimation;
import com.chad.library.adapter4.animation.SlideInLeftAnimation;
import com.chad.library.adapter4.animation.SlideInRightAnimation;
import com.chad.library.adapter4.util.AdapterUtilsKt;
import com.chad.library.adapter4.viewholder.StateLayoutVH;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1292d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 \u0094\u0001*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\b\u0012\u0004\u0012\u00020\u00040\u0005:\u000e\u0093\u0001\u0094\u0001\u0095\u0001\u0096\u0001\u0097\u0001\u0098\u0001\u0099\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010IJ\u001f\u0010F\u001a\u00020G2\b\b\u0001\u0010J\u001a\u00020/2\u0006\u0010H\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010KJ \u0010L\u001a\u00020G2\b\b\u0001\u0010J\u001a\u00020/2\f\u0010M\u001a\b\u0012\u0004\u0012\u00028\u00000NH\u0016J\u0016\u0010L\u001a\u00020G2\f\u0010M\u001a\b\u0012\u0004\u0012\u00028\u00000NH\u0016J*\u0010O\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0001\u0010P\u001a\u00020/2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00028\u000002J*\u0010R\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0001\u0010P\u001a\u00020/2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00028\u000004J\u001a\u0010S\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010Q\u001a\u00020;J\u001d\u0010T\u001a\u00020G2\u0006\u0010U\u001a\u00028\u00012\u0006\u0010V\u001a\u00020/H\u0014¢\u0006\u0002\u0010WJ\u0006\u0010X\u001a\u00020GJ\u0016\u0010Y\u001a\u00020\f2\u000e\b\u0002\u0010Z\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007J\u0017\u0010[\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010J\u001a\u00020/¢\u0006\u0002\u0010\\J\u0006\u0010]\u001a\u00020/J\u0016\u0010]\u001a\u00020/2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0014J\u0010\u0010^\u001a\u00020_2\u0006\u0010J\u001a\u00020/H\u0016J\u000e\u0010`\u001a\u00020/2\u0006\u0010J\u001a\u00020/J\u001e\u0010`\u001a\u00020/2\u0006\u0010J\u001a\u00020/2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0014J\u000e\u0010a\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u000106J\u000e\u0010b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u000108J\u0010\u0010c\u001a\u00020\f2\u0006\u0010d\u001a\u00020/H\u0016J\u0013\u0010e\u001a\u00020/2\u0006\u0010f\u001a\u00028\u0000¢\u0006\u0002\u0010gJ\u0018\u0010h\u001a\u00020G2\u0006\u0010i\u001a\u00020/2\u0006\u0010j\u001a\u00020/H\u0016J\u0010\u0010k\u001a\u00020G2\u0006\u0010>\u001a\u00020\nH\u0017J'\u0010l\u001a\u00020G2\u0006\u0010m\u001a\u00028\u00012\u0006\u0010J\u001a\u00020/2\b\u0010f\u001a\u0004\u0018\u00018\u0000H$¢\u0006\u0002\u0010nJ5\u0010l\u001a\u00020G2\u0006\u0010m\u001a\u00028\u00012\u0006\u0010J\u001a\u00020/2\b\u0010f\u001a\u0004\u0018\u00018\u00002\f\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0014¢\u0006\u0002\u0010pJ\u0016\u0010l\u001a\u00020G2\u0006\u0010m\u001a\u00020\u00042\u0006\u0010J\u001a\u00020/J$\u0010l\u001a\u00020G2\u0006\u0010m\u001a\u00020\u00042\u0006\u0010J\u001a\u00020/2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00020:J%\u0010q\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010r\u001a\u00020s2\u0006\u0010V\u001a\u00020/H$¢\u0006\u0002\u0010tJ\u0016\u0010q\u001a\u00020\u00042\u0006\u0010r\u001a\u00020s2\u0006\u0010V\u001a\u00020/J\u0010\u0010u\u001a\u00020G2\u0006\u0010>\u001a\u00020\nH\u0017J\u0018\u0010v\u001a\u00020G2\u0006\u0010w\u001a\u00020\u00162\u0006\u0010J\u001a\u00020/H\u0014J\u0018\u0010x\u001a\u00020\f2\u0006\u0010w\u001a\u00020\u00162\u0006\u0010J\u001a\u00020/H\u0014J\u0018\u0010y\u001a\u00020G2\u0006\u0010w\u001a\u00020\u00162\u0006\u0010J\u001a\u00020/H\u0014J\u0018\u0010z\u001a\u00020\f2\u0006\u0010w\u001a\u00020\u00162\u0006\u0010J\u001a\u00020/H\u0014J\u0010\u0010{\u001a\u00020G2\u0006\u0010m\u001a\u00020\u0004H\u0017J\u0010\u0010|\u001a\u00020G2\u0006\u0010m\u001a\u00020\u0004H\u0017J\u0015\u0010}\u001a\u00020G2\u0006\u0010H\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010IJ\u0012\u0010~\u001a\u00020G2\b\b\u0001\u0010J\u001a\u00020/H\u0016J\u0012\u0010\u007f\u001a\u00020G2\b\u0010\u0080\u0001\u001a\u00030\u0081\u0001H\u0016J\u001d\u0010\u0082\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0001\u0010P\u001a\u00020/J\u001d\u0010\u0083\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0001\u0010P\u001a\u00020/J\u000f\u0010\u0084\u0001\u001a\u00020G2\u0006\u0010Q\u001a\u00020;J\u0011\u0010\u0085\u0001\u001a\u00020G2\u0006\u0010m\u001a\u00020\u0004H\u0002J!\u0010\u0086\u0001\u001a\u00020G2\b\b\u0001\u0010J\u001a\u00020/2\u0006\u0010H\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010KJ\u001c\u0010\u0087\u0001\u001a\u00020G2\u0006\u0010\u0011\u001a\u00020\u00122\t\b\u0001\u0010\u0088\u0001\u001a\u00020/H\u0007J\u0010\u0010)\u001a\u00020G2\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J#\u0010\u008b\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010Q\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u000106J#\u0010\u008c\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010Q\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u000108J\u001a\u0010\u008d\u0001\u001a\u00020G2\u0006\u0010\u0011\u001a\u00020\u00122\t\b\u0001\u0010\u0088\u0001\u001a\u00020/J\u001b\u0010\u008e\u0001\u001a\u00020G2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0006\u0010m\u001a\u00020\u0004H\u0014J\u0019\u0010\u0091\u0001\u001a\u00020G2\u000e\u0010Z\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007H\u0016J\u0019\u0010\u0092\u0001\u001a\u00020G2\u0006\u0010i\u001a\u00020/2\u0006\u0010j\u001a\u00020/H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R.\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u00168F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R*\u0010 \u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b!\u0010\u0019\u001a\u0004\b \u0010\u000e\"\u0004\b\"\u0010\u0010R$\u0010#\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010R(\u0010&\u001a\u0004\u0018\u00010%2\b\u0010\u0015\u001a\u0004\u0018\u00010%@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010\bR\u000e\u0010.\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00100\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000002\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00103\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00105\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00107\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00109\u001a\n\u0012\u0004\u0012\u00020;\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00000:8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b=\u0010,R\u0011\u0010>\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b?\u0010@R(\u0010A\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u001b\"\u0004\bC\u0010\u001dR\u0016\u0010D\u001a\u00020\f*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\bD\u0010E¨\u0006\u009a\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseQuickAdapter;", "T", "", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "items", "", "(Ljava/util/List;)V", "_recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "animationEnable", "", "getAnimationEnable", "()Z", "setAnimationEnable", "(Z)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "value", "Landroid/view/View;", "emptyView", "getEmptyView$annotations", "()V", "getEmptyView", "()Landroid/view/View;", "setEmptyView", "(Landroid/view/View;)V", "isAnimationFirstOnly", "setAnimationFirstOnly", "isEmptyViewEnable", "isEmptyViewEnable$annotations", "setEmptyViewEnable", "isStateViewEnable", "setStateViewEnable", "Lcom/chad/library/adapter4/animation/ItemAnimator;", "itemAnimation", "getItemAnimation", "()Lcom/chad/library/adapter4/animation/ItemAnimator;", "setItemAnimation", "(Lcom/chad/library/adapter4/animation/ItemAnimator;)V", "getItems", "()Ljava/util/List;", "setItems", "mLastPosition", "", "mOnItemChildClickArray", "Landroid/util/SparseArray;", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemChildClickListener;", "mOnItemChildLongClickArray", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemChildLongClickListener;", "mOnItemClickListener", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemClickListener;", "mOnItemLongClickListener", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemLongClickListener;", "mOnViewAttachStateChangeListeners", "", "Lcom/chad/library/adapter4/BaseQuickAdapter$OnViewAttachStateChangeListener;", "mutableItems", "getMutableItems", "recyclerView", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "stateView", "getStateView", "setStateView", "isEmptyViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Z", "add", "", "data", "(Ljava/lang/Object;)V", "position", "(ILjava/lang/Object;)V", "addAll", "collection", "", "addOnItemChildClickListener", "id", "listener", "addOnItemChildLongClickListener", "addOnViewAttachStateChangeListener", "bindViewClickListener", "viewHolder", "viewType", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "clearOnViewAttachStateChangeListener", "displayEmptyView", "list", "getItem", "(I)Ljava/lang/Object;", "getItemCount", "getItemId", "", "getItemViewType", "getOnItemClickListener", "getOnItemLongClickListener", "isFullSpanItem", "itemType", "itemIndexOfFirst", "item", "(Ljava/lang/Object;)I", "move", "fromPosition", "toPosition", "onAttachedToRecyclerView", "onBindViewHolder", "holder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/lang/Object;)V", "payloads", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/lang/Object;Ljava/util/List;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "(Landroid/content/Context;Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onDetachedFromRecyclerView", "onItemChildClick", "v", "onItemChildLongClick", "onItemClick", "onItemLongClick", "onViewAttachedToWindow", "onViewDetachedFromWindow", "remove", "removeAt", "removeAtRange", "range", "Lkotlin/ranges/IntRange;", "removeOnItemChildClickListener", "removeOnItemChildLongClickListener", "removeOnViewAttachStateChangeListener", "runAnimator", "set", "setEmptyViewLayout", "layoutResId", "animationType", "Lcom/chad/library/adapter4/BaseQuickAdapter$AnimationType;", "setOnItemClickListener", "setOnItemLongClickListener", "setStateViewLayout", "startItemAnimator", "anim", "Landroid/animation/Animator;", "submitList", "swap", "AnimationType", "Companion", "OnItemChildClickListener", "OnItemChildLongClickListener", "OnItemClickListener", "OnItemLongClickListener", "OnViewAttachStateChangeListener", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public abstract class BaseQuickAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int EMPTY_PAYLOAD = 0;
    public static final int EMPTY_VIEW = 268436821;
    private RecyclerView _recyclerView;
    private boolean animationEnable;
    private boolean isAnimationFirstOnly;
    private boolean isStateViewEnable;
    private ItemAnimator itemAnimation;
    private List<? extends T> items;
    private int mLastPosition;
    private SparseArray<OnItemChildClickListener<T>> mOnItemChildClickArray;
    private SparseArray<OnItemChildLongClickListener<T>> mOnItemChildLongClickArray;
    private OnItemClickListener<T> mOnItemClickListener;
    private OnItemLongClickListener<T> mOnItemLongClickListener;
    private List<OnViewAttachStateChangeListener> mOnViewAttachStateChangeListeners;
    private View stateView;

    @Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseQuickAdapter$AnimationType;", "", "(Ljava/lang/String;I)V", "AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public enum AnimationType {
        AlphaIn,
        ScaleIn,
        SlideInBottom,
        SlideInLeft,
        SlideInRight
    }

    @Metadata(m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bæ\u0080\u0001\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002J*\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00028\u0002\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemChildClickListener;", "T", "", "onItemClick", "", "adapter", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "view", "Landroid/view/View;", "position", "", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnItemChildClickListener<T> {
        void onItemClick(BaseQuickAdapter<T, ?> adapter, View view, int position);
    }

    @Metadata(m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bæ\u0080\u0001\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002J*\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00028\u0002\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemChildLongClickListener;", "T", "", "onItemLongClick", "", "adapter", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "view", "Landroid/view/View;", "position", "", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnItemChildLongClickListener<T> {
        boolean onItemLongClick(BaseQuickAdapter<T, ?> adapter, View view, int position);
    }

    @Metadata(m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bæ\u0080\u0001\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002J*\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00028\u0002\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemClickListener;", "T", "", "onClick", "", "adapter", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "view", "Landroid/view/View;", "position", "", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnItemClickListener<T> {
        void onClick(BaseQuickAdapter<T, ?> adapter, View view, int position);
    }

    @Metadata(m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bæ\u0080\u0001\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002J*\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00028\u0002\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseQuickAdapter$OnItemLongClickListener;", "T", "", "onLongClick", "", "adapter", "Lcom/chad/library/adapter4/BaseQuickAdapter;", "view", "Landroid/view/View;", "position", "", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnItemLongClickListener<T> {
        boolean onLongClick(BaseQuickAdapter<T, ?> adapter, View view, int position);
    }

    @Metadata(m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/BaseQuickAdapter$OnViewAttachStateChangeListener;", "", "onViewAttachedToWindow", "", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onViewDetachedFromWindow", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
    public interface OnViewAttachStateChangeListener {
        void onViewAttachedToWindow(RecyclerView.ViewHolder holder);

        void onViewDetachedFromWindow(RecyclerView.ViewHolder holder);
    }

    @Metadata(m1294k = 3, m1295mv = {1, 8, 0}, m1297xi = 48)
    public class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnimationType.values().length];
            try {
                iArr[AnimationType.AlphaIn.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnimationType.ScaleIn.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnimationType.SlideInBottom.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AnimationType.SlideInLeft.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AnimationType.SlideInRight.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BaseQuickAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Deprecated(message = "使用 stateView", replaceWith = @ReplaceWith(expression = "stateView", imports = {}))
    public static void getEmptyView$annotations() {
    }

    @Deprecated(message = "使用 isStateViewEnable", replaceWith = @ReplaceWith(expression = "isStateViewEnable", imports = {}))
    public static void isEmptyViewEnable$annotations() {
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected int getItemViewType(int position, List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        return 0;
    }

    public boolean isFullSpanItem(int itemType) {
        return false;
    }

    protected abstract void onBindViewHolder(VH holder, int position, T item);

    protected abstract VH onCreateViewHolder(Context context, ViewGroup parent, int viewType);

    public BaseQuickAdapter(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.items = list;
    }

    public BaseQuickAdapter(List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items = items;
        this.mLastPosition = -1;
        this.isAnimationFirstOnly = true;
    }

    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView = this._recyclerView;
        if (recyclerView == null) {
            throw new IllegalStateException("Please get it after onAttachedToRecyclerView()".toString());
        }
        Intrinsics.checkNotNull(recyclerView);
        return recyclerView;
    }

    public final Context getContext() {
        Context context = getRecyclerView().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "recyclerView.context");
        return context;
    }

    public final boolean isEmptyViewHolder(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "<this>");
        return viewHolder instanceof StateLayoutVH;
    }

    public final void setEmptyViewEnable(boolean z) {
        setStateViewEnable(z);
    }

    public final boolean getIsStateViewEnable() {
        return this.isStateViewEnable;
    }

    public final boolean isStateViewEnable() {
        return this.isStateViewEnable;
    }

    public final void setStateViewEnable(boolean z) {
        boolean zDisplayEmptyView$default = displayEmptyView$default(this, null, 1, null);
        this.isStateViewEnable = z;
        boolean zDisplayEmptyView$default2 = displayEmptyView$default(this, null, 1, null);
        if (zDisplayEmptyView$default && !zDisplayEmptyView$default2) {
            notifyItemRemoved(0);
            return;
        }
        if (zDisplayEmptyView$default2 && !zDisplayEmptyView$default) {
            notifyItemInserted(0);
        } else if (zDisplayEmptyView$default && zDisplayEmptyView$default2) {
            notifyItemChanged(0, 0);
        }
    }

    public final View getStateView() {
        return this.stateView;
    }

    public final void setStateView(View view) {
        boolean zDisplayEmptyView$default = displayEmptyView$default(this, null, 1, null);
        this.stateView = view;
        boolean zDisplayEmptyView$default2 = displayEmptyView$default(this, null, 1, null);
        if (zDisplayEmptyView$default && !zDisplayEmptyView$default2) {
            notifyItemRemoved(0);
            return;
        }
        if (zDisplayEmptyView$default2 && !zDisplayEmptyView$default) {
            notifyItemInserted(0);
        } else if (zDisplayEmptyView$default && zDisplayEmptyView$default2) {
            notifyItemChanged(0, 0);
        }
    }

    public final void setEmptyView(View view) {
        setStateView(view);
    }

    public final View getStateView() {
        return this.stateView;
    }

    public final boolean getAnimationEnable() {
        return this.animationEnable;
    }

    public final void setAnimationEnable(boolean z) {
        this.animationEnable = z;
    }

    public final boolean getIsAnimationFirstOnly() {
        return this.isAnimationFirstOnly;
    }

    public final void setAnimationFirstOnly(boolean z) {
        this.isAnimationFirstOnly = z;
    }

    public final ItemAnimator getItemAnimation() {
        return this.itemAnimation;
    }

    public final void setItemAnimation(ItemAnimator itemAnimator) {
        this.animationEnable = true;
        this.itemAnimation = itemAnimator;
    }

    protected void onBindViewHolder(VH holder, int position, T item, List<? extends Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        onBindViewHolder(holder, position, item);
    }

    protected int getItemCount(List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return items.size();
    }

    @Override
    public final int getItemCount() {
        if (displayEmptyView$default(this, null, 1, null)) {
            return 1;
        }
        return getItemCount(getItems());
    }

    @Override
    public final int getItemViewType(int position) {
        return displayEmptyView$default(this, null, 1, null) ? EMPTY_VIEW : getItemViewType(position, getItems());
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 268436821) {
            return new StateLayoutVH(parent, this.stateView, null, 4, null);
        }
        Context context = parent.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        RecyclerView.ViewHolder viewHolderOnCreateViewHolder = onCreateViewHolder(context, parent, viewType);
        bindViewClickListener(viewHolderOnCreateViewHolder, viewType);
        return viewHolderOnCreateViewHolder;
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof StateLayoutVH) {
            return;
        }
        onBindViewHolder(holder, position, getItem(position));
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else if (holder instanceof StateLayoutVH) {
            ((StateLayoutVH) holder).changeStateView(this.stateView);
        } else {
            onBindViewHolder(holder, position, getItem(position), payloads);
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onViewAttachedToWindow(holder);
        if ((holder instanceof StateLayoutVH) || isFullSpanItem(getItemViewType(holder.getBindingAdapterPosition()))) {
            AdapterUtilsKt.asStaggeredGridFullSpan(holder);
        } else {
            runAnimator(holder);
        }
        List<OnViewAttachStateChangeListener> list = this.mOnViewAttachStateChangeListeners;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((OnViewAttachStateChangeListener) it.next()).onViewAttachedToWindow(holder);
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<OnViewAttachStateChangeListener> list = this.mOnViewAttachStateChangeListeners;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((OnViewAttachStateChangeListener) it.next()).onViewDetachedFromWindow(holder);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        this._recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        this._recyclerView = null;
    }

    protected void bindViewClickListener(final VH viewHolder, int viewType) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        if (this.mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    BaseQuickAdapter.bindViewClickListener$lambda$5$lambda$4(viewHolder, this, view);
                }
            });
        }
        if (this.mOnItemLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public final boolean onLongClick(View view) {
                    return BaseQuickAdapter.bindViewClickListener$lambda$7$lambda$6(viewHolder, this, view);
                }
            });
        }
        SparseArray<OnItemChildClickListener<T>> sparseArray = this.mOnItemChildClickArray;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                View viewFindViewById = viewHolder.itemView.findViewById(sparseArray.keyAt(i));
                if (viewFindViewById != null) {
                    Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<View>(id)");
                    viewFindViewById.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public final void onClick(View view) {
                            BaseQuickAdapter.bindViewClickListener$lambda$10$lambda$9$lambda$8(viewHolder, this, view);
                        }
                    });
                }
            }
        }
        SparseArray<OnItemChildLongClickListener<T>> sparseArray2 = this.mOnItemChildLongClickArray;
        if (sparseArray2 != null) {
            int size2 = sparseArray2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                View viewFindViewById2 = viewHolder.itemView.findViewById(sparseArray2.keyAt(i2));
                if (viewFindViewById2 != null) {
                    Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById<View>(id)");
                    viewFindViewById2.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public final boolean onLongClick(View view) {
                            return BaseQuickAdapter.bindViewClickListener$lambda$13$lambda$12$lambda$11(viewHolder, this, view);
                        }
                    });
                }
            }
        }
    }

    public static final void bindViewClickListener$lambda$5$lambda$4(RecyclerView.ViewHolder viewHolder, BaseQuickAdapter this$0, View v) {
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        if (bindingAdapterPosition == -1) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(v, "v");
        this$0.onItemClick(v, bindingAdapterPosition);
    }

    public static final boolean bindViewClickListener$lambda$7$lambda$6(RecyclerView.ViewHolder viewHolder, BaseQuickAdapter this$0, View v) {
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        if (bindingAdapterPosition == -1) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(v, "v");
        return this$0.onItemLongClick(v, bindingAdapterPosition);
    }

    public static final void bindViewClickListener$lambda$10$lambda$9$lambda$8(RecyclerView.ViewHolder viewHolder, BaseQuickAdapter this$0, View v) {
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        if (bindingAdapterPosition == -1) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(v, "v");
        this$0.onItemChildClick(v, bindingAdapterPosition);
    }

    public static final boolean bindViewClickListener$lambda$13$lambda$12$lambda$11(RecyclerView.ViewHolder viewHolder, BaseQuickAdapter this$0, View v) {
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        if (bindingAdapterPosition == -1) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(v, "v");
        return this$0.onItemChildLongClick(v, bindingAdapterPosition);
    }

    protected void onItemClick(View v, int position) {
        Intrinsics.checkNotNullParameter(v, "v");
        OnItemClickListener<T> onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onClick(this, v, position);
        }
    }

    protected boolean onItemLongClick(View v, int position) {
        Intrinsics.checkNotNullParameter(v, "v");
        OnItemLongClickListener<T> onItemLongClickListener = this.mOnItemLongClickListener;
        if (onItemLongClickListener != null) {
            return onItemLongClickListener.onLongClick(this, v, position);
        }
        return false;
    }

    protected void onItemChildClick(View v, int position) {
        OnItemChildClickListener<T> onItemChildClickListener;
        Intrinsics.checkNotNullParameter(v, "v");
        SparseArray<OnItemChildClickListener<T>> sparseArray = this.mOnItemChildClickArray;
        if (sparseArray == null || (onItemChildClickListener = sparseArray.get(v.getId())) == null) {
            return;
        }
        onItemChildClickListener.onItemClick(this, v, position);
    }

    protected boolean onItemChildLongClick(View v, int position) {
        OnItemChildLongClickListener<T> onItemChildLongClickListener;
        Intrinsics.checkNotNullParameter(v, "v");
        SparseArray<OnItemChildLongClickListener<T>> sparseArray = this.mOnItemChildLongClickArray;
        if (sparseArray == null || (onItemChildLongClickListener = sparseArray.get(v.getId())) == null) {
            return false;
        }
        return onItemChildLongClickListener.onItemLongClick(this, v, position);
    }

    public final T getItem(int position) {
        return (T) CollectionsKt.getOrNull(getItems(), position);
    }

    public final int itemIndexOfFirst(T item) {
        Intrinsics.checkNotNullParameter(item, "item");
        Iterator<T> it = getItems().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Intrinsics.areEqual(item, it.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public final void setStateViewLayout(Context context, int layoutResId) {
        Intrinsics.checkNotNullParameter(context, "context");
        setStateView(LayoutInflater.from(context).inflate(layoutResId, (ViewGroup) new FrameLayout(context), false));
    }

    @Deprecated(message = "使用 setStateViewLayout()", replaceWith = @ReplaceWith(expression = "setStateViewLayout(context, layoutResId)", imports = {}))
    public final void setEmptyViewLayout(Context context, int layoutResId) {
        Intrinsics.checkNotNullParameter(context, "context");
        setStateViewLayout(context, layoutResId);
    }

    public static boolean displayEmptyView$default(BaseQuickAdapter baseQuickAdapter, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: displayEmptyView");
        }
        if ((i & 1) != 0) {
            list = baseQuickAdapter.getItems();
        }
        return baseQuickAdapter.displayEmptyView(list);
    }

    public final boolean displayEmptyView(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (this.stateView == null || !this.isStateViewEnable) {
            return false;
        }
        return list.isEmpty();
    }

    private final void runAnimator(RecyclerView.ViewHolder holder) {
        if (this.animationEnable) {
            if (!this.isAnimationFirstOnly || holder.getLayoutPosition() > this.mLastPosition) {
                AlphaInAnimation alphaInAnimation = this.itemAnimation;
                if (alphaInAnimation == null) {
                    alphaInAnimation = new AlphaInAnimation(0L, 0.0f, 3, null);
                }
                View view = holder.itemView;
                Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
                startItemAnimator(alphaInAnimation.animator(view), holder);
                this.mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    protected void startItemAnimator(Animator anim, RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(anim, "anim");
        Intrinsics.checkNotNullParameter(holder, "holder");
        anim.start();
    }

    public final void setItemAnimation(AnimationType animationType) {
        AlphaInAnimation alphaInAnimation;
        Intrinsics.checkNotNullParameter(animationType, "animationType");
        int i = WhenMappings.$EnumSwitchMapping$0[animationType.ordinal()];
        if (i == 1) {
            alphaInAnimation = new AlphaInAnimation(0L, 0.0f, 3, null);
        } else if (i == 2) {
            alphaInAnimation = new ScaleInAnimation(0L, 0.0f, 3, null);
        } else if (i == 3) {
            alphaInAnimation = new SlideInBottomAnimation(0L, 1, null);
        } else if (i == 4) {
            alphaInAnimation = new SlideInLeftAnimation(0L, 1, null);
        } else {
            if (i != 5) {
                throw new NoWhenBranchMatchedException();
            }
            alphaInAnimation = new SlideInRightAnimation(0L, 1, null);
        }
        setItemAnimation(alphaInAnimation);
    }

    public void submitList(List<? extends T> list) {
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        this.mLastPosition = -1;
        boolean zDisplayEmptyView$default = displayEmptyView$default(this, null, 1, null);
        boolean zDisplayEmptyView = displayEmptyView(list);
        if (zDisplayEmptyView$default && !zDisplayEmptyView) {
            setItems(list);
            notifyItemRemoved(0);
            notifyItemRangeInserted(0, list.size());
        } else if (zDisplayEmptyView && !zDisplayEmptyView$default) {
            notifyItemRangeRemoved(0, getItems().size());
            setItems(list);
            notifyItemInserted(0);
        } else if (zDisplayEmptyView$default && zDisplayEmptyView) {
            setItems(list);
            notifyItemChanged(0, 0);
        } else {
            setItems(list);
            notifyDataSetChanged();
        }
    }

    public void set(int position, T data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (position >= getItems().size()) {
            throw new IndexOutOfBoundsException("position: " + position + ". size:" + getItems().size());
        }
        getMutableItems().set(position, data);
        notifyItemChanged(position);
    }

    public void add(int position, T data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (position > getItems().size() || position < 0) {
            throw new IndexOutOfBoundsException("position: " + position + ". size:" + getItems().size());
        }
        if (displayEmptyView$default(this, null, 1, null)) {
            notifyItemRemoved(0);
        }
        getMutableItems().add(position, data);
        notifyItemInserted(position);
    }

    public void add(T data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (displayEmptyView$default(this, null, 1, null)) {
            notifyItemRemoved(0);
        }
        if (getMutableItems().add(data)) {
            notifyItemInserted(getItems().size() - 1);
        }
    }

    public void addAll(int position, Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        if (collection.isEmpty()) {
            return;
        }
        if (position > getItems().size() || position < 0) {
            throw new IndexOutOfBoundsException("position: " + position + ". size:" + getItems().size());
        }
        if (displayEmptyView$default(this, null, 1, null)) {
            notifyItemRemoved(0);
        }
        if (getMutableItems().addAll(position, collection)) {
            notifyItemRangeInserted(position, collection.size());
        }
    }

    public void addAll(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        if (collection.isEmpty()) {
            return;
        }
        if (displayEmptyView$default(this, null, 1, null)) {
            notifyItemRemoved(0);
        }
        int size = getItems().size();
        if (getMutableItems().addAll(collection)) {
            notifyItemRangeInserted(size, collection.size());
        }
    }

    public void removeAt(int position) {
        if (position >= getItems().size()) {
            throw new IndexOutOfBoundsException("position: " + position + ". size:" + getItems().size());
        }
        getMutableItems().remove(position);
        notifyItemRemoved(position);
        if (displayEmptyView$default(this, null, 1, null)) {
            notifyItemInserted(0);
        }
    }

    public void remove(T data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int iIndexOf = getItems().indexOf(data);
        if (iIndexOf == -1) {
            return;
        }
        removeAt(iIndexOf);
    }

    public void removeAtRange(IntRange range) {
        int last;
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            return;
        }
        if (range.getFirst() >= getItems().size()) {
            throw new IndexOutOfBoundsException("Range first position: " + range.getFirst() + " - last position: " + range.getLast() + ". size:" + getItems().size());
        }
        if (range.getLast() >= getItems().size()) {
            last = getItems().size() - 1;
        } else {
            last = range.getLast();
        }
        int first = range.getFirst();
        if (first <= last) {
            int i = last;
            while (true) {
                getMutableItems().remove(i);
                if (i == first) {
                    break;
                } else {
                    i--;
                }
            }
        }
        notifyItemRangeRemoved(range.getFirst(), (last - range.getFirst()) + 1);
        if (displayEmptyView$default(this, null, 1, null)) {
            notifyItemInserted(0);
        }
    }

    public void swap(int fromPosition, int toPosition) {
        if (fromPosition >= 0 && fromPosition < getItems().size()) {
            if (toPosition >= 0 && toPosition < getItems().size()) {
                Collections.swap(getItems(), fromPosition, toPosition);
                notifyItemChanged(fromPosition);
                notifyItemChanged(toPosition);
            }
        }
    }

    public void move(int fromPosition, int toPosition) {
        if (fromPosition >= 0 && fromPosition < getItems().size()) {
            if (toPosition >= 0 && toPosition < getItems().size()) {
                getMutableItems().add(toPosition, getMutableItems().remove(fromPosition));
                notifyItemMoved(fromPosition, toPosition);
            }
        }
    }

    private final List<T> getMutableItems() {
        List<T> items = getItems();
        if (items instanceof ArrayList) {
            List<T> items2 = getItems();
            Intrinsics.checkNotNull(items2, "null cannot be cast to non-null type java.util.ArrayList<T of com.chad.library.adapter4.BaseQuickAdapter>");
            return (ArrayList) items2;
        }
        if (TypeIntrinsics.isMutableList(items)) {
            List<T> items3 = getItems();
            Intrinsics.checkNotNull(items3, "null cannot be cast to non-null type kotlin.collections.MutableList<T of com.chad.library.adapter4.BaseQuickAdapter>");
            return TypeIntrinsics.asMutableList(items3);
        }
        List<T> mutableList = CollectionsKt.toMutableList((Collection) getItems());
        setItems(mutableList);
        return mutableList;
    }

    public final BaseQuickAdapter<T, VH> setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
        return this;
    }

    public final OnItemClickListener<T> getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    public final BaseQuickAdapter<T, VH> setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        this.mOnItemLongClickListener = listener;
        return this;
    }

    public final OnItemLongClickListener<T> getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    public final BaseQuickAdapter<T, VH> addOnItemChildClickListener(int id, OnItemChildClickListener<T> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        SparseArray<OnItemChildClickListener<T>> sparseArray = this.mOnItemChildClickArray;
        if (sparseArray == null) {
            sparseArray = new SparseArray<>(2);
        }
        sparseArray.put(id, listener);
        this.mOnItemChildClickArray = sparseArray;
        return this;
    }

    public final BaseQuickAdapter<T, VH> removeOnItemChildClickListener(int id) {
        SparseArray<OnItemChildClickListener<T>> sparseArray = this.mOnItemChildClickArray;
        if (sparseArray != null) {
            sparseArray.remove(id);
        }
        return this;
    }

    public final BaseQuickAdapter<T, VH> addOnItemChildLongClickListener(int id, OnItemChildLongClickListener<T> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        SparseArray<OnItemChildLongClickListener<T>> sparseArray = this.mOnItemChildLongClickArray;
        if (sparseArray == null) {
            sparseArray = new SparseArray<>(2);
        }
        sparseArray.put(id, listener);
        this.mOnItemChildLongClickArray = sparseArray;
        return this;
    }

    public final BaseQuickAdapter<T, VH> removeOnItemChildLongClickListener(int id) {
        SparseArray<OnItemChildLongClickListener<T>> sparseArray = this.mOnItemChildLongClickArray;
        if (sparseArray != null) {
            sparseArray.remove(id);
        }
        return this;
    }

    public final BaseQuickAdapter<T, VH> addOnViewAttachStateChangeListener(OnViewAttachStateChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ArrayList arrayList = this.mOnViewAttachStateChangeListeners;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        if (!arrayList.contains(listener)) {
            arrayList.add(listener);
        }
        this.mOnViewAttachStateChangeListeners = arrayList;
        return this;
    }

    public final void removeOnViewAttachStateChangeListener(OnViewAttachStateChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        List<OnViewAttachStateChangeListener> list = this.mOnViewAttachStateChangeListeners;
        if (list != null) {
            list.remove(listener);
        }
    }

    public final void clearOnViewAttachStateChangeListener() {
        List<OnViewAttachStateChangeListener> list = this.mOnViewAttachStateChangeListeners;
        if (list != null) {
            list.clear();
        }
    }
}
