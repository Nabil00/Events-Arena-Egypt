package com.arena_egypt.nabil.events;

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void message(Context c , String s) {
        Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
    }
}
