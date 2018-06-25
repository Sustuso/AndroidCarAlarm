package com.sustuso.caralarm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.Date;


public class DbClass {

    public boolean createOptionTable(Context context)
    {
        DbOptionClass dbopt = new DbOptionClass(context);
        dbopt.CreateDB();
        return true;
    }

    public void insertOptionTable(Context context, String Name, String Value)
    {
        DbOptionClass dbopt = new DbOptionClass(context);
        dbopt.InsertDB(Name, Value);

    }

    public OptionClass readOptionTable(Context context)
    {
        try
        {
            DbOptionClass dbopt = new DbOptionClass(context);
            OptionClass optionclass = new OptionClass(dbopt.ReadDB());
            return (optionclass);
        } catch (Exception exc){
            return (null);
        }

    }

    public boolean checkExistDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase("CarAlarm.db", null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {

        }
        return checkDB != null;
    }

    public boolean createLogTable(Context context)
    {
        DbLogClass dblog = new DbLogClass(context);
        dblog.CreateDB();
        return true;
    }

    public void insertLogTable(Context context, Date DateTime, String Alarm, String Message)
    {
        DbLogClass dblog = new DbLogClass(context);
        dblog.InsertDB(DateTime, Alarm, Message);

    }

    public OptionClass readLogTable(Context context)
    {
        try
        {
//            DbLogClass dblog = new DbLogClass(context);
//            OptionClass optionclass = new OptionClass(dbopt.ReadDB());
            return (null);
        } catch (Exception exc){
            return (null);
        }

    }




}
