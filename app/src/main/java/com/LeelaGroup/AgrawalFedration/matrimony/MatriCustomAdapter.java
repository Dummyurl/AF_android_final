package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.R;

import java.util.ArrayList;

/**
 * Created by USer on 26-04-2017.
 */

public class MatriCustomAdapter extends RecyclerView.Adapter<MatriCustomAdapter.MyViewHolder> {


    //ArrayList personNames;
    ArrayList personImages;
    Context context;
    String mat_id;

    public MatriCustomAdapter(Context context, ArrayList personImages, String mat_id) {
        this.context = context;
        //this.personNames = personNames;
        this.personImages = personImages;
        this.mat_id = mat_id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.matrimony_rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // set the data in items

        holder.image.setImageResource((Integer) personImages.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:

                        if (MyUtility.isConnected(context)) {

                            Intent intent = new Intent(context, BasicDetailsActivity.class);
                            intent.putExtra("mat_id", mat_id);
                            context.startActivity(intent);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }
                        break;

                    case 1:
                        if (MyUtility.isConnected(context)) {

                            Intent intent1 = new Intent(context, ContactInformationActivity.class);
                            intent1.putExtra("mat_id", mat_id);
                            context.startActivity(intent1);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }
                        break;

                    case 2:
                        if (MyUtility.isConnected(context)) {

                            Intent intent2 = new Intent(context, SocialAttributeActivity.class);
                            intent2.putExtra("mat_id", mat_id);
                            context.startActivity(intent2);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }

                        break;

                    case 3:
                        if (MyUtility.isConnected(context)) {

                            Intent intent3 = new Intent(context, EducationDetailsActivity.class);
                            intent3.putExtra("mat_id", mat_id);
                            context.startActivity(intent3);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }
                        break;

                    case 4:
                        if (MyUtility.isConnected(context)) {
                            Intent intent4 = new Intent(context, OccupationDetailsActivity.class);
                            intent4.putExtra("mat_id", mat_id);
                            context.startActivity(intent4);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }
                        break;

                    case 5:
                        if (MyUtility.isConnected(context)) {
                            Intent intent5 = new Intent(context, FamilyDetailsActivity.class);
                            intent5.putExtra("mat_id", mat_id);
                            context.startActivity(intent5);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }
                        break;

                    case 6:
                        if (MyUtility.isConnected(context)) {
                            Intent intent6 = new Intent(context, PhysicalAttributeActivity.class);
                            intent6.putExtra("mat_id", mat_id);
                            context.startActivity(intent6);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }
                        break;

                    case 7:
                        if (MyUtility.isConnected(context)) {
                            Intent intent7 = new Intent(context, OtherActivity.class);
                            intent7.putExtra("mat_id", mat_id);
                            context.startActivity(intent7);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }
                        break;

                    case 8:
                        if (MyUtility.isConnected(context)) {
                            Intent intent8 = new Intent(context, PartnerPreferenceActivity.class);
                            intent8.putExtra("mat_id", mat_id);
                            context.startActivity(intent8);
                        } else {
                            MyUtility.internetProblem(holder.layout);
                        }
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
        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            //name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
            layout = (LinearLayout) itemView.findViewById(R.id.ml_parent);
        }
    }
}
