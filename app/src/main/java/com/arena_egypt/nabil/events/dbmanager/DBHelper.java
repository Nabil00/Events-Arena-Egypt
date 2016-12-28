package com.arena_egypt.nabil.events.dbmanager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.arena_egypt.nabil.events.Message;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EventStore.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.EventEntry.TABLE_NAME + " (" +
                    DBContract.EventEntry._ID + " INTEGER PRIMARY KEY," +
                    DBContract.EventEntry.COLUMN_NAME + " TEXT," +
                    DBContract.EventEntry.COLUMN_DATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.EventEntry.TABLE_NAME;

    private final Context context;


    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
