package com.p020hp.printsdk;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.p014db.SupportSQLiteStatement;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import java.net.URI;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public final class C1666a3 extends AbstractC1771u2 {

    public final RoomDatabase f884a;

    public final EntityInsertionAdapter<C1766t2> f885b;

    public final C1718j3 f886c = new C1718j3();

    public final EntityInsertionAdapter<C1708h3> f887d;

    public final SharedSQLiteStatement f888e;

    public class a extends EntityInsertionAdapter<C1766t2> {
        public a(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override
        public void bind(SupportSQLiteStatement supportSQLiteStatement, C1766t2 c1766t2) {
            C1766t2 c1766t22 = c1766t2;
            String strM566a = C1666a3.this.f886c.m566a(c1766t22.f1794b);
            if (strM566a == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, strM566a);
            }
            String str = c1766t22.f1795c;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            supportSQLiteStatement.bindLong(3, c1766t22.f1796d);
            supportSQLiteStatement.bindLong(4, c1766t22.f1797e);
            supportSQLiteStatement.bindLong(5, C1666a3.this.f886c.m564a(c1766t22.f1798f));
            String strM566a2 = C1666a3.this.f886c.m566a(c1766t22.f1799g);
            if (strM566a2 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, strM566a2);
            }
            supportSQLiteStatement.bindLong(7, c1766t22.f1800h);
            String str2 = c1766t22.f1801i;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, str2);
            }
            String str3 = c1766t22.f1802j;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, str3);
            }
            String str4 = c1766t22.f1803k;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, str4);
            }
            String str5 = c1766t22.f1804l;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, str5);
            }
            String str6 = c1766t22.f1805m;
            if (str6 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, str6);
            }
            String str7 = c1766t22.f1806n;
            if (str7 == null) {
                supportSQLiteStatement.bindNull(13);
            } else {
                supportSQLiteStatement.bindString(13, str7);
            }
            String str8 = c1766t22.f1807o;
            if (str8 == null) {
                supportSQLiteStatement.bindNull(14);
            } else {
                supportSQLiteStatement.bindString(14, str8);
            }
            String str9 = c1766t22.f1808p;
            if (str9 == null) {
                supportSQLiteStatement.bindNull(15);
            } else {
                supportSQLiteStatement.bindString(15, str9);
            }
            String str10 = c1766t22.f1809q;
            if (str10 == null) {
                supportSQLiteStatement.bindNull(16);
            } else {
                supportSQLiteStatement.bindString(16, str10);
            }
            String str11 = c1766t22.f1810r;
            if (str11 == null) {
                supportSQLiteStatement.bindNull(17);
            } else {
                supportSQLiteStatement.bindString(17, str11);
            }
            String str12 = c1766t22.f1811s;
            if (str12 == null) {
                supportSQLiteStatement.bindNull(18);
            } else {
                supportSQLiteStatement.bindString(18, str12);
            }
            supportSQLiteStatement.bindLong(19, c1766t22.f1812t);
            String str13 = c1766t22.f1813u;
            if (str13 == null) {
                supportSQLiteStatement.bindNull(20);
            } else {
                supportSQLiteStatement.bindString(20, str13);
            }
            String str14 = c1766t22.f1814v;
            if (str14 == null) {
                supportSQLiteStatement.bindNull(21);
            } else {
                supportSQLiteStatement.bindString(21, str14);
            }
            supportSQLiteStatement.bindLong(22, c1766t22.f1815w);
            supportSQLiteStatement.bindLong(23, c1766t22.getF787a());
        }

        @Override
        public String createQuery() {
            return "INSERT OR REPLACE INTO `history_job` (`job_id`,`job_name`,`job_create_time`,`job_submit_time`,`job_status_info`,`printer_id`,`job_done_time`,`job_type`,`job_size`,`job_path`,`job_options_copies`,`job_options_mediaSize`,`job_options_color`,`job_options_scale`,`job_options_quality`,`job_options_sides`,`job_options_orientation`,`job_options_pageRanges`,`job_options_mediaMargin`,`job_options_inputTray`,`job_options_paperType`,`job_options_dpi`,`impressionsCompleted`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    public class b extends EntityInsertionAdapter<C1708h3> {
        public b(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override
        public void bind(SupportSQLiteStatement supportSQLiteStatement, C1708h3 c1708h3) {
            C1708h3 c1708h32 = c1708h3;
            String strM566a = C1666a3.this.f886c.m566a(c1708h32.f1307a);
            if (strM566a == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, strM566a);
            }
            String str = c1708h32.f1308b;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            C1718j3 c1718j3 = C1666a3.this.f886c;
            URI uri = c1708h32.f1309c;
            c1718j3.getClass();
            String string = uri != null ? uri.toString() : null;
            if (string == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, string);
            }
            C1718j3 c1718j32 = C1666a3.this.f886c;
            URI uri2 = c1708h32.f1310d;
            c1718j32.getClass();
            String string2 = uri2 != null ? uri2.toString() : null;
            if (string2 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, string2);
            }
        }

        @Override
        public String createQuery() {
            return "INSERT OR REPLACE INTO `history_printer` (`printer_id`,`printer_name`,`printer_uri`,`printer_image`) VALUES (?,?,?,?)";
        }
    }

    public class c extends SharedSQLiteStatement {
        public c(C1666a3 c1666a3, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override
        public String createQuery() {
            return "Delete FROM history_job WHERE job_submit_time < ?";
        }
    }

    public C1666a3(RoomDatabase roomDatabase) {
        this.f884a = roomDatabase;
        this.f885b = new a(roomDatabase);
        this.f887d = new b(roomDatabase);
        this.f888e = new c(this, roomDatabase);
    }

    public final void m463a(ArrayMap<String, ArrayList<C1766t2>> arrayMap) {
        Set<String> setKeySet = arrayMap.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (arrayMap.size() > 999) {
            ArrayMap<String, ArrayList<C1766t2>> arrayMap2 = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = arrayMap.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap2.put(arrayMap.keyAt(i), arrayMap.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    m463a(arrayMap2);
                    arrayMap2 = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                m463a(arrayMap2);
                return;
            }
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `job_id`,`job_name`,`job_create_time`,`job_submit_time`,`job_status_info`,`printer_id`,`job_done_time`,`job_type`,`job_size`,`job_path`,`job_options_copies`,`job_options_mediaSize`,`job_options_color`,`job_options_scale`,`job_options_quality`,`job_options_sides`,`job_options_orientation`,`job_options_pageRanges`,`job_options_mediaMargin`,`job_options_inputTray`,`job_options_paperType`,`job_options_dpi`,`impressionsCompleted` FROM `history_job` WHERE `printer_id` IN (");
        int size2 = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size2);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size2 + 0);
        int i3 = 1;
        for (String str : setKeySet) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i3);
            } else {
                roomSQLiteQueryAcquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor cursorQuery = DBUtil.query(this.f884a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "printer_id");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                ArrayList<C1766t2> arrayList = arrayMap.get(cursorQuery.getString(columnIndex));
                if (arrayList != null) {
                    String string = cursorQuery.isNull(0) ? null : cursorQuery.getString(0);
                    if (this.f886c == null) {
                        throw null;
                    }
                    UUID uuidFromString = UUID.fromString(string);
                    String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                    long j = cursorQuery.getLong(2);
                    long j2 = cursorQuery.getLong(3);
                    HpPrintJobState hpPrintJobStateM565a = this.f886c.m565a(cursorQuery.getInt(4));
                    String string3 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                    if (this.f886c == null) {
                        throw null;
                    }
                    C1766t2 c1766t2 = new C1766t2(uuidFromString, string2, j, j2, hpPrintJobStateM565a, UUID.fromString(string3), cursorQuery.getLong(6), cursorQuery.isNull(7) ? null : cursorQuery.getString(7), cursorQuery.isNull(8) ? null : cursorQuery.getString(8), cursorQuery.isNull(9) ? null : cursorQuery.getString(9), cursorQuery.isNull(10) ? null : cursorQuery.getString(10), cursorQuery.isNull(11) ? null : cursorQuery.getString(11), cursorQuery.isNull(12) ? null : cursorQuery.getString(12), cursorQuery.isNull(13) ? null : cursorQuery.getString(13), cursorQuery.isNull(14) ? null : cursorQuery.getString(14), cursorQuery.isNull(15) ? null : cursorQuery.getString(15), cursorQuery.isNull(16) ? null : cursorQuery.getString(16), cursorQuery.isNull(17) ? null : cursorQuery.getString(17), cursorQuery.getInt(18), cursorQuery.isNull(19) ? null : cursorQuery.getString(19), cursorQuery.isNull(20) ? null : cursorQuery.getString(20), cursorQuery.getInt(21));
                    c1766t2.setImpressionsCompleted(cursorQuery.getInt(22));
                    arrayList.add(c1766t2);
                }
            }
        } finally {
            cursorQuery.close();
        }
    }
}
