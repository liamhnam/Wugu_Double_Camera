package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p014db.SupportSQLiteDatabase;

public final class C0537xee9a7189 implements Function {
    @Override
    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).yieldIfContendedSafely());
    }
}
