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
import com.LeelaGroup.AgrawalFedration.matrimony.models.ProfileModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by USer on 22-08-2017.
 */

public class MyCreatedProfilesAdapter extends RecyclerView.Adapter<MyCreatedProfilesAdapter.MyViewHolder> {
    ArrayList<ProfileModel> profileModelArrayList;
    Context context;

    public MyCreatedProfilesAdapter(ArrayList<ProfileModel> profileModelArrayList, Context context) {
        this.profileModelArrayList = profileModelArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_profiles_row_layout,parent,false);

        return new MyViewHolder(view,context,profileModelArrayList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProfileModel profileModel=profileModelArrayList.get(position);
        Glide.with(context).load(profileModel.getMregProfPic()).placeholder(R.drawable.profile_circle).dontAnimate().into(holder.pic);
        holder.name.setText(profileModel.getMregFname()+" "+profileModel.getMregLname());
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return profileModelArrayList.size();
    }

    public static final class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView pic;
        ArrayList<ProfileModel> profileModels;
        Context ctx;


        public MyViewHolder(View itemView, final Context ctx, final ArrayList<ProfileModel> profileModels) {
            super(itemView);
            this.profileModels=profileModels;
            this.ctx=ctx;

            name=(TextView)itemView.findViewById(R.id.tv_pname);
            pic=(ImageView)itemView.findViewById(R.id.iv_pro_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    ProfileModel profileModel=profileModels.get(position);
                    Intent intent=new Intent(ctx,MyProfileActivity.class);
                    intent.putExtra("pid",profileModel.getMatBasicContId());
                    ctx.startActivity(intent);
                }
            });
           /* itemView.setOnClickListener(this);*/

        }

       /* @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Toast.makeText(ctx, position, Toast.LENGTH_SHORT).show();
        }*/
    }
}
