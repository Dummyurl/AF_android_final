package com.LeelaGroup.AgrawalFedration.notification;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.business.BusinessModuleClick;

import java.util.ArrayList;

/**
 * Created by USer on 12-09-2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {


   private ArrayList<NotifyPojo> arrayList;
    private Context context;

    public NotificationAdapter(ArrayList<NotifyPojo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_row_layout, parent, false);
        return new NotificationViewHolder(layoutView,arrayList,context);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
     /*   holder.Title.setText(arrayList.get(position));
        holder.Message.setText(arrayList.get(position));
        holder.Time.setText(arrayList.get(position));*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  static final class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ArrayList<NotifyPojo> arrayList;
        private Context context;
        TextView Title,Message,Time;
        public NotificationViewHolder(View itemView, ArrayList<NotifyPojo> arrayList, Context context) {
            super(itemView);
            this.arrayList = arrayList;
            this.context = context;


            Title=(TextView)itemView.findViewById(R.id.title);
            Message=(TextView)itemView.findViewById(R.id.message);
            Time=(TextView)itemView.findViewById(R.id.time);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            NotifyPojo notifyPojo=this.arrayList.get(position);
            Intent intent=new Intent(this.context,BusinessModuleClick.class);
           /* intent.putExtra("title",notifyPojo.);
            intent.putExtra("message",notifyPojo.);
            intent.putExtra("time",notifyPojo.);*/
            this.context.startActivity(intent);
        }
    }
}
