package com.arena_egypt.nabil.events.activities;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arena_egypt.nabil.events.Message;
import com.arena_egypt.nabil.events.MyNotification;
import com.arena_egypt.nabil.events.R;
import com.arena_egypt.nabil.events.dbmanager.DataManger;
import com.arena_egypt.nabil.events.entities.Event;
import com.arena_egypt.nabil.events.list.RecyclerAdapter;
import com.arena_egypt.nabil.events.list.RecyclerViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter ;
    DataManger dataManger;
    ArrayList<Event> not_selected_events, coming_events;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        nothing but send a notification manually to test the scenarios
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyNotification myNotification = new MyNotification(HomeActivity.this);
                myNotification.make();
            }
        });

        dataManger = new DataManger(this);
//        retrieve Events that selected before
        coming_events = dataManger.getAllData();

//        prepare data for listView
        not_selected_events = getNotSelectedEvents(createDummyData(),coming_events);

//        List setup and data preparation
        recyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(this , not_selected_events);
        recyclerView.setAdapter(adapter);
//        delete from list animation and insert into database
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.getItemAnimator().setRemoveDuration(250);


//        A broadcast receiver to listen to Date changed and Notification Actions
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                final MyNotification myNotification = new MyNotification(HomeActivity.this);

//                Date changed
                if(action.equals(Intent.ACTION_DATE_CHANGED)){

                    if(checkTodayEvents()){
                        myNotification.make();
                    }

//                    Snooze option
                }else if(action.equals("SNOOZE_INTENT")) {
                    myNotification.dismiss();
                    Message.message(HomeActivity.this, " SNOOZE ");

                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while(true ) {
                                    sleep(3000);
                                    myNotification.make();
                                    break;
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();


//                    Dismiss Notification
                }else if(action.equals("DISMISS_INTENT")) {

                    Message.message(HomeActivity.this, "DISMISS");
                    myNotification.dismiss();

                }

            }
        };



//        IntentFilters for Broadcast receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_DATE_CHANGED);
        filter.addAction("SNOOZE_INTENT");
        filter.addAction("DISMISS_INTENT");

//        register the Receiver
        registerReceiver(mReceiver, filter);

    }

//    check if event Today
    private boolean checkTodayEvents() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
        String strDate = mdformat.format(calendar.getTime());
        for(int i=0 ; i<coming_events.size() ; i++){
            if(coming_events.get(i).getDate().equals(strDate)){
                return true;
            }
        }
        return false;
    }

//    Filter the dummy data to get the UNselected Events before and show in the list
    private ArrayList<Event> getNotSelectedEvents (ArrayList<Event> dummy_events ,ArrayList<Event> coming_events){

        if(coming_events.size()!=0)
        for (int i=0 ;i<coming_events.size() ;i++){
            Event event = coming_events.get(i);
            for(int j=0;i<dummy_events.size();j++){
                if(dummy_events.get(j).getName().equals(event.getName()))
                {
                    dummy_events.remove(j);
                    break;
                }
            }
        }
        return dummy_events;
    }

//    creat a Dummy Events
    private ArrayList<Event> createDummyData (){
        ArrayList<Event> dummy_events = new ArrayList<>(10);
        for(int i=1 ; i<=10 ; i++){
            Event event =new Event();
            event.setName("Event "+i);
            event.setDate(i+" / 5 / 2020");

            event.setLocation("Event "+i+" in Cairo,Egypt");
            event.setDescription("Event "+i+" Description");

            dummy_events.add(i-1,event);

        }
        return dummy_events;
    }


//    Click listener for each Event to insert into data base and delete from list
    public void rowClicked(View view) {

        RecyclerViewHolder vholder = (RecyclerViewHolder) view.getTag();
        int position = vholder.getAdapterPosition();

        long id = dataManger.insertData(not_selected_events.get(position).getName(), not_selected_events.get(position).getDate());
        Message.message(this,"Added to DB at "+id);
        not_selected_events.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
