package com.p020hp.printsdk;

import android.database.Cursor;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

public class CallableC1703g3 implements Callable<List<C1766t2>> {

    public final RoomSQLiteQuery f1220a;

    public final C1666a3 f1221b;

    public CallableC1703g3(C1666a3 c1666a3, RoomSQLiteQuery roomSQLiteQuery) {
        this.f1221b = c1666a3;
        this.f1220a = roomSQLiteQuery;
    }

    @Override
    public List<C1766t2> call() {
        String string;
        int i;
        String string2;
        int i2;
        String string3;
        int i3;
        String string4;
        int i4;
        String string5;
        int i5;
        String string6;
        int i6;
        String string7;
        int i7;
        String string8;
        int i8;
        String string9;
        int i9;
        String string10;
        int i10;
        this.f1221b.f884a.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.f1221b.f884a, this.f1220a, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_name");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_create_time");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_submit_time");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_status_info");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "printer_id");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_done_time");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_type");
                int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_size");
                int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_path");
                int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_copies");
                int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_mediaSize");
                int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_color");
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_scale");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_quality");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_sides");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_orientation");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_pageRanges");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_mediaMargin");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_inputTray");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_paperType");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "job_options_dpi");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "impressionsCompleted");
                int i11 = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    if (cursorQuery.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow;
                        string = null;
                    } else {
                        string = cursorQuery.getString(columnIndexOrThrow);
                        i = columnIndexOrThrow;
                    }
                    if (this.f1221b.f886c != null) {
                        UUID uuidFromString = UUID.fromString(string);
                        String string11 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        long j = cursorQuery.getLong(columnIndexOrThrow3);
                        long j2 = cursorQuery.getLong(columnIndexOrThrow4);
                        HpPrintJobState hpPrintJobStateM565a = this.f1221b.f886c.m565a(cursorQuery.getInt(columnIndexOrThrow5));
                        String string12 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                        if (this.f1221b.f886c != null) {
                            UUID uuidFromString2 = UUID.fromString(string12);
                            long j3 = cursorQuery.getLong(columnIndexOrThrow7);
                            String string13 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                            String string14 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                            String string15 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                            String string16 = cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11);
                            if (cursorQuery.isNull(columnIndexOrThrow12)) {
                                i2 = i11;
                                string2 = null;
                            } else {
                                string2 = cursorQuery.getString(columnIndexOrThrow12);
                                i2 = i11;
                            }
                            if (cursorQuery.isNull(i2)) {
                                i3 = columnIndexOrThrow14;
                                string3 = null;
                            } else {
                                string3 = cursorQuery.getString(i2);
                                i3 = columnIndexOrThrow14;
                            }
                            if (cursorQuery.isNull(i3)) {
                                i11 = i2;
                                i4 = columnIndexOrThrow15;
                                string4 = null;
                            } else {
                                i11 = i2;
                                string4 = cursorQuery.getString(i3);
                                i4 = columnIndexOrThrow15;
                            }
                            if (cursorQuery.isNull(i4)) {
                                columnIndexOrThrow15 = i4;
                                i5 = columnIndexOrThrow16;
                                string5 = null;
                            } else {
                                columnIndexOrThrow15 = i4;
                                string5 = cursorQuery.getString(i4);
                                i5 = columnIndexOrThrow16;
                            }
                            if (cursorQuery.isNull(i5)) {
                                columnIndexOrThrow16 = i5;
                                i6 = columnIndexOrThrow17;
                                string6 = null;
                            } else {
                                columnIndexOrThrow16 = i5;
                                string6 = cursorQuery.getString(i5);
                                i6 = columnIndexOrThrow17;
                            }
                            if (cursorQuery.isNull(i6)) {
                                columnIndexOrThrow17 = i6;
                                i7 = columnIndexOrThrow18;
                                string7 = null;
                            } else {
                                columnIndexOrThrow17 = i6;
                                string7 = cursorQuery.getString(i6);
                                i7 = columnIndexOrThrow18;
                            }
                            if (cursorQuery.isNull(i7)) {
                                columnIndexOrThrow18 = i7;
                                i8 = columnIndexOrThrow19;
                                string8 = null;
                            } else {
                                columnIndexOrThrow18 = i7;
                                string8 = cursorQuery.getString(i7);
                                i8 = columnIndexOrThrow19;
                            }
                            int i12 = cursorQuery.getInt(i8);
                            columnIndexOrThrow19 = i8;
                            int i13 = columnIndexOrThrow20;
                            if (cursorQuery.isNull(i13)) {
                                columnIndexOrThrow20 = i13;
                                i9 = columnIndexOrThrow21;
                                string9 = null;
                            } else {
                                columnIndexOrThrow20 = i13;
                                string9 = cursorQuery.getString(i13);
                                i9 = columnIndexOrThrow21;
                            }
                            if (cursorQuery.isNull(i9)) {
                                columnIndexOrThrow21 = i9;
                                i10 = columnIndexOrThrow22;
                                string10 = null;
                            } else {
                                columnIndexOrThrow21 = i9;
                                string10 = cursorQuery.getString(i9);
                                i10 = columnIndexOrThrow22;
                            }
                            columnIndexOrThrow22 = i10;
                            C1766t2 c1766t2 = new C1766t2(uuidFromString, string11, j, j2, hpPrintJobStateM565a, uuidFromString2, j3, string13, string14, string15, string16, string2, string3, string4, string5, string6, string7, string8, i12, string9, string10, cursorQuery.getInt(i10));
                            int i14 = columnIndexOrThrow2;
                            int i15 = columnIndexOrThrow23;
                            c1766t2.setImpressionsCompleted(cursorQuery.getInt(i15));
                            arrayList.add(c1766t2);
                            columnIndexOrThrow3 = columnIndexOrThrow3;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow23 = i15;
                            columnIndexOrThrow2 = i14;
                            columnIndexOrThrow14 = i3;
                        }
                    }
                    throw null;
                }
                this.f1221b.f884a.setTransactionSuccessful();
                return arrayList;
            } finally {
                cursorQuery.close();
                this.f1220a.release();
            }
        } finally {
            this.f1221b.f884a.endTransaction();
        }
    }
}
