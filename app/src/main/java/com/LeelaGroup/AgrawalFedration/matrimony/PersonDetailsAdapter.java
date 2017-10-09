package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.matrimony.models.FetchFilterDetail;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by USer on 25-04-2017.
 */

public class PersonDetailsAdapter extends RecyclerView.Adapter<PersonDetailsAdapter.PersonDetailsViewHolder> {
    ArrayList<FetchFilterDetail> personDetails=new ArrayList<FetchFilterDetail>();
    Context ctx;


    public PersonDetailsAdapter(ArrayList<FetchFilterDetail> personDetails, Context ctx) {
        this.personDetails = personDetails;
        this.ctx=ctx;
    }

    @Override
    public PersonDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.person_details_layout,parent,false);
        PersonDetailsViewHolder personDetailsViewHolder=new PersonDetailsViewHolder(view,ctx,personDetails);
        return personDetailsViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonDetailsViewHolder holder, int position) {
        FetchFilterDetail PERDETL =personDetails.get(position);

        Glide.with(ctx).load(PERDETL.getMregProfPic()).placeholder(R.drawable.profile_circle).dontAnimate().into(holder.persnImg);
        String s=PERDETL.getMregProfPic();
        holder.pId.setText(PERDETL.getId());
        holder.pAge.setText(PERDETL.getAge());
        holder.pname.setText(PERDETL.getName());
        holder.pDesignation.setText(PERDETL.getEducation());

    }

    @Override
    public int getItemCount() {
        return personDetails.size();
    }

    public static class PersonDetailsViewHolder extends RecyclerView.ViewHolder implements OnClickListener
    {

        ImageView persnImg;
        TextView pId,pAge,pname,pDesignation;
        ArrayList<FetchFilterDetail> personDetails=new ArrayList<FetchFilterDetail>();
        LinearLayout layout;
        Context ctx;


        public PersonDetailsViewHolder(View itemView,Context ctx,ArrayList<FetchFilterDetail> personDetails) {
            super(itemView);
            this.personDetails=personDetails;
            this.ctx=ctx;
            itemView.setOnClickListener(this);


            persnImg=(ImageView)itemView.findViewById(R.id.person_image);
            pId=(TextView)itemView.findViewById(R.id.person_id);
            pAge=(TextView)itemView.findViewById(R.id.person_age);
            pname=(TextView)itemView.findViewById(R.id.person_name);
            pDesignation=(TextView)itemView.findViewById(R.id.person_designation);
            layout=(LinearLayout)itemView.findViewById(R.id.pdl_parent);
        }

        @Override
        public void onClick(View v) {
            if (MyUtility.isConnected(ctx)){
                int posittion=getAdapterPosition();
                FetchFilterDetail personDetail=this.personDetails.get(posittion);
                Intent intent=new Intent(this.ctx,PersonDeailsAbstract.class);
                intent.putExtra("mat_id",personDetail.getMat_id());
                this.ctx.startActivity(intent);
            }else {
                MyUtility.internetProblem(layout);
            }
        }
    }

    public void setFilter(ArrayList<FetchFilterDetail> filterList)
    {
        personDetails=new ArrayList<>();
        personDetails.addAll(filterList);
        notifyDataSetChanged();

    }
}
