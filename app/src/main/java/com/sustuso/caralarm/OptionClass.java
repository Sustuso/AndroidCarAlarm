package com.sustuso.caralarm;

import android.database.Cursor;

public class OptionClass {
    public String apptype = "NotSet"; //Server, Client, NotSet
    public Boolean active = false;
    public String guid = "";
    public Integer serverrefresh = 30;
    public Boolean debug = true;


    public OptionClass(Cursor cursor) {
        String name;
        String value;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex(DbOptionClass.FeedEntry.COLUMN_NAME));
                value = cursor.getString(cursor.getColumnIndex(DbOptionClass.FeedEntry.COLUMN_VALUE));
                if (name.equals("apptype")) {
                    this.apptype = value;
                }
                if (name.equals("guid")) {
                    this.guid = value;
                }
                if (name.equals("active")) {
                    this.active = Boolean.valueOf(value);
                }
                if (name.equals("serverrefresh")) {
                    this.serverrefresh = Integer.valueOf(value);
                }
                if (name.equals("debug")) {
                    this.debug = Boolean.valueOf(value);
                }
            } while (cursor.moveToNext());

        }
     }
}
