package com.example.android.a6things.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by holly on 8/16/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "6Things.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_LIST_TABLE =  "CREATE TABLE " + ListContract.ListEntry.TABLE_NAME + " ("
                + ListContract.ListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ListContract.ListEntry.COLUMN_TASK + " TEXT, "
                + ListContract.ListEntry.COLUMN_COMPLETE + " INTEGER DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
