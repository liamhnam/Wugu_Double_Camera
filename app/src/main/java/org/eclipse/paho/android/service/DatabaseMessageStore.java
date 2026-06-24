package org.eclipse.paho.android.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Iterator;
import java.util.UUID;
import org.eclipse.paho.android.service.MessageStore;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class DatabaseMessageStore implements MessageStore {
    private static final String ARRIVED_MESSAGE_TABLE_NAME = "MqttArrivedMessageTable";
    private static final String MTIMESTAMP = "mtimestamp";
    private static final String TAG = "DatabaseMessageStore";

    private SQLiteDatabase f3773db = null;
    private MQTTDatabaseHelper mqttDb;
    private MqttTraceHandler traceHandler;

    private static class MQTTDatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "mqttAndroidService.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TAG = "MQTTDatabaseHelper";
        private MqttTraceHandler traceHandler;

        public MQTTDatabaseHelper(MqttTraceHandler mqttTraceHandler, Context context) {
            super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
            this.traceHandler = mqttTraceHandler;
        }

        @Override
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.traceHandler.traceDebug(TAG, "onCreate {CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);}");
            try {
                sQLiteDatabase.execSQL("CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);");
                this.traceHandler.traceDebug(TAG, "created the table");
            } catch (SQLException e) {
                this.traceHandler.traceException(TAG, "onCreate", e);
                throw e;
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.traceHandler.traceDebug(TAG, "onUpgrade");
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS MqttArrivedMessageTable");
                onCreate(sQLiteDatabase);
                this.traceHandler.traceDebug(TAG, "onUpgrade complete");
            } catch (SQLException e) {
                this.traceHandler.traceException(TAG, "onUpgrade", e);
                throw e;
            }
        }
    }

    public DatabaseMessageStore(MqttService mqttService, Context context) {
        this.mqttDb = null;
        this.traceHandler = mqttService;
        this.mqttDb = new MQTTDatabaseHelper(this.traceHandler, context);
        this.traceHandler.traceDebug(TAG, "DatabaseMessageStore<init> complete");
    }

    @Override
    public String storeArrived(String str, String str2, MqttMessage mqttMessage) {
        this.f3773db = this.mqttDb.getWritableDatabase();
        this.traceHandler.traceDebug(TAG, "storeArrived{" + str + "}, {" + mqttMessage.toString() + "}");
        byte[] payload = mqttMessage.getPayload();
        int qos = mqttMessage.getQos();
        boolean zIsRetained = mqttMessage.isRetained();
        boolean zIsDuplicate = mqttMessage.isDuplicate();
        ContentValues contentValues = new ContentValues();
        String string = UUID.randomUUID().toString();
        contentValues.put(MqttServiceConstants.MESSAGE_ID, string);
        contentValues.put(MqttServiceConstants.CLIENT_HANDLE, str);
        contentValues.put(MqttServiceConstants.DESTINATION_NAME, str2);
        contentValues.put(MqttServiceConstants.PAYLOAD, payload);
        contentValues.put(MqttServiceConstants.QOS, Integer.valueOf(qos));
        contentValues.put(MqttServiceConstants.RETAINED, Boolean.valueOf(zIsRetained));
        contentValues.put(MqttServiceConstants.DUPLICATE, Boolean.valueOf(zIsDuplicate));
        contentValues.put(MTIMESTAMP, Long.valueOf(System.currentTimeMillis()));
        try {
            this.f3773db.insertOrThrow(ARRIVED_MESSAGE_TABLE_NAME, null, contentValues);
            this.traceHandler.traceDebug(TAG, "storeArrived: inserted message with id of {" + string + "} - Number of messages in database for this clientHandle = " + getArrivedRowCount(str));
            return string;
        } catch (SQLException e) {
            this.traceHandler.traceException(TAG, "onUpgrade", e);
            throw e;
        }
    }

    private int getArrivedRowCount(String str) {
        Cursor cursorQuery = this.f3773db.query(ARRIVED_MESSAGE_TABLE_NAME, new String[]{MqttServiceConstants.MESSAGE_ID}, "clientHandle=?", new String[]{str}, null, null, null);
        int i = cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        cursorQuery.close();
        return i;
    }

    @Override
    public boolean discardArrived(String str, String str2) {
        this.f3773db = this.mqttDb.getWritableDatabase();
        this.traceHandler.traceDebug(TAG, "discardArrived{" + str + "}, {" + str2 + "}");
        try {
            int iDelete = this.f3773db.delete(ARRIVED_MESSAGE_TABLE_NAME, "messageId=? AND clientHandle=?", new String[]{str2, str});
            if (iDelete != 1) {
                this.traceHandler.traceError(TAG, "discardArrived - Error deleting message {" + str2 + "} from database: Rows affected = " + iDelete);
                return false;
            }
            this.traceHandler.traceDebug(TAG, "discardArrived - Message deleted successfully. - messages in db for this clientHandle " + getArrivedRowCount(str));
            return true;
        } catch (SQLException e) {
            this.traceHandler.traceException(TAG, "discardArrived", e);
            throw e;
        }
    }

    @Override
    public Iterator<MessageStore.StoredMessage> getAllArrivedMessages(String str) {
        return new Iterator<MessageStore.StoredMessage>(str) {

            private Cursor f3774c;
            private boolean hasNext;
            private final String[] selectionArgs;
            final String val$clientHandle;

            {
                this.val$clientHandle = str;
                String[] strArr = {str};
                this.selectionArgs = strArr;
                DatabaseMessageStore.this.f3773db = DatabaseMessageStore.this.mqttDb.getWritableDatabase();
                if (str == null) {
                    this.f3774c = DatabaseMessageStore.this.f3773db.query(DatabaseMessageStore.ARRIVED_MESSAGE_TABLE_NAME, null, null, null, null, null, "mtimestamp ASC");
                } else {
                    this.f3774c = DatabaseMessageStore.this.f3773db.query(DatabaseMessageStore.ARRIVED_MESSAGE_TABLE_NAME, null, "clientHandle=?", strArr, null, null, "mtimestamp ASC");
                }
                this.hasNext = this.f3774c.moveToFirst();
            }

            @Override
            public boolean hasNext() {
                if (!this.hasNext) {
                    this.f3774c.close();
                }
                return this.hasNext;
            }

            @Override
            public MessageStore.StoredMessage next() {
                Cursor cursor = this.f3774c;
                String string = cursor.getString(cursor.getColumnIndex(MqttServiceConstants.MESSAGE_ID));
                Cursor cursor2 = this.f3774c;
                String string2 = cursor2.getString(cursor2.getColumnIndex(MqttServiceConstants.CLIENT_HANDLE));
                Cursor cursor3 = this.f3774c;
                String string3 = cursor3.getString(cursor3.getColumnIndex(MqttServiceConstants.DESTINATION_NAME));
                Cursor cursor4 = this.f3774c;
                byte[] blob = cursor4.getBlob(cursor4.getColumnIndex(MqttServiceConstants.PAYLOAD));
                Cursor cursor5 = this.f3774c;
                int i = cursor5.getInt(cursor5.getColumnIndex(MqttServiceConstants.QOS));
                Cursor cursor6 = this.f3774c;
                boolean z = Boolean.parseBoolean(cursor6.getString(cursor6.getColumnIndex(MqttServiceConstants.RETAINED)));
                Cursor cursor7 = this.f3774c;
                boolean z2 = Boolean.parseBoolean(cursor7.getString(cursor7.getColumnIndex(MqttServiceConstants.DUPLICATE)));
                MqttMessageHack mqttMessageHack = DatabaseMessageStore.this.new MqttMessageHack(blob);
                mqttMessageHack.setQos(i);
                mqttMessageHack.setRetained(z);
                mqttMessageHack.setDuplicate(z2);
                this.hasNext = this.f3774c.moveToNext();
                return DatabaseMessageStore.this.new DbStoredData(string, string2, string3, mqttMessageHack);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            protected void finalize() throws Throwable {
                this.f3774c.close();
                super.finalize();
            }
        };
    }

    @Override
    public void clearArrivedMessages(String str) {
        int iDelete;
        this.f3773db = this.mqttDb.getWritableDatabase();
        String[] strArr = {str};
        if (str == null) {
            this.traceHandler.traceDebug(TAG, "clearArrivedMessages: clearing the table");
            iDelete = this.f3773db.delete(ARRIVED_MESSAGE_TABLE_NAME, null, null);
        } else {
            this.traceHandler.traceDebug(TAG, "clearArrivedMessages: clearing the table of " + str + " messages");
            iDelete = this.f3773db.delete(ARRIVED_MESSAGE_TABLE_NAME, "clientHandle=?", strArr);
        }
        this.traceHandler.traceDebug(TAG, "clearArrivedMessages: rows affected = " + iDelete);
    }

    private class DbStoredData implements MessageStore.StoredMessage {
        private String clientHandle;
        private MqttMessage message;
        private String messageId;
        private String topic;

        DbStoredData(String str, String str2, String str3, MqttMessage mqttMessage) {
            this.messageId = str;
            this.topic = str3;
            this.message = mqttMessage;
        }

        @Override
        public String getMessageId() {
            return this.messageId;
        }

        @Override
        public String getClientHandle() {
            return this.clientHandle;
        }

        @Override
        public String getTopic() {
            return this.topic;
        }

        @Override
        public MqttMessage getMessage() {
            return this.message;
        }
    }

    private class MqttMessageHack extends MqttMessage {
        public MqttMessageHack(byte[] bArr) {
            super(bArr);
        }

        @Override
        protected void setDuplicate(boolean z) {
            super.setDuplicate(z);
        }
    }

    @Override
    public void close() {
        SQLiteDatabase sQLiteDatabase = this.f3773db;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }
}
