package com.LeelaGroup.AgrawalFedration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.Social_Refurn.Social_Refurn;
import com.LeelaGroup.AgrawalFedration.business.BusinessActivity;
import com.LeelaGroup.AgrawalFedration.medical.Medical_Module;

import java.util.ArrayList;

/**
 * Created by Adwait on 26/04/2017.
 */

public class ModuleCustomAdapter extends RecyclerView.Adapter<ModuleCustomAdapter.MyViewHolder> {


    MatrimonySession matrimonySession;
    EducationSessionManager educationSessionManager;
    private ArrayList personImages;
    Context context;
    ArrayList ItemNames;

    public ModuleCustomAdapter(Context context, ArrayList personImages ) {
        this.context = context;
        this.personImages = personImages;

    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.image.setImageResource((Integer) personImages.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        educationSessionManager=new EducationSessionManager(context);
                        educationSessionManager.goTomain();
                        break;
                    case 1:
                        Intent intent2 = new Intent(context, BusinessActivity.class);
                        context.startActivity(intent2);
                        break;
                    case 2:
                        Intent intent=new Intent(context,Medical_Module.class);
                        context.startActivity(intent);
                        break;
                    case 3:
                        matrimonySession=new MatrimonySession(context);
                        matrimonySession.goTomain();
                        break;
                  /*  case 4:
                        Intent intent4 = new Intent(context, Social_Refurn.class);
                        context.startActivity(intent4);
                        break;*/


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

        ImageView image;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            image = (ImageView) itemView.findViewById(R.id.image);
            //textView = (TextView) itemView.findViewById(R.id.text);



        }
    }
}
