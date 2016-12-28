package com.arena_egypt.nabil.events;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.arena_egypt.nabil.events.activities.HomeActivity;


public class MyNotification {

    Context context ;

    public MyNotification(Context context) {
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void make (){

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent snooze = new Intent();
        snooze.setAction("SNOOZE_INTENT");
        PendingIntent pendingIntentSnooze = PendingIntent.getBroadcast(context, 12345, snooze, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent dismiss = new Intent();
        dismiss.setAction("DISMISS_INTENT");
        PendingIntent pendingIntentDismiss = PendingIntent.getBroadcast(context, 12345, dismiss, PendingIntent.FLAG_UPDATE_CURRENT);



        Notification noti = new Notification.Builder(context)
                .setContentTitle("Event Reminder")
                .setContentText("Event To day").setSmallIcon(R.drawable.event)
                .setSound(soundUri)
                .addAction(R.mipmap.ic_launcher,"SNOOZE",pendingIntentSnooze)
                .addAction(R.mipmap.ic_launcher,"DISMISS",pendingIntentDismiss)
                .setVibrate(new long[] { 300, 300 , 300, 300 })
                .build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);



    }

    public void dismiss(){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}
