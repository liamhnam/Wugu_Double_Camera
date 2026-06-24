package com.chad.library.adapter4.dragswipe;

import android.graphics.Canvas;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.RecyclerView;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aê\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00012:\b\u0006\u0010\u0002\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u00032b\b\u0006\u0010\u000b\u001a\\\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\n0\f28\b\u0006\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003H\u0086\bø\u0001\u0000\u001aÎ\u0002\u0010\u0012\u001a\u00020\u0001*\u00020\u00012:\b\u0006\u0010\u0013\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\n0\u00032w\b\u0006\u0010\u0015\u001aq\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\n0\u00162M\b\u0006\u0010\u001e\u001aG\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\n0\u001f28\b\u0006\u0010!\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\n0\u0003H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, m1293d2 = {"setItemDragListener", "Lcom/chad/library/adapter4/dragswipe/QuickDragAndSwipe;", "onItemDragStart", "Lkotlin/Function2;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lkotlin/ParameterName;", NamingTable.TAG, "viewHolder", "", "pos", "", "onItemDragMoving", "Lkotlin/Function4;", "source", TypedValues.TransitionType.S_FROM, TypedValues.AttributesType.S_TARGET, TypedValues.TransitionType.S_TO, "onItemDragEnd", "setItemSwipeListener", "onItemSwipeStart", "bindingAdapterPosition", "onItemSwipeMoving", "Lkotlin/Function5;", "Landroid/graphics/Canvas;", "canvas", "", "dX", "dY", "", "isCurrentlyActive", "onItemSwiped", "Lkotlin/Function3;", "direction", "onItemSwipeEnd", "com.github.CymChad.brvah"}, m1294k = 2, m1295mv = {1, 8, 0}, m1297xi = 48)
public final class DragSwipeExtKt {
    public static QuickDragAndSwipe setItemDragListener$default(QuickDragAndSwipe quickDragAndSwipe, Function2 onItemDragStart, Function4 onItemDragMoving, Function2 onItemDragEnd, int i, Object obj) {
        if ((i & 1) != 0) {
            onItemDragStart = new Function2<RecyclerView.ViewHolder, Integer, Unit>() {
                public final void invoke(RecyclerView.ViewHolder viewHolder, int i2) {
                }

                @Override
                public Unit invoke(RecyclerView.ViewHolder viewHolder, Integer num) {
                    invoke(viewHolder, num.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            onItemDragMoving = new Function4<RecyclerView.ViewHolder, Integer, RecyclerView.ViewHolder, Integer, Unit>() {
                public final void invoke(RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i3) {
                    Intrinsics.checkNotNullParameter(viewHolder, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(viewHolder2, "<anonymous parameter 2>");
                }

                @Override
                public Unit invoke(RecyclerView.ViewHolder viewHolder, Integer num, RecyclerView.ViewHolder viewHolder2, Integer num2) {
                    invoke(viewHolder, num.intValue(), viewHolder2, num2.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            onItemDragEnd = new Function2<RecyclerView.ViewHolder, Integer, Unit>() {
                public final void invoke(RecyclerView.ViewHolder viewHolder, int i2) {
                    Intrinsics.checkNotNullParameter(viewHolder, "<anonymous parameter 0>");
                }

                @Override
                public Unit invoke(RecyclerView.ViewHolder viewHolder, Integer num) {
                    invoke(viewHolder, num.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(quickDragAndSwipe, "<this>");
        Intrinsics.checkNotNullParameter(onItemDragStart, "onItemDragStart");
        Intrinsics.checkNotNullParameter(onItemDragMoving, "onItemDragMoving");
        Intrinsics.checkNotNullParameter(onItemDragEnd, "onItemDragEnd");
        quickDragAndSwipe.setItemDragListener(new DragSwipeExtKt$setItemDragListener$4$listener$1(onItemDragStart, onItemDragMoving, onItemDragEnd));
        return quickDragAndSwipe;
    }

    public static final QuickDragAndSwipe setItemDragListener(QuickDragAndSwipe quickDragAndSwipe, Function2<? super RecyclerView.ViewHolder, ? super Integer, Unit> onItemDragStart, Function4<? super RecyclerView.ViewHolder, ? super Integer, ? super RecyclerView.ViewHolder, ? super Integer, Unit> onItemDragMoving, Function2<? super RecyclerView.ViewHolder, ? super Integer, Unit> onItemDragEnd) {
        Intrinsics.checkNotNullParameter(quickDragAndSwipe, "<this>");
        Intrinsics.checkNotNullParameter(onItemDragStart, "onItemDragStart");
        Intrinsics.checkNotNullParameter(onItemDragMoving, "onItemDragMoving");
        Intrinsics.checkNotNullParameter(onItemDragEnd, "onItemDragEnd");
        quickDragAndSwipe.setItemDragListener(new DragSwipeExtKt$setItemDragListener$4$listener$1(onItemDragStart, onItemDragMoving, onItemDragEnd));
        return quickDragAndSwipe;
    }

    public static QuickDragAndSwipe setItemSwipeListener$default(QuickDragAndSwipe quickDragAndSwipe, Function2 onItemSwipeStart, Function5 onItemSwipeMoving, Function3 onItemSwiped, Function2 onItemSwipeEnd, int i, Object obj) {
        if ((i & 1) != 0) {
            onItemSwipeStart = new Function2<RecyclerView.ViewHolder, Integer, Unit>() {
                public final void invoke(RecyclerView.ViewHolder viewHolder, int i2) {
                }

                @Override
                public Unit invoke(RecyclerView.ViewHolder viewHolder, Integer num) {
                    invoke(viewHolder, num.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            onItemSwipeMoving = new Function5<Canvas, RecyclerView.ViewHolder, Float, Float, Boolean, Unit>() {
                public final void invoke(Canvas canvas, RecyclerView.ViewHolder viewHolder, float f, float f2, boolean z) {
                    Intrinsics.checkNotNullParameter(canvas, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(viewHolder, "<anonymous parameter 1>");
                }

                @Override
                public Unit invoke(Canvas canvas, RecyclerView.ViewHolder viewHolder, Float f, Float f2, Boolean bool) {
                    invoke(canvas, viewHolder, f.floatValue(), f2.floatValue(), bool.booleanValue());
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            onItemSwiped = new Function3<RecyclerView.ViewHolder, Integer, Integer, Unit>() {
                public final void invoke(RecyclerView.ViewHolder viewHolder, int i2, int i3) {
                    Intrinsics.checkNotNullParameter(viewHolder, "<anonymous parameter 0>");
                }

                @Override
                public Unit invoke(RecyclerView.ViewHolder viewHolder, Integer num, Integer num2) {
                    invoke(viewHolder, num.intValue(), num2.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 8) != 0) {
            onItemSwipeEnd = new Function2<RecyclerView.ViewHolder, Integer, Unit>() {
                public final void invoke(RecyclerView.ViewHolder viewHolder, int i2) {
                    Intrinsics.checkNotNullParameter(viewHolder, "<anonymous parameter 0>");
                }

                @Override
                public Unit invoke(RecyclerView.ViewHolder viewHolder, Integer num) {
                    invoke(viewHolder, num.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(quickDragAndSwipe, "<this>");
        Intrinsics.checkNotNullParameter(onItemSwipeStart, "onItemSwipeStart");
        Intrinsics.checkNotNullParameter(onItemSwipeMoving, "onItemSwipeMoving");
        Intrinsics.checkNotNullParameter(onItemSwiped, "onItemSwiped");
        Intrinsics.checkNotNullParameter(onItemSwipeEnd, "onItemSwipeEnd");
        quickDragAndSwipe.setItemSwipeListener(new DragSwipeExtKt$setItemSwipeListener$5$listener$1(onItemSwipeStart, onItemSwipeMoving, onItemSwiped, onItemSwipeEnd));
        return quickDragAndSwipe;
    }

    public static final QuickDragAndSwipe setItemSwipeListener(QuickDragAndSwipe quickDragAndSwipe, Function2<? super RecyclerView.ViewHolder, ? super Integer, Unit> onItemSwipeStart, Function5<? super Canvas, ? super RecyclerView.ViewHolder, ? super Float, ? super Float, ? super Boolean, Unit> onItemSwipeMoving, Function3<? super RecyclerView.ViewHolder, ? super Integer, ? super Integer, Unit> onItemSwiped, Function2<? super RecyclerView.ViewHolder, ? super Integer, Unit> onItemSwipeEnd) {
        Intrinsics.checkNotNullParameter(quickDragAndSwipe, "<this>");
        Intrinsics.checkNotNullParameter(onItemSwipeStart, "onItemSwipeStart");
        Intrinsics.checkNotNullParameter(onItemSwipeMoving, "onItemSwipeMoving");
        Intrinsics.checkNotNullParameter(onItemSwiped, "onItemSwiped");
        Intrinsics.checkNotNullParameter(onItemSwipeEnd, "onItemSwipeEnd");
        quickDragAndSwipe.setItemSwipeListener(new DragSwipeExtKt$setItemSwipeListener$5$listener$1(onItemSwipeStart, onItemSwipeMoving, onItemSwiped, onItemSwipeEnd));
        return quickDragAndSwipe;
    }
}
