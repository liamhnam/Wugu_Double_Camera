package com.p020hp.printsdk;

import android.database.Cursor;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

public class CallableC1781w2 implements Callable<List<C1708h3>> {

    public final RoomSQLiteQuery f1950a;

    public final C1666a3 f1951b;

    public CallableC1781w2(C1666a3 c1666a3, RoomSQLiteQuery roomSQLiteQuery) {
        this.f1951b = c1666a3;
        this.f1950a = roomSQLiteQuery;
    }

    @Override
    public List<C1708h3> call() {
        this.f1951b.f884a.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.f1951b.f884a, this.f1950a, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_name");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_uri");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_image");
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                    this.f1951b.f886c.getClass();
                    arrayList.add(new C1708h3(UUID.fromString(string), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), this.f1951b.f886c.m567a(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)), this.f1951b.f886c.m567a(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4))));
                }
                this.f1951b.f884a.setTransactionSuccessful();
                return arrayList;
            } finally {
                cursorQuery.close();
                this.f1950a.release();
            }
        } finally {
            this.f1951b.f884a.endTransaction();
        }
    }
}
