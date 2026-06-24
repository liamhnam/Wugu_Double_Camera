package com.p020hp.printsdk;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

public class CallableC1697f3 implements Callable<List<C1713i3>> {

    public final RoomSQLiteQuery f1205a;

    public final C1666a3 f1206b;

    public CallableC1697f3(C1666a3 c1666a3, RoomSQLiteQuery roomSQLiteQuery) {
        this.f1206b = c1666a3;
        this.f1205a = roomSQLiteQuery;
    }

    @Override
    public List<C1713i3> call() {
        C1708h3 c1708h3;
        this.f1206b.f884a.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.f1206b.f884a, this.f1205a, true, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_name");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_uri");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_image");
                ArrayMap<String, ArrayList<C1766t2>> arrayMap = new ArrayMap<>();
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndexOrThrow);
                    if (arrayMap.get(string) == null) {
                        arrayMap.put(string, new ArrayList<>());
                    }
                }
                cursorQuery.moveToPosition(-1);
                this.f1206b.m463a(arrayMap);
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    if (cursorQuery.isNull(columnIndexOrThrow) && cursorQuery.isNull(columnIndexOrThrow2) && cursorQuery.isNull(columnIndexOrThrow3) && cursorQuery.isNull(columnIndexOrThrow4)) {
                        c1708h3 = null;
                    } else {
                        String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        this.f1206b.f886c.getClass();
                        c1708h3 = new C1708h3(UUID.fromString(string2), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), this.f1206b.f886c.m567a(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)), this.f1206b.f886c.m567a(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4)));
                    }
                    ArrayList<C1766t2> arrayList2 = arrayMap.get(cursorQuery.getString(columnIndexOrThrow));
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList<>();
                    }
                    arrayList.add(new C1713i3(c1708h3, arrayList2));
                }
                this.f1206b.f884a.setTransactionSuccessful();
                return arrayList;
            } finally {
                cursorQuery.close();
                this.f1205a.release();
            }
        } finally {
            this.f1206b.f884a.endTransaction();
        }
    }
}
