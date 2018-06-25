package com.sustuso.caralarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.Date;

public class DbLogClass {
    private Context ct;

    public DbLogClass(Context context) {
        ct = context;
    }

    public class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "log";
        public static final String COLUMN_DATETIME = "datetime";
        public static final String COLUMN_ALARM = "alarm";
        public static final String COLUMN_MESSAGE = "message";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_DATETIME + " TEXT ," +
                    FeedEntry.COLUMN_ALARM + " DATETIME ," +
                    FeedEntry.COLUMN_MESSAGE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public class OptionsDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "CarAlarm.db";

        public OptionsDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
//        public void Delete(SQLiteDatabase db,String Name)
//        {
//            ContentValues values = new ContentValues();
//            values.put(FeedEntry.COLUMN_NAME, Name);
//            db.delete(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME + "=" + Name, null);
//        }

        public void InsertOrUpdate(SQLiteDatabase db, Date dateTime, String alarm, String message)
        {
            ContentValues values = new ContentValues();
            values.put(FeedEntry.COLUMN_DATETIME, dateTime.toString());
            values.put(FeedEntry.COLUMN_ALARM, alarm);
            values.put(FeedEntry.COLUMN_MESSAGE, message);

            String query = "Select * from "+ FeedEntry.TABLE_NAME+" where "
                    + FeedEntry.COLUMN_DATETIME + " = (Select MAX("+FeedEntry.COLUMN_DATETIME+") from "+ FeedEntry.TABLE_NAME +")";
            Cursor cursor = db.rawQuery(query,null);

            if (cursor != null && cursor.moveToFirst()){
                if (cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_ALARM)).equals(alarm)){
                    db.update(FeedEntry.TABLE_NAME, values, "_id = ?" , new String[] {cursor.getString( cursor.getColumnIndex(FeedEntry._ID))});
                }else{
                    db.insertWithOnConflict(FeedEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                }
            }else{
                db.insertWithOnConflict(FeedEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }

        }
    }
    public void CreateDB(){
        OptionsDbHelper mDbHelper = new OptionsDbHelper(ct);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.onUpgrade(db,0,1);
    }

    public void InsertDB(Date datetime, String alarm, String message){
        OptionsDbHelper mDbHelper = new OptionsDbHelper(ct);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.InsertOrUpdate(db, datetime, alarm, message);
    }

    public Cursor ReadDB() {
        OptionsDbHelper mDbHelper = new OptionsDbHelper(ct);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

//            String[] projection = {
//                    BaseColumns._ID,
//                    FeedEntry.COLUMN_NAME,
//                    FeedEntry.COLUMN_VALUE
//            };

//            String selection = FeedEntry.COLUMN_NAME + " = ?";
//            String[] selectionArgs = { "My Title" };

//            String sortOrder = "";
//                    FeedEntry.COLUMN_VALUE + " DESC";

//            Cursor cursor = db.query(
//                    FeedEntry.TABLE_NAME,   // The table to query
//                    projection,             // The array of columns to return (pass null to get all)
//                    selection,              // The columns for the WHERE clause
//                    selectionArgs,          // The values for the WHERE clause
//                    null,                   // don't group the rows
//                    null,                   // don't filter by row groups
//                    sortOrder               // The sort order
//            );


        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        return (cursor);
    }

}
