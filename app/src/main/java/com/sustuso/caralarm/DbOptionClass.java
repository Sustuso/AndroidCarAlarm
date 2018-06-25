package com.sustuso.caralarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbOptionClass {
        private Context ct;

        public DbOptionClass(Context context) {
            ct = context;
        }

        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "options";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_VALUE = "value";
        }

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME + " TEXT UNIQUE," +
                        FeedEntry.COLUMN_VALUE + " TEXT)";

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
            public void Delete(SQLiteDatabase db,String Name)
            {
                ContentValues values = new ContentValues();
                values.put(FeedEntry.COLUMN_NAME, Name);
                db.delete(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME + "=" + Name, null);
            }

            public void InsertOrUpdate(SQLiteDatabase db,String Name, String Value)
            {

                ContentValues values = new ContentValues();
                values.put(FeedEntry.COLUMN_NAME, Name);
                values.put(FeedEntry.COLUMN_VALUE, Value);
//                db.insert(FeedEntry.TABLE_NAME, null, values);

                int u = db.update(FeedEntry.TABLE_NAME, values, FeedEntry.COLUMN_NAME +" = ?" , new String[] {Name});
                if (u == 0) {
                    db.insertWithOnConflict(FeedEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                }
            }
        }
        public void CreateDB(){
            OptionsDbHelper mDbHelper = new OptionsDbHelper(ct);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            mDbHelper.onUpgrade(db,0,1);
        }

        public void InsertDB(String Name, String Value){
            OptionsDbHelper mDbHelper = new OptionsDbHelper(ct);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            mDbHelper.InsertOrUpdate(db,Name,Value);
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
