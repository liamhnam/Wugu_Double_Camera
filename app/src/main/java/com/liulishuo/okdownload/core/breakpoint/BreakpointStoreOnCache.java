package com.liulishuo.okdownload.core.breakpoint;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.IdentifiedTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BreakpointStoreOnCache implements DownloadStore {
    public static final int FIRST_ID = 1;
    private final KeyToIdMap keyToIdMap;
    private final HashMap<String, String> responseFilenameMap;
    private final List<Integer> sortedOccupiedIds;
    private final SparseArray<BreakpointInfo> storedInfos;
    private final SparseArray<IdentifiedTask> unStoredTasks;

    @Override
    public BreakpointInfo getAfterCompleted(int i) {
        return null;
    }

    @Override
    public boolean isOnlyMemoryCache() {
        return true;
    }

    @Override
    public void onTaskStart(int i) {
    }

    public BreakpointStoreOnCache() {
        this(new SparseArray(), new HashMap());
    }

    BreakpointStoreOnCache(SparseArray<BreakpointInfo> sparseArray, HashMap<String, String> map, SparseArray<IdentifiedTask> sparseArray2, List<Integer> list, KeyToIdMap keyToIdMap) {
        this.unStoredTasks = sparseArray2;
        this.storedInfos = sparseArray;
        this.responseFilenameMap = map;
        this.sortedOccupiedIds = list;
        this.keyToIdMap = keyToIdMap;
    }

    public BreakpointStoreOnCache(SparseArray<BreakpointInfo> sparseArray, HashMap<String, String> map) {
        this.unStoredTasks = new SparseArray<>();
        this.storedInfos = sparseArray;
        this.responseFilenameMap = map;
        this.keyToIdMap = new KeyToIdMap();
        int size = sparseArray.size();
        this.sortedOccupiedIds = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.sortedOccupiedIds.add(Integer.valueOf(sparseArray.valueAt(i).f2072id));
        }
        Collections.sort(this.sortedOccupiedIds);
    }

    @Override
    public BreakpointInfo get(int i) {
        return this.storedInfos.get(i);
    }

    @Override
    public BreakpointInfo createAndInsert(DownloadTask downloadTask) {
        int id = downloadTask.getId();
        BreakpointInfo breakpointInfo = new BreakpointInfo(id, downloadTask.getUrl(), downloadTask.getParentFile(), downloadTask.getFilename());
        synchronized (this) {
            this.storedInfos.put(id, breakpointInfo);
            this.unStoredTasks.remove(id);
        }
        return breakpointInfo;
    }

    @Override
    public void onSyncToFilesystemSuccess(BreakpointInfo breakpointInfo, int i, long j) throws IOException {
        BreakpointInfo breakpointInfo2 = this.storedInfos.get(breakpointInfo.f2072id);
        if (breakpointInfo != breakpointInfo2) {
            throw new IOException("Info not on store!");
        }
        breakpointInfo2.getBlock(i).increaseCurrentOffset(j);
    }

    @Override
    public boolean update(BreakpointInfo breakpointInfo) {
        String filename = breakpointInfo.getFilename();
        if (breakpointInfo.isTaskOnlyProvidedParentPath() && filename != null) {
            this.responseFilenameMap.put(breakpointInfo.getUrl(), filename);
        }
        BreakpointInfo breakpointInfo2 = this.storedInfos.get(breakpointInfo.f2072id);
        if (breakpointInfo2 == null) {
            return false;
        }
        if (breakpointInfo2 == breakpointInfo) {
            return true;
        }
        synchronized (this) {
            this.storedInfos.put(breakpointInfo.f2072id, breakpointInfo.copy());
        }
        return true;
    }

    @Override
    public void onTaskEnd(int i, EndCause endCause, Exception exc) {
        if (endCause == EndCause.COMPLETED) {
            remove(i);
        }
    }

    @Override
    public synchronized void remove(int i) {
        this.storedInfos.remove(i);
        if (this.unStoredTasks.get(i) == null) {
            this.sortedOccupiedIds.remove(Integer.valueOf(i));
        }
        this.keyToIdMap.remove(i);
    }

    @Override
    public synchronized int findOrCreateId(DownloadTask downloadTask) {
        Integer num = this.keyToIdMap.get(downloadTask);
        if (num != null) {
            return num.intValue();
        }
        int size = this.storedInfos.size();
        for (int i = 0; i < size; i++) {
            BreakpointInfo breakpointInfoValueAt = this.storedInfos.valueAt(i);
            if (breakpointInfoValueAt != null && breakpointInfoValueAt.isSameFrom(downloadTask)) {
                return breakpointInfoValueAt.f2072id;
            }
        }
        int size2 = this.unStoredTasks.size();
        for (int i2 = 0; i2 < size2; i2++) {
            IdentifiedTask identifiedTaskValueAt = this.unStoredTasks.valueAt(i2);
            if (identifiedTaskValueAt != null && identifiedTaskValueAt.compareIgnoreId(downloadTask)) {
                return identifiedTaskValueAt.getId();
            }
        }
        int iAllocateId = allocateId();
        this.unStoredTasks.put(iAllocateId, downloadTask.mock(iAllocateId));
        this.keyToIdMap.add(downloadTask, iAllocateId);
        return iAllocateId;
    }

    @Override
    public BreakpointInfo findAnotherInfoFromCompare(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        SparseArray<BreakpointInfo> sparseArrayClone;
        synchronized (this) {
            sparseArrayClone = this.storedInfos.clone();
        }
        int size = sparseArrayClone.size();
        for (int i = 0; i < size; i++) {
            BreakpointInfo breakpointInfoValueAt = sparseArrayClone.valueAt(i);
            if (breakpointInfoValueAt != breakpointInfo && breakpointInfoValueAt.isSameFrom(downloadTask)) {
                return breakpointInfoValueAt;
            }
        }
        return null;
    }

    @Override
    public String getResponseFilename(String str) {
        return this.responseFilenameMap.get(str);
    }

    synchronized int allocateId() {
        int iIntValue;
        int i = 0;
        int size = 0;
        int i2 = 0;
        while (true) {
            iIntValue = 1;
            if (size >= this.sortedOccupiedIds.size()) {
                size = 0;
                break;
            }
            Integer num = this.sortedOccupiedIds.get(size);
            if (num == null) {
                i = i2 + 1;
                break;
            }
            int iIntValue2 = num.intValue();
            if (i2 != 0) {
                int i3 = i2 + 1;
                if (iIntValue2 != i3) {
                    i = i3;
                    break;
                }
                size++;
                i2 = iIntValue2;
            } else {
                if (iIntValue2 != 1) {
                    size = 0;
                    i = 1;
                    break;
                }
                size++;
                i2 = iIntValue2;
            }
        }
        if (i != 0) {
            iIntValue = i;
        } else if (!this.sortedOccupiedIds.isEmpty()) {
            List<Integer> list = this.sortedOccupiedIds;
            iIntValue = 1 + list.get(list.size() - 1).intValue();
            size = this.sortedOccupiedIds.size();
        }
        this.sortedOccupiedIds.add(size, Integer.valueOf(iIntValue));
        return iIntValue;
    }
}
