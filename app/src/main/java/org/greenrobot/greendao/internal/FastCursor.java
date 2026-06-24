package org.greenrobot.greendao.internal;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

public final class FastCursor implements Cursor {
    private final int count;
    private int position;
    private final CursorWindow window;

    @Override
    public Uri getNotificationUri() {
        return null;
    }

    public FastCursor(CursorWindow cursorWindow) {
        this.window = cursorWindow;
        this.count = cursorWindow.getNumRows();
    }

    @Override
    public int getCount() {
        return this.window.getNumRows();
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public boolean move(int i) {
        return moveToPosition(this.position + i);
    }

    @Override
    public boolean moveToPosition(int i) {
        if (i < 0 || i >= this.count) {
            return false;
        }
        this.position = i;
        return true;
    }

    @Override
    public boolean moveToFirst() {
        this.position = 0;
        return this.count > 0;
    }

    @Override
    public boolean moveToLast() {
        int i = this.count;
        if (i <= 0) {
            return false;
        }
        this.position = i - 1;
        return true;
    }

    @Override
    public boolean moveToNext() {
        int i = this.position;
        if (i >= this.count - 1) {
            return false;
        }
        this.position = i + 1;
        return true;
    }

    @Override
    public boolean moveToPrevious() {
        int i = this.position;
        if (i <= 0) {
            return false;
        }
        this.position = i - 1;
        return true;
    }

    @Override
    public boolean isFirst() {
        return this.position == 0;
    }

    @Override
    public boolean isLast() {
        return this.position == this.count - 1;
    }

    @Override
    public boolean isBeforeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAfterLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getColumnIndex(String str) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getColumnName(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getColumnNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getBlob(int i) {
        return this.window.getBlob(this.position, i);
    }

    @Override
    public String getString(int i) {
        return this.window.getString(this.position, i);
    }

    @Override
    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getShort(int i) {
        return this.window.getShort(this.position, i);
    }

    @Override
    public int getInt(int i) {
        return this.window.getInt(this.position, i);
    }

    @Override
    public long getLong(int i) {
        return this.window.getLong(this.position, i);
    }

    @Override
    public float getFloat(int i) {
        return this.window.getFloat(this.position, i);
    }

    @Override
    public double getDouble(int i) {
        return this.window.getDouble(this.position, i);
    }

    @Override
    public boolean isNull(int i) {
        return this.window.isNull(this.position, i);
    }

    @Override
    public void deactivate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean requery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isClosed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerContentObserver(ContentObserver contentObserver) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unregisterContentObserver(ContentObserver contentObserver) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getWantsAllOnMoveCalls() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle getExtras() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle respond(Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getType(int i) {
        throw new UnsupportedOperationException();
    }
}
