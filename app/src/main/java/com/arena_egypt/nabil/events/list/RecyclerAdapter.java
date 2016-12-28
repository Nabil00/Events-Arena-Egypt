package com.arena_egypt.nabil.events.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arena_egypt.nabil.events.Message;
import com.arena_egypt.nabil.events.R;
import com.arena_egypt.nabil.events.entities.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;


public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerViewHolder> {


    ArrayList<Event> events = new ArrayList<>();

    Context context;
    LayoutInflater inflater;
    public RecyclerAdapter(Context context , ArrayList<Event> events) {
        this.context=context;
        this.events = events ;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.item_list, parent, false);
        RecyclerViewHolder viewHolder=new RecyclerViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.name.setText(events.get(position).getName());
        holder.date.setText(events.get(position).getDate());
        holder.location.setText(events.get(position).getLocation());
        holder.desc.setText(events.get(position).getDescription());

        Picasso.with(context).load(R.drawable.event).into(holder.imageView);
        holder.layout.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


}
