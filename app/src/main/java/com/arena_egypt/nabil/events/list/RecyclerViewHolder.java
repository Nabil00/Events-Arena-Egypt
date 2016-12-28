package com.arena_egypt.nabil.events.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arena_egypt.nabil.events.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView name,date,desc,location;
    ImageView imageView;
    LinearLayout layout ;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        layout = (LinearLayout) itemView.findViewById(R.id.row);
        name= (TextView) itemView.findViewById(R.id.list_name);
        desc= (TextView) itemView.findViewById(R.id.list_desc);
        date= (TextView) itemView.findViewById(R.id.list_date);
        location= (TextView) itemView.findViewById(R.id.list_location);
        imageView= (ImageView) itemView.findViewById(R.id.list_event_pic);

    }
}
