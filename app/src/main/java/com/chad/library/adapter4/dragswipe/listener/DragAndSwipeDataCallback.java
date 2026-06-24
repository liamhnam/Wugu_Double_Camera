package com.chad.library.adapter4.dragswipe.listener;

import kotlin.Metadata;

@Metadata(m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, m1293d2 = {"Lcom/chad/library/adapter4/dragswipe/listener/DragAndSwipeDataCallback;", "", "dataMove", "", "fromPosition", "", "toPosition", "dataRemoveAt", "position", "com.github.CymChad.brvah"}, m1294k = 1, m1295mv = {1, 8, 0}, m1297xi = 48)
public interface DragAndSwipeDataCallback {
    void dataMove(int fromPosition, int toPosition);

    void dataRemoveAt(int position);
}
