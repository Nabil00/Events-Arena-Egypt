package com.arena_egypt.nabil.events.dbmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arena_egypt.nabil.events.Message;
import com.arena_egypt.nabil.events.entities.Event;

import java.io.IOException;
import java.util.ArrayList;

public class DataManger {

    private DBHelper helper;
    public DataManger (Context context){
        helper = new DBHelper(context);
    }

    public long insertData (String name , String date){

        ContentValues values = new ContentValues();
        values.put(DBContract.EventEntry.COLUMN_NAME,name);
        values.put(DBContract.EventEntry.COLUMN_DATE,date);

        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            return db.insert(DBContract.EventEntry.TABLE_NAME, null, values);
        }catch (Exception e){
            return -1;
        }
    }

    public ArrayList<Event> getAllData (){
        SQLiteDatabase db = helper.getWritableDatabase();

        String []column = {DBContract.EventEntry.COLUMN_NAME,DBContract.EventEntry.COLUMN_DATE};
        Cursor cursor = db.query(DBContract.EventEntry.TABLE_NAME,column,null,null,null,null,null,null);


        ArrayList<Event> coming_events = new ArrayList<>(cursor.getCount());

        while (cursor.moveToNext()){
            Event event = new Event(cursor.getString(0),null,null,cursor.getString(1));
            coming_events.add(event);
        }
        return coming_events ;
    }

    public String delSomeData(String oldName) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String whereArgs []= {oldName};
        int count = db.delete(DBContract.EventEntry.TABLE_NAME,"name =?",whereArgs);
        return count+"";
    }

}
