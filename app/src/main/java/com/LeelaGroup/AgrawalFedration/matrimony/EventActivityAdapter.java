package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.matrimony.models.EventsDetails;

import java.util.ArrayList;

/**
 * Created by USer on 25-07-2017.
 */

public class EventActivityAdapter extends RecyclerView.Adapter<EventActivityAdapter.MyViewHolder>{

    ArrayList <EventsDetails> arrayList;
    Context context;

    public EventActivityAdapter(ArrayList<EventsDetails> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.enevts_row_layout,parent,false);

        return new MyViewHolder(view,context,arrayList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        EventsDetails eventsDetails=arrayList.get(position);
        holder.evname.setText(eventsDetails.getEventName());
        holder.evAddress.setText(eventsDetails.getEventAddr());
        holder.evLoc.setText(eventsDetails.getEventLocation());
        holder.evDate.setText(eventsDetails.getEventDate());
        holder.evTime.setText(eventsDetails.getEventTime());
        holder.evDesc.setText(eventsDetails.getEventDesc());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static final class MyViewHolder extends RecyclerView.ViewHolder {


        TextView evname,evAddress,evLoc,evDate,evTime,evDesc,evViewMore;
        LinearLayout layout;
        TextView calanderIcon,mapIcon,clockIcon;
        ArrayList<EventsDetails> eventsDetailses=new ArrayList<EventsDetails>();
        Context ctx;

        public MyViewHolder(View itemView, final Context ctx, final ArrayList<EventsDetails> eventsDetailses) {
            super(itemView);
            this.eventsDetailses=eventsDetailses;
            this.ctx=ctx;


            Typeface icon = Typeface.createFromAsset(ctx.getAssets(), "fontawesome-webfont.ttf" );

            calanderIcon = (TextView)itemView.findViewById( R.id.calendar_icon);
            calanderIcon.setTypeface(icon);

            mapIcon = (TextView)itemView.findViewById( R.id.location_icon);
            mapIcon.setTypeface(icon);

            clockIcon = (TextView)itemView.findViewById( R.id.clock_icon);
            clockIcon.setTypeface(icon);

            evname=(TextView)itemView.findViewById(R.id.tv_evname);
            evAddress=(TextView)itemView.findViewById(R.id.ev_a_addr);
            evLoc=(TextView)itemView.findViewById(R.id.ev_a_loc);
            evDate=(TextView)itemView.findViewById(R.id.evDate);
            evTime=(TextView)itemView.findViewById(R.id.evTime);
            evDesc=(TextView)itemView.findViewById(R.id.evDesc);
            evViewMore=(TextView)itemView.findViewById(R.id.evViewMore);

            layout=(LinearLayout) itemView.findViewById(R.id.erl_parent);

           evViewMore.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (MyUtility.isConnected(ctx)){
                       int position=getAdapterPosition();
                       EventsDetails eventsDetails=eventsDetailses.get(position);
                       Intent intent=new Intent(ctx,EventFullViewActivity.class);
                       intent.putExtra("evname",eventsDetails.getEventName());
                       intent.putExtra("evAddress",eventsDetails.getEventAddr());
                       intent.putExtra("evLoc",eventsDetails.getEventLocation());
                       intent.putExtra("evDate",eventsDetails.getEventDate());
                       intent.putExtra("evTime",eventsDetails.getEventTime());
                       intent.putExtra("evDesc",eventsDetails.getEventDesc());
                       intent.putExtra("evpic",eventsDetails.getEventWedPic());
                       intent.putExtra("ecpatri",eventsDetails.getEventPatrika());
                       ctx.startActivity(intent);
                   }else {
                       MyUtility.internetProblem(layout);
                   }
               }
           });
        }


    }
}
