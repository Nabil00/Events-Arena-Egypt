package com.arena_egypt.nabil.events.dbmanager;

import android.content.Context;
import android.provider.BaseColumns;

/**
 * Created by Nabil on 12/27/2016.
 */
public class DBContract {


    public DBContract() {
    }

    public static class EventEntry implements BaseColumns {
        public static final String TABLE_NAME = "events";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";

    }
}
