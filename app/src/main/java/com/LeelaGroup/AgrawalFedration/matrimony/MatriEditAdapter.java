package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.R;

import java.util.ArrayList;

/**
 * Created by USer on 16-06-2017.
 */

public class MatriEditAdapter extends RecyclerView.Adapter<MatriEditAdapter.MyViewHolder> {

    Context context;
    ArrayList personImages;

    public MatriEditAdapter(Context context, ArrayList personImages) {
        this.context = context;
        this.personImages = personImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.matrimony_rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items

        holder.image.setImageResource((Integer) personImages.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(context,FormBasicDetailsActivity.class);
                        context.startActivity(intent);
                        break;

                    case 1:Intent intent1=new Intent(context,FormContactInformationActivity.class);
                        context.startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2=new Intent(context,FormSocialAttributeActivity.class);
                        context.startActivity(intent2);
                        break;

                    case 3:Intent intent3=new Intent(context,FormEducationDetailsActivity.class);
                        context.startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4=new Intent(context,FormOccupationDetailsActivity.class);
                        context.startActivity(intent4);
                        break;

                    case 5:Intent intent5=new Intent(context,FormFamilyDetailsActivity.class);
                        context.startActivity(intent5);
                        break;

                    case 6:
                        Intent intent6=new Intent(context,FormPhysicalAttributeActivity.class);
                        context.startActivity(intent6);
                        break;

                    case 7:Intent intent7=new Intent(context,FormOthersActivity.class);
                        context.startActivity(intent7);
                        break;

                    case 8:Intent intent8=new Intent(context,FormPartnerPreferenceActivity.class);
                        context.startActivity(intent8);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return personImages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            //name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
